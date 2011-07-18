/*
 * Created on Aug 15, 2005
*
* TODO To change the template for this generated file go to
* Window - Preferences - Java - Code Style - Code Templates
*/
package com.oifm.asm;

import java.util.ArrayList;

import com.oifm.base.OIBaseActionForm;

/**
* @author Administrator
*
* TODO To change the template for this generated type comment go to
* Window - Preferences - Java - Code Style - Code Templates
*/
public class ASMFormCategoriesOfEditorNotes extends OIBaseActionForm {

	
	private String actionName;
	private String noteId;
	private String noteTitle;
	private String noteDetail;
	private String noteReply;
    private ArrayList arnoteId;
    private String boardId;
    private ArrayList arBoardId;
    private String titleId;
    private ArrayList arTitleId;
    private String[] threadId;
    private ArrayList arThreadId;
    
    private String desNoteId;
    private ArrayList arDesNoteID;
    private String desBoardId;
    private ArrayList arDesBoardId;
    private String desTitleId;
    private ArrayList arDesTitleId;
    
    public ASMFormCategoriesOfEditorNotes()
    {
    	arnoteId = new ArrayList();
        arBoardId = new ArrayList();
        arTitleId = new ArrayList();
        arThreadId = new ArrayList();
        
        arDesNoteID = new ArrayList();
        arDesBoardId = new ArrayList();
        arDesTitleId = new ArrayList();
    }

	/**
	 * @return the arBoardId
	 */
	public ArrayList getArBoardId() {
		return arBoardId;
	}

	/**
	 * @param arBoardId the arBoardId to set
	 */
	public void setArBoardId(ArrayList arBoardId) {
		this.arBoardId = arBoardId;
	}

	/**
	 * @return the arDesBoardId
	 */
	public ArrayList getArDesBoardId() {
		return arDesBoardId;
	}

	/**
	 * @param arDesBoardId the arDesBoardId to set
	 */
	public void setArDesBoardId(ArrayList arDesBoardId) {
		this.arDesBoardId = arDesBoardId;
	}

	/**
	 * @return the arDesNoteID
	 */
	public ArrayList getArDesNoteID() {
		return arDesNoteID;
	}

	/**
	 * @param arDesNoteID the arDesNoteID to set
	 */
	public void setArDesNoteID(ArrayList arDesNoteID) {
		this.arDesNoteID = arDesNoteID;
	}

	/**
	 * @return the arDesTitleId
	 */
	public ArrayList getArDesTitleId() {
		return arDesTitleId;
	}

	/**
	 * @param arDesTitleId the arDesTitleId to set
	 */
	public void setArDesTitleId(ArrayList arDesTitleId) {
		this.arDesTitleId = arDesTitleId;
	}

	/**
	 * @return the arnoteId
	 */
	public ArrayList getArnoteId() {
		return arnoteId;
	}

	/**
	 * @param arnoteId the arnoteId to set
	 */
	public void setArnoteId(ArrayList arnoteId) {
		this.arnoteId = arnoteId;
	}

	public void addArNoteID(Object obj) {
        this.arnoteId.add(obj);
    }
	/**
	 * @return the arThreadId
	 */
	public ArrayList getArThreadId() {
		return arThreadId;
	}

	/**
	 * @param arThreadId the arThreadId to set
	 */
	public void setArThreadId(ArrayList arThreadId) {
		this.arThreadId = arThreadId;
	}

	/**
	 * @return the arTitleId
	 */
	public ArrayList getArTitleId() {
		return arTitleId;
	}

	/**
	 * @param arTitleId the arTitleId to set
	 */
	public void setArTitleId(ArrayList arTitleId) {
		this.arTitleId = arTitleId;
	}

	/**
	 * @return the boardId
	 */
	public String getBoardId() {
		return boardId;
	}

	/**
	 * @param boardId the boardId to set
	 */
	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}

	/**
	 * @return the desBoardId
	 */
	public String getDesBoardId() {
		return desBoardId;
	}

	/**
	 * @param desBoardId the desBoardId to set
	 */
	public void setDesBoardId(String desBoardId) {
		this.desBoardId = desBoardId;
	}

	/**
	 * @return the desNoteId
	 */
	public String getDesNoteId() {
		return desNoteId;
	}

	/**
	 * @param desNoteId the desNoteId to set
	 */
	public void setDesNoteId(String desNoteId) {
		this.desNoteId = desNoteId;
	}

	/**
	 * @return the desTitleId
	 */
	public String getDesTitleId() {
		return desTitleId;
	}

	/**
	 * @param desTitleId the desTitleId to set
	 */
	public void setDesTitleId(String desTitleId) {
		this.desTitleId = desTitleId;
	}

	/**
	 * @return the noteId
	 */
	public String getNoteId() {
		return noteId;
	}

	/**
	 * @param noteId the noteId to set
	 */
	public void setNoteId(String noteId) {
		this.noteId = noteId;
	}

	/**
	 * @return the threadId
	 */
	public String[] getThreadId() {
		return threadId;
	}

	/**
	 * @param threadId the threadId to set
	 */
	public void setThreadId(String[] threadId) {
		this.threadId = threadId;
	}

	/**
	 * @return the titleId
	 */
	public String getTitleId() {
		return titleId;
	}

	/**
	 * @param titleId the titleId to set
	 */
	public void setTitleId(String titleId) {
		this.titleId = titleId;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getNoteDetail() {
		return noteDetail;
	}

	public void setNoteDetail(String noteDetail) {
		this.noteDetail = noteDetail;
	}

	public String getNoteReply() {
		return noteReply;
	}

	public void setNoteReply(String noteReply) {
		this.noteReply = noteReply;
	}

	public String getNoteTitle() {
		return noteTitle;
	}

	public void setNoteTitle(String noteTitle) {
		this.noteTitle = noteTitle;
	}

	

	}

