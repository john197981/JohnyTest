package com.oifm.consultation;

import java.util.ArrayList;

public class OIFormConsultListingWebHelper1 
{
    private String categoryId;
    private String categoryName;
    ArrayList arOIFormConsultListingWebHelper2;

    /**
     * @return Returns the arOIFormConsultListingWebHelper2.
     */
    public ArrayList getArOIFormConsultListingWebHelper2() {
        return arOIFormConsultListingWebHelper2;
    }
    /**
     * @param arOIFormConsultListingWebHelper2 The arOIFormConsultListingWebHelper2 to set.
     */
    public void setArOIFormConsultListingWebHelper2(
            ArrayList arOIFormConsultListingWebHelper2) {
        this.arOIFormConsultListingWebHelper2 = arOIFormConsultListingWebHelper2;
    }
    public void addArOIFormConsultListingWebHelper2(Object obj) 
    {
        this.arOIFormConsultListingWebHelper2.add(obj);
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
    
    public OIFormConsultListingWebHelper1()
    {
        arOIFormConsultListingWebHelper2 = new ArrayList();
    }
}
