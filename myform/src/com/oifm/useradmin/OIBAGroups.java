package com.oifm.useradmin;

import com.oifm.base.OIBaseBa;
public class OIBAGroups extends OIBaseBa {
    private String description;
    private String createdBy;
    private String name;
    private java.util.Date createdOn;
    private Integer groupId;
    private String createdOnstr;
    private String divName;
    private String nickname;
    private Integer numOfUsers;
    private String sortKey = "0";
    private String sortOrder = "0";
    
	/**
	 * @return Returns the sortKey.
	 */
	public String getSortKey() {
		return sortKey;
	}
	/**
	 * @param sortKey The sortKey to set.
	 */
	public void setSortKey(String sortKey) {
		if (sortKey != null) this.sortKey = sortKey;
		else this.sortKey = "0";
	}
	/**
	 * @return Returns the sortOrder.
	 */
	public String getSortOrder() {
		return sortOrder;
	}
	/**
	 * @param sortOrder The sortOrder to set.
	 */
	public void setSortOrder(String sortOrder) {
		if (sortOrder != null) this.sortOrder = sortOrder;
		else this.sortOrder = "0";
	}
    public OIBAGroups(){}
	/**
	 * @return Returns the nickname.
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * @param nickname The nickname to set.
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	/**
	 * @return Returns the numOfUsers.
	 */
	public Integer getNumOfUsers() {
		return numOfUsers;
	}
	/**
	 * @param numOfUsers The numOfUsers to set.
	 */
	public void setNumOfUsers(Integer numOfUsers) {
		this.numOfUsers = numOfUsers;
	}
	/**
	 * @return Returns the createdBy.
	 */
	public String getCreatedBy() {
		return createdBy;
	}
	/**
	 * @param createdBy The createdBy to set.
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	/**
	 * @return Returns the createdOn.
	 */
	public java.util.Date getCreatedOn() {
		return createdOn;
	}
	/**
	 * @param createdOn The createdOn to set.
	 */
	public void setCreatedOn(java.util.Date createdOn) {
		this.createdOn = createdOn;
	}
	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return Returns the groupId.
	 */
	public Integer getGroupId() {
		return groupId;
	}
	/**
	 * @param groupId The groupId to set.
	 */
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return Returns the createdOnstr.
	 */
	public String getCreatedOnstr() {
		return createdOnstr;
	}
	/**
	 * @param createdOnstr The createdOnstr to set.
	 */
	public void setCreatedOnstr(String createdOnstr) {
		this.createdOnstr = createdOnstr;
	}
	/**
	 * @return Returns the divName.
	 */
	public String getDivName() {
		return divName;
	}
	/**
	 * @param divName The divName to set.
	 */
	public void setDivName(String divName) {
		this.divName = divName;
	}
}
