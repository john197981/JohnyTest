package com.oifm.consultation;
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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.ArrayList;
import org.apache.log4j.Logger;

import com.oifm.base.OIBaseDao;
import com.oifm.utility.OISQLUtilities;

public class OIDAOConsultFeedBack extends OIBaseDao 
{
    Logger logger = Logger.getLogger(OIDAOConsultFeedBack.class.getName());
    /**
     * getConnection
     * Set the Query
     * 
     * return the ArrayList of OIBVFeedBack 
     */
    public ArrayList readAllFeedBack(int pPaperId,Connection connection) throws Exception
    {
        ArrayList arOIBVFeedBack = new ArrayList();
        OIBVFeedBack aOIBVFeedBack = null;
        PreparedStatement preparedStatement=null;
        ResultSet rs=null;
        try
        {
            preparedStatement=connection.prepareStatement(OIConsultationSqls.CONSULT_READ_FEEDBACKALL);
            preparedStatement.setInt(1,pPaperId);
            preparedStatement.setInt(2,pPaperId);
            rs = preparedStatement.executeQuery();
            while(rs.next())
            {
                aOIBVFeedBack = new OIBVFeedBack();
                aOIBVFeedBack.setFeedBack(rs.getString("FEEDBACK1"));
                if (rs.getString(OIConsultConstant.Q_feedback)!=null)
                    aOIBVFeedBack.setFeedBack(aOIBVFeedBack.getFeedBack() + " " + rs.getString(OIConsultConstant.Q_feedback));
                aOIBVFeedBack.setFeedBackDate(rs.getDate(OIConsultConstant.Q_feedbackon));
                aOIBVFeedBack.setUserId(rs.getString(OIConsultConstant.Q_userid));
                aOIBVFeedBack.setUserName(rs.getString(OIConsultConstant.Q_name));
                aOIBVFeedBack.setSchool(rs.getString("SCHOOL"));
                aOIBVFeedBack.setDivision(rs.getString("DIVISION"));
                aOIBVFeedBack.setAge(rs.getString("age"));
                aOIBVFeedBack.setService(rs.getString("service"));
                aOIBVFeedBack.setDesignation(rs.getString("Designation"));
                aOIBVFeedBack.setEmail(rs.getString("emailid"));
                aOIBVFeedBack.setSchoolLevel(rs.getString("SCHOOL_TYPE"));
                arOIBVFeedBack.add(aOIBVFeedBack);
            }
            //preparedStatement.close();
        }catch(SQLException sqle)
        {
            logger.error("readAllFeedBack -" + sqle.getMessage());
            //connection.rollback();
            throw sqle;
        }
        finally
        {
            //freeConnection();
        	// Start : Added by deva on Sep 26, 2007     
            OISQLUtilities.closeRsetPstatement(rs, preparedStatement);
        }
        if (arOIBVFeedBack.size()==0)
            arOIBVFeedBack = null;

        return arOIBVFeedBack;
    }
    //added by edmund
    public ArrayList readNameEmail(int pPaperId,Connection connection) throws Exception
    {
    	ArrayList arOIBVFeedBack = new ArrayList();
        OIBVFeedBack aOIBVFeedBack = null;
        PreparedStatement preparedStatement=null;
        ResultSet rs=null;
        try
        {
            preparedStatement=connection.prepareStatement(OIConsultationSqls.CONSULT_READ_FEEDBACKALL);
            preparedStatement.setInt(1,pPaperId);
            preparedStatement.setInt(2,pPaperId);
            rs = preparedStatement.executeQuery();
            while(rs.next())
            {
                aOIBVFeedBack = new OIBVFeedBack();
                aOIBVFeedBack.setFeedBack(rs.getString("FEEDBACK1"));
                if (rs.getString(OIConsultConstant.Q_feedback)!=null)
                    aOIBVFeedBack.setFeedBack(aOIBVFeedBack.getFeedBack() + " " + rs.getString(OIConsultConstant.Q_feedback));
                aOIBVFeedBack.setFeedBackDate(rs.getDate(OIConsultConstant.Q_feedbackon));
                aOIBVFeedBack.setUserId(rs.getString(OIConsultConstant.Q_userid));
               //commented by edmund
               aOIBVFeedBack.setUserName(rs.getString(OIConsultConstant.Q_name));
                /*aOIBVFeedBack.setSchool(rs.getString("SCHOOL"));
                aOIBVFeedBack.setDivision(rs.getString("DIVISION"));
                aOIBVFeedBack.setAge(rs.getString("age"));
                aOIBVFeedBack.setService(rs.getString("service"));
                aOIBVFeedBack.setDesignation(rs.getString("Designation"));*/
                aOIBVFeedBack.setEmail(rs.getString("emailid"));
                //aOIBVFeedBack.setSchoolLevel(rs.getString("SCHOOL_TYPE"));
                //aOIBVFeedBack.setResponses( new OIDAOConsultResponse().readResponse(pPaperId,aOIBVFeedBack.getUserId(),connection));
               
                arOIBVFeedBack.add(aOIBVFeedBack);
            }
            //preparedStatement.close();
        }catch(SQLException sqle)
        {
            logger.error("readAllFeedBackAndResponses -" + sqle.getMessage());
            //connection.rollback();
            throw sqle;
        }
        finally
        {
            //freeConnection();
//       	 Start : Added by deva on Sep 26, 2007
        	OISQLUtilities.closeRsetPstatement(rs, preparedStatement);
            
        }
        if (arOIBVFeedBack.size()==0)
            arOIBVFeedBack = null;

        return arOIBVFeedBack;
    }
    
/*/  added by edmund
    public ArrayList readSummaryData(Connection connection, int pPaperId, String strQuery) throws Exception
    {
    	ArrayList arOIBVFeedBack = new ArrayList();
        OIBVFeedBack aOIBVFeedBack = null;
        PreparedStatement preparedStatement=null;
        ResultSet rs=null;
        try
        {
            preparedStatement=connection.prepareStatement(OIConsultationSqls.CONSULT_READ_FEEDBACKALL);
            preparedStatement.setInt(1,pPaperId);
            preparedStatement.setInt(2,pPaperId);
            rs = preparedStatement.executeQuery();
            while(rs.next())
            {
                aOIBVFeedBack = new OIBVFeedBack();
                aOIBVFeedBack.setFeedBack(rs.getString("FEEDBACK1"));
                if (rs.getString(OIConsultConstant.Q_feedback)!=null)
                    aOIBVFeedBack.setFeedBack(aOIBVFeedBack.getFeedBack() + " " + rs.getString(OIConsultConstant.Q_feedback));
                aOIBVFeedBack.setFeedBackDate(rs.getDate(OIConsultConstant.Q_feedbackon));
                aOIBVFeedBack.setUserId(rs.getString(OIConsultConstant.Q_userid));
               //commented by edmund
               aOIBVFeedBack.setUserName(rs.getString(OIConsultConstant.Q_name));
                //aOIBVFeedBack.setSchool(rs.getString("SCHOOL"));
                //aOIBVFeedBack.setDivision(rs.getString("DIVISION"));
                //aOIBVFeedBack.setAge(rs.getString("age"));
                //aOIBVFeedBack.setService(rs.getString("service"));
                //aOIBVFeedBack.setDesignation(rs.getString("Designation"));
                aOIBVFeedBack.setEmail(rs.getString("emailid"));
                //aOIBVFeedBack.setSchoolLevel(rs.getString("SCHOOL_TYPE"));
                //aOIBVFeedBack.setResponses( new OIDAOConsultResponse().readResponse(pPaperId,aOIBVFeedBack.getUserId(),connection));
               
                arOIBVFeedBack.add(aOIBVFeedBack);
            }
            //preparedStatement.close();
        }catch(SQLException sqle)
        {
            logger.error("readAllFeedBackAndResponses -" + sqle.getMessage());
            //connection.rollback();
            throw sqle;
        }
        finally
        {
            //freeConnection();
            if (preparedStatement!=null)
                preparedStatement.close();
            if (rs!=null)
                rs.close();
        }
        if (arOIBVFeedBack.size()==0)
            arOIBVFeedBack = null;

        return arOIBVFeedBack;
    }
    */
    //modify by edmund
	  public ArrayList readAllFeedBackAndResponses(int pPaperId,Connection connection,ArrayList arrQuestionList) throws Exception
    {
        ArrayList arOIBVFeedBack = new ArrayList();
        OIBVFeedBack aOIBVFeedBack = null;
        PreparedStatement preparedStatement=null;
        ResultSet rs=null;
        try
        {
            preparedStatement=connection.prepareStatement(OIConsultationSqls.CONSULT_READ_FEEDBACKALL);
            preparedStatement.setInt(1,pPaperId);
            preparedStatement.setInt(2,pPaperId);
            rs = preparedStatement.executeQuery();
            while(rs.next())
            {
                aOIBVFeedBack = new OIBVFeedBack();
                aOIBVFeedBack.setFeedBack(rs.getString("FEEDBACK1"));
                if (rs.getString(OIConsultConstant.Q_feedback)!=null)
                aOIBVFeedBack.setFeedBack(aOIBVFeedBack.getFeedBack() + " " + rs.getString(OIConsultConstant.Q_feedback));
                aOIBVFeedBack.setFeedBackDate(rs.getDate(OIConsultConstant.Q_feedbackon));
                aOIBVFeedBack.setUserId(rs.getString(OIConsultConstant.Q_userid));
               //commented by edmund
               //aOIBVFeedBack.setUserName(rs.getString(OIConsultConstant.Q_name));
                aOIBVFeedBack.setSchool(rs.getString("SCHOOL"));
                aOIBVFeedBack.setDivision(rs.getString("DIVISION"));
                aOIBVFeedBack.setAge(rs.getString("age"));
                aOIBVFeedBack.setService(rs.getString("service"));
                aOIBVFeedBack.setDesignation(rs.getString("Designation"));
//              commented by edmund
                //aOIBVFeedBack.setEmail(rs.getString("emailid"));
                aOIBVFeedBack.setSchoolLevel(rs.getString("SCHOOL_TYPE"));
				aOIBVFeedBack.setResponses( new OIDAOConsultResponse().readResponse(pPaperId,aOIBVFeedBack.getUserId(),connection,arrQuestionList));
				
                arOIBVFeedBack.add(aOIBVFeedBack);
            }
            //preparedStatement.close();
        }catch(SQLException sqle)
        {
            logger.error("readAllFeedBackAndResponses -" + sqle.getMessage());
            //connection.rollback();
            throw sqle;
        }
        finally
        {
            //freeConnection();
//        	 Start : Added by deva on Sep 26, 2007
        	OISQLUtilities.closeRsetPstatement(rs, preparedStatement);
        }
        if (arOIBVFeedBack.size()==0)
            arOIBVFeedBack = null;

        return arOIBVFeedBack;
    }
    
    public ArrayList readFeedBackWithinLimit(int pPaperId,int startRecord,int endRecord, Connection connection) throws Exception
    {
        ArrayList arOIBVFeedBack = new ArrayList();
        OIBVFeedBack aOIBVFeedBack = null;
        PreparedStatement preparedStatement=null;
        ResultSet rs = null;
        try
        {
            preparedStatement=connection.prepareStatement(OIConsultationSqls.CONSULT_READ_FEEDBACKWITHINLIMIT);
            preparedStatement.setInt(1,pPaperId);
            preparedStatement.setInt(2,pPaperId);
            preparedStatement.setInt(3,startRecord);
            preparedStatement.setInt(4,endRecord);
            rs = preparedStatement.executeQuery();
            while(rs.next())
            {
                aOIBVFeedBack = new OIBVFeedBack();
                aOIBVFeedBack.setFeedBack(rs.getString("FEEDBACK1"));
                if (rs.getString(OIConsultConstant.Q_feedback)!=null)
                aOIBVFeedBack.setFeedBack(aOIBVFeedBack.getFeedBack() + " " + rs.getString(OIConsultConstant.Q_feedback));
                aOIBVFeedBack.setFeedBackDate(rs.getDate(OIConsultConstant.Q_feedbackon));
                aOIBVFeedBack.setUserId(rs.getString(OIConsultConstant.Q_userid));
                aOIBVFeedBack.setUserName(rs.getString(OIConsultConstant.Q_name));
                aOIBVFeedBack.setSchool(rs.getString("SCHOOL"));
                aOIBVFeedBack.setDivision(rs.getString("DIVISION"));
                arOIBVFeedBack.add(aOIBVFeedBack);
            }
            //preparedStatement.close();
        }catch(SQLException sqle)
        {
            logger.error("readFeedBackWithinLimit -" + sqle.getMessage());
            //connection.rollback();
            throw sqle;
        }
        finally
        {
            //freeConnection();
//        	 Start : Added by deva on Sep 26, 2007
        	OISQLUtilities.closeRsetPstatement(rs, preparedStatement);

        }
        if (arOIBVFeedBack.size()==0)
            arOIBVFeedBack = null;

        return arOIBVFeedBack;
    }

    public int readTotalFeedBack(int pPaperId,Connection connection) throws Exception
    {
        int count=0;
        PreparedStatement preparedStatement=null;
        ResultSet rs=null;
        try
        {
            preparedStatement=connection.prepareStatement(OIConsultationSqls.CONSULT_READ_TOTAL_FEEDBACK);
            preparedStatement.setInt(1,pPaperId);
            preparedStatement.setInt(2,pPaperId);
            rs = preparedStatement.executeQuery();
            if(rs.next())
            {
                count = rs.getInt(OIConsultConstant.Q_totalCount);
            }
            //preparedStatement.close();
        }catch(SQLException sqle)
        {
            logger.error("readTotalFeedBack -" + sqle.getMessage());
            //connection.rollback();
            throw sqle;
        }
        finally
        {
            //freeConnection();
//        	 Start : Added by deva on Sep 26, 2007
        	OISQLUtilities.closeRsetPstatement(rs, preparedStatement);

        }

        return count;
    }
    
    //Added by Oscar @ 26 June 2007
	public Hashtable getNumberOfQuestionRespondent(Connection conn, String strPaperId) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Hashtable table = null;
		try 
		{
			table = new Hashtable();
			
			pstmt = conn.prepareStatement(OIConsultationSqls.NUMBER_OF_QUESTION_RESPONDENTS);
			pstmt.setString(1, strPaperId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				table.put(rs.getString("QUESTIONID"), rs.getString("COUNT"));
			}
		} catch (SQLException se) {
		
		} finally {
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}
		return table;
	}
    
    /**
     * getConnection
     * 
     * Query
     * 
     * "Select feedBack1 afeedBack1, feedBack2 afeedBack2 from OI_CP_FEEDBACK feedback where feedback.paperID=? and feedback.userId=?"
     * 
     * return OIBAFeedBack 
     */
    public OIBAFeedBack readFeedBack(int pPaperId, String pUserId,Connection connection) throws Exception
    {
        OIBAFeedBack aOIBAFeedBack = null;
        PreparedStatement preparedStatement=null;
        ResultSet rs=null;
        try
        {
            preparedStatement=connection.prepareStatement(OIConsultationSqls.CONSULT_PAPER_FEEDBACK_READ);
            preparedStatement.setInt(1,pPaperId);
            preparedStatement.setString(2,pUserId);
            rs = preparedStatement.executeQuery();
            if(rs.next())
            {
                aOIBAFeedBack = new OIBAFeedBack();
                aOIBAFeedBack.setFeedBack1(rs.getString(OIConsultConstant.Q_FEEDBACK1));
                aOIBAFeedBack.setFeedBack2(rs.getString(OIConsultConstant.Q_FEEDBACK2));
                aOIBAFeedBack.setFeedBackOn(rs.getDate(OIConsultConstant.Q_FEEDBACK_ON));
                aOIBAFeedBack.setPaperId(rs.getInt(OIConsultConstant.Q_PAPERID));
                aOIBAFeedBack.setFeedBackId(rs.getInt(OIConsultConstant.Q_FEEDBACKID));
                aOIBAFeedBack.setUserId(rs.getString(OIConsultConstant.Q_USERID));
            }
            //preparedStatement.close();
        }catch(SQLException sqle)
        {
            logger.error("readFeedBack() - " + sqle.getMessage());
            //connection.rollback();
            throw sqle;
        }
        finally
        {
            //freeConnection();
//        	 Start : Added by deva on Sep 26, 2007
        	OISQLUtilities.closeRsetPstatement(rs, preparedStatement);

        }

        return aOIBAFeedBack;
    }
    
    /**
     * saves the feedback 
     */
    public boolean saveFeedBack(OIBAFeedBack pOIBAFeedBack,boolean saveFlag,Connection connection) throws Exception
    {
        boolean flag=false;
        PreparedStatement preparedStatement=null;
        try
        {
            if (saveFlag)
            {
                preparedStatement=connection.prepareStatement(OIConsultationSqls.CONSULT_PAPER_FEEDBACK_INSERT);
                preparedStatement.setInt(1,pOIBAFeedBack.getPaperId());
                preparedStatement.setString(2,pOIBAFeedBack.getUserId());
                preparedStatement.setString(3,pOIBAFeedBack.getFeedBack1());
                preparedStatement.setString(4,pOIBAFeedBack.getFeedBack2());
            }
            else
            {
                preparedStatement=connection.prepareStatement(OIConsultationSqls.CONSULT_PAPER_FEEDBACK_UPDATE);
                preparedStatement.setString(1,pOIBAFeedBack.getFeedBack1());
                preparedStatement.setString(2,pOIBAFeedBack.getFeedBack2());
                preparedStatement.setInt(3,pOIBAFeedBack.getFeedBackId());
            }
            
            int i = preparedStatement.executeUpdate();
            if (i==0)
            {
                logger.error("Save Failed");
                throw new Exception("Save Failed");
            }
            else
            {
                flag = true;
            }
            //preparedStatement.close();
        }catch(SQLException sqle)
        {
            logger.error(sqle.getMessage());
            //connection.rollback();
            throw sqle;
        }
        finally
        {
            //freeConnection();
            if (preparedStatement!=null)
                preparedStatement.close();
        }
        return flag;
    }
    
    public boolean deleteFeedBacks(int pPaperId,Connection connection) throws Exception
    {
        boolean flag=false;
        //int i=0;
        PreparedStatement preparedStatement=null;
        try
        {
            preparedStatement=connection.prepareStatement(OIConsultationSqls.CONSULT_PAPER_FEEDBACK_DELETE);
            preparedStatement.setInt(1,pPaperId);
            flag = preparedStatement.execute();
            //preparedStatement.close();
        }catch(SQLException sqle)
        {
            logger.error(sqle.getMessage());
            //connection.rollback();
            throw sqle;
        }
        finally
        {
            //freeConnection();
            if (preparedStatement!=null)
                preparedStatement.close();
        }

        return flag;
    }

//	added by edmund to get data for survey demographic detail report
	public ArrayList getConsultDetailReport(Connection con, String strPaperId, String strQuery, int iCount, String strDesignation, String strLevel) throws SQLException 
	{
		ArrayList alReportData = new ArrayList();
		ArrayList alTempData = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//System.out.println("OIDAOConsultFeedBack: getConsultDetailReport - var : "+strQuery);
		try 
		{
			logger.info("*********getConsultDetailReport query is"+strQuery);
			pstmt = con.prepareStatement(strQuery);
			pstmt.setString(1, strPaperId);
			
				if(iCount == 1){
					pstmt.setString(2, strDesignation);
				}else if(iCount == 2){
					pstmt.setString(2, strLevel);
				}else if(iCount == 3){
					pstmt.setString(2, strDesignation);
					pstmt.setString(3, strLevel);
				}
				
			rs = pstmt.executeQuery();
			logger.debug("strQuery = "+strQuery);
			while(rs.next())	
			{
				alTempData = new ArrayList();
				alTempData.add(rs.getString("questionid"));
				alTempData.add(rs.getString("question"));
				alTempData.add(rs.getString("answer1"));
				alTempData.add(rs.getString("answer2"));
				alTempData.add(rs.getString("answer3"));
				alTempData.add(rs.getString("answer4"));
				alTempData.add(rs.getString("answer5"));
				alTempData.add(rs.getString("NEEDOTHERREMARK"));
				alTempData.add(rs.getString("response1Count"));
				alTempData.add(rs.getString("response2Count"));
				alTempData.add(rs.getString("response3Count"));
				alTempData.add(rs.getString("response4Count"));
				alTempData.add(rs.getString("response5Count"));
				alTempData.add(rs.getString("oRemarksCount"));
				alReportData.add(alTempData);
			}
			
		} catch(SQLException se) 
		{
		    logger.error(" getConsultRespondentsData() "+se.getMessage());
			throw se;
		} finally 
		{
//			 Start : Added by deva on Sep 26, 2007
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}
		return alReportData;
	}
	

	public String getPaperTitle(Connection con, String strId, String strQuery) throws SQLException 
	{
		String strTitle = "";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try 
		{
			pstmt = con.prepareStatement(strQuery);
			pstmt.setInt(1, Integer.parseInt(strId));
			rs = pstmt.executeQuery();
			
			logger.debug("strQuery = "+strQuery);
			while(rs.next())	
			{
				strTitle = rs.getString("title");
			}
			
		} catch(SQLException se) 
		{
		    logger.error(" getPaperTitle() "+se.getMessage());
			throw se;
		} finally 
		{
//			 Start : Added by deva on Sep 26, 2007
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}
		return strTitle;
	}
}
