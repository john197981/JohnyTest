package com.oifm.common;

import org.apache.log4j.Logger;

import com.oifm.asm.ASMBALetters;
import com.oifm.asm.ASMBOCommon;
import com.oifm.asm.ASMDAOView;
import com.oifm.base.OIBaseBo;
import com.oifm.codemaster.OIBACodeMaster;
import com.oifm.codemaster.OIDAOCodeMaster;
import com.oifm.useradmin.OIBAProfile;
import com.oifm.useradmin.OIDAOMemberProfile;
import com.oifm.utility.OIDBRegistry;

public class OIBOAlertFriends extends OIBaseBo 
{
    Logger logger = Logger.getLogger(OIBOAlertFriends.class.getName());
    
    public OIResponseObject formulateMessage(int pID,String pModule,String pUrl,String pUserId)
    {
    	String strTopic ="";
    	try
        {
            getConnection();
            OIBACodeMaster aOIBACodeMasterSubject = null;
            OIBACodeMaster aOIBACodeMasterBody = null;
            if (pModule.equals("C") || pModule.equals("CP"))
            {
                aOIBACodeMasterSubject = new OIDAOCodeMaster().readType("MAIL_TEMPLATE","AF_CP_MAIL_SUBJECT",connection);
                aOIBACodeMasterBody = new OIDAOCodeMaster().readType("MAIL_TEMPLATE","AF_CP_MAIL_BODY",connection);
            }
            else if (pModule.equals("F"))
            {
                aOIBACodeMasterSubject = new OIDAOCodeMaster().readType("MAIL_TEMPLATE","FM_EMAIL_THREAD_TO_FRIEND_SUBJECT",connection);
                aOIBACodeMasterBody = new OIDAOCodeMaster().readType("MAIL_TEMPLATE","FM_EMAIL_THREAD_TO_FRIEND_BODY",connection);
            }
            //ASM Home
            else if (pModule.equals("ASM"))
            {
                aOIBACodeMasterSubject = new OIDAOCodeMaster().readType("MAIL_TEMPLATE","ASM_HOME_EMAIL_TO_FRIEND_SUBJECT",connection);
                ASMBALetters aASMBALetters = new ASMDAOView().readDetailLetters(""+pID,connection,null);
                if (aASMBALetters!=null && aASMBALetters.getTopic()!=null)
                {
                	aOIBACodeMasterSubject.setDescription(OIDBRegistry.getValues("ASM.ALERTFRIEND.SUBJECT.DEFAULT.MESSAGE")+"-"+aASMBALetters.getTopic());                	
                }
                else
                {
                	aOIBACodeMasterSubject.setDescription(OIDBRegistry.getValues("ASM.ALERTFRIEND.SUBJECT.DEFAULT.MESSAGE"));
                }
                aOIBACodeMasterBody = new OIDAOCodeMaster().readType("MAIL_TEMPLATE","ASM_HOME_EMAIL_TO_FRIEND_BODY",connection);
                ASMBOCommon objBO =new ASMBOCommon();
                strTopic=objBO.getTopic(""+pID);
                //This is for replacing the word Topic with the Topic description
                aOIBACodeMasterBody.setDescription(aOIBACodeMasterBody.getDescription().replaceAll("TOPIC",strTopic));
            }
            // ASM Editor, 14th March 2011, added the ASMEditorNote module for addressing the EditorNote issue.
            else if (pModule.equals("ASMEditorNote"))
            {
                aOIBACodeMasterSubject = new OIDAOCodeMaster().readType("MAIL_TEMPLATE","ASM_HOME_EMAIL_TO_FRIEND_SUBJECT",connection);
                ASMBALetters aASMBALetters = new ASMDAOView().readDetailLetters(""+pID,connection,null);
                if (aASMBALetters!=null && aASMBALetters.getTopic()!=null)
                {
                	aOIBACodeMasterSubject.setDescription(OIDBRegistry.getValues("ASM.ALERTFRIEND.SUBJECT.DEFAULT.MESSAGE")+"-"+aASMBALetters.getTopic());                	
                }
                else
                {
                	aOIBACodeMasterSubject.setDescription("ASM Editor Note");
                }
                aOIBACodeMasterBody = new OIDAOCodeMaster().readType("MAIL_TEMPLATE","ASM_HOME_EMAIL_TO_FRIEND_BODY",connection);
                ASMBOCommon objBO =new ASMBOCommon();
                strTopic=objBO.getTopic(""+pID);
                //This is for replacing the word Topic with the Topic description
                aOIBACodeMasterBody.setDescription(aOIBACodeMasterBody.getDescription().replaceAll("TOPIC",strTopic).replaceAll("ASM", "ASM Editor Note"));
            }            
            else if (pModule.equals("ASMDRAFT"))
            {
                aOIBACodeMasterSubject = new OIDAOCodeMaster().readType("MAIL_TEMPLATE","ASM_DRAFT_EMAIL_TO_FRIEND_SUBJECT",connection);
                ASMBALetters aASMBALetters= new ASMDAOView().readDetailLetters(""+pID,connection,null);
                if (aASMBALetters!=null && aASMBALetters.getTopic()!=null)
                {
                	aOIBACodeMasterSubject.setDescription(OIDBRegistry.getValues("ASM.ALERTFRIEND.SUBJECT.DEFAULT.MESSAGE")+"-"+aASMBALetters.getTopic());                	
                }
                else
                {
                	aOIBACodeMasterSubject.setDescription(OIDBRegistry.getValues("ASM.ALERTFRIEND.SUBJECT.DEFAULT.MESSAGE"));
                }
                aOIBACodeMasterBody = new OIDAOCodeMaster().readType("MAIL_TEMPLATE","ASM_DRAFT_EMAIL_TO_FRIEND_BODY",connection);
                ASMBOCommon objBO =new ASMBOCommon();
                //strTopic=objBO.getTopic(""+pID);
                //This is for replacing the word Topic with the Topic description
                aOIBACodeMasterBody.setDescription(aOIBACodeMasterBody.getDescription() + "\n\n" + aASMBALetters.getLetter());
            }
            OIBAProfile aOIBAProfile = new OIDAOMemberProfile().readMemberProfile(pUserId,connection);
            logger.info(aOIBACodeMasterBody.getDescription());
            aOIBACodeMasterBody.setDescription(aOIBACodeMasterBody.getDescription().replaceAll("URL",pUrl));
            
            logger.info(aOIBACodeMasterBody.getDescription());
            responseObject.addResponseObject("aOIBACodeMasterSubject",aOIBACodeMasterSubject);
            responseObject.addResponseObject("aOIBACodeMasterBody",aOIBACodeMasterBody);
            responseObject.addResponseObject("aOIBAProfile",aOIBAProfile);
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
