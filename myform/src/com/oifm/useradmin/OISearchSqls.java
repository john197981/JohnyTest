package com.oifm.useradmin;

public interface OISearchSqls  
{
	/** Search Query  for Select Users**/ 
	 
	public static final String SELECT 		= " SELECT USERID, ROWNUM NUM , NAME  ,";
	public static final String SELECT_SU	= " SELECT DISTINCT PROFILE.USERID, PROFILE.NAME, DECODE(MEMBER.SURVEYID,?,'D','E')ACTIVE ,";
	public static final String SELECT_SUBQUERY  = " SELECT USERID, NAME, ACTIVE, ROWNUM NUM , ";
			
	
	public static final String FROM_WHERE 	= " FROM OI_AD_PROFILE PROFILE,OI_CP_MEMBERS MEMBER WHERE ";
	public static final String FROM_WHERE_SU 	= " FROM OI_AD_PROFILE PROFILE,OI_SU_MEMBERS MEMBER WHERE ";
	public static final String FROM_WHERE_SUBQRY  	= "  WHERE ";
	public static final String LIKE 		= " LIKE UPPER( ";
	public static final String PERCENTAGE 	= "%"; 
	public static final String EQUALGREATER	= " >= ";
	public static final String LESSTHANEQUAL= " <= ";
	
	public static final String EQUAL 		= " = ";
	public static final String COMMA 		= ",";
	public static final String AND			= " AND ";
	public static final String QRYCCA1      = " (CCA_1 LIKE UPPER('";       
	public static final String QRYCCA2      = "') OR CCA_2 LIKE UPPER ('" ; 
	public static final String QRYCCA3      = " FROM OI_AD_PROFILE PROFILE, OI_CP_MEMBERS MEMBER, OI_AD_SUBLVLTEACH TEACH WHERE PROFILE.USERID = TEACH.USERID ";
	public static final String QRYCCA3_SU   = " FROM OI_AD_PROFILE PROFILE, OI_SU_MEMBERS MEMBER, OI_AD_SUBLVLTEACH TEACH WHERE PROFILE.USERID = TEACH.USERID ";
	public static final String QRYPAPERID	= "	AND MEMBER.PAPERID = " ;
	public static final String QRYSURVEYID	= "	AND MEMBER.SURVEYID = " ;
	
	public static final String QRYROW1 		= " SELECT * FROM ( ";
	public static final String QRYROW2 		= " ) WHERE NUM >= ? AND NUM <= ? ";
	
	
		
	public static final String SRCHSUBQUERY =   "(SELECT DISTINCT PROFILE.USERID ";
	     
												/*"ROLEID,NAME,SCHOOLS.SCHOOLNAME 0SCHOOL,NICKNAME,SCHLVL.DESCRIPTION SCHOOL_TYPE, "+
												"EMAILID,GRADE,ROUND((SYSDATE - BIRTH_DT)/(365)) AGE, "+
												"CDDESIGNATION.DESCRIPTION DESIGNATION,INTEREST, "+
												"CDDIVISION.DESCRIPTION DIVISION,CCA_1, CCA_2, "+									
											    "CDMASTER.DESCRIPTION BRANCH_ZONE,CDCLUSTER.DESCRIPTION SCHOOL_CLUSTER, "+  
												"HOBBIES,TO_CHAR(PROFILE.JOINING_DT,'YYYY') JOINDT,  DIV_STATUS   "+
											 	"FROM OI_AD_PROFILE PROFILE,  "+
												"OI_AD_SUBLVLTEACH TEACH, OI_AD_SCHOOLS SCHOOLS, "+ 
												"OI_AD_CODE_MASTER CDMASTER ,OI_AD_CODE_MASTER CDCLUSTER, OI_AD_CODE_MASTER SCHLVL, "+
												"OI_AD_CODE_MASTER CDDESIGNATION, OI_AD_CODE_MASTER CDDIVISION "+
												"WHERE PROFILE.USERID = TEACH.USERID(+) "+
												"AND PROFILE.SCHOOLCODE= SCHOOLS.SCHOOLCODE(+) "+
												"AND PROFILE.ZONEBRANCHCODE=CDMASTER.VALUE(+) "+
												"AND (CDMASTER.TYPE='BRANCHZONE_CODE' OR CDMASTER.TYPE IS NULL) "+ 
												"AND PROFILE.CLUSTERCODE=CDCLUSTER.VALUE(+) "+
												"AND (CDCLUSTER.TYPE='SCHOOL_CLUSTER' OR CDCLUSTER.TYPE IS NULL) "+ 
												"AND PROFILE.SCHOOLLVLCODE=SCHLVL.VALUE(+) "+
												"AND (CDDESIGNATION.TYPE='DESIGNATION_CODE' OR CDDESIGNATION.TYPE IS NULL) "+
												"AND PROFILE.DESIGNATIONCODE = CDDESIGNATION.VALUE(+) "+
												"AND (CDDIVISION.TYPE='DIVISION_CODE' OR CDDIVISION.TYPE IS NULL) "+
												"AND PROFILE.DIVISIONCODE = CDDIVISION.VALUE(+)  ";
	
												*/
	
	public static final String SLTCOUNT = "SELECT COUNT(*) FROM ( " ;
	/** Search Query  for Select Groups**/
	public static final String QRY_SLTGRPS = " SELECT GROUPID,NAME,DESCRIPTION,GROUP_TYPE FROM OI_FM_GROUPS order by group_type asc, name asc" ;
	public static final String QRY_SLTURS1 =  " SELECT USERID FROM OI_FM_GROUPMEMBERS MEMBER WHERE  GROUPID IN ( ";
	public static final String QRY_SLTURS2 =  " AND USERID NOT IN (SELECT USERID FROM OI_CP_MEMBERS MEMBER WHERE MEMBER.PAPERID = "; 
	public static final String QRY_SLTURS2_SU =  " AND USERID NOT IN (SELECT USERID FROM OI_SU_MEMBERS MEMBER WHERE MEMBER.SURVEYID = ";
	
	
	/** Insert Query to Consulation Paper **/
	public static final String QRY_ADD_USERS_CP =" INSERT INTO OI_CP_MEMBERS (MEMBERID,PAPERID,USERID,INVITATION_DT,SUBMITTED,SUBMITTED_ON) VALUES( SEQ_OI_CP_MEMBERID.NEXTVAL, ?, ?, ?, ?, ?)";
	
	/** Insert Query to Survey **/
	public static final String QRY_ADD_USERS_SU =" INSERT INTO OI_SU_MEMBERS (MEMBERID,SURVEYID,USERID,INVITATION_DT,SUBMITTED_ST,SUBMITTED_ON) VALUES( SEQ_OI_SU_MEMBERID.NEXTVAL, ?, ?, ?, ?, ?)";
	
	/** Query to fetch List of School **/
	public static final String QRY_SCHOOL =  "SELECT DISTINCT SCHOOL.SCHOOLCODE,SCHOOLNAME  FROM OI_AD_SCHOOLS SCHOOL, OI_AD_PROFILE PROFILE WHERE SCHOOL.SCHOOLCODE = PROFILE.SCHOOLCODE";
	
	/** Query to fetch List of Cluster **/
	public static final String QRY_CLUSTER ="SELECT DISTINCT MASTER.VALUE,DESCRIPTION FROM  OI_AD_CODE_MASTER MASTER, OI_AD_PROFILE PROFILE WHERE MASTER.TYPE='SCHOOL_CLUSTER' AND MASTER.VALUE=PROFILE.CLUSTERCODE"; 
	
	/** Query to fetch List of School Type**/
	public static final String QRY_SCHTYP = "SELECT DISTINCT MASTER.VALUE,DESCRIPTION FROM  OI_AD_CODE_MASTER MASTER, OI_AD_PROFILE PROFILE WHERE MASTER.TYPE='SCHOOL_LEVEL' AND MASTER.VALUE=PROFILE.SCHOOLLVLCODE";

	public static final String QRY_AGE = " ROUND((SYSDATE - BIRTH_DT)/(365)) AGE  ";
	
	public static final String QRY_AGE_WHERE = " ROUND((SYSDATE - BIRTH_DT)/(365)) ";

	public static final String QRY_USERID_CP =  "SELECT USERID FROM OI_CP_MEMBERS WHERE PAPERID = ?";
	public static final String QRY_USERID_SU =  "SELECT USERID FROM OI_SU_MEMBERS WHERE SURVEYID = ?"; 
	
	public static final String QRY_USERID_BOARD="SELECT USERID FROM OI_FM_BOARDMODADMIN WHERE BOARDID = ?" ;

	public static final String QRY_USERID_GROUPS="SELECT USERID FROM OI_FM_GROUPMEMBERS WHERE GROUPID = ?";

	
	
	public static final String QRY_MEMBER_PROFILE = "select USERID, NICKNAME, ROLEID, SALUTATION, JOINING_DT, BIRTH_DT, GRADE, DIV_STATUS, NAME, EMAILID, HOBBIES, INTEREST, TOTALSURVEYS, TOTALPAPERS, CREATED_ON, SHOW_SIGNATURE, SIGNATURE, UPDATED_ON, OBSOLETE,total.TOTALPOSTINGS from OI_AD_PROFILE profile, (select count(post.POSTID) TOTALPOSTINGS from OI_FM_POSTS post where post.POSTED_BY = ? and post.MODERATION_STAT='Y') total where profile.USERID = ?";
	
	public static final String QRY_PROFILE_UPDATE_PAPERS = "update OI_AD_PROFILE  set TOTALPAPERS = NVL(TOTALPAPERS,0) + 1 where upper(USERID) = upper(?)";
	
	/** Query to Insert the Users to Board Admin **/
	 public static final String QRY_BOARD_INS ="INSERT INTO OI_FM_BOARDMODADMIN (BOARDMODADMINID, BOARDID, USERID) VALUES  (SEQ_OI_FM_BOARDMODADMINID.NEXTVAL, ?, ?)";
	 
	 public static final String QRY_GROUPS_INS ="INSERT INTO OI_FM_GROUPMEMBERS(GROUPMEMBERID, GROUPID, USERID) VALUES (SEQ_OI_FM_GROUPMEMBERID.NEXTVAL, ?, ?)";
	
	 //public static final String QRY_ADMIN = " AND (PROFILE.USERID IN (SELECT USERID FROM OI_AD_PROFILE WHERE ROLEID IN (SELECT ROLEID FROM OI_AD_FUNCTIONROLE WHERE FUNCTIONID IN('MNTBORD','MTNTOPI') "+ 
	 public static final String QRY_ADMIN = " (PROFILE.USERID IN (SELECT USERID FROM OI_AD_PROFILE WHERE ROLEID IN (SELECT ROLEID FROM OI_AD_FUNCTIONROLE WHERE FUNCTIONID IN('MNTPROF') "+
											" MINUS SELECT ROLEID FROM OI_AD_FUNCTIONROLE WHERE FUNCTIONID IN('MTNCTBD'))) " +
											" OR PROFILE.USERID IN (SELECT USERID FROM OI_AD_PROFILE WHERE ROLEID IN (SELECT ROLEID FROM OI_AD_FUNCTIONROLE WHERE FUNCTIONID IN ('MODPOST') "+
	 										" MINUS SELECT ROLEID FROM OI_AD_FUNCTIONROLE WHERE FUNCTIONID IN('MNTPROF','MTNCTBD'))) " +
	 										" )";
										 
	 
	 public static final String QRY_MODERATOR =" PROFILE.USERID IN (SELECT USERID FROM OI_AD_PROFILE WHERE ROLEID IN (SELECT ROLEID FROM OI_AD_FUNCTIONROLE WHERE FUNCTIONID IN ('MODPOST') "+
	 										   " MINUS SELECT ROLEID FROM OI_AD_FUNCTIONROLE WHERE FUNCTIONID IN('MNTPROF','MTNCTBD'))) ";
	 
	 public static final String QRY_ADMINNAME ="SELECT DISTINCT ROLEID FROM OI_AD_FUNCTIONROLE WHERE FUNCTIONID IN('MTNCTBD')";
	 
	 //anbu added- discussion forum 
	 public static final String QRY_TOTAL_POSTING = "select count(1) TOTALPOSTINGS from OI_FM_POSTS post where post.POSTED_BY = ?";
	 
//Summary Details
	 //Discussion forum-Age
	 public static final String QRY_SMRY_DET_DF_AGE="select nvl(DESCRIPTION,'Not Known') AGEGROUP, COUNT(USER_ID) NO_OF_PARTICIPANTS, SUM(NO_OF_POSTS) POSTS  from "+
		"(select count(M.POSTID) NO_OF_POSTS,M.POSTED_BY USER_ID, "+
		"DECODE(SIGN(ROUND((SYSDATE-nvl(P.BIRTH_DT,SYSDATE))/365)-0) * greatest(least(ROUND((SYSDATE-nvl(P.BIRTH_DT,SYSDATE))/365),'40'),'30') "+
		",'0','Not Known','30','Less than 30 Years Old','40','Above 40 yrs old','Between 30 to 40 yrs old') DESCRIPTION "+
		"from OI_FM_POSTS M,oi_ad_profile P,OI_AD_CODE_MASTER D  where M.POSTED_BY=P.USERID "+
		"and P.DESIGNATIONCODE =D.VALUE (+) "+
		"and (D.TYPE is null or D.TYPE='DESIGNATION_CODE') "+
		"GROUP BY M.POSTED_BY,DESCRIPTION,P.BIRTH_DT "+
		") GROUP BY DESCRIPTION";
		 

	 //Discussion forum-Designation
	 public static final String QRY_SMRY_DET_DF_DESG="select nvl(DESIGNATION,'Not Known') DESIGNATION, COUNT(USER_ID) NO_OF_PARTICIPANTS, SUM(NO_OF_POSTS) POSTS  from "+
		"(select count(M.POSTID) NO_OF_POSTS,M.POSTED_BY USER_ID,D.DESCRIPTION DESIGNATION  "+
		"from OI_FM_POSTS M,oi_ad_profile P,OI_AD_CODE_MASTER D  where M.POSTED_BY=P.USERID "+
		"and P.DESIGNATIONCODE =D.VALUE (+) "+
		"and (D.TYPE is null or D.TYPE='DESIGNATION_CODE') "+
		"GROUP BY M.POSTED_BY,DESCRIPTION "+
		") GROUP BY DESIGNATION ";

 	 //Discussion forum-Schoollevel
	 public static final String QRY_SMRY_DET_DF_SCHLVL="select nvl(DESCRIPTION,'Not Known') SCHOOL_LEVEL, COUNT(USER_ID) NO_OF_PARTICIPANTS, SUM(NO_OF_POSTS) POSTS  from "+
		"(select count(M.POSTID) NO_OF_POSTS,M.POSTED_BY USER_ID, "+
		"S.DESCRIPTION DESCRIPTION "+
		"from OI_FM_POSTS M,oi_ad_profile P,OI_AD_CODE_MASTER S  where M.POSTED_BY=P.USERID "+
		"and P.SCHOOLLVLCODE =S.VALUE (+) "+
		"and (S.TYPE is null or S.TYPE='SCHOOL_LEVEL') "+
		"GROUP BY M.POSTED_BY,DESCRIPTION "+
		") GROUP BY DESCRIPTION ";
	
	 //Consultation Paper -Age
	 public static final String QRY_SMRY_DET_CP_AGE = "select nvl(DESCRIPTION,'Not Known') AGEGROUP, COUNT(USER_ID) NO_OF_PARTICIPANTS, SUM(CONSULTATION_PAPERS) TOTAL_PAPERS  from "+
		"(select count(M.DRAFTID) CONSULTATION_PAPERS,M.USERID USER_ID, "+
		"DECODE(SIGN(ROUND((SYSDATE-nvl(P.BIRTH_DT,SYSDATE))/365)-0) * greatest(least(ROUND((SYSDATE-nvl(P.BIRTH_DT,SYSDATE))/365),'40'),'30') "+
		",'0','Not Known','30','Less than 30 Years Old','40','Above 40 yrs old','Between 30 to 40 yrs old') DESCRIPTION "+
		"from OI_CPSU_DRAFTS M,oi_ad_profile P,OI_AD_CODE_MASTER D  where M.USERID=P.USERID "+
		" AND M.DRAFT_TYPE='C' AND STATUS='S' "+
		"and P.DESIGNATIONCODE =D.VALUE (+) "+
		"and (D.TYPE is null or D.TYPE='DESIGNATION_CODE') "+
		"GROUP BY M.USERID,DESCRIPTION,P.BIRTH_DT "+
		") GROUP BY DESCRIPTION";
		 
	 
	 //Consultation Paper -Designation
	 public static final String QRY_SMRY_DET_CP_DESG="select nvl(DESIGNATION,'Not Known') DESIGNATION, COUNT(USER_ID) NO_OF_PARTICIPANTS, SUM(CONSULTATION_PAPERS) TOTAL_PAPERS  from "+
			"(select count(M.DRAFTID) CONSULTATION_PAPERS,M.USERID USER_ID,D.DESCRIPTION DESIGNATION "+
			"from OI_CPSU_DRAFTS M,oi_ad_profile P,OI_AD_CODE_MASTER D  where  M.DRAFT_TYPE='C' AND STATUS='S' "+
			"and M.UserID=P.USERID "+
			"and P.DESIGNATIONCODE =D.VALUE (+) "+
			"and (D.TYPE is null or D.TYPE='DESIGNATION_CODE') "+
			"GROUP BY M.USERID,D.DESCRIPTION "+
			") GROUP BY DESIGNATION "; 

	 //Consultation Paper -School Level
	 public static final String QRY_SMRY_DET_CP_SCHLVL="select nvl(DESCRIPTION,'Not Known') SCHOOL_LEVEL, COUNT(USER_ID) NO_OF_PARTICIPANTS, SUM(CONSULTATION_PAPERS) TOTAL_PAPERS  from "+
			"(select count(M.DRAFTID) CONSULTATION_PAPERS,M.USERID USER_ID, "+
			"S.DESCRIPTION DESCRIPTION "+
			"from OI_CPSU_DRAFTS M,oi_ad_profile P,OI_AD_CODE_MASTER S  where M.USERID=P.USERID "+
			" AND M.DRAFT_TYPE='C' AND STATUS='S' "+
			"and P.SCHOOLLVLCODE =S.VALUE (+) "+
			"and (S.TYPE is null or S.TYPE='SCHOOL_LEVEL') "+
			"GROUP BY M.USERID,DESCRIPTION "+
			") GROUP BY DESCRIPTION"; 
	 
	 //Survey -Age
	 public static final String QRY_SMRY_DET_SV_AGE = "select nvl(DESCRIPTION,'Not Known') AGEGROUP, COUNT(USER_ID) NO_OF_PARTICIPANTS, SUM(SURVEYS) TOTAL_SURVEYS  from "+
		"(select count(M.DRAFTID) SURVEYS,M.USERID USER_ID, "+
		"DECODE(SIGN(ROUND((SYSDATE-nvl(P.BIRTH_DT,SYSDATE))/365)-0) * greatest(least(ROUND((SYSDATE-nvl(P.BIRTH_DT,SYSDATE))/365),'40'),'30') "+
		",'0','Not Known','30','Less than 30 Years Old','40','Above 40 yrs old','Between 30 to 40 yrs old') DESCRIPTION "+
		"from OI_CPSU_DRAFTS M,oi_ad_profile P,OI_AD_CODE_MASTER D  where M.USERID=P.USERID "+
		" AND M.DRAFT_TYPE='S' AND STATUS='S' "+
		"and P.DESIGNATIONCODE =D.VALUE (+) "+
		"and (D.TYPE is null or D.TYPE='DESIGNATION_CODE') "+
		"GROUP BY M.USERID,DESCRIPTION,P.BIRTH_DT "+
		") GROUP BY DESCRIPTION ";
		 
	 
	 //Survey -Designation
	 public static final String QRY_SMRY_DET_SV_DESG="select nvl(DESIGNATION,'Not Known') DESIGNATION, COUNT(USER_ID) NO_OF_PARTICIPANTS, SUM(SURVEYS) TOTAL_SURVEYS  from "+
			"(select count(M.DRAFTID) SURVEYS,M.USERID USER_ID,D.DESCRIPTION DESIGNATION "+
			"from OI_CPSU_DRAFTS M,oi_ad_profile P,OI_AD_CODE_MASTER D  where  M.DRAFT_TYPE='S' AND STATUS='S' "+
			"and M.UserID=P.USERID "+
			"and P.DESIGNATIONCODE =D.VALUE (+) "+
			"and (D.TYPE is null or D.TYPE='DESIGNATION_CODE') "+
			"GROUP BY M.USERID,D.DESCRIPTION "+
			") GROUP BY DESIGNATION "; 
	
	
	 //Survey -School Level
	 public static final String QRY_SMRY_DET_SV_SCHLVL= "select nvl(DESCRIPTION,'Not Known') SCHOOL_LEVEL, COUNT(USER_ID) NO_OF_PARTICIPANTS, SUM(SURVEYS) TOTAL_SURVEYS  from "+
			"(select count(M.DRAFTID) SURVEYS,M.USERID USER_ID, "+
			"S.DESCRIPTION DESCRIPTION "+
			"from OI_CPSU_DRAFTS M,oi_ad_profile P,OI_AD_CODE_MASTER S  where M.USERID=P.USERID "+
			" AND M.DRAFT_TYPE='S' AND STATUS='S' "+
			"and P.SCHOOLLVLCODE =S.VALUE (+) "+
			"and (S.TYPE is null or S.TYPE='SCHOOL_LEVEL') "+
			"GROUP BY M.USERID,DESCRIPTION "+
			") GROUP BY DESCRIPTION";
				

} 