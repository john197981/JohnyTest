package com.oifm.useradmin;

import com.oifm.base.OIBaseActionForm;
public class OIFormGroups extends OIBaseActionForm {
    private String description;
    private String createdBy;
    private String name;
    private java.util.Date createdOn;
    private Integer groupId;
    private String createdOnstr;
    private String divName;
    private String nextAction;
    private String[] toBeRemoved;
    private String sortKey;
    private String sortOrder;
    
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
	/**
	 * @return Returns the nextAction.
	 */
	public String getNextAction() {
		return nextAction;
	}
	/**
	 * @param nextAction The nextAction to set.
	 */
	public void setNextAction(String nextAction) {
		this.nextAction = nextAction;
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
	 * @return Returns the toBeRemoved.
	 */
	public String[] getToBeRemoved() {
		return toBeRemoved;
	}
	/**
	 * @param toBeRemoved The toBeRemoved to set.
	 */
	public void setToBeRemoved(String[] toBeRemoved) {
		this.toBeRemoved = toBeRemoved;
	}
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
		this.sortKey = sortKey;
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
		this.sortOrder = sortOrder;
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
}
