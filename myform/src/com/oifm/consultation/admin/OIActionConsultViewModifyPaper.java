package com.oifm.consultation.admin;
/*
 * Class Name:
 * Description:
 * 
 * 	Created By			Created/Modified on			Revision				Remarks
 * -----------------------------------------------------------------------------------------------------
 * 	Rajkumar			05/07/2005					Draft					Inital Version
 * 
 * -----------------------------------------------------------------------------------------------------
 */

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.apache.struts.util.LabelValueBean;

import com.oifm.base.OIBaseAction;
import com.oifm.common.OIBAAddTag;
import com.oifm.common.OIBOAddTag;
import com.oifm.common.OIDownloadHelper;
import com.oifm.common.OIResponseObject;
import com.oifm.consultation.OIConsultConstant;
import com.oifm.login.OILoginConstants;
import com.oifm.survey.OIBASurvey;
import com.oifm.survey.OISurveyConstants;
import com.oifm.survey.admin.OIBOSurveyAdmin;
import com.oifm.utility.DateUtility;
import com.oifm.utility.OIDBRegistry;
import com.oifm.utility.OIFileUtility;

public class OIActionConsultViewModifyPaper extends OIBaseAction 
{
    Logger logger=Logger.getLogger(OIActionConsultViewModifyPaper.class.getName());
    private static final String POPULATE_ACTION = "populate";
    private static final String REMOVE_QUESTION_ACTION = "removeQuestion";
    private static final String REMOVE_ATTACHMENT_ACTION = "removeAttachment";
    private static final String UPDATE_ACTION = "update";
    private static final String DELETE_ACTION = "delete";
	private static final String IMPORT_ACTION = "import";
	private static final String QUESTION_MOVE_UP = "QUESTION_MOVE_UP";
	private static final String QUESTION_MOVE_DOWN = "QUESTION_MOVE_DOWN";

//  added by edmund
    private static final String RESULT_ACTION = "result";
    private static final String RESPONDENTS = "respondents";

    public ActionForward doIt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String actionName)
    {
        if (actionName!=null)
        {
        	if (actionName.equals(POPULATE_ACTION))
	        {
	            return populate(mapping, form, request, response);
	        }
	        if (actionName.equals(REMOVE_QUESTION_ACTION))
	        {
	            return removeQuestion(mapping, form, request, response);
	        }
	        if (actionName.equals(REMOVE_ATTACHMENT_ACTION))
	        {
	            return removeAttachment(mapping, form, request, response);
	        }
	        if (actionName.equals(UPDATE_ACTION))
	        {
	            return update(mapping, form, request, response);
	        }
	        if (actionName.equals(DELETE_ACTION))
	        {
	            return delete(mapping, form, request, response);
	        }
	        if (actionName.equals("downLoad"))
	        {
	            return downLoad(mapping, form, request, response);
	        }
			if (actionName.equals(IMPORT_ACTION))
	        {
	            return importPaper(mapping, form, request, response);
	        }
			if (actionName.equals(QUESTION_MOVE_UP) || actionName.equals(QUESTION_MOVE_DOWN))
	        {
	            return reorderQuestion(mapping, form, request, response,actionName);
	     	}
	        //added by edmund
	        if (actionName.equals(RESULT_ACTION))
	        {
	            return resultSelection(mapping, form, request, response);
	        }
	        if (actionName.equals(RESPONDENTS))
	        {
	            return getRespondentsList(mapping, form, request, response);
	        }
	        if (actionName.equals("summary"))
	        {
	            return getReportSummary(mapping, form, request, response);
	        }
	        if (actionName.equals("detail"))
	        {
	            return getReportSelection(mapping, form, request, response);
	        }
	        if (actionName.equals("selection"))
	        {
	            return getDetailReport(mapping, form, request, response);
	        }
	        if (actionName.equals("otherQuestion"))
	        {
	            return getDetailQuestion(mapping, form, request, response);
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
    
    /**
     * This method will call the OIBOConsultPaper.savePaper() & pass OIBAConsultPaper as the input parameter
     * 
     * If save is unsuccessful then the error has to be shown in the same page
     * 
     * Otherwise it should display the Consultation Paper Listing page. 
     */
    public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        OIFormConsultPaper aOIFormConsultPaper = (OIFormConsultPaper) form;
        FormFile aFormFile = aOIFormConsultPaper.getFileName();
        String fileName = null;
        OIBOAddTag tagBO = new OIBOAddTag();
        OIBAConsultPaper aOIBAConsultPaper = new OIBAConsultPaper();

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
	            //close the stream
	            stream.close();
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
	            OIResponseObject aOIResponseObject = new OIBOConsultPaper().readPaper(Integer.parseInt(aOIFormConsultPaper.getPaperId().trim()));
	            ArrayList arOIBAConsultQuestion = (ArrayList) aOIResponseObject.getResponseObject(OIConsultConstant.K_arOIBAConsultQuestion);
	            OIFormConsultPaperHelper aOIFormConsultPaperHelper=null;
	            OIBAConsultQuestion aOIBAConsultQuestion=null;
	            if (arOIBAConsultQuestion!=null)
	            {
	                for (int i=0;i<arOIBAConsultQuestion.size();i++)
	    	        {
	    	            aOIBAConsultQuestion = (OIBAConsultQuestion) arOIBAConsultQuestion.get(i);
	    	            aOIFormConsultPaperHelper = new OIFormConsultPaperHelper();
	    	            aOIFormConsultPaperHelper.setAnswer1(aOIBAConsultQuestion.getAnswer1());
	    	            aOIFormConsultPaperHelper.setAnswer2(aOIBAConsultQuestion.getAnswer2());
	    	            aOIFormConsultPaperHelper.setAnswer3(aOIBAConsultQuestion.getAnswer3());
	    	            aOIFormConsultPaperHelper.setAnswer4(aOIBAConsultQuestion.getAnswer4());
	    	            aOIFormConsultPaperHelper.setAnswer5(aOIBAConsultQuestion.getAnswer5());
	    	            aOIFormConsultPaperHelper.setNeedOtherRemark(aOIBAConsultQuestion.getNeedOtherRemark());
	    	            aOIFormConsultPaperHelper.setQuestion(aOIBAConsultQuestion.getQuestion());
	    	            aOIFormConsultPaperHelper.setQuestionId(aOIBAConsultQuestion.getQuestionId() + "");
	    	            aOIFormConsultPaperHelper.setAnswerType(aOIBAConsultQuestion.getAnswerType());
						aOIFormConsultPaperHelper.setLikertScale(aOIBAConsultQuestion.getLikertScale());
						aOIFormConsultPaperHelper.setUseSameLikert(aOIBAConsultQuestion.getUseSameLikert());
	    	            aOIFormConsultPaperHelper.setPaperId(aOIBAConsultQuestion.getPaperId() + "");
	    	            aOIFormConsultPaperHelper.setQuestionNo(i+1 + "");
	    	            aOIFormConsultPaper.addArOIBAConsultQuestion(aOIFormConsultPaperHelper);
	    	        }
	            }

	            logger.info(fnfe.getMessage());
	            request.setAttribute(OILoginConstants.K_ERROR,fnfe.getMessage());
	            request.setAttribute(OIConsultConstant.CONSULT_CREATE_PAPER_FORM,aOIFormConsultPaper);
	            request.setAttribute(OILoginConstants.PAGENAME,"consultPaper");
	            return mapping.findForward(OIConsultConstant.POPULATE_CONSULTLISTING);
	        }

	        //destroy the temporary file created
	        aFormFile.destroy();
        }
        if (aOIFormConsultPaper.getPaperId()!=null)
            aOIBAConsultPaper.setPaperId(Integer.parseInt(aOIFormConsultPaper.getPaperId()));
        aOIBAConsultPaper.setCategoryID(Integer.parseInt(aOIFormConsultPaper.getCategoryID()));
        if (fileName == null && aOIFormConsultPaper.getAttachedFile()!=null)
            aOIBAConsultPaper.setAttachedFile(aOIFormConsultPaper.getAttachedFile());
        else
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
        
        if (aOIFormConsultPaper.getActive()!= null && aOIFormConsultPaper.getActive().equals("on"))
        {
            aOIBAConsultPaper.setActive("1");
        }
        else
        {
            aOIBAConsultPaper.setActive("0");
        }
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
            aOIResponseObject = new OIBOConsultPaper().readPaper(Integer.parseInt(aOIFormConsultPaper.getPaperId().trim()));
            ArrayList arOIBAConsultQuestion = (ArrayList) aOIResponseObject.getResponseObject(OIConsultConstant.K_arOIBAConsultQuestion);
            OIFormConsultPaperHelper aOIFormConsultPaperHelper=null;
            OIBAConsultQuestion aOIBAConsultQuestion=null;
            if (arOIBAConsultQuestion!=null)
            {
                for (int i=0;i<arOIBAConsultQuestion.size();i++)
    	        {
    	            aOIBAConsultQuestion = (OIBAConsultQuestion) arOIBAConsultQuestion.get(i);
    	            aOIFormConsultPaperHelper = new OIFormConsultPaperHelper();
    	            aOIFormConsultPaperHelper.setAnswer1(aOIBAConsultQuestion.getAnswer1());
    	            aOIFormConsultPaperHelper.setAnswer2(aOIBAConsultQuestion.getAnswer2());
    	            aOIFormConsultPaperHelper.setAnswer3(aOIBAConsultQuestion.getAnswer3());
    	            aOIFormConsultPaperHelper.setAnswer4(aOIBAConsultQuestion.getAnswer4());
    	            aOIFormConsultPaperHelper.setAnswer5(aOIBAConsultQuestion.getAnswer5());
    	            aOIFormConsultPaperHelper.setNeedOtherRemark(aOIBAConsultQuestion.getNeedOtherRemark());
    	            aOIFormConsultPaperHelper.setQuestion(aOIBAConsultQuestion.getQuestion());
    	            aOIFormConsultPaperHelper.setQuestionId(aOIBAConsultQuestion.getQuestionId() + "");
    	            aOIFormConsultPaperHelper.setAnswerType(aOIBAConsultQuestion.getAnswerType());
					aOIFormConsultPaperHelper.setLikertScale(aOIBAConsultQuestion.getLikertScale());
					aOIFormConsultPaperHelper.setUseSameLikert(aOIBAConsultQuestion.getUseSameLikert());
    	            aOIFormConsultPaperHelper.setPaperId(aOIBAConsultQuestion.getPaperId() + "");
    	            aOIFormConsultPaperHelper.setQuestionNo(i+1 + "");
    	            aOIFormConsultPaper.addArOIBAConsultQuestion(aOIFormConsultPaperHelper);
    	        }
            }

            request.setAttribute(OILoginConstants.K_ERROR,error);
            request.setAttribute(OIConsultConstant.CONSULT_CREATE_PAPER_FORM,aOIFormConsultPaper);
            request.setAttribute(OILoginConstants.PAGENAME,"consultPaper");
            return mapping.findForward(OIConsultConstant.POPULATE_CONSULTLISTING);
        }
        else{
        	ArrayList alTagNames = convertToTagList(aOIFormConsultPaper.getStrTagIdList());
    		//if(strSurveyId!=null && strSurveyId!="")
    			tagBO.updateTag((aOIBAConsultPaper.getPaperId() + ""), alTagNames, "CP");
    			
    			removeSessionAttribute(request, "alTagNames");
    			removeSessionAttribute(request, "oiFormBlogTag");
        }
        
        return new ActionForward("/consultListingAction.do?hiddenAction=populate");
    }
    
    /**
     * This method calls the readPaper of OIBOConsultPaper, which passes paper ID as parameter 
     */
    public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
    	String paperId = request.getParameter(OIConsultConstant.K_paperId);
        String tags = "";
        String tagIds = "";
        OIBOAddTag tagBO = new OIBOAddTag();
        OIResponseObject aOIResponseObject2 = null;
        
        OIResponseObject aOIResponseObject = new OIBOConsultPaper().readPaper(Integer.parseInt(paperId.trim()));
        OIFormConsultPaper aOIFormConsultPaper = new OIFormConsultPaper(); 
        
        removeSessionAttribute(request, "alTagNames");
		removeSessionAttribute(request, "oiFormBlogTag");
		
		if(paperId != null)
			aOIResponseObject2 = tagBO.getTags(paperId,"CP");
		
		
		ArrayList tagList = (ArrayList)aOIResponseObject2.getResponseObject("tagList");
		if(tagList != null){
			for (Iterator iter = tagList.iterator(); iter.hasNext();)
			{
				OIBAAddTag tag = (OIBAAddTag) iter.next();
				tags += tag.getTagName() + ",";	
				tagIds += tag.getTagId() + ",";
			}
			//aOIFormConsultPaper.setStrTagWords(tags);
			setSessionAttribute(request, "alTagNames",tagList);
		}
        
        ArrayList messageList = (ArrayList) aOIResponseObject.getResponseObject(OILoginConstants.K_MESSAGE);
        String error = (String) aOIResponseObject.getResponseObject(OILoginConstants.K_ERROR);
        if (error != null)
        {
            request.setAttribute(OILoginConstants.K_ERROR,error);
            return new ActionForward(OILoginConstants.ERRORPAGE);
        }
        ArrayList arOIBAConsultCategory = (ArrayList) aOIResponseObject.getResponseObject(OIConsultConstant.K_arOIBAConsultCategory);
        ArrayList arOIBAConsultQuestion = (ArrayList) aOIResponseObject.getResponseObject(OIConsultConstant.K_arOIBAConsultQuestion);
        String publishFlag = (String) aOIResponseObject.getResponseObject("publishFlag");
        
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
        OIBAConsultPaper aOIBAConsultPaper = (OIBAConsultPaper) aOIResponseObject.getResponseObject(OIConsultConstant.K_aOIBAConsultPaper);
        if (aOIBAConsultPaper.getActive() != null && aOIBAConsultPaper.getActive().equals("1"))
            aOIFormConsultPaper.setActive("on");
        else
            aOIFormConsultPaper.setActive("off");
        
        aOIFormConsultPaper.setAttachedFile(aOIBAConsultPaper.getAttachedFile());
        aOIFormConsultPaper.setCategoryID(aOIBAConsultPaper.getCategoryID() + "");
        aOIFormConsultPaper.setDescription(aOIBAConsultPaper.getDescription());
        //aOIFormConsultPaper.setFileName(aOIBAConsultPaper.getActive());
        String textFromDt=null,testToDt=null;
        try
        {
            if (aOIBAConsultPaper.getFromDt()!=null && (!aOIBAConsultPaper.getFromDt().equals("")))
                textFromDt = DateUtility.getMMDDYYYYStringFromDate(aOIBAConsultPaper.getFromDt());
            if (aOIBAConsultPaper.getToDt()!=null && (!aOIBAConsultPaper.getToDt().equals("")))
                testToDt = DateUtility.getMMDDYYYYStringFromDate(aOIBAConsultPaper.getToDt());
        }
        catch(Exception e)
        {
            
        }
        aOIFormConsultPaper.setFromDt(textFromDt);
        aOIFormConsultPaper.setToDt(testToDt);
        aOIFormConsultPaper.setMailStatus(aOIBAConsultPaper.getMailStatus());
        aOIFormConsultPaper.setPaperId(aOIBAConsultPaper.getPaperId() + "");
        aOIFormConsultPaper.setReminderMode(aOIBAConsultPaper.getReminderMode());
        aOIFormConsultPaper.setSecurity(aOIBAConsultPaper.getSecurity());
        aOIFormConsultPaper.setSummary(aOIBAConsultPaper.getSummary());
        aOIFormConsultPaper.setTargetAudiance(aOIBAConsultPaper.getTargetAudiance());
		aOIFormConsultPaper.setContactPerson(aOIBAConsultPaper.getContactPerson());
		aOIFormConsultPaper.setPhone(aOIBAConsultPaper.getPhone());
		aOIFormConsultPaper.setEmailAddress(aOIBAConsultPaper.getEmailAddress());
        aOIFormConsultPaper.setTitle(aOIBAConsultPaper.getTitle());
        aOIFormConsultPaper.setStrDefaultEmailMessage(new OIBOConsultPaper().getPaperDefaultMessage());

		//NEW FIELDS
		aOIFormConsultPaper.setStrInstruction(aOIBAConsultPaper.getStrInstruction());
		aOIFormConsultPaper.setStrCompletionTime(aOIBAConsultPaper.getStrCompletionTime());
		aOIFormConsultPaper.setStrFindingsPlannedDt(aOIBAConsultPaper.getStrFindingsPlannedDt());
		aOIFormConsultPaper.setStrViewFindingType(aOIBAConsultPaper.getStrViewFindingType());
		aOIFormConsultPaper.setPublishStatus(aOIBAConsultPaper.getPublishStatus());
		aOIFormConsultPaper.setStrOpenTag(aOIBAConsultPaper.getStrOpenTag());
		aOIFormConsultPaper.setStrTagWords(aOIBAConsultPaper.getStrTagWords());
		aOIFormConsultPaper.setEmailAddress(aOIBAConsultPaper.getEmailAddress());
		aOIFormConsultPaper.setStrEmailDate(aOIBAConsultPaper.getStrEmailDate());
		aOIFormConsultPaper.setStrEmailMessage(aOIBAConsultPaper.getStrEmailMessage());
		aOIFormConsultPaper.setStrTargetGpIds(aOIBAConsultPaper.getStrTargetGpIds());
		
		aOIFormConsultPaper.setStrTagWords(tags);
		aOIFormConsultPaper.setStrTagIdList(tagIds);
		
		if (getSessionAttribute(request,"divCode")!= null && ((String) getSessionAttribute(request,"divCode")).equals(aOIBAConsultPaper.getDivCode()))
        {
            aOIFormConsultPaper.setReadOnlyFlag("false");
        }
        else
        {
            aOIFormConsultPaper.setReadOnlyFlag("true");
        }
        ArrayList arList = (ArrayList) getSessionAttribute(request,OILoginConstants.FUNCTION_LIST);
        if (arList!=null)
        {
	        for (int i=0;i<arList.size();i++)
	        {
	            String functionId = (String) arList.get(i);
	            if (functionId.equalsIgnoreCase("MTNCTBD"))
	            {
	                aOIFormConsultPaper.setReadOnlyFlag("false");
	            }
	        }
        }
        OIFormConsultPaperHelper aOIFormConsultPaperHelper=null;
        OIBAConsultQuestion aOIBAConsultQuestion=null;
        if (arOIBAConsultQuestion!=null)
        {
            for (int i=0;i<arOIBAConsultQuestion.size();i++)
	        {
	            aOIBAConsultQuestion = (OIBAConsultQuestion) arOIBAConsultQuestion.get(i);
	            aOIFormConsultPaperHelper = new OIFormConsultPaperHelper();
	            aOIFormConsultPaperHelper.setAnswer1(aOIBAConsultQuestion.getAnswer1());
	            aOIFormConsultPaperHelper.setAnswer2(aOIBAConsultQuestion.getAnswer2());
	            aOIFormConsultPaperHelper.setAnswer3(aOIBAConsultQuestion.getAnswer3());
	            aOIFormConsultPaperHelper.setAnswer4(aOIBAConsultQuestion.getAnswer4());
	            aOIFormConsultPaperHelper.setAnswer5(aOIBAConsultQuestion.getAnswer5());
	            aOIFormConsultPaperHelper.setNeedOtherRemark(aOIBAConsultQuestion.getNeedOtherRemark());
	            aOIFormConsultPaperHelper.setQuestion(aOIBAConsultQuestion.getQuestion());
	            aOIFormConsultPaperHelper.setQuestionId(aOIBAConsultQuestion.getQuestionId() + "");
	            aOIFormConsultPaperHelper.setAnswerType(aOIBAConsultQuestion.getAnswerType());
				aOIFormConsultPaperHelper.setLikertScale(aOIBAConsultQuestion.getLikertScale());
				aOIFormConsultPaperHelper.setUseSameLikert(aOIBAConsultQuestion.getUseSameLikert());
				aOIFormConsultPaperHelper.setCanMoveUp(aOIBAConsultQuestion.getCanMoveUp());
				aOIFormConsultPaperHelper.setCanMoveDown(aOIBAConsultQuestion.getCanMoveDown());
	            aOIFormConsultPaperHelper.setPaperId(aOIBAConsultQuestion.getPaperId() + "");
	            aOIFormConsultPaperHelper.setQuestionNo(i+1 + "");
	            aOIFormConsultPaper.addArOIBAConsultQuestion(aOIFormConsultPaperHelper);
	        }
        }
        request.setAttribute(OIConsultConstant.CONSULT_CREATE_PAPER_FORM,aOIFormConsultPaper);
        request.setAttribute("publishFlag",publishFlag);
        request.setAttribute(OILoginConstants.PAGENAME,"consultPaper");
        return mapping.findForward(OIConsultConstant.POPULATE_CONSULTLISTING);
    }
    //added by edmund
    public ActionForward getRespondentsList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
    	OIFormConsultPaper aOIFormConsultPaper = (OIFormConsultPaper)form; 
    	String paperId = request.getParameter(OIConsultConstant.K_paperId);
    	String title = request.getParameter("paperTitle");
    	String strExport = request.getParameter("export");
    	OIResponseObject aOIResponseObject = new OIBOConsultPaper().getRespondentsData(Integer.parseInt(paperId));
        
        request.setAttribute(OIConsultConstant.K_arOIBAConsultNameEmail,aOIResponseObject.getResponseObject(OIConsultConstant.K_arOIBAConsultNameEmail));
        request.setAttribute("paperTitle",aOIResponseObject.getResponseObject("PaperTitle"));
        //request.setAttribute(OILoginConstants.PAGENAME,"consultRespondentsList");
        if(strExport != null){

            return mapping.findForward("RespondentsListExport");
		}
		else{
			return mapping.findForward("RespondentsList");
		}
        
    }
    
//  added by edmund
    public ActionForward getReportSummary(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
    	OIFormConsultPaper aOIFormConsultPaper = (OIFormConsultPaper)form; 
    	String paperId = request.getParameter(OIConsultConstant.K_paperId);
    	//String title = request.getParameter("paperTitle");
    	String strExport = request.getParameter("export");
    	
    	OIResponseObject aOIResponseObject = new OIBOConsultPaper().getReportSummary(paperId);
        
        request.setAttribute("ConsultDemographicSummary",aOIResponseObject.getResponseObject("ConsultDemographicSummary"));
        request.setAttribute("TotalUser", aOIResponseObject.getResponseObject("TotalUser"));
        request.setAttribute("paperTitle",aOIResponseObject.getResponseObject("PaperTitle"));
        //request.setAttribute(OILoginConstants.PAGENAME,"consultRespondentsList");
        if(strExport != null){

            return mapping.findForward("ConsultSummaryExport");
		}
		else{
			return mapping.findForward("ConsultSummary");
		}
        
    }
    
//  added by edmund
    public ActionForward getReportSelection(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    { 
    	OIFormConsultPaper aOIFormConsultPaper = (OIFormConsultPaper)form;
    	String paperId = request.getParameter(OIConsultConstant.K_paperId);
    	//String title = request.getParameter("paperTitle");
    	String strExport = request.getParameter("export");
    	OIBOSurveyAdmin objBOSurvey = new OIBOSurveyAdmin();
    	OIResponseObject aOIResponseObject = objBOSurvey.getSurveyResultSelection();;
    	request.setAttribute("SurveyDemographicSelection", aOIResponseObject.getResponseObject("SurveyDemographicSelection"));
    	request.setAttribute("paperTitle",aOIResponseObject.getResponseObject("PaperTitle"));
        request.setAttribute("pageName", "SurveyResultDetail");
        request.setAttribute("PaperId",paperId);
        
        return mapping.findForward("populate");
        
    }
    
//  added by edmund
    public ActionForward getDetailReport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    { 
    	String strAge = (String)request.getParameter("strAge");
		String strLevel = (String)request.getParameter("strLevel");
		String strDesignation = (String)request.getParameter("strDesignation");
		String strYear = (String)request.getParameter("strYear");
		String strExport = request.getParameter("export");
    	String paperId = request.getParameter(OIConsultConstant.K_paperId);
    	String tDesignation = "All";
		String tLevel	= "All";
    	//String title = request.getParameter("paperTitle");
    	
    	if(request.getParameter("tDesignation")!=null && !request.getParameter("tDesignation").equals(""))
			tDesignation = (String)request.getParameter("tDesignation");
		
		if(request.getParameter("tLevel")!=null && !request.getParameter("tLevel").equals(""))
			tLevel = (String)request.getParameter("tLevel");
		
    	OIBOConsultPaper objBOpaper = new OIBOConsultPaper();
    	
    	OIResponseObject aOIResponseObject = objBOpaper.getConsultResultDetail(paperId,strAge,strLevel,strDesignation,strYear);
    	request.setAttribute("TotalUser", aOIResponseObject.getResponseObject("TotalUser"));
    	request.setAttribute("ConsultDetail", aOIResponseObject.getResponseObject("ConsultDetail"));
        request.setAttribute("paperTitle",aOIResponseObject.getResponseObject("PaperTitle"));
        request.setAttribute("QuestionRespondents", aOIResponseObject.getResponseObject("QuestionRespondents"));
        request.setAttribute("PaperId",paperId);
        request.setAttribute("strAge",strAge);
		request.setAttribute("strLevel",strLevel);
		request.setAttribute("strDesignation",strDesignation);
		request.setAttribute("strYear",strYear);
		request.setAttribute("tDesignation", tDesignation);
		request.setAttribute("tLevel", tLevel);

		if(strExport != null){
			return mapping.findForward("ConsultDetailExport");
		}
		else{
			return mapping.findForward("ConsultDetail");
		}
        
    }
//  added by edmund
    public ActionForward getDetailQuestion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    { 
    	OIBOConsultPaper objBOpaper = new OIBOConsultPaper();
    	String strQuestionId = (String)request.getParameter("questionId");
		String strQuestion = (String)request.getParameter("question");
		
		OIResponseObject aOIResponseObject= objBOpaper.getOpenQuestion(strQuestionId);
		
		request.setAttribute("OpenEndQuestion", aOIResponseObject.getResponseObject("OpenEndQuestion"));
		request.setAttribute("strQuestion", strQuestion);

		
			return mapping.findForward("OpenEndQuestion");
        
    }
    /**
     * This method redirect user to result selection page 
     */
    public ActionForward resultSelection(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
    	String paperId = request.getParameter(OIConsultConstant.K_paperId);
        OIResponseObject aOIResponseObject = new OIBOConsultPaper().readPaper(Integer.parseInt(paperId.trim()));
        OIFormConsultPaper aOIFormConsultPaper = new OIFormConsultPaper(); 

        String error = (String) aOIResponseObject.getResponseObject(OILoginConstants.K_ERROR);
        if (error != null)
        {
            request.setAttribute(OILoginConstants.K_ERROR,error);
            return new ActionForward(OILoginConstants.ERRORPAGE);
        }
        ArrayList arOIBAConsultCategory = (ArrayList) aOIResponseObject.getResponseObject(OIConsultConstant.K_arOIBAConsultCategory);
        String publishFlag = (String) aOIResponseObject.getResponseObject("publishFlag");

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
        OIBAConsultPaper aOIBAConsultPaper = (OIBAConsultPaper) aOIResponseObject.getResponseObject(OIConsultConstant.K_aOIBAConsultPaper);
        if (aOIBAConsultPaper.getActive() != null && aOIBAConsultPaper.getActive().equals("1"))
            aOIFormConsultPaper.setActive("on");
        else
            aOIFormConsultPaper.setActive("off");
        
        aOIFormConsultPaper.setAttachedFile(aOIBAConsultPaper.getAttachedFile());
        aOIFormConsultPaper.setCategoryID(aOIBAConsultPaper.getCategoryID() + "");
        aOIFormConsultPaper.setDescription(aOIBAConsultPaper.getDescription());
        //aOIFormConsultPaper.setFileName(aOIBAConsultPaper.getActive());
        String textFromDt=null,testToDt=null;
        try
        {
            if (aOIBAConsultPaper.getFromDt()!=null && (!aOIBAConsultPaper.getFromDt().equals("")))
                textFromDt = DateUtility.getMMDDYYYYStringFromDate(aOIBAConsultPaper.getFromDt());
            if (aOIBAConsultPaper.getToDt()!=null && (!aOIBAConsultPaper.getToDt().equals("")))
                testToDt = DateUtility.getMMDDYYYYStringFromDate(aOIBAConsultPaper.getToDt());
        }
        catch(Exception e)
        {
            
        }

        aOIFormConsultPaper.setFromDt(textFromDt);
        aOIFormConsultPaper.setToDt(testToDt);
        aOIFormConsultPaper.setMailStatus(aOIBAConsultPaper.getMailStatus());
        aOIFormConsultPaper.setPaperId(aOIBAConsultPaper.getPaperId() + "");
        aOIFormConsultPaper.setReminderMode(aOIBAConsultPaper.getReminderMode());
        aOIFormConsultPaper.setSecurity(aOIBAConsultPaper.getSecurity());
        aOIFormConsultPaper.setSummary(aOIBAConsultPaper.getSummary());
        aOIFormConsultPaper.setTargetAudiance(aOIBAConsultPaper.getTargetAudiance());
		aOIFormConsultPaper.setContactPerson(aOIBAConsultPaper.getContactPerson());
		aOIFormConsultPaper.setPhone(aOIBAConsultPaper.getPhone());
		aOIFormConsultPaper.setEmailAddress(aOIBAConsultPaper.getEmailAddress());
        aOIFormConsultPaper.setTitle(aOIBAConsultPaper.getTitle());

		//NEW FIELDS
		aOIFormConsultPaper.setStrInstruction(aOIBAConsultPaper.getStrInstruction());
		aOIFormConsultPaper.setStrCompletionTime(aOIBAConsultPaper.getStrCompletionTime());
		aOIFormConsultPaper.setStrFindingsPlannedDt(aOIBAConsultPaper.getStrFindingsPlannedDt());
		aOIFormConsultPaper.setStrViewFindingType(aOIBAConsultPaper.getStrViewFindingType());
		aOIFormConsultPaper.setPublishStatus(aOIBAConsultPaper.getPublishStatus());
		aOIFormConsultPaper.setStrOpenTag(aOIBAConsultPaper.getStrOpenTag());
		aOIFormConsultPaper.setStrTagWords(aOIBAConsultPaper.getStrTagWords());
		aOIFormConsultPaper.setEmailAddress(aOIBAConsultPaper.getEmailAddress());
		aOIFormConsultPaper.setStrEmailDate(aOIBAConsultPaper.getStrEmailDate());
		aOIFormConsultPaper.setStrEmailMessage(aOIBAConsultPaper.getStrEmailMessage());
		aOIFormConsultPaper.setStrTargetGpIds(aOIBAConsultPaper.getStrTargetGpIds());

        if (getSessionAttribute(request,"divCode")!= null && ((String) getSessionAttribute(request,"divCode")).equals(aOIBAConsultPaper.getDivCode()))
        {
            aOIFormConsultPaper.setReadOnlyFlag("false");
        }
        else
        {
            aOIFormConsultPaper.setReadOnlyFlag("true");
        }
        ArrayList arList = (ArrayList) getSessionAttribute(request,OILoginConstants.FUNCTION_LIST);
        if (arList!=null)
        {
	        for (int i=0;i<arList.size();i++)
	        {
	            String functionId = (String) arList.get(i);
	            if (functionId.equalsIgnoreCase("MTNCTBD"))
	            {
	                aOIFormConsultPaper.setReadOnlyFlag("false");
	            }
	        }
        }
        request.setAttribute("publishFlag",publishFlag);
        request.setAttribute(OILoginConstants.PAGENAME,"consultResult");
        return mapping.findForward(OIConsultConstant.POPULATE_CONSULTLISTING);
    }
    
    /**
     * call the OIDAOConsultQuestion.removeQuestion() & pass OIBAConsultQuestion as the input parameter
     * 
     * If remove is unsuccessful then the error has to be shown in the same page as it cannot be removed
     * 
     * Otherwise populate the same page without the question. 
     */
    public ActionForward removeQuestion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        String questionId = request.getParameter(OIConsultConstant.K_questionId);
        String paperId = request.getParameter(OIConsultConstant.K_paperId);
        OIResponseObject aOIResponseObject = new OIBOConsultQuestion().removeQuestion(Integer.parseInt(questionId),Integer.parseInt(paperId));

        ArrayList messageList = (ArrayList) aOIResponseObject.getResponseObject(OILoginConstants.K_MESSAGE);
        String error = (String) aOIResponseObject.getResponseObject(OILoginConstants.K_ERROR);
        if (error != null)
        {
            request.setAttribute(OILoginConstants.K_ERROR,error);
            try
            {
                request.setAttribute("URL",OIDBRegistry.getValues("OIFM.contextroot") + "/consultViewModifyPageAction.do?hiddenAction=populate&paperId=" + paperId);
            }
            catch(Exception e){}
            return new ActionForward(OILoginConstants.ERRORPAGE);
        }

        return new ActionForward("/consultViewModifyPageAction.do?hiddenAction=populate&paperId=" + paperId);
    }
    
  
    /**
     * This method will delete the respective file &
     * call the OIBOConsultPaper.removeAttachment() & pass OIBAConsultPaper as the input parameter
     * 
     * If save is unsuccessful then the error has to be shown in the same page as it cannot be removed
     * 
     * Otherwise populate the same page without the Attachment. 
     */
    public ActionForward removeAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        String paperId = request.getParameter(OIConsultConstant.K_paperId);
        String attachedFileName = request.getParameter(OIConsultConstant.K_attachedFileName);
        
        OIResponseObject aOIResponseObject = new OIBOConsultPaper().removeAttachment(Integer.parseInt(paperId));

        ArrayList messageList = (ArrayList) aOIResponseObject.getResponseObject(OILoginConstants.K_MESSAGE);
        String error = (String) aOIResponseObject.getResponseObject(OILoginConstants.K_ERROR);
        if (error != null)
        {
            request.setAttribute(OILoginConstants.K_ERROR,error);
            try
            {
                request.setAttribute("URL",OIDBRegistry.getValues("OIFM.contextroot") + "/consultViewModifyPageAction.do?hiddenAction=populate&paperId=" + paperId);
            }
            catch(Exception e){}
            return new ActionForward(OILoginConstants.ERRORPAGE);
        }

        try
        {
	        boolean flag=false;
	        String desFileName = OIDBRegistry.getValues("OI.PAPER");
	        if (attachedFileName!=null) 
	            flag = new File(desFileName,attachedFileName).delete();
        }
        catch(Exception e)
        {
            error = e.getMessage();
            request.setAttribute(OILoginConstants.K_ERROR,error);
            try
            {
                request.setAttribute("URL",OIDBRegistry.getValues("OIFM.contextroot") + "/consultViewModifyPageAction.do?hiddenAction=populate&paperId=" + paperId);
            }
            catch(Exception ex){}
            return new ActionForward(OILoginConstants.ERRORPAGE);
        }


        return new ActionForward("/consultViewModifyPageAction.do?hiddenAction=populate&paperId=" + paperId);
    }
    
    /**
     * This method will call the OIBOConsultCategory.deletePaper() & pass OIBAConsultPaper as the input parameter
     * 
     * If delete is unsuccessful then the error has to be shown in the same page
     * 
     * Otherwise it should display the Consultation Paper Listing page. 
     */
    public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        OIFormConsultPaper aOIFormConsultPaper = (OIFormConsultPaper) form;
        OIBAConsultPaper aOIBAConsultPaper = new OIBAConsultPaper();
        aOIBAConsultPaper.setPaperId(Integer.parseInt(aOIFormConsultPaper.getPaperId()));

        String attachedFileName = request.getParameter(OIConsultConstant.K_attachedFileName);

        OIResponseObject oIResponseObject = new OIBOConsultPaper().deletePaper(aOIBAConsultPaper);
        ArrayList messageList = (ArrayList) oIResponseObject.getResponseObject(OILoginConstants.K_MESSAGE);
        String error = (String) oIResponseObject.getResponseObject(OILoginConstants.K_ERROR);
        if (error != null)
        {
            oIResponseObject = new OIBOConsultCategory().readAllCategory();
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
            OIResponseObject aOIResponseObject = new OIBOConsultPaper().readPaper(Integer.parseInt(aOIFormConsultPaper.getPaperId().trim()));
    		
            ArrayList arOIBAConsultQuestion = (ArrayList) aOIResponseObject.getResponseObject(OIConsultConstant.K_arOIBAConsultQuestion);
            OIFormConsultPaperHelper aOIFormConsultPaperHelper=null;
            OIBAConsultQuestion aOIBAConsultQuestion=null;
            if (arOIBAConsultQuestion!=null)
            {
                for (int i=0;i<arOIBAConsultQuestion.size();i++)
    	        {
    	            aOIBAConsultQuestion = (OIBAConsultQuestion) arOIBAConsultQuestion.get(i);
    	            aOIFormConsultPaperHelper = new OIFormConsultPaperHelper();
    	            aOIFormConsultPaperHelper.setAnswer1(aOIBAConsultQuestion.getAnswer1());
    	            aOIFormConsultPaperHelper.setAnswer2(aOIBAConsultQuestion.getAnswer2());
    	            aOIFormConsultPaperHelper.setAnswer3(aOIBAConsultQuestion.getAnswer3());
    	            aOIFormConsultPaperHelper.setAnswer4(aOIBAConsultQuestion.getAnswer4());
    	            aOIFormConsultPaperHelper.setAnswer5(aOIBAConsultQuestion.getAnswer5());
    	            aOIFormConsultPaperHelper.setQuestion(aOIBAConsultQuestion.getQuestion());
    	            aOIFormConsultPaperHelper.setQuestionId(aOIBAConsultQuestion.getQuestionId() + "");
    	            aOIFormConsultPaperHelper.setAnswerType(aOIBAConsultQuestion.getAnswerType());
					aOIFormConsultPaperHelper.setLikertScale(aOIBAConsultQuestion.getLikertScale());
				    aOIFormConsultPaperHelper.setUseSameLikert(aOIBAConsultQuestion.getUseSameLikert());
    	            aOIFormConsultPaperHelper.setPaperId(aOIBAConsultQuestion.getPaperId() + "");
    	            aOIFormConsultPaperHelper.setQuestionNo(i+1 + "");
    	            aOIFormConsultPaper.addArOIBAConsultQuestion(aOIFormConsultPaperHelper);
    	        }
            }

            try
            {
    	        boolean flag=false;
    	        String desFileName = OIDBRegistry.getValues("OI.PAPER");
    	        if (attachedFileName!=null) 
    	            flag = new File(desFileName,attachedFileName).delete();
            }
            catch(Exception e)
            {
                error = e.getMessage();
                request.setAttribute(OILoginConstants.K_ERROR,error);
                try
                {
                    request.setAttribute("URL",OIDBRegistry.getValues("OIFM.contextroot") + "/consultViewModifyPageAction.do?hiddenAction=populate&paperId=" + aOIFormConsultPaper.getPaperId());
                }
                catch(Exception ex){}
                return new ActionForward(OILoginConstants.ERRORPAGE);
            }
            
            request.setAttribute(OILoginConstants.K_ERROR,error);
            request.setAttribute(OIConsultConstant.CONSULT_CREATE_PAPER_FORM,aOIFormConsultPaper);
            request.setAttribute(OILoginConstants.PAGENAME,"consultPaper");
            return mapping.findForward(OIConsultConstant.POPULATE_CONSULTLISTING);
        }
        if (messageList!=null)
        {
            request.setAttribute(OILoginConstants.K_MESSAGE,messageList);
            request.setAttribute(OIConsultConstant.CONSULT_CREATE_PAPER_FORM,aOIFormConsultPaper);
            request.setAttribute(OILoginConstants.PAGENAME,"consultPaper");
            return new ActionForward("/consultCreateCategoryAction.do?hiddenAction=populate");            
        }

        return new ActionForward("/consultListingAction.do?hiddenAction=populate");
    }
    
    public ActionForward downLoad(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        String attachedFileName = request.getParameter("attachedFileName");
        try
        {
            String desFileName = OIDBRegistry.getValues("OI.PAPER");
            new OIDownloadHelper().downloadFile(response,"OI.PAPER",attachedFileName,attachedFileName);
        }
        catch(Exception e)
        {
            String error = e.getMessage();
            request.setAttribute(OILoginConstants.K_ERROR,error);
            return new ActionForward(OILoginConstants.ERRORPAGE);
        }
        return null;
   	}

	public ActionForward importPaper(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        String importFromId = request.getParameter("importFromId");
	
        String strUserId = (String)getSessionAttribute(request, OILoginConstants.USER_ID);
		String paperId = null;
		OIResponseObject objResponseObject =  new OIBOConsultPaper().importPaper(new Integer(importFromId),strUserId);
		
		if(objResponseObject!=null)
		{
			if(objResponseObject.getResponseObject("error") == null) 
			{
				paperId = (String)objResponseObject.getResponseObject("paperId");
			}
		}
		
		OIFormConsultPaper aOIFormConsultPaper = new OIFormConsultPaper(); 
		if(paperId!=null && paperId.length()>0 && !"0".equals(paperId))
		{
			OIResponseObject aOIResponseObject = new OIBOConsultPaper().readPaper(Integer.parseInt(paperId.trim()));
			
			// Start tags
			
			String tags = "";
	        String tagIds = "";
	        OIBOAddTag tagBO = new OIBOAddTag();
	        OIResponseObject aOIResponseObject2 = null;
	        
			removeSessionAttribute(request, "alTagNames");
			removeSessionAttribute(request, "oiFormBlogTag");
			
			if(paperId != null)
				aOIResponseObject2 = tagBO.getTags(paperId,"CP");
			
			
			ArrayList tagList = (ArrayList)aOIResponseObject2.getResponseObject("tagList");
			if(tagList != null){
				for (Iterator iter = tagList.iterator(); iter.hasNext();)
				{
					OIBAAddTag tag = (OIBAAddTag) iter.next();
					tags += tag.getTagName() + ",";	
					tagIds += tag.getTagId() + ",";
				}
				//aOIFormConsultPaper.setStrTagWords(tags);
				setSessionAttribute(request, "alTagNames",tagList);
			}
			
			// End tags

			ArrayList messageList = (ArrayList) aOIResponseObject.getResponseObject(OILoginConstants.K_MESSAGE);
			String error = (String) aOIResponseObject.getResponseObject(OILoginConstants.K_ERROR);
			if (error != null)
			{
				request.setAttribute(OILoginConstants.K_ERROR,error);
				return new ActionForward(OILoginConstants.ERRORPAGE);
			}
			ArrayList arOIBAConsultCategory = (ArrayList) aOIResponseObject.getResponseObject(OIConsultConstant.K_arOIBAConsultCategory);
			ArrayList arOIBAConsultQuestion = (ArrayList) aOIResponseObject.getResponseObject(OIConsultConstant.K_arOIBAConsultQuestion);
			String publishFlag = (String) aOIResponseObject.getResponseObject("publishFlag");

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
			OIBAConsultPaper aOIBAConsultPaper = (OIBAConsultPaper) aOIResponseObject.getResponseObject(OIConsultConstant.K_aOIBAConsultPaper);
			if (aOIBAConsultPaper.getActive() != null && aOIBAConsultPaper.getActive().equals("1"))
				aOIFormConsultPaper.setActive("on");
			else
				aOIFormConsultPaper.setActive("off");
			
			aOIFormConsultPaper.setAttachedFile(aOIBAConsultPaper.getAttachedFile());
			aOIFormConsultPaper.setCategoryID(aOIBAConsultPaper.getCategoryID() + "");
			aOIFormConsultPaper.setDescription(aOIBAConsultPaper.getDescription());
			//aOIFormConsultPaper.setFileName(aOIBAConsultPaper.getActive());
			String textFromDt=null,testToDt=null;
			try
			{
				if (aOIBAConsultPaper.getFromDt()!=null && (!aOIBAConsultPaper.getFromDt().equals("")))
					textFromDt = DateUtility.getMMDDYYYYStringFromDate(aOIBAConsultPaper.getFromDt());
				if (aOIBAConsultPaper.getToDt()!=null && (!aOIBAConsultPaper.getToDt().equals("")))
					testToDt = DateUtility.getMMDDYYYYStringFromDate(aOIBAConsultPaper.getToDt());
			}
			catch(Exception e)
			{
				
			}

			aOIFormConsultPaper.setFromDt(textFromDt);
			aOIFormConsultPaper.setToDt(testToDt);
			aOIFormConsultPaper.setMailStatus(aOIBAConsultPaper.getMailStatus());
			aOIFormConsultPaper.setPaperId(aOIBAConsultPaper.getPaperId() + "");
			aOIFormConsultPaper.setReminderMode(aOIBAConsultPaper.getReminderMode());
			aOIFormConsultPaper.setSecurity(aOIBAConsultPaper.getSecurity());
			aOIFormConsultPaper.setSummary(aOIBAConsultPaper.getSummary());
			aOIFormConsultPaper.setTargetAudiance(aOIBAConsultPaper.getTargetAudiance());
			aOIFormConsultPaper.setContactPerson(aOIBAConsultPaper.getContactPerson());
			aOIFormConsultPaper.setPhone(aOIBAConsultPaper.getPhone());
			aOIFormConsultPaper.setEmailAddress(aOIBAConsultPaper.getEmailAddress());
			aOIFormConsultPaper.setTitle(aOIBAConsultPaper.getTitle());

			//NEW FIELDS
			aOIFormConsultPaper.setStrInstruction(aOIBAConsultPaper.getStrInstruction());
			aOIFormConsultPaper.setStrCompletionTime(aOIBAConsultPaper.getStrCompletionTime());
			aOIFormConsultPaper.setStrFindingsPlannedDt(aOIBAConsultPaper.getStrFindingsPlannedDt());
			aOIFormConsultPaper.setStrViewFindingType(aOIBAConsultPaper.getStrViewFindingType());
			aOIFormConsultPaper.setPublishStatus(aOIBAConsultPaper.getPublishStatus());
			aOIFormConsultPaper.setStrOpenTag(aOIBAConsultPaper.getStrOpenTag());
			aOIFormConsultPaper.setStrTagWords(aOIBAConsultPaper.getStrTagWords());
			aOIFormConsultPaper.setEmailAddress(aOIBAConsultPaper.getEmailAddress());
			aOIFormConsultPaper.setStrEmailDate(aOIBAConsultPaper.getStrEmailDate());
			aOIFormConsultPaper.setStrEmailMessage(aOIBAConsultPaper.getStrEmailMessage());
			aOIFormConsultPaper.setStrTargetGpIds(aOIBAConsultPaper.getStrTargetGpIds());
			
			aOIFormConsultPaper.setStrTagWords(tags);
			aOIFormConsultPaper.setStrTagIdList(tagIds);

			if (getSessionAttribute(request,"divCode")!= null && ((String) getSessionAttribute(request,"divCode")).equals(aOIBAConsultPaper.getDivCode()))
			{
				aOIFormConsultPaper.setReadOnlyFlag("false");
			}
			else
			{
				aOIFormConsultPaper.setReadOnlyFlag("true");
			}
			ArrayList arList = (ArrayList) getSessionAttribute(request,OILoginConstants.FUNCTION_LIST);
			if (arList!=null)
			{
				for (int i=0;i<arList.size();i++)
				{
					String functionId = (String) arList.get(i);
					if (functionId.equalsIgnoreCase("MTNCTBD"))
					{
						aOIFormConsultPaper.setReadOnlyFlag("false");
					}
				}
			}
			OIFormConsultPaperHelper aOIFormConsultPaperHelper=null;
			OIBAConsultQuestion aOIBAConsultQuestion=null;
			if (arOIBAConsultQuestion!=null)
			{
				for (int i=0;i<arOIBAConsultQuestion.size();i++)
				{
					aOIBAConsultQuestion = (OIBAConsultQuestion) arOIBAConsultQuestion.get(i);
					aOIFormConsultPaperHelper = new OIFormConsultPaperHelper();
					aOIFormConsultPaperHelper.setAnswer1(aOIBAConsultQuestion.getAnswer1());
					aOIFormConsultPaperHelper.setAnswer2(aOIBAConsultQuestion.getAnswer2());
					aOIFormConsultPaperHelper.setAnswer3(aOIBAConsultQuestion.getAnswer3());
					aOIFormConsultPaperHelper.setAnswer4(aOIBAConsultQuestion.getAnswer4());
					aOIFormConsultPaperHelper.setAnswer5(aOIBAConsultQuestion.getAnswer5());
					aOIFormConsultPaperHelper.setNeedOtherRemark(aOIBAConsultQuestion.getNeedOtherRemark());
					aOIFormConsultPaperHelper.setQuestion(aOIBAConsultQuestion.getQuestion());
					aOIFormConsultPaperHelper.setQuestionId(aOIBAConsultQuestion.getQuestionId() + "");
					aOIFormConsultPaperHelper.setAnswerType(aOIBAConsultQuestion.getAnswerType());
					aOIFormConsultPaperHelper.setLikertScale(aOIBAConsultQuestion.getLikertScale());
				    aOIFormConsultPaperHelper.setUseSameLikert(aOIBAConsultQuestion.getUseSameLikert());
					aOIFormConsultPaperHelper.setPaperId(aOIBAConsultQuestion.getPaperId() + "");
					aOIFormConsultPaperHelper.setQuestionNo(i+1 + "");
					aOIFormConsultPaper.addArOIBAConsultQuestion(aOIFormConsultPaperHelper);
				}
			}

			request.setAttribute(OIConsultConstant.CONSULT_CREATE_PAPER_FORM,aOIFormConsultPaper);
			request.setAttribute("publishFlag",publishFlag);
			request.setAttribute(OILoginConstants.PAGENAME,"consultPaper");
		}
		else
		{
			 request.setAttribute(OILoginConstants.K_ERROR,"Invalid Consultation Paper Id");
			 
			 OIResponseObject oIResponseObject = new OIBOConsultCategory().readAllCategory();
		        ArrayList messageList = (ArrayList) oIResponseObject.getResponseObject(OILoginConstants.K_MESSAGE);
		        String error = (String) oIResponseObject.getResponseObject(OILoginConstants.K_ERROR);
		        aOIFormConsultPaper = new OIFormConsultPaper();
		        ArrayList arOIBAConsultCategory = (ArrayList) oIResponseObject.getResponseObject(OIConsultConstant.K_arOIBAConsultCategory);
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
		        request.setAttribute(OIConsultConstant.CONSULT_CREATE_PAPER_FORM,aOIFormConsultPaper);
		        request.setAttribute(OILoginConstants.PAGENAME,"consultPaper");

		}
			return mapping.findForward(OIConsultConstant.POPULATE_CONSULTLISTING);
    }

	 public ActionForward reorderQuestion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String actionName)
    {
		 String questionId = request.getParameter(OIConsultConstant.K_questionId);
		int order = actionName.equals(QUESTION_MOVE_UP)?-1:1;
        String paperId = request.getParameter(OIConsultConstant.K_paperId);
        OIResponseObject aOIResponseObject = new OIBOConsultQuestion().reorderQuestion(Integer.parseInt(questionId),order);
        ArrayList messageList = (ArrayList) aOIResponseObject.getResponseObject(OILoginConstants.K_MESSAGE);
        String error = (String) aOIResponseObject.getResponseObject(OILoginConstants.K_ERROR);
        if (error != null)
        {
            request.setAttribute(OILoginConstants.K_ERROR,error);
            try
            {
                request.setAttribute("URL",OIDBRegistry.getValues("OIFM.contextroot") + "/consultViewModifyPageAction.do?hiddenAction=populate&paperId=" + paperId);
            }
            catch(Exception e){}
            return new ActionForward(OILoginConstants.ERRORPAGE);
        }
        return new ActionForward("/consultViewModifyPageAction.do?hiddenAction=populate&paperId=" + paperId);
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
