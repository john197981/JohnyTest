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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseDao;
import com.oifm.consultation.OIConsultConstant;
import com.oifm.consultation.OIConsultationSqls;
import com.oifm.utility.OISQLUtilities;

public class OIDAOConsultQuestion extends OIBaseDao 
{
    Logger logger = Logger.getLogger(OIDAOConsultQuestion.class.getName());
    /**
     * This method deletes only the Attachment of the respective paper
     * 
     * 1. gets connection from the base class
     * 2. create preparedStatement
     * 3. sets value of the primary keys to the prepare statement, which will delete that record from the database
     * 4. execute the query 
     */
    public boolean removeQuestion(int pQuestionId,Connection connection) throws Exception
    {
        boolean flag=false;
        //int i=0;
        PreparedStatement preparedStatement = null;
        try
        {
            preparedStatement=connection.prepareStatement(OIConsultationSqls.DELETE_QUESTION);
            preparedStatement.setInt(1,pQuestionId);
            flag = preparedStatement.execute();
            //preparedStatement.close();
        }catch(SQLException sqle)
        {
            //connection.rollback();
            logger.error(sqle.getMessage());
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
    
    /**
     * This method returns an arrayList of OIBAConsultQuestion
     * 
     * It gets paperId as input parameter. 
     */
    public ArrayList readArray(int pPaperId,Connection connection) throws Exception
    {
        ArrayList arOIBAConsultQuestion = new ArrayList();
        OIBAConsultQuestion aOIBAConsultQuestion = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs=null;
        try
        {
            preparedStatement = connection.prepareStatement(OIConsultationSqls.CONSULT_QUESTION_READALL);
            preparedStatement.setInt(1,pPaperId);
			 rs = preparedStatement.executeQuery(); 
			while (rs.next())
            {
                aOIBAConsultQuestion = new OIBAConsultQuestion();
                aOIBAConsultQuestion.setPaperId(rs.getInt(OIConsultConstant.Q_PAPERID));
                aOIBAConsultQuestion.setQuestionId(rs.getInt(OIConsultConstant.Q_QUESTIONID));
                aOIBAConsultQuestion.setQuestion(rs.getString(OIConsultConstant.Q_QUESTION));
                aOIBAConsultQuestion.setAnswerType(rs.getString(OIConsultConstant.Q_ANSWER_TYPE));            
                aOIBAConsultQuestion.setAnswer1(rs.getString(OIConsultConstant.Q_ANSWER1));            
                aOIBAConsultQuestion.setAnswer2(rs.getString(OIConsultConstant.Q_ANSWER2));            
                aOIBAConsultQuestion.setAnswer3(rs.getString(OIConsultConstant.Q_ANSWER3));            
                aOIBAConsultQuestion.setAnswer4(rs.getString(OIConsultConstant.Q_ANSWER4));            
                aOIBAConsultQuestion.setAnswer5(rs.getString(OIConsultConstant.Q_ANSWER5));
                aOIBAConsultQuestion.setNeedOtherRemark(rs.getString("NEEDOTHERREMARK"));
				aOIBAConsultQuestion.setLikertScale(rs.getString("LIKERT_SCALE"));
				aOIBAConsultQuestion.setUseSameLikert(rs.getString("USE_LIKERT"));
				aOIBAConsultQuestion.setCanMoveUp(rs.getString("CAN_MOVE_UP"));
				aOIBAConsultQuestion.setCanMoveDown(rs.getString("CAN_MOVE_DOWN"));
			    arOIBAConsultQuestion.add(aOIBAConsultQuestion);
            }
            //preparedStatement.close();
        }catch(Exception sqle)
        {
            logger.error(sqle.getMessage());
            //connection.rollback();
            throw sqle;
        }
        finally
        {
            //freeConnection();
        	OISQLUtilities.closeRsetPstatement(rs, preparedStatement);
//            if (preparedStatement!=null)
//                preparedStatement.close();
//            if (rs!=null)
//                rs.close();
        }
        
        if (arOIBAConsultQuestion.size()==0)
            arOIBAConsultQuestion=null;
        return arOIBAConsultQuestion;
    }
    
    /**
     * It gets the ArrayList of OIBAConsultQuestion as input parameter.
     * 
     * 1. gets connection from the base class
     * 2. create preparedStatement
     * 3. loop starts
     *       3.1. sets value to the   
     *         prepare statement
     *       3.2. save it to the database
     * 4. loop ends 
     */
    public void saveArray(java.util.ArrayList pArrayList, int pPaperId)
    {
    }
    
    /**
     * 1. gets connection from the base class
     * 2. create preparedStatement
     * 3. sets value to the prepare statement
     * 4. save it to the database 
     */
    public boolean save(OIBAConsultQuestion pOIBAConsultQuestion,Connection connection) throws Exception
    {
        boolean flag=false;
        int i=0;
        PreparedStatement preparedStatement = null;
        try
        {
            if (pOIBAConsultQuestion.getQuestionId()==0)
            {
	            preparedStatement = connection.prepareStatement(OIConsultationSqls.CONSULT_QUESTION_INSERT);
	            preparedStatement.setInt(1,pOIBAConsultQuestion.getPaperId());
	            preparedStatement.setString(2,pOIBAConsultQuestion.getQuestion());
	            preparedStatement.setString(3,pOIBAConsultQuestion.getAnswerType());
	            preparedStatement.setString(4,pOIBAConsultQuestion.getAnswer1());
	            preparedStatement.setString(5,pOIBAConsultQuestion.getAnswer2());
	            preparedStatement.setString(6,pOIBAConsultQuestion.getAnswer3());
	            preparedStatement.setString(7,pOIBAConsultQuestion.getAnswer4());
	            preparedStatement.setString(8,pOIBAConsultQuestion.getAnswer5());
	            preparedStatement.setString(9,pOIBAConsultQuestion.getNeedOtherRemark());
				preparedStatement.setString(10,pOIBAConsultQuestion.getLikertScale());
				preparedStatement.setString(11,pOIBAConsultQuestion.getUseSameLikert());
            }
            else
            {
	            preparedStatement = connection.prepareStatement(OIConsultationSqls.CONSULT_QUESTION_UPDATE);
	            preparedStatement.setString(1,pOIBAConsultQuestion.getQuestion());
	            preparedStatement.setString(2,pOIBAConsultQuestion.getAnswerType());
	            preparedStatement.setString(3,pOIBAConsultQuestion.getAnswer1());
	            preparedStatement.setString(4,pOIBAConsultQuestion.getAnswer2());
	            preparedStatement.setString(5,pOIBAConsultQuestion.getAnswer3());
	            preparedStatement.setString(6,pOIBAConsultQuestion.getAnswer4());
	            preparedStatement.setString(7,pOIBAConsultQuestion.getAnswer5());
	            preparedStatement.setString(8,pOIBAConsultQuestion.getNeedOtherRemark());
				preparedStatement.setString(9,pOIBAConsultQuestion.getLikertScale());
				preparedStatement.setString(10,pOIBAConsultQuestion.getUseSameLikert());
	            preparedStatement.setInt(11,pOIBAConsultQuestion.getQuestionId());
            }
            i = preparedStatement.executeUpdate();
			if(pOIBAConsultQuestion.getLikertScale()!=null && !"".equals(pOIBAConsultQuestion.getLikertScale()) && pOIBAConsultQuestion.getUseSameLikert()!=null  && !"".equals(pOIBAConsultQuestion.getLikertScale()) )
			{
				ArrayList alQuestionValues = new ArrayList();
				alQuestionValues.add("0");
				alQuestionValues.add(pOIBAConsultQuestion.getUseSameLikert());
				
				OISQLUtilities.executeSingle(connection, OIConsultationSqls.UPDATE_SRC_LIKERT_SCALE, alQuestionValues, "UPDATE_SRC_LIKERT_SCALE", "OIDAOConsultQuestion") ;
				
			}
            //preparedStatement.close();
        }catch(Exception sqle)
        {
            //connection.rollback();
            logger.error(sqle.getMessage());
            throw sqle;
        }
        finally
        {
            //freeConnection();
            if (preparedStatement!=null)
                preparedStatement.close();
        }
        
        if (i==0)
        {
            logger.error("Save Failed");
            throw new Exception("Save Failed");
        }
        else
        {
            flag = true;
        }
        return flag;
    }
    
    /**
     * This method reads the question & answer of the passes QuestionID
     * 
     * It returns OIBAConsultQuestion 
     */
    public OIBAConsultQuestion read(int pQuestionId,Connection connection) throws Exception
    {
        OIBAConsultQuestion aOIBAConsultQuestion = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs=null;
        try
        {
            preparedStatement = connection.prepareStatement(OIConsultationSqls.CONSULT_QUESTION_READ);
            preparedStatement.setInt(1,pQuestionId);
            rs = preparedStatement.executeQuery(); 

            if (rs.next())
            {
                aOIBAConsultQuestion = new OIBAConsultQuestion();
                aOIBAConsultQuestion.setPaperId(rs.getInt(OIConsultConstant.Q_PAPERID));
                aOIBAConsultQuestion.setQuestionId(rs.getInt(OIConsultConstant.Q_QUESTIONID));
                aOIBAConsultQuestion.setQuestion(rs.getString(OIConsultConstant.Q_QUESTION));
                aOIBAConsultQuestion.setAnswerType(rs.getString(OIConsultConstant.Q_ANSWER_TYPE));            
                aOIBAConsultQuestion.setAnswer1(rs.getString(OIConsultConstant.Q_ANSWER1));            
                aOIBAConsultQuestion.setAnswer2(rs.getString(OIConsultConstant.Q_ANSWER2));            
                aOIBAConsultQuestion.setAnswer3(rs.getString(OIConsultConstant.Q_ANSWER3));            
                aOIBAConsultQuestion.setAnswer4(rs.getString(OIConsultConstant.Q_ANSWER4));            
                aOIBAConsultQuestion.setAnswer5(rs.getString(OIConsultConstant.Q_ANSWER5));
                aOIBAConsultQuestion.setNeedOtherRemark(rs.getString("NEEDOTHERREMARK"));
				aOIBAConsultQuestion.setLikertScale(rs.getString("LIKERT_SCALE"));
				aOIBAConsultQuestion.setUseSameLikert(rs.getString("USE_LIKERT"));
            }
            //preparedStatement.close();
        }catch(Exception sqle)
        {
            //connection.rollback();
            logger.error(sqle.getMessage());
            throw sqle;
        }
        finally
        {
            //freeConnection();
        	OISQLUtilities.closeRsetPstatement(rs, preparedStatement);
//            if (preparedStatement!=null)
//                preparedStatement.close();
//            if (rs!=null)
//                rs.close();
        }
        
        return aOIBAConsultQuestion;
    }

    /**
     * It get the paperId as input parameter.
     * 
     * 1. gets connection from the base class
     * 2. create preparedStatement
     * 3. sets value of the primary keys to the prepare statement, which will delete all records of that paperID from the database
     * 4. execute the query 
     */
    public boolean deleteQuestions(int pPaperId,Connection connection) throws Exception
    {
        boolean flag=false;
        //int i=0;
        PreparedStatement preparedStatement = null;
        try
        {
            preparedStatement=connection.prepareStatement(OIConsultationSqls.CONSULT_PAPER_QUESTIONS_DELETE);
            preparedStatement.setInt(1,pPaperId);
            flag = preparedStatement.execute();
            //preparedStatement.close();
        }catch(SQLException sqle)
        {
            //connection.rollback();
            logger.error(sqle.getMessage());
            throw sqle;
        }
        finally
        {
            //freeConnection();
        	OISQLUtilities.closePstatement(preparedStatement);
//            if (preparedStatement!=null)
//                preparedStatement.close();
        }

        return flag;
    }

	public boolean reorderQuestion(int pQuestionId,int order, Connection connection) throws Exception
    {
      boolean reorderFlag = false;
		CallableStatement cstmt = null;
		try{
					//logger.error("reorder query "+OIConsultationSqls.REORDER_QUERY);
					cstmt= connection.prepareCall(OIConsultationSqls.REORDER_QUERY);
					cstmt.setInt(1,pQuestionId);
					cstmt.setInt(2,order);
					cstmt.execute();
				
				reorderFlag=true;
			
		
		} catch(SQLException se) {
			logger.error(" reorderQuestion() "+se.getMessage());
			throw se;
		}
		finally {
			OISQLUtilities.closeStatement(cstmt);
		}
		return reorderFlag;
	}
	public OIBAConsultQuestion getPreviousQuestion(String pQuestionId,String paperId, Connection connection) throws Exception
    {
        OIBAConsultQuestion aOIBAConsultQuestion = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs=null;
        try
        {
            preparedStatement = connection.prepareStatement(OIConsultationSqls.CONSULT_QUESTION_READ_PREV);
            preparedStatement.setString(1,pQuestionId);
			 preparedStatement.setString(2,paperId);
            rs = preparedStatement.executeQuery(); 

            if (rs.next())
            {
                aOIBAConsultQuestion = new OIBAConsultQuestion();
                aOIBAConsultQuestion.setPaperId(rs.getInt(OIConsultConstant.Q_PAPERID));
                aOIBAConsultQuestion.setQuestionId(rs.getInt(OIConsultConstant.Q_QUESTIONID));
                aOIBAConsultQuestion.setQuestion(rs.getString(OIConsultConstant.Q_QUESTION));
                aOIBAConsultQuestion.setAnswerType(rs.getString(OIConsultConstant.Q_ANSWER_TYPE));            
                aOIBAConsultQuestion.setAnswer1(rs.getString(OIConsultConstant.Q_ANSWER1));            
                aOIBAConsultQuestion.setAnswer2(rs.getString(OIConsultConstant.Q_ANSWER2));            
                aOIBAConsultQuestion.setAnswer3(rs.getString(OIConsultConstant.Q_ANSWER3));            
                aOIBAConsultQuestion.setAnswer4(rs.getString(OIConsultConstant.Q_ANSWER4));            
                aOIBAConsultQuestion.setAnswer5(rs.getString(OIConsultConstant.Q_ANSWER5));
                aOIBAConsultQuestion.setNeedOtherRemark(rs.getString("NEEDOTHERREMARK"));
				aOIBAConsultQuestion.setLikertScale(rs.getString("LIKERT_SCALE"));
				aOIBAConsultQuestion.setUseSameLikert(rs.getString("USE_LIKERT"));
            }
            //preparedStatement.close();
        }catch(Exception sqle)
        {
            //connection.rollback();
            logger.error(sqle.getMessage());
            throw sqle;
        }
        finally
        {
            //freeConnection();
        	OISQLUtilities.closeRsetPstatement(rs, preparedStatement);
//            if (preparedStatement!=null)
//                preparedStatement.close();
//            if (rs!=null)
//                rs.close();
        }
        
        return aOIBAConsultQuestion;
    }
	
	public void updateLikertBlockAnswers(Connection connection, String strQuestionId) throws SQLException
    {
    	CallableStatement cst = null;
    	try
    	{
    		cst = connection.prepareCall(OIConsultationSqls.UPDATE_LIKERT_BLOCK_ANSWERS);
    		cst.setInt(1, Integer.parseInt(strQuestionId));
    		cst.execute();
    	}
    	catch(SQLException se) {
			logger.error("updateLikertBlockAnswers() - "+se.getMessage());
			throw se;
		}
		finally {
			OISQLUtilities.closeStatement(cst);
		}
    }
	
	public void updateLikertBlockQuestions(Connection connection, String strQuestionId, boolean moveQuestion) throws SQLException
	{
		CallableStatement cst = null;
		try
		{
			cst = connection.prepareCall(OIConsultationSqls.UPDATE_LIKERT_BLOCK_QUESTIONS);
			cst.setInt(1, Integer.parseInt(strQuestionId));
			cst.setString(2, (moveQuestion) ? "TRUE" : "FALSE");
			cst.execute();
		}
		catch (SQLException se)
		{
			logger.error("updateLikertBlockQuestions() - " + se.getMessage());
			throw se;
		}
		finally
		{
			OISQLUtilities.closeStatement(cst);
		}
	}
}
