package com.oifm.login;
/*
 * Class Name:
 * Description:
 * 
 * 	Created By			Created/Modified on			Revision				Remarks
 * -----------------------------------------------------------------------------------------------------
 * 	Rajkumar			28/06/2005					Draft					Inital Version
 * 
 * -----------------------------------------------------------------------------------------------------
 */
 
public interface OILoginSqls 
{
	//public static final String	READ_ROLES = "select function.name afunctionName, functionrole.FUNCTIONID afunctionId,role.ROLEID aroleId,role.NAME aroleName,profile.DIVISIONCODE divisionCode,profile.SALUTATION || ' ' || profile.NAME  Name from OI_AD_ROLE role,OI_AD_PROFILE profile,OI_AD_FUNCTIONROLE functionrole, OI_AD_FUNCTIONS function where profile.USERID=? and role.ROLEID=profile.ROLEID and role.ROLEID=functionrole.ROLEID and function.FUNCTIONID=functionrole.FUNCTIONID";
    public static final String	READ_ROLES = "select function.name afunctionName, functionrole.FUNCTIONID afunctionId,role.ROLEID aroleId,role.NAME aroleName,profile.DIVISIONCODE divisionCode,profile.NAME  Name,profile.EMAILID,profile.nickname nick,profile.USERID from OI_AD_ROLE role,OI_AD_PROFILE profile,OI_AD_FUNCTIONROLE functionrole, OI_AD_FUNCTIONS function where upper(profile.USERID)=upper(?) and role.ROLEID=profile.ROLEID and role.ROLEID=functionrole.ROLEID and function.FUNCTIONID=functionrole.FUNCTIONID";
	public static final String	CHECK_USERID = "select count(*) aUserCount from OI_AD_PROFILE profile where upper(profile.userid)=upper(?) and nvl(OBSOLETE,'N')='N'";
	public static final String	CHECK_TMP_USER = "select SURVEYID from OI_SU_SURVEY where SURVEYTAG = (select SURVEYTAG from OI_SU_TEMPSURVEYUSER where upper(TEMPSURVEYUSERID) = upper(?) and upper(password)=upper(?) and nvl(OBSOLETE,'N')='N')";
    public static final String	READ_USER_NAME = "select profile.NAME  Name from OI_AD_PROFILE profile where upper(profile.USERID)=upper(?)";
    
    /** ADMIN **/
	public static final String QRY_CHK_ADMIN = " SELECT ROLEID  FROM OI_AD_PROFILE WHERE ROLEID IN ( "+
	 											" SELECT DISTINCT ROLEID FROM OI_AD_FUNCTIONROLE WHERE FUNCTIONID IN('MTNCTBD') "+
												" AND upper(USERID) = upper(?) ) ";
	/**DIVADMIN **/ 
	public static final String QRY_CHK_DIVADMIN = " SELECT ROLEID FROM OI_AD_PROFILE WHERE ROLEID IN (SELECT ROLEID FROM OI_AD_FUNCTIONROLE WHERE FUNCTIONID IN('MNTBORD','MTNTOPI') "+ 
	 											   " MINUS SELECT ROLEID FROM OI_AD_FUNCTIONROLE WHERE FUNCTIONID IN('MTNCTBD')) AND upper(USERID) = upper(?) ";

	/**MODERATOR**/ 
	public static final String QRY_CHK_MODERATOR = " SELECT ROLEID FROM OI_AD_PROFILE WHERE ROLEID IN (SELECT ROLEID FROM OI_AD_FUNCTIONROLE WHERE FUNCTIONID IN ('MODPOST') "+
												   " MINUS SELECT ROLEID FROM OI_AD_FUNCTIONROLE WHERE FUNCTIONID IN('MNTBORD','MTNTOPI','MTNCTBD')) AND upper(USERID) = upper(?) ";
	 
}