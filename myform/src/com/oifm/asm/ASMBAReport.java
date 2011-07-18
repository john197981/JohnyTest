/*********************************ASMBAReport.java******************* 
 * Title 		: ASMBAReport
 * Description 	: This class is the BA Class for ASM Report Page. 
 * Author 		: Anbalagan Varadharajan 
 * Version No 	: 1.0 
 * Date Created : 23 - Dec -2005
 * Copyright 	: Scandent Group
 ******************************************************************************/

package com.oifm.asm;

import java.util.ArrayList;
 

public class ASMBAReport extends ASMBVReport   
{
	private ASMBVReport objBV[] = null;
	private int iMonths =0;
	private int iTotRecLtr = 0;
	private String strSortBy =null;
	private ArrayList alDivision =null;
	private ArrayList alCategory =null;
	private int iLink =0;
	
	/**
	 * @return Returns the objBV.
	 */
	public ASMBVReport[] getObjBV() {
		return objBV;
	}
	/**
	 * @param objBV The objBV to set.
	 */
	public void setObjBV(ASMBVReport[] objBV) {
		this.objBV = objBV;
	}

	/**
	 * @return Returns the iTotRecLtr.
	 */
	public int getTotRecLtr() {
		return iTotRecLtr;
	}
	/**
	 * @param totRecLtr The iTotRecLtr to set.
	 */
	public void setTotRecLtr(int totRecLtr) {
		iTotRecLtr = totRecLtr;
	}

	/**
	 * @return Returns the iLink.
	 */
	public int getHidLink() {
		return iLink;
	}
	/**
	 * @param link The iLink to set.
	 */
	public void setHidLink(int link) {
		iLink = link;
	}
	
	/**
	 * @return Returns the iMonths.
	 */
	public int getHidMonths() {
		return iMonths;
	}
	/**
	 * @param months The iMonths to set.
	 */
	public void setHidMonths(int months) {
		iMonths = months;
	}
	/**
	 * @return Returns the hidSortBy.
	 */
	public String getHidSortBy() {
		return strSortBy;
	}
	/**
	 * @param hidSortBy The hidSortBy to set.
	 */
	public void setHidSortBy(String strSortBy) {
		this.strSortBy = strSortBy;
	}	

	/**
	 * @return Returns the alDivision.
	 */
	public ArrayList getAlDivision() {
		return alDivision;
	}
	/**
	 * @param alDivision The alDivision to set.
	 */
	public void setAlDivision(ArrayList alDivision) {
		this.alDivision = alDivision;
	}
	/**
	 * @return the alCategory
	 */
	public ArrayList getAlCategory()
	{
		return alCategory;
	}
	/**
	 * @param alCategory the alCategory to set
	 */
	public void setAlCategory(ArrayList alCategory)
	{
		this.alCategory = alCategory;
	}
	
}
