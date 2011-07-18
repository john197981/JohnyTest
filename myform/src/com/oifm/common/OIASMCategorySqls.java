package com.oifm.common;

/**
 * @author Rakesh		mail@ : Rakesh.Chagallu@cambridge-asia.com
 * Desc : Sql queries for ASM Category
 * Date : 15 Jan 2008
 */
import com.oifm.common.OILabelConstants;
public interface OIASMCategorySqls
{
	public static final String	READ_CATEGORY = "select CATEGORYID,NAME,DESCRIPTION from OI_AM_CATEGORY category where category.CATEGORYID=?";
	public static final String	READ_ALL_CATEGORY = "select CATEGORYID,NAME,DESCRIPTION from OI_AM_CATEGORY category where category.CATEGORYID<>1 order by NAME";
	public static final String	SAVE_CATEGORY = "INSERT INTO OI_AM_CATEGORY (CATEGORYID,NAME,DESCRIPTION,CREATED_BY, CREATED_ON) VALUES (SEQ_OI_AM_CATEGORYID.NEXTVAL,?,?,?,SYSDATE)";
	public static final String	UPDATE_CATEGORY = "update OI_AM_CATEGORY set NAME=?, DESCRIPTION=? where CATEGORYID=?";
	public static final String	DELETE_CATEGORY = "delete from OI_AM_CATEGORY category where category.CATEGORYID=?";
	public static final String  ASM_CHECK_DUPLICATE_CATEGORY = "select count(*) count from OI_AM_CATEGORY category where TRIM(lower(category.NAME))=TRIM(lower(?))";
	public static final String  ASM_CHECK_DUPLICATE_CATEGORY1 = "select count(*) count from OI_AM_CATEGORY category where TRIM(lower(category.NAME))=TRIM(lower(?)) and category.CATEGORYID<>?";
	public static final String	UPDATE_LETTERS ="update OI_AM_LETTERS set CATEGORYID=1 where CATEGORYID=?";
}

