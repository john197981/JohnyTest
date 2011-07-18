package com.oifm.forum.admin;

import java.util.ArrayList;

public class OIFormForumListingHelper2 
{
    private String boardId;
    private String boardName;
    private String division;
    private String linkFlag;
    private ArrayList arOIFormForumListingHelper3;
    
    public OIFormForumListingHelper2()
    {
        arOIFormForumListingHelper3 = new ArrayList();
    }
    
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
     * @return Returns the arOIFormForumListingHelper3.
     */
    public ArrayList getArOIFormForumListingHelper3() {
        return arOIFormForumListingHelper3;
    }
    /**
     * @param arOIFormForumListingHelper3 The arOIFormForumListingHelper3 to set.
     */
    public void setArOIFormForumListingHelper3(
            ArrayList arOIFormForumListingHelper3) {
        this.arOIFormForumListingHelper3 = arOIFormForumListingHelper3;
    }

    public void addArOIFormForumListingHelper3(Object obj) 
    {
        this.arOIFormForumListingHelper3.add(obj);
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
}
