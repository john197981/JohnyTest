package com.oifm.consultation.admin;
/*
 * Class Name:
 * Description:
 * 
 * 	Created By			Created/Modified on			Revision				Remarks
 * -----------------------------------------------------------------------------------------------------
 * 	Rajkumar			06/07/2005					Draft					Inital Version
 * 
 * -----------------------------------------------------------------------------------------------------
 */

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.apache.struts.util.LabelValueBean;

import com.oifm.base.OIBaseAction;
import com.oifm.codemaster.OIBACodeMaster;
import com.oifm.common.OIDownloadHelper;
import com.oifm.common.OIPageInfoBean;
import com.oifm.common.OIResponseObject;
import com.oifm.consultation.OIBAResponse;
import com.oifm.consultation.OIBVFeedBack;
import com.oifm.consultation.OIConsultConstant;
import com.oifm.login.OILoginConstants;
import com.oifm.utility.DateUtility;
import com.oifm.utility.OIDBRegistry;
import com.oifm.utility.OIFileUtility;

public class OIActionConsultPublish extends OIBaseAction 
{
    Logger logger = Logger.getLogger(OIActionConsultPublish.class.getName());
    private static final String POPULATE_ACTION = "populate";
    private static final String EXPORT_ACTION = "export";
    private static final String UPDATE_ACTION = "update";
    private static final String REMOVE_PUBLISH_ATTACHMENT_ACTION = "removePublishAttachment";

    public ActionForward doIt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String actionName)
    {
        if (actionName!=null)
        {
	        if (actionName.equals(POPULATE_ACTION))
	        {
	            return populate(mapping, form, request, response);
	        }
	        if (actionName.equals(EXPORT_ACTION))
	        {
	            return export(mapping, form, request, response);
	        }
	        if (actionName.equals(UPDATE_ACTION))
	        {
	            return update(mapping, form, request, response);
	        }
	        if (actionName.equals(REMOVE_PUBLISH_ATTACHMENT_ACTION))
	        {
	            return removePublishAttachment(mapping, form, request, response);
	        }
	        if (actionName.equals("downLoad"))
	        {
	            return downLoad(mapping, form, request, response);
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
     * This method calls the populateConsultPublish of OIBOConsultPublish, which passes paper ID as parameter 
     */
    public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
    	System.out.println("inside the action class : populate method :)");
        String paperId = request.getParameter(OIConsultConstant.K_paperId);
        String pageNo=null;
        if (request.getParameter(OIConsultConstant.K_pageNo)==null)
            pageNo="1";
        else
            pageNo = request.getParameter(OIConsultConstant.K_pageNo);
        OIResponseObject aOIResponseObject = new OIBOConsultPublish().populateConsultPublish(Integer.parseInt(paperId),Integer.parseInt(pageNo));
        OIFormConsultPublish aOIFormConsultPublish = new OIFormConsultPublish();
        ArrayList messageList = (ArrayList) aOIResponseObject.getResponseObject(OILoginConstants.K_MESSAGE);
        String error = (String) aOIResponseObject.getResponseObject(OILoginConstants.K_ERROR);
        if (error != null)
        {
            request.setAttribute(OILoginConstants.K_ERROR,error);
            return new ActionForward(OILoginConstants.ERRORPAGE);
        }
        ArrayList arOIBACodeMaster = (ArrayList) aOIResponseObject.getResponseObject(OIConsultConstant.K_arOIBACodeMaster);
        OIBACodeMaster aOIBACodeMaster = null;
        aOIFormConsultPublish.addArPublishStatus(new LabelValueBean("Please Select...",""));
        if (arOIBACodeMaster!=null)
        {
	        for (int i=0;i<arOIBACodeMaster.size();i++)
	        {
	            aOIBACodeMaster = (OIBACodeMaster) arOIBACodeMaster.get(i);
	            aOIFormConsultPublish.addArPublishStatus(new LabelValueBean(aOIBACodeMaster.getDescription(),aOIBACodeMaster.getValue()));
	        }
        }
        ArrayList arOIBVFeedBack = (ArrayList) aOIResponseObject.getResponseObject(OIConsultConstant.K_arOIBVFeedBack);
        OIBVFeedBack aOIBVFeedBack = null;
        OIFormConsultPublishHelper aOIFormConsultPublishHelper = null;
        if (arOIBVFeedBack!=null)
        {
	        for (int i=0;i<arOIBVFeedBack.size();i++)
	        {
	            aOIBVFeedBack = (OIBVFeedBack) arOIBVFeedBack.get(i);
	            aOIFormConsultPublishHelper = new OIFormConsultPublishHelper();
	            aOIFormConsultPublishHelper.setFeedBack(aOIBVFeedBack.getFeedBack());
	            String textDate=null;
	            try
	            {
		            if (aOIBVFeedBack.getFeedBackDate()!=null)
		                textDate=DateUtility.getMMDDYYYYStringFromDate(aOIBVFeedBack.getFeedBackDate());
	            }
	            catch(Exception e)
	            {
	                logger.info(e.getMessage());
	            }
	
	            aOIFormConsultPublishHelper.setFeedBackDate(textDate);
	            aOIFormConsultPublishHelper.setName(aOIBVFeedBack.getUserName());
	            aOIFormConsultPublishHelper.setNric(aOIBVFeedBack.getUserId());
	            aOIFormConsultPublishHelper.setSchool(aOIBVFeedBack.getSchool());
	            aOIFormConsultPublishHelper.setDivision(aOIBVFeedBack.getDivision());
	            aOIFormConsultPublish.addArOIFormConsultPublishHelper(aOIFormConsultPublishHelper);
	        }
        }
        OIBAConsultPaper aOIBAConsultPaper = (OIBAConsultPaper) aOIResponseObject.getResponseObject(OIConsultConstant.K_aOIBAConsultPaper);
        if (aOIBAConsultPaper.getActive().equals("1"))
            aOIFormConsultPublish.setActive(OIConsultConstant.K_Open);
        else
            aOIFormConsultPublish.setActive(OIConsultConstant.K_Closed);
        aOIFormConsultPublish.setAttachedFile(aOIBAConsultPaper.getAttachedFile());
        if (aOIBAConsultPaper.getDescription()!=null)
        	aOIFormConsultPublish.setDescription(aOIBAConsultPaper.getDescription().replaceAll("\n","<br>"));
        else
        	aOIFormConsultPublish.setDescription(aOIBAConsultPaper.getDescription());
        //aOIFormConsultPublish.setDescription(aOIBAConsultPaper.getDescription());
        String fromDate=null,toDate=null;
        try
        {
            if (aOIBAConsultPaper.getFromDt()!=null)
                fromDate=DateUtility.getMMDDYYYYStringFromDate(aOIBAConsultPaper.getFromDt());;
                if (aOIBAConsultPaper.getToDt()!=null)
                    toDate=DateUtility.getMMDDYYYYStringFromDate(aOIBAConsultPaper.getToDt());;
        }
        catch(Exception e)
        {
            logger.info(e.getMessage());
        }

        aOIFormConsultPublish.setFromDt(fromDate);
        aOIFormConsultPublish.setToDt(toDate);
        aOIFormConsultPublish.setSummary(aOIBAConsultPaper.getSummary());
        aOIFormConsultPublish.setTargetAudiance(aOIBAConsultPaper.getTargetAudiance());
		aOIFormConsultPublish.setContactPerson(aOIBAConsultPaper.getContactPerson());
		aOIFormConsultPublish.setPhone(aOIBAConsultPaper.getPhone());
		aOIFormConsultPublish.setEmailAddress(aOIBAConsultPaper.getEmailAddress());
        aOIFormConsultPublish.setTitle(aOIBAConsultPaper.getTitle());
        aOIFormConsultPublish.setPublishStatus(aOIBAConsultPaper.getPublishedStatus());
        aOIFormConsultPublish.setPaperId(aOIBAConsultPaper.getPaperId() + "");
        aOIFormConsultPublish.setSummaryFile(aOIBAConsultPaper.getAttachedSum());

		//NEW FIELDS
		aOIFormConsultPublish.setStrInstruction(aOIBAConsultPaper.getStrInstruction());
		aOIFormConsultPublish.setStrCompletionTime(aOIBAConsultPaper.getStrCompletionTime());
		aOIFormConsultPublish.setStrFindingsPlannedDt(aOIBAConsultPaper.getStrFindingsPlannedDt());
		aOIFormConsultPublish.setStrViewFindingType(aOIBAConsultPaper.getStrViewFindingType());
		aOIFormConsultPublish.setPublishStatus(aOIBAConsultPaper.getPublishStatus());
		aOIFormConsultPublish.setStrOpenTag(aOIBAConsultPaper.getStrOpenTag());
		aOIFormConsultPublish.setStrTagWords(aOIBAConsultPaper.getStrTagWords());
		aOIFormConsultPublish.setEmailAddress(aOIBAConsultPaper.getEmailAddress());
		aOIFormConsultPublish.setStrEmailDate(aOIBAConsultPaper.getStrEmailDate());
		aOIFormConsultPublish.setStrEmailMessage(aOIBAConsultPaper.getStrEmailMessage());
		aOIFormConsultPublish.setStrTargetGpIds(aOIBAConsultPaper.getStrTargetGpIds());


        if (getSessionAttribute(request,"divCode")!= null && ((String) getSessionAttribute(request,"divCode")).equals(aOIBAConsultPaper.getDivCode()))
        {
            aOIFormConsultPublish.setReadOnlyFlag("false");
        }
        else
        {
            aOIFormConsultPublish.setReadOnlyFlag("true");
        }
        ArrayList arList = (ArrayList) getSessionAttribute(request,OILoginConstants.FUNCTION_LIST);
        if (arList!=null)
        {
	        for (int i=0;i<arList.size();i++)
	        {
	            String functionId = (String) arList.get(i);
	            if (functionId.equalsIgnoreCase("MTNCTBD"))
	            {
	                aOIFormConsultPublish.setReadOnlyFlag("false");
	            }
	        }
        }

        OIPageInfoBean aOIPageInfoBean = (OIPageInfoBean) aOIResponseObject.getResponseObject(OIConsultConstant.K_aOIPageInfoBean);
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
        request.setAttribute(OIConsultConstant.CONSULT_PUBLISH_FORM,aOIFormConsultPublish);
        request.setAttribute(OILoginConstants.PAGENAME,"consultPaperPublish");
        return mapping.findForward(OIConsultConstant.POPULATE_CONSULTLISTING);
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
        OIFormConsultPublish aOIFormConsultPublish = (OIFormConsultPublish) form;
        
        FormFile aFormFile = aOIFormConsultPublish.getSummaryFileName();
        String fileName = null;
        
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
	            if (!writeFile) 
	            {
	                //only write files out that are less than 1MB
	               /* if (aFormFile.getFileSize() < (2*1024000)) 
	                {
	                    byte[] buffer = new byte[8192];
	                    int bytesRead = 0;
	                    while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) 
	                    {
	                        baos.write(buffer, 0, bytesRead);
	                    }
	                    data = new String(baos.toByteArray());
	                }
	                else 
	                {
	                    data = new String("The file is greater than 2MB, and has not been written to stream. File Size: " + aFormFile.getFileSize() + " bytes. ");
	                }*/
	            }
	            else 
	            {
	                if (aFormFile.getFileSize() < (2*1024*1024)) 
	                {
	                    String desFileName = OIDBRegistry.getValues("OI.PAPER.SUMMARY");
	                    String paperId="";
	                    if (aOIFormConsultPublish.getPaperId()!=null)
	                        paperId = aOIFormConsultPublish.getPaperId();
	                    fileName = OIFileUtility.generateFileName(OIDBRegistry.getValues("OI.CONS.FILENAMEPREFIXSUMMARY"),paperId,OIDBRegistry.getValues("OI.CONS.EXT"));
	                    OIFileUtility.writeFile(stream,desFileName,fileName);
		                data = OIDBRegistry.getValues("OI.CONS.FILEMESSAGE") + desFileName;
	                }
	                else
	                {
	                    data = new String(OIDBRegistry.getValues("OI.CONS.FILESIZE") + aFormFile.getFileSize() + OIDBRegistry.getValues("OI.CONS.BYTES"));
	                    throw new Exception(data);
	                }
	            }
	            logger.info(data);
	            //close the stream
	            stream.close();
	        }
	        catch (Exception fnfe)
	        {
	            logger.error(fnfe.getMessage());
	            request.setAttribute(OILoginConstants.K_ERROR,fnfe.getMessage());
	            try
	            {
	                request.setAttribute("URL",OIDBRegistry.getValues("OIFM.contextroot") + "/consultPaperPublishAction.do?hiddenAction=populate&paperId=" + aOIFormConsultPublish.getPaperId());
	            }
	            catch(Exception e){}
	            return new ActionForward(OILoginConstants.ERRORPAGE);
	        }

	        //destroy the temporary file created
	        aFormFile.destroy();
        }

        
        //**************************************************************************************************
        OIBAConsultPaper aOIBAConsultPaper = new OIBAConsultPaper();
        aOIBAConsultPaper.setPaperId(Integer.parseInt(aOIFormConsultPublish.getPaperId()));
        aOIBAConsultPaper.setSummary(aOIFormConsultPublish.getSummary());
        aOIBAConsultPaper.setPublishedStatus(aOIFormConsultPublish.getPublishStatus());
        if (aOIFormConsultPublish.getSummaryFile()!=null && (! aOIFormConsultPublish.getSummaryFile().trim().equals("")))
            aOIBAConsultPaper.setAttachedSum(aOIFormConsultPublish.getSummaryFile());
        else
            aOIBAConsultPaper.setAttachedSum(fileName);
        OIResponseObject aOIResponseObject = new OIBOConsultPaper().publishPaper(aOIBAConsultPaper); 
        ArrayList messageList = (ArrayList) aOIResponseObject.getResponseObject(OILoginConstants.K_MESSAGE);
        String error = (String) aOIResponseObject.getResponseObject(OILoginConstants.K_ERROR);
        if (error != null)
        {
            request.setAttribute(OILoginConstants.K_ERROR,error);
            try
            {
                request.setAttribute("URL",OIDBRegistry.getValues("OIFM.contextroot") + "/consultPaperPublishAction.do?hiddenAction=populate&paperId=" + aOIFormConsultPublish.getPaperId());
            }
            catch(Exception ex){}
            return new ActionForward(OILoginConstants.ERRORPAGE);
        }
        return new ActionForward("/consultListingAction.do?hiddenAction=populate");
    }
    
    public ActionForward removePublishAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        String paperId = request.getParameter(OIConsultConstant.K_paperId);
        String summaryFile = request.getParameter(OIConsultConstant.K_summaryFile);
        
        try
        {
            String desFileName = OIDBRegistry.getValues("OI.PAPER.SUMMARY");
            boolean flag=false;
            if (summaryFile!=null) 
                flag = new File(desFileName,summaryFile).delete();
        }
        catch(Exception e)
        {
            String error = e.getMessage();
            request.setAttribute(OILoginConstants.K_ERROR,error);
            return new ActionForward(OILoginConstants.ERRORPAGE);
        }

        OIResponseObject aOIResponseObject = new OIBOConsultPaper().removePublishAttachment(Integer.parseInt(paperId));

        ArrayList messageList = (ArrayList) aOIResponseObject.getResponseObject(OILoginConstants.K_MESSAGE);
        String error = (String) aOIResponseObject.getResponseObject(OILoginConstants.K_ERROR);
        if (error != null)
        {
            request.setAttribute(OILoginConstants.K_ERROR,error);
            try
            {
                request.setAttribute("URL",OIDBRegistry.getValues("OIFM.contextroot") + "/consultPaperPublishAction.do?hiddenAction=populate&paperId=" + paperId);
            }
            catch(Exception ex){}
                return new ActionForward(OILoginConstants.ERRORPAGE);
        }

        
        return new ActionForward("/consultPaperPublishAction.do?hiddenAction=populate&paperId=" + paperId);
    }

    //modify by edmund
    public ActionForward export(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
    	logger.info("<<<<<<<<<< Inside EXPORT action >>>>>>>>>>>");
        String paperId = request.getParameter(OIConsultConstant.K_paperId);
        logger.info("<<<<<<<<<< Paper ID >>>>>>>>>>>" + paperId);
        OIResponseObject aOIResponseObject = new OIBOConsultPublish().exportConsultPublish(Integer.parseInt(paperId));
        OIFormConsultPublish aOIFormConsultPublish = new OIFormConsultPublish();
        ArrayList messageList = (ArrayList) aOIResponseObject.getResponseObject(OILoginConstants.K_MESSAGE);
        String error = (String) aOIResponseObject.getResponseObject(OILoginConstants.K_ERROR);
        if (error != null)
        {
            request.setAttribute(OILoginConstants.K_ERROR,error);
            return new ActionForward(OILoginConstants.ERRORPAGE);
        }
        ArrayList arOIBACodeMaster = (ArrayList) aOIResponseObject.getResponseObject(OIConsultConstant.K_arOIBACodeMaster);
        OIBACodeMaster aOIBACodeMaster = null;
        String str=null;
        try
        {
            str = OIDBRegistry.getValues("OI.CONS.PUBLISH.SELECT");
          //  logger.info("<<<<<<<<<< str >>>>>>>>>>>" + str);
        }catch(Exception e){}
        aOIFormConsultPublish.addArPublishStatus(new LabelValueBean(str,""));
        if (arOIBACodeMaster!=null)
        {
	        for (int i=0;i<arOIBACodeMaster.size();i++)
	        {
	            aOIBACodeMaster = (OIBACodeMaster) arOIBACodeMaster.get(i);
	            aOIFormConsultPublish.addArPublishStatus(new LabelValueBean(aOIBACodeMaster.getDescription(),aOIBACodeMaster.getValue()));
	        }
        }
        ArrayList arOIBVFeedBack = (ArrayList) aOIResponseObject.getResponseObject(OIConsultConstant.K_arOIBVFeedBack);
        OIBVFeedBack aOIBVFeedBack = null;
        OIFormConsultPublishHelper aOIFormConsultPublishHelper = null;
        if (arOIBVFeedBack!=null)
        {
	        for (int i=0;i<arOIBVFeedBack.size();i++)
	        {
	            aOIBVFeedBack = (OIBVFeedBack) arOIBVFeedBack.get(i);
	            aOIFormConsultPublishHelper = new OIFormConsultPublishHelper();
	            aOIFormConsultPublishHelper.setFeedBack(aOIBVFeedBack.getFeedBack());
	           // logger.info("****&&&&&&&&&&&&aOIBVFeedBack.getFeedBack()&&&&&&&&&&&&****"+aOIBVFeedBack.getFeedBack().toString());
	            String textDate=null;
	            try
	            {
		            if (aOIBVFeedBack.getFeedBackDate()!=null)
		                textDate=DateUtility.getMMDDYYYYStringFromDate(aOIBVFeedBack.getFeedBackDate());
	            }
	            catch(Exception e)
	            {
	                logger.info(e.getMessage());
	            }
	
	            aOIFormConsultPublishHelper.setFeedBackDate(textDate);
//	          commented by edmund
	            //aOIFormConsultPublishHelper.setName(aOIBVFeedBack.getUserName());
	            aOIFormConsultPublishHelper.setNric(aOIBVFeedBack.getUserId());
	           // logger.info("<<<<<<<<<< aOIBVFeedBack.getUserId() >>>>>>>>>>>" + aOIBVFeedBack.getUserId());
	            aOIFormConsultPublishHelper.setSchool(aOIBVFeedBack.getSchool());	            
	           // logger.info("<<<<<<<<<< aOIBVFeedBack.getSchool() >>>>>>>>>>>" + aOIBVFeedBack.getSchool());
	            aOIFormConsultPublishHelper.setDivision(aOIBVFeedBack.getDivision());	           
	           // logger.info("<<<<<<<<<< aOIBVFeedBack.getDivision() >>>>>>>>>>>" + aOIBVFeedBack.getDivision());
	            aOIFormConsultPublishHelper.setDesignation(aOIBVFeedBack.getDesignation());	           
	           // logger.info("<<<<<<<<<< aOIBVFeedBack.getDesignation() >>>>>>>>>>>" + aOIBVFeedBack.getDesignation());
//	          commented by edmund
	            //aOIFormConsultPublishHelper.setEmail(aOIBVFeedBack.getEmail());
	            aOIFormConsultPublishHelper.setSchoolLevel(aOIBVFeedBack.getSchoolLevel());	          
	            aOIFormConsultPublishHelper.setAge(aOIBVFeedBack.getAge());	            
	            aOIFormConsultPublishHelper.setService(aOIBVFeedBack.getService());	            
				aOIFormConsultPublishHelper.setResponses(aOIBVFeedBack.getResponses());
				
				//logger.info("<<<<<<<<<< aOIBVFeedBack.getResponses() >>>>>>>>>>>" + aOIBVFeedBack.getResponses());
				
			
				aOIFormConsultPublish.addArOIFormConsultPublishHelper(aOIFormConsultPublishHelper);
	        }
        }
        ArrayList arOIBAConsultQuestion = (ArrayList) aOIResponseObject.getResponseObject(OIConsultConstant.K_arOIBAConsultQuestion);
        OIFormConsultPaperHelper aOIFormConsultPaperHelper=null;
        OIBAConsultQuestion aOIBAConsultQuestion=null;
        request.setAttribute("arOIBAConsultQuestion",arOIBAConsultQuestion);
        if (arOIBAConsultQuestion!=null)
        {
            for (int i=0;i<arOIBAConsultQuestion.size();i++)
	        {
	            aOIBAConsultQuestion = (OIBAConsultQuestion) arOIBAConsultQuestion.get(i);
	            aOIFormConsultPaperHelper = new OIFormConsultPaperHelper();
	            aOIFormConsultPaperHelper.setAnswer1(aOIBAConsultQuestion.getAnswer1());
	           // logger.info("%%%%$$$$$^^^^^^****(((((( ---- " + aOIBAConsultQuestion.getAnswer1());
	            aOIFormConsultPaperHelper.setAnswer2(aOIBAConsultQuestion.getAnswer2());	         
	            aOIFormConsultPaperHelper.setAnswer3(aOIBAConsultQuestion.getAnswer3());	            
	            aOIFormConsultPaperHelper.setAnswer4(aOIBAConsultQuestion.getAnswer4());	           
	            aOIFormConsultPaperHelper.setAnswer5(aOIBAConsultQuestion.getAnswer5());	            
	            aOIFormConsultPaperHelper.setQuestion(aOIBAConsultQuestion.getQuestion());	         
	            aOIFormConsultPaperHelper.setQuestionId(aOIBAConsultQuestion.getQuestionId() + "");	            
	            aOIFormConsultPaperHelper.setAnswerType(aOIBAConsultQuestion.getAnswerType());	            
	            aOIFormConsultPaperHelper.setPaperId(aOIBAConsultQuestion.getPaperId() + "");	            	            
	            aOIFormConsultPaperHelper.setQuestionNo(i+1 + "");
	            aOIFormConsultPublish.addArOIBAConsultQuestion(aOIFormConsultPaperHelper);
	        }
        }

        OIBAConsultPaper aOIBAConsultPaper = (OIBAConsultPaper) aOIResponseObject.getResponseObject(OIConsultConstant.K_aOIBAConsultPaper);
        if (aOIBAConsultPaper.getActive().equals("1"))
            aOIFormConsultPublish.setActive(OIConsultConstant.K_Open);
        else
            aOIFormConsultPublish.setActive(OIConsultConstant.K_Closed);
        aOIFormConsultPublish.setAttachedFile(aOIBAConsultPaper.getAttachedFile());
        aOIFormConsultPublish.setDescription(aOIBAConsultPaper.getDescription());
        String fromDate=null,toDate=null;
        try
        {
            if (aOIBAConsultPaper.getFromDt()!=null)
                fromDate=DateUtility.getMMDDYYYYStringFromDate(aOIBAConsultPaper.getFromDt());;
                if (aOIBAConsultPaper.getToDt()!=null)
                    toDate=DateUtility.getMMDDYYYYStringFromDate(aOIBAConsultPaper.getToDt());;
        }
        catch(Exception e)
        {
            logger.info(e.getMessage());
        }

        aOIFormConsultPublish.setFromDt(fromDate);
        aOIFormConsultPublish.setToDt(toDate);
        aOIFormConsultPublish.setSummary(aOIBAConsultPaper.getSummary());
        aOIFormConsultPublish.setTargetAudiance(aOIBAConsultPaper.getTargetAudiance());
		aOIFormConsultPublish.setContactPerson(aOIBAConsultPaper.getContactPerson());
		aOIFormConsultPublish.setPhone(aOIBAConsultPaper.getPhone());
		aOIFormConsultPublish.setEmailAddress(aOIBAConsultPaper.getEmailAddress());
        aOIFormConsultPublish.setTitle(aOIBAConsultPaper.getTitle());
        aOIFormConsultPublish.setPublishStatus(aOIBAConsultPaper.getPublishedStatus());
        aOIFormConsultPublish.setPaperId(aOIBAConsultPaper.getPaperId() + "");
        aOIFormConsultPublish.setSummaryFile(aOIBAConsultPaper.getAttachedSum());
       // ArrayList arOIBAResponse = (ArrayList) aOIResponseObject.getResponseObject("arOIBAResponse");
		
        //logger.info(aOIFormConsultPublish.getArOIBAConsultQuestion().size() + "");
        //request.setAttribute(OIConsultConstant.K_arOIBAResponse,arOIBAResponse);
       /* for (int l=0;l<arOIBAResponse.size();l++){
		OIBAResponse obj = (OIBAResponse)arOIBAResponse.get(l);
			logger.info("<<<<<<<<<< TEST USER ID >>>>>>>>>>>" + obj.getUserId());
			logger.info("<<<<<<<<<< TEST QUESTION ID >>>>>>>>>>>" + obj.getQuestionId());
			logger.info("<<<<<<<<<< TEST Response 1 >>>>>>>>>>>" + obj.getResponse1());
			logger.info("<<<<<<<<<< TEST Response 2 >>>>>>>>>>>" + obj.getResponse2());
			logger.info("<<<<<<<<<< TEST Response 3 >>>>>>>>>>>" + obj.getResponse3());
			logger.info("<<<<<<<<<< TEST Response 4 >>>>>>>>>>>" + obj.getResponse4());
			logger.info("<<<<<<<<<< TEST Response 5 >>>>>>>>>>>" + obj.getResponse5());
			logger.info("<<<<<<<<<< TEST Response ID >>>>>>>>>>>" + obj.getResponseId());
	}*/
        request.setAttribute(OIConsultConstant.CONSULT_PUBLISH_FORM,aOIFormConsultPublish);

        return mapping.findForward("export");
    }

    public ActionForward downLoad(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        String summaryFile = request.getParameter("summaryFile");
        try
        {
            String desFileName = OIDBRegistry.getValues("OI.PAPER.SUMMARY");
            new OIDownloadHelper().downloadFile(response,"OI.PAPER.SUMMARY",summaryFile,summaryFile);
        }
        catch(Exception e)
        {
            String error = e.getMessage();
            request.setAttribute(OILoginConstants.K_ERROR,error);
            return new ActionForward(OILoginConstants.ERRORPAGE);
        }
        return null;
   	}
}
