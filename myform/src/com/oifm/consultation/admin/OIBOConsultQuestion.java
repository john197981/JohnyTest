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

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseBo;
import com.oifm.common.OIResponseObject;
import com.oifm.consultation.OIConsultConstant;
import com.oifm.consultation.OIDAOConsultFeedBack;
import com.oifm.consultation.OIDAOConsultResponse;
import com.oifm.utility.OIDBRegistry;
public class OIBOConsultQuestion extends OIBaseBo 
{
    Logger logger = Logger.getLogger(OIBOConsultQuestion.class.getName());

    /**
     * This method will call the OIDAOConsultQuestion.read() & pass questionID as the input parameter
     * 
     * and returns responseObject, which holds OIBAConsultQuestion object returned by OIDAOConsultQuestion.read() method 
     */
    public OIResponseObject readQuestion(int pQuestionId)
    {
        try
        {
            getConnection();
            OIBAConsultQuestion aOIBAConsultQuestion = new OIDAOConsultQuestion().read(pQuestionId,connection);
            responseObject.addResponseObject(OIConsultConstant.K_aOIBAConsultQuestion,aOIBAConsultQuestion);
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
    
    /**
     * This method will call the OIDAOConsultQuestion.removeQuestion() & pass OIBAConsultQuestion as the input parameter 
     */
    public OIResponseObject removeQuestion(int questionId,int pPaperId)
    {
        try
        {
            getConnection();
            connection.setAutoCommit(false);
            if (new OIDAOConsultPaper().checkActivePaper(pPaperId,connection))
            {
            	OIDAOConsultQuestion objDAOConsultQuestion = new OIDAOConsultQuestion();
            	
            	// Updating next and previous questions in likert block
				// No condition check done in application.
				// Condition check is done in DB procedure. Operation will only proceed if
				// the question was (or still) a likert question and was (or still) in a block
				// NOTE: User can change the likert block setting or question type setting before
				//       clicking on the delete button
            	objDAOConsultQuestion.updateLikertBlockQuestions(connection, Integer.toString(questionId), false);
            	
            	new OIDAOConsultResponse().deleteResponses(pPaperId,connection);
	            new OIDAOConsultFeedBack().deleteFeedBacks(pPaperId,connection);
	            objDAOConsultQuestion.removeQuestion(questionId,connection);
            }
            else
            {
                error = OIDBRegistry.getValues("OI.CONS.QUESTION.ACTIVE");
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
     * This method will call the OIDAOConsultQuestion.save() & pass OIBAConsultQuestion as the input parameter 
     */
    public OIResponseObject saveQuestion(OIBAConsultQuestion pOIBAConsultQuestion)
    {
        try
        {
            getConnection();
            connection.setAutoCommit(false);
            OIDAOConsultQuestion objDAO = new OIDAOConsultQuestion();
            
            // Updating next and previous questions in a likert block.
			// Condition check for likert block updation:
			// 1. For 1st question in block, change in question type: Question is not likert and
			//    strUseSameLikert is not null (was previously a likert block question)
			//    NOTE: For 1st question in the block, strUseSameLikert is = 0.
			// 2. For questions other than 1st question in the block.
			//    strUseSameLikert will be null if the user uncheck the checkbox (doesn't matter if
			//    the question type changes or still a likert scale question) and some number if
			//    the checkbox is checked.
			//
			// These are the only condition checks done in the application side. The rest of the checks
			// are done in DB procedure. Operation will proceed if the question was (or still) a likert
			// question and was in a block. The checks are divided into 2 places because checking
			// for question's previous setting is easier to be done in DB rather than in application
			if ((!"1".equalsIgnoreCase(pOIBAConsultQuestion.getLikertScale()) &&
				pOIBAConsultQuestion.getUseSameLikert() != null &&
				!pOIBAConsultQuestion.getUseSameLikert().equals("")) ||
				(pOIBAConsultQuestion.getUseSameLikert() == null ||
				 "".equalsIgnoreCase(pOIBAConsultQuestion.getUseSameLikert())))
			{
				boolean flag = pOIBAConsultQuestion.getUseSameLikert() == null ||
							   "".equalsIgnoreCase(pOIBAConsultQuestion.getUseSameLikert());
				objDAO.updateLikertBlockQuestions(connection, Integer.toString(pOIBAConsultQuestion.getQuestionId()), flag);
			}
			
			if (!"1".equalsIgnoreCase(pOIBAConsultQuestion.getLikertScale()))
				pOIBAConsultQuestion.setUseSameLikert("");
            
            boolean flag = objDAO.save(pOIBAConsultQuestion,connection);
            
            if (pOIBAConsultQuestion.getQuestionId() != 0 &&
            	pOIBAConsultQuestion.getUseSameLikert() != null &&
            	pOIBAConsultQuestion.getUseSameLikert().equals("0"))
            	objDAO.updateLikertBlockAnswers(connection, Integer.toString(pOIBAConsultQuestion.getQuestionId()));
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

	 public OIResponseObject reorderQuestion(int questionId,int order)
    {
        try
        {
            getConnection();
            //connection.setAutoCommit(false);
            new OIDAOConsultQuestion().reorderQuestion(questionId,order, connection);
            //connection.commit();
            //connection.setAutoCommit(true);
        }
        catch(Exception e)
        {
            error = e.getMessage();
            logger.error(e);
//            try
//            {
//                connection.rollback();
//            }catch (Exception ex){}
            //logger.info(error);
        }
        finally
        {
            freeConnection();
        }
        addToResponseObject();
        return responseObject;
    }
	public OIBAConsultQuestion getPreviousQuestion(String pQuestionId, String paperId)
    {
		 OIBAConsultQuestion aOIBAConsultQuestion =null;
        try
        {
            getConnection();
            aOIBAConsultQuestion = new OIDAOConsultQuestion().getPreviousQuestion(pQuestionId,paperId,connection);
            
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
       
        return aOIBAConsultQuestion;
    }
	
}
