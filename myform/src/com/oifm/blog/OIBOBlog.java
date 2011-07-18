/**
 * 
 */
package com.oifm.blog;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseBo;
import com.oifm.common.OIPageInfoBean;
import com.oifm.common.OIResponseObject;
import com.oifm.utility.OIDBRegistry;

/**
 * @author Admin
 * 
 */
public class OIBOBlog extends OIBaseBo
{
	private static Logger logger = Logger.getLogger(OIBOBlog.class);	
	
	public OIBOBlog()
	{
		super();
	}
	
	public OIResponseObject getBlogsList() 
    {
        List allBlogsList = null;
        OIDAOBlog objBlogs = new OIDAOBlog();
        try 
        {
            getConnection();
            allBlogsList = objBlogs.getBlogsList(connection);			
        } 
        catch (SQLException se) 
        {
            error = ""+se.getErrorCode();
            logger.error("objBlogs - SQLException->" + se);
        } 
        catch (Exception e) 
        {
            error = "OIDEFAULT";
            logger.error("objBlogs->" + e);
        } 
        finally 
        {
            freeConnection();
            addToResponseObject();
            responseObject.addResponseObject(OIBlogConstants.BLOGS_LIST, allBlogsList);
        }
        return responseObject;
    }
	
	public OIResponseObject deleteBlogs(List blogs) 
    {
        OIDAOBlog objBlogs = new OIDAOBlog();
        try 
        {
            getConnection();
            objBlogs.deleteBlogs(connection,blogs);			
        } 
        catch (SQLException se) 
        {
            error = ""+se.getErrorCode();
            logger.error("deleteBlogs - SQLException->" + se);
        } 
        catch (Exception e) 
        {
            error = "OIDEFAULT";
            logger.error("deleteBlogs->" + e);
        } 
        finally 
        {
            freeConnection();
            addToResponseObject();            
        }
        return responseObject;
    }
	
	public OIResponseObject getBlogConfig() 
    {
        OIBABlogConfig blogConfig = null;
        OIDAOBlog objBlogs = new OIDAOBlog();
        try 
        {
            getConnection();
            blogConfig = objBlogs.getBlogConfig(connection);			
        } 
        catch (SQLException se) 
        {
            error = ""+se.getErrorCode();
            logger.error("getBlogConfig - SQLException->" + se);
        } 
        catch (Exception e) 
        {
            error = "OIDEFAULT";
            logger.error("getBlogConfig->" + e);
        } 
        finally 
        {
            freeConnection();
            addToResponseObject();
            responseObject.addResponseObject(OIBlogConstants.BLOG_CONFIG, blogConfig);
        }
        return responseObject;
    }
	
	public OIResponseObject updateBlogConfig(OIBABlogConfig blogConfig) 
    {
        OIDAOBlog objBlogs = new OIDAOBlog();
        try 
        {
            getConnection();
            objBlogs.updateBlogConfig(connection,blogConfig);			
        } 
        catch (SQLException se) 
        {
            error = ""+se.getErrorCode();
            logger.error("updateBlogConfig - SQLException->" + se);
        } 
        catch (Exception e) 
        {
            error = "OIDEFAULT";
            logger.error("updateBlogConfig->" + e);
        } 
        finally 
        {
            freeConnection();
            addToResponseObject();            
        }
        return responseObject;
    }
	
	public OIResponseObject createBlog(OIBABlog blog) 
    {
        OIDAOBlog objBlogs = new OIDAOBlog();
        try 
        {
            getConnection();
            objBlogs.createBlog(connection,blog);			
        } 
        catch (SQLException se) 
        {
            error = ""+se.getErrorCode();
            logger.error("createBlog - SQLException->" + se);
        } 
        catch (Exception e) 
        {
            error = "OIDEFAULT";
            logger.error("createBlog->" + e);
        } 
        finally 
        {
            freeConnection();
            addToResponseObject();            
        }
        return responseObject;
    }	
	
	public OIResponseObject getAllUsers(String searchString, int currentRow, int totalRecPerPage) 
    {
        List allUsers = null;
        OIDAOBlog objBlogs = new OIDAOBlog();
        OIPageInfoBean aOIPageInfoBean = null;
        
        try 
        {
            getConnection();
            aOIPageInfoBean = new OIPageInfoBean(currentRow,totalRecPerPage);
         //   System.out.println("OIBOBlog: getAllUsers - currentRow : "+currentRow);
         //   System.out.println("OIBOBlog: getAllUsers - totalRecPerPage : "+totalRecPerPage);
            
            int totalRecs = objBlogs.getTotalRecCount(connection, OIBlogSqls.GET_ALL_USERS_COUNT, searchString);
         //   System.out.println("OIBOBlog: getAllUsers - totalRecs : "+totalRecs);
            aOIPageInfoBean.setTotalRec(totalRecs);
            responseObject.addResponseObject("aOIPageInfoBean",aOIPageInfoBean);  
           
        //    System.out.println("OIBOBlog: getAllUsers - Start Record : "+aOIPageInfoBean.getStartRec());
        //    System.out.println("OIBOBlog: getAllUsers - End Record : "+aOIPageInfoBean.getEndRec());
            
            allUsers = objBlogs.getAllUsers(connection, searchString, aOIPageInfoBean.getStartRec(),aOIPageInfoBean.getEndRec());
        //    System.out.println("OIBOBlog: getAllUsers - var : "+"test");
        } 
        catch (SQLException se) 
        {
            error = ""+se.getErrorCode();
            logger.error("getAllUsers - SQLException->" + se);
        } 
        catch (Exception e) 
        {
            error = "OIDEFAULT";
            logger.error("getAllUsers->" + e);
        } 
        finally 
        {
            freeConnection();
            addToResponseObject();
            responseObject.addResponseObject(OIBlogConstants.ALL_USERS, allUsers);
        }
        return responseObject;
    }
	
	public OIResponseObject getBlog(Integer blogId) 
    {
        OIBABlog blog = null;
        OIDAOBlog objBlogs = new OIDAOBlog();
        try 
        {
            getConnection();
            blog = objBlogs.getBlog(connection,blogId);			
        } 
        catch (SQLException se) 
        {
            error = ""+se.getErrorCode();
            logger.error("objBlogs - SQLException->" + se);
        } 
        catch (Exception e) 
        {
            error = "OIDEFAULT";
            logger.error("objBlogs->" + e);
        } 
        finally 
        {
            freeConnection();
            addToResponseObject();
            responseObject.addResponseObject(OIBlogConstants.BLOG_DATA, blog);
        }
        return responseObject;
    }
	
	public OIResponseObject updateBlog(OIBABlog blog) 
    {
        OIDAOBlog objBlogs = new OIDAOBlog();
        try 
        {
            getConnection();
            objBlogs.updateBlog(connection,blog);			
        } 
        catch (SQLException se) 
        {
            error = ""+se.getErrorCode();
            logger.error("updateBlog - SQLException->" + se);
        } 
        catch (Exception e) 
        {
            error = "OIDEFAULT";
            logger.error("updateBlog->" + e);
        } 
        finally 
        {
            freeConnection();
            addToResponseObject();            
        }
        return responseObject;
    }
	
	public OIResponseObject getAllBlogsAuthors() 
    {
        List allBlogsAuthors = null;
        OIDAOBlog objBlogs = new OIDAOBlog();
        try 
        {
            getConnection();
            allBlogsAuthors = objBlogs.getAllBlogsAuthorsDetail(connection);			
        } 
        catch (SQLException se) 
        {
            error = ""+se.getErrorCode();
            logger.error("getAllBlogsAuthors - SQLException->" + se);
        } 
        catch (Exception e) 
        {
            error = "OIDEFAULT";
            logger.error("getAllBlogsAuthors->" + e);
        } 
        finally 
        {
            freeConnection();
            addToResponseObject();
            responseObject.addResponseObject(OIBlogConstants.ALL_BLOGS_AUTHORS, allBlogsAuthors);
        }
        return responseObject;
    }
	
	
	public OIResponseObject getBlogDetail(Integer blogId) 
    {
        OIBABlogDetail oiBABlogDetail = null;
        OIDAOBlog blogDAO = new OIDAOBlog();
       
        int noOfFeatPosts = 4;
        List latestPosts = null;
        List previousfeaturedPosts = null;
        try 
        {
            getConnection();
            oiBABlogDetail = new OIBABlogDetail();
            
            List blogs = blogDAO.getBlogsList(connection); 
            oiBABlogDetail.setBlogs(blogs);
            
            OIBABlogConfig blogConfig = blogDAO.getBlogConfig(connection);
            oiBABlogDetail.setBlogConfig(blogConfig);
            if (blogConfig!=null)
			{
            	latestPosts = blogDAO.getAllLatestPosts(connection, blogConfig.getNoOfPosts(), false, blogId);
            	oiBABlogDetail.setLatestPosts(latestPosts);
                
                if (blogConfig.getNoOfFeatPosts()!=null)
    			{
                	noOfFeatPosts = blogConfig.getNoOfFeatPosts().intValue();
                	//logger.info("getBlogDetail - noOfFeatPosts->" + noOfFeatPosts);
                	
                	// To Accomadate the Main Entry.
                	noOfFeatPosts++;
                	//logger.info("getBlogDetail - noOfFeatPosts->" + noOfFeatPosts);
    			}
			}            
           
            previousfeaturedPosts = blogDAO.getAllLatestPosts(connection, new Integer(noOfFeatPosts), true, blogId);
            if((previousfeaturedPosts !=null ) && (previousfeaturedPosts.size()>0))
            {
            	//logger.info("getBlogDetail - previousfeaturedPosts.size()->" + previousfeaturedPosts.size());
            	oiBABlogDetail.setLatestFeaturedPost((OIBABlogAdminCreateEntry)previousfeaturedPosts.get(0)); 
            	if(previousfeaturedPosts.size()>1)
            	{
            		oiBABlogDetail.setPreviousFeaturedPosts(previousfeaturedPosts.subList(1, previousfeaturedPosts.size()));
            	}
            }
            
            responseObject.addResponseObject("ALL_TAGS",blogDAO.getAllTags(connection));
        } 
        catch (SQLException se) 
        {
            error = ""+se.getErrorCode();
            logger.error("getBlogDetail - SQLException->" + se);
        } 
        catch (Exception e) 
        {
            error = "OIDEFAULT";
            logger.error("getBlogDetail->" + e);
        } 
        finally 
        {
            freeConnection();
            addToResponseObject();
            responseObject.addResponseObject(OIBlogConstants.BLOG_DETAIL, oiBABlogDetail);
        }
        return responseObject;
    }
	
	public OIResponseObject getArchivesPosts(OIBABlogArchives oiBABlogArchives) 
    {
        OIDAOBlog blogDAO = new OIDAOBlog();
        OIBABlogDetail oiBABlogDetail = null;
        List authors =  null;
        OIPageInfoBean aOIPageInfoBean = null;
        
        try 
        {
            getConnection();
            oiBABlogDetail = new OIBABlogDetail();
            
            OIDAOBlogAdmin oiDAOBlogAdmin = new OIDAOBlogAdmin();
            List categories = oiDAOBlogAdmin.getCategories(connection);
            oiBABlogDetail.setCategories(categories);
            
            if (oiBABlogArchives.getBlogId()==null)
			{
            	 authors =  blogDAO.getAllBlogsAuthors(connection);
			}
            else
            {
            	 authors =  blogDAO.getBlogAuthors(connection, oiBABlogArchives.getBlogId());
            }           
            oiBABlogDetail.setAuthors(authors);
            
            List months = blogDAO.getAllPostMonths(connection, oiBABlogArchives.getBlogId());
            oiBABlogDetail.setMonths(months);           
            
            aOIPageInfoBean = new OIPageInfoBean(Integer.parseInt(oiBABlogArchives.getRowId()),oiBABlogArchives.getRecPerPage());
            
            oiBABlogArchives = blogDAO.getTotalArchivesPostsCount(connection, oiBABlogArchives);
            //System.out.println("OIBOBlog: getArchivesPosts - Total Records : "+oiBABlogArchives.getTotalRecords());
            aOIPageInfoBean.setTotalRec(oiBABlogArchives.getTotalRecords()); 
            
            //System.out.println("OIBOBlog: getArchivesPosts - Start Record : "+aOIPageInfoBean.getStartRec());
            //System.out.println("OIBOBlog: getArchivesPosts - End Record : "+aOIPageInfoBean.getEndRec());
            
            List blogs = blogDAO.getArchivesPosts(connection, oiBABlogArchives, aOIPageInfoBean.getStartRec(),aOIPageInfoBean.getEndRec());
            //System.out.println("OIBOBlog: getArchivesPosts - blogs : "+blogs);
            oiBABlogDetail.setBlogs(blogs);
            responseObject.addResponseObject("BlogArchivesCountQuery",oiBABlogArchives.getTotalCountQuery());  
            responseObject.addResponseObject("BlogArchivesQuery",oiBABlogArchives.getArchivesQuery());   
            responseObject.addResponseObject("aOIPageInfoBean",aOIPageInfoBean);   
            
        } 
        catch (SQLException se) 
        {
            error = ""+se.getErrorCode();
            logger.error("getArchivesPosts - SQLException->" + se);
        } 
        catch (Exception e) 
        {
            error = "OIDEFAULT";
            logger.error("getArchivesPosts->" + e);
        } 
        finally 
        {
            freeConnection();
            addToResponseObject();
            responseObject.addResponseObject(OIBlogConstants.BLOG_DETAIL, oiBABlogDetail);
        }
        return responseObject;
    }
	
	public boolean isAuthor (String userId, Integer blogId)
	{
		OIDAOBlog objDAO = new OIDAOBlog();
		boolean ret = false;
		
		try
		{
			getConnection();
			ret = objDAO.isAuthor(connection, userId, blogId);
		} 
        catch (SQLException se) 
        {
            logger.error("isAuthor - SQLException->" + se);
        } 
        catch (Exception e) 
        {
            logger.error("isAuthor->" + e);
        } 
        finally 
        {
            freeConnection();
        }
		
		return ret;
	}
	
	// START
	// Added by Oscar for picture displaying
	
	public OIResponseObject readPicture(HttpServletResponse response, String fileName) {
		try {
			response.setContentType(getContentType(fileName));
			readFile(response, fileName);
		} catch (Exception e) {
			error = "OIDEFAULT";
			logger.error("readPicture() - " + e);
		}
		return responseObject;
	}
	
	private String getContentType(String fileName) {
		if (fileName.toLowerCase().endsWith("gif")) return "image/gif";
		else if (fileName.toLowerCase().endsWith("jpg")) return "image/jpeg";
		else if (fileName.toLowerCase().endsWith("bmp")) return "image/x-ms-bmp";
		else if (fileName.toLowerCase().endsWith("png")) return "image/x-png";
		else return null;
	}
	
	private boolean readFile(HttpServletResponse response, String fileName) throws Exception{
		InputStream iStream = null;
		OutputStream oStream = null;
		final int BUFFER_SIZE = 8192;
		int bytesRead = 0;
		boolean ret = false;
		
		try {
			byte[] buffer = new byte[BUFFER_SIZE];
			
			iStream = new FileInputStream(OIDBRegistry.getValues("OI.BLOG.PIC") + fileName);
			oStream = response.getOutputStream();
			
			while ((bytesRead = iStream.read(buffer, 0, BUFFER_SIZE)) != -1) {
				oStream.write(buffer, 0, bytesRead);
			}
			
			ret = true;
			
		} catch (Exception e) {
			logger.error("readFile() - " + e);
			throw e;
		} finally {
			iStream.close();
			oStream.flush();
			oStream.close();
		}
		return ret;
	}
	
	// END
	
	// START
	// by Oscar for reusable archives list for RHS menu
	
	public OIResponseObject getArchivesList(OIBAIndividualBlog objBA) 
    {
        OIDAOBlog blogDAO = new OIDAOBlog();
        OIBABlogDetail oiBABlogDetail = null;
        List authors =  null;
        
        try 
        {
            getConnection();
            oiBABlogDetail = new OIBABlogDetail();
            
            OIDAOBlogAdmin oiDAOBlogAdmin = new OIDAOBlogAdmin();
            List categories = oiDAOBlogAdmin.getCategories(connection);
            oiBABlogDetail.setCategories(categories);
            
            if (objBA.getBlogId()==null)
			{
            	 authors =  blogDAO.getAllBlogsAuthors(connection);
			}
            else
            {
            	 authors =  blogDAO.getBlogAuthors(connection, objBA.getBlogId());
            }           
            oiBABlogDetail.setAuthors(authors);
            
            List months = blogDAO.getAllPostMonths(connection, objBA.getBlogId());
            oiBABlogDetail.setMonths(months);
        } 
        catch (SQLException se) 
        {
            error = ""+se.getErrorCode();
            logger.error("getArchivesList - SQLException->" + se);
        } 
        catch (Exception e) 
        {
            error = "OIDEFAULT";
            logger.error("getArchivesList->" + e);
        } 
        finally 
        {
            freeConnection();
            addToResponseObject();
            responseObject.addResponseObject(OIBlogConstants.BLOG_DETAIL, oiBABlogDetail);
        }
        return responseObject;
    }
	
	// END
	
	public boolean doesBlogTitleExists (String blogTitle,Integer blogId)
	{
		OIDAOBlog objDAO = new OIDAOBlog();
		boolean ret = false;
		
		try
		{
			getConnection();
			ret = objDAO.doesBlogTitleExists(connection, blogTitle,blogId);
		} 
        catch (SQLException se) 
        {
            logger.error("doesBlogTitleExists - SQLException->" + se);
        } 
        catch (Exception e) 
        {
            logger.error("doesBlogTitleExists->" + e);
        } 
        finally 
        {
            freeConnection();
        }
		
		return ret;
	}
	
	public String getBlogTitle (Integer blogId)
	{
		OIDAOBlog objDAO = new OIDAOBlog();
		String blogTitle = "";
		
		try
		{
			getConnection();
			blogTitle = objDAO.getBlogTitle(connection, blogId);
		} 
        catch (SQLException se) 
        {
            logger.error("getBlogTitle - SQLException->" + se);
        } 
        catch (Exception e) 
        {
            logger.error("getBlogTitle->" + e);
        } 
        finally 
        {
            freeConnection();
        }
		
		return blogTitle;
	}
}
