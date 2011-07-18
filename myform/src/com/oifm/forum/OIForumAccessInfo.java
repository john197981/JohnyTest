
package com.oifm.forum;

import java.io.Serializable;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.oifm.common.OIFunctionConstants;

public class OIForumAccessInfo implements Serializable {

	private ArrayList alFunctions;
	private boolean isSameBoard;
	private boolean allowPM;
	private boolean allowAM;
	private boolean mainAdmin;
	private boolean divAdmin;
	private boolean divAdminOrModerator;
	private boolean user;
	private static Logger logger = Logger.getLogger(OIActionThread.class);
	public OIForumAccessInfo() {}
	
	public OIForumAccessInfo(ArrayList alFunctions) {
		this.alFunctions = alFunctions;
		this.mainAdmin = checkMainAdmin();
		this.divAdmin = checkDivAdmin();
		this.divAdminOrModerator = checkDivAdminOrModerator();
		this.user = checkUser();
	} 
	public void setFunctions(ArrayList alFunctions) {
		this.alFunctions = alFunctions; 		
	} 
	public void setAllowAM(boolean allowAM) {
		this.allowAM = allowAM;
	}
	public void setAllowPM(boolean allowPM) {
		this.allowPM = allowPM;
	}
	public void setSameBoard(boolean isSameBoard) {
		this.isSameBoard = isSameBoard;
	}
	public boolean isAllowAM() {
		return allowAM;
	}
	public boolean isAllowPM() {
		return allowPM;
	}
	public boolean isCreatePrivateThread() {
		boolean flag = false;
		if(divAdminOrModerator)
		{
		flag =  (divAdminOrModerator && alFunctions.contains(OIFunctionConstants.CREATE_PRIVATE_THREAD))?true:(alFunctions != null && isSameBoard && alFunctions.contains(OIFunctionConstants.CREATE_PRIVATE_THREAD));
		} 
		else if(mainAdmin)
		{
			flag =  (mainAdmin && alFunctions.contains(OIFunctionConstants.CREATE_PRIVATE_THREAD))?true:(alFunctions != null && isSameBoard && alFunctions.contains(OIFunctionConstants.CREATE_PRIVATE_THREAD));
		}
		else if(user)
		{
			flag =  (user && alFunctions.contains(OIFunctionConstants.CREATE_PRIVATE_THREAD))?true:(alFunctions != null && isSameBoard && alFunctions.contains(OIFunctionConstants.CREATE_PRIVATE_THREAD));
		}
		return flag;
	}
	public boolean isEditThread() {
		boolean flag = false;
		if(divAdminOrModerator){
		flag =  (divAdminOrModerator && alFunctions.contains(OIFunctionConstants.EDIT_THREAD))?true:(alFunctions != null && isSameBoard && alFunctions.contains(OIFunctionConstants.EDIT_THREAD));
		}
		else if(mainAdmin)
		{
			flag =  (mainAdmin && alFunctions.contains(OIFunctionConstants.EDIT_THREAD))?true:(alFunctions != null && isSameBoard && alFunctions.contains(OIFunctionConstants.EDIT_THREAD));
		}
		else if(user)
		{
			flag =  (user && alFunctions.contains(OIFunctionConstants.EDIT_THREAD))?true:(alFunctions != null && isSameBoard && alFunctions.contains(OIFunctionConstants.EDIT_THREAD));
		}
		return flag;
	}
	public boolean isDeleteThread() {
		boolean flag = false;
		if(divAdminOrModerator){
		flag =  (divAdminOrModerator && alFunctions.contains(OIFunctionConstants.DELETE_THREAD))?true:(alFunctions != null && isSameBoard && alFunctions.contains(OIFunctionConstants.DELETE_THREAD));
		}
		else if(mainAdmin)
		{
			flag =  (mainAdmin && alFunctions.contains(OIFunctionConstants.DELETE_THREAD))?true:(alFunctions != null && isSameBoard && alFunctions.contains(OIFunctionConstants.DELETE_THREAD));
		}
		else if(user)
		{
			flag =  (user && alFunctions.contains(OIFunctionConstants.DELETE_THREAD))?true:(alFunctions != null && isSameBoard && alFunctions.contains(OIFunctionConstants.DELETE_THREAD));
		}
		return flag;
	}
	public boolean isEditPosting() {
		boolean flag = false;
		if(divAdminOrModerator){
		flag =  (divAdminOrModerator && alFunctions.contains(OIFunctionConstants.EDIT_POSTING))?true:(alFunctions != null && isSameBoard && alFunctions.contains(OIFunctionConstants.EDIT_POSTING));
		}
		else if(mainAdmin){
			flag =  (mainAdmin && alFunctions.contains(OIFunctionConstants.EDIT_POSTING))?true:(alFunctions != null && isSameBoard && alFunctions.contains(OIFunctionConstants.EDIT_POSTING));
		}
		else if(user)
		{
			flag =  (user && alFunctions.contains(OIFunctionConstants.EDIT_POSTING))?true:(alFunctions != null && isSameBoard && alFunctions.contains(OIFunctionConstants.EDIT_POSTING));
		}
		return flag;
	}
	public boolean isDeletePosting() {
		boolean flag = false;
		if(divAdminOrModerator){
		flag =  (divAdminOrModerator && alFunctions.contains(OIFunctionConstants.DELETE_POSTING))?true:(alFunctions != null && isSameBoard && alFunctions.contains(OIFunctionConstants.DELETE_POSTING));
		}
		else if(mainAdmin){
			flag =  (mainAdmin && alFunctions.contains(OIFunctionConstants.DELETE_POSTING))?true:(alFunctions != null && isSameBoard && alFunctions.contains(OIFunctionConstants.DELETE_POSTING));
		}
		else if(user)
		{
			flag =  (user && alFunctions.contains(OIFunctionConstants.DELETE_POSTING))?true:(alFunctions != null && isSameBoard && alFunctions.contains(OIFunctionConstants.DELETE_POSTING));
		}
		return flag;
	}
	public boolean isLockThread() {
		boolean flag = false;
		if(divAdminOrModerator){
		flag =  (divAdminOrModerator && alFunctions.contains(OIFunctionConstants.LOCK_UNLOCK_THREAD))?true:(alFunctions != null && isSameBoard && alFunctions.contains(OIFunctionConstants.LOCK_UNLOCK_THREAD));
		}
		else if(mainAdmin){
			flag =  (mainAdmin && alFunctions.contains(OIFunctionConstants.LOCK_UNLOCK_THREAD))?true:(alFunctions != null && isSameBoard && alFunctions.contains(OIFunctionConstants.LOCK_UNLOCK_THREAD));
		}
		else if(user)
		{
			flag =  (user && alFunctions.contains(OIFunctionConstants.LOCK_UNLOCK_THREAD))?true:(alFunctions != null && isSameBoard && alFunctions.contains(OIFunctionConstants.LOCK_UNLOCK_THREAD));
		}
		return flag;
	}
	public boolean isExportThread() {
		boolean flag = false;
		if(divAdminOrModerator){
		flag =  (divAdminOrModerator && alFunctions.contains(OIFunctionConstants.EXPORT_THREAD))?true:(alFunctions != null && isSameBoard && alFunctions.contains(OIFunctionConstants.EXPORT_THREAD));
		}
		else if(mainAdmin){
			flag =  (mainAdmin && alFunctions.contains(OIFunctionConstants.EXPORT_THREAD))?true:(alFunctions != null && isSameBoard && alFunctions.contains(OIFunctionConstants.EXPORT_THREAD));
		}
		else if(user)
		{
			flag =  (user && alFunctions.contains(OIFunctionConstants.EXPORT_THREAD))?true:(alFunctions != null && isSameBoard && alFunctions.contains(OIFunctionConstants.EXPORT_THREAD));
		}
		return flag;
	}
	public boolean isMoveThread() {
		boolean flag = false;
		if(divAdminOrModerator){
		flag =  (divAdminOrModerator && alFunctions.contains(OIFunctionConstants.MOVE_THREADS))?true:(alFunctions != null && isSameBoard && alFunctions.contains(OIFunctionConstants.MOVE_THREADS));
		}
		else if(mainAdmin){
			flag =  (mainAdmin && alFunctions.contains(OIFunctionConstants.MOVE_THREADS))?true:(alFunctions != null && isSameBoard && alFunctions.contains(OIFunctionConstants.MOVE_THREADS));
		}
		else if(user)
		{
			flag =  (user && alFunctions.contains(OIFunctionConstants.MOVE_THREADS))?true:(alFunctions != null && isSameBoard && alFunctions.contains(OIFunctionConstants.MOVE_THREADS));
		}
		return flag;
	}
	public boolean isMainAdmin() {
		return this.mainAdmin;
	}
	public boolean isDivAdminOrModerator() {
		return this.divAdminOrModerator;
	}
	public boolean isUser() {
		return this.user;
	}
	
	private boolean checkMainAdmin() {
		
		return (alFunctions != null && alFunctions.contains(OIFunctionConstants.MAINTAIN_CATEGORY_BOARD) && 
				alFunctions.contains(OIFunctionConstants.MAINTAIN_BOARD) && 
				alFunctions.contains(OIFunctionConstants.MAINTAIN_TOPIC));
	}
	private boolean checkDivAdmin() {
		
		return (alFunctions != null  && 
				alFunctions.contains(OIFunctionConstants.MAINTAIN_BOARD) && 
				alFunctions.contains(OIFunctionConstants.MAINTAIN_TOPIC));
	}

	private boolean checkDivAdminOrModerator() {
		return (alFunctions != null && alFunctions.contains(OIFunctionConstants.MAINTAIN_CATEGORY_BOARD) || 
				alFunctions.contains(OIFunctionConstants.MAINTAIN_BOARD) || 
				alFunctions.contains(OIFunctionConstants.MAINTAIN_TOPIC) || 
				alFunctions.contains(OIFunctionConstants.MODERATE_THREADS_POSTINGS));
	}
	
	private boolean checkUser() {
		return (alFunctions != null && !alFunctions.contains(OIFunctionConstants.MAINTAIN_CATEGORY_BOARD) && 
				!alFunctions.contains(OIFunctionConstants.MAINTAIN_BOARD) &&
				!alFunctions.contains(OIFunctionConstants.MAINTAIN_TOPIC) && 
				!alFunctions.contains(OIFunctionConstants.MODERATE_THREADS_POSTINGS));
	}

}

