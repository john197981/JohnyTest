/*********************************ASMBVReport.java******************* 
 * Title 		: ASMBVReport
 * Description 	: This class is the VO Class for Report. 
 * Author 		: Anbalagan Varadharajan 
 * Version No 	: 1.0 
 * Date Created : 23 - Dec -2005
 * Copyright 	: Scandent Group
 ******************************************************************************/

package com.oifm.asm;
import java.io.Serializable;
 

public class ASMBVReport implements Serializable  
{
	private boolean chkSubmitOn = false; 
	private boolean chkWrittenBy = false;
	private boolean chkDesigLW = false;
	private boolean chkDivisionLW = false;
	private boolean chkLetterContent = false;
	private boolean chkLetterTitle = false;
	private boolean chkYIS = false;
	private boolean chkAge = false;
	private boolean chkDivInCharge = false;
	private boolean chkRedirectTo = false;
	private boolean chkRedirectOn = false;
	private boolean chkRepliedBy = false;
	private boolean chkReplyContent = false;
	private boolean chkCategory = false;
	private boolean chkRepliedOn = false;
	
	private String txtSubOnFromDate = null;
	private String txtSubOnToDate = null;
	private String txtWrittenBy = null;
	private String txtDesigLW = null;
	private String txtDivisionLW = null;
	private String txtLetterContent = null;
	private String txtYISFromDate = null;
	private String txtYISToDate = null;
	private String txtAgeFromDate = null;
	private String txtAgeToDate = null;
	private String txtRedirectTo = null;
	private String txtRedirectFromDate = null;
	private String txtRedirectToDate = null;
	private String txtReplycontent = null;
	
	private String cboDivInCharge = null;
	private String txtRepliedBy = null;
	private String cboCategory = null;
	private String txtReplyFromDate = null;
	private String txtReplyToDate = null;
	
	private String strLetterTopic = null;
	private String strLetterID = null;
	private String strPageDesc = null;
	private String strHiddenAction = null;
	private String hidDivIncharge =null;
	private String hidSchDiv =null;
	private String hidCategory =null;
	/**
	 * @return Returns the strLetterTopic.
	 */
	public String getHidLetterTopic() {
		return strLetterTopic;
	}
	/**
	 * @param strLetterTopic The strLetterTopic to set.
	 */
	public void setHidLetterTopic(String strLetterTopic) {
		this.strLetterTopic = strLetterTopic;
	}
	/**
	 * @return Returns the strLetterID.
	 */
	
	public String getHidLetterID() {
		return strLetterID;
	}
	/**
	 * @param strLetterID The strLetterID to set.
	 */
	public void setHidLetterID(String strLetterID) {
		this.strLetterID = strLetterID;
	}
	/**
	 * @return Returns the strPageDesc.
	 */
	public String getHidPageDesc() {
		return strPageDesc;
	}
	/**
	 * @param strPageDesc The strPageDesc to set.
	 */
	public void setHidPageDesc(String strPageDesc) {
		this.strPageDesc = strPageDesc;
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
	 * @return Returns the cboDivInCharge.
	 */
	public String getCboDivInCharge() {
		return cboDivInCharge;
	}
	/**
	 * @param cboDivInCharge The cboDivInCharge to set.
	 */
	public void setCboDivInCharge(String cboDivInCharge) {
		this.cboDivInCharge = cboDivInCharge;
	}
	/**
	 * @return Returns the txtRepliedBy.
	 */
	public String getTxtRepliedBy() {
		return txtRepliedBy;
	}
	/**
	 * @param txtRepliedBy The cboRepliedBy to set.
	 */
	public void setTxtRepliedBy(String txtRepliedBy) {
		this.txtRepliedBy = txtRepliedBy;
	}
	/**
	 * @return Returns the chkAge.
	 */
	public boolean getChkAge() {
		return chkAge;
	}
	/**
	 * @param chkAge The chkAge to set.
	 */
	public void setChkAge(boolean chkAge) {
		this.chkAge = chkAge;
	}
	/**
	 * @return Returns the chkDesigLW.
	 */
	public boolean getChkDesigLW() {
		return chkDesigLW;
	}
	/**
	 * @param chkDesigLW The chkDesigLW to set.
	 */
	public void setChkDesigLW(boolean chkDesigLW) {
		this.chkDesigLW = chkDesigLW;
	}
	/**
	 * @return Returns the chkDivInCharge.
	 */
	public boolean getChkDivInCharge() {
		return chkDivInCharge;
	}
	/**
	 * @param chkDivInCharge The chkDivInCharge to set.
	 */
	public void setChkDivInCharge(boolean chkDivInCharge) {
		this.chkDivInCharge = chkDivInCharge;
	}
	/**
	 * @return the chkCategory
	 */
	public boolean getChkCategory()
	{
		return chkCategory;
	}
	/**
	 * @param chkCategory the chkCategory to set
	 */
	public void setChkCategory(boolean chkCategory)
	{
		this.chkCategory = chkCategory;
	}
	
	
	
	
	
	/**
	 * @return Returns the chkDivisionLW.
	 */
	public boolean getChkDivisionLW() {
		return chkDivisionLW;
	}
	/**
	 * @param chkDivisionLW The chkDivisionLW to set.
	 */
	public void setChkDivisionLW(boolean chkDivisionLW) {
		this.chkDivisionLW = chkDivisionLW;
	}
	/**
	 * @return Returns the chkLetterContent.
	 */
	public boolean getChkLetterContent() {
		return chkLetterContent;
	}
	/**
	 * @param chkLetterContent The chkLetterContent to set.
	 */
	public void setChkLetterContent(boolean chkLetterContent) {
		this.chkLetterContent = chkLetterContent;
	}
	/**
	 * @return Returns the chkRedirectOn.
	 */
	public boolean getChkRedirectOn() {
		return chkRedirectOn;
	}
	/**
	 * @param chkRedirectOn The chkRedirectOn to set.
	 */
	public void setChkRedirectOn(boolean chkRedirectOn) {
		this.chkRedirectOn = chkRedirectOn;
	}
	/**
	 * @return Returns the chkRedirectTo.
	 */
	public boolean getChkRedirectTo() {
		return chkRedirectTo;
	}
	/**
	 * @param chkRedirectTo The chkRedirectTo to set.
	 */
	public void setChkRedirectTo(boolean chkRedirectTo) {
		this.chkRedirectTo = chkRedirectTo;
	}
	/**
	 * @return Returns the chkRepliedBy.
	 */
	public boolean getChkRepliedBy() {
		return chkRepliedBy;
	}
	/**
	 * @param chkRepliedBy The chkRepliedBy to set.
	 */
	public void setChkRepliedBy(boolean chkRepliedBy) {
		this.chkRepliedBy = chkRepliedBy;
	}
	/**
	 * @return Returns the chkReplyContent.
	 */
	public boolean getChkReplyContent() {
		return chkReplyContent;
	}
	/**
	 * @param chkReplyContent The chkReplyContent to set.
	 */
	public void setChkReplyContent(boolean chkReplyContent) {
		this.chkReplyContent = chkReplyContent;
	}
	/**
	 * @return Returns the chkSubmitOn.
	 */
	public boolean getChkSubmitOn() {
		return chkSubmitOn;
	}
	/**
	 * @param chkSubmitOn The chkSubmitOn to set.
	 */
	public void setChkSubmitOn(boolean chkSubmitOn) {
		this.chkSubmitOn = chkSubmitOn;
	}
	/**
	 * @return Returns the chkWrittenBy.
	 */
	public boolean getChkWrittenBy() {
		return chkWrittenBy;
	}
	/**
	 * @param chkWrittenBy The chkWrittenBy to set.
	 */
	public void setChkWrittenBy(boolean chkWrittenBy) {
		this.chkWrittenBy = chkWrittenBy;
	}
	/**
	 * @return Returns the chkYIS.
	 */
	public boolean getChkYIS() {
		return chkYIS;
	}
	/**
	 * @param chkYIS The chkYIS to set.
	 */
	public void setChkYIS(boolean chkYIS) {
		this.chkYIS = chkYIS;
	}
	/**
	 * @return Returns the txtAgeFromDate.
	 */
	public String getTxtAgeFromDate() {
		return txtAgeFromDate;
	}
	/**
	 * @param txtAgeFromDate The txtAgeFromDate to set.
	 */
	public void setTxtAgeFromDate(String txtAgeFromDate) {
		this.txtAgeFromDate = txtAgeFromDate;
	}
	/**
	 * @return Returns the txtAgeToDate.
	 */
	public String getTxtAgeToDate() {
		return txtAgeToDate;
	}
	/**
	 * @param txtAgeToDate The txtAgeToDate to set.
	 */
	public void setTxtAgeToDate(String txtAgeToDate) {
		this.txtAgeToDate = txtAgeToDate;
	}
	/**
	 * @return Returns the txtDesigLW.
	 */
	public String getTxtDesigLW() {
		return txtDesigLW;
	}
	/**
	 * @param txtDesigLW The txtDesigLW to set.
	 */
	public void setTxtDesigLW(String txtDesigLW) {
		this.txtDesigLW = txtDesigLW;
	}
	/**
	 * @return Returns the txtDivisionLW.
	 */
	public String getTxtDivisionLW() {
		return txtDivisionLW;
	}
	/**
	 * @param txtDivisionLW The txtDivisionLW to set.
	 */
	public void setTxtDivisionLW(String txtDivisionLW) {
		this.txtDivisionLW = txtDivisionLW;
	}
	/**
	 * @return Returns the txtLetterContent.
	 */
	public String getTxtLetterContent() {
		return txtLetterContent;
	}
	/**
	 * @param txtLetterContent The txtLetterContent to set.
	 */
	public void setTxtLetterContent(String txtLetterContent) {
		this.txtLetterContent = txtLetterContent;
	}
	/**
	 * @return Returns the txtRedirectFromDate.
	 */
	public String getTxtRedirectFromDate() {
		return txtRedirectFromDate;
	}
	/**
	 * @param txtRedirectFromDate The txtRedirectFromDate to set.
	 */
	public void setTxtRedirectFromDate(String txtRedirectFromDate) {
		this.txtRedirectFromDate = txtRedirectFromDate;
	}
	/**
	 * @return Returns the txtRedirectTo.
	 */
	public String getTxtRedirectTo() {
		return txtRedirectTo;
	}
	/**
	 * @param txtRedirectTo The txtRedirectTo to set.
	 */
	public void setTxtRedirectTo(String txtRedirectTo) {
		this.txtRedirectTo = txtRedirectTo;
	}
	/**
	 * @return Returns the txtRedirectToDate.
	 */
	public String getTxtRedirectToDate() {
		return txtRedirectToDate;
	}
	/**
	 * @param txtRedirectToDate The txtRedirectToDate to set.
	 */
	public void setTxtRedirectToDate(String txtRedirectToDate) {
		this.txtRedirectToDate = txtRedirectToDate;
	}
	/**
	 * @return Returns the txtReplycontent.
	 */
	public String getTxtReplycontent() {
		return txtReplycontent;
	}
	/**
	 * @param txtReplycontent The txtReplycontent to set.
	 */
	public void setTxtReplycontent(String txtReplycontent) {
		this.txtReplycontent = txtReplycontent;
	}
	/**
	 * @return Returns the txtSubOnFromDate.
	 */
	public String getTxtSubOnFromDate() {
		return txtSubOnFromDate;
	}
	/**
	 * @param txtSubOnFromDate The txtSubOnFromDate to set.
	 */
	public void setTxtSubOnFromDate(String txtSubOnFromDate) {
		this.txtSubOnFromDate = txtSubOnFromDate;
	}
	/**
	 * @return Returns the txtSubOnToDate.
	 */
	public String getTxtSubOnToDate() {
		return txtSubOnToDate;
	}
	/**
	 * @param txtSubOnToDate The txtSubOnToDate to set.
	 */
	public void setTxtSubOnToDate(String txtSubOnToDate) {
		this.txtSubOnToDate = txtSubOnToDate;
	}
	/**
	 * @return Returns the txtWrittenBy.
	 */
	public String getTxtWrittenBy() {
		return txtWrittenBy;
	}
	/**
	 * @param txtWrittenBy The txtWrittenBy to set.
	 */
	public void setTxtWrittenBy(String txtWrittenBy) {
		this.txtWrittenBy = txtWrittenBy;
	}
	/**
	 * @return Returns the txtYISFromDate.
	 */
	public String getTxtYISFromDate() {
		return txtYISFromDate;
	}
	/**
	 * @param txtYISFromDate The txtYISFromDate to set.
	 */
	public void setTxtYISFromDate(String txtYISFromDate) {
		this.txtYISFromDate = txtYISFromDate;
	}
	/**
	 * @return Returns the txtYISToDate.
	 */
	public String getTxtYISToDate() {
		return txtYISToDate;
	}
	/**
	 * @param txtYISToDate The txtYISToDate to set.
	 */
	public void setTxtYISToDate(String txtYISToDate) {
		this.txtYISToDate = txtYISToDate;
	}
	/**
	 * @return Returns the hidDivIncharge.
	 */
	public String getHidDivIncharge() {
		return hidDivIncharge;
	}
	/**
	 * @param hidDivIncharge The hidDivIncharge to set.
	 */
	public void setHidDivIncharge(String hidDivIncharge) {
		this.hidDivIncharge = hidDivIncharge;
	}
	/**
	 * @return Returns the hidSchDiv.
	 */
	public String getHidSchDiv() {
		return hidSchDiv;
	}
	/**
	 * @param hidSchDiv The hidSchDiv to set.
	 */
	public void setHidSchDiv(String hidSchDiv) {
		this.hidSchDiv = hidSchDiv;
	}
	
	/**
	 * @return Returns the chkLetterTitle.
	 */
	public boolean getChkLetterTitle() {
		return chkLetterTitle;
	}
	/**
	 * @param chkLetterTitle The chkLetterTitle to set.
	 */
	public void setChkLetterTitle(boolean chkLetterTitle) {
		this.chkLetterTitle = chkLetterTitle;
	}
	/**
	 * @return the cboCategory
	 */
	public String getCboCategory()
	{
		return cboCategory;
	}
	/**
	 * @param cboCategory the cboCategory to set
	 */
	public void setCboCategory(String cboCategory)
	{
		this.cboCategory = cboCategory;
	}
	
	/**
	 * @return the chkRepliedOn
	 */
	public boolean getChkRepliedOn()
	{
		return chkRepliedOn;
	}
	/**
	 * @param chkRepliedOn the chkRepliedOn to set
	 */
	public void setChkRepliedOn(boolean chkRepliedOn)
	{
		this.chkRepliedOn = chkRepliedOn;
	}
	/**
	 * @return the hidCategory
	 */
	public String getHidCategory()
	{
		return hidCategory;
	}
	/**
	 * @param hidCategory the hidCategory to set
	 */
	public void setHidCategory(String hidCategory)
	{
		this.hidCategory = hidCategory;
	}
	/**
	 * @return the txtReplyFromDate
	 */
	public String getTxtReplyFromDate()
	{
		return txtReplyFromDate;
	}
	/**
	 * @param txtReplyFromDate the txtReplyFromDate to set
	 */
	public void setTxtReplyFromDate(String txtReplyFromDate)
	{
		this.txtReplyFromDate = txtReplyFromDate;
	}
	/**
	 * @return the txtReplyToDate
	 */
	public String getTxtReplyToDate()
	{
		return txtReplyToDate;
	}
	/**
	 * @param txtReplyToDate the txtReplyToDate to set
	 */
	public void setTxtReplyToDate(String txtReplyToDate)
	{
		this.txtReplyToDate = txtReplyToDate;
	}
	
}
