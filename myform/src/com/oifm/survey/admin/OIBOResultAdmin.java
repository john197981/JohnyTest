package com.oifm.survey.admin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseBo;
import com.oifm.common.OIResponseObject;
import com.oifm.survey.OIBAResult;
import com.oifm.survey.OISurveySqls;

public class OIBOResultAdmin extends OIBaseBo {
	
	private static Logger logger = Logger.getLogger(OIBOResultAdmin.class);

	public OIBOResultAdmin()	
	{
		super();
	}
	
	public OIResponseObject getResultList(OIBAResult objResultVo)	
	{
		OIDAOQuestionAdmin objQuestionAdmin = new OIDAOQuestionAdmin(); 
		OIDAOSectionAdmin objSectionAdmin = new OIDAOSectionAdmin(); 
		ArrayList alSectionList = null;
		Hashtable htbQuestionList = null;
		boolean isSurveyDivision = true;
 
		try 
		{
			getConnection();
			isSurveyDivision = (new OIDAOSurveyAdmin()).isSurveyOwnerDivision(connection, objResultVo.getStrSurveyId(), objResultVo.getStrDivisionCode());
			alSectionList = objSectionAdmin.getSectionHierarchy(connection, objResultVo.getStrSurveyId());
			htbQuestionList = objQuestionAdmin.getSectionsQuestionsList(connection, objResultVo.getStrSurveyId());
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
	
	public OIResponseObject getSurveyRespondentsList(OIBAResult objResultVo){
		OIDAOResponseAdmin objSurveyAdmin = new OIDAOResponseAdmin();
		ArrayList alRespondentsList = null;
		String strSurveyId = objResultVo.getStrSectionId();
		//System.out.println("BO1"+strSurveyId);
		try 
		{
			getConnection();
			//System.out.println("BO2");
			alRespondentsList = objSurveyAdmin.getSurveyRespondentsList(connection, strSurveyId, OISurveySqls.SURVEY_RESPONDENTS);
		} 
		catch(SQLException se) 
		{
			error = ""+se.getErrorCode();
            logger.error(se);
		} 
		catch(Exception be)	
		{
			error = "OIDEFAULT";
			logger.error(" getSurveyRespondentsList => "+be);
		} 
		finally 
		{
			freeConnection();
			//System.out.println("BO4");
			addToResponseObject();
			responseObject.addResponseObject("SurveyRespondentsList", alRespondentsList);
			//System.out.println("BO3");
		}
		return responseObject;
	}
	
	public OIResponseObject getSurveyResultDetail(OIBAResult objResultVo){
		OIDAOResponseAdmin objSurveyResult = new OIDAOResponseAdmin();
		//ArrayList alRespondentsLevel = new ArrayList();
		//ArrayList alRespondentsDesignation = new ArrayList();
		ArrayList alReportData = new ArrayList();
		
		String strSurveyId = objResultVo.getStrSurveyId();
		String strAge = objResultVo.getStrAge();
		String strLevel = objResultVo.getStrLevel();
		String strDesignation = objResultVo.getStrDesignation();
		String strYear = objResultVo.getStrYear();
		String strQuery = OISurveySqls.DETAIL_FRONT;
		int iCount = 0;
		//System.out.println("OIBOResultAdmin: getSurveyResultDetail - var : "+strSurveyId);
		if (!strAge.equals("0")){
			
			if(strAge.equals("1"))
			strQuery += OISurveySqls.DETAIL_AGE_BELOW_30;
			
			if(strAge.equals("2"))
				strQuery += OISurveySqls.DETAIL_AGE_30_40;
			
			if(strAge.equals("3"))
				strQuery += OISurveySqls.DETAIL_AGE_ABOVE_40;			
		}
		
		if (!strYear.equals("0")){
			
			if(strYear.equals("1"))
				strQuery += OISurveySqls.DETAIL_YEAR_BELOW_3;
			
			if(strYear.equals("2"))
				strQuery += OISurveySqls.DETAIL_YEAR_3_TO_5;
			
			if(strYear.equals("3"))
				strQuery += OISurveySqls.DETAIL_YEAR_5_t0_10;
			
			if(strYear.equals("4"))
				strQuery += OISurveySqls.DETAIL_YEAR_ABOVE_10;
		}
		
		if (!strDesignation.equals("0")){
			strQuery += OISurveySqls.DETAIL_DESIGNATION;
			iCount = 1;
		}
		
		if (!strLevel.equals("0")){
			strQuery += OISurveySqls.DETAIL_LEVEL;
			
			if(iCount == 1)
				iCount = 3;
			else
				iCount = 2;
		}
		
		strQuery += OISurveySqls.DETAIL_END;
		
		try 
		{
			getConnection();
			alReportData.add(objSurveyResult.getSurveyDetailReport(connection, strSurveyId, strQuery, iCount, strDesignation, strLevel));
			//System.out.println("OIBOResultAdmin: getSurveyResultDetail - var : "+strDesignation);
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
			responseObject.addResponseObject("SurveyDemoDetail", alReportData);
		}
		return responseObject;
	}

	/*public OIResponseObject getSurveyReportDetail(OIBAResult objResultVo){
		OIDAOResponseAdmin objSurveyResult = new OIDAOResponseAdmin();
		ArrayList alCombo = new ArrayList();
		
		try
        {
            /**Create the Connection Object **/
    	/*    getConnection();
    	    
    	    if(objResultVo.getStrPopulate()!= null){
    	    	//alCombo = objSurveyResult.readList(connection,OIAdminSqls.QRY_DIVISION);
    	    	addList(alCombo ,OIAdminConstants.DIVISION);
    	    }
    	    else {
    	    	
    	    }
        }catch (Exception ex)
        {
            logger.error(OILabelConstants.EXCEPTION_IN_BO,ex);
    		responseObject.addResponseObject(OILabelConstants.ERROR,ex.getMessage());
    		throw ex;
    	}
        finally
        {
    		freeConnection();
    		alCombo = null;		
    	}
    	logger.debug("getSurveyReportDetail()");
		
		
		return responseObject;
	}*/
	
	/** This is the helper to add arraylist to responseobject
	  * @param alList
	  * @param strKey
	  * @return
	  */
  
	 private void addList(ArrayList alList ,String strKey )
	 {
  	  	responseObject.addResponseObject(strKey,alList);
	 }
}
