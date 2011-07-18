package com.oifm.blog;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseBo;
import com.oifm.common.OICommonMailBean;
import com.oifm.common.OIResponseObject;
import com.oifm.utility.MailUtility;
import com.oifm.utility.OIDBRegistry;

public class OIBOIndividualBlogEntry extends OIBaseBo
{
	private static final Logger logger = Logger.getLogger(OIBOIndividualBlogEntry.class);
	
	
	public OIBOIndividualBlogEntry() {super();}
	
	public OIResponseObject getBlogEntryDetails(OIBAIndividualBlog objBA)
	{
		OIBAIndividualBlog objBAEntryDetails = null;
		ArrayList alComments = null;
		OIDAOIndividualBlogEntry objDAO = new OIDAOIndividualBlogEntry();
		
		try
		{
			getConnection();
			objBAEntryDetails = objDAO.getEntryDetails(connection, objBA.getEntryId());
			alComments = objDAO.getEntryComments(connection, objBA.getEntryId());
		} 
        catch (SQLException se) 
        {
            error = "" + se.getErrorCode();
            logger.error("getBlogEntryDetails - SQLException->" + se);
        } 
        catch (Exception e) 
        {
            error = "OIDEFAULT";
            logger.error("getBlogEntryDetails->" + e);
        } 
        finally 
        {
            freeConnection();
            addToResponseObject();
            responseObject.addResponseObject("EntryDetails", objBAEntryDetails);
            responseObject.addResponseObject("Comments", alComments);
        }
		
		return responseObject;
	}
	
	public OIResponseObject saveComment(OIBAIndividualBlog objBA, String userId)
	{
		OIDAOIndividualBlogEntry objDAO = new OIDAOIndividualBlogEntry();
		
		try
		{
			getConnection();
			objDAO.saveComment(connection, objBA.getBlogId(), objBA.getEntryId(), objBA.getComment(), userId);
		} 
        catch (SQLException se) 
        {
            error = "" + se.getErrorCode();
            logger.error("saveComment - SQLException->" + se);
        } 
        catch (Exception e) 
        {
            error = "OIDEFAULT";
            logger.error("saveComment->" + e);
        } 
        finally 
        {
            freeConnection();
            addToResponseObject();
        }
        
        return responseObject;
	}
	
	public void sendCommentNotificationMail(OIBAIndividualBlog objBA)
	{
		OIDAOIndividualBlogEntry objDAO = new OIDAOIndividualBlogEntry();
		OIBAIndividualBlog objBAEntryDetails = null;
		
		try
		{
			getConnection();
			
			objBAEntryDetails = objDAO.getEntryDetails(connection, objBA.getEntryId());
			
			HashMap hashMap = objDAO.getNotificationMailSubjectBody(connection);
			
			LinkedHashMap emailIds = objDAO.getNotificationMailRecipient(connection, objBA.getBlogId());
			
			if (emailIds.size() > 0)
			{
				OICommonMailBean objMailBean = new OICommonMailBean();
				MailUtility objMailUtil = new MailUtility();
				
				String tempMessage = (String)hashMap.get("MAIL_BODY");
				
				tempMessage = tempMessage.replaceAll("\\[BLOG_TITLE\\]", objBAEntryDetails.getBlogTitle());
				tempMessage = tempMessage.replaceAll("\\[ENTRY_TITLE\\]", objBAEntryDetails.getTitle());
				
				objMailBean.setSubject((String)hashMap.get("SUBJECT"));
				objMailBean.setMessage(tempMessage);
				objMailBean.setType(OIDBRegistry.getValues("type"));
				objMailBean.setSmtpHost(OIDBRegistry.getValues("smtp"));
				objMailBean.setFrom(OIDBRegistry.getValues("sendmailtoaddress"));
				
				for (Iterator iter = emailIds.values().iterator(); iter.hasNext();)
				{
					ArrayList alList = (ArrayList)iter.next();
					String strEmail = alList.toString();
					strEmail = strEmail.trim().substring(1,strEmail.length() - 1);
					objMailBean.setBcc(strEmail);
					objMailUtil.sendMessage(objMailBean);
				}
			}
			
		} 
        catch (Exception e) 
        {
            logger.error("sendCommentNotificationMail->" + e);
        } 
        finally 
        {
            freeConnection();
        }
	}
}
