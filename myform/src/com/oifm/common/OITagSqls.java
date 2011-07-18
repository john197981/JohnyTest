package com.oifm.common;

public interface OITagSqls {

	
	//public static final String GET_TAG = "SELECT distinct a.tag_id tag_id, name FROM oi_tags a, OI_TAGS_MAP b WHERE a.tag_id = b.tag_id and b.MODULE_TYPE = 'BG'";
	public static final String GET_TAG = "SELECT tag_id, name FROM oi_tags order by UPPER(name)";
	public static final String CHECK_TAG = "SELECT tag_ID FROM oi_tags WHERE name = ?";
	public static final String INSERT_NEW_TAG = "INSERT INTO OI_TAGS (TAG_ID, NAME) VALUES (SEQ_OI_BG_TAGID.NEXTVAL, ?)";
	public static final String DELETE_Profile_TAG = "DELETE FROM OI_TAGS_MAP WHERE MODULE_TYPE = 'PR' AND USERID = ?";
	public static final String INSERT_Profile_TAG = "INSERT INTO OI_TAGS_MAP (TAG_ID,MODULE_TYPE,USERID) VALUES (?, 'PR', ?)";
	public static final String GET_Profile_TAG = "SELECT a.tag_id tagId, name FROM oi_tags a, oi_tags_map b WHERE a.tag_id = b.tag_id AND b.MODULE_TYPE = 'PR' AND b.userid = ?";

	public static final String INSERT_TAG = "INSERT INTO OI_TAGS_MAP (TAG_ID,MODULE_TYPE,MODULE_ID) VALUES (?, ?, ?)";
	public static final String DELETE_TAG = "DELETE FROM OI_TAGS_MAP WHERE MODULE_TYPE = ? AND MODULE_ID = ?";


}
