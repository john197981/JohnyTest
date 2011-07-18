package com.oifm.login;
/*
 * Class Name:
 * Description:
 * 
 * 	Created By			Created/Modified on			Revision				Remarks
 * -----------------------------------------------------------------------------------------------------
 * 	Rajkumar			28/06/2005					Draft					Inital Version
 * 
 * -----------------------------------------------------------------------------------------------------
 */

import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.oifm.base.OIBaseAction;
import com.oifm.common.OIBAConfiguration;
import com.oifm.common.OIResponseObject;
import com.oifm.common.OIFunctionConstants;
import com.oifm.forum.OIBOThread;
import com.oifm.forum.OIForumConstants;
import com.oifm.survey.OISurveyConstants;
import com.oifm.utility.OIDBRegistry;
//import com.oifm.utility.OIDBRegistry;
import com.oifm.utility.OIUtilities;

public class OIActionLogin extends OIBaseAction 
{
    Logger logger = Logger.getLogger(OIActionLogin.class.getName());
    
    private static String VALID_ACTION = "valid";
    private static String LOGOUT_ACTION = "logout";
    public ActionForward doIt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String actionName)
    {
        if (actionName!=null)
        {
	        if (actionName.equals(VALID_ACTION))
	        {
	            return validUser(mapping,form,request,response);
	        }
	        if (actionName.equals(LOGOUT_ACTION))
	        {
	            return logout(mapping,form,request,response);
	        }
        }
        /*String error = "Invalid Access";
        request.setAttribute("TopFlag","true");
        try
        {
            request.setAttribute("URL",OIDBRegistry.getValues("OIFM.contextroot") + "/jsp/index.jsp");
        }
        catch(Exception e)
        {
            logger.error(e.getMessage());
        }
        request.setAttribute(OILoginConstants.K_ERROR,error);
        return new ActionForward(OILoginConstants.ERRORPAGE);*/
        return validUser(mapping,form,request,response);
    }
    
    public ActionForward validUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        /*setSessionAttribute(request,OILoginConstants.USER_ID,null);
        setSessionAttribute(request,OILoginConstants.USER_NAME,null);
        setSessionAttribute(request,OILoginConstants.EMAIL,null);*/

        String name = null,password=null;
        //For Scandent
        if (request.getParameter(OILoginConstants.USER_ID)!=null)
        {
        	logger.info("aaa");
        	System.out.println("bbb");
            name = ((String) request.getParameter(OILoginConstants.USER_ID)).toUpperCase();
        }
        
        //For GDS
        if (getSessionAttribute(request,OILoginConstants.USER_ID)!= null)
        {
            name = ((String) getSessionAttribute(request,OILoginConstants.USER_ID)).toUpperCase();
        }
        
        OIResponseObject oIResponseObject = (OIResponseObject) new OIBOLogin().readRoles(name,password);
        ArrayList messageList = (ArrayList) oIResponseObject.getResponseObject(OILoginConstants.K_MESSAGE);
        String error = (String) oIResponseObject.getResponseObject(OILoginConstants.K_ERROR);
        if (error != null)
        {
            request.setAttribute("TopFlag","true");
            try
            {
                request.setAttribute("URL",OIDBRegistry.getValues("OI.LOGINURL"));
            }
            catch(Exception e)
            {
                logger.error(e.getMessage());
                logger.error(e);
            }
            request.setAttribute(OILoginConstants.K_ERROR,error);
            return new ActionForward(OILoginConstants.ERRORPAGE);
        }
        String surveyTag = (String) oIResponseObject.getResponseObject("surveyTag");
        ArrayList arOIBVRole = (ArrayList) oIResponseObject.getResponseObject(OILoginConstants.K_arOIBVRole);
        OIBAConfiguration aOIBAConfigurationTimeOut = (OIBAConfiguration) oIResponseObject.getResponseObject("aOIBAConfigurationTimeOut");
        OIBAConfiguration aOIBAConfigurationAttachFile = (OIBAConfiguration) oIResponseObject.getResponseObject("aOIBAConfigurationAttachFile");

        ArrayList functionList = new ArrayList();
        String roleId=null;
        String roleName=null;
        String divCode=null;
        String userName=null;
        String email=null; 
        String strNickName = null;
        if (arOIBVRole!=null)
        {
            OIBVRole oIBVRole = null;
            for (int i=0;i<arOIBVRole.size();i++)
            {
                oIBVRole = (OIBVRole) arOIBVRole.get(i);
                roleId = oIBVRole.getRoleId();
                name = oIBVRole.getUserId();
                roleName = oIBVRole.getRoleName();
                userName = oIBVRole.getUserName();
                divCode = oIBVRole.getDivCode();
                email = oIBVRole.getEmail();
                strNickName =oIBVRole.getNickname();
                
                String functionId = oIBVRole.getFunctionId();
                functionList.add(functionId);
            }
            if (functionList.size()==0)
                functionList=null;
        }
		if(isGDSUser(name)) 
			functionList.add(OIFunctionConstants.GDS_USER);
		else functionList.add(OIFunctionConstants.TEMP_USER);
		if (surveyTag!=null)
		{
		    functionList = new ArrayList();
		    functionList.add(OIFunctionConstants.TEMP_USER);
		}
		if (userName!=null)
		    setSessionAttribute(request,OILoginConstants.USER_NAME, userName);
		else
		    setSessionAttribute(request,OILoginConstants.USER_NAME, (String) oIResponseObject.getResponseObject("userName"));
        setSessionAttribute(request,OILoginConstants.USER_ID, name);
        setSessionAttribute(request,OILoginConstants.ROLE_ID, roleId);
        setSessionAttribute(request,OILoginConstants.ROLE_NAME, roleName);
        setSessionAttribute(request,OILoginConstants.DIVCODE, divCode);
        setSessionAttribute(request,OILoginConstants.FUNCTION_LIST, functionList);
        setSessionAttribute(request,OILoginConstants.FUNCTION_LIST, functionList);
        if (aOIBAConfigurationTimeOut!=null)
            setSessionAttribute(request,"sessionTimeout", aOIBAConfigurationTimeOut.getValue());
        else
            setSessionAttribute(request,"sessionTimeout", "0");
        if (email!=null)
            setSessionAttribute(request,OILoginConstants.EMAIL, email);
        setSessionAttribute(request,OILoginConstants.NICKNAME, strNickName);
        
        /** Setting the Role in the session**/
        setSessionAttribute(request,OILoginConstants.ROLE, (String) oIResponseObject.getResponseObject(OILoginConstants.ROLE));

		if (surveyTag!=null)
		{
		    return new ActionForward(OISurveyConstants.SURVEY_USER_DO + "?strSurveyId=" + surveyTag +"&hiddenAction=" + OISurveyConstants.SURVEY_DETAILS);
		}

        Cookie cookies[] = request.getCookies();
    	String module=null;
    	String id=null;
    	for(int i = 0; i < cookies.length; i++ )
    	{
    		if (cookies[i].getName().equals("MyModule"))
    		{
    		    module=cookies[i].getValue();
    		}
    		if (cookies[i].getName().equals("MyID"))
    		{
    		    id=cookies[i].getValue();
    		}
    	}
	    //logger.info(module);
	    //logger.info(id.toString());
	    if (module != null && !id.equals("null") && OIUtilities.getInt(id) > 0)
	    {
	        if (module.equals("C"))
	            return new ActionForward("/webConsultOpenPaperAction.do?paperId=" + id+"&hiddenAction=populate");
	        if (module.equals("CP"))
	            return new ActionForward("/webConsultPastPaperAction.do?paperId=" + id+"&hiddenAction=populate");
	        if (module.equals("S"))
	            return new ActionForward(OISurveyConstants.SURVEY_USER_DO + "?strSurveyId=" + id+"&hiddenAction=" + OISurveyConstants.SURVEY_DETAILS);
	        if (module.equals("F"))
	        {
	        	// Added by K.K.Kumaresan on Sep 16,2009 for the direct link issue
	        	OIBOThread objBOThread = new OIBOThread();
	        	boolean validUser=objBOThread.isValidUser(id,name); // If the user is valid,it returns true.
		    	validUser=true;
		    	logger.info(" validUser "+validUser);
		    	if(!validUser)
		    	{
		                request.setAttribute("TopFlag","true");
		                try
		                {
		                    request.setAttribute("URL",OIDBRegistry.getValues("OI.LOGINURL"));
		                }
		                catch(Exception e)
		                {
		                    logger.error(e.getMessage());
		                    logger.error(e);
		                }
		                String strRedirect = OIForumConstants.ERROR_DO+"?error=OIDEFAULT";
		                request.setAttribute(OILoginConstants.K_ERROR,error);
		                return new ActionForward(strRedirect);
		    	}	
				else 
	            	return new ActionForward(OIForumConstants.THREAD_MAINTAIN_DO + "?strThreadId=" + id + "&hiddenAction=PostingList&directLink=Y");
	        }    
	        if (module.equals("ASM"))
	            return new ActionForward("/asmHome.do?hidLetterID=" + id + "&hiddenAction=populate&hidPageDesc=RecentLetters");
	        if (module.equals("ASMEditorNote")) // 14th March 2011, Added by Ramesh for addressing the Editor note link.
	            return new ActionForward("/ASMEditor.do?contentId="+id+"&hiddenAction=WEB");	        
	        if (module.equals("AC"))
	            return new ActionForward("/consultViewModifyPageAction.do?hiddenAction=populate&paperId=" + id);
	        if (module.equals("AS"))
	            return new ActionForward("/SurveyAdmin.do?hiddenAction=" + OISurveyConstants.SURVEY_EDIT + "&strSurveyId=" + id);
	        if (module.equals("BHP"))
	            return new ActionForward("/BlogHome.do?&hiddenAction=BlogModuleHomePage");
	        if (module.equals("BE"))
	            return new ActionForward("/IndividualBlogEntry.do?entryId=" + id);
	    }
	    if (module != null && id.equals("null"))
	    {
	    	//logger.info("REDIRECT To ASM Module");
	        if (module.equals("ASM"))
	            return new ActionForward("/asmHome.do?hiddenAction=populate");
	    }

	    request.setAttribute(OILoginConstants.EMAIL,"");
        return mapping.findForward(OILoginConstants.FORWARD_SUCCESS);
    }
    
    public ActionForward logout(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
		HttpSession session = request.getSession(false);
		
		if (session != null) 
		{
		    session.invalidate();
		}
        return mapping.findForward(LOGOUT_ACTION);
    }

	private boolean isGDSUser(String strUserId)
	{
		boolean isGdsUser = false;
		if(strUserId != null && !strUserId.equals("")) 
		{
			isGdsUser = (strUserId.length() <= 9);
		}
		return isGdsUser;
	}
}
