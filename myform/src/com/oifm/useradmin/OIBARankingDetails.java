
package com.oifm.useradmin;

public class OIBARankingDetails 
{
	 
	private String actionName;
	private String actionTime;
	
	public OIBARankingDetails() {}
	 /**
     * @return Returns the birthDt.
     */
    public String getActionName() {
        return actionName;
    }
    /**
     * @param birthDt The birthDt to set.
     */
    public void setActionName(String actionName) {
        this.actionName = actionName;
    }
    /**
     * @return Returns the createdOn.
     */
    public String getActionTime() {
        return actionTime;
    }
    /**
     * @param createdOn The createdOn to set.
     */
    public void setActionTime(String actionTime) {
        this.actionTime = actionTime;
    }
}