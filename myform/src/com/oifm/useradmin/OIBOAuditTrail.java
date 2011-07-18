/*
 * File name	= OIBOAuditTrail.java
 * Package		= com.oifm.useradmin
 * Created on 	= Aug 21, 2005
 * Created by	= Oscar
 * Copyright	= Scandent Group
 *
 */

package com.oifm.useradmin;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseBo;
import com.oifm.common.OIResponseObject;
import com.oifm.forum.OIDAOPosting;
import com.oifm.utility.OIUtilities;


public class OIBOAuditTrail extends OIBaseBo 
{
	private static Logger logger = Logger.getLogger(OIBOAuditTrail.class);
	
	public OIBOAuditTrail() {}
	
	public OIResponseObject getTransactions(OIBAAuditTrail objBAAuditTrail) 
	{
        ArrayList alTransactions = null;
        OIDAOAuditTrail objAuditTrail = new OIDAOAuditTrail();
        
        try 
        {
            getConnection();
            alTransactions = objAuditTrail.searchTransaction(connection, objBAAuditTrail.getStrType(), objBAAuditTrail.getStrFrom(), objBAAuditTrail.getStrTo());
            assignExpressions(alTransactions);
        } 
        catch (SQLException se) 
        {
            error = ""+se.getErrorCode();
            logger.error("getTransactions - SQLException->" + se);
        } 
        catch (Exception e) 
        {
            error = "OIDEFAULT";
            logger.error("getTransactions->" + e);
        } 
        finally 
        {
            freeConnection();
            addToResponseObject();
            responseObject.addResponseObject("Transactions", alTransactions);
        }
        return responseObject;
    }
	
	private void assignExpressions(ArrayList alTransactions) 
	{
		OIAuditTrailBean objTrans = null;
		String strImagesRoot = OIUtilities.getUploadFilesDir("OIFM.docroot");
		String strImgPart1 = "<img src='"+strImagesRoot;
		String strImgPart2 = "' border='0'>";
		String strTemp = "";
		if(alTransactions != null && alTransactions.size() > 0)	
		{
			for (int j=0; j<alTransactions.size(); j++)	
			{
				objTrans = (OIAuditTrailBean) alTransactions.get(j);
				strTemp = OIDAOPosting.allocateExpressions(strImgPart1, strImgPart2, objTrans.getStrPostMessage()); 
				objTrans.setStrPostMessage(strTemp);
				strTemp = OIDAOPosting.allocateExpressions(strImgPart1, strImgPart2, objTrans.getStrModMessage()); 
				objTrans.setStrModMessage(strTemp);
			}
		}
	}
}