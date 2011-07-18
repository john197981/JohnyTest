package com.oifm.search;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

import com.oifm.base.OIBaseAction;
import com.oifm.common.OIPageInfoBean;
import com.oifm.common.OIResponseObject;
import com.oifm.consultation.OIConsultConstant;
import com.oifm.login.OILoginConstants;
import com.oifm.utility.OIDBRegistry;

public class OIActionAdvancedSearch extends OIBaseAction
{
    Logger logger = Logger.getLogger(OIActionAdvancedSearch.class.getName());
    private static final String POPULATE_ACTION = "populate";
    private static final String SEARCH_ACTION = "search";
    private static final String SEARCH_BY_USER_ACTION = "searchByUser";

    public ActionForward doIt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String actionName)
    {
        if (actionName!=null)
        {
	        if (actionName.equals(POPULATE_ACTION))
	        {
	            return populate(mapping, form, request, response);
	        }
	        if (actionName.equals(SEARCH_ACTION))
	        {
	            return search(mapping, form, request, response);
	        }
	        if (actionName.equals(SEARCH_BY_USER_ACTION))
	        {
	            return searchByUser(mapping, form, request, response);
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
        OIFormAdvancedSearch aOIFormAdvancedSearch = new OIFormAdvancedSearch();
        aOIFormAdvancedSearch.setSearchBy("Please Select...");
        aOIFormAdvancedSearch.setFindThreads("Please Select...");
        aOIFormAdvancedSearch.setForumSearchBy("Please Select...");
        aOIFormAdvancedSearch.setSurveySearchBy("Please Select...");
        aOIFormAdvancedSearch.setPaperSearchBy("Please Select...");
        
        aOIFormAdvancedSearch.addArSearchBy(new LabelValueBean("Please Select...",""));
        aOIFormAdvancedSearch.addArSearchBy(new LabelValueBean("Any of the word","Any of the word"));
        aOIFormAdvancedSearch.addArSearchBy(new LabelValueBean("By Entire Phrase","By Entire Phrase"));

        aOIFormAdvancedSearch.addArFindThreads(new LabelValueBean("Please Select...",""));
        aOIFormAdvancedSearch.addArFindThreads(new LabelValueBean("Atleast","Atleast"));
        aOIFormAdvancedSearch.addArFindThreads(new LabelValueBean("Exactly","Exactly"));

        aOIFormAdvancedSearch.addArForumSearchBy(new LabelValueBean("Please Select...",""));
        aOIFormAdvancedSearch.addArForumSearchBy(new LabelValueBean("All Posts","All Posts"));
        aOIFormAdvancedSearch.addArForumSearchBy(new LabelValueBean("From Last","From Last"));

        aOIFormAdvancedSearch.addArPaperSearchBy(new LabelValueBean("Please Select...",""));
        aOIFormAdvancedSearch.addArPaperSearchBy(new LabelValueBean("All Papers","All Papers"));
        aOIFormAdvancedSearch.addArPaperSearchBy(new LabelValueBean("From Last","From Last"));

        aOIFormAdvancedSearch.addArSurveySearchBy(new LabelValueBean("Please Select...",""));
        aOIFormAdvancedSearch.addArSurveySearchBy(new LabelValueBean("All Survey","All Survey"));
        aOIFormAdvancedSearch.addArSurveySearchBy(new LabelValueBean("From Last","From Last"));
        
        aOIFormAdvancedSearch.addArBlogSearchBy(new LabelValueBean("Please Select...",""));
        aOIFormAdvancedSearch.addArBlogSearchBy(new LabelValueBean("All ","All "));
        aOIFormAdvancedSearch.addArBlogSearchBy(new LabelValueBean("From Last","From Last"));

        request.setAttribute("AdvancedSearchForm",aOIFormAdvancedSearch);
        request.setAttribute(OILoginConstants.PAGENAME,"advancedSearch");
        return mapping.findForward(OIConsultConstant.POPULATE_CONSULTLISTING);
    }

    public ActionForward search(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        OIFormAdvancedSearch aOIFormAdvancedSearch = (OIFormAdvancedSearch) form;
        OIBASearchCriteria aOIBASearchCriteria = new OIBASearchCriteria();
        logger.info(aOIFormAdvancedSearch.getInForum());
        try
        {
            PropertyUtils.copyProperties(aOIBASearchCriteria, aOIFormAdvancedSearch);
            PropertyUtils.copyProperties(aOIFormAdvancedSearch,aOIBASearchCriteria);
        }
        catch(Exception e){}
        String userId = (String) getSessionAttribute(request,OILoginConstants.USER_ID);
        OIResponseObject aOIResponseObject = new OIBOAdvancedSearch().search(aOIBASearchCriteria,userId);
        
        ArrayList arOISearchResultBean = (ArrayList) aOIResponseObject.getResponseObject("arOISearchResultBean");
        OIPageInfoBean aOIPageInfoBean = (OIPageInfoBean) aOIResponseObject.getResponseObject("aSUOIPageInfoBean");
        if (aOIPageInfoBean!=null)
        {
	        ArrayList arPage = new ArrayList();
	        int start = aOIPageInfoBean.getCurrLinkStart();
	        for (int i=start;i<start + aOIPageInfoBean.getNoOfLinks();i++)
	        {
	            if (i<=aOIPageInfoBean.getNoOfPages())
	                arPage.add(i+"");
	        }
	        
	        if (aOIPageInfoBean.getNoOfPages()>=1)
	            request.setAttribute("totalPage",aOIPageInfoBean.getNoOfPages() + "");
	        else
	            request.setAttribute("totalPage",aOIPageInfoBean.getNoOfPages() + "");
	        request.setAttribute(OIConsultConstant.K_currentPage,aOIPageInfoBean.getPageNo() + "");
	
	        request.setAttribute(OIConsultConstant.K_pageNo,aOIPageInfoBean.getPageNo() + "");
	        request.setAttribute(OIConsultConstant.K_nextSet,aOIPageInfoBean.isNSet() + "");
	        request.setAttribute(OIConsultConstant.K_previousSet,aOIPageInfoBean.isPSet() + "");
	        request.setAttribute(OIConsultConstant.K_nextPage,aOIPageInfoBean.getNextSet() + "");
	        request.setAttribute(OIConsultConstant.K_previousPage,aOIPageInfoBean.getPrevSet() + "");
	        request.setAttribute(OIConsultConstant.K_arPage,arPage);
        }
        request.setAttribute(OILoginConstants.PAGENAME,"advancedSearchResult");
        request.setAttribute("arOISearchResultBean",arOISearchResultBean);
        request.setAttribute("AdvancedSearchForm",aOIFormAdvancedSearch);
        return mapping.findForward("search");
        //return mapping.findForward(OIConsultConstant.POPULATE_CONSULTLISTING);
    }

    public ActionForward searchByUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        OIFormAdvancedSearch aOIFormAdvancedSearch = (OIFormAdvancedSearch) form;
        OIBASearchCriteria aOIBASearchCriteria = new OIBASearchCriteria();
        logger.info(aOIFormAdvancedSearch.getInForum());
        try
        {
            PropertyUtils.copyProperties(aOIBASearchCriteria, aOIFormAdvancedSearch);
            PropertyUtils.copyProperties(aOIFormAdvancedSearch,aOIBASearchCriteria);
        }
        catch(Exception e){}
        String userId = (String) getSessionAttribute(request,OILoginConstants.USER_ID);
        OIResponseObject aOIResponseObject = new OIBOAdvancedSearch().searchByUser(aOIBASearchCriteria,userId);
        
        ArrayList arOISearchResultBean = (ArrayList) aOIResponseObject.getResponseObject("arOISearchResultBean");
        OIPageInfoBean aOIPageInfoBean = (OIPageInfoBean) aOIResponseObject.getResponseObject("aSUOIPageInfoBean");
        if (aOIPageInfoBean!=null)
        {
	        ArrayList arPage = new ArrayList();
	        int start = aOIPageInfoBean.getCurrLinkStart();
	        for (int i=start;i<start + aOIPageInfoBean.getNoOfLinks();i++)
	        {
	            if (i<=aOIPageInfoBean.getNoOfPages())
	                arPage.add(i+"");
	        }
	        
	        if (aOIPageInfoBean.getNoOfPages()>=1)
	            request.setAttribute("totalPage",aOIPageInfoBean.getNoOfPages() + "");
	        else
	            request.setAttribute("totalPage",aOIPageInfoBean.getNoOfPages() + "");
	        request.setAttribute(OIConsultConstant.K_currentPage,aOIPageInfoBean.getPageNo() + "");
	
	        request.setAttribute(OIConsultConstant.K_pageNo,aOIPageInfoBean.getPageNo() + "");
	        request.setAttribute(OIConsultConstant.K_nextSet,aOIPageInfoBean.isNSet() + "");
	        request.setAttribute(OIConsultConstant.K_previousSet,aOIPageInfoBean.isPSet() + "");
	        request.setAttribute(OIConsultConstant.K_nextPage,aOIPageInfoBean.getNextSet() + "");
	        request.setAttribute(OIConsultConstant.K_previousPage,aOIPageInfoBean.getPrevSet() + "");
	        request.setAttribute(OIConsultConstant.K_arPage,arPage);
        }
        request.setAttribute(OILoginConstants.PAGENAME,"advancedSearchResult");
        request.setAttribute("arOISearchResultBean",arOISearchResultBean);
        request.setAttribute("AdvancedSearchForm",aOIFormAdvancedSearch);
        return mapping.findForward("search");
        //return mapping.findForward(OIConsultConstant.POPULATE_CONSULTLISTING);
    }
}
