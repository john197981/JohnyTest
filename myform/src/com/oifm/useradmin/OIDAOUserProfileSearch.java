/*************************************************************************************************
 * File Name		:	OIDAOUserProfileSearch.java
 * Author			:	SureshKumar.R
 * Description		:   This DAO is used to search the users, loads type of schools,clusters and school names
 * 						and adds the list of users
 * Created Date		:	Jul 13 2005
 * Version			:	1.0
 * Copyright 		: 	Scandent Group
 ************************************************************************************************/
package com.oifm.useradmin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseDao;
import com.oifm.common.OILabelConstants;
import com.oifm.useradmin.admin.OIAdminSqls;
import com.oifm.utility.OIDBRegistry;
import com.oifm.utility.OIFormUtilities;
import com.oifm.utility.OIUtilities;

public class OIDAOUserProfileSearch extends OIBaseDao
{
	transient private final Logger LOGGER = Logger.getLogger(this.getClass().getName());
	private StringBuffer sbQuery = new StringBuffer(0);
	private StringBuffer sbQryWhere = new StringBuffer(0);
	private StringBuffer sbSubQuery = new StringBuffer(OIAdminSqls.SRCHSUBQUERY);
	private StringBuffer sbSubQryWhere = new StringBuffer(" FROM OI_AD_PROFILE PROFILE ");
	private StringBuffer sbSubQryJoin = new StringBuffer(0);
	private StringBuffer sbSubOb = new StringBuffer(" (PROFILE.obsolete IS NULL OR PROFILE.obsolete NOT IN ('e','E') ) ");
	private ArrayList alColNames = null;

	public OIDAOUserProfileSearch()
	{
	    LOGGER.debug(OILabelConstants.BEGIN_METHOD_DAO + this.getClass().getName());
	} 
	
	/**
	 * This method populates List of School Names,Cluster,School Types
	 * @param connection
	 * @return ArrayList
	 * @throws Exception
	 */
    public ArrayList readList(Connection objCon,String strQuery) throws Exception
    {
    	LOGGER.debug(OILabelConstants.BEGIN_METHOD_DAO +"readList"); 
    	ArrayList alList=null;
        ResultSet objRs =null;
        PreparedStatement objPs=null;
        boolean bFlag = false;
        try
        { 
        	objPs = objCon.prepareStatement(strQuery);
           	objRs = objPs.executeQuery();
            String strVal = null;
            if(objRs!= null)
            {
           		bFlag = true;
           		alList = new ArrayList(); 
           		alList.add(new org.apache.struts.util.LabelValueBean(OIDBRegistry.getValues(OILabelConstants.PLS_SELECT),""));
           		while(objRs.next())
           		{
           		    alList.add(new org.apache.struts.util.LabelValueBean(OIFormUtilities.chkIsNull(objRs.getString(2)),OIFormUtilities.chkIsNull(objRs.getString(1))));
           		}
        	} 
        }
        catch(Exception sqle)
        {
			LOGGER.error("readList " + sqle.getMessage());
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
    	
    	LOGGER.debug(OILabelConstants.END_METHOD_DAO +"readList");
    	return alList;
    }
    
        
   /**
    * This method fetch the profile debugrmation with the given search criteria
    * @param objCon
    * @param objBVUsrPrfSrh
    * @param nStartNum
    * @param nEndNum
    * @return
    * @throws Exception
    */
    public ArrayList searchUserProfile(Connection objCon, OIBVUserProfileSearch objBVUsrPrfSrh,int nStartNum, int nEndNum) throws Exception
    {
        LOGGER.debug(OILabelConstants.BEGIN_METHOD_DAO +"searchUserProfile"); 
        
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
         	 
         //	System.out.println("OIDAOUserProfileSearch-searchUserProfile quey " + strQuery);
         	
         	String strTemp = addSorting(objBVUsrPrfSrh, strQuery);
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
           			hmSearch.put("PAPERID" ,"E");
           			for(int i = 0; i < nLen ;i++)
           			{
           			    hmSearch.put(strColName[i],OIFormUtilities.chkIsNull(objRs.getString(strColName[i].trim())));
           			}
           			arSrhUsrPrf.add(hmSearch);
           		}
        	} 
           
            chngCaption(objBVUsrPrfSrh);
        }
        catch(Exception sqle)
        {
			LOGGER.error("searchUserProfile " + sqle.getMessage());
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
        LOGGER.debug(OILabelConstants.END_METHOD_DAO +"searchUserProfile");
        return arSrhUsrPrf;
    }
 
    
    
    /**
     * This is the helper method to construct search criteria query.
     * @param objBVUsrPrfSrh
     * @return Strng
     */ 
    	
        
    private String constructQuery (OIBVUserProfileSearch objBVUsrPrfSrh)
    {
        LOGGER.debug(OILabelConstants.BEGIN_METHOD_DAO +"constructQuery");
    	 
    	/**NAME LIKE '%'	EMAILID =	AGE -- BIRTH_DT =	SCHOOL ='%'	TOTALPAPRES =	ZONEBRANCH LIKE '%'	SCHOOL_CLUSTER LIKE '%'		SCHOOL_TYPE		TOTALSURVEYS**/
    	//StringBuffer sbQryWhere = new StringBuffer(0);
    	StringBuffer sbSltsubQuery = new StringBuffer(0);
    	String strTemp = null;
    	String strName = null;
    	boolean bCCA = false;
     	boolean bAge = false;
     	boolean bJoinDt = false;
     	boolean bWhere = false;
    	String strColName[] = objBVUsrPrfSrh.getColName();
    	String strFlag = OIFormUtilities.chkIsNull(objBVUsrPrfSrh.getFlag());
   	 	//sbQuery.append(OISearchSqls.SELECT);
    	if(objBVUsrPrfSrh.getHidPage().equals(OISearchConstants.ADDEMAILS))
    	{
       		sbQuery.append("EMAILID  ").append(OISearchSqls.COMMA);
        }

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
				sbQuery.append(" AGE ")
					   .append(OISearchSqls.COMMA);
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
		 if(OIFormUtilities.chkIsNull(objBVUsrPrfSrh.getCca()).length()!=0)
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
    	     }
    	     
    	     if(sbSubQryJoin.length() > 0)
    	     {
    	         sbQuery.append (" WHERE ");
    	         sbQuery.append(removeAnd(sbSubQryJoin.toString()));
		     	 bWhere = true;
    	     }
    	     if(sbSubQryWhere.length() > 0 && sbSubQryJoin.length() > 0 ){
      	 		sbQuery.append(" And " + sbSubOb);
      	 		//System.out.println("OIDAOAdminUserProfile-constructQuery-and" + sbSubOb );
      	 	}else if(!(sbSubQryJoin.length() > 0) && (sbSubQryWhere.length() > 0) ){
      	 		sbQuery.append(" Where " + sbSubOb);
      	 		//System.out.println("OIDAOAdminUserProfile-constructQuery-where" + sbSubOb );
      	 	}else if(!(sbSubQryJoin.length() > 0) && !(sbSubQryWhere.length() > 0) ){
      	 		//System.out.println("OIDAOAdminUserProfile-constructQuery-null" + sbSubOb); 
      	 	}  				    
    	     /** This query added for Add boards users **/
    	     if(objBVUsrPrfSrh.getHidPage().equals(OISearchConstants.ADDBOARDS))
    	     {
    	         if(!bWhere)
    	         {
    	             sbQuery.append (" WHERE ");
    	         }
    	         else
    	         {
    	             sbQuery.append (" AND ");
    	         }
			    
    	         if(objBVUsrPrfSrh.getHidBoard().equals("A"))
    	         {  
    	             /** This Where condition added for the ADMIN user Group **/
    	             sbQuery.append(OISearchSqls.QRY_ADMIN);
    	         }
    	         else if(objBVUsrPrfSrh.getHidBoard().equals("D"))
    	         { 
    	             /** This Where Condition added for the DivADmin user Group **/	
    	             sbQuery.append(OISearchSqls.QRY_MODERATOR);
    	         }
    	     }
    	     sbQuery.append(" ORDER BY ").append(" & ").append(")");
    	 }
    	 
    	 nIndex = sbQryWhere.lastIndexOf(OISearchSqls.AND);
    	 if(nIndex > 0)
    	 {
    	     strTemp = sbQryWhere.substring(0,nIndex);
    	     sbQuery.append(OISearchSqls.FROM_WHERE_SUBQRY + strTemp); 
    	 }
    				
    	 objBVUsrPrfSrh.setQuery(sbQuery.toString());
    	 LOGGER.info(sbQuery.toString());
				
    	 return sbQuery.toString();
    }
    
    /**
     * This is method is used to insert to Memebers Table
     * @param objCon
     * @param objBVUsrPrfSrh
     * @return
     * @throws Exception
     */
    	
    public boolean addUsers(Connection objCon, OIBVUserProfileSearch objBVUsrPrfSrh,String strQuery) throws Exception
    {
    	ArrayList arEmail=null;
        ResultSet objRs =null;
        PreparedStatement objPs=null;
        String[] strUserId = null;
		boolean bFlag = false;
        int[] nUpdate = null;
    	
        try
        {
    	 	objPs = objCon.prepareStatement(strQuery);
    	 	strUserId = objBVUsrPrfSrh.getUserId();
    	 	
    	 	if(strUserId!=null)
    	 	{
    	 		int batchCount = 0;
    	 		int executeCount = 0;
    	 		
    	 	    for(int i =0;i < strUserId.length; i++ )
    	 	    {
    	 	        if(!OIFormUtilities.chkIsNull(strUserId[i]).equals("userId"))
    	 	        {
    	 	            objPs.setInt(1, Integer.parseInt(objBVUsrPrfSrh.getId()));
			        	objPs.setString(2,OIFormUtilities.chkIsNull(strUserId[i]));
			        	objPs.setString(3,null);
			        	objPs.setString(4,null);
			        	objPs.setString(5,null);
			        	objPs.addBatch();
			        	batchCount++;
    	 	        }
    	 	        
    	 	        if (batchCount == 5000)
    	 	        {
    	 	        	nUpdate = objPs.executeBatch();
    	 	        	executeCount += nUpdate.length;
    	 	        	//objCon.prepareStatement(strQuery);
    	 	        	batchCount = 0;
    	 	        }
    	 	    }
    	 	   if (batchCount > 0)
    	 	   {
		    	 	nUpdate = objPs.executeBatch();
		    	 	executeCount += nUpdate.length;
    	 	   }
	            if(executeCount >0)
	            {
	                objBVUsrPrfSrh.setUpCnt(String.valueOf(executeCount));
	            	bFlag = true;
	            }
    	 	}
        }
        catch(Exception sqle)
        {
			LOGGER.error("addUsers " + sqle.getMessage());
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
     * This is the helper method to change the caption 
     * @param objBVUsrPrfSrh
     */
    
    private void chngCaption(OIBVUserProfileSearch objBVUsrPrfSrh)
    {
    }
    
    /**
	 * This method is to get the list of groups with Description and Group. 
	 * @param connection
	 * @return ArrayList
	 * @throws Exception
	 */
    public ArrayList searchUserGroup(Connection objCon) throws Exception
    {
    	LOGGER.debug(OILabelConstants.BEGIN_METHOD_DAO +"searchUserGroup"); 
    	ArrayList alList=null;
        ResultSet objRs =null;
        PreparedStatement objPs=null;
        OIBVUserProfileSearch objGroups = null; 
        boolean bFlag = false;
        try
        { 
        	objPs = objCon.prepareStatement(OISearchSqls.QRY_SLTGRPS);
           	objRs = objPs.executeQuery();
            String strVal = null;
            if(objRs!= null)
            {
           		bFlag = true;
           		alList = new ArrayList(); 
           		while(objRs.next())
           		{
           		    objGroups = new OIBVUserProfileSearch();
           			objGroups.setId(OIFormUtilities.chkIsNull(objRs.getString(1)));
					objGroups.setGrpName(OIFormUtilities.chkIsNull(objRs.getString(2)));
					objGroups.setGrpDesc(OIFormUtilities.chkIsNull(objRs.getString(3)));  
					objGroups.setGrpType(OIFormUtilities.chkIsNull(objRs.getString(4)));
           			alList.add(objGroups);
           		}
        	}
		}
        catch(Exception sqle)
		{
			LOGGER.error("searchUserGroup " + sqle.getMessage());
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
                 	
    	LOGGER.debug(OILabelConstants.END_METHOD_DAO +"searchUserGroup");
    	return alList;
    }
  
    /**
	 * This method is to get the list of groups with Description and Group. 
	 * @param connection
	 * @return ArrayList
	 * @throws Exception
	 */
    public boolean selectUser(Connection objCon,OIBVUserProfileSearch objBVUsrPrfSrh,String strQuery) throws Exception
    {
    	LOGGER.debug(OILabelConstants.BEGIN_METHOD_DAO +"selectUser"); 
    	ArrayList alList=null;
        ResultSet objRs =null;
        PreparedStatement objPs=null;
        OIBVUserProfileSearch objGroups = null; 
        boolean bFlag = false;
        StringBuffer sbGr = new StringBuffer(OISearchSqls.QRY_SLTURS1);
        try
        { 
        	String strGrpid[] = objBVUsrPrfSrh.getGroupId();
        	if(strGrpid!= null && strGrpid.length >0)
        	{
        		int nLen = strGrpid.length;
        		for(int i =0;i<nLen-1;i++)
        		{
        		    sbGr.append(strGrpid[i]);
					if( i != nLen-1)
					{
						sbGr.append(OISearchSqls.COMMA);
					}
        		}	
        		
        		sbGr.append("0");
        		sbGr.append(" )").append(strQuery).append(objBVUsrPrfSrh.getId()).append(" )");
	        	objPs = objCon.prepareStatement(sbGr.toString());
	        	objRs = objPs.executeQuery();
	            String strVal = null;
	            if(objRs!= null)
	            {
	                alList = new ArrayList(); 
	           		while(objRs.next())
	           		{
	           		    bFlag = true;
	           			alList.add(OIFormUtilities.chkIsNull(objRs.getString(1)));
	           		}
	            }
        	}
        }
        catch(Exception sqle)
        {
            LOGGER.error("selectUser " + sqle.getMessage());
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
        
        if (alList.size()>0)
        {
        	String[] strUsr =  new String[alList.size()];
        	objBVUsrPrfSrh.setUserId((String[]) alList.toArray(strUsr));
        }		
                 	
    	LOGGER.debug(OILabelConstants.END_METHOD_DAO +"selectUser");
    	return bFlag;
    } 
    
    /**
     * This method is used to get RowCount for Pagination.
     * @param objCon
     * @param objBVUsrPrfSrh
     * @return
     * @throws Exception
     */

    public boolean srchRowCntQry(Connection objCon, OIBVUserProfileSearch objBVUsrPrfSrh) throws Exception
    {
        LOGGER.debug(OILabelConstants.BEGIN_METHOD_DAO +"srchRowCntQry"); 
        
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
         		strQuery = OISearchSqls.SLTCOUNT+objBVUsrPrfSrh.getQuery()+" )" ;
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
           
            chngCaption(objBVUsrPrfSrh);
        }
        catch(Exception sqle)
        {
			LOGGER.error("srchRowCntQry " + sqle.getMessage());
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
        
        LOGGER.debug(OILabelConstants.END_METHOD_DAO +"srchRowCntQry");
        return bFlag;
    }
 
    /**
     * This method is used to get RowCount for Pagination.
     * @param objCon
     * @param objBVUsrPrfSrh
     * @return
     * @throws Exception
     */
    public ArrayList selectIds(Connection objCon, OIBVUserProfileSearch objBVUsrPrfSrh,String strQuery,ArrayList arSearch) throws Exception
    {
        LOGGER.debug(OILabelConstants.BEGIN_METHOD_DAO +"selectIds"); 
        
        ArrayList arTemp =null;
        ResultSet objRs =null;
        PreparedStatement objPs=null;
        OIBVUserProfileSearch objBVUsr = null;
        LinkedHashMap hmSearch = null; 
		boolean bFlag = false;
        
        try
        { 
        	if(arSearch != null && arSearch.size()>0)
        	{
        		int nLen = arSearch.size();
	        	objPs = objCon.prepareStatement(strQuery);
	        	objPs.setInt(1,Integer.parseInt(objBVUsrPrfSrh.getId()));
	        	objRs = objPs.executeQuery();
	            if(objRs!= null )
	            {
	            	bFlag = true;
	            	arTemp = new ArrayList();
	            	while(objRs.next())
	            	{
	            	    for(int i = 0;i <nLen ;i++ )
	            	    {
	            	        hmSearch = (LinkedHashMap) arSearch.get(i);
	            			if( hmSearch.get("USERID").toString().equals(OIFormUtilities.chkIsNull(objRs.getString(1))))
	            			{
	            			    hmSearch.put("PAPERID","D");
	            			}
	            	    }	
	            	}
	            }
        	}
		}
        catch(Exception sqle)
        {
			LOGGER.error("selectIds " + sqle.getMessage());
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
        
        LOGGER.debug(OILabelConstants.END_METHOD_DAO +"selectIds");
        return arSearch;
    }
 
    

    /**
     * This method Inserts the Select USers to OI_FM_BOARDMODADMINID table
     * @param objCon
     * @param objBVUsrPrfSrh
     * @return
     * @throws Exception
     */
    
    
    public boolean addBoards(Connection objCon, OIBVUserProfileSearch objBVUsrPrfSrh) throws Exception
    {
    	 LOGGER.debug(OILabelConstants.BEGIN_METHOD_DAO +"addBoards"); 

    	 PreparedStatement objPs=null;
    	 String[] strUserId = null;
    	 boolean bFlag = false;
    	 int[] nUpdate = null;
    	
    	 try
    	 {
    	 	objPs = objCon.prepareStatement(OISearchSqls.QRY_BOARD_INS);
    	 	strUserId = objBVUsrPrfSrh.getUserId();
    	 	if(strUserId!=null)
    	 	{
    	 		int batchCount = 0;
    	 		int executeCount = 0;
    	 		
    	 	    for(int i =0;i < strUserId.length; i++ )
    	 	    {
    	 	        if(!OIFormUtilities.chkIsNull(strUserId[i]).equals("userId"))
    	 	        {
    	 	            objPs.setInt(1, Integer.parseInt(objBVUsrPrfSrh.getId()));
			        	objPs.setString(2,OIFormUtilities.chkIsNull(strUserId[i]));
			        	objPs.addBatch();
			        	batchCount++;
    	 	        }
    	 	        
    	 	        if (batchCount == 5000)
    	 	        {
    	 	        	nUpdate = objPs.executeBatch();
    	 	        	executeCount += nUpdate.length;
    	 	        	batchCount = 0;
    	 	        	//objPs = objCon.prepareStatement(OISearchSqls.QRY_BOARD_INS);
    	 	        }
    	 	    }
    	 	   if (batchCount > 0)
    	 	   {
		    	 	nUpdate = objPs.executeBatch();
		    	 	executeCount += nUpdate.length;
    	 	   }
	    	 	
	            if(executeCount >0)
	            {
	                objBVUsrPrfSrh.setUpCnt(String.valueOf(executeCount));
	            	bFlag = true;
	            }
    	 	}
    	 }
    	 catch(Exception sqle)
    	 {
			LOGGER.error("addBoards : " + sqle.getMessage());
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
    	 LOGGER.debug(OILabelConstants.END_METHOD_DAO +"addBoards");
    	 return bFlag;
    }
    
    /**
     * This method Inserts the Select USers to Groups Table
     * @param objCon
     * @param objBVUsrPrfSrh
     * @return
     * @throws Exception
     */
    public boolean addGroups(Connection objCon, OIBVUserProfileSearch objBVUsrPrfSrh) throws Exception
    {
        LOGGER.debug(OILabelConstants.BEGIN_METHOD_DAO +"addGroups"); 
        
        PreparedStatement objPs=null;
        String[] strUserId = null;
		boolean bFlag = false;
        int[] nUpdate = null;
    	
        try
        {
    	 	objPs = objCon.prepareStatement(OISearchSqls.QRY_GROUPS_INS);
    	 	strUserId = objBVUsrPrfSrh.getUserId();
    	 	if(strUserId!=null)
    	 	{
    	 		int batchCount = 0;
	 	    	int executeCount = 0;
	 	    	
    	 	    for(int i =0;i < strUserId.length; i++ )
    	 	    {
    	 	        if(!OIFormUtilities.chkIsNull(strUserId[i]).equals("userId"))
    	 	        {
    	 	            objPs.setInt(1, Integer.parseInt(objBVUsrPrfSrh.getId()));
			        	objPs.setString(2,OIFormUtilities.chkIsNull(strUserId[i]));
			        	objPs.addBatch();
			        	batchCount++;
	    	 		}
    	 	        
    	 	        if (batchCount == 5000)
    	 	        {
    	 	        	nUpdate = objPs.executeBatch();
    	 	        	executeCount += nUpdate.length;
    	 	        	batchCount = 0;
    	 	        	//objPs = objCon.prepareStatement(OISearchSqls.QRY_GROUPS_INS);
    	 	        }
	    	 	}
    	 	   if (batchCount > 0)
    	 	   {
		    	 	nUpdate = objPs.executeBatch();
		    	 	executeCount += nUpdate.length;
    	 	   }
	    	 	
	            if(executeCount >0)
	            {
	                objBVUsrPrfSrh.setUpCnt(String.valueOf(executeCount));
	            	bFlag = true;
	            }
    	 	}
        }
        catch(Exception sqle)
        {
			LOGGER.error("addGroups - " + sqle.getMessage());
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
		LOGGER.debug(OILabelConstants.END_METHOD_DAO +"addGroups");
		return bFlag;
    }
    
    
    
    /**
     * This is helper method to replace sorting pararmeters
     * @param objBV
     * @param strQuery
     * @return
     * @throws Exception
     */
    private String addSorting(OIBVUserProfileSearch objBV,String strQuery) throws Exception 
    {
    	Pattern pat=Pattern.compile("&");
     	Matcher matcher=pat.matcher(strQuery);
     	String strTemp  = matcher.replaceFirst(objBV.getHidSortKey() + OILabelConstants.SPACE + objBV.getHidOrder());
     	return strTemp;
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
            sbQuery.append(" " + OIUtilities.addSingleQuoteDB(strKey) + " " ).append(OISearchSqls.COMMA);
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
}