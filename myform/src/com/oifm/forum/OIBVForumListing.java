package com.oifm.forum;

public class OIBVForumListing 
{
    private String level;
    private String category;
    private String categoryId;
    private String boardName;
    private String boardId;
    private String topicName;
    private String topicId;
    private String threadName;
    private String threadId;
    private String nickName;
    private String postedBy;
    private String createdBy;
    private String createdOn;
    private String lastPostOn;
    private String noThreads;
    private String numberOfPosts;
	private int totalPosts=0;
    private String icon="";

	/**
	 * @return Returns the icon.
	 */
	public String getIcon() {
		return icon;
	}
	/**
	 * @param icon The icon to set.
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

    /**
     * @return Returns the numberOfPosts.
     */
    public String getNumberOfPosts() {
        return numberOfPosts;
    }
    /**
     * @param numberOfPosts The numberOfPosts to set.
     */
    public void setNumberOfPosts(String numberOfPosts) {
        this.numberOfPosts = numberOfPosts;
    }
    /**
     * @return Returns the createdOn.
     */
    public String getCreatedOn() {
        return createdOn;
    }
    /**
     * @param createdOn The createdOn to set.
     */
    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }
    /**
     * @return Returns the level.
     */
    public String getLevel() {
        return level;
    }
    /**
     * @param level The level to set.
     */
    public void setLevel(String level) {
        this.level = level;
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
     * @return Returns the category.
     */
    public String getCategory() {
        return category;
    }
    /**
     * @param category The category to set.
     */
    public void setCategory(String category) {
        this.category = category;
    }
    /**
     * @return Returns the lastPostOn.
     */
    public String getLastPostOn() {
        return lastPostOn;
    }
    /**
     * @param lastPostOn The lastPostOn to set.
     */
    public void setLastPostOn(String lastPostOn) {
        this.lastPostOn = lastPostOn;
    }
    /**
     * @return Returns the nickName.
     */
    public String getNickName() {
        return nickName;
    }
    /**
     * @param nickName The nickName to set.
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    /**
     * @return Returns the noThreads.
     */
    public String getNoThreads() {
        return noThreads;
    }
    /**
     * @param noThreads The noThreads to set.
     */
    public void setNoThreads(String noThreads) {
        this.noThreads = noThreads;
    }
    /**
     * @return Returns the postedBy.
     */
    public String getPostedBy() {
        return postedBy;
    }
    /**
     * @param postedBy The postedBy to set.
     */
    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }
    /**
     * @return Returns the threadId.
     */
    public String getThreadId() {
        return threadId;
    }
    /**
     * @param threadId The threadId to set.
     */
    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }
    /**
     * @return Returns the threadName.
     */
    public String getThreadName() {
        return threadName;
    }
    /**
     * @param threadName The threadName to set.
     */
    public void setThreadName(String threadName) {
        this.threadName = threadName;
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
	 * @return Returns the totalPosts.
	 */
	public int getTotalPosts() {
		return totalPosts;
	}
	/**
	 * @param totalPosts The totalPosts to set.
	 */
	public void setTotalPosts(int totalPosts) {
		this.totalPosts = totalPosts;
	}

}
