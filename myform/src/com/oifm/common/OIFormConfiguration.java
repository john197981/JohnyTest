package com.oifm.common;

import java.util.ArrayList;

import com.oifm.base.OIBaseActionForm;

public class OIFormConfiguration extends OIBaseActionForm 
{
    private String sessionTimeout;
    private String forumArchivePeriod;
    private String surveyArchivePeriod;
    private String attachmentSize;
    private String paperArchivePeriod;
    private String noPostingForHotTopics;
    private String categorizeThread;
    private String noTopicToShowBookmark;
    private String personalMessage;
    private String alertAdmin;
    private String noOfDaysToConsiderLatestTopics;
    private String sendRemiders;
    private String homeFrmDt;
    private String homeToDt;
    private String forumFrmDt;
    private String forumToDt;
    private ArrayList arMessages;

    private String homeAnnouncement;
    private String homeAnnouncementTag;
    private String forumAnnouncement;
    private String forumAnnouncementTag;
    private String siteDisclaimer;
    private String siteDisclaimerTag;
    private String seniorMgmntWelcome;
    private String seniorMgmntWelcomeTag;
    private String submitViewWelcome;
    private String submitViewWelcomeTag;
    private String prevRepWelcome;
    private String prevRepWelcomeTag;
    private String aboutWelcome;
    private String aboutWelcomeTag;
    private String editorWelcome;
    private String editorWelcomeTag;

    public OIFormConfiguration()
    {
    	arMessages = new ArrayList();
    }
    
    /**
	 * @return Returns the noteMessage.
	 */
	public String getNoteMessage() {
		return noteMessage;
	}
	/**
	 * @param noteMessage The noteMessage to set.
	 */
	public void setNoteMessage(String noteMessage) {
		this.noteMessage = noteMessage;
	}
	/**
	 * @return Returns the remainderMessage.
	 */
	public String getRemainderMessage() {
		return remainderMessage;
	}
	/**
	 * @param remainderMessage The remainderMessage to set.
	 */
	public void setRemainderMessage(String remainderMessage) {
		this.remainderMessage = remainderMessage;
	}
    private String noteMessage;
    private String remainderMessage;
    
    /**
     * @return Returns the alertAdmin.
     */
    public String getAlertAdmin() {
        return alertAdmin;
    }
    /**
     * @param alertAdmin The alertAdmin to set.
     */
    public void setAlertAdmin(String alertAdmin) {
        this.alertAdmin = alertAdmin;
    }
    /**
     * @return Returns the attachmentSize.
     */
    public String getAttachmentSize() {
        return attachmentSize;
    }
    /**
     * @param attachmentSize The attachmentSize to set.
     */
    public void setAttachmentSize(String attachmentSize) {
        this.attachmentSize = attachmentSize;
    }
    /**
     * @return Returns the categorizeThread.
     */
    public String getCategorizeThread() {
        return categorizeThread;
    }
    /**
     * @param categorizeThread The categorizeThread to set.
     */
    public void setCategorizeThread(String categorizeThread) {
        this.categorizeThread = categorizeThread;
    }
    /**
     * @return Returns the forumAnnouncement.
     */
    public String getForumAnnouncement() {
        return forumAnnouncement;
    }
    /**
     * @param forumAnnouncement The forumAnnouncement to set.
     */
    public void setForumAnnouncement(String forumAnnouncement) {
        this.forumAnnouncement = forumAnnouncement;
    }
    /**
     * @return Returns the forumArchivePeriod.
     */
    public String getForumArchivePeriod() {
        return forumArchivePeriod;
    }
    /**
     * @param forumArchivePeriod The forumArchivePeriod to set.
     */
    public void setForumArchivePeriod(String forumArchivePeriod) {
        this.forumArchivePeriod = forumArchivePeriod;
    }
    /**
     * @return Returns the forumFrmDt.
     */
    public String getForumFrmDt() {
        return forumFrmDt;
    }
    /**
     * @param forumFrmDt The forumFrmDt to set.
     */
    public void setForumFrmDt(String forumFrmDt) {
        this.forumFrmDt = forumFrmDt;
    }
    /**
     * @return Returns the forumToDt.
     */
    public String getForumToDt() {
        return forumToDt;
    }
    /**
     * @param forumToDt The forumToDt to set.
     */
    public void setForumToDt(String forumToDt) {
        this.forumToDt = forumToDt;
    }
    /**
     * @return Returns the homeAnnouncement.
     */
    public String getHomeAnnouncement() {
        return homeAnnouncement;
    }
    /**
     * @param homeAnnouncement The homeAnnouncement to set.
     */
    public void setHomeAnnouncement(String homeAnnouncement) {
        this.homeAnnouncement = homeAnnouncement;
    }
    /**
     * @return Returns the homeFrmDt.
     */
    public String getHomeFrmDt() {
        return homeFrmDt;
    }
    /**
     * @param homeFrmDt The homeFrmDt to set.
     */
    public void setHomeFrmDt(String homeFrmDt) {
        this.homeFrmDt = homeFrmDt;
    }
    /**
     * @return Returns the homeToDt.
     */
    public String getHomeToDt() {
        return homeToDt;
    }
    /**
     * @param homeToDt The homeToDt to set.
     */
    public void setHomeToDt(String homeToDt) {
        this.homeToDt = homeToDt;
    }
    /**
     * @return Returns the noOfDaysToConsiderLatestTopics.
     */
    public String getNoOfDaysToConsiderLatestTopics() {
        return noOfDaysToConsiderLatestTopics;
    }
    /**
     * @param noOfDaysToConsiderLatestTopics The noOfDaysToConsiderLatestTopics to set.
     */
    public void setNoOfDaysToConsiderLatestTopics(
            String noOfDaysToConsiderLatestTopics) {
        this.noOfDaysToConsiderLatestTopics = noOfDaysToConsiderLatestTopics;
    }
    /**
     * @return Returns the noPostingForHotTopics.
     */
    public String getNoPostingForHotTopics() {
        return noPostingForHotTopics;
    }
    /**
     * @param noPostingForHotTopics The noPostingForHotTopics to set.
     */
    public void setNoPostingForHotTopics(String noPostingForHotTopics) {
        this.noPostingForHotTopics = noPostingForHotTopics;
    }
    /**
     * @return Returns the noTopicToShowBookmark.
     */
    public String getNoTopicToShowBookmark() {
        return noTopicToShowBookmark;
    }
    /**
     * @param noTopicToShowBookmark The noTopicToShowBookmark to set.
     */
    public void setNoTopicToShowBookmark(String noTopicToShowBookmark) {
        this.noTopicToShowBookmark = noTopicToShowBookmark;
    }
    /**
     * @return Returns the paperArchivePeriod.
     */
    public String getPaperArchivePeriod() {
        return paperArchivePeriod;
    }
    /**
     * @param paperArchivePeriod The paperArchivePeriod to set.
     */
    public void setPaperArchivePeriod(String paperArchivePeriod) {
        this.paperArchivePeriod = paperArchivePeriod;
    }
    /**
     * @return Returns the personalMessage.
     */
    public String getPersonalMessage() {
        return personalMessage;
    }
    /**
     * @param personalMessage The personalMessage to set.
     */
    public void setPersonalMessage(String personalMessage) {
        this.personalMessage = personalMessage;
    }
    /**
     * @return Returns the sendRemiders.
     */
    public String getSendRemiders() {
        return sendRemiders;
    }
    /**
     * @param sendRemiders The sendRemiders to set.
     */
    public void setSendRemiders(String sendRemiders) {
        this.sendRemiders = sendRemiders;
    }
    /**
     * @return Returns the sessionTimeout.
     */
    public String getSessionTimeout() {
        return sessionTimeout;
    }
    /**
     * @param sessionTimeout The sessionTimeout to set.
     */
    public void setSessionTimeout(String sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }
    /**
     * @return Returns the siteDisclaimer.
     */
    public String getSiteDisclaimer() {
        return siteDisclaimer;
    }
    /**
     * @param siteDisclaimer The siteDisclaimer to set.
     */
    public void setSiteDisclaimer(String siteDisclaimer) {
        this.siteDisclaimer = siteDisclaimer;
    }
    /**
     * @return Returns the surveyArchivePeriod.
     */
    public String getSurveyArchivePeriod() {
        return surveyArchivePeriod;
    }
    /**
     * @param surveyArchivePeriod The surveyArchivePeriod to set.
     */
    public void setSurveyArchivePeriod(String surveyArchivePeriod) {
        this.surveyArchivePeriod = surveyArchivePeriod;
    }
	/**
	 * @return Returns the seniorMgmntWelcome.
	 */
	public String getSeniorMgmntWelcome() {
		return seniorMgmntWelcome;
	}
	/**
	 * @param seniorMgmntWelcome The seniorMgmntWelcome to set.
	 */
	public void setSeniorMgmntWelcome(String seniorMgmntWelcome) {
		this.seniorMgmntWelcome = seniorMgmntWelcome;
	}
	/**
	 * @return Returns the aboutWelcome.
	 */
	public String getAboutWelcome() {
		return aboutWelcome;
	}
	/**
	 * @param aboutWelcome The aboutWelcome to set.
	 */
	public void setAboutWelcome(String aboutWelcome) {
		this.aboutWelcome = aboutWelcome;
	}
	/**
	 * @return Returns the editorWelcome.
	 */
	public String getEditorWelcome() {
		return editorWelcome;
	}
	/**
	 * @param editorWelcome The editorWelcome to set.
	 */
	public void setEditorWelcome(String editorWelcome) {
		this.editorWelcome = editorWelcome;
	}
	/**
	 * @return Returns the prevRepWelcome.
	 */
	public String getPrevRepWelcome() {
		return prevRepWelcome;
	}
	/**
	 * @param prevRepWelcome The prevRepWelcome to set.
	 */
	public void setPrevRepWelcome(String prevRepWelcome) {
		this.prevRepWelcome = prevRepWelcome;
	}
	/**
	 * @return Returns the submitViewWelcome.
	 */
	public String getSubmitViewWelcome() {
		return submitViewWelcome;
	}
	/**
	 * @param submitViewWelcome The submitViewWelcome to set.
	 */
	public void setSubmitViewWelcome(String submitViewWelcome) {
		this.submitViewWelcome = submitViewWelcome;
	}
	/**
	 * @return Returns the arMessages.
	 */
	public ArrayList getArMessages() {
		return arMessages;
	}
	/**
	 * @param arMessages The arMessages to set.
	 */
	public void setArMessages(ArrayList arMessages) {
		this.arMessages = arMessages;
	}

	public void addArMessages(OIBAMessageConfiguration aOIBAMessageConfiguration) {
		this.arMessages.add(aOIBAMessageConfiguration);
	}
}
