package com.oifm.consultation;

/*
 * Class Name:
 * Description:
 * 
 * 	Created By			Created/Modified on			Revision				Remarks
 * -----------------------------------------------------------------------------------------------------
 * 	Rajkumar			07/07/2005					Draft					Inital Version
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
import com.oifm.common.OIBAConfiguration;
import com.oifm.common.OIResponseObject;
import com.oifm.login.OILoginConstants;
import com.oifm.utility.DateUtility;
import com.oifm.utility.OIDBRegistry;
import com.oifm.utility.StringUtility;

public class OIActionConsultListing extends OIBaseAction 
{
    Logger logger = Logger.getLogger(OIActionConsultListing.class.getName());
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
        catch(Exception e)
        {}
        request.setAttribute(OILoginConstants.K_ERROR,error);
        return new ActionForward(OILoginConstants.ERRORPAGE);
    }

    /**
     * This method calls the readAllConsultPaper of OIBOConsultWeb 
     */
    public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        String userId = (String) getSessionAttribute(request,OILoginConstants.USER_ID);
        OIResponseObject aOIResponseObject =  new OIBOConsultWeb().readAllConsultPaper(userId);
        ArrayList messageList = (ArrayList) aOIResponseObject.getResponseObject(OILoginConstants.K_MESSAGE);
        String error = (String) aOIResponseObject.getResponseObject(OILoginConstants.K_ERROR);
        if (error != null)
        {
            request.setAttribute(OILoginConstants.K_ERROR,error);
            return new ActionForward(OILoginConstants.ERRORPAGE);
        }
        OIFormConsultListingWeb aOIFormConsultListingWeb = new OIFormConsultListingWeb();
        OIFormConsultListingWebHelper1 aOIFormConsultListingWebHelper1 = null;
        OIFormConsultListingWebHelper2 aOIFormConsultListingWebHelper2 = null;
        OIBVPaper aOIBVPaper = null;
        ArrayList arOIBVPaperPresent = (ArrayList) aOIResponseObject.getResponseObject(OIConsultConstant.K_arOIBVPaperPresent);
        ArrayList arOIBVPaperPast = (ArrayList) aOIResponseObject.getResponseObject(OIConsultConstant.K_arOIBVPaperPast);
 		OIBAConfiguration aOIBAConfiguration = (OIBAConfiguration) aOIResponseObject.getResponseObject("aOIBAConfiguration");
         if (arOIBVPaperPresent!=null)
        {
            int flagCategoryId=0;
            for (int i=0;i<arOIBVPaperPresent.size();i++)
            {

                aOIBVPaper = (OIBVPaper) arOIBVPaperPresent.get(i);

                if (aOIBVPaper.getCategoryId()==flagCategoryId)
                {
                    flagCategoryId = aOIBVPaper.getCategoryId();

                }
                else
                {

                    aOIFormConsultListingWebHelper1 = new OIFormConsultListingWebHelper1();
                    aOIFormConsultListingWebHelper1.setCategoryId(aOIBVPaper.getCategoryId() + "");
                    aOIFormConsultListingWebHelper1.setCategoryName(aOIBVPaper.getCategoryName());
                    aOIFormConsultListingWeb.addArOIBVPaperPresent(aOIFormConsultListingWebHelper1);
                    flagCategoryId = aOIBVPaper.getCategoryId();
                }

                aOIFormConsultListingWebHelper2 = new OIFormConsultListingWebHelper2();
                aOIFormConsultListingWebHelper2.setActive(aOIBVPaper.getActive());
                aOIFormConsultListingWebHelper2.setDescription(aOIBVPaper.getDescription());
                try
                {

                    aOIFormConsultListingWebHelper2.setExpireDate(DateUtility.getMMDDYYYYStringFromDate(aOIBVPaper.getExpireDate()));
                    aOIFormConsultListingWebHelper2.setStartDate(DateUtility.getMMDDYYYYStringFromDate(aOIBVPaper.getStartDate()));
                }
                catch(Exception e)
                {

                    logger.info(e.getMessage());
                }

                aOIFormConsultListingWebHelper2.setPaperId(aOIBVPaper.getPaperId()+"");
                aOIFormConsultListingWebHelper2.setPaperName(aOIBVPaper.getPaperName());
                aOIFormConsultListingWebHelper2.setSecurity(aOIBVPaper.getSecurity());
                aOIFormConsultListingWebHelper2.setStatus(aOIBVPaper.getStatus());

                aOIFormConsultListingWebHelper1.addArOIFormConsultListingWebHelper2(aOIFormConsultListingWebHelper2);
            }
        }
        else
        {

            aOIFormConsultListingWeb.setArOIBVPaperPresent(null);
        }

        
        if (arOIBVPaperPast!=null)
        {

            int flagCategoryId=0;
            for (int i=0;i<arOIBVPaperPast.size();i++)
            {

                aOIBVPaper = (OIBVPaper) arOIBVPaperPast.get(i);

                if (aOIBVPaper.getCategoryId()==flagCategoryId)
                {

                    flagCategoryId = aOIBVPaper.getCategoryId();
                }
                else
                {

                    aOIFormConsultListingWebHelper1 = new OIFormConsultListingWebHelper1();
                    aOIFormConsultListingWebHelper1.setCategoryId(aOIBVPaper.getCategoryId() + "");
                    aOIFormConsultListingWebHelper1.setCategoryName(aOIBVPaper.getCategoryName());
                    aOIFormConsultListingWeb.addArOIBVPaperPast(aOIFormConsultListingWebHelper1);
                    flagCategoryId = aOIBVPaper.getCategoryId();
                }

                aOIFormConsultListingWebHelper2 = new OIFormConsultListingWebHelper2();
                aOIFormConsultListingWebHelper2.setActive(aOIBVPaper.getActive());
                aOIFormConsultListingWebHelper2.setDescription(StringUtility.getNNoWords(aOIBVPaper.getDescription(),15).concat("..."));
                /*if (aOIBVPaper.getDescription()!=null && aOIBVPaper.getDescription().length()>50)
                    aOIFormConsultListingWebHelper2.setDescription(aOIBVPaper.getDescription().substring(0,50).concat("..."));
                else
                    aOIFormConsultListingWebHelper2.setDescription(aOIBVPaper.getDescription());*/
                try
                {

                    aOIFormConsultListingWebHelper2.setExpireDate(DateUtility.getMMDDYYYYStringFromDate(aOIBVPaper.getExpireDate()));
                    aOIFormConsultListingWebHelper2.setStartDate(DateUtility.getMMDDYYYYStringFromDate(aOIBVPaper.getStartDate()));
                }
                catch(Exception e)
                {

                    logger.info(e.getMessage());
                }
                aOIFormConsultListingWebHelper2.setPaperId(aOIBVPaper.getPaperId()+"");
                aOIFormConsultListingWebHelper2.setPaperName(aOIBVPaper.getPaperName());
                aOIFormConsultListingWebHelper2.setSecurity(aOIBVPaper.getSecurity());
                aOIFormConsultListingWebHelper2.setStatus(aOIBVPaper.getStatus());
                
                aOIFormConsultListingWebHelper1.addArOIFormConsultListingWebHelper2(aOIFormConsultListingWebHelper2);
            }
        }
        else
        {

            aOIFormConsultListingWeb.setArOIBVPaperPast(null);
        }
        if(aOIBAConfiguration != null && aOIBAConfiguration.getValue()!= null){
        	aOIBAConfiguration.setValue(aOIBAConfiguration.getValue().replaceAll("\n","<br>"));}

        request.setAttribute(OIConsultConstant.CONSULT_LISTING_PAPER_FORM_WEB,aOIFormConsultListingWeb);

        if(aOIBAConfiguration != null && aOIBAConfiguration.getValue()!= null){

        	request.setAttribute("aOIBAConfiguration",aOIBAConfiguration);
        }else{new OIBAConfiguration();}
        
        request.setAttribute(OILoginConstants.PAGENAME,"ConsultListing");
        return mapping.findForward(OIConsultConstant.POPULATE_CONSULTLISTING);
    }
}
