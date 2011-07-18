package com.oifm.consultation.admin;
/*
 * Class Name:
 * Description:
 * 
 * 	Created By			Created/Modified on			Revision				Remarks
 * -----------------------------------------------------------------------------------------------------
 * 	Rajkumar			02/07/2005					Draft					Inital Version
 * 
 * -----------------------------------------------------------------------------------------------------
 */

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.apache.struts.util.LabelValueBean;

import com.oifm.base.OIBaseAction;
import com.oifm.common.OIBOAddTag;
import com.oifm.common.OIResponseObject;
import com.oifm.consultation.OIConsultConstant;
import com.oifm.login.OILoginConstants;
import com.oifm.utility.DateUtility;
import com.oifm.utility.OIDBRegistry;
import com.oifm.utility.OIFileUtility;
public class OIActionConsultCreatePaper extends OIBaseAction 
{
    Logger logger=Logger.getLogger(OIActionConsultCreatePaper.class.getName());
    private static final String POPULATE_ACTION = "populate";
    private static final String SAVE_ACTION = "save";
    public ActionForward doIt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String actionName)
    {
        if (actionName!=null)
        {
	        if (actionName.equals(POPULATE_ACTION))
	        {
	            return populate(mapping, form, request, response);
	        }
	        if (actionName.equals(SAVE_ACTION))
	        {
	            return save(mapping, form, request, response);
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
     * This method will call the OIBOConsultPaper.savePaper() & pass OIBAConsultPaper as the input parameter
     * 
     * If save is unsuccessful then the error has to be shown in the same page
     * 
     * Otherwise it should display the Consultation Paper Listing page. 
     */
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        OIFormConsultPaper aOIFormConsultPaper = (OIFormConsultPaper) form;
        FormFile aFormFile = aOIFormConsultPaper.getFileName();
        String fileName = null;
        OIBOAddTag tagBO = new OIBOAddTag();
        
        if (aFormFile!=null && (! aFormFile.getFileName().equals("")))
        {
	        try 
	        {
	            fileName= aFormFile.getFileName();
	            
	            if (! fileName.toLowerCase().endsWith(OIDBRegistry.getValues("OI.CONS.EXT")))
	            {
	                throw new Exception(OIDBRegistry.getValues("OI.CONS.PDF"));
	            }
	
	            //retrieve the content type
	            String contentType = aFormFile.getContentType();
	
	            //boolean writeFile = theForm.getWriteFile();
	            boolean writeFile = true;
	
	            //retrieve the file size
	            String size = (aFormFile.getFileSize() + OIDBRegistry.getValues("OI.CONS.BYTES"));
	
	            String data = null;
	
	            //retrieve the file data
	            ByteArrayOutputStream baos = new ByteArrayOutputStream();
	            //InputStream stream = aFormFile.getInputStream();
                if (aFormFile.getFileSize() > (2*1024*1024)) 
                {
                    data = new String(OIDBRegistry.getValues("OI.CONS.FILESIZE") + aFormFile.getFileSize() + OIDBRegistry.getValues("OI.CONS.BYTES"));
                    throw new Exception(data);
                }
	            //fileName = OIFileUtility.generateFileName("CPAT",paperId,".pdf");
	            logger.info(data);
	            //close the stream
	            //stream.close();
	        }
	        catch (Exception fnfe) 
	        {
	            OIResponseObject oIResponseObject = new OIBOConsultCategory().readAllCategory();
	            ArrayList arOIBAConsultCategory = (ArrayList) oIResponseObject.getResponseObject(OIConsultConstant.K_arOIBAConsultCategory);
	            String str=null;
	            try
	            {
	                str = OIDBRegistry.getValues("OI.CONS.CATEGORY.SELECT");
	            }catch(Exception e){}
	            aOIFormConsultPaper.addArCategoryID(new LabelValueBean(str,""));
	            for (int i=0;i<arOIBAConsultCategory.size();i++)
	            {
	                OIBAConsultCategory aOIBAConsultCategory = (OIBAConsultCategory) arOIBAConsultCategory.get(i);
	                aOIFormConsultPaper.addArCategoryID(new LabelValueBean(aOIBAConsultCategory.getName(),aOIBAConsultCategory.getCategoryID()+""));
	            }

	            logger.error(fnfe.getMessage());
	            aOIFormConsultPaper.setPaperId(null);
	            request.setAttribute(OILoginConstants.K_ERROR,fnfe.getMessage());
	            request.setAttribute(OIConsultConstant.CONSULT_CREATE_PAPER_FORM,aOIFormConsultPaper);
	            request.setAttribute(OILoginConstants.PAGENAME,"consultPaper");
	            return mapping.findForward(OIConsultConstant.POPULATE_CONSULTLISTING);
	        }

	        //destroy the temporary file created
	        aFormFile.destroy();
        }
        //****************************************************************************************************8
        OIBAConsultPaper aOIBAConsultPaper = new OIBAConsultPaper();
        aOIBAConsultPaper.setCategoryID(Integer.parseInt(aOIFormConsultPaper.getCategoryID()));
        aOIBAConsultPaper.setAttachedFile(fileName);
        aOIBAConsultPaper.setCreatedBy((String) getSessionAttribute(request,OILoginConstants.USER_ID));
        aOIBAConsultPaper.setDescription(aOIFormConsultPaper.getDescription());
        Date fromDt=null,toDt=null;
        try
        {
            fromDt = DateUtility.getDateFromMMDDYYYYString(aOIFormConsultPaper.getFromDt());
            toDt = DateUtility.getDateFromMMDDYYYYString(aOIFormConsultPaper.getToDt());
        }
        catch(Exception e)
        {
            
        }
        aOIBAConsultPaper.setFromDt(fromDt);
        aOIBAConsultPaper.setToDt(toDt);
        aOIBAConsultPaper.setMailStatus(aOIFormConsultPaper.getMailStatus());
        aOIBAConsultPaper.setSecurity(aOIFormConsultPaper.getSecurity());
        //logger.info(aOIFormConsultPaper.getSecurity());
        aOIBAConsultPaper.setSummary(aOIFormConsultPaper.getSummary());
        aOIBAConsultPaper.setTargetAudiance(aOIFormConsultPaper.getTargetAudiance());
		aOIBAConsultPaper.setContactPerson(aOIFormConsultPaper.getContactPerson());
		aOIBAConsultPaper.setPhone(aOIFormConsultPaper.getPhone());
		aOIBAConsultPaper.setEmailAddress(aOIFormConsultPaper.getEmailAddress());
        aOIBAConsultPaper.setTitle(aOIFormConsultPaper.getTitle());

		//NEW FIELDS
		aOIBAConsultPaper.setStrInstruction(aOIFormConsultPaper.getStrInstruction());
		aOIBAConsultPaper.setStrCompletionTime(aOIFormConsultPaper.getStrCompletionTime());
		aOIBAConsultPaper.setStrFindingsPlannedDt(aOIFormConsultPaper.getStrFindingsPlannedDt());
		aOIBAConsultPaper.setStrViewFindingType(aOIFormConsultPaper.getStrViewFindingType());
		aOIBAConsultPaper.setPublishStatus(aOIFormConsultPaper.getPublishStatus());
		aOIBAConsultPaper.setStrOpenTag(aOIFormConsultPaper.getStrOpenTag());
		aOIBAConsultPaper.setStrTagWords(aOIFormConsultPaper.getStrTagWords());
		aOIBAConsultPaper.setEmailAddress(aOIFormConsultPaper.getEmailAddress());
		aOIBAConsultPaper.setStrEmailDate(aOIFormConsultPaper.getStrEmailDate());
		aOIBAConsultPaper.setStrEmailMessage(aOIFormConsultPaper.getStrEmailMessage());
		aOIBAConsultPaper.setStrTargetGpIds(aOIFormConsultPaper.getStrTargetGpIds());

        //aOIBAConsultPaper.setToDt(new Date(aOIFormConsultPaper.getToDt()));
        
        if (aOIFormConsultPaper.getActive()!= null && aOIFormConsultPaper.getActive().equals("on"))
        {
            aOIBAConsultPaper.setActive("1");
        }
        else
        {
            aOIBAConsultPaper.setActive("0");
        }
        //logger.info(aOIFormConsultPaper.getReminderMode());
        aOIBAConsultPaper.setReminderMode(aOIFormConsultPaper.getReminderMode());
        if (getSessionAttribute(request,OILoginConstants.DIVCODE)!=null)
            aOIBAConsultPaper.setDivCode((String)getSessionAttribute(request,OILoginConstants.DIVCODE));
        
        OIResponseObject aOIResponseObject = new OIBOConsultPaper().savePaper(aOIBAConsultPaper);
        
        
        
        
        ArrayList messageList = (ArrayList) aOIResponseObject.getResponseObject(OILoginConstants.K_MESSAGE);
        String error = (String) aOIResponseObject.getResponseObject(OILoginConstants.K_ERROR);
        if (error != null)
        {
            OIResponseObject oIResponseObject = new OIBOConsultCategory().readAllCategory();
            ArrayList arOIBAConsultCategory = (ArrayList) oIResponseObject.getResponseObject(OIConsultConstant.K_arOIBAConsultCategory);
            String str=null;
            try
            {
                str = OIDBRegistry.getValues("OI.CONS.CATEGORY.SELECT");
            }catch(Exception e){}
            aOIFormConsultPaper.addArCategoryID(new LabelValueBean(str,""));
           for (int i=0;i<arOIBAConsultCategory.size();i++)
            {
                OIBAConsultCategory aOIBAConsultCategory = (OIBAConsultCategory) arOIBAConsultCategory.get(i);
                aOIFormConsultPaper.addArCategoryID(new LabelValueBean(aOIBAConsultCategory.getName(),aOIBAConsultCategory.getCategoryID()+""));
            }
            
            aOIFormConsultPaper.setPaperId(null);
            request.setAttribute(OILoginConstants.K_ERROR,error);
            request.setAttribute(OIConsultConstant.CONSULT_CREATE_PAPER_FORM,aOIFormConsultPaper);
            request.setAttribute(OILoginConstants.PAGENAME,"consultPaper");
            return mapping.findForward(OIConsultConstant.POPULATE_CONSULTLISTING);
        }
        
        aOIBAConsultPaper = (OIBAConsultPaper) aOIResponseObject.getResponseObject("aOIBAConsultPaper");
        
        ArrayList alTagNames = convertToTagList(aOIFormConsultPaper.getStrTagIdList());
		//System.out.println("OIActionConsultCreatePaper: doIt - alTagNames : "+alTagNames.size());
		//if(strSurveyId!=null && strSurveyId!="")
			tagBO.updateTag((aOIBAConsultPaper.getPaperId() + ""), alTagNames, "CP");
        
        
        aOIFormConsultPaper.setPaperId(aOIBAConsultPaper.getPaperId() + "");
		try
		{
		    if (fileName!=null)
		    {
			    String data=null;
			    InputStream stream = aFormFile.getInputStream();
		        if (aFormFile.getFileSize() < (2*1024*1024)) 
		        {
		            String desFileName = OIDBRegistry.getValues("OI.PAPER");
		            String paperId="";
		            if (aOIFormConsultPaper.getPaperId()!=null)
		                paperId = aOIFormConsultPaper.getPaperId();
		            fileName = OIFileUtility.generateFileName(OIDBRegistry.getValues("OI.CONS.FILENAMEPREFIX"),paperId,OIDBRegistry.getValues("OI.CONS.EXT"));
		            OIFileUtility.writeFile(stream,desFileName,fileName);
		            data = OIDBRegistry.getValues("OI.CONS.FILEMESSAGE") + desFileName;
		        }
		        else
		        {
		            data = new String(OIDBRegistry.getValues("OI.CONS.FILESIZE") + aFormFile.getFileSize() + OIDBRegistry.getValues("OI.CONS.BYTES"));
		            throw new Exception(data);
		        }
			    logger.info(data);
			    stream.close();
		    }
		    aOIBAConsultPaper.setAttachedFile(fileName);
	        aOIResponseObject = new OIBOConsultPaper().savePaper(aOIBAConsultPaper);
		}
		catch (Exception fnfe) 
		{
		    OIResponseObject oIResponseObject = new OIBOConsultCategory().readAllCategory();
		    ArrayList arOIBAConsultCategory = (ArrayList) oIResponseObject.getResponseObject(OIConsultConstant.K_arOIBAConsultCategory);
            String str=null;
            try
            {
                str = OIDBRegistry.getValues("OI.CONS.CATEGORY.SELECT");
            }catch(Exception e){}
            aOIFormConsultPaper.addArCategoryID(new LabelValueBean(str,""));
		    for (int i=0;i<arOIBAConsultCategory.size();i++)
		    {
		        OIBAConsultCategory aOIBAConsultCategory = (OIBAConsultCategory) arOIBAConsultCategory.get(i);
		        aOIFormConsultPaper.addArCategoryID(new LabelValueBean(aOIBAConsultCategory.getName(),aOIBAConsultCategory.getCategoryID()+""));
		    }
		
		    logger.error(fnfe.getMessage());
		    //aOIFormConsultPaper.setPaperId(null);
		    request.setAttribute(OILoginConstants.K_ERROR,fnfe.getMessage());
		    request.setAttribute(OIConsultConstant.CONSULT_CREATE_PAPER_FORM,aOIFormConsultPaper);
		    request.setAttribute(OILoginConstants.PAGENAME,"consultPaper");
		    return mapping.findForward(OIConsultConstant.POPULATE_CONSULTLISTING);
		}
		
		if (request.getParameter("flag")!=null)
		{
		    return new ActionForward("/consultViewModifyPageAction.do?hiddenAction=populate&paperId=" + aOIFormConsultPaper.getPaperId());
		}
		
        return new ActionForward("/consultListingAction.do?hiddenAction=populate");
    }
    
    /**
     * This method calls the readAllCategory of OIBOConsultCategory 
     */
    public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        OIResponseObject oIResponseObject = new OIBOConsultCategory().readAllCategory();
        ArrayList messageList = (ArrayList) oIResponseObject.getResponseObject(OILoginConstants.K_MESSAGE);
        String error = (String) oIResponseObject.getResponseObject(OILoginConstants.K_ERROR);
        OIFormConsultPaper aOIFormConsultPaper = new OIFormConsultPaper();
        ArrayList arOIBAConsultCategory = (ArrayList) oIResponseObject.getResponseObject(OIConsultConstant.K_arOIBAConsultCategory);
        
        removeSessionAttribute(request, "alTagNames");
		removeSessionAttribute(request, "oiFormBlogTag");
        
        
        if (error != null)
        {
            request.setAttribute(OILoginConstants.K_ERROR,error);
            request.setAttribute(OIConsultConstant.CONSULT_CREATE_PAPER_FORM,form);
            request.setAttribute(OILoginConstants.PAGENAME,"consultPaper");
            return new ActionForward("/consultCreatePageAction.do?hiddenAction=populate");
        }
        String str=null;
        try
        {
            str = OIDBRegistry.getValues("OI.CONS.CATEGORY.SELECT");
        }catch(Exception e){}
        aOIFormConsultPaper.addArCategoryID(new LabelValueBean(str,""));
        for (int i=0;i<arOIBAConsultCategory.size();i++)
        {
            OIBAConsultCategory aOIBAConsultCategory = (OIBAConsultCategory) arOIBAConsultCategory.get(i);
            aOIFormConsultPaper.addArCategoryID(new LabelValueBean(aOIBAConsultCategory.getName(),aOIBAConsultCategory.getCategoryID()+""));
        }
        try
        {
            aOIFormConsultPaper.setFromDt(DateUtility.getMMDDYYYYStringFromDate(new Date()));
        }
        catch(Exception e)
        {
            
        }
        aOIFormConsultPaper.setSecurity("R");
        aOIFormConsultPaper.setReminderMode("M");
        aOIFormConsultPaper.setReadOnlyFlag("false");
        aOIFormConsultPaper.setStrOpenTag("N");
        aOIFormConsultPaper.setStrDefaultEmailMessage(new OIBOConsultPaper().getPaperDefaultMessage());
        request.setAttribute(OIConsultConstant.CONSULT_CREATE_PAPER_FORM,aOIFormConsultPaper);
        request.setAttribute(OILoginConstants.PAGENAME,"consultPaper");
        return mapping.findForward(OIConsultConstant.POPULATE_CONSULTLISTING);
    }
    
    private ArrayList convertToTagList(String strTagIdList){
		ArrayList tags = new ArrayList();
		String [] tagIds = strTagIdList.split(",");
		for(int i=0; i<tagIds.length; i++)
		{
			tags.add(tagIds[i]);
		}
		
		return tags;
	}
}
