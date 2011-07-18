/*********************************ASMActionView.java******************* 
 * Title 		: ASMActionHomePage
 * Description 	: This Interface will have the SQL Queries of the ASM Module 
 * Author 		: Rajkumar  
 * Version No 	: 1.0 
 * Date Created : 19 - Dec -2005
 * Modified By	: Rakesh	
 * Modified date: 25 Jan 2008
 * Copyright 	: Scandent Group
 ******************************************************************************/
package com.oifm.asm;

public interface ASMSqls 
{
	//ASM View
	//Edited LETTER_DRAFT_INSERT,LETTER_SUBMITTED_INSERT(Added Category Id with value 1)
	public final static String ASM_VIEW_USER_LETTERS = "select LETTERID, TOPIC, STATUS, DRAFTEDON, SUBMITTEDON,REDIRECTEDON, REPLIEDON, PUBLISHEDON from OI_AM_LETTERS where CREATEDBY = ? order by LETTERID desc";
	public final static String ASM_DETAIL_LETTERS = "select LETTERID, TOPIC, LETTER, STATUS, DRAFTEDON, SUBMITTEDON, REDIRECTEDON, REDIRECTEDBY, REDIRECTEDTO, REDIRECTEDCC,REDIRECTEDBCC, REDIRECTSTATUS, REDIRECTMESSAGE, SUBJECT, REPLIEDON, REPLIEDBY, REPLY, PUBLISHONSITE, PUBLISHEDON,PUBLISHEDFROM, PUBLISHEDTO, CREATEDBY, REMINDERMODE, REMINDERSENTON, DIVISION_CODE,CONTACTNO,b.name as AUTHOR_NAME from OI_AM_LETTERS a,OI_AD_PROFILE b where a.createdby=b.userid and letterId=?";
	public final static String LETTER_DRAFT_INSERT = "insert into OI_AM_LETTERS (LETTERID, TOPIC, LETTER, STATUS, DRAFTEDON, CREATEDBY, CONTACTNO, REPLY, CATEGORYID) values (SEQ_OI_AM_LETTERID.NEXTVAL,?,EMPTY_CLOB(),?,?,?,?,EMPTY_CLOB(),1)";
	public final static String LETTER_SUBMITTED_INSERT = "insert into OI_AM_LETTERS (LETTERID, TOPIC, LETTER, STATUS, SUBMITTEDON, CREATEDBY, CONTACTNO, REPLY, CATEGORYID) values (SEQ_OI_AM_LETTERID.NEXTVAL,?,EMPTY_CLOB(),?,?,?,?,EMPTY_CLOB(),1)";
	public final static String LETTER_DRAFT_UPDATE = "update OI_AM_LETTERS set TOPIC=?, LETTER = EMPTY_CLOB(), STATUS=?, DRAFTEDON=?, CONTACTNO=? where LETTERID = ?";
	public final static String LETTER_SUBMITTED_UPDATE = "update OI_AM_LETTERS set TOPIC=?, LETTER = EMPTY_CLOB(), STATUS=?, SUBMITTEDON=?, CONTACTNO=? where LETTERID = ?";
	public final static String LETTER_DRAFT_DELETE = "delete from OI_AM_LETTERS letter where letter.LETTERID=?";
	
	
	//Common DAO used for Right side menu
	public final static String ASM_EDITORS_NOTE ="Select CONTENTID,CONTENT,EDITON from oi_am_content where contenttype ='E' AND EDITON <= SYSDATE ORDER BY EDITON DESC, CONTENTID DESC ";
	// Ramesh Added the below SQL for retrieiving the Editor Notes for the specified id.
	public final static String ASM_EDITORS_NOTE_WITH_ID ="Select CONTENTID,CONTENT,EDITON from oi_am_content where contenttype ='E' AND CONTENTID= ? ";
	public final static String ASM_RECENT_LETTERS ="Select letterid,topic,PUBLISHEDON from oi_am_letters where status='P' AND to_date(SYSDATE,'dd/mm/yyyy') >= to_date(PUBLISHEDFROM,'dd/mm/yyyy') AND to_date(SYSDATE,'dd/mm/yyyy')<=to_date(PUBLISHEDTO,'dd/mm/yyyy') AND PUBLISHONSITE='Y' order by publishedon desc";
	public final static String ASM_TOPIC ="Select topic from oi_am_letters where LETTERID = ";
	public final static String ASM_PAGINATION ="SELECT DESCRIPTION FROM OI_AD_CODE_MASTER WHERE TYPE='";
	public final static String ASM_USER_DETAILS1 ="select * from (Select tablealias.*,rownum as num from (Select NAME,EMAILID from OI_AD_PROFILE where EMAILID is not null and userid in (select userid from OI_FM_GROUPS gp, OI_FM_GROUPMEMBERS gm where gp.NAME = 'ASM Division Rep' and gp.GROUPID = gm.GROUPID) AND DIVISIONCODE ='";
	public final static String ASM_USER_DETAILS2 ="' order by NAME ) tablealias )  where num>=? and num<=? ";
	public final static String ASM_TOTAL_USER_DETAILS ="Select count(*) total from oi_ad_profile where EMAILID is not null and userid in (select userid from OI_FM_GROUPS gp, OI_FM_GROUPMEMBERS gm where gp.NAME = 'ASM Division Rep' and gp.GROUPID = gm.GROUPID) AND divisioncode='";
	
	public final static String ASM_DIVISION="select value,description from oi_ad_code_master where type='DIVISION_CODE' order by description ";
	public final static String ASM_CATEGORY="SELECT CATEGORYID,NAME from OI_AM_CATEGORY ORDER BY NAME ";
	public final static String ASM_DESCRIPTION ="SELECT DESCRIPTION FROM OI_AD_CODE_MASTER WHERE TYPE='MAIL_TEMPLATE' AND VALUE='";
	public final static String ASM_SCHOOL="select schoolcode,schoolname from oi_ad_schools order by schoolname ";
	public final static String ASM_GET_DESC="SELECT VALUE FROM OI_AD_CONFIGURATION WHERE TAG='";
	
	// HOME PAGE
	public final static String ASM_ANNOUNCEMENT ="Select content from oi_am_content where contenttype ='N'";
	public final static String ASM_LETTER_REPLY1 =" Select letterid,topic,letter,reply,publishedon,b.name createname,repliedby replyname from oi_am_letters  a,oi_ad_profile b where a.createdby= b.userid and status='P' AND to_date(SYSDATE,'dd/mm/yyyy') >= to_date(PUBLISHEDFROM,'dd/mm/yyyy') AND to_date(SYSDATE,'dd/mm/yyyy')<=to_date(PUBLISHEDTO,'dd/mm/yyyy') AND PUBLISHONSITE='Y' order by publishedon desc ";
	public final static String ASM_LETTER_REPLY2 ="Select letterid,topic,letter,reply,publishedon,b.name createname,repliedby replyname from oi_am_letters  a,oi_ad_profile b where a.createdby= b.userid and letterid =";
	
	//PREVIOUS REPLIES
	
	public final static String ASM_PREVIOUS_REP1="select * from (Select tablealias.*,rownum as num from (Select LETTERID,TOPIC,SUBMITTEDON,B.NAME AS CREATEDBY,PUBLISHEDON from oi_am_letters a,oi_ad_profile b where a.createdby=b.userid and status='P' and PUBLISHEDON <=SYSDATE and round(MONTHS_BETWEEN(SYSDATE,PUBLISHEDON),0) <=(select To_number(trim(description)) from oi_ad_code_master where TYPE='ASMMONTHS') order by PUBLISHEDON DESC) tablealias ) where num>=? and num<=? ";
	public final static String ASM_PREVIOUS_REP2="select * from (Select tablealias.*,rownum as num from (Select LETTERID,TOPIC,SUBMITTEDON,B.NAME AS CREATEDBY,PUBLISHEDON from oi_am_letters a,oi_ad_profile b where a.createdby=b.userid and status='P' and PUBLISHEDON <=SYSDATE and round(MONTHS_BETWEEN(SYSDATE,PUBLISHEDON),0) >(select To_number(trim(description)) from oi_ad_code_master where TYPE='ASMMONTHS') order by PUBLISHEDON DESC) tablealias ) where num>=? and num<=? ";
		
	public final static String ASM_TOTAL_PREVIOUS_REP1="Select count(*) total from oi_am_letters where status='P' and round(MONTHS_BETWEEN(SYSDATE,PUBLISHEDON),0) <= (select To_number(trim(description)) from oi_ad_code_master where TYPE ='ASMMONTHS') ";
	public final static String ASM_TOTAL_PREVIOUS_REP2="Select count(*) total from oi_am_letters where status='P' and round(MONTHS_BETWEEN(SYSDATE,PUBLISHEDON),0) > (select To_number(trim(description)) from oi_ad_code_master where TYPE ='ASMMONTHS') ";
	
	//REPLY REDIRECT
	//public final static String ASM_REPLY_REDIRECT1 ="Select * from (Select rownum as num,LETTERID,TOPIC,SUBMITTEDON,CREATEDBY,PUBLISHEDON,REDIRECTEDON, REPLIEDON,DESCRIPTION DIVISION_INCHARGE,STATUS from oi_am_letters A,OI_AD_CODE_MASTER B where STATUS<>'D' AND A.DIVISION_CODE=B.VALUE(+) AND NVL(B.TYPE,'DIVISION_CODE')='DIVISION_CODE' order by ";
	//public final static String ASM_REPLY_REDIRECT2 =" ) where num>=? and num<=?";

	public final static String ASM_REPLY_REDIRECT1 ="select * from (Select tablealias.*,rownum as num from (Select LETTERID,TOPIC,SUBMITTEDON,C.NAME AS CREATEDBY,PUBLISHEDON,REDIRECTEDON, REPLIEDON,b.DESCRIPTION DIVISION_INCHARGE,STATUS,d.NAME CATEGORY from oi_am_letters A,OI_AD_CODE_MASTER B,oi_ad_profile c,OI_AM_CATEGORY d where a.createdby=c.userid and STATUS<>'D' AND A.DIVISION_CODE=B.VALUE(+) AND NVL(B.TYPE,'DIVISION_CODE')='DIVISION_CODE' AND d.CATEGORYID=A.CATEGORYID(+) order by ";
	public final static String ASM_REPLY_REDIRECT2 =" ) tablealias ) where num>=? and num<=?";
		
	public final static String ASM_TOTAL_REPLY_REDIRECT = "Select count(*) total from oi_am_letters where STATUS<> 'D'";
	
	//REPLY REDIRECT EDIT PAGE
	public final static String ASM_REPLY_REDIRECT_EDIT= "Select LETTERID,TOPIC,LETTER,STATUS,SUBMITTEDON, REDIRECTEDON, REDIRECTEDTO,REDIRECTEDCC,REDIRECTEDBCC,REDIRECTMESSAGE,SUBJECT,REPLIEDBY,REPLIEDON,REPLY,PUBLISHONSITE,PUBLISHEDON,PUBLISHEDFROM,PUBLISHEDTO,CREATEDBY,REMINDERMODE,DIVISION_CODE,b.NAME,EMAILID,CONTACTNO,c.NAME AS CATEGORY, c.CATEGORYID from oi_am_letters a,oi_ad_profile b,oi_am_category c where a.createdby=b.userid AND c.CATEGORYID=a.CATEGORYID(+) AND LETTERID= ";
	public final static String ASM_REPLY_REDIRECT_DELETE = "delete from OI_AM_LETTERS letter where letter.LETTERID=?";
	
	//Report Page
	public final static String ASM_REPORT1 = "select * from (Select tablealias.*,rownum as num from (Select LETTERID,TOPIC, SUBMITTEDON, CREATEDBY, AUTHOR_NAME,AUTHOR_DESIGNATION,AUTHOR_DIVISION_SCHOOL_CODE, AUTHOR_DIVISION_SCHOOL_DESC, LETTER, YIS, AGE,DIVISION_INCHARGE_CODE,DIVISION_INCHARGE_DESCRIPTION,CATEGORY, REDIRECTEDTO, REDIRECTEDON, REPLIEDBY,REPLIEDON,REPLY from VW_ASM_LETTER_DETAILS ";
	public final static String ASM_REPORT2 = ") tablealias ) where num>=? and num<=? ";
	
	public final static String ASM_EXCEL_REPORT = "Select LETTERID,TOPIC, SUBMITTEDON, CREATEDBY, AUTHOR_NAME, AUTHOR_DESIGNATION,AUTHOR_DIVISION_SCHOOL_CODE, AUTHOR_DIVISION_SCHOOL_DESC, LETTER, YIS, AGE,DIVISION_INCHARGE_CODE,DIVISION_INCHARGE_DESCRIPTION,CATEGORY, REDIRECTEDTO, REDIRECTEDON, REPLIEDBY,REPLIEDON,REPLY from VW_ASM_LETTER_DETAILS ";
	
	public final static String ASM_TOTAL_REPORT ="Select count(*) total from VW_ASM_LETTER_DETAILS ";
	
	//Get the user list
	public final static String ASM_USER_LIST ="select * from (Select tablealias.*,rownum as num from (Select NAME,EMAILID from OI_AD_PROFILE where roleid ='USER' and EMAILID is not null and userid in (select userid from OI_FM_GROUPS gp, OI_FM_GROUPMEMBERS gm where gp.NAME = 'ASM Division Rep' and gp.GROUPID = gm.GROUPID) order by NAME ) tablealias ) where num>=? and num<=? ";
	public final static String ASM_TOTAL_USER_LIST ="Select count(*) total from oi_ad_profile where roleid ='USER' and EMAILID is not null and userid in (select userid from OI_FM_GROUPS gp, OI_FM_GROUPMEMBERS gm where gp.NAME = 'ASM Division Rep' and gp.GROUPID = gm.GROUPID)";
	
	
	public final static String ASM_REMINDER_DAYS = "select DESCRIPTION from OI_AD_CODE_MASTER where TYPE = 'ASM_REMINDER_DAYS'";
	public final static String ASM_USER_DETAIL = "SELECT NAME, B.DESCRIPTION AS Designation, NVL(E.SCHOOLNAME,D.DESCRIPTION) AS AUTHOR_DIVISION_SCHOOL_DESC FROM OI_AD_CODE_MASTER B,OI_AD_PROFILE C, OI_AD_CODE_MASTER D,OI_AD_SCHOOLS E WHERE userid=  ? AND (B.TYPE(+)='DESIGNATION_CODE' AND B.VALUE(+)=C.DESIGNATIONCODE) AND (D.TYPE (+)= 'DIVISION_CODE' AND D.VALUE (+) = C.DIVISIONCODE) AND (E.SCHOOLCODE (+)= C.SCHOOLCODE)";
	
	//Get the Senior Management User List [ASM Reports/Reply Redirect Letters ]
	public final static String ASM_SNR_USER_DETAIL = "select * from (Select tablealias.*,rownum as num from (Select NAME,EMAILID from OI_AD_PROFILE where userid in (select userid from OI_FM_GROUPS gp, OI_FM_GROUPMEMBERS gm where gp.NAME = 'Senior Management' and gp.GROUPID = gm.GROUPID) order by NAME ) tablealias ) where num>=? and num<=? ";
	public final static String ASM_SNR_TOTAL_USER_DETAIL = "Select count(*) total from oi_ad_profile where userid in (select userid from OI_FM_GROUPS gp, OI_FM_GROUPMEMBERS gm where gp.NAME = 'Senior Management' and gp.GROUPID = gm.GROUPID) ";
}
 