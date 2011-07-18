package com.oifm.forum.admin;

import java.util.ArrayList;

import com.oifm.base.OIBaseActionForm;

public class OIFormForumBoard extends OIBaseActionForm
{
    private String categoryId;
    private ArrayList arCategoryID;
    private String divisionId;
    private ArrayList arDivisionId;
    private String boardId;
    private String boardName;
    private ArrayList arUserId;

    public OIFormForumBoard()
    {
        arUserId = new ArrayList();
        arCategoryID = new ArrayList();
        arDivisionId = new ArrayList();
    }
    
    /**
     * @return Returns the arUserId.
     */
    public ArrayList getArUserId() {
        return arUserId;
    }
    /**
     * @param arUserId The arUserId to set.
     */
    public void setArUserId(ArrayList arUserId) {
        this.arUserId = arUserId;
    }
    public void addArUserId(Object obj) {
        this.arUserId.add(obj);
    }
    /**
     * @return Returns the arCategoryID.
     */
    public ArrayList getArCategoryID() {
        return arCategoryID;
    }
    /**
     * @param arCategoryID The arCategoryID to set.
     */
    public void setArCategoryID(ArrayList arCategoryID) {
        this.arCategoryID = arCategoryID;
    }

    public void addArCategoryID(Object obj) {
        this.arCategoryID.add(obj);
    }
    /**
     * @return Returns the arDivisionId.
     */
    public ArrayList getArDivisionId() {
        return arDivisionId;
    }
    /**
     * @param arDivisionId The arDivisionId to set.
     */
    public void setArDivisionId(ArrayList arDivisionId) {
        this.arDivisionId = arDivisionId;
    }

    public void addArDivisionId(Object obj) {
        this.arDivisionId.add(obj);
    }
    /**
     * @return Returns the boardId.
     */
    public String getBoardId() {
        return boardId;
    }
    /**
     * @param boardId The boardId to set.
     */
    public void setBoardId(String boardId) {
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
    public String getCategoryId() {
        return categoryId;
    }
    /**
     * @param categoryId The categoryId to set.
     */
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
    /**
     * @return Returns the divisionId.
     */
    public String getDivisionId() {
        return divisionId;
    }
    /**
     * @param divisionId The divisionId to set.
     */
    public void setDivisionId(String divisionId) {
        this.divisionId = divisionId;
    }
}
