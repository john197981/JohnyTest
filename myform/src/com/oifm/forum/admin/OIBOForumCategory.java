package com.oifm.forum.admin;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseBo;
import com.oifm.common.OIResponseObject;
import com.oifm.forum.OIForumConstants;
import com.oifm.utility.OIDBRegistry;

public class OIBOForumCategory extends OIBaseBo 
{
    Logger logger = Logger.getLogger(OIBOForumCategory.class.getName());
    
    public OIResponseObject saveCategory(OIBAForumCategory pOIBAForumCategory)
    {
        try
        {
            getConnection();
            connection.setAutoCommit(false);
            OIDAOForumCategory aOIDAOForumCategory = new OIDAOForumCategory();
            if (aOIDAOForumCategory.checkDuplicateCategory(pOIBAForumCategory.getName(),pOIBAForumCategory.getCategoryID(),connection))
            {
                boolean flag = aOIDAOForumCategory.save(pOIBAForumCategory,connection);
            }
            else
            {
                error = OIDBRegistry.getValues("OI.FORUM.DUPLICATE.CATEGORY");
            }
            connection.commit();
            connection.setAutoCommit(true);
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
    public OIResponseObject readCategory(int pCategoryId)
    {
        try
        {
            getConnection();
            OIBAForumCategory aOIBAForumCategory = new OIDAOForumCategory().read(pCategoryId,connection);
            responseObject.addResponseObject(OIForumConstants.K_aOIBAForumCategory,aOIBAForumCategory);
        }
        catch(Exception e)
        {
            error = e.getMessage();
        }
        finally
        {
            freeConnection();
        }
        addToResponseObject();
        return responseObject;
    }
    public OIResponseObject deleteCategory(OIBAForumCategory pOIBAForumCategory)
    {
        try
        {
            getConnection();
            connection.setAutoCommit(false);
            OIDAOForumCategory aOIDAOForumCategory = new OIDAOForumCategory();
            if (aOIDAOForumCategory.checkCategory(pOIBAForumCategory.getCategoryID(),connection)==0)
            {
                boolean flag = aOIDAOForumCategory.delete(pOIBAForumCategory,connection);
            }
            else
            {
                addError(OIDBRegistry.getValues("OI.FORUM.CATEGORY.CHECK"));
            }
            connection.commit();
            connection.setAutoCommit(true);
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
