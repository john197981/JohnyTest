package com.oifm.forum;

import java.util.ArrayList;

import com.oifm.base.OIBaseActionForm;


public class OIFormThreadsSearch extends OIBaseActionForm {
	private String strThreadList;
	private String strThreadListType;
	public String getStrThreadList() {
		return strThreadList;
	}
	public void setStrThreadList(String strThreadList) {
		this.strThreadList = strThreadList;
	}
	public String getStrThreadListType() {
		return strThreadListType;
	}
	public void setStrThreadListType(String strThreadListType) {
		this.strThreadListType = strThreadListType;
	}
}