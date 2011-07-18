/*
 * File name	= ASMGlobals.java
 * Package		= com.oifm.asm
 * Created on 	= Dec 14, 2005
 * Created by	= Oscar
 * Copyright	= Scandent Group
 *
 */

package com.oifm.asm;


public final class ASMGlobals {
	
	//******************************************************
	//*                     CONSTANTS                      *
	//******************************************************
	
		//Error redirect
			public static final String ERR_REDIRECT = "/error.do";
	
	//******************************************************
	//*                    SQL QUERIES                     *
	//******************************************************
	
		/* Common */
	
			//Queries for pagination
			public static final String QRY_COUNT = "SELECT COUNT(*) COUNT FROM ( ";
			public static final String QRY_ROW1 = "SELECT * FROM ( ";
			public static final String QRY_ROW2 = " ) WHERE NUM >= ? AND NUM <= ? ";
			
			//Queries for retrieving page sizes
			public static final String QRY_LETTER_PAGE_SIZE = "SELECT DESCRIPTION FROM OI_AD_CODE_MASTER WHERE VALUE = 'ASMLETTERPAGESIZE'";
			public static final String QRY_EDITORS_NOTE_PAGE_SIZE = "SELECT DESCRIPTION FROM OI_AD_CODE_MASTER WHERE VALUE = 'ASMEDITORSNOTESIZE'";
	
	
		/* About ASM */
	
			//Query to retrieve About ASM entries
			public static final String QRY_ABOUT_RETRIEVE_LIST = "SELECT ROWNUM NUM, CONTENTID, TOPIC, CONTENT FROM OI_AM_CONTENT WHERE CONTENTTYPE = 'B' ORDER BY TOPIC ASC";
	
			//Query to retrieve an entry
			public static final String QRY_ABOUT_RETRIEVE = "SELECT TOPIC, CONTENT FROM OI_AM_CONTENT WHERE CONTENTID = ?";
	
			//Query to create new entry
			public static final String QRY_ABOUT_CREATE = "INSERT INTO OI_AM_CONTENT(CONTENTID, CONTENTTYPE, TOPIC, CONTENT, CREATEDON, CREATEDBY, UPDATEDON, UPDATEDBY) VALUES (SEQ_OI_AM_CONTENTID.NEXTVAL, 'B', ?, ?, SYSDATE, ?, SYSDATE, ?)";
	
			//Query to modify an entry
			public static final String QRY_ABOUT_MODIFY = "UPDATE OI_AM_CONTENT SET TOPIC = ?, CONTENT = ?, UPDATEDON = SYSDATE, UPDATEDBY = ? WHERE CONTENTID = ?";
	
			//Query to delete an entry
			public static final String QRY_ABOUT_DELETE = "DELETE FROM OI_AM_CONTENT WHERE CONTENTID = ?";
	
			
		/* Editor's Note */
			
			//Query to retrieve list of entries for admin page
			public static final String QRY_EDITORS_LIST_FULL = "Select ROWNUM NUM, a.* from (SELECT CONTENTID, EDITON, TOPIC, CONTENT FROM OI_AM_CONTENT WHERE CONTENTTYPE = 'E' ORDER BY EDITON DESC, CONTENTID DESC) a";
			
			//Query to retrieve list of entries for user page
			public static final String QRY_EDITORS_LIST = "Select ROWNUM NUM, a.* from (SELECT CONTENTID, EDITON, TOPIC, CONTENT FROM OI_AM_CONTENT WHERE CONTENTTYPE = 'E' AND EDITON <= SYSDATE ORDER BY EDITON DESC, CONTENTID DESC) a";
			
			//Query to retrieve details of an entry
			public static final String QRY_EDITORS_DETAILS = "SELECT EDITON, TOPIC, CONTENT FROM OI_AM_CONTENT WHERE CONTENTID = ?";
			
			//Query to create new entry
			public static final String QRY_EDITORS_CREATE = "INSERT INTO OI_AM_CONTENT(CONTENTID, CONTENTTYPE, EDITON, TOPIC, CONTENT, CREATEDON, CREATEDBY, UPDATEDON, UPDATEDBY) VALUES (SEQ_OI_AM_CONTENTID.NEXTVAL, 'E', ?, ?, ?, SYSDATE, ?, SYSDATE, ?)";
			
			//Query to modify an entry
			public static final String QRY_EDITORS_MODIFY = "UPDATE OI_AM_CONTENT SET EDITON = ?, TOPIC = ?, CONTENT = ?, UPDATEDON = SYSDATE, UPDATEDBY = ? WHERE CONTENTID = ?";
			
			//Query to delete an entry
			public static final String QRY_EDITORS_DELETE = "DELETE FROM OI_AM_CONTENT WHERE CONTENTID = ?";
	
			
		/* Announcement */
			
			//Query to retrieve announcement
			public static final String QRY_ANNOUNCE_RETRIEVE = "SELECT CONTENT FROM OI_AM_CONTENT WHERE CONTENTTYPE = 'N'";
			
			//Query to save announcement
			public static final String QRY_ANNOUNCE_SAVE = "UPDATE OI_AM_CONTENT SET CONTENT = ?, UPDATEDON = SYSDATE, UPDATEDBY = ? WHERE CONTENTTYPE = 'N'";
	
			
		/* Senior Management */
			
			//Query to retrieve list
			public static final String QRY_MGMNT_LIST = "SELECT MGMTID, HEADING, SUBHEADING, NAME, DESIGNATION, DIVISION, PROFILE, DIVISIONURL, PHOTOGRAPH FROM OI_AM_MANAGEMENT ORDER BY HEADING ASC, SUBHEADING ASC";
			
			//Query to retrieve detail
			public static final String QRY_MGMNT_DETAIL = "SELECT MGMTID, HEADING, SUBHEADING, NAME, DESIGNATION, DIVISION, PROFILE, DIVISIONURL, PHOTOGRAPH FROM OI_AM_MANAGEMENT WHERE MGMTID = ?";
			
			//Query to create new profile
			public static final String QRY_MGMNT_CREATE = "INSERT INTO OI_AM_MANAGEMENT(MGMTID, HEADING, SUBHEADING, NAME, DESIGNATION, DIVISION, PROFILE, DIVISIONURL, CREATEDON, CREATEDBY, UPDATEDON, UPDATEDBY) VALUES (SEQ_OI_AM_MGMTID.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, SYSDATE, ?, SYSDATE, ?)";
			
			//Query to modify a profile
			public static final String QRY_MGMNT_MODIFY = "UPDATE OI_AM_MANAGEMENT SET HEADING = ?, SUBHEADING = ?, NAME = ?, DESIGNATION = ?, DIVISION = ?, PROFILE = ?, DIVISIONURL = ?, PHOTOGRAPH = ?, UPDATEDON = SYSDATE, UPDATEDBY = ? WHERE MGMTID = ?";
			
			//Query to delete a profile
			public static final String QRY_MGMNT_DELETE = "DELETE FROM OI_AM_MANAGEMENT WHERE MGMTID = ?";
			
			//Query to retrieve id
			public static final String QRY_MGMNT_GET_ID = "SELECT MGMTID FROM OI_AM_MANAGEMENT WHERE NAME = ? ORDER BY MGMTID DESC";
			
			//Query to set photograph file name
			public static final String QRY_MGMNT_SET_PHOTO = "UPDATE OI_AM_MANAGEMENT SET PHOTOGRAPH = ? WHERE MGMTID = ?";
			
			//Query to remove photograph file name
			public static final String QRY_MGMNT_REM_PHOTO = "UPDATE OI_AM_MANAGEMENT SET PHOTOGRAPH = '' WHERE MGMTID = ?";
			
			//Query to retrieve website welcome message
			public static final String QRY_MGMNT_WELCOME_MSG = "SELECT VALUE FROM OI_AD_CONFIGURATION WHERE TAG = 'ASMMGMNTWELCOME'";

			/*Query for the list of category to be used for category letters.Added by Nimitta*/
			public static final String QRY_GET_ALL_CATEGORY = "select categoryid,name from oi_am_category";
			
			/*Query for the list of letters belonging to a particular category id.Added by Nimitta*/
			public static final String QRY_GET_ALL_LETTERS_FOR_CATEGORYID = "SELECT letterid,topic FROM OI_AM_LETTERS ";
			
			/*Query for the list of all editor notes .Added by Nimitta*/
			public static final String QRY_GET_ALL_EDITOR_NOTES = "SELECT CONTENTID,TOPIC FROM OI_AM_CONTENT WHERE contenttype ='E' AND EDITON <= SYSDATE ORDER BY EDITON DESC, CONTENTID DESC";
			
			/*Query for the list of all editor notes .Added by Nimitta*/
			public static final String QRY_CATID_FOR_LETTERID = "SELECT categoryid FROM OI_AM_LETTERS WHERE letterid = ? ";

			
			
}
