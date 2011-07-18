
package com.oifm.survey.admin;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseBo;
import com.oifm.common.OIResponseObject;
import com.oifm.survey.OIBASection;

public class OIBOSectionAdmin extends OIBaseBo 
{
	private static Logger logger = Logger.getLogger(OIBOSectionAdmin.class);

	public OIBOSectionAdmin()	
	{
		super();
	}

	public OIResponseObject getSectionDetails(OIBASection objSectionVo)	
	{
		OIDAOSectionAdmin objSectionAdmin = new OIDAOSectionAdmin(); 
		OIDAOSurveyAdmin objSurveyAdmin = new OIDAOSurveyAdmin();
		boolean isCurrentlyValid = false;
		boolean isSurveyDivision = true;
		ArrayList alSectionList = null;
		ArrayList alSectionParentsList = null;
		try 
		{
			getConnection();
			alSectionList = objSectionAdmin.getSectionHierarchy(connection, objSectionVo.getStrSurveyId());
			isSurveyDivision = objSurveyAdmin.isSurveyOwnerDivision(connection, objSectionVo.getStrSurveyId(), objSectionVo.getStrDivisionCode());
			if(objSectionVo.getStrSectionId() != null && !objSectionVo.getStrSectionId().equals("")) 
			{
				alSectionParentsList = objSectionAdmin.getSectionParentsList(connection, objSectionVo.getStrSurveyId(), objSectionVo.getStrSectionId());
				objSectionVo = objSectionAdmin.fetchSectionInfo(connection, objSectionVo.getStrSectionId());
			}
			isCurrentlyValid = !objSurveyAdmin.checkSurveyCanDelete(connection, objSectionVo.getStrSurveyId());
		} 
		catch(SQLException se) 
		{
			error = ""+se.getErrorCode();
            logger.error(se);
		} 
		catch(Exception be)	
		{
			error = "OIDEFAULT";
			logger.error(" getSectionDetails => "+be);
		} 
		finally 
		{
			freeConnection();
			objSectionAdmin.setLevelsHirarchy(alSectionList);
			responseObject.addResponseObject("SectionParentsList", alSectionParentsList);
			responseObject.addResponseObject("SectionList", alSectionList);
			responseObject.addResponseObject("objSectionVo", objSectionVo);
			responseObject.addResponseObject("isCurrentlyValid", new Boolean(isCurrentlyValid));
			responseObject.addResponseObject("isSurveyDivision", new Boolean(isSurveyDivision));
			addToResponseObject();
		}
		return responseObject;
	}
	
	public OIResponseObject saveSectionDetails(OIBASection objSectionVo)	
	{
		boolean saveFlag = false;
		OIDAOSectionAdmin objSectionAdmin = new OIDAOSectionAdmin(); 
		ArrayList alSectionList = null;
		ArrayList alSectionParentsList = null;
		try 
		{
			getConnection();
			if(objSectionVo.getStrSectionId() != null && !objSectionVo.getStrSectionId().equals("")) 
			{
				saveFlag = objSectionAdmin.modifySection(connection, objSectionVo);
			} 
			else 
			{
				saveFlag = objSectionAdmin.createNewSection(connection, objSectionVo);
			}
			alSectionList = objSectionAdmin.getSectionHierarchy(connection, objSectionVo.getStrSurveyId());
			if(!saveFlag) 
			{
				alSectionParentsList = objSectionAdmin.getSectionParentsList(connection, objSectionVo.getStrSurveyId(), objSectionVo.getStrSectionId());
				error = "OIDEFAULT";
			}
			else
			{
			    addMessageList("SuccessSave");
			}
		} 
		catch(SQLException se) 
		{
			error = ""+se.getErrorCode();
            logger.error(se);
		} 
		catch(Exception be)	
		{
			error = "OIDEFAULT";
			logger.error(" saveSectionDetails => "+be);
		} 
		finally 
		{
			freeConnection();
			objSectionAdmin.setLevelsHirarchy(alSectionList);
			responseObject.addResponseObject("SectionList", alSectionList);
			responseObject.addResponseObject("SectionParentsList", alSectionParentsList);
			addToResponseObject();
		}
		return responseObject;
	}

	public OIResponseObject deleteSectionInfo(OIBASection objSectionVo)	
	{
		boolean deleteFlag = false;
		ArrayList alSectionList = null;
		ArrayList alSectionParentsList = null;
		OIDAOSectionAdmin objSectionAdmin = new OIDAOSectionAdmin();
		try 
		{
			getConnection();
			connection.setAutoCommit(false);
			if(objSectionVo.getStrSurveyId() != null && !objSectionVo.getStrSurveyId().equals("")) 
			{
				deleteFlag = (new OIDAOSurveyAdmin()).checkSurveyCanDelete(connection, objSectionVo.getStrSurveyId());
				if(deleteFlag) 
				{
					deleteFlag = (new OIDAOResponseAdmin()).delteSectionResponses(connection, objSectionVo.getStrSectionId());
					if(deleteFlag)
					{
					    deleteFlag = (new OIDAOQuestionAdmin()).delteSectionQuestions(connection, objSectionVo.getStrSectionId());
					}
					if(deleteFlag)
					{
					    deleteFlag = objSectionAdmin.deleteSection(connection, objSectionVo.getStrSectionId());
					}
				}
			} 
			if(!deleteFlag) 
			{
				error = "OIDEFAULT";
			} 
			else 
			{
			    addMessageList("SuccessDelete");
			}
			alSectionList = objSectionAdmin.getSectionHierarchy(connection, objSectionVo.getStrSurveyId());
			alSectionParentsList = objSectionAdmin.getSectionParentsList(connection, objSectionVo.getStrSurveyId(), objSectionVo.getStrSectionId());
		} 
		catch(SQLException se) 
		{
			deleteFlag = false;
			error = ""+se.getErrorCode();
            logger.error(se);
		} 
		catch(Exception be)	
		{
			deleteFlag = false;
			error = "OIDEFAULT";
			logger.error(" deleteSectionInfo => "+be);
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
				logger.error(" deleteSurveyInfo => "+se);
			}
			freeConnection();
			addToResponseObject();
			objSectionAdmin.setLevelsHirarchy(alSectionList);
			responseObject.addResponseObject("SectionList", alSectionList);
			responseObject.addResponseObject("SectionParentsList", alSectionParentsList);
		}
		return responseObject;
	}
}
