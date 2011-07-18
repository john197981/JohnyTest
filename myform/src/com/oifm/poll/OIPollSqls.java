/*************************************************************************************
* Title 		: OIPollConstants.java
* Description 	: This file contains sql query of poll moudle   				    
* Author		: Suresh Kumar.R 
* Version No 	: 1.0
* Date Created 	: 26 - Jul -2005
* Copyright 	: Scandent Group
***************************************************************************************/

package com.oifm.poll;


public interface OIPollSqls	 {

	
	public static final String QRY_INSERT ="INSERT INTO OI_PL_POLL(POLLID, TITLE,QUESTION, START_ON, EXPIRY_ON, ANSWER1, ANSWER2, ANSWER3, ANSWER4, ANSWER5,MULTIPLE_ANS,SHOW_RESULT,PUBLISHED) "+
           								    "VALUES(SEQ_OI_PL_POLLID.NEXTVAL,?,?, TO_DATE(?,'DD/MM/YYYY'),TO_DATE(?,'DD/MM/YYYY'),?,?,?,?,?,?,?,?)";
	
	public static final String QRY_LIST ="SELECT POLLID,TITLE,QUESTION,START_ON,EXPIRY_ON FROM OI_PL_POLL ORDER BY START_ON"; 
	
	public static final String QRY_VIEW ="SELECT POLLID, TITLE,QUESTION, START_ON, EXPIRY_ON, ANSWER1, ANSWER2, ANSWER3, ANSWER4, ANSWER5,MULTIPLE_ANS,SHOW_RESULT,PUBLISHED,to_char(start_on,'MON DD YYYY') SDATE FROM OI_PL_POLL WHERE POLLID = ?";
	
	public static final String QRY_UPDATE ="UPDATE OI_PL_POLL SET TITLE = ?, QUESTION = ? , START_ON = TO_DATE(?,'DD/MM/YYYY') , EXPIRY_ON = TO_DATE(?,'DD/MM/YYYY'), ANSWER1 = ? , ANSWER2 = ? , ANSWER3 = ? , ANSWER4 =? , ANSWER5 =? ,MULTIPLE_ANS = ? ,SHOW_RESULT = ? ,PUBLISHED = ?  WHERE  POLLID = ? ";        								    
		
	public static final String QRY_DELETE_POLL ="DELETE FROM OI_PL_POLL WHERE POLLID = ?";
	
	public static final String QRY_DELETE_RESPONSE ="DELETE FROM OI_PL_RESPONSE WHERE POLLID = ?";
	
	public static final String QRY_RESULT = "SELECT RESPONSEID,POLLID,RESPONSE1,RESPONSE2,RESPONSE3,RESPONSE4,RESPONSE5,(RESPONSE1+RESPONSE2+RESPONSE3+RESPONSE4+RESPONSE5) TOTAL, (RESPONSE1+RESPONSE2+RESPONSE3+RESPONSE4+RESPONSE5)/5 AVERAGE  FROM OI_PL_RESPONSE WHERE POLLID = ?";
	  
	public static final String QRY_PUB_DATE_MODIFY =  "SELECT COUNT(*) FROM " +
													  "(SELECT START_ON,EXPIRY_ON FROM OI_PL_POLL WHERE POLLID != ?)" +
													  " WHERE TO_DATE(START_ON,'DD-MON-YY')  BETWEEN TO_DATE(?,'DD-MON-YY') "+
													  " AND TO_DATE(?,'DD-MON-YY') OR " +
													  " TO_DATE(EXPIRY_ON,'DD-MON-YY')  BETWEEN TO_DATE(?,'DD-MON-YY') "+
													  " AND TO_DATE(?,'DD-MON-YY') "+
													  " OR  TO_DATE(?,'DD-MON-YY')  BETWEEN TO_DATE(START_ON,'DD-MON-YY')  AND TO_DATE(EXPIRY_ON,'DD-MON-YY') "+
													  "	OR  TO_DATE(?,'DD-MON-YY')  BETWEEN TO_DATE(START_ON,'DD-MON-YY')  AND TO_DATE(EXPIRY_ON,'DD-MON-YY') ";


	public static final String QRY_PUB_DATE_ADD = "SELECT COUNT(*) FROM OI_PL_POLL " +
												  " WHERE TO_DATE(START_ON,'DD-MON-YY')  BETWEEN TO_DATE(?,'DD-MON-YY') "+
												  " AND TO_DATE(?,'DD-MON-YY') OR " +
												  " TO_DATE(EXPIRY_ON,'DD-MON-YY')  BETWEEN TO_DATE(?,'DD-MON-YY') "+   
												  " AND TO_DATE(?,'DD-MON-YY') " +
												  " OR  TO_DATE(?,'DD-MON-YY')  BETWEEN TO_DATE(START_ON,'DD-MON-YY')  AND TO_DATE(EXPIRY_ON,'DD-MON-YY') "+
												  "	OR  TO_DATE(?,'DD-MON-YY')  BETWEEN TO_DATE(START_ON,'DD-MON-YY')  AND TO_DATE(EXPIRY_ON,'DD-MON-YY') ";
	
	
	
	public static final String QRY_PUBLISH =  "SELECT POLLID,TITLE FROM OI_PL_POLL WHERE  UPPER(PUBLISHED) =UPPER('Y')";
	
	
	public static final String QRY_RES_UPDATE = "UPDATE OI_PL_RESPONSE SET ";
	
	
	public static final String QRY_PUB_QUES ="SELECT POLLID,QUESTION, ANSWER1, ANSWER2, ANSWER3, ANSWER4, ANSWER5,MULTIPLE_ANS,SHOW_RESULT,PUBLISHED  FROM OI_PL_POLL  "+ 
											 "WHERE TRUNC(SYSDATE) BETWEEN TRUNC(START_ON) AND TRUNC(EXPIRY_ON)";  

	public static final String QRY_SYSDATE_CHK ="SELECT SYSDATE FROM DUAL WHERE TO_DATE(?,'DD-MON-YY') >= TO_DATE(SYSDATE,'DD-MON-YY') OR  TO_DATE(?,'DD-MON-YY') >= TO_DATE(SYSDATE,'DD-MON-YY')";   
	
	
	public static final String READ_HOTEST_TOPIC = "SELECT distinct TITLE,'H' TTYPE,T.THREADID,T.LASTPOST_ON FROM OI_FM_THREAD T, OI_FM_POSTS P, (SELECT THREADS.THREADID, MEMBERS.USERID  FROM OI_FM_THREAD THREADS, OI_FM_THREAD_GROUPS THREAD_GROUPS, OI_FM_GROUPMEMBERS MEMBERS  WHERE THREADS.THREADID=THREAD_GROUPS.THREADID   AND THREAD_GROUPS.GROUPID = MEMBERS.GROUPID AND MEMBERS.USERID= ?) MEMB WHERE T.THREADID=P.THREADID and (select count(*) from OI_FM_POSTS countPost where countPost.POSTED_ON>(SYSDATE - (select VALUE from OI_AD_CONFIGURATION where TAG='CATEGORISETHREADS')) and countPost.THREADID=P.THREADID)>=(select VALUE from OI_AD_CONFIGURATION where TAG='HOTTOPIC') and T.THREADID=MEMB.THREADID(+) AND  ((MEMB.USERID= ? AND T.SECURITY='Y') OR T.SECURITY ='N') and rownum<=3";
	public static final String READ_LATEST_TOPIC = "select * from(SELECT distinct TITLE,'L' TTYPE,T.THREADID,P.POSTED_ON FROM OI_FM_THREAD T,OI_FM_POSTS P, (SELECT THREADS.THREADID, MEMBERS.USERID  FROM OI_FM_THREAD THREADS, OI_FM_THREAD_GROUPS THREAD_GROUPS, OI_FM_GROUPMEMBERS MEMBERS  WHERE THREADS.THREADID=THREAD_GROUPS.THREADID   AND THREAD_GROUPS.GROUPID = MEMBERS.GROUPID AND MEMBERS.USERID= ?) MEMB WHERE T.THREADID=P.THREADID and P.POSTED_ON > (SYSDATE - (select VALUE from OI_AD_CONFIGURATION where TAG='LATESTTOPIC')) and T.THREADID=MEMB.THREADID(+) AND ((MEMB.USERID= ? AND T.SECURITY='Y') OR T.SECURITY ='N') and T.MODERATION_STAT='Y' and P.POSTED_ON=T.LASTPOST_ON ORDER BY P.POSTED_ON DESC)";
	//public static final String READ_LATEST_PAPER = "select * from (select * from (select * from (select category.CATEGORYID,category.NAME,PAPERID,TITLE,DESCRIPTION,FROM_DT,TO_DT, SECURITY , ACTIVE from OI_CP_PAPER paper, OI_CP_CATEGORY category where category.CATEGORYID=paper.CATEGORYID and SECURITY like 'U' and ACTIVE='1' and trunc(SYSDATE) between trunc(FROM_DT) and trunc(TO_DT) ) t, (select STATUS,PAPERID tPAPERID from OI_CP_PAPER paper1,OI_CPSU_DRAFTS draft where draft.CPSUID=paper1.PAPERID and draft.USERID=? and draft.DRAFT_TYPE like'C') su where su.tPAPERID(+) = t.PAPERID  UNION select * from (select category.CATEGORYID, category.NAME,PAPERID,TITLE,DESCRIPTION,FROM_DT,TO_DT, SECURITY , ACTIVE from OI_CP_PAPER paper, OI_CP_CATEGORY category where category.CATEGORYID=paper.CATEGORYID and SECURITY like 'R' and paper.PAPERID in (select members.PAPERID from OI_CP_MEMBERS members where members.USERID=?)  and ACTIVE='1' and trunc(SYSDATE) between trunc(FROM_DT) and trunc(TO_DT))  t, (select STATUS,PAPERID tPAPERID from OI_CP_PAPER paper1, OI_CPSU_DRAFTS draft where draft.CPSUID=paper1.PAPERID and  draft.USERID=? and draft.DRAFT_TYPE like'C')  su where su.tPAPERID(+) = t.PAPERID ) order by FROM_DT desc) where rownum<=3";
	public static final String READ_LATEST_PAPER = "select * from (select * from (select * from (select category.CATEGORYID,category.NAME,PAPERID,TITLE,DESCRIPTION,FROM_DT,TO_DT, SECURITY , ACTIVE from OI_CP_PAPER paper, OI_CP_CATEGORY category where category.CATEGORYID=paper.CATEGORYID and SECURITY like 'U' and ACTIVE='1' and trunc(SYSDATE) between trunc(FROM_DT) and trunc(TO_DT) ) t, (select STATUS,PAPERID tPAPERID from OI_CP_PAPER paper1,OI_CPSU_DRAFTS draft where draft.CPSUID=paper1.PAPERID and draft.USERID=? and draft.DRAFT_TYPE like'C') su where su.tPAPERID(+) = t.PAPERID  UNION select * from (select category.CATEGORYID, category.NAME,PAPERID,TITLE,DESCRIPTION,FROM_DT,TO_DT, SECURITY , ACTIVE from OI_CP_PAPER paper, OI_CP_CATEGORY category where category.CATEGORYID=paper.CATEGORYID and SECURITY like 'R' and OI_PKG.IS_CP_MEMBER(paper.PAPERID,?) > 0  and ACTIVE='1' and trunc(SYSDATE) between trunc(FROM_DT) and trunc(TO_DT))  t, (select STATUS,PAPERID tPAPERID from OI_CP_PAPER paper1, OI_CPSU_DRAFTS draft where draft.CPSUID=paper1.PAPERID and  draft.USERID=? and draft.DRAFT_TYPE like'C')  su where su.tPAPERID(+) = t.PAPERID ) order by FROM_DT desc) where rownum<=3";
	
	public static final String GET_BANNER_CNT= "SELECT DESCRIPTION  FROM OI_AD_CODE_MASTER WHERE TYPE='BANNER'";
	
 }