/*****************************************************************************
* Title 		: OIDAOAdminUserProfile.java
* Description 	: This DAO file is to maintain the Code master records.    
* Author		: Suresh Kumar.R 
* Version No 	: 1.0
* Date Created 	: 01 - Aug -2005
* Copyright 	: Scandent Group
********************************************************************************/
package com.oifm.useradmin.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;
import com.oifm.common.OILabelConstants;
import com.oifm.useradmin.OIBVUserProfileSearch;
import com.oifm.useradmin.OISearchConstants;
import com.oifm.useradmin.OISearchSqls;
import com.oifm.utility.OIDBRegistry;
import com.oifm.utility.OIFormUtilities;
import com.oifm.utility.OIUtilities;

/** 
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class OIDAOAdminUserProfile 
{
	transient private final Logger logger = Logger.getLogger(this.getClass().getName());
	private StringBuffer sbQuery = new StringBuffer(0);
	private StringBuffer sbQryWhere = new StringBuffer(0);
	private StringBuffer sbSubQuery = new StringBuffer(OIAdminSqls.SRCHSUBQUERY);
	private StringBuffer sbSubQryWhere = new StringBuffer(" FROM OI_AD_PROFILE PROFILE ");
	private StringBuffer sbSubQryJoin = new StringBuffer(0);
	private StringBuffer sbSubOb = new StringBuffer(" (PROFILE.obsolete IS NULL OR PROFILE.obsolete NOT IN ('e','E') ) ");
	private ArrayList alColNames = null;
	public OIDAOAdminUserProfile()
	{
		logger.debug(OILabelConstants.BEGIN_METHOD_DAO + this.getClass().getName());
	} 
		
	/**
	 * This method populates List of School Names,Cluster,School Types
	 * @param connection
	 * @return ArrayList
	 * @throws Exception
	 */
    
	public ArrayList readList(Connection objCon,String strQuery) throws Exception
	{
    	logger.debug(OILabelConstants.BEGIN_METHOD_DAO +"readList"); 
    	ArrayList alList=null;
        ResultSet objRs =null;
        PreparedStatement objPs=null;
        boolean bFlag = false;
        try
        { 
        	objPs = objCon.prepareStatement(strQuery);
           	objRs = objPs.executeQuery();
            String strLabel = null;
            String strValue = null;
            if(objRs!= null)
            {
           		bFlag = true;
           		alList = new ArrayList(); 
           		alList.add(new org.apache.struts.util.LabelValueBean(OIDBRegistry.getValues(OILabelConstants.PLS_SELECT),""));
           		while(objRs.next())
           		{
           			strLabel = OIFormUtilities.chkIsNull(objRs.getString(1));
           			strValue = OIFormUtilities.chkIsNull(objRs.getString(2));
           			if(strLabel.length()>0 && strValue.length()>0)
           			{
           				alList.add(new org.apache.struts.util.LabelValueBean(strLabel,strValue));
           			}	
           		}
        	} 
        }
        catch(Exception sqle)
        {
			logger.error(sqle.getMessage());
			bFlag = false;
			throw sqle;
		}
        finally
        {
			if (objPs!= null)
			{
				objPs.close();
			}
			if (objRs!= null)
			{
				objRs.close();
			}
		}
        if (alList.size()==0)
        {
        	alList=null;
        }		
             
    	logger.debug(OILabelConstants.END_METHOD_DAO +"readList");
    	return alList;
    }

    
    /**
     * This method fetch the profile information with the given search criteria
     * @param objCon
     * @param objBVUsrPrfSrh
     * @param nStartNum
     * @param nEndNum
     * @return
     * @throws Exception
     */
     public ArrayList searchUserProfile(Connection objCon, OIBVAdminUserProfile objBVUsrPrfSrh,int nStartNum, int nEndNum) throws Exception
     {
         logger.debug(OILabelConstants.BEGIN_METHOD_DAO +"searchUserProfile"); 
         
         ArrayList arSrhUsrPrf=null;
         ResultSet objRs =null;
         PreparedStatement objPs=null;
         OIBVUserProfileSearch objBVUsr = null;
         LinkedHashMap hmSearch = null; 
 		 boolean bFlag = false;  
         String strQuery = null; 
         try
         { 
             String strColName[] = objBVUsrPrfSrh.getColName();

             if(objBVUsrPrfSrh.getQuery()==null)
             {
                 strQuery = OISearchSqls.QRYROW1+ sbQuery.append(OISearchSqls.QRYROW2).toString();
             } 
             else
             {
                 strQuery  = OISearchSqls.QRYROW1+ objBVUsrPrfSrh.getQuery() + OISearchSqls.QRYROW2; 
             }
	         String strTemp = addSorting(objBVUsrPrfSrh, strQuery);
	        // System.out.println("OIDAOAdminUserProfile-searchUserProfile strTemp="+strTemp);
	         objPs = objCon.prepareStatement(strTemp);
	         objPs.setInt(1,nStartNum);
	         objPs.setInt(2,nEndNum);
	         int nLen = strColName.length;
	         objRs = objPs.executeQuery();
	         if( strColName.length >0 && objRs!= null)
	         {
	             bFlag = true;
	             arSrhUsrPrf = new ArrayList();
	             while(objRs.next())
	             {
	                 hmSearch = new LinkedHashMap();
	                 for(int i = 0; i < nLen ;i++)
	                 {
	                     hmSearch.put(strColName[i],OIFormUtilities.chkIsNull(objRs.getString(strColName[i].trim())));
	                 }
	                 arSrhUsrPrf.add(hmSearch);
	                // System.out.println("hmSearch" + hmSearch);
	             }
	         } 
         }
         catch(Exception sqle)
         {
             logger.error(sqle.getMessage());
             bFlag = false;
             throw sqle;
         }
         finally
         {
             if (objPs!= null)
             {
                 objPs.close();
             }
             if (objRs!= null)
             {
                 objRs.close();
             }
         }
         if (arSrhUsrPrf.size()==0)
         	arSrhUsrPrf=null;
         logger.debug(OILabelConstants.END_METHOD_DAO +"searchUserProfile");
         return arSrhUsrPrf;
     }
  
     /**
      * This is the helper method to construct search criteria query.
      * @param objBVUsrPrfSrh
      * @return Strng
      */ 
     	         
     private String constructQuery (OIBVAdminUserProfile objBVUsrPrfSrh) throws Exception
     {
     	logger.debug(OILabelConstants.BEGIN_METHOD_DAO +"constructQuery");
     	StringBuffer sbSltsubQuery = new StringBuffer(0);
     	String strTemp = null;
     	String strName = null;
     	boolean bCCA = false;
     	boolean bAge = false;
     	boolean bJoinDt = false;
     	boolean bDivadmin = false;
     	String strColName[] = objBVUsrPrfSrh.getColName();
     	alColNames = objBVUsrPrfSrh.getAlListCol();
     	sbQuery.append(OIAdminSqls.SELECT);
     	     	
        /** NRIC **/
     	chkBlank("USERID",objBVUsrPrfSrh.getNRIC());
     	 
     	/**Role  DropDown**/
     	chkEqual("ROLEID",objBVUsrPrfSrh.getRole(),"","","","ROLEID");
     	
     	/** NAME **/
     	chkBlank("NAME",objBVUsrPrfSrh.getName());
     	     	
     	/** SCHOOL DropDown**/
     	chkEqual("SCHOOL",objBVUsrPrfSrh.getSchool()," OI_AD_SCHOOLS SCHOOLS ","PROFILE.SCHOOLCODE= SCHOOLS.SCHOOLCODE(+) ", " SCHOOLS.SCHOOLNAME SCHOOL "," SCHOOLCODE " );
     			
		/** NICKNAME **/
     	chkBlank("NICKNAME",objBVUsrPrfSrh.getNickName());
     	
		/** SCHOOL_TYPE DropDown**/
     	chkEqual("SCHOOL_TYPE",objBVUsrPrfSrh.getSchoolType()," OI_AD_CODE_MASTER SCHLVL ", " (SCHLVL.TYPE='SCHOOL_LEVEL' OR SCHLVL.TYPE IS NULL) AND  PROFILE.SCHOOLLVLCODE=SCHLVL.VALUE(+) ", "SCHLVL.DESCRIPTION SCHOOL_TYPE ", " SCHOOLLVLCODE " );
     	
		/** EMAILID **/
     	chkBlank("EMAILID",objBVUsrPrfSrh.getMailId());
     			
		/** AGE **/
		 if(objBVUsrPrfSrh.getAlListCol().contains("AGE"))
		 {
		     /**Select Clause **/
		     sbQuery.append(" AGE ").append(OISearchSqls.COMMA);
		     bAge = true;
		 }
		if(OIFormUtilities.chkIsNull(objBVUsrPrfSrh.getFromAge()).length()!=0 && OIFormUtilities.chkIsNull(objBVUsrPrfSrh.getToAge()).length()!=0)
		{
		    bAge = true;
		    /**Where Clause **/
			sbQryWhere.append(" AGE ")
				.append(OISearchSqls.EQUALGREATER)
				.append(Integer.parseInt(OIFormUtilities.chkIsNull(objBVUsrPrfSrh.getFromAge())))
				.append(OISearchSqls.AND)
				.append(" AGE ")
				.append(OISearchSqls.LESSTHANEQUAL)
				.append(Integer.parseInt(OIFormUtilities.chkIsNull(objBVUsrPrfSrh.getToAge())))
				.append(OISearchSqls.AND);
		}
		else if (OIFormUtilities.chkIsNull(objBVUsrPrfSrh.getFromAge()).length()!=0)
		{
			/**Where Clause **/
		    bAge = true;
			sbQryWhere.append(" AGE ")  
				.append(OISearchSqls.EQUALGREATER)
				.append(Integer.parseInt(OIFormUtilities.chkIsNull(objBVUsrPrfSrh.getFromAge())))
				.append(OISearchSqls.AND);
		}
		else if(OIFormUtilities.chkIsNull(objBVUsrPrfSrh.getToAge()).length()!=0)
		{
		    sbQryWhere.append(" AGE ")
				.append(OISearchSqls.LESSTHANEQUAL)
				.append(Integer.parseInt(OIFormUtilities.chkIsNull(objBVUsrPrfSrh.getToAge())))
				.append(OISearchSqls.AND);
			bAge = true;
		}	
		if(bAge)
		{
		    sbSubQuery.append(",").append("ROUND((SYSDATE - BIRTH_DT)/(30 * 12)) AGE ");
		}
			
		/** DESIGNATION DropDown**/
		chkEqual("DESIGNATION",objBVUsrPrfSrh.getDesign()," OI_AD_CODE_MASTER CDDESIGNATION " ," (CDDESIGNATION.TYPE='DESIGNATION_CODE' OR CDDESIGNATION.TYPE IS NULL) AND PROFILE.DESIGNATIONCODE = CDDESIGNATION.VALUE(+)" , " CDDESIGNATION.DESCRIPTION DESIGNATION ", " DESIGNATIONCODE " );
     			
		/** INTEREST **/
		chkBlank("INTEREST",objBVUsrPrfSrh.getArea());
     					
		/** DIVISION DropDown**/
		chkEqual("DIVISION",objBVUsrPrfSrh.getDivision()," OI_AD_CODE_MASTER CDDIVISION " ," (CDDIVISION.TYPE = 'DIVISION_CODE' OR CDDIVISION.TYPE IS NULL) AND  PROFILE.DIVISIONCODE = CDDIVISION.VALUE(+)  ", " CDDIVISION.DESCRIPTION DIVISION " , " DIVISIONCODE ");
     		
		/** CCA **/
 	    if(objBVUsrPrfSrh.getAlListCol().contains("CCA_1"))
 	    {
 	        /**Select Clause **/
	   		sbQuery.append(" CCA_1 ").append(OISearchSqls.COMMA);
	   		bCCA = true;
 	    }
 	    if( OIFormUtilities.chkIsNull(objBVUsrPrfSrh.getCca()).length()!=0)
 	    {
 	        sbQryWhere.append(OISearchSqls.QRYCCA1)
 	        	.append(OISearchSqls.PERCENTAGE)
	   			.append(OIUtilities.addSingleQuoteDB(objBVUsrPrfSrh.getCca().trim()))
	   			.append(OISearchSqls.PERCENTAGE)
	   			.append(OISearchSqls.QRYCCA2)
	   			.append(OISearchSqls.PERCENTAGE)
	   			.append(OIUtilities.addSingleQuoteDB(objBVUsrPrfSrh.getCca().trim()))
	   			.append(OISearchSqls.PERCENTAGE)
	   			.append(OISearchConstants.SINGLEQUOTE)
	   			.append(" ))")
	   			.append(OISearchSqls.AND);
	   		bCCA = true;
	   	}
 	    if(bCCA)
 	    {
		    sbSubQuery.append(",").append("CCA_1, CCA_2");
		    sbSubQryWhere.append(", OI_AD_SUBLVLTEACH TEACH ");
		    sbSubQryJoin.append(" PROFILE.USERID = TEACH.USERID(+) AND ");
		}
 	    /** BRANCH_ZONE DropDown**/
 	    chkEqual("BRANCH_ZONE",objBVUsrPrfSrh.getZone(), " OI_AD_CODE_MASTER CDMASTER ", " PROFILE.ZONEBRANCHCODE=CDMASTER.VALUE(+) AND (CDMASTER.TYPE='BRANCHZONE_CODE' OR CDMASTER.TYPE IS NULL) " , " CDMASTER.DESCRIPTION BRANCH_ZONE " , " ZONEBRANCHCODE ");
     	
		/** SCHOOL_CLUSTER DropDown**/
		chkEqual("SCHOOL_CLUSTER",objBVUsrPrfSrh.getCluster(), " OI_AD_CODE_MASTER CDCLUSTER " ," PROFILE.CLUSTERCODE=CDCLUSTER.VALUE(+) AND (CDCLUSTER.TYPE='SCHOOL_CLUSTER' OR CDCLUSTER.TYPE IS NULL) ", " CDCLUSTER.DESCRIPTION SCHOOL_CLUSTER " ," CLUSTERCODE ");
				
		/** Hobbies **/
		chkBlank("HOBBIES",objBVUsrPrfSrh.getHobbies());
				
		/** Year of Join  **/
		if(objBVUsrPrfSrh.getAlListCol().contains("JOINDT"))
		{
		    /**Select Clause **/
			sbQuery.append(" JOINDT ").append(OISearchSqls.COMMA);
			bJoinDt = true;
		}
		if(OIFormUtilities.chkIsNull(objBVUsrPrfSrh.getFromYrJoin()).length()!=0 && OIFormUtilities.chkIsNull(objBVUsrPrfSrh.getToYrJoin()).length()!=0)
		{
		    /**Where Clause **/
		    bJoinDt = true;
			sbQryWhere.append(" JOINDT ")
				.append(OISearchSqls.EQUALGREATER)
				.append(Integer.parseInt(OIFormUtilities.chkIsNull(objBVUsrPrfSrh.getFromYrJoin())))
				.append(OISearchSqls.AND)
				.append(" JOINDT ")
				.append(OISearchSqls.LESSTHANEQUAL)
				.append(Integer.parseInt(OIFormUtilities.chkIsNull(objBVUsrPrfSrh.getToYrJoin())))
				.append(OISearchSqls.AND);
		}
		else if (OIFormUtilities.chkIsNull(objBVUsrPrfSrh.getFromYrJoin()).length()!=0)
		{
		    /**Where Clause **/
			bJoinDt = true;
			sbQryWhere.append(" JOINDT ")
				.append(OISearchSqls.EQUALGREATER)
				.append(Integer.parseInt(OIFormUtilities.chkIsNull(objBVUsrPrfSrh.getFromYrJoin())))
				.append(OISearchSqls.AND);
		}
		else if(OIFormUtilities.chkIsNull(objBVUsrPrfSrh.getToYrJoin()).length()!=0)
		{
		    bJoinDt = true;
			sbQryWhere.append(" JOINDT ")
				.append(OISearchSqls.LESSTHANEQUAL)
				.append(Integer.parseInt(OIFormUtilities.chkIsNull(objBVUsrPrfSrh.getToYrJoin())))
				.append(OISearchSqls.AND);
		}
		
		if(bJoinDt)
		{
		    sbSubQuery.append(",").append(" TO_CHAR(PROFILE.JOINING_DT,'YYYY') JOINDT ");
		}
			
		/** Hobbies **/
		chkBlank("DIV_STATUS",objBVUsrPrfSrh.getDivStatus());
		
		if(objBVUsrPrfSrh.getSubTech().length()!=0)
		{
		    sbQryWhere.append(constructSubLevel(OIFormUtilities.chkIsNull(objBVUsrPrfSrh.getSubTech())))
     			.append( " )")
				.append(OISearchSqls.AND);
     	}
     	if(objBVUsrPrfSrh.getLevelTech().length()!=0)
     	{
     	    sbQryWhere.append(constructTechLevel(OIFormUtilities.chkIsNull(objBVUsrPrfSrh.getLevelTech())))
     			.append( " )")
     			.append(OISearchSqls.AND);
     	}
		
     	int nIndex = sbQuery.lastIndexOf(OISearchSqls.COMMA);
     	if(nIndex > 0 )
     	{
     	    strTemp = sbQuery.substring(0,sbQuery.toString().trim().length()-1);
     	 	sbQuery.setLength(0);
     	 	sbQuery.append(strTemp).append(" FROM  ");
 				   
     	 	/** Appending the Sub Query **/
     	 	sbQuery.append(sbSubQuery);
     	 	if(sbSubQryWhere.length() > 0)
     	 	{
     	 	  sbQuery.append(sbSubQryWhere.toString());
     	 	  
     	 	  //sbQuery.append(" Where " + sbSubOb);
     	 	  
       	 	}
     	 	
     	 	if(sbSubQryJoin.length() > 0)
     	 	{
     	 	    sbQuery.append (" WHERE ");
     	 	    sbQuery.append(removeAnd(sbSubQryJoin.toString()));
     	 	    //sbQuery.append(" And " + sbSubOb);
     	 	    bDivadmin = true;
       	 	}
     	 	
     	 	if(sbSubQryWhere.length() > 0 && sbSubQryJoin.length() > 0 ){
     	 		sbQuery.append(" And " + sbSubOb);
     	 	//	System.out.println("OIDAOAdminUserProfile-constructQuery-and" + sbSubOb );
     	 	}else if(!(sbSubQryJoin.length() > 0) && (sbSubQryWhere.length() > 0) ){
     	 		sbQuery.append(" Where " + sbSubOb);
     	 		//System.out.println("OIDAOAdminUserProfile-constructQuery-where" + sbSubOb );
     	 	}else if(!(sbSubQryJoin.length() > 0) && !(sbSubQryWhere.length() > 0) ){
     	 		//System.out.println("OIDAOAdminUserProfile-constructQuery-null" + sbSubOb); 
     	 	}
			/** Checking for the Role in Query by adding the role **/
     	 	/** if role is Divadmin/Moderator  -  AND ROLEID <> 'ADMIN' AND ROLEID <> 'DIVADMIN' is appended in query **/ 
     	 	if(objBVUsrPrfSrh.getLoginRole().equalsIgnoreCase(OIDBRegistry.getValues(OIAdminConstants.divadmin))  || objBVUsrPrfSrh.getLoginRole().equalsIgnoreCase(OIDBRegistry.getValues(OIAdminConstants.moderator)))
     	 	{
     	 		/*Modify by Aik Mun @ 24 Feb 2009*/
//     	 	    if(!bDivadmin)
//     	 	    {
//     	 	        sbQuery.append (" WHERE ");
//     	 	    }
//     	 	    else
//     	 	    {
     	 	        sbQuery.append (" AND ");
//     	 	    }
     	 		sbQuery.append(OIAdminSqls.ROLESQL)
     	 			.append("UPPER(")	
					.append(OISearchConstants.SINGLEQUOTE+OIDBRegistry.getValues(OIAdminConstants.admin)+OISearchConstants.SINGLEQUOTE)
					.append(") AND ");
     	 		sbQuery.append(OIAdminSqls.ROLESQL)
					.append("UPPER(")	
					.append(OISearchConstants.SINGLEQUOTE +OIDBRegistry.getValues(OIAdminConstants.divadmin)+OISearchConstants.SINGLEQUOTE)
					.append(")");
     	 	}
     	 	sbQuery.append("&").append( " )");
     	}
     	
     	nIndex = sbQryWhere.lastIndexOf(OISearchSqls.AND);
     	if(nIndex > 0)
     	{
     	    strTemp = sbQryWhere.substring(0,nIndex);
     	 	sbQuery.append(OISearchSqls.FROM_WHERE_SUBQRY + strTemp); 
     	}
     	     	 
     	logger.info(sbQuery.toString());
     	objBVUsrPrfSrh.setQuery(sbQuery.toString());
     	//System.out.println("OIDAOAdminUserProfile-constructQuery final"+sbQuery.toString());
     	return  sbQuery.toString();
     }

     /**
      * This method is used to get RowCount for Pagination.
      * @param objCon
      * @param objBVUsrPrfSrh
      * @return
      * @throws Exception
      */
     public boolean srchRowCntQry(Connection objCon, OIBVAdminUserProfile objBVUsrPrfSrh) throws Exception
     {
         logger.debug(OILabelConstants.BEGIN_METHOD_DAO +"srchRowCntQry"); 
         ResultSet objRs =null;
         PreparedStatement objPs=null;
         boolean bFlag = false;
         String strQuery = null;        
         
         try
         { 
        	 //System.out.println("OIActionAdminUserProfile-doIt DAO1 query="+objBVUsrPrfSrh.getQuery());
             String strColName[] = objBVUsrPrfSrh.getColName();
	         for(int i = 0; i <  strColName.length ;i++)
	         {
	             objBVUsrPrfSrh.getAlListCol().add(strColName[i]);
	         }	 

	         if(objBVUsrPrfSrh.getQuery()==null)
	         {
	             constructQuery(objBVUsrPrfSrh);
	             strQuery = OISearchSqls.SLTCOUNT+sbQuery.toString()+" )" ;
	         } 
	         else
	         {
	             strQuery = OISearchSqls.SLTCOUNT+ objBVUsrPrfSrh.getQuery()+" )" ;
	         }
	         String strTemp = addSorting(objBVUsrPrfSrh, strQuery);   
	         
	         objPs = objCon.prepareStatement(strTemp);
	         int nLen = strColName.length;
	         objRs = objPs.executeQuery();
	         if(objRs!= null && objRs.next())
	         {
	             bFlag = true;   		       		
	             objBVUsrPrfSrh.setRowCount(objRs.getInt(1));
	         } 
         }
         catch(Exception sqle)
         {
             logger.error(sqle.getMessage());
             bFlag = false;
             throw sqle;
         }
         finally
         {
             if (objPs!= null)
             {
                 objPs.close();
             }
             if (objRs!= null)
             {
                 objRs.close();
             }
         }
         
         logger.debug(OILabelConstants.END_METHOD_DAO +"srchRowCntQry");
         return bFlag;
     }
     
     /**
      * This method fetches the user profile data based on the userid
      * @param objCon
      * @param objBV
      * @return
      * @throws Exception
      */
     public ArrayList viewProfile(Connection objCon, OIBVAdminUserProfile objBV) throws Exception
     {
         logger.debug(OILabelConstants.BEGIN_METHOD_DAO +"viewProfile"); 
         ResultSet objRs =null;
         PreparedStatement objPs=null;
         boolean bFlag = false;
         ArrayList alUser  = null; 
                  
         try
         {     
             objPs = objCon.prepareStatement(OIAdminSqls.QRY_PROFILE);
	         for(int i = 1;i<8;i++)
	         {
	             objPs.setString(i,objBV.getId());
	         }
	         objRs = objPs.executeQuery();
	         if(objRs!= null && objRs.next())
	         {
	             alUser = new ArrayList();
		         objBV.setNRIC(OIFormUtilities.chkIsNull(objRs.getString(1)));
		         objBV.setRole(OIFormUtilities.chkIsNull(objRs.getString(2)));
		         objBV.setSalutation(OIFormUtilities.chkIsNull(objRs.getString(3)));
		         objBV.setNickName(OIFormUtilities.chkIsNull(objRs.getString(4)));
		         objBV.setName(OIFormUtilities.chkIsNull(objRs.getString(5)));
		         objBV.setMailId(OIFormUtilities.chkIsNull(objRs.getString(6)));
		         objBV.setAge(OIFormUtilities.chkIsNull(objRs.getString(7)));
		         objBV.setYrJoin(OIFormUtilities.chkIsNull(objRs.getString(8)));
		         objBV.setDesign(OIFormUtilities.chkIsNull(objRs.getString(9)));
		         objBV.setGrade(OIFormUtilities.chkIsNull(objRs.getString(10)));
		         objBV.setDivStatus(OIFormUtilities.chkIsNull(objRs.getString(11)));
		         objBV.setDivision(OIFormUtilities.chkIsNull(objRs.getString(12)));
		         objBV.setZone(OIFormUtilities.chkIsNull(objRs.getString(13)));
		         objBV.setSchoolType(OIFormUtilities.chkIsNull(objRs.getString(14)));
		         objBV.setSchool(OIFormUtilities.chkIsNull(objRs.getString(15)));
		         objBV.setCluster(OIFormUtilities.chkIsNull(objRs.getString(16)));
		         objBV.setHobbies(OIFormUtilities.chkIsNull(objRs.getString(17)));
		         objBV.setArea(OIFormUtilities.chkIsNull(objRs.getString(18)));
		         objBV.setSign(OIFormUtilities.chkIsNull(objRs.getString(19)));
		         objBV.setShowSign(OIFormUtilities.chkIsNull(objRs.getString(20)));
		         objBV.setTotPostings(OIFormUtilities.chkIsNull(objRs.getString(21)));
				 objBV.setObsolete(OIFormUtilities.chkIsNull(objRs.getString(22)));
		         alUser.add(objBV);         		
	         } 
         }catch(Exception sqle)
         {
             logger.error(sqle.getMessage());
             bFlag = false;
             throw sqle;
         }
         finally
         {
             if (objPs!= null)
             {
                 objPs.close();
             }
             if (objRs!= null)
             {
                 objRs.close();
             }
         }
         logger.debug(OILabelConstants.END_METHOD_DAO +"viewProfile");
         return alUser;
     }     
     
     /**
      * This method fetches CCA, Subject Teaching , Level Teaching. 
      * @param objCon
      * @param objBV
      * @return
      * @throws Exception
      */
     public HashMap viewTech(Connection objCon, OIBVAdminUserProfile objBV) throws Exception
     {
         logger.debug(OILabelConstants.BEGIN_METHOD_DAO +"viewTech"); 
                                  
         ResultSet objRs =null;
         PreparedStatement objPs=null;
         boolean bFlag = false;
         ArrayList alUser  = null;
         HashMap hmTemp = null;
         int nIndex = 1;
         int nTemp = 0;
         try
         {   
             objPs = objCon.prepareStatement(OIAdminSqls.QRY_TEACH);
	         objPs.setString(1,objBV.getId());
	         objRs = objPs.executeQuery();
	         if(objRs!= null && objRs.next())
	         {
	             hmTemp = new HashMap();
	             setCCALevel(hmTemp, objRs, 1, 10 ,OIAdminConstants.SUB_LEVEL);
	             setCCALevel(hmTemp, objRs, 11, 20 ,OIAdminConstants.TEACH_LEVEL);
	             setCCALevel(hmTemp, objRs, 21, 22 ,OIAdminConstants.CCA);
	         } 
         }
         catch(Exception sqle)
         {
             logger.error(sqle.getMessage());
             bFlag = false;
             throw sqle;
         }
         finally
         {
             if (objPs!= null)
             {
                 objPs.close();
             }
             if (objRs!= null)
             {
                 objRs.close();
             }
         }
         
         logger.debug(OILabelConstants.END_METHOD_DAO +"viewTech");
         return hmTemp;
     }     
     
     /**
      * This  method update user profile details of Admin
      * @param objCon
      * @param objBV
      * @return
      * @throws Exception
      */
     public boolean updateProfile(Connection objCon, OIBVAdminUserProfile objBV) throws Exception
     {
     	logger.info(OILabelConstants.BEGIN_METHOD_DAO +"updateProfile"); 
        PreparedStatement objPs=null;
        boolean bFlag = false;
        int nUpate =0;
        try
        { 
            objPs = objCon.prepareStatement(OIAdminSqls.QRY_PROFILE_UPDATE);
	        objPs.setString(1,objBV.getRole());
			objPs.setString(2,objBV.getMailId());
			objPs.setString(3,objBV.getSign());
			objPs.setString(4,objBV.getArea());
			objPs.setString(5,objBV.getHobbies());
			objPs.setString(6,objBV.getId());

	        nUpate = objPs.executeUpdate();
	        if(nUpate > 0)
	        {
	            bFlag = true;
	        }
		}
        catch(Exception sqle)
        {
            logger.error(sqle.getMessage());
			bFlag = false;
			throw sqle;
		}
        finally
        {
            if (objPs!= null)
            {
				objPs.close();
			}
	    }

        logger.info(OILabelConstants.END_METHOD_DAO +"updateProfile");
        return bFlag;
     }
     
     /**
     * This  method is to check the user NickName 
     * @param objCon
     * @param objBV
     * @return
     * @throws Exception
     */
    public boolean selectNickName(Connection objCon, OIBVAdminUserProfile objBV) throws Exception
    {
       logger.debug(OILabelConstants.BEGIN_METHOD_DAO +"selectNickName"); 
       PreparedStatement objPs=null;
       ResultSet objRs =null;
       boolean bFlag = false;
       int nUpate =0;
       try
       { 
           objPs = objCon.prepareStatement(OIAdminSqls.QRY_NICKNAME);
		   objPs.setString(1,objBV.getId().trim());
		   objPs.setString(2,objBV.getNickName().trim());
		   objRs = objPs.executeQuery();
		   if(objRs!= null && objRs.next())
		   {
		       bFlag=true;
		   }          	       	        
       }
       catch(Exception sqle)
       {
           logger.error(sqle.getMessage());
           bFlag = false;
           throw sqle;
       }
       finally
       {
           if (objPs!= null)
           {
               objPs.close();
           }
           if (objRs!= null)
           {
           		objRs.close();
           }
       }
       
       logger.debug(OILabelConstants.END_METHOD_DAO +"selectNickName");
       return bFlag;
    }
  
    /**
      * This is the helper to construct the search query.
      * @param strValue
      * @return
      */
    private String addCriteria(String strValue )
    {
        StringBuffer sbTemp = new StringBuffer(0);
     	sbTemp.append(OISearchSqls.LIKE)
     		.append(OISearchConstants.SINGLEQUOTE)
			.append(OISearchSqls.PERCENTAGE)
		    .append(OIUtilities.addSingleQuoteDB(strValue.trim()))
			.append(OISearchSqls.PERCENTAGE)
			.append(OISearchConstants.SINGLEQUOTE)
			.append(")")
			.append(OISearchSqls.AND);  
     	return sbTemp.toString();	
    }
    
    /**
      * This is the helper method to check Subject Level, Level Teaching and CCA
      * @param hm
      * @param objRs
      * @param sIndex
      * @param eIndex
      * @param strKey
      * @throws Exception
      */
    private void  setCCALevel(HashMap hm, ResultSet objRs, int sIndex, int eIndex,String strKey)throws Exception
    {
     	ArrayList alUsr = new ArrayList();
     	int nTemp = 0;
     	
     	for(int i = sIndex ;i<=eIndex;i++)
     	{
     	    if(OIFormUtilities.chkIsNull(objRs.getString(i)).length()>0 )
     	    {
     	        nTemp =1;
     	    }
			alUsr.add(OIFormUtilities.chkIsNull(objRs.getString(i)));
     	}
		if(nTemp>0)
		{
		    hm.put(strKey,alUsr);
		}
     }
     
     /**
      * 
      * @param strKey
      * @param strValue
      */
    private void chkBlank(String strKey,String strValue)
    {
    	String strTemp = OIFormUtilities.chkIsNull(strValue);
    	boolean bFlag = false;
    	if(alColNames.contains(strKey))
    	{
    	    /**Select Clause **/
	   		sbQuery.append(" " + OIUtilities.addSingleQuoteDB(strKey) + " " ).append(OISearchSqls.COMMA);
	   		bFlag = true;
    	}   		

    	if(strTemp.length()!=0)
    	{
    	    /**Where Clause **/
	  		sbQryWhere.append("UPPER( "+ OIUtilities.addSingleQuoteDB(strKey) +")").append(addCriteria(strTemp));
	  		bFlag = true;
    	}
    	if(bFlag && !strKey.equals("USERID"))
    	{
    	    sbSubQuery.append(",").append(strKey);
    	}  
    }
    
    /**
     * This method checks for the equal condition
     * @param strKey
     * @param strValue
     */
    private void chkEqual(String strKey,String strValue ,String strTableNames, String strSubWhere, String strDescColsbQuery, String strCode )
    {    
    	String strTemp = OIFormUtilities.chkIsNull(strValue);
    	boolean bFlag = false;
    	if(alColNames.contains(strKey))
    	{
    	    /**Select Clause **/
    		sbQuery.append(" " + OIUtilities.addSingleQuoteDB(strKey) + " " )
				.append(OISearchSqls.COMMA);
    		bFlag = true;
    	}
    	if(strTemp.length()!=0)
    	{
    	    /**Where Clause **/
			sbQryWhere.append(strCode)
				.append(OISearchSqls.EQUAL)
				.append(OISearchConstants.SINGLEQUOTE)
				.append(OIUtilities.addSingleQuoteDB(strValue))
				.append(OISearchConstants.SINGLEQUOTE)
				.append(OISearchSqls.AND);
			bFlag = true;
    	}
    	if(bFlag)
    	{
	    	/** The 3 String Buffer is used frame sub query **/
    	    sbSubQuery.append(",").append("PROFILE.").append(strCode);
    	    if(!strKey.equals("ROLEID"))
    	    {
    	        sbSubQryWhere.append(",").append(strTableNames);
				sbSubQryJoin.append(strSubWhere).append(" AND ");
				sbSubQuery.append(",").append(strDescColsbQuery);
    	    }	
    	}	
    }	
	  
    /**
     * This is the helper method to construct TechLevel Query 
     * @param strValue 
     * @return
     */
    private String constructTechLevel(String strValue)
    {
        StringBuffer sbTemp = new StringBuffer(OIAdminSqls.QRY_TEACHLEVEL)
        	.append(OIAdminSqls.QRY_LIKE)
			.append(OIUtilities.addSingleQuoteDB(strValue))
			.append(OIAdminSqls.QRY_SINGLEQUOTE);
        for(int i = 2;i<=10 ;i++)
        {
            sbTemp.append(OIAdminSqls.QRY_TEACH_UPPER).append(i)
				.append(OIAdminSqls.QRY_LIKE)
				.append(OIUtilities.addSingleQuoteDB(strValue))
				.append(OIAdminSqls.QRY_SINGLEQUOTE);		
    	}

    	return sbTemp.toString();
    }
    
    /**
     * This is the helper method to construct Subject Level Query 
     * @param strValue
     * @return
     */
    private String constructSubLevel(String strValue)
    {
    	StringBuffer sbTemp = new StringBuffer(OIAdminSqls.QRY_SUB)
    		.append(OIAdminSqls.QRY_LIKE)
			.append(OIUtilities.addSingleQuoteDB(strValue))
			.append(OIAdminSqls.QRY_SINGLEQUOTE);
    	for(int i = 2;i<=10 ;i++)
    	{
    	    sbTemp.append(OIAdminSqls.QRY_SUB_UPPER).append(i)
				.append(OIAdminSqls.QRY_LIKE)
				.append(OIUtilities.addSingleQuoteDB(strValue))
				.append(OIAdminSqls.QRY_SINGLEQUOTE);		
    	}

    	return sbTemp.toString();
    }

    /***
     * This method returns admin roles 
     * @param objCon
     * @param objBV
     * @return
     * @throws Exception
     */
    public boolean getAdminUser(Connection objCon, OIBVAdminUserProfile objBV)throws Exception
    {
        logger.debug(OILabelConstants.BEGIN_METHOD_DAO +"getAdminUser"); 
        ResultSet objRs =null;
        PreparedStatement objPs=null;
        boolean bFlag = false;
        try
        {
            objPs = objCon.prepareStatement(OISearchSqls.QRY_ADMINNAME);

            objRs = objPs.executeQuery();
	        if(objRs!= null && objRs.next())
	        {
	            bFlag= true;
	            objBV.setAdminId(OIFormUtilities.chkIsNull(objRs.getString(1)));			          				
	        }       	 
        }
        catch(Exception sqle)
        {
            logger.error(sqle.getMessage());
    		bFlag = false;
    		throw sqle;
        }
        finally
        {
            if (objPs!= null)
            {
                objPs.close();
            }
    		if (objRs!= null)
    		{
    		    objRs.close();
    		}
        }

        logger.debug(OILabelConstants.END_METHOD_DAO +"getAdminUser");
    	return bFlag;
    }
    
    /**
	 * This method populates List of School Names,Cluster,School Types
	 * @param connection
	 * @return ArrayList
	 * @throws Exception
	 */
    public ArrayList getBookMarks(Connection objCon, OIBVAdminUserProfile objBV,String strFlag) throws Exception
    {
        logger.debug(OILabelConstants.BEGIN_METHOD_DAO +"getBookMarks"); 
    	ArrayList alList=null;
        ResultSet objRs =null;
        PreparedStatement objPs=null;
        boolean bFlag = false;
        try
        { 
            objPs = objCon.prepareStatement(OIAdminSqls.QRY_BOOKMARKS);
	        objPs.setString(1,objBV.getId());
	        objPs.setString(2,strFlag);
	        objRs = objPs.executeQuery();
	        String strVal = null;
	        if(objRs!= null)
	        {
	            bFlag = true;
	           	alList = new ArrayList(); 
	           	while(objRs.next())
	           	{
	           	    alList.add(new org.apache.struts.util.LabelValueBean(OIFormUtilities.chkIsNull(objRs.getString(3)),OIFormUtilities.chkIsNull(objRs.getString(2))));
	           	}
	        }                    
        }
        catch(Exception sqle)
        {
            logger.error(sqle.getMessage());
			bFlag = false;
			throw sqle;
		}
        finally
        {
			if (objPs!= null)
			{
				objPs.close();
			}
			if (objRs!= null)
			{
				objRs.close();
			}
		}
        if (alList.size()==0)
        {
        	alList=null;
        }		
        logger.debug(OILabelConstants.END_METHOD_DAO +"getBookMarks");
        return alList;
    }
    
    
    /**
     * This method update the user profile details of website
     * @param objCon
     * @param objBV
     * @return
     * @throws Exception
     */
    public boolean updateUser(Connection objCon, OIBVAdminUserProfile objBV) throws Exception
    {
        logger.debug(OILabelConstants.BEGIN_METHOD_DAO +"updateUser"); 
        PreparedStatement objPs=null;
        boolean bFlag = false;
        int nUpate =0;
        try
        { 
            objPs = objCon.prepareStatement(OIAdminSqls.QRY_WEB_PROFILE_UPDATE);
	        objPs.setString(1,objBV.getNickName());
			objPs.setString(2,objBV.getMailId());
			objPs.setString(3,objBV.getArea());
			objPs.setString(4,objBV.getHobbies());
			objPs.setString(5,objBV.getSign());
			objPs.setString(6,objBV.getShowSign());
			objPs.setString(7,objBV.getId());
	       	nUpate = objPs.executeUpdate();
	        if(nUpate > 0)
	        {
	            bFlag = true;
	        }
        }
        catch(Exception sqle)
        {
            logger.error(sqle.getMessage());
			bFlag = false;
			throw sqle;
		}
        finally
        {
            if (objPs!= null)
            {
				objPs.close();
			}
	    }

        logger.debug(OILabelConstants.END_METHOD_DAO +"updateUser");
        return bFlag;
     }

	 /**
     * This method update the user profile details of website
     * @param objCon
     * @param objBV
     * @return
     * @throws Exception
     */
    public boolean enableDisableUser(Connection objCon, OIBVAdminUserProfile objBV) throws Exception
    {
        logger.debug(OILabelConstants.BEGIN_METHOD_DAO +"enableDisableUser"); 
        PreparedStatement objPs=null;
        boolean bFlag = false;
        int nUpate =0;
        try
        { 
            objPs = objCon.prepareStatement(OIAdminSqls.QRY_PROFILE_DISABLE_ENABLE);
	        objPs.setString(1,objBV.getObsolete());
			objPs.setString(2,objBV.getId());
	       	nUpate = objPs.executeUpdate();
	        if(nUpate > 0)
	        {
	            bFlag = true;
	        }
        }
        catch(Exception sqle)
        {
            logger.error(sqle.getMessage());
			bFlag = false;
			throw sqle;
		}
        finally
        {
            if (objPs!= null)
            {
				objPs.close();
			}
	    }

        logger.debug(OILabelConstants.END_METHOD_DAO +"enableDisableUser");
        return bFlag;
     }
    /**
     * This method deletes the select bookmarkid
     * @param objCon
     * @param objBV
     * @return
     * @throws Exception
     */
    public boolean delBookMarks(Connection objCon,OIBVAdminUserProfile objBV)throws Exception
    {
        ArrayList arEmail=null;
        ResultSet objRs =null;
        PreparedStatement objPs=null;
        String[] strRemoveId = null;
    	boolean bFlag = false;
        int[] nDelete = null;
        String strAction = objBV.getHiddenAction();
        try
        {
            objPs = objCon.prepareStatement(OIAdminSqls.QRY_DEL_BOOKMARKS);
        	if(strAction.equals(OIAdminConstants.DELBOOKMARK))
        	{
        	    strRemoveId = objBV.getBookIds();
        	}
        	else if(strAction.equals(OIAdminConstants.DELSTICKY))
        	{
        	    strRemoveId = objBV.getStickyIds();
        	}
        	
        	int batchCount = 0;
        	int executeCount = 0;
        	
        	for(int i =0;i < strRemoveId.length; i++ )
        	{
        	    objPs.setInt(1,Integer.parseInt(strRemoveId[i]));
            	objPs.addBatch();
            	batchCount++;
            	
            	if (batchCount == 5000)
            	{
            		nDelete = objPs.executeBatch();
            		executeCount += nDelete.length;
            		batchCount = 0;
            		//objPs = objCon.prepareStatement(OIAdminSqls.QRY_DEL_BOOKMARKS);
            	}
        	}

        	if (batchCount > 0)
        	{
	        	nDelete = objPs.executeBatch();
	        	executeCount += nDelete.length;
        	}
            if(executeCount >0)
            {
                bFlag = true;
            }
        }
        catch(Exception sqle)
        {
            logger.error(sqle.getMessage());
    		bFlag = false;
    		throw sqle;
        }
        finally
        {
            if (objPs!= null)
            {
                objPs.close();
            }
    		if (objRs!= null)
    		{
    		    objRs.close();
    		}
        }
    	return bFlag;
    }

    
    /**
     * This method displays the user profile information.
     * @param objCon
     * @param objBV 
     * @return
     * @throws Exception
     */
    public ArrayList viewUserProfile(Connection objCon, OIBVAdminUserProfile objBV) throws Exception
    {
        logger.debug(OILabelConstants.BEGIN_METHOD_DAO +"viewUserProfile"); 

        ResultSet objRs =null;
        PreparedStatement objPs=null;
        boolean bFlag = false;
        ArrayList alUser  = null; 

        try
        {
            objPs = objCon.prepareStatement(OIAdminSqls.QRY_USERPROFILE);
            objPs.setString(1,objBV.getId());
            objPs.setString(2,objBV.getId());
            objRs = objPs.executeQuery();
            if(objRs!= null && objRs.next())
            {
                alUser = new ArrayList();
	            objBV.setNRIC(OIFormUtilities.chkIsNull(objRs.getString(1)));
	            objBV.setName(OIFormUtilities.chkIsNull(objRs.getString(2)));
	            objBV.setNickName(OIFormUtilities.chkIsNull(objRs.getString(3)));
	            objBV.setMailId(OIFormUtilities.chkIsNull(objRs.getString(4)));
	            objBV.setArea(OIFormUtilities.chkIsNull(objRs.getString(5)));
	            objBV.setHobbies(OIFormUtilities.chkIsNull(objRs.getString(6)));
				objBV.setSign(OIFormUtilities.chkIsNull(objRs.getString(7)));
				objBV.setShowSign(OIFormUtilities.chkIsNull(objRs.getString(8)));
				objBV.setTotPostings(OIFormUtilities.chkIsNull(objRs.getString(9)));
	            alUser.add(objBV);         		
            } 
        }
        catch(Exception sqle)
        {
            logger.error(sqle.getMessage());
     		bFlag = false;
     		throw sqle;
        }
        finally
        {
            if (objPs!= null)
            {
                objPs.close();
     		}
     		if (objRs!= null)
     		{
     		    objRs.close();
     		}
        }
        logger.debug(OILabelConstants.END_METHOD_DAO +"viewUserProfile");
        return alUser;
    }     

    /**
     * This is helper method to replace sorting pararmeters
     * @param objBV
     * @param strQuery
     * @return
     * @throws Exception
     */
    private String addSorting(OIBVAdminUserProfile objBV,String strQuery) throws Exception 
    {
        Pattern pat=Pattern.compile("&");
     	Matcher matcher=pat.matcher(strQuery);
     	String strTemp  = matcher.replaceFirst( " ORDER BY " + objBV.getSortKey() + OILabelConstants.SPACE + objBV.getOrder());
     	return strTemp;
    }
    
    /**
     * This helper method removes the text AND from the Query.
     * @param strVal
     * @return
     */
    private String removeAnd(String strVal)
    {
        String strTemp = strVal;
        int nAndIndex = strTemp.lastIndexOf(OISearchSqls.AND);
        if(nAndIndex >0)
        {
          strTemp = strTemp.substring(0,strTemp.trim().length()- 3);
   	 	}
        return strTemp;
    }
    
    public ArrayList getMailDomains(Connection conn) {
    	ArrayList arList = new ArrayList();
    	PreparedStatement pst = null;
    	ResultSet rs = null;
    	try {
    		pst = conn.prepareStatement(OIAdminSqls.QRY_MAIL_DOMAIN);
    		rs = pst.executeQuery();
    		
    		while (rs.next()) {
    			if (rs.getString("DESCRIPTION") != null && rs.getString("DESCRIPTION").length() > 0)
    				arList.add(rs.getString("DESCRIPTION"));
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		try {
    			if (pst!= null)
    			{
    				pst.close();
    			}
    			if (rs!= null)
    			{
    				rs.close();
    			}
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
    	
    	
    	return arList;
    }
	 public ArrayList getRoles(Connection conn) {
    	ArrayList arList = new ArrayList();
    	PreparedStatement pst = null;
    	ResultSet rs = null;
    	try {
    		pst = conn.prepareStatement(OIAdminSqls.QRY_ROLES);
    		rs = pst.executeQuery();
    		
    		while (rs.next()) {
    			if (rs.getString("ROLEID") != null && rs.getString("ROLEID").length() > 0)
    				arList.add(rs.getString("ROLEID"));
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		try {
    			if (pst!= null)
    			{
    				pst.close();
    			}
    			if (rs!= null)
    			{
    				rs.close();
    			}
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
    	
    	
    	return arList;
    }
}
