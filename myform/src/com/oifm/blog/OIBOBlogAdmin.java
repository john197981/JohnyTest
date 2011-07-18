package com.oifm.blog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts.upload.FormFile;

import com.oifm.base.OIBaseBo;
import com.oifm.common.OIPageInfoBean;
import com.oifm.common.OIResponseObject;
import com.oifm.utility.OIDBRegistry;

public class OIBOBlogAdmin extends OIBaseBo
{
	private static Logger logger = Logger.getLogger(OIBOBlogAdmin.class);
	
	public OIBOBlogAdmin()
	{}
	
	//modify by edmund
	public OIResponseObject insertAdminBlogNewEntry(OIBABlogAdminCreateEntry blogAdmin, String strUserid, Integer blogId) 
    {
       	OIDAOBlogAdmin objBlogs = new OIDAOBlogAdmin();
        FormFile file = blogAdmin.getFileUpload();
        
        int entryId = 0;

        try 
        {
        	if(blogAdmin.getEntryExcept().length()>499)
        	{
        		throw new Exception("BLOG.ExcerptSize");
        	}
        	
        	if(file != null)
        	{
        		if ((file.getFileName()!=null)&& (!file.getFileName().trim().equals("")) && file.getFileSize() > 0)
				{

    				if (!checkFileType(file)) throw new Exception("BLOG.FileType");
    				if (!checkFileSize(file)) throw new Exception("BLOG.FileSize");
				}        		
			}
        	
            getConnection();
            entryId = objBlogs.insertAdminBlogNewEntry(connection,blogAdmin,strUserid,blogId);	
            
            if(entryId > 0){
            	
            	if(file != null)
            	{
            		if ((file.getFileName()!=null)&& (!file.getFileName().trim().equals("")) && file.getFileSize() > 0)
    				{
	    				String fileName = contructFileName(file, entryId, strUserid, blogId.intValue());
	    				
	    				if (!uploadFile(file, fileName)) throw new Exception("BLOG.Uploading");
	    				objBlogs.setPhoto(connection, fileName, entryId, blogId.intValue());
    				}        		
    			}
            }
            
            
            
        } 
        catch (SQLException se) 
        {
            error = ""+se.getErrorCode();
            logger.error("insertAdminBlogNewEntry - SQLException->" + se);
            responseObject.addResponseObject("error", se.getMessage());
        } 
        catch (Exception e) 
        {
        	if(e.getMessage()!=null)
        	{
	        	if (e.getMessage().equals("BLOG.FileType") || e.getMessage().equals("BLOG.FileSize") || e.getMessage().equals("BLOG.Uploading")) 
	        		error = e.getMessage();
	        	
	        	if(e.getMessage().equals("BLOG.ExcerptSize"))
	        		error = e.getMessage();
        	}
			else
				error = "OIDEFAULT";
            logger.error("insertAdminBlogNewEntry->" + e);
            
            responseObject.addResponseObject("error", e.getMessage());
        } 
        finally 
        {
            freeConnection();
            addToResponseObject();
            responseObject.addResponseObject("entryId", new Integer(entryId));
        }
        return responseObject;
    }

	public OIResponseObject updateAdminBlogNewEntry(OIBABlogAdminCreateEntry blogAdmin, String strUserid ,int entryid, Integer blogId) 
    {
       	OIDAOBlogAdmin objBlogs = new OIDAOBlogAdmin();
        FormFile file = blogAdmin.getFileUpload();
        
        int entryId = 0;

        try 
        {
        	//System.out.println("OIBOBlogAdmin: updateAdminBlogNewEntry - getEntryExcept : "+blogAdmin.getEntryExcept().length());
        	if(blogAdmin.getEntryExcept().length()>499)
        	{
        		throw new Exception("BLOG.ExcerptSize");
        	}
        	
        	if(file != null)
        	{
        		if ((file.getFileName()!=null)&& (!file.getFileName().trim().equals("")) && file.getFileSize() > 0)
				{

    				if (!checkFileType(file)) throw new Exception("BLOG.FileType");
    				if (!checkFileSize(file)) throw new Exception("BLOG.FileSize");
				}        		
			}
        	
        	getConnection();
            entryId = objBlogs.updateAdminBlogNewEntry(connection,blogAdmin,entryid,blogId);	
            
            if(entryId > 0){
            	
            	if(file != null)
            	{
            		if ((file.getFileName()!=null)&& (!file.getFileName().trim().equals("")) && file.getFileSize() > 0)
    				{
	    				String fileName = contructFileName(file, entryId, strUserid, blogId.intValue());
	    				
	    				if (!uploadFile(file, fileName)) throw new Exception("BLOG.Uploading");
	    				objBlogs.setPhoto(connection, fileName, entryId, blogId.intValue());
    				}        		
    			}
            }
            
            
            
        } 
        catch (SQLException se) 
        {
            error = ""+se.getErrorCode();
            logger.error("insertAdminBlogNewEntry - SQLException->" + se);
            responseObject.addResponseObject("error", se.getMessage());
        } 
        catch (Exception e) 
        {
        	if(e.getMessage()!=null)
        	{
	        	if (e.getMessage().equals("BLOG.FileType") || e.getMessage().equals("BLOG.FileSize") || e.getMessage().equals("BLOG.Uploading")) 
	        		error = e.getMessage();
	        	
	        	if(e.getMessage().equals("BLOG.ExcerptSize"))
	        		error = e.getMessage();
        	}
			else
				error = "OIDEFAULT";
            logger.error("insertAdminBlogNewEntry->" + e);
            
            responseObject.addResponseObject("error", e.getMessage());
        } 
        finally 
        {
            freeConnection();
            addToResponseObject();  
            responseObject.addResponseObject("entryId", new Integer(entryId));
        }
        return responseObject;
    }
	
	public ArrayList getOtherBlogAuthors(Integer blogId, String userId)
	{
		ArrayList arList = null;
		OIDAOBlogAdmin objDAO = new OIDAOBlogAdmin();
		
		try
		{
			getConnection();
			arList = objDAO.getOtherBlogAuthors(connection, blogId, userId);
		}
		catch (SQLException se)
		{
			logger.error("getOtherBlogAuthors - SQLException->" + se);
		}
		catch (Exception e)
		{
			logger.error("getOtherBlogAuthors->" + e);
		}
		finally
		{
			freeConnection();
		}
		
		return arList;
	}
	
	public boolean checkEntryTitleExists(OIBABlogAdminCreateEntry objBA)
	{
		OIDAOBlogAdmin  objDAO = new OIDAOBlogAdmin();
		boolean ret = true;
		
		try
		{
			getConnection();
			
			//System.out.println("OIBOBlogAdmin: checkEntryTitleExists - entryId : "+objBA.getEntryId());
			
			if (objBA.getEntryId() != null && objBA.getEntryId().intValue() > 0)
				ret = objDAO.checkEntryTitleExist(connection, objBA.getBlogId(), objBA.getEntryTitle(), objBA.getEntryId());
			else
				ret = objDAO.checkEntryTitleExist(connection, objBA.getBlogId(), objBA.getEntryTitle());
		}
		catch (SQLException se)
		{
			logger.error("checkEntryTitleExists - SQLException->" + se);
		}
		catch (Exception e)
		{
			logger.error("checkEntryTitleExists->" + e);
		}
		finally
		{
			freeConnection();
		}
		return ret;
	}
	
	public OIResponseObject getNoOfPostsRecentEntriesComments(Integer blogId)
	{
		OIDAOBlogAdmin  objDAO = new OIDAOBlogAdmin();
		List lstRecentEntries = null;
		List lstRecentComments = null;
		OIBABlogAdminRecentEntries objBA = null;
		
		try
		{	
			getConnection();
			objBA = objDAO.getBlogNoOfPosts(connection, blogId);
			lstRecentEntries = objDAO.getRecentEntries(connection, blogId);
			lstRecentComments = objDAO.getRecentComments(connection, blogId);
			
		}
		catch (SQLException se)
		{
			error = "" + se.getErrorCode();
			logger.error("getNoOfPostsRecentEntriesComments - SQLException->" + se);
		}
		catch (Exception e)
		{
			error = "OIDEFAULT";
			logger.error("getNoOfPostsRecentEntriesComments->" + e);
		}
		finally
		{
			freeConnection();
			addToResponseObject();
			responseObject.addResponseObject("objBA", objBA);
			if (lstRecentEntries.size() > 0) responseObject.addResponseObject("entriesList", lstRecentEntries);
			if (lstRecentComments.size() > 0) responseObject.addResponseObject("commentsList", lstRecentComments);
		}
		return responseObject;
	}
	
//	public OIResponseObject getRecentEntries() 
//    {
//       	OIDAOBlogAdmin  objBlogs = new OIDAOBlogAdmin();
//       	List entriesList = null;
//        
//        try 
//        {
//            getConnection();
//            entriesList=objBlogs.getRecentEntries(connection);			
//        } 
//        catch (SQLException se) 
//        {
//            error = ""+se.getErrorCode();
//            logger.error("getRecentEntries - SQLException->" + se);
//        } 
//        catch (Exception e) 
//        {
//            error = "OIDEFAULT";
//            logger.error("getRecentEntries->" + e);
//        } 
//        finally 
//        {
//            freeConnection();
//            addToResponseObject();
//            responseObject.addResponseObject("entriesList", entriesList);
//        }
//        return responseObject;
//    }
//
//	public OIResponseObject getRecentComments() 
//    {
//       	OIDAOBlogAdmin  objBlogs = new OIDAOBlogAdmin();
//       	List commentsList = null;
//        
//        try 
//        {
//            getConnection();
//            commentsList=objBlogs.getRecentComments(connection);			
//        } 
//        catch (SQLException se) 
//        {
//            error = ""+se.getErrorCode();
//            logger.error("getRecentComments - SQLException->" + se);
//        } 
//        catch (Exception e) 
//        {
//            error = "OIDEFAULT";
//            logger.error("getRecentComments->" + e);
//        } 
//        finally 
//        {
//            freeConnection();
//            addToResponseObject();
//            responseObject.addResponseObject("commentsList", commentsList);
//        }
//        return responseObject;
//    }
	
	//added by edmund
	public OIResponseObject getAllComments(Integer blogId) 
    {
       	OIDAOBlogAdmin  objBlogs = new OIDAOBlogAdmin();
       	List commentsList = null;
        
        try 
        {
            getConnection();
            commentsList=objBlogs.getAllComments(connection, blogId);			
        } 
        catch (SQLException se) 
        {
            error = ""+se.getErrorCode();
            logger.error("getAllComments - SQLException->" + se);
        } 
        catch (Exception e) 
        {
            error = "OIDEFAULT";
            logger.error("getAllComments->" + e);
        } 
        finally 
        {
            freeConnection();
            addToResponseObject();
            responseObject.addResponseObject("commentsList", commentsList);
        }
        return responseObject;
    }
	
	public OIResponseObject updateNoOfPosts(Integer blogId,Integer noOfPostsToShow ) 
    {
        OIDAOBlog objBlogs = new OIDAOBlog();
        try 
        {
            getConnection();
            objBlogs.updateBlogNoOfPostShow(connection, blogId, noOfPostsToShow);			
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
	
	public OIResponseObject getAllEntriesList() 
    {
       	OIDAOBlogAdmin  objBlogs = new OIDAOBlogAdmin();
       	List allEntriesList = null;
        
        try 
        {
            getConnection();
            allEntriesList=objBlogs.getEntriesList(connection);			
        } 
        catch (SQLException se) 
        {
            error = ""+se.getErrorCode();
            logger.error("getAllEntriesList - SQLException->" + se);
        } 
        catch (Exception e) 
        {
            error = "OIDEFAULT";
            logger.error("getAllEntriesList->" + e);
        } 
        finally 
        {
            freeConnection();
            addToResponseObject();
            responseObject.addResponseObject("entriesList", allEntriesList);
        }
        return responseObject;
    }
	
	//added by edmund
	public OIResponseObject getBlogEntriesList(Integer blogId) 
    {
       	OIDAOBlogAdmin  objBlogs = new OIDAOBlogAdmin();
       	List allEntriesList = null;
        
        try 
        {
            getConnection();
            allEntriesList=objBlogs.getBlogEntriesList(connection,blogId);			
        } 
        catch (SQLException se) 
        {
            error = ""+se.getErrorCode();
            logger.error("getAllEntriesList - SQLException->" + se);
        } 
        catch (Exception e) 
        {
            error = "OIDEFAULT";
            logger.error("getAllEntriesList->" + e);
        } 
        finally 
        {
            freeConnection();
            addToResponseObject();
            responseObject.addResponseObject("entriesList", allEntriesList);
        }
        return responseObject;
    }
	
	//modify by edmund
	public OIResponseObject getEntry(Integer entryId, Integer blogId) 
    {
		OIBABlogAdminCreateEntry BABlog = new OIBABlogAdminCreateEntry();
        OIDAOBlogAdmin objBlogs = new OIDAOBlogAdmin();
        try 
        {
        	//System.out.println("OIBOBlogAdmin: getEntry - var : "+"BO1");
            getConnection();
            BABlog = objBlogs.getEntry(connection,entryId, blogId);	
            //System.out.println("OIBOBlogAdmin: getEntry - var : "+"BO2");        
            } 
        catch (SQLException se) 
        {
            error = ""+se.getErrorCode();
            logger.error("getEntry - SQLException->" + se);
        } 
        catch (Exception e) 
        {
            error = "OIDEFAULT";
            logger.error("getEntry->" + e);
        } 
        finally 
        {
            freeConnection();
            addToResponseObject();
            responseObject.addResponseObject("ENTRY_DATA", BABlog);
        }
        return responseObject;
    }
	
	//added by edmund
	public OIResponseObject saveNewTag(String tagName) 
    {
		OIDAOBlogAdmin objBlogs = new OIDAOBlogAdmin();
		int flag = 0;
        try 
        {
        	//System.out.println("OIBOBlogAdmin: saveNewTag - var : "+"BO1");
            getConnection();
            objBlogs.saveNewTag(connection,tagName);	
            //System.out.println("OIBOBlogAdmin: saveNewTag - var : "+"BO2");        
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
	
//	added by edmund
	public OIResponseObject saveTag(Integer entryId, ArrayList alTagNames) 
    {
		OIDAOBlogAdmin objBlogs = new OIDAOBlogAdmin();
		int flag = 0;
        try 
        {
        	//System.out.println("OIBOBlogAdmin: saveTag - var : "+"BO1");
            getConnection();
            objBlogs.saveTag(connection,entryId,alTagNames);	
            //System.out.println("OIBOBlogAdmin: saveTag - var : "+"BO2");        
        } 
        catch (SQLException se) 
        {
            error = ""+se.getErrorCode();
            logger.error("saveTag - SQLException->" + se);
        } 
        catch (Exception e) 
        {
            logger.error("saveTag->" + e);
        } 
        finally 
        {
            freeConnection();
            addToResponseObject();
            responseObject.addResponseObject("error", error);
            
        }
        return responseObject;
    }
	
//	added by edmund
	public OIResponseObject updateTag(Integer entryId, ArrayList alTagNames) 
    {
		OIDAOBlogAdmin objBlogs = new OIDAOBlogAdmin();
		int flag = 0;
        try 
        {
        	//System.out.println("OIBOBlogAdmin: updateTag - var : "+"BO1");
            getConnection();
            objBlogs.updateTag(connection,entryId,alTagNames);	
            //System.out.println("OIBOBlogAdmin: updateTag - var : "+"BO2");        
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
	
//	added by edmund
	public OIResponseObject getTags() 
    {
		OIDAOBlogAdmin objBlogs = new OIDAOBlogAdmin();
		List list = new ArrayList();
        try 
        {
        	//System.out.println("OIBOBlogAdmin: getTags - var() : "+"BO1");
            getConnection();
            list = objBlogs.getTags(connection);	
            //System.out.println("OIBOBlogAdmin: getTags - var() : "+"BO2");        
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
	
//	added by edmund
	public OIResponseObject getTags(int entry) 
    {
		OIDAOBlogAdmin objBlogs = new OIDAOBlogAdmin();
		List list = new ArrayList();
        try 
        {
        	//System.out.println("OIBOBlogAdmin: getTags - var(e) : "+"BO1");
            getConnection();
            list = objBlogs.getTags(connection, entry);	
            //System.out.println("OIBOBlogAdmin: getTags - var(e) : "+"BO2");        
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
	
	//added by edmund
	public OIResponseObject getTags(int currentRow, int totalRecPerPage) 
    {
        List allTags = null;
        OIDAOBlog objBlogs = new OIDAOBlog();
        OIDAOBlogAdmin objAdmin = new OIDAOBlogAdmin();
        OIPageInfoBean aOIPageInfoBean = null;
        
        try 
        {
            getConnection();
            aOIPageInfoBean = new OIPageInfoBean(currentRow,totalRecPerPage);
            //System.out.println("OIBOBlog: getTags - currentRow : "+currentRow);
           // System.out.println("OIBOBlog: getTags - totalRecPerPage : "+totalRecPerPage);
            
            int totalRecs = objBlogs.getTotalRecCount(connection, OIBlogSqls.GET_TAG_LIST_COUNT);
            //System.out.println("OIBOBlog: getTags - totalRecs : "+totalRecs);
            aOIPageInfoBean.setTotalRec(totalRecs);
            responseObject.addResponseObject("aOIPageInfoBean",aOIPageInfoBean);  
           
            //System.out.println("OIBOBlog: getTags - Start Record : "+aOIPageInfoBean.getStartRec());
            //System.out.println("OIBOBlog: getTags - End Record : "+aOIPageInfoBean.getEndRec());
            
            allTags = objAdmin.getAllTags(connection, aOIPageInfoBean.getStartRec(),aOIPageInfoBean.getEndRec());             
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
            responseObject.addResponseObject("AllTags", allTags);
        }
        return responseObject;
    }

	//added by edmund
	private boolean checkFileType (FormFile file) {
		if (file.getFileName().toLowerCase().endsWith("gif") || 
			file.getFileName().toLowerCase().endsWith("jpg") ||
			file.getFileName().toLowerCase().endsWith("bmp") ||
			file.getFileName().toLowerCase().endsWith("png")) return true;
		else return false;
	}
	
//	added by edmund
	private boolean checkFileSize (FormFile file) {
		if (file.getFileSize() <= ((128 * 1024)*2)) return true;
		else return false;
	}
	
//	added by edmund
	private String contructFileName (FormFile file, int entryId, String userId, int blogId) {
		String ext = file.getFileName().substring(file.getFileName().lastIndexOf("."));
		NumberFormat formatter = new DecimalFormat("00000");
		return "BLOG" + userId + formatter.format(blogId) + entryId + ext;
	}

//	added by edmund
	private boolean uploadFile (FormFile file, String fileName) throws Exception {
		InputStream iStream = null;
		OutputStream oStream = null;
		final int BUFFER_SIZE = 8192;
		int bytesRead = 0;
		boolean ret = false;
		
		try {
			byte[] buffer = new byte[BUFFER_SIZE];
			
			iStream = file.getInputStream();
			oStream = new FileOutputStream(OIDBRegistry.getValues("OI.BLOG.PIC") + fileName);
			
			while ((bytesRead = iStream.read(buffer, 0, BUFFER_SIZE)) != -1) {
				oStream.write(buffer, 0, bytesRead);
			}
			
			ret = true;
			
		} catch (Exception e) {
			logger.error("uploadFile() - " + e);
			throw e;
		} finally {
			iStream.close();
			oStream.close();
		}
		return ret;
	}

	//added by edmund
	public OIResponseObject deleteEntry(Integer blogId, String [] aEntryId) 
    {
        OIDAOBlogAdmin objBlogs = new OIDAOBlogAdmin();
        try 
        {
        	//System.out.println("OIBOBlogAdmin: getEntry - var : "+"BO1");
            getConnection();
            objBlogs.deleteEntry(connection, blogId, aEntryId);	
            //System.out.println("OIBOBlogAdmin: getEntry - var : "+"BO2");        
        } 
        catch (SQLException se) 
        {
            error = ""+se.getErrorCode();
            logger.error("getEntry - SQLException->" + se);
        } 
        catch (Exception e) 
        {
            error = "OIDEFAULT";
            logger.error("getEntry->" + e);
        } 
        finally 
        {
            freeConnection();
            addToResponseObject();
            responseObject.addResponseObject("status", null);
        }
        return responseObject;
    }
	
//	added by edmund
	public OIResponseObject deleteComment(Integer blogId, String [] aCommentId) 
    {
        OIDAOBlogAdmin objBlogs = new OIDAOBlogAdmin();
        try 
        {
        	//System.out.println("OIBOBlogAdmin: delete comment - var : "+"BO1");
            getConnection();
            objBlogs.deleteComment(connection, blogId, aCommentId);	
            //System.out.println("OIBOBlogAdmin: delete comment - var : "+"BO2");        
        } 
        catch (SQLException se) 
        {
            error = ""+se.getErrorCode();
            logger.error("delete comment - SQLException->" + se);
        } 
        catch (Exception e) 
        {
            error = "OIDEFAULT";
            logger.error("delete comment->" + e);
        } 
        finally 
        {
            freeConnection();
            addToResponseObject();
            responseObject.addResponseObject("status", null);
        }
        return responseObject;
    }

//	added by edmund
	public OIResponseObject setFeature(Integer blogId, String [] aEntryId, String mode) 
    {
        OIDAOBlogAdmin objBlogs = new OIDAOBlogAdmin();
        try 
        {
        	//System.out.println("OIBOBlogAdmin: setFeature - var : "+"BO1");
            getConnection();
            objBlogs.setFeature(connection, blogId, aEntryId, mode);
        } 
        catch (SQLException se) 
        {
            error = ""+se.getErrorCode();
            logger.error("setFeature - SQLException->" + se);
        } 
        catch (Exception e) 
        {
            error = "OIDEFAULT";
            logger.error("setFeature->" + e);
        } 
        finally 
        {
            freeConnection();
            addToResponseObject();
            responseObject.addResponseObject("status", null);
        }
        return responseObject;
    }
	
//added by edmund
	public OIResponseObject removePhoto(String strPicName, String strEntryId) {
		OIDAOBlogAdmin objBlogs = new OIDAOBlogAdmin();
		try {
			getConnection();
			if ((strPicName != null && strPicName.length() > 0) && !deleteFile(strPicName)) throw new Exception();
			if (!objBlogs.removePhoto(connection, Integer.parseInt(strEntryId))) throw new Exception();
		} catch (SQLException e) {
			error = "" + e.getErrorCode();
			logger.error("removePhoto() - SQLException - " + e);
		} catch (Exception e) {
			error = "OIDEFAULT";
			logger.error("removePhoto() - " + e);
		} finally {
			freeConnection();
			addToResponseObject();
		}
		return responseObject;
	}

	//added by edmund
	private boolean deleteFile (String fileName) {
		boolean ret = false;
		try {
			File file = new File(OIDBRegistry.getValues("OI.BLOG.PIC") + fileName);
			ret = file.delete();
		} catch (Exception e) {
			logger.error("deleteFile() - " + e);
		}
		return ret;
	}
}
