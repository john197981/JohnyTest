
package com.oifm.common;

//import java.math.BigDecimal;

import org.apache.log4j.Logger;


public class OIPageInfoBean implements java.io.Serializable	
{
    Logger logger = Logger.getLogger(OIPageInfoBean.class.getName());
	private int pageNo;
	private int noOfPages;
	private int recPerPage;
	private int startRec;
	private int endRec;
	private int totalRec;
	private boolean isNextPage;
	private boolean isPrevPage;
	private int noOfLinks;
	private int currLinkStart;
	private int nextSet;
	private int prevSet;
	private boolean isNSet;
	private boolean isPSet;

	public OIPageInfoBean()	
	{
		this.recPerPage = OIApplConstants.RECORDS_PER_PAGE;
		this.pageNo = 1;
		this.startRec = 1;
		this.endRec = this.startRec + this.recPerPage - 1;
	} 
	
	public OIPageInfoBean(int pageNo)	
	{
		this.recPerPage = OIApplConstants.RECORDS_PER_PAGE;
		this.pageNo = (pageNo > 0)?pageNo:1;
		this.startRec = (this.pageNo - 1) * this.recPerPage + 1;
		this.endRec = this.startRec + this.recPerPage - 1;
	}
	
	public OIPageInfoBean(int pageNo, int recPerPage)	
	{
		this.recPerPage = recPerPage;
		this.pageNo = (pageNo > 0)?pageNo:1;
		this.startRec = (this.pageNo - 1) * this.recPerPage + 1;
		this.endRec = this.startRec + this.recPerPage - 1;
	}
	
	public int getEndRec() 
	{
		this.endRec = this.pageNo * this.recPerPage;
		return (this.endRec > this.totalRec)? this.totalRec: this.endRec;
	}
	public boolean isNextPage() 
	{
		return (this.totalRec > this.recPerPage);
	}
	public boolean isPrevPage() 
	{
		return (pageNo > 1);
	}
	public int getNoOfPages() {
		return (int)Math.ceil(1.0*this.totalRec/this.recPerPage);

	}
	public int getPageNo() 
	{
		return pageNo;
	}
	public int getRecPerPage() 
	{
		return recPerPage;
	}
	public int getStartRec() 
	{
		return startRec;
	}
	public int getTotalRec() 
	{
		return totalRec;
	}
	public void setEndRec(int endRec) 
	{
		this.endRec = endRec;
	}
	public void setNextPage(boolean isNextPage) 
	{
		this.isNextPage = isNextPage;
	}
	public void setPrevPage(boolean isPrevPage) 
	{
		this.isPrevPage = isPrevPage;
	}
	public void setNoOfPages(int noOfPages) 
	{
		this.noOfPages = noOfPages;
	}
	public void setPageNo(int pageNo) 
	{
		this.pageNo = pageNo;
	}
	public void setRecPerPage(int recPerPage) 
	{
		this.recPerPage = recPerPage;
	}
	public void setStartRec(int startRec) 
	{
		this.startRec = startRec;
	}
	public void setTotalRec(int totalRec) 
	{
		this.totalRec = totalRec;
	}
	
	public int getCurrLinkStart()
	{
	    noOfLinks=OIApplConstants.NUMBER_OF_LINKS;
	    double temp = OIApplConstants.NUMBER_OF_LINKS;
	    //long totalSets = new BigDecimal(getNoOfPages()/temp).setScale(0,BigDecimal.ROUND_HALF_UP).longValue();
	    long totalSets = (long) Math.ceil(1.0*getNoOfPages()/temp);
	    //(int)Math.ceil(1.0*this.totalRec/this.recPerPage)
	    int currentSet=1;
	    this.currLinkStart = 1;
        if (currentSet<totalSets)
        {
            this.setNSet(true);
            this.setNextSet(currLinkStart + noOfLinks);
        }
        if (pageNo>1)
        {
		    for (int i=2;i<=getNoOfPages();i++)
		    {
		        if(i%noOfLinks==1)
		        {
		            this.currLinkStart=i;
		            currentSet = currentSet + 1;
		            if (currentSet==totalSets)
		            {
		                this.setNSet(false);
		            }
		            if (currentSet<totalSets)
		            {
		                this.setNSet(true);
		                this.setNextSet(i + noOfLinks );
		            }
		            if (currentSet>1)
		            {
		                this.setPSet(true);
		                this.setPrevSet(i - 1);
		            }
		        }
		        if (pageNo==i)
		        {
		            break;
		        }
		    }
        }
	    return currLinkStart;
	}
    /**
     * @return Returns the isNSet.
     */
    public boolean isNSet() {
        return isNSet;
    }
    /**
     * @param isNSet The isNSet to set.
     */
    public void setNSet(boolean isNSet) {
        this.isNSet = isNSet;
    }
    /**
     * @return Returns the isPSet.
     */
    public boolean isPSet() {
        return isPSet;
    }
    /**
     * @param isPSet The isPSet to set.
     */
    public void setPSet(boolean isPSet) {
        this.isPSet = isPSet;
    }
    /**
     * @return Returns the nextSet.
     */
    public int getNextSet() {
        return nextSet;
    }
    /**
     * @param nextSet The nextSet to set.
     */
    public void setNextSet(int nextSet) {
        this.nextSet = nextSet;
    }
    /**
     * @return Returns the noOfLinks.
     */
    public int getNoOfLinks() {
        return noOfLinks;
    }
    /**
     * @param noOfLinks The noOfLinks to set.
     */
    public void setNoOfLinks(int noOfLinks) {
        this.noOfLinks = noOfLinks;
    }
    /**
     * @return Returns the prevSet.
     */
    public int getPrevSet() {
        return prevSet;
    }
    /**
     * @param prevSet The prevSet to set.
     */
    public void setPrevSet(int prevSet) {
        this.prevSet = prevSet;
    }
}
