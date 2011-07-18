/*
 * Created on Aug 4, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.oifm.useradmin.admin;

import com.oifm.base.OIBaseActionForm;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class OIFormAdminUserProfile extends OIBaseActionForm {
	
		private String strNRIC;
		private String strRole;
	 	private String strName;
	 	private String strSchool;
	 	private String strNickName;
	 	private String strSchoolType;
	 	private String strMailId;
	 	private String strMailName;
	 	private String strMailDomain;
	 	private String strGrade;
	    
	    private String strFromAge;
	    private String strToAge;
	    
	    private String strDesign;
	    private String strArea;
	    private String strDivision;
	    private String strCca;
	    private String strZone;
	    private String strCluster;
	    private String strLevelTech;
	    private String strSubTech;
	    private String strHobbies;
	    
	    
	    private String strFromYrJoin;
	    private String strToYrJoin;
	    
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

	    private String strCol;
	    private String strSign;
	    private String[] strBookIds;
	    private String strShowSign; 
	    private String[] strStickyIds;
	    private String strTotPostings;
		private String obsolete;
		
		
		private String tags;
		
		public String getTags() {
			return tags;
		}
		public void setTags(String tags) {
			this.tags = tags;
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
			this.strToYrJoin = strToYrJoin;
		}
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
			this.strStickyIds = strStickyIds;
		}
		/**
		 * @return Returns the strShowSign.
		 */
		public String getShowSign() {
			return strShowSign;
		}
		/**
		 * @param strShowSign The strShowSign to set.
		 */
		public void setShowSign(String strShowSign) {
			this.strShowSign = strShowSign;
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
			this.strBookIds = strBookIds;
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
			this.strCol = strCol;
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
			nRowCount = rowCount;
		}
		/**
		 * @return Returns the strAge.
		 */
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
			this.strMailId = strMailId;
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
			this.strUserId = strUserId;
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
			this.strZone = strZone;
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
			this.strTotPostings = strTotPostings;
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
		public void setMailDomain(String strMailDomain) {
			this.strMailDomain = strMailDomain;
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
		public void setMailName(String strMailName) {
			this.strMailName = strMailName;
		}

		/**
		 * @return Returns the obsolete.
		 */

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
