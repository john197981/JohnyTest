/*
 * Created on Aug 15, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.oifm.codemaster;

import com.oifm.base.OIBaseActionForm;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class OIFormCodeMaster extends OIBaseActionForm {

	
	private String codeId;
    private String type;
    private String value;
    private String description;
    private String obsolete;
    private String hiddenAction;
    private String shortName;
    
    private String strHidType;
    private String strHidCode;
    private String strHidDesc;
    private String strHidObs;
    private String strHidName;

	/**
	 * @return Returns the strHidCode.
	 */
	public String getHidCode() {
		return strHidCode;
	}
	/**
	 * @param strHidCode The strHidCode to set.
	 */
	public void setHidCode(String strHidCode) {
		this.strHidCode = strHidCode;
	}
	/**
	 * @return Returns the strHidDesc.
	 */
	public String getHidDesc() {
		return strHidDesc;
	}
	/**
	 * @param strHidDesc The strHidDesc to set.
	 */
	public void setHidDesc(String strHidDesc) {
		this.strHidDesc = strHidDesc;
	}
	/**
	 * @return Returns the strHidObs.
	 */
	public String getHidObs() {
		return strHidObs;
	}
	/**
	 * @param strHidObs The strHidObs to set.
	 */
	public void setHidObs(String strHidObs) {
		this.strHidObs = strHidObs;
	}
	/**
	 * @return Returns the strHidType.
	 */
	public String getHidType() {
		return strHidType;
	}
	/**
	 * @param strHidType The strHidType to set.
	 */
	public void setHidType(String strHidType) {
		this.strHidType = strHidType;
	}
    /**
     * @return Returns the codeId.
     */
    public String getStrCodeId() {
        return codeId;
    }
    /**
     * @param codeId The codeId to set.
     */
    public void setStrCodeId(String codeId) {
        this.codeId = codeId;
    }
    /**
     * @return Returns the description.
     */
    public String getDescription() {
        return description;
    }
    /**
     * @param description The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * @return Returns the obsolete.
     */
    public String getObsolete() {
        return obsolete;
    }
    /**
     * @param obsolete The obsolete to set.
     */
    public void setObsolete(String obsolete) {
        this.obsolete = obsolete;
    }
    /**
     * @return Returns the type.
     */
    public String getType() {
        return type;
    }
    /**
     * @param type The type to set.
     */
    public void setType(String type) {
        this.type = type;
    }
    /**
     * @return Returns the value.
     */
    public String getValue() {
        return value;
    }
    /**
     * @param value The value to set.
     */
    public void setValue(String value) {
        this.value = value;
    }
    

    public String getHiddenAction()
    {
        return hiddenAction;
    }
    public void setHiddenAction(String hiddenAction)
    {
        this.hiddenAction=hiddenAction;
    }
    
    /**
     * @return Returns the shortName.
     */
    public String getShortName(){
    	return shortName;
    }
    /**
     * @param shortName The shortName to set.
     */
    public void setShortName(String shortName){
    	this.shortName = shortName;
    }
    /**
     * @return Returns the strHidName.
     */
    public String getHidName(){
    	return strHidName;
    }
    /**
     * @param strHidName The strHidName to set.
     */
    public void setHidName(String strHidName){
    	this.strHidName = strHidName;
    }
}
