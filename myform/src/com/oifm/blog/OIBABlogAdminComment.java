package com.oifm.blog;

import java.io.Serializable;
import java.util.Date;

public class OIBABlogAdminComment implements Serializable {

	private String commentor = null;
	private String entryTitle = null;
	private Date commentDate = null;
	private Date entryDate = null;
	private String comment = null;
	private String entryId = null;
	private String commentId = null;
	
	/**
	 * @return the commentor
	 */
	public String getCommentor() {
		return commentor;
	}
	/**
	 * @param commentor the commentor to set
	 */
	public void setCommentor(String commentor) {
		this.commentor = commentor;
	}
	/**
	 * @return the entryTitle
	 */
	public String getEntryTitle() {
		return entryTitle;
	}
	/**
	 * @param entryTitle the entryTitle to set
	 */
	public void setEntryTitle(String entryTitle) {
		this.entryTitle = entryTitle;
	}
	/**
	 * @return the commentDate
	 */
	public Date getCommentDate() {
		return commentDate;
	}
	/**
	 * @param commentDate the commentDate to set
	 */
	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}
	/**
	 * @return the entryDate
	 */
	public Date getEntryDate() {
		return entryDate;
	}
	/**
	 * @param entryDate the entryDate to set
	 */
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	
	public String getComment(){
		return comment;
	}
	
	public void setComment(String comment){
		this.comment = comment;
	}
	
	public String getEntryId(){
		return entryId;
	}
	
	public void setEntryId(String entryId){
		this.entryId = entryId;
	}
	
	public String getCommentId(){
		return commentId;
	}
	
	public void setCommentId(String commentId){
		this.commentId = commentId;
	}
}
