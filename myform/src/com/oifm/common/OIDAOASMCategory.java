package com.oifm.common;

/**
 * @author admin
 *
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.oifm.common.OIASMCategorySqls;
import com.oifm.common.OIASMCategoryConstant;
import com.oifm.consultation.OIConsultationSqls;

import org.apache.log4j.Logger;
import com.oifm.base.OIBaseDao;
import com.oifm.utility.OISQLUtilities;

public class OIDAOASMCategory extends OIBaseDao {

	Logger logger = Logger.getLogger(OIDAOASMCategory.class.getName());

	/**
	 * 1. gets connection from the base class 2. create preparedStatement 3. sets
	 * value of the primary keys to the prepare statement, which will delete that
	 * record from the database 4. execute the query
	 */
	public boolean delete(OIBAASMCategory aOIBAASMCategory, Connection connection) throws Exception {
		boolean flag = false;
		// int i=0;
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(OIASMCategorySqls.DELETE_CATEGORY);
			preparedStatement.setInt(1, aOIBAASMCategory.getCategoryID());
			flag = preparedStatement.execute();
			// preparedStatement.close();
		} catch (SQLException sqle) {
			logger.error(sqle.getMessage());
			// connection.rollback();
			throw sqle;
		} finally {
			// freeConnection();
			if (preparedStatement != null)
				preparedStatement.close();
		}
		
		return flag;
	}

	/**
	 * 1. gets connection from the base class 2. create preparedStatement 3. sets
	 * value to the prepare statement 4. save it to the database
	 */
	public boolean save(OIBAASMCategory aOIBAASMCategory, Connection connection) throws SQLException, Exception {
		boolean flag = false;
		int i = 0;
		PreparedStatement preparedStatement = null;
		try {
			// getConnection();
			logger.info(aOIBAASMCategory.getCategoryID() + "");
			if (aOIBAASMCategory.getCategoryID() == 0) {
				preparedStatement = connection.prepareStatement(OIASMCategorySqls.SAVE_CATEGORY);
				preparedStatement.setString(1, aOIBAASMCategory.getName());
				preparedStatement.setString(2, aOIBAASMCategory.getDescription());
				preparedStatement.setString(3, aOIBAASMCategory.getCreatedBy());
			} else {
				preparedStatement = connection.prepareStatement(OIASMCategorySqls.UPDATE_CATEGORY);
				preparedStatement.setString(1, aOIBAASMCategory.getName());
				preparedStatement.setString(2, aOIBAASMCategory.getDescription());
				preparedStatement.setInt(3, aOIBAASMCategory.getCategoryID());
			}
			i = preparedStatement.executeUpdate();
			// preparedStatement.close();
		} catch (SQLException sqle) {
			logger.error(sqle.getMessage());
			// connection.rollback();
			throw sqle;
		} finally {
			// freeConnection();
			if (preparedStatement != null)
				preparedStatement.close();
		}

		if (i == 0) {
			logger.error("Save Failed");
			throw new Exception("Save Failed");
		} else {
			flag = true;
		}
		return flag;
	}

	/**
	 * This method reads the category of the passes CategoryID
	 * 
	 * It returns OIBAASMCategory object.
	 */
	public OIBAASMCategory read(int acategoryID, Connection connection) throws Exception {
		OIBAASMCategory aOIBAASMCategory = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		try {
			// getConnection();
			preparedStatement = connection.prepareStatement(OIASMCategorySqls.READ_CATEGORY);
			preparedStatement.setInt(1, acategoryID);
			rs = preparedStatement.executeQuery();

			if (rs.next()) {
				aOIBAASMCategory = new OIBAASMCategory();
				aOIBAASMCategory.setCategoryID(rs.getInt(OIASMCategoryConstant.Q_CATEGORYID));
				aOIBAASMCategory.setName(rs.getString(OIASMCategoryConstant.Q_NAME));
				aOIBAASMCategory.setDescription(rs.getString(OIASMCategoryConstant.Q_DESCRIPTION));
			}
			// preparedStatement.close();
		} catch (SQLException sqle) {
			logger.error(sqle.getMessage());
			// connection.rollback();
			throw sqle;
		} finally {
			// freeConnection();
			if (preparedStatement != null)
				preparedStatement.close();
			if (rs != null)
				rs.close();
		}

		return aOIBAASMCategory;
	}

	/**
	 * This method should return all the categories present in the table.
	 * 
	 * It returns an ArrayList of OIBAASMCategory object
	 */

	public ArrayList readAll(Connection connection) throws Exception {
		ArrayList arOIBAASMCategory = new ArrayList();
		OIBAASMCategory aOIBAASMCategory = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			// getConnection();
			preparedStatement = connection.prepareStatement(OIASMCategorySqls.READ_ALL_CATEGORY);
			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				aOIBAASMCategory = new OIBAASMCategory();
				aOIBAASMCategory.setCategoryID(rs.getInt(OIASMCategoryConstant.Q_CATEGORYID));
				aOIBAASMCategory.setName(rs.getString(OIASMCategoryConstant.Q_NAME));
				aOIBAASMCategory.setDescription(rs.getString(OIASMCategoryConstant.Q_DESCRIPTION));
				arOIBAASMCategory.add(aOIBAASMCategory);
			}
			// preparedStatement.close();
		} catch (SQLException sqle) {
			logger.error(sqle.getMessage());
			// connection.rollback();
			throw sqle;
		} finally {
			// freeConnection();
			// instead of if (preparedStatement!=null)
			// preparedStatement.close();
			// if (rs!=null)
			// rs.close();
			OISQLUtilities.closeRsetPstatement(rs, preparedStatement);

		}
		if (arOIBAASMCategory.size() == 0)
			arOIBAASMCategory = null;

		return arOIBAASMCategory;
	}

	//This method should update Letters Category Id to Default(ie 1) when their 
	// corresponding categories are deleted.
	public int checkCategory(int acategoryID, Connection connection) throws Exception {
		int count = 0;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			// getConnection();
			//System.out.println("OIDAOASMCategory-checkCategorynnnnnn");
			preparedStatement = connection.prepareStatement(OIASMCategorySqls.UPDATE_LETTERS);
			preparedStatement.setInt(1, acategoryID);
			count=preparedStatement.executeUpdate();
			//System.out.println("OIDAOASMCategory-checkCategoryddddd"+count);
			// preparedStatement.close();
		} catch (SQLException sqle) {
			logger.error(sqle.getMessage());
			// connection.rollback();
			throw sqle;
		} finally {
			// freeConnection();
			if (preparedStatement != null)
				preparedStatement.close();
			if (rs != null)
				rs.close();
		}

		return count;
	}

	/**
	 * To check duplicate category
	 */
	public boolean checkDuplicateCategory(String aCategoryName, int aCategoryId, Connection connection) throws Exception {
		boolean flag = false;
		PreparedStatement preparedStatement = null;
		try {
			// getConnection();
			if (aCategoryId == 0) {
				preparedStatement = connection.prepareStatement(OIASMCategorySqls.ASM_CHECK_DUPLICATE_CATEGORY);
				preparedStatement.setString(1, aCategoryName);
			} else {
				preparedStatement = connection.prepareStatement(OIASMCategorySqls.ASM_CHECK_DUPLICATE_CATEGORY1);
				preparedStatement.setString(1, aCategoryName);
				preparedStatement.setInt(2, aCategoryId);
			}
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				if (rs.getInt("count") == 0)
					flag = true;
			}
			// preparedStatement.close();
		} catch (SQLException sqle) {
			logger.error(sqle.getMessage());
			// connection.rollback();
			throw sqle;
		} finally {
			// freeConnection();
			if (preparedStatement != null)
				preparedStatement.close();
		}
		return flag;
	}
	
	
}

