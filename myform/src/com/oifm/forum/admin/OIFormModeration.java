
package com.oifm.forum.admin;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseActionForm;
import com.oifm.forum.OIBAPosting;

public class OIFormModeration extends OIBaseActionForm  {

	private transient static Logger logger = Logger.getLogger(OIFormModeration.class);

	private String strUserId;
	private String strPostId;
	private String strThreadId;
	private ArrayList modStatus;
	
	public OIFormModeration() {
		modStatus = new ArrayList();
	}

	public String getStrUserId() {
		return strUserId;
	}
	public void setStrUserId(String strUserId) {
		this.strUserId = strUserId;
	}
	
	public ArrayList getModStatus() {
		return modStatus;
	}
	public void setModStatus(ArrayList modStatus) {
		this.modStatus = modStatus;
	}

	public String getStrPostId() {
		return strPostId;
	}
	public void setStrPostId(String strPostId) {
		this.strPostId = strPostId;
	}

	public String getStrThreadId() {
		return strThreadId;
	}
	public void setStrThreadId(String strThreadId) {
		this.strThreadId = strThreadId;
	}

	public OIBAPosting getObjPosting(int ind) {
		if (modStatus.size() <= ind) {
			for (int i = modStatus.size(); i <= ind; i++) {
				modStatus.add(new OIBAPosting());
			}
		}
		return (OIBAPosting)(this.modStatus.get(ind));
	}
	public void setObjPosting(int ind, OIBAPosting objPosting) {
		if (modStatus.size() <= ind) {
			for (int i = modStatus.size(); i <= ind; i++) {
				modStatus.add(new OIBAPosting());
			}
		}
logger.debug("******* set ind : "+ind+" Post id : "+objPosting.getStrPostId()+" status : "+objPosting.getStrApproveStatus());
		this.modStatus.set(ind, objPosting);
	}

}
