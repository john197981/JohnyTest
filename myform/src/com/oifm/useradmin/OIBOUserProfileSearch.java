/****************************************************************************************************
 * File Name		:	OIBOUserProfileSearch.java
 * Author			:	SureshKumar.R
 * Description		:   This Bo class is used to load the values based on the search criteria of users 	
 * Created Date		:	Jul 13, 2005
 * Version			:	1.0
 * Copyright : Scandent Group
 ****************************************************************************************************/


package com.oifm.useradmin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.oifm.base.OIBaseBo;
import com.oifm.common.OIDAOSendMail;
import com.oifm.common.OILabelConstants;
import com.oifm.common.OIPageInfoBean;
import com.oifm.common.OIResponseObject;
import com.oifm.useradmin.admin.OIAdminConstants;
import com.oifm.useradmin.admin.OIAdminSqls;
import com.oifm.useradmin.admin.OIDAOAdminUserProfile;
import com.oifm.utility.OIDBRegistry;
import com.oifm.utility.OIFormUtilities;
public class OIBOUserProfileSearch extends OIBaseBo 
{
    /**	Create instance for LOGGER and objects  used**/
	transient private final Log LOGGER = LogFactory.getLog(this.getClass());
	
	public OIBOUserProfileSearch()
	{
		LOGGER.info(OILabelConstants.BEGIN_METHOD_BO + this.getClass().getName());
	}
	
	/**
    * This is the main method to process the profile, it will delagate to populate,search methods.
    * @param objBVUsrPrfSrh
    * @return
    */
    public OIResponseObject processProfile(OIBVUserProfileSearch objBVUsrPrfSrh) throws Exception
    {
    	
    	LOGGER.info(OILabelConstants.BEGIN_METHOD_BO + "searchProfile");
    	
    	/** Get theb Hidden Action form the BV Object **/
    	String strHidAct = OIFormUtilities.chkIsNull(objBVUsrPrfSrh.getHiddenAction());
    	String strHidPage  = objBVUsrPrfSrh.getHidPage();
    	//System.out.println("OIBOUserProfileSearch-processProfile" + strHidAct);
    	LOGGER.info("strHidAct-" + strHidAct);
    	LOGGER.info("strHidPage-" + strHidPage);
    	boolean bFlag = false;
    	
    	if(strHidAct.equals(OILabelConstants.POPULATE))
    	{
    	    responseObject = getLists(objBVUsrPrfSrh);
    	}
    	else if(strHidAct.equals(OISearchConstants.ADDUSER) && strHidPage.equals(OISearchConstants.ADDBOARDS))
    	{
    	    addBoards(objBVUsrPrfSrh);
    		responseObject = getLists(objBVUsrPrfSrh);
    		responseObject = searchProfile( objBVUsrPrfSrh);
    	}
    	else if(strHidAct.equals(OISearchConstants.ADDUSER) && strHidPage.equals(OISearchConstants.ADDGROUPS))
    	{
    	    addGroups(objBVUsrPrfSrh);
    		responseObject = getLists(objBVUsrPrfSrh);
    		responseObject = searchProfile( objBVUsrPrfSrh);
    	}
    	else if(strHidAct.equals(OISearchConstants.SEARCH))
    	{
    	    responseObject = searchProfile( objBVUsrPrfSrh);
    		responseObject = getLists(objBVUsrPrfSrh);
    	}
    	else if(strHidAct.equals(OISearchConstants.ADDUSER))
    	{	
    	    addUser(objBVUsrPrfSrh);
    		responseObject = getLists(objBVUsrPrfSrh);
    		responseObject = searchProfile( objBVUsrPrfSrh);
    		/** Select Group **/	
    	}
    	else if (strHidAct.equals(OISearchConstants.SEARCHGROUP) || strHidAct.equals(OISearchConstants.SEARCHALLGROUP))
    	{
    	    responseObject = searchGroup(objBVUsrPrfSrh);
    	/** Add Group **/
    	}
    	else if (strHidAct.equals(OISearchConstants.ADDGROUP))
    	{
    	    bFlag = selectUser(objBVUsrPrfSrh);
    		if(bFlag)
    		{
    		    addUser(objBVUsrPrfSrh);
    		}	
    	}

    	if (responseObject.getResponseObject(OILabelConstants.ERROR)!=null)
    	    LOGGER.info("not null");
    	else
    	    LOGGER.info("null");
    	LOGGER.info(OILabelConstants.END_METHOD_BO + "searchProfile");
    	   	
		return responseObject;
    }
    
    /**
     * This method populates the dropdowns for the user profile
     * @param objBV
     * @return
     */
    private OIResponseObject getLists( OIBVUserProfileSearch objBVUsrPrfSrh)throws Exception
    {
        LOGGER.debug(OILabelConstants.BEGIN_METHOD_BO + "getLists");

        ArrayList alCombo = new ArrayList();
        ArrayList alList = new ArrayList();
        OIDAOAdminUserProfile objDAO = new OIDAOAdminUserProfile(); 
        try
        {
        //	System.out.println("OIBOUserProfileSearch-getLists--iam comin here...1111" );
            /**Create the Connection Object **/
    	    getConnection();

    	    /** Get the List of Role**/ 
    	    //alCombo = objDAO.readList(connection,OIAdminSqls.QRY_ROLE);
    	    /** The RoleId was fetched from database , now it was fetched from Properties file **/ 

    	    alCombo = getRoleIds(objBVUsrPrfSrh);
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
            LOGGER.error(OILabelConstants.EXCEPTION_IN_BO,ex);
    	    if (ex.getMessage()==null)
    	    {
    	        responseObject.addResponseObject(OILabelConstants.ERROR,"OIDEFAULT");
    	    }
    	    else
    	    {
    	        responseObject.addResponseObject(OILabelConstants.ERROR,ex.getMessage());
    	    }
    		//throw ex;
    	}
        finally
        {
            freeConnection();
    		alCombo = null;		
    	}
    	LOGGER.debug(OILabelConstants.END_METHOD_BO + "getLists");		 
    	return responseObject;
     }
    
    /**
     * This bo method fetchs the user profile information
     * @param objBVUsrPrfSrh
     * @return
     */
    private OIResponseObject searchProfile(OIBVUserProfileSearch objBVUsrPrfSrh)throws Exception
    {
        LOGGER.info(OILabelConstants.BEGIN_METHOD_BO + "searchProfile");
    	ArrayList alSrhUsrs = new ArrayList();
    	OIDAOUserProfileSearch objDAOUsrPrfSrh = new OIDAOUserProfileSearch();
    	OIPageInfoBean aOIPageInfoBean = null;
    	boolean bFlag = false;
    	
    	try
    	{
    	    /**Create the Connection Object **/
    	    getConnection();	
    		/**DAO call to search the List of Users **/
	    	//totalRecord
    	   // System.out.println("OIBOUserProfileSearch-searchProfile2222" );
	    	aOIPageInfoBean = new OIPageInfoBean(Integer.parseInt(objBVUsrPrfSrh.getRowId()),OIDAOSendMail.recPerPage(connection));
	    	bFlag = objDAOUsrPrfSrh.srchRowCntQry(connection,objBVUsrPrfSrh);

	    	if(bFlag)
	    	{
	    	    aOIPageInfoBean.setTotalRec(objBVUsrPrfSrh.getRowCount());
	    	    alSrhUsrs = objDAOUsrPrfSrh.searchUserProfile(connection,objBVUsrPrfSrh,aOIPageInfoBean.getStartRec(),aOIPageInfoBean.getEndRec());
	    	    if(objBVUsrPrfSrh.getFlag().equals(OILabelConstants.CONSULT_PAPER))
	    	    {
	    	        alSrhUsrs = objDAOUsrPrfSrh.selectIds(connection,objBVUsrPrfSrh,OISearchSqls.QRY_USERID_CP,alSrhUsrs);
	    	    }
	    	    else if (objBVUsrPrfSrh.getFlag().equals(OILabelConstants.SURVEY))
	    	    {
	    	        alSrhUsrs = objDAOUsrPrfSrh.selectIds(connection,objBVUsrPrfSrh,OISearchSqls.QRY_USERID_SU,alSrhUsrs);
	    	    }
	    	    else if (objBVUsrPrfSrh.getHidPage().equals(OISearchConstants.ADDBOARDS))
	    	    {
	    	        alSrhUsrs = objDAOUsrPrfSrh.selectIds(connection,objBVUsrPrfSrh,OISearchSqls.QRY_USERID_BOARD,alSrhUsrs);
	    	    }
	    	    else if (objBVUsrPrfSrh.getHidPage().equals(OISearchConstants.ADDGROUPS))
	    	    {
	    	        alSrhUsrs = objDAOUsrPrfSrh.selectIds(connection,objBVUsrPrfSrh,OISearchSqls.QRY_USERID_GROUPS,alSrhUsrs);
	    	    }	
    	    }
	        
	    	responseObject.addResponseObject("aOIPageInfoBean",aOIPageInfoBean);
	    	if(alSrhUsrs!=null)
	    	{
	    	    responseObject.addResponseObject(OISearchConstants.SEARCH,alSrhUsrs);
	    	}
    	}
    	catch (Exception ex)
    	{
    	    LOGGER.error(OILabelConstants.EXCEPTION_IN_BO,ex);
    	    if (ex.getMessage()==null)
    	    {
    	        responseObject.addResponseObject(OILabelConstants.ERROR,"OIDEFAULT");
    	    }
    	    else
    	    {
    	        responseObject.addResponseObject(OILabelConstants.ERROR,ex.getMessage());
    	    }
			//throw ex;
		}
    	finally
    	{
			freeConnection();
			alSrhUsrs = null;		
		}
		LOGGER.info(OILabelConstants.END_METHOD_BO + "searchProfile");		 
		return responseObject;
    }
    
    /**
     * This is the helper to add arraylist to responseobject
     * @param alList
     * @param strKey
     * @return
     */
    
    private void addList(ArrayList alList ,String strKey )
    {
        //	if(alList!=null){
    	responseObject.addResponseObject(strKey,alList);
    	LOGGER.info("strKey "+ strKey);	
    	//  }
    }
    
    /**
     * This method calls
     * 
     * if pModuleName.equals("Consultation")
     * 1. OIDAOUserProfileSearch.saveConsultationMemeber
     * 
     * if pModuleName.equals("Survey")
     * 2. OIDAOUserProfileSearch.saveSurveyMember
     * 
     * if pModuleName.equals("Group")
     * 3. OIDAOUserProfileSearch.saveGroupMember
     * 
     * and returns responseObject 
     */  

    public boolean addUser(OIBVUserProfileSearch objBVUsrPrfSrh)throws Exception
    {
        LOGGER.info(OILabelConstants.BEGIN_METHOD_BO + "addUser");
    	String strFlag = objBVUsrPrfSrh.getFlag();
    	
    	boolean bUpdate = false;
    	OIDAOUserProfileSearch objDAOUsrPrfSrh = new OIDAOUserProfileSearch(); 
    	try
    	{
    	    /**Create the Connection Object **/
	    	getConnection();	
    		/**DAO call to search the List of Users **/
	    	connection.setAutoCommit(false);
	    	  
	    	if(strFlag.equals("C"))
	    	{
	    	    bUpdate = objDAOUsrPrfSrh.addUsers(connection,objBVUsrPrfSrh, OISearchSqls.QRY_ADD_USERS_CP);
	    	}
	    	else if(strFlag.equals("S"))
	    	{ 
	    	    bUpdate = objDAOUsrPrfSrh.addUsers(connection,objBVUsrPrfSrh,OISearchSqls.QRY_ADD_USERS_SU);
	    	}
	    	connection.commit();
    	}
    	catch (Exception ex)
    	{
    	    if (ex.getMessage()==null)
    	    {
    	        responseObject.addResponseObject(OILabelConstants.ERROR,"OIDEFAULT");
    	    }
    	    else
    	    {
    	        responseObject.addResponseObject(OILabelConstants.ERROR,ex.getMessage());
    	    }
    	    LOGGER.error(OILabelConstants.EXCEPTION_IN_BO,ex);
    	    LOGGER.error(ex.getMessage());
			try
			{
			    connection.rollback();
			}
			catch(SQLException sqlEx)
			{	
			    LOGGER.error(OILabelConstants.EXCEPTION_IN_BO,sqlEx);
	    	}
			//throw ex;
    	}
    	finally
    	{
    	    freeConnection();
		}
		LOGGER.info(OILabelConstants.END_METHOD_BO + "addUser");		 
		return bUpdate;
    }
    
    /**
     * 
     * @param objBVUsrPrfSrh
     * @return
     */
    private OIResponseObject searchGroup(OIBVUserProfileSearch objBVUsrPrfSrh)throws Exception
    {
    	LOGGER.info(OILabelConstants.BEGIN_METHOD_BO + "searchGroup");
    	ArrayList alSrhUsrs = new ArrayList();
    	OIDAOUserProfileSearch objDAOUsrPrfSrh = new OIDAOUserProfileSearch(); 
    	try
    	{
    	    /**Create the Connection Object **/
	    	getConnection();	
    		/**DAO call to search the List of Users **/	
	    	alSrhUsrs = objDAOUsrPrfSrh.searchUserGroup(connection);

	    	if(alSrhUsrs!=null)
	    	{
	    	    responseObject.addResponseObject(OISearchConstants.SEARCH,alSrhUsrs);
	    	}
	    }
    	catch (Exception ex)
    	{
    	    LOGGER.error(OILabelConstants.EXCEPTION_IN_BO,ex);
    	    if (ex.getMessage()==null)
    	    {
    	        responseObject.addResponseObject(OILabelConstants.ERROR,"OIDEFAULT");
    	    }
    	    else
    	    {
    	        responseObject.addResponseObject(OILabelConstants.ERROR,ex.getMessage());
    	    }
			//throw ex;
		}
    	finally
    	{
			freeConnection();
			alSrhUsrs = null;		
		}
		LOGGER.info(OILabelConstants.END_METHOD_BO + "searchGroup");		 
		return responseObject;
    }
    
    /**
     * 
     * @param objBVUsrPrfSrh
     * @return
     */
    
    private boolean selectUser(OIBVUserProfileSearch objBVUsrPrfSrh)throws Exception
    {
    	LOGGER.info(OILabelConstants.BEGIN_METHOD_BO + "selectUser");
    	boolean bFlag = false;
    	String strFlag = objBVUsrPrfSrh.getFlag();
    	OIDAOUserProfileSearch objDAOUsrPrfSrh = new OIDAOUserProfileSearch(); 
    	try
    	{
    	    /**Create the Connection Object **/
	    	getConnection();	
    		/**DAO call to search the List of Users **/	
	    	if(strFlag.equals("C"))
	    	{
	    	    bFlag = objDAOUsrPrfSrh.selectUser(connection,objBVUsrPrfSrh, OISearchSqls.QRY_SLTURS2);
	    	}
	    	else if(strFlag.equals("S"))
	    	{
	    	    bFlag = objDAOUsrPrfSrh.selectUser(connection,objBVUsrPrfSrh, OISearchSqls.QRY_SLTURS2_SU);
	    	}
    	}
    	catch (Exception ex)
    	{
    	    LOGGER.error(OILabelConstants.EXCEPTION_IN_BO,ex);
    	    if (ex.getMessage()==null)
    	    {
    	        responseObject.addResponseObject(OILabelConstants.ERROR,"OIDEFAULT");
    	    }
    	    else
    	    {
    	        responseObject.addResponseObject(OILabelConstants.ERROR,ex.getMessage());
    	    }
			//throw ex;
		}
    	finally
    	{
    	    freeConnection();
		}
		LOGGER.info(OILabelConstants.END_METHOD_BO + "selectUser");		 
		return bFlag;
    }
    
    
    /**
     * This method adds the Boards Details 
	 * @param objBVUsrPrfSrh
	 * @return
	 */
    
    public boolean addBoards(OIBVUserProfileSearch objBVUsrPrfSrh)throws Exception
    {
		LOGGER.info(OILabelConstants.BEGIN_METHOD_BO + "addBoards");
		String strFlag = objBVUsrPrfSrh.getFlag();
		
		boolean bUpdate = false;
		OIDAOUserProfileSearch objDAOUsrPrfSrh = new OIDAOUserProfileSearch(); 
		try
		{
		    /**Create the Connection Object **/
	    	getConnection();	
	    	/**DAO call to search the List of Users **/
	    	connection.setAutoCommit(false);
	    	bUpdate = objDAOUsrPrfSrh.addBoards(connection,objBVUsrPrfSrh);
	    	connection.commit();
	    }
		catch (Exception ex)
		{
		    LOGGER.error(OILabelConstants.EXCEPTION_IN_BO,ex);
    	    if (ex.getMessage()==null)
    	    {
    	        responseObject.addResponseObject(OILabelConstants.ERROR,"OIDEFAULT");
    	    }
    	    else
    	    {
    	        responseObject.addResponseObject(OILabelConstants.ERROR,ex.getMessage());
    	    }
			try
			{
			    connection.rollback();
			}
			catch(SQLException sqlEx)
			{	
			    LOGGER.error(OILabelConstants.EXCEPTION_IN_BO,sqlEx);
	    	}
			//throw ex;
		}
		finally
		{
		    freeConnection();
		}
		LOGGER.info(OILabelConstants.END_METHOD_BO + "addBoards");		 
		return bUpdate;
	}
    
	/**
	 * This method is used to Added the User Groups
	 * @param objBVUsrPrfSrh
	 * @return
	 * @throws Exception
	 */
	public boolean addGroups(OIBVUserProfileSearch objBVUsrPrfSrh)throws Exception
	{
	    LOGGER.info(OILabelConstants.BEGIN_METHOD_BO + "addGroups");
	    String strFlag = objBVUsrPrfSrh.getFlag();
	
	    boolean bUpdate = false;
	    OIDAOUserProfileSearch objDAOUsrPrfSrh = new OIDAOUserProfileSearch(); 
	    try
	    {
	        /**Create the Connection Object **/
	        getConnection();	
	        /**DAO call to search the List of Users **/
	        connection.setAutoCommit(false);
	        bUpdate = objDAOUsrPrfSrh.addGroups(connection,objBVUsrPrfSrh);
	        connection.commit();
	    }
	    catch (Exception ex)
	    {
	        LOGGER.error(OILabelConstants.EXCEPTION_IN_BO,ex);
    	    if (ex.getMessage()==null)
    	    {
    	        responseObject.addResponseObject(OILabelConstants.ERROR,"OIDEFAULT");
    	    }
    	    else
    	    {
    	        responseObject.addResponseObject(OILabelConstants.ERROR,ex.getMessage());
    	    }
			try
			{
			    connection.rollback();
			}catch(SQLException sqlEx)
			{	
			    LOGGER.error(OILabelConstants.EXCEPTION_IN_BO,sqlEx);
			 	throw sqlEx;
			}
			//throw ex;
	    }
	    finally
	    {
	        freeConnection();
	    }
	    LOGGER.info(OILabelConstants.END_METHOD_BO + "addGroups");		 
	    return bUpdate;
	}
	
	/**
   	 * This method reads the values for roleids from properties file
   	 * @param objBV
   	 */
	
	private ArrayList getRoleIds(OIBVUserProfileSearch objBV)throws Exception
	{
   		ArrayList alList = new ArrayList();
   		alList.add(new org.apache.struts.util.LabelValueBean(OIDBRegistry.getValues(OILabelConstants.PLS_SELECT),OIAdminConstants.DBQUOTES));
   		if(objBV.getLoginRole()!=null && objBV.getLoginRole().trim().length()>0 )
   		{
   		    String strRoleId = objBV.getLoginRole().trim().toUpperCase();
			try{
				strRoleId= OIDBRegistry.getValues(OISearchConstants.SELECTUSERROLE+objBV.getLoginRole().trim().toUpperCase());
			}catch(Exception e){
				strRoleId = objBV.getLoginRole().trim().toUpperCase();
			}
   			StringTokenizer stValues = new StringTokenizer(strRoleId,OILabelConstants.COMMA);
   			while(stValues.hasMoreElements())
   			{
   				strRoleId = stValues.nextToken();
   				alList.add(new org.apache.struts.util.LabelValueBean(strRoleId,strRoleId));
   			}
   		}
   		return alList;
    }
}