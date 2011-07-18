package com.oifm.forum.admin;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.oifm.base.OIBaseAction;
import com.oifm.common.OIResponseObject;
import com.oifm.login.OILoginConstants;
import com.oifm.utility.DateUtility;
import com.oifm.utility.OIDBRegistry;

public class OIActionForumListing extends OIBaseAction 
{
    Logger logger = Logger.getLogger(OIActionForumListing.class.getName());
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
        catch(Exception e){}
        request.setAttribute(OILoginConstants.K_ERROR,error);
        return new ActionForward(OILoginConstants.ERRORPAGE);
    }
    
    public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        String userId = (String) getSessionAttribute(request,OILoginConstants.USER_ID);
        OIResponseObject aOIResponseObject = new OIBOForumListing().readForumList(userId);
        ArrayList messageList = (ArrayList) aOIResponseObject.getResponseObject(OILoginConstants.K_MESSAGE);
        String error = (String) aOIResponseObject.getResponseObject(OILoginConstants.K_ERROR);
        if (error != null)
        {
            request.setAttribute(OILoginConstants.K_ERROR,error);
            return new ActionForward(OILoginConstants.ERRORPAGE);
        }

        ArrayList arOIBVForumList = (ArrayList) aOIResponseObject.getResponseObject("arOIBVForumList");
        
        OIFormForumListing aOIFormForumListing = new OIFormForumListing();
        OIFormForumListingHelper1 aOIFormForumListingHelper1 = null;
        OIFormForumListingHelper2 aOIFormForumListingHelper2 = null;
        OIFormForumListingHelper3 aOIFormForumListingHelper3 = null;
        if (arOIBVForumList!=null)
        {
            int categoryId=0;
            int boardId=0;
            int topicId=0;
            for(int i=0;i<arOIBVForumList.size();i++)
            {
                OIBVForumList aOIBVForumList = (OIBVForumList) arOIBVForumList.get(i);
                if (categoryId!=aOIBVForumList.getCategoryId())
                {
                    aOIFormForumListingHelper1 = new OIFormForumListingHelper1();
                    categoryId=aOIBVForumList.getCategoryId();
                    aOIFormForumListingHelper1.setCategoryId(aOIBVForumList.getCategoryId()+"");
                    aOIFormForumListingHelper1.setCategoryName(aOIBVForumList.getCategoryName());
                    aOIFormForumListing.addArOIFormForumListingHelper1(aOIFormForumListingHelper1);
                }
                if (boardId!=aOIBVForumList.getBoardId())
                {
                    aOIFormForumListingHelper2 = new OIFormForumListingHelper2();
                    boardId=aOIBVForumList.getBoardId();
                    aOIFormForumListingHelper2.setBoardId(aOIBVForumList.getBoardId()+"");
                    aOIFormForumListingHelper2.setBoardName(aOIBVForumList.getBoardName());
                    aOIFormForumListingHelper2.setDivision(aOIBVForumList.getDivision());
                    aOIFormForumListingHelper2.setLinkFlag(aOIBVForumList.getLinkFlag());
                    aOIFormForumListingHelper1.addArOIFormForumListingHelper2(aOIFormForumListingHelper2);
                }
                if (topicId!=aOIBVForumList.getTopicId())
                {
                    aOIFormForumListingHelper3 = new OIFormForumListingHelper3();
                    topicId=aOIBVForumList.getTopicId();
                    aOIFormForumListingHelper3.setTopicId(aOIBVForumList.getTopicId()+"");
                    aOIFormForumListingHelper3.setTopicName(aOIBVForumList.getTopicName());
                    try
                    {
                        if (aOIBVForumList.getCreatedOn()!=null)
                            aOIFormForumListingHelper3.setCreatedOn(DateUtility.getMMDDYYYYStringFromDate(aOIBVForumList.getCreatedOn()));
                    }catch(Exception e){}
                    aOIFormForumListingHelper2.addArOIFormForumListingHelper3(aOIFormForumListingHelper3);
                }
            }
        }
        
        request.setAttribute("ForumListing",aOIFormForumListing);
        request.setAttribute(OILoginConstants.PAGENAME,"adminForumListing");
        return mapping.findForward(POPULATE_ACTION);
    }
}
