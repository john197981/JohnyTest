package com.oifm.forum.admin;

import java.io.PrintWriter;
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
import com.oifm.forum.OIBAThread;
import com.oifm.login.OILoginConstants;
import com.oifm.utility.OIDBRegistry;

public class OIActionForumMoveThread extends OIBaseAction
{
    Logger logger = Logger.getLogger(OIActionForumMoveThread.class.getName());
    private static final String POPULATE_ACTION = "populate";
    private static final String SAVE_ACTION = "save";
    private static final String BOARD_ACTION = "board";
    private static final String TOPIC_ACTION = "topic";
    private static final String THREAD_ACTION = "thread";

    public ActionForward doIt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String actionName)
    {
        if (actionName!=null)
        {
	        if (actionName.equals(POPULATE_ACTION))
	        {
	            return populate(mapping, form, request, response);
	        }
	        else if (actionName.equals(SAVE_ACTION))
	        {
	            return save(mapping, form, request, response);
	        }
	        else if (actionName.equals(BOARD_ACTION))
	        {
	            return populateBoard(mapping, form, request, response);
	        }
	        else if (actionName.equals(TOPIC_ACTION))
	        {
	            return populateTopic(mapping, form, request, response);
	        }
	        else if (actionName.equals(THREAD_ACTION))
	        {
	            return populateThread(mapping, form, request, response);
	        }
        }else{
        	return populate(mapping, form, request, response);
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
    
    public ActionForward populateBoard(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
    	//System.out.println("Start Ajax populate Board");
        String roleId = (String) getSessionAttribute(request,OILoginConstants.ROLE_ID);
        String userId = (String) getSessionAttribute(request,OILoginConstants.USER_ID);
        if (roleId.equals("ADMIN"))
        {
            userId="";
        }
        int categoryId =0;
        if (request.getParameter("categoryId")!=null)
            categoryId = Integer.parseInt(request.getParameter("categoryId"));
            
        OIResponseObject aOIResponseObject = new OIBOForumMoveThread().readBoard(categoryId,userId);
//        ArrayList messageList = (ArrayList) aOIResponseObject.getResponseObject(OILoginConstants.K_MESSAGE);
        String error = (String) aOIResponseObject.getResponseObject(OILoginConstants.K_ERROR);
        if (error != null)
        {
            request.setAttribute(OILoginConstants.K_ERROR,error);
            return new ActionForward(OILoginConstants.ERRORPAGE);
        }
        ArrayList arOIBAForumBoard = (ArrayList) aOIResponseObject.getResponseObject("arOIBAForumBoard");

        StringBuffer sbBoardOpt = new StringBuffer();
        
        if(request.getParameter("boardType")!=null && request.getParameter("boardType").equalsIgnoreCase("desBoardOpt")){
        	sbBoardOpt.append("<select name=\"desBoardId\" class=\"Text\" onchange=\"javascript:loadTopic('des');\">");
        }else{
        	sbBoardOpt.append("<select name=\"boardId\" class=\"Text\" onchange=\"javascript:loadTopic();\">");
        }
        
        sbBoardOpt.append("<option value=''>"); 
        sbBoardOpt.append("Please select...</option>");
        if (arOIBAForumBoard!=null)
        {
	        for (int i=0;i<arOIBAForumBoard.size();i++)
	        {
	            OIBAForumBoard aOIBAForumBoard = (OIBAForumBoard) arOIBAForumBoard.get(i);
	            sbBoardOpt.append("<option value='"+aOIBAForumBoard.getBoardId()+"'>"); 
	            sbBoardOpt.append(aOIBAForumBoard.getBoardName()+"</option>");
	        }
        }
        sbBoardOpt.append("</select>");
        
        try{
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(sbBoardOpt.toString());
        out.flush();
        }catch(Exception e){
        	e.printStackTrace();
        }
        //System.out.println("finish Ajax populate board");
        return null;
    }
    
    public ActionForward populateTopic(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
    	//System.out.println("Start Ajax populate Topic");
        
        int boardId =0;
        if (request.getParameter("boardId")!=null)
        	boardId = Integer.parseInt(request.getParameter("boardId"));
            
        OIResponseObject aOIResponseObject = new OIBOForumMoveThread().readTopic(boardId);
//        ArrayList messageList = (ArrayList) aOIResponseObject.getResponseObject(OILoginConstants.K_MESSAGE);
        String error = (String) aOIResponseObject.getResponseObject(OILoginConstants.K_ERROR);
        if (error != null)
        {
            request.setAttribute(OILoginConstants.K_ERROR,error);
            return new ActionForward(OILoginConstants.ERRORPAGE);
        }
        ArrayList arOIBAForumTopic = (ArrayList) aOIResponseObject.getResponseObject("arOIBAForumTopic");

        StringBuffer sbBoardOpt = new StringBuffer();
        
        if(request.getParameter("topicType")!=null && request.getParameter("topicType").equalsIgnoreCase("desTopicOpt")){
        	sbBoardOpt.append("<select name=\"desTopicId\" class=\"Text\">");
        }else{
        	sbBoardOpt.append("<select name=\"topicId\" class=\"Text\" onchange=\"javascript:loadThread();\">");
        }
       
        sbBoardOpt.append("<option value=''>"); 
        sbBoardOpt.append("Please select...</option>");
        if (arOIBAForumTopic!=null)
        {
	        for (int i=0;i<arOIBAForumTopic.size();i++)
	        {
	            OIBAForumTopic aOIBAForumTopic = (OIBAForumTopic) arOIBAForumTopic.get(i);
	            sbBoardOpt.append("<option value='"+aOIBAForumTopic.getTopicId()+"'>"); 
	            sbBoardOpt.append(aOIBAForumTopic.getTopicName()+"</option>");
	        }
        }
        sbBoardOpt.append("</select>");
        
        try{
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(sbBoardOpt.toString());
        out.flush();
        }catch(Exception e){
        	e.printStackTrace();
        }
        //System.out.println("Finish Ajax populate Topic");
        return null;
    }
    
    public ActionForward populateThread(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
    	//System.out.println("Start Ajax populate Thread");
        
        int topicId =0;
        if (request.getParameter("topicId")!=null)
        	topicId = Integer.parseInt(request.getParameter("topicId"));
            
        OIResponseObject aOIResponseObject = new OIBOForumMoveThread().readThread(topicId);
//        ArrayList messageList = (ArrayList) aOIResponseObject.getResponseObject(OILoginConstants.K_MESSAGE);
        String error = (String) aOIResponseObject.getResponseObject(OILoginConstants.K_ERROR);
        if (error != null)
        {
            request.setAttribute(OILoginConstants.K_ERROR,error);
            return new ActionForward(OILoginConstants.ERRORPAGE);
        }
        ArrayList arOIBAForumThread= (ArrayList) aOIResponseObject.getResponseObject("arOIBAForumThread");

        StringBuffer sbBoardOpt = new StringBuffer();
        
        sbBoardOpt.append("<select name=\"threadId\" class=\"Text\" length=\"30\" size=\"4\" multiple=\"true\">");
       
        sbBoardOpt.append("<option value='All'>"); 
        sbBoardOpt.append("All</option>");
        if (arOIBAForumThread!=null)
        {
	        for (int i=0;i<arOIBAForumThread.size();i++)
	        {
	        	OIBAThread aOIBAForumThread = (OIBAThread) arOIBAForumThread.get(i);
	            sbBoardOpt.append("<option value='"+aOIBAForumThread.getStrThreadId()+"'>"); 
	            sbBoardOpt.append(aOIBAForumThread.getStrTitle()+"</option>");
	        }
        }
        sbBoardOpt.append("</select>");
        
        try{
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(sbBoardOpt.toString());
        out.flush();
        }catch(Exception e){
        	e.printStackTrace();
        }
        //System.out.println("Finish Ajax populate Topic");
        return null;
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

        OIFormForumMoveThread aOIFormForumMoveThread = new OIFormForumMoveThread();
        if (categoryId!=0)
            aOIFormForumMoveThread.setCategoryId(categoryId + "");
        String str=null;
        try
        {
            str = OIDBRegistry.getValues("OI.FORUM.CATEGORY.SELECT");
        }catch(Exception e){}
        aOIFormForumMoveThread.addArCategoryID(new LabelValueBean(str,""));
        if (arOIBAForumCategory!=null)
        {
	        for (int i=0;i<arOIBAForumCategory.size();i++)
	        {
	            OIBAForumCategory aOIBAForumCategory = (OIBAForumCategory) arOIBAForumCategory.get(i);
	            aOIFormForumMoveThread.addArCategoryID(new LabelValueBean(aOIBAForumCategory.getName(),aOIBAForumCategory.getCategoryID()+""));
	        }
        }
        /*try
        {
            str = OIDBRegistry.getValues("OI.FORUM.BOARD.SELECT");
        }catch(Exception e){}
        aOIFormForumMoveThread.addArBoardId(new LabelValueBean(str,""));
        if (arOIBAForumBoard!=null)
        {
	        for (int i=0;i<arOIBAForumBoard.size();i++)
	        {
	            OIBAForumBoard aOIBAForumBoard = (OIBAForumBoard) arOIBAForumBoard.get(i);
	            aOIFormForumMoveThread.addArBoardId(new LabelValueBean(aOIBAForumBoard.getBoardName(),aOIBAForumBoard.getBoardId()+""));
	        }
        }*/
        if(request.getAttribute("saveFlag")!=null)
        	request.setAttribute("saveFlag", request.getAttribute("saveFlag"));
        aOIFormForumMoveThread.setCategoryId(null);
        request.setAttribute("ForumMoveThreadForm",aOIFormForumMoveThread);
        request.setAttribute(OILoginConstants.PAGENAME,"ForumMoveThread");
        
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
        OIFormForumMoveThread aOIFormForumMoveThread = (OIFormForumMoveThread) form;
        OIBAForumMoveThread aOIBAMoveThread = new OIBAForumMoveThread();
        
        aOIBAMoveThread.setDesTopicId(aOIFormForumMoveThread.getDesTopicId());
        aOIBAMoveThread.setThreadId(aOIFormForumMoveThread.getThreadId());
        aOIBAMoveThread.setTopicId(aOIFormForumMoveThread.getTopicId());
        OIResponseObject aOIResponseObject = new OIBOForumMoveThread().saveThread(aOIBAMoveThread);
        
        String saveFlag = (String)aOIResponseObject.getResponseObject("saveFlag");

        ArrayList messageList = (ArrayList) aOIResponseObject.getResponseObject(OILoginConstants.K_MESSAGE);
        String error = (String) aOIResponseObject.getResponseObject(OILoginConstants.K_ERROR);
        
        if (error != null)
        {
            request.setAttribute(OILoginConstants.K_ERROR,error);
            int categoryId =0;
            if (aOIFormForumMoveThread.getArCategoryID()!=null)
                categoryId = Integer.parseInt(aOIFormForumMoveThread.getCategoryId());
            aOIResponseObject = new OIBOForumTopic().readTopic(0,categoryId,0,userId);

            ArrayList arOIBAForumCategory = (ArrayList) aOIResponseObject.getResponseObject("arOIBAForumCategory");
            
            String str=null;
            try
            {
                str = OIDBRegistry.getValues("OI.FORUM.CATEGORY.SELECT");
            }catch(Exception e){}
            aOIFormForumMoveThread.addArCategoryID(new LabelValueBean(str,""));
            
            if (arOIBAForumCategory!=null)
            {
    	        for (int i=0;i<arOIBAForumCategory.size();i++)
    	        {
    	            OIBAForumCategory aOIBAForumCategory = (OIBAForumCategory) arOIBAForumCategory.get(i);
    	            aOIFormForumMoveThread.addArCategoryID(new LabelValueBean(aOIBAForumCategory.getName(),aOIBAForumCategory.getCategoryID()+""));
    	        }
            }
            
            aOIFormForumMoveThread.setCategoryId(null);
            
            request.setAttribute("ForumMoveThreadForm",aOIFormForumMoveThread);
            request.setAttribute(OILoginConstants.PAGENAME,"ForumMoveThread");
            
            return mapping.findForward(OIConsultConstant.POPULATE_CONSULTLISTING);
        }
        request.setAttribute("saveFlag", saveFlag);
        return new ActionForward("/adminForumMoveThreadAction.do?hiddenAction=populate");
    }
}
