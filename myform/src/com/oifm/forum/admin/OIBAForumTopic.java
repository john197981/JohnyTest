package com.oifm.forum.admin;

import java.util.Date;

public class OIBAForumTopic 
{
    private int boardId;
    private int topicId;
    private String topicName;
    private String modReq;
    private String createdBy;
    private Date createdOn;
    private String topicDesc;
    
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
     * @return Returns the modReq.
     */
    public String getModReq() {
        return modReq;
    }
    /**
     * @param modReq The modReq to set.
     */
    public void setModReq(String modReq) {
        this.modReq = modReq;
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
