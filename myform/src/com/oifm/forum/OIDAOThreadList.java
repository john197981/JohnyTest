/*******************************************************************************************
* Title 		: OIDAOThreadList 
* Description 	: This is the DAO for Listing the Threads in discussion forum
* Author		: Suresh Kumar.R 
* Version No 	: 1.0
* Date Created 	: 09 Aug 2005
* Copyright 	: Scandent Group
*******************************************************************************************/

package com.oifm.forum;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.oifm.common.OILabelConstants;
import com.oifm.useradmin.OIDAOMemberProfile;
import com.oifm.useradmin.OISearchSqls;
import com.oifm.utility.OIEncrypter;
import com.oifm.utility.OIFormUtilities;
import com.oifm.utility.OISQLUtilities;
import com.oifm.utility.OIUtilities;

public class OIDAOThreadList 
{
	transient private final Log LOGGER = LogFactory.getLog(this.getClass());
	
	public OIDAOThreadList()
	{
			LOGGER.info(OILabelConstants.BEGIN_METHOD_DAO + this.getClass().getName());
	} 
	
	/**
	 * This method returns list of users of Consulation Paper
	 * @param objCon
	 * @param objOIBVSendMail
	 * @return
	 * @throws Exception
	 */
	public ArrayList getThreadLst(Connection objCon,OIBAThread objBA,ArrayList alThreads,int nStartNum,int nEndNum, boolean isBoardAdmin)throws Exception
    {
	    OIBAThread objThread=null;
        ArrayList arThread=null;
        ResultSet objRs =null;
        PreparedStatement objPs=null;
        boolean bFlag,bCheck = false;
        StringBuffer sbQuery = new StringBuffer(0);
        String strLocked = null;
        String strPrivate = null;
        LinkedHashMap hmUsers = null;
        LinkedHashMap hmMsgs = null;
        LinkedHashMap hmSticky = null;
        LinkedHashMap hmAdminSticky = null;
        //Added by Zhao Yu on 2008-Sep-25
        String eName = "";
        try
        { 
            if(alThreads!=null)
            {
        		hmUsers = (LinkedHashMap) alThreads.get(0);
        		hmMsgs = (LinkedHashMap) alThreads.get(1);
        		hmSticky = (LinkedHashMap) alThreads.get(2);
        		hmAdminSticky = (LinkedHashMap) alThreads.get(3);
        	}
            
            if (isBoardAdmin) {
            	sbQuery.append(OISearchSqls.QRYROW1)
				   .append(OIForumSqls.QRY_THREADLST_ADMIN)
				   .append(objBA.getHidSortKey())
				   .append(OILabelConstants.SPACE)
				   .append(objBA.getHidOrder())
				   .append( ") LIST ")
				   .append(OISearchSqls.QRYROW2);
				   
		     	objPs = objCon.prepareStatement(sbQuery.toString());
		     	objPs.setString (1,objBA.getStrUserId());
		     	objPs.setInt (2,Integer.parseInt(objBA.getStrTopicId()));
		     	objPs.setInt(3,nStartNum);
		     	objPs.setInt(4,nEndNum);
            } else {
            	sbQuery.append(OISearchSqls.QRYROW1)
				   .append(OIForumSqls.QRY_THREADLST)
				   .append(objBA.getHidSortKey())
				   .append(OILabelConstants.SPACE)
				   .append(objBA.getHidOrder())
				   .append( ") LIST ")
				   .append(OISearchSqls.QRYROW2);
				   
		     	objPs = objCon.prepareStatement(sbQuery.toString());
		     	objPs.setString (1,objBA.getStrUserId());
		     	objPs.setString (2,objBA.getStrUserId());
		     	objPs.setString (3,objBA.getStrUserId());
		     	objPs.setInt (4,Integer.parseInt(objBA.getStrTopicId()));
		     	objPs.setInt(5,nStartNum);
		     	objPs.setInt(6,nEndNum);
            }
            
 			LOGGER.info("nStartNum" +nStartNum);
			LOGGER.info("nEndNum" +nEndNum);
			LOGGER.info(sbQuery.toString());
        	objRs = objPs.executeQuery();
        	OIDAOMemberProfile objMemberDAO=null;
            if(objRs!= null)
            {
           		bFlag = true;
           		arThread = new ArrayList(); 
           		while(objRs.next())
           		{
           		    objThread = new OIBAThread();
           		    objMemberDAO=new OIDAOMemberProfile();
	           		objThread.setStrTitle(OIFormUtilities.chkIsNull(objRs.getString(1)));
	           		objThread.setStrLastpostOn(OIFormUtilities.chkIsNull(objRs.getString(2)));
		            objThread.setStrReplies(OIFormUtilities.chkIsNull(objRs.getString(3))); 
		            objThread.setStrHits(OIFormUtilities.chkIsNull(objRs.getString(4)));
		            objBA.setStrTopicName(OIFormUtilities.chkIsNull(objRs.getString(5)));
		            objThread.setStrThreadId(OIFormUtilities.chkIsNull(objRs.getString(6)));
		            //Amended by Zhao Yu on 2008-Sep-25
		            //objThread.setStrModeratedBy(OIFormUtilities.chkIsNull(objRs.getString(7)));
		            eName = OIEncrypter.encrypt(OIFormUtilities.chkIsNull(objRs.getString(7)));
		            objThread.setStrModeratedBy(eName);
		            //Amended by Zhao Yu on 2008-Sep-25, encrypt user nric
		            //objThread.setStrPostedBy(OIFormUtilities.chkIsNull(objRs.getString(8)));
		            eName = OIEncrypter.encrypt(OIFormUtilities.chkIsNull(objRs.getString(8)));
		            objThread.setStrPostedBy(eName);
		            objThread.setStrModName(OIFormUtilities.chkIsNull(objRs.getString(9)));
		            objThread.setStrPostedName(OIFormUtilities.chkIsNull(objRs.getString(10)));
		            //  objThread.setStrImgMsg(OIFormUtilities.chkIsNull(objRs.getString(12)));
		            objThread.setStrLocked(OIFormUtilities.chkIsNull(objRs.getString(11)));
		            objThread.setStrSecurity(OIFormUtilities.chkIsNull(objRs.getString(12)));
		            objThread.setStrModerationStat(OIFormUtilities.chkIsNull(objRs.getString(13)));
		            objThread.setStrTopicDesc(OIFormUtilities.chkIsNull(objRs.getString(17)));
		            
		            ///added by anbu
		            //objThread.setTotalPosts(objMemberDAO.totalPotsing(objThread.getStrPostedBy(),objCon));
		            //Amended by Zhao Yu on 2008-Sep-25
		            objThread.setTotalPosts(objMemberDAO.totalPotsing(OIFormUtilities.chkIsNull(objRs.getString(8)),objCon));
                    
		            chkAdminSticky(objThread,hmAdminSticky);
		            chkSticky(objThread,hmSticky);
		            chkMsgs(objThread,hmMsgs);
		            chkThreads(objThread,hmUsers);
		            arThread.add(objThread);
	           	}
        	} 
		}
        catch(Exception sqle)
        {
			LOGGER.error(sqle.getMessage());
			bFlag = false;
			throw sqle;
		}
        finally
        {
        	if (objRs!= null)
			{
				objRs.close();
			}
			if (objPs!= null)
			{
				objPs.close();
			}
			
			hmUsers = null;
		    hmMsgs = null;
		    hmSticky = null;
		}
        if (arThread.size()==0)
        {
        	arThread=null;
        }
        
        return arThread;
    }
	
    /**
     * This method Updates the hits count.
     * @param objCon
     * @param objBA
     * @return
     * @throws Exception
     */
    public boolean updateHits(Connection objCon,OIBAThread objBA)throws Exception
    {
        PreparedStatement objPs=null;
        boolean bFlag = false;
        int nUpdate = 0;
        try
        {
            objPs = objCon.prepareStatement(OIForumSqls.QRY_UPDATE_HITS);
        	objPs.setInt (1,Integer.parseInt(objBA.getStrThreadId()));
        	LOGGER.info("Query" + OIForumSqls.QRY_UPDATE_HITS);
        	nUpdate = objPs.executeUpdate(); 
        	LOGGER.info("Update Rows " + nUpdate);
            if(nUpdate >0)
            {
                bFlag = true;
            }                        	
        }
        catch(Exception sqle)
        {
            LOGGER.error(sqle.getMessage());
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
    	return bFlag;
    }
		
    
    
    /**
     * This method fetches the total count of records.
     * @param objCon
     * @param objBVUsrPrfSrh
     * @return
     * @throws Exception   
     */
     public boolean srchRowCntQry(Connection objCon, OIBAThread objBA, boolean isBoardAdmin)throws Exception
     {
         LOGGER.info(OILabelConstants.BEGIN_METHOD_DAO +"srchRowCntQry"); 
         
         ResultSet objRs =null;
         PreparedStatement objPs=null;  
         boolean bFlag = false;
           
         try
         { 
        	 if (isBoardAdmin) {
        		 objPs = objCon.prepareStatement(OISearchSqls.SLTCOUNT+OIForumSqls.QRY_THREADLST_ADMIN + objBA.getHidSortKey()+OILabelConstants.SPACE+objBA.getHidOrder()+ ") LIST )");
                 objPs.setString (1,objBA.getStrUserId());
                 objPs.setInt (2,Integer.parseInt(objBA.getStrTopicId()));
        	 } else {
        		 objPs = objCon.prepareStatement(OISearchSqls.SLTCOUNT+OIForumSqls.QRY_THREADLST + objBA.getHidSortKey()+OILabelConstants.SPACE+objBA.getHidOrder()+ ") LIST )");
                 objPs.setString (1,objBA.getStrUserId());
                 objPs.setString (2,objBA.getStrUserId());
                 objPs.setString (3,objBA.getStrUserId());
                 objPs.setInt (4,Integer.parseInt(objBA.getStrTopicId()));
        	 }
             
             LOGGER.info("query" +OISearchSqls.SLTCOUNT+OIForumSqls.QRY_THREADLST + objBA.getHidSortKey()+OILabelConstants.SPACE+objBA.getHidOrder()+ ") LIST )");
             objRs = objPs.executeQuery();
             if(objRs!= null && objRs.next())
             {
                 bFlag = true;   		       		
                 objBA.setRowCount(objRs.getInt(1));
             } 
         }
         catch(Exception sqle)
         {
             LOGGER.error(sqle.getMessage());
             bFlag = false;
             throw sqle;
         }
         finally
         {
        	 if (objRs!= null)
             {
                 objRs.close();
             }
             if (objPs!= null)
             {
                 objPs.close();
             }
             
         }
            
         LOGGER.info(OILabelConstants.END_METHOD_DAO +"srchRowCntQry");
         return bFlag;
     }
     
     public boolean isTopicUserBoardSame(Connection con, String strTopicId, String strUserId) throws SQLException 
 	{
 		boolean isSameFlag = false;
 		ArrayList alTopic = new ArrayList();
 		alTopic.add(strTopicId);
 		alTopic.add(strUserId);
 		isSameFlag = OISQLUtilities.checkExists(con, OIForumSqls.TOPIC_BOARD_SAME, alTopic, "isTopicUserBoardSame", "OIDAOThreadList");
 		return isSameFlag;
 	}
    	
     /**
      * This method populates 
      * @param connection
      * @return ArrayList
      * @throws Exception
      */
     
     public ArrayList readList(Connection objCon,String strQuery) throws Exception
     {
         LOGGER.info(OILabelConstants.BEGIN_METHOD_DAO +"readList"); 
         ArrayList alList=null;
         ResultSet objRs =null;
         PreparedStatement objPs=null;
         boolean bFlag = false;
         try
         { 
             objPs = objCon.prepareStatement(strQuery);
             LOGGER.info(strQuery);
             objRs = objPs.executeQuery();
             String strVal = null;
             if(objRs!= null)
             {
                 bFlag = true;
                 alList = new ArrayList(); 
                 alList.add(new org.apache.struts.util.LabelValueBean("",""));
                 while(objRs.next())
                 {
                     alList.add(new org.apache.struts.util.LabelValueBean(OIFormUtilities.chkIsNull(objRs.getString(1)),OIFormUtilities.chkIsNull(objRs.getString(1))));
                 }
             } 
         }
         catch(Exception sqle)
         {
             LOGGER.error(sqle.getMessage());
             bFlag = false;
             throw sqle;
         }
         finally
         {
        	 if (objRs!= null)
             {
                 objRs.close();
             }
             if (objPs!= null)
             {
                 objPs.close();
             }
             
         }
         
         if (alList.size()==0)
         {
             alList=null;
         }		
         LOGGER.info(OILabelConstants.END_METHOD_DAO +"readList");
         return alList;
     }
    	
     /**
      * This method returns Threadid and userid of posted threads
      * @param objCon
      * @param objBA
      * @return
      * @throws Exception
      */
     
     public LinkedHashMap getUsers(Connection objCon,OIBAThread objBA) throws Exception
     {
         LOGGER.info(OILabelConstants.BEGIN_METHOD_DAO +"getUsers"); 
         LinkedHashMap hmUsers = null;
         ResultSet objRs =null;
         PreparedStatement objPs=null;
         boolean bFlag = false;
         try
         { 
             objPs = objCon.prepareStatement(OIForumSqls.QRY_POSTED);
             objPs.setInt (1,Integer.parseInt(objBA.getStrTopicId()));
             objPs.setString (2,objBA.getStrUserId());
             LOGGER.info("objBA.getStrUserId()"+objBA.getStrUserId());
             LOGGER.info(OIForumSqls.QRY_POSTED);
             objRs = objPs.executeQuery();
             String strVal = null;
             if(objRs!= null)
             {
                 bFlag = true;
                 hmUsers = new LinkedHashMap();
                 while(objRs.next())
                 {
                     hmUsers.put(OIFormUtilities.chkIsNull(objRs.getString(1)),OIFormUtilities.chkIsNull(objRs.getString(2)));
                 }
             } 
         }
         catch(Exception sqle)
         {
             LOGGER.error(sqle.getMessage());
             bFlag = false;
             throw sqle;
         }
         finally
         {
        	 if (objRs!= null)
             {
                 objRs.close();
             }
             if (objPs!= null)
             {
                 objPs.close();
             }
             
         }
         
         LOGGER.info(OILabelConstants.END_METHOD_DAO +"getUsers");
         return hmUsers;
     }
        
     /**
      * This method fetches the Topic Description for the given topic id
      * @param objCon
      * @param objBA
      * @return
      * @throws Exception
      */

     public boolean getTopicName(Connection objCon,OIBAThread objBA) throws Exception
     {
         LOGGER.info(OILabelConstants.BEGIN_METHOD_DAO +"getTopicName"); 
         LinkedHashMap hmUsers = null;
         ResultSet objRs =null;
         PreparedStatement objPs=null;
         boolean bFlag = false;
         try
         { 
             objPs = objCon.prepareStatement(OIForumSqls.QRY_TOPICNAME);
             if (objBA.getStrTopicId().equals(""))
                 objBA.setStrTopicId("0");
             LOGGER.info(OIForumSqls.QRY_TOPICNAME + "-->" + objBA.getStrTopicId());
             objPs.setInt (1,Integer.parseInt(objBA.getStrTopicId()));
             objRs = objPs.executeQuery();
             String strVal = null;
             if(objRs!= null && objRs.next())
             {
                 objBA.setStrTopicName(objRs.getString(1));	
             }
         }
         catch(Exception sqle)
         {
             LOGGER.error(sqle.getMessage());
             bFlag = false;
             throw sqle;
         }
         finally
         {
        	 if (objRs!= null)
             {
                 objRs.close();
             }
             if (objPs!= null)
             {
                 objPs.close();
             }
             
         }
                	
         LOGGER.info(OILabelConstants.END_METHOD_DAO +"getTopicName");
         return bFlag;
     }
        
     /**
      * This method returns the flags of different messages.
      * @param objCon
      * @param objBA
      * @return
      * @throws Exception
      */
        
     public LinkedHashMap getMsgs(Connection objCon,OIBAThread objBA) throws Exception
     {
         LOGGER.info(OILabelConstants.BEGIN_METHOD_DAO +"getMsgs"); 
         LinkedHashMap hmMsgs= null;
         ResultSet objRs =null;
         PreparedStatement objPs=null;
         boolean bFlag = false;
         String strThreadId = null;
         String strFlag = null;
         String strTemp = null;
          
         try
         { 
             objPs = objCon.prepareStatement(OIForumSqls.QRY_MSGS);
             objPs.setInt (1,Integer.parseInt(objBA.getStrTopicId()));
             objPs.setInt (2,Integer.parseInt(objBA.getStrTopicId()));
             LOGGER.info(OIForumSqls.QRY_MSGS);
             objRs = objPs.executeQuery();
             String strVal = null;
             if(objRs!= null)
             {
                 bFlag = true;
                 hmMsgs = new LinkedHashMap();
                 while(objRs.next())
                 {
                     strFlag = OIFormUtilities.chkIsNull(objRs.getString(2));
                     strThreadId = OIFormUtilities.chkIsNull(objRs.getString(3));
                     if(hmMsgs.containsKey(strThreadId))
                     {
                         strTemp = hmMsgs.get(strThreadId).toString();
                         hmMsgs.put(strThreadId,strTemp+strFlag);
                     }
                     else
                     {
                         hmMsgs.put(strThreadId,strFlag);
                     }	
                 }
             } 
         }
         catch(Exception sqle)
         {
             LOGGER.error(sqle.getMessage());
             bFlag = false;
             throw sqle;
         }
         finally
         {
        	 if (objRs!= null)
             {
                 objRs.close();
             }
             if (objPs!= null)
             {
                 objPs.close();
             }
             
             strThreadId = null;
             strFlag = null;
             strTemp = null;
         }

         LOGGER.info(OILabelConstants.END_METHOD_DAO +"getMsgs");
         return hmMsgs;
     }
        
     /**
      * This method return list of sticky threads
      * @param objCon
      * @param objBA
      * @return
      * @throws Exception
      */
     public LinkedHashMap getStickyThreads(Connection objCon,OIBAThread objBA) throws Exception
     {
         LOGGER.info(OILabelConstants.BEGIN_METHOD_DAO +"getStickyThreads"); 
         LinkedHashMap hmMsgs= null;
         ResultSet objRs =null;
         PreparedStatement objPs=null;
         boolean bFlag = false;
         String strThreadId = null;
          
         try
         { 
             objPs = objCon.prepareStatement(OIForumSqls.QRY_STICKYTHREAD);
             objPs.setString (1,objBA.getStrUserId());
             LOGGER.info(OIForumSqls.QRY_MSGS);
             objRs = objPs.executeQuery();
             String strVal = null;
             if(objRs!= null)
             {
                 bFlag = true;
                 hmMsgs = new LinkedHashMap();
                 while(objRs.next())
                 {
                     strThreadId = OIFormUtilities.chkIsNull(objRs.getString(1));
                     hmMsgs.put(strThreadId,strThreadId);
                 }	
             }
         }
         catch(Exception sqle)
         {
             LOGGER.error(sqle.getMessage());
             bFlag = false;
             throw sqle;
         }
         finally
         {
        	 if (objRs!= null)
             {
                 objRs.close();
             }
             if (objPs!= null)
             {
                 objPs.close();
             }
             
             strThreadId = null;
         }
               	
         LOGGER.info(OILabelConstants.END_METHOD_DAO +"getStickyThreads");
         return hmMsgs;
     }
        
     /**
      * This method return list of sticky threads
      * @param objCon
      * @param objBA
      * @return
      * @throws Exception
      */
     public LinkedHashMap getAdminStickyThreads(Connection objCon,OIBAThread objBA) throws Exception
     {
         LOGGER.info(OILabelConstants.BEGIN_METHOD_DAO +"getAdminStickyThreads"); 
         LinkedHashMap hmMsgs= null;
         ResultSet objRs =null;
         PreparedStatement objPs=null;
         String strThreadId = null;
          
         try
         { 
             objPs = objCon.prepareStatement(OIForumSqls.QRY_ADMIN_STICKYTHREAD);
             LOGGER.info(OIForumSqls.QRY_ADMIN_STICKYTHREAD);
             objRs = objPs.executeQuery();
             String strVal = null;
             if(objRs!= null)
             {
                  hmMsgs = new LinkedHashMap();
                 while(objRs.next())
                 {
                     strThreadId = OIFormUtilities.chkIsNull(objRs.getString(1));
                     hmMsgs.put(strThreadId,strThreadId);
                 }	
             }
         }
         catch(Exception sqle)
         {
             LOGGER.error(sqle.getMessage());
             throw sqle;
         }
         finally
         {
        	 if (objRs!= null)
             {
                 objRs.close();
             }
             if (objPs!= null)
             {
                 objPs.close();
             }
             
             strThreadId = null;
         }
               	
         LOGGER.info(OILabelConstants.END_METHOD_DAO +"getAdminStickyThreads");
         return hmMsgs;
     }

     /**
      * This method checks for the sticky threads
      * @param objThread
      * @param hmSticky
      */
     private void chkSticky(OIBAThread objThread,LinkedHashMap hmSticky)
     {
     	LOGGER.info("1.." );
         if(hmSticky != null && !OIUtilities.replaceNull(objThread.getStrImgMsg()).equalsIgnoreCase(OIForumConstants.FLAG_ADMIN))
         { 
         	LOGGER.info("1.." );
             if(hmSticky.containsKey(objThread.getStrThreadId()))
             {
             	LOGGER.info("2.." );
                 objThread.setStrImgMsg(OIForumConstants.FLAG_A);
             }
             else
             {
             	LOGGER.info("3.." );
                 objThread.setStrImgMsg(OILabelConstants.SPACE);
             }	
         }		
     }
        
     /**
      * This method checks for the sticky threads
      * @param objThread
      * @param hmSticky
      */
     private void chkAdminSticky(OIBAThread objThread,LinkedHashMap hmAdminSticky)
     {
         if(hmAdminSticky != null)
         { 
             if(hmAdminSticky.containsKey(objThread.getStrThreadId()))
             {
                 objThread.setStrImgMsg(OIForumConstants.FLAG_ADMIN);
             }
          }		
     }

     /**
      * Chec 
      * @param objThread
      * @param hmMsgs
      */
     private void chkMsgs(OIBAThread objThread,LinkedHashMap hmMsgs)
     {
         if(hmMsgs != null && !(OIUtilities.replaceNull(objThread.getStrImgMsg()).equalsIgnoreCase(OIForumConstants.FLAG_A) ||
         		objThread.getStrImgMsg().equalsIgnoreCase(OIForumConstants.FLAG_ADMIN)))
         {
             if(hmMsgs.containsKey(objThread.getStrThreadId()))
             {
                 objThread.setStrImgMsg(hmMsgs.get(objThread.getStrThreadId()).toString());
                 LOGGER.info("hmMsgs.get(objThread.getStrThreadId()).toString()"+hmMsgs.get(objThread.getStrThreadId()).toString());
             }
         }
     }
       
     /**
      * Checking for the Threads of Posted,Locked,Private
      * @param objThread
      * @param hmUsers
      */
     private void chkThreads(OIBAThread objThread,LinkedHashMap hmUsers)
     {
         LOGGER.info("objThread.setStrImgThread "+ objThread.getStrImgThread());
         if(hmUsers != null)
         {
             if(hmUsers.containsKey(objThread.getStrThreadId()))
             {
                 objThread.setStrImgThread(OIForumConstants.FLAG_A);
             }
             else if(objThread.getStrLocked().trim().equalsIgnoreCase(OIForumConstants.FLAG_Y))
             {
                 objThread.setStrImgThread(OIForumConstants.FLAG_B);
             }
             else if(objThread.getStrSecurity().trim().equalsIgnoreCase(OIForumConstants.FLAG_Y))
             {
                 objThread.setStrImgThread(OIForumConstants.FLAG_C);
             }
         }
         else
         {
             if(objThread.getStrLocked().trim().equalsIgnoreCase(OIForumConstants.FLAG_Y))
             {
                 objThread.setStrImgThread(OIForumConstants.FLAG_B);
             }
             else if(objThread.getStrSecurity().trim().equalsIgnoreCase(OIForumConstants.FLAG_Y))
             {
                 objThread.setStrImgThread(OIForumConstants.FLAG_C);
             }
         }
     }
}