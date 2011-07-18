package com.oifm.forum.admin;

import java.util.ArrayList;

import com.oifm.base.OIBaseActionForm;

public class OIFormForumListing extends OIBaseActionForm
{
    ArrayList arOIFormForumListingHelper1;
    
    public OIFormForumListing() 
    {
        arOIFormForumListingHelper1 = new ArrayList();
    }
    
    /**
     * @return Returns the arOIFormForumListingHelper1.
     */
    public ArrayList getArOIFormForumListingHelper1() 
    {
        return arOIFormForumListingHelper1;
    }
    /**
     * @param arOIFormForumListingHelper1 The arOIFormForumListingHelper1 to set.
     */
    public void setArOIFormForumListingHelper1(ArrayList arOIFormForumListingHelper1) 
    {
        this.arOIFormForumListingHelper1 = arOIFormForumListingHelper1;
    }
    
    public void addArOIFormForumListingHelper1(Object obj) 
    {
        this.arOIFormForumListingHelper1.add(obj);
    }    
}
