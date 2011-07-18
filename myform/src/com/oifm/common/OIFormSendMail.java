/*************************************************************************************************
 * File Name		:	OIFormSendMail.java
 * Author			:	SureshKumar.R
 * Description		:   This is the form bean values from the view are populated 			   		
 * Created Date		:	Jul 10 2005
 * Version			:	1.0
 * Copyright 		: Scandent Group
 ************************************************************************************************/
package com.oifm.common;

import com.oifm.base.OIBaseActionForm;

public class OIFormSendMail extends OIBaseActionForm
{
    //Input parameters
    private String id;
    private String SurveyOrCons;
    private String SendOrRemain;  
    
//	This variable is used to store the order of the readUsers whether It is Ascending or Desending
    private String readUsrsOrder;
    
    //This is used to store the ID's of the selected check box for removal 
	private String strHidIdvalues; 
        
    //Output parameters
    private String Subject;
    private String Message;
    
    private String nric;
    private String name;
    private String response;
    private String papers;
    private String Surveys;
    
    private String[] strRemoveId;
    private String strEmail;
    
    private String strHidSortKey;
    private String strHidOrder;
    
    private String NricOrder;
    private String NameOrder;
    private String ResponseOrder;
    private String PapersOrder;
    private String SurveysOrder;
    private String strCaption;
    private String strFlag;
    private String strWinObj;
    
    private String nickname;
	/**
	 * @return Returns the strWinObj.
	 */
	public String getWinObj() {
		return strWinObj;
	}
	/**
	 * @param strWinObj The strWinObj to set.
	 */
	public void setWinObj(String strWinObj) {
		this.strWinObj = strWinObj;
	}
	/**
	 * @return Returns the strFlag.
	 */
	public String getFlag() {
		return strFlag;
	}
	/**
	 * @param strFlag The strFlag to set.
	 */
	public void setFlag(String strFlag) {
		this.strFlag = strFlag;
	}
    /**
	 * @return Returns the strCaption.
	 */
	public String getCaption() {
		return strCaption;
	}
	/**
	 * @param strCaption The strCaption to set.
	 */
	public void setCaption(String strCaption) {
		this.strCaption = strCaption;
	}
    
	/**
	 * @return Returns the hidSortKey.
	 */
	public String getHidSortKey() {
		return strHidSortKey;
	}
	/**
	 * @param hidSortKey The hidSortKey to set.
	 */
	public void setHidSortKey(String hidSortKey) {
		this.strHidSortKey = hidSortKey;
	}
    
	/**
	 * @return Returns the strEmail.
	 */
	public String getEmail() {
		return strEmail;
	}
	/**
	 * @param strEmail The strEmail to set.
	 */
	public void setEmail(String strEmail) {
		this.strEmail = strEmail;
	}
	/**
	 * @return Returns the strRemoveId.
	 */
	public String[] getRemoveId() {
		return strRemoveId;
	}
	/**
	 * @param strRemoveId The strRemoveId to set.
	 */
	public void setRemoveId(String[] strRemoveId) {
		this.strRemoveId = strRemoveId;
	}
    /**
     * @return Returns the id.
     */
    public String getId() {
        return id;
    }
    /**
     * @param id The id to set.
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * @return Returns the message.
     */
    public String getMessage() {
        return Message;
    }
    /**
     * @param message The message to set.
     */
    public void setMessage(String message) {
        Message = message;
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
     * @return Returns the sendOrRemain.
     */
    public String getSendOrRemain() {
        return SendOrRemain;
    }
    /**
     * @param sendOrRemain The sendOrRemain to set.
     */
    public void setSendOrRemain(String sendOrRemain) {
        SendOrRemain = sendOrRemain;
    }
    /**
     * @return Returns the subject.
     */
    public String getSubject() {
        return Subject;
    }
    /**
     * @param subject The subject to set.
     */
    public void setSubject(String subject) {
        Subject = subject;
    }
    /**
     * @return Returns the surveyOrCons.
     */
    public String getSurveyOrCons() {
        return SurveyOrCons;
    }
    /**
     * @param surveyOrCons The surveyOrCons to set.
     */
    public void setSurveyOrCons(String surveyOrCons) {
        SurveyOrCons = surveyOrCons;
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
	public String getStrHidIdvalues() {
		return strHidIdvalues;
	}

	/**
	 * @param string
	 */
	public void setStrHidIdvalues(String string) {
		strHidIdvalues = string;
	}

/**
 * @return
 */
public String getReadUsrsOrder() {
	return readUsrsOrder;
}

/**
 * @param string
 */
public void setReadUsrsOrder(String string) {
	readUsrsOrder = string;
}

	/**
	 * @return Returns the strHidOrder.
	 */
	public String getHidOrder() {
		return strHidOrder;
	}
	/**
	 * @param strHidOrder The strHidOrder to set.
	 */
	public void setHidOrder(String strHidOrder) {
		this.strHidOrder = strHidOrder;
	}
	/**
	 * @return Returns the nameOrder.
	 */
	public String getNameOrder() {
		return NameOrder;
	}
	/**
	 * @param nameOrder The nameOrder to set.
	 */
	public void setNameOrder(String nameOrder) {
		NameOrder = nameOrder;
	}
	/**
	 * @return Returns the nricOrder.
	 */
	public String getNricOrder() {
		return NricOrder;
	}
	/**
	 * @param nricOrder The nricOrder to set.
	 */
	public void setNricOrder(String nricOrder) {
		NricOrder = nricOrder;
	}
	/**
	 * @return Returns the papersOrder.
	 */
	public String getPapersOrder() {
		return PapersOrder;
	}
	/**
	 * @param papersOrder The papersOrder to set.
	 */
	public void setPapersOrder(String papersOrder) {
		PapersOrder = papersOrder;
	}
	/**
	 * @return Returns the responseOrder.
	 */
	public String getResponseOrder() {
		return ResponseOrder;
	}
	/**
	 * @param responseOrder The responseOrder to set.
	 */
	public void setResponseOrder(String responseOrder) {
		ResponseOrder = responseOrder;
	}
	/**
	 * @return Returns the surveysOrder.
	 */
	public String getSurveysOrder() {
		return SurveysOrder;
	}
	/**
	 * @param surveysOrder The surveysOrder to set.
	 */
	public void setSurveysOrder(String surveysOrder) {
		SurveysOrder = surveysOrder;
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
