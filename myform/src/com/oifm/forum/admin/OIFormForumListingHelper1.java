package com.oifm.forum.admin;

import java.util.ArrayList;

public class OIFormForumListingHelper1 
{
    private String categoryId;
    private String categoryName;
    private ArrayList arOIFormForumListingHelper2;
    
    public OIFormForumListingHelper1()
    {
        arOIFormForumListingHelper2 = new ArrayList();
    }
    /**
     * @return Returns the arOIFormForumListingHelper2.
     */
    public ArrayList getArOIFormForumListingHelper2() {
        return arOIFormForumListingHelper2;
    }
    /**
     * @param arOIFormForumListingHelper2 The arOIFormForumListingHelper2 to set.
     */
    public void setArOIFormForumListingHelper2(
            ArrayList arOIFormForumListingHelper2) {
        this.arOIFormForumListingHelper2 = arOIFormForumListingHelper2;
    }
    public void addArOIFormForumListingHelper2(Object obj) 
    {
        this.arOIFormForumListingHelper2.add(obj);
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
}
