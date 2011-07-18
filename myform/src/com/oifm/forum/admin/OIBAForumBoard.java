package com.oifm.forum.admin;

import java.util.Date;

public class OIBAForumBoard 
{
    private int boardId;
    private int categoryId;
    private int divisionId;
    private String boardName;
    private String createdBy;
    private Date createdOn;
    
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
     * @return Returns the divisionId.
     */
    public int getDivisionId() {
        return divisionId;
    }
    /**
     * @param divisionId The divisionId to set.
     */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }
}
