package com.oifm.forum.admin;

import java.util.Date;

public class OIBVForumList 
{
    private String categoryName;
    private int categoryId;
    private String boardName;
    private int boardId;
    private String division;
    private int topicId;
    private String topicName;
    private Date createdOn;
    private String linkFlag;
    
    /**
     * @return Returns the linkFlag.
     */
    public String getLinkFlag() {
        return linkFlag;
    }
    /**
     * @param linkFlag The linkFlag to set.
     */
    public void setLinkFlag(String linkFlag) {
        this.linkFlag = linkFlag;
    }
    /**
     * @return Returns the boardId.
     */
    public int getBoardId() {
        return boardId;
    }
    /**
     * @param boardId The boardId to set.
     */
    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }
    /**
     * @return Returns the boardName.
     */
    public String getBoardName() {
        return boardName;
    }
    /**
     * @param boardName The boardName to set.
     */
    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }
    /**
     * @return Returns the categoryId.
     */
    public int getCategoryId() {
        return categoryId;
    }
    /**
     * @param categoryId The categoryId to set.
     */
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    /**
     * @return Returns the categoryName.
     */
    public String getCategoryName() {
        return categoryName;
    }
    /**
     * @param categoryName The categoryName to set.
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    /**
     * @return Returns the createdOn.
     */
    public Date getCreatedOn() {
        return createdOn;
    }
    /**
     * @param createdOn The createdOn to set.
     */
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
    /**
     * @return Returns the division.
     */
    public String getDivision() {
        return division;
    }
    /**
     * @param division The division to set.
     */
    public void setDivision(String division) {
        this.division = division;
    }
    /**
     * @return Returns the topicId.
     */
    public int getTopicId() {
        return topicId;
    }
    /**
     * @param topicId The topicId to set.
     */
    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }
    /**
     * @return Returns the topicName.
     */
    public String getTopicName() {
        return topicName;
    }
    /**
     * @param topicName The topicName to set.
     */
    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }
}
