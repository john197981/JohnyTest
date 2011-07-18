/*************************************************************************************************
 * File Name		:	OIActionUserProfileSearch.java
 * Author			:	SureshKumar.R
 * Description		:   This Action class is used to search for the users 		
 * Created Date		:	Jul 13, 2005
 * Version			:	1.0
 * Copyright : Scandent Group
 *************************************************************************************************/
package com.oifm.useradmin;

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
import com.oifm.common.OILabelConstants;
import com.oifm.common.OIPageInfoBean;
import com.oifm.common.OIResponseObject;
import com.oifm.consultation.OIConsultConstant;
import com.oifm.login.OILoginConstants;
import com.oifm.useradmin.admin.OIAdminConstants;
import com.oifm.utility.OIDBRegistry;
import com.oifm.utility.OIFormUtilities;

public class OIActionUserProfileSearch extends OIBaseAction 
{
	Logger logger = Logger.getLogger(this.getClass().getName());
	
    public ActionForward doIt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String actionName)
    {
    	/** Logger Declaration **/		
		logger.info(OILabelConstants.BEGIN_METHOD + "OIActionUserProfileSearch");
		
    	String strForward = OISearchConstants.SELECTUSER;    		
    	OIFormSearch objFrmSearch =  (OIFormSearch)form;
    	OIBVUserProfileSearch objBVUsrPrfSrh = new OIBVUserProfileSearch();
    	OIBVUserProfileSearch objBVUsr[] = null;
    	String strAction = null;
    	HttpSession session = request.getSession(false);    
    	try
    	{
    	    setFormToBV(objFrmSearch, objBVUsrPrfSrh);
    		strAction = OIFormUtilities.chkIsNull(objFrmSearch.getHiddenAction()); 
    		PropertyUtils.copyProperties(objBVUsrPrfSrh, objFrmSearch);
    		/**Call to the BO's process method**/
    		if (request.getParameter("pageNo")==null)
    		{
    		    objBVUsrPrfSrh.setRowId("1");
    		}
    		else
    		{
    		    objBVUsrPrfSrh.setRowId(OIFormUtilities.chkIsNull(request.getParameter("pageNo")));
    			if(objBVUsrPrfSrh.getRowId().length()==0)
    			{
    			    objBVUsrPrfSrh.setRowId("0");
    			}
    		}
    		
    		if(session.getAttribute(OILoginConstants.ROLE)!=null)
    		{
    		    objBVUsrPrfSrh.setLoginRole(session.getAttribute(OILoginConstants.ROLE).toString());
    		}
    		if(!strAction.equals(OIAdminConstants.SEARCH) && session.getAttribute(OILabelConstants.QUERY)!=null)
    		{
    		    objBVUsrPrfSrh.setQuery(session.getAttribute(OILabelConstants.QUERY).toString());
    			objBVUsrPrfSrh.setColName((String[])session.getAttribute(OIAdminConstants.COLNAMES));
    		}
    		
    		if(strAction.equals(OIAdminConstants.PAGINATION) || strAction.equals(OIAdminConstants.SORT))
    		{
    		    objBVUsrPrfSrh.setHiddenAction(OIAdminConstants.SEARCH);
    			objFrmSearch.setHiddenAction(OIAdminConstants.SEARCH);
				strAction=OIAdminConstants.SEARCH;
    		}
    		   
	        OIResponseObject aOIResponseObject = new OIBOUserProfileSearch().processProfile(objBVUsrPrfSrh);

	        if(aOIResponseObject.containsKey(OILabelConstants.ERROR))
	        { 
	            logger.info("OKOK");
	            logger.info((String) aOIResponseObject.getResponseObject(OILabelConstants.ERROR));
	            //request.setAttribute("error",(String) aOIResponseObject.getResponseObject(OILabelConstants.ERROR));
	            request.setAttribute("error",OIDBRegistry.getValues((String) aOIResponseObject.getResponseObject(OILabelConstants.ERROR)));
				request.setAttribute("TopFlag","TopFlag");
		        strForward = "/error.do";
		        return new ActionForward(strForward);
	        }

	        populate(aOIResponseObject,request);
	        request.setAttribute(OISearchConstants.COLNAMES,objBVUsrPrfSrh.getAlListCol());

	        
	        if(aOIResponseObject.containsKey(OISearchConstants.SEARCH))
	        {
	            ArrayList alUsrLst = (ArrayList) aOIResponseObject.getResponseObject(OISearchConstants.SEARCH);
				request.setAttribute(OISearchConstants.SEARCH,alUsrLst);
				objFrmSearch.setUpCnt( OIFormUtilities.chkIsNull(objBVUsrPrfSrh.getUpCnt()));
					
				/** This if condition of the Select Groups **/
				if( OIFormUtilities.chkIsNull(objFrmSearch.getHiddenAction()).equals(OISearchConstants.SEARCHGROUP))
				{
				    objBVUsr = new OIBVUserProfileSearch[alUsrLst.size()];
					objBVUsr = (OIBVUserProfileSearch[]) alUsrLst.toArray(objBVUsr);
					request.setAttribute(OISearchConstants.SEARCH,objBVUsr);
					strForward = OISearchConstants.SELECTGROUPS;
					logger.error("came to part1");
				}
				if( OIFormUtilities.chkIsNull(objFrmSearch.getHiddenAction()).equals(OISearchConstants.SEARCHALLGROUP))
				{
				    objBVUsr = new OIBVUserProfileSearch[alUsrLst.size()];
					objBVUsr = (OIBVUserProfileSearch[]) alUsrLst.toArray(objBVUsr);
					request.setAttribute(OISearchConstants.SEARCH,objBVUsr);
					strForward = OISearchConstants.SELECTALLGROUPS;
					request.setAttribute("callingModule",request.getParameter("module"));
					logger.error("came to part2");
				}
	        }
	        /*else if(aOIResponseObject.containsKey(OILabelConstants.ERROR))
	        { 
	            request.setAttribute("error",aOIResponseObject.getResponseObject(OILabelConstants.ERROR).toString());
		        strForward = "/error.do";
		        return new ActionForward(strForward);	
	        }*/
			
	        if( OIFormUtilities.chkIsNull(objFrmSearch.getHiddenAction()).equals(OISearchConstants.ADDGROUP)  || OIFormUtilities.chkIsNull(objFrmSearch.getHiddenAction()).equals(OISearchConstants.SEARCHGROUP))
	        {	
	            strForward = OISearchConstants.SELECTGROUPS;
				logger.error("came to part1a");
	        }	
			else if(OIFormUtilities.chkIsNull(objFrmSearch.getHiddenAction()).equals(OISearchConstants.SEARCHALLGROUP))
	        {	
	            strForward = OISearchConstants.SELECTALLGROUPS;
				logger.error("came to part2a");
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
			    request.setAttribute("SelectForm",form);
		    }

		    if(strAction.equals(OIAdminConstants.SEARCH))
		    {
		        session.setAttribute(OILabelConstants.QUERY,objBVUsrPrfSrh.getQuery());
				session.setAttribute(OIAdminConstants.COLNAMES,objBVUsrPrfSrh.getColName());
		    }	
    	}
    	catch(Exception ex)
    	{
			logger.error(OILabelConstants.EXCEPTION_IN_ACTION,ex);
			request.setAttribute("error",ex.getMessage());
			request.setAttribute("TopFlag","TopFlag");
			strForward= "/error.do";
			return new ActionForward(strForward);
		}
    	finally
    	{				
			/** Releasing all objects **/ 	
			objFrmSearch = null;
			objBVUsrPrfSrh = null;
		}

    	logger.info(OILabelConstants.END_METHOD + "OIActionUserProfileSearch");
		logger.info(OILabelConstants.END_METHOD + strForward);
		request.setAttribute("pageName","SendMail");
		return mapping.findForward(strForward);
    }//doit ends here
    
    /**
     * This is method is used to transfer Form values to BV. 
     * @param objFrmSearch
     * @param objBVUsrPrfSrh
     */
    private void setFormToBV(OIFormSearch objFrmSearch,OIBVUserProfileSearch objBVUsrPrfSrh)
    {
        String strAction = OIFormUtilities.chkIsNull(objFrmSearch.getHiddenAction());
    	objBVUsrPrfSrh.setUserId(objFrmSearch.getUserId());
    	objBVUsrPrfSrh.setHiddenAction(strAction);
    	objBVUsrPrfSrh.setFlag(OIFormUtilities.chkIsNull(objFrmSearch.getFlag()));
    	objBVUsrPrfSrh.setId(OIFormUtilities.chkIsNull(objFrmSearch.getId()));
    	objBVUsrPrfSrh.setRowId(OIFormUtilities.chkIsNull(objFrmSearch.getRowId()));
    	objBVUsrPrfSrh.setHidPage(OIFormUtilities.chkIsNull(objFrmSearch.getHidPage()));
    	objBVUsrPrfSrh.setHidBoard(OIFormUtilities.chkIsNull(objFrmSearch.getHidBoard()));
    		
    	if(strAction.equals(OISearchConstants.SEARCHGROUP) || strAction.equals(OISearchConstants.ADDGROUP) || strAction.equals(OISearchConstants.SEARCHALLGROUP))
    	{
    	    objBVUsrPrfSrh.setGroupId(objFrmSearch.getGroupId());
    		objBVUsrPrfSrh.setGrpDesc(OIFormUtilities.chkIsNull(objFrmSearch.getGrpDesc()));
			objBVUsrPrfSrh.setGrpName(OIFormUtilities.chkIsNull(objFrmSearch.getName()));
    	}
    	else
    	{
    	    objBVUsrPrfSrh.setName(OIFormUtilities.chkIsNull(objFrmSearch.getName()));
			objBVUsrPrfSrh.setMailId(OIFormUtilities.chkIsNull(objFrmSearch.getMailId()));
			objBVUsrPrfSrh.setAge(OIFormUtilities.chkIsNull(objFrmSearch.getAge()));
			objBVUsrPrfSrh.setSchool(OIFormUtilities.chkIsNull(objFrmSearch.getSchool()));
			objBVUsrPrfSrh.setNoOfConsultationPapers(OIFormUtilities.chkIsNull(objFrmSearch.getNoOfConsultationPapers()));
			objBVUsrPrfSrh.setZone(OIFormUtilities.chkIsNull(objFrmSearch.getZone()));
			objBVUsrPrfSrh.setCluster(OIFormUtilities.chkIsNull(objFrmSearch.getCluster()));
			objBVUsrPrfSrh.setSchoolType(OIFormUtilities.chkIsNull(objFrmSearch.getSchoolType()));
			objBVUsrPrfSrh.setCca(OIFormUtilities.chkIsNull(objFrmSearch.getCca()));
	    	objBVUsrPrfSrh.setNoOfSurvey(OIFormUtilities.chkIsNull(objFrmSearch.getNoOfSurvey()));
			objBVUsrPrfSrh.setColName(objFrmSearch.getColName());
			objBVUsrPrfSrh.setHidOrder(OIFormUtilities.chkIsNull(objFrmSearch.getHidOrder()));
			objBVUsrPrfSrh.setHidSortKey(OIFormUtilities.chkIsNull(objFrmSearch.getHidSortKey()));
    	}	
    }
    	
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
			logger.debug(request.getAttribute(strKey));
        }
    }	
}