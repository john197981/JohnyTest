package com.oifm.consultation.admin;
/*
 * Class Name:
 * Description:
 * 
 * 	Created By			Created/Modified on			Revision				Remarks
 * -----------------------------------------------------------------------------------------------------
 * 	Rajkumar			30/06/2005					Draft					Inital Version
 * 
 * -----------------------------------------------------------------------------------------------------
 */

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseBo;
import com.oifm.common.OIResponseObject;
import com.oifm.consultation.OIConsultConstant;
import com.oifm.utility.OIDBRegistry;
public class OIBOConsultCategory extends OIBaseBo 
{
    Logger logger = Logger.getLogger(OIBOConsultCategory.class.getName());
    
    /**
     * This method will call the OIDAOConsultCategory.save() & pass OIBAConsultCategory as the input parameter 
     */
    public OIResponseObject saveCategory(OIBAConsultCategory pOIBAConsultCategory)
    {
        try
        {
            getConnection();
            connection.setAutoCommit(false);
            OIDAOConsultCategory aOIDAOConsultCategory = new OIDAOConsultCategory();
            //if (pOIBAConsultCategory.getCategoryID()==0)
            //{
	            if (aOIDAOConsultCategory.checkDuplicateCategory(pOIBAConsultCategory.getName(),pOIBAConsultCategory.getCategoryID(),connection))
	            {
	                boolean flag = aOIDAOConsultCategory.save(pOIBAConsultCategory,connection);
	            }
	            else
	            {
	                error = OIDBRegistry.getValues("OI.CONS.DUPLICATE.CATEGORY");
	            }
            //}
            //else
            //{
                //boolean flag = aOIDAOConsultCategory.save(pOIBAConsultCategory,connection);
            //}
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
     * This method will call the OIDAOConsultCategory.readAll() 
     * 
     * and returns responseObject, which holds ArrayList of OIBAConsultCategory object retunrned by OIDAOConsultCategory.readAll() method 
     */
    public OIResponseObject readAllCategory()
    {
        try
        {
            getConnection();
            ArrayList arOIBAConsultCategory = new OIDAOConsultCategory().readAll(connection);
            responseObject.addResponseObject(OIConsultConstant.K_arOIBAConsultCategory,arOIBAConsultCategory);
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
     * This method will call the OIDAOConsultCategory.delete() & pass OIBAConsultCategory as the input parameter 
     */
    public OIResponseObject deleteCategory(OIBAConsultCategory pOIBAConsultCategory)
    {
        try
        {
            getConnection();
            connection.setAutoCommit(false);
            OIDAOConsultCategory aOIDAOConsultCategory = new OIDAOConsultCategory();
            if (aOIDAOConsultCategory.checkCategory(pOIBAConsultCategory.getCategoryID(),connection)==0)
            {
                boolean flag = aOIDAOConsultCategory.delete(pOIBAConsultCategory,connection);
            }
            else
            {
                addMessageList(OIDBRegistry.getValues("OI.CONS.CATEGORY.CHECK"));
            }
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
     * This method will call the OIDAOConsultCategory.read() & pass CategoryID as the input parameter
     * 
     * and returns responseObject, which holds OIBAConsultCategory object returned by OIDAOConsultCategory.read() method 
     */
    public OIResponseObject readCategory(int pCategoryId)
    {
        try
        {
            getConnection();
            OIBAConsultCategory aOIBAConsultCategory = new OIDAOConsultCategory().read(pCategoryId,connection);
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
}
