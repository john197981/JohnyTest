package com.oifm.common;

import com.oifm.base.OIBaseActionForm;

public class OIFormAlertFriends extends OIBaseActionForm
{
    private String emailId;
    private String to;
    private String subject;
    private String content;
    private String iD;
    private String url;
    
    /**
     * @return Returns the content.
     */
    public String getContent() {
        return content;
    }
    /**
     * @param content The content to set.
     */
    public void setContent(String content) {
        this.content = content;
    }
    /**
     * @return Returns the emailId.
     */
    public String getEmailId() {
        return emailId;
    }
    /**
     * @param emailId The emailId to set.
     */
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
    /**
     * @return Returns the subject.
     */
    public String getSubject() {
        return subject;
    }
    /**
     * @param subject The subject to set.
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }
    /**
     * @return Returns the to.
     */
    public String getTo() {
        return to;
    }
    /**
     * @param to The to to set.
     */
    public void setTo(String to) {
        this.to = to;
    }
    /**
     * @return Returns the iD.
     */
    public String getID() {
        return iD;
    }
    /**
     * @param id The iD to set.
     */
    public void setID(String id) {
        iD = id;
    }
    /**
     * @return Returns the url.
     */
    public String getUrl() {
        return url;
    }
    /**
     * @param url The url to set.
     */
    public void setUrl(String url) {
        this.url = url;
    }
}
