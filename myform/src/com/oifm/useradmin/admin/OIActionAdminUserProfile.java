/*************************************************************************************************
 * File Name		:	OIActionAdminUserProfile.java
 * Author			:	SureshKumar.R
 * Description		:   This Action class is used to search for the users 		
 * Created Date		:	Aug 04, 2005
 * Version			:	1.0
 * Copyright 		: Scandent Group
 *************************************************************************************************/
package com.oifm.useradmin.admin;

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
import com.oifm.common.OIBOAddTag;
import com.oifm.common.OILabelConstants;
import com.oifm.common.OIPageInfoBean;
import com.oifm.common.OIResponseObject;
import com.oifm.consultation.OIConsultConstant;
import com.oifm.login.OILoginConstants;
import com.oifm.useradmin.OISearchConstants;
import com.oifm.utility.OIFormUtilities;
import com.oifm.useradmin.admin.OIFormAdminUserProfile;
import org.apache.commons.beanutils.PropertyUtils;
 


public class OIActionAdminUserProfile extends OIBaseAction 
{
	Logger logger = Logger.getLogger(this.getClass().getName());
	
    public ActionForward doIt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String actionName)
    {
    	/** Logger Declaration **/		
		logger.debug(OILabelConstants.BEGIN_METHOD + "OIActionAdminUserProfile");		
    	String strForward = OISearchConstants.SEARCH;    		
    	OIFormAdminUserProfile objFrmSearch =  (OIFormAdminUserProfile)form;
    	String strAction = OIFormUtilities.chkIsNull(objFrmSearch.getHiddenAction());
    	logger.info("strAction-" + strAction);
    	OIBVAdminUserProfile objBV =  new OIBVAdminUserProfile(); 
    	HttpSession session = request.getSession(false);    
    	OIResponseObject aOIResponseObject= null;
    	OIBOAddTag tagBO = new OIBOAddTag();
    	String strUserId = (String)getSessionAttribute(request, OILoginConstants.USER_ID);
    	
    	try
    	{
    		request.setAttribute("pageName","AdminUserProfile");
    		PropertyUtils.copyProperties(objBV, objFrmSearch);
    		//System.out.println("OIActionAdminUserProfile-doIt objBV query="+objBV.getQuery());
    		if(strAction.equals(OIAdminConstants.USER) || strAction.equals(OIAdminConstants.USERUPDATE)||strAction.equals(OIAdminConstants.DELBOOKMARK)||strAction.equals(OIAdminConstants.DELSTICKY))
    		{
		    	logger.info("EMail Id " +objBV.getMailId());
		    	session.setAttribute(OILoginConstants.EMAIL,objBV.getMailId());
    			objBV.setId(OIFormUtilities.chkIsNull(session.getAttribute(OILoginConstants.USER_ID).toString()));
    			strForward ="UserView";
    			
    			if(strAction.equals(OIAdminConstants.USERUPDATE))
    			{
    				ArrayList alTagNames = (ArrayList) getSessionAttribute(request, "alTagNames");
    				
    				
    				//if(alTagNames.size()>0){
    					//System.out.println("OIActionAdminUserProfile: doIt - update tag : 1");
        				tagBO.updateTag(strUserId, alTagNames);
    				//}
    			}
    			
    			removeSessionAttribute(request, "alTagNames");
				removeSessionAttribute(request, "oiFormBlogTag");
				
				aOIResponseObject = tagBO.getTags(strUserId);
				ArrayList tagList = (ArrayList)aOIResponseObject.getResponseObject("tagList");
				if(tagList != null){
					setSessionAttribute(request, "alTagNames",tagList);
				}
    		}
    		
    		if("RefreshAction".equals(actionName))
			{
				//request.setAttribute(OIBlogConstants.PAGE_NAME, OIBlogConstants.CREATE_BLOG_PAGE);
    			strForward ="UserView";
			}
    			
    		if(session.getAttribute(OILoginConstants.ROLE)!=null)
    		{
    		    objBV.setLoginRole(OIFormUtilities.chkIsNull(session.getAttribute(OILoginConstants.ROLE).toString()));
    		}
    		
    		if(!strAction.equals(OIAdminConstants.SEARCH) && session.getAttribute(OILabelConstants.QUERY)!=null)
    		{
    			//System.out.println("OIActionAdminUserProfile-doIt set query");
    			//System.out.println("OIActionAdminUserProfile-doIt session query="+
    			//		session.getAttribute(OILabelConstants.QUERY).toString());
    		    objBV.setQuery(session.getAttribute(OILabelConstants.QUERY).toString());
    			objBV.setColName((String[])session.getAttribute(OIAdminConstants.COLNAMES));
    		}
    		if(strAction.equals(OIAdminConstants.PAGINATION) || strAction.equals(OIAdminConstants.SORT))
    		{
    		    objBV.setHiddenAction(OIAdminConstants.SEARCH);
    			objFrmSearch.setHiddenAction(OIAdminConstants.SEARCH);
				strAction=OIAdminConstants.SEARCH;
    		}
    			
    			
    		/**Call to the BO's process method**/
    		if (request.getParameter("pageNo")==null)
    		{
    		    objBV.setRowId("1");
    		}
    		else
    		{
    		    objBV.setRowId(OIFormUtilities.chkIsNull(request.getParameter("pageNo")));
    			if(objBV.getRowId().length()==0)
    			{
    			    objBV.setRowId("0");
    			}
    		}	
    		//System.out.println("OIActionAdminUserProfile-doIt objBV query2="+objBV.getQuery());
	        aOIResponseObject = new OIBOAdminUserProfile().processProfile( objBV);
	        populate(aOIResponseObject,request);
	        request.setAttribute(OISearchConstants.COLNAMES,objBV.getAlListCol());
	        objFrmSearch.setShowSign(objBV.getShowSign());
	        request.setAttribute("Domains", new OIBOAdminUserProfile().getMailDomains());
	        
	           			   
	        if(aOIResponseObject.containsKey(OISearchConstants.SEARCH))
	        {
	        	//System.out.println("OIActionAdminUserProfile-doIt search1");
	            logger.info("Search" + aOIResponseObject.containsKey(OISearchConstants.SEARCH));
				ArrayList alUsrLst = (ArrayList) aOIResponseObject.getResponseObject(OISearchConstants.SEARCH);
				request.setAttribute(OISearchConstants.SEARCH,alUsrLst);
				objFrmSearch.setUpCnt( OIFormUtilities.chkIsNull(objBV.getUpCnt()));
	        }
	        else if(aOIResponseObject.containsKey(OILabelConstants.ERROR))
	        { 
	            request.setAttribute("error",aOIResponseObject.getResponseObject(OILabelConstants.ERROR).toString());
		        strForward = "/error.do";
		        return new ActionForward(strForward);	
			}
			if( OIFormUtilities.chkIsNull(objFrmSearch.getHiddenAction()).equals(OISearchConstants.ADDGROUP)  || OIFormUtilities.chkIsNull(objFrmSearch.getHiddenAction()).equals(OISearchConstants.SEARCHGROUP))
			{	
				strForward = OISearchConstants.SELECTGROUPS;
			}	

		    OIPageInfoBean aOIPageInfoBean = (OIPageInfoBean) aOIResponseObject.getResponseObject(OIConsultConstant.K_aOIPageInfoBean);
		    if (aOIPageInfoBean!=null)
		    {
		        ArrayList arPage = new ArrayList();
		        
			    int start = aOIPageInfoBean.getCurrLinkStart();
			    for (int i=start;i<start + aOIPageInfoBean.getNoOfLinks();i++)
			    {
			        if (i<=aOIPageInfoBean.getNoOfPages())
			            arPage.add(i+"");
			    }
			        
			    request.setAttribute("totalPage",aOIPageInfoBean.getNoOfPages() + "");
			    request.setAttribute(OIConsultConstant.K_currentPage,aOIPageInfoBean.getPageNo() + "");

			    request.setAttribute(OIConsultConstant.K_pageNo,aOIPageInfoBean.getPageNo() + "");
			    request.setAttribute(OIConsultConstant.K_nextSet,aOIPageInfoBean.isNSet() + "");
			    request.setAttribute(OIConsultConstant.K_previousSet,aOIPageInfoBean.isPSet() + "");
			    request.setAttribute(OIConsultConstant.K_nextPage,aOIPageInfoBean.getNextSet() + "");
			    request.setAttribute(OIConsultConstant.K_previousPage,aOIPageInfoBean.getPrevSet() + "");
			    request.setAttribute(OIConsultConstant.K_arPage,arPage);
		    }
		        
		    if(strAction.equals("view") || strAction.equals(OIAdminConstants.SAVE) || strAction.equals(OIAdminConstants.DISABLE))
		    {
		        strForward = "AdminUserView";	
		    }
		    if(strAction.equals(OIAdminConstants.SAVE))
		    { 
		        request.setAttribute(OIAdminConstants.SAVE,OIAdminConstants.SAVE);
		    }
			else if(strAction.equals(OIAdminConstants.DISABLE))
		    { 
		        request.setAttribute(OIAdminConstants.DISABLE,OIAdminConstants.DISABLE);
		    }
		    else if (strAction.equals(OIAdminConstants.DELBOOKMARK))
		    {
		        request.setAttribute(OIAdminConstants.DELBOOKMARK,OIAdminConstants.DELBOOKMARK);
		    }
		    else if (strAction.equals(OIAdminConstants.DELSTICKY))
		    {
		        request.setAttribute(OIAdminConstants.DELSTICKY,OIAdminConstants.DELSTICKY);
		    }
		    else if(strAction.equals(OIAdminConstants.USERUPDATE))
		    {
		        if(aOIResponseObject.containsKey(OILabelConstants.DUPNICKNAME))
		        {
		            request.setAttribute(OILabelConstants.DUPNICKNAME,OILabelConstants.DUPNICKNAME);
		        }
		        else
		        {
		            request.setAttribute(OIAdminConstants.SAVE,OIAdminConstants.SAVE);
		        } 	
			}
		    if(strAction.equals(OIAdminConstants.SEARCH))
		    {
		    	//System.out.println("OIActionAdminUserProfile-doIt straction = search");
		        session.setAttribute(OILabelConstants.QUERY,objBV.getQuery());
	    		session.setAttribute(OIAdminConstants.COLNAMES,objBV.getColName());
			}	
		    setBVToForm( objBV,objFrmSearch);		        
    	}
    	catch(Exception ex)
    	{
    	    logger.error(OILabelConstants.EXCEPTION_IN_ACTION,ex);
		    if(strAction.equals("view") || strAction.equals(OIAdminConstants.SAVE) || strAction.equals(OIAdminConstants.DISABLE))
		    {
	        	request.setAttribute("TopFlag","TopFlag");
	        }
			request.setAttribute("error",ex.getMessage());
			strForward= "/error.do";
			return new ActionForward(strForward);
		}
    	finally
    	{				
			/** Releasing all objects **/ 	
			objFrmSearch = null;
			objBV = null;
		}
    	
    	logger.debug(OILabelConstants.END_METHOD + "OIActionAdminUserProfile");
		logger.debug(OILabelConstants.END_METHOD + strForward);
		
		return mapping.findForward(strForward);
    }//doit ends here
    
    
    /**
     * This method is used to populate the values in combo boxes school, school type ,cluster.
     * @param objBVUsrPrfSrh
     * @param aOIResponseObject
     * @param request
     */
    private void populate(OIResponseObject aOIResponseObject,HttpServletRequest request)
    {
        chkKey( aOIResponseObject,request,OIAdminConstants.ROLE);
    	chkKey( aOIResponseObject,request,OIAdminConstants.SCHOOL);
   	    chkKey( aOIResponseObject,request,OIAdminConstants.CLUSTER);
   	    chkKey( aOIResponseObject,request,OIAdminConstants.SCH_TYP);
   	    chkKey( aOIResponseObject,request,OIAdminConstants.DESIGNATION);
    	chkKey( aOIResponseObject,request,OIAdminConstants.DIVISION);
   	    chkKey( aOIResponseObject,request,OIAdminConstants.ZONE);
   	    chkKey( aOIResponseObject,request,OIAdminConstants.DIV_STATUS);
   	    chkKey( aOIResponseObject,request,OIAdminConstants.PROFILE);
   	    chkKey( aOIResponseObject,request,OIAdminConstants.SUB_LEVEL);
   	    chkKey( aOIResponseObject,request,OIAdminConstants.TEACH_LEVEL);
   	    chkKey( aOIResponseObject,request,OIAdminConstants.CCA);
   	    chkKey( aOIResponseObject,request,OIAdminConstants.BOOKMARKS);
   	    chkKey( aOIResponseObject,request,OIAdminConstants.STICKY);
    }
    	
    /**
     * This is the helper method to check dropdown key available in responseobject 
     * @param aOIResponseObject
     * @param strKey
     * @param request
     */

    private void chkKey(OIResponseObject aOIResponseObject,HttpServletRequest request,String strKey)
    {
        if(aOIResponseObject.containsKey(strKey))
        {
            request.setAttribute(strKey,aOIResponseObject.getResponseObject(strKey));
        }
    }	
		
    private void setBVToForm(OIBVAdminUserProfile objBV,OIFormAdminUserProfile objFrmSearch)
    {
        objFrmSearch.setName(objBV.getName());
		objFrmSearch.setNickName(objBV.getNickName());
		objFrmSearch.setMailName(objBV.getMailName());
		objFrmSearch.setMailDomain(objBV.getMailDomain());
		objFrmSearch.setArea(objBV.getArea());
		objFrmSearch.setHobbies(objBV.getHobbies());
		objFrmSearch.setSign(objBV.getSign());
		objFrmSearch.setShowSign(objBV.getShowSign());
		objFrmSearch.setTotPostings(objBV.getTotPostings());
		objFrmSearch.setObsolete(objBV.getObsolete());
    }
}