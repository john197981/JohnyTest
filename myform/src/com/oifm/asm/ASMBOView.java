package com.oifm.asm;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseBo;
import com.oifm.common.OIResponseObject;


/*
 * Class Name:
 * Description:
 * 
 * 	Created By			Created/Modified on			Revision				Remarks
 * -----------------------------------------------------------------------------------------------------
 * 	Rajkumar			19/12/2005					Draft					Inital Version
 * 
 * -----------------------------------------------------------------------------------------------------
 */
public class ASMBOView extends OIBaseBo
{
    Logger logger = Logger.getLogger(ASMBOView.class.getName());

    public OIResponseObject listUserLetters(String pUserId,ASMBACommon objCommonBA)
    {
        try
        {
        	readUserLetters(pUserId);
        	new ASMBOCommon().process(objCommonBA,null);
        }
        catch(Exception e)
        {
            error = e.getMessage();
            logger.error("readUserLetters--" + e);
        }
        finally
        {
        }
        addToResponseObject();
        return responseObject;
    }

    public OIResponseObject readUserLetters(String pUserId)
    {
        try
        {
            getConnection();
            ASMDAOCommon objCommonDAO =new ASMDAOCommon();
            ArrayList arASMBALetters= new ASMDAOView().readUserLetters(pUserId,connection);
            String strModDesc = objCommonDAO.getModuleDescription(connection,"ASMSUBVIEWWELCOME");
            responseObject.addResponseObject("arASMBALetters",arASMBALetters);
            responseObject.addResponseObject("strModDesc",strModDesc);
        }
        catch(Exception e)
        {
            error = e.getMessage();
            logger.error("readUserLetters--" + e);
        }
        finally
        {
            freeConnection();
        }
        addToResponseObject();
        return responseObject;
    }

    public OIResponseObject readDetailLetters(String pUserId,String pLetterId)
    {
        try
        {
            getConnection();
            ASMBALetters aASMBALetters = new ASMBALetters();
            
            if (pLetterId!=null)
            {
            	aASMBALetters= new ASMDAOView().readDetailLetters(pLetterId,connection,pUserId);
            }
            
            ArrayList arASMBALetters= new ASMDAOView().readUserLetters(pUserId,connection);
            
            if (arASMBALetters!=null && pLetterId!=null)
            {
	            for (int i=0;i<arASMBALetters.size();i++)
	            {
	            	ASMBALetters aaASMBALetters = (ASMBALetters) arASMBALetters.get(i);
	            	if (aaASMBALetters.getLetterId().equals(pLetterId))
	            	{
	            		if (i>0)
	            		{
	            			ASMBALetters aaASMBALetters1 = (ASMBALetters) arASMBALetters.get(i-1);
	            			aASMBALetters.setPreviousLetterId(aaASMBALetters1.getLetterId());
	            			aASMBALetters.setPreviousStatus(aaASMBALetters1.getStatus());
	            		}
	            		if (i<arASMBALetters.size()-1)
	            		{
	            			ASMBALetters aaASMBALetters2 = (ASMBALetters) arASMBALetters.get(i+1);
	            			aASMBALetters.setNextLetterId(aaASMBALetters2.getLetterId());
	            			aASMBALetters.setNextStatus(aaASMBALetters2.getStatus());
	            		}
	            	}
	            }
            }
            responseObject.addResponseObject("aASMBALetters",aASMBALetters);
        }
        catch(Exception e)
        {
            error = e.getMessage();
            logger.error("readDetailLetters--" + e);
        }
        finally
        {
            freeConnection();
        }
        addToResponseObject();
        return responseObject;
    }

    public OIResponseObject saveLetter(String pUserId,ASMBALetters aASMBALetters)
    {
        try
        {
            getConnection();
            ASMDAOView aASMDAOView = new ASMDAOView();
            boolean flag = aASMDAOView.saveLetter(pUserId,aASMBALetters,connection);
            ArrayList arASMBALetters= aASMDAOView.readUserLetters(pUserId,connection);

            responseObject.addResponseObject("arASMBALetters",arASMBALetters);
        }
        catch(Exception e)
        {
            error = e.getMessage();
            logger.error("saveLetter--" + e);
        }
        finally
        {
            freeConnection();
        }
        addToResponseObject();
        return responseObject;
    }

    public OIResponseObject deleteDraftLetter(String pUserId,ASMBALetters aASMBALetters)
    {
        try
        {
            getConnection();
            ASMDAOView aASMDAOView = new ASMDAOView();
            boolean flag = aASMDAOView.deleteLetter(pUserId,aASMBALetters,connection);
            ArrayList arASMBALetters= aASMDAOView.readUserLetters(pUserId,connection);

            responseObject.addResponseObject("arASMBALetters",arASMBALetters);
        }
        catch(Exception e)
        {
            error = e.getMessage();
            logger.error("deleteDraftLetter--" + e);
        }
        finally
        {
            freeConnection();
        }
        addToResponseObject();
        return responseObject;
    }
}
