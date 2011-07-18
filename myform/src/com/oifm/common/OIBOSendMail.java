 /*************************************************************************************************
 * File Name		:	OIBOSendMail.java
 * Author			:	SureshKumar.R
 * Description		:   This BO is used to Send the Mail		
 * Created Date		:	Jul 10, 2005
 * Version			:	1.0
 * Copyright : Scandent Group
 *************************************************************************************************/
 

package com.oifm.common;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseBo;
import com.oifm.utility.MailUtility;
import com.oifm.utility.OIDBRegistry;
import com.oifm.utility.OIFormUtilities;

public class OIBOSendMail extends OIBaseBo 
{
    //	Create instance for LOGGER and objects  used
	transient private final Logger LOGGER = Logger.getLogger(this.getClass().getName());
	
	public OIBOSendMail()
	{
		LOGGER.debug(OILabelConstants.BEGIN_METHOD_BO + this.getClass().getName());
	}
	
	
    /**
     * This method calls
     * 
     * if pModuleName.equals("Survey")
     * 1. OIDAOSendMail.readSurveyMembers
     * 
     * if pModuleName.equals("Consultation")
     * 2. OIDAOUserProfileSearch.readConsultationMembers
     * 
     * and returns responseObject, which holds ArrayLists of OIBVMailList 
     */
			
	
    public OIResponseObject readUserInformation(OIBVSendMail objOIBVSendMail)throws Exception
    {
    	LOGGER.debug(OILabelConstants.BEGIN_METHOD_BO + "readUserInformation");
    	
    	/** Get theb Hidden Action form the BV Object **/
    	String strModule = OIFormUtilities.chkIsNull(objOIBVSendMail.getSurveyOrCons());
    	String strHidAct = OIFormUtilities.chkIsNull(objOIBVSendMail.getHiddenAction());
    	
    	/** Check for Consultation Paper  **/
    	if(strModule.trim().equalsIgnoreCase(OILabelConstants.CONSULT_PAPER))
    	{ 	
    	    responseObject = getConPaper(objOIBVSendMail);
    		/** Check for Survey **/
     	}
    	else if(strModule.trim().equalsIgnoreCase(OILabelConstants.SURVEY))
    	{ 
    	    responseObject = getSurvey(objOIBVSendMail);
    	}
    	if(strHidAct.equals(OILabelConstants.PMMAIL)||strHidAct.equals(OILabelConstants.ADMINMAIL))
    	{
    		//System.out.println("OIBOSendMail-readUserInformation pmmail 1");
    	    responseObject = getMailIds(objOIBVSendMail);
    	}
    	    	    	
    	LOGGER.debug(OILabelConstants.END_METHOD_BO + "readUserInformation");
    	   	
		return responseObject;
    }
        
    
    /**
     * This method manipulates on Consualtion Paper to Populate, Remove , Sort operations
     * @param objOIBVSendMail
     * @return
     */
    
    private OIResponseObject getConPaper(OIBVSendMail objOIBVSendMail)throws Exception
    {
    	/** Get the Hidden Action **/
    	String strHidAct = OIFormUtilities.chkIsNull(objOIBVSendMail.getHiddenAction());
    	OIDAOSendMail objDAOSendMail = new OIDAOSendMail();
    	ArrayList arOIBVSendMail = null;
    	boolean bFlag= true;
    	try
    	{
    	    /** Create the Connection Object **/
		    getConnection();
		    
			/** Check for the Action -  Populate, Remove , Sort**/
		    if(strHidAct.equals(OILabelConstants.POPULATE))
		    {
		        /** Get The Subject and Message **/
		    	objDAOSendMail.readConsPapMessage(connection,objOIBVSendMail);
				arOIBVSendMail = objDAOSendMail.readConsPapUsers(connection,objOIBVSendMail);
		    }
		    else if(strHidAct.equals(OILabelConstants.REMOVE))
		    {
		        connection.setAutoCommit(false);	
		    	objDAOSendMail.deleteUsers(connection,objOIBVSendMail ,OISendMailSqls.CONS_PAPER_DELETE);
		    	connection.commit();
		    	arOIBVSendMail = objDAOSendMail.readConsPapUsers(connection,objOIBVSendMail);
		    }
		    else if(strHidAct.equals(OILabelConstants.SORT))
		    {	
		        arOIBVSendMail = objDAOSendMail.sortConsPapUsers(connection,objOIBVSendMail);
		    }
		    else if(strHidAct.equals(OILabelConstants.SENDMAIL))
		    {
		        sendMail(objOIBVSendMail,connection);
	    		arOIBVSendMail =objDAOSendMail.readUsrEmail(connection,objOIBVSendMail,OISendMailSqls.EMAIL_CP);
	    		bFlag = false;
		    }
		    else if (strHidAct.equals(OILabelConstants.ADDUSERS))
		    {
		        arOIBVSendMail = objDAOSendMail.readConsPapUsers(connection,objOIBVSendMail);
		    }
				    	 
		    if (strHidAct.equals(OILabelConstants.PAPERSURVEY))
		    {
		        arOIBVSendMail = objDAOSendMail.getPapersSurveys(connection,objOIBVSendMail,OISendMailSqls.QRY_PAPERS);
		    }
		    else if(bFlag)
		    {
		        /** Get the List of All Email Ids **/
		     	objDAOSendMail.readEmail(connection,objOIBVSendMail,OISendMailSqls.EMAIL_CP);
		    }		

		    if(arOIBVSendMail!=null)
		    {
		        responseObject.addResponseObject(OILabelConstants.OBJARBV,arOIBVSendMail);
		    }	
    	}
    	catch (Exception ex)
    	{
    	    LOGGER.error(OILabelConstants.EXCEPTION_IN_BO,ex);
    		/*	try{
    				if(strHidAct.equals(OILabelConstants.REMOVE)){
    					//connection.rollback();
    				}
    			}catch(SQLException sqle){
    				LOGGER.error(OILabelConstants.EXCEPTION_IN_BO,sqle);
    			}*/
    		responseObject.addResponseObject(OILabelConstants.ERROR,ex.getMessage());
    		throw ex;
    	}
    	finally
    	{
    		freeConnection();
    	}
		
    	return responseObject;
    }		
    
    /**
     * This method manipulates on Survery to Populate, Remove , Sort
     * @param objOIBVSendMail
     * @return
     */
    
    private OIResponseObject getSurvey(OIBVSendMail objOIBVSendMail) throws Exception
    {
    	/** Get the Hidden Action **/
    	String strHidAct = OIFormUtilities.chkIsNull(objOIBVSendMail.getHiddenAction());
    	OIDAOSendMail objDAOSendMail = new OIDAOSendMail();
    	ArrayList arOIBVSendMail = null;
    	boolean bFlag = true;
    	try
    	{
    	    /** Create the Connection Object **/
		    getConnection();
			      
		    /** Check for the Action -  Populate, Remove , Sort**/
		    if(strHidAct.equals(OILabelConstants.POPULATE))
		    {
		        /** Get the Subject & Message **/ 
				objDAOSendMail.readSurveyMessage(connection,objOIBVSendMail);
		    	arOIBVSendMail = objDAOSendMail.readSurveyUsers(connection,objOIBVSendMail);
		    }
		    else if(strHidAct.equals(OILabelConstants.REMOVE))
		    {
		        connection.setAutoCommit(false);	
		    	objDAOSendMail.deleteUsers(connection,objOIBVSendMail ,OISendMailSqls.SURVEY_DELETE);
		    	connection.commit();
		    	arOIBVSendMail = objDAOSendMail.readSurveyUsers(connection,objOIBVSendMail);
		    }
		    else if(strHidAct.equals(OILabelConstants.SORT))
		    {	
		        arOIBVSendMail = objDAOSendMail.sortSurveyUsers(connection,objOIBVSendMail);
		    }
		    else if(strHidAct.equals(OILabelConstants.SENDMAIL))
		    {
		        sendMail(objOIBVSendMail,connection);
		     	arOIBVSendMail =objDAOSendMail.readUsrEmail(connection,objOIBVSendMail,OISendMailSqls.EMAIL_SU);
		     	bFlag = false;
		    }
		    else if (strHidAct.equals(OILabelConstants.ADDUSERS))
		    {
		        arOIBVSendMail = objDAOSendMail.readSurveyUsers(connection,objOIBVSendMail);
		    }
		    	 
		    if (strHidAct.equals(OILabelConstants.PAPERSURVEY))
		    {
		        arOIBVSendMail = objDAOSendMail.getPapersSurveys(connection,objOIBVSendMail,OISendMailSqls.QRY_SURVEY);
		    }
		    else if(bFlag)
		    {
		        /** Get the List of All Email Ids **/
				objDAOSendMail.readEmail(connection,objOIBVSendMail,OISendMailSqls.EMAIL_SU);
		    }
		 		 
		    if(arOIBVSendMail!=null)
		    {
		        responseObject.addResponseObject(OILabelConstants.OBJARBV,arOIBVSendMail);
		    }
    	}
    	catch (Exception ex)
    	{
    		LOGGER.error(OILabelConstants.EXCEPTION_IN_BO,ex);
    		responseObject.addResponseObject(OILabelConstants.ERROR,ex.getMessage());
    		throw ex;
    	}
    	finally
    	{
    		freeConnection();	
    	}
		   	 
    	return responseObject;
    } 
    
    
    /**
     * This method is used to Send the Mail to List of users.
     * @param objOIBVSendMail
     */
    private void sendMail(OIBVSendMail objOIBVSendMail,Connection objCon) throws Exception
    {
    	OICommonMailBean objCmnMailBean = new OICommonMailBean();
    	MailUtility objMailUtil = new MailUtility(); 
    	OIDAOSendMail objDAOSendMail = new OIDAOSendMail();
    	String strModule = OIFormUtilities.chkIsNull(objOIBVSendMail.getSurveyOrCons());
    	
    	HashMap hmValidIds = null;
    	
    	try
    	{    		
    		String strEmail = objOIBVSendMail.getEmail();
    		
    		/** Calling chkDomains method  Check  TO  Mail Ids**/ 

    		hmValidIds =  chkDomains(null,strEmail.trim().substring(1,strEmail.length() - 1));

		    if(hmValidIds == null)
		    {
		        responseObject.addResponseObject(OILabelConstants.MAILERROR,OILabelConstants.MAILERROR);
		    }
		    else
		    {
		        if(hmValidIds.containsKey(OILabelConstants.VALIDIDS))
		        {
		        	LOGGER.info("sendMail - valid IDs");
		        	
		    		objCon.setAutoCommit(false);
		    		
		    		
		    		objCmnMailBean.setSubject(objOIBVSendMail.getSubject());
		    		objCmnMailBean.setMessage(objOIBVSendMail.getMessage());
		    		
		    		objCmnMailBean.setBcc((String)hmValidIds.get(OILabelConstants.VALIDIDS));
		    		//objCmnMailBean.setBcc(strEmail.trim().substring(1,strEmail.length() - 1));
		    		 //strEmail = objPostingVo.getStrNickName().replaceAll(" ","_");
		    		if (objOIBVSendMail.getFrom()!=null)
		    			
		    		{
		    		    objCmnMailBean.setFrom(objOIBVSendMail.getFrom().replaceAll(" ","_"));
		    		}
		    		else
		    		{
		    		    objCmnMailBean.setFrom("");
		    		}
		    		objCmnMailBean.setTo(objOIBVSendMail.getFrom());
		    		objCmnMailBean.setSmtpHost(OIDBRegistry.getValues("smtp"));
		    		objCmnMailBean.setType(OIDBRegistry.getValues("type"));
		    		
		    		LOGGER.info("sendMail - sending");
		    		
		    		objMailUtil.sendMessage(objCmnMailBean);
		    		
		    		LOGGER.info("sendMail - finished sending, updating mail/reminder status");
		    		
		    		if(strModule.trim().equals(OILabelConstants.CONSULT_PAPER))
		    		{ 	
		    		    /** Check for Consultation Paper  **/
		    			objDAOSendMail.updateRem(objCon,objOIBVSendMail,OISendMailSqls.QRYREMAINDER_CP,OISendMailSqls.QRY_UPDATE_SYSDATE_CP);
					}
		    		else if(strModule.trim().equals(OILabelConstants.SURVEY))
					{ 	
					    /** Check for Survey **/
			        	objDAOSendMail.updateRem(objCon,objOIBVSendMail,OISendMailSqls.QRYREMAINDER_SU,OISendMailSqls.QRY_UPDATE_SYSDATE_SU);
						
					}    		
		    		objCon.commit();
    		
		        }
		        
		    }
	        
		    
    	}
    	catch (Exception ex)
    	{    		
    		LOGGER.error(OILabelConstants.EXCEPTION_IN_BO,ex);
    		responseObject.addResponseObject(OILabelConstants.MAILERROR,ex.getMessage());
    		throw ex;
    	}
    	finally
    	{
    		objCon.setAutoCommit(true);
    		connection = objCon;
    		objCmnMailBean = null;	
    	}
     } 
    
    /**
    * This method manipulates on Consualtion Paper to Populate, Remove , Sort operations
    * @param objOIBVSendMail
    * @return
    */
    
   private  OIResponseObject getMailIds(OIBVSendMail objOIBVSendMail)throws Exception
   {
	   	/** Get the Hidden Action **/
	   	String strHidAct = objOIBVSendMail.getHiddenAction();
	   	OIDAOSendMail objDAOSendMail = new OIDAOSendMail();
	   	ArrayList arOIBVSendMail = new ArrayList();
	   	ArrayList alTemp = null;
	   	ArrayList alMod = null;
		OICommonMailBean objCmnMailBean = new OICommonMailBean();
		MailUtility objMailUtil = new MailUtility(); 
		StringBuffer sbMail = new StringBuffer(0);
		String strEmail = null;
		HashMap hmValidIds = null;
		
	   	try
	   	{
	   	    /** Create the Connection Object **/
		    getConnection();
		    //System.out.println("OIBOSendMail-readUserInformation pmmail 2");
		    /** Check for the Action -  Pm Mail, Admin Mail **/
		    if(strHidAct.equals(OILabelConstants.PMMAIL))
		    {
		    //	System.out.println("OIBOSendMail-readUserInformation pmmail 3");
		        arOIBVSendMail = objDAOSendMail.getAdminIds(connection,objOIBVSendMail,OISendMailSqls.QRY_PM_MAIL,strHidAct );
		    }
		    else if(strHidAct.equals(OILabelConstants.ADMINMAIL))
		    {
		    //	System.out.println("OIBOSendMail-readUserInformation pmmail 4");
		        arOIBVSendMail = objDAOSendMail.getAdminIds(connection,objOIBVSendMail,OISendMailSqls.QRY_ADMIN_MAIL,strHidAct);
	   			
		        /** Getting the Respective Moderator Mail ids of Topic id **/
		        //Commented by Anbu bcos mail should not go to moderators
	   			/*
		        alMod = objDAOSendMail.getAdminIds(connection,objOIBVSendMail,OISendMailSqls.QRY_ADMIN_MOD,OILabelConstants.PMMAIL);
	   			
		        if(alMod!=null && arOIBVSendMail!= null)
	   			{
	   			    String strTemp = alMod.toString();
	   				strTemp = strTemp.trim().substring(1,strTemp.length() - 1);
	   				arOIBVSendMail.add(strTemp);
	   			}
	   			*/

	   		}
	   		
		    objDAOSendMail.getPostedOn(connection ,objOIBVSendMail);
		    LOGGER.info("getMailIds--" + objOIBVSendMail.getFrom());
	   		//alTemp = objDAOSendMail.getAdminIds(connection,objOIBVSendMail,OISendMailSqls.QRY_FRM_MAILID,"");
		    alTemp = new ArrayList();
		    if (objOIBVSendMail.getFrom()!=null)
		    {
		        alTemp.add(objOIBVSendMail.getFrom().replaceAll(" ","_"));
		    }
		    else
		    {
		        alTemp.add("");
		    }
		    LOGGER.info("getMailIds--" + objOIBVSendMail.getFrom());

		    
	   		if(arOIBVSendMail!=null && arOIBVSendMail.size()>0)
	   		{
	   		//	System.out.println("OIBOSendMail-getMailIds size="+arOIBVSendMail.size());
	   		    strEmail = arOIBVSendMail.toString();
	   		    
	   		    /** Calling chkDomains method  Check  TO  Mail Ids**/ 
			    hmValidIds =  chkDomains(connection,strEmail.trim().substring(1,strEmail.length() - 1));
			    if(hmValidIds == null)
			    {
			        responseObject.addResponseObject(OILabelConstants.MAILERROR,OILabelConstants.MAILERROR);
			    }
			    else
			    {
			        if(hmValidIds.containsKey(OILabelConstants.VALIDIDS))
			        {
			            objCmnMailBean.setBcc(hmValidIds.get(OILabelConstants.VALIDIDS).toString());	  	  	
					    objCmnMailBean.setType(OIDBRegistry.getValues("type"));	    // Type of format
			 		    objCmnMailBean.setSmtpHost(OIDBRegistry.getValues("smtp")); // Mail Server
					    objCmnMailBean.setSubject(objOIBVSendMail.getSubject());
					    strEmail = alTemp.toString();
					    //objCmnMailBean.setFrom(OIDBRegistry.getValues("sendmailtoaddress"));
					   // objCmnMailBean.setPrincipal(OIDBRegistry.getValues("sendmailMyForum")) ;
					    objCmnMailBean.setFrom(OIDBRegistry.getValues("sendmailMyForum"));
					  //  System.out.println("OIBOSendMail-getMailIds objCmnMailBean="+objCmnMailBean.getTo());
					 //   System.out.println("OIBOSendMail-getMailIds objCmnMailBean="+objCmnMailBean.getBcc());
					 //   System.out.println("OIBOSendMail-getMailIds objCmnMailBean="+objCmnMailBean.getCc());
					    
					    sbMail.append(objOIBVSendMail.getMessage())
							.append("\n\n")
							.append(OIDBRegistry.getValues("AlertAFriend"))
							.append("?module=F&id=")
							.append(objOIBVSendMail.getId())
							.append("\n\n");
					    if (objOIBVSendMail.getPostedOn()!=null)
					    {
					        sbMail.append(OIDBRegistry.getValues("postedon"))
							.append(objOIBVSendMail.getPostedOn());
					    }
						/*.append(OIDBRegistry.getValues("mailfwdpath"))
						.append(objOIBVSendMail.getURL())
						.append(OIForumConstants.THREAD_MAINTAIN_DO)
						.append(OIForumConstants.THREADFWD)
						.append(objOIBVSendMail.getId())*/
					    sbMail.append("\n\n")
					    .append(getFoot(connection,objDAOSendMail));
					    objCmnMailBean.setMessage(sbMail.toString());
					    LOGGER.info(objCmnMailBean.getMessage());
					  //  System.out.println("OIBOSendMail-getMailIds objCmnMailBean="+objCmnMailBean.getTo());
					  //  System.out.println("OIBOSendMail-getMailIds objCmnMailBean="+objCmnMailBean.getBcc());
					  //  System.out.println("OIBOSendMail-getMailIds objCmnMailBean="+objCmnMailBean.getCc());
					  //  System.out.println("Connection - Footer Mesg : "+ getFoot(connection,objDAOSendMail));
					    objMailUtil.sendMessage(objCmnMailBean);
					    responseObject.addResponseObject(OILabelConstants.VALIDIDS,hmValidIds.get(OILabelConstants.VALIDIDS));
			        }
			        if(hmValidIds.containsKey(OILabelConstants.INVALIDIDS))
			        {
			            responseObject.addResponseObject(OILabelConstants.INVALIDIDS,hmValidIds.get(OILabelConstants.INVALIDIDS));
			        }
			        if(hmValidIds.containsKey(OILabelConstants.NODOMAINS))
			        {
			            responseObject.addResponseObject(OILabelConstants.NODOMAINS,OILabelConstants.NODOMAINS);
			        }
			    }
	   		}		    		
	   	}catch (Exception ex)
	   	{
	   		//System.out.println("OIBOSendMail-getMailIds ex="+ex.getMessage());
	   		LOGGER.error(OILabelConstants.EXCEPTION_IN_BO,ex);
	   		responseObject.addResponseObject(OILabelConstants.MAILERROR,ex.getMessage());
	   		throw ex;
	   	}
	   	finally
	   	{
	   		freeConnection();
	   		strHidAct = null;
		   	objDAOSendMail = null;
		   	arOIBVSendMail = null;
		   	alTemp = null;
			objCmnMailBean = null;
			objMailUtil = null; 
			sbMail = null;
			strEmail = null;
	   	}
	   	return responseObject;
	}		
    
   /**
    * This is the helper method to check the domains availability. 
    * @param objCon
    * @param strIds
    * @return
    * @throws Exception
    */
    
   public HashMap chkDomains(Connection objCon,String strIds)throws Exception 
   {
   		LOGGER.info("strIds--" + strIds);
		HashMap hmDomains  = new HashMap();
	 	OIDAOSendMail objDAOSendMail = new OIDAOSendMail();
	 	ArrayList alDomains = null; 
	 	String strDomains = null;
	 	String strTempId = null;
	 	String strValid = OILabelConstants.DBQUOTES;
	 	String strInValid = OILabelConstants.DBQUOTES;
	 	boolean connectionOpenedLocally = false;
	 	//ArrayList alInvalids = new ArrayList();
		try
		{
			/** Create the Connection Object **/
			if(objCon==null)
			{
				getConnection();
				objCon = connection;
				connectionOpenedLocally = true;
			}	
			alDomains = objDAOSendMail.getDomainNames(objCon);
			
			if(alDomains==null)
			{
				hmDomains.put(OILabelConstants.NODOMAINS,alDomains);	
			}
			//if(strDomains != null &&  strIds!=null && strIds.trim().length()> 0)
			if(alDomains != null &&  strIds!=null && strIds.trim().length()> 0)
			{
				strTempId = chkMailIds(strIds);
				
				if(strIds.indexOf(OILabelConstants.COMMA) < 0  && strTempId != null)
				{
					LOGGER.info("strDomains===="+strDomains);
					LOGGER.info("strTempId===="+strTempId);
					boolean flag=false;
					for(int i =0;i<alDomains.size();i++)
					{
						if(alDomains.get(i)!=null)
						{
							strDomains = (String) alDomains.get(i);
						}
						if (strDomains.toUpperCase().equals(strTempId.toUpperCase()))
					    {
					        flag=true;
					        break;
						}
					}
					if (flag)
					{
				        hmDomains.put(OILabelConstants.VALIDIDS,strIds);						
					}
					else
					{
						hmDomains.put(OILabelConstants.INVALIDIDS,strIds);
					}
				}
				else
				{
					StringTokenizer stMailIds = new StringTokenizer(strIds,OILabelConstants.COMMA);
					while(stMailIds.hasMoreTokens())
					{
						strTempId =	(String) stMailIds.nextToken();
						String strAtValue = chkMailIds(strTempId);
						boolean flag=false;
						for(int i =0;i<alDomains.size();i++)
						{
							if(alDomains.get(i)!=null)
							{
								strDomains = (String) alDomains.get(i);
							}
							if( strAtValue != null)
							{
								if (strDomains.toUpperCase().equals(strAtValue.toUpperCase()))
							    {
									flag=true;
									break;
								}
							}
						}
						if (flag)
						{
					        strValid = strValid+strTempId+OILabelConstants.COMMA;
						}
						else
						{
					        strInValid = strInValid+strTempId+OILabelConstants.COMMA;
						}
					}
					if(strValid.length()>0)
					{
						hmDomains.put(OILabelConstants.VALIDIDS,strValid.substring(0,strValid.length()-1));
					}
					if(strInValid.length()>0)
					{
						hmDomains.put(OILabelConstants.INVALIDIDS,strInValid.substring(0,strInValid.length()-1));
					}
				}
			}	
		}
		catch (Exception ex)
		{
			LOGGER.error(OILabelConstants.EXCEPTION_IN_BO,ex);
			responseObject.addResponseObject(OILabelConstants.ERROR,ex.getMessage());
			throw ex;
		}
		finally
		{
			if(connectionOpenedLocally)
			{
				freeConnection();
			}	
		}
		if(hmDomains.size()==0)
		{
			hmDomains = null;
		}
		LOGGER.debug(OILabelConstants.END_METHOD_BO + "chkDomains");
		return  hmDomains;
   } 
   
   /**
    * This helper method checks for @ symbol in the email id
    * @param strMail
    * @return
    */ 
    
    private String chkMailIds(String strMail)
    {
    	String strTemp = null;
    	if(strMail!=null && strMail.length()>0)
    	{
	    	int nIndex = strMail.indexOf(OILabelConstants.AT);
	    	if(nIndex > 0)
	    	{
	    		strTemp = strMail.substring(nIndex+1,strMail.length()).toUpperCase();
	    	}
    	}   	
    	return strTemp;
    }
    
    // This method calls the DAO to retrieve PM Footer Mesg
    // Added by Rakesh.
    private String getFoot(Connection connection, OIDAOSendMail objDAOSendMail )throws Exception{
    	String strFoot = "";
    	try
		{
			//System.out.println("Connection - objCon : "+connection);
			strFoot = (String)objDAOSendMail.getFootMsg(connection);
			//System.out.println("Connection - Footer Mesg : "+ strFoot);
			return strFoot;
		}
		catch (Exception ex)
		{
			//logger.error(OIBatchConstants.EXCEPTION_IN_BO + ex.getMessage());
			throw ex;
		}
    }

    public String readNickname(String aName){
    	String nickname =null;
    	try{
			getConnection();
			//System.out.println("OIBOSendMail-readNickname" + aName );
			nickname = new OIDAOSendMail().getNickname((String) aName, connection);
			//OIBASendMail aOIBASendMail = new OIDAOSendMail().getNickname(aName, connection);
			//responseObject.addResponseObject(OILabelConstants.K_aOIBASendMail, aOIBASendMail);
    	}catch(Exception e){
    		error = e.getMessage();
			//logger.error(e);
		}finally{
			freeConnection();
		}
		addToResponseObject();
		return nickname;
    }
}