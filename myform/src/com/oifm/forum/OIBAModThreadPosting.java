
package com.oifm.forum;

import java.io.Serializable;
import java.util.ArrayList;

public class OIBAModThreadPosting implements Serializable  {

	private String strUserId;
	private String strModeratedBy;
	private String strModeratedOn;
	private String[] aryPostStatus;
	private String[] aryPostingIds;
	private String strBoardName;
	private String strTopicName;
	private boolean nextBoard;
	private ArrayList alPostsList;
	
	public OIBAModThreadPosting() {	}

	public String[] getAryPostingIds() {
		return aryPostingIds;
	}
	public String[] getAryPostStatus() {
		return aryPostStatus;
	}
	public String getStrModeratedBy() {
		return strModeratedBy;
	}
	public String getStrModeratedOn() {
		return strModeratedOn;
	}
	public String getStrUserId() {
		return strUserId;
	}
	public ArrayList getAlPostsList() {
		return alPostsList;
	}
	public boolean isNextBoard() {
		return nextBoard;
	}
	public String getStrBoardName() {
		return strBoardName;
	}
	public String getStrTopicName() {
		return strTopicName;
	}
	public void setNextBoard(boolean nextBoard) {
		this.nextBoard = nextBoard;
	}
	public void setStrBoardName(String strBoardName) {
		this.strBoardName = strBoardName;
	}
	public void setStrTopicName(String strTopicName) {
		this.strTopicName = strTopicName;
	}
	public void setAlPostsList(ArrayList alPostsList) {
		this.alPostsList = alPostsList;
	}
	public void setAryPostingIds(String[] aryPostingIds) {
		this.aryPostingIds = aryPostingIds;
	}
	public void setAryPostStatus(String[] aryPostStatus) {
		this.aryPostStatus = aryPostStatus;
	}
	public void setStrModeratedBy(String strModeratedBy) {
		this.strModeratedBy = strModeratedBy;
	}
	public void setStrModeratedOn(String strModeratedOn) {
		this.strModeratedOn = strModeratedOn;
	}
	public void setStrUserId(String strUserId) {
		this.strUserId = strUserId;
	}
}
