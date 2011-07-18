
package com.oifm.survey.admin;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseBo;
import com.oifm.common.OIResponseObject;
import com.oifm.survey.OISurveyConstants;

public class OIBOTempUserAdmin extends OIBaseBo 
{
	private static Logger logger = Logger.getLogger(OIBOTempUserAdmin.class);

	public OIBOTempUserAdmin()	
	{
		super();
	}

	public OIResponseObject getSurveyBatchUsersList(OIBATempUser objTempUserVo)	
	{
		responseObject = fetchSurveyBatchUsersList(objTempUserVo);
		ArrayList alUsersList = null;
		if(responseObject.getResponseObject("error") == null && responseObject.getResponseObject("SurveyBatchUsersList") != null) 
		{
			alUsersList = (ArrayList)responseObject.getResponseObject("SurveyBatchUsersList");
			OITempUserEncrypter.decryptTempUser(alUsersList);
		}
		return responseObject;
	}	
	
	private OIResponseObject fetchSurveyBatchUsersList(OIBATempUser objTempUserVo)	
	{
		OIDAOTempUserAdmin objTempUserAdmin = new OIDAOTempUserAdmin(); 
		OIDAOSurveyAdmin objSurveyAdmin = new OIDAOSurveyAdmin(); 
		ArrayList alSurveyList = null;
		ArrayList alSurveyBatchList = null;
		ArrayList alSurveyBatchUsersList = null; 
		boolean isSurveyDivision = true;
		try 
		{
			getConnection();
			alSurveyList = objSurveyAdmin.getPublicSurveyList(connection, objTempUserVo.getStrDivisionCode());
			if(objTempUserVo.getStrSurveyId() != null && !objTempUserVo.getStrSurveyId().equals("")) 
			{
				isSurveyDivision = objSurveyAdmin.isSurveyOwnerDivision(connection, objTempUserVo.getStrSurveyId(), objTempUserVo.getStrDivisionCode());
				alSurveyBatchList = objTempUserAdmin.getSurveyBatchList(connection, objTempUserVo.getStrSurveyId());
				if(objTempUserVo.getStrBatchNo() != null && !objTempUserVo.getStrBatchNo().equals("")) 
				{
					alSurveyBatchUsersList = objTempUserAdmin.getSurveyBatchTempUsersList(connection, objTempUserVo.getStrSurveyId(), objTempUserVo.getStrBatchNo());
				}
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
			logger.error(" getSurveyBatchUsersList => "+be);
		} 
		finally 
		{
			freeConnection();
			addToResponseObject();
			responseObject.addResponseObject("SurveyList", alSurveyList);
			responseObject.addResponseObject("SurveyBatchList", alSurveyBatchList);
			responseObject.addResponseObject("SurveyBatchUsersList", alSurveyBatchUsersList);
			responseObject.addResponseObject("isSurveyDivision", new Boolean(isSurveyDivision));
		}
		return responseObject;
	}
	
	public OIResponseObject generateSaveTempUserInfo(OIBATempUser objTempUserVo)	
	{
		boolean saveFlag = false;
		OIRandomGen objRandomgen = null;
		OIDAOTempUserAdmin objTempUserAdmin = new OIDAOTempUserAdmin();
		String strBatchNo = "";
		ArrayList alUserIds = new ArrayList();
		ArrayList alPasswords = new ArrayList();
		ArrayList alTempUsers = null;
		
		try 
		{
			if(objTempUserVo.getStrSurveyId() != null && !objTempUserVo.getStrSurveyId().equals("")) 
			{
				objRandomgen = new OIRandomGen();
				alTempUsers = objRandomgen.getStringTokens(objTempUserVo.getNoOfUsers()*2, OISurveyConstants.TEMP_USER_ID_SIZE);
				if(alTempUsers.size() == objTempUserVo.getNoOfUsers()*2) 
				{
					alUserIds.addAll(alTempUsers.subList(0, objTempUserVo.getNoOfUsers()));
					alPasswords.addAll(alTempUsers.subList(objTempUserVo.getNoOfUsers(), alTempUsers.size()));
					alPasswords = OITempUserEncrypter.encrypt(alPasswords);
				}			

				getConnection();

				strBatchNo = objTempUserAdmin.getNewSurveyBatchNumber(connection, objTempUserVo.getStrSurveyId());
				if(strBatchNo != null && !strBatchNo.equals("") && alUserIds != null && alPasswords != null) 
				{
					objTempUserVo.setStrBatchNo(strBatchNo);
					saveFlag = saveTempUserInfo(objTempUserVo, alUserIds, alPasswords);
				}
			}
			if(!saveFlag)
			{
				error = "OIDEFAULT";
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
			logger.error(" generateSaveTempUserInfo => "+be);
		} 
		finally 
		{
			addToResponseObject();
			freeConnection();
		}
		return responseObject;
	}


	private boolean saveTempUserInfo(OIBATempUser objTempUserVo, ArrayList alUserIds, ArrayList alPasswords)	
	{
		boolean saveFlag = false;
		OIDAOTempUserAdmin objTempUserAdmin = new OIDAOTempUserAdmin(); 
		try 
		{
			connection.setAutoCommit(false);
			saveFlag = objTempUserAdmin.insertTempUsers(connection, objTempUserVo.getStrSurveyId(), objTempUserVo.getStrBatchNo(), alUserIds, alPasswords);
			if(!saveFlag)
			{
				error = "OIDEFAULT";
			}
		} 
		catch(SQLException se) 
		{
			saveFlag = false;
			error = ""+se.getErrorCode();
            logger.error(se);
		} 
		catch(Exception be)	
		{
			saveFlag = false;
			error = "OIDEFAULT";
			logger.error(" saveTempUserInfo => "+be);
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
				logger.error(" saveTempUserInfo => "+se);
			}
			addToResponseObject();
		}
		return saveFlag;
	}	

	public OIResponseObject deleteTempUserInfo(OIBATempUser objTempUserVo)	
	{
		boolean deleteFlag = false;
		OIDAOTempUserAdmin objTempUserAdmin = new OIDAOTempUserAdmin(); 
		try 
		{
			getConnection();
			if(objTempUserVo.getStrSurveyId() != null && !objTempUserVo.getStrSurveyId().equals("") && objTempUserVo.getStrBatchNo() != null && !objTempUserVo.getStrBatchNo().equals("")) 
			{
				deleteFlag = objTempUserAdmin.deleteSurveyBatchTempUsers(connection, objTempUserVo.getStrSurveyId(), objTempUserVo.getStrBatchNo());
			} 
			if(!deleteFlag)
			{
				error = "OIDEFAULT";
			}
			else 
			{
			    addMessageList("SuccessDelete");
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
			logger.error(" deleteTempUserInfo => "+be);
		} 
		finally 
		{
			addToResponseObject();
			freeConnection();
		}
		return responseObject;
	}	

	private ArrayList getTempUserList(OIBATempUser objTempUserVo) 
	{
		OIDAOTempUserAdmin objTempUserAdmin = new OIDAOTempUserAdmin();
		ArrayList alSurveyBatchUsersList = null;
		try 
		{
			getConnection();
			if(objTempUserVo.getStrSurveyId() != null && !objTempUserVo.getStrSurveyId().equals("") && objTempUserVo.getStrBatchNo() != null && !objTempUserVo.getStrBatchNo().equals("")) 
			{
				alSurveyBatchUsersList = objTempUserAdmin.getSurveyBatchTempUsersList(connection, objTempUserVo.getStrSurveyId(), objTempUserVo.getStrBatchNo());
			}
		} 
		catch(SQLException se) 
		{
			error = ""+se.getErrorCode();
		} 
		catch(Exception be)	
		{
			error = "OIDEFAULT";
			logger.error(" getSurveyBatchUsersList => "+be);
		} 
		finally 
		{
			addToResponseObject();
			freeConnection();
		}
		return alSurveyBatchUsersList;
	}
	
	public String exportTempUserList(OIBATempUser objTempUserVo) 
	{
		ArrayList alSurveyBatchUsersList = null;
		StringBuffer stbrUsersInfo = new StringBuffer();
		OIBATempUser objTempUser = null;
		try 
		{
			alSurveyBatchUsersList = getTempUserList(objTempUserVo);
			OITempUserEncrypter.decryptTempUser(alSurveyBatchUsersList);

			if(alSurveyBatchUsersList != null && alSurveyBatchUsersList.size() > 0) 
			{
				stbrUsersInfo.append("\nSurvey Id : \t"+objTempUserVo.getStrSurveyId());
				stbrUsersInfo.append("\tBatch No : \t"+objTempUserVo.getStrBatchNo());
				stbrUsersInfo.append("\n\nUser Id \t Password \t Obsolete ");
				for(int i=0; i< alSurveyBatchUsersList.size(); i++) 
				{
					objTempUser = (OIBATempUser) alSurveyBatchUsersList.get(i);
					stbrUsersInfo.append("\n"+objTempUser.getStrTempUserId());
					stbrUsersInfo.append("\t"+objTempUser.getStrPassword());
					stbrUsersInfo.append("\t"+objTempUser.getStrObsolete());
				}
			}
		} 
		catch(Exception be)	
		{
			error = "OIDEFAULT";
			logger.error(" exportTempUserList => "+be);
		} 
		return stbrUsersInfo.toString();
	}
}
