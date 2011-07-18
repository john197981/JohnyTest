package com.oifm.forum.admin;

public class OIBAForumMembers 
{
    private int memberId;
    private int boardId;
    private String userId;
    private String userName;
    private String roleName;
    private String fnFlag;
    
    /**
     * @return Returns the fnFlag.
     */
    public String getFnFlag() {
        return fnFlag;
    }
    /**
     * @param fnFlag The fnFlag to set.
     */
    public void setFnFlag(String fnFlag) {
        this.fnFlag = fnFlag;
    }
    /**
     * @return Returns the roleName.
     */
    public String getRoleName() {
        return roleName;
    }
    /**
     * @param roleName The roleName to set.
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    /**
     * @return Returns the userName.
     */
    public String getUserName() {
        return userName;
    }
    /**
     * @param userName The userName to set.
     */
    public void setUserName(String userName) {
        this.userName = userName;
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
     * @return Returns the memberId.
     */
    public int getMemberId() {
        return memberId;
    }
    /**
     * @param memberId The memberId to set.
     */
    public void setMemberId(int memberId) {
        this.memberId = memberId;
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
