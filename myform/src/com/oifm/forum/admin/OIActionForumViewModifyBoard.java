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
import com.oifm.codemaster.OIBACodeMaster;
import com.oifm.common.OIResponseObject;
import com.oifm.consultation.OIConsultConstant;
import com.oifm.login.OILoginConstants;
import com.oifm.utility.OIDBRegistry;

public class OIActionForumViewModifyBoard extends OIBaseAction
{
    Logger logger = Logger.getLogger(OIActionForumViewModifyBoard.class.getName());
    
    private static final String POPULATE_ACTION = "populate";
    private static final String UPDATE_ACTION = "update";
    private static final String REMOVEUSER_ACTION = "removeUser";
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
	        if (actionName.equals(REMOVEUSER_ACTION))
	        {
	            return removeUser(mapping, form, request, response);
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
        String boardId = request.getParameter("boardId");
        if (boardId==null)
            boardId="0";
        OIResponseObject aOIResponseObject = new OIBOForumBoard().readBoard(Integer.parseInt(boardId));
        ArrayList messageList = (ArrayList) aOIResponseObject.getResponseObject(OILoginConstants.K_MESSAGE);
        String error = (String) aOIResponseObject.getResponseObject(OILoginConstants.K_ERROR);
        if (error != null)
        {
            request.setAttribute(OILoginConstants.K_ERROR,error);
            return new ActionForward(OILoginConstants.ERRORPAGE);
        }
        ArrayList arOIBAForumCategory = (ArrayList) aOIResponseObject.getResponseObject("arOIBAForumCategory");
        ArrayList arOIBACodeMaster = (ArrayList) aOIResponseObject.getResponseObject("arOIBACodeMaster");
        OIBAForumBoard aOIBAForumBoard = (OIBAForumBoard) aOIResponseObject.getResponseObject("aOIBAForumBoard");
        ArrayList arOIBAForumMembers = (ArrayList) aOIResponseObject.getResponseObject("arOIBAForumMembers");
        OIFormForumBoard aOIFormForumBoard = new OIFormForumBoard();
        
        String str=null;
        try
        {
            str = OIDBRegistry.getValues("OI.FORUM.CATEGORY.SELECT");
        }catch(Exception e){}
        aOIFormForumBoard.addArCategoryID(new LabelValueBean(str,""));
        for (int i=0;i<arOIBAForumCategory.size();i++)
        {
            OIBAForumCategory aOIBAForumCategory = (OIBAForumCategory) arOIBAForumCategory.get(i);
            aOIFormForumBoard.addArCategoryID(new LabelValueBean(aOIBAForumCategory.getName(),aOIBAForumCategory.getCategoryID()+""));
        }
        try
        {
            str = OIDBRegistry.getValues("OI.FORUM.DIVISION.SELECT");
        }catch(Exception e){}
        aOIFormForumBoard.addArDivisionId(new LabelValueBean(str,""));
        for (int i=0;i<arOIBACodeMaster.size();i++)
        {
            OIBACodeMaster aOIBACodeMaster = (OIBACodeMaster) arOIBACodeMaster.get(i);
            aOIFormForumBoard.addArDivisionId(new LabelValueBean(aOIBACodeMaster.getDescription(),aOIBACodeMaster.getValue()+""));
        }
        if (aOIBAForumBoard!=null)
        {
            aOIFormForumBoard.setBoardId(aOIBAForumBoard.getBoardId() + "");
            aOIFormForumBoard.setCategoryId(aOIBAForumBoard.getCategoryId() + "");
            aOIFormForumBoard.setDivisionId(aOIBAForumBoard.getDivisionId() + "");
            aOIFormForumBoard.setBoardName(aOIBAForumBoard.getBoardName());
            if (arOIBAForumMembers!=null)
            {
                for(int i=0;i<arOIBAForumMembers.size();i++)
                {
                    OIBAForumMembers aOIBAForumMembers = (OIBAForumMembers) arOIBAForumMembers.get(i);
                    aOIFormForumBoard.addArUserId(aOIBAForumMembers);
                }
            }
        }

        request.setAttribute("ForumBoardForm",aOIFormForumBoard);
        request.setAttribute(OILoginConstants.PAGENAME,"ForumBoard");
        return mapping.findForward(OIConsultConstant.POPULATE_CONSULTLISTING);
    }

    public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        OIFormForumBoard aOIFormForumBoard = (OIFormForumBoard) form;
        OIBAForumBoard aOIBAForumBoard = new OIBAForumBoard();
        if (aOIFormForumBoard.getBoardId()!=null && (!aOIFormForumBoard.getBoardId().trim().equals("")))
            aOIBAForumBoard.setBoardId(Integer.parseInt(aOIFormForumBoard.getBoardId()));
        aOIBAForumBoard.setCategoryId(Integer.parseInt(aOIFormForumBoard.getCategoryId()));
        aOIBAForumBoard.setDivisionId(Integer.parseInt(aOIFormForumBoard.getDivisionId()));
        aOIBAForumBoard.setBoardName(aOIFormForumBoard.getBoardName());
        OIResponseObject aOIResponseObject = new OIBOForumBoard().saveBoard(aOIBAForumBoard);
        
        ArrayList messageList = (ArrayList) aOIResponseObject.getResponseObject(OILoginConstants.K_MESSAGE);
        String error = (String) aOIResponseObject.getResponseObject(OILoginConstants.K_ERROR);
        if (error != null)
        {
            aOIResponseObject = new OIBOForumBoard().readBoard(0);
            ArrayList arOIBAForumCategory = (ArrayList) aOIResponseObject.getResponseObject("arOIBAForumCategory");
            ArrayList arOIBACodeMaster = (ArrayList) aOIResponseObject.getResponseObject("arOIBACodeMaster");
            String str=null;
            try
            {
                str = OIDBRegistry.getValues("OI.FORUM.CATEGORY.SELECT");
            }catch(Exception e){}
            aOIFormForumBoard.addArCategoryID(new LabelValueBean(str,""));
            for (int i=0;i<arOIBAForumCategory.size();i++)
            {
                OIBAForumCategory aOIBAForumCategory = (OIBAForumCategory) arOIBAForumCategory.get(i);
                aOIFormForumBoard.addArCategoryID(new LabelValueBean(aOIBAForumCategory.getName(),aOIBAForumCategory.getCategoryID()+""));
            }
            try
            {
                str = OIDBRegistry.getValues("OI.FORUM.DIVISION.SELECT");
            }catch(Exception e){}
            aOIFormForumBoard.addArDivisionId(new LabelValueBean(str,""));
            for (int i=0;i<arOIBACodeMaster.size();i++)
            {
                OIBACodeMaster aOIBACodeMaster = (OIBACodeMaster) arOIBACodeMaster.get(i);
                aOIFormForumBoard.addArDivisionId(new LabelValueBean(aOIBACodeMaster.getDescription(),aOIBACodeMaster.getValue()+""));
            }

            request.setAttribute(OILoginConstants.K_ERROR,error);
            request.setAttribute("ForumBoardForm",aOIFormForumBoard);
            request.setAttribute(OILoginConstants.PAGENAME,"ForumBoard");
            return mapping.findForward(OIConsultConstant.POPULATE_CONSULTLISTING);
        }

        return new ActionForward("/adminForumListing.do?hiddenAction=populate");
    }

    public ActionForward removeUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        OIFormForumBoard aOIFormForumBoard = (OIFormForumBoard) form;
        String user[] = request.getParameterValues("userId");
        OIBAForumBoard aOIBAForumBoard = new OIBAForumBoard();
        logger.info(aOIFormForumBoard.getBoardId());
        if (aOIFormForumBoard.getBoardId()!=null && (!aOIFormForumBoard.getBoardId().trim().equals("")))
            aOIBAForumBoard.setBoardId(Integer.parseInt(aOIFormForumBoard.getBoardId()));
        //aOIBAForumBoard.setCategoryId(Integer.parseInt(aOIFormForumBoard.getCategoryId()));
        //aOIBAForumBoard.setDivisionId(Integer.parseInt(aOIFormForumBoard.getDivisionId()));
        //aOIBAForumBoard.setBoardName(aOIFormForumBoard.getBoardName());
        OIResponseObject aOIResponseObject = new OIBOForumBoard().removeUserFromBoard(aOIBAForumBoard,user);

        ArrayList messageList = (ArrayList) aOIResponseObject.getResponseObject(OILoginConstants.K_MESSAGE);
        String error = (String) aOIResponseObject.getResponseObject(OILoginConstants.K_ERROR);
        if (error != null)
        {
            aOIResponseObject = new OIBOForumBoard().readBoard(0);
            ArrayList arOIBAForumCategory = (ArrayList) aOIResponseObject.getResponseObject("arOIBAForumCategory");
            ArrayList arOIBACodeMaster = (ArrayList) aOIResponseObject.getResponseObject("arOIBACodeMaster");
            String str=null;
            try
            {
                str = OIDBRegistry.getValues("OI.FORUM.CATEGORY.SELECT");
            }catch(Exception e){}
            aOIFormForumBoard.addArCategoryID(new LabelValueBean(str,""));
            for (int i=0;i<arOIBAForumCategory.size();i++)
            {
                OIBAForumCategory aOIBAForumCategory = (OIBAForumCategory) arOIBAForumCategory.get(i);
                aOIFormForumBoard.addArCategoryID(new LabelValueBean(aOIBAForumCategory.getName(),aOIBAForumCategory.getCategoryID()+""));
            }
            try
            {
                str = OIDBRegistry.getValues("OI.FORUM.DIVISION.SELECT");
            }catch(Exception e){}
            aOIFormForumBoard.addArDivisionId(new LabelValueBean(str,""));
            for (int i=0;i<arOIBACodeMaster.size();i++)
            {
                OIBACodeMaster aOIBACodeMaster = (OIBACodeMaster) arOIBACodeMaster.get(i);
                aOIFormForumBoard.addArDivisionId(new LabelValueBean(aOIBACodeMaster.getDescription(),aOIBACodeMaster.getValue()+""));
            }

            request.setAttribute(OILoginConstants.K_ERROR,error);
            request.setAttribute("ForumBoardForm",aOIFormForumBoard);
            request.setAttribute(OILoginConstants.PAGENAME,"ForumBoard");
            //return new ActionForward("/adminForumViewModifyBoardAction.do?hiddenAction=populate");
            return mapping.findForward(OIConsultConstant.POPULATE_CONSULTLISTING);
        }

        return new ActionForward("/adminForumViewModifyBoardAction.do?hiddenAction=populate");

    }
    
    public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        OIFormForumBoard aOIFormForumBoard = (OIFormForumBoard) form;
        OIBAForumBoard aOIBAForumBoard = new OIBAForumBoard();
        if (aOIFormForumBoard.getBoardId()!=null && (!aOIFormForumBoard.getBoardId().trim().equals("")))
            aOIBAForumBoard.setBoardId(Integer.parseInt(aOIFormForumBoard.getBoardId()));
        aOIBAForumBoard.setCategoryId(Integer.parseInt(aOIFormForumBoard.getCategoryId()));
        aOIBAForumBoard.setDivisionId(Integer.parseInt(aOIFormForumBoard.getDivisionId()));
        aOIBAForumBoard.setBoardName(aOIFormForumBoard.getBoardName());
        OIResponseObject aOIResponseObject = new OIBOForumBoard().deleteBoard(aOIBAForumBoard);

        ArrayList messageList = (ArrayList) aOIResponseObject.getResponseObject(OILoginConstants.K_MESSAGE);
        String error = (String) aOIResponseObject.getResponseObject(OILoginConstants.K_ERROR);
        if (error != null)
        {
            aOIResponseObject = new OIBOForumBoard().readBoard(aOIBAForumBoard.getBoardId());
            ArrayList arOIBAForumCategory = (ArrayList) aOIResponseObject.getResponseObject("arOIBAForumCategory");
            ArrayList arOIBACodeMaster = (ArrayList) aOIResponseObject.getResponseObject("arOIBACodeMaster");
            aOIBAForumBoard = (OIBAForumBoard) aOIResponseObject.getResponseObject("aOIBAForumBoard");
            ArrayList arOIBAForumMembers = (ArrayList) aOIResponseObject.getResponseObject("arOIBAForumMembers");
            String str=null;
            try
            {
                str = OIDBRegistry.getValues("OI.FORUM.CATEGORY.SELECT");
            }catch(Exception e){}
            aOIFormForumBoard.addArCategoryID(new LabelValueBean(str,""));
            for (int i=0;i<arOIBAForumCategory.size();i++)
            {
                OIBAForumCategory aOIBAForumCategory = (OIBAForumCategory) arOIBAForumCategory.get(i);
                aOIFormForumBoard.addArCategoryID(new LabelValueBean(aOIBAForumCategory.getName(),aOIBAForumCategory.getCategoryID()+""));
            }
            try
            {
                str = OIDBRegistry.getValues("OI.FORUM.DIVISION.SELECT");
            }catch(Exception e){}
            aOIFormForumBoard.addArDivisionId(new LabelValueBean(str,""));
            for (int i=0;i<arOIBACodeMaster.size();i++)
            {
                OIBACodeMaster aOIBACodeMaster = (OIBACodeMaster) arOIBACodeMaster.get(i);
                aOIFormForumBoard.addArDivisionId(new LabelValueBean(aOIBACodeMaster.getDescription(),aOIBACodeMaster.getValue()+""));
            }
            if (aOIBAForumBoard!=null)
            {
                aOIFormForumBoard.setBoardId(aOIBAForumBoard.getBoardId() + "");
                aOIFormForumBoard.setCategoryId(aOIBAForumBoard.getCategoryId() + "");
                aOIFormForumBoard.setDivisionId(aOIBAForumBoard.getDivisionId() + "");
                aOIFormForumBoard.setBoardName(aOIBAForumBoard.getBoardName());
                if (arOIBAForumMembers!=null)
                {
                    for(int i=0;i<arOIBAForumMembers.size();i++)
                    {
                        OIBAForumMembers aOIBAForumMembers = (OIBAForumMembers) arOIBAForumMembers.get(i);
                        aOIFormForumBoard.addArUserId(aOIBAForumMembers);
                    }
                }
            }

            request.setAttribute(OILoginConstants.K_ERROR,error);
            request.setAttribute("ForumBoardForm",aOIFormForumBoard);
            request.setAttribute(OILoginConstants.PAGENAME,"ForumBoard");
            return mapping.findForward(OIConsultConstant.POPULATE_CONSULTLISTING);
        }

        return new ActionForward("/adminForumListing.do?hiddenAction=populate");
    }
}
