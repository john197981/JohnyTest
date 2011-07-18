 package com.oifm.codemaster;
/*
 * Class Name:
 * Description:
 * 
 * 	Created By			Created/Modified on			Revision				Remarks
 * -----------------------------------------------------------------------------------------------------
 * 	Rajkumar			06/07/2005					Draft					Inital Version
 * 
 * -----------------------------------------------------------------------------------------------------
 */

public class OICodeMasterSqls 
{
	/** Query to Fetch the Role **/
	public static final String  QRY_CODE_TYPE = "SELECT DISTINCT TYPE FROM OI_AD_CODE_MASTER ORDER BY TYPE";

	/** Query to Fetch the Role **/
	public static final String  CODE_TYPE = "TYPE = ";

	public static final String QRY_SERACH = "SELECT CODEID, TYPE,VALUE,DESCRIPTION,SHORTNAME,OBSOLETE FROM OI_AD_CODE_MASTER";	
	
	public static final String 	WHERE  = " WHERE ";  
	public static final String  VALUE =  "UPPER(VALUE) LIKE UPPER('%";
	public static final String  DESCRIPTION ="UPPER(DESCRIPTION) LIKE UPPER('%";
	public static final String  SHORTNAME ="UPPER(SHORTNAME) LIKE UPPER('%";
	public static final String  OBSOLETE = "UPPER(OBSOLETE) = UPPER('";  
	public static final String  PERCENT ="%')";
	public static final String  ORDER_BY = " ORDER BY TYPE ";
	public static final String  AND 	= " AND ";
		
	
	public static final String QRY_UPDATE = "UPDATE OI_AD_CODE_MASTER SET DESCRIPTION = ?,SHORTNAME = ?,OBSOLETE = ? WHERE CODEID = ?";
	
	public static final String READ_CODEMASTERTYPE = "select CODEID,TYPE,VALUE, DESCRIPTION, OBSOLETE from OI_AD_CODE_MASTER where TYPE in (?,?)";
	public static final String READ_CODEMASTERTYPE_ONE = "select CODEID,TYPE,VALUE, DESCRIPTION, OBSOLETE from OI_AD_CODE_MASTER where TYPE in (?)";
    public static final String READ_CODEMASTERTYPEVALUE = "select CODEID,TYPE,VALUE, DESCRIPTION, OBSOLETE from OI_AD_CODE_MASTER where TYPE = ? and VALUE=?";
    public static final String CODETYPE="codetype";
    
    public static final String QRY_SLT_CDEMASTER ="SELECT CODEID,TYPE,VALUE,DESCRIPTION,SHORTNAME,OBSOLETE FROM OI_AD_CODE_MASTER WHERE CODEID = ?";
}
