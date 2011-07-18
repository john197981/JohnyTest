
package com.oifm.forum;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.oifm.base.OIBaseAction;
import com.oifm.common.OIResponseObject;
import com.oifm.login.OILoginConstants;
import com.oifm.utility.OIFormUtilities;

public class OIActionThreadsSearch extends OIBaseAction 
{
	private static Logger logger = Logger.getLogger(OIActionPosting.class);

    public ActionForward doIt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String strAction)
    {
    	String strForward = "";
    	String strRedirect = "";
    	try 
    	{
    		String strThreadListType = request.getParameter("threadListType");
    		String strThreadList = request.getParameter("threadList");
    		logger.info(" strThreadListType "+strThreadListType);
    		logger.info(" strThreadList "+strThreadList);
    		String userId = (String) getSessionAttribute(request,OILoginConstants.USER_ID);
    		
    		if(strThreadListType!= null && strThreadListType.equals("Hrs")){
	    		int iHours = Integer.parseInt(strThreadList);
	    		OIResponseObject aOIResponseObject = new OIBOThreadsSearch().readLists(userId,iHours);
	            ArrayList arOIBVUpToTopicListing = (ArrayList) aOIResponseObject.getResponseObject("arOIBVUpToTopicListing");
	    		request.setAttribute("arOIBVUpToTopicListing",arOIBVUpToTopicListing);
	    		request.setAttribute("pageName", "oifmThreadsSearch");
	    		strForward = "ListPage";
    		}else{	    	
    			if(strThreadList!= null && strThreadList.equals("BM")){
		    		OIResponseObject aOIResponseObject = new OIBOThreadsSearch().readBMLists(userId);
		            ArrayList arOIBVUpToTopicListing = (ArrayList) aOIResponseObject.getResponseObject("arOIBVUpToTopicListing");
		    		request.setAttribute("arOIBVUpToTopicListing",arOIBVUpToTopicListing);
		    		request.setAttribute("pageName", "oifmThreadsSearch");
		    		strForward = "ListPage";
    			}else if(strThreadList!= null && strThreadList.equals("PO")){
    				OIResponseObject aOIResponseObject = new OIBOThreadsSearch().readPOLists(userId);
		            ArrayList arOIBVUpToTopicListing = (ArrayList) aOIResponseObject.getResponseObject("arOIBVUpToTopicListing");
		    		request.setAttribute("arOIBVUpToTopicListing",arOIBVUpToTopicListing);
		    		request.setAttribute("pageName", "oifmThreadsSearch");
		    		strForward = "ListPage";
    			}
    		}
    		
    	} 
    	catch(Exception e) 
    	{
			e.printStackTrace();
	    	strRedirect = OIForumConstants.ERROR_DO+"?error=OIDEFAULT";
		} 
    	finally 
    	{
			
		}
		
		if(!strRedirect.equals(""))
		{
			return new ActionForward(strRedirect);
		}
		else
		{
			return (mapping.findForward(strForward));
		}
	}	

    private void prepareContentForQuote(OIFormPosting objPosting, OIBAPosting objPostingVo) 
    {
    	objPosting.setStrPosting("<br><br>QUOTE:<br><P><B>Posted By : </B>"+objPostingVo.getStrNickName()+"  <B> Posted On : </B>"+objPostingVo.getStrPostedOn()+"</P>"+objPostingVo.getStrPosting());
    	objPosting.setStrPostId("");
    	objPosting.setStrThreadId(objPostingVo.getStrThreadId());
    }
}