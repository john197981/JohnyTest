/*********************************ASMActionView.java******************* 
 * Title 		: ASMActionHomePage
 * Description 	: This class is the Action Class for ASM Submit View Letter. 
 * Author 		: Rajkumar 
 * Version No 	: 1.0 
 * Date Created : 16 - Dec -2005
 * Copyright 	: Scandent Group
 ******************************************************************************/
package com.oifm.asm;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.oifm.base.OIBaseAction;
import com.oifm.common.OIResponseObject;
import com.oifm.login.OILoginConstants;
import com.oifm.utility.DateUtility;
import com.oifm.utility.OIDBRegistry;

public class ASMActionView extends OIBaseAction
{
	private static Logger logger = Logger.getLogger(ASMActionView.class);

    private static final String POPULATE_ACTION = "populate";
    private static final String NEWLETTER_ACTION = "newOrEditLetter";
    private static final String DETAILLETTER_ACTION = "detailLetter";
    private static final String SUBMIT_ACTION = "submit";
    private static final String DRAFT_ACTION = "draft";
    private static final String DELETE_ACTION = "delete";

	public ActionForward doIt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String actionName)
	{
        if (actionName!=null)
        {
	        if (actionName.equals(POPULATE_ACTION))
	        {
	            return populate(mapping, form, request, response);
	        }
	        else if (actionName.equals(DETAILLETTER_ACTION))
	        {
	            return detailLetter(mapping, form, request, response);
	        }
	        else if (actionName.equals(NEWLETTER_ACTION))
	        {
	            return editOrNewLetter(mapping, form, request, response);
	        }
	        else if (actionName.equals(SUBMIT_ACTION))
	        {
	            return submitDraft(mapping, form, request, response,"S");
	        }
	        else if (actionName.equals(DRAFT_ACTION))
	        {
	            return submitDraft(mapping, form, request, response,"D");
	        }
	        else if (actionName.equals(DELETE_ACTION))
	        {
	            return delete(mapping, form, request, response);
	        }
        }
        String error=null;
        try
        {
            error = OIDBRegistry.getValues("OI.CONS.GEN"); //"Action not Available";
        }
        catch(Exception e){}
        request.setAttribute(OILoginConstants.K_ERROR,error);
        return new ActionForward(OILoginConstants.ERRORPAGE);
	}
    public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        String userId = (String) getSessionAttribute(request,OILoginConstants.USER_ID);
        ASMBACommon objCommonBA=new ASMBACommon();

        OIResponseObject aOIResponseObject = new ASMBOView().listUserLetters(userId,objCommonBA);
        ArrayList messageList = (ArrayList) aOIResponseObject.getResponseObject(OILoginConstants.K_MESSAGE);
        String error = (String) aOIResponseObject.getResponseObject(OILoginConstants.K_ERROR);
        if (error != null)
        {
            request.setAttribute(OILoginConstants.K_ERROR,error);
            return new ActionForward(OILoginConstants.ERRORPAGE);
        }
        ArrayList arASMBALetters = (ArrayList) aOIResponseObject.getResponseObject("arASMBALetters");
        String moduleDesc = null;
        if (aOIResponseObject.getResponseObject("strModDesc")!=null)
        {
        	moduleDesc = (String) aOIResponseObject.getResponseObject("strModDesc");
        }
//      ######################################################
		//This is for rightSide Portal -Start
		request.setAttribute("TotRecSizRecLtr",""+objCommonBA.getTotRecLtr());
		request.setAttribute("recent_letter",objCommonBA.getObjBV());
		request.setAttribute("editors_note_id",objCommonBA.getHidEditorsNoteID());
		request.setAttribute("editors_note",objCommonBA.getHidEditorsNote());
		request.setAttribute("editors_note_posted_on",objCommonBA.getHidEditorsNotePostedOn());
//		This is for rightSide Portal -End
		request.setAttribute("ModuleDesc",moduleDesc);
        request.setAttribute("arASMBALetters",arASMBALetters);
    	request.setAttribute(OILoginConstants.PAGENAME,"ASMView");
        return mapping.findForward("populate");
    }

    public ActionForward editOrNewLetter(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        String userId = (String) getSessionAttribute(request,OILoginConstants.USER_ID);
        String letterId = null;
        if (request.getParameter("letterId")!=null)
        {
        	letterId = (String) request.getParameter("letterId");
        }

        ASMFormLetter aASMFormLetter = new ASMFormLetter();
        if (letterId!=null && ! letterId.trim().equals(""))
        {
        	OIResponseObject aOIResponseObject = new ASMBOView().readDetailLetters(userId,letterId);
            ArrayList messageList = (ArrayList) aOIResponseObject.getResponseObject(OILoginConstants.K_MESSAGE);
            String error = (String) aOIResponseObject.getResponseObject(OILoginConstants.K_ERROR);
            if (error != null)
            {
                request.setAttribute(OILoginConstants.K_ERROR,error);
                return new ActionForward(OILoginConstants.ERRORPAGE);
            }
            ASMBALetters aASMBALetters = (ASMBALetters) aOIResponseObject.getResponseObject("aASMBALetters");
            try
			{
            	PropertyUtils.copyProperties(aASMFormLetter,aASMBALetters);
			}
            catch(Exception e)
			{
				logger.error(e.getMessage());
			}
        }
        
        request.setAttribute("ASMFormLetter",aASMFormLetter);
        return mapping.findForward("newOrEditLetter");
    }
    
    public ActionForward submitDraft(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response,String status)
    {
        String userId = (String) getSessionAttribute(request,OILoginConstants.USER_ID);
        
        ASMBALetters aASMBALetters = new ASMBALetters();
        ASMFormLetter aASMFormLetter = (ASMFormLetter) form;
        try
		{
        	aASMFormLetter.setStatus(status);
        	if (status.equals("S"))
        	{
        		aASMFormLetter.setSubmittedOn(DateUtility.getMMDDYYYYStringFromDate(new Date()));
        	}
        	if (status.equals("D"))
        	{
        		aASMFormLetter.setDraftedOn(DateUtility.getMMDDYYYYStringFromDate(new Date()));
        	}
        	PropertyUtils.copyProperties(aASMBALetters,aASMFormLetter);
		}
        catch(Exception e)
		{
			logger.error(e.getMessage());
		}
    	OIResponseObject aOIResponseObject = new ASMBOView().saveLetter(userId,aASMBALetters);
        ArrayList messageList = (ArrayList) aOIResponseObject.getResponseObject(OILoginConstants.K_MESSAGE);
        String error = (String) aOIResponseObject.getResponseObject(OILoginConstants.K_ERROR);
        if (error != null)
        {
            request.setAttribute(OILoginConstants.K_ERROR,error);
            return new ActionForward(OILoginConstants.ERRORPAGE);
        }
        ArrayList arASMBALetters = (ArrayList) aOIResponseObject.getResponseObject("arASMBALetters");

        request.setAttribute("arASMBALetters",arASMBALetters);
    	request.setAttribute(OILoginConstants.PAGENAME,"ASMView");

        //return mapping.findForward("populate");
    	return new ActionForward("/ASMView.do?hiddenAction=populate");
    }
    
    public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        String userId = (String) getSessionAttribute(request,OILoginConstants.USER_ID);
        
        ASMBALetters aASMBALetters = new ASMBALetters();
        ASMFormLetter aASMFormLetter = (ASMFormLetter) form;
        try
		{
        	PropertyUtils.copyProperties(aASMBALetters,aASMFormLetter);
		}
        catch(Exception e)
		{
			logger.error(e.getMessage());
		}

        OIResponseObject aOIResponseObject = new ASMBOView().deleteDraftLetter(userId,aASMBALetters);
        ArrayList messageList = (ArrayList) aOIResponseObject.getResponseObject(OILoginConstants.K_MESSAGE);
        String error = (String) aOIResponseObject.getResponseObject(OILoginConstants.K_ERROR);
        if (error != null)
        {
            request.setAttribute(OILoginConstants.K_ERROR,error);
            return new ActionForward(OILoginConstants.ERRORPAGE);
        }
        ArrayList arASMBALetters = (ArrayList) aOIResponseObject.getResponseObject("arASMBALetters");

        request.setAttribute("arASMBALetters",arASMBALetters);
    	request.setAttribute(OILoginConstants.PAGENAME,"ASMView");
        return mapping.findForward("populate");
    }

    public ActionForward detailLetter(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        String userId = (String) getSessionAttribute(request,OILoginConstants.USER_ID);
        String letterId = null;
        if (request.getParameter("letterId")!=null)
        {
        	letterId = (String) request.getParameter("letterId");
        }

        ASMFormLetter aASMFormLetter = new ASMFormLetter();
        if (letterId!=null && ! letterId.trim().equals(""))
        {
        	OIResponseObject aOIResponseObject = new ASMBOView().readDetailLetters(userId,letterId);
            ArrayList messageList = (ArrayList) aOIResponseObject.getResponseObject(OILoginConstants.K_MESSAGE);
            String error = (String) aOIResponseObject.getResponseObject(OILoginConstants.K_ERROR);
            if (error != null)
            {
                request.setAttribute(OILoginConstants.K_ERROR,error);
                return new ActionForward(OILoginConstants.ERRORPAGE);
            }
            ASMBALetters aASMBALetters = (ASMBALetters) aOIResponseObject.getResponseObject("aASMBALetters");
            try
			{
            	PropertyUtils.copyProperties(aASMFormLetter,aASMBALetters);
			}
            catch(Exception e)
			{
				logger.error(e.getMessage());
			}
        }
        request.setAttribute("ASMFormLetter",aASMFormLetter);
    	request.setAttribute(OILoginConstants.PAGENAME,"ASMDetailLetter");
        return mapping.findForward("detailLetter");
    }

}
