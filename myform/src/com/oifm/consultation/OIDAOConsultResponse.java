package com.oifm.consultation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseDao;
import com.oifm.consultation.admin.OIBAConsultQuestion;
import com.oifm.utility.OISQLUtilities;
import com.sun.org.apache.bcel.internal.generic.NEWARRAY;

public class OIDAOConsultResponse extends OIBaseDao 
{
	Logger logger = Logger.getLogger(OIDAOConsultResponse.class.getName());
	/**
	 * Query
	 * 
	 * View which combines OI_CP_RESPONSE & OI_CP_QUESTION
	 * 
	 * returns ArrayList of OIBAResponse 
	 */
	public ArrayList readResponse(int pPaperId, String pUserId,Connection connection)throws Exception
	{
		ArrayList arOIBAResponse = new ArrayList();
		OIBAResponse aOIBAResponse=null;
		PreparedStatement preparedStatement=null;
		ResultSet rs= null;
		try
		{
			preparedStatement=connection.prepareStatement(OIConsultationSqls.CONSULT_PAPER_RESPONSE_READ);
			preparedStatement.setInt(2,pPaperId);
			preparedStatement.setString(1,pUserId);
			rs = preparedStatement.executeQuery();

			while (rs.next())
			{
				logger.info("inside the while loop >>>>>> question ID is - " + rs.getInt(OIConsultConstant.Q_QUESTIONID));
				logger.info("inside the while loop >>>>>> user ID is - " + rs.getString(OIConsultConstant.Q_USERID));
				aOIBAResponse = new OIBAResponse();
				aOIBAResponse.setQuestionId(rs.getInt(OIConsultConstant.Q_QUESTIONID));
				aOIBAResponse.setResponseId(rs.getInt(OIConsultConstant.Q_RESPONSEID));
				aOIBAResponse.setRemarks(rs.getString(OIConsultConstant.Q_REMARKS));
				aOIBAResponse.setResponse1(rs.getString(OIConsultConstant.Q_RESPONSE1));
				aOIBAResponse.setResponse2(rs.getString(OIConsultConstant.Q_RESPONSE2));
				aOIBAResponse.setResponse3(rs.getString(OIConsultConstant.Q_RESPONSE3));
				aOIBAResponse.setResponse4(rs.getString(OIConsultConstant.Q_RESPONSE4));
				aOIBAResponse.setResponse5(rs.getString(OIConsultConstant.Q_RESPONSE5));
				aOIBAResponse.setUserId(rs.getString(OIConsultConstant.Q_USERID));
				aOIBAResponse.setResponseOn(rs.getDate(OIConsultConstant.Q_RESPONSE_ON));
				aOIBAResponse.setOtherRemarks(rs.getString("OTHERREMARKS"));
				arOIBAResponse.add(aOIBAResponse);
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
//			Start : Added by deva on Sep 26, 2007
			OISQLUtilities.closeRsetPstatement(rs, preparedStatement);
		}

		if (arOIBAResponse.size()==0)
			arOIBAResponse=null;

		return arOIBAResponse;
	}

	/**
	 * Query
	 * 
	 * View which combines OI_CP_RESPONSE & OI_CP_QUESTION
	 * 
	 * returns ArrayList of OIBAResponse 
	 */
	public ArrayList readResponse(int pPaperId, String pUserId,Connection connection,ArrayList arrQuestionList)throws Exception
	{
		ArrayList arOIBAResponse = new ArrayList();
		ArrayList newArray = new ArrayList();
		OIBAResponse aOIBAResponse=null;
		PreparedStatement preparedStatement=null;
		ResultSet rs= null;
		HashMap hashmap = new HashMap(); 
		try
		{
			preparedStatement=connection.prepareStatement(OIConsultationSqls.CONSULT_PAPER_RESPONSE_READ);
			preparedStatement.setInt(2,pPaperId);
			preparedStatement.setString(1,pUserId);
			rs = preparedStatement.executeQuery();
			if (arrQuestionList!=null)
		        {
				//logger.info("inside if :::::::" + arrQuestionList.size());
		            for (int i=0;i<arrQuestionList.size();i++)
			        {
		            	OIBAConsultQuestion aOIBAConsultQuestion = (OIBAConsultQuestion) arrQuestionList.get(i);
			            aOIBAConsultQuestion.getQuestion();	         
			          //  logger.info("the question id ///// " + aOIBAConsultQuestion.getQuestionId());
			            int k = i+1;
			            String qNO = Integer.toString(k);
			            String qID = Integer.toString(aOIBAConsultQuestion.getQuestionId());
			            //logger.info("the question NO for i: " + qNO);
			            /* StringBuilder result = new StringBuilder();

			            result.append(aOIBAConsultQuestion.getQuestionId());
			            result.append(",");
			            result.append(aOIBAConsultQuestion.getQuestionId());
			            result.append(",");
			            result.substring(0, result.length() - 1) ;
			           // aOIFormConsultPaperHelper.setQuestionNo(i+1 + "");
*/			
			            
			            
			            hashmap.put(qID, qNO); 
			            
			            //logger.info("hashmap value for 369 is ::::::  " + hashmap.get("369"));
			        
			        
			        }
		        }
			/*else{
				logger.info("inside else :::::::");
			}*/

			while (rs.next())
			{
					aOIBAResponse = new OIBAResponse();
					aOIBAResponse.setQuestionId(rs.getInt(OIConsultConstant.Q_QUESTIONID));
					aOIBAResponse.setResponseId(rs.getInt(OIConsultConstant.Q_RESPONSEID));
					aOIBAResponse.setRemarks(rs.getString(OIConsultConstant.Q_REMARKS));
					aOIBAResponse.setResponse1(rs.getString(OIConsultConstant.Q_RESPONSE1));
					aOIBAResponse.setResponse2(rs.getString(OIConsultConstant.Q_RESPONSE2));
					aOIBAResponse.setResponse3(rs.getString(OIConsultConstant.Q_RESPONSE3));
					aOIBAResponse.setResponse4(rs.getString(OIConsultConstant.Q_RESPONSE4));
					aOIBAResponse.setResponse5(rs.getString(OIConsultConstant.Q_RESPONSE5));
					aOIBAResponse.setUserId(rs.getString(OIConsultConstant.Q_USERID));
					aOIBAResponse.setResponseOn(rs.getDate(OIConsultConstant.Q_RESPONSE_ON));
					aOIBAResponse.setOtherRemarks(rs.getString("OTHERREMARKS"));
					//String questionNo = (String)hashmap.get( Integer.toString(rs.getInt(OIConsultConstant.Q_QUESTIONID)));
					/*logger.info("the value of user ID is  : " + rs.getString(OIConsultConstant.Q_USERID)); 
					logger.info("the value of question NO is  : " + questionNo + " question ID :::: " + rs.getInt(OIConsultConstant.Q_QUESTIONID));*/
					//aOIBAResponse.setQuestionNo(1);
					arOIBAResponse.add(aOIBAResponse);
				
			}
			
			//System.out.println("size of question list " + arrQuestionList.size());
			//System.out.println("size of response list " + arOIBAResponse.size());
			String setFlag = "N";
			int k = 0;
			for (int j=0;j<arrQuestionList.size();j++){
				k = j+1;
				OIBAConsultQuestion aOIBAConsultQuestion = (OIBAConsultQuestion) arrQuestionList.get(j);
				int qID1 = aOIBAConsultQuestion.getQuestionId();
				aOIBAResponse = new OIBAResponse();
				for (int i=0;i<arOIBAResponse.size();i++)
				{
					
					OIBAResponse objResponse = (OIBAResponse)arOIBAResponse.get(i);
					int qID2 = objResponse.getQuestionId();
					if(qID1 == qID2){

						aOIBAResponse.setQuestionId(objResponse.getQuestionId());
						aOIBAResponse.setResponseId(objResponse.getResponseId());
						aOIBAResponse.setRemarks(objResponse.getRemarks());
						aOIBAResponse.setResponse1(objResponse.getResponse1());
						aOIBAResponse.setResponse2(objResponse.getResponse2());
						aOIBAResponse.setResponse3(objResponse.getResponse3());
						aOIBAResponse.setResponse4(objResponse.getResponse4());
						aOIBAResponse.setResponse5(objResponse.getResponse5());
						aOIBAResponse.setUserId(objResponse.getUserId());
						aOIBAResponse.setResponseOn(objResponse.getResponseOn());
						aOIBAResponse.setOtherRemarks(objResponse.getOtherRemarks());	
						aOIBAResponse.setQuestionNo(1);
						newArray.add(aOIBAResponse);
						break;
					}else{
						try{
							//aOIBAResponse = new OIBAResponse();
							aOIBAResponse.setQuestionNo(0);
							
							/*if(k == arrQuestionList.size()) {
								newArray.add(aOIBAResponse);
								//System.out.println("just b4 else break :: K : " + k);
								//break;
							}*/
						}catch(Exception e){
							logger.info("error is ::: " + e.getMessage());
							e.printStackTrace();
						}
						//break;
					}

				


				}//END of for (int i=0;i<arOIBAResponse.size();i++)
				
				if(aOIBAResponse.getQuestionNo() == 0){
					newArray.add(aOIBAResponse);
				}
				
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
//			Start : Added by deva on Sep 26, 2007
			OISQLUtilities.closeRsetPstatement(rs, preparedStatement);
		}
		logger.info("Size of newArray >>> "+ newArray.size());
		//System.out.println("Size of newArray >>> "+ newArray.size());
		if (newArray.size()==0)
			newArray = null;

		return newArray;
	}

	/**
	 * check the QuestionId & userId exists
	 * 
	 * if exists update
	 * else
	 * insert. 
	 */
	public boolean saveResponse(OIBAResponse pOIBAResponse,boolean saveFlag,Connection connection) throws Exception
	{
		boolean flag=false;
		int i=0;
		PreparedStatement preparedStatement=null;
		try
		{
			if (saveFlag)
			{
				preparedStatement=connection.prepareStatement(OIConsultationSqls.CONSULT_PAPER_RESPONSE_INSERT);
				preparedStatement.setInt(1,pOIBAResponse.getQuestionId());
				preparedStatement.setString(2,pOIBAResponse.getUserId());
				preparedStatement.setString(3,pOIBAResponse.getResponse1());
				preparedStatement.setString(4,pOIBAResponse.getResponse2());
				preparedStatement.setString(5,pOIBAResponse.getResponse3());
				preparedStatement.setString(6,pOIBAResponse.getResponse4());
				preparedStatement.setString(7,pOIBAResponse.getResponse5());
				preparedStatement.setString(8,pOIBAResponse.getRemarks());
				preparedStatement.setString(9,pOIBAResponse.getOtherRemarks());
			}
			else
			{
				preparedStatement=connection.prepareStatement(OIConsultationSqls.CONSULT_PAPER_RESPONSE_UPDATE);
				preparedStatement.setString(1,pOIBAResponse.getResponse1());
				preparedStatement.setString(2,pOIBAResponse.getResponse2());
				preparedStatement.setString(3,pOIBAResponse.getResponse3());
				preparedStatement.setString(4,pOIBAResponse.getResponse4());
				preparedStatement.setString(5,pOIBAResponse.getResponse5());
				preparedStatement.setString(6,pOIBAResponse.getRemarks());
				preparedStatement.setString(7,pOIBAResponse.getOtherRemarks());
				preparedStatement.setInt(8,pOIBAResponse.getResponseId());
			}
			i = preparedStatement.executeUpdate();
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

	public boolean deleteResponses(int pPaperId,Connection connection)throws Exception
	{
		boolean flag=false;
		//int i=0;
		PreparedStatement preparedStatement=null;
		try
		{
			preparedStatement=connection.prepareStatement(OIConsultationSqls.CONSULT_PAPER_RESPONSE_DELETE);
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

	public OIBAResponse checkResponse(int pQuestionId, String pUserId,Connection connection)throws Exception
	{
		OIBAResponse aOIBAResponse=null;
		PreparedStatement preparedStatement=null;
		ResultSet rs=null;
		try
		{
			preparedStatement=connection.prepareStatement(OIConsultationSqls.CONSULT_PAPER_RESPONSE_READ_Q);
			preparedStatement.setInt(1,pQuestionId);
			preparedStatement.setString(2,pUserId);
			rs = preparedStatement.executeQuery();

			if (rs.next())
			{
				aOIBAResponse = new OIBAResponse();
				aOIBAResponse.setQuestionId(rs.getInt(OIConsultConstant.Q_QUESTIONID));
				aOIBAResponse.setResponseId(rs.getInt(OIConsultConstant.Q_RESPONSEID));
				aOIBAResponse.setRemarks(rs.getString(OIConsultConstant.Q_REMARKS));
				aOIBAResponse.setResponse1(rs.getString(OIConsultConstant.Q_RESPONSE1));
				aOIBAResponse.setResponse2(rs.getString(OIConsultConstant.Q_RESPONSE2));
				aOIBAResponse.setResponse3(rs.getString(OIConsultConstant.Q_RESPONSE3));
				aOIBAResponse.setResponse4(rs.getString(OIConsultConstant.Q_RESPONSE4));
				aOIBAResponse.setResponse5(rs.getString(OIConsultConstant.Q_RESPONSE5));
				aOIBAResponse.setUserId(rs.getString(OIConsultConstant.Q_USERID));
				aOIBAResponse.setResponseOn(rs.getDate(OIConsultConstant.Q_RESPONSE_ON));
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
//			Start : Added by deva on Sep 26, 2007
			OISQLUtilities.closeRsetPstatement(rs, preparedStatement);
		}

		return aOIBAResponse;
	}

	public OIBADraft readDraft(int paperId,String userId,Connection connection) throws Exception
	{
		OIBADraft aOIBADraft = null;
		PreparedStatement preparedStatement=null;
		ResultSet rs =null;
		try
		{
			preparedStatement=connection.prepareStatement(OIConsultationSqls.CONSULT_PAPER_DRAFT);
			preparedStatement.setInt(2,paperId);
			preparedStatement.setString(1,userId);
			rs = preparedStatement.executeQuery();

			if (rs.next())
			{
				aOIBADraft = new OIBADraft();
				aOIBADraft.setCpsuId(rs.getInt(OIConsultConstant.Q_CPSUID));
				aOIBADraft.setDraftId(rs.getInt(OIConsultConstant.Q_DRAFTID));
				aOIBADraft.setDraftedOn(rs.getDate(OIConsultConstant.Q_DRAFTED_ON));
				aOIBADraft.setDraftType(rs.getString(OIConsultConstant.Q_DRAFT_TYPE));
				aOIBADraft.setStatus(rs.getString(OIConsultConstant.Q_STATUS));
				aOIBADraft.setUserId(rs.getString(OIConsultConstant.Q_USERID));
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
//			Start : Added by deva on Sep 26, 2007
			OISQLUtilities.closeRsetPstatement(rs, preparedStatement);
		}
		return aOIBADraft;
	}


	public boolean saveDraft(OIBADraft aOIBADraft,Connection connection) throws Exception
	{
		boolean flag=false;
		//logger.info(aOIBADraft.getDraftId() + "");
		PreparedStatement preparedStatement=null;
		try
		{
			preparedStatement = null;
			if (aOIBADraft.getDraftId()==0)
			{
				preparedStatement=connection.prepareStatement(OIConsultationSqls.CONSULT_PAPER_DRAFT_INSERT);
				preparedStatement.setInt(1,aOIBADraft.getCpsuId());
				preparedStatement.setString(2,aOIBADraft.getUserId());
				preparedStatement.setString(3,aOIBADraft.getStatus());
			}
			else
			{
				preparedStatement=connection.prepareStatement(OIConsultationSqls.CONSULT_PAPER_DRAFT_UPDATE);
				preparedStatement.setInt(2,aOIBADraft.getDraftId());
				preparedStatement.setString(1,aOIBADraft.getStatus());
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

	public ArrayList readAllResponse(int pPaperId, Connection connection)throws Exception
	{
		ArrayList arOIBAResponse = new ArrayList();
		OIBAResponse aOIBAResponse=null;
		PreparedStatement preparedStatement=null;
		ResultSet rs=null;
		try
		{
			preparedStatement=connection.prepareStatement(OIConsultationSqls.CONSULT_PAPER_RESPONSE_READALL);
			preparedStatement.setInt(1,pPaperId);
			rs = preparedStatement.executeQuery();

			while (rs.next())
			{
				aOIBAResponse = new OIBAResponse();
				aOIBAResponse.setQuestionId(rs.getInt(OIConsultConstant.Q_QUESTIONID));
				aOIBAResponse.setResponseId(rs.getInt(OIConsultConstant.Q_RESPONSEID));
				aOIBAResponse.setRemarks(rs.getString(OIConsultConstant.Q_REMARKS));
				aOIBAResponse.setResponse1(rs.getString(OIConsultConstant.Q_RESPONSE1));
				aOIBAResponse.setResponse2(rs.getString(OIConsultConstant.Q_RESPONSE2));
				aOIBAResponse.setResponse3(rs.getString(OIConsultConstant.Q_RESPONSE3));
				aOIBAResponse.setResponse4(rs.getString(OIConsultConstant.Q_RESPONSE4));
				aOIBAResponse.setResponse5(rs.getString(OIConsultConstant.Q_RESPONSE5));
				aOIBAResponse.setUserId(rs.getString("NICKNAME"));
				aOIBAResponse.setResponseOn(rs.getDate(OIConsultConstant.Q_RESPONSE_ON));
				aOIBAResponse.setOtherRemarks(rs.getString("OTHERREMARKS"));
				arOIBAResponse.add(aOIBAResponse);
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
//			Start : Added by deva on Sep 26, 2007
			OISQLUtilities.closeRsetPstatement(rs, preparedStatement);
		}

		if (arOIBAResponse.size()==0)
			arOIBAResponse=null;

		return arOIBAResponse;
	}
}
