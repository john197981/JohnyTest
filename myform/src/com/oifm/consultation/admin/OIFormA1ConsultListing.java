package com.oifm.consultation.admin;

import java.util.ArrayList;

public class OIFormA1ConsultListing
{
    private String categoryName;
    private String categoryId;
    public ArrayList arOIFormA2ConsultListing; 

    public OIFormA1ConsultListing() 
    {
        arOIFormA2ConsultListing = new ArrayList();
    }
    /**
     * @return Returns the arOIFormAConsultListing.
     */
    public ArrayList getArOIFormA2ConsultListing() {
        return arOIFormA2ConsultListing;
    }
    /**
     * @param arOIFormAConsultListing The arOIFormAConsultListing to set.
     */
    public void setArOIFormA2ConsultListing(ArrayList arOIFormA2ConsultListing) {
        this.arOIFormA2ConsultListing = arOIFormA2ConsultListing;
    }
    
    public void addArOIFormA2ConsultListing(Object obj)
    {
        arOIFormA2ConsultListing.add(obj);
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
