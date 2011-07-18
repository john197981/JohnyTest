
package com.oifm.survey;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseBo;
import com.oifm.common.OIResponseObject;

public class OIBOSurveyUser extends OIBaseBo 
{
	private static Logger logger = Logger.getLogger(OIBOSurveyUser.class);
	private boolean isGDSUser;
	
	public OIBOSurveyUser(boolean isGDSUser)	
	{
		super();
		this.isGDSUser = isGDSUser;
	}

	public OIResponseObject getSurveyList(OIBASurveySection objSurveySectionVo)	
	{
		OIDAOSurveyUser objSurveyUser = new OIDAOSurveyUser(); 
		ArrayList alCurrSurveyList = null;
		ArrayList alPastSurveyList = null;
		try 
		{
			getConnection();
			alCurrSurveyList = objSurveyUser.getCurrentSurveysList(connection, objSurveySectionVo.getStrUserId());
			alPastSurveyList = objSurveyUser.getPastSurveysList(connection, objSurveySectionVo.getStrUserId());
		} 
		catch(SQLException se) 
		{
			error = ""+se.getErrorCode();
            logger.error(se);
		} 
		catch(Exception be)	
		{
			error = "OIDEFAULT";
			logger.error(" getSurveyList => "+be);
		} 
		finally 
		{
			freeConnection();
			addToResponseObject();
			responseObject.addResponseObject("CurrSurveyList", alCurrSurveyList);
			responseObject.addResponseObject("PastSurveyList", alPastSurveyList);
		}
		return responseObject;
	}
	
	public OIResponseObject getSurveyPreview(OIBASurveySection objSurveySectionVo)	
	{
		OIDAOSurveyUser objSurveyUser = new OIDAOSurveyUser(); 
		OIDAOSectionUser objSectionUser = new OIDAOSectionUser(); 
		ArrayList alSectionList = null;
		OIBASurvey objSurveyVo = null;
		Integer compPercent = null;
		try 
		{
			if(objSurveySectionVo.getStrSurveyId() != null && !objSurveySectionVo.getStrSurveyId().equals("")) 
			{
				getConnection();
				objSurveyVo = objSurveyUser.fetchSurveyInfo(connection, objSurveySectionVo);
				objSurveyVo.setUserSubmitted(true);
				alSectionList = objSectionUser.getSectionHierarchy(connection, objSurveySectionVo.getStrSurveyId());
				objSectionUser.setLevelsHirarchy(alSectionList);
				objSectionUser.setSurveySectionStatistics(connection, objSurveySectionVo.getStrSurveyId(), "#", alSectionList, OISurveySqls.PUBLISH_SECTION_STATISTICS);
				logger.debug(" StrSurveyId : "+ objSurveySectionVo.getStrSurveyId() +" StrUserId : "+ objSurveySectionVo.getStrUserId() + " UserSubmitted => "+objSurveyVo.isUserSubmitted() );
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
			logger.error(" getSurveyPreview => "+be);
		} 
		finally 
		{
			freeConnection();
			addToResponseObject();
			responseObject.addResponseObject("objSurveyVo", objSurveyVo);				
			responseObject.addResponseObject("SectionList", alSectionList);				
		}
		return responseObject;
	}
	
	public OIResponseObject getSurveyDetailPreview(String strSurveyId)	
	{
		OIDAOSurveyUser objSurveyUser = new OIDAOSurveyUser(); 
		OIBASurvey objSurveyVo = null;
		OIBASurveySection objSurveySectionVo = null;
		try 
		{
			objSurveySectionVo = new OIBASurveySection();
			objSurveySectionVo.setStrSurveyId(strSurveyId);
			if(strSurveyId != null && !strSurveyId.equals("")) 
			{
				getConnection();
				objSurveyVo = objSurveyUser.fetchSurveyInfo(connection, objSurveySectionVo);
				objSurveyVo.setUserSubmitted(true);
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
			logger.error(" getSurveyPreview => "+be);
		} 
		finally 
		{
			freeConnection();
			addToResponseObject();
			responseObject.addResponseObject("objSurveyVo", objSurveyVo);							
		}
		return responseObject;
	}

	public OIResponseObject getSurveyDetails(OIBASurveySection objSurveySectionVo)	
	{
		OIDAOSurveyUser objSurveyUser = new OIDAOSurveyUser(); 
		OIDAOSectionUser objSectionUser = new OIDAOSectionUser(); 
		ArrayList alSectionList = null;
		OIBASurvey objSurveyVo = null;
		Integer compPercent = null;
		boolean isAccess = false;
		try 
		{
			if(objSurveySectionVo.getStrSurveyId() != null && !objSurveySectionVo.getStrSurveyId().equals("")) {
				getConnection();
				isAccess = objSurveyUser.checkUserCanAccessSurvey(connection, objSurveySectionVo.getStrSurveyId(), objSurveySectionVo.getStrUserId());
				// Added by K.K.Kumaresan on Mar 25,2008 
				String userId=objSurveySectionVo.getStrUserId();
				if(userId!=null && !userId.equals(""))
				{
					if(userId.indexOf("@")>=0)	// If the user logged in thru mail id,access is granted for that survey.
					{
						isAccess=true;
					}
				}
				// ends
				if(isAccess) 
				{
					objSurveyVo = objSurveyUser.fetchSurveyInfo(connection, objSurveySectionVo);
					logger.error("Inside getSurveyDetails");
					if(objSurveyVo.getStrPublishedStatus() != null && objSurveyVo.getStrPublishedStatus().equals("N")) 
					{
						objSurveyVo.setUserCanSubmit(!objSurveyUser.checkUserResponsesForMandatoryQsts(connection, objSurveySectionVo.getStrSurveyId(), objSurveySectionVo.getStrUserId(), ((isGDSUser)?OISurveySqls.CHECK_SURVEY_MANDATORY_QSTS:OISurveySqls.CHECK_SURVEY_MANDATORY_QSTS_TUSER)));
						objSurveyVo.setUserSubmitted(objSurveyUser.checkUserSubmittedSurvey(connection, objSurveySectionVo.getStrSurveyId(), objSurveySectionVo.getStrUserId()));
						alSectionList = objSectionUser.getSectionHierarchy(connection, objSurveySectionVo.getStrSurveyId());
						objSectionUser.setLevelsHirarchy(alSectionList);
						objSectionUser.setSurveySectionStatistics(connection, objSurveySectionVo.getStrSurveyId(), objSurveySectionVo.getStrUserId(), alSectionList, ((isGDSUser)?OISurveySqls.PUBLISH_SECTION_STATISTICS:OISurveySqls.PUBLISH_SECTION_STATISTICS_TUSER));
						compPercent = new Integer(objSurveyUser.getUserSurveyPercentageCompletion(connection, objSurveySectionVo.getStrSurveyId(), objSurveySectionVo.getStrUserId(), ((isGDSUser)?OISurveySqls.SURVEY_QUEST_RESP_RATIO:OISurveySqls.SURVEY_QUEST_RESP_RATIO_TUSER)));
						logger.debug(" StrSurveyId : "+ objSurveySectionVo.getStrSurveyId() +" StrUserId : "+ objSurveySectionVo.getStrUserId() + " UserCanSubmit => "+objSurveyVo.isUserCanSubmit() +" UserSubmitted => "+objSurveyVo.isUserSubmitted() );
					}
				} 
				else
				{
				    error = "NoAccess";
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
			logger.error(" getSurveyDetails => "+be);
		} 
		finally 
		{
			freeConnection();
			addToResponseObject();
			responseObject.addResponseObject("objSurveyVo", objSurveyVo);				
			responseObject.addResponseObject("SectionList", alSectionList);				
			responseObject.addResponseObject("compPercent", compPercent);				
		}
		return responseObject;
	}
	
	public OIResponseObject getDownloadFileInfo(OIBASurveySection objSurveySectionVo) 
	{
		OIDAOSurveyUser objSurveyUser = new OIDAOSurveyUser();
		String strFileName= "";
		boolean isAccess = false;
		try 
		{
			String strSurveyId = objSurveySectionVo.getStrSurveyId();
			if(strSurveyId != null && !strSurveyId.equals("")) 
			{
				getConnection();
				isAccess = objSurveyUser.checkUserCanAccessSurvey(connection, objSurveySectionVo.getStrSurveyId(), objSurveySectionVo.getStrUserId());
//				Added by K.K.Kumaresan on Apr 03,2009 
				String userId=objSurveySectionVo.getStrUserId();
				if(userId!=null && !userId.equals(""))
				{
					if(userId.indexOf("@")>=0)	// If the user logged in thru mail id,access is granted for that survey.
					{
						isAccess=true;
					}
				}
				// ends
				if(isAccess) 
				{
					strFileName = objSurveyUser.getSurveyAttachedFile(connection, strSurveyId);
				} 
				else 
				{
				    error = "NoAccess";
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
			logger.error(" getDownloadSurveyInfo => "+be);
		} 
		finally 
		{
			freeConnection();
			addToResponseObject();
			responseObject.addResponseObject("strFileName", strFileName);
		}
		return responseObject;
	}

	public OIResponseObject updateSurveyUserSubmit(OIBASurveySection objSurveySectionVo,String emailUser) 
	{
		OIDAOResponseUser objResponseUser = new OIDAOResponseUser();
		boolean saveFlag = false;
		try 
		{
			if(objSurveySectionVo.getStrSurveyId() != null && !objSurveySectionVo.getStrSurveyId().equals("")) 
			{
				getConnection();
				saveFlag = objResponseUser.updateSubmitionInfo(connection, objSurveySectionVo.getStrSurveyId(), objSurveySectionVo.getStrUserId());
				OIDAOSurveyUser objSurveyUser = new OIDAOSurveyUser(); 
				OIBASurvey objSurveyVo = null;
				objSurveyVo = objSurveyUser.fetchSurveyInfo(connection, objSurveySectionVo);
				if (objSurveyVo!=null && objSurveyVo.getStrSurveyType().equals("Y"))
				{
					if(saveFlag)
					{
						
					    saveFlag = objResponseUser.updateSurveyMembers(connection, objSurveySectionVo.getStrSurveyId(), objSurveySectionVo.getStrUserId());
					}
				}
				if(saveFlag)
				{
					//System.out.println("objSurveySectionVo.getStrUserId() is"+objSurveySectionVo.getStrUserId());
					if(emailUser!=null && emailUser.equals("true"))
					{
						// if it is a emailUser, skip it.
					}
					else
					{
						
						saveFlag = objResponseUser.updateUserSurveyCount(connection, objSurveySectionVo.getStrUserId());
					}	
				}
				
				if(!saveFlag)
				{
				    error = "OIDEFAULT";
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
			logger.error(" updateSurveyUserSubmit => "+be);
		} 
		finally 
		{
			freeConnection();
			addToResponseObject();
		}
		return responseObject;
	}
	
	public OIResponseObject getSectionQstRespDetails(OIBAQuestionResponse objQuestionResponseVo)	
	{
		OIDAOSurveyUser objSurveyUser = new OIDAOSurveyUser();
		OIDAOSectionUser objSectionUser = new OIDAOSectionUser();
		OIDAOQuestionUser objQuestionUser = new OIDAOQuestionUser();
		OIDAOResponseUser objResponseUser = new OIDAOResponseUser();
		ArrayList alQuestion = null;
		ArrayList aryResponse = null;
		OIBASection objSection = null;
		boolean isUserSubmitted = true;
		boolean isAccess = false;

		try 
		{
		    logger.debug(" getSectionQstRespDetails() => strSurveyId : "+objQuestionResponseVo.getStrSurveyId()+"	StrSectionId() : "+objQuestionResponseVo.getStrSectionId() +" strUserId : "+ objQuestionResponseVo.getStrUserId());
			if(objQuestionResponseVo.getStrSectionId() != null && !objQuestionResponseVo.getStrSectionId().equals("")) 
			{
				getConnection();
				isAccess = objSurveyUser.checkUserCanAccessSurvey(connection, objQuestionResponseVo.getStrSurveyId(), objQuestionResponseVo.getStrUserId());
				//		Added by K.K.Kumaresan on Apr 03,2009 
				String userId=objQuestionResponseVo.getStrUserId();
				if(userId!=null && !userId.equals(""))
				{
					if(userId.indexOf("@")>=0)	// If the user logged in thru mail id,access is granted for that survey.
					{
						isAccess=true;
					}
				}
				// ends
				if(isAccess) 
				{
					objSection = objSectionUser.fetchSectionInfo(connection, objQuestionResponseVo.getStrSectionId());
					objSectionUser.setPrevNextSectionInfo(connection, objSection);
					logger.debug(" getSectionQstRespDetails() => objSection : "+objSection);
					alQuestion = objQuestionUser.fetchSectionQuestionList(connection, objQuestionResponseVo.getStrSectionId());
					logger.debug(" getSectionQstRespDetails() => alQuestion : "+alQuestion);
					aryResponse = objResponseUser.getPublishedResponses(connection, objQuestionResponseVo.getStrSectionId(), objQuestionResponseVo.getStrUserId(), ((isGDSUser)?OISurveySqls.PUBLISH_RESPONSES_LIST:OISurveySqls.PUBLISH_RESPONSES_LIST_TUSER));
					logger.debug(" getSectionQstRespDetails() => aryResponse : "+aryResponse);
					isUserSubmitted = objSurveyUser.checkUserSubmittedSurvey(connection, objQuestionResponseVo.getStrSurveyId(), objQuestionResponseVo.getStrUserId());

					if(aryResponse != null && aryResponse.size() > 0)
					{
						setRadioResponseToPublish(aryResponse);
					}
				} 
				else 
				{
				    error = "NoAccess";
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
			logger.error(" getSectionQstRespDetails => "+be);
		} 
		finally 
		{
			freeConnection();
			addToResponseObject();
			responseObject.addResponseObject("SectionDetails", objSection);
			responseObject.addResponseObject("QuestionList", alQuestion);
			responseObject.addResponseObject("ResponseList", aryResponse);
			responseObject.addResponseObject("isUserSubmitted", new Boolean(isUserSubmitted));
		}
		return responseObject;
	}

	public OIResponseObject getSectionPreviewDetails(OIBAQuestionResponse objQuestionResponseVo)	
	{
		OIDAOSectionUser objSectionUser = new OIDAOSectionUser();
		OIDAOQuestionUser objQuestionUser = new OIDAOQuestionUser();
		OIDAOResponseUser objResponseUser = new OIDAOResponseUser();
		ArrayList alQuestion = null;
		ArrayList aryResponse = new ArrayList();
		OIBASection objSection = null;
		try 
		{
		    logger.debug(" getSectionPreviewDetails() => StrSectionId() : "+objQuestionResponseVo.getStrSectionId());
			if(objQuestionResponseVo.getStrSectionId() != null && !objQuestionResponseVo.getStrSectionId().equals("")) 
			{
				getConnection();
				objSection = objSectionUser.fetchSectionInfo(connection, objQuestionResponseVo.getStrSectionId());
				logger.debug(" getSectionPreviewDetails() => objSection : "+objSection);
				alQuestion = objQuestionUser.fetchSectionQuestionList(connection, objQuestionResponseVo.getStrSectionId());
				logger.debug(" getSectionPreviewDetails() => alQuestion : "+alQuestion);
				aryResponse = objResponseUser.getPublishedResponses(connection, objQuestionResponseVo.getStrSectionId(), "#", OISurveySqls.PUBLISH_RESPONSES_LIST);
				logger.debug(" getSectionPreviewDetails() => aryResponse : "+aryResponse);
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
			logger.error(" getSectionPreviewDetails => "+be);
		} 
		finally 
		{
			freeConnection();
			addToResponseObject();
			responseObject.addResponseObject("SectionDetails", objSection);
			responseObject.addResponseObject("QuestionList", alQuestion);
			responseObject.addResponseObject("ResponseList", aryResponse);
		}
		return responseObject;
	}
	
	public OIResponseObject saveQstRespDetails(OIBAQuestionResponse objQuestionResponseVo)	
	{
		OIDAOSurveyUser objSurveyUser = new OIDAOSurveyUser();
		OIDAOSectionUser objSectionUser = new OIDAOSectionUser();
		OIDAOQuestionUser objQuestionUser = new OIDAOQuestionUser();
		OIDAOResponseUser objResponseUser = new OIDAOResponseUser();
		ArrayList alQuestion = null;
		ArrayList alRespQstIds = null;
		ArrayList aryResponse = null;
		OIBASection objSection = null;
		boolean isUserSubmitted = true;
		boolean saveFalg = false;
		try 
		{
			if(objQuestionResponseVo.getStrSectionId() != null && !objQuestionResponseVo.getStrSectionId().equals("")) 
			{
				setRadioResponseToSave(objQuestionResponseVo.getResponseList());
				getConnection();
				alRespQstIds = objResponseUser.getUserRespQstIdsList(connection, objQuestionResponseVo.getStrSectionId(), objQuestionResponseVo.getStrUserId(), ((isGDSUser)?OISurveySqls.SEC_USER_RESP_QST:OISurveySqls.SEC_USER_RESP_QST_TUSER));
				saveFalg = saveSectionResponses(objQuestionResponseVo.getResponseList(), alRespQstIds, objQuestionResponseVo.getStrSurveyId(), objQuestionResponseVo.getStrUserId());
				if(saveFalg) 
				{
				    logger.debug(" before compare Section Id : "+objQuestionResponseVo.getStrSectionId() + "\t NExt Section Id : " + objQuestionResponseVo.getStrNextSectionId());
					if(!objQuestionResponseVo.getStrSectionId().equals(objQuestionResponseVo.getStrNextSectionId())) 
					{
						objQuestionResponseVo.setStrSectionId(objQuestionResponseVo.getStrNextSectionId());
						logger.debug("Section Id : "+objQuestionResponseVo.getStrSectionId() + "\t NExt Section Id : " + objQuestionResponseVo.getStrNextSectionId());
						objSection = objSectionUser.fetchSectionInfo(connection, objQuestionResponseVo.getStrSectionId());
						logger.debug("After loading Section Info : "+objSection);
						objSectionUser.setPrevNextSectionInfo(connection, objSection);
						logger.debug("After setting prev & Next Section Info : "+objSection);
						alQuestion = objQuestionUser.fetchSectionQuestionList(connection, objQuestionResponseVo.getStrSectionId());
						logger.debug("alQuestion Info : "+alQuestion);
						aryResponse = objResponseUser.getPublishedResponses(connection, objQuestionResponseVo.getStrSectionId(), objQuestionResponseVo.getStrUserId(), ((isGDSUser)?OISurveySqls.PUBLISH_RESPONSES_LIST:OISurveySqls.PUBLISH_RESPONSES_LIST_TUSER));
						logger.debug("aryResponse Info : "+aryResponse);
						isUserSubmitted = objSurveyUser.checkUserSubmittedSurvey(connection, objQuestionResponseVo.getStrSurveyId(), objQuestionResponseVo.getStrUserId());
						if(aryResponse != null && aryResponse.size() > 0)
						{
							setRadioResponseToPublish(aryResponse);
						}
						logger.debug("After invoking setRadioResponseToPublish() : "+aryResponse);
						responseObject.addResponseObject("isCloseScreen", new Boolean(false));
					} 
					else 
					{
						responseObject.addResponseObject("isCloseScreen", new Boolean(true));
					}
				} 
				else 
				{
				    logger.debug("##########  IN Reloading same page ");
				    logger.debug("Section Id : "+objQuestionResponseVo.getStrSectionId());
					objSection = objSectionUser.fetchSectionInfo(connection, objQuestionResponseVo.getStrSectionId());
					logger.debug("After loading Section Info : "+objSection);
					objSectionUser.setPrevNextSectionInfo(connection, objSection);
					logger.debug("After setting prev & Next Section Info : "+objSection);
					alQuestion = objQuestionUser.fetchSectionQuestionList(connection, objQuestionResponseVo.getStrSectionId());
					logger.debug("alQuestion Info : "+alQuestion);
					aryResponse = objQuestionResponseVo.getResponseList();
					isUserSubmitted = objSurveyUser.checkUserSubmittedSurvey(connection, objQuestionResponseVo.getStrSurveyId(), objQuestionResponseVo.getStrUserId());
					responseObject.addResponseObject("isCloseScreen", new Boolean(false));					
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
			logger.error(" saveQstRespDetails => "+be);
		} 
		finally 
		{
			freeConnection();
			addToResponseObject();
			responseObject.addResponseObject("SectionDetails", objSection);
			responseObject.addResponseObject("QuestionList", alQuestion);
			responseObject.addResponseObject("ResponseList", aryResponse);
			responseObject.addResponseObject("isUserSubmitted", new Boolean(isUserSubmitted));
		}
		return responseObject;
	}

	private boolean saveSectionResponses(ArrayList alResponseList, ArrayList alRespQstIds, String strSurveyId, String strUserId) 
	{
		boolean saveFlag = true;
		OIBAResponse objResponse = null;
		OIDAOResponseUser objResponseUser = new OIDAOResponseUser();
		ArrayList alInsertResponses = new ArrayList();
		ArrayList alUpdateResponses = new ArrayList();
		ArrayList alDeleteResponseIds = new ArrayList();
		try 
		{
//			logger.info("1");
			for(int i=0; i<alResponseList.size(); i++) 
			{
				objResponse = (OIBAResponse)alResponseList.get(i);
//				logger.info("2");
				if(objResponse.isEmptyResponse()) 
				{
//					logger.info("3 - " + alRespQstIds.size());
					if(alRespQstIds.indexOf(objResponse.getStrQuestionId()) > -1) 
					{ 
//						logger.info("4");
						alDeleteResponseIds.add(objResponse.getStrResponseId());
//						logger.info(" Delete response index : "+i+" Question No : "+objResponse.getStrQuestionId());					
					}
				} 
				else 
				{
					if(alRespQstIds.indexOf(objResponse.getStrQuestionId()) > -1) 
					{ 
						alUpdateResponses.add(objResponse);
//						logger.info(" Update response index : "+i+" Question No : "+objResponse.getStrQuestionId());					
					} 
					else 
					{
						alInsertResponses.add(objResponse);
//						logger.info(" Insert response index : "+i+" Question No : "+objResponse.getStrQuestionId());
					}
				}
			}
		} 
		catch(Exception be)	
		{
			saveFlag = false;
			error = "OIDEFAULT";
			logger.error(" saveSectionResponses => "+be);
		} 
		try 
		{
		    logger.info(" saveSectionResponses => before invoking updateSurveyUserResponses() => saveFlag : "+saveFlag);
			if(saveFlag) 
			{
				saveFlag = objResponseUser.updateSurveyUserResponses(connection, isGDSUser, alInsertResponses, alUpdateResponses, alDeleteResponseIds, strSurveyId, strUserId);
			}
		} 
		catch(SQLException se)	
		{
			saveFlag = false;
			error = se.getErrorCode()+"";
			logger.error(" saveSectionResponses => "+se);
		} 
		catch(Exception be)	
		{
			saveFlag = false;
			error = "OIDEFAULT";
			logger.error(" saveSectionResponses => "+be);
		} 
		return saveFlag;
	}

	private void setRadioResponseToPublish(ArrayList aryResponse) 
	{
		OIBAResponse objResponse = null;
		for(int i=0; i<aryResponse.size(); i++) 
		{
			objResponse = (OIBAResponse)aryResponse.get(i);
			logger.debug("index i : "+i+" type : "+objResponse.getStrQstType()+" ");
			if(objResponse.getStrQstType().equals(OISurveyConstants.SINGLE_CHOICE) ||
			   objResponse.getStrQstType().equals(OISurveyConstants.LIKERT_SCALE)) 
			{
				objResponse.setRbValueSystemToForm();
				logger.debug("objResponse =>  radio selected "+objResponse.getStrRbResponse());
			}
		}
	}
	
	private void setRadioResponseToSave(ArrayList aryResponse) 
	{
		OIBAResponse objResponse = null;
		for(int i=0; i<aryResponse.size(); i++) 
		{
			objResponse = (OIBAResponse)aryResponse.get(i);
			if(objResponse.getStrQstType().equals(OISurveyConstants.SINGLE_CHOICE) ||
			   objResponse.getStrQstType().equals(OISurveyConstants.LIKERT_SCALE)) 
			{
				objResponse.setRbValueFormToSystem();
				logger.debug("objResponse =>  radio selected "+objResponse.getStrRbResponse());
			}
		}
	}

	public ArrayList getSections(String surveyId)	
	{
		
		ArrayList arySections = new ArrayList();
		OIDAOSectionUser objSectionUser = new OIDAOSectionUser();
		try 
		{
		  	if(surveyId != null && !surveyId.equals("")) 
			{
				getConnection();
				arySections = objSectionUser.getSections(connection, surveyId);
				
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
			logger.error(" getSections => "+be);
		} 
		finally 
		{
			freeConnection();
			
		}
		return arySections;
	}
}
