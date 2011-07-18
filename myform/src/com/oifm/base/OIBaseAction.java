package com.oifm.base;
/*
 * Class Name:
 * Description:
 * 
 * 	Created By			Created/Modified on			Revision				Remarks
 * -----------------------------------------------------------------------------------------------------
 * 	Rajkumar			16/06/2005					Draft					Inital Version
 * 
 * -----------------------------------------------------------------------------------------------------
 */ 
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.oifm.common.OIFunctionConstants;
import com.oifm.login.OILoginConstants;
import com.oifm.survey.OIFormSurveySection;
import com.oifm.survey.admin.OIBOSurveyAdmin;
import com.oifm.utility.OIDBRegistry;
import com.oifm.utility.OIEncrypter;

public abstract class OIBaseAction extends Action 
{
	private static Logger logger = Logger.getLogger(OIBaseAction.class);

    public abstract ActionForward doIt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String actionName);
    
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        String action = request.getParameter(OIBaseConstant.OI_BASE_ACTION);
        try
        {
            String servletInfo = request.getServletPath();
            if (! servletInfo.equals("/loginAction.do"))
            {
            	String emailAddress=request.getParameter("strUserId");
            	String surveyId=request.getParameter("strSurveyId");
            	//            	 Added by K.K.Kumaresan on Jan 23,2009
            	if(emailAddress!=null && !emailAddress.equals("") && surveyId!=null && !surveyId.equals(""))
            	{
            		// without login. coming thru mail id. 
            		OIBOSurveyAdmin surveyAdmin=new OIBOSurveyAdmin();
            		logger.info("emailAddress is      "+emailAddress+"surveyId is    "+surveyId);
            		// For decrypting the mail address and the survey name.
            		emailAddress = OIEncrypter.decrypt(emailAddress);
            		surveyId = OIEncrypter.decrypt(surveyId);
            		logger.info("emailAddress is      "+emailAddress+"surveyId is    "+surveyId);
            		boolean flag=surveyAdmin.checkEligibleUser(emailAddress,surveyId);
            		//boolean flag=true;	// warning. Need to comment this line.
            		if(flag)		// if it is a valid user. 
            		{
	            		setSessionAttribute(request,OILoginConstants.USER_ID, emailAddress);
	            		ArrayList functionList = new ArrayList();
	            		functionList.add(OIFunctionConstants.TEMP_USER);
	            		setSessionAttribute(request,OILoginConstants.FUNCTION_LIST, functionList);
	            		setSessionAttribute(request,OILoginConstants.EMAIL_USER, "true");
	            		
	            		OIFormSurveySection objSurveySection = (OIFormSurveySection)form;
	            		objSurveySection.setStrUserId(emailAddress);
	            		objSurveySection.setStrSurveyId(surveyId);
	            		
            		}
            		else
            		{
            			request.setAttribute("TopFlag","true");
                        request.setAttribute("URL",OIDBRegistry.getValues("OI.LOGINURL"));
            			request.setAttribute("error","Invalid link");
                        return new ActionForward("/error.do");
            		}
            		
            	}
            	else if (getSessionAttribute(request,OILoginConstants.USER_ID)==null)
                {
                    request.setAttribute("TopFlag","true");
                    request.setAttribute("URL",OIDBRegistry.getValues("OI.LOGINURL"));
                    request.setAttribute("error","Session Time Out. Please login to My Forum once again to continue");
                    return new ActionForward("/error.do");
                }
            }
//            logger.info(request.getRequestURL().toString());
//            logger.info(request.getContextPath());
//            logger.info(request.getPathInfo());
//            logger.info(request.getRealPath());
//            logger.info(request.getServletPath());
//            logger.info(request.getRemoteAddr());
        }
        catch(Exception e)
        {
            logger.error(e.getMessage());
        }
        return this.doIt(mapping,form,request,response,action);
    }
    
    protected Object getSessionAttribute(HttpServletRequest request, String name) 
    {
        logger.debug("Getting " + name + " from session.");

        Object obj = null;
        HttpSession session = request.getSession(false);

        if (session != null) 
        {
            obj = session.getAttribute(name);
        }

        return obj;
    }

    protected void removeSessionAttribute(HttpServletRequest request,String name) 
    {
		logger.debug("Removing " + name + " from session.");
		HttpSession session = request.getSession(false);
		
		if (session != null) 
		{
		    session.removeAttribute(name);
		}
    }

    protected void setSessionAttribute(HttpServletRequest request, String name,Object aObject) 
    {
		//logger.debug("Setting " + name + " of type " + aObject.getClass().getName() + " on session.");
		HttpSession session = request.getSession(false);
		
		if (session != null) 
		{
		    session.setAttribute(name, aObject);		    
		}
    }

    protected Object getApplicationAttribute(String name) 
    {
        return getServletContext().getAttribute(name);
    }

    protected ServletContext getServletContext() 
    {
        return servlet.getServletContext();
    }

}
