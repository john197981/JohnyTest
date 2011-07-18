/*
 * Created on Jul 1, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.oifm.common;

import java.io.Serializable;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class OIBASendMail  implements Serializable {
    
    private String nric;
    private String name;
	
    private String response;
    private String papers;
    private String Surveys;    
    
    //This is the Id which will be used to store the Id values which can be used for removing the Selected Users from the database 
	private String SurOrConId;
    
	private String Subject;
	private String Message;
    
	private String strChk;
	
	private String strId;
	
	private String strDescription;
	private String strStartDate;
	private String strEndDate;
	private String nickname;
	
	
	/**
	 * @return Returns the strId.
	 */
	public String getId() {
		return strId;
	}
	/**
	 * @param strId The strId to set.
	 */
	public void setId(String strId) {
		this.strId = strId;
	}
	
	
	
	
	/**
	 * @return Returns the strDescription.
	 */
	public String getDescription() {
		return strDescription;
	}
	/**
	 * @param strDescription The strDescription to set.
	 */
	public void setDescription(String strDescription) {
		this.strDescription = strDescription;
	}
	/**
	 * @return Returns the strEndDate.
	 */
	public String getEndDate() {
		return strEndDate;
	}
	/**
	 * @param strEndDate The strEndDate to set.
	 */
	public void setEndDate(String strEndDate) {
		this.strEndDate = strEndDate;
	}
	
	/**
	 * @return Returns the strStartDate.
	 */
	public String getStartDate() {
		return strStartDate;
	}
	/**
	 * @param strStartDate The strStartDate to set.
	 */
	public void setStartDate(String strStartDate) {
		this.strStartDate = strStartDate;
	}
	

	/**
	 * @return Returns the strChk.
	 */
	public String getChk() {
		return strChk;
	}
	/**
	 * @param strChk The strChk to set.
	 */
	public void setChk(String strChk) {
		this.strChk = strChk;
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
    /**
     * @return Returns the nric.
     */
    public String getNric() {
        return nric;
    }
    /**
     * @param nric The nric to set.
     */
    public void setNric(String nric) {
        this.nric = nric;
    }
    /**
     * @return Returns the papers.
     */
    public String getPapers() {
        return papers;
    }
    /**
     * @param papers The papers to set.
     */
    public void setPapers(String papers) {
        this.papers = papers;
    }
    /**
     * @return Returns the response.
     */
    public String getResponse() {
        return response;
    }
    /**
     * @param response The response to set.
     */
    public void setResponse(String response) {
        this.response = response;
    }
    /**
     * @return Returns the surveys.
     */
    public String getSurveys() {
        return Surveys;
    }
    /**
     * @param surveys The surveys to set.
     */
    public void setSurveys(String surveys) {
        Surveys = surveys;
    }
	/**
	 * @return
	 */
	public String getMessage() {
		return Message;
	}

	/**
	 * @return
	 */
	public String getSubject() {
		return Subject;
	}

	/**
	 * @param string
	 */
	public void setMessage(String string) {
		Message = string;
	}

	/**
	 * @param string
	 */
	public void setSubject(String string) {
		Subject = string;
	}

	

	/**
	 * @return
	 */
	public String getSurOrConId() {
		return SurOrConId;
	}

	/**
	 * @param string
	 */
	public void setSurOrConId(String string) {
		SurOrConId = string;
	}
	/**
	 * @return the nickname
	 */
	public String getNickname()
	{
		return nickname;
	}
	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}

}
