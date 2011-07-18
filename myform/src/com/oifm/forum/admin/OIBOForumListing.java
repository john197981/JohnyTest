package com.oifm.forum.admin;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseBo;
import com.oifm.common.OIResponseObject;

public class OIBOForumListing extends OIBaseBo 
{
    Logger logger = Logger.getLogger(OIBOForumListing.class.getName());
    
    public OIResponseObject readForumList(String pUserId)
    {
        try
        {
            getConnection();
            ArrayList arOIBVForumList = new OIDAOForumListing().readForumList(pUserId,connection);
            responseObject.addResponseObject("arOIBVForumList",arOIBVForumList);
        }
        catch(Exception e)
        {
            error = e.getMessage();
            try
            {
                connection.rollback();
            }catch (Exception ex){}
        }
        finally
        {
            freeConnection();
        }
        addToResponseObject();
        return responseObject;
    }
}
