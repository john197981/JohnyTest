package com.oifm.useradmin.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
import org.apache.log4j.Logger;
import com.oifm.base.OIBaseBo;
import com.oifm.common.OIDAOSendMail;
import com.oifm.common.OILabelConstants;
import com.oifm.common.OIPageInfoBean;
import com.oifm.common.OIResponseObject;
import com.oifm.useradmin.OISearchConstants;
import com.oifm.utility.OIDBRegistry;
import com.oifm.utility.OIFormUtilities;
import java.sql.Connection;

public class OIBOAdminUserProfile  extends OIBaseBo 
{
	/**	Create instance for logger and objects  used**/
	transient private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	public OIBOAdminUserProfile()
	{
		logger.debug(OILabelConstants.BEGIN_METHOD_BO + this.getClass().getName());
	}
	
   /**
    * This is the main method to process the profile, it will delagate to populate,search methods.
    * @param objBVUsrPrfSrh
    * @return
    */
    public OIResponseObject processProfile(OIBVAdminUserProfile objBV) throws Exception
    {
    	logger.info(OILabelConstants.BEGIN_METHOD_BO + "processProfile");
    	
    	/** Get theb Hidden Action form the BV Object **/
    	String strHidAct = OIFormUtilities.chkIsNull(objBV.getHiddenAction());
    	boolean bFlag = false;
    	
   		if(strHidAct.trim().length()==0)
   		{
    		responseObject = getLists(objBV);
    	}
   		else if(strHidAct.equals(OIAdminConstants.SEARCH))
    	{
   		//	System.out.println("OIActionAdminUserProfile-doIt objBV queryBO="+objBV.getQuery());
    		responseObject = searchProfile(objBV);
    		responseObject = getLists(objBV);
    	}
   		else if(strHidAct.equals(OIAdminConstants.VIEW))
   		{
    		responseObject = getRoleList(objBV);
    		responseObject = viewProfile(objBV);
    	}
   		else if(strHidAct.equals(OIAdminConstants.SAVE))
    	{
    		bFlag = updateProfile(objBV); 
    		responseObject = viewProfile(objBV);
    		responseObject = getRoleList(objBV);
    	}
   		else if(strHidAct.equals(OIAdminConstants.USER))
   		{
   			responseObject = webUserProfile(objBV);
   			responseObject = getBookMarks(objBV);
   		}
   		else if(strHidAct.equals(OIAdminConstants.USERUPDATE))
   		{
   			bFlag = updateUser(objBV); 
			if(bFlag)
			{
				responseObject = webUserProfile(objBV);
			}	
			responseObject = getBookMarks(objBV);
		}
		else if(strHidAct.equals(OIAdminConstants.DISABLE))
   		{
   			bFlag = enableDisableUser(objBV); 
			if(bFlag)
			{
				responseObject = webUserProfile(objBV);
			}	
			responseObject = getBookMarks(objBV);
		}
   		else if(strHidAct.equals(OIAdminConstants.DELBOOKMARK) ||strHidAct.equals(OIAdminConstants.DELSTICKY))
   		{
			bFlag = delBookMarks(objBV);
			responseObject = webUserProfile(objBV);
			responseObject = getBookMarks(objBV);
		}
    		
    	logger.debug(OILabelConstants.END_METHOD_BO + "processProfile");
    	   	
		return responseObject;
    }
    
    /**
     * This method populates the dropdowns for the user profile
     * @param objBV
     * @return
     */

    private OIResponseObject getLists(OIBVAdminUserProfile objBV)throws Exception
    {
        logger.debug(OILabelConstants.BEGIN_METHOD_BO + "getLists");
        	
        ArrayList alCombo = new ArrayList();
        ArrayList alList = new ArrayList();
        OIDAOAdminUserProfile objDAO = new OIDAOAdminUserProfile(); 
        try
        {
            /**Create the Connection Object **/
    	    getConnection();
    	    	  
    	    /** Get the List of Role**/ 
    	    //alCombo = objDAO.readList(connection,OIAdminSqls.QRY_ROLE);
    	    /** The RoleId was fetched from database , now it was fetched from Properties file **/ 
    	    if("ADMIN".equals(objBV.getLoginRole().trim().toUpperCase()))
			{
    			alCombo = getRoleIds(connection,objBV);
			}
			else
			{
				alCombo = getRoleIds(objBV);
			}
    	    addList(alCombo ,OIAdminConstants.ROLE);
    	    	      	    	     	    	  
    	    /** Get the List of School**/ 
    	    alCombo = objDAO.readList(connection,OIAdminSqls.QRY_SCHOOL);
    	    addList(alCombo ,OIAdminConstants.SCHOOL);
    	    	  
    	    /** Get the List of Cluster**/
    	    alCombo = objDAO.readList(connection,OIAdminSqls.QRY_CLUSTER);
    	    addList(alCombo ,OIAdminConstants.CLUSTER);
    	    	  	    	  
    	    /** Get the List of School Type **/
    	    alCombo = objDAO.readList(connection,OIAdminSqls.QRY_SCHTYP);
    	    addList(alCombo ,OIAdminConstants.SCH_TYP);
    	    	  
    	    /** Get the List of Designation **/
    	    alCombo = objDAO.readList(connection,OIAdminSqls.QRY_DESIGNATION);
    	    addList(alCombo ,OIAdminConstants.DESIGNATION);
    	    	  
    	    /** Get the List of Division **/
    	    alCombo = objDAO.readList(connection,OIAdminSqls.QRY_DIVISION);
    	    addList(alCombo ,OIAdminConstants.DIVISION);
    	    	  
    	    /** Get the List of ZONE **/
    	    alCombo = objDAO.readList(connection,OIAdminSqls.QRY_BRANCHZONE);
    	    addList(alCombo ,OIAdminConstants.ZONE);
    	    	  
    	    /** Get the List of DIVISONAL STATUS **/
    	    alCombo = objDAO.readList(connection,OIAdminSqls.QRY_DIV_STATUS);
    	    addList(alCombo ,OIAdminConstants.DIV_STATUS);
    	    	  
    	    /** Get the List of Grade **/
    	    // alCombo = objDAO.readList(connection,OIAdminSqls.QRY_GRADE);
    	    // addList(alCombo ,OIAdminConstants.GRADE);
        }
        catch (Exception ex)
        {
            logger.error(OILabelConstants.EXCEPTION_IN_BO,ex);
    		responseObject.addResponseObject(OILabelConstants.ERROR,ex.getMessage());
    		throw ex;
    	}
        finally
        {
    		freeConnection();
    		alCombo = null;		
    	}
    	logger.debug(OILabelConstants.END_METHOD_BO + "getLists");		 
    	return responseObject;
    }
   
    /**
     * This bo method fetchs the user profile information
     * @param objBVUsrPrfSrh
     * @return
     */
    private OIResponseObject searchProfile(OIBVAdminUserProfile objBVUsrPrfSrh)throws Exception
    {
    	logger.debug(OILabelConstants.BEGIN_METHOD_BO + "searchProfile");
    	ArrayList alSrhUsrs = new ArrayList();
    	OIDAOAdminUserProfile objDAOUsrPrfSrh = new OIDAOAdminUserProfile();
    	OIPageInfoBean aOIPageInfoBean = null;
    	boolean bFlag = false;
    	try
    	{
    	    /**Create the Connection Object **/ 
	    	getConnection();	
    		/**DAO call to search the List of Users **/
	    	//totalRecord 
	    	  	
	    	aOIPageInfoBean = new OIPageInfoBean(Integer.parseInt(objBVUsrPrfSrh.getRowId()),OIDAOSendMail.recPerPage(connection));
	    	bFlag = objDAOUsrPrfSrh.srchRowCntQry(connection,objBVUsrPrfSrh);
	    	if(bFlag)
	    	{
	    	    aOIPageInfoBean.setTotalRec(objBVUsrPrfSrh.getRowCount());
	            alSrhUsrs = objDAOUsrPrfSrh.searchUserProfile(connection,objBVUsrPrfSrh,aOIPageInfoBean.getStartRec(),aOIPageInfoBean.getEndRec());
	    	}	
	        responseObject.addResponseObject("aOIPageInfoBean",aOIPageInfoBean);
	    	if(alSrhUsrs!=null)
	    	{
	    	    responseObject.addResponseObject(OISearchConstants.SEARCH,alSrhUsrs);
	    	}
	    }
    	catch (Exception ex)
    	{
    	    logger.error(OILabelConstants.EXCEPTION_IN_BO,ex);
			responseObject.addResponseObject(OILabelConstants.ERROR,ex.getMessage());
			throw ex;
		}
    	finally
    	{
			freeConnection();
			alSrhUsrs = null;		
		}
		logger.debug(OILabelConstants.END_METHOD_BO + "searchProfile");		 
		return responseObject;
    }
    
    /**
     * This method fetches the user profile information for particular userid.
     * @param objBVUsrPrfSrh
     * @return
     */
    private OIResponseObject viewProfile(OIBVAdminUserProfile objBVUsrPrfSrh)throws Exception
    {
    	logger.debug(OILabelConstants.BEGIN_METHOD_BO + "viewProfile");
    	
    	ArrayList alView = new ArrayList();
    	String strHidAct = OIFormUtilities.chkIsNull(objBVUsrPrfSrh.getHiddenAction());
    	OIDAOAdminUserProfile objDAO = new OIDAOAdminUserProfile(); 
    	try
    	{
    	    /**Create the Connection Object **/
	    	getConnection();

	    	/** Get the List of Role**/ 
	    	alView = objDAO.viewProfile(connection,objBVUsrPrfSrh);
	    	addList(alView ,OIAdminConstants.PROFILE);
	    	  
	    	if(!strHidAct.equals(OIAdminConstants.USER) && !strHidAct.equals(OIAdminConstants.USERUPDATE))
	    	{
	    	    objDAO.getAdminUser(connection,objBVUsrPrfSrh);
		    	if(alView!=null)
		    	{
		    	    OIBVAdminUserProfile objTemp = (OIBVAdminUserProfile)alView.get(0);
		    	  	objTemp.setAdminId(objBVUsrPrfSrh.getAdminId());
		    	}
		    	 
		     	HashMap hmLevel  =  objDAO.viewTech(connection,objBVUsrPrfSrh);
		     	if(hmLevel != null)
		     	{
		     	    addLevel(hmLevel ,OIAdminConstants.SUB_LEVEL);
		    	  	addLevel(hmLevel ,OIAdminConstants.TEACH_LEVEL);
		    	  	addLevel(hmLevel ,OIAdminConstants.CCA);
		     	}
	    	}
    	}
    	catch (Exception ex)
    	{
    	    logger.error(OILabelConstants.EXCEPTION_IN_BO,ex);
    		responseObject.addResponseObject(OILabelConstants.ERROR,ex.getMessage());
    		throw ex;
    	}
    	finally
    	{
    		freeConnection();
    		alView = null;		
    	}
    	logger.debug(OILabelConstants.END_METHOD_BO + "viewProfile");		 
    	return responseObject;
    }
    
    /**
     * This method returns List of roles
     * @param objBVUsrPrfSrh
     * @return
     */
    private OIResponseObject getRoleList(OIBVAdminUserProfile objBVUsrPrfSrh)throws Exception
    {
    	logger.debug(OILabelConstants.BEGIN_METHOD_BO + "getRoleList");
    	
    	ArrayList alCombo = new ArrayList();
    	OIDAOAdminUserProfile objDAO = new OIDAOAdminUserProfile(); 
    	try
    	{
    	    /**Create the Connection Object **/
	    	getConnection();

	    	/** Get the List of Role**/ 
	    	if("ADMIN".equals(objBVUsrPrfSrh.getLoginRole().trim().toUpperCase()))
			{
    			alCombo = getRoleIds(connection,objBVUsrPrfSrh);
			}
			else
			{
				alCombo = getRoleIds(objBVUsrPrfSrh);
			}
	    	addList(alCombo ,OIAdminConstants.ROLE);
    	}
    	catch (Exception ex)
    	{
    	    logger.error(OILabelConstants.EXCEPTION_IN_BO,ex);
    		responseObject.addResponseObject(OILabelConstants.ERROR,ex.getMessage());
    		throw ex;
    	}
    	finally
    	{
    	    freeConnection();
    		alCombo = null;		
    	}
    	logger.debug(OILabelConstants.END_METHOD_BO + "getRoleList");		 
    	return responseObject;
    }
    
    /**
	  * This method is used to update the profile details
	  * @param objBVPoll
	  * @return
	  */
	 private  boolean updateProfile(OIBVAdminUserProfile  objBVUsrPrfSrh )throws Exception
	 {
	 	
	     logger.info(OILabelConstants.BEGIN_METHOD_BO + "updateProfile");
	     OIDAOAdminUserProfile objDAO = new OIDAOAdminUserProfile(); 
	     boolean bFlag =false;   	
	     try
	     {
	         /** Create the Connection Object **/
	         getConnection();
	         connection.setAutoCommit(false);
	         bFlag = objDAO.updateProfile(connection, objBVUsrPrfSrh);
	         connection.commit();
	     }
	     catch (Exception ex)
	     {
	         logger.error(OILabelConstants.EXCEPTION_IN_BO,ex);
	         responseObject.addResponseObject(OILabelConstants.ERROR,ex.getMessage());
	         throw ex;
	     }
	     finally
	     {
	         freeConnection();
	     }
	     logger.info(OILabelConstants.END_METHOD_BO + "updateProfile");	
	     return bFlag;
	 } 
	 
	 
	 /**
	  * This helper method loads the List of bookmarks
	  * @param objBV
	  * @return
	  * @throws Exception
	  */
	 private OIResponseObject getBookMarks(OIBVAdminUserProfile objBV)throws Exception
	 {
	     logger.debug(OILabelConstants.BEGIN_METHOD_BO + "getBookMarks");

	     ArrayList alCombo = new ArrayList();
	     OIDAOAdminUserProfile objDAO = new OIDAOAdminUserProfile(); 
	     try
	     {
	         /**Create the Connection Object **/
	         getConnection();
	         
	         /** Get the List of Role**/ 
	         alCombo = objDAO.getBookMarks(connection,objBV,OIAdminConstants.FLAG_B);
	         if(alCombo!=null)
	         {
	             addList(alCombo ,OIAdminConstants.BOOKMARKS);
	         }	
	         alCombo = objDAO.getBookMarks(connection,objBV,OIAdminConstants.FLAG_S);
	         if(alCombo!=null)
	         {
	             addList(alCombo ,OIAdminConstants.STICKY);
	         }
	     }
	     catch (Exception ex)
	     {
	         logger.error(OILabelConstants.EXCEPTION_IN_BO,ex);
	         responseObject.addResponseObject(OILabelConstants.ERROR,ex.getMessage());
	         throw ex;
	     }
	     finally
	     {
	         freeConnection();
	         alCombo = null;		
	     }
	     logger.debug(OILabelConstants.END_METHOD_BO + "getBookMarks");		 
	     return responseObject;
	 }
       
	 
	 /**
	  * This method update user profile details of Website
	  * @param objBV
	  * @return
	  * @throws Exception
	  */
	 
	 private boolean updateUser(OIBVAdminUserProfile objBV)throws Exception
	 {
	 	logger.debug(OILabelConstants.BEGIN_METHOD_BO + "updateUser");
	 	OIDAOAdminUserProfile objDAO = new OIDAOAdminUserProfile(); 
	 	boolean bFlag =false;   	
	   	try
	   	{
	   	    /** Create the Connection Object **/
		    getConnection();
		    bFlag = objDAO.selectNickName(connection, objBV);
		    if(bFlag)
		    {
		        responseObject.addResponseObject(OILabelConstants.DUPNICKNAME,OILabelConstants.DUPNICKNAME);
		    	bFlag = false;
		    }
		    else
		    {	
		        connection.setAutoCommit(false);
			    bFlag = objDAO.updateUser(connection, objBV);
				connection.commit();
		    }   
	   	}
	   	catch (Exception ex)
	   	{
	   	    logger.error(OILabelConstants.EXCEPTION_IN_BO,ex);
	   		responseObject.addResponseObject(OILabelConstants.ERROR,ex.getMessage());
	   		throw ex;
	   	}
	   	finally
	   	{
	   	    freeConnection();
	   	}
	   	logger.debug(OILabelConstants.END_METHOD_BO + "updateUser");	
		return bFlag;
	 }

	 /**
	  * This method enable or disable the user of Website
	  * @param objBV
	  * @return
	  * @throws Exception
	  */
	 
	 private boolean enableDisableUser(OIBVAdminUserProfile objBV)throws Exception
	 {
	 	logger.debug(OILabelConstants.BEGIN_METHOD_BO + "enableDisableUser");
	 	OIDAOAdminUserProfile objDAO = new OIDAOAdminUserProfile(); 
	 	boolean bFlag =false;   	
	   	try
	   	{
	   	    /** Create the Connection Object **/
		    getConnection();
			connection.setAutoCommit(false);
			bFlag = objDAO.enableDisableUser(connection, objBV);
			connection.commit();
		       
	   	}
	   	catch (Exception ex)
	   	{
	   	    logger.error(OILabelConstants.EXCEPTION_IN_BO,ex);
	   		responseObject.addResponseObject(OILabelConstants.ERROR,ex.getMessage());
	   		throw ex;
	   	}
	   	finally
	   	{
	   	    freeConnection();
	   	}
	   	logger.debug(OILabelConstants.END_METHOD_BO + "enableDisableUser");	
		return bFlag;
	 }
	 
	 
	 /**
	  * This method deletes the selected book marks
	  * @param objBV
	  * @return
	  * @throws Exception
	  */
	 private boolean delBookMarks(OIBVAdminUserProfile objBV) throws Exception
	 {
	     logger.debug(OILabelConstants.BEGIN_METHOD_BO + "delBookMarks");
	     OIDAOAdminUserProfile objDAO = new OIDAOAdminUserProfile(); 
	     boolean bFlag =false;   	
	     try
	     {
	         /** Create the Connection Object **/
	         getConnection();
	         connection.setAutoCommit(false);
	         bFlag = objDAO.delBookMarks(connection, objBV);
	         connection.commit();
	     }
	     catch (Exception ex)
	     {
	         logger.error(OILabelConstants.EXCEPTION_IN_BO,ex);
	         responseObject.addResponseObject(OILabelConstants.ERROR,ex.getMessage());
	         throw ex;
	     }
	     finally
	     {
	         freeConnection();
	     }
	     logger.debug(OILabelConstants.END_METHOD_BO + "delBookMarks");	
	     return bFlag;
	 }
	 
	 /**
	  * This method displayes the webUserProfile Information
	  * @param objBVUsrPrfSrh
	  * @return
	  */
	 private OIResponseObject webUserProfile(OIBVAdminUserProfile objBVUsrPrfSrh) throws Exception
	 {
	     logger.info(OILabelConstants.BEGIN_METHOD_BO + "webUserProfile");

	     ArrayList alView = new ArrayList();
	    OIDAOAdminUserProfile objDAO = new OIDAOAdminUserProfile(); 
	    logger.info("before try");
	    try
	    {
	        /**Create the Connection Object **/
		    getConnection();
logger.info("after getConnection");
		    /** Get the List of Role**/ 
		    alView = objDAO.viewUserProfile(connection,objBVUsrPrfSrh);
		    logger.info("after DAO");
		    addList(alView ,OIAdminConstants.PROFILE);
		    	  
	    }
	    catch (Exception ex)
	    {
	        logger.error(OILabelConstants.EXCEPTION_IN_BO,ex);
	  		responseObject.addResponseObject(OILabelConstants.ERROR,ex.getMessage());
	  		throw ex;
	    }
	    finally
	    {
	  		freeConnection();
	    }
	    logger.info(OILabelConstants.END_METHOD_BO + "webUserProfile");	
	    return responseObject;
	 }  
	 
	 /** This is the helper to add arraylist to responseobject
	  * @param alList
	  * @param strKey
	  * @return
	  */
   
	 private void addList(ArrayList alList ,String strKey )
	 {
	    //	if(alList!=null){
   	  	responseObject.addResponseObject(strKey,alList);
   	  	//  }
	 }
    		
	 /**
	  * This helper methods adds subject Level,Level Teaching,CCA to response Object.
	  * @param hm
	  * @param strKey
	  */
	 private void addLevel(HashMap hm, String strKey)
	 {
	     if(hm.containsKey(strKey))
	     {
	         ArrayList alTemp = (ArrayList)hm.get(strKey);
	         responseObject.addResponseObject(strKey,alTemp);
	     }
	 }
  
   	/**
   	 * This method reads the values for roleids from properties file
   	 * @param objBV
   	 */
	 private ArrayList getRoleIds(Connection connection,OIBVAdminUserProfile objBV)throws Exception
	 {
	     ArrayList alList = new ArrayList();
	     alList.add(new org.apache.struts.util.LabelValueBean(OIDBRegistry.getValues(OILabelConstants.PLS_SELECT),OIAdminConstants.DBQUOTES));
		 OIDAOAdminUserProfile objDAO = new OIDAOAdminUserProfile();
	   /*  if(objBV.getLoginRole()!=null && objBV.getLoginRole().trim().length()>0 )
	     {
	         String strRoleId = OIDBRegistry.getValues(objBV.getLoginRole().trim().toUpperCase());

	         StringTokenizer stValues = new StringTokenizer(strRoleId,OILabelConstants.COMMA);
	         while(stValues.hasMoreElements())
	         {
	             strRoleId = stValues.nextToken();
	             alList.add(new org.apache.struts.util.LabelValueBean(strRoleId,strRoleId));
	         }
	     }*/
		 try
			{
				ArrayList arRoles = objDAO.getRoles(connection);
				String strRoleId="";
				if(arRoles!=null)
				{
					for(int i=0;i<arRoles.size();i++)
					{
						strRoleId = (String)arRoles.get(i);
						alList.add(new org.apache.struts.util.LabelValueBean(strRoleId,strRoleId));
					}
				}
				
			}
			catch (Exception ex)
			{
				logger.error(OILabelConstants.EXCEPTION_IN_BO,ex);
				responseObject.addResponseObject(OILabelConstants.ERROR,ex.getMessage());
				throw ex;
			}
			
	     return alList;
	 }

	 private ArrayList getRoleIds(OIBVAdminUserProfile objBV)throws Exception
	 {
	     ArrayList alList = new ArrayList();
	     alList.add(new org.apache.struts.util.LabelValueBean(OIDBRegistry.getValues(OILabelConstants.PLS_SELECT),OIAdminConstants.DBQUOTES));
	     if(objBV.getLoginRole()!=null && objBV.getLoginRole().trim().length()>0 )
	     {
	         String strRoleId = OIDBRegistry.getValues(objBV.getLoginRole().trim().toUpperCase());
	         StringTokenizer stValues = new StringTokenizer(strRoleId,OILabelConstants.COMMA);
	         while(stValues.hasMoreElements())
	         {
	             strRoleId = stValues.nextToken();
	             alList.add(new org.apache.struts.util.LabelValueBean(strRoleId,strRoleId));
	         }
	     }
	     return alList;
	 }
	 
	 
	 public ArrayList getMailDomains() throws Exception {
	 	
	 	OIDAOAdminUserProfile objDAO = new OIDAOAdminUserProfile();
	 	OIResponseObject objRespObj = new OIResponseObject();
	 	ArrayList arDomain = null;
	 	try
		{
	 		getConnection();
	 		arDomain = objDAO.getMailDomains(connection);
		}
	    catch (Exception ex)
	    {
	        logger.error(OILabelConstants.EXCEPTION_IN_BO,ex);
	  		responseObject.addResponseObject(OILabelConstants.ERROR,ex.getMessage());
	  		throw ex;
	    }
	    finally
	    {
	  		freeConnection();
	    }
 		return arDomain;
	 }
}