package com.oifm.forum;

import com.oifm.common.OILabelConstants;

public interface OIForumSqls 
{
	public static final String  FORUM_CHECK_DUPLICATE_CATEGORY = "select count(*) count from OI_FM_CATEGORY category where TRIM(lower(category.NAME))=TRIM(lower(?))";
	public static final String  FORUM_CHECK_DUPLICATE_CATEGORY1 = "select count(*) count from OI_FM_CATEGORY category where TRIM(lower(category.NAME))=TRIM(lower(?)) and category.CATEGORYID<>?";
 
	public static final String	SAVE_CATEGORY = "insert into OI_FM_CATEGORY (CATEGORYID,NAME, CREATED_ON, CREATED_BY) values (SEQ_OI_FM_CATEGORYID.NEXTVAL,?,SYSDATE,?)";
	public static final String	UPDATE_CATEGORY = "update OI_FM_CATEGORY set NAME=? where CATEGORYID=?";
	public static final String  FORUM_LISTING = "select * from ( select * from (SELECT category.CATEGORYID cCATEGORYID,category.NAME cName,board.BOARDID bBOARDID,board.NAME bName from OI_FM_CATEGORY category,OI_FM_BOARD board where category.CATEGORYID=board.CATEGORYID(+)) a,(SELECT cdMaster.DESCRIPTION,DIVISIONID, board.BOARDID bbBOARDID from OI_FM_BOARD board, OI_AD_CODE_MASTER cdMaster where cdMaster.TYPE='DIVISION_CODE' and cdMaster.VALUE = board.DIVISIONID) b where a.bBOARDID=b.bbBOARDID(+)) d, (SELECT topic.TOPICID,topic.TOPICNAME,topic.CREATED_ON, board.BOARDID ccbBOARDID  from OI_FM_BOARD board, OI_FM_TOPIC topic where topic.BOARDID(+)=board.BOARDID) c where d.bBOARDID=c.ccbBOARDID(+) order by cCATEGORYID,bBOARDID,TOPICID";
	public static final String  FORUM_USERLIST = "select BOARDID,USERID from OI_FM_BOARDMODADMIN";
	public static final String	READ_CATEGORY = "select CATEGORYID,NAME from OI_FM_CATEGORY category where category.CATEGORYID=?";
	public static final String	DELETE_CATEGORY = "delete from OI_FM_CATEGORY category where category.CATEGORYID=?";
	public static final String	CHECK_CATEGORY ="select count(*) count from OI_FM_BOARD board where board.CATEGORYID=?";
	public static final String	READ_ALL_CATEGORY = "select CATEGORYID,NAME from OI_FM_CATEGORY category";

	public static final String	SAVE_BOARD = "Insert into OI_FM_BOARD (BOARDID, CATEGORYID, DIVISIONID, NAME, CREATED_BY, CREATED_ON) Values (SEQ_OI_FM_BOARDID.NEXTVAL, ?, ?, ?, ?,SYSDATE)";
	public static final String	UPDATE_BOARD = "update OI_FM_BOARD set CATEGORYID=?,DIVISIONID=?,NAME=? where BOARDID=?";
	public static final String  FORUM_CHECK_DUPLICATE_BOARD = "select count(*) count from OI_FM_BOARD board where board.CATEGORYID=? and TRIM(lower(board.NAME)) = TRIM(lower(?))";
	public static final String  FORUM_CHECK_DUPLICATE_BOARD1 = "select count(*) count from OI_FM_BOARD board where board.CATEGORYID=? and TRIM(lower(board.NAME)) = TRIM(lower(?)) and board.BOARDID<>?";
	public static final String	READ_BOARD = "select BOARDID, CATEGORYID, DIVISIONID, NAME, CREATED_BY, CREATED_ON from OI_FM_BOARD where BOARDID=?";
	public static final String	READ_BOARD_MOD_ADMIN = "select distinct BOARDMODADMINID, BOARDID, profile.USERID, profile.NAME,role.Name rName, count(functionid) FN_FLAG from OI_FM_BOARDMODADMIN forum,OI_AD_PROFILE profile,OI_AD_ROLE role,OI_AD_FUNCTIONROLE fnrole where forum.BOARDID=? and profile.USERID=forum.USERID and profile.roleId=role.roleid and fnrole.ROLEID=role.ROLEID and functionid in('MNTBORD','MODPOST') group by BOARDMODADMINID, BOARDID, profile.USERID, profile.NAME,role.Name";
	public static final String	CHECK_BOARD ="select count(*) count from OI_FM_TOPIC topic where topic.BOARDID=?";
	public static final String	DELETE_BOARD = "delete from OI_FM_BOARD board where board.BOARDID=?";
	public static final String  REMOVE_USER = "delete from OI_FM_BOARDMODADMIN forum where forum.BOARDID=? and forum.USERID=?";
	public static final String	READ_ALL_BOARD = "select BOARDID,NAME from OI_FM_BOARD board where board.CATEGORYID=?";
	public static final String	READ_ALL_BOARD_USER = "select BOARDID,NAME from OI_FM_BOARD board where board.CATEGORYID=? and BOARDID in (select BOARDID from OI_FM_BOARDMODADMIN WHERE USERID=?)";
	public static final String	DELETE_BOARDADMIN = "delete from OI_FM_BOARDMODADMIN board where board.BOARDID=?";
	  
	public static final String  READ_Topic = "select TOPICID, BOARDID, TOPICNAME, MODERATION_REQ, CREATED_BY, CREATED_ON,TOPICDESC from OI_FM_TOPIC where TOPICID=?";
	public static final String  FORUM_CHECK_DUPLICATE_TOPIC = "select count(*) count from OI_FM_TOPIC topic where topic.BOARDID=? and TRIM(lower(topic.TOPICNAME)) = TRIM(lower(?))";
	public static final String  FORUM_CHECK_DUPLICATE_TOPIC1 = "select count(*) count from OI_FM_TOPIC topic where topic.BOARDID=? and TRIM(lower(topic.TOPICNAME)) = TRIM(lower(?)) and topic.TOPICID<>?";
	public static final String	SAVE_TOPIC = "Insert into OI_FM_TOPIC (TOPICID, BOARDID, TOPICNAME, MODERATION_REQ, CREATED_BY, CREATED_ON,TOPICDESC) Values (SEQ_OI_FM_TOPICID.NEXTVAL, ?, ?, ?, ?,SYSDATE,?)";
	public static final String	UPDATE_TOPIC = "update OI_FM_TOPIC set BOARDID=?, TOPICNAME=?, MODERATION_REQ=?, TOPICDESC=? where TOPICID=?";
	public static final String	DELETE_TOPIC = "delete from OI_FM_TOPIC topic where topic.TOPICID=?";
	public static final String	READ_ARRAY_THREAD = "select threadId from OI_FM_THREAD where topicid=?";
	public static final String	CHECK_TOPIC ="select count(*) count from OI_FM_THREAD thread where thread.topicid=?";
	
	/* Added/Modified by Aik Mun @ Jan 14, 2009 */
	public static final String  READ_TopicList = "select TOPICID, BOARDID, TOPICNAME, MODERATION_REQ, CREATED_BY, CREATED_ON,TOPICDESC from OI_FM_TOPIC where BOARDID=? ORDER BY TOPICNAME";
	public static final String	READ_THREADLIST = "select threadId,TITLE from OI_FM_THREAD where topicid=? ORDER BY TITLE";
	public static final String	MOVE_THREAD = "UPDATE OI_FM_THREAD SET TOPICID = ? WHERE THREADID = ?";
	public static final String	MOVE_ALL_THREAD = "UPDATE OI_FM_THREAD SET TOPICID = ? WHERE TOPICID = ?";
	
	/** Thread Listing **/
	public static final String QRY_THREADLST = " SELECT LIST.*,ROWNUM NUM FROM (SELECT DISTINCT TITLE,TO_CHAR(LASTPOST_ON, '"+OILabelConstants.DISPLAY_DATE_TIME_FORMAT+"') POSTED ,DECODE(REPLIES,0,'',REPLIES) REP ,DECODE(HITS,0,'',HITS) HIT, TOPICNAME," +
											   " THREADS.THREADID, THREADS.MODERATED_BY,POSTED_BY, " +
											   " PROFILE.NICKNAME MODNAME, POSTS.NICKNAME POSTNAME, THREADS.LOCKED,THREADS.SECURITY,THREADS.MODERATION_REQ,LASTPOST_ON,REPLIES,HITS,TOPICDESC FROM OI_FM_THREAD THREADS, " +
											   " (SELECT THREADS.THREADID, MEMBERS.USERID FROM OI_FM_THREAD THREADS, OI_FM_THREAD_GROUPS THREAD_GROUPS," +
											   " OI_FM_GROUPMEMBERS MEMBERS WHERE THREADS.THREADID=THREAD_GROUPS.THREADID  AND THREAD_GROUPS.GROUPID = MEMBERS.GROUPID " +
											   " AND MEMBERS.USERID= ?) MEMB, OI_FM_POSTS POSTS,OI_AD_PROFILE PROFILE ,OI_FM_TOPIC TOPIC WHERE THREADS.THREADID=MEMB.THREADID(+)" +
											   " AND  (((MEMB.USERID= ? OR THREADS.CREATED_BY= ?) AND THREADS.SECURITY='Y') OR THREADS.SECURITY ='N') AND THREADS.LASTPOST_ID = POSTS.POSTID(+) and TOPIC.TOPICID = THREADS.TOPICID " +
											   " AND THREADS.TOPICID =  ? AND THREADS.MODERATION_STAT='Y' AND THREADS.MODERATED_BY = PROFILE.USERID(+)   ORDER BY     " ;
	
	public static final String QRY_THREADLST_ADMIN = " SELECT LIST.*,ROWNUM NUM FROM (SELECT DISTINCT TITLE,TO_CHAR(LASTPOST_ON, '"+OILabelConstants.DISPLAY_DATE_TIME_FORMAT+"') POSTED ,DECODE(REPLIES,0,'',REPLIES) REP ,DECODE(HITS,0,'',HITS) HIT, TOPICNAME," +
	   " THREADS.THREADID, THREADS.MODERATED_BY,POSTED_BY, " +
	   " PROFILE.NICKNAME MODNAME, POSTS.NICKNAME POSTNAME, THREADS.LOCKED,THREADS.SECURITY,THREADS.MODERATION_REQ,LASTPOST_ON,REPLIES,HITS,TOPICDESC FROM OI_FM_THREAD THREADS, " +
	   " (SELECT THREADS.THREADID, MEMBERS.USERID FROM OI_FM_THREAD THREADS, OI_FM_THREAD_GROUPS THREAD_GROUPS," +
	   " OI_FM_GROUPMEMBERS MEMBERS WHERE THREADS.THREADID=THREAD_GROUPS.THREADID  AND THREAD_GROUPS.GROUPID = MEMBERS.GROUPID " +
	   " AND MEMBERS.USERID= ?) MEMB, OI_FM_POSTS POSTS,OI_AD_PROFILE PROFILE ,OI_FM_TOPIC TOPIC WHERE THREADS.THREADID=MEMB.THREADID(+)" +
	   " AND THREADS.LASTPOST_ID = POSTS.POSTID(+) and TOPIC.TOPICID = THREADS.TOPICID " +
	   " AND THREADS.TOPICID =  ? AND THREADS.MODERATION_STAT='Y' AND THREADS.MODERATED_BY = PROFILE.USERID(+)   ORDER BY     " ;
     
	public static final String TOPIC_BOARD_SAME = "SELECT OFH.TOPICID, OFB.BOARDID FROM OI_FM_BOARDMODADMIN OFBM, OI_FM_BOARD OFB, OI_FM_TOPIC OFH WHERE OFBM.BOARDID = OFB.BOARDID AND OFB.BOARDID = OFH.BOARDID AND OFH.TOPICID = ? AND USERID = ?";
	
	public static final String QRY_UPDATE_HITS ="UPDATE OI_FM_THREAD SET HITS = HITS + 1 WHERE THREADID = ? ";
	
	public static final String QRY_POSTED ="SELECT DISTINCT THREADS.THREADID,POSTED_BY FROM OI_FM_POSTS POSTS, OI_FM_THREAD THREADS WHERE THREADS.TOPICID = ? AND POSTS.THREADID = THREADS.THREADID AND POSTED_BY IN ?";  
	
	public static final String QRY_MSGS  = " SELECT DISTINCT TITLE,'H' TTYPE,T.THREADID FROM OI_FM_THREAD T,OI_FM_POSTS P WHERE T.THREADID=P.THREADID  "+ 
										   " AND (SELECT COUNT(*) FROM OI_FM_POSTS COUNTPOST  "+
										   " WHERE COUNTPOST.POSTED_ON>(SYSDATE - (SELECT VALUE FROM OI_AD_CONFIGURATION WHERE TAG='CATEGORISETHREADS')) "+ 
										   " AND COUNTPOST.THREADID=P.THREADID)>=(SELECT VALUE FROM OI_AD_CONFIGURATION WHERE TAG='HOTTOPIC') AND T.TOPICID = ? "+ 
										   " UNION SELECT DISTINCT TITLE,'L' TTYPE,T.THREADID FROM OI_FM_THREAD T,OI_FM_POSTS P  "+
										   " WHERE T.THREADID=P.THREADID AND P.POSTED_ON > (SYSDATE - (SELECT VALUE FROM OI_AD_CONFIGURATION WHERE TAG='LATESTTOPIC') ) AND T.TOPICID = ? "+ 
										   " ORDER BY THREADID,TTYPE ASC";
	
	//public static final String QRY_LOCKED ="SELECT DISTINCT THREADS.THREADID,LOCKED FROM OI_FM_THREAD THREADS  WHERE THREADS.TOPICID = ? AND UPPER(LOCKED) =UPPER('Y')";
	
	//public static final String QRY_PRIVATE ="SELECT DISTINCT THREADS.THREADID,SECURITY FROM OI_FM_THREAD THREADS  WHERE THREADS.TOPICID = ? AND UPPER(SECURITY) =UPPER('Y')";
	
	/** Forum Listing **/
	public static final String READ_HOTTEST_THREAD = " select * from( select * from( SELECT distinct TITLE,'H' TTYPE,T.THREADID,P.POSTED_ON,hot.count FROM OI_FM_THREAD T,OI_FM_POSTS P, (SELECT THREADS.THREADID, MEMBERS.USERID  FROM OI_FM_THREAD THREADS, OI_FM_THREAD_GROUPS THREAD_GROUPS, OI_FM_GROUPMEMBERS MEMBERS  WHERE THREADS.THREADID=THREAD_GROUPS.THREADID   AND THREAD_GROUPS.GROUPID = MEMBERS.GROUPID AND MEMBERS.USERID=?) MEMB , (select count(*) count,tt.THREADID ttTHREADID from OI_FM_POSTS countPt,OI_FM_THREAD tt where tt.THREADID=countPt.THREADID and countPt.POSTED_ON> (SYSDATE - (select VALUE from OI_AD_CONFIGURATION where TAG='CATEGORISETHREADS')) group by tt.THREADID ) hot WHERE T.THREADID=P.THREADID and hot.ttTHREADID=T.THREADID and (select count(*) from OI_FM_POSTS countPost where countPost.POSTED_ON> (SYSDATE - (select VALUE from OI_AD_CONFIGURATION where TAG='CATEGORISETHREADS')) and countPost.THREADID=P.THREADID)>= (select VALUE from OI_AD_CONFIGURATION where TAG='HOTTOPIC') and T.THREADID=MEMB.THREADID(+) AND ((MEMB.USERID=? AND T.SECURITY='Y') OR T.SECURITY ='N' ) and T.MODERATION_STAT='Y' and P.POSTED_ON=T.LASTPOST_ON ORDER BY P.POSTED_ON DESC ) UNION select * from( SELECT distinct TITLE,'L' TTYPE,T.THREADID,P.POSTED_ON,hot.count FROM OI_FM_THREAD T,OI_FM_POSTS P, (SELECT THREADS.THREADID, MEMBERS.USERID  FROM OI_FM_THREAD THREADS, OI_FM_THREAD_GROUPS THREAD_GROUPS, OI_FM_GROUPMEMBERS MEMBERS  WHERE THREADS.THREADID=THREAD_GROUPS.THREADID   AND THREAD_GROUPS.GROUPID = MEMBERS.GROUPID AND MEMBERS.USERID= ?) MEMB , (select count(*) count,tt.THREADID ttTHREADID from OI_FM_POSTS countPt,OI_FM_THREAD tt where tt.THREADID=countPt.THREADID and countPt.POSTED_ON> (SYSDATE - (select VALUE from OI_AD_CONFIGURATION where TAG='CATEGORISETHREADS')) group by tt.THREADID ) hot WHERE T.THREADID=P.THREADID and hot.ttTHREADID=T.THREADID and P.POSTED_ON > (SYSDATE - (select VALUE from OI_AD_CONFIGURATION where TAG='LATESTTOPIC')) and T.THREADID=MEMB.THREADID(+) AND ((MEMB.USERID= ? AND T.SECURITY='Y') OR T.SECURITY ='N') and T.MODERATION_STAT='Y' and P.POSTED_ON=T.LASTPOST_ON ORDER BY P.POSTED_ON DESC) )ORDER BY count desc,POSTED_ON desc,THREADID";
	public static final String READ_LATEST_THREAD = "SELECT COUNT(1) count,a.TOPICID,topic.TOPICNAME from (SELECT 'L' TTYPE,T.TOPICID FROM OI_FM_THREAD T,OI_FM_POSTS P, (SELECT THREADS.THREADID, MEMBERS.USERID  FROM OI_FM_THREAD THREADS, OI_FM_THREAD_GROUPS THREAD_GROUPS, OI_FM_GROUPMEMBERS MEMBERS  WHERE THREADS.THREADID=THREAD_GROUPS.THREADID   AND THREAD_GROUPS.GROUPID = MEMBERS.GROUPID AND MEMBERS.USERID= ?) MEMB WHERE T.THREADID=P.THREADID and P.POSTED_ON > (SYSDATE - (select VALUE from OI_AD_CONFIGURATION where TAG='LATESTTOPIC')) and T.THREADID=MEMB.THREADID(+) AND ((MEMB.USERID= ? AND T.SECURITY='Y') OR T.SECURITY ='N')) a, OI_FM_TOPIC Topic where Topic.TOPICID=a.TOPICID  and rownum<=3 GROUP BY a.TOPICID,topic.TOPICNAME"; 
	public static final String READ_HQ_REPLIES = "select * from (SELECT distinct TITLE,'H' TTYPE,T.THREADID,P.POSTED_ON FROM OI_FM_THREAD T,OI_FM_POSTS P, (SELECT THREADS.THREADID, MEMBERS.USERID  FROM OI_FM_THREAD THREADS, OI_FM_THREAD_GROUPS THREAD_GROUPS, OI_FM_GROUPMEMBERS MEMBERS  WHERE THREADS.THREADID=THREAD_GROUPS.THREADID   AND THREAD_GROUPS.GROUPID = MEMBERS.GROUPID AND MEMBERS.USERID= ?) MEMB WHERE T.THREADID=P.THREADID and (select count(*) from OI_FM_POSTS countPost where countPost.POSTED_ON>(SYSDATE - (select VALUE from OI_AD_CONFIGURATION where TAG='CATEGORISETHREADS')) and countPost.THREADID=P.THREADID)>= (select VALUE from OI_AD_CONFIGURATION where TAG='HOTTOPIC') and upper(P.HQREPLY)='Y' and T.THREADID=MEMB.THREADID(+) AND  ((MEMB.USERID= ? AND T.SECURITY='Y') OR T.SECURITY ='N') UNION SELECT distinct TITLE,'L' TTYPE,T.THREADID,P.POSTED_ON FROM OI_FM_THREAD T,OI_FM_POSTS P, (SELECT THREADS.THREADID, MEMBERS.USERID  FROM OI_FM_THREAD THREADS, OI_FM_THREAD_GROUPS THREAD_GROUPS,  OI_FM_GROUPMEMBERS MEMBERS  WHERE THREADS.THREADID=THREAD_GROUPS.THREADID   AND THREAD_GROUPS.GROUPID = MEMBERS.GROUPID AND MEMBERS.USERID= ?) MEMB WHERE T.THREADID=P.THREADID and P.POSTED_ON > (SYSDATE - (select VALUE from OI_AD_CONFIGURATION where TAG='LATESTTOPIC')) and upper(P.HQREPLY)='Y' and  T.THREADID=MEMB.THREADID(+) AND   ((MEMB.USERID= ? AND T.SECURITY='Y') OR T.SECURITY ='N') ) order by  THREADID desc,POSTED_ON desc";
	//public static final String READ_UPTO_TOPIC = "SELECT PARENT_ID, level HRNO, REPLACE(CHILD_ID,'T','') TOPICID, NAME FROM (SELECT '' PARENT_ID, 'C'||C.CATEGORYID CHILD_ID, C.NAME NAME FROM OI_FM_CATEGORY C UNION SELECT PARENT_ID, CHILD_ID, NAME FROM (SELECT 'C'||B.CATEGORYID PARENT_ID, 'B'||B.BOARDID CHILD_ID, B.NAME NAME FROM OI_FM_BOARD B UNION SELECT 'B'||B.BOARDID PARENT_ID, 'T'||T.TOPICID CHILD_ID, T.TOPICNAME NAME FROM OI_FM_BOARD B, OI_FM_TOPIC T WHERE B.BOARDID=T.BOARDID) START WITH PARENT_ID like 'C%' CONNECT BY PRIOR CHILD_ID = PARENT_ID) START WITH PARENT_ID IS NULL CONNECT BY PRIOR CHILD_ID = PARENT_ID ORDER BY SIBLINGS BY CHILD_ID";
	//public static final String READ_UPTO_TOPIC = "SELECT PARENT_ID, level HRNO, REPLACE(CHILD_ID,'T','') TOPICID, NAME FROM (SELECT '' PARENT_ID, 'C'||C.CATEGORYID CHILD_ID, C.NAME NAME FROM OI_FM_CATEGORY C UNION SELECT PARENT_ID, CHILD_ID, NAME FROM (SELECT 'C'||B.CATEGORYID PARENT_ID, 'B'||B.BOARDID CHILD_ID, B.NAME NAME FROM OI_FM_BOARD B UNION SELECT 'B'||B.BOARDID PARENT_ID, 'T'||T.TOPICID CHILD_ID, T.TOPICNAME NAME FROM OI_FM_BOARD B, OI_FM_TOPIC T WHERE B.BOARDID=T.BOARDID) START WITH PARENT_ID like 'C%' CONNECT BY PRIOR CHILD_ID = PARENT_ID) START WITH PARENT_ID IS NULL CONNECT BY PRIOR CHILD_ID = PARENT_ID ORDER SIBLINGS BY  CHILD_ID";
	//public static final String READ_POST_LIST = "select * from(select topic.topicid,topicName,thread.THREADID,thread.TITLE,post.POSTED_ON,post.NICKNAME, post.POSTED_BY from OI_FM_TOPIC topic,OI_FM_THREAD thread,OI_FM_POSTS post, ( SELECT THREADS.THREADID, MEMBERS.USERID  FROM OI_FM_THREAD THREADS, OI_FM_THREAD_GROUPS THREAD_GROUPS,  OI_FM_GROUPMEMBERS MEMBERS  WHERE THREADS.THREADID=THREAD_GROUPS.THREADID   AND THREAD_GROUPS.GROUPID = MEMBERS.GROUPID  AND MEMBERS.USERID= ?) MEMB where topic.TOPICID=thread.TOPICID and  post.POSTED_ON=(select max(post1.POSTED_ON) from OI_FM_POSTS  post1 where post1.THREADID=thread.THREADID)  and post.POSTID=thread.LASTPOST_ID and thread.THREADID=MEMB.THREADID(+) AND ((( USERID= ? or thread.created_by=?) AND thread.SECURITY='Y') OR thread.SECURITY ='N') ) a, (select count(*) count,topic.TOPICID tTOPICID from OI_FM_TOPIC topic,OI_FM_THREAD thread where topic.TOPICID=thread.TOPICID and thread.MODERATION_STAT='Y' group by topic.TOPICID ) b where a.topicid=b.tTOPICID order by topicId desc,POSTED_ON desc";
	public static final String READ_UPTO_TOPIC = "SELECT PARENT_ID, LEVEL HRNO, REPLACE(CHILD_ID,'T','') TOPICID, NAME FROM (SELECT NULL PARENT_ID, 'C'||C.CATEGORYID CHILD_ID, C.NAME NAME FROM OI_FM_CATEGORY C UNION SELECT 'C'||B.CATEGORYID PARENT_ID, 'B'||B.BOARDID CHILD_ID, B.NAME NAME FROM OI_FM_BOARD B UNION SELECT 'B'||T.BOARDID PARENT_ID, 'T'||T.TOPICID CHILD_ID, T.TOPICNAME NAME FROM OI_FM_TOPIC T) START WITH PARENT_ID IS NULL CONNECT BY PRIOR CHILD_ID = PARENT_ID ORDER SIBLINGS BY TO_NUMBER(REPLACE(REPLACE(REPLACE(CHILD_ID,'T', ''),'B',''),'C',''))";
	
	public static final String READ_POST_LIST1 = "select * from(select topic.topicid,topicName,thread.THREADID,thread.TITLE,post.POSTED_ON,post.NICKNAME, post.POSTED_BY from OI_FM_TOPIC topic,OI_FM_THREAD thread,OI_FM_POSTS post, ( SELECT THREADS.THREADID, MEMBERS.USERID  FROM OI_FM_THREAD THREADS, OI_FM_THREAD_GROUPS THREAD_GROUPS,  OI_FM_GROUPMEMBERS MEMBERS  WHERE THREADS.THREADID=THREAD_GROUPS.THREADID   AND THREAD_GROUPS.GROUPID = MEMBERS.GROUPID  AND MEMBERS.USERID= ?) MEMB where topic.TOPICID=thread.TOPICID and  post.POSTED_ON=(select max(post1.POSTED_ON) from OI_FM_POSTS  post1 where post1.THREADID=thread.THREADID)  and post.POSTID=thread.LASTPOST_ID and thread.THREADID=MEMB.THREADID(+) AND ((( USERID= ? or thread.created_by=?) AND thread.SECURITY='Y') OR thread.SECURITY ='N') ) a, (select count(*) count,topic.TOPICID tTOPICID from OI_FM_TOPIC topic,OI_FM_THREAD thread where topic.TOPICID=thread.TOPICID and thread.MODERATION_STAT='Y' group by topic.TOPICID ) b where a.topicid=b.tTOPICID ";
	public static final String READ_POST_LIST2 =" order by topicId desc,POSTED_ON desc";
	
	public static final String QRY_TOPICNAME= "SELECT TOPICNAME FROM OI_FM_TOPIC WHERE TOPICID = ?";
	public static final String QRY_STICKYTHREAD ="SELECT DISTINCT THREADID FROM OI_FM_BOOKMARKS WHERE BKM_STICKY = 'S' AND USERID = ?";
	public static final String READ_POST_LIST_SEARCH = "select * from(select topic.topicid,topicName,thread.THREADID,thread.TITLE,post.POSTED_ON,post.NICKNAME, post.POSTED_BY,DECODE(REPLIES,0,'',REPLIES) REP ,DECODE(HITS,0,'',HITS) HIT from OI_FM_TOPIC topic,OI_FM_THREAD thread,OI_FM_POSTS post, ( SELECT THREADS.THREADID, MEMBERS.USERID  FROM OI_FM_THREAD THREADS, OI_FM_THREAD_GROUPS THREAD_GROUPS,  OI_FM_GROUPMEMBERS MEMBERS  WHERE THREADS.THREADID=THREAD_GROUPS.THREADID   AND THREAD_GROUPS.GROUPID = MEMBERS.GROUPID  AND MEMBERS.USERID= ?) MEMB where topic.TOPICID=thread.TOPICID and  round((sysdate-POSTED_ON)*24) <= ?  and post.POSTID=thread.LASTPOST_ID and thread.THREADID=MEMB.THREADID(+) AND (((MEMB.USERID= ? or thread.created_by=?) AND thread.SECURITY='Y') OR thread.SECURITY ='N') ) a, (select count(*) count,topic.TOPICID tTOPICID  from OI_FM_TOPIC topic,OI_FM_THREAD thread where topic.TOPICID=thread.TOPICID and thread.MODERATION_STAT='Y' group by topic.TOPICID ) b where a.topicid=b.tTOPICID order by topicId desc,POSTED_ON desc";
	public static final String READ_POST_LIST_SEARCH_BM = "select * from(select topic.topicid,topicName,thread.THREADID,thread.TITLE,post.POSTED_ON,post.NICKNAME,post.POSTED_BY,DECODE(REPLIES,0,'',REPLIES) REP ,DECODE(HITS,0,'',HITS) HIT from OI_FM_TOPIC topic,OI_FM_THREAD thread,OI_FM_POSTS post, ( SELECT THREADS.THREADID, MEMBERS.USERID  FROM OI_FM_THREAD THREADS, OI_FM_THREAD_GROUPS THREAD_GROUPS,  OI_FM_GROUPMEMBERS MEMBERS WHERE THREADS.THREADID=THREAD_GROUPS.THREADID   AND THREAD_GROUPS.GROUPID = MEMBERS.GROUPID AND MEMBERS.USERID= ?) MEMB where topic.TOPICID=thread.TOPICID  and post.POSTID=thread.LASTPOST_ID and thread.THREADID=MEMB.THREADID(+) AND (((MEMB.USERID= ? or thread.created_by=?) AND thread.SECURITY='Y') OR thread.SECURITY ='N') ) a, (select count(*) count,topic.TOPICID tTOPICID from OI_FM_TOPIC topic,OI_FM_THREAD thread where topic.TOPICID=thread.TOPICID and thread.MODERATION_STAT='Y' group by topic.TOPICID ) b ,(select count(BOOKMARKID) bkcount, topic.TOPICID tTOPICID from OI_FM_TOPIC topic,OI_FM_THREAD thread,OI_FM_BOOKMARKS bk where topic.TOPICID=thread.TOPICID and thread.MODERATION_STAT='Y' and bk.threadid = thread.threadid  group by topic.TOPICID ) c where a.topicid=b.tTOPICID and b.tTOPICID=c.tTOPICID  and c.bkcount > 0 order by topicId desc,POSTED_ON desc";
	public static final String READ_POST_LIST_SEARCH_PO = "select * from(select topic.topicid,topicName,thread.THREADID,thread.TITLE,post.POSTED_ON,post.NICKNAME,post.POSTED_BY,DECODE(REPLIES,0,'',REPLIES) REP ,DECODE(HITS,0,'',HITS) HIT from OI_FM_TOPIC topic,OI_FM_THREAD thread,OI_FM_POSTS post, ( SELECT THREADS.THREADID, MEMBERS.USERID  FROM OI_FM_THREAD THREADS, OI_FM_THREAD_GROUPS THREAD_GROUPS,  OI_FM_GROUPMEMBERS MEMBERS WHERE THREADS.THREADID=THREAD_GROUPS.THREADID   AND THREAD_GROUPS.GROUPID = MEMBERS.GROUPID AND MEMBERS.USERID= ?) MEMB where topic.TOPICID=thread.TOPICID  and post.POSTID=thread.LASTPOST_ID and thread.THREADID=MEMB.THREADID(+) AND (((MEMB.USERID= ? or thread.created_by=?) AND thread.SECURITY='Y') OR thread.SECURITY ='N') ) a, (select count(*) count,topic.TOPICID tTOPICID from OI_FM_TOPIC topic,OI_FM_THREAD thread where topic.TOPICID=thread.TOPICID and thread.MODERATION_STAT='Y' group by topic.TOPICID ) b ,(select count(BOOKMARKID) bkcount, topic.TOPICID tTOPICID from OI_FM_TOPIC topic,OI_FM_THREAD thread,OI_FM_BOOKMARKS bk where topic.TOPICID=thread.TOPICID and thread.MODERATION_STAT='Y' and bk.threadid = thread.threadid  group by topic.TOPICID ) c where a.topicid=b.tTOPICID and b.tTOPICID=c.tTOPICID  and c.bkcount > 0 order by topicId desc,POSTED_ON desc";

//Anbu added
	public static final String QRY_ADMIN_STICKYTHREAD ="SELECT DISTINCT THREADID FROM OI_FM_BOOKMARKS WHERE BKM_STICKY = 'A'";
	
	//check for already posted
	public static final String QRY_WP_ALREADY_POSTED= "SELECT THREADS.THREADID FROM OI_FM_POSTS POSTS,"+ 
	"OI_FM_THREAD THREADS WHERE THREADS.TOPICID = ? AND POSTS.THREADID = THREADS.THREADID AND "+
	"POSTED_BY IN ? AND THREADS.THREADID=? ";
	//check for hottest and latest
	public static final String QRY_WP_HL = " SELECT DISTINCT TITLE,'H' TTYPE,T.THREADID FROM OI_FM_THREAD T,OI_FM_POSTS P WHERE T.THREADID=P.THREADID "+   
	" AND (SELECT COUNT(*) FROM OI_FM_POSTS COUNTPOST   "+
	" WHERE COUNTPOST.POSTED_ON>(SYSDATE - (SELECT VALUE FROM OI_AD_CONFIGURATION WHERE TAG='CATEGORISETHREADS')) "+  
	" AND COUNTPOST.THREADID=P.THREADID)>=(SELECT VALUE FROM OI_AD_CONFIGURATION WHERE TAG='HOTTOPIC') AND T.TOPICID = ? "+  
	" AND  T.THREADID=? "+
	" UNION SELECT DISTINCT TITLE,'L' TTYPE,T.THREADID FROM OI_FM_THREAD T,OI_FM_POSTS P "+   
	" WHERE T.THREADID=P.THREADID AND P.POSTED_ON > (SYSDATE - (SELECT VALUE FROM OI_AD_CONFIGURATION WHERE "+  
	" TAG='LATESTTOPIC') ) AND T.TOPICID = ? AND  T.THREADID=?  "+
	" ORDER BY THREADID,TTYPE ASC  ";
 
	//check for stick and admin sticky
	public static final String QRY_WP_STICKYTHREAD  = " SELECT THREADID FROM OI_FM_BOOKMARKS WHERE BKM_STICKY IN('S','A') AND USERID = ? AND THREADID =?";
//Check for loc or private
	public static final String QRY_WP_LP = "SELECT THREADS.LOCKED,THREADS.SECURITY  FROM OI_FM_THREAD THREADS, "+  
 		" (SELECT THREADS.THREADID, MEMBERS.USERID FROM  "+
		" OI_FM_THREAD THREADS, OI_FM_THREAD_GROUPS THREAD_GROUPS, "+
		" OI_FM_GROUPMEMBERS MEMBERS WHERE THREADS.THREADID=THREAD_GROUPS.THREADID "+ 
		" AND THREAD_GROUPS.GROUPID = MEMBERS.GROUPID  "+
		" AND MEMBERS.USERID= ?) MEMB, OI_FM_POSTS POSTS,OI_AD_PROFILE PROFILE ,OI_FM_TOPIC TOPIC "+ 
		" WHERE THREADS.THREADID=MEMB.THREADID(+) "+
		" AND  (((MEMB.USERID= ? OR THREADS.CREATED_BY= ?) AND THREADS.SECURITY='Y') OR "+ 
		" THREADS.SECURITY ='N') AND THREADS.LASTPOST_ID = POSTS.POSTID(+) and TOPIC.TOPICID = THREADS.TOPICID "+ 
		" AND THREADS.TOPICID =  ? AND THREADS.MODERATION_STAT='Y' AND THREADS.MODERATED_BY = PROFILE.USERID(+)  "+        
		" AND THREADS.THREADID=? ";

}
