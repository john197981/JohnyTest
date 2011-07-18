/*
 * File name	= OIBOSearch.java
 * Package		= com.oifm.search
 * Created on 	= Aug 14, 2005
 * Created by	= Oscar
 * Copyright	= Scandent Group
 *
 */

package com.oifm.search;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseBo;
import com.oifm.common.OIResponseObject;
import com.oifm.forum.OIDAOPosting;
import com.oifm.utility.OIUtilities;


public class OIBOSearch extends OIBaseBo {
	private static Logger logger = Logger.getLogger(OIBOSearch.class);
	
	public OIBOSearch () {}
	
	public String constructForumSearchQuery (ArrayList alTokens) {
		String ret = OISearchSqls.QRY_SIMPLE_SEARCH_FM_SELECT;
		for (int i = 0; i < alTokens.size(); i++) {
			ret += OISearchSqls.QRY_SIMPLE_SEARCH_FM_TITLE;
			ret += alTokens.get(i);
			ret += OISearchSqls.QRY_SIMPLE_SEARCH_CLOSE_OR;
		}
		for (int i = 0; i < alTokens.size(); i++) {
			ret += OISearchSqls.QRY_SIMPLE_SEARCH_FM_POSTING;
			ret += alTokens.get(i);
			if (i < alTokens.size() - 1) ret += OISearchSqls.QRY_SIMPLE_SEARCH_CLOSE_OR;
		}
		ret += OISearchSqls.QRY_SIMPLE_SEARCH_CLOSE;
		logger.info(ret);
		return ret;
	}
	
	public String constructSurveySearchQuery (ArrayList alTokens) {
		String ret = OISearchSqls.QRY_SIMPLE_SEARCH_SU_SELECT;
		for (int i = 0; i < alTokens.size(); i++) {
			ret += OISearchSqls.QRY_SIMPLE_SEARCH_SU_NAME;
			ret += alTokens.get(i);
			ret += OISearchSqls.QRY_SIMPLE_SEARCH_CLOSE_OR;
		}
		for (int i = 0; i < alTokens.size(); i++) {
			ret += OISearchSqls.QRY_SIMPLE_SEARCH_SU_DESC;
			ret += alTokens.get(i);
			ret += OISearchSqls.QRY_SIMPLE_SEARCH_CLOSE_OR;
		}
		for (int i = 0; i < alTokens.size(); i++) {
			ret += OISearchSqls.QRY_SIMPLE_SEARCH_SU_SUMMARY;
			ret += alTokens.get(i);
			if (i < alTokens.size() - 1) ret += OISearchSqls.QRY_SIMPLE_SEARCH_CLOSE_OR;
		}
		ret += OISearchSqls.QRY_SIMPLE_SEARCH_CLOSE;
		logger.info(ret);
		return ret;
	}

	//	 Added by K.K.Kumaresan on Aug 18,2009
	public String constructASMSearchQuery (ArrayList alTokens) {
		
		String query=" Select ROWNUM NUM,letterid,topic,SUBJECT,CREATEDBY,LETTER,TO_CHAR(SUBMITTEDON,'DD-Mon-YYYY') AS CREATED_ON,NICKNAME " +
		" from oi_am_letters,OI_AD_PROFILE where status='P' AND to_date(SYSDATE,'dd/mm/yyyy') >= to_date(PUBLISHEDFROM,'dd/mm/yyyy') AND to_date(SYSDATE,'dd/mm/yyyy')>=to_date(PUBLISHEDTO,'dd/mm/yyyy')" +
		" AND PUBLISHONSITE='Y' AND CREATEDBY = USERID AND (  ";
		
		// For topic
		for (int i = 0; i < alTokens.size(); i++) {
			query += " UPPER(topic) LIKE UPPER('%";
			query += alTokens.get(i);
			query += "%') OR ";
		}
		
		//		 For Subject
		for (int i = 0; i < alTokens.size(); i++) {
			query += " UPPER(SUBJECT) LIKE UPPER('%";
			query += alTokens.get(i);
			if (i < alTokens.size() - 1) query += "%') OR ";
		}
		query += "%\'))";
		
		logger.info("constructASMSearchQuery is*******"+query);
		return query;
}
	
	public String constructPaperSearchQuery (ArrayList alTokens) {
		String ret = OISearchSqls.QRY_SIMPLE_SEARCH_CP_SELECT;
		for (int i = 0; i < alTokens.size(); i++) {
			ret += OISearchSqls.QRY_SIMPLE_SEARCH_CP_TITLE;
			ret += alTokens.get(i);
			ret += OISearchSqls.QRY_SIMPLE_SEARCH_CLOSE_OR;
		}
		for (int i = 0; i < alTokens.size(); i++) {
			ret += OISearchSqls.QRY_SIMPLE_SEARCH_CP_DESC;
			ret += alTokens.get(i);
			ret += OISearchSqls.QRY_SIMPLE_SEARCH_CLOSE_OR;
		}
		for (int i = 0; i < alTokens.size(); i++) {
			ret += OISearchSqls.QRY_SIMPLE_SEARCH_CP_SUMMARY;
			ret += alTokens.get(i);
			if (i < alTokens.size() - 1) ret += OISearchSqls.QRY_SIMPLE_SEARCH_CLOSE_OR;
		}
		ret += OISearchSqls.QRY_SIMPLE_SEARCH_CLOSE;
		logger.info(ret);
		return ret;
	}
	
	public String constructBlogSearchQuery (ArrayList alTokens)
	{
		String ret = OISearchSqls.QRY_SIMPLE_SEARCH_BG_SELECT;
		for (int i = 0; i < alTokens.size(); i++)
		{
			ret += OISearchSqls.QRY_SIMPLE_SEARCH_BG_TAG;
			ret += alTokens.get(i);
			ret += OISearchSqls.QRY_SIMPLE_SEARCH_CLOSE_OR;
		}
		
		for (int i = 0; i < alTokens.size(); i++)
		{
			ret += OISearchSqls.QRY_SIMPLE_SEARCH_BG_ENTRY_EXCERPT;
			ret += alTokens.get(i);
			ret += OISearchSqls.QRY_SIMPLE_SEARCH_CLOSE_OR;
		}
		
		for (int i = 0; i < alTokens.size(); i++)
		{
			ret += OISearchSqls.QRY_SIMPLE_SEARCH_BG_ENTRY_BODY;
			ret += alTokens.get(i);
			ret += OISearchSqls.QRY_SIMPLE_SEARCH_CLOSE_OR;
		}
		
		for (int i = 0; i < alTokens.size(); i++)
		{
			ret += OISearchSqls.QRY_SIMPLE_SEARCH_BG_TITLE;
			ret += alTokens.get(i);
			if (i < alTokens.size() - 1) ret += OISearchSqls.QRY_SIMPLE_SEARCH_CLOSE_OR;
		}
		ret += OISearchSqls.QRY_SIMPLE_SEARCH_CLOSE;
		
		logger.info(ret);
		return ret;
	}
	
	public OIResponseObject getPageSize () {
		int ret = 0;
        OIDAOSearch objSearch = new OIDAOSearch();
        try {
            getConnection();
            ret = objSearch.getNumPerPage(connection);		
        } catch (SQLException se) {
            error = ""+se.getErrorCode();
            logger.error("getPageSize - SQLException" + se);
        } catch (Exception e) {
            error = "OIDEFAULT";
            logger.error("getPageSize" + e);
        } finally {
            freeConnection();
            addToResponseObject();
            responseObject.addResponseObject("PageSize", new Integer(ret));
        }
        return responseObject;
	}
	
	public OIResponseObject getTotalRec(String query, String userID) {
		int ret = 0;
        OIDAOSearch objSearch = new OIDAOSearch();
        try {
            getConnection();
            ret = objSearch.getCount(connection, query, userID);	
        } catch (SQLException se) {
            error = ""+se.getErrorCode();
            logger.error("getPageSize - SQLException" + se);
        } catch (Exception e) {
            error = "OIDEFAULT";
            logger.error("getPageSize" + e);
        } finally {
            freeConnection();
            addToResponseObject();
            responseObject.addResponseObject("TotalRecord", new Integer(ret));
        }
        return responseObject;
	}
	
	public OIResponseObject getTotalRec(String query) {
		int ret = 0;
        OIDAOSearch objSearch = new OIDAOSearch();
        try {
            getConnection();
            ret = objSearch.getCount(connection, query);	
        } catch (SQLException se) {
            error = ""+se.getErrorCode();
            logger.error("getPageSize - SQLException" + se);
        } catch (Exception e) {
            error = "OIDEFAULT";
            logger.error("getPageSize" + e);
        } finally {
            freeConnection();
            addToResponseObject();
            responseObject.addResponseObject("TotalRecord", new Integer(ret));
        }
        return responseObject;
	}
	
	public OIResponseObject doSearch(String query, String userID, int row1, int row2) {
        ArrayList alResult = null;
        OIDAOSearch objSearch = new OIDAOSearch();
        try {
            getConnection();
            alResult = objSearch.getSearchResult(connection, query, userID, row1, row2);
            assignExpressions(alResult);
        } catch (SQLException se) {
            error = ""+se.getErrorCode();
            logger.error("doSearch - SQLException" + se);
        } catch (Exception e) {
            error = "OIDEFAULT";
            logger.error("doSearch - " + e);
        } finally {
            freeConnection();
            addToResponseObject();
            responseObject.addResponseObject("SearchResult", alResult);
        }
        return responseObject;
    }
	
	public OIResponseObject doSearch(String query, int row1, int row2) {
        ArrayList alResult = null;
        OIDAOSearch objSearch = new OIDAOSearch();
        try {
            getConnection();
            alResult = objSearch.getSearchResult(connection, query, row1, row2);
            assignExpressions(alResult);
        } catch (SQLException se) {
            error = ""+se.getErrorCode();
            logger.error("doSearch - SQLException" + se);
        } catch (Exception e) {
            error = "OIDEFAULT";
            logger.error("doSearch - " + e);
        } finally {
            freeConnection();
            addToResponseObject();
            responseObject.addResponseObject("SearchResult", alResult);
        }
        return responseObject;
    }
	
	private void assignExpressions(ArrayList alResultList) {
		OISearchResultBean objResult = null;
		String strImagesRoot = OIUtilities.getUploadFilesDir("OIFM.docroot");
		String strImgPart1 = "<img src='"+strImagesRoot;
		String strImgPart2 = "' border='0'>";
		String strPosting = "";
		if(alResultList != null && alResultList.size() > 0)	{
			for (int j=0; j<alResultList.size(); j++)	{
				objResult = (OISearchResultBean) alResultList.get(j);
				strPosting = OIDAOPosting.allocateExpressions(strImgPart1, strImgPart2, objResult.getStrDescription()); 
				objResult.setStrDescription(strPosting);
			}
		}
	}
}
