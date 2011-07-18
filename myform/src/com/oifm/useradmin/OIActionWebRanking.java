/*
 * File name	= OIActionRanking.java
 * Package		= com.oifm.useradmin
 * Created on 	= Aug 16, 2005
 * Created by	= Oscar
 * Copyright	= Scandent Group
 *
 */

package com.oifm.useradmin;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.oifm.base.OIBaseAction;
import com.oifm.common.OIFunctionConstants;
import com.oifm.common.OIResponseObject;
import com.oifm.login.OILoginConstants;
import com.oifm.common.OIActionHelper;
import java.io.PrintWriter;

public class OIActionWebRanking extends OIBaseAction {
	
	Logger logger = Logger.getLogger(OIActionWebRanking.class);

	public ActionForward doIt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String actionName) {
		String strAction = (actionName != null)? actionName:OIRankingConstants.RANK_MAIN;		        
        String strForward = "";
        String strRedirect = "";
        String strUserId = "";
        ArrayList alFunctions = null;
        boolean isAllowRanking = false;
        
        OIBORanking objBORanking = null;
        OIResponseObject objResponseObject = null;
        try{
        	
        	OIFormWebRanking objFormWebRanking = (OIFormWebRanking)form;
        	String strHidAction = objFormWebRanking.getHidAction();
        	strHidAction = request.getParameter("hidAction");
            alFunctions = (ArrayList)getSessionAttribute(request, OILoginConstants.FUNCTION_LIST);
            strUserId = (String)getSessionAttribute(request, OILoginConstants.USER_ID);
            isAllowRanking = alFunctions.contains(OIFunctionConstants.USER_RANKING);
            
            objBORanking = new OIBORanking();            
    		OIBAWebRanking objBAWebRanking = new OIBAWebRanking();
    		PropertyUtils.copyProperties(objBAWebRanking, objFormWebRanking);
    		
    		
    		if(strHidAction!=null && strHidAction.equals("WebRankDetails")){
    			objResponseObject = objBORanking.getWebsiteRanking(objBAWebRanking);
    			request.setAttribute("ResultDetails", objResponseObject.getResponseObject("ResultDetails"));
    		}else if(strHidAction!=null && strHidAction.equals("ClearLog")){
    			objBORanking.clearLog();
    			objResponseObject = objBORanking.getWebsiteRanking(objBAWebRanking);
    			request.setAttribute("Result", objResponseObject.getResponseObject("Result"));
    		}else{
    			objResponseObject = objBORanking.getWebsiteRanking(objBAWebRanking);
    			request.setAttribute("Result", objResponseObject.getResponseObject("Result"));
    		}
    		
    		objResponseObject = objBORanking.getWebsiteRankingList();
    		double iTotalCount  = objBORanking.getCount();
    		logger.info(" iTotalCount "+iTotalCount);
    		request.setAttribute("totalCount",String.valueOf(iTotalCount));
    		
            request.setAttribute("typeList", objResponseObject.getResponseObject("typeList"));
            
            /* Get the description for type */
            ArrayList alList = (ArrayList) objResponseObject.getResponseObject("type");
            String strType = objBAWebRanking.getTypeList(); 
            strType = strType == null ?strType="":strType;
            String strTypeDesc = "";
			
            for(int iCount = 0 ; iCount < alList.size() ; iCount ++){
            	ArrayList alRow = (ArrayList)alList.get(iCount);
            	 if(String.valueOf(alRow.get(0)).equals(strType)){
            	 	strTypeDesc = String.valueOf(alRow.get(1));
            	 }
            }
            if(strHidAction!=null && strHidAction.equals("WebRankDetails")){
            	request.setAttribute("pageName", "WebRankDetails");
            	request.setAttribute("WebRanking", objBAWebRanking);
            	request.setAttribute("Description", strTypeDesc);
            }
            else{
            	request.setAttribute("pageName", "WebRank");
            }
            
            if(strHidAction!=null && strHidAction.equals("WebRankDetails")){
            	strForward = "WebRankDetails";
            }else if(strHidAction!=null && strHidAction.equals("WebRankExport")){
            	strForward = "";				
				PrintWriter out = (new OIActionHelper()).getWriter(response, "WebRankingExport_.xls");
				String strInfo = getInfo((ArrayList)objResponseObject.getResponseObject("Result"));
				out.write(strInfo);
				out.flush();
				 
            }else{
            	strForward = OIRankingConstants.RANK_PAGE;
            }
            
        } catch(Exception e) {
        	logger.error("TRY - CATCH : " + e);
            strRedirect = OIRankingConstants.ERROR_DO+"?error=OIDEFAULT";
        } finally {
            if(!strForward.equals("") && objResponseObject.getResponseObject("error") != null && !objResponseObject.getResponseObject("error").equals("null") ) 
                request.setAttribute("error", objResponseObject.getResponseObject("error"));
        }
        
        if(!strRedirect.equals("")) 
            return new ActionForward(strRedirect);
        else
            return (mapping.findForward(strForward));
    }
	private String getInfo(ArrayList alSectionList)  
	{
		
		int cnt =0;
		StringBuffer stbrOut = new StringBuffer();
		stbrOut.append("Name \t");
		stbrOut.append("Designation \t");
		stbrOut.append("Age \t");
		stbrOut.append("School Level \t");
		stbrOut.append("No. of Years in Service \t");
		stbrOut.append("No. of Hits \t");
		for(int i=0; i<alSectionList.size(); i++) 
		{
			OIRankingBean objRankingBean = (OIRankingBean)alSectionList.get(i);
			stbrOut.append("\n");
			stbrOut.append(objRankingBean.getStrName()+"\t");
			stbrOut.append(objRankingBean.getStrDesignation()+"\t");
			stbrOut.append(objRankingBean.getStrAge()+"\t");
			stbrOut.append(objRankingBean.getStrSchoolLevel()+"\t");
			stbrOut.append(objRankingBean.getStrYIS()+"\t");
			stbrOut.append(objRankingBean.getStrHits()+"\t");
		}
		return stbrOut.toString();
	}


}