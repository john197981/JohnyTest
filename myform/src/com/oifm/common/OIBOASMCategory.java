package com.oifm.common;

/**
 * @author Rakesh 		Mailto@ : Rakesh.Chagallu@cambridge-asia.com
 * Desc : This is Business Obj that carries out the operationlike delete, read.
 * Date : 15 Jan 2008
 */
import java.util.ArrayList;
import org.apache.log4j.Logger;
import com.oifm.common.OIDAOASMCategory;
import com.oifm.common.OIBAASMCategory;
import com.oifm.common.OIASMCategoryConstant;
import com.oifm.base.OIBaseBo;
import com.oifm.utility.OIDBRegistry;

public class OIBOASMCategory extends OIBaseBo {

	Logger logger = Logger.getLogger(OIBOASMCategory.class.getName());

	/**
	 * This method will call the OIDAOASMCategory.save() & pass OIBAASMCategory as
	 * the input parameter
	 */
	public OIResponseObject saveCategory(OIBAASMCategory aOIBAASMCategory) {
		try {
			getConnection();
			connection.setAutoCommit(false);
			OIDAOASMCategory aOIDAOASMCategory = new OIDAOASMCategory();
			if (aOIDAOASMCategory.checkDuplicateCategory(aOIBAASMCategory.getName(), aOIBAASMCategory.getCategoryID(),
					connection)) {
				boolean flag = aOIDAOASMCategory.save(aOIBAASMCategory, connection);
			logger.info("The flag in OIBOASM for save is" + flag);
			} else {
				error = OIDBRegistry.getValues("OI.CONS.DUPLICATE.CATEGORY");
			}
			
			connection.commit();
			connection.setAutoCommit(true);
		} catch (Exception e) {
			error = e.getMessage();
			logger.error(e);
			try {
				connection.rollback();
			} catch (Exception ex) {
			}
		} finally {
			freeConnection();
		}
		addToResponseObject();
		return responseObject;
	}

	/**
	 * This method will call the OIDAOASMCategory.readAll()
	 * 
	 * and returns responseObject, which holds ArrayList of OIBAASMCategory object
	 * retunrned by OIDAOASMCategory.readAll() method
	 */
	public OIResponseObject readAllCategory() {
		try {
			getConnection();
			ArrayList arOIBAASMCategory = new OIDAOASMCategory().readAll(connection);
			responseObject.addResponseObject(OIASMCategoryConstant.K_arOIBAASMCategory, arOIBAASMCategory);
		} catch (Exception e) {
			error = e.getMessage();
			logger.error(e);
		} finally {
			freeConnection();
		}
		addToResponseObject();
		return responseObject;
	}

	/**
	 * This method will call the OIDAOASMCategory.delete() & pass OIBAASMCategory
	 * as the input parameter
	 */
	public OIResponseObject deleteCategory(OIBAASMCategory aOIBAASMCategory) {
		try {
			getConnection();
			connection.setAutoCommit(false);
			OIDAOASMCategory aOIDAOASMCategory = new OIDAOASMCategory();
			//System.out.println("The before");
			aOIDAOASMCategory.checkCategory(aOIBAASMCategory.getCategoryID(), connection); 
				boolean flag = aOIDAOASMCategory.delete(aOIBAASMCategory, connection);
			//	System.out.println("The Flag" + flag);
//			} else {
//				System.out.println("The else");
//				addCategoryList(OIDBRegistry.getValues("OI.CONS.CATEGORY.CHECK"));
//			}
			connection.commit();
			connection.setAutoCommit(true);
		} catch (Exception e) {
			error = e.getMessage();
			logger.error(e);
			try {
				connection.rollback();
			} catch (Exception ex) {
			}
		} finally {
			freeConnection();
		}
		addToResponseObject();
		return responseObject;
	}

	/**
	 * This method will call the OIDAOASMCategory.read() & pass CategoryID as the
	 * input parameter
	 * 
	 * and returns responseObject, which holds OIBAASMCategory object returned by
	 * OIDAOASMCategory.read() method
	 */
	public OIResponseObject readCategory(int aCategoryID) {
		try {
			getConnection();
			OIBAASMCategory aOIBAASMCategory = new OIDAOASMCategory().read(aCategoryID, connection);
			responseObject.addResponseObject(OIASMCategoryConstant.K_aOIBAASMCategory, aOIBAASMCategory);
		} catch (Exception e) {
			error = e.getMessage();
			logger.error(e);
		} finally {
			freeConnection();
		}
		addToResponseObject();
		return responseObject;
	}

	
}

