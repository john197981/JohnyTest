package com.oifm.survey.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.CallableStatement;

import org.apache.log4j.Logger;
import org.apache.struts.util.LabelValueBean;

import com.oifm.common.OIPageInfoBean;
import com.oifm.survey.OIBASurvey;
import com.oifm.survey.OIDAOSurveyUser;
import com.oifm.survey.OISurveySqls;
import com.oifm.utility.OISQLUtilities;
import com.oifm.utility.OIUtilities;

public class OIDAOSurveyAdmin extends OIDAOSurveyUser 
{
	private static final Logger logger = Logger.getLogger(OIDAOSurveyAdmin.class);

	public OIDAOSurveyAdmin()	
	{
	}

	public int getSurveysCount(Connection con) throws SQLException 
	{
		return OISQLUtilities.getNumber(con, OISurveySqls.TOTAL_SURVEYS, "getSurveysCount", "OIDAOSurveyAdmin");
	}
	
	public int getDivisionSurveysCount(Connection con, String strDivCode) throws SQLException 
	{
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int retCount = 0;
			try
			{
				pstmt = con.prepareStatement(OISurveySqls.TOTAL_DIVISION_SURVEYS);
				pstmt.setInt(1, Integer.parseInt(strDivCode));
				rs = pstmt.executeQuery();
				if (rs.next()) 
				{
					retCount = rs.getInt(1);
				}
			} 
			catch(SQLException se) 
			{
			    retCount = 0;
				throw se;
			} 
			finally 
			{
				OISQLUtilities.closeRsetPstatement(rs, pstmt);
			}

			return retCount;
	}
	
	public ArrayList getSurveyList(Connection con, OIPageInfoBean pageInfo) throws SQLException 
	{
		ArrayList alSurveyList = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OIBASurvey objSurvey = null;
		
		try 
		{
			pstmt = con.prepareStatement(OISurveySqls.CURRENT_SURVEYS);
			pstmt.setInt(1, pageInfo.getStartRec());
			pstmt.setInt(2, pageInfo.getEndRec());
			logger.info("getSurveyList-->" + OISurveySqls.CURRENT_SURVEYS);
			logger.info(" No of records : "+pageInfo.getTotalRec() + " StartRec : "+pageInfo.getStartRec() + " EndRec : "+pageInfo.getEndRec());
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				objSurvey = new OIBASurvey();
				objSurvey.setStrSurveyId(rs.getString("SURVEYID"));
				objSurvey.setStrSurveyName(rs.getString("NAME"));
				objSurvey.setStrDescription(rs.getString("DESCRIPTION"));
				objSurvey.setStrFromDate(rs.getString("START_ON"));
				objSurvey.setStrToDate(rs.getString("EXPIRY_ON"));
				objSurvey.setStrSurveyType(rs.getString("SECURITY"));
				objSurvey.setStrPublishedStatus(rs.getString("PUBLISHED_ST"));
				objSurvey.setStrPublishedOn(rs.getString("PUBLISHEDON"));
				objSurvey.setStrMailStatus(rs.getString("MAIL_STATUS"));
				objSurvey.setStrIsActive(rs.getString("ACTIVE"));
				objSurvey.setStrDivisionName(rs.getString("DIVISIONNAME"));
				objSurvey.setStrDivisionCode(rs.getString("DIVISIONCODE"));
				objSurvey.setStrNoOfResponse(rs.getString("noOfRes"));
				objSurvey.setStrTargetAudience(rs.getString("TARGETAUDIENCE"));

				alSurveyList.add(objSurvey);
			}
		} 
		catch(SQLException se) 
		{
		    logger.error(" getSurveyList() "+se.getMessage());
			throw se;
		} 
		finally 
		{
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}
		return alSurveyList;
	}

	//added by edmund
	public ArrayList getSurveyList(Connection con, OIPageInfoBean pageInfo, OIBASurvey objSurveyVo) throws SQLException 
	{
		ArrayList alSurveyList = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OIBASurvey objSurvey = null;
		
		//System.out.println("OIDAOSurveyAdmin: getSurveyList - sortkey : "+objSurveyVo.getHidSortKey());
		String strQuery = OISurveySqls.CURRENT_SURVEYS_SORT_FRONT;
		
		if(objSurveyVo.getHidSortKey().equals("MAIL_STATUS"))
		{
			/*strQuery += " OSS.EXPIRY_ON ";
			strQuery += objSurveyVo.getHidOrder() + ",";
			strQuery += OISurveySqls.SORT_MAIL_STATUS;*/
			
		}
		
		if (objSurveyVo.getHidSortKey().equalsIgnoreCase("OSS.start_on") || objSurveyVo.getHidSortKey().equalsIgnoreCase("PUBLISHED_ON") || objSurveyVo.getHidSortKey().equalsIgnoreCase("NOOFRES") || objSurveyVo.getHidSortKey().equalsIgnoreCase("SURVEYID"))
			strQuery +=  objSurveyVo.getHidSortKey();
		else
			strQuery += "UPPER(" + objSurveyVo.getHidSortKey() + ")";
		strQuery += " ";
		strQuery += objSurveyVo.getHidOrder();
		strQuery += OISurveySqls.CURRENT_SURVEYS_SORT_BACK;
		
		try 
		{
			pstmt = con.prepareStatement(strQuery);
			pstmt.setInt(1, pageInfo.getStartRec());
			pstmt.setInt(2, pageInfo.getEndRec());
			logger.info("getSurveyList-->" + strQuery);
			logger.info(" No of records : "+pageInfo.getTotalRec() + " StartRec : "+pageInfo.getStartRec() + " EndRec : "+pageInfo.getEndRec());
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				objSurvey = new OIBASurvey();
				objSurvey.setStrSurveyId(rs.getString("SURVEYID"));
				objSurvey.setStrSurveyName(rs.getString("NAME"));
				objSurvey.setStrDescription(rs.getString("DESCRIPTION"));
				objSurvey.setStrFromDate(rs.getString("START_ON"));
				objSurvey.setStrToDate(rs.getString("EXPIRY_ON"));
				objSurvey.setStrSurveyType(rs.getString("SECURITY"));
				objSurvey.setStrPublishedStatus(rs.getString("PUBLISHED_ST"));
				objSurvey.setStrPublishedOn(rs.getString("PUBLISHEDON"));
				objSurvey.setStrMailStatus(rs.getString("MAIL_STATUS"));
				objSurvey.setStrIsActive(rs.getString("ACTIVE"));
				objSurvey.setStrDivisionName(rs.getString("DIVISIONNAME"));
				objSurvey.setStrDivisionCode(rs.getString("DIVISIONCODE"));
				objSurvey.setStrNoOfResponse(rs.getString("noOfRes"));
				objSurvey.setStrTargetAudience(rs.getString("TARGETAUDIENCE"));
				
				alSurveyList.add(objSurvey);
			}
		} 
		catch(SQLException se) 
		{
		    logger.error(" getSurveyList() "+se.getMessage());
			throw se;
		} 
		finally 
		{
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}
		return alSurveyList;
	}
	
//	added by edmund
	public ArrayList getDivisionSurveyList(Connection con, OIPageInfoBean pageInfo, OIBASurvey objSurveyVo) throws SQLException 
	{
		ArrayList alSurveyList = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OIBASurvey objSurvey = null;
		
		//System.out.println("DAO: getDivisionSurveyList - sortkey : "+objSurveyVo.getHidSortKey());
		String strQuery = OISurveySqls.CURRENT_SURVEYS_DIVISIONS_FRONT;
		
		if(objSurveyVo.getHidSortKey().equals("MAIL_STATUS"))
		{
			/*strQuery += " OSS.EXPIRY_ON ";
			strQuery += objSurveyVo.getHidOrder() + ",";
			strQuery += OISurveySqls.SORT_MAIL_STATUS;*/
			
		}
		
		strQuery += objSurveyVo.getHidSortKey();
		strQuery += " ";
		strQuery += objSurveyVo.getHidOrder();
		
		if (objSurveyVo.getHidSortKey().equalsIgnoreCase("START_ON"))
		{
			strQuery += ", EXPIRY_ON";
			strQuery += " ";
			strQuery += objSurveyVo.getHidOrder();
		}
		strQuery += OISurveySqls.CURRENT_SURVEYS_DIVISIONS_BACK;
		
		try 
		{
			pstmt = con.prepareStatement(strQuery);
			pstmt.setInt(1,Integer.parseInt(objSurveyVo.getStrDivisionCode()) );
			pstmt.setInt(2, pageInfo.getStartRec());
			pstmt.setInt(3, pageInfo.getEndRec());
			
			logger.info("getSurveyList-->" + strQuery);
			logger.info(" No of records : "+pageInfo.getTotalRec() + " StartRec : "+pageInfo.getStartRec() + " EndRec : "+pageInfo.getEndRec());
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				objSurvey = new OIBASurvey();
				objSurvey.setStrSurveyId(rs.getString("SURVEYID"));
				objSurvey.setStrSurveyName(rs.getString("NAME"));
				objSurvey.setStrDescription(rs.getString("DESCRIPTION"));
				objSurvey.setStrFromDate(rs.getString("START_ON"));
				objSurvey.setStrToDate(rs.getString("EXPIRY_ON"));
				objSurvey.setStrSurveyType(rs.getString("SECURITY"));
				objSurvey.setStrPublishedStatus(rs.getString("PUBLISHED_ST"));
				objSurvey.setStrPublishedOn(rs.getString("PUBLISHEDON"));
				objSurvey.setStrMailStatus(rs.getString("MAIL_STATUS"));
				objSurvey.setStrIsActive(rs.getString("ACTIVE"));
				objSurvey.setStrDivisionName(rs.getString("DIVISIONNAME"));
				objSurvey.setStrDivisionCode(rs.getString("DIVISIONCODE"));
				objSurvey.setStrNoOfResponse(rs.getString("noOfRes"));
				objSurvey.setStrTargetAudience(rs.getString("TARGETAUDIENCE"));
				
				alSurveyList.add(objSurvey);
			}
		} 
		catch(SQLException se) 
		{
		    logger.error(" getDivisionSurveyList() "+se.getMessage());
			throw se;
		} 
		finally 
		{
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}
		return alSurveyList;
	}
	
	//added by edmund
	public ArrayList getDivisionSurveyList(Connection con, OIPageInfoBean pageInfo, String strDivCode) throws SQLException 
	{
		ArrayList alSurveyList = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OIBASurvey objSurvey = null;
		
		try 
		{
			pstmt = con.prepareStatement(OISurveySqls.CURRENT_SURVEYS_DIVISIONS_LIST);
			pstmt.setInt(1,Integer.parseInt(strDivCode) );
			pstmt.setInt(2, pageInfo.getStartRec());
			pstmt.setInt(3, pageInfo.getEndRec());
			
			logger.info("getSurveyList-->" + OISurveySqls.CURRENT_SURVEYS_DIVISIONS_LIST);
			logger.info(" No of records : "+pageInfo.getTotalRec() + " StartRec : "+pageInfo.getStartRec() + " EndRec : "+pageInfo.getEndRec());
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				objSurvey = new OIBASurvey();
				objSurvey.setStrSurveyId(rs.getString("SURVEYID"));
				objSurvey.setStrSurveyName(rs.getString("NAME"));
				objSurvey.setStrDescription(rs.getString("DESCRIPTION"));
				objSurvey.setStrFromDate(rs.getString("START_ON"));
				objSurvey.setStrToDate(rs.getString("EXPIRY_ON"));
				objSurvey.setStrSurveyType(rs.getString("SECURITY"));
				objSurvey.setStrPublishedStatus(rs.getString("PUBLISHED_ST"));
				objSurvey.setStrPublishedOn(rs.getString("PUBLISHEDON"));
				objSurvey.setStrMailStatus(rs.getString("MAIL_STATUS"));
				objSurvey.setStrIsActive(rs.getString("ACTIVE"));
				objSurvey.setStrDivisionName(rs.getString("DIVISIONNAME"));
				objSurvey.setStrDivisionCode(rs.getString("DIVISIONCODE"));
				objSurvey.setStrNoOfResponse(rs.getString("noOfRes"));
				objSurvey.setStrTargetAudience(rs.getString("TARGETAUDIENCE"));

				alSurveyList.add(objSurvey);
			}
		} 
		catch(SQLException se) 
		{
		    logger.error(" getDivisionSurveyList() "+se.getMessage());
			throw se;
		} 
		finally 
		{
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}
		return alSurveyList;
	}
	
	public ArrayList getSurveyDivision(Connection con) throws SQLException 
	{
		ArrayList alSurveyList = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OIBASurvey objSurvey = null;
		
		try 
		{
			pstmt = con.prepareStatement(OISurveySqls.CURRENT_SURVEY_DIVISIONS);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				objSurvey = new OIBASurvey();
				
				objSurvey.setStrDivisionName(rs.getString("DIVISIONNAME"));
				objSurvey.setStrDivisionCode(rs.getString("DIVISIONCODE"));
				alSurveyList.add(objSurvey);
			}
		} 
		catch(SQLException se) 
		{
		    logger.error(" getSurveyList() "+se.getMessage());
			throw se;
		} 
		finally 
		{
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}
		return alSurveyList;
	}
	
	public ArrayList getPublicSurveyList(Connection con, String strDivisionCode) throws SQLException 
	{
		ArrayList alSurveyList = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OIBASurvey objSurvey = null;
		try 
		{
			pstmt = con.prepareStatement(OISurveySqls.SURVEYS_LIST_TEMP_USERS);
			pstmt.setString(1, strDivisionCode);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				objSurvey = new OIBASurvey();
				objSurvey.setStrSurveyId(rs.getString("SURVEYID"));
				objSurvey.setStrSurveyName(rs.getString("NAME"));
				alSurveyList.add(objSurvey);
			}
		} 
		catch(SQLException se) 
		{
		    logger.error(" getPublicSurveyList() "+se.getMessage());
			throw se;
		} 
		finally 
		{
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}
		return alSurveyList;
	}

	public String getNewSurveyId(Connection con) throws SQLException 
	{
		return OISQLUtilities.getString(con, OISurveySqls.NEW_SURVEY_ID, "getNewSurveyId", "OIDAOSurveyAdmin");
	}

	public boolean isSurveyOwnerDivision(Connection con, String strSurveyId, String strDivisionCode) throws SQLException 
	{
		ArrayList alSurveyValues = new ArrayList();
		boolean isSurveyDivision = false;
		if(strSurveyId != null) 
		{
			alSurveyValues.add(strSurveyId);
			alSurveyValues.add(strDivisionCode);
			isSurveyDivision = OISQLUtilities.checkExists(con, OISurveySqls.IS_SURVEY_DIVISION, alSurveyValues, "isSurveyOwnerDivision", "OIDAOSurveyAdmin");
		}
		return isSurveyDivision;
	}
	
	public boolean createNewSurvey(Connection con, OIBASurvey objSurvey) throws SQLException 
	{
		boolean insertFlag = false;
		ArrayList alSurveyValues = new ArrayList();
		CallableStatement cstmt=null;
		if(objSurvey != null) 
		{
			alSurveyValues.add(objSurvey.getStrSurveyId());
			alSurveyValues.add(objSurvey.getStrSurveyName());
			alSurveyValues.add(objSurvey.getStrDescription());
			alSurveyValues.add(objSurvey.getStrInstruction());
			alSurveyValues.add(objSurvey.getStrFromDate());
			alSurveyValues.add(objSurvey.getStrToDate());
			alSurveyValues.add(objSurvey.getStrSurveyType());
			alSurveyValues.add(objSurvey.getStrReminderMode())	;
			alSurveyValues.add(objSurvey.getStrCreatedBy());
			if(objSurvey.getStrDivisionCode()!=null)
			{
				alSurveyValues.add(objSurvey.getStrDivisionCode());
			}
			else
			{
				alSurveyValues.add("");
			}
			alSurveyValues.add(objSurvey.getStrTargetAudience());
			alSurveyValues.add(objSurvey.getStrContactPerson());
			alSurveyValues.add(objSurvey.getStrPhone());
			alSurveyValues.add(objSurvey.getStrEmailAddress());
			//added new for SR starts
			alSurveyValues.add(objSurvey.getStrCompletionTime());
			alSurveyValues.add(objSurvey.getStrFindingsPlannedDt());
			logger.error("objSurvey.getStrViewFindingType()=="+objSurvey.getStrViewFindingType());
			logger.error("objSurvey.getStrAcknowledgeMode()=="+objSurvey.getStrAcknowledgeMode());
			//alSurveyValues.add(objSurvey.getStrViewFindingType());
			//alSurveyValues.add(objSurvey.getStrAcknowledgeMode());
			if(objSurvey.getStrViewFindingType()!=null)
			{
				alSurveyValues.add(objSurvey.getStrViewFindingType());
			}
			else
			{
				alSurveyValues.add("");
			}
			if(objSurvey.getStrAcknowledgeMode()!=null)
			{
				alSurveyValues.add(objSurvey.getStrAcknowledgeMode());
			}
			else
			{
				alSurveyValues.add("");
			}
			if(objSurvey.getStrOpenTag()!=null)
			{
				alSurveyValues.add(objSurvey.getStrOpenTag());
			}
			else
			{
				alSurveyValues.add("");
			}
			//alSurveyValues.add(objSurvey.getStrOpenTag());
			alSurveyValues.add(objSurvey.getStrTagWords()); 
			alSurveyValues.add(objSurvey.getStrEmailDate());
			alSurveyValues.add(objSurvey.getStrEmailMessage().replaceAll("<<<ID>>>",objSurvey.getStrSurveyId()));
			alSurveyValues.add(objSurvey.getOutsideMOEChecked());	// Added by K.K.Kumaresan on Jan 19,2008
			logger.error("alSurveyValues == "+alSurveyValues);
			//ends
			insertFlag = (OISQLUtilities.executeSingle(con, OISurveySqls.CREATE_SURVEY, alSurveyValues, "createNewSurvey", "OIDAOSurveyAdmin") == 1);

			if(insertFlag)
			{
				try{
						cstmt= con.prepareCall(OISurveySqls.ADD_SU_MEMBERS);
						cstmt.setInt(1,Integer.parseInt(objSurvey.getStrSurveyId()));
						cstmt.setString(2,objSurvey.getStrTargetGpIds());
						cstmt.execute();
					} catch(SQLException se) {
							logger.error(" add survey gp() "+se.getMessage());
							throw se;
					}
					finally {
						OISQLUtilities.closeStatement(cstmt);
					}
			}
			
			
		}
		return insertFlag;
	}
	
	

	public boolean modifySurvey(Connection con, OIBASurvey objSurvey) throws SQLException 
	{
		boolean modifyFlag = false;
		ArrayList alSurveyValues = new ArrayList();
		CallableStatement cstmt=null;
		if(objSurvey != null) 
		{
			alSurveyValues.add(objSurvey.getStrSurveyName());
			alSurveyValues.add(objSurvey.getStrDescription());
			alSurveyValues.add(objSurvey.getStrInstruction());
			alSurveyValues.add(objSurvey.getStrFromDate());
			alSurveyValues.add(objSurvey.getStrToDate());
			alSurveyValues.add(objSurvey.getStrSurveyType());
			alSurveyValues.add(objSurvey.getStrReminderMode());
			alSurveyValues.add(objSurvey.getStrIsActive());
			alSurveyValues.add(objSurvey.getStrTargetAudience());
			alSurveyValues.add(objSurvey.getStrContactPerson());
			alSurveyValues.add(objSurvey.getStrPhone());
			alSurveyValues.add(objSurvey.getStrEmailAddress());
			//added new for SR starts
			alSurveyValues.add(objSurvey.getStrCompletionTime());
			alSurveyValues.add(objSurvey.getStrFindingsPlannedDt());
			alSurveyValues.add(objSurvey.getStrViewFindingType());
			alSurveyValues.add(objSurvey.getStrAcknowledgeMode());
			alSurveyValues.add(objSurvey.getStrOpenTag()); 
			alSurveyValues.add(objSurvey.getStrTagWords());
			alSurveyValues.add(objSurvey.getStrEmailDate());
			alSurveyValues.add(objSurvey.getStrEmailMessage());
			
			// added by K.K.Kumaresan on Jan 19,2008
			alSurveyValues.add(objSurvey.getOutsideMOEChecked());
			 
			//ends
			alSurveyValues.add(objSurvey.getStrSurveyId());
			modifyFlag = (OISQLUtilities.executeSingle(con, OISurveySqls.UPDATE_SURVEY, alSurveyValues, "modifySurvey", "OIDAOSurveyAdmin") == 1);

			if(modifyFlag)
			{
				try{
						cstmt= con.prepareCall(OISurveySqls.ADD_SU_MEMBERS);
						cstmt.setInt(1,Integer.parseInt(objSurvey.getStrSurveyId()));
						cstmt.setString(2,objSurvey.getStrTargetGpIds());
						cstmt.execute();
					} catch(SQLException se) {
							logger.error(" add survey gp() "+se.getMessage());
							throw se;
					}
					finally {
						OISQLUtilities.closeStatement(cstmt);
					}
			}
			
		}
		return modifyFlag;
	}

	public boolean modifySurveyPublish(Connection con, OIBASurvey
			objSurvey) throws SQLException
			{
			boolean modifyFlag = false;
			ArrayList alSurveyValues = new ArrayList();
			if(objSurvey != null)
			{
			alSurveyValues.add(objSurvey.getStrSummary());
			if(!OIUtilities.replaceNull(objSurvey.getStrAttachedFile()).equals("")){
			alSurveyValues.add(objSurvey.getStrAttachedFile());
			}

			alSurveyValues.add(objSurvey.getStrAcknowledgeMode());
			alSurveyValues.add("Y");
			alSurveyValues.add(objSurvey.getStrSurveyId());

			if(!OIUtilities.replaceNull(objSurvey.getStrAttachedFile()).equals("")){
			modifyFlag = (OISQLUtilities.executeSingle(con, OISurveySqls.UPDATE_SURVEY_PUBLISH, alSurveyValues, "modifySurveyPublish", "OIDAOSurveyAdmin") == 1);
			}else{
			modifyFlag = (OISQLUtilities.executeSingle(con, OISurveySqls.UPDATE_SURVEY_PUBLISH_WO_FILE, alSurveyValues, "modifySurveyPublish", "OIDAOSurveyAdmin") == 1);
			}
			}
			return modifyFlag;
			}


	public boolean removeSurveyAttchedInfo(Connection con, String strSurveyId) throws SQLException 
	{
		boolean modifyFlag = false;
		ArrayList alSurveyValues = new ArrayList();
		// edited by K.K.Kumaresan on Jan 15,2008 for resetting the file name.
		if(strSurveyId != null && !strSurveyId.equals("")) 
		{
			alSurveyValues.add(strSurveyId);
			modifyFlag = (OISQLUtilities.executeSingle(con, OISurveySqls.RESET_SURVEY_PUBLISH_ATTCHMENT, alSurveyValues, "removeSurveyAttchedInfo", "OIDAOSurveyAdmin") == 1);
		}
		return modifyFlag;
	}

	public boolean activateSurvey(Connection con, String strSurveyId) throws SQLException 
	{
		boolean modifyFlag = false;
		ArrayList alSurveyValues = new ArrayList();
		logger.debug("activateSurvey() => "+strSurveyId);
		if(strSurveyId != null && !strSurveyId.equals("")) 
		{
			alSurveyValues.add(strSurveyId);
			modifyFlag = (OISQLUtilities.executeSingle(con, OISurveySqls.ACTIVATE_SURVEY, alSurveyValues, "activateSurvey", "OIDAOSurveyAdmin") == 1);
		}
		return modifyFlag;
	}
	
	public boolean deleteSurvey(Connection con, String strSurveyId) throws SQLException 
	{
		boolean deleteFlag = false;
		ArrayList alSurveyValues = new ArrayList();
		if(strSurveyId != null) 
		{
			alSurveyValues.add(strSurveyId);
			deleteFlag = (OISQLUtilities.executeSingle(con, OISurveySqls.DELETE_SURVEY, alSurveyValues, "deleteSurvey", "OIDAOSurveyAdmin") == 1);
		}
		return deleteFlag;
	}

	public boolean checkSurveyCanDelete(Connection con, String strSurveyId) throws SQLException 
	{
		boolean deleteFlag = false;
		ArrayList alSurveyValues = new ArrayList();
		if(strSurveyId != null) 
		{
			alSurveyValues.add(strSurveyId);
			deleteFlag = OISQLUtilities.checkExists(con, OISurveySqls.CHECK_DELETE_SURVEY, alSurveyValues, "checkSurveyCanDelete", "OIDAOSurveyAdmin");
		}
		return deleteFlag;
	}

	public ArrayList getLabelValues(Connection con, String codeType,String codeType1) throws SQLException 
	{
		ArrayList alLabelValues = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		LabelValueBean objLabelValue = null;
		
		try 
		{
			pstmt = con.prepareStatement(OISurveySqls.EXTRACT_LABEL_VALUES);
			pstmt.setString(1, codeType);
			pstmt.setString(2, codeType1);
			rs = pstmt.executeQuery();
			while(rs.next())	
			{
				alLabelValues.add(new LabelValueBean(rs.getString("DESCRIPTION"), rs.getString("VALUE")));
			}
		} 
		catch(SQLException se) 
		{
		    logger.error(" getLabelValues() "+se.getMessage());
			throw se;
		} 
		finally 
		{
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}
		return alLabelValues;
	}

	 public String copyFrom(Connection con, Integer strQuestionId, String userId) throws SQLException {
		CallableStatement cstmt = null;
		int destId=0;
		try{
			if(strQuestionId != null) {
				
				
					logger.error("copy query "+OISurveySqls.COPY_QUERY);
					cstmt= con.prepareCall(OISurveySqls.COPY_QUERY);
					cstmt.setInt(1,strQuestionId.intValue());
					cstmt.registerOutParameter(2,java.sql.Types.INTEGER);
					cstmt.setString(3, userId);
					
					cstmt.execute();
					destId = cstmt.getInt (2);

			}
		
		} catch(SQLException se) {
			logger.error(" copyFrom() "+se.getMessage());
			throw se;
		}
		finally {
			OISQLUtilities.closeStatement(cstmt);
		}
		return String.valueOf(destId);
	}
	 
	 public boolean doesSurveyExists(Connection con,Integer surveyId) throws SQLException
	{
		boolean doesSurveyExists = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try
		{
			pstmt = con.prepareStatement(OISurveySqls.DOES_SURVEY_EXISTS);
			pstmt.setInt(1, surveyId.intValue());
			
			rs = pstmt.executeQuery();
			
			while (rs.next())
			{		
				doesSurveyExists = true;
			}			
		}
		catch (SQLException se)
		{
			logger.error(" doesSurveyExists() " + se.getMessage());
			throw se;			
		}
		finally
		{
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}		
		return doesSurveyExists;
	}
	 
	 // added by K.K.Kumaresan on Jan 19,2008
	 /**
	  * delete External Mail Address
	  * @param con
	  * @param strSurveyId
	  * @return
	  * @throws SQLException
	  */
	 public boolean deleteExternalMailAddress(Connection con, String strSurveyId) throws SQLException 
	{
		boolean deleteFlag = false;
		ArrayList alSurveyValues = new ArrayList();
		try
		{
			if(strSurveyId != null) 
			{
				alSurveyValues.add(strSurveyId);
				deleteFlag = (OISQLUtilities.executeSingle(con, OISurveySqls.DELETE_EXTERNAL_MAIL, alSurveyValues, "deleteExternalMailAddress", "OIDAOSurveyAdmin") == 1);
				logger.info("deleteExternalMailAddress query is"+OISurveySqls.DELETE_EXTERNAL_MAIL);
			}
		}
		catch (SQLException se)
		{
			logger.error(" deleteExternalMailAddress() " + se.getMessage());
			throw se;			
		}
		return deleteFlag;
	}
	 
	 //	 added by K.K.Kumaresan on Jan 19,2008
	 /**
		 * insert External MailAddress
		 * @param con
		 * @param objSurvey
		 * @return
		 * @throws SQLException
		 */
		public boolean insertExternalMailAddress(Connection con, OIBASurvey objSurvey) throws SQLException 
		{
			boolean externalMailFlag =true;
			try
			{
				ArrayList alSurveyValues = new ArrayList();
				String externalMailAddress=objSurvey.getExternalMailAddress();
				String outsideMOEChecked=objSurvey.getOutsideMOEChecked();
				
				if(outsideMOEChecked!= null && outsideMOEChecked.equals("Y") )
				{
					if(externalMailAddress!=null && !externalMailAddress.equals(""))
					{
						alSurveyValues.add(objSurvey.getStrSurveyId());
						alSurveyValues.add(externalMailAddress);
						externalMailFlag = (OISQLUtilities.executeSingle(con, OISurveySqls.INSERT_EXTERNAL_MAIL, alSurveyValues, "createExternalAddress", "OIDAOSurveyAdmin") == 1);
					}		
				} // outsideMOEChecked 
			}
			catch(SQLException se)
			{
				logger.error(" insertExternalMailAddress "+se.getMessage());
				throw se;
			}
			return externalMailFlag;
		}
	 
	
} 
