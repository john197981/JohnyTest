/*
 * File name	= OIActionSimpleSearch.java
 * Package		= com.oifm.search
 * Created on 	= Aug 14, 2005
 * Created by	= Oscar
 * Copyright	= Scandent Group
 *
 */

package com.oifm.search;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.oifm.base.OIBaseAction;
import com.oifm.common.OIPageInfoBean;
import com.oifm.common.OIResponseObject;
import com.oifm.login.OILoginConstants;


public class OIActionSimpleSearch extends OIBaseAction {

	private static Logger logger = Logger.getLogger(OIActionSimpleSearch.class);
	
	public ActionForward doIt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String actionName) {
		String strAction = (actionName.trim() != null || actionName.trim().equals(""))? actionName:OISearchConstants.SEARCH_FORUM;
        String strForward = "";
        String strRedirect = "";
        String strUserId = "";
        String query = null;
        int pageSize = 0;
        int totalRecord = 0;
        OIBASimpleSearch objBASearch = new OIBASimpleSearch();
        OIFormSimpleSearch objFormSearch= (OIFormSimpleSearch) form;
        OIBOSearch objBOSearch = null;
        OIPageInfoBean objPageInfo = null;
        OIResponseObject objResponseObject = null;
        
        try{
            strUserId = (String)getSessionAttribute(request, OILoginConstants.USER_ID);
            objBOSearch = new OIBOSearch();
            objResponseObject = objBOSearch.getPageSize();
            pageSize = ((Integer)objResponseObject.getResponseObject("PageSize")).intValue();
            //pageSize = 2;
            PropertyUtils.copyProperties(objBASearch, objFormSearch);
            ArrayList alTokens = objBASearch.getAlSearchTokens();
            logger.info("inside Action class - strAction = " + strAction);
            
            if (strAction.equals(OISearchConstants.SEARCH_FORUM)){
            	query = objBOSearch.constructForumSearchQuery(alTokens);
            	objPageInfo = new OIPageInfoBean(Integer.parseInt(objBASearch.getPageNo()), pageSize);
            	objResponseObject = objBOSearch.getTotalRec(query, strUserId);
            	totalRecord = ((Integer)objResponseObject.getResponseObject("TotalRecord")).intValue();
            	objPageInfo.setTotalRec(totalRecord);
            	objResponseObject = objBOSearch.doSearch(query, strUserId, objPageInfo.getStartRec(), objPageInfo.getEndRec());
            	if (objPageInfo.getNoOfPages() > 1)request.setAttribute("PageInfo", objPageInfo);
            	request.setAttribute("SearchResult", objResponseObject.getResponseObject("SearchResult"));
            	request.setAttribute("pageName", "SearchPage");
            	request.setAttribute("SearchTokens", alTokens);
            	request.setAttribute("Type", "Forum");
            	request.setAttribute("TotalResult", new Integer(totalRecord));
            	strForward = OISearchConstants.SEARCH_PAGE;
            	
            } else if (strAction.equals(OISearchConstants.SEARCH_SURVEY)){
            	query = objBOSearch.constructSurveySearchQuery(alTokens);
            	objPageInfo = new OIPageInfoBean(Integer.parseInt(objBASearch.getPageNo()), pageSize);
            	objResponseObject = objBOSearch.getTotalRec(query, strUserId);
            	totalRecord = ((Integer)objResponseObject.getResponseObject("TotalRecord")).intValue();
            	objPageInfo.setTotalRec(totalRecord);
            	objResponseObject = objBOSearch.doSearch(query, strUserId, objPageInfo.getStartRec(), objPageInfo.getEndRec());
            	if (objPageInfo.getNoOfPages() > 1)request.setAttribute("PageInfo", objPageInfo);
            	request.setAttribute("SearchResult", objResponseObject.getResponseObject("SearchResult"));
            	request.setAttribute("pageName", "SearchPage");
            	request.setAttribute("SearchTokens", alTokens);
            	request.setAttribute("Type", "Survey");
            	request.setAttribute("TotalResult", new Integer(totalRecord));
            	strForward = OISearchConstants.SEARCH_PAGE;
            	
            } else if (strAction.equals(OISearchConstants.SEARCH_PAPER)){
            	query = objBOSearch.constructPaperSearchQuery(alTokens);
            	objPageInfo = new OIPageInfoBean(Integer.parseInt(objBASearch.getPageNo()), pageSize);
            	objResponseObject = objBOSearch.getTotalRec(query, strUserId);
            	totalRecord = ((Integer)objResponseObject.getResponseObject("TotalRecord")).intValue();
            	objPageInfo.setTotalRec(totalRecord);
            	objResponseObject = objBOSearch.doSearch(query, strUserId, objPageInfo.getStartRec(), objPageInfo.getEndRec());
            	if (objPageInfo.getNoOfPages() > 1)request.setAttribute("PageInfo", objPageInfo);
            	request.setAttribute("SearchResult", objResponseObject.getResponseObject("SearchResult"));
            	request.setAttribute("pageName", "SearchPage");
            	request.setAttribute("SearchTokens", alTokens);
            	request.setAttribute("Type", "Paper");
            	request.setAttribute("TotalResult", new Integer(totalRecord));
            	strForward = OISearchConstants.SEARCH_PAGE;
            	
            } else if (strAction.equals(OISearchConstants.SEARCH_BLOG)){
            	query = objBOSearch.constructBlogSearchQuery(alTokens);
            	objPageInfo = new OIPageInfoBean(Integer.parseInt(objBASearch.getPageNo()), pageSize);
            	objResponseObject = objBOSearch.getTotalRec(query);
            	totalRecord = ((Integer)objResponseObject.getResponseObject("TotalRecord")).intValue();
            	objPageInfo.setTotalRec(totalRecord);
            	objResponseObject = objBOSearch.doSearch(query, objPageInfo.getStartRec(), objPageInfo.getEndRec());
            	if (objPageInfo.getNoOfPages() > 1)request.setAttribute("PageInfo", objPageInfo);
            	request.setAttribute("SearchResult", objResponseObject.getResponseObject("SearchResult"));
            	request.setAttribute("pageName", "SearchPage");
            	request.setAttribute("SearchTokens", alTokens);
            	request.setAttribute("Type", "Blog");
            	request.setAttribute("TotalResult", new Integer(totalRecord));
            	strForward = OISearchConstants.SEARCH_PAGE;
            	
            }
            else if (strAction.equals(OISearchConstants.SEARCH_ASM)){
            	
            	// Need to change. by K.K.Kumaresan on Aug 16,2009
            	logger.info("inside Search ASM page************ = " );
            	query = objBOSearch.constructASMSearchQuery(alTokens);
            	objPageInfo = new OIPageInfoBean(Integer.parseInt(objBASearch.getPageNo()), pageSize);
            	objResponseObject = objBOSearch.getTotalRec(query);
            	totalRecord = ((Integer)objResponseObject.getResponseObject("TotalRecord")).intValue();
            	logger.info("totalRecord = "+totalRecord );
            	objPageInfo.setTotalRec(totalRecord);
            	logger.info("objPageInfo.getStartRec() = "+objPageInfo.getStartRec() );
            	logger.info("objPageInfo.getEndRec() "+objPageInfo.getEndRec());
            	objResponseObject = objBOSearch.doSearch(query, objPageInfo.getStartRec(), objPageInfo.getEndRec());
            	if (objPageInfo.getNoOfPages() > 1)request.setAttribute("PageInfo", objPageInfo);
            	request.setAttribute("SearchResult", objResponseObject.getResponseObject("SearchResult"));
            	request.setAttribute("pageName", "SearchPage");
            	request.setAttribute("SearchTokens", alTokens);
            	request.setAttribute("Type", "ASM");
            	request.setAttribute("TotalResult", new Integer(totalRecord));
            	logger.info("last " );
            	strForward = OISearchConstants.SEARCH_PAGE;
            	
            	
            }
    
        } catch(Exception e) {
            e.printStackTrace();
            strRedirect = OISearchConstants.ERROR_DO+"?error=OIDEFAULT";
        } finally {
            if(!strForward.equals("") && objResponseObject.getResponseObject("error") != null && !objResponseObject.getResponseObject("error").equals("null") ) 
                request.setAttribute("error", objResponseObject.getResponseObject("error"));
        }
        
        if(!strRedirect.equals("")) 
            return new ActionForward(strRedirect);
        else
            return (mapping.findForward(strForward));
	}

}
