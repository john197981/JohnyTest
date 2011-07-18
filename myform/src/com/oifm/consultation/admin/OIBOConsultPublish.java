package com.oifm.consultation.admin;

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

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseBo;
import com.oifm.codemaster.OIDAOCodeMaster;
import com.oifm.common.OIDAOSendMail;
import com.oifm.common.OIPageInfoBean;
import com.oifm.common.OIResponseObject;
import com.oifm.consultation.OIConsultConstant;
import com.oifm.consultation.OIDAOConsultFeedBack;
public class OIBOConsultPublish extends OIBaseBo 
{
    Logger logger = Logger.getLogger(OIBOConsultPublish.class.getName());
    /**
     * Call OIDAOConsultPaper.read
     * 
     * Call OIDAOConsultFeedBack.readAllFeedBack
     * 
     * return the ArrayList of OIBVFeedBack & OIBAConsultPaper 
     */
    public OIResponseObject populateConsultPublish(int pPaperId,int pPageNo)
    {
    	System.out.println("inside populateConsultPublish 11111");
        try
        {
            getConnection();
            OIBAConsultPaper aOIBAConsultPaper = new OIDAOConsultPaper().read(pPaperId,connection);
            int totalRecord = new OIDAOConsultFeedBack().readTotalFeedBack(pPaperId,connection);
            OIPageInfoBean aOIPageInfoBean = new OIPageInfoBean(pPageNo,OIDAOSendMail.recPerPage(connection));
            //aOIPageInfoBean.setRecPerPage();
            aOIPageInfoBean.setTotalRec(totalRecord);
            ArrayList arOIBVFeedBack = new OIDAOConsultFeedBack().readFeedBackWithinLimit(pPaperId,aOIPageInfoBean.getStartRec(),aOIPageInfoBean.getEndRec(),connection);
            String type = "PUBLISH_CATEGORY";
            String type1 = "";
            if (aOIBAConsultPaper!= null && aOIBAConsultPaper.getSecurity().equals("R"))
                type1 = "PUBLISH_CATEGORY_P";
            ArrayList arOIBACodeMaster = new OIDAOCodeMaster().readType(type,connection,type1);
            responseObject.addResponseObject(OIConsultConstant.K_aOIBAConsultPaper,aOIBAConsultPaper);
            responseObject.addResponseObject(OIConsultConstant.K_arOIBVFeedBack,arOIBVFeedBack);
            responseObject.addResponseObject(OIConsultConstant.K_arOIBACodeMaster,arOIBACodeMaster);
            responseObject.addResponseObject(OIConsultConstant.K_aOIPageInfoBean,aOIPageInfoBean);
        }
        catch(Exception e)
        {
            error = e.getMessage();
            logger.error(e);
            //logger.info(error);
        }
        finally
        {
            freeConnection();
        }
        addToResponseObject();
        return responseObject;
    }

    //modify by edmund
    public OIResponseObject exportConsultPublish(int pPaperId)
    {
    	System.out.println("inside exportConsultPublish 22222222");
        try
        {
            getConnection();
            OIBAConsultPaper aOIBAConsultPaper = new OIDAOConsultPaper().read(pPaperId,connection);
            ArrayList arOIBAConsultQuestion = new OIDAOConsultQuestion().readArray(pPaperId,connection);
            ArrayList arOIBVFeedBack = new OIDAOConsultFeedBack().readAllFeedBackAndResponses(pPaperId,connection,arOIBAConsultQuestion);
            //ArrayList arOIBAResponse = new OIDAOConsultResponse().readAllResponse(pPaperId,connection);
            String type = "PUBLISH_CATEGORY";
            String type1 = "";
            if (aOIBAConsultPaper!= null && aOIBAConsultPaper.getSecurity().equals("R"))
                type1 = "PUBLISH_CATEGORY_P";
            ArrayList arOIBACodeMaster = new OIDAOCodeMaster().readType(type,connection,type1);
            //ArrayList arOIBACodeMaster = new OIDAOCodeMaster().readType(type,connection);
            responseObject.addResponseObject(OIConsultConstant.K_aOIBAConsultPaper,aOIBAConsultPaper);
            responseObject.addResponseObject(OIConsultConstant.K_arOIBVFeedBack,arOIBVFeedBack);
            responseObject.addResponseObject(OIConsultConstant.K_arOIBACodeMaster,arOIBACodeMaster);
            responseObject.addResponseObject(OIConsultConstant.K_arOIBAConsultQuestion,arOIBAConsultQuestion);
            //responseObject.addResponseObject(OIConsultConstant.K_arOIBAResponse,arOIBAResponse);
        }
        catch(Exception e)
        {
            error = e.getMessage();
            logger.error(e);
            //logger.info(error);
        }
        finally
        {
            freeConnection();
        }
        addToResponseObject();
        return responseObject;
    }
}
