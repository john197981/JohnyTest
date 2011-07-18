package com.oifm.common;

public class OIBAMessageConfiguration 
{
	private String message;
	private String messageCaption;
	private String messageTag;
	
	/**
	 * @return Returns the message.
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message The message to set.
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return Returns the messageTag.
	 */
	public String getMessageTag() {
		return messageTag;
	}
	/**
	 * @param messageTag The messageTag to set.
	 */
	public void setMessageTag(String messageTag) {
		this.messageTag = messageTag;
	}
	/**
	 * @return Returns the messageCaption.
	 */
	public String getMessageCaption() {
		return messageCaption;
	}
	/**
	 * @param messageCaption The messageCaption to set.
	 */
	public void setMessageCaption(String messageCaption) {
		this.messageCaption = messageCaption;
	}
}
