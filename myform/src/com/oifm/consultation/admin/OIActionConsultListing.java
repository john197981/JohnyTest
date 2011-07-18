package com.oifm.consultation.admin;
/*
 * Class Name:
 * Description:
 * 
 * 	Created By			Created/Modified on			Revision				Remarks
 * -----------------------------------------------------------------------------------------------------
 * 	Rajkumar			01/07/2005					Draft					Inital Version
 * 
 * -----------------------------------------------------------------------------------------------------
 */

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.oifm.base.OIBaseAction;
import com.oifm.common.OIResponseObject;
import com.oifm.consultation.OIConsultConstant;
import com.oifm.login.OILoginConstants;
import com.oifm.utility.DateUtility;
import com.oifm.utility.OIDBRegistry;


public class OIActionConsultListing extends OIBaseAction 
{
    Logger logger = Logger.getLogger(OIActionConsultListing.class.getName());
    private static final String POPULATE_ACTION = "populate";
    private static final String POPULATE_DIVISION_LIST_ACTION = "divisionList";
    private static final String POPULATE_DIVISION_PAPER_LIST_ACTION = "populatePaper";
    
    public ActionForward doIt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String actionName)
    {
        if (actionName!=null)
        {
	        if (actionName.equals(POPULATE_ACTION))
	        {
	            return populate(mapping, form, request, response);
	        }
	        else if(actionName.equals(POPULATE_DIVISION_LIST_ACTION))
			{
				return populateDivisions(mapping, form, request, response);
			}
	        else if(actionName.equals(POPULATE_DIVISION_PAPER_LIST_ACTION))
			{
				return populatePaper(mapping, form, request, response);
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

    public ActionForward populateDivisions(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
    	//System.out.println("OIActionConsultListing: populateDivisions - Act : Act 1");
    	OIBOConsultListing objBo = new OIBOConsultListing();
    	OIResponseObject objResponseObject = null;
    	objResponseObject = objBo.getConsultDivision();

    	request.setAttribute("CategoryFlag", objResponseObject.getResponseObject("CategoryFlag"));
        request.setAttribute(OIConsultConstant.CONSULT_LISTING_FORM,objResponseObject.getResponseObject(OIConsultConstant.K_arOIBVConsultDivision));
        request.setAttribute(OILoginConstants.PAGENAME,"ConsultDivisionListing");
        return mapping.findForward(OIConsultConstant.POPULATE_CONSULTLISTING);	
    }

    public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        OIResponseObject oIResponseObject = (OIResponseObject) new OIBOConsultListing().readConsultation();
        ArrayList messageList = (ArrayList) oIResponseObject.getResponseObject(OILoginConstants.K_MESSAGE);
        ArrayList ar1=null;
        ArrayList ar2=null;
        String error = (String) oIResponseObject.getResponseObject(OILoginConstants.K_ERROR);
        OIFormConsultListing aOIFormConsultListing = new OIFormConsultListing();
        OIFormA1ConsultListing aOIFormA1ConsultListing=null;
        OIFormA2ConsultListing aOIFormA2ConsultListing=null;
        if (error != null)
        {
            request.setAttribute(OILoginConstants.K_ERROR,error);
            return new ActionForward(OILoginConstants.ERRORPAGE);
        }
        ArrayList arOIBVConsultListing = (ArrayList) oIResponseObject.getResponseObject(OIConsultConstant.K_arOIBVConsultListing);
        ArrayList arOIBVConsultListingFeedBack = (ArrayList) oIResponseObject.getResponseObject(OIConsultConstant.K_arOIBVConsultListingFeedBack);
        
        int flagCategoryId=0;

        if (arOIBVConsultListing!=null)
        {
	        for(int i=0;i<arOIBVConsultListing.size();i++)
	        {
	            OIBVConsultListing aOIBVConsultListing = (OIBVConsultListing) arOIBVConsultListing.get(i);
	            if (aOIBVConsultListing.getCategoryId()==flagCategoryId)
	            {
	                flagCategoryId = aOIBVConsultListing.getCategoryId();
	            }
	            else
	            {
	                //ar1 = aOIFormConsultListing.getArOIFormA1ConsultListing();
	                aOIFormA1ConsultListing = new OIFormA1ConsultListing();
	                aOIFormA1ConsultListing.setCategoryId(aOIBVConsultListing.getCategoryId() + "");
	                aOIFormA1ConsultListing.setCategoryName(aOIBVConsultListing.getCategoryName());
	                aOIFormConsultListing.addArOIFormA1ConsultListing(aOIFormA1ConsultListing);
	                flagCategoryId = aOIBVConsultListing.getCategoryId();
	            }
	            if (aOIBVConsultListing.getPaperTitle()!=null && (! aOIBVConsultListing.getPaperTitle().trim().equals("")))
	            {
		            aOIFormA2ConsultListing = new OIFormA2ConsultListing();
		            if (aOIBVConsultListing.getDescription()!=null)
		            	aOIFormA2ConsultListing.setDescription(aOIBVConsultListing.getDescription().replaceAll("\n","<br>"));
		            else
		            	aOIFormA2ConsultListing.setDescription(aOIBVConsultListing.getDescription());

		            //aOIFormA2ConsultListing.setDescription(aOIBVConsultListing.getDescription());
		            aOIFormA2ConsultListing.setDivision(aOIBVConsultListing.getDivision());
		            String textFromDt=null,testToDt=null,textTargetAudiance=null;
		            try
		            {
		                if (aOIBVConsultListing.getFromDt()!=null && (!aOIBVConsultListing.getFromDt().equals("")))
		                    textFromDt = DateUtility.getMMDDYYYYStringFromDate(aOIBVConsultListing.getFromDt());
		                if (aOIBVConsultListing.getToDt()!=null && (!aOIBVConsultListing.getToDt().equals("")))
		                    testToDt = DateUtility.getMMDDYYYYStringFromDate(aOIBVConsultListing.getToDt());
						if (aOIBVConsultListing.getTargetAudiance()!=null && (!aOIBVConsultListing.getTargetAudiance().equals("")))
		                    textTargetAudiance = aOIBVConsultListing.getTargetAudiance();
		            }
		            catch(Exception e)
		            {
		                
		            }
	
		            aOIFormA2ConsultListing.setFromDt(textFromDt);
		            aOIFormA2ConsultListing.setToDt(testToDt);
					aOIFormA2ConsultListing.setTargetAudiance(textTargetAudiance);
					
					aOIFormA2ConsultListing.setNoOfFeedbacks("0");
	
		            aOIFormA2ConsultListing.setMailStatus(aOIBVConsultListing.getMailStatus());
		            if (arOIBVConsultListingFeedBack!=null)
		            {
			            for (int j=0;j<arOIBVConsultListingFeedBack.size();j++)
			            {
			                OIBVConsultListingFeedBack aOIBVConsultListingFeedBack = (OIBVConsultListingFeedBack) arOIBVConsultListingFeedBack.get(j);
			                if (aOIBVConsultListingFeedBack.getPaperID()==aOIBVConsultListing.getPaperId())
			                {
			                    aOIFormA2ConsultListing.setNoOfFeedbacks(aOIBVConsultListingFeedBack.getNoOfFeedbacks() + "");
			                }
			            }
		            }
		            aOIFormA2ConsultListing.setPaperId(aOIBVConsultListing.getPaperId() + "");
		            aOIFormA2ConsultListing.setPaperTitle(aOIBVConsultListing.getPaperTitle());
		            aOIFormA2ConsultListing.setSecurity(aOIBVConsultListing.getSecurity());
		            aOIFormA1ConsultListing.addArOIFormA2ConsultListing(aOIFormA2ConsultListing);
	            }
	            if (aOIFormA1ConsultListing.arOIFormA2ConsultListing.size()==0)
	            {
	                aOIFormA1ConsultListing.arOIFormA2ConsultListing=null;
	            }
	        }
        }
        else
        {
            aOIFormConsultListing.setArOIFormA1ConsultListing(null);
        }
        
        request.setAttribute(OIConsultConstant.CONSULT_LISTING_FORM,aOIFormConsultListing);
        request.setAttribute(OILoginConstants.PAGENAME,"ConsultListing");
        return mapping.findForward(OIConsultConstant.POPULATE_CONSULTLISTING);
    }
    
    public ActionForward populatePaper(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
	    //System.out.println("...................populatePaper................");
	    String divisionCode = "-1";
		String hidSortKey = "";
		String hidOrder = "";
		
		if(request.getParameter("divisionCode") != null)
			divisionCode = request.getParameter("divisionCode");
		
		if(divisionCode.length()<2){
			divisionCode = "-1";
		}
		
		//System.out.println("populatePaper:divisionCode" + divisionCode+"haha");
		if(request.getParameter("hidSortKey") != null)
				hidSortKey = request.getParameter("hidSortKey");	
		if(request.getParameter("hidOrder") != null)
				hidOrder = request.getParameter("hidOrder");

        OIResponseObject oIResponseObject = (OIResponseObject) new OIBOConsultListing().readConsultationPaper(divisionCode,hidSortKey,hidOrder);

        ArrayList messageList = (ArrayList) oIResponseObject.getResponseObject(OILoginConstants.K_MESSAGE);
        ArrayList ar1=null;
        ArrayList ar2=null;
        String error = (String) oIResponseObject.getResponseObject(OILoginConstants.K_ERROR);
        OIFormConsultListing aOIFormConsultListing = new OIFormConsultListing();
		aOIFormConsultListing.setHidSortKey(hidSortKey);
		aOIFormConsultListing.setHidOrder(hidOrder);
        OIFormA1ConsultListing aOIFormA1ConsultListing=null;
        OIFormA2ConsultListing aOIFormA2ConsultListing=null;
        if (error != null)
        {
            request.setAttribute(OILoginConstants.K_ERROR,error);
            return new ActionForward(OILoginConstants.ERRORPAGE);
        }
        ArrayList arOIBVConsultListing = (ArrayList) oIResponseObject.getResponseObject(OIConsultConstant.K_arOIBVConsultListing);
        ArrayList arOIBVConsultListingFeedBack = (ArrayList) oIResponseObject.getResponseObject(OIConsultConstant.K_arOIBVConsultListingFeedBack);
      
        int flagCategoryId=0;

        if (arOIBVConsultListing!=null)
        {
	        for(int i=0;i<arOIBVConsultListing.size();i++)
	        {
	            OIBVConsultListing aOIBVConsultListing = (OIBVConsultListing) arOIBVConsultListing.get(i);
	            if (aOIBVConsultListing.getCategoryId()==flagCategoryId)
	            {
	                flagCategoryId = aOIBVConsultListing.getCategoryId();
	            }
	            else
	            {
	                //ar1 = aOIFormConsultListing.getArOIFormA1ConsultListing();
	                aOIFormA1ConsultListing = new OIFormA1ConsultListing();
	                aOIFormA1ConsultListing.setCategoryId(aOIBVConsultListing.getCategoryId() + "");
	                aOIFormA1ConsultListing.setCategoryName(aOIBVConsultListing.getCategoryName());
	                aOIFormConsultListing.addArOIFormA1ConsultListing(aOIFormA1ConsultListing);
	                flagCategoryId = aOIBVConsultListing.getCategoryId();
	            }
	            if (aOIBVConsultListing.getPaperTitle()!=null && (! aOIBVConsultListing.getPaperTitle().trim().equals("")))
	            {
		            aOIFormA2ConsultListing = new OIFormA2ConsultListing();
		            if (aOIBVConsultListing.getDescription()!=null)
		            	aOIFormA2ConsultListing.setDescription(aOIBVConsultListing.getDescription().replaceAll("\n","<br>"));
		            else
		            	aOIFormA2ConsultListing.setDescription(aOIBVConsultListing.getDescription());
                    //aOIFormA2ConsultListing.setDescription(aOIBVConsultListing.getDescription());
		            aOIFormA2ConsultListing.setDivision(aOIBVConsultListing.getDivision());
		            String textFromDt=null,testToDt=null,textTargetAudiance=null;
		            try
		            {
		                if (aOIBVConsultListing.getFromDt()!=null && (!aOIBVConsultListing.getFromDt().equals("")))
		                    textFromDt = DateUtility.getMMDDYYYYStringFromDate(aOIBVConsultListing.getFromDt());
		                if (aOIBVConsultListing.getToDt()!=null && (!aOIBVConsultListing.getToDt().equals("")))
		                    testToDt = DateUtility.getMMDDYYYYStringFromDate(aOIBVConsultListing.getToDt());
						if (aOIBVConsultListing.getTargetAudiance()!=null && (!aOIBVConsultListing.getTargetAudiance().equals("")))
		                    textTargetAudiance = aOIBVConsultListing.getTargetAudiance();
		            }
		            catch(Exception e)
		            {
		                
		            }
	
		            aOIFormA2ConsultListing.setFromDt(textFromDt);
		            aOIFormA2ConsultListing.setToDt(testToDt);
					aOIFormA2ConsultListing.setTargetAudiance(textTargetAudiance);
					aOIFormA2ConsultListing.setPublishedOn(aOIBVConsultListing.getPublishedOn());
	
		            aOIFormA2ConsultListing.setMailStatus(aOIBVConsultListing.getMailStatus());
		            //if (arOIBVConsultListingFeedBack!=null)
		            //{
			            //for (int j=0;j<arOIBVConsultListingFeedBack.size();j++)
			           // {
			                //OIBVConsultListingFeedBack aOIBVConsultListingFeedBack = (OIBVConsultListingFeedBack) arOIBVConsultListingFeedBack.get(j);
			                //if (aOIBVConsultListingFeedBack.getPaperID()==aOIBVConsultListing.getPaperId())
			                //{
			                    //aOIFormA2ConsultListing.setNoOfFeedbacks(aOIBVConsultListingFeedBack.getNoOfFeedbacks() + "");
			                    aOIFormA2ConsultListing.setNoOfFeedbacks(aOIBVConsultListing.getNoFeedBacks()+"");
			                //}
			           // }
		           // }
		            aOIFormA2ConsultListing.setPaperId(aOIBVConsultListing.getPaperId() + "");
		            aOIFormA2ConsultListing.setPaperTitle(aOIBVConsultListing.getPaperTitle());
		            aOIFormA2ConsultListing.setSecurity(aOIBVConsultListing.getSecurity());
		            aOIFormA1ConsultListing.addArOIFormA2ConsultListing(aOIFormA2ConsultListing);
	            }
	            if (aOIFormA1ConsultListing.arOIFormA2ConsultListing.size()==0)
	            {
	                aOIFormA1ConsultListing.arOIFormA2ConsultListing=null;
	            }
	        }
        }
        else
        {
            aOIFormConsultListing.setArOIFormA1ConsultListing(null);
        }
        request.setAttribute(OILoginConstants.PAGENAME,"ConsultListing");
        request.setAttribute(OIConsultConstant.CONSULT_LISTING_FORM,aOIFormConsultListing);
		request.setAttribute("hidSortKey", hidSortKey);
		request.setAttribute("hidOrder", hidOrder);
		
        return mapping.findForward(OIConsultConstant.POPULATE_CONSULTLISTING);
    }
}
