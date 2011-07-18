/*************************************************************************************************
 * File Name		:	OISendMailSqls.java
 * Author			:	SureshKumar.R
 * Description		:   This file list of constant for the sql query.  			   		
 * Created Date		:	Jul 10 2005
 * Version			:	1.0
 * Copyright 		: Scandent Group
 ************************************************************************************************/

package com.oifm.common;

public interface OISendMailSqls  
{
	//public static final String	SAVE_CATEGORY = "insert into OI_CP_CATEGORY (CATEGORYID,NAME, CREATED_ON, CREATED_BY) values (SEQ_OI_CP_CATEGORYID.NEXTVAL,?,SYSDATE,?)";
	//public static final String	READ_CATEGORY = "select CATEGORYID,NAME from OI_CP_CATEGORY category where category.CATEGORYID=?";
	//public static final String	UPDATE_CATEGORY = "update OI_CP_CATEGORY set NAME=? where CATEGORYID=?"; 
    //public static final String	SAVE_CATEGORY = "insert into OI_CP_CATEGORY (NAME, CREATED_ON, CREATED_BY) values (?,SYSDATE,?)";
    //public static final String	CONSULT_LISTING = "select category.NAME aName,category.CATEGORYID aCategoryId,paper.PAPERID aPaperId,paper.TITLE aTitle, paper.DESCRIPTION aDescription, paper.FROM_DT aFromDt, paper.TO_DT aToDt, paper.MAIL_STATUS aMailStatus from OI_CP_CATEGORY category, OI_CP_PAPER paper where  paper.CATEGORYID = category.CATEGORYID"; 
    //public static final String	CONSULT_LISTING_FEEDBACK = "select feedback.PAPERID aPaperId, count(*) aNoFeedBacks from OI_CP_FEEDBACK feedback group by feedback.PAPERID";
    
//  If a person comes from Survey page and send mail
    public static final String SENDMAIL_MSG_DESC1 ="select DESCRIPTION from OI_AD_CODE_MASTER where type='MAIL_TEMPLATE' and VALUE LIKE 'SU_MAIL%'";
    
    		
        
//  If a person comes from Survey page and Remainder
    //public static final String SENDMAIL_MSG_DESC2 ="select DESCRIPTION from OI_AD_CODE_MASTER where type='MAIL_TEMPLATE' and VALUE LIKE 'SU_REMIN%'";

//  If a person comes from Consultation page and Send mail
    //public static final String SENDMAIL_MSG_DESC3 ="select DESCRIPTION from OI_AD_CODE_MASTER where type='MAIL_TEMPLATE' and VALUE LIKE 'CP_MAIL%'";
    
//  If a person comes from Consultation page and Remainder
    //public static final String SENDMAIL_MSG_DESC4 ="select DESCRIPTION from OI_AD_CODE_MASTER where type='MAIL_TEMPLATE' and VALUE LIKE 'CP_REMIN%'";   
    
    
    
     /**Consulation Paper**/
     //public static final String SELECT_CON_PAPER_MEM ="SELECT PROFILE.USERID, PROFILE.NAME , MEMBER.SUBMITTED ,PROFILE.TOTALPAPERS,PROFILE.TOTALSURVEYS,  INVITATION_DT FROM OI_CP_MEMBERS MEMBER, OI_AD_PROFILE PROFILE WHERE MEMBER.PAPERID = ?  AND MEMBER.USERID = PROFILE.USERID " ;
     public static final String SELECT_CON_PAPER_MEM ="select * from (select * from ( SELECT PROFILE.USERID, PROFILE.NAME , MEMBER.SUBMITTED , INVITATION_DT FROM OI_CP_MEMBERS MEMBER, OI_AD_PROFILE PROFILE WHERE MEMBER.PAPERID = ?  AND MEMBER.USERID =  PROFILE.USERID) su,(select count(*) TOTALSURVEYS,draft.USERID sid from OI_CPSU_DRAFTS draft where draft.DRAFT_TYPE='S'  and draft.STATUS='S' group by draft.USERID) df where df.sid(+)=su.USERID) spf,   (select count(*) TOTALPAPERS,drat.USERID usid from OI_CPSU_DRAFTS drat where drat.DRAFT_TYPE='C' and drat.STATUS='S' group by drat.USERID) pdf where pdf.usid(+)=USERID";
     
     /** Survey **/
     //public static final String SELECT_SURVEY_MEM ="SELECT PROFILE.USERID, PROFILE.NAME , MEMBER.SUBMITTED_ST ,PROFILE.TOTALPAPERS,PROFILE.TOTALSURVEYS,  INVITATION_DT FROM OI_SU_MEMBERS MEMBER, OI_AD_PROFILE PROFILE WHERE MEMBER.SURVEYID = ?  AND MEMBER.USERID = PROFILE.USERID ";
     public static final String SELECT_SURVEY_MEM = "select * from (select * from ( SELECT PROFILE.USERID, PROFILE.NAME , MEMBER.SUBMITTED_ST ,   INVITATION_DT FROM OI_SU_MEMBERS MEMBER, OI_AD_PROFILE PROFILE WHERE MEMBER.SURVEYID = ?  AND (PROFILE.OBSOLETE NOT IN ('E','e') OR PROFILE.OBSOLETE IS NULL)  AND MEMBER.USERID =  PROFILE.USERID) su,(select count(*) TOTALSURVEYS,draft.USERID sid from OI_CPSU_DRAFTS draft where draft.DRAFT_TYPE='S'  and draft.STATUS='S' group by draft.USERID) df where df.sid(+)=su.USERID) spf, (select count(*) TOTALPAPERS,drat.USERID usid from OI_CPSU_DRAFTS drat where drat.DRAFT_TYPE='C' and drat.STATUS='S' group by drat.USERID) pdf where pdf.usid(+)=USERID";
         
    /** Query to reterive Subject & Descritpion.**/
     public static final String SENDMAIL_C_SUB = "SELECT DESCRIPTION FROM OI_AD_CODE_MASTER WHERE TYPE ='MAIL_TEMPLATE'AND VALUE = ?";

    /**Query to reterive all E-mail Ids.. **/
     public static final String EMAIL_CP = "SELECT EMAILID,PROFILE.NAME,MEMBER.SUBMITTED FROM OI_AD_PROFILE PROFILE , OI_CP_MEMBERS MEMBER WHERE MEMBER.USERID = PROFILE.USERID AND PAPERID = ? AND SUBMITTED IS NULL ORDER BY PROFILE.NAME ASC "; 

     public static final String EMAIL_SU = "SELECT EMAILID,PROFILE.NAME,MEMBER.SUBMITTED_ST FROM OI_AD_PROFILE PROFILE , OI_SU_MEMBERS MEMBER WHERE MEMBER.USERID = PROFILE.USERID AND SURVEYID = ? AND SUBMITTED_ST IS NULL ORDER BY PROFILE.NAME ASC ";

     
     /** Query to Delete Consulation Paper Users **/
     public static final String CONS_PAPER_DELETE = "DELETE FROM OI_CP_MEMBERS WHERE USERID = ? AND PAPERID = ? "; 
    
     /** Query to Delete Survery Users **/
     public static final String SURVEY_DELETE = "DELETE FROM OI_SU_MEMBERS WHERE USERID = ? AND SURVEYID = ? " ;
     
     public static final String ORDERBY = " ORDER BY 1 ASC ";
      
     /** Query to Select Title from Consulation Paper **/
     public static final String QRY_TITLE_CP = "SELECT TITLE,EMAILMESSAGE FROM OI_CP_PAPER WHERE PAPERID = ? ";
     
     /** Query to Select Name from Survery **/
     public static final String QRY_TITLE_SU = "SELECT NAME,EMAILMESSAGE FROM  OI_SU_SURVEY WHERE SURVEYID = ?";
     
     public static final String QRYREMAINDER_CP = "UPDATE OI_CP_PAPER SET MAIL_STATUS = 'R'WHERE PAPERID = ? " ;
     	        
     public static final String QRYREMAINDER_SU = "UPDATE OI_SU_SURVEY SET MAIL_STATUS = 'R'WHERE SURVEYID = ? "; 
     
     public static final String QRY_REC_PER_PAGE ="SELECT DESCRIPTION FROM OI_AD_CODE_MASTER WHERE TYPE = 'RECORDS_PER_PAGE'";
      
     public static final String QRY_UPDATE_SYSDATE_CP = "UPDATE OI_CP_MEMBERS  SET  INVITATION_DT = SYSDATE WHERE USERID IN "+    
	 												    "(SELECT PROFILE.USERID FROM OI_CP_MEMBERS MEMBER, OI_AD_PROFILE PROFILE WHERE MEMBER.PAPERID = ?  AND MEMBER.USERID = PROFILE.USERID) " +
	 			 									    " AND PAPERID = ?  ";
     
     public static final String QRY_UPDATE_SYSDATE_SU = "UPDATE OI_SU_MEMBERS  SET  INVITATION_DT = SYSDATE WHERE USERID IN "+    
	    												"(SELECT PROFILE.USERID FROM OI_SU_MEMBERS MEMBER, OI_AD_PROFILE PROFILE WHERE MEMBER.SURVEYID = ?  AND MEMBER.USERID = PROFILE.USERID) " +
														" AND SURVEYID = ?  ";
        
     
/*   public static final String QRY_PAPERS =" SELECT DISTINCT PAPER.PAPERID,DESCRIPTION,TITLE,TO_CHAR(FROM_DT,'DD-Mon-YYYY'), "+
										  "	TO_CHAR(SUBMITTED_ON,'DD-Mon-YYYY') FROM OI_CP_PAPER PAPER,OI_CP_MEMBERS MEMBER WHERE MEMBER.PAPERID =PAPER.PAPERID "+ 
										  "	AND PAPER.PAPERID IN (SELECT PAPERID FROM OI_CP_MEMBERS WHERE USERID = ? ) AND PAPER.SECURITY ='R' ";
											

   public static final String QRY_SURVEY =" SELECT DISTINCT SURVEY.SURVEYID,DESCRIPTION,NAME,TO_CHAR(START_ON,'DD-Mon-YYYY'),  TO_CHAR(EXPIRY_ON,'DD-Mon-YYYY') " +
										  "	FROM OI_SU_SURVEY SURVEY , OI_SU_MEMBERS MEMBER WHERE SURVEY.SURVEYID = MEMBER.SURVEYID AND SURVEY.SURVEYID IN (SELECT SURVEYID FROM OI_SU_MEMBERS WHERE USERID = ? ) "+
										  "	AND SURVEY.SECURITY='Y' ";*/
     
     public static final String QRY_PAPERS = "SELECT PAPER.PAPERID,DESCRIPTION,TITLE,TO_CHAR(FROM_DT,'DD-Mon-YYYY'), TO_CHAR(to_dt,'DD-Mon-YYYY') FROM OI_CP_PAPER PAPER, OI_CPSU_DRAFTS draft where draft.userid=? and PAPER.PAPERID = draft.CPSUID  and draft.DRAFT_TYPE like'C' and draft.STATUS like 'S'";

     public static final String QRY_SURVEY = "SELECT SURVEY.SURVEYID,DESCRIPTION,NAME,TO_CHAR(START_ON,'DD-Mon-YYYY'),  TO_CHAR(EXPIRY_ON,'DD-Mon-YYYY') FROM OI_SU_SURVEY SURVEY, OI_CPSU_DRAFTS draft where draft.userid=? and SURVEY.SURVEYID = draft.CPSUID  and draft.DRAFT_TYPE like'S' and draft.STATUS like 'S'";
   
   public static final String QRY_PM_MAIL ="SELECT EMAILID FROM OI_AD_PROFILE WHERE USERID = (SELECT POSTED_BY FROM OI_FM_POSTS WHERE POSTID = ?)";

   public static final String QRY_ADMIN_MAIL ="SELECT EMAILID FROM OI_AD_PROFILE WHERE ROLEID IN(SELECT ROLEID FROM OI_AD_FUNCTIONROLE WHERE FUNCTIONID IN('MTNCTBD'))";

   public static final String QRY_POSTS ="SELECT THREADID, TO_CHAR(POSTED_ON,'DD-Mon-YYYY HH:MM:SS') FROM OI_FM_POSTS WHERE POSTID = ?";
   
   //public static final String QRY_FRM_MAILID = "SELECT NICKNAME EMAILID FROM OI_AD_PROFILE WHERE USERID =?"; 
   public static final String QRY_FRM_MAILID = "SELECT EMAILID FROM OI_AD_PROFILE WHERE USERID =?";
   
   public static final String QRY_DOMAINNAMES ="SELECT UPPER(DESCRIPTION) FROM OI_AD_CODE_MASTER WHERE TYPE='DOMAIN'";
      
   public static final String QRY_ADMIN_MOD  =" SELECT EMAILID FROM (SELECT AP.USERID,EMAILID, ROLEID FROM OI_AD_PROFILE AP,(SELECT USERID 	FROM OI_FM_BOARDMODADMIN BM, OI_FM_TOPIC TP, OI_FM_THREAD TH, OI_FM_POSTS PO  " + 
											 " WHERE BM.BOARDID = TP.BOARDID AND TP.TOPICID = TH.TOPICID  AND TH.THREADID = PO.THREADID AND POSTID = ? ) POST 	WHERE AP.USERID = POST.USERID) PS, "+
											 " (SELECT ROLEID FROM OI_AD_FUNCTIONROLE WHERE FUNCTIONID IN('MNTBORD','MTNTOPI') MINUS SELECT ROLEID FROM OI_AD_FUNCTIONROLE WHERE FUNCTIONID IN('MTNCTBD')) RL "+
											 " WHERE PS.ROLEID = RL.ROLEID ";

   // email texts for survey & consultation paper

   public static final String QRY_SU_EMAIL_RETRIEVE = "SELECT EMAILMESSAGE CONTENT FROM OI_SU_SURVEY WHERE SURVEYID=?";

   public static final String QRY_CP_EMAIL_RETRIEVE = "SELECT EMAILMESSAGE CONTENT FROM OI_CP_PAPER WHERE PAPERID=?";

   public static final String QRY_SU_EMAIL_SAVE = "UPDATE OI_SU_SURVEY SET EMAILMESSAGE=? WHERE SURVEYID=?";

   public static final String QRY_CP_EMAIL_SAVE = "UPDATE OI_CP_PAPER SET EMAILMESSAGE=? WHERE PAPERID=?";

   //Footer Msg for PM Function, Added by Rakesh.
   public static final String QRY_ADD_FOOTER_MESG = "SELECT DESCRIPTION FROM OI_AD_CODE_MASTER WHERE TYPE='MAIL_TEMPLATE' AND VALUE LIKE 'PM_MAIL_FOOTER%'";
  
   // Added by Rakesh. This quey gets the nickname of the user.
   public static final String READ_NICKNAME = "SELECT NICKNAME FROM OI_AD_PROFILE WHERE USERID=?";
   
}