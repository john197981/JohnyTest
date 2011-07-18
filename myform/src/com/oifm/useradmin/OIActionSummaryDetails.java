/*
 * Roles module Action class
 * 
 * File name	= OIActionRoles.java
 * Package		= com.oifm.useradmin
 * Created on 	= Aug 5, 2005
 * Created by	= Oscar
 * Copyright	= Scandent Group
 *
 */
package com.oifm.useradmin;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.oifm.base.OIBaseAction;
import com.oifm.common.OIActionHelper;
import com.oifm.common.OIResponseObject;
import com.oifm.utility.OIUtilities;


public class OIActionSummaryDetails extends OIBaseAction {
    
    private static Logger logger = Logger.getLogger(OIActionSummaryDetails.class);
    
    public ActionForward doIt(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response,
            String actionName) {
    	//String strAction = (actionName != null)? actionName:"SummaryDetails";
    	
        String strForward = "";
        String strRedirect = "";
        OIBOSummaryDetails objBO =new OIBOSummaryDetails(); 
        OIResponseObject objResponseObject = null;
        
        try{
            
        	strForward = actionName;
        	objResponseObject = objBO.getSummaryDetails();
            request.setAttribute("TotalList", objResponseObject.getResponseObject("TotalList"));
            if(OIUtilities.replaceNull(actionName).equalsIgnoreCase("SummaryDetails_Excel")){
            	exportExcel(objResponseObject.getResponseObject("TotalList"),response);
            	strForward = "";
            }
            request.setAttribute("pageName", actionName);
            
            
            
        } catch(Exception e) {
        	System.out.println("action------error"+e);
            e.printStackTrace();
            strRedirect = OIRolesConstants.ERROR_DO+"?error=OIDEFAULT";
        } finally {
            if(!strForward.equals("") && objResponseObject.getResponseObject("error") != null && !objResponseObject.getResponseObject("error").equals("null") ) 
                request.setAttribute("error", objResponseObject.getResponseObject("error"));
        }
        
        if(!strRedirect.equals("")) 
            return new ActionForward(strRedirect);
        else
            return (mapping.findForward(strForward));
    }
    
    private void exportExcel(Object objList,HttpServletResponse response) throws Exception{
    	try{
    	PrintWriter out = (new OIActionHelper()).getWriter(response, "SummaryDetails.xls");
    	StringBuffer stbrOut = new StringBuffer();
    	int count1=0;
		int count2=0;
    	
    	if(objList!=null){
		 	ArrayList alTotalList =(ArrayList)objList;
			if(alTotalList!=null && alTotalList.size()>0){
				for(int i=0;i<alTotalList.size();i++){
					if(i==0){
						stbrOut.append("Discussion Forum: By Age Group \t");
					}else if(i==1){
						stbrOut.append("Discussion Forum: By Designation \t");
 					}else if(i==2){
						stbrOut.append("Discussion Forum: By School Levels (Applicable to school staff only) \t");
 					}else if(i==3){
						stbrOut.append("Consultation Paper : By Age Group \t");
					}else if(i==4){
						stbrOut.append("Consultation Paper : By Designation \t");
					}else if(i==5){
						stbrOut.append("Consultation Paper :By School Levels (Applicable to school staff only) \t");
					}else if(i==6){
						stbrOut.append("Survey : By Age Group \t");
					}else if(i==7){
						stbrOut.append("Survey : By Designation \t");
					}else if(i==8){
						stbrOut.append("Survey :By School Levels (Applicable to school staff only) \t");
					}
					
					stbrOut.append("\n");
					if(i==0 || i==3 || i==6 ){
						stbrOut.append("Age Group \t");
					}else if(i==1 || i==4 || i==7 ){
						stbrOut.append("Designation \t");
					}else if(i==2 || i==5 || i==8 ){
						stbrOut.append("School Level \t");
					}
					
					stbrOut.append("No. Of Participants \t");
					
					if(i==0 || i==1 || i==2 ){
						stbrOut.append("No. Of Posts \t");
					}else if(i==3 || i==4 || i==5 ){
						stbrOut.append("No. Of Papers \t");
					}else if(i==6 || i==7 || i==8 ){
						stbrOut.append("No. Of Surveys \t");
					}
		
					if(alTotalList.get(i)!=null){
						ArrayList alList =(ArrayList)alTotalList.get(i);
						if(alList!=null && alList.size()>0){
							OIRankingBean[] objVO= (OIRankingBean[])alList.get(0);
							
							count1 = 0;
							count2 = 0;
							for(int j=0;j<objVO.length;j++){
								stbrOut.append("\n");
								stbrOut.append(objVO[j].getStrName()+" \t");
								stbrOut.append(objVO[j].getStrPostCount()+" \t");
								stbrOut.append(objVO[j].getStrTotalCount()+" \t");
								
								count1 = count1+OIUtilities.replaceNulltoZero(objVO[j].getStrPostCount());
 								count2 = count2+OIUtilities.replaceNulltoZero(objVO[j].getStrTotalCount());
								
							}
							stbrOut.append("\n");
							stbrOut.append("Total \t");
							stbrOut.append(count1 +"\t");
							stbrOut.append(count2 +"\t");
						}
					}
					stbrOut.append("\n");
					stbrOut.append("\n");
					stbrOut.append("\n");
					
				}
			}
     	}
		out.write(stbrOut.toString());
		out.flush();
    	}catch(Exception e){
    		System.out.println("exportExcel===="+e);
    	}
    }
    
}
