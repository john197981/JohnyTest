package com.oifm.forum;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; 
import java.util.ArrayList;
 
import org.apache.log4j.Logger;

import com.oifm.base.OIBaseDao;
import com.oifm.useradmin.OIDAOMemberProfile;
import com.oifm.utility.DateUtility;
import com.oifm.utility.OIEncrypter;
import com.oifm.utility.OIUtilities;

public class OIDAOForumWebListing extends OIBaseDao
{
    Logger logger = Logger.getLogger(OIDAOForumWebListing.class.getName());
    
    public ArrayList readHottestThread(String pUserId,Connection connection) throws Exception
    {
        ArrayList arOIBVHottestThread = new ArrayList();;
        OIBVHottestThread aOIBVHottestThread = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs=null;
        try
        {
            preparedStatement = connection.prepareStatement(OIForumSqls.READ_HOTTEST_THREAD);
            preparedStatement.setString(1,pUserId);
            preparedStatement.setString(2,pUserId);
            preparedStatement.setString(3,pUserId);
            preparedStatement.setString(4,pUserId);
            rs = preparedStatement.executeQuery();
            String flag="";
            int i=0;
            int threadId=0;
            while(rs.next())
            {
                if (threadId!=rs.getInt("THREADID") && rs.getString("TTYPE").equals("H"))
                {
                    threadId=rs.getInt("THREADID");
	                i++;
	                if (i>=4)
	                    break;
                    flag="";
	                aOIBVHottestThread = new OIBVHottestThread();
	                aOIBVHottestThread.setThreadId(rs.getString("THREADID"));
	                aOIBVHottestThread.setTitle(rs.getString("TITLE"));
	                /*if (rs.getInt("count")>=2)
	                    aOIBVHottestThread.setLatestFlag("true");
	                else
	                    aOIBVHottestThread.setLatestFlag("false");*/
	                aOIBVHottestThread.setNumberOfPosts(rs.getString("count"));
                    aOIBVHottestThread.setPostedOn(DateUtility.getMMDDYYYYStringFromDate(rs.getDate("POSTED_ON")));
	                arOIBVHottestThread.add(aOIBVHottestThread);
                }
                if (rs.getString("TTYPE").equals("H") && flag.equals(""))
                {
                    flag="H";
                    aOIBVHottestThread.setLatestFlag("false");
                }
                if (rs.getString("TTYPE").equals("L") && flag.equals("H"))
                {
                    flag="L";
                    aOIBVHottestThread.setLatestFlag("true");
                }
            }
        }
        catch(Exception e)
        {
            logger.error("readHottestThread -" + e.getMessage());
            throw e;
        }
        finally
        {
        	if (rs!=null)
                rs.close();
           if (preparedStatement!=null)
               preparedStatement.close();
        }
        if (arOIBVHottestThread.size()==0)
            arOIBVHottestThread=null;
        return arOIBVHottestThread;
    }

    public ArrayList readLatestThread(String pUserId,Connection connection) throws Exception
    {
        ArrayList arOIBVLatestThread = new ArrayList();;
        OIBVHottestThread aOIBVHottestThread = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs=null;
        try
        {
            preparedStatement = connection.prepareStatement(OIForumSqls.READ_LATEST_THREAD);
            preparedStatement.setString(1,pUserId);
            preparedStatement.setString(2,pUserId);
            rs = preparedStatement.executeQuery();
            while(rs.next())
            {
                aOIBVHottestThread = new OIBVHottestThread();
                aOIBVHottestThread.setTopicName(rs.getString("TOPICNAME"));
                aOIBVHottestThread.setTopicId(rs.getString("TOPICID"));
                //aOIBVHottestThread.setTitle(rs.getString("TITLE"));

                if (rs.getInt("count")>=2)
                    aOIBVHottestThread.setLatestFlag("true");
                else
                    aOIBVHottestThread.setLatestFlag("false");
                arOIBVLatestThread.add(aOIBVHottestThread);
            }
        }
        catch(Exception e)
        {
            logger.error("readLatestThread -" + e.getMessage());
            throw e;
        }
        finally
        {
        	if (rs!=null)
                rs.close();
            if (preparedStatement!=null)
                preparedStatement.close();
            
        }
        if (arOIBVLatestThread.size()==0)
            arOIBVLatestThread=null;
        return arOIBVLatestThread;
    }


    public ArrayList readHQReplies(String pUserId,Connection connection) throws Exception
    {
        ArrayList arOIBVHQReplies = new ArrayList();;
        OIBVHottestThread aOIBVHottestThread = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs=null;
        try
        {
            preparedStatement = connection.prepareStatement(OIForumSqls.READ_HQ_REPLIES);
            preparedStatement.setString(1,pUserId);
            preparedStatement.setString(2,pUserId);
            preparedStatement.setString(3,pUserId);
            preparedStatement.setString(4,pUserId);
            rs = preparedStatement.executeQuery();
            int threadId=0;
            String flag="";
            int i=0;
            while(rs.next())
            {
                if (threadId!=rs.getInt("THREADID"))
                {
                    i++;
                    if (i>=4)
                        break;
                    flag="";
                    threadId=rs.getInt("THREADID");
                    aOIBVHottestThread = new OIBVHottestThread();
                    aOIBVHottestThread.setThreadId(rs.getString("THREADID"));
                    aOIBVHottestThread.setTitle(rs.getString("TITLE"));
                    arOIBVHQReplies.add(aOIBVHottestThread);
                }
                if (rs.getString("TTYPE").equals("H") && flag.equals(""))
                {
                    flag="H";
                    aOIBVHottestThread.setLatestFlag("H");
                }
                if (rs.getString("TTYPE").equals("L") && flag.equals("H"))
                    aOIBVHottestThread.setLatestFlag("HL");
                if (rs.getString("TTYPE").equals("L") && flag.equals(""))
                    aOIBVHottestThread.setLatestFlag("L");
            }
        }
        catch(Exception e)
        {
            logger.error("readHottestThread -" + e.getMessage());
            throw e;
        }
        finally
        {
        	if (rs!=null)
                rs.close();
            if (preparedStatement!=null)
                preparedStatement.close();
            
        }
        if (arOIBVHQReplies.size()==0)
            arOIBVHQReplies=null;
        return arOIBVHQReplies;
    }

    public ArrayList readUpToTopicListing(Connection connection) throws Exception
    {
        ArrayList arOIBVUpToTopicListing = new ArrayList();
        OIBVForumListing aOIBVForumListing = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs=null;
        try
        {
            preparedStatement = connection.prepareStatement(OIForumSqls.READ_UPTO_TOPIC);
            rs = preparedStatement.executeQuery();
            
            while (rs.next())
            {
                aOIBVForumListing = new OIBVForumListing();
                aOIBVForumListing.setLevel(rs.getString("HRNO"));
                
                if (rs.getInt("HRNO")==1)
                {
                    aOIBVForumListing.setCategory(rs.getString("NAME"));
                    aOIBVForumListing.setCategoryId(rs.getString("TOPICID"));
                }	
                if (rs.getInt("HRNO")==2)
                {
                    aOIBVForumListing.setBoardName(rs.getString("NAME"));
                	aOIBVForumListing.setBoardId(rs.getString("TOPICID"));
                }
                if (rs.getInt("HRNO")==3)
                {
                    aOIBVForumListing.setTopicName(rs.getString("NAME"));
                    aOIBVForumListing.setTopicId(rs.getString("TOPICID"));
                }
                arOIBVUpToTopicListing.add(aOIBVForumListing);
            }
        }
        catch(Exception e)
        {
            logger.error("readUpToTopicListing -" + e.getMessage());
            throw e;
        }
        finally
        {
        	if (rs!=null)
                rs.close();
        	if (preparedStatement!=null)
                preparedStatement.close();
            
        }
        if (arOIBVUpToTopicListing.size()==0)
            arOIBVUpToTopicListing=null;
        return arOIBVUpToTopicListing;
    }

    public ArrayList readPostListing(String pUserId,ArrayList arOIBVUpToTopicListing,Connection connection,String strMode) throws Exception
    {
        OIBVForumListing aOIBVForumListing = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs=null;
		//Amended by Zhao Yu on 2008-Aug-21
		//Encrypt user's nric
		String eName = "";        
        try
        {
        	String strQry=OIForumSqls.READ_POST_LIST1;
            if(OIUtilities.replaceNull(strMode).equalsIgnoreCase("12") || 
            		OIUtilities.replaceNull(strMode).equalsIgnoreCase("24") || 
					OIUtilities.replaceNull(strMode).equalsIgnoreCase("72")){
            	strQry= strQry+" AND ROUND((SYSDATE-POSTED_ON)*24) <="+Integer.parseInt(strMode);
        	
            }else if(OIUtilities.replaceNull(strMode).equalsIgnoreCase("ViewUserPosting")){
            	strQry= strQry+" and POSTED_BY='"+pUserId+"' ";
        	}
            
        	strQry = strQry + OIForumSqls.READ_POST_LIST2;
 
            preparedStatement = connection.prepareStatement(strQry);
            preparedStatement.setString(1,pUserId);
            preparedStatement.setString(2,pUserId);
            preparedStatement.setString(3,pUserId);
            rs = preparedStatement.executeQuery();
            int topicId=0;
            OIDAOMemberProfile objMemberDAO=null;

            while (rs.next())
            {
                if (topicId!=rs.getInt("topicid"))
                {
                    topicId = rs.getInt("topicid");
	                for (int i=0;i<arOIBVUpToTopicListing.size();i++)
	                {
	                    aOIBVForumListing = (OIBVForumListing) arOIBVUpToTopicListing.get(i);
	                    if (aOIBVForumListing.getTopicId()!=null && aOIBVForumListing.getTopicId().equalsIgnoreCase(rs.getString("topicid")))
	                    {
	                    	objMemberDAO=new OIDAOMemberProfile();
	                        aOIBVForumListing.setThreadName(rs.getString("TITLE"));
	                        aOIBVForumListing.setThreadId(rs.getString("THREADID"));
	                        aOIBVForumListing.setNickName(rs.getString("NICKNAME"));
	                        eName = OIEncrypter.encrypt(rs.getString("POSTED_BY"));	                        
	                        aOIBVForumListing.setPostedBy(eName);
	                        aOIBVForumListing.setNoThreads(rs.getString("COUNT"));
	                        aOIBVForumListing.setLastPostOn(DateUtility.getMMDDYYYYStringFromDate(rs.getDate("POSTED_ON")));
	                        //Added by anbu
	                        String strIcon=objMemberDAO.checkSticky(pUserId,aOIBVForumListing.getThreadId(),connection);
	                        if(strIcon.equals("")){
	                        	strIcon=objMemberDAO.hotLatMsg(""+topicId,aOIBVForumListing.getThreadId(),connection);
		                        if(strIcon.equals("")){
		                        	strIcon=objMemberDAO.alreadyPosted(pUserId,""+topicId,aOIBVForumListing.getThreadId(),connection);
			                        if(strIcon.equals("")){
			                        	strIcon=objMemberDAO.lockSec(pUserId,""+topicId,aOIBVForumListing.getThreadId(),connection);
	 		                        }
 		                        }
 	                        }
	                        aOIBVForumListing.setIcon(strIcon);
	                        //Amended by Zhao Yu on 2008-Sep-25
	                        //aOIBVForumListing.setTotalPosts(objMemberDAO.totalPotsing(aOIBVForumListing.getPostedBy(),connection));
	                        aOIBVForumListing.setTotalPosts(objMemberDAO.totalPotsing(rs.getString("POSTED_BY"),connection));
	                        arOIBVUpToTopicListing.set(i,aOIBVForumListing);
	                        break;
	                    }
	                }
                }
            }//end of while block
            //System.out.println("strmode==="+strMode);
             
            //call the temp function to remove the category and board if the posting value is null
            if(OIUtilities.replaceNull(strMode).equalsIgnoreCase("12") || 
            		OIUtilities.replaceNull(strMode).equalsIgnoreCase("24") || 
					OIUtilities.replaceNull(strMode).equalsIgnoreCase("72") ||
					OIUtilities.replaceNull(strMode).equalsIgnoreCase("ViewUserPosting")
            		){
            	ArrayList tempList =new ArrayList(); 
            	tempList =arOIBVUpToTopicListing;
            	arOIBVUpToTopicListing =null;
            	arOIBVUpToTopicListing = changeList(tempList);
            }
        }
        catch(SQLException e)
        {
            logger.error("sql -readUpToTopicListing -" + e.getMessage());
            throw e;
        }
        catch(Exception e)
        {
            logger.error("readUpToTopicListing -" + e.getMessage());
            throw e;
        }
        finally
        {
        	if (rs!=null)
                rs.close();
        	if (preparedStatement!=null)
                preparedStatement.close();
            
        }
        if (arOIBVUpToTopicListing.size()==0)
            arOIBVUpToTopicListing=null;
        return arOIBVUpToTopicListing;
    }
    
	private ArrayList changeList(ArrayList al)throws Exception{
		ArrayList tempArr = new ArrayList();
		try{
		 
		
		String strCategory="";
		String strBoardName="";
		String strTopicName="";
		
		String strCategoryID="";
		String strBoardID="";
		String strTopicID="";
		
		String strDummyCategory="";
		String strDummyBoardName="";
		String strDummyCategoryID="";
		String strDummyBoardID="";
		
		for(int i=0;i<al.size();i++){
			
			OIBVForumListing objRealVO = (OIBVForumListing)al.get(i);
			if(objRealVO.getLevel().equalsIgnoreCase("1")){
 				strCategory=objRealVO.getCategory();
				strCategoryID=objRealVO.getCategoryId();
				
			}
			else if(objRealVO.getLevel().equalsIgnoreCase("2")){
 				strBoardName=objRealVO.getBoardName();
				strBoardID=objRealVO.getBoardId();
			}
			else if(objRealVO.getLevel().equalsIgnoreCase("3")){
 				 
				if(!OIUtilities.replaceNull(objRealVO.getThreadName()).equals("")){
					strTopicName = objRealVO.getTopicName();
					strTopicID = objRealVO.getTopicId();
					
					OIBVForumListing objNewVO=new OIBVForumListing();   
					
					if(!strDummyCategory.equalsIgnoreCase(strCategory)){
						objNewVO =new OIBVForumListing();
						objNewVO.setLevel("1");
						objNewVO.setCategory(strCategory);
						objNewVO.setCategoryId(strCategoryID);
						tempArr.add(objNewVO);
						strDummyCategory =strCategory;
						strDummyCategoryID =strCategoryID;
					}
					
					if(!strDummyBoardName.equalsIgnoreCase(strBoardName)){
						objNewVO =new OIBVForumListing();
						objNewVO.setLevel("2");
						objNewVO.setBoardName(strBoardName);
						objNewVO.setBoardId(strBoardID);
						tempArr.add(objNewVO);
						strDummyBoardName =strBoardName;
						strDummyBoardID =strBoardID;
					}
					
					objNewVO =new OIBVForumListing();
					objNewVO.setLevel("3");
					objNewVO.setTopicId(strTopicID);
					objNewVO.setTopicName(strTopicName);
					objNewVO.setThreadName(objRealVO.getThreadName());
					
					objNewVO.setThreadId(objRealVO.getThreadId());
					objNewVO.setNickName(objRealVO.getNickName());
					objNewVO.setPostedBy(objRealVO.getPostedBy());
					objNewVO.setNoThreads(objRealVO.getNoThreads());
					objNewVO.setLastPostOn(objRealVO.getLastPostOn());
					objNewVO.setIcon(objRealVO.getIcon());
					objNewVO.setTotalPosts(objRealVO.getTotalPosts());
					
					tempArr.add(objNewVO);
					
					
				}	
			}
 		}
		 
	}catch(Exception e){
		System.out.println("error in changelist---"+e);
	}
		return tempArr;
		
	}
		
    
}
