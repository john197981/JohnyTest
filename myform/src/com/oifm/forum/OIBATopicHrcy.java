
package com.oifm.forum;

import java.io.Serializable;

public class OIBATopicHrcy implements Serializable  {

	private String strId;
	private String strTitle;
	private int level;
	
	public OIBATopicHrcy() {	}

	public int getLevel() {
		return level;
	}
	public String getStrId() {
		return strId;
	}
	public String getStrTitle() {
		return strTitle;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public void setStrId(String strId) {
		this.strId = strId;
	}
	public void setStrTitle(String strTitle) {
		this.strTitle = strTitle;
	}
}
