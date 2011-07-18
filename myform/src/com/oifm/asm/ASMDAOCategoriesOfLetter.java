/*
 * File name	= ASMDAOAbout.java
 * Package		= com.oifm.asm
 * Created on 	= Dec 16, 2005
 * Created by	= Oscar
 * Copyright	= Scandent Group
 *
 */

package com.oifm.asm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseDao;
import com.oifm.utility.OISQLUtilities;
import com.oifm.utility.OIUtilities;


public class ASMDAOCategoriesOfLetter extends OIBaseDao {
	private static final Logger logger = Logger.getLogger(ASMDAOCategoriesOfLetter.class);
	
	public ASMDAOCategoriesOfLetter() {}
	
	public ArrayList getCategoryList(Connection conn) throws Exception {
		logger.info("Start of getCategoryList");
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList categoryList = new ArrayList();
		ASMBVCategoryDetails objCategoryDetails ;
		try {
			pst = conn.prepareStatement(ASMGlobals.QRY_GET_ALL_CATEGORY);
			logger.info("Start of getCategoryList"+ASMGlobals.QRY_GET_ALL_CATEGORY);
			rs = pst.executeQuery();
			
			while (rs.next()) {
				objCategoryDetails = new ASMBVCategoryDetails();
				objCategoryDetails.setStrCategoryID(new Integer(rs.getInt("CATEGORYID")));
				objCategoryDetails.setStrCategoryName(rs.getString("NAME"));
				logger.info("category details b4 adding to the array :::: " + objCategoryDetails.getStrCategoryName());
				categoryList.add(objCategoryDetails);
			}
		} catch (Exception e){
			logger.error("getList() - " + e);
			throw e;
		} finally {
			OISQLUtilities.closeRsetPstatement(rs, pst);
		}
		logger.info("End of getCategoryList");
		return categoryList;
	}
	
	public int getCategorybyLetterID(Connection conn,int letterId) throws Exception {
		logger.info("Start of getCategorybyLetterID");
		PreparedStatement pst = null;
		ResultSet rs = null;
		int categoryId=0;
		ASMBVCategoryDetails objCategoryDetails ;
		try {
			pst = conn.prepareStatement(ASMGlobals.QRY_CATID_FOR_LETTERID);
			logger.info("Start of getCategoryList"+ASMGlobals.QRY_GET_ALL_CATEGORY);
			pst.setInt(1, letterId);
			rs = pst.executeQuery();
			
			while (rs.next()) {
				categoryId = rs.getInt("categoryid");
				logger.info("category details b4 adding to the array :::: " + categoryId);
				
			}
		} catch (Exception e){
			logger.error("getList() - " + e);
			throw e;
		} finally {
			OISQLUtilities.closeRsetPstatement(rs, pst);
		}
		logger.info("End of getCategoryList");
		return categoryId;
	}
	
	public ArrayList getLetterbyCategory(Connection conn, String categoryID) throws Exception {
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList categoryList = new ArrayList();
		ASMBVCategoryDetails objCategoryDetails ;
		try {
			StringBuffer strQry = new StringBuffer();
			strQry.append(ASMGlobals.QRY_GET_ALL_LETTERS_FOR_CATEGORYID);
			if(!categoryID.equals(null)){
				strQry.append("WHERE categoryid=?");
			}
			pst = conn.prepareStatement(strQry.toString());
			logger.info("strQry********* " + strQry.toString());
			/*if(!categoryID.equals(null)){
				pst.setInt(1, categoryID.intValue());
			}*/
			
			if(!categoryID.equals(null)){
				
				pst.setString(1, categoryID);
			}
			if(categoryID!=null && !categoryID.equals(""))
			{
				rs = pst.executeQuery();
				
				while (rs.next()) {
					objCategoryDetails = new ASMBVCategoryDetails();
					objCategoryDetails.setStrLetterID(new Integer(rs.getInt("letterid")));
					objCategoryDetails.setStrLetterName(rs.getString("topic"));
					//logger.info("category details b4 adding to the array :::: " + objCategoryDetails.getStrCategoryName());
					categoryList.add(objCategoryDetails);
				}
			}
		} catch (Exception e){
			logger.error("getList() - " + e);
			throw e;
		} /*finally {
			OISQLUtilities.closeRsetPstatement(rs, pst);
		}*/
		return categoryList;
	}
	
	public ASMBVLetterDetails getLetterContentByLtrIDCtgryID(Connection conn,Integer categoryID,Integer letterId) throws Exception {
		PreparedStatement pst = null;
		ResultSet rs = null;
		ASMBVLetterDetails objBA = null;
		try { 
			System.out.println("letter id ::::: " + letterId + "category Id :::: " + categoryID);
			//To be moved to the constant file later.....Nimitta
			String qry ="SELECT letterid,topic,letter,reply FROM OI_AM_LETTERS WHERE categoryid=? ";
			StringBuffer sbQry=new StringBuffer();
        	sbQry.append(qry);
        	if(!letterId.equals(null)){
        		sbQry.append(" AND letterid=?");
        	}
        	//System.out.println("query is :::::: " + sbQry.toString());
			pst = conn.prepareStatement(sbQry.toString());
			pst.setInt(1, categoryID.intValue());
			if(!letterId.equals(null)){
				pst.setInt(2, letterId.intValue());
        	}
			
			rs = pst.executeQuery();
			//ASMBVLetterDetails objBA1 = (ASMBVLetterDetails)rs;
			//System.out.println("TEST TEST TEST TEST   " + rs.next());
			while (rs.next()) {
				//System.out.println("inside while" + new Integer(rs.getInt("LETTERID")));
				objBA = new ASMBVLetterDetails();
				objBA.setIntCategoryID(categoryID);
				objBA.setIntLetterid(new Integer(rs.getInt("LETTERID")));
				objBA.setStrTopic(rs.getString("TOPIC"));
				objBA.setStrLetterContent(OIUtilities.clobToString(rs.getClob("LETTER")));
				objBA.setStrLetterReply(OIUtilities.clobToString(rs.getClob("REPLY")));
				//list.add(objBA);
			}
			//System.out.println("values are set in the object" + objBA.getStrLetterContent() + objBA.getIntLetterid());
		} catch (Exception e){
			logger.error("getList() - " + e);
			e.printStackTrace();
			throw e;
		} finally {
			OISQLUtilities.closeRsetPstatement(rs, pst);
		}
		return objBA;
	}
	
	
	
		
}
