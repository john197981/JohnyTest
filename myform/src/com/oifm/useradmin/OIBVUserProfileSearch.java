/*************************************************************************************************
 * File Name		:	OIBVUserProfileSearch.java
 * Author			:	SureshKumar.R
 * Description		:   This BV is used to store search criteria 		
 * Created Date		:	Jul 13, 2005
 * Version			:	1.0
 * Copyright : Scandent Group
 *************************************************************************************************/

package com.oifm.useradmin;

import java.io.Serializable;
import java.util.ArrayList;

public class OIBVUserProfileSearch  implements Serializable {
	
	 	private String strName;
	    private String strAge;
	    private String strMailId;
	    private String strSchool;
	    private String strNoOfConsultationPapers;
	    private String strZone;
	    private String strCluster;
	    private String strSchoolType;
	    private String strCca;
	    private String strNoOfSurvey;
	    private String strHiddenAction;
	    
	    private String id;
	    private String[] strUserId;
	    private String[] strColName;
	    private String strFlag;
	    
	    /** Group Search **/
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
	    private String strHidBoard;
	    private String strAdminId;
	    private ArrayList alListCol = new ArrayList();
	    private String strQuery;
	    
	    
	    /*---*/
	    
		private String strNRIC;
		private String strRole;
	 	private String strSalutation;
	 	private String strNickName;
	 	private String strGrade;
	    
	    private String strDesign;
	    private String strArea;
	    private String strDivision;
	    private String strLevelTech;
	    private String strSubTech;
	    private String strHobbies;
	    private String strYrJoin;
	    private String strDivStatus;
	    
	    	    
	    private String strId;
	    private String strChk;
	    private String hiddenAction;
	    private String strCol;
	    private String strSign;
	    private String strShowSign;
	    private String strTotPostings;
	    private String[] strBookIds;
	    private String strLoginRole;
	    
	    private String[] strStickyIds;
	    
	    private String strFromAge;
	    private String strToAge;
	    
	    private String strFromYrJoin;
	    private String strToYrJoin;
	    
		private String strGrpType;
	    
	    
	
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
			this.alListCol = alListCol;
		}
		
		
		/**
		 * @return Returns the nRowCount.
		 */
		public int getRowCount() {
			return nRowCount;
		}
		/**
		 * @param rowCount The nRowCount to set.
		 */
		public void setRowCount(int rowCount) {
			nRowCount = rowCount;
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
			this.strAdminId = strAdminId;
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
			this.strArea = strArea;
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
		 * @return Returns the strCol.
		 */
		public String getCol() {
			return strCol;
		}
		/**
		 * @param strCol The strCol to set.
		 */
		public void setCol(String strCol) {
			this.strCol = strCol;
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
		 * @return Returns the strHidBoard.
		 */
		public String getHidBoard() {
			return strHidBoard;
		}
		/**
		 * @param strHidBoard The strHidBoard to set.
		 */
		public void setHidBoard(String strHidBoard) {
			this.strHidBoard = strHidBoard;
		}
		/**
		 * @return Returns the strHiddenAction.
		 */
		public String getHiddenAction() {
			return strHiddenAction;
		}
		/**
		 * @param strHiddenAction The strHiddenAction to set.
		 */
		public void setHiddenAction(String strHiddenAction) {
			this.strHiddenAction = strHiddenAction;
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
		 * @return Returns the strLoginRole.
		 */
		public String getLoginRole() {
			return strLoginRole;
		}
		/**
		 * @param strLoginRole The strLoginRole to set.
		 */
		public void setLoginRole(String strLoginRole) {
			this.strLoginRole = strLoginRole;
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
		 * @return Returns the strNoOfConsultationPapers.
		 */
		public String getNoOfConsultationPapers() {
			return strNoOfConsultationPapers;
		}
		/**
		 * @param strNoOfConsultationPapers The strNoOfConsultationPapers to set.
		 */
		public void setNoOfConsultationPapers(
				String strNoOfConsultationPapers) {
			this.strNoOfConsultationPapers = strNoOfConsultationPapers;
		}
		/**
		 * @return Returns the strNoOfSurvey.
		 */
		public String getNoOfSurvey() {
			return strNoOfSurvey;
		}
		/**
		 * @param strNoOfSurvey The strNoOfSurvey to set.
		 */
		public void setNoOfSurvey(String strNoOfSurvey) {
			this.strNoOfSurvey = strNoOfSurvey;
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
		 * @return Returns the strQuery.
		 */
		public String getQuery() {
			return strQuery;
		}
		/**
		 * @param strQuery The strQuery to set.
		 */
		public void setQuery(String strQuery) {
			this.strQuery = strQuery;
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
		 * @return Returns the strSalutation.
		 */
		public String getSalutation() {
			return strSalutation;
		}
		/**
		 * @param strSalutation The strSalutation to set.
		 */
		public void setSalutation(String strSalutation) {
			this.strSalutation = strSalutation;
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
		 * @return Returns the strYrJoin.
		 */
		public String getYrJoin() {
			return strYrJoin;
		}
		/**
		 * @param strYrJoin The strYrJoin to set.
		 */
		public void setYrJoin(String strYrJoin) {
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
			this.strZone = strZone;
		}

		/**
		 * @return Returns the strGrpType.
		 */
		public String getGrpType() {
			return strGrpType;
		}
		/**
		 * @param strGrpType The strGrpType to set.
		 */
		public void setGrpType(String strGrpType) {
			this.strGrpType = strGrpType;
		}
}
