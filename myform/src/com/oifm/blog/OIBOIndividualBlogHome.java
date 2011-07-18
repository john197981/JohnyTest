package com.oifm.blog;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseBo;
import com.oifm.common.OIResponseObject;

public class OIBOIndividualBlogHome extends OIBaseBo
{
	private static final Logger logger = Logger.getLogger(OIBOIndividualBlogHome.class);
	
	public OIBOIndividualBlogHome () {super();}
	
	public OIResponseObject getIndividualBlogEntries(OIBAIndividualBlog objBAIndividualBlogHome)
	{
		ArrayList alList = new ArrayList();
		OIDAOIndividualBlogHome objDAO = new OIDAOIndividualBlogHome();
		
		try
		{
			getConnection();
			if (objBAIndividualBlogHome.getBlogId() != null && objBAIndividualBlogHome.getBlogId().intValue() > 0)
				alList = objDAO.getIndividualBlogEntries(connection, objBAIndividualBlogHome.getBlogId());			
        } 
        catch (SQLException se) 
        {
            error = "" + se.getErrorCode();
            logger.error("getIndividualBlogEntries - SQLException->" + se);
        } 
        catch (Exception e) 
        {
            error = "OIDEFAULT";
            logger.error("getIndividualBlogEntries->" + e);
        } 
        finally 
        {
            freeConnection();
            addToResponseObject();
            responseObject.addResponseObject("BlogEntries", alList);
        }
		
		return responseObject;
	}
}
