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

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseBo;
import com.oifm.common.OIResponseObject;
import com.oifm.consultation.OIConsultConstant;
import com.oifm.consultation.OIConsultationSqls;
import com.oifm.consultation.OIDAOConsultFeedBack;
import com.oifm.consultation.OIDAOConsultResponse;
import com.oifm.survey.admin.OIDAOResponseAdmin;
import com.oifm.utility.OIDBRegistry;

public class OIBOConsultPaper extends OIBaseBo 
{
    Logger logger = Logger.getLogger(OIBOConsultPaper.class.getName());
    /**
     * This method will 
     * 
     * call the OIDAOConsultQuestion.deleteArray & pass paperId as the input parameter
     * 
     * call the OIDAOConsultPaper.delete() & pass OIBAConsultPaper as the input parameter 
     */
    public OIResponseObject deletePaper(OIBAConsultPaper pOIBAConsultPaper)
    {
        try
        {
            getConnection();
            connection.setAutoCommit(false);
            if (new OIDAOConsultPaper().checkActivePaper(pOIBAConsultPaper.getPaperId(),connection))
            {
	            new OIDAOConsultResponse().deleteResponses(pOIBAConsultPaper.getPaperId(),connection);
	            new OIDAOConsultFeedBack().deleteFeedBacks(pOIBAConsultPaper.getPaperId(),connection);
	            new OIDAOConsultQuestion().deleteQuestions(pOIBAConsultPaper.getPaperId(),connection);
	            new OIDAOConsultPaper().deleteMembers(pOIBAConsultPaper.getPaperId(),connection);
	            new OIDAOConsultPaper().delete(pOIBAConsultPaper.getPaperId(),connection);
            }
            else
            {
                error = OIDBRegistry.getValues("OI.CONS.PAPER.ACTIVE");
            }
            connection.commit();
            connection.setAutoCommit(true);
        }
        catch(Exception e)
        {
            error = e.getMessage();
            logger.error(e);            
            try
            {
                connection.rollback();
            }catch (Exception ex){}
            //logger.info(error);
        }
        finally
        {
            freeConnection();
        }
        addToResponseObject();
        return responseObject;
    }
    
    /**
     * This method will call the OIDAOConsultPaper.save() & pass OIBAConsultPaper as the input parameter
     * 
     * Check if the save is new save then, get the paperId of the paper.
     * 
     * call the OIDAOConsultQuestion.saveArray() & pass ArrayList of OIBAConsultQuestion & paperId as the 
     * input parameter. 
     */
    public OIResponseObject savePaper(OIBAConsultPaper pOIBAConsultPaper)
    {
        try
        {
            getConnection();
            connection.setAutoCommit(false);
            OIDAOConsultPaper aOIDAOConsultPaper = new OIDAOConsultPaper();
            if (pOIBAConsultPaper.getPaperId()== 0)
            {
	            if (aOIDAOConsultPaper.checkDuplicatePaper(pOIBAConsultPaper.getTitle(),0,pOIBAConsultPaper.getCategoryID(),connection))
	            {
	                aOIDAOConsultPaper.save(pOIBAConsultPaper,connection);
	                if (pOIBAConsultPaper.getPaperId()==0)
	                    pOIBAConsultPaper = aOIDAOConsultPaper.getPaperID(pOIBAConsultPaper.getTitle(),connection);
	                responseObject.addResponseObject("aOIBAConsultPaper",pOIBAConsultPaper);
	            }
	            else
	            {
	                error = OIDBRegistry.getValues("OI.CONS.DUPLICATE.PAPER");
	            }
            }
            else
            {
	            if (aOIDAOConsultPaper.checkDuplicatePaper(pOIBAConsultPaper.getTitle(),pOIBAConsultPaper.getPaperId(),pOIBAConsultPaper.getCategoryID(),connection))
	            {
	                aOIDAOConsultPaper.save(pOIBAConsultPaper,connection);
	            }
	            else
	            {
	                error = OIDBRegistry.getValues("OI.CONS.DUPLICATE.PAPER");
	            }
            }
            connection.commit();
            connection.setAutoCommit(true);
        }
        catch(Exception e)
        {
            error = e.getMessage();
            logger.error(e);
            try
            {
                connection.rollback();
            }catch (Exception ex){}
            //logger.info(error);
        }
        finally
        {
            freeConnection();
        }
        addToResponseObject();
        return responseObject;
    }

    /**
     * This method will call the OIDAOConsultPaper.read() & pass paperID as the input parameter
     * 
     * Call OIDAOConsultQuestion.readArray() & pass paperID as the input parameter
     * 
     * call the OIDAOConsultCategory.readAll() 
     * 
     * and returns responseObject, which holds OIBAConsultPaper object returned by OIDAOConsultPaper.read() method, ArrayList of OIBAConsultQuestion object retunrned by OIDAOConsultQuestion.readArray() method, ArrayList of OIBAConsultCategory object retunrned by OIDAOConsultCategory.readAll() method 
     */
    public OIResponseObject readPaper(int pPaperId)
    {
        try
        {
            getConnection();
            OIBAConsultPaper aOIBAConsultPaper = new OIDAOConsultPaper().read(pPaperId,connection);
            ArrayList arOIBAConsultQuestion = new OIDAOConsultQuestion().readArray(pPaperId,connection);
            ArrayList arOIBAConsultCategory = new OIDAOConsultCategory().readAll(connection);
            boolean publishFlag = new OIDAOConsultPaper().checkNotActivePaper(pPaperId,connection);
            responseObject.addResponseObject("publishFlag",new Boolean(publishFlag).toString());
            responseObject.addResponseObject(OIConsultConstant.K_aOIBAConsultPaper,aOIBAConsultPaper);
            responseObject.addResponseObject(OIConsultConstant.K_arOIBAConsultCategory,arOIBAConsultCategory);
            responseObject.addResponseObject(OIConsultConstant.K_arOIBAConsultQuestion,arOIBAConsultQuestion);
        }
        catch(Exception e)
        {
            error = e.getMessage();
            logger.error(e);
            //logger.info(error);
        }
        finally
        {
            freeConnection();
        }
        addToResponseObject();
        return responseObject;
    }
    //added by edmund
    public OIResponseObject getRespondentsData(int pPaperId)
    {
    	String strPaperTitle = "";
    	OIDAOConsultFeedBack objCousult = new OIDAOConsultFeedBack();
        try
        {
            getConnection();
            //OIBAConsultPaper aOIBAConsultPaper = new OIDAOConsultPaper().getRespondentsData(pPaperId,connection);
            //ArrayList arOIBAConsultQuestion = new OIDAOConsultQuestion().readArray(pPaperId,connection);
            //ArrayList arOIBAConsultCategory = new OIDAOConsultCategory().readAll(connection);
            ArrayList alOIBAFeedBack = new OIDAOConsultFeedBack().readNameEmail(pPaperId,connection);
            boolean publishFlag = new OIDAOConsultPaper().checkNotActivePaper(pPaperId,connection);
            responseObject.addResponseObject("publishFlag",new Boolean(publishFlag).toString());
            responseObject.addResponseObject(OIConsultConstant.K_arOIBAConsultNameEmail,alOIBAFeedBack);
            //responseObject.addResponseObject(OIConsultConstant.K_aOIBAConsultPaper,aOIBAConsultPaper);
           // responseObject.addResponseObject(OIConsultConstant.K_arOIBAConsultCategory,arOIBAConsultCategory);
            //responseObject.addResponseObject(OIConsultConstant.K_arOIBAConsultQuestion,arOIBAConsultQuestion);
            strPaperTitle = objCousult.getPaperTitle(connection, String.valueOf(pPaperId),OIConsultationSqls.GET_PAPER_TITLE);
            responseObject.addResponseObject("PaperTitle", strPaperTitle);
        }
        catch(Exception e)
        {
            error = e.getMessage();
            logger.error(e);
            //logger.info(error);
        }
        finally
        {
            freeConnection();
        }
        addToResponseObject();
        return responseObject;
    }
    
//  added by edmund
    public OIResponseObject getReportSummary(String strPaperId)
    {
    	OIDAOConsultFeedBack objCousult = new OIDAOConsultFeedBack();
    	OIDAOResponseAdmin objSurveyAdmin = new OIDAOResponseAdmin();
    	ArrayList alRespondentsAge = new ArrayList();
		ArrayList alRespondentsLevel = new ArrayList();
		ArrayList alRespondentsYear = new ArrayList();
		ArrayList alRespondentsDesignation = new ArrayList();
		ArrayList alRespondentsData = new ArrayList();
		String strPaperTitle = "";
		
		String strTotalUser = "1";
		
        try
        {
            getConnection();
            alRespondentsAge = objSurveyAdmin.getSurveyRespondentsData(connection, strPaperId, OIConsultationSqls.CONSULT_READ_AGE);
			alRespondentsLevel = objSurveyAdmin.getSurveyRespondentsData(connection, strPaperId, OIConsultationSqls.CONSULT_READ_LEVEL);
			alRespondentsYear = objSurveyAdmin.getSurveyRespondentsData(connection, strPaperId, OIConsultationSqls.CONSULT_READ_YEAR);
			alRespondentsDesignation = objSurveyAdmin.getSurveyRespondentsData(connection, strPaperId, OIConsultationSqls.CONSULT_READ_DESIGNATION);
			strPaperTitle = objCousult.getPaperTitle(connection, strPaperId,OIConsultationSqls.GET_PAPER_TITLE);
			
			alRespondentsData.add(alRespondentsAge);
			alRespondentsData.add(alRespondentsLevel);
			alRespondentsData.add(alRespondentsYear);
			alRespondentsData.add(alRespondentsDesignation);
			
			strTotalUser = objSurveyAdmin.getTotalUser(connection,strPaperId,OIConsultationSqls.TOTAL_USER);
            
			boolean publishFlag = new OIDAOConsultPaper().checkNotActivePaper(Integer.parseInt(strPaperId),connection);
            responseObject.addResponseObject("publishFlag",new Boolean(publishFlag).toString());
            responseObject.addResponseObject("PaperTitle", strPaperTitle);
        }
        catch(Exception e)
        {
            error = e.getMessage();
            logger.error(e);
            //logger.info(error);
        }
        finally
        {
            freeConnection();
            responseObject.addResponseObject("ConsultDemographicSummary", alRespondentsData);
			responseObject.addResponseObject("TotalUser", strTotalUser);
        }
        addToResponseObject();
        return responseObject;
    }
    
//	added by edmund
	public OIResponseObject getConsultResultDetail(String strPaperId, String strAge, String strLevel, String strDesignation, String strYear){
		
		OIDAOConsultFeedBack objCousult = new OIDAOConsultFeedBack();
    	OIDAOResponseAdmin objSurveyAdmin = new OIDAOResponseAdmin();
		ArrayList alReportData = new ArrayList();
		String strTotalUser = "1";
		String strPaperTitle = "";
		Hashtable questionRespondentTable = null;
		
		String strQuery = OIConsultationSqls.DETAIL_FRONT;
		int iCount = 0;
		if (!strAge.equals("0")){
			
			if(strAge.equals("1"))
			strQuery += OIConsultationSqls.DETAIL_AGE_BELOW_30;
			
			if(strAge.equals("2"))
				strQuery += OIConsultationSqls.DETAIL_AGE_30_40;
			
			if(strAge.equals("3"))
				strQuery += OIConsultationSqls.DETAIL_AGE_ABOVE_40;			
		}
		
		if (!strYear.equals("0")){
			
			if(strYear.equals("1"))
				strQuery += OIConsultationSqls.DETAIL_YEAR_BELOW_3;
			
			if(strYear.equals("2"))
				strQuery += OIConsultationSqls.DETAIL_YEAR_3_TO_5;
			
			if(strYear.equals("3"))
				strQuery += OIConsultationSqls.DETAIL_YEAR_5_t0_10;
			
			if(strYear.equals("4"))
				strQuery += OIConsultationSqls.DETAIL_YEAR_ABOVE_10;
		}
		
		if (!strDesignation.equals("0")){
			strQuery += OIConsultationSqls.DETAIL_DESIGNATION;
			iCount = 1;
		}
		
		if (!strLevel.equals("0")){
			strQuery += OIConsultationSqls.DETAIL_LEVEL;
			
			if(iCount == 1)
				iCount = 3;
			else
				iCount = 2;
		}
		
		strQuery += OIConsultationSqls.DETAIL_END;
		
		try 
		{
			getConnection();
			alReportData =objCousult.getConsultDetailReport(connection, strPaperId, strQuery, iCount, strDesignation, strLevel);
			strTotalUser = objSurveyAdmin.getTotalUser(connection, strPaperId, OIConsultationSqls.TOTAL_USER );
			strPaperTitle = objCousult.getPaperTitle(connection, strPaperId,OIConsultationSqls.GET_PAPER_TITLE);
			questionRespondentTable = objCousult.getNumberOfQuestionRespondent(connection, strPaperId);
		} 
		catch(SQLException se) 
		{
			error = ""+se.getErrorCode();
            logger.error(se);
		} 
		catch(Exception be)	
		{
			error = "OIDEFAULT";
			logger.error(" getSurveySummary => "+be);
		} 
		finally 
		{
			freeConnection();
			addToResponseObject();
			responseObject.addResponseObject("ConsultDetail", alReportData);
			responseObject.addResponseObject("TotalUser", strTotalUser);
			responseObject.addResponseObject("PaperTitle", strPaperTitle);
			responseObject.addResponseObject("QuestionRespondents", questionRespondentTable);
		}
		return responseObject;
	}
	
//	added by edmund
	public OIResponseObject getOpenQuestion(String strQuestionId){
		OIDAOResponseAdmin objSurveyResult = new OIDAOResponseAdmin();
		ArrayList alReportData = new ArrayList();
		
		String strQuery = OIConsultationSqls.OPEN_END_QUESTION;

		try 
		{
			getConnection();
			alReportData =objSurveyResult.getOpenEndQuesiton(connection, strQuestionId, strQuery);
		} 
		catch(SQLException se) 
		{
			error = ""+se.getErrorCode();
            logger.error(se);
		} 
		catch(Exception be)	
		{
			error = "OIDEFAULT";
			logger.error(" getSurveySummary => "+be);
		} 
		finally 
		{
			freeConnection();
			addToResponseObject();
			responseObject.addResponseObject("OpenEndQuestion", alReportData);
		}
		return responseObject;
	}

    /**
     * This method will call the OIDAOConsultPaper.removeAttachment() & pass OIBAConsultPaper as the input parameter 
     */
    public OIResponseObject removeAttachment(int pPaperId)
    {
        try
        {
            getConnection();
            connection.setAutoCommit(false);
            if (new OIDAOConsultPaper().checkActivePaper(pPaperId,connection))
            {
                boolean flag = new OIDAOConsultPaper().removeAttachment(pPaperId,connection);
            }
            else
            {
                error = OIDBRegistry.getValues("OI.CONS.PAPER.ACTIVE.ATTACHMENT");
            }
            connection.commit();
            connection.setAutoCommit(true);
        }
        catch(Exception e)
        {
            error = e.getMessage();
            logger.error(e);
            try
            {
                connection.rollback();
            }catch (Exception ex){}
            //logger.info(error);
        }
        finally
        {
            freeConnection();
        }
        addToResponseObject();
        return responseObject;

    }
    
    public OIResponseObject publishPaper(OIBAConsultPaper pOIBAConsultPaper)
    {
        try
        {
            getConnection();
            connection.setAutoCommit(false);
            if (new OIDAOConsultPaper().checkNotActivePaper(pOIBAConsultPaper.getPaperId(),connection))
            {
                new OIDAOConsultPaper().publishPaper(pOIBAConsultPaper,connection);
            }
            else
            {
                error = OIDBRegistry.getValues("OI.CONS.PAPER.ACTIVE.PUBLISHED");
            }
            connection.commit();
            connection.setAutoCommit(true);
        }
        catch(Exception e)
        {
            error = e.getMessage();
            logger.error(e);
            try
            {
                connection.rollback();
            }catch (Exception ex){}
            //logger.info(error);
        }
        finally
        {
            freeConnection();
        }
        addToResponseObject();
        return responseObject;
    }

    public OIResponseObject removePublishAttachment(int pPaperId)
    {
        try
        {
            getConnection();
            connection.setAutoCommit(false);
            boolean flag = new OIDAOConsultPaper().removePublishAttachment(pPaperId,connection);
            connection.commit();
            connection.setAutoCommit(true);
        }
        catch(Exception e)
        {
            error = e.getMessage();
            logger.error(e);
            try
            {
                connection.rollback();
            }catch (Exception ex){}
            //logger.info(error);
        }
        finally
        {
            freeConnection();
        }
        addToResponseObject();
        return responseObject;
    }

	public OIResponseObject importPaper(Integer importFromId, String userId)
    {
		String paperId = null;
		OIDAOConsultPaper daoConsultPaper = new OIDAOConsultPaper();
        try
        {
        	getConnection();
        	boolean doesPaperExists = daoConsultPaper.doesPaperExists(connection, importFromId);
			if (doesPaperExists)
			{            
				connection.setAutoCommit(false);
				paperId = new OIDAOConsultPaper().importPaper(importFromId,connection, userId);
				connection.commit();
	            connection.setAutoCommit(true);
			}
			else
			{
				error = "OI.CONS.ID.INVALID";
			}
           
            
        }
        catch(Exception e)
        {
            error = e.getMessage();
            logger.error(e);            
            try
            {
                connection.rollback();
            }catch (Exception ex){}
            //logger.info(error);
        }
        finally
        {
            freeConnection();
            addToResponseObject(); 
            responseObject.addResponseObject("paperId", paperId);
            responseObject.addResponseObject("error", error);
        }
       
        return responseObject;
    }
	
	public String getPaperDefaultMessage()
	{
		String defaultMessage = null;
		OIDAOConsultPaper objDAO = new OIDAOConsultPaper();
		try
		{
			getConnection();
			
			defaultMessage = objDAO.getPaperDefaultMessage(connection);
		}
		catch (Exception e)
		{
		}
		finally
		{
			freeConnection();
		}
		
		return defaultMessage;
	}
}
