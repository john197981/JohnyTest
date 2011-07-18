package com.oifm.forum;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.oifm.base.OIBaseAction;
import com.oifm.common.OIBAConfiguration;
import com.oifm.common.OIResponseObject;
import com.oifm.consultation.OIConsultConstant;
import com.oifm.login.OILoginConstants;
import com.oifm.utility.OIDBRegistry;
import com.oifm.utility.OIUtilities;

public class OIActionForumWebListing extends OIBaseAction
{
    Logger logger = Logger.getLogger(OIActionForumWebListing.class.getName());
    private static final String POPULATE_ACTION = "populate";

    public ActionForward doIt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String actionName)
    {
        if (actionName!=null)
        {
	        if (actionName.equals(POPULATE_ACTION))
	        {
	            return populate(mapping, form, request, response);
	        }
        }
        String error=null;
        try
        {
            error = OIDBRegistry.getValues("OI.CONS.GEN"); //"Action not Available";
        }
        catch(Exception e)
        {}

        request.setAttribute(OILoginConstants.K_ERROR,error);
        return new ActionForward(OILoginConstants.ERRORPAGE);
    }
    
    public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        String userId = (String) getSessionAttribute(request,OILoginConstants.USER_ID);
        
 		String strMode = OIUtilities.replaceNull(request.getParameter("hidMode"));
        
        OIResponseObject aOIResponseObject = new OIBOForumWebListing().readLists(userId,strMode);
        
        ArrayList messageList = (ArrayList) aOIResponseObject.getResponseObject(OILoginConstants.K_MESSAGE);
        String error = (String) aOIResponseObject.getResponseObject(OILoginConstants.K_ERROR);
        if (error != null)
        {
            request.setAttribute(OILoginConstants.K_ERROR,error);
            return new ActionForward(OILoginConstants.ERRORPAGE);
        }
        OIBAConfiguration aOIBAConfiguration = (OIBAConfiguration) aOIResponseObject.getResponseObject("aOIBAConfiguration");
        ArrayList arOIBVHottestThread = (ArrayList) aOIResponseObject.getResponseObject("arOIBVHottestThread");
        ArrayList arOIBVLatestThread = (ArrayList) aOIResponseObject.getResponseObject("arOIBVLatestThread");
        ArrayList arOIBVHQReplies = (ArrayList) aOIResponseObject.getResponseObject("arOIBVHQReplies");
        ArrayList arOIBVUpToTopicListing = (ArrayList) aOIResponseObject.getResponseObject("arOIBVUpToTopicListing");

        request.setAttribute("arOIBVUpToTopicListing",arOIBVUpToTopicListing);
        request.setAttribute("arOIBVHQReplies",arOIBVHQReplies);
        request.setAttribute("arOIBVLatestThread",arOIBVLatestThread);
        request.setAttribute("arOIBVHottestThread",arOIBVHottestThread);
        request.setAttribute("aOIBAConfiguration",aOIBAConfiguration);
        request.setAttribute(OILoginConstants.PAGENAME,"ForumWebListing");
    	String strSplThreadID = (String) aOIResponseObject.getResponseObject("strSplThreadID");
    	String strSplThreadName = (String) aOIResponseObject.getResponseObject("strSplThreadName");
    	request.setAttribute("strSplThreadID",strSplThreadID);
    	request.setAttribute("strSplThreadName",strSplThreadName);
    	request.setAttribute("strMode",strMode);

        
        return mapping.findForward(OIConsultConstant.POPULATE_CONSULTLISTING);
    }

}
