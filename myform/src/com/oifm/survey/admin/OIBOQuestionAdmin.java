
package com.oifm.survey.admin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseBo;
import com.oifm.common.OIResponseObject;
import com.oifm.survey.OIBAQuestion;

public class OIBOQuestionAdmin extends OIBaseBo 
{
	private static Logger logger = Logger.getLogger(OIBOQuestionAdmin.class);

	public OIBOQuestionAdmin()	
	{
		super();
	}

	public OIResponseObject getQuestionList(OIBAQuestion objQuestionVo)	
	{
		OIDAOQuestionAdmin objQuestionAdmin = new OIDAOQuestionAdmin(); 
		OIDAOSectionAdmin objSectionAdmin = new OIDAOSectionAdmin(); 
		ArrayList alSectionList = null;
		Hashtable htbQuestionList = null;
		boolean isSurveyDivision = true;
 
		try 
		{
			getConnection();
			isSurveyDivision = (new OIDAOSurveyAdmin()).isSurveyOwnerDivision(connection, objQuestionVo.getStrSurveyId(), objQuestionVo.getStrDivisionCode());
			alSectionList = objSectionAdmin.getSectionHierarchy(connection, objQuestionVo.getStrSurveyId());
			htbQuestionList = objQuestionAdmin.getSectionsQuestionsList(connection, objQuestionVo.getStrSurveyId());
		} 
		catch(SQLException se) 
		{
			error = ""+se.getErrorCode();
            logger.error(se);
		} catch(Exception be)	
		{
			error = "OIDEFAULT";
			logger.error(" getQuestionList => "+be);
		} 
		finally 
		{
			freeConnection();
			addToResponseObject();
			objSectionAdmin.setLevelsHirarchy(alSectionList);
			responseObject.addResponseObject("SectionList", alSectionList);
			responseObject.addResponseObject("QuestionList", htbQuestionList);
			responseObject.addResponseObject("isSurveyDivision", new Boolean(isSurveyDivision));
		}
		return responseObject;
	}

	public OIResponseObject getQuestionDetails(OIBAQuestion objQuestionVo,OIBAQuestion objPrevQuestionVo)	
	{
		OIDAOSectionAdmin objSectionAdmin = new OIDAOSectionAdmin(); 
		OIDAOQuestionAdmin objQuestionAdmin = new OIDAOQuestionAdmin(); 
		OIDAOSurveyAdmin objSurveyAdmin = new OIDAOSurveyAdmin();
		boolean isCurrentlyValid = false;
		boolean isSurveyDivision = true;
		ArrayList alSectionList = null;
		String strTempSurvey = "";
		try 
		{
		    //logger.error(" StrQuestionId => "+objQuestionVo.getStrQuestionId() +" StrSectionId => "+ objQuestionVo.getStrSectionId() +" StrSurveyId => "+ objQuestionVo.getStrSurveyId());
			strTempSurvey = objQuestionVo.getStrSurveyId();
			getConnection();
			isSurveyDivision = objSurveyAdmin.isSurveyOwnerDivision(connection, objQuestionVo.getStrSurveyId(), objQuestionVo.getStrDivisionCode());
			isCurrentlyValid = !objSurveyAdmin.checkSurveyCanDelete(connection, objQuestionVo.getStrSurveyId());
			if(objQuestionVo!=null && objQuestionVo.getStrQuestionId() != null && !objQuestionVo.getStrQuestionId().equals("")) 
			{
				objQuestionVo = objQuestionAdmin.getQuestionDetails(connection, objQuestionVo.getStrQuestionId());
				if(objQuestionVo!=null  && objQuestionVo.getStrSectionId() != null && !objQuestionVo.getStrSectionId().equals(""))
				  objPrevQuestionVo = objQuestionAdmin.getPrevQuestionDetails(connection, objQuestionVo.getStrQuestionId(),"");
			} 
			else if(objQuestionVo!=null && objQuestionVo.getStrSectionId() != null && !objQuestionVo.getStrSectionId().equals("")) 
			{
		
				objQuestionVo = objQuestionAdmin.getTemplateQuestionDetails(connection, objQuestionVo.getStrSectionId());
				
				if(objQuestionVo!=null  && objQuestionVo.getStrSectionId() != null && !objQuestionVo.getStrSectionId().equals(""))
				  objPrevQuestionVo = objQuestionAdmin.getPrevQuestionDetails(connection, "", objQuestionVo.getStrSectionId());
				
			}
			alSectionList = objSectionAdmin.getSectionHierarchy(connection, strTempSurvey);
			 logger.error(" objPrevQuestionVo => "+objPrevQuestionVo);
			
		} 
		catch(SQLException se) 
		{
			error = ""+se.getErrorCode();
		} 
		catch(Exception be)	
		{
			error = "OIDEFAULT";
			logger.error(" getQuestionDetails => "+be);
		} 
		finally 
		{
			freeConnection();
			addToResponseObject();
//			objSectionAdmin.setLevelsHirarchy(alSectionList);
			responseObject.addResponseObject("SectionList", alSectionList);
			responseObject.addResponseObject("objQuestionVo", objQuestionVo);	
			responseObject.addResponseObject("objPrevQuestionVo", objPrevQuestionVo);		
			responseObject.addResponseObject("isCurrentlyValid", new Boolean(isCurrentlyValid));
			responseObject.addResponseObject("isSurveyDivision", new Boolean(isSurveyDivision));
		}
		return responseObject;
	}


	
	public OIResponseObject saveQuestionDetails(OIBAQuestion objQuestionVo, OIBAQuestion objPrevQuestionVo)	
	{
		boolean saveFlag = false;
		ArrayList alSectionList = null;
		OIDAOQuestionAdmin objQuestionAdmin = new OIDAOQuestionAdmin();
		
		
		try 
		{
			getConnection();
			connection.setAutoCommit(false);
			if(objQuestionVo.getStrQuestionId() != null && !objQuestionVo.getStrQuestionId().equals("")) 
			{
				// Updating next and previous questions in a likert block.
				// Condition check for likert block updation:
				// 1. For 1st question in block, change in question type: Question type is not L and
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
				if ((!"L".equalsIgnoreCase(objQuestionVo.getStrQuestionType()) &&
					 objQuestionVo.getStrUseSameLikert() != null &&
					 !objQuestionVo.getStrUseSameLikert().equals("")) ||
					(objQuestionVo.getStrUseSameLikert() == null ||
					  "".equalsIgnoreCase(objQuestionVo.getStrUseSameLikert())))
				{
					boolean flag = objQuestionVo.getStrUseSameLikert() == null ||
								   "".equalsIgnoreCase(objQuestionVo.getStrUseSameLikert());
					objQuestionAdmin.updateLikertBlockQuestions(connection, objQuestionVo.getStrQuestionId(), flag);
				}
				
				if (!"L".equalsIgnoreCase(objQuestionVo.getStrQuestionType()))
					objQuestionVo.setStrUseSameLikert("");
				
				saveFlag = objQuestionAdmin.modifyQuestion(connection, objQuestionVo);
				
				if (objQuestionVo.getStrUseSameLikert() != null &&
					objQuestionVo.getStrUseSameLikert().equalsIgnoreCase("0"));
					objQuestionAdmin.updateLikertBlockAnswers(connection, objQuestionVo.getStrQuestionId());
				objPrevQuestionVo = objQuestionAdmin.getPrevQuestionDetails(connection, objQuestionVo.getStrQuestionId(),"");
			} 
			else 
			{
				saveFlag = objQuestionAdmin.createNewQuestion(connection, objQuestionVo);
				objPrevQuestionVo = objQuestionAdmin.getPrevQuestionDetails(connection, "", objQuestionVo.getStrSectionId());
			}
			if(!saveFlag)
			{
			    error = "OIDEFAULT";
			}
			else
			{
			    addMessageList("SuccessSave");
			}
			alSectionList = (new OIDAOSectionAdmin()).getSectionHierarchy(connection, objQuestionVo.getStrSurveyId());
		} 
		catch(SQLException se) 
		{
			
			error = ""+se.getErrorCode();
            logger.error(se);
		} 
		catch(Exception be)	
		{
			
			error = "OIDEFAULT";
			logger.error(" saveQuestionDetails => "+be);
		} 
		finally 
		{
			try 
			{
				if(saveFlag)
				{
				    connection.commit();
				}
				else
				{
				    connection.rollback();
				}
				connection.setAutoCommit(true);
			} 
			catch(SQLException se) 
			{
				error = ""+se.getErrorCode();
				logger.error(" saveQuestionDetails => "+se);
			}
			freeConnection();
			addToResponseObject();
			responseObject.addResponseObject("SectionList", alSectionList);
			responseObject.addResponseObject("objPrevQuestionVo", objPrevQuestionVo);
		}
		return responseObject;
	}

	public OIResponseObject deleteQuestionInfo(OIBAQuestion objQuestionVo, OIBAQuestion objPrevQuestionVo)	
	{
		boolean deleteFlag = false;
		ArrayList alSectionList = null;
		OIDAOQuestionAdmin objQuestionAdmin = new OIDAOQuestionAdmin(); 
		try 
		{
			getConnection();
			connection.setAutoCommit(false);
			if(objQuestionVo.getStrSurveyId() != null && !objQuestionVo.getStrSurveyId().equals("")) 
			{
				// Updating next and previous questions in likert block
				// No condition check done in application.
				// Condition check is done in DB procedure. Operation will only proceed if
				// the question was (or still) a likert question and was (or still) in a block
				// NOTE: User can change the likert block setting or question type setting before
				//       clicking on the delete button
				
//				if (objQuestionVo.getStrQuestionType().equalsIgnoreCase("L") ||
//					(objQuestionVo.getStrUseSameLikert() != null &&
//					 !objQuestionVo.getStrUseSameLikert().equals("")))
						objQuestionAdmin.updateLikertBlockQuestions(connection, objQuestionVo.getStrQuestionId(), false);
				deleteFlag = (new OIDAOSurveyAdmin()).checkSurveyCanDelete(connection, objQuestionVo.getStrSurveyId());
				logger.debug(" checkSurveyCanDelete => "+deleteFlag);
				if(deleteFlag) 
				{
					deleteFlag = (new OIDAOResponseAdmin()).delteQuestionResponses(connection, objQuestionVo.getStrQuestionId());
					logger.debug(" delteQuestionResponses => "+deleteFlag);
					if(deleteFlag)
					{
					    deleteFlag = (new OIDAOQuestionAdmin()).deleteQuestion(connection, objQuestionVo.getStrQuestionId());
					}
					logger.debug(" delteQuestion => "+deleteFlag);
				}
				objPrevQuestionVo = objQuestionAdmin.getPrevQuestionDetails(connection, "", objQuestionVo.getStrSectionId());
			} 
			if(!deleteFlag) 
			{
				alSectionList = (new OIDAOSectionAdmin()).getSectionHierarchy(connection, objQuestionVo.getStrSurveyId());
				error = "OIDEFAULT";
			}
			else
			{
			    addMessageList("SuccessDelete");
			}
		} 
		catch(SQLException se) 
		{
			deleteFlag = false;
			error = ""+se.getErrorCode();
		} 
		catch(Exception be)	
		{
			deleteFlag = false;
			error = "OIDEFAULT";
			logger.error(" deleteQuestionInfo => "+be);
		} 
		finally 
		{
			try 
			{
				if(deleteFlag)
				{
				    connection.commit();
				}
				else
				{
				    connection.rollback();
				}
				connection.setAutoCommit(true);
			} 
			catch(SQLException se) 
			{
				error = ""+se.getErrorCode();
				logger.error(" deleteQuestionInfo => "+se);
			}
			freeConnection();
			addToResponseObject();
			responseObject.addResponseObject("SectionList", alSectionList);
			responseObject.addResponseObject("objPrevQuestionVo", objPrevQuestionVo);
		}
		return responseObject;
	}	

	public OIResponseObject reorderQuestion(OIBAQuestion objQuestionVo,String reorderType)	
	{
		boolean reorderFlag=false;
		ArrayList alSectionList = null;
		OIDAOQuestionAdmin objQuestionAdmin = new OIDAOQuestionAdmin(); 
		try 
		{
			getConnection();
			connection.setAutoCommit(false);
			if(objQuestionVo.getStrSurveyId() != null && !objQuestionVo.getStrSurveyId().equals("")) 
			{
				logger.error("question id BO== "+objQuestionVo.getStrQuestionId());
				reorderFlag = (new OIDAOQuestionAdmin()).reorderQuestion(connection, objQuestionVo.getStrQuestionId(), reorderType);
				logger.debug(" reOrderFlag => "+reorderFlag);
				
			} 
			if(!reorderFlag) 
			{
				error = "OIDEFAULT";
			}
			else
			{
			    addMessageList("SuccessReorder");
			}
		} 
		catch(SQLException se) 
		{
			reorderFlag = false;
			error = ""+se.getErrorCode();
		} 
		catch(Exception be)	
		{
			reorderFlag = false;
			error = "OIDEFAULT";
			logger.error(" deleteQuestionInfo => "+be);
		} 
		finally 
		{
			try 
			{
				if(reorderFlag)
				{
				    connection.commit();
				}
				else
				{
				    connection.rollback();
				}
				connection.setAutoCommit(true);
			} 
			catch(SQLException se) 
			{
				error = ""+se.getErrorCode();
				logger.error(" reOrderQuestionInfo => "+se);
			}
			freeConnection();
		
		}
		return responseObject;
	}	
}