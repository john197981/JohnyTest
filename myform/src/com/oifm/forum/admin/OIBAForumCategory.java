package com.oifm.forum.admin;

import java.util.Date;

import com.oifm.base.OIBaseBa;

public class OIBAForumCategory extends OIBaseBa 
{
    private Date createdOn;
    private String createdBy;
    private String name;
    private int categoryID;
    
    /**
     * @return Returns the categoryID.
     */
    public int getCategoryID() {
        return categoryID;
    }
    /**
     * @param categoryID The categoryID to set.
     */
    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
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
}
