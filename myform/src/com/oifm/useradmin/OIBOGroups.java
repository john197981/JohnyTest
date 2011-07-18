/*
 * File name	= OIBOGroups.java
 * Package		= com.oifm.useradmin
 * Created on 	= Aug 11, 2005
 * Created by	= Oscar
 * Copyright	= Scandent Group
 *
 */

package com.oifm.useradmin;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseBo;
import com.oifm.common.OIResponseObject;

public class OIBOGroups extends OIBaseBo 
{
	private static Logger logger = Logger.getLogger(OIBOGroups.class);
    
    public OIBOGroups () {super();}
    
    public OIResponseObject getGroupsList() 
    {
        ArrayList alGroupsList = null;
        OIDAOGroups objGroups = new OIDAOGroups();
        try 
        {
            getConnection();
            alGroupsList = objGroups.getGroupsList(connection);			
        } 
        catch (SQLException se) 
        {
            error = ""+se.getErrorCode();
            logger.error("getGroupsList - SQLException->" + se);
        } 
        catch (Exception e) 
        {
            error = "OIDEFAULT";
            logger.error("getGroupsList->" + e);
        } 
        finally 
        {
            freeConnection();
            addToResponseObject();
            responseObject.addResponseObject("GroupsList", alGroupsList);
        }
        return responseObject;
    }
    
    public OIResponseObject getGroupDetails(OIBAGroups objBAGroups) 
    {
    	OIDAOGroups objGroups = new OIDAOGroups();
    	try 
    	{
			getConnection();
    		if(objBAGroups.getGroupId() != null && objBAGroups.getGroupId().intValue() > 0) 
    		{
    			objBAGroups = objGroups.getGroupDetails(connection, objBAGroups.getGroupId());
    		}
        } 
    	catch (SQLException se) 
    	{
            error = ""+se.getErrorCode();
            logger.error("getGroupDetails - SQLException->" + se);
        } 
    	catch (Exception e) 
        {
            error = "OIDEFAULT";
            logger.error("getGroupDetails->" + e);
        } 
    	finally 
    	{
            freeConnection();
            addToResponseObject();
            responseObject.addResponseObject("objBAGroups", objBAGroups);
        }
        return responseObject;
    }
    
    public OIResponseObject getGroupMembers(OIBAGroups objBAGroups) 
    {
    	OIDAOGroups objGroups = new OIDAOGroups();
    	ArrayList alMembersList = null;
    	try 
    	{
			getConnection();
    		if(objBAGroups.getGroupId() != null && objBAGroups.getGroupId().intValue() > 0) 
    		{
    			alMembersList = objGroups.getGroupMembers(connection, objBAGroups.getGroupId(), objBAGroups.getSortKey(), objBAGroups.getSortOrder());
    		}
        } 
    	catch (SQLException se) 
    	{
            error = ""+se.getErrorCode();
            logger.error("getGroupMembers - SQLException->" + se);
        } 
    	catch (Exception e) 
        {
            error = "OIDEFAULT";
            logger.error("getGroupMembers - " + e);
        } 
    	finally 
    	{
            freeConnection();
            addToResponseObject();
            if (alMembersList != null && alMembersList.size() > 0)
            {
                responseObject.addResponseObject("MembersList", alMembersList);
            }
        }
        return responseObject;
    }
    
    public OIResponseObject createGroup (OIBAGroups objBAGroups, String userID) 
    {
    	OIDAOGroups objGroups = new OIDAOGroups();
    	try 
    	{
    		getConnection();
    		connection.setAutoCommit(false);
    		if (!objGroups.hasDuplicate(connection, objBAGroups.getGroupId(), objBAGroups.getName()))
    		{
    			error = "OI.GROUPS.DUPLICATE_GROUP";
    		}
    		else if (!objGroups.createGroup(connection, objBAGroups, userID))
    		{
    			error = "OIDEFAULT";
    		}
        } 
    	catch (SQLException se) 
    	{
            error = ""+se.getErrorCode();
            logger.error("createGroup - SQLException->" + se);
        } 
    	catch (Exception e) 
        {
            error = "OIDEFAULT";
            logger.error("createGroup->" + e);
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
                logger.error("createGroup - SQLException\n" + se);
            }
            freeConnection();
            addToResponseObject();
        }
        return responseObject;
    }
    
    public OIResponseObject editGroup (OIBAGroups objBAGroups, String userID) 
    {
    	OIDAOGroups objGroups = new OIDAOGroups();
    	try 
    	{
    		getConnection();
			connection.setAutoCommit(false);
    		if(objBAGroups.getGroupId() != null && objBAGroups.getGroupId().intValue() > 0) 
    		{
    			if (!objGroups.isOwner(connection, objBAGroups.getGroupId(), userID))
    			{
    				error = "OI.GROUPS.NO_EDIT";
    			}
    			else if (!objGroups.hasDuplicate(connection, objBAGroups.getGroupId(), objBAGroups.getName()))
    			{
    				error = "OI.GROUPS.DUPLICATE_GROUP";
    			}
    			else if (!objGroups.editGroup(connection, objBAGroups))
    			{
    				error = "OIDEFAULT";
    			}
    		}
        } 
    	catch (SQLException se) 
    	{
            error = ""+se.getErrorCode();
            logger.error("editGroup - SQLException->"  + se);
        } 
    	catch (Exception e) 
        {
            error = "OIDEFAULT";
            logger.error("editGroup->" + e);
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
                logger.error("editGroup - SQLException\n" + se);
            }
            freeConnection();
            addToResponseObject();
        }
        return responseObject;
    }
    
    public OIResponseObject deleteGroup (OIBAGroups objBAGroups, String userID) 
    {
    	OIDAOGroups objGroups = new OIDAOGroups();
    	try 
    	{
    		getConnection();
			connection.setAutoCommit(false);
    		if(objBAGroups.getGroupId() != null && objBAGroups.getGroupId().intValue() > 0) 
    		{
    			if (!objGroups.isOwner(connection, objBAGroups.getGroupId(), userID))
    			{
    				error = "OI.GROUPS.NO_DELETE";
    			}
    			else if (!objGroups.removeMembers(connection, objBAGroups))
    			{
    				error = "OIDEFAULT";
    			}
    			else if (!objGroups.removeThreadGroup(connection, objBAGroups))
    			{
    				error = "OIDEFAULT";
    			}
    			else if (!objGroups.deleteGroup(connection, objBAGroups))
    			{
    				error = "OIDEFAULT";
    			}
    		}
        } 
    	catch (SQLException se) 
    	{
            error = ""+se.getErrorCode();
            logger.error("deleteGroup - SQLException->" + se);
        } 
    	catch (Exception e) 
        {
            error = "OIDEFAULT";
            logger.error("deleteGroup=>" + e);
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
                logger.error("editGroup - SQLException\n" + se);
            }
            freeConnection();
            addToResponseObject();
        }
        return responseObject;
    }
    
    public OIResponseObject removeMember(OIBAGroups objBAGroups, String[] userIDs)
    {
    	OIDAOGroups objGroups = new OIDAOGroups();
    	int temp = 0;
    	int ret = 0;
    	try 
    	{
    		getConnection();
			connection.setAutoCommit(false);
    		if(objBAGroups.getGroupId() != null && objBAGroups.getGroupId().intValue() > 0 && userIDs.length > 0) 
    		{
    			for (int i = 0; i < userIDs.length; i++)
    			{
    				if ((temp = objGroups.removeMember(connection, objBAGroups, userIDs[i])) < 0) 
    				{
    					error = "OIDEFAULT";
    					break;
    				} 
    				else
    				{
    				    ret += temp;
    				}
    			}
    			responseObject.addResponseObject("Remove", new Integer(ret));
    		}
        } 
    	catch (SQLException se) 
    	{
            error = ""+se.getErrorCode();
            logger.error("removeMember - SQLException->" + se);
        } 
    	catch (Exception e) 
        {
            error = "OIDEFAULT";
            logger.error("removeMember->" + e);
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
                logger.error("removeMember - SQLException\n" + se);
            }
            freeConnection();
            addToResponseObject();
        }
        return responseObject;
    }
    
    public OIResponseObject isOwner(OIBAGroups objBAGroups, String userID) 
    {
    	OIDAOGroups objGroups = new OIDAOGroups();
    	boolean isOwner = false;
    	
    	try 
    	{
			getConnection();
    		if(objBAGroups.getGroupId() != null && objBAGroups.getGroupId().intValue() > 0) 
    		{
    			isOwner = objGroups.isOwner(connection, objBAGroups.getGroupId(), userID);
    		}
        } 
    	catch (SQLException se) 
    	{
            error = ""+se.getErrorCode();
            logger.error("isAssigned - SQLException->" + se);
        } 
    	catch (Exception e) 
        {
            error = "OIDEFAULT";
            logger.error("isAssigned->" + e);
        } 
    	finally 
        {
            freeConnection();
            addToResponseObject();
            responseObject.addResponseObject("isOwner", new Boolean(isOwner));
        }
        return responseObject;
    }
    
    public OIResponseObject getGroupID(String name) 
    {
    	OIDAOGroups objGroups = new OIDAOGroups();
    	Integer groupID = null;
    	
    	try 
    	{
    		getConnection();
    		groupID = objGroups.getGroupID(connection, name);
        } 
    	catch (SQLException se) 
    	{
            error = ""+se.getErrorCode();
            logger.error("getGroupID - SQLException->" + se);
        } 
    	catch (Exception e) 
        {
            error = "OIDEFAULT";
            logger.error("getGroupID->" + e);
        } 
    	finally 
        {
            freeConnection();
            addToResponseObject();
            responseObject.addResponseObject("groupID", groupID);
        }
        return responseObject;
    }
    
    public OIResponseObject getFixedGroupsList() 
    {
        ArrayList alFixedGroupsList = null;
        OIDAOGroups objGroups = new OIDAOGroups();
        try 
        {
            getConnection();
            alFixedGroupsList = objGroups.getFixedGroupsList(connection);			
        } 
        catch (SQLException se) 
        {
            error = ""+se.getErrorCode();
            logger.error("getFixedGroupsList - SQLException->" + se);
        } 
        catch (Exception e) 
        {
            error = "OIDEFAULT";
            logger.error("getFixedGroupsList->" + e);
        } 
        finally 
        {
            freeConnection();
            addToResponseObject();
            responseObject.addResponseObject("FixedGroupsList", alFixedGroupsList);
        //    System.out.println("OIBOGroups: getFixedGroupsList - alFixedGroupsList : "+alFixedGroupsList.size());
        }
        return responseObject;
    }
}