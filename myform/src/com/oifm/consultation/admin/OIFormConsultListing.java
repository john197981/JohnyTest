package com.oifm.consultation.admin;
/*
 * Class Name:
 * Description:
 * 
 * 	Created By			Created/Modified on			Revision				Remarks
 * -----------------------------------------------------------------------------------------------------
 * 	Rajkumar			30/06/2005					Draft					Inital Version
 * 
 * -----------------------------------------------------------------------------------------------------
 */

import java.util.ArrayList;

import com.oifm.base.OIBaseActionForm;

public class OIFormConsultListing extends OIBaseActionForm
{
    private ArrayList arOIFormA1ConsultListing; 

	private String hidSortKey;
	private String hidOrder;
	private int pageNo;
	public String publishedOn;

    public OIFormConsultListing() 
    {
        arOIFormA1ConsultListing = new ArrayList();
    }
    /**
     * @return Returns the arOIFormA1ConsultListing.
     */
    public ArrayList getArOIFormA1ConsultListing() {
        return arOIFormA1ConsultListing;
    }
    public void addArOIFormA1ConsultListing(Object obj)
    {
        arOIFormA1ConsultListing.add(obj);
    }

    /**
     * @param arOIFormA1ConsultListing The arOIFormA1ConsultListing to set.
     */
    public void setArOIFormA1ConsultListing(ArrayList arOIFormA1ConsultListing) {
        this.arOIFormA1ConsultListing = arOIFormA1ConsultListing;
    }

	
	/**
	 * @return Returns the hidSortKey.
	 */
	public String getPublishedOn() {
		return publishedOn;
	}
	/**
	 * @param hidSortKey The hidSortKey to set.
	 */
	public void setPublishedOn(String publishedOn) {
		this.publishedOn = publishedOn;
	}
	
	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	/**
	 * @return Returns the hidSortKey.
	 */
	public String getHidSortKey() {
		return hidSortKey;
	}
	/**
	 * @param hidSortKey The hidSortKey to set.
	 */
	public void setHidSortKey(String hidSortKey) {
		this.hidSortKey = hidSortKey;
	}
    
	/**
	 * @return Returns the strHidOrder.
	 */
	public String getHidOrder() {
		return hidOrder;
	}
	/**
	 * @param strHidOrder The strHidOrder to set.
	 */
	public void setHidOrder(String strHidOrder) {
		this.hidOrder = strHidOrder;
	}


}
