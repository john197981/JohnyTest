package com.oifm.common;

/**
 * @author Rakesh 		mailto@ : Rakesh.Chagallu@cambridge-asia.com
 * Desc : This is designed for  Sql Queries Variables, constants declared in 
 * OIBOASMCategory (Business Object)
 * Date : 15 Jan 2008
 */
public interface OIASMCategoryConstant 
{
	// For Queries variables
	public static final String	Q_CATEGORYID = "CATEGORYID";
	public static final String	Q_NAME = "NAME";
	public static final String	Q_DESCRIPTION = "DESCRIPTION";
	
	
	// For Struts Mappings 
	public static final String POPULATE_ASMCATEGORY = "populate";
	public static final String POPULATEMESSAGE_ASMCATEGORY = "populateMessage";
	
	// Keys
	public static final String	K_arOIBAASMCategory = "arOIBAASMCategory";
	public static final String	K_aOIBAASMCategory = "aOIBAASMCategory";
	
	
	// Form Bean
	public static final String	ASM_CATEGORY_FORM = "ASMCategoryForm";
	//public static final String	CONSULT_CATEGORY_FORM = "ConsultCategoryForm";
	
	// ASM admin
	public static final String	K_categoryID = "categoryID";
	
	public static final String CATEGORYLIST = "CATEGORYID";
	public static final String LIST_CATEGORY = "CategoryList";
}

