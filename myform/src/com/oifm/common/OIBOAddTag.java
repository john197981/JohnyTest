package com.oifm.common;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseBo;
import com.oifm.blog.OIBOBlog;
import com.oifm.blog.OIBlogSqls;
import com.oifm.consultation.OIConsultationSqls;
import com.oifm.survey.OISurveySqls;

public class OIBOAddTag extends OIBaseBo{

	private static Logger logger = Logger.getLogger(OIBOBlog.class);
	
	public OIBOAddTag()
	{}
	
	public OIResponseObject getTags() 
    {
		OIDAOAddTag objTags = new OIDAOAddTag();
		List list = new ArrayList();
        try 
        {
        	getConnection();
            list = objTags.getTags(connection);	
        } 
        catch (SQLException se) 
        {
            error = ""+se.getErrorCode();
            logger.error("getTags - SQLException->" + se);
        } 
        catch (Exception e) 
        {
        		error = "OIDEFAULT";
            logger.error("getTags->" + e);
        } 
        finally 
        {
            freeConnection();
            addToResponseObject();
            responseObject.addResponseObject("tagList",list);
            responseObject.addResponseObject("error", error);
            
        }
        return responseObject;
    }
	
	public OIResponseObject saveNewTag(String tagName) 
    {
		OIDAOAddTag objTags = new OIDAOAddTag();
		int flag = 0;
        try 
        {
        	getConnection();
            objTags.saveNewTag(connection,tagName);
        } 
        catch (SQLException se) 
        {
            error = ""+se.getErrorCode();
            logger.error("saveNewTag - SQLException->" + se);
        } 
        catch (Exception e) 
        {
        	if (e.getMessage().equals("Blog.Tag.Existing")) 
        		error = ""+e.getMessage();
        	else
        		error = "OIDEFAULT";
            logger.error("saveNewTag->" + e);
        } 
        finally 
        {
            freeConnection();
            addToResponseObject();
            responseObject.addResponseObject("error", error);
            
        }
        return responseObject;
    }
	
	public OIResponseObject updateTag(String userId, ArrayList alTagNames) 
    {
		OIDAOAddTag objTags = new OIDAOAddTag();
		int flag = 0;
        try 
        {
        	getConnection();
            objTags.updateTag(connection,userId,alTagNames);   
        } 
        catch (SQLException se) 
        {
            error = ""+se.getErrorCode();
            logger.error("updateTag - SQLException->" + se);
        } 
        catch (Exception e) 
        {
            logger.error("updateTag->" + e);
        } 
        finally 
        {
            freeConnection();
            addToResponseObject();
            responseObject.addResponseObject("error", error);
            
        }
        return responseObject;
    }
	
	public OIResponseObject updateTag(String Id, ArrayList alTagNames, String strModule) 
    {
		OIDAOAddTag objTags = new OIDAOAddTag();
		int flag = 0;
        try 
        {
        	getConnection();
            objTags.updateTag(connection,Id,alTagNames,strModule);
        } 
        catch (SQLException se) 
        {
            error = ""+se.getErrorCode();
            logger.error("updateTag - SQLException->" + se);
        } 
        catch (Exception e) 
        {
            logger.error("updateTag->" + e);
        } 
        finally 
        {
            freeConnection();
            addToResponseObject();
            responseObject.addResponseObject("error", error);
            
        }
        return responseObject;
    }
	
	public OIResponseObject getTags(String id) 
    {
		OIDAOAddTag objTags = new OIDAOAddTag();
		List list = new ArrayList();
        try 
        {
        	getConnection();
            list = objTags.getTags(connection, id);    
        } 
        catch (SQLException se) 
        {
            error = ""+se.getErrorCode();
            logger.error("getTags - SQLException->" + se);
        } 
        catch (Exception e) 
        {
        		error = "OIDEFAULT";
            logger.error("getTags->" + e);
        } 
        finally 
        {
            freeConnection();
            addToResponseObject();
            responseObject.addResponseObject("tagList",list);
            responseObject.addResponseObject("error", error);
            
        }
        return responseObject;
    }
	
	public OIResponseObject getTags(String id , String strModule) 
    {
		OIDAOAddTag objTags = new OIDAOAddTag();
		List list = new ArrayList();
		String strQuery = "";
        try 
        {
        	if(strModule.equalsIgnoreCase("SU"))
        	{
        		strQuery = OISurveySqls.GET_SURVEY_TAGS;
        	}
        	else if(strModule.equalsIgnoreCase("CP"))
        	{
        		strQuery = OIConsultationSqls.GET_PAPER_TAGS;
        	}
        	else if(strModule.equalsIgnoreCase("BG"))
        	{
        		strQuery = OIBlogSqls.GET_BLOG_TAG;
        	}
        	
            getConnection();
            list = objTags.getTags(connection, id, strQuery); 
        } 
        catch (SQLException se) 
        {
            error = ""+se.getErrorCode();
            logger.error("getTags - SQLException->" + se);
        } 
        catch (Exception e) 
        {
        		error = "OIDEFAULT";
            logger.error("getTags->" + e);
        } 
        finally 
        {
            freeConnection();
            addToResponseObject();
            responseObject.addResponseObject("tagList",list);
            responseObject.addResponseObject("error", error);
            
        }
        return responseObject;
    }
}
