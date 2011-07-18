package com.oifm.forum.admin;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

import com.oifm.base.OIBaseAction;
import com.oifm.common.OIResponseObject;
import com.oifm.consultation.OIConsultConstant;
import com.oifm.login.OILoginConstants;
import com.oifm.utility.OIDBRegistry;

public class OIActionForumCreateTopic extends OIBaseAction
{
    Logger logger = Logger.getLogger(OIActionForumCreateTopic.class.getName());
    private static final String POPULATE_ACTION = "populate";
    private static final String SAVE_ACTION = "save";

    public ActionForward doIt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String actionName)
    {
        if (actionName!=null)
        {
	        if (actionName.equals(POPULATE_ACTION))
	        {
	            return populate(mapping, form, request, response);
	        }
	        if (actionName.equals(SAVE_ACTION))
	        {
	            return save(mapping, form, request, response);
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
        String roleId = (String) getSessionAttribute(request,OILoginConstants.ROLE_ID);
        String userId = (String) getSessionAttribute(request,OILoginConstants.USER_ID);
        if (roleId.equals("ADMIN"))
        {
            userId="";
        }
        int categoryId =0;
        if (request.getParameter("categoryId")!=null)
            categoryId = Integer.parseInt(request.getParameter("categoryId"));
            
        OIResponseObject aOIResponseObject = new OIBOForumTopic().readTopic(0,categoryId,0,userId);
        ArrayList messageList = (ArrayList) aOIResponseObject.getResponseObject(OILoginConstants.K_MESSAGE);
        String error = (String) aOIResponseObject.getResponseObject(OILoginConstants.K_ERROR);
        if (error != null)
        {
            request.setAttribute(OILoginConstants.K_ERROR,error);
            return new ActionForward(OILoginConstants.ERRORPAGE);
        }
        ArrayList arOIBAForumCategory = (ArrayList) aOIResponseObject.getResponseObject("arOIBAForumCategory");
        ArrayList arOIBAForumBoard = (ArrayList) aOIResponseObject.getResponseObject("arOIBAForumBoard");

        OIFormForumTopic aOIFormForumTopic = new OIFormForumTopic();
        if (categoryId!=0)
            aOIFormForumTopic.setCategoryId(categoryId + "");
        String str=null;
        try
        {
            str = OIDBRegistry.getValues("OI.FORUM.CATEGORY.SELECT");
        }catch(Exception e){}
        aOIFormForumTopic.addArCategoryID(new LabelValueBean(str,""));
        if (arOIBAForumCategory!=null)
        {
	        for (int i=0;i<arOIBAForumCategory.size();i++)
	        {
	            OIBAForumCategory aOIBAForumCategory = (OIBAForumCategory) arOIBAForumCategory.get(i);
	            aOIFormForumTopic.addArCategoryID(new LabelValueBean(aOIBAForumCategory.getName(),aOIBAForumCategory.getCategoryID()+""));
	        }
        }
        try
        {
            str = OIDBRegistry.getValues("OI.FORUM.BOARD.SELECT");
        }catch(Exception e){}
        aOIFormForumTopic.addArBoardId(new LabelValueBean(str,""));
        if (arOIBAForumBoard!=null)
        {
	        for (int i=0;i<arOIBAForumBoard.size();i++)
	        {
	            OIBAForumBoard aOIBAForumBoard = (OIBAForumBoard) arOIBAForumBoard.get(i);
	            aOIFormForumTopic.addArBoardId(new LabelValueBean(aOIBAForumBoard.getBoardName(),aOIBAForumBoard.getBoardId()+""));
	        }
        }
        
        request.setAttribute("ForumTopicForm",aOIFormForumTopic);
        request.setAttribute(OILoginConstants.PAGENAME,"ForumTopic");
        return mapping.findForward(OIConsultConstant.POPULATE_CONSULTLISTING);
    }
    
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        String roleId = (String) getSessionAttribute(request,OILoginConstants.ROLE_ID);
        String userId = (String) getSessionAttribute(request,OILoginConstants.USER_ID);
        if (roleId.equals("ADMIN"))
        {
            userId="";
        }
        OIFormForumTopic aOIFormForumTopic = (OIFormForumTopic) form;
        OIBAForumTopic aOIBAForumTopic = new OIBAForumTopic();
        if (aOIFormForumTopic.getTopicId()!=null && (!aOIFormForumTopic.getTopicId().trim().equals("")))
            aOIBAForumTopic.setTopicId(Integer.parseInt(aOIFormForumTopic.getTopicId()));
        aOIBAForumTopic.setBoardId(Integer.parseInt(aOIFormForumTopic.getBoardId()));
        aOIBAForumTopic.setTopicName(aOIFormForumTopic.getTopicName());
        aOIBAForumTopic.setCreatedBy((String) getSessionAttribute(request,OILoginConstants.USER_ID));
        aOIBAForumTopic.setTopicDesc(aOIFormForumTopic.getTopicDesc());
        if (aOIFormForumTopic.getModRequired()!= null && aOIFormForumTopic.getModRequired().equals("on"))
        {
            aOIBAForumTopic.setModReq("Y");
        }
        else
        {
            aOIBAForumTopic.setModReq("N");
        }

        OIResponseObject aOIResponseObject = new OIBOForumTopic().saveTopic(aOIBAForumTopic);

        ArrayList messageList = (ArrayList) aOIResponseObject.getResponseObject(OILoginConstants.K_MESSAGE);
        String error = (String) aOIResponseObject.getResponseObject(OILoginConstants.K_ERROR);
        if (error != null)
        {
            request.setAttribute(OILoginConstants.K_ERROR,error);
            int categoryId =0;
            if (aOIFormForumTopic.getArCategoryID()!=null)
                categoryId = Integer.parseInt(aOIFormForumTopic.getCategoryId());
            aOIResponseObject = new OIBOForumTopic().readTopic(0,categoryId,0,userId);

            ArrayList arOIBAForumCategory = (ArrayList) aOIResponseObject.getResponseObject("arOIBAForumCategory");
            ArrayList arOIBAForumBoard = (ArrayList) aOIResponseObject.getResponseObject("arOIBAForumBoard");

            
            String str=null;
            try
            {
                str = OIDBRegistry.getValues("OI.FORUM.CATEGORY.SELECT");
            }catch(Exception e){}
            aOIFormForumTopic.addArCategoryID(new LabelValueBean(str,""));
            if (arOIBAForumCategory!=null)
            {
    	        for (int i=0;i<arOIBAForumCategory.size();i++)
    	        {
    	            OIBAForumCategory aOIBAForumCategory = (OIBAForumCategory) arOIBAForumCategory.get(i);
    	            aOIFormForumTopic.addArCategoryID(new LabelValueBean(aOIBAForumCategory.getName(),aOIBAForumCategory.getCategoryID()+""));
    	        }
            }
            try
            {
                str = OIDBRegistry.getValues("OI.FORUM.BOARD.SELECT");
            }catch(Exception e){}
            aOIFormForumTopic.addArBoardId(new LabelValueBean(str,""));
            if (arOIBAForumBoard!=null)
            {
    	        for (int i=0;i<arOIBAForumBoard.size();i++)
    	        {
    	            OIBAForumBoard aOIBAForumBoard = (OIBAForumBoard) arOIBAForumBoard.get(i);
    	            aOIFormForumTopic.addArBoardId(new LabelValueBean(aOIBAForumBoard.getBoardName(),aOIBAForumBoard.getBoardId()+""));
    	        }
            }
            aOIFormForumTopic.setTopicId(null);
            request.setAttribute("ForumTopicForm",aOIFormForumTopic);
            request.setAttribute(OILoginConstants.PAGENAME,"ForumTopic");
            return mapping.findForward(OIConsultConstant.POPULATE_CONSULTLISTING);
        }
        return new ActionForward("/adminForumListing.do?hiddenAction=populate");
    }
}
