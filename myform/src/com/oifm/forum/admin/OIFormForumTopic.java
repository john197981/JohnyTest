package com.oifm.forum.admin;

import java.util.ArrayList;

import com.oifm.base.OIBaseActionForm;

public class OIFormForumTopic extends OIBaseActionForm
{
    private String categoryId;
    private ArrayList arCategoryID;
    private String boardId;
    private ArrayList arBoardId;
    private String topicId;
    private String topicName;
    private String modRequired;
    private String topicDesc;
    
    public OIFormForumTopic()
    {
        arCategoryID = new ArrayList();
        arBoardId = new ArrayList();
    }
    
    /**
     * @return Returns the arBoardId.
     */
    public ArrayList getArBoardId() {
        return arBoardId;
    }
    /**
     * @param arBoardId The arBoardId to set.
     */
    public void setArBoardId(ArrayList arBoardId) {
        this.arBoardId = arBoardId;
    }
    public void addArBoardId(Object obj) {
        this.arBoardId.add(obj);
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
     * @return Returns the modRequired.
     */
    public String getModRequired() {
        return modRequired;
    }
    /**
     * @param modRequired The modRequired to set.
     */
    public void setModRequired(String modRequired) {
        this.modRequired = modRequired;
    }
    /**
     * @return Returns the topicId.
     */
    public String getTopicId() {
        return topicId;
    }
    /**
     * @param topicId The topicId to set.
     */
    public void setTopicId(String topicId) {
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
    /**
     * @return Returns the topicName.
     */
    public String getTopicDesc() {
        return topicDesc;
    }
    /**
     * @param topicName The topicDesc to set.
     */
    public void setTopicDesc(String topicDesc) {
        this.topicDesc = topicDesc;
    }
}
