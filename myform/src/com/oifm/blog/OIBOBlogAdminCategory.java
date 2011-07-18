package com.oifm.blog;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseBo;
import com.oifm.common.OIResponseObject;

public class OIBOBlogAdminCategory extends OIBaseBo
{
	private static Logger logger = Logger.getLogger(OIBOBlog.class);
	
	public OIBOBlogAdminCategory()
	{}

	public OIResponseObject createCategory(OIBABlogAdminCategory blogAdmin) 
    {
       	OIDAOBlogAdmin objBlogs = new OIDAOBlogAdmin();
       	List categoriesList = null;
       	
        try 
        {
            getConnection();
            objBlogs.createCategory(connection,blogAdmin);	
            
            categoriesList = objBlogs.getCategories(connection)	;	
            responseObject.addResponseObject("categoriesList", categoriesList);
        } 
        catch (SQLException se) 
        {
            error = ""+se.getErrorCode();
            logger.error("createCategory - SQLException->" + se);
        } 
        catch (Exception e) 
        {
            error = "OIDEFAULT";
            logger.error("createCategory->" + e);
        } 
        finally 
        {
            freeConnection();
            addToResponseObject();            
        }
        return responseObject;
    }
	

	public OIResponseObject getCategories() 
    {
       	OIDAOBlogAdmin objBlogs = new OIDAOBlogAdmin();
       	List categoriesList = null;
        
        try 
        {
            getConnection();
            categoriesList = objBlogs.getCategories(connection)	;	
        } 
        catch (SQLException se) 
        {
            error = ""+se.getErrorCode();
            logger.error("get Category - SQLException->" + se);
        } 
        catch (Exception e) 
        {
            error = "OIDEFAULT";
            logger.error("get Category->" + e);
        } 
        finally 
        {
            freeConnection();
            addToResponseObject(); 
            responseObject.addResponseObject("categoriesList", categoriesList);
        }
        return responseObject;
    }
	
	
	public OIResponseObject getCategory(Integer categoryId) 
    {
       	OIDAOBlogAdmin objBlogs = new OIDAOBlogAdmin();
       	OIBABlogAdminCategory objBA = null;
       	List categoriesList = null;
        
        try 
        {
            getConnection();
            objBA = objBlogs.getCategory(connection,categoryId)	;
            
            categoriesList = objBlogs.getCategories(connection)	;	
        } 
        catch (SQLException se) 
        {
            error = ""+se.getErrorCode();
            logger.error("getCategory(Integer categoryId)  - SQLException->" + se);
        } 
        catch (Exception e) 
        {
            error = "OIDEFAULT";
            logger.error("getCategory(Integer categoryId)  ->" + e);
        } 
        finally 
        {
            freeConnection();
            addToResponseObject(); 
            responseObject.addResponseObject("GetCategory", objBA);
            responseObject.addResponseObject("categoriesList", categoriesList);
        }
        return responseObject;
    }
	
	
	public OIResponseObject updateCategory(OIBABlogAdminCategory objBA) 
    {
       	OIDAOBlogAdmin objBlogs = new OIDAOBlogAdmin();
    	List categoriesList = null;
       	       
        try 
        {
            getConnection();
            objBlogs.updateCategory(connection,objBA);
            
            categoriesList = objBlogs.getCategories(connection)	;	
            
        } 
        catch (SQLException se) 
        {
            error = ""+se.getErrorCode();
            logger.error("getCategory(Integer categoryId)  - SQLException->" + se);
        } 
        catch (Exception e) 
        {
            error = "OIDEFAULT";
            logger.error("getCategory(Integer categoryId)  ->" + e);
        } 
        finally 
        {
            freeConnection();
            addToResponseObject(); 
            responseObject.addResponseObject("categoriesList", categoriesList);
        }
        return responseObject;
    }

	public boolean doesCategoryExists (String category,Integer categoryId)
	{
		OIDAOBlog objDAO = new OIDAOBlog();
		boolean ret = false;
		
		try
		{
			getConnection();
			ret = objDAO.doesCategoryExists(connection, category, categoryId);
		} 
        catch (SQLException se) 
        {
            logger.error("doesCategoryExists - SQLException->" + se);
        } 
        catch (Exception e) 
        {
            logger.error("doesCategoryExists->" + e);
        } 
        finally 
        {
            freeConnection();
        }
		
		return ret;
	}
	

}
