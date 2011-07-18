/*
 * File name	= ASMActionEditor.java
 * Package		= com.oifm.asm
 * Created on 	= Dec 19, 2005
 * Created by	= Oscar
 * Copyright	= Scandent Group
 *
 */

package com.oifm.asm;

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
import com.oifm.common.OIFunctionConstants;
import com.oifm.common.OIPageInfoBean;
import com.oifm.common.OIResponseObject;
import com.oifm.login.OILoginConstants;


public class ASMActionEditor extends OIBaseAction {

//	 Constants
	
	private final String WEB_DISPLAY = "WEB";
	private final String ADMIN_LIST = "ADMIN";
	private final String ADMIN_EDIT = "EDIT";
	private final String ADMIN_DO_CREATE = "DO_CREATE";
	private final String ADMIN_DO_EDIT = "DO_EDIT";
	private final String ADMIN_DO_DELETE = "DO_DELETE";
	
	// End of constants

	private static final Logger logger = Logger.getLogger(ASMActionEditor.class);
	
	public ActionForward doIt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String actionName) {
		String action = (actionName != null && actionName.length() != 0)? actionName:WEB_DISPLAY;
		HttpSession session = null;
		String forward = "";
        String redirect = "";
        String userID = "";
        String strRedirect = "";
        int pageSize = 0;
        int totalRecord = 0;
        ArrayList functions = null;
        boolean isASMRep = false;
        ASMBAEditor objBA = new ASMBAEditor();
        ASMFormEditor objForm = (ASMFormEditor) form;
        ASMBOEditor objBO = null;
        OIPageInfoBean pageInfo = null;
        OIResponseObject responseObject = null;
        
        try{
           
        	System.out.println("**** Ask Senior Mgt Start By Rakesh ****");
        	session = request.getSession();
        	String fromCategory=request.getParameter("fromCategory");
        	String id =request.getParameter("id");
        	System.out.println("id ::::" + id +"category  " + fromCategory);
        	functions = (ArrayList)getSessionAttribute(request, OILoginConstants.FUNCTION_LIST);
            userID = (String)getSessionAttribute(request, OILoginConstants.USER_ID);
            isASMRep = functions.contains(OIFunctionConstants.EDIT_POSTING);
            
                      
            System.out.println("**** Ask Senior Mgt Start2 By Rakesh ****");
           // if(isASMRep) {
            System.out.println("**** Ask Senior Mgt cOMMENTED By Rakesh ****");
            	objBO = new ASMBOEditor();
            	System.out.println("**** Inside isASMRep Ask Senior Mgt By Rakesh ****");
            	if (action.equals(WEB_DISPLAY)) {
            		System.out.println("**** Inside isASMRep action.equals(WEB_DISPLAY By Rakesh ****");
            		responseObject = objBO.getPageSize();
                    pageSize = ((Integer)responseObject.getResponseObject("PageSize")).intValue();
                    pageInfo = new OIPageInfoBean(objForm.getPageNo(), pageSize);
                	responseObject = objBO.getTotalRec();
                	totalRecord = ((Integer)responseObject.getResponseObject("TotalRecord")).intValue();
                	pageInfo.setTotalRec(totalRecord);
            		responseObject = objBO.getListPage(pageInfo.getStartRec(), pageInfo.getEndRec());
            		if (pageInfo.getNoOfPages() > 1)request.setAttribute("PageInfo", pageInfo);
            		request.setAttribute("list", responseObject.getResponseObject("List"));
            		request.setAttribute("pageName", "ASMEditor");
            		
            		responseObject = objBO.getModuleDescription();
            		request.setAttribute("ModuleDesc", responseObject.getResponseObject("ModuleDesc"));
            		
            		forward = "WebDisplay";
            		
            		// For right menu
            		ASMBACommon objCommonBA = new ASMBACommon();
            		ASMBOCommon objCommonBO = new ASMBOCommon();
            		String contentId=null;
            		if(request.getParameter("contentId")!=null){
            			contentId=request.getParameter("contentId");
            		}
            		
            		objCommonBO.process(objCommonBA, contentId);
            		request.setAttribute("TotRecSizRecLtr",""+objCommonBA.getTotRecLtr());
            		request.setAttribute("recent_letter",objCommonBA.getObjBV());
            		request.setAttribute("editors_note_id",objCommonBA.getHidEditorsNoteID());
            		request.setAttribute("editors_note",objCommonBA.getHidEditorsNote());
            		request.setAttribute("editors_note_posted_on",objCommonBA.getHidEditorsNotePostedOn());
            		
            	} else if (action.equals(ADMIN_LIST)) {
            		System.out.println("**** Inside isASMRep action.equals(ADMIN_LIST) By Rakesh ****");
            		responseObject = objBO.getList();
            		request.setAttribute("list", responseObject.getResponseObject("List"));
            		request.setAttribute("pageName", "ASMEditorList");
            		forward = "AdminPage";
            		
            	} else if (action.equals(ADMIN_EDIT)) {
            		System.out.println("**** Inside isASMRep (action.equals(ADMIN_EDIT) By Rakesh ****");
            		PropertyUtils.copyProperties(objBA, objForm);
            		System.out.println("Inside edit" +objBA.getId());
            		responseObject = objBO.getDetail(objBA);
            		objBA = (ASMBAEditor) responseObject.getResponseObject("Detail");
            		PropertyUtils.copyProperties(objForm, objBA);
            		
            		if(fromCategory!=null && fromCategory.equals("Y"))
            		{
            			//redirect = "/ASMEditor.do?fromCategory=Y&hiddenAction=" + ADMIN_EDIT;
            		}
            		
            		//else{
            		
            			request.setAttribute("pageName", "ASMEditorEdit");
            	
            			
                		forward = "AdminPage";
            		//}
            		
            		
            	} else if (action.equals(ADMIN_DO_CREATE)) {
            		System.out.println("**** Inside isASMRep (action.equals(ADMIN_DO_CREATE) By Rakesh ****");
            		PropertyUtils.copyProperties(objBA, objForm);
            		responseObject = objBO.create(objBA, userID);
            		if(responseObject.getResponseObject("error") != null) {
            			request.setAttribute("pageName", "ASMEditorEdit");
                		forward = "AdminPage";
            		} else {
            			redirect = "/ASMEditor.do?hiddenAction=" + ADMIN_LIST;
            		}
            		
            	} else if (action.equals(ADMIN_DO_EDIT)) {
            		System.out.println("**** Inside isASMRep (action.equals(ADMIN_DO_EDIT) By Rakesh ****");
            		//String getCategory = (String)request.getAttribute("fromCategory");
            		PropertyUtils.copyProperties(objBA, objForm);
            		responseObject = objBO.modify(objBA, userID);
            		
            		if(fromCategory!=null && fromCategory.equals("Y"))
            		{
            			redirect = "/ASMCategoriesOfEditorNotes.do?hiddenAction=Adminsearch&noteId="+id;
            			logger.info("Inside if");
            		} 
            		else
            		{  

                		if(responseObject.getResponseObject("error") != null) {
                			System.out.println("**** Inside isASMRep responseObject.getResponseObject By Rakesh ****");
                			request.setAttribute("pageName", "ASMEditorEdit");
                    		forward = "AdminPage";
                		} else {
                			redirect = "/ASMEditor.do?hiddenAction=" + ADMIN_LIST;
                		}
            		}
            		
            		
            	} else if (action.equals(ADMIN_DO_DELETE)) {
            		System.out.println("**** Inside isASMRep (action.equals(ADMIN_DO_DELETE) By Rakesh ****");
            		PropertyUtils.copyProperties(objBA, objForm);
            		responseObject = objBO.delete(objBA);
            		if(fromCategory!=null && fromCategory.equals("Y"))
            		{
            			System.out.println("**** Inside isASMRep (action.equals(ADMIN_DO_DELETE) fromCategory By Rakesh ****");
            			redirect = "/ASMCategoriesOfEditorNotes.do?hiddenAction=ADMIN";
            			logger.info("Inside if");
            		} 
            		else{
            			
            			if(responseObject.getResponseObject("error") != null) {
            				System.out.println("**** Inside isASMRep (action.equals(ADMIN_DO_DELETE) else By Rakesh ****");
            				request.setAttribute("pageName", "ASMEditorEdit");
            				forward = "AdminPage";
            			} else {
            				redirect = "/ASMEditor.do?hiddenAction=" + ADMIN_LIST;
            			}
            		 }
            	}
            	
            	//RAKESH COMMENTED
//           } else {
//        	   System.out.println("---Error page No Access 1--");
//              	strRedirect = ASMGlobals.ERR_REDIRECT + "?error=NoAccess";
//            }
        }catch(Exception e) {
        	logger.error("Action : TRY - CATCH : " + e);
        	System.out.println("---Inside Exception Error page --");
        	redirect = ASMGlobals.ERR_REDIRECT + "?error=OIDEFAULT";
        } finally {
        	System.out.println("--- In Finally  --");
            if(!forward.equals("") && responseObject.getResponseObject("error") != null && !responseObject.getResponseObject("error").equals("null") ) 
            	request.setAttribute("error", responseObject.getResponseObject("error"));
        }
        
        //if(!strRedirect.equals("")) 
        if(!redirect.equals("")){			// Modified by Kumaresan on Sep 23,2009
        	System.out.println("--The Redirect is not null "+redirect);
            return new ActionForward(redirect);
        }
        else{
        	System.out.println("--The Redirect is null & forward "+forward);
            return (mapping.findForward(forward));
        }
	}

}
