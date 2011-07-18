/*
 * Roles module Business Object
 * 
 * File name	= OIBORoles.java
 * Package		= com.oifm.useradmin
 * Created on 	= Aug 5, 2005
 * Created by	= Oscar
 * Copyright	= Scandent Group
 *
 */
package com.oifm.useradmin;

import java.util.*;
import java.sql.*;
import org.apache.struts.util.LabelValueBean;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseBo;
import com.oifm.common.OIFunctionConstants;
import com.oifm.common.OIResponseObject;


public class OIBORoles extends OIBaseBo 
{
    private static Logger logger = Logger.getLogger(OIBORoles.class);
    
    public OIBORoles () {super();}
    
    public OIResponseObject getRolesList() 
    {
        ArrayList alRolesList = null;
        OIDAORoles objRolesData = new OIDAORoles();
        try 
        {
            getConnection();
            alRolesList = objRolesData.getRolesList(connection);			
        } 
        catch (SQLException se) 
        {
            error = ""+se.getErrorCode();
            logger.error("getRolesList - SQLException->" + se);
        } 
        catch (Exception e) 
        {
            error = "OIDEFAULT";
            logger.error("getRolesList->" + e);
        } 
        finally 
        {
            freeConnection();
            addToResponseObject();
            responseObject.addResponseObject("RolesList", alRolesList);
        }
        return responseObject;
    }
    
    public OIResponseObject getRoleDetails(OIBARoles objBARoles) 
    {
        OIDAORoles objRolesData = new OIDAORoles();
        
        try
        {
        	getConnection();
            if(objBARoles.getStrRoleID() != null && !objBARoles.getStrRoleID().equals("")) 
            {
                String[] strTemp = null;
                objBARoles = objRolesData.getRoleDetails(connection, objBARoles.getStrRoleID());
                strTemp = objRolesData.getRoleFunctions(connection, objBARoles.getStrRoleID());
                if (strTemp != null)
                {
                    objBARoles.setStrFunctions(strTemp);
                }
            }
        } 
        catch (SQLException se) 
        {
            error = "" + se.getErrorCode();
            logger.error("getRoleDetails - SQLException " + se);
        } catch (Exception e) 
        {
            error = "OIDEFAULT";
            logger.error("getRoleDetails - Exception " + e);
        } 
        finally 
        {
            freeConnection();
            addToResponseObject();
            responseObject.addResponseObject("objBARoles", objBARoles);
        }
        return responseObject;
    }
    
    public OIResponseObject createRole(OIBARoles objBARoles, String strNickname) 
    {
        OIDAORoles objRolesData = new OIDAORoles();
        
        try
        {
            getConnection();
            connection.setAutoCommit(false);
            if (objRolesData.hasDuplicate(connection, objBARoles.getStrRoleID()))
            {
                error = "OI.ROLE.DUPLICATE_ROLE";
            }
            else if (!objRolesData.createRole(connection, objBARoles, strNickname))
            {
                error = "OIDEFAULT";
            }
            else if (!objRolesData.setFunctions(connection, objBARoles.getStrFunctions(), objBARoles.getStrRoleID()))
            {
                error = "OIDEFAULT";
            }
            else if (!checkAddAdminTab(connection, objBARoles))
            {
                error = "OIDEFAULT";
            }
        } 
        catch (SQLException se) 
        {
            error = "" + se.getErrorCode();
            logger.error("createRole - SQLException\n" + se);
        } 
        catch (Exception e) 
        {
            error = "OIDEFAULT";
            logger.error("createRole - Exception\n" + e);
        } 
        finally 
        {
            try 
            {
                if (error != null)
                {
                    connection.rollback();
                }
                else
                {
                    connection.commit();
                }
                connection.setAutoCommit(true);
            } 
            catch (SQLException se) 
            {
                error = "" + se.getErrorCode();
                logger.error("saveRole - SQLException\n" + se);
            }
            freeConnection();
            addToResponseObject();
        }
        return responseObject;
    }
    
    public OIResponseObject saveRole(OIBARoles objBARoles) 
    {
        OIDAORoles objRolesData = new OIDAORoles();
        
        try
        {
            getConnection();
            connection.setAutoCommit(false);
            
            if (!objRolesData.saveRole(connection, objBARoles))
            {
                error = "OIDEFAULT";
            }
            else if (!objRolesData.deleteFunctions(connection, objBARoles.getStrRoleID()))
            {
                error = "OIDEFAULT";
            }
            else if (!objRolesData.setFunctions(connection, objBARoles.getStrFunctions(), objBARoles.getStrRoleID()))
            {
                error = "OIDEFAULT";
            }
            else if (!checkAddAdminTab(connection, objBARoles))
            {
                error = "OIDEFAULT";
            }
        } 
        catch (SQLException se) 
        {
            error = "" + se.getErrorCode();
            logger.error("saveRole - SQLException\n" + se);
        } 
        catch (Exception e) 
        {
            error = "OIDEFAULT";
            logger.error("saveRole - Exception\n" + e);
        } 
        finally 
        {
            try 
            {
                if (error != null)
                {
                    connection.rollback();
                }
                else
                {
                    connection.commit();
                }
                connection.setAutoCommit(true);
            } 
            catch (SQLException se) 
            {
                error = "" + se.getErrorCode();
                logger.error("saveRole - SQLException\n" + se);
            }
            freeConnection();
            addToResponseObject();
        }
        return responseObject;
    }
    
    public OIResponseObject deleteRole(OIBARoles objBARoles) 
    {
        OIDAORoles objRolesData = new OIDAORoles();
        
        try
        {
            getConnection();
            connection.setAutoCommit(false);
            
            if (!objRolesData.canDelete(connection, objBARoles.getStrRoleID()))
            {
                error = "OI.ROLE.DELETE";
            }
            else if (!objRolesData.deleteFunctions(connection, objBARoles.getStrRoleID()))
            {
                error = "OIDEFAULT";
            }
            else if (!objRolesData.deleteRole(connection, objBARoles.getStrRoleID()))
            {
                error = "OIDEFAULT";
            }
        } 
        catch (SQLException se) 
        {
            error = "" + se.getErrorCode();
            logger.error("deleteRole - SQLException\n" + se);
        } 
        catch (Exception e) 
        {
            error = "OIDEFAULT";
            logger.error("deleteRole - Exception\n" + e);
        } 
        finally 
        {
            try 
            {
                if (error != null)
                {
                    connection.rollback();
                }
                else
                {
                    connection.commit();
                }
                connection.setAutoCommit(true);
            } 
            catch (SQLException se) 
            {
                error = "" + se.getErrorCode();
                logger.error("saveRole - SQLException\n" + se);
            }
            freeConnection();
            addToResponseObject();
        }
        return responseObject;
    }
    
    public OIResponseObject getFunctionsList(ArrayList alFunctions) 
    {
        HashMap hashFunctions = new HashMap();
        LabelValueBean temp = null;
        ArrayList ADMIN_DF = new ArrayList(OIFunctionConstants.ADMIN_DF.length);
        ArrayList ADMIN_CP = new ArrayList(OIFunctionConstants.ADMIN_CP.length);
        ArrayList ADMIN_SU = new ArrayList(OIFunctionConstants.ADMIN_SU.length);
        ArrayList ADMIN_OTHERS = new ArrayList(OIFunctionConstants.ADMIN_OTHERS.length);
        ArrayList WEBSITE_DF = new ArrayList(OIFunctionConstants.WEBSITE_DF.length);
        ArrayList ADMIN_ASM = new ArrayList(OIFunctionConstants.ADMIN_ASM.length);
        ArrayList ADMIN_BLOG = new ArrayList(OIFunctionConstants.ADMIN_BLOG.length);
        OIDAORoles objRolesData = new OIDAORoles();
        try 
        {
            getConnection();
            hashFunctions = objRolesData.getFunctionsList(connection);
            
            for(int i = 0; i < OIFunctionConstants.ADMIN_DF.length; i++) 
            {
            	if (alFunctions.contains(OIFunctionConstants.ADMIN_DF[i])) 
            	{
            		temp = new LabelValueBean(OIFunctionConstants.ADMIN_DF[i], (String)hashFunctions.get(OIFunctionConstants.ADMIN_DF[i]));
            		ADMIN_DF.add(temp);
            	}
            }
            
            for(int i = 0; i < OIFunctionConstants.ADMIN_CP.length; i++) 
            {
            	if (alFunctions.contains(OIFunctionConstants.ADMIN_CP[i])) 
            	{
            		temp = new LabelValueBean(OIFunctionConstants.ADMIN_CP[i], (String)hashFunctions.get(OIFunctionConstants.ADMIN_CP[i]));
            		ADMIN_CP.add(temp);
            	}
            }
            
            for(int i = 0; i < OIFunctionConstants.ADMIN_SU.length; i++) 
            {
            	if (alFunctions.contains(OIFunctionConstants.ADMIN_SU[i])) 
            	{
            		temp = new LabelValueBean(OIFunctionConstants.ADMIN_SU[i], (String)hashFunctions.get(OIFunctionConstants.ADMIN_SU[i]));
            		ADMIN_SU.add(temp);
            	}
            }
            
            for(int i = 0; i < OIFunctionConstants.ADMIN_OTHERS.length; i++) 
            {
            	if (alFunctions.contains(OIFunctionConstants.ADMIN_OTHERS[i])) 
            	{
            		temp = new LabelValueBean(OIFunctionConstants.ADMIN_OTHERS[i], (String)hashFunctions.get(OIFunctionConstants.ADMIN_OTHERS[i]));
            		ADMIN_OTHERS.add(temp);
            	}
            }
            
            for(int i = 0; i < OIFunctionConstants.WEBSITE_DF.length; i++) 
            {
            	if (alFunctions.contains(OIFunctionConstants.WEBSITE_DF[i])) 
            	{
            		temp = new LabelValueBean(OIFunctionConstants.WEBSITE_DF[i], (String)hashFunctions.get(OIFunctionConstants.WEBSITE_DF[i]));
            		WEBSITE_DF.add(temp);
            	}
            }
            
            for(int i = 0; i < OIFunctionConstants.ADMIN_ASM.length; i++) 
            {
            	logger.info("inside ASM - " + OIFunctionConstants.ADMIN_ASM[i]);
            	
            	if (alFunctions.contains(OIFunctionConstants.ADMIN_ASM[i])) 
            	{
            		temp = new LabelValueBean(OIFunctionConstants.ADMIN_ASM[i], (String)hashFunctions.get(OIFunctionConstants.ADMIN_ASM[i]));
            		logger.info("adding ASM");
            		ADMIN_ASM.add(temp);
            	}
            }
            
            for(int i = 0; i < OIFunctionConstants.ADMIN_BLOG.length; i++) 
            {
            	logger.info("inside BLOG - " + OIFunctionConstants.ADMIN_BLOG[i]);
            	//logger.info("adding BLOG OIFunctionConstants.ADMIN_BLOG[i] : "+OIFunctionConstants.ADMIN_BLOG[i]);
            	//logger.info("alFunctions.contains(OIFunctionConstants.ADMIN_BLOG[i]) : "+alFunctions.contains(OIFunctionConstants.ADMIN_BLOG[i]));
            	
            	if (alFunctions.contains(OIFunctionConstants.ADMIN_BLOG[i])) 
            	{
            		temp = new LabelValueBean(OIFunctionConstants.ADMIN_BLOG[i], (String)hashFunctions.get(OIFunctionConstants.ADMIN_BLOG[i]));
            		//logger.info("adding BLOG (String)hashFunctions.get(OIFunctionConstants.ADMIN_BLOG[i]) : "+(String)hashFunctions.get(OIFunctionConstants.ADMIN_BLOG[i]));
            		//logger.info("adding BLOG : "+temp);
            		ADMIN_BLOG.add(temp);
            	}
            }
            
        } 
        catch (SQLException se) 
        {
            error = "" + se.getErrorCode();
            logger.error("getFunctionsList - SQLException\n" + se);
        } 
        finally 
        {
            freeConnection();
            addToResponseObject();
            responseObject.addResponseObject("ADMIN_DF", ADMIN_DF);
            responseObject.addResponseObject("ADMIN_CP", ADMIN_CP);
            responseObject.addResponseObject("ADMIN_SU", ADMIN_SU);
            responseObject.addResponseObject("ADMIN_OTHERS", ADMIN_OTHERS);
            responseObject.addResponseObject("WEBSITE_DF", WEBSITE_DF);
            responseObject.addResponseObject("ADMIN_ASM", ADMIN_ASM);
            responseObject.addResponseObject("ADMIN_BLOG", ADMIN_BLOG);
        }
        
        return responseObject;
    }
    
    public OIResponseObject canDelete(OIBARoles objBARoles) 
    {
        OIDAORoles objRolesData = new OIDAORoles();
        boolean canDelete = false;
        
        try
        {
            getConnection();
            if(objBARoles.getStrRoleID() != null && !objBARoles.getStrRoleID().equals("")) 
            {
                canDelete = objRolesData.canDelete(connection, objBARoles.getStrRoleID());
            }
        } 
        catch (SQLException se) 
        {
            error = "" + se.getErrorCode();
            logger.error("canDelete - SQLException\n" + se);
        } 
        catch (Exception e) 
        {
            error = "OIDEFAULT";
            logger.error("canDelete - Exception\n" + e);
        } 
        finally 
        {
            freeConnection();
            addToResponseObject();
            responseObject.addResponseObject("canDelete", new Boolean(canDelete));
        }
        return responseObject;
    }
    
    private boolean checkAddAdminTab (Connection conn, OIBARoles objBARoles) 
    {
        OIDAORoles objRolesData = new OIDAORoles();
        String[] tempStr = objBARoles.getStrFunctions();
        String[] adminTab = {OIFunctionConstants.ADMINISTRATION_TAB};
        boolean flag = false;
        boolean added = true;
        
        try
        {
            if (tempStr != null && tempStr.length != 0) 
            {
                for (int i = 0; i < tempStr.length; i++) 
                {
                    for (int j = 0; j < OIFunctionConstants.ADMIN_TAB.length; j++) 
                    {
                        if (tempStr[i].equalsIgnoreCase(OIFunctionConstants.ADMIN_TAB[j])) 
                        {
                            flag = true;
                            break;
                        }
                    }
                    if (flag)
                    {
                        break;
                    }
                }
                if (flag)
                {
                    added = objRolesData.setFunctions(conn, adminTab, objBARoles.getStrRoleID());
                }
            }
            
        } 
        catch (SQLException se) 
        {
            error = "" + se.getErrorCode();
            logger.error("checkAddAdminTab - SQLException\n" + se);
        } 
        catch (Exception e) 
        {
            error = "OIDEFAULT";
            logger.error("checkAddAdminTab - Exception\n" + e);
        }
        return added;
    }
}