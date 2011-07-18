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

public class OIActionForumViewModifyTopic extends OIBaseAction
{
    Logger logger = Logger.getLogger(OIActionForumViewModifyTopic.class.getName());
    private static final String POPULATE_ACTION = "populate";
    private static final String UPDATE_ACTION = "update";
    private static final String DELETE_ACTION = "delete";

    public ActionForward doIt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String actionName)
    {
        if (actionName!=null)
        {
	        if (actionName.equals(POPULATE_ACTION))
	        {
	            return populate(mapping, form, request, response);
	        }
	        if (actionName.equals(UPDATE_ACTION))
	        {
	            return update(mapping, form, request, response);
	        }
	        if (actionName.equals(DELETE_ACTION))
	        {
	            return delete(mapping, form, request, response);
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
        int topicId =0,categoryId=0;
        if (request.getParameter("topicId")!=null)
            topicId = Integer.parseInt(request.getParameter("topicId"));

        if ((request.getParameter("flag")!=null) && ((String) request.getParameter("flag")).equals("T"))
            categoryId = Integer.parseInt(((OIFormForumTopic) form).getCategoryId());
        OIResponseObject aOIResponseObject = new OIBOForumTopic().readTopic(0,categoryId,topicId,userId);
        ArrayList messageList = (ArrayList) aOIResponseObject.getResponseObject(OILoginConstants.K_MESSAGE);
        String error = (String) aOIResponseObject.getResponseObject(OILoginConstants.K_ERROR);
        if (error != null)
        {
            request.setAttribute(OILoginConstants.K_ERROR,error);
            return new ActionForward(OILoginConstants.ERRORPAGE);
        }
        ArrayList arOIBAForumCategory = (ArrayList) aOIResponseObject.getResponseObject("arOIBAForumCategory");
        ArrayList arOIBAForumBoard = (ArrayList) aOIResponseObject.getResponseObject("arOIBAForumBoard");
        OIBAForumTopic aOIBAForumTopic = (OIBAForumTopic) aOIResponseObject.getResponseObject("aOIBAForumTopic");
        OIBAForumBoard aOIBAForumBoard = (OIBAForumBoard) aOIResponseObject.getResponseObject("aOIBAForumBoard");

        OIFormForumTopic aOIFormForumTopic = new OIFormForumTopic();
        if (aOIBAForumBoard != null && aOIBAForumBoard.getCategoryId()!=0 && (request.getParameter("flag")==null))
            aOIFormForumTopic.setCategoryId(aOIBAForumBoard.getCategoryId() + "");
        else if ((request.getParameter("flag")!=null) && ((String) request.getParameter("flag")).equals("T"))
            aOIFormForumTopic.setCategoryId(((OIFormForumTopic) form).getCategoryId());

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
        if (aOIBAForumBoard!=null && aOIBAForumBoard.getBoardId()!=0 && (request.getParameter("flag")==null))
            aOIFormForumTopic.setBoardId(aOIBAForumBoard.getBoardId() + "");
        else if ((request.getParameter("flag")!=null) && ((String) request.getParameter("flag")).equals("T"))
            aOIFormForumTopic.setBoardId(((OIFormForumTopic) form).getBoardId());
        try
        {
            str = OIDBRegistry.getValues("OI.FORUM.BOARD.SELECT");
        }catch(Exception e){}
        aOIFormForumTopic.addArBoardId(new LabelValueBean(str,""));
        if (arOIBAForumBoard!=null)
        {
	        for (int i=0;i<arOIBAForumBoard.size();i++)
	        {
	            OIBAForumBoard aOIBAForumBoard1 = (OIBAForumBoard) arOIBAForumBoard.get(i);
	            aOIFormForumTopic.addArBoardId(new LabelValueBean(aOIBAForumBoard1.getBoardName(),aOIBAForumBoard1.getBoardId()+""));
	        }
        }
        aOIFormForumTopic.setTopicName(aOIBAForumTopic.getTopicName());
        aOIFormForumTopic.setTopicId(aOIBAForumTopic.getTopicId() + "");
        aOIFormForumTopic.setModRequired(aOIBAForumTopic.getModReq());
        aOIFormForumTopic.setTopicDesc(aOIBAForumTopic.getTopicDesc());
        if (aOIBAForumTopic.getModReq()!= null && aOIBAForumTopic.getModReq().equals("Y"))
        {
            aOIFormForumTopic.setModRequired("on");
        }
        else
        {
            aOIFormForumTopic.setModRequired("off");
        }

        request.setAttribute("ForumTopicForm",aOIFormForumTopic);
        request.setAttribute(OILoginConstants.PAGENAME,"ForumTopic");
        return mapping.findForward(OIConsultConstant.POPULATE_CONSULTLISTING);
    }

    public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
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
        aOIBAForumTopic.setTopicDesc(aOIFormForumTopic.getTopicDesc());
        aOIBAForumTopic.setCreatedBy((String) getSessionAttribute(request,OILoginConstants.USER_ID));
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
            
            request.setAttribute("ForumTopicForm",aOIFormForumTopic);
            return new ActionForward("/adminForumViewModifyTopicAction.do?hiddenAction=populate");
        }
        return new ActionForward("/adminForumListing.do?hiddenAction=populate");
    }
    public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
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
        if (aOIFormForumTopic.getModRequired()!= null && aOIFormForumTopic.getModRequired().equals("on"))
        {
            aOIBAForumTopic.setModReq("Y");
        }
        else
        {
            aOIBAForumTopic.setModReq("N");
        }
        
        OIResponseObject aOIResponseObject = new OIBOForumTopic().deleteTopic(aOIBAForumTopic);

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
            
            request.setAttribute("ForumTopicForm",aOIFormForumTopic);
            request.setAttribute(OILoginConstants.PAGENAME,"ForumTopic");
            return mapping.findForward(OIConsultConstant.POPULATE_CONSULTLISTING);
        }

        return new ActionForward("/adminForumListing.do?hiddenAction=populate");
    }
}
