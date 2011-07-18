/*************************************************************************************************
 * File Name		:	OIDAOSendMail.java
 * Author			:	SureshKumar.R
 * Description		:   This DAO is used to fetch consultation paper , Surveys information and send 
 * 						the mail to list of users.   		
 * Created Date		:	Jul 10 2005
 * Version			:	1.0
 * Copyright 		: Scandent Group
 ************************************************************************************************/ 

package com.oifm.common;  

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;

import com.oifm.utility.DateUtility;
import com.oifm.utility.OIFormUtilities;
import com.oifm.utility.OIUtilities;

public class OIDAOSendMail
{
	transient private final Logger LOGGER = Logger.getLogger(this.getClass().getName());
	
	public OIDAOSendMail()
	{
	    LOGGER.debug(OILabelConstants.BEGIN_METHOD_DAO + this.getClass().getName());
	} 
	
	/**
	 * This method returns Message & Subject of Consulation Paper  
	 * @param con
	 * @param objOIBVSendMail
	 * @return
	 * @throws Exception
	 */
	public boolean readConsPapMessage(Connection objCon,OIBVSendMail objOIBVSendMail)throws Exception
    {
		LOGGER.debug(OILabelConstants.BEGIN_METHOD_DAO +"readConsPapMessage"); 
    	
    	OIBVMailList objBVMailList = null; 
        ArrayList arOIBVSendMail = null;
        ResultSet objRs =null;
        PreparedStatement objPs=null;
        boolean bFlag = false;
        try
        {
            objPs = objCon.prepareStatement(OISendMailSqls.SENDMAIL_C_SUB);
        	/** S - Send Mail   - Retrival of Subject & Message **/
            if( (objOIBVSendMail.getSendOrRemain().equalsIgnoreCase("S")))
            {
            	/** Setting of CP Send Mail Subject **/
            	objPs.setString (1,OILabelConstants.CP_MAIL_SUBJECT);
            	
            	objRs = objPs.executeQuery();
            	if(objRs !=null && objRs.next())
            	{
            		bFlag = true;
            		objOIBVSendMail.setSubject(OIFormUtilities.chkIsNull(objRs.getString(1)));
            	}
            	/** Setting of CP Mail Body **/	
            	objPs.setString (1,OILabelConstants.CP_MAIL_BODY);
            	objRs = objPs.executeQuery();
            	if(objRs!=null  && objRs.next())
            	{
            		 objOIBVSendMail.setMessage(OIFormUtilities.chkIsNull(objRs.getString(1)));
            	}
            }
            else if(objOIBVSendMail.getSendOrRemain().equalsIgnoreCase("R"))
            {
            	/** Setting of CP Remainder Mail Subject **/
            	objPs.setString (1,OILabelConstants.CP_REMINDER_SUBJECT);
            	objRs = objPs.executeQuery();
                if(objRs !=null && objRs.next())
                {
                	bFlag = true;
                	objOIBVSendMail.setSubject(OIFormUtilities.chkIsNull(objRs.getString(1)));
                }
                /** Setting of CP Remainder Mail Body **/
                objPs.setString (1,OILabelConstants.CP_REMINDER_BODY);
                objRs = objPs.executeQuery();
                if(objRs !=null && objRs.next())
                {
                    objOIBVSendMail.setMessage(OIFormUtilities.chkIsNull(objRs.getString(1)));
                }
            }
                
            objPs = objCon.prepareStatement(OISendMailSqls.QRY_TITLE_CP);
            objPs.setInt (1,Integer.parseInt(objOIBVSendMail.getId()));
            objRs = objPs.executeQuery();
            if(objRs !=null && objRs.next())
            {
                bFlag = true;
                objOIBVSendMail.setCaption(OIFormUtilities.chkIsNull(objRs.getString(1)));
				String newMsg = OIUtilities.clobToString(objRs.getClob(2));
				if(newMsg!=null && newMsg.length()>0)
				{
					objOIBVSendMail.setMessage(newMsg);
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
            if (objPs!= null)
            {
				objPs.close();
			}
			if (objRs!= null)
			{
				objRs.close();
			}
        }
    	LOGGER.debug(OILabelConstants.END_METHOD_DAO +"readConsPapMessage");
        return bFlag;
    }
	
	/**
	 * This method returns list of users of Consulation Paper
	 * @param objCon
	 * @param objOIBVSendMail
	 * @return
	 * @throws Exception
	 */
    
    public ArrayList readConsPapUsers(Connection objCon,OIBVSendMail objOIBVSendMail)throws Exception
    {
        OIBASendMail aOIBASendMail=null;
        ArrayList arOIBASendMail=null;
        ResultSet objRs =null;
        PreparedStatement objPs=null;
        boolean bFlag = false;
        try
        { 
        	objPs = objCon.prepareStatement(OISendMailSqls.SELECT_CON_PAPER_MEM + OISendMailSqls.ORDERBY);
        	objPs.setInt (1,Integer.parseInt(objOIBVSendMail.getId()));
        	objRs = objPs.executeQuery();
            if(objRs!= null)
            {
           		bFlag = true;
           		arOIBASendMail = new ArrayList(); 
           		while(objRs.next())
           		{
           			aOIBASendMail = new OIBASendMail();
           			aOIBASendMail.setNric(OIFormUtilities.chkIsNull(objRs.getString(1)));
	                aOIBASendMail.setName(OIFormUtilities.chkIsNull(objRs.getString(2)));
	                aOIBASendMail.setResponse(OIFormUtilities.chkIsNull(objRs.getString(3)));
	                aOIBASendMail.setChk(OIFormUtilities.chkIsNull(objRs.getString(4)));
	                aOIBASendMail.setSurveys(OIFormUtilities.chkIsNull(objRs.getString(5)));
	                aOIBASendMail.setPapers(OIFormUtilities.chkIsNull(objRs.getString(7)));
	                arOIBASendMail.add(aOIBASendMail);
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
			if (objPs!= null)
			{
				objPs.close();
			}
			if (objRs!= null)
			{
				objRs.close();
			}
		}
        if (arOIBASendMail.size()==0)
        {
            arOIBASendMail=null;
        }
        
        return arOIBASendMail;
    }

    /**
	 * This method return the Subject & Descritpion 
	 * @param con
	 * @param objOIBVSendMail
	 * @return
	 * @throws Exception
	 */
	public boolean readSurveyMessage(Connection objCon,OIBVSendMail objOIBVSendMail)throws Exception
    {
		LOGGER.debug(OILabelConstants.BEGIN_METHOD_DAO +"readSurveyMessage"); 
    	
    	OIBVMailList objBVMailList = null; 
        ArrayList arOIBVSendMail = null;
        ResultSet objRs =null;
        PreparedStatement objPs=null;
        boolean bFlag = false;
        try
        {
        	objPs = objCon.prepareStatement(OISendMailSqls.SENDMAIL_C_SUB);
        	/** S - Send Mail   - Retrival of Subject & Message **/
            if( (objOIBVSendMail.getSendOrRemain().equalsIgnoreCase("S")))
            {
            	/** Setting of CP Send Mail Subject **/
            	objPs.setString (1,OILabelConstants.SU_MAIL_SUBJECT);
            	
            	objRs = objPs.executeQuery();
            	if(objRs !=null && objRs.next())
            	{
            		bFlag = true;
            		objOIBVSendMail.setSubject(OIFormUtilities.chkIsNull(objRs.getString(1)));
            	}
            	/** Setting of CP Mail Body **/	
            	objPs.setString (1,OILabelConstants.SU_MAIL_BODY);
            	objRs = objPs.executeQuery();
            	if(objRs!=null  && objRs.next())
            	{
            		 objOIBVSendMail.setMessage(OIFormUtilities.chkIsNull(objRs.getString(1)));
            	}
            }
            else if(objOIBVSendMail.getSendOrRemain().equalsIgnoreCase("R"))
            {
            	/** Setting of CP Remainder Mail Subject **/
            	objPs.setString (1,OILabelConstants.SU_REMINDER_SUBJECT);
            	objRs = objPs.executeQuery();
                if(objRs !=null && objRs.next())
                {
                	bFlag = true;
                	objOIBVSendMail.setSubject(OIFormUtilities.chkIsNull(objRs.getString(1)));
                }
                /** Setting of CP Remainder Mail Body **/
                objPs.setString (1,OILabelConstants.SU_REMINDER_BODY);
                objRs = objPs.executeQuery();
                if(objRs !=null && objRs.next())
                {
                    objOIBVSendMail.setMessage(OIFormUtilities.chkIsNull(objRs.getString(1)));
                }
            }
            objPs = objCon.prepareStatement(OISendMailSqls.QRY_TITLE_SU);
            objPs.setInt (1,Integer.parseInt(objOIBVSendMail.getId()));
            objRs = objPs.executeQuery();
            if(objRs !=null && objRs.next())
            {
            	bFlag = true;
            	objOIBVSendMail.setCaption(OIFormUtilities.chkIsNull(objRs.getString(1)));
				String newMsg = OIUtilities.clobToString(objRs.getClob(2));
				if(newMsg!=null && newMsg.length()>0)
				{
					objOIBVSendMail.setMessage(newMsg);
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
    		if (objPs!= null)
    		{
				objPs.close();
			}
			if (objRs!= null)
			{
				objRs.close();
			}
        }
    	LOGGER.debug(OILabelConstants.END_METHOD_DAO +"readSurveyMessage");
        return bFlag;
    }
	
    /**
     * This method return the List of users of surveys 
     * @param objCon
     * @param objOIBVSendMail
     * @return
     * @throws Exception
     */
    public ArrayList readSurveyUsers(Connection objCon,OIBVSendMail objOIBVSendMail)throws Exception
    {
        OIBASendMail aOIBASendMail=null;
        ArrayList arOIBASendMail=null;
        ResultSet objRs =null;
        PreparedStatement objPs=null;
        boolean bFlag = false;
        try
        { 
        	objPs = objCon.prepareStatement(OISendMailSqls.SELECT_SURVEY_MEM + OISendMailSqls.ORDERBY);
        	objPs.setInt (1,Integer.parseInt(objOIBVSendMail.getId()));
        	objRs = objPs.executeQuery();
            if(objRs!= null)
            {
           		bFlag = true;
           		arOIBASendMail = new ArrayList(); 
           		while(objRs.next())
           		{
           			aOIBASendMail = new OIBASendMail();
                
           			aOIBASendMail.setNric(OIFormUtilities.chkIsNull(objRs.getString(1)));
	                aOIBASendMail.setName(OIFormUtilities.chkIsNull(objRs.getString(2)));
	                aOIBASendMail.setResponse(OIFormUtilities.chkIsNull(objRs.getString(3)));
	                aOIBASendMail.setChk(OIFormUtilities.chkIsNull(objRs.getString(4)));
	                aOIBASendMail.setSurveys(OIFormUtilities.chkIsNull(objRs.getString(5)));
	                aOIBASendMail.setPapers(OIFormUtilities.chkIsNull(objRs.getString(7)));

	                arOIBASendMail.add(aOIBASendMail);
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
			if (objPs!= null)
			{
				objPs.close();
			}
			if (objRs!= null)
			{
				objRs.close();
			}
		}
        if (arOIBASendMail.size()==0)
        {
            arOIBASendMail=null;
        }
        
        return arOIBASendMail;
    }
 
    /**
     * This Method return Email Ids of all Users.
     * @param objCon
     * @param objOIBVSendMail
     * @return
     * @throws Exception
     */
    
    public boolean readEmail(Connection objCon,OIBVSendMail objOIBVSendMail,String strQuery)throws Exception
    {
       	ArrayList arEmail=null;
        ResultSet objRs =null;
        PreparedStatement objPs=null;
        boolean bFlag = false;
    	try
    	{
    	 	objPs = objCon.prepareStatement(strQuery);
    	 	objPs.setInt (1,Integer.parseInt(objOIBVSendMail.getId()));
            objRs = objPs.executeQuery();
            if(objRs!= null)
            {
           		bFlag = true;
           		arEmail = new ArrayList(); 
           		while(objRs.next())
           		{
           		    if(objRs.getString(1)!=null &&  !OIFormUtilities.chkIsNull(objRs.getString(3)).equalsIgnoreCase("Y"))
           		    {
           		        arEmail.add(objRs.getString(1));
           		    }	
           		}
            }
            if(arEmail!=null && arEmail.size()>0)
            {
            	objOIBVSendMail.setEmail(arEmail.toString());
            }
            else
            {
            	objOIBVSendMail.setEmail("");
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
			if (objRs!= null)
			{
				objRs.close();
			}
		}
        return bFlag;
   }
    
    
    /**
     * This Method return Email Ids and Users Names
     * @param objCon
     * @param objOIBVSendMail
     * @return
     * @throws Exception
     */
    
    public ArrayList readUsrEmail(Connection objCon,OIBVSendMail objOIBVSendMail,String strQuery)throws Exception
    {
       	ArrayList arEmail=null;
        ResultSet objRs =null;
        PreparedStatement objPs=null;
        boolean bFlag = false;
        OIBASendMail objBA = null; 
        
    	try
    	{
    	 	objPs = objCon.prepareStatement(strQuery);
    	 	objPs.setInt (1,Integer.parseInt(objOIBVSendMail.getId()));
            objRs = objPs.executeQuery();
            if(objRs!= null)
            {
           		bFlag = true;
           		arEmail = new ArrayList();
           		while(objRs.next())
           		{
           			if(!OIFormUtilities.chkIsNull(objRs.getString(3)).equalsIgnoreCase("Y"))
           			{
	           			objBA = new OIBASendMail();
	           			objBA.setId(OIFormUtilities.chkIsNull(objRs.getString(1)));
	           			objBA.setName(OIFormUtilities.chkIsNull(objRs.getString(2)));
	           			arEmail.add(objBA);
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
			if (objPs!= null)
			{
				objPs.close();
			}
			if (objRs!= null)
			{
				objRs.close();
			}
		}
        
        return arEmail;
   }
    
    /**
     * This method is used to delete the users for Consulation Paper and Surveys based on the query passed
     * @param objCon
     * @param objOIBVSendMail
     * @param strQuery
     * @return
     * @throws Exception
     */
    public boolean deleteUsers(Connection objCon,OIBVSendMail objOIBVSendMail ,String strQuery)throws Exception
    {
    	ArrayList arEmail=null;
        ResultSet objRs =null;
        PreparedStatement objPs=null;
        String[] strRemoveId = null;
		boolean bFlag = false;
        int[] nDelete = null;
    	try
    	{
    	 	objPs = objCon.prepareStatement(strQuery);
    	 	strRemoveId = objOIBVSendMail.getRemoveId();
    	 	
    	 	int batchCount = 0;
    	 	int executeCount = 0;
    	 	
    	 	for(int i =0;i < strRemoveId.length; i++ )
    	 	{
    	 		if(!strRemoveId[i].equals("userid"))
    	 		{
	     	 		objPs.setString(1,OIFormUtilities.chkIsNull(strRemoveId[i]));
	        	 	objPs.setInt(2, Integer.parseInt(objOIBVSendMail.getId()));
	        	 	objPs.addBatch();
	        	 	batchCount++;
    	 		}
    	 		
    	 		if (batchCount == 5000)
    	 		{
    	 			nDelete = objPs.executeBatch();
    	 			executeCount += nDelete.length;
    	 			batchCount = 0;
    	 			//objPs = objCon.prepareStatement(strQuery);
    	 		}
        	}
    	 	
    	 	LOGGER.debug(strQuery);
    	 	if (batchCount > 0)
        	{
	    	 	nDelete = objPs.executeBatch(); 
	    	 	executeCount += nDelete.length;
        	}
                       
            if(executeCount >0)
            {
               	objOIBVSendMail.setDelCnt(String.valueOf(nDelete.length));
            	bFlag = true;
            }
            else
            {
            	objOIBVSendMail.setDelCnt(null);
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
			if (objRs!= null)
			{
				objRs.close();
			}
		}
		return bFlag;
    }
    
    /**
	 * This method returns list of users of Consulation Paper
	 * @param objCon
	 * @param objOIBVSendMail
	 * @return
	 * @throws Exception
	 */  
    
    public ArrayList sortConsPapUsers(Connection objCon,OIBVSendMail objOIBVSendMail)throws Exception
    {
        OIBASendMail aOIBASendMail=null;
        ArrayList arOIBASendMail=null;
        ResultSet objRs =null;
        PreparedStatement objPs=null;
        StringBuffer sbQuery = new StringBuffer(0); 
        
        boolean bFlag = false;
        try
        { 
        	sbQuery.append(OISendMailSqls.SELECT_CON_PAPER_MEM)
        		   .append(OILabelConstants.ORDER_BY)
				   //.append("PROFILEINFO.")
        		   .append(OIFormUtilities.chkIsNull(objOIBVSendMail.getHidSortKey()))
				   .append(OILabelConstants.SPACE)
				   .append(objOIBVSendMail.getHidOrder());
        		   
        		   
        	objPs = objCon.prepareStatement(sbQuery.toString());
        	objPs.setInt (1,Integer.parseInt(objOIBVSendMail.getId()));
        	objRs = objPs.executeQuery();
            if(objRs!= null)
            {
           		bFlag = true;
           		arOIBASendMail = new ArrayList(); 
           		while(objRs.next())
           		{
           			aOIBASendMail = new OIBASendMail();
           			aOIBASendMail.setNric(OIFormUtilities.chkIsNull(objRs.getString(1)));
	                aOIBASendMail.setName(OIFormUtilities.chkIsNull(objRs.getString(2)));
	                aOIBASendMail.setResponse(OIFormUtilities.chkIsNull(objRs.getString(3)));
	                aOIBASendMail.setPapers(OIFormUtilities.chkIsNull(objRs.getString(4)));
	                aOIBASendMail.setSurveys(OIFormUtilities.chkIsNull(objRs.getString(5))); 
	                aOIBASendMail.setChk(OIFormUtilities.chkIsNull(objRs.getString(6)));
	                arOIBASendMail.add(aOIBASendMail);
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
			if (objPs!= null)
			{
				objPs.close();
			}
			if (objRs!= null)
			{
				objRs.close();
			}
			sbQuery.setLength(0);
		}
        if (arOIBASendMail.size()==0)
        {
            arOIBASendMail=null;
        }
        return arOIBASendMail;
    }
    
    /**
     * This method return the List of users of surveys 
     * @param objCon
     * @param objOIBVSendMail
     * @return
     * @throws Exception
     */
    public ArrayList sortSurveyUsers(Connection objCon,OIBVSendMail objOIBVSendMail)throws Exception
    {
        OIBASendMail aOIBASendMail=null;
        ArrayList arOIBASendMail=null;
        ResultSet objRs =null;
        PreparedStatement objPs=null;
        boolean bFlag = false;
        StringBuffer sbQuery = new StringBuffer(0); 
        
        try
        { 
        	sbQuery.append(OISendMailSqls.SELECT_SURVEY_MEM)
 		   		   .append(OILabelConstants.ORDER_BY)
				   .append(objOIBVSendMail.getHidSortKey())
				   .append(OILabelConstants.SPACE)
			       .append(objOIBVSendMail.getHidOrder());
        	  
        	objPs = objCon.prepareStatement(sbQuery.toString());
        	objPs.setInt (1,Integer.parseInt(objOIBVSendMail.getId()));
        	objRs = objPs.executeQuery();
            if(objRs!= null)
            {
           		bFlag = true;
           		arOIBASendMail = new ArrayList(); 
           		while(objRs.next())
           		{
           			aOIBASendMail = new OIBASendMail();
           			aOIBASendMail.setNric(OIFormUtilities.chkIsNull(objRs.getString(1)));
	                aOIBASendMail.setName(OIFormUtilities.chkIsNull(objRs.getString(2)));
	                aOIBASendMail.setResponse(OIFormUtilities.chkIsNull(objRs.getString(3))); 
	                aOIBASendMail.setPapers(OIFormUtilities.chkIsNull(objRs.getString(4)));
	                aOIBASendMail.setSurveys(OIFormUtilities.chkIsNull(objRs.getString(5)));
	                aOIBASendMail.setChk(OIFormUtilities.chkIsNull(objRs.getString(6)));
	                arOIBASendMail.add(aOIBASendMail);
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
			if (objPs!= null)
			{
				objPs.close();
			}
			if (objRs!= null)
			{
				objRs.close();
			}
			sbQuery.setLength(0);
		}
        if (arOIBASendMail.size()==0)
        {
            arOIBASendMail=null;
        }
        
        return arOIBASendMail;
    }
    
    /**
     * This method is used to update the Send Remainder in Survey and Paper table 
     * @param objCon
     * @param objOIBVSendMail
     * @param strQuery
     * @return
     * @throws Exception
     */
    public boolean updateRem(Connection objCon,OIBVSendMail objOIBVSendMail ,String strQuery,String strQryUpdate)throws Exception
    {
        ArrayList arEmail=null;
        ResultSet objRs =null;
        PreparedStatement objPs=null;
        String[] strRemoveId = null;
    	boolean bFlag = false;
        int nUpdate = 0;
        try
        {
            objPs = objCon.prepareStatement(strQuery);
            objPs.setInt(1,Integer.parseInt(objOIBVSendMail.getId()));
        	nUpdate = objPs.executeUpdate(); 
            if(nUpdate >0)
            {
                bFlag = true;
            }
                
            if(bFlag)
            {
                /** This method updates the sysdate in invitation column in both OI_CP_MEMBERS,OI_SU_MEMBERS **/
	            objPs = objCon.prepareStatement(strQryUpdate);
	        	objPs.setInt(1,Integer.parseInt(objOIBVSendMail.getId()));
	        	objPs.setInt(2,Integer.parseInt(objOIBVSendMail.getId()));
	        	nUpdate = objPs.executeUpdate();
	        	if(nUpdate==0)
	        	{
	        	    bFlag = false;	                	
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
            if (objPs!= null)
            {
                objPs.close();
    		}
    		if (objRs!= null)
    		{
    		    objRs.close();
    		}
        }
    	return bFlag;
    }
        
    /**
     * This method fetchs the number of records to be displayed per page
     * @param objCon
     * @return
     * @throws Exception
     */
    public static int recPerPage(Connection objCon)throws Exception
    {
        ResultSet objRs =null;
        PreparedStatement objPs=null;
        String[] strRemoveId = null;
		boolean bFlag = false;
        int nRecords = 0;
    	try
    	{
    	 	objPs = objCon.prepareStatement(OISendMailSqls.QRY_REC_PER_PAGE);
    	  	objRs = objPs.executeQuery(); 
            if( objRs != null && objRs.next())
            {
            	nRecords = objRs.getInt(1); 
            }
    	}
    	catch(Exception sqle)
    	{
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
		return nRecords;
     }
    
     /**
      * This method return the List of users of surveys 
      * @param objCon
      * @param objOIBVSendMail
      * @return
      * @throws Exception
      */
     public ArrayList getPapersSurveys(Connection objCon,OIBVSendMail objOIBVSendMail,String strQuery)throws Exception
     {
         OIBASendMail aOIBASendMail=null;
         ArrayList arPapSur=null;
         ResultSet objRs =null;
         PreparedStatement objPs=null;
         boolean bFlag = false;
         StringBuffer sbQuery = new StringBuffer(0); 

         try
         { 
             objPs = objCon.prepareStatement(strQuery);
             objPs.setString (1,objOIBVSendMail.getId());
             objRs = objPs.executeQuery();
             if(objRs!= null)
             {
                 bFlag = true;
                 arPapSur = new ArrayList(); 
                 while(objRs.next())
                 {
                     aOIBASendMail = new OIBASendMail();

                     aOIBASendMail.setId(OIFormUtilities.chkIsNull(objRs.getString(1)));
                     aOIBASendMail.setDescription(OIFormUtilities.chkIsNull(objRs.getString(2)));
                     aOIBASendMail.setName(OIFormUtilities.chkIsNull(objRs.getString(3)));
                     aOIBASendMail.setStartDate(OIFormUtilities.chkIsNull(objRs.getString(4)));
                     aOIBASendMail.setEndDate(OIFormUtilities.chkIsNull(objRs.getString(5)));
                     arPapSur.add(aOIBASendMail);
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
             if (objPs!= null)
             {
                 objPs.close();
             }
             if (objRs!= null)
             {
                 objRs.close();
             }
             sbQuery.setLength(0);
         }
         if (arPapSur.size()==0)
         {
             arPapSur=null;
         }
         
         return arPapSur;
     }
     
     /**
      * This method returns Personal MailId, Admin Mail Ids.
      * @param objCon
      * @param objOIBVSendMail
      * @param strQuery
      * @return
      * @throws Exception
      */
     public ArrayList getAdminIds(Connection objCon,OIBVSendMail objOIBVSendMail,String strQuery,String strHidAct)throws Exception
     {
       	ArrayList arEmail=null;
        ResultSet objRs =null;
        PreparedStatement objPs=null;
        boolean bFlag = false;
        OIBASendMail objBA = null; 
        
    	try
    	{
    	 	objPs = objCon.prepareStatement(strQuery);
    	 	if(strHidAct.equals(OILabelConstants.PMMAIL))
    	 	{
    	 		objPs.setInt (1,Integer.parseInt(objOIBVSendMail.getId()));
    	 	}
    	 	else if(strHidAct.length()==0)
    	 	{
    	 		objPs.setString(1,objOIBVSendMail.getUserId());
    	 	}
            objRs = objPs.executeQuery();
            if(objRs!= null)
            {
           		bFlag = true;
           		arEmail = new ArrayList();
           		while(objRs.next())
           		{
           			if(objRs.getString(1)!= null)
           			{
           			    arEmail.add(OIFormUtilities.chkIsNull(objRs.getString(1)));
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
			if (objPs!= null)
			{
				objPs.close();
			}
			if (objRs!= null)
			{
				objRs.close();
			}
		}
        
        return arEmail;
   }
     
     /**
      * This methods returns Threadid and postedby for the given topic Id
      * @param objCon
      * @param objOIBVSendMail
      * @param strQuery
      * @return
      * @throws Exception
      */
     public boolean getPostedOn(Connection objCon,OIBVSendMail objOIBVSendMail)throws Exception
     {
       	ArrayList arEmail=null;
        ResultSet objRs =null;
        PreparedStatement objPs=null;
        boolean bFlag = false;
        OIBASendMail objBA = null; 
        
    	try
    	{
    	 	objPs = objCon.prepareStatement(OISendMailSqls.QRY_POSTS);
    	 	objPs.setInt (1,Integer.parseInt(objOIBVSendMail.getId()));
    	 	objRs = objPs.executeQuery();
            if(objRs!= null && objRs.next())
            {
           		bFlag = true;
           		objOIBVSendMail.setId(objRs.getString(1)); /** ThreadId; **/
           		objOIBVSendMail.setPostedOn(objRs.getString(2)); /** Posted date and Time **/
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
			if (objRs!= null)
			{
				objRs.close();
			}
		}
        
        return bFlag;
   }
       
     /**
      * This method returns the domain Ids  	
      * @param objCon
      * @return
      * @throws Exception
      */
     public ArrayList getDomainNames(Connection objCon) throws Exception
     {
        ArrayList arMailIds=null;
        ResultSet objRs =null;
        PreparedStatement objPs=null;
        boolean bFlag = false;
        
        try 
        { 
        	objPs = objCon.prepareStatement(OISendMailSqls.QRY_DOMAINNAMES);
        	objRs = objPs.executeQuery();
            if(objRs!= null)
            {
           		bFlag = true;
           		arMailIds = new ArrayList(); 
           		while(objRs.next())
           		{
           		    if(objRs.getString(1)!= null && OIFormUtilities.chkIsNull(objRs.getString(1)).length() >0)
           		    {
           		        arMailIds.add(OIFormUtilities.chkIsNull(objRs.getString(1)));
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
			if (objPs!= null)
			{
				objPs.close();
			}
			if (objRs!= null)
			{
				objRs.close();
			}
		}
        if (arMailIds.size()==0)
        {
        	arMailIds=null;
        }
        
        return arMailIds;
     }
     
     /**
      * This is the helper method to convert date to String dd-mmm-yyyy format
      * @param objDt
      * @return
      */
     private String convertDate(Date objDt)
     {
     	String strDate = null;
     	try
     	{
	     	if (objDt!=null)
	     	{
	     		strDate = DateUtility.getMMDDYYYYStringFromDate(objDt);
	     	}
     	}
     	catch(Exception e)
     	{
	    	LOGGER.error(e.getMessage());
	    }	
	   	return  OIFormUtilities.chkIsNull(strDate);
     }	
     
     // This method is to append the footer Message for PM mails.
     // Added by Rakesh.
     
     public String getFootMsg(Connection connection)throws Exception{
    	String strFoot ="";
    	ResultSet objRs =null;
        PreparedStatement objPs=null;
        try{
        	objPs = connection.prepareStatement(OISendMailSqls.QRY_ADD_FOOTER_MESG);
			 
			objRs = objPs.executeQuery();
			while(objRs.next())
			{
				strFoot=objRs.getString("Description");
			}
        }catch(Exception sqle){
        	LOGGER.error("Exception in DAO"+ sqle.getMessage());
    		throw sqle;
        }finally{
    		if (objPs!= null){
				objPs.close();
			}
			if (objRs!= null){
				objRs.close();
			}
        }	
    	
    	return strFoot; 
     }
     
     public String getNickname(String aName, Connection connection)throws Exception{
    	 
    	 	OIBASendMail aOIBASendMail = null;
 			ResultSet rs = null;
 			PreparedStatement preparedStatement = null;
 			try {
 				// getConnection();
 				//System.out.println("OIDAOSendMail1-getNickname");
 				preparedStatement = connection.prepareStatement(OISendMailSqls.READ_NICKNAME);
 				preparedStatement.setString(1, aName);
 				//System.out.println("OIDAOSendMail-getNickname2" + preparedStatement.executeQuery());
 				rs = preparedStatement.executeQuery();
 				if (rs.next()) {
 					aOIBASendMail = new OIBASendMail();
 					//aName=rs.getString("NICKNAME");
 					aOIBASendMail.setNickname(rs.getString("NICKNAME"));
 				//System.out.println("OIDAOSendMail3-getNickname" + rs.getString("NICKNAME"));
 				}
 				// preparedStatement.close();
 			} catch (SQLException sqle) {
 			// connection.rollback();
 				LOGGER.error("Exception in DAO"+ sqle.getMessage());
 				throw sqle;
 			} finally {
 				// freeConnection();
 				if (preparedStatement != null)
 					preparedStatement.close();
 				if (rs != null)
 					rs.close();
 			}
 			//System.out.println("OIDAOSendMail-getNickname" + aOIBASendMail.getNickname());
 		return aOIBASendMail.getNickname();
    	 
     }
     
     
}