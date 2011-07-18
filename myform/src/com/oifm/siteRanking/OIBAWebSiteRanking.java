package com.oifm.siteRanking;

import java.util.Date;

public class OIBAWebSiteRanking 
{
	private String userId;
	private String actionId;
	private String actionType;
	private Date actionTime;
	private int itemId;
	
	/**
	 * @return Returns the actionId.
	 */
	public String getActionId() {
		return actionId;
	}
	/**
	 * @param actionId The actionId to set.
	 */
	public void setActionId(String actionId) {
		this.actionId = actionId;
	}
	/**
	 * @return Returns the actionTime.
	 */
	public Date getActionTime() {
		return actionTime;
	}
	/**
	 * @param actionTime The actionTime to set.
	 */
	public void setActionTime(Date actionTime) {
		this.actionTime = actionTime;
	}
	/**
	 * @return Returns the actionType.
	 */
	public String getActionType() {
		return actionType;
	}
	/**
	 * @param actionType The actionType to set.
	 */
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	/**
	 * @return Returns the itemId.
	 */
	public int getItemId() {
		return itemId;
	}
	/**
	 * @param itemId The itemId to set.
	 */
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	/**
	 * @return Returns the userId.
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId The userId to set.
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
