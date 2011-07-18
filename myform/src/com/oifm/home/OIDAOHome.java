/*****************************************************************************
* Title 		: OIDAOHome.java
* Description 	: This DAO file manipulates on poll to display the poll,results of poll.     
* Author		: Suresh Kumar.R 
* Version No 	: 1.0
* Date Created 	: 01 - Aug -2005
* Copyright 	: Scandent Group
********************************************************************************/

package com.oifm.home;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.oifm.asm.ASMBVCommon;
import com.oifm.asm.ASMSqls;
import com.oifm.blog.OIBABlogAdminCreateEntry;
import com.oifm.blog.OIBVBlogCommon;
import com.oifm.blog.OIBlogConstants;
import com.oifm.blog.OIBlogSqls;
import com.oifm.common.OILabelConstants;
import com.oifm.consultation.OIBVPaper;
import com.oifm.consultation.OIConsultConstant;
import com.oifm.forum.OIBVHottestThread;
import com.oifm.poll.OIBVPoll;
import com.oifm.poll.OIPollSqls;
import com.oifm.survey.OIBASurvey;
import com.oifm.utility.DateUtility;
import com.oifm.utility.OIFormUtilities;
import com.oifm.utility.OISQLUtilities;
import com.oifm.utility.OIUtilities;


public class OIDAOHome 
{
	//transient private final Log LOGGER = LogFactory.getLog(this.getClass());
    Logger logger = Logger.getLogger(OIDAOHome.class.getName());
	
	public OIDAOHome()
	{
		logger.debug(OILabelConstants.BEGIN_METHOD_BO + this.getClass().getName());
	}
	
	/**
	 * This method fetches the poll details  
	 * @param objCon
	 * @param objBVPoll
	 * @return
	 * @throws Exception
	 */	    	
	public ArrayList viewPoll(Connection objCon  ) throws Exception
	{
	    logger.debug(OILabelConstants.BEGIN_METHOD_DAO +"viewPoll"); 
		OIBVPoll objBVPoll = new OIBVPoll(); 
	    ResultSet objRs =null;
	    PreparedStatement objPs=null;
	    boolean bFlag = false;
	    ArrayList alPoll = null;
	    try
	    { 
	        objPs = objCon.prepareStatement(OIHomeSqls.QRY_POLL_VIEW);
	        logger.debug(OIHomeSqls.QRY_POLL_VIEW);
	        objRs = objPs.executeQuery();
	        if(objRs!= null && objRs.next())
	        {
	            alPoll = new ArrayList();
	           	objBVPoll.setPollId(OIFormUtilities.chkIsNull(objRs.getString(1)));
	           	objBVPoll.setTitle(OIFormUtilities.chkIsNull(objRs.getString(2)));
	           	objBVPoll.setQuestion(OIFormUtilities.chkIsNull(objRs.getString(3)));
	           	objBVPoll.setAnswer1(OIFormUtilities.chkIsNull(objRs.getString(4)));
	           	objBVPoll.setAnswer2(OIFormUtilities.chkIsNull(objRs.getString(5)));
	           	objBVPoll.setAnswer3(OIFormUtilities.chkIsNull(objRs.getString(6)));
	           	objBVPoll.setAnswer4(OIFormUtilities.chkIsNull(objRs.getString(7)));
	           	objBVPoll.setAnswer5(OIFormUtilities.chkIsNull(objRs.getString(8)));
	           	objBVPoll.setMutAns(OIFormUtilities.chkIsNull(objRs.getString(9)));
				objBVPoll.setShowRes(OIFormUtilities.chkIsNull(objRs.getString(10)));
				objBVPoll.setPublished(OIFormUtilities.chkIsNull(objRs.getString(11)));
				alPoll.add(objBVPoll);
	        }
	    }
	    catch(Exception sqle)
	    {
	        logger.error(sqle.getMessage());
			bFlag = false;
			throw sqle;
		}
	    finally
	    {
	        if (objPs!= null)
	        {
	            objPs.close();
			}
			if (objRs!= null)
			{
			    objRs.close();
			}
		}

	    logger.debug(OILabelConstants.END_METHOD_DAO +"viewPoll");
	    return alPoll;
	}
	    
	/**
	 * This method get the checks for the existence of poll to be published.
	 * @param objCon
	 * @param objBV
	 * @return
	 * @throws Exception
	 */
	 
	public boolean selectPoll(Connection objCon ,OIBVHome objBV)  throws Exception
	{
		logger.debug(OILabelConstants.BEGIN_METHOD_DAO +"selectPoll"); 
		OIBVPoll objBVPoll = new OIBVPoll(); 
        ResultSet objRs =null;
        PreparedStatement objPs=null;
        boolean bFlag = false;
        ArrayList alPoll = null;
        try
        { 
         	objPs = objCon.prepareStatement(OIHomeSqls.QRY_CNT_POLL);
         	objPs.setInt(1,Integer.parseInt(objBV.getPollId()));
        	logger.debug(OIHomeSqls.QRY_CNT_POLL);
            objRs = objPs.executeQuery();
            if(objRs!= null && objRs.next())
            {
            	if(objRs.getInt(1)>0)
            	{
            		bFlag = true;		
            	}
            }		
		}catch(Exception sqle)
		{
			logger.error(sqle.getMessage());
			bFlag = false;
			throw sqle;
		}finally
		{
			if (objPs!= null)
			{
				objPs.close();
			}
			if (objRs!= null)
			{
				objRs.close();
			}
		}
	   
	    logger.debug(OILabelConstants.END_METHOD_DAO +"selectPoll");
	    return bFlag;
	}

	/**
	 * This method fetches the poll details  
	 * @param objCon
	 * @param objBV
	 * @return
	 * @throws Exception
	 */	    	
	public boolean insertPoll(Connection objCon,OIBVHome objBV ) throws Exception
	{
		logger.debug(OILabelConstants.BEGIN_METHOD_DAO +"insertPoll"); 
		PreparedStatement objPs=null;
        boolean bFlag = false;
        int nTemp =1;
        try
        { 
        	objPs = objCon.prepareStatement(OIHomeSqls.QRY_INS_POLL);
        	logger.debug(OIHomeSqls.QRY_INS_POLL);
        	objPs.setInt(1,Integer.parseInt(objBV.getPollId()));
        	objPs.setInt(2,0);
        	objPs.setInt(3,0);
        	objPs.setInt(4,0);
        	objPs.setInt(5,0);
        	objPs.setInt(6,0);
        	
        	if(objBV.getMultiple().equalsIgnoreCase(OILabelConstants.FLAG_Y)){
        			
        				if(objBV.getRes1().trim().length()!=0){
			        	   		objPs.setInt(2,nTemp);	
			        	}
			        	if(objBV.getRes2().trim().length()!=0){
			        		objPs.setInt(3,nTemp);
			            }
			        	if(objBV.getRes3().trim().length()!=0){
			            	objPs.setInt(4,nTemp);
			        	}
			        	if(objBV.getRes4().trim().length()!=0)
			        	{
				           	objPs.setInt(5,nTemp);	
				        }
			        	if(objBV.getRes5().trim().length()!=0)
				        {
				           	objPs.setInt(6,nTemp);	
				        }	    
        	}else{
        	
        		if(objBV.getResponse()!= null && objBV.getResponse().trim().length() >0){
        			int nInt = Integer.parseInt(objBV.getResponse().trim());
        			for(int i=1;i<=5;i++){
        				if(nInt == i){
        					objPs.setInt(i+1,1);	
        				}else{
        					objPs.setInt(i+1,0);
        				}
        			}
        				
        		}
        	}	
	
	        int nUpdate = objPs.executeUpdate(); 
	        if(nUpdate>0){
	            bFlag = true;
	        }
		
		}catch(Exception sqle) {
			logger.error(sqle.getMessage());
			bFlag = false;
			throw sqle;
		}
        finally
        {
			if (objPs!= null)
			{
				objPs.close();
			}
		}
	       
        logger.debug(OILabelConstants.END_METHOD_DAO +"insertPoll");
        return bFlag;
	 }
	
	 /**
	 * This method fetches the poll details  
	 * @param objCon
	 * @param objBV
	 * @return
	 * @throws Exception
	 */	    	
	public boolean updatetPoll(Connection objCon,OIBVHome objBV ) throws Exception
	{
		logger.debug(OILabelConstants.BEGIN_METHOD_DAO +"updatetPoll"); 
		ResultSet objRs =null;
        PreparedStatement objPs=null;
        boolean bFlag = false;
        int nChk = 0;
        int nUpdate =0;
	        
        StringBuffer sbQuery = new StringBuffer(OIHomeSqls.QRY_UPD_RES_POLL);
        try
        { 
        	if(objBV.getMultiple().trim().equalsIgnoreCase("Y"))
        	{
        	    if(objBV.getRes1().trim().length()!=0)
        	    {
		        	sbQuery.append("Response1 = Response1 + 1 ");
		        	sbQuery.append(" , ");
		        	nChk++;
		        }
        	    if(objBV.getRes2().trim().length()!=0)
        	    {
		            sbQuery.append("Response2 = Response2 + 1 ");
			        sbQuery.append(" , ");
			        nChk++;
        	    }
        	    if(objBV.getRes3().trim().length()!=0)
        	    {
        	        sbQuery.append("Response3 = Response3 + 1 ");
			        sbQuery.append(" , ");
			        nChk++;
        	    }
        	    if(objBV.getRes4().trim().length()!=0)
        	    {
        	        sbQuery.append("Response4 = Response4 + 1 ");
			        sbQuery.append(" , ");
			        nChk++;
        	    }
        	    if(objBV.getRes5().trim().length()!=0)
        	    {
        	        sbQuery.append("Response5 = Response5 + 1 ");
			        sbQuery.append(" , ");
			        nChk++;
        	    }
	        }
        	else
        	{
        	    if(objBV.getResponse().trim().length()!=0)
        	    {
        	        sbQuery.append("Response").append(objBV.getResponse().trim())
					.append(" = Response").append(objBV.getResponse().trim())
					.append(" + 1 ");
	        		nChk++;
        	    }
        	}	
				
            int nIndex = sbQuery.toString().lastIndexOf(",");
            String strTemp = sbQuery.toString();
            if(nIndex >0)
            {
	           	strTemp = sbQuery.substring(0,nIndex-1);
	        }
	        if(nChk>0)
	        {	
	            objPs = objCon.prepareStatement(strTemp+OIHomeSqls.QRY_WHR_POLL + Integer.parseInt(objBV.getPollId()));
			    logger.debug("Query "+strTemp+OIHomeSqls.QRY_WHR_POLL + Integer.parseInt(objBV.getPollId()));
			    nUpdate = objPs.executeUpdate();
			    if(nUpdate>0)
			    {
			        bFlag = true;
			        logger.debug("Records Update "+nUpdate);
			    }
	        }
	        else
	        {
	            bFlag = true;
	        }
        }
        catch(Exception sqle)
        {
			logger.error(sqle.getMessage());
			bFlag = false;
			throw sqle;
		}
        finally
        {
            if (objPs!= null)
            {
                objPs.close();
			}
			if (objRs!= null)
			{
				objRs.close();
			}
		}
	       
	    logger.debug(OILabelConstants.END_METHOD_DAO +"updatetPoll");
	    return bFlag;
	}
	
	/**
	 * This method checks for the publish flag is Y/N and get the title of checked published Poll.
	 * @param objCon
	 * @param objBVPoll
	 * @return
	 * @throws Exception
	 */
		
	public boolean getPublishCnt(Connection objCon ,ArrayList alPoll,OIBVHome objBV ) throws Exception
	{
		logger.debug(OILabelConstants.BEGIN_METHOD_DAO +"getPublishCnt"); 

		ResultSet objRs =null;
	    PreparedStatement objPs=null;
	    boolean bFlag = false;
	        
	    try{ 
	           
	            	
			    objPs = objCon.prepareStatement(OIPollSqls.QRY_PUBLISH);
			    logger.debug(OIPollSqls.QRY_PUBLISH);
			    objRs = objPs.executeQuery();
			    if(objRs!= null && objRs.next()) {
			        bFlag = true;
			        objBV.setPublished("Y");
			        objBV.setPubId(objRs.getString(1));
			        if(alPoll!=null && alPoll.size()>0){
			        	OIBVPoll objBVPoll = (OIBVPoll) alPoll.get(0); 	
			        	objBVPoll.setPublished("Y");
				        objBVPoll.setPubId(objRs.getString(1));
				    }
			    }
		
	    }catch(Exception sqle){
	    
	        logger.error(sqle.getMessage());
			bFlag = false;
			throw sqle;
		}finally{
	        if (objPs!= null){
	            objPs.close();
			}
			if (objRs!= null){
			    objRs.close();
			}
		}
	       
	    logger.debug(OILabelConstants.END_METHOD_DAO +"getPublishCnt");
	    return bFlag;
	}
	
    public ArrayList readHottestThread(String pUserId,Connection connection) throws Exception
    {
        ArrayList arOIBVHottestThread = new ArrayList();;
        OIBVHottestThread aOIBVHottestThread = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs=null;
        try
        {
            preparedStatement = connection.prepareStatement(OIPollSqls.READ_HOTEST_TOPIC);
            preparedStatement.setString(1,pUserId);
            preparedStatement.setString(2,pUserId);
            rs = preparedStatement.executeQuery();
            while(rs.next())
            {
                aOIBVHottestThread = new OIBVHottestThread();
                aOIBVHottestThread.setThreadId(rs.getString("THREADID"));
                aOIBVHottestThread.setTitle(rs.getString("TITLE"));
                aOIBVHottestThread.setLatestFlag(rs.getString("TTYPE"));
                if (rs.getDate("LASTPOST_ON")!=null)
                    aOIBVHottestThread.setPostedOn(DateUtility.getMMDDYYYYStringFromDate(rs.getDate("LASTPOST_ON")));
                arOIBVHottestThread.add(aOIBVHottestThread);
            }
        }
        catch(Exception e)
        {
            logger.error("readHottestThread -" + e.getMessage());
            throw e;
        }
        finally
        {
            if (preparedStatement!=null)
                preparedStatement.close();
            if (rs!=null)
                rs.close();
        }
        if (arOIBVHottestThread.size()==0)
            arOIBVHottestThread=null;
        return arOIBVHottestThread;
    }
    
    public ArrayList readLatestThread(String pUserId,Connection connection) throws Exception
    {
        ArrayList arOIBVLatestThread = new ArrayList();
        OIBVHottestThread aOIBVHottestThread = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs=null;
        try
        {
            preparedStatement = connection.prepareStatement(OIPollSqls.READ_LATEST_TOPIC);
            preparedStatement.setString(1,pUserId);
            preparedStatement.setString(2,pUserId);
            rs = preparedStatement.executeQuery();
            String flag="";
            int i=0;
            int threadId=0;
            while(rs.next())
            {
                if (threadId!=rs.getInt("THREADID") && rs.getString("TTYPE").equals("L"))
                {
                    threadId=rs.getInt("THREADID");
	                i++;
	                if (i>=4)
	                    break;
                    flag="";
	                aOIBVHottestThread = new OIBVHottestThread();
	                aOIBVHottestThread.setThreadId(rs.getString("THREADID"));
	                aOIBVHottestThread.setTitle(rs.getString("TITLE"));
	                aOIBVHottestThread.setLatestFlag("false");
                    aOIBVHottestThread.setPostedOn(DateUtility.getMMDDYYYYStringFromDate(rs.getDate("POSTED_ON")));
                    arOIBVLatestThread.add(aOIBVHottestThread);
                }
            }
        }
        catch(Exception e)
        {
            logger.error("readLatestThread -" + e.getMessage());
            throw e;
        }
        finally
        {
            if (preparedStatement!=null)
                preparedStatement.close();
            if (rs!=null)
                rs.close();
        }
        if (arOIBVLatestThread.size()==0)
            arOIBVLatestThread=null;
        return arOIBVLatestThread;
    }

    public ArrayList readLatestPapers(String pUserId,Connection connection) throws Exception
    {
        ArrayList arOIBVPaper = new ArrayList();
        OIBVPaper aOIBVPaper = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs=null;
        try
        {
            preparedStatement = connection.prepareStatement(OIPollSqls.READ_LATEST_PAPER);
            preparedStatement.setString(1,pUserId);
            preparedStatement.setString(2,pUserId);
            preparedStatement.setString(3,pUserId);
            rs = preparedStatement.executeQuery();
            
            while (rs.next())
            {
                aOIBVPaper = new OIBVPaper();
                aOIBVPaper.setActive(rs.getString(OIConsultConstant.Q_ACTIVE));
                aOIBVPaper.setCategoryId(rs.getInt(OIConsultConstant.Q_CATEGORYID));
                aOIBVPaper.setPaperId(rs.getInt(OIConsultConstant.Q_PAPERID));
                aOIBVPaper.setCategoryName(rs.getString(OIConsultConstant.Q_NAME));
                aOIBVPaper.setDescription(rs.getString(OIConsultConstant.Q_DESCRIPTION));
                aOIBVPaper.setExpireDate(rs.getDate(OIConsultConstant.Q_TO_DT));
                aOIBVPaper.setStartStrDate(DateUtility.getMMDDYYYYStringFromDate(rs.getDate(OIConsultConstant.Q_FROM_DT)));
                aOIBVPaper.setPaperName(rs.getString(OIConsultConstant.Q_TITLE));
                aOIBVPaper.setSecurity(rs.getString(OIConsultConstant.Q_SECURITY));
                arOIBVPaper.add(aOIBVPaper);
            }
            if (arOIBVPaper.size()==0)
                arOIBVPaper = null;
            //preparedStatement.close();
        }catch(Exception sqle)
        {
            logger.error(sqle.getMessage());
            //connection.rollback();
            throw sqle;
        }
        finally
        {
            //freeConnection();
            if (preparedStatement!=null)
                preparedStatement.close();
            if (rs!=null)
                rs.close();
        }

        return arOIBVPaper;
    }

    public ArrayList readLatestSurvey(String pUserId,Connection connection) throws Exception
    {
        ArrayList arOIBASurvey = new ArrayList();
        OIBASurvey aOIBASurvey = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs=null;
        try
        {
            preparedStatement = connection.prepareStatement(OIHomeSqls.READ_LATEST_SURVEY);
            preparedStatement.setString(1,pUserId);
            preparedStatement.setString(2,pUserId);
            //preparedStatement.setString(3,pUserId);
            rs = preparedStatement.executeQuery();
            
            while (rs.next()) 
            {
                aOIBASurvey = new OIBASurvey();
                aOIBASurvey.setStrSurveyId(rs.getString("SURVEYID"));
                aOIBASurvey.setStrSurveyName(rs.getString("NAME"));
                aOIBASurvey.setStrFromDate(DateUtility.getMMDDYYYYStringFromDate(rs.getDate("START_ON")));
                aOIBASurvey.setStrToDate(DateUtility.getMMDDYYYYStringFromDate(rs.getDate("EXPIRY_ON")));
                aOIBASurvey.setStrDescription(rs.getString("DESCRIPTION"));
                arOIBASurvey.add(aOIBASurvey);
            }
            if (arOIBASurvey.size()==0)
                arOIBASurvey = null;
            //preparedStatement.close();
        }catch(Exception sqle)
        {
            logger.error(sqle.getMessage());
            //connection.rollback();
            throw sqle;
        }
        finally
        {
            //freeConnection();
            if (preparedStatement!=null)
                preparedStatement.close();
            if (rs!=null)
                rs.close();
        }

        return arOIBASurvey;
    }
    
    public ArrayList readASMLetters(String pUserId,Connection connection) throws Exception
    {
        ArrayList arLetter = new ArrayList();
        ASMBVCommon objBV = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs=null;
        try
        {
            preparedStatement = connection.prepareStatement(ASMSqls.ASM_RECENT_LETTERS);
            rs = preparedStatement.executeQuery();
            int incr=0;
            
            while (rs.next())
            {
            	if(incr<3){
	            	objBV = new ASMBVCommon();
	            	objBV.setHidRecLtrID(OIUtilities.replaceNull(rs.getString("LETTERID")));
	            	objBV.setHidRecLtrTopic(OIUtilities.replaceNull(rs.getString("TOPIC")));
	            	objBV.setHidRecLtrPubOn(
	            			DateUtility.getMMDDYYYYStringFromDate(rs.getDate("PUBLISHEDON")));
	            	arLetter.add(objBV);
	            	incr =incr+1;
            	}
            }
            if (arLetter.size()==0)
            	arLetter = null;
            //preparedStatement.close();
        }catch(Exception sqle)
        {
            logger.error(sqle.getMessage());
            //connection.rollback();
            throw sqle;
        }
        finally
        {
            //freeConnection();
            if (preparedStatement!=null)
                preparedStatement.close();
            if (rs!=null)
                rs.close();
        }

        return arLetter;
    }
    

	public void getBannerCount(Connection objCon ,OIBVHome objBV ) throws Exception
	{
		logger.debug(OILabelConstants.BEGIN_METHOD_DAO +"getBannerCount"); 

		ResultSet objRs =null;
	    PreparedStatement objPs=null;
	    boolean bFlag = false;
	        
	    try{ 
	           
	            	
			    objPs = objCon.prepareStatement(OIPollSqls.GET_BANNER_CNT);
			    logger.debug(OIPollSqls.GET_BANNER_CNT);
			    objRs = objPs.executeQuery();
			    if(objRs!= null && objRs.next()) {
 				   objBV.setBannerCnt(Integer.parseInt(objRs.getString(1)));
 			    }else{
 			    	objBV.setBannerCnt(0);
   			    }
		
	    }catch(Exception sqle){
	    
	        logger.error(sqle.getMessage());
			bFlag = false;
			throw sqle;
		}finally{
	        if (objPs!= null){
	            objPs.close();
			}
			if (objRs!= null){
			    objRs.close();
			}
		}
	       
	    logger.debug(OILabelConstants.END_METHOD_DAO +"getBannerCount");
 	}
	
    public ArrayList readLatestBlogs(String pUserId,Connection connection) throws Exception
    {
        ArrayList arOIBVBlog = new ArrayList();
        //ArrayList list = new ArrayList();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		OIBVBlogCommon oIBVBlogCommon = null;
		StringBuffer query =  null; 
		
		try
		{		
	
            preparedStatement = connection.prepareStatement(OIHomeSqls.READ_LATEST_BLOG);
            rs = preparedStatement.executeQuery();
			
			while (rs.next())
			{
				oIBVBlogCommon = new OIBVBlogCommon();
				oIBVBlogCommon.setEntryId(new Integer(rs.getString("ENTRY_ID")));
				oIBVBlogCommon.setEntryTitle(rs.getString("ENTRY_TITLE"));
				oIBVBlogCommon.setBlogId(new Integer(rs.getString("BLOG_ID")));
				oIBVBlogCommon.setCreatedOn(DateUtility.getMMDDYYYYStringFromDate(rs.getDate("CREATED_ON")));
				oIBVBlogCommon.setBlogTitle(rs.getString("BLOG_TITLE"));	
				arOIBVBlog.add(oIBVBlogCommon);
			}			
		}
		catch (SQLException se)
		{
			logger.error(" getLatestBlogs() " + se.getMessage());
			throw se;			
		}
		finally
		{
			OISQLUtilities.closeRsetPstatement(rs, preparedStatement);
		}		
		return arOIBVBlog;
    }

    public void getTag(String pUserId,OIBVHome objBV,Connection connection) throws Exception
	{
		logger.debug(OILabelConstants.BEGIN_METHOD_DAO +"getTag"); 

		ResultSet rs =null;
	    PreparedStatement pstmt=null;
	    try{ 
	           
	            pstmt = connection.prepareStatement(OIHomeSqls.GET_TAG);
	            pstmt.setString(1, pUserId);
			    rs=pstmt.executeQuery();
			    if(rs!= null && rs.next())
			    {
			    	objBV.setStrTag(rs.getString("TAG_WORDS"));
			    }	
	    }catch(Exception sqle){
	    
	        logger.error(sqle.getMessage());

			throw sqle;
		}finally{
	        if (pstmt!= null){
	        	pstmt.close();
			}
		}
	       
	    logger.debug(OILabelConstants.END_METHOD_DAO +"getTag");
 	}

    public boolean updateTag(String pUserId,OIBVHome objBV,Connection connection) throws Exception
	{
		logger.debug(OILabelConstants.BEGIN_METHOD_DAO +"updateTag"); 

		ResultSet rs =null;
	    PreparedStatement pstmt=null;
		int count = 0;
		boolean status = false;

	        
	    try{ 
	    		
	            pstmt = connection.prepareStatement(OIHomeSqls.UPDATE_TAG);
	            pstmt.setString(1, objBV.getStrTag());
	            pstmt.setString(2, pUserId);
			    count=pstmt.executeUpdate();
			    if (count==1)
			    {
			    	status = true;
			    }
			    
			    
	    }catch(Exception sqle){
	    
	        logger.error(sqle.getMessage());
			
			throw sqle;
		}finally{
	        if (pstmt!= null){
	        	pstmt.close();
			}
		}
	       
	    logger.debug(OILabelConstants.END_METHOD_DAO +"updateTag");
	    return status;
 	}

}
