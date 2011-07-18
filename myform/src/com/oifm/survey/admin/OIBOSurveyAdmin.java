
package com.oifm.survey.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.upload.FormFile;

import com.oifm.base.OIBaseBo;
import com.oifm.common.OIApplConstants;
import com.oifm.common.OIDAOSendMail;
import com.oifm.common.OILabelConstants;
import com.oifm.common.OIPageInfoBean;
import com.oifm.common.OIResponseObject;
import com.oifm.survey.OIBAQuestion;
import com.oifm.survey.OIBAResponse;
import com.oifm.survey.OIBASection;
import com.oifm.survey.OIBASurvey;
import com.oifm.survey.OIBASurveyResponse;
import com.oifm.survey.OIBASurveySection;
import com.oifm.survey.OIDAOSectionUser;
import com.oifm.survey.OISurveyConstants;
import com.oifm.survey.OISurveySqls;
import com.oifm.utility.OIUtilities;

public class OIBOSurveyAdmin extends OIBaseBo 
{
	private static Logger logger = Logger.getLogger(OIBOSurveyAdmin.class);

	public OIBOSurveyAdmin()	
	{
		super();
	}

	public OIResponseObject getSurveyList(OIBASurvey objSurveyVo)	
	{
		OIDAOSurveyAdmin objSurveyAdmin = new OIDAOSurveyAdmin();
		OIPageInfoBean objPageBean = null;
		ArrayList alSurveyList = null;
		int recPerPage = OIApplConstants.RECORDS_PER_PAGE;
		
		try 
		{
			
			//modify by edmund
			if(objSurveyVo.getHidOrder()!= null && objSurveyVo.getHidSortKey() != null && "sort".equals(objSurveyVo.getStrSort())){
				objSurveyVo.setPageNo(1);
				
				if(objSurveyVo.getHidSortKey().equals("START_ON"))
				{
					objSurveyVo.setHidSortKey("OSS.start_on");
				}
			}
			
			getConnection();
			recPerPage = OIDAOSendMail.recPerPage(connection);
			if(recPerPage > 0) 
			{
				objPageBean = new OIPageInfoBean(objSurveyVo.getPageNo(), recPerPage);
			}
			else
			{
			    objPageBean = new OIPageInfoBean(objSurveyVo.getPageNo(), OIApplConstants.RECORDS_PER_PAGE);
			}
			int recTotal = objSurveyAdmin.getSurveysCount(connection);
			objPageBean.setTotalRec(recTotal);
			
			//added by edmund
			//System.out.println("OIBOSurveyAdmin: getSurveyList - BO : getSurveyList");
			if(objSurveyVo.getHidOrder()!= null && objSurveyVo.getHidOrder().trim().length()!=0 &&objSurveyVo.getHidSortKey() != null && objSurveyVo.getHidSortKey().trim().length()!=0){
				if(objSurveyVo.getHidSortKey().equals("START_ON"))
				{
					objSurveyVo.setHidSortKey("OSS.start_on");
				}
				//System.out.println("OIBOSurveyAdmin: getSurveyList - SortKey : "+objSurveyVo.getHidSortKey());
				alSurveyList = objSurveyAdmin.getSurveyList(connection, objPageBean, objSurveyVo);
			}else
			{
				alSurveyList = objSurveyAdmin.getSurveyList(connection, objPageBean);
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
			logger.error(" getSurveyList => "+be);
		} 
		finally 
		{
			freeConnection();
			addToResponseObject();
			responseObject.addResponseObject("SurveyList", alSurveyList);
			responseObject.addResponseObject("PageInfo", objPageBean);
		}
		return responseObject;
	}
	
	public OIResponseObject getDivisionSurveyList(OIBASurvey objSurveyVo)	
	{
		OIDAOSurveyAdmin objSurveyAdmin = new OIDAOSurveyAdmin();
		OIPageInfoBean objPageBean = null;
		ArrayList alSurveyList = null;
		int recPerPage = OIApplConstants.RECORDS_PER_PAGE;
		
		try 
		{
			if(objSurveyVo.getHidOrder()!= null && objSurveyVo.getHidSortKey() != null && "sort".equals(objSurveyVo.getStrSort())){
				objSurveyVo.setPageNo(1);
				
				if(objSurveyVo.getHidSortKey().equals("START_ON"))
				{
					objSurveyVo.setHidSortKey("OSS.start_on");
				}
			}
			
			getConnection();
			recPerPage = OIDAOSendMail.recPerPage(connection);
			if(recPerPage > 0) 
			{
				objPageBean = new OIPageInfoBean(objSurveyVo.getPageNo(), recPerPage);
			}
			else
			{
			    objPageBean = new OIPageInfoBean(objSurveyVo.getPageNo(), OIApplConstants.RECORDS_PER_PAGE);
			}
			int recTotal = objSurveyAdmin.getDivisionSurveysCount(connection,objSurveyVo.getStrDivisionCode());
			objPageBean.setTotalRec(recTotal);
			
			//added by edmund
			//System.out.println("getDivisionSurveyList: getSurveyList - BO : getSurveyList");
			if(objSurveyVo.getHidOrder()!= null && objSurveyVo.getHidSortKey() != null && objSurveyVo.getHidSortKey() != ""){
				//System.out.println("getDivisionSurveyList: getSurveyList - SortKey : "+objSurveyVo.getHidSortKey());
				alSurveyList = objSurveyAdmin.getDivisionSurveyList(connection, objPageBean, objSurveyVo);
			}else
			{
				alSurveyList = objSurveyAdmin.getDivisionSurveyList(connection, objPageBean,objSurveyVo.getStrDivisionCode() );
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
			logger.error(" getSurveyList => "+be);
		} 
		finally 
		{
			freeConnection();
			addToResponseObject();
			responseObject.addResponseObject("SurveyList", alSurveyList);
			responseObject.addResponseObject("PageInfo", objPageBean);
		}
		return responseObject;
	}

	public OIResponseObject getSurveyDivision()	
	{
		OIDAOSurveyAdmin objSurveyAdmin = new OIDAOSurveyAdmin();
		ArrayList alSurveyList = null;
		
		try 
		{
			getConnection();
			alSurveyList = objSurveyAdmin.getSurveyDivision(connection);
		} 
		catch(SQLException se) 
		{
			error = ""+se.getErrorCode();
            logger.error(se);
		} 
		catch(Exception be)	
		{
			error = "OIDEFAULT";
			logger.error(" getSurveyDivisionList => "+be);
		} 
		finally 
		{
			freeConnection();
			addToResponseObject();
			responseObject.addResponseObject("SurveyDivision", alSurveyList);
		}
		return responseObject;
	}
	
	public OIResponseObject getSurveyDetails(OIBASurvey objSurveyVo)
	{
		OIDAOSurveyAdmin objSurveyAdmin = new OIDAOSurveyAdmin();
		String strDivCode = objSurveyVo.getStrDivisionCode();
		if (strDivCode==null)
		{
		    strDivCode="";
		}
		boolean isCurrentlyValid = false;
		boolean isSurveyDivision = true;
		boolean isOwner = false;
		try 
		{
			getConnection();
			if(objSurveyVo.getStrSurveyId() != null && !objSurveyVo.getStrSurveyId().equals("")) 
			{
				OIBASurveySection objSurveySectionVo = new OIBASurveySection();
				objSurveySectionVo.setStrSurveyId(objSurveyVo.getStrSurveyId());
				objSurveyVo = objSurveyAdmin.fetchSurveyInfo(connection, objSurveySectionVo);
				isCurrentlyValid = !objSurveyAdmin.checkSurveyCanDelete(connection, objSurveyVo.getStrSurveyId());
				isSurveyDivision = objSurveyAdmin.isSurveyOwnerDivision(connection, objSurveyVo.getStrSurveyId(), strDivCode);
				if (objSurveyVo.getStrDivisionCode()!=null)
				    isOwner = (objSurveyVo.getStrDivisionCode().equals(strDivCode));
			}
			objSurveyVo.setStrDefaultEmailMessage(objSurveyAdmin.getSurveyDefaultMessage(connection));
		} catch(SQLException se) 
		{
			error = ""+se.getErrorCode();
            logger.error(se);
		} catch(Exception be)	
		{
			error = "OIDEFAULT";
			logger.error(" getSurveyDetails => "+be);
		} 
		finally 
		{
			freeConnection();
			addToResponseObject();
			responseObject.addResponseObject("objSurveyVo", objSurveyVo);
			responseObject.addResponseObject("isCurrentlyValid", new Boolean(isCurrentlyValid));
			responseObject.addResponseObject("isSurveyDivision", new Boolean(isSurveyDivision));
		//	responseObject.addResponseObject("isOwner", new Boolean(isOwner));
		}
		return responseObject;
	}

	public OIResponseObject getSurveyPublishDetails(OIBASurvey objSurveyVo, String strRemoveFileName)	
	{
		OIDAOSurveyAdmin objSurveyAdmin = new OIDAOSurveyAdmin();
		String strDivCode = objSurveyVo.getStrDivisionCode();
		ArrayList alAckTypes = null;
		boolean isOwner = false;
		boolean removeFlag = false;
		boolean isSurveyDivision = true;
		try 
		{
			if(objSurveyVo.getStrSurveyId() != null && !objSurveyVo.getStrSurveyId().equals("")) 
			{
				getConnection();
				if(strRemoveFileName != null && !strRemoveFileName.equals("")) 
				{
					OIFileUpload objFileUpload = new OIFileUpload();
					removeFlag = objFileUpload.removeFile(strRemoveFileName);
					logger.debug(" remove attachment info for survey : "+removeFlag );
					/*if(!removeFlag)	
					{
						//error = "OIDEFAULT";
						System.out.println("error = OIDEFAULT");
					} 
					else 
					{
					    logger.debug(" Before updating attachment info for survey : "+objSurveyVo.getStrSurveyId());
						objSurveyAdmin.removeSurveyAttchedInfo(connection, objSurveyVo.getStrSurveyId());
					}*/
					objSurveyAdmin.removeSurveyAttchedInfo(connection, objSurveyVo.getStrSurveyId());
				}
				OIBASurveySection objSurveySectionVo = new OIBASurveySection();
				objSurveySectionVo.setStrSurveyId(objSurveyVo.getStrSurveyId());
				objSurveyVo = objSurveyAdmin.fetchSurveyInfo(connection, objSurveySectionVo);
				isSurveyDivision = objSurveyAdmin.isSurveyOwnerDivision(connection, objSurveyVo.getStrSurveyId(), strDivCode);
			//	isOwner = (objSurveyVo.getStrDivisionCode().equals(strDivCode));
			}
			if (objSurveyVo.getStrSurveyType().equals("Y"))
			{
			    alAckTypes = objSurveyAdmin.getLabelValues(connection, OISurveyConstants.PUBLISH_CATEGORY,OISurveyConstants.PUBLISH_CATEGORY_P);
			}
			else
			{
			    alAckTypes = objSurveyAdmin.getLabelValues(connection, OISurveyConstants.PUBLISH_CATEGORY,"");
			}
			responseObject.addResponseObject("AckTypes", alAckTypes);
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
			responseObject.addResponseObject("isSurveyDivision", new Boolean(isSurveyDivision));
//			responseObject.addResponseObject("isOwner", new Boolean(isOwner));
		}
		return responseObject;
	}

	public OIResponseObject saveSurveyDetails(OIBASurvey objSurveyVo)
	{
		boolean saveFlag = false;
		OIDAOSurveyAdmin objSurveyAdmin = new OIDAOSurveyAdmin();
		String strSurveyId = "";
		try 
		{
			getConnection();
			if(objSurveyVo.getStrSurveyId() != null && !objSurveyVo.getStrSurveyId().equals("")) 	// modify mode
			{
				saveFlag = objSurveyAdmin.modifySurvey(connection, objSurveyVo);
				//				 added by K.K.Kumaresan on Jan 19,2008
				//		delete all the outside email entries for this survey.
				boolean flag=objSurveyAdmin.deleteExternalMailAddress(connection,objSurveyVo.getStrSurveyId());
			} 
			else 
			{
				strSurveyId = objSurveyAdmin.getNewSurveyId(connection);
				objSurveyVo.setStrSurveyId(strSurveyId);
				saveFlag = objSurveyAdmin.createNewSurvey(connection, objSurveyVo);
				
				responseObject.addResponseObject("strSurveyId", strSurveyId);
			}
			boolean flag= objSurveyAdmin.insertExternalMailAddress(connection, objSurveyVo); 	//	added by K.K.Kumaresan on Jan 19,2008
			if(!saveFlag)
				error = "OIDEFAULT";
			else addMessageList("SuccessSave");
		} 
		catch(SQLException se) 
		{
			error = ""+se.getErrorCode();
            logger.error(se);
		} 
		catch(Exception be)	
		{
			error = "OIDEFAULT";
			logger.error(" saveSurveyDetails => "+be);
		} 
		finally 
		{
			addToResponseObject();
			freeConnection();
		}
		return responseObject;
	}

	public OIResponseObject activateSurvey(OIBASurvey objSurveyVo)	
	{
		boolean saveFlag = false;
		OIDAOSurveyAdmin objSurveyAdmin = new OIDAOSurveyAdmin();
		try 
		{
			getConnection();
			logger.debug("activateSurvey() => "+objSurveyVo.getStrSurveyId());
			if(objSurveyVo.getStrSurveyId() != null && !objSurveyVo.getStrSurveyId().equals(""))
				saveFlag = objSurveyAdmin.activateSurvey(connection, objSurveyVo.getStrSurveyId());
			if(!saveFlag) 
			    error = "OIDEFAULT";
			else 
			    addMessageList("SuccessSave");
		} 
		catch(SQLException se) 
		{
			error = ""+se.getErrorCode();
            logger.error(se);
		} 
		catch(Exception be)	
		{
			error = "OIDEFAULT";
			logger.error(" activateSurvey => "+be);
		} 
		finally 
		{
			addToResponseObject();
			freeConnection();
		}
		return responseObject;
	}

	public OIResponseObject deleteSurveyInfo(OIBASurvey objSurveyVo)
	{
		boolean deleteFlag = false;
		OIDAOSurveyAdmin objSurveyAdmin = new OIDAOSurveyAdmin();
		OIDAOResponseAdmin objResponseAdmin = new OIDAOResponseAdmin();
		try 
		{
			getConnection();
			connection.setAutoCommit(false);
			if(objSurveyVo.getStrSurveyId() != null && !objSurveyVo.getStrSurveyId().equals("")) 
			{
				deleteFlag = objSurveyAdmin.checkSurveyCanDelete(connection, objSurveyVo.getStrSurveyId());
				if(deleteFlag) 
				{
					deleteFlag = objResponseAdmin.delteSurveyResponses(connection, objSurveyVo.getStrSurveyId());
					if(deleteFlag) 
					{
					    deleteFlag = (new OIDAOQuestionAdmin()).delteSurveyQuestions(connection, objSurveyVo.getStrSurveyId());
					}
					if(deleteFlag)
					{
					    deleteFlag = (new OIDAOSectionAdmin()).delteSurveySections(connection, objSurveyVo.getStrSurveyId());
					}
					if(deleteFlag)
					{
					    deleteFlag = objResponseAdmin.delteDraftInfo(connection, objSurveyVo.getStrSurveyId());
					}
					if(deleteFlag)
					{
						deleteFlag = objResponseAdmin.deleteMembers(connection, objSurveyVo.getStrSurveyId());
					}
					if(deleteFlag)
					{
					    deleteFlag = objSurveyAdmin.deleteSurvey(connection, objSurveyVo.getStrSurveyId());
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
		} 
		catch(SQLException se) 
		{
			deleteFlag = false;
			error = ""+se.getErrorCode();
			logger.error(" deleteSurveyInfo => "+se);
		} 
		catch(Exception be)	
		{
			deleteFlag = false;
			error = "OIDEFAULT";
			logger.error(" deleteSurveyInfo => "+be);
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
		}
		return responseObject;
	}

	public OIResponseObject saveSurveyPublishInfo(OIBASurvey objSurveyVo, FormFile formFile)
	{
		boolean saveFlag = false;
		ArrayList alAckTypes = null;
		OIDAOSurveyAdmin objSurveyAdmin = new OIDAOSurveyAdmin();
		try 
		{
		    logger.debug(" upload FileName : "+formFile.getFileName() +" New FileName : "+objSurveyVo.getStrAttachedFile());
			if(formFile != null && !formFile.getFileName().trim().equals("") && formFile.getFileSize() > 0) 
			{
				if(formFile.getFileSize() >= 2*1024*1024) 
				{
					error = "FileSizeExceed";
				} 
				else 
				{
					OIFileUpload objFileUpload = new OIFileUpload();
					saveFlag = objFileUpload.uploadFile(formFile, objSurveyVo.getStrAttachedFile());
				} 
			} 
			else 
			{
				objSurveyVo.setStrAttachedFile("");
				saveFlag = true;
			}
			getConnection();
			if(saveFlag) 
			{
				saveFlag = objSurveyAdmin.modifySurveyPublish(connection, objSurveyVo);
				if(!saveFlag)
				{
					error = "OIDEFAULT";
				}
				else
				{
				    addMessageList("SuccessSave");
				}
			} 
			else 
			{	OIBASurveySection objSurveySectionVo = new OIBASurveySection();
				objSurveySectionVo.setStrSurveyId(objSurveyVo.getStrSurveyId());
				objSurveyVo = objSurveyAdmin.fetchSurveyInfo(connection, objSurveySectionVo);
				//alAckTypes = objSurveyAdmin.getLabelValues(connection, OISurveyConstants.PUBLISH_CATEGORY);
				if (objSurveyVo.getStrSurveyType().equals("Y"))
				{
				    alAckTypes = objSurveyAdmin.getLabelValues(connection, OISurveyConstants.PUBLISH_CATEGORY,OISurveyConstants.PUBLISH_CATEGORY_P);
				}
				else
				{
				    alAckTypes = objSurveyAdmin.getLabelValues(connection, OISurveyConstants.PUBLISH_CATEGORY,"");
				}
				if(error == null || error.equals("") )
				{
					error = "FileUploadError";
				}
			}
		}
		catch(IOException ie) 
		{
			error = "FileUploadError";
			logger.error(" saveSurveyPublishInfo() => "+ie);
		}
		catch(SQLException se) 
		{
			error = ""+se.getErrorCode();
            logger.error(se);
		}
		catch(Exception be) 
		{
			error = "OIDEFAULT";
			logger.error(" saveSurveyPublishInfo => "+be);
		}
		finally 
		{
			freeConnection();
			addToResponseObject();
			responseObject.addResponseObject("AckTypes", alAckTypes);
			responseObject.addResponseObject("objSurveyVo", objSurveyVo);
		}
		return responseObject;
	}

	public OIResponseObject exportSurveyDetails(String strSurveyId, PrintWriter out)
	{
		OIDAOSectionUser objSectionUser = new OIDAOSectionUser(); 
		OIDAOSurveyAdmin objSurveyAdmin = new OIDAOSurveyAdmin();
		OIDAOQuestionAdmin objQuestionAdmin = new OIDAOQuestionAdmin();
		ArrayList alResponse = null;
		ArrayList alIdsList = null;
		boolean exportFlag = false;
		boolean moreRecords = true;
		boolean isPublicSurvey = false;
		boolean isTempUser = false;
		String strOut = "";
		int startRec = 1;
		try 
		{
			getConnection();
			if(strSurveyId != null && !strSurveyId.equals("")) 
			{
				
				OIBASurveySection objSurveySectionVo = new OIBASurveySection();
				objSurveySectionVo.setStrSurveyId(strSurveyId);
				OIBASurvey objSurveyVo = objSurveyAdmin.fetchSurveyInfo(connection, objSurveySectionVo);
				ArrayList alSectionList = objSectionUser.getSectionHierarchy(connection, strSurveyId);
				objSectionUser.setLevelsHirarchy(alSectionList);
				Hashtable htbSecQstAns = objQuestionAdmin.getSectionsQuestionsAnswersList(connection, strSurveyId);
				alIdsList = objQuestionAdmin.getQuestionIdsList(connection, strSurveyId);
				//comented by rajesh freeConnection();
				
				//commented by edmund
				//strOut = getStrSurveyInfo(objSurveyVo, alSectionList, htbSecQstAns);
				//writeContentToStream(strOut, out);
				//isPublicSurvey = (objSurveyVo != null && objSurveyVo.getStrSurveyType().equals("N"));
				//end comment
				
				while(moreRecords) 
				{
					alResponse = getSurveyResponses(strSurveyId, startRec, isTempUser);
					logger.debug("alResponse = "+alResponse);					
				/*	if(alResponse.size() > OISurveyConstants.EXPORT_BUFFER_RECS) {
						moreRecords = true;
						startRec += OISurveyConstants.EXPORT_BUFFER_RECS;
					} else 
				*/	strOut = getStrSurveyResponsesInfo(alIdsList, alResponse,alSectionList,htbSecQstAns,objSurveyVo);
					
					logger.debug("strOut = "+strOut);

					writeContentToStream(strOut, out);
					moreRecords = false;
					if(!isTempUser && moreRecords) 
					{
						moreRecords = true;
						isTempUser = true;
						startRec = 1;
					}
				}
				//Added by Edmund
				strOut = getStrSurveyInfo(objSurveyVo, alSectionList, htbSecQstAns);
				writeContentToStream(strOut, out);
				isPublicSurvey = (objSurveyVo != null && objSurveyVo.getStrSurveyType().equals("N"));
				//ended by Edmund
				
				exportFlag = true;
			}	
		}
		catch(IOException ie) 
		{
			error = "SurveyExportError";
			logger.error(" exportSurveyDetails() => "+ie);
		}
		catch(SQLException se) 
		{
			error = ""+se.getErrorCode();
            logger.error(se);
		}
		catch(Exception be) 
		{
			error = "OIDEFAULT";
			logger.error(" exportSurveyDetails => "+be);
		}
		finally 
		{
			freeConnection();
    		if(out != null)	
    		{
    			out.flush();
    			out.close();
    		}
			addToResponseObject();
			responseObject.addResponseObject("exportFlag", new Boolean(exportFlag));
		}
		return responseObject;
	}

	public OIResponseObject exportSurveyDetailsHtml(String strSurveyId, HttpServletRequest request)
	{
		OIDAOSectionUser objSectionUser = new OIDAOSectionUser(); 
		OIDAOSurveyAdmin objSurveyAdmin = new OIDAOSurveyAdmin();
		OIDAOQuestionAdmin objQuestionAdmin = new OIDAOQuestionAdmin();
		ArrayList alResponse = null;
		ArrayList alIdsList = null;
		boolean exportFlag = false;
		boolean moreRecords = true;
		boolean isPublicSurvey = false;
		boolean isTempUser = false;
		String strOut = "";
		int startRec = 1;
		try 
		{
			getConnection();
			if(strSurveyId != null && !strSurveyId.equals("")) 
			{
				
				OIBASurveySection objSurveySectionVo = new OIBASurveySection();
				objSurveySectionVo.setStrSurveyId(strSurveyId);
				OIBASurvey objSurveyVo = objSurveyAdmin.fetchSurveyInfo(connection, objSurveySectionVo);
				ArrayList alSectionList = objSectionUser.getSectionHierarchy(connection, strSurveyId);
				objSectionUser.setLevelsHirarchy(alSectionList);
				Hashtable htbSecQstAns = objQuestionAdmin.getSectionsQuestionsAnswersList(connection, strSurveyId);
				alIdsList = objQuestionAdmin.getQuestionIdsList(connection, strSurveyId);
				//comented by rajesh freeConnection();
				strOut = getStrSurveyInfoHtml(objSurveyVo, alSectionList, htbSecQstAns);
				request.setAttribute("surveyInfo",strOut);
				//writeContentToStream(strOut, out);
				isPublicSurvey = (objSurveyVo != null && objSurveyVo.getStrSurveyType().equals("N"));
				while(moreRecords) 
				{
					alResponse = getSurveyResponses(strSurveyId, startRec, isTempUser);
					logger.debug("alResponse = "+alResponse);					
				/*	if(alResponse.size() > OISurveyConstants.EXPORT_BUFFER_RECS) {
						moreRecords = true;
						startRec += OISurveyConstants.EXPORT_BUFFER_RECS;
					} else 
				*/	ArrayList alQuestAnsList = getStrSurveyResponsesInfoHtml(alIdsList, alResponse,alSectionList,htbSecQstAns);
					
					logger.debug("strOut = "+strOut);

					//writeContentToStream(strOut, out);
					request.setAttribute("alQuestAnsList",alQuestAnsList);
					request.setAttribute("alIdsList",alIdsList);
					request.setAttribute("alResponse",alResponse);
					moreRecords = false;
					if(!isTempUser && moreRecords) 
					{
						moreRecords = true;
						isTempUser = true;
						startRec = 1;
					}
				}
				exportFlag = true;
			}	
		}
		catch(IOException ie) 
		{
			error = "SurveyExportError";
			logger.error(" exportSurveyDetails() => "+ie);
		}
		catch(SQLException se) 
		{
			error = ""+se.getErrorCode();
            logger.error(se);
		}
		catch(Exception be) 
		{
			error = "OIDEFAULT";
			logger.error(" exportSurveyDetails => "+be);
		}
		finally 
		{
			freeConnection();
    	/*	if(out != null)	
    		{
    			out.flush();
    			out.close();
    		}*/
			addToResponseObject();
			responseObject.addResponseObject("exportFlag", new Boolean(exportFlag));
		}
		return responseObject;
	}
	
	private ArrayList getSurveyResponses(String strSurveyId, int startRec, boolean isTempUser) throws SQLException 
	{
		OIDAOResponseAdmin objResponseAdmin = new OIDAOResponseAdmin();
		ArrayList alResponse = new ArrayList();
		try 
		{
			//comment by rajesh getConnection();
			alResponse = objResponseAdmin.getSurveyResponses(connection, strSurveyId, startRec, ((isTempUser)? OISurveySqls.SURVEY_RESPONSES_TUSER : OISurveySqls.SURVEY_RESPONSES));
		}
		catch(SQLException se) 
		{
            logger.error(se);
			throw se;
		}
		finally 
		{
			//comment by rajesh freeConnection();
		}
		return alResponse;
	}

	private String getStrSurveyInfo(OIBASurvey objSurveyVo, ArrayList alSectionList, Hashtable htbSecQstAns) throws IOException  
	{
		OIBASection objSection = null;
		OIBAQuestion objQuestion = null;
		ArrayList alQuestAns = null;
		int cnt =0;
		StringBuffer stbrOut = new StringBuffer();
		//added by edmund
		//stbrOut.append("Survey Name:"+OILabelConstants.TAB+objSurveyVo.getStrSurveyName()+OILabelConstants.LINE+OILabelConstants.LINE);
		//stbrOut.append("Description:"+OILabelConstants.TAB+objSurveyVo.getStrDescription().replace('\n', ' ')+OILabelConstants.LINE);
		//stbrOut.append("Instruction:"+OILabelConstants.TAB+OIUtilities.returnEmptyIfNull(objSurveyVo.getStrInstruction()).replace('\n', ' ')+OILabelConstants.LINE);
		//stbrOut.append("Period:"+OILabelConstants.TAB+objSurveyVo.getStrFromDate()+" to "+objSurveyVo.getStrToDate()+OILabelConstants.LINE+OILabelConstants.LINE);
		
		stbrOut.append(OILabelConstants.LINE);
		//ended by edmund
		for(int i=0; i<alSectionList.size(); i++) 
		{
			objSection = (OIBASection)alSectionList.get(i);
			stbrOut.append("'"+objSection.getStrLevelLabel()+OILabelConstants.TAB+objSection.getStrSectionName()+OILabelConstants.LINE);
			stbrOut.append(OILabelConstants.TAB+"Description:"+OILabelConstants.TAB+objSection.getStrDescription().replace('\n', ' ')+OILabelConstants.LINE);
			stbrOut.append(OILabelConstants.TAB+"Instruction:"+OILabelConstants.TAB+OIUtilities.returnEmptyIfNull(objSection.getStrInstruction()).replace('\n', ' ')+OILabelConstants.LINE+OILabelConstants.LINE);
			if(htbSecQstAns.containsKey(objSection.getStrSectionId())) 
			{
				alQuestAns = (ArrayList)htbSecQstAns.get(objSection.getStrSectionId());
				for(int j=0; j<alQuestAns.size(); j++) 
				{
					objQuestion = (OIBAQuestion)alQuestAns.get(j);
					//stbrOut.append(OILabelConstants.TAB+"Q"+(++cnt)+"-"+objQuestion.getStrQuestionId()+OILabelConstants.TAB+objQuestion.getStrQuestion().replace('\n', ' ')+OILabelConstants.LINE);
					stbrOut.append(OILabelConstants.TAB+"Q"+(++cnt)+OILabelConstants.TAB+objQuestion.getStrQuestion().replace('\n', ' ')+OILabelConstants.LINE);
					stbrOut.append(OILabelConstants.TAB+OILabelConstants.TAB+"Instruction:"+OILabelConstants.TAB+OIUtilities.returnEmptyIfNull(objQuestion.getStrInstruction()).replace('\n', ' ')+OILabelConstants.LINE);
					stbrOut.append(OILabelConstants.TAB+OILabelConstants.TAB+"Answer 1:"+OILabelConstants.TAB+OIUtilities.returnEmptyIfNull(objQuestion.getStrAnswer1())+OILabelConstants.LINE);
					stbrOut.append(OILabelConstants.TAB+OILabelConstants.TAB+"Answer 2:"+OILabelConstants.TAB+OIUtilities.returnEmptyIfNull(objQuestion.getStrAnswer2())+OILabelConstants.LINE);
					stbrOut.append(OILabelConstants.TAB+OILabelConstants.TAB+"Answer 3:"+OILabelConstants.TAB+OIUtilities.returnEmptyIfNull(objQuestion.getStrAnswer3())+OILabelConstants.LINE);
					stbrOut.append(OILabelConstants.TAB+OILabelConstants.TAB+"Answer 4:"+OILabelConstants.TAB+OIUtilities.returnEmptyIfNull(objQuestion.getStrAnswer4())+OILabelConstants.LINE);
					stbrOut.append(OILabelConstants.TAB+OILabelConstants.TAB+"Answer 5:"+OILabelConstants.TAB+OIUtilities.returnEmptyIfNull(objQuestion.getStrAnswer5())+OILabelConstants.LINE);
					stbrOut.append(OILabelConstants.TAB+OILabelConstants.TAB+"Answer 6:"+OILabelConstants.TAB+OIUtilities.returnEmptyIfNull(objQuestion.getStrAnswer6())+OILabelConstants.LINE);
					stbrOut.append(OILabelConstants.TAB+OILabelConstants.TAB+"Answer 7:"+OILabelConstants.TAB+OIUtilities.returnEmptyIfNull(objQuestion.getStrAnswer7())+OILabelConstants.LINE);
					stbrOut.append(OILabelConstants.TAB+OILabelConstants.TAB+"Answer 8:"+OILabelConstants.TAB+OIUtilities.returnEmptyIfNull(objQuestion.getStrAnswer8())+OILabelConstants.LINE);
					stbrOut.append(OILabelConstants.TAB+OILabelConstants.TAB+"Answer 9:"+OILabelConstants.TAB+OIUtilities.returnEmptyIfNull(objQuestion.getStrAnswer9())+OILabelConstants.LINE);
					stbrOut.append(OILabelConstants.TAB+OILabelConstants.TAB+"Answer 10:"+OILabelConstants.TAB+OIUtilities.returnEmptyIfNull(objQuestion.getStrAnswer10())+OILabelConstants.LINE+OILabelConstants.LINE);
				}			
			}
		}
		return stbrOut.toString();
	}

	private String getStrSurveyInfoHtml(OIBASurvey objSurveyVo, ArrayList alSectionList, Hashtable htbSecQstAns) throws IOException  
	{
		OIBASection objSection = null;
		OIBAQuestion objQuestion = null;
		ArrayList alQuestAns = null;
		int cnt =0;
		StringBuffer stbrOut = new StringBuffer();
		stbrOut.append("<table border =1 width=100%>");
		stbrOut.append("<tr><td>");
		stbrOut.append("<b>Survey Name:</b></td><td colspan=3>"+objSurveyVo.getStrSurveyName()+"</td></tr>");
		stbrOut.append("<tr><td><b>Description:</b></td><td colspan=3>"+objSurveyVo.getStrDescription().replace('\n', ' ')+"</td></tr>");
		stbrOut.append("<tr><td><b>Instruction:</b></td><td colspan=3>"+OIUtilities.returnEmptyIfNull(objSurveyVo.getStrInstruction()).replace('\n', ' ')+"&nbsp;</td></tr>");
		stbrOut.append("<tr><td><b>Period:</b></td><td colspan=3>"+objSurveyVo.getStrFromDate()+" to "+objSurveyVo.getStrToDate()+"</td></tr>");
		
		for(int i=0; i<alSectionList.size(); i++) 
		{
			objSection = (OIBASection)alSectionList.get(i);
			stbrOut.append("<tr><td>"+objSection.getStrLevelLabel()+"</td><td colspan=3>"+objSection.getStrSectionName()+"</td></tr>");
			stbrOut.append("<tr><td>&nbsp;</td><td>"+"Description:"+"</td><td colspan=2>"+objSection.getStrDescription().replace('\n', ' ')+"</td></tr>");
			stbrOut.append("<tr><td>&nbsp;</td><td>"+"Instruction:"+"</td><td colspan=2>"+OIUtilities.returnEmptyIfNull(objSection.getStrInstruction()).replace('\n', ' ')+"</td></tr>");
			if(htbSecQstAns.containsKey(objSection.getStrSectionId())) 
			{
				alQuestAns = (ArrayList)htbSecQstAns.get(objSection.getStrSectionId());
				for(int j=0; j<alQuestAns.size(); j++) 
				{
					objQuestion = (OIBAQuestion)alQuestAns.get(j);
					//stbrOut.append(OILabelConstants.TABCOL+"Q"+(++cnt)+"-"+objQuestion.getStrQuestionId()+OILabelConstants.TABCOL+objQuestion.getStrQuestion().replace('\n', ' ')+OILabelConstants.TABROW);
					stbrOut.append("<tr><td>&nbsp;</td><td>"+"Q"+(++cnt)+"</td><td colspan=2>"+objQuestion.getStrQuestion().replace('\n', ' ')+"</td></tr>");
					stbrOut.append("<tr><td>&nbsp;</td><td>&nbsp;</td><td>"+"Instruction:"+"</td><td>"+OIUtilities.returnEmptyIfNull(objQuestion.getStrInstruction()).replace('\n', ' ')+"&nbsp;</td></tr>");
					stbrOut.append("<tr><td>&nbsp;</td><td>&nbsp;</td><td>"+"Answer 1:"+"</td><td>"+OIUtilities.returnEmptyIfNull(objQuestion.getStrAnswer1())+"&nbsp;</td></tr>");
					stbrOut.append("<tr><td>&nbsp;</td><td>&nbsp;</td><td>"+"Answer 2:"+"</td><td>"+OIUtilities.returnEmptyIfNull(objQuestion.getStrAnswer2())+"&nbsp;</td></tr>");
					stbrOut.append("<tr><td>&nbsp;</td><td>&nbsp;</td><td>"+"Answer 3:"+"</td><td>"+OIUtilities.returnEmptyIfNull(objQuestion.getStrAnswer3())+"&nbsp;</td></tr>");
					stbrOut.append("<tr><td>&nbsp;</td><td>&nbsp;</td><td>"+"Answer 4:"+"</td><td>"+OIUtilities.returnEmptyIfNull(objQuestion.getStrAnswer4())+"&nbsp;</td></tr>");
					stbrOut.append("<tr><td>&nbsp;</td><td>&nbsp;</td><td>"+"Answer 5:"+"</td><td>"+OIUtilities.returnEmptyIfNull(objQuestion.getStrAnswer5())+"&nbsp;</td></tr>");
					stbrOut.append("<tr><td>&nbsp;</td><td>&nbsp;</td><td>"+"Answer 6:"+"</td><td>"+OIUtilities.returnEmptyIfNull(objQuestion.getStrAnswer6())+"&nbsp;</td></tr>");
					stbrOut.append("<tr><td>&nbsp;</td><td>&nbsp;</td><td>"+"Answer 7:"+"</td><td>"+OIUtilities.returnEmptyIfNull(objQuestion.getStrAnswer7())+"&nbsp;</td></tr>");
					stbrOut.append("<tr><td>&nbsp;</td><td>&nbsp;</td><td>"+"Answer 8:"+"</td><td>"+OIUtilities.returnEmptyIfNull(objQuestion.getStrAnswer8())+"&nbsp;</td></tr>");
					stbrOut.append("<tr><td>&nbsp;</td><td>&nbsp;</td><td>"+"Answer 9:"+"</td><td>"+OIUtilities.returnEmptyIfNull(objQuestion.getStrAnswer9())+"&nbsp;</td></tr>");
					stbrOut.append("<tr><td>&nbsp;</td><td>&nbsp;</td><td>"+"Answer 10:"+"</td><td>"+OIUtilities.returnEmptyIfNull(objQuestion.getStrAnswer10())+"&nbsp;</td></tr>");
				}			
			}
		}
		stbrOut.append("</table>");
		return stbrOut.toString();
	}

	//added extra parameter "OIBASurvey objSurveyVo" by edmund
	private String getStrSurveyResponsesInfo(ArrayList alIdsList, ArrayList alResponseList,ArrayList alSectionList,Hashtable htbSecQstAns,OIBASurvey objSurveyVo) throws Exception  
	{
		StringBuffer stbrOut = new StringBuffer();
		OIBASurveyResponse objSurveyResponse = null;
		OIBAResponse objResponse = null;
		Hashtable htbUserResponses =  null;
		//added by edmund
		boolean firstAns = true;
		
		try
		{
			//Added by edmund
			stbrOut.append("Survey Name:"+OILabelConstants.TAB+objSurveyVo.getStrSurveyName()+OILabelConstants.LINE+OILabelConstants.LINE);
			stbrOut.append("Description:"+OILabelConstants.TAB+objSurveyVo.getStrDescription().replace('\n', ' ')+OILabelConstants.LINE);
			stbrOut.append("Instruction:"+OILabelConstants.TAB+OIUtilities.returnEmptyIfNull(objSurveyVo.getStrInstruction()).replace('\n', ' ')+OILabelConstants.LINE);
			stbrOut.append("Period:"+OILabelConstants.TAB+objSurveyVo.getStrFromDate()+" to "+objSurveyVo.getStrToDate()+OILabelConstants.LINE+OILabelConstants.LINE);
			//Ended by edmund
			if(alResponseList != null && alResponseList.size() > 0) 
			{
				stbrOut.append(OILabelConstants.TAB+OILabelConstants.TAB+OILabelConstants.TAB+OILabelConstants.TAB+OILabelConstants.TAB+OILabelConstants.TAB+OILabelConstants.TAB);
				//for (int j=1; j<=alIdsList.size(); j++) 
				//{
			    int jj=1;

			    StringBuffer tempStbrOut = new StringBuffer();
			    for(int i=0; i<alSectionList.size(); i++) 
			    {
			        OIBASection objSection = (OIBASection)alSectionList.get(i);
			        if(htbSecQstAns.containsKey(objSection.getStrSectionId())) 
			    	{
			            ArrayList alQuestAns = (ArrayList)htbSecQstAns.get(objSection.getStrSectionId());
			    		for(int k=0; k<alQuestAns.size(); k++) 
			    		{
			    		    OIBAQuestion objQuestion = (OIBAQuestion)alQuestAns.get(k);
			    		    //if (objQuestion.getStrAnswer1().trim().startsWith("Please use the"))
			    		    if (objQuestion.getStrQuestionType().trim().equals("T"))
			    		    {
			    		    	if(!objQuestion.getStrOtherRemarks().equalsIgnoreCase("N"))
			    		    		stbrOut.append("Q"+jj+OILabelConstants.TAB+OILabelConstants.TAB);
			    		    	else
			    		    		stbrOut.append("Q"+jj+OILabelConstants.TAB);
			    		        //changed by edmund
			    		        tempStbrOut.append("Answer"+OILabelConstants.TAB);
			    		        /*tempStbrOut.append("R2"+OILabelConstants.TAB);
			    		        tempStbrOut.append("R3"+OILabelConstants.TAB);
			    		        tempStbrOut.append("R4"+OILabelConstants.TAB);
			    		        tempStbrOut.append("R5"+OILabelConstants.TAB);
			    		        tempStbrOut.append("R6"+OILabelConstants.TAB);
			    		        tempStbrOut.append("R7"+OILabelConstants.TAB);
			    		        tempStbrOut.append("R8"+OILabelConstants.TAB);
			    		        tempStbrOut.append("R9"+OILabelConstants.TAB);
			    		        tempStbrOut.append("R10"+OILabelConstants.TAB);
			    		        */
			    		    }
			    		    else
			    		    {
			    		    	if(!objQuestion.getStrOtherRemarks().equalsIgnoreCase("N"))
			    		    		stbrOut.append("Q"+jj+OILabelConstants.TAB+OILabelConstants.TAB);
			    		    	else
			    		    		stbrOut.append("Q"+jj+OILabelConstants.TAB);
			    		    	
			    		        tempStbrOut.append("Answer"+OILabelConstants.TAB);
			    		      /*  if (objQuestion.getStrAnswer1() != null && ! objQuestion.getStrAnswer1().trim().equals(""))
			    		        {
			    		            stbrOut.append(OILabelConstants.TAB);
			    		            tempStbrOut.append("R1"+OILabelConstants.TAB);
			    		        }
			    		        if (objQuestion.getStrAnswer2() != null && ! objQuestion.getStrAnswer2().trim().equals(""))
			    		        {
			    		            stbrOut.append(OILabelConstants.TAB);
			    		            tempStbrOut.append("R2"+OILabelConstants.TAB);
			    		        }
			    		        if (objQuestion.getStrAnswer3() != null && ! objQuestion.getStrAnswer3().trim().equals(""))
			    		        {
			    		            stbrOut.append(OILabelConstants.TAB);
			    		            tempStbrOut.append("R3"+OILabelConstants.TAB);
			    		        }
			    		        if (objQuestion.getStrAnswer4() != null && ! objQuestion.getStrAnswer4().trim().equals(""))
			    		        {
			    		            stbrOut.append(OILabelConstants.TAB);
			    		            tempStbrOut.append("R4"+OILabelConstants.TAB);
			    		        }
			    		        if (objQuestion.getStrAnswer5() != null && ! objQuestion.getStrAnswer5().trim().equals(""))
			    		        {
			    		            stbrOut.append(OILabelConstants.TAB);
			    		            tempStbrOut.append("R5"+OILabelConstants.TAB);
			    		        }
			    		        if (objQuestion.getStrAnswer6() != null && ! objQuestion.getStrAnswer6().trim().equals(""))
			    		        {
			    		            stbrOut.append(OILabelConstants.TAB);
			    		            tempStbrOut.append("R6"+OILabelConstants.TAB);
			    		        }
			    		        if (objQuestion.getStrAnswer7() != null && ! objQuestion.getStrAnswer7().trim().equals(""))
			    		        {
			    		            stbrOut.append(OILabelConstants.TAB);
			    		            tempStbrOut.append("R7"+OILabelConstants.TAB);
			    		        }
			    		        if (objQuestion.getStrAnswer8() != null && ! objQuestion.getStrAnswer8().trim().equals(""))
			    		        {
			    		            stbrOut.append(OILabelConstants.TAB);
			    		            tempStbrOut.append("R8"+OILabelConstants.TAB);
			    		        }
			    		        if (objQuestion.getStrAnswer9() != null && ! objQuestion.getStrAnswer9().trim().equals(""))
			    		        {
			    		            stbrOut.append(OILabelConstants.TAB);
			    		            tempStbrOut.append("R9"+OILabelConstants.TAB);
			    		        }
			    		        if (objQuestion.getStrAnswer10() != null && ! objQuestion.getStrAnswer10().trim().equals(""))
			    		        {
			    		            stbrOut.append(OILabelConstants.TAB);
			    		            tempStbrOut.append("R10"+OILabelConstants.TAB);
			    		        }
			    		        //stbrOut.append(OILabelConstants.TAB);
			    		         * 
			    		         */
			    		    }
			    		    if(!objQuestion.getStrOtherRemarks().equalsIgnoreCase("N"))
			    		    	tempStbrOut.append("Remarks"+OILabelConstants.TAB);
				    		jj++;
			    		}
			    	}
			    }
					
				//}
			    //Added by edmund
				stbrOut.append(OILabelConstants.LINE);
				//stbrOut.append("Name"+OILabelConstants.TAB + "Age"+OILabelConstants.TAB + "Years in Service"+OILabelConstants.TAB + "Email Address"+OILabelConstants.TAB + "Designation"+OILabelConstants.TAB + "Division Name"+OILabelConstants.TAB+"School Name"+OILabelConstants.TAB+ "SchoolLevel"+OILabelConstants.TAB );
				stbrOut.append(OILabelConstants.TAB + "Age"+OILabelConstants.TAB + "Years in Service"+OILabelConstants.TAB + "Designation"+OILabelConstants.TAB + "Division Name"+OILabelConstants.TAB+"School Name"+OILabelConstants.TAB+ "SchoolLevel"+OILabelConstants.TAB );
				//ended by edmund
				stbrOut.append(tempStbrOut.toString());
				for (int j=1; j<=alIdsList.size(); j++)
				{
					for (int k=1; k<=10; k++)
					{
						//stbrOut.append("R"+k+OILabelConstants.TAB);
					}
					//stbrOut.append("Remarks"+OILabelConstants.TAB);
				}
				stbrOut.append(OILabelConstants.LINE);
				//logger.info("Total Size-" + alResponseList.size());
				for(int i=0; i<alResponseList.size(); i++) 
				{
    		        //logger.info("Raj-1");
				    objSurveyResponse = (OIBASurveyResponse) alResponseList.get(i);
				    //Added by edmund
					//stbrOut.append(OIUtilities.returnEmptyIfNull(objSurveyResponse.getStrNickName())+OILabelConstants.TAB+OIUtilities.returnEmptyIfNull(objSurveyResponse.getAge())+OILabelConstants.TAB+OIUtilities.returnEmptyIfNull(objSurveyResponse.getService())+OILabelConstants.TAB+OIUtilities.returnEmptyIfNull(objSurveyResponse.getEmail())+OILabelConstants.TAB+OIUtilities.returnEmptyIfNull(objSurveyResponse.getDesignation())+OILabelConstants.TAB+OIUtilities.returnEmptyIfNull(objSurveyResponse.getStrDivisioncode())+OILabelConstants.TAB+OIUtilities.returnEmptyIfNull(objSurveyResponse.getStrSchoolCode())+OILabelConstants.TAB+OIUtilities.returnEmptyIfNull(objSurveyResponse.getSchoolLevel())+OILabelConstants.TAB);
					stbrOut.append(OILabelConstants.TAB+OIUtilities.returnEmptyIfNull(objSurveyResponse.getAge())+OILabelConstants.TAB+OIUtilities.returnEmptyIfNull(objSurveyResponse.getService())+OILabelConstants.TAB+ OIUtilities.returnEmptyIfNull(objSurveyResponse.getDesignation())+OILabelConstants.TAB+OIUtilities.returnEmptyIfNull(objSurveyResponse.getStrDivisioncode())+OILabelConstants.TAB+OIUtilities.returnEmptyIfNull(objSurveyResponse.getStrSchoolCode())+OILabelConstants.TAB+OIUtilities.returnEmptyIfNull(objSurveyResponse.getSchoolLevel())+OILabelConstants.TAB);
					//ended by edmund
					htbUserResponses = objSurveyResponse.getHtbUserResponse();
				    for(int j=0; j<alSectionList.size(); j++) 
				    {
	    		        //logger.info("Raj-2");
				        OIBASection objSection = (OIBASection)alSectionList.get(j);
				        if(htbSecQstAns.containsKey(objSection.getStrSectionId())) 
				    	{
				            ArrayList alQuestAns = (ArrayList)htbSecQstAns.get(objSection.getStrSectionId());
		    		        //logger.info("Raj-3");
				    		for(int k=0; k<alQuestAns.size(); k++) 
				    		{
				    		    OIBAQuestion objQuestion = (OIBAQuestion)alQuestAns.get(k);
			    		        //logger.info("Raj-4");

								//for (int j=0; j<alIdsList.size(); j++) 
								//{
									objResponse = (OIBAResponse)htbUserResponses.get(objQuestion.getStrQuestionId());
									if(objResponse == null) 
									{
									    objResponse = new OIBAResponse();
									}
				
									//stbrOut.append(OIUtilities.returnEmptyIfNull(objResponse.getStrQuestionId() + "-" + objResponse.getStrResponse1())+OILabelConstants.TAB);
					    		   // if (objQuestion.getStrQuestionType().trim().equals("T"))
					    		   // {
					    		    	//added by edmund
					    		    	String strTemp = new String();
					    		    	
					    		    	// START ADDED BY OSCAR 27 JUN 07					    		
					    		    						    		    	
					    		    	strTemp += OIUtilities.returnWithSeparate((objResponse.getStrResponse1() == null || objResponse.getStrResponse1().trim().length() == 0 || objResponse.getStrResponse1().trim().equalsIgnoreCase("null"))?objResponse.getStrResponse1():("[Answer 1]" + objResponse.getStrResponse1()), strTemp);
					    		    	strTemp += OIUtilities.returnWithSeparate((objResponse.getStrResponse2() == null || objResponse.getStrResponse2().trim().length() == 0 || objResponse.getStrResponse2().trim().equalsIgnoreCase("null"))?objResponse.getStrResponse2():("[Answer 2]" + objResponse.getStrResponse2()), strTemp);
					    		    	strTemp += OIUtilities.returnWithSeparate((objResponse.getStrResponse3() == null || objResponse.getStrResponse3().trim().length() == 0 || objResponse.getStrResponse3().trim().equalsIgnoreCase("null"))?objResponse.getStrResponse3():("[Answer 3]" + objResponse.getStrResponse3()), strTemp);
					    		    	strTemp += OIUtilities.returnWithSeparate((objResponse.getStrResponse4() == null || objResponse.getStrResponse4().trim().length() == 0 || objResponse.getStrResponse4().trim().equalsIgnoreCase("null"))?objResponse.getStrResponse4():("[Answer 4]" + objResponse.getStrResponse4()), strTemp);
					    		    	strTemp += OIUtilities.returnWithSeparate((objResponse.getStrResponse5() == null || objResponse.getStrResponse5().trim().length() == 0 || objResponse.getStrResponse5().trim().equalsIgnoreCase("null"))?objResponse.getStrResponse5():("[Answer 5]" + objResponse.getStrResponse5()), strTemp);
					    		    	strTemp += OIUtilities.returnWithSeparate((objResponse.getStrResponse6() == null || objResponse.getStrResponse6().trim().length() == 0 || objResponse.getStrResponse6().trim().equalsIgnoreCase("null"))?objResponse.getStrResponse6():("[Answer 6]" + objResponse.getStrResponse6()), strTemp);
					    		    	strTemp += OIUtilities.returnWithSeparate((objResponse.getStrResponse7() == null || objResponse.getStrResponse7().trim().length() == 0 || objResponse.getStrResponse7().trim().equalsIgnoreCase("null"))?objResponse.getStrResponse7():("[Answer 7]" + objResponse.getStrResponse7()), strTemp);
					    		    	strTemp += OIUtilities.returnWithSeparate((objResponse.getStrResponse8() == null || objResponse.getStrResponse8().trim().length() == 0 || objResponse.getStrResponse8().trim().equalsIgnoreCase("null"))?objResponse.getStrResponse8():("[Answer 8]" + objResponse.getStrResponse8()), strTemp);
					    		    	strTemp += OIUtilities.returnWithSeparate((objResponse.getStrResponse9() == null || objResponse.getStrResponse9().trim().length() == 0 || objResponse.getStrResponse9().trim().equalsIgnoreCase("null"))?objResponse.getStrResponse9():("[Answer 9]" + objResponse.getStrResponse9()), strTemp);
					    		    	strTemp += OIUtilities.returnWithSeparate((objResponse.getStrResponse10() == null || objResponse.getStrResponse10().trim().length() == 0 || objResponse.getStrResponse10().trim().equalsIgnoreCase("null"))?objResponse.getStrResponse10():("[Answer 10]" + objResponse.getStrResponse10()), strTemp);
					    		    	// END ADDED BY OSCAR					    		    	
					    		    					    		    	
//					    		    	strTemp += OIUtilities.returnWithSeparate(objResponse.getStrResponse1(), strTemp);
//					    		    	strTemp += OIUtilities.returnWithSeparate(objResponse.getStrResponse2(), strTemp);
//					    		    	strTemp += OIUtilities.returnWithSeparate(objResponse.getStrResponse3(), strTemp);
//					    		    	strTemp += OIUtilities.returnWithSeparate(objResponse.getStrResponse4(), strTemp);
//					    		    	strTemp += OIUtilities.returnWithSeparate(objResponse.getStrResponse5(), strTemp);
//					    		    	strTemp += OIUtilities.returnWithSeparate(objResponse.getStrResponse6(), strTemp);
//					    		    	strTemp += OIUtilities.returnWithSeparate(objResponse.getStrResponse7(), strTemp);
//					    		    	strTemp += OIUtilities.returnWithSeparate(objResponse.getStrResponse8(), strTemp);
//					    		    	strTemp += OIUtilities.returnWithSeparate(objResponse.getStrResponse9(), strTemp);
//					    		    	strTemp += OIUtilities.returnWithSeparate(objResponse.getStrResponse10(), strTemp);
					    		    	
//					    		    	strTemp += (objResponse.getStrResponse1() == null || objResponse.getStrResponse1().trim().length() == 0 || objResponse.getStrResponse1().trim().equalsIgnoreCase("null"))?"":("[Answer 1] " + objResponse.getStrResponse1().trim() + "\n");
//					    		    	strTemp += (objResponse.getStrResponse2() == null || objResponse.getStrResponse2().trim().length() == 0 || objResponse.getStrResponse2().trim().equalsIgnoreCase("null"))?"":("[Answer 2] " + objResponse.getStrResponse2().trim() + "\n");
//					    		    	strTemp += (objResponse.getStrResponse3() == null || objResponse.getStrResponse3().trim().length() == 0 || objResponse.getStrResponse3().trim().equalsIgnoreCase("null"))?"":("[Answer 3] " + objResponse.getStrResponse3().trim() + "\n");
//					    		    	strTemp += (objResponse.getStrResponse4() == null || objResponse.getStrResponse4().trim().length() == 0 || objResponse.getStrResponse4().trim().equalsIgnoreCase("null"))?"":("[Answer 4] " + objResponse.getStrResponse4().trim() + "\n");
//					    		    	strTemp += (objResponse.getStrResponse5() == null || objResponse.getStrResponse5().trim().length() == 0 || objResponse.getStrResponse5().trim().equalsIgnoreCase("null"))?"":("[Answer 5] " + objResponse.getStrResponse5().trim() + "\n");
//					    		    	strTemp += (objResponse.getStrResponse6() == null || objResponse.getStrResponse6().trim().length() == 0 || objResponse.getStrResponse6().trim().equalsIgnoreCase("null"))?"":("[Answer 6] " + objResponse.getStrResponse6().trim() + "\n");
//					    		    	strTemp += (objResponse.getStrResponse7() == null || objResponse.getStrResponse7().trim().length() == 0 || objResponse.getStrResponse7().trim().equalsIgnoreCase("null"))?"":("[Answer 7] " + objResponse.getStrResponse7().trim() + "\n");
//					    		    	strTemp += (objResponse.getStrResponse8() == null || objResponse.getStrResponse8().trim().length() == 0 || objResponse.getStrResponse8().trim().equalsIgnoreCase("null"))?"":("[Answer 8] " + objResponse.getStrResponse8().trim() + "\n");
//					    		    	strTemp += (objResponse.getStrResponse9() == null || objResponse.getStrResponse9().trim().length() == 0 || objResponse.getStrResponse9().trim().equalsIgnoreCase("null"))?"":("[Answer 9] " + objResponse.getStrResponse9().trim() + "\n");
//					    		    	strTemp += (objResponse.getStrResponse10() == null || objResponse.getStrResponse10().trim().length() == 0 || objResponse.getStrResponse10().trim().equalsIgnoreCase("null"))?"":("[Answer 10] " + objResponse.getStrResponse10().trim() + "\n");
					    		    	
					    		    	if(strTemp.indexOf(",") == 0){
					    		    		strTemp = strTemp.substring(1, strTemp.length());
					    		    	}
					    		    	strTemp  = "\"" + strTemp + "\"";
					    		    	stbrOut.append(strTemp);
										/*stbrOut.append(OIUtilities.returnEmptyIfNull(objResponse.getStrResponse1()));
										stbrOut.append(OIUtilities.returnEmptyIfNull(objResponse.getStrResponse2()));
										stbrOut.append(OIUtilities.returnEmptyIfNull(objResponse.getStrResponse3()));
										stbrOut.append(OIUtilities.returnEmptyIfNull(objResponse.getStrResponse4()));
										stbrOut.append(OIUtilities.returnEmptyIfNull(objResponse.getStrResponse5()));
										stbrOut.append(OIUtilities.returnEmptyIfNull(objResponse.getStrResponse6()));
										stbrOut.append(OIUtilities.returnEmptyIfNull(objResponse.getStrResponse7()));
										stbrOut.append(OIUtilities.returnEmptyIfNull(objResponse.getStrResponse8()));
										stbrOut.append(OIUtilities.returnEmptyIfNull(objResponse.getStrResponse9()));
										stbrOut.append(OIUtilities.returnEmptyIfNull(objResponse.getStrResponse10()));*/
					    		   // }
					    		   /* else
					    		    {
					    		        if (objQuestion.getStrAnswer1() != null && ! objQuestion.getStrAnswer1().trim().equals(""))
					    		        {
					    		        	if(firstAns && objResponse.getStrResponse1() != null){
					    		        		stbrOut.append(OIUtilities.returnEmptyIfNull(objResponse.getStrResponse1()));			
					    		        		firstAns = false;
					    		        	}
					    		        	else if(objResponse.getStrResponse1() != null){
					    		        		stbrOut.append(","+OIUtilities.returnEmptyIfNull(objResponse.getStrResponse1()));								    		       
					    		        	}
						    		        //logger.info("Answer-1" + OIUtilities.returnEmptyIfNull(objResponse.getStrResponse1()));
					    		        }
					    		        if (objQuestion.getStrAnswer2() != null && ! objQuestion.getStrAnswer2().trim().equals(""))
					    		        {
					    		        	if(firstAns && objResponse.getStrResponse2() != null){
					    		        		stbrOut.append(OIUtilities.returnEmptyIfNull(objResponse.getStrResponse2()));			
					    		        		firstAns = false;
					    		        	}
					    		        	else if(objResponse.getStrResponse2() != null){
					    		        		stbrOut.append(","+OIUtilities.returnEmptyIfNull(objResponse.getStrResponse2()));								    		       
					    		        	}
											//stbrOut.append(strSeparate+OIUtilities.returnEmptyIfNull(objResponse.getStrResponse2()));
						    		        //logger.info("Answer-2" + OIUtilities.returnEmptyIfNull(objResponse.getStrResponse2()));
					    		        }
					    		        if (objQuestion.getStrAnswer3() != null && ! objQuestion.getStrAnswer3().trim().equals(""))
					    		        {
					    		        	if(firstAns && objResponse.getStrResponse3() != null){
					    		        		stbrOut.append(OIUtilities.returnEmptyIfNull(objResponse.getStrResponse3()));			
					    		        		firstAns = false;
					    		        	}
					    		        	else if(objResponse.getStrResponse3() != null){
					    		        		stbrOut.append(","+OIUtilities.returnEmptyIfNull(objResponse.getStrResponse3()));								    		       
					    		        	}
					    		        	//logger.info("Answer-3" + OIUtilities.returnEmptyIfNull(objResponse.getStrResponse3()));
					    		        }
					    		        if (objQuestion.getStrAnswer4() != null && ! objQuestion.getStrAnswer4().trim().equals(""))
					    		        {
					    		        	if(firstAns && objResponse.getStrResponse4() != null){
					    		        		stbrOut.append(OIUtilities.returnEmptyIfNull(objResponse.getStrResponse4()));			
					    		        		firstAns = false;
					    		        	}
					    		        	else if(objResponse.getStrResponse4() != null){
					    		        		stbrOut.append(","+OIUtilities.returnEmptyIfNull(objResponse.getStrResponse4()));								    		       
					    		        	}
					    		        	//logger.info("Answer-4" + OIUtilities.returnEmptyIfNull(objResponse.getStrResponse4()));
					    		        }
					    		        if (objQuestion.getStrAnswer5() != null && ! objQuestion.getStrAnswer5().trim().equals(""))
					    		        {
					    		        	if(firstAns && objResponse.getStrResponse5() != null){
					    		        		stbrOut.append(OIUtilities.returnEmptyIfNull(objResponse.getStrResponse5()));			
					    		        		firstAns = false;
					    		        	}
					    		        	else if(objResponse.getStrResponse5() != null){
					    		        		stbrOut.append(","+OIUtilities.returnEmptyIfNull(objResponse.getStrResponse5()));								    		       
					    		        	}
					    		        	//logger.info("Answer-5" + OIUtilities.returnEmptyIfNull(objResponse.getStrResponse5()));
					    		        }
					    		        if (objQuestion.getStrAnswer6() != null && ! objQuestion.getStrAnswer6().trim().equals(""))
					    		        {
					    		        	if(firstAns && objResponse.getStrResponse6() != null){
					    		        		stbrOut.append(OIUtilities.returnEmptyIfNull(objResponse.getStrResponse6()));			
					    		        		firstAns = false;
					    		        	}
					    		        	else if(objResponse.getStrResponse6() != null){
					    		        		stbrOut.append(","+OIUtilities.returnEmptyIfNull(objResponse.getStrResponse6()));								    		       
					    		        	}
					    		        	//logger.info("Answer-6" + OIUtilities.returnEmptyIfNull(objResponse.getStrResponse6()));
					    		        }
					    		        if (objQuestion.getStrAnswer7() != null && ! objQuestion.getStrAnswer7().trim().equals(""))
					    		        {
					    		        	if(firstAns && objResponse.getStrResponse7() != null){
					    		        		stbrOut.append(OIUtilities.returnEmptyIfNull(objResponse.getStrResponse7()));			
					    		        		firstAns = false;
					    		        	}
					    		        	else if(objResponse.getStrResponse7() != null){
					    		        		stbrOut.append(","+OIUtilities.returnEmptyIfNull(objResponse.getStrResponse7()));								    		       
					    		        	}
					    		        	//logger.info("Answer-7" + OIUtilities.returnEmptyIfNull(objResponse.getStrResponse7()));
					    		        }
					    		        if (objQuestion.getStrAnswer8() != null && ! objQuestion.getStrAnswer8().trim().equals(""))
					    		        {
					    		        	if(firstAns && objResponse.getStrResponse8() != null){
					    		        		stbrOut.append(OIUtilities.returnEmptyIfNull(objResponse.getStrResponse8()));			
					    		        		firstAns = false;
					    		        	}
					    		        	else if(objResponse.getStrResponse8() != null){
					    		        		stbrOut.append(","+OIUtilities.returnEmptyIfNull(objResponse.getStrResponse8()));								    		       
					    		        	}
					    		        	//logger.info("Answer-8" + OIUtilities.returnEmptyIfNull(objResponse.getStrResponse8()));
					    		        }
					    		        if (objQuestion.getStrAnswer9() != null && ! objQuestion.getStrAnswer9().trim().equals(""))
					    		        {
					    		        	if(firstAns && objResponse.getStrResponse9() != null){
					    		        		stbrOut.append(OIUtilities.returnEmptyIfNull(objResponse.getStrResponse9()));			
					    		        		firstAns = false;
					    		        	}
					    		        	else if(objResponse.getStrResponse9() != null){
					    		        		stbrOut.append(","+OIUtilities.returnEmptyIfNull(objResponse.getStrResponse9()));								    		       
					    		        	}
					    		        	//logger.info("Answer-9" + OIUtilities.returnEmptyIfNull(objResponse.getStrResponse9()));
					    		        }
					    		        if (objQuestion.getStrAnswer10() != null && ! objQuestion.getStrAnswer10().trim().equals(""))
					    		        {
					    		        	if(firstAns && objResponse.getStrResponse10() != null){
					    		        		stbrOut.append(OIUtilities.returnEmptyIfNull(objResponse.getStrResponse10()));			
					    		        		firstAns = false;
					    		        	}
					    		        	else if(objResponse.getStrResponse10() != null){
					    		        		stbrOut.append(","+OIUtilities.returnEmptyIfNull(objResponse.getStrResponse10()));								    		       
					    		        	}
					    		        	//logger.info("Answer-10" + OIUtilities.returnEmptyIfNull(objResponse.getStrResponse10()));
					    		        }
					    		    }*/
					    		    firstAns = true;
					    		    if(!objQuestion.getStrOtherRemarks().equalsIgnoreCase("N"))
					    		    	stbrOut.append(OILabelConstants.TAB+OIUtilities.returnEmptyIfNull(objResponse.getStrOtherRemarks())+OILabelConstants.TAB);
									else
										stbrOut.append(OILabelConstants.TAB);
								//}
				    		}
				    	}
				    }
					stbrOut.append(OILabelConstants.LINE);
				}
			}
		}catch(Exception e)
		{
		    logger.error("getStrSurveyResponsesInfo => " + e);
		    throw e;
		}
		return stbrOut.toString();
	}

	private ArrayList getStrSurveyResponsesInfoHtml(ArrayList alIdsList, ArrayList alResponseList,ArrayList alSectionList,Hashtable htbSecQstAns) throws Exception  
	{
		//StringBuffer stbrOut = new StringBuffer();
		OIBASurveyResponse objSurveyResponse = null;
		OIBAResponse objResponse = null;
		Hashtable htbUserResponses =  null;
		ArrayList alQuestAnsList = new ArrayList();

		try
		{
			if(alResponseList != null && alResponseList.size() > 0) 
			{
				//stbrOut.append("<table border=1>");
				//stbrOut.append(OILabelConstants.TABCOL+OILabelConstants.TABCOL+OILabelConstants.TABCOL+OILabelConstants.TABCOL+OILabelConstants.TABCOL+OILabelConstants.TABCOL+OILabelConstants.TABCOL+OILabelConstants.TABCOL);
				//for (int j=1; j<=alIdsList.size(); j++) 
				//{
			    int jj=1;

			    StringBuffer tempStbrOut = new StringBuffer();
				
				    for(int j=0; j<alSectionList.size(); j++) 
				    {
	    		        //logger.info("Raj-2");
				        OIBASection objSection = (OIBASection)alSectionList.get(j);
				        if(htbSecQstAns.containsKey(objSection.getStrSectionId())) 
				    	{
							
				            ArrayList alQuestAns = (ArrayList)htbSecQstAns.get(objSection.getStrSectionId());
		    		        //logger.info("Raj-3");
				    		for(int k=0; k<alQuestAns.size(); k++) 
				    		{
								alQuestAnsList.add((OIBAQuestion)alQuestAns.get(k));
				    		   
				    		}
				    	}
				    }
				//return alQuestAnsList;
				/*int endCount = alQuestAnsList.size()>20?20:alQuestAnsList.size();
			    
			    		for(int k=0; k<endCount; k++) 
			    		{
			    		    OIBAQuestion objQuestion = (OIBAQuestion)alQuestAnsList.get(k);
			    		    //if (objQuestion.getStrAnswer1().trim().startsWith("Please use the"))
			    		    if (objQuestion.getStrQuestionType().trim().equals("T"))
			    		    {
			    		        stbrOut.append("Q"+jj+OILabelConstants.TABCOL+OILabelConstants.TABCOL+OILabelConstants.TABCOL+OILabelConstants.TABCOL+OILabelConstants.TABCOL+OILabelConstants.TABCOL+OILabelConstants.TABCOL+OILabelConstants.TABCOL+OILabelConstants.TABCOL+OILabelConstants.TABCOL+OILabelConstants.TABCOL);
			    		        tempStbrOut.append("R1"+OILabelConstants.TABCOL);
			    		        tempStbrOut.append("R2"+OILabelConstants.TABCOL);
			    		        tempStbrOut.append("R3"+OILabelConstants.TABCOL);
			    		        tempStbrOut.append("R4"+OILabelConstants.TABCOL);
			    		        tempStbrOut.append("R5"+OILabelConstants.TABCOL);
			    		        tempStbrOut.append("R6"+OILabelConstants.TABCOL);
			    		        tempStbrOut.append("R7"+OILabelConstants.TABCOL);
			    		        tempStbrOut.append("R8"+OILabelConstants.TABCOL);
			    		        tempStbrOut.append("R9"+OILabelConstants.TABCOL);
			    		        tempStbrOut.append("R10"+OILabelConstants.TABCOL);
			    		    }
			    		    else
			    		    {
			    		        stbrOut.append("Q"+jj+OILabelConstants.TABCOL);
			    		        if (objQuestion.getStrAnswer1() != null && ! objQuestion.getStrAnswer1().trim().equals(""))
			    		        {
			    		            stbrOut.append(OILabelConstants.TABCOL);
			    		            tempStbrOut.append("R1"+OILabelConstants.TABCOL);
			    		        }
			    		        if (objQuestion.getStrAnswer2() != null && ! objQuestion.getStrAnswer2().trim().equals(""))
			    		        {
			    		            stbrOut.append(OILabelConstants.TABCOL);
			    		            tempStbrOut.append("R2"+OILabelConstants.TABCOL);
			    		        }
			    		        if (objQuestion.getStrAnswer3() != null && ! objQuestion.getStrAnswer3().trim().equals(""))
			    		        {
			    		            stbrOut.append(OILabelConstants.TABCOL);
			    		            tempStbrOut.append("R3"+OILabelConstants.TABCOL);
			    		        }
			    		        if (objQuestion.getStrAnswer4() != null && ! objQuestion.getStrAnswer4().trim().equals(""))
			    		        {
			    		            stbrOut.append(OILabelConstants.TABCOL);
			    		            tempStbrOut.append("R4"+OILabelConstants.TABCOL);
			    		        }
			    		        if (objQuestion.getStrAnswer5() != null && ! objQuestion.getStrAnswer5().trim().equals(""))
			    		        {
			    		            stbrOut.append(OILabelConstants.TABCOL);
			    		            tempStbrOut.append("R5"+OILabelConstants.TABCOL);
			    		        }
			    		        if (objQuestion.getStrAnswer6() != null && ! objQuestion.getStrAnswer6().trim().equals(""))
			    		        {
			    		            stbrOut.append(OILabelConstants.TABCOL);
			    		            tempStbrOut.append("R6"+OILabelConstants.TABCOL);
			    		        }
			    		        if (objQuestion.getStrAnswer7() != null && ! objQuestion.getStrAnswer7().trim().equals(""))
			    		        {
			    		            stbrOut.append(OILabelConstants.TABCOL);
			    		            tempStbrOut.append("R7"+OILabelConstants.TABCOL);
			    		        }
			    		        if (objQuestion.getStrAnswer8() != null && ! objQuestion.getStrAnswer8().trim().equals(""))
			    		        {
			    		            stbrOut.append(OILabelConstants.TABCOL);
			    		            tempStbrOut.append("R8"+OILabelConstants.TABCOL);
			    		        }
			    		        if (objQuestion.getStrAnswer9() != null && ! objQuestion.getStrAnswer9().trim().equals(""))
			    		        {
			    		            stbrOut.append(OILabelConstants.TABCOL);
			    		            tempStbrOut.append("R9"+OILabelConstants.TABCOL);
			    		        }
			    		        if (objQuestion.getStrAnswer10() != null && ! objQuestion.getStrAnswer10().trim().equals(""))
			    		        {
			    		            stbrOut.append(OILabelConstants.TABCOL);
			    		            tempStbrOut.append("R10"+OILabelConstants.TABCOL);
			    		        }
			    		        //stbrOut.append(OILabelConstants.TABCOL);
			    		    }
			    		    tempStbrOut.append("Remarks"+OILabelConstants.TABCOL);
				    		jj++;
			    		}
			    	*/
					
				//}
				/*stbrOut.append(OILabelConstants.TABROW);
				stbrOut.append("Name"+OILabelConstants.TABCOL + "Age"+OILabelConstants.TABCOL + "Years in Service"+OILabelConstants.TABCOL + "Email Address"+OILabelConstants.TABCOL + "Designation"+OILabelConstants.TABCOL + "Division Name"+OILabelConstants.TABCOL+"School Name"+OILabelConstants.TABCOL+ "SchoolLevel"+OILabelConstants.TABCOL );
				stbrOut.append(tempStbrOut.toString());
				for (int j=1; j<=alIdsList.size(); j++)
				{
					for (int k=1; k<=10; k++)
					{
						//stbrOut.append("R"+k+OILabelConstants.TABCOL);
					}
					//stbrOut.append("Remarks"+OILabelConstants.TABCOL);
				}
				stbrOut.append(OILabelConstants.TABROW);
				//logger.info("Total Size-" + alResponseList.size());
				for(int i=0; i<alResponseList.size(); i++) 
				{
    		        //logger.info("Raj-1");
				    objSurveyResponse = (OIBASurveyResponse) alResponseList.get(i);
					stbrOut.append(OIUtilities.returnEmptyIfNull(objSurveyResponse.getStrNickName())+OILabelConstants.TABCOL+OIUtilities.returnEmptyIfNull(objSurveyResponse.getAge())+OILabelConstants.TABCOL+OIUtilities.returnEmptyIfNull(objSurveyResponse.getService())+OILabelConstants.TABCOL+OIUtilities.returnEmptyIfNull(objSurveyResponse.getEmail())+OILabelConstants.TABCOL+OIUtilities.returnEmptyIfNull(objSurveyResponse.getDesignation())+OILabelConstants.TABCOL+OIUtilities.returnEmptyIfNull(objSurveyResponse.getStrDivisioncode())+OILabelConstants.TABCOL+OIUtilities.returnEmptyIfNull(objSurveyResponse.getStrSchoolCode())+OILabelConstants.TABCOL+OIUtilities.returnEmptyIfNull(objSurveyResponse.getSchoolLevel())+OILabelConstants.TABCOL);
					htbUserResponses = objSurveyResponse.getHtbUserResponse();
					
					System.out.println("alQuestAnsList.size()=="+alQuestAnsList.size());
					for(int k=0; k<endCount; k++) 
					{
					 OIBAQuestion objQuestion = (OIBAQuestion)alQuestAnsList.get(k);
			    		        //logger.info("Raj-4");

								//for (int j=0; j<alIdsList.size(); j++) 
								//{
									objResponse = (OIBAResponse)htbUserResponses.get(objQuestion.getStrQuestionId());
									if(objResponse == null) 
									{
									    objResponse = new OIBAResponse();
									}
				
									//stbrOut.append(OIUtilities.returnEmptyIfNull(objResponse.getStrQuestionId() + "-" + objResponse.getStrResponse1())+OILabelConstants.TABCOL);
					    		    if (objQuestion.getStrQuestionType().trim().equals("T"))
					    		    {
										stbrOut.append(OIUtilities.returnEmptyIfNull(objResponse.getStrResponse1())+OILabelConstants.TABCOL);
										stbrOut.append(OIUtilities.returnEmptyIfNull(objResponse.getStrResponse2())+OILabelConstants.TABCOL);
										stbrOut.append(OIUtilities.returnEmptyIfNull(objResponse.getStrResponse3())+OILabelConstants.TABCOL);
										stbrOut.append(OIUtilities.returnEmptyIfNull(objResponse.getStrResponse4())+OILabelConstants.TABCOL);
										stbrOut.append(OIUtilities.returnEmptyIfNull(objResponse.getStrResponse5())+OILabelConstants.TABCOL);
										stbrOut.append(OIUtilities.returnEmptyIfNull(objResponse.getStrResponse6())+OILabelConstants.TABCOL);
										stbrOut.append(OIUtilities.returnEmptyIfNull(objResponse.getStrResponse7())+OILabelConstants.TABCOL);
										stbrOut.append(OIUtilities.returnEmptyIfNull(objResponse.getStrResponse8())+OILabelConstants.TABCOL);
										stbrOut.append(OIUtilities.returnEmptyIfNull(objResponse.getStrResponse9())+OILabelConstants.TABCOL);
										stbrOut.append(OIUtilities.returnEmptyIfNull(objResponse.getStrResponse10())+OILabelConstants.TABCOL);
					    		    }
					    		    else
					    		    {
					    		        if (objQuestion.getStrAnswer1() != null && ! objQuestion.getStrAnswer1().trim().equals(""))
					    		        {
											stbrOut.append(OIUtilities.returnEmptyIfNull(objResponse.getStrResponse1())+OILabelConstants.TABCOL);
						    		        //logger.info("Answer-1" + OIUtilities.returnEmptyIfNull(objResponse.getStrResponse1()));
					    		        }
					    		        if (objQuestion.getStrAnswer2() != null && ! objQuestion.getStrAnswer2().trim().equals(""))
					    		        {
											stbrOut.append(OIUtilities.returnEmptyIfNull(objResponse.getStrResponse2())+OILabelConstants.TABCOL);
						    		        //logger.info("Answer-2" + OIUtilities.returnEmptyIfNull(objResponse.getStrResponse2()));
					    		        }
					    		        if (objQuestion.getStrAnswer3() != null && ! objQuestion.getStrAnswer3().trim().equals(""))
					    		        {
											stbrOut.append(OIUtilities.returnEmptyIfNull(objResponse.getStrResponse3())+OILabelConstants.TABCOL);
						    		        //logger.info("Answer-3" + OIUtilities.returnEmptyIfNull(objResponse.getStrResponse3()));
					    		        }
					    		        if (objQuestion.getStrAnswer4() != null && ! objQuestion.getStrAnswer4().trim().equals(""))
					    		        {
											stbrOut.append(OIUtilities.returnEmptyIfNull(objResponse.getStrResponse4())+OILabelConstants.TABCOL);
						    		        //logger.info("Answer-4" + OIUtilities.returnEmptyIfNull(objResponse.getStrResponse4()));
					    		        }
					    		        if (objQuestion.getStrAnswer5() != null && ! objQuestion.getStrAnswer5().trim().equals(""))
					    		        {
											stbrOut.append(OIUtilities.returnEmptyIfNull(objResponse.getStrResponse5())+OILabelConstants.TABCOL);
						    		        //logger.info("Answer-5" + OIUtilities.returnEmptyIfNull(objResponse.getStrResponse5()));
					    		        }
					    		        if (objQuestion.getStrAnswer6() != null && ! objQuestion.getStrAnswer6().trim().equals(""))
					    		        {
											stbrOut.append(OIUtilities.returnEmptyIfNull(objResponse.getStrResponse6())+OILabelConstants.TABCOL);
						    		        //logger.info("Answer-6" + OIUtilities.returnEmptyIfNull(objResponse.getStrResponse6()));
					    		        }
					    		        if (objQuestion.getStrAnswer7() != null && ! objQuestion.getStrAnswer7().trim().equals(""))
					    		        {
											stbrOut.append(OIUtilities.returnEmptyIfNull(objResponse.getStrResponse7())+OILabelConstants.TABCOL);
						    		        //logger.info("Answer-7" + OIUtilities.returnEmptyIfNull(objResponse.getStrResponse7()));
					    		        }
					    		        if (objQuestion.getStrAnswer8() != null && ! objQuestion.getStrAnswer8().trim().equals(""))
					    		        {
											stbrOut.append(OIUtilities.returnEmptyIfNull(objResponse.getStrResponse8())+OILabelConstants.TABCOL);
						    		        //logger.info("Answer-8" + OIUtilities.returnEmptyIfNull(objResponse.getStrResponse8()));
					    		        }
					    		        if (objQuestion.getStrAnswer9() != null && ! objQuestion.getStrAnswer9().trim().equals(""))
					    		        {
											stbrOut.append(OIUtilities.returnEmptyIfNull(objResponse.getStrResponse9())+OILabelConstants.TABCOL);
						    		        //logger.info("Answer-9" + OIUtilities.returnEmptyIfNull(objResponse.getStrResponse9()));
					    		        }
					    		        if (objQuestion.getStrAnswer10() != null && ! objQuestion.getStrAnswer10().trim().equals(""))
					    		        {
											stbrOut.append(OIUtilities.returnEmptyIfNull(objResponse.getStrResponse10())+OILabelConstants.TABCOL);
						    		        //logger.info("Answer-10" + OIUtilities.returnEmptyIfNull(objResponse.getStrResponse10()));
					    		        }
					    		    }
									stbrOut.append(OIUtilities.returnEmptyIfNull(objResponse.getStrOtherRemarks())+OILabelConstants.TABCOL);
								//}
					}
					stbrOut.append(OILabelConstants.TABROW);
				}
				stbrOut.append("</table>");*/
			}
		}catch(Exception e)
		{
		    logger.error("getStrSurveyResponsesInfoHtml => " + e);
		    throw e;
		}
		return alQuestAnsList;
	}

	private void writeContentToStream(String strOut, PrintWriter out) throws Exception 
	{
		try 
		{
			out.write(strOut);
			out.flush();
		} 
		catch(Exception ie) 
		{
		    logger.error(" writeContentToStream => "+ie);
    		throw ie;
		}
	}
	
	//added by edmund for survey respondents report
	public OIResponseObject getSurveyRespondentsList(String strSurveyId){
		OIDAOResponseAdmin objSurveyAdmin = new OIDAOResponseAdmin();
		ArrayList alRespondentsList = null;
		String strSurveyTitle = "";
		try 
		{
			getConnection();
			alRespondentsList = objSurveyAdmin.getSurveyRespondentsList(connection, strSurveyId, OISurveySqls.SURVEY_RESPONDENTS);
			strSurveyTitle = objSurveyAdmin.getSurveyTitle(connection, strSurveyId,OISurveySqls.GET_SURVEY_TITLE);
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
			addToResponseObject();
			responseObject.addResponseObject("SurveyRespondentsList", alRespondentsList);
			responseObject.addResponseObject("SurveyTitle", strSurveyTitle);
			
		}
		return responseObject;
	}

	public OIResponseObject copyFrom(Integer sourceSurveyId, String userId)
	{
		String destSurveyId = "";
		OIDAOSurveyAdmin objSurveyAdmin = new OIDAOSurveyAdmin();
		try 
		{
			getConnection();
			boolean doesSurveyExists = objSurveyAdmin.doesSurveyExists(connection, sourceSurveyId);
			//System.out.println("OIBOSurveyAdmin: copyFrom - doesSurveyExists : "+doesSurveyExists);
			if (doesSurveyExists)
			{
				destSurveyId = objSurveyAdmin.copyFrom(connection, sourceSurveyId, userId);
			}
			else
			{
				error = "OI.SURVEY.ID.INVALID";
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
			logger.error(" copyFromSurveyDetails => "+be);
		} 
		finally 
		{
			freeConnection();
			addToResponseObject(); 
            responseObject.addResponseObject("destSurveyId", destSurveyId);
            responseObject.addResponseObject("error", error);
		}
		return responseObject;
	}
	
	//added by edmund for survey demographic summary report
	public OIResponseObject getSurveySummary(String strSurveyId){
		OIDAOResponseAdmin objSurveyAdmin = new OIDAOResponseAdmin();
		ArrayList alRespondentsAge = new ArrayList();
		ArrayList alRespondentsLevel = new ArrayList();
		ArrayList alRespondentsYear = new ArrayList();
		ArrayList alRespondentsDesignation = new ArrayList();
		ArrayList alRespondentsData = new ArrayList();
		
		String strTotalUser = "1";
		String strSurveyTitle = "";
		
		try 
		{
			getConnection();
			alRespondentsAge = objSurveyAdmin.getSurveyRespondentsData(connection, strSurveyId, OISurveySqls.SURVEY_RESPONDENTS_AGE);
			alRespondentsLevel = objSurveyAdmin.getSurveyRespondentsData(connection, strSurveyId, OISurveySqls.SURVEY_RESPONDENTS_LEVEL);
			alRespondentsYear = objSurveyAdmin.getSurveyRespondentsData(connection, strSurveyId, OISurveySqls.SURVEY_RESPONDENTS_YEAR);
			alRespondentsDesignation = objSurveyAdmin.getSurveyRespondentsData(connection, strSurveyId, OISurveySqls.SURVEY_RESPONDENTS_DESIGNATION);
			
			alRespondentsData.add(alRespondentsAge);
			alRespondentsData.add(alRespondentsLevel);
			alRespondentsData.add(alRespondentsYear);
			alRespondentsData.add(alRespondentsDesignation);
			
			strTotalUser = objSurveyAdmin.getTotalUser(connection, strSurveyId, OISurveySqls.TOTAL_USER);
			strSurveyTitle = objSurveyAdmin.getSurveyTitle(connection, strSurveyId,OISurveySqls.GET_SURVEY_TITLE);
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
			responseObject.addResponseObject("SurveyDemographicSummary", alRespondentsData);
			responseObject.addResponseObject("TotalUser", strTotalUser);
			responseObject.addResponseObject("SurveyTitle", strSurveyTitle);
		}
		return responseObject;
	}
//	added by edmund
	public OIResponseObject getSurveyResultSelection(){
		OIDAOResponseAdmin objSurveyAdmin = new OIDAOResponseAdmin();
		ArrayList alRespondentsLevel = new ArrayList();
		ArrayList alRespondentsDesignation = new ArrayList();
		ArrayList alRespondentsData = new ArrayList();
		
		try 
		{
			getConnection();
			alRespondentsLevel = objSurveyAdmin.getSurveySelectionData(connection, OISurveySqls.SCHOOL_LEVEL);
			alRespondentsDesignation = objSurveyAdmin.getSurveySelectionData(connection, OISurveySqls.DESIGNATION);
			
			alRespondentsData.add(alRespondentsLevel);
			alRespondentsData.add(alRespondentsDesignation);
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
			responseObject.addResponseObject("SurveyDemographicSelection", alRespondentsData);
		}
		return responseObject;
	}
	
//	added by edmund
	public OIResponseObject getSurveyResultDetail(String strSurveyId, String strAge, String strLevel, String strDesignation, String strYear){
		OIDAOResponseAdmin objSurveyResult = new OIDAOResponseAdmin();
		OIDAOResponseAdmin objSurveyAdmin = new OIDAOResponseAdmin();
		ArrayList alReportData = new ArrayList();
		String strTotalUser = "1";
		String strSurveyTitle = "";
		Hashtable questionRespondentTable = null;
		
		String strQuery = "SELECT * FROM (";
		strQuery = strQuery + OISurveySqls.DETAIL_FRONT;
		
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
		strQuery += ") a, OI_SU_QUESTIONS b WHERE a.questionid=b.questionid "+
		"ORDER BY B.SECTIONID, NVL(QUESTIONORDER,B.QUESTIONID)";		
		
		try 
		{
			getConnection();
			alReportData =objSurveyResult.getSurveyDetailReport(connection, strSurveyId, strQuery, iCount, strDesignation, strLevel);
			strTotalUser = objSurveyResult.getTotalUser(connection, strSurveyId, OISurveySqls.TOTAL_USER);
			strSurveyTitle = objSurveyAdmin.getSurveyTitle(connection, strSurveyId,OISurveySqls.GET_SURVEY_TITLE);
			questionRespondentTable = objSurveyResult.getNumberOfQuestionRespondent(connection, strSurveyId);
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
			responseObject.addResponseObject("TotalUser", strTotalUser);
			responseObject.addResponseObject("SurveyTitle", strSurveyTitle);
			responseObject.addResponseObject("QuestionRespondents", questionRespondentTable);
		}
		return responseObject;
	}
	
//	added by edmund
	public OIResponseObject getOpenQuestion(String strQuestionId){
		OIDAOResponseAdmin objSurveyResult = new OIDAOResponseAdmin();
		ArrayList alReportData = new ArrayList();
		
		 
		String strQuestionName= ""; 
		try 
		{
			getConnection();
			strQuestionName =objSurveyResult.getOpenEndQuesitonName(connection, strQuestionId, OISurveySqls.OPEN_END_QUESTION_NAME);
			alReportData =objSurveyResult.getOpenEndQuesiton(connection, strQuestionId, OISurveySqls.OPEN_END_QUESTION);
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
			responseObject.addResponseObject("OpenEndQuestion", alReportData);
			responseObject.addResponseObject("strQuestionName", strQuestionName);
		}
		return responseObject;
	}
	
	// Added by K.K.Kumaresan on Jan 20,2008
	/**
	 * fetch External Email Address
	 */
	public String fetchExternalEmailAddress(OIBASurvey objSurveyVo)
	{
		OIDAOSurveyAdmin objSurveyAdmin = new OIDAOSurveyAdmin();
		String externalEmailAddress=null;
		logger.info("Start of fetching externalEmailAddress");
		try 
		{
			getConnection();
			if(objSurveyVo.getStrSurveyId() != null && !objSurveyVo.getStrSurveyId().equals("")) 
			{
				externalEmailAddress= objSurveyAdmin.fetchExternalEmailAddress(connection, objSurveyVo.getStrSurveyId());
				
			}
			
		} catch(Exception se) 
		{
			 logger.error("exception is"+se.getMessage());
		} 
		finally 
		{
			freeConnection();
		}
		logger.info("End of fetching externalEmailAddress");
		return externalEmailAddress;
	}
	
	//	 Added by K.K.Kumaresan on Jan 21,2008
	/**
	 * check Eligible User
	 */
	public boolean checkEligibleUser(String userId,String surveyId)
	{
		OIDAOSurveyAdmin objSurveyAdmin = new OIDAOSurveyAdmin();
		boolean eligible=true;
		logger.info("Start of checkEligibleUser");
		try 
		{
			getConnection();
			if(userId != null && !userId.equals("") && surveyId != null && !surveyId.equals("")) 
			{
				eligible= objSurveyAdmin.checkEligibleUser(connection,userId,surveyId);
				
			}
			
		} catch(Exception se) 
		{
			 logger.error("exception is"+se.getMessage());
		} 
		finally 
		{
			freeConnection();
		}
		logger.info("End of checkEligibleUser");
		return eligible;
	}
	
}
