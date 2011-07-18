package com.oifm.consultation;

/*
 * Class Name:
 * Description:
 * 
 * 	Created By			Created/Modified on			Revision				Remarks
 * -----------------------------------------------------------------------------------------------------
 * 	Rajkumar			01/08/2005					Draft					Inital Version
 * 
 * -----------------------------------------------------------------------------------------------------
 */
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseBo;
import com.oifm.common.OIBAConfiguration;
import com.oifm.common.OIDAOConfiguration;
import com.oifm.common.OIResponseObject;
import com.oifm.consultation.admin.OIBAConsultCategory;
import com.oifm.consultation.admin.OIBAConsultPaper;
import com.oifm.consultation.admin.OIDAOConsultCategory;
import com.oifm.consultation.admin.OIDAOConsultPaper;
import com.oifm.consultation.admin.OIDAOConsultQuestion;
import com.oifm.siteRanking.OIBAWebSiteRanking;
import com.oifm.siteRanking.OIDAOWebSiteRanking;
import com.oifm.useradmin.OIDAOMemberProfile;

public class OIBOConsultWeb extends OIBaseBo 
{
    Logger logger = Logger.getLogger(OIBOConsultWeb.class.getName());
    /**
     * call OIDAOConsultWeb.readAllActivePapers
     * 
     * call OIDAOConsultWeb.readAllPastPapers
     * 
     * return responseObject which consists of the above 2 ArrayLists 
     */
    public OIResponseObject readAllConsultPaper(String pUserId)
    {
        try
        {
            getConnection();
            OIDAOConsultWeb aOIDAOConsultWeb = new OIDAOConsultWeb();
            ArrayList arOIBVPaperPresent = aOIDAOConsultWeb.readAllActivePapers(pUserId,connection);
            ArrayList arOIBVPaperPast = aOIDAOConsultWeb.readAllPastPapers(pUserId,connection);
			OIBAConfiguration aOIBAConfiguration = new OIDAOConfiguration().read("CONSULTATIONWELCOME",connection);
            responseObject.addResponseObject(OIConsultConstant.K_arOIBVPaperPresent,arOIBVPaperPresent);
            responseObject.addResponseObject(OIConsultConstant.K_arOIBVPaperPast,arOIBVPaperPast);
            responseObject.addResponseObject("aOIBAConfiguration",aOIBAConfiguration);
        }
        catch(Exception e)
        {
            error = e.getMessage();
            logger.error(e);
        }
        finally
        {
            freeConnection();
        }
        addToResponseObject();
        return responseObject;
    }
    
    /**
     * Call OIDAOConsultPaper.read
     * 
     * call OIDAOConsultQuestion.readArray
     * 
     * call OIDAOConsultFeedBack.readFeedBack
     * 
     * call OIDAOConsultResponse.readResponse 
     */
    public OIResponseObject readConsultPaperDetail(int pPaperId, String pUserId)
    {
        try
        {
            getConnection();
            connection.setAutoCommit(false);
            OIBAConsultPaper aOIBAConsultPaper = new OIDAOConsultPaper().read(pPaperId,connection);
            OIBAConsultCategory aOIBAConsultCategory = new OIDAOConsultCategory().read(aOIBAConsultPaper.getCategoryID(),connection);
            ArrayList arOIBAConsultQuestion = new OIDAOConsultQuestion().readArray(pPaperId,connection);
            OIBAFeedBack aOIBAFeedBack = new OIDAOConsultFeedBack().readFeedBack(pPaperId,pUserId, connection);
            ArrayList arOIBAResponse = new OIDAOConsultResponse().readResponse(pPaperId,pUserId,connection);
            OIBAMember aOIBAMember = new OIBAMember();
            aOIBAMember.setPaperId(pPaperId);
            aOIBAMember.setUserId(pUserId);
            aOIBAMember = new OIDAOConsultMember().readMember(aOIBAMember,connection);
            OIBADraft aOIBADraft = new OIDAOConsultResponse().readDraft(pPaperId,pUserId, connection);
            //WeSite Ranking starts here
            OIBAWebSiteRanking aOIBAWebSiteRanking = new OIBAWebSiteRanking();
            aOIBAWebSiteRanking.setActionId("VC");
            //aOIBAWebSiteRanking.setActionType("C");
            aOIBAWebSiteRanking.setUserId(pUserId);
            aOIBAWebSiteRanking.setItemId(pPaperId);
            new OIDAOWebSiteRanking().save(aOIBAWebSiteRanking,connection);
            //WeSite Ranking ends here
            responseObject.addResponseObject(OIConsultConstant.K_aOIBAConsultPaper,aOIBAConsultPaper);
            responseObject.addResponseObject(OIConsultConstant.K_aOIBAConsultCategory,aOIBAConsultCategory);
            responseObject.addResponseObject(OIConsultConstant.K_arOIBAConsultQuestion,arOIBAConsultQuestion);
            responseObject.addResponseObject(OIConsultConstant.K_aOIBAFeedBack,aOIBAFeedBack);
            responseObject.addResponseObject(OIConsultConstant.K_arOIBAResponse,arOIBAResponse);
            responseObject.addResponseObject(OIConsultConstant.K_aOIBAMember,aOIBAMember);
            responseObject.addResponseObject(OIConsultConstant.K_aOIBADraft,aOIBADraft);
            connection.commit();
            connection.setAutoCommit(true);
        }
        catch(Exception e)
        {
            error = e.getMessage();
            logger.error(e);
            try
            {
                connection.rollback();
            }catch (Exception ex){}
        }
        finally
        {
            freeConnection();
        }
        addToResponseObject();
        return responseObject;
    }
    
    /**
     * call OIDAOConsultFeedBack.saveFeedBack()
     * 
     * call OIDAOConsultResponse.saveResponse() 
     */
    public OIResponseObject saveConsultPaperDetail(ArrayList pArOIBAResponse, OIBAFeedBack pOIBAFeedBack)
    {
        try
        {
            getConnection();
            connection.setAutoCommit(false);
            OIBAFeedBack aOIBAFeedBack = new OIDAOConsultFeedBack().readFeedBack(pOIBAFeedBack.getPaperId(),pOIBAFeedBack.getUserId(), connection);
            boolean saveFlag = false;
            if (aOIBAFeedBack==null)
            {
                saveFlag = true;
            }
            else
            {
                pOIBAFeedBack.setFeedBackId(aOIBAFeedBack.getFeedBackId());
            }
            new OIDAOConsultFeedBack().saveFeedBack(pOIBAFeedBack,saveFlag,connection);
            
            OIDAOConsultResponse aOIDAOConsultResponse = new OIDAOConsultResponse();
            for(int i=0;i<pArOIBAResponse.size();i++)
            {
                OIBAResponse aOIBAResponse = (OIBAResponse) pArOIBAResponse.get(i);
                OIBAResponse cAOIBAResponse = aOIDAOConsultResponse.checkResponse(aOIBAResponse.getQuestionId(),pOIBAFeedBack.getUserId(),connection);
                saveFlag=false;
                if (cAOIBAResponse==null)
                {
                    saveFlag=true;
                }
                else
                {
                    aOIBAResponse.setResponseId(cAOIBAResponse.getResponseId());
                }
                aOIDAOConsultResponse.saveResponse(aOIBAResponse,saveFlag,connection);
            }
            
            OIBADraft aOIBADraft = aOIDAOConsultResponse.readDraft(pOIBAFeedBack.getPaperId(),pOIBAFeedBack.getUserId(), connection);
            if (aOIBADraft==null)
                aOIBADraft = new OIBADraft();
            aOIBADraft.setUserId(pOIBAFeedBack.getUserId());
            aOIBADraft.setStatus("D");
            aOIBADraft.setCpsuId(pOIBAFeedBack.getPaperId());
            aOIDAOConsultResponse.saveDraft(aOIBADraft,connection);
            //WeSite Ranking starts here
            OIBAWebSiteRanking aOIBAWebSiteRanking = new OIBAWebSiteRanking();
            aOIBAWebSiteRanking.setActionId("D");
            //aOIBAWebSiteRanking.setActionType("C");
            aOIBAWebSiteRanking.setUserId(pOIBAFeedBack.getUserId());
            aOIBAWebSiteRanking.setItemId(pOIBAFeedBack.getPaperId());
            //new OIDAOWebSiteRanking().save(aOIBAWebSiteRanking,connection);
            //WeSite Ranking ends here
            connection.commit();
            connection.setAutoCommit(true);
        }
        catch(Exception e)
        {
            error = e.getMessage();
            logger.error(e);
            try
            {
                connection.rollback();
            }catch (Exception ex){}
        }
        finally
        {
            freeConnection();
        }
        addToResponseObject();
        return responseObject;
    }
    
    /**
     * call OIDAOConsultFeedBack.saveFeedBack()
     * 
     * call OIDAOConsultResponse.saveResponse()
     * 
     * call OIDAOConsultMember.saveMember() 
     */
    public OIResponseObject submitConsultPaperDetail(ArrayList pArOIBAResponse, OIBAFeedBack pOIBAFeedBack)
    {
        try
        {
            getConnection();
            connection.setAutoCommit(false);
            OIBAFeedBack aOIBAFeedBack = new OIDAOConsultFeedBack().readFeedBack(pOIBAFeedBack.getPaperId(),pOIBAFeedBack.getUserId(), connection);
            boolean saveFlag = false;
            if (aOIBAFeedBack==null)
            {
                saveFlag = true;
            }
            else
            {
                pOIBAFeedBack.setFeedBackId(aOIBAFeedBack.getFeedBackId());
            }
            new OIDAOConsultFeedBack().saveFeedBack(pOIBAFeedBack,saveFlag,connection);
            
            OIDAOConsultResponse aOIDAOConsultResponse = new OIDAOConsultResponse();
            for(int i=0;i<pArOIBAResponse.size();i++)
            {
                OIBAResponse aOIBAResponse = (OIBAResponse) pArOIBAResponse.get(i);
                OIBAResponse cAOIBAResponse = aOIDAOConsultResponse.checkResponse(aOIBAResponse.getQuestionId(),pOIBAFeedBack.getUserId(),connection);
                saveFlag=false;
                if (cAOIBAResponse==null)
                {
                    saveFlag=true;
                }
                else
                {
                    aOIBAResponse.setResponseId(cAOIBAResponse.getResponseId());
                }
                aOIDAOConsultResponse.saveResponse(aOIBAResponse,saveFlag,connection);
            }
            OIBADraft aOIBADraft = aOIDAOConsultResponse.readDraft(pOIBAFeedBack.getPaperId(),pOIBAFeedBack.getUserId(), connection);
            if (aOIBADraft==null)
                aOIBADraft = new OIBADraft();
            aOIBADraft.setUserId(pOIBAFeedBack.getUserId());
            aOIBADraft.setStatus("S");
            aOIBADraft.setCpsuId(pOIBAFeedBack.getPaperId());
            aOIDAOConsultResponse.saveDraft(aOIBADraft,connection);
            OIBAMember aOIBAMember = new OIBAMember();
            aOIBAMember.setPaperId(pOIBAFeedBack.getPaperId());
            aOIBAMember.setUserId(pOIBAFeedBack.getUserId());
            aOIBAMember = new OIDAOConsultMember().readMember(aOIBAMember,connection);
            if (aOIBAMember.getMemberId()!=0)
            {
                new OIDAOConsultMember().updateMember(aOIBAMember,connection);
            }
            new OIDAOMemberProfile().updatePapers(pOIBAFeedBack.getUserId(),connection);
            //WeSite Ranking starts here
            OIBAWebSiteRanking aOIBAWebSiteRanking = new OIBAWebSiteRanking();
            aOIBAWebSiteRanking.setActionId("SC");
            //aOIBAWebSiteRanking.setActionType("C");
            aOIBAWebSiteRanking.setUserId(pOIBAFeedBack.getUserId());
            aOIBAWebSiteRanking.setItemId(pOIBAFeedBack.getPaperId());
            new OIDAOWebSiteRanking().save(aOIBAWebSiteRanking,connection);
            //WeSite Ranking ends here
            connection.commit();
            connection.setAutoCommit(true);
        }
        catch(Exception e)
        {
            error = e.getMessage();
            logger.error(e);
            try
            {
                connection.rollback();
            }catch (Exception ex){}
        }
        finally
        {
            freeConnection();
        }
        addToResponseObject();
        return responseObject;
    }
    
    public OIResponseObject readPastPaper(int pPaperId, String pUserId)
    {
        try
        {
            getConnection();
            OIBAConsultPaper aOIBAConsultPaper = new OIDAOConsultPaper().read(pPaperId,connection);
            OIBAConsultCategory aOIBAConsultCategory = new OIDAOConsultCategory().read(aOIBAConsultPaper.getCategoryID(),connection);
            //WeSite Ranking starts here
            OIBAWebSiteRanking aOIBAWebSiteRanking = new OIBAWebSiteRanking();
            aOIBAWebSiteRanking.setActionId("VC");
            //aOIBAWebSiteRanking.setActionType("C");
            aOIBAWebSiteRanking.setUserId(pUserId);
            aOIBAWebSiteRanking.setItemId(pPaperId);
            new OIDAOWebSiteRanking().save(aOIBAWebSiteRanking,connection);
            //WeSite Ranking ends here
            responseObject.addResponseObject(OIConsultConstant.K_aOIBAConsultPaper,aOIBAConsultPaper);
            responseObject.addResponseObject(OIConsultConstant.K_aOIBAConsultCategory,aOIBAConsultCategory);
        }
        catch(Exception e)
        {
            error = e.getMessage();
            logger.error(e);
        }
        finally
        {
            freeConnection();
        }
        addToResponseObject();
        return responseObject;
    }
    
    public OIResponseObject readQuetions(int pPaperId)
    {
        try
        {
            getConnection();
            ArrayList arOIBAConsultQuestion = new OIDAOConsultQuestion().readArray(pPaperId,connection);
            responseObject.addResponseObject(OIConsultConstant.K_arOIBAConsultQuestion,arOIBAConsultQuestion);
        }
        catch(Exception e)
        {
            error = e.getMessage();
            logger.error(e);
        }
        finally
        {
            freeConnection();
        }
        addToResponseObject();
        return responseObject;
    }
    
}
