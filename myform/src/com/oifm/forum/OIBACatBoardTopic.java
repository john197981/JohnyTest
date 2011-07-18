
package com.oifm.forum;

import java.io.Serializable;

public class OIBACatBoardTopic implements Serializable {

	private String strCategoryId;
	private String strCategory;
	private String strBoardId;
	private String strBoard;
	private String strTopicId;
	private String strTopic;
	private String strThreadId;
	private String strThread;
	private String strTopicDesc;

	public String getStrBoard() {
		return strBoard;
	}
	public String getStrBoardId() {
		return strBoardId;
	}
	public String getStrCategory() {
		return strCategory;
	}
	public String getStrCategoryId() {
		return strCategoryId;
	}
	public String getStrThread() {
		return strThread;
	}
	public String getStrThreadId() {
		return strThreadId;
	}
	public String getStrTopic() {
		return strTopic;
	}
	public String getStrTopicId() {
		return strTopicId;
	}
	public void setStrBoard(String strBoard) {
		this.strBoard = strBoard;
	}
	public void setStrBoardId(String strBoardId) {
		this.strBoardId = strBoardId;
	}
	public void setStrCategory(String strCategory) {
		this.strCategory = strCategory;
	}
	public void setStrCategoryId(String strCategoryId) {
		this.strCategoryId = strCategoryId;
	}
	public void setStrThread(String strThread) {
		this.strThread = strThread;
	}
	public void setStrThreadId(String strThreadId) {
		this.strThreadId = strThreadId;
	}
	public void setStrTopic(String strTopic) {
		this.strTopic = strTopic;
	}
	public void setStrTopicId(String strTopicId) {
		this.strTopicId = strTopicId;
	}
	public void setStrTopicDesc(String strTopicDesc) {
		this.strTopicDesc = strTopicDesc;
	}
	public String getStrTopicDesc() {
		return strTopicDesc;
	}
	
}
