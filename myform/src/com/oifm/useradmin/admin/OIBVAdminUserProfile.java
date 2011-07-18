/*
 * Created on Aug 4, 2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.oifm.useradmin.admin;

import java.io.Serializable;
import java.util.ArrayList;

import org.apache.log4j.Logger;
/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class OIBVAdminUserProfile implements Serializable {
	
	Logger logger = Logger.getLogger(this.getClass().getName());
	
				
			private String strNRIC;
			private String strRole;
		 	private String strName;
		 	private String strSalutation;
		 	private String strSchool;
		 	private String strNickName;
		 	private String strSchoolType;
		 	private String strMailId;
		 	private String strMailName;
		 	private String strMailDomain;
			private String obsolete;
			
			private String tags;
			
			public String getTags() {
				return tags;
			}
			public void setTags(String tags) {
				this.tags = tags;
			} 
			/**
			 * @return Returns the strMailDomain.
			 */
			public String getMailDomain() {
				return strMailDomain;
			}
			/**
			 * @param strMailDomain The strMailDomain to set.
			 */
			public void setMailDomain(String strMailDomain) 
			{
				//logger.info("1");
				this.strMailDomain = strMailDomain;
				if (strMailName != null && strMailName.length() > 0 && this.strMailDomain != null && this.strMailDomain.length() > 0) 
				{
					this.strMailId = this.strMailName.concat("@").concat(this.strMailDomain);
				}
			}
			/**
			 * @return Returns the strMailName.
			 */
			public String getMailName() {
				return strMailName;
			}
			/**
			 * @param strMailName The strMailName to set.
			 */
			public void setMailName(String strMailName) 
			{
				try
				{
					this.strMailName = strMailName;
					//logger.info("2");
					if (this.strMailName != null)
					{
						this.strMailName = this.strMailName.trim();
					}
					if (this.strMailName != null && this.strMailName.length() > 0 && this.strMailDomain != null && this.strMailDomain.length() > 0) 
					{
						this.strMailId = this.strMailName.concat("@").concat(this.strMailDomain);
					}
				}
				catch(Exception e)
				{
					logger.error(e.getMessage());
				}
			}
		 	private String strGrade;
		    private String strAge;
		    private String strDesign;
		    private String strArea;
		    private String strDivision;
		    private String strCca;
		    private String strZone;
		    private String strCluster;
		    private String strLevelTech;
		    private String strSubTech;
		    private String strHobbies;
		    private String strYrJoin;
		    private String strDivStatus;
		    
		    	    
		    private String strId;
		    private String strChk;
		    private String[] strUserId;
		    private String[] strColName;
		    
		    private String strFlag;
		    
		    private String[] strGroupId;
		    private String strGrpName;
		    private String strGrpDesc;
		    
		    /** Row Id **/
		    private String strRowId;
			private int nRowCount;
			
			
		    private String strHidSortKey;
		    private String strHidOrder;
		    
		    private String strhidPage;
		    
		    private String strUpCnt;

		    private ArrayList alListCol = new ArrayList();
		    
		    private String hiddenAction;
		    private String strCol;
		    private String strSign;
		    private String strAdminId;
		    private String strShowSign;
		    private String strTotPostings;
		    private String[] strBookIds;
		    private String strLoginRole;
		    private String strQuery;
		    private String[] strStickyIds;
		    
		    private String strFromAge;
		    private String strToAge;
		    
		    private String strFromYrJoin;
		    private String strToYrJoin;
		    
		    
			/**
			 * @return Returns the strStickyIds.
			 */
			public String[] getStickyIds() {
				return strStickyIds;
			}
			/**
			 * @param strStickyIds The strStickyIds to set.
			 */
			public void setStickyIds(String[] strStickyIds) {
				//logger.info("3");
				this.strStickyIds = strStickyIds;
			}
			/**
			 * @return Returns the strLoginId.
			 */
			public String getLoginRole() {
				return strLoginRole;
			}
			/**
			 * @param strLoginId The strLoginId to set.
			 */
			public void setLoginRole(String strLoginId) {
				//logger.info("4");
				this.strLoginRole = strLoginId;
			}
			/**
			 * @return Returns the strBookIds.
			 */
			public String[] getBookIds() {
				return strBookIds;
			}
			/**
			 * @param strBookIds The strBookIds to set.
			 */
			public void setBookIds(String[] strBookIds) {
				//logger.info("5");
				this.strBookIds = strBookIds;
			}
			/**
			 * @return Returns the strTotPostings.
			 */
			public String getTotPostings() {
				return strTotPostings;
			}
			/**
			 * @param strTotPostings The strTotPostings to set.
			 */
			public void setTotPostings(String strTotPostings) {
				//logger.info("6");
				this.strTotPostings = strTotPostings;
			}
			/**
			 * @return Returns the strLoginId.
			 */
			public String getShowSign() {
				return strShowSign;
			}
			/**
			 * @param strLoginId The strLoginId to set.
			 */
			public void setShowSign(String strShowSign) {
				//logger.info("7");
				this.strShowSign = strShowSign;
			}
		    /**
			 * @return Returns the strAdminId.
			 */
			public String getAdminId() {
				return strAdminId;
			}
			/**
			 * @param strAdminId The strAdminId to set.
			 */
			public void setAdminId(String strAdminId) {
				//logger.info("8");
				this.strAdminId = strAdminId;
			}
			/**
			 * @return Returns the strSalutation.
			 */
			public String getSalutation() {
				return strSalutation;
			}
			/**
			 * @param strSalutation The strSalutation to set.
			 */
			public void setSalutation(String strSalutation) {
				//logger.info("9");
				this.strSalutation = strSalutation;
			}
			/**
			 * @return Returns the strSign.
			 */
			public String getSign() {
				return strSign;
			}
			/**
			 * @param strSign The strSign to set.
			 */
			public void setSign(String strSign) {
				//logger.info("10");
				this.strSign = strSign;
			}
			/**
			 * @return Returns the strCol.
			 */
			public String getCols() {
				return strCol;
			}
			/**
			 * @param strCol The strCol to set.
			 */
			public void setCols(String strCol) {
				//logger.info("11");
				this.strCol = strCol;
			}

		    public String getHiddenAction()
		    {
		        return hiddenAction;
		    }
		    public void setHiddenAction(String hiddenAction)
		    {
				//logger.info("12");
		        this.hiddenAction=hiddenAction;
		    }
			/**
			 * @return Returns the strCluster.
			 */
			public String getCluster() {
				return strCluster;
			}
			/**
			 * @param strCluster The strCluster to set.
			 */
			public void setCluster(String strCluster) {
				//logger.info("13");
				this.strCluster = strCluster;
			}
			/**
			 * @return Returns the strNickName.
			 */
			public String getNickName() {
				return strNickName;
			}
			/**
			 * @param strNickName The strNickName to set.
			 */
			public void setNickName(String strNickName) {
				//logger.info("14");
				this.strNickName = strNickName;
			}
			/**
			 * @return Returns the nRowCount.
			 */
			public int getNRowCount() {
				return nRowCount;
			}
			/**
			 * @param rowCount The nRowCount to set.
			 */
			public void setNRowCount(int rowCount) {
				//logger.info("15");
				nRowCount = rowCount;
			}
			/**
			 * @return Returns the strAge.
			 */
			public String getAge() {  
				return strAge;
			}
			/**
			 * @param strAge The strAge to set.
			 */
			public void setAge(String strAge) {
				//logger.info("16");
				this.strAge = strAge;
			}
			/**
			 * @return Returns the strArea.
			 */
			public String getArea() {
				return strArea;
			}
			/**
			 * @param strArea The strArea to set.
			 */
			public void setArea(String strArea) {
				//logger.info("17");
				this.strArea = strArea;
			}
			/**
			 * @return Returns the strCca.
			 */
			public String getCca() {
				return strCca;
			}
			/**
			 * @param strCca The strCca to set.
			 */
			public void setCca(String strCca) {
				//logger.info("18");
				this.strCca = strCca;
			}
			/**
			 * @return Returns the strChk.
			 */
			public String getChk() {
				return strChk;
			}
			/**
			 * @param strChk The strChk to set.
			 */
			public void setChk(String strChk) {
				//logger.info("19");
				this.strChk = strChk;
			}
			/**
			 * @return Returns the strColName.
			 */
			public String[] getColName() {
				return strColName;
			}
			/**
			 * @param strColName The strColName to set.
			 */
			public void setColName(String[] strColName) {
				//logger.info("20");
				this.strColName = strColName;
			}
			/**
			 * @return Returns the strDesign.
			 */
			public String getDesign() {
				return strDesign;
			}
			/**
			 * @param strDesign The strDesign to set.
			 */
			public void setDesign(String strDesign) {
				//logger.info("21");
				this.strDesign = strDesign;
			}
			/**
			 * @return Returns the strDivision.
			 */
			public String getDivision() {
				return strDivision;
			}
			/**
			 * @param strDivision The strDivision to set.
			 */
			public void setDivision(String strDivision) {
				//logger.info("22");
				this.strDivision = strDivision;
			}
			/**
			 * @return Returns the strDivStatus.
			 */
			public String getDivStatus() {
				return strDivStatus;
			}
			/**
			 * @param strDivStatus The strDivStatus to set.
			 */
			public void setDivStatus(String strDivStatus) {
				//logger.info("23");
				this.strDivStatus = strDivStatus;
			}
			/**
			 * @return Returns the strFlag.
			 */
			public String getFlag() {
				return strFlag;
			}
			/**
			 * @param strFlag The strFlag to set.
			 */
			public void setFlag(String strFlag) {
				//logger.info("24");
				this.strFlag = strFlag;
			}
			/**
			 * @return Returns the strGrade.
			 */
			public String getGrade() {
				return strGrade;
			}
			/**
			 * @param strGrade The strGrade to set.
			 */
			public void setGrade(String strGrade) {
				//logger.info("25");
				this.strGrade = strGrade;
			}
			/**
			 * @return Returns the strGroupId.
			 */
			public String[] getGroupId() {
				return strGroupId;
			}
			/**
			 * @param strGroupId The strGroupId to set.
			 */
			public void setGroupId(String[] strGroupId) {
				//logger.info("26");
				this.strGroupId = strGroupId;
			}
			/**
			 * @return Returns the strGrpDesc.
			 */
			public String getGrpDesc() {
				return strGrpDesc;
			}
			/**
			 * @param strGrpDesc The strGrpDesc to set.
			 */
			public void setGrpDesc(String strGrpDesc) {
				//logger.info("27");
				this.strGrpDesc = strGrpDesc;
			}
			/**
			 * @return Returns the strGrpName.
			 */
			public String getGrpName() {
				return strGrpName;
			}
			/**
			 * @param strGrpName The strGrpName to set.
			 */
			public void setGrpName(String strGrpName) {
				//logger.info("28");
				this.strGrpName = strGrpName;
			}
			/**
			 * @return Returns the strHidOrder.
			 */
			public String getHidOrder() {
				return strHidOrder;
			}
			/**
			 * @param strHidOrder The strHidOrder to set.
			 */
			public void setHidOrder(String strHidOrder) {
				//logger.info("29");
				this.strHidOrder = strHidOrder;
			}
			/**
			 * @return Returns the strhidPage.
			 */
			public String getHidPage() {
				return strhidPage;
			}
			/**
			 * @param strhidPage The strhidPage to set.
			 */
			public void setHidPage(String strhidPage) {
				//logger.info("30");
				this.strhidPage = strhidPage;
			}
			/**
			 * @return Returns the strHidSortKey.
			 */
			public String getHidSortKey() {
				return strHidSortKey;
			}
			/**
			 * @param strHidSortKey The strHidSortKey to set.
			 */
			public void setHidSortKey(String strHidSortKey) {
				//logger.info("31");
				this.strHidSortKey = strHidSortKey;
			}
			/**
			 * @return Returns the strHobbies.
			 */
			public String getHobbies() {
				return strHobbies;
			}
			/**
			 * @param strHobbies The strHobbies to set.
			 */
			public void setHobbies(String strHobbies) {
				//logger.info("32");
				this.strHobbies = strHobbies;
			}
			/**
			 * @return Returns the strId.
			 */
			public String getId() {
				return strId;
			}
			/**
			 * @param strId The strId to set.
			 */
			public void setId(String strId) {
				//logger.info("33");
				this.strId = strId;
			}
			/**
			 * @return Returns the strLevelTech.
			 */
			public String getLevelTech() {
				return strLevelTech;
			}
			/**
			 * @param strLevelTech The strLevelTech to set.
			 */
			public void setLevelTech(String strLevelTech) {
				//logger.info("34");
				this.strLevelTech = strLevelTech;
			}
			/**
			 * @return Returns the strMailId.
			 */
			public String getMailId() {
				return strMailId;
			}
			/**
			 * @param strMailId The strMailId to set.
			 */
			public void setMailId(String strMailId) {
				//logger.info("35");
				this.strMailId = strMailId;
				if ((this.strMailId != null) && (this.strMailId.length() > 0)) {
					int idx = this.strMailId.indexOf("@");
					//if (idx > 0 && idx < this.strMailId.length()) {
						this.strMailName = this.strMailId.substring(0,idx);
						this.strMailDomain = this.strMailId.substring(idx+1);
					//}
				}
			}
			/**
			 * @return Returns the strName.
			 */
			public String getName() {
				return strName;
			}
			/**
			 * @param strName The strName to set.
			 */
			public void setName(String strName) {
				//logger.info("36");
				this.strName = strName;
			}
			/**
			 * @return Returns the strNRIC.
			 */
			public String getNRIC() {
				return strNRIC;
			}
			/**
			 * @param strNRIC The strNRIC to set.
			 */
			public void setNRIC(String strNRIC) {
				//logger.info("37");
				this.strNRIC = strNRIC;
			}
			/**
			 * @return Returns the strRole.
			 */
			public String getRole() {
				return strRole;
			}
			/**
			 * @param strRole The strRole to set.
			 */
			public void setRole(String strRole) {
				//logger.info("38");
				this.strRole = strRole;
			}
			/**
			 * @return Returns the strRowId.
			 */
			public String getRowId() {
				return strRowId;
			}
			/**
			 * @param strRowId The strRowId to set.
			 */
			public void setRowId(String strRowId) {
				//logger.info("39");
				this.strRowId = strRowId;
			}
			/**
			 * @return Returns the strSchool.
			 */
			public String getSchool() {
				return strSchool;
			}
			/**
			 * @param strSchool The strSchool to set.
			 */
			public void setSchool(String strSchool) {
				//logger.info("40");
				this.strSchool = strSchool;
			}
			/**
			 * @return Returns the strSchoolType.
			 */
			public String getSchoolType() {
				return strSchoolType;
			}
			/**
			 * @param strSchoolType The strSchoolType to set.
			 */
			public void setSchoolType(String strSchoolType) {
				//logger.info("41");
				this.strSchoolType = strSchoolType;
			}
			/**
			 * @return Returns the strSubTech.
			 */
			public String getSubTech() {
				return strSubTech;
			}
			/**
			 * @param strSubTech The strSubTech to set.
			 */
			public void setSubTech(String strSubTech) {
				//logger.info("42");
				this.strSubTech = strSubTech;
			}
			/**
			 * @return Returns the strUpCnt.
			 */
			public String getUpCnt() {
				return strUpCnt;
			}
			/**
			 * @param strUpCnt The strUpCnt to set.
			 */
			public void setUpCnt(String strUpCnt) {
				//logger.info("43");
				this.strUpCnt = strUpCnt;
			}
			/**
			 * @return Returns the strUserId.
			 */
			public String[] getUserId() {
				return strUserId;
			}
			/**
			 * @param strUserId The strUserId to set.
			 */
			public void setUserId(String[] strUserId) {
				//logger.info("44");
				this.strUserId = strUserId;
			}
			/**
			 * @return Returns the strYrJoin.
			 */
			public String getYrJoin() {
				return strYrJoin;
			}
			/**
			 * @param strYrJoin The strYrJoin to set.
			 */
			public void setYrJoin(String strYrJoin) {
				//logger.info("45");
				this.strYrJoin = strYrJoin;
			}
			/**
			 * @return Returns the strZone.
			 */
			public String getZone() {
				return strZone;
			}
			/**
			 * @param strZone The strZone to set.
			 */
			public void setZone(String strZone) {
				//logger.info("46");
				this.strZone = strZone;
			}
	

			/**
			 * @return Returns the alListCol.
			 */
			public ArrayList getAlListCol() {
				return alListCol;
			}
			/**
			 * @param alListCol The alListCol to set.
			 */
			public void setAlListCol(ArrayList alListCol) {
				//logger.info("47");
				this.alListCol = alListCol;
			}
	
			/**
			 * @return Returns the strRowCount.
			 */
			public int getRowCount() {
				return nRowCount;
			}
			/**
			 * @param strRowCount The strRowCount to set.
			 */
			public void setRowCount(int nCount) {
				//logger.info("48");
				this.nRowCount = nCount;
			}
			/**
			 * @return Returns the strHidOrder.
			 */
			public String getOrder() {
				return strHidOrder;
			}
			/**
			 * @param strHidOrder The strHidOrder to set.
			 */
			public void setOrder(String strHidOrder) {
				//logger.info("49");
				this.strHidOrder = strHidOrder;
			}
			/**
			 * @return Returns the strHidSortKey.
			 */
			public String getSortKey() {
				return strHidSortKey;
			}
			/**
			 * @param strHidSortKey The strHidSortKey to set.
			 */
			public void setSortKey(String strHidSortKey) {
				//logger.info("50");
				this.strHidSortKey = strHidSortKey;
			}
			/**
			 * @return Returns the strQuery.
			 */
			public String getQuery() {
				return strQuery;
			}
			/**
			 * @param strQuery The strQuery to set.
			 */
			public void setQuery(String strQuery) {
				//logger.info("51");
				this.strQuery = strQuery;
			}
			/**
			 * @return Returns the strFromAge.
			 */
			public String getFromAge() {
				return strFromAge;
			}
			/**
			 * @param strFromAge The strFromAge to set.
			 */
			public void setFromAge(String strFromAge) {
				//logger.info("52");
				this.strFromAge = strFromAge;
			}
			/**
			 * @return Returns the strFromYrJoin.
			 */
			public String getFromYrJoin() {
				return strFromYrJoin;
			}
			/**
			 * @param strFromYrJoin The strFromYrJoin to set.
			 */
			public void setFromYrJoin(String strFromYrJoin) {
				//logger.info("53");
				this.strFromYrJoin = strFromYrJoin;
			}
			/**
			 * @return Returns the strToAge.
			 */
			public String getToAge() {
				return strToAge;
			}
			/**
			 * @param strToAge The strToAge to set.
			 */
			public void setToAge(String strToAge) {
				//logger.info("54");
				this.strToAge = strToAge;
			}
			/**
			 * @return Returns the strToYrJoin.
			 */
			public String getToYrJoin() {
				return strToYrJoin;
			}
			/**
			 * @param strToYrJoin The strToYrJoin to set.
			 */
			public void setToYrJoin(String strToYrJoin) {
				//logger.info("55");
				this.strToYrJoin = strToYrJoin;
			}

			public String getObsolete() {
				return obsolete;
			}
			/**
			 * @param obsolete The obsolete to set.
			 */
			public void setObsolete(String obsolete) {
				//logger.info("55");
				this.obsolete = obsolete;
			}
}
