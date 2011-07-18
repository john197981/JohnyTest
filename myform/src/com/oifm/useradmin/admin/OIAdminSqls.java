/*
 * Created on Aug 4, 2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.oifm.useradmin.admin;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface OIAdminSqls {

	/** Query to Fetch the Role **/
	public static final String  QRY_ROLE = "SELECT DISTINCT ROLE.ROLEID FROM OI_AD_ROLE ROLE,OI_AD_PROFILE PROFILE WHERE ROLE.ROLEID = PROFILE.ROLEID";
	public static final String  QRY_ROLES = "SELECT ROLEID FROM OI_AD_ROLE";
	/** Query to fetch List of School **/
	public static final String QRY_SCHOOL = "SELECT DISTINCT SCHOOLNAME ,SCHOOL.SCHOOLCODE FROM OI_AD_SCHOOLS SCHOOL, OI_AD_PROFILE PROFILE WHERE " +
											"SCHOOL.SCHOOLCODE = PROFILE.SCHOOLCODE  ";
	
	
	/** Query to fetch List of Cluster **/
	public static final String QRY_CLUSTER = "SELECT DISTINCT DESCRIPTION,MASTER.VALUE FROM  OI_AD_CODE_MASTER MASTER, " +
											 "OI_AD_PROFILE PROFILE WHERE MASTER.TYPE='SCHOOL_CLUSTER' AND MASTER.VALUE=PROFILE.CLUSTERCODE "; 

	/** Query to fetch List of School Type**/
	public static final String QRY_SCHTYP = "SELECT DISTINCT DESCRIPTION,MASTER.VALUE FROM  OI_AD_CODE_MASTER MASTER, "+
											"OI_AD_PROFILE PROFILE WHERE MASTER.TYPE='SCHOOL_LEVEL' AND MASTER.VALUE=PROFILE.SCHOOLLVLCODE";

	/**Query to fetch the List of Designation **/
	public static final String QRY_DESIGNATION = "SELECT DISTINCT DESCRIPTION,MASTER.VALUE FROM  OI_AD_CODE_MASTER MASTER, "+ 
												 "OI_AD_PROFILE PROFILE WHERE MASTER.TYPE='DESIGNATION_CODE' AND MASTER.VALUE=PROFILE.DESIGNATIONCODE "; 
	
	/**Query to fetch the List of DIVISION **/
	public static final String QRY_DIVISION = "SELECT DISTINCT DESCRIPTION,MASTER.VALUE FROM  OI_AD_CODE_MASTER MASTER, "+
											   "OI_AD_PROFILE PROFILE WHERE MASTER.TYPE='DIVISION_CODE' AND MASTER.VALUE=PROFILE.DIVISIONCODE "; 
	
	/**Query to fetch the List of  BRANCHZONE**/
	public static final String QRY_BRANCHZONE = "SELECT DISTINCT DESCRIPTION,MASTER.VALUE FROM  OI_AD_CODE_MASTER MASTER, "+
											  "OI_AD_PROFILE PROFILE WHERE MASTER.TYPE='BRANCHZONE_CODE' AND MASTER.VALUE=PROFILE.ZONEBRANCHCODE";  

	
	public static final String QRY_DIV_STATUS = "SELECT DISTINCT DIV_STATUS,DIV_STATUS FROM OI_AD_PROFILE";
	
	public static final String QRY_GRADE = "SELECT DISTINCT GRADE FROM OI_AD_PROFILE";
	
	
	
	public static final String SRCHSUBQUERY =   "(SELECT DISTINCT PROFILE.USERID  "; 
	     												
											/*	"ROLEID,NAME,SCHOOLS.SCHOOLNAME SCHOOL,NICKNAME,SCHLVL.DESCRIPTION SCHOOL_TYPE, "+
												"EMAILID,GRADE,ROUND((SYSDATE - BIRTH_DT)/(30 * 12)) AGE, "+
												"CDDESIGNATION.DESCRIPTION DESIGNATION,INTEREST, "+
												"CDDIVISION.DESCRIPTION DIVISION,CCA_1, CCA_2, "+
																				
												
												"SCHOOLS.SCHOOLNAME SCHOOL "										
											    "SCHLVL.DESCRIPTION SCHOOL_TYPE "
											    "CDMASTER.DESCRIPTION BRANCH_ZONE "
											    "CDCLUSTER.DESCRIPTION SCHOOL_CLUSTER "
											    " CDDIVISION.DESCRIPTION DIVISION "
											    " CDDESIGNATION.DESCRIPTION DESIGNATION "
											    
											      
												"HOBBIES,TO_CHAR(PROFILE.JOINING_DT,'YYYY') JOINDT,  DIV_STATUS,   "+
												"PROFILE.SCHOOLCODE , SCHOOLLVLCODE, DESIGNATIONCODE, DIVISIONCODE, ZONEBRANCHCODE, CLUSTERCODE "+
											 	
												"FROM OI_AD_PROFILE PROFILE  --"+
												", OI_AD_SUBLVLTEACH TEACH "+
												", OI_AD_SCHOOLS SCHOOLS --"+ 
												", OI_AD_CODE_MASTER CDMASTER --"+
												", OI_AD_CODE_MASTER CDCLUSTER -- "+
												", OI_AD_CODE_MASTER SCHLVL -- "+
												", OI_AD_CODE_MASTER CDDESIGNATION "+
												", OI_AD_CODE_MASTER CDDIVISION "+
												"WHERE " +
												
												"PROFILE.USERID = TEACH.USERID(+) "+
												"PROFILE.SCHOOLCODE= SCHOOLS.SCHOOLCODE(+) AND "+
												"AND PROFILE.ZONEBRANCHCODE=CDMASTER.VALUE(+) AND (CDMASTER.TYPE='BRANCHZONE_CODE' OR CDMASTER.TYPE IS NULL) --"+ 
												"AND PROFILE.CLUSTERCODE=CDCLUSTER.VALUE(+) AND (CDCLUSTER.TYPE='SCHOOL_CLUSTER' OR CDCLUSTER.TYPE IS NULL) "+ 
												"AND PROFILE.SCHOOLLVLCODE=SCHLVL.VALUE(+) 
												 AND (CDDESIGNATION.TYPE='DESIGNATION_CODE' OR CDDESIGNATION.TYPE IS NULL) AND PROFILE.DESIGNATIONCODE = CDDESIGNATION.VALUE(+) 
												 AND (CDDIVISION.TYPE='DIVISION_CODE' OR CDDIVISION.TYPE IS NULL) "+
												"AND PROFILE.DIVISIONCODE = CDDIVISION.VALUE(+) ";
												*/
											
	public static final String ROLESQL =  "  UPPER(ROLEID) <>  ";
	public static final String SELECT 		= " SELECT ROWNUM NUM ,";
	public static final String SELECT_SU	= " SELECT DISTINCT ";
	public static final String SELECT_SUBQUERY  = " SELECT ROWNUM NUM , ";
		
	public static final String 	QRY_TEACH = "SELECT S_TEACH1 , S_TEACH2 ,  S_TEACH3 , S_TEACH4 , S_TEACH5, S_TEACH7 ,  S_TEACH6 , S_TEACH8 ,  S_TEACH9 ,  S_TEACH10 ,"+
											"L_TEACH1 , L_TEACH2  ,  L_TEACH3  ,  L_TEACH4  ,  L_TEACH5  ,  L_TEACH6  ,  L_TEACH7  ,  L_TEACH8  ,  L_TEACH9  ,  L_TEACH10 ,"+
											 "CCA_1     ,  CCA_2     FROM  OI_AD_SUBLVLTEACH WHERE USERID =  ?";   
	public static final String QRY_PROFILE = "  SELECT USERID, ROLEID , SALUTATION , NICKNAME,NAME,EMAILID , "+ 
											  "ROUND((SYSDATE - BIRTH_DT)/(30 * 12)) AGE,"+
											  "TO_CHAR(JOINING_DT,'YYYY') JOINDT, " + 
											  "(SELECT DISTINCT DESCRIPTION FROM OI_AD_CODE_MASTER,OI_AD_PROFILE WHERE TYPE='DESIGNATION_CODE' AND VALUE = DESIGNATIONCODE AND USERID = ? AND ROWNUM = 1 ) DESIGNATION ,"+
											  " GRADE, DIV_STATUS,"+
											  "(SELECT DISTINCT DESCRIPTION FROM OI_AD_CODE_MASTER,OI_AD_PROFILE WHERE TYPE='DIVISION_CODE' AND VALUE = DIVISIONCODE AND USERID = ? AND ROWNUM = 1 )  DIVISION,"+
											  "(SELECT DISTINCT DESCRIPTION FROM OI_AD_CODE_MASTER,OI_AD_PROFILE WHERE TYPE='BRANCHZONE_CODE' AND VALUE = ZONEBRANCHCODE AND USERID = ? AND ROWNUM = 1 )  ZONE,"+
											  "(SELECT DISTINCT DESCRIPTION FROM OI_AD_CODE_MASTER,OI_AD_PROFILE WHERE TYPE='SCHOOL_LEVEL' AND VALUE = SCHOOLLVLCODE AND USERID = ? AND ROWNUM = 1 )  SCHOOLLEVEL,"+
											  "(SELECT DISTINCT SCHOOLNAME FROM  OI_AD_SCHOOLS SCHOOL,OI_AD_PROFILE PROFILE WHERE SCHOOL.SCHOOLCODE = PROFILE.SCHOOLCODE AND USERID = ? AND ROWNUM = 1 )  SCHOOLCODE,"+
											  "(SELECT DISTINCT DESCRIPTION FROM OI_AD_CODE_MASTER,OI_AD_PROFILE WHERE TYPE='SCHOOL_CLUSTER' AND VALUE = CLUSTERCODE AND USERID = ? AND ROWNUM = 1 )  CLUSTERCODE,"+
											  "HOBBIES,  INTEREST,  SIGNATURE, SHOW_SIGNATURE,TOTALPOSTINGS,OBSOLETE  FROM OI_AD_PROFILE WHERE USERID = ?  AND ROWNUM = 1";

	
	
	
	 public static final String QRY_PROFILE_UPDATE ="UPDATE OI_AD_PROFILE SET ROLEID = ?, EMAILID = ?,SIGNATURE =?,INTEREST = ?, HOBBIES =? WHERE USERID = ?";

	 public static final String QRY_PROFILE_DISABLE_ENABLE ="UPDATE OI_AD_PROFILE SET OBSOLETE = ? WHERE USERID = ?";
	
	 public static final String QRY_TEACHLEVEL = " USERID =(SELECT DISTINCT USERID FROM OI_AD_SUBLVLTEACH WHERE UPPER(L_TEACH1 ";
	  
	 public static final String QRY_TEACH_UPPER =" OR UPPER(L_TEACH";
	 
	 public static final String QRY_LIKE =") LIKE UPPER('%";
	 
	 public static final String QRY_SINGLEQUOTE ="%') ";
	 
	 public static final String QRY_SUB = " USERID =(SELECT DISTINCT USERID FROM OI_AD_SUBLVLTEACH WHERE UPPER(S_TEACH1 ";
	  
	 public static final String QRY_SUB_UPPER =" OR UPPER(S_TEACH";
	  
	 
	 
	 public static final String QRY_BOOKMARKS ="SELECT BOOK.THREADID,BOOKMARKID,TITLE FROM OI_FM_BOOKMARKS BOOK ,OI_FM_THREAD THREADS WHERE USERID = ? AND BOOK.THREADID = THREADS.THREADID AND BKM_STICKY = ?"; 

	 public static final String QRY_WEB_PROFILE_UPDATE ="UPDATE OI_AD_PROFILE SET NICKNAME = ?,EMAILID = ?,INTEREST = ?,HOBBIES =?, SIGNATURE =?,SHOW_SIGNATURE= ? WHERE USERID = ?";
	 
	 public static final String QRY_DEL_BOOKMARKS ="DELETE FROM OI_FM_BOOKMARKS WHERE BOOKMARKID = ?";
	 
	 public static final String QRY_NICKNAME="SELECT NICKNAME FROM OI_AD_PROFILE WHERE  USERID <> ? AND UPPER(NICKNAME) = UPPER(?) ";
	 
	 public static final String QRY_USERPROFILE = " SELECT USERID,NAME,NICKNAME,EMAILID,INTEREST, HOBBIES, SIGNATURE, SHOW_SIGNATURE,POSTINGS.TOTALPOSTINGS FROM OI_AD_PROFILE, "+
												  "	(SELECT COUNT(POST.POSTID) TOTALPOSTINGS FROM OI_FM_POSTS POST WHERE POST.MODERATION_STAT='Y'  AND POSTED_BY = ? )POSTINGS "+
												  "	WHERE USERID =  ?	";
		 
	 public static final String QRY_MAIL_DOMAIN = "SELECT DESCRIPTION FROM OI_AD_CODE_MASTER WHERE TYPE = 'DOMAIN'";
}