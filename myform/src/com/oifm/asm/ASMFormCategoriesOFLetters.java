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
public class ASMFormCategoriesOFLetters extends OIBaseActionForm {

	
	private String categoryId;
    private ArrayList arCategoryID;
    private String boardId;
    private ArrayList arBoardId;
    private String topicId;
    private String strTopicId;
    private ArrayList arTopicId;
    private String[] threadId;
    private ArrayList arThreadId;
    private String strLetterContent;
	private String strLetterReply;
	private String letterId;
	private ArrayList arLetterID;
    
    private String desCategoryId;
    private ArrayList arDesCategoryID;
    private String desBoardId;
    private ArrayList arDesBoardId;
    private String desTopicId;
    private ArrayList arDesTopicId;
    
    public ASMFormCategoriesOFLetters()
    {
        arCategoryID = new ArrayList();
        arLetterID = new ArrayList();
        arBoardId = new ArrayList();
        arTopicId = new ArrayList();
        arThreadId = new ArrayList();
        
        arDesCategoryID = new ArrayList();
        arDesBoardId = new ArrayList();
        arDesTopicId = new ArrayList();
    }
    
    public void addArLetterID(Object obj) {
        this.arLetterID.add(obj);
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
	public void addArBoardId(Object obj) {
        this.arBoardId.add(obj);
    }
	

	public ArrayList getArLetterID() {
		return arLetterID;
	}

	public void setArLetterID(ArrayList arLetterID) {
		this.arLetterID = arLetterID;
	}

	public String getLetterId() {
		return letterId;
	}

	public void setLetterId(String letterId) {
		this.letterId = letterId;
	}

	/**
	 * @return the arCategoryID
	 */
	public ArrayList getArCategoryID() {
		return arCategoryID;
	}

	/**
	 * @param arCategoryID the arCategoryID to set
	 */
	public void setArCategoryID(ArrayList arCategoryID) {
		this.arCategoryID = arCategoryID;
	}
	public void addArCategoryID(Object obj) {
        this.arCategoryID.add(obj);
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
	public void addArDesBoardId(Object obj) {
        this.arDesBoardId.add(obj);
    }

	/**
	 * @return the arDesCategoryID
	 */
	public ArrayList getArDesCategoryID() {
		return arDesCategoryID;
	}

	/**
	 * @param arDesCategoryID the arDesCategoryID to set
	 */
	public void setArDesCategoryID(ArrayList arDesCategoryID) {
		this.arDesCategoryID = arDesCategoryID;
	}
	public void addArDesCategoryID(Object obj) {
        this.arDesCategoryID.add(obj);
    }

	/**
	 * @return the arDesTopicId
	 */
	public ArrayList getArDesTopicId() {
		return arDesTopicId;
	}

	/**
	 * @param arDesTopicId the arDesTopicId to set
	 */
	public void setArDesTopicId(ArrayList arDesTopicId) {
		this.arDesTopicId = arDesTopicId;
	}
	public void addArDesTopicId(Object obj) {
        this.arDesTopicId.add(obj);
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
	public void addArThreadId(Object obj) {
        this.arThreadId.add(obj);
    }

	/**
	 * @return the arTopicId
	 */
	public ArrayList getArTopicId() {
		return arTopicId;
	}

	/**
	 * @param arTopicId the arTopicId to set
	 */
	public void setArTopicId(ArrayList arTopicId) {
		this.arTopicId = arTopicId;
	}
	public void addArTopicId(Object obj) {
        this.arTopicId.add(obj);
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
	 * @return the categoryId
	 */
	public String getCategoryId() {
		return categoryId;
	}

	/**
	 * @param categoryId the categoryId to set
	 */
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
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
	 * @return the desCategoryId
	 */
	public String getDesCategoryId() {
		return desCategoryId;
	}

	/**
	 * @param desCategoryId the desCategoryId to set
	 */
	public void setDesCategoryId(String desCategoryId) {
		this.desCategoryId = desCategoryId;
	}

	/**
	 * @return the desTopicId
	 */
	public String getDesTopicId() {
		return desTopicId;
	}

	/**
	 * @param desTopicId the desTopicId to set
	 */
	public void setDesTopicId(String desTopicId) {
		this.desTopicId = desTopicId;
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
	 * @return the topicId
	 */
	public String getTopicId() {
		return topicId;
	}

	/**
	 * @param topicId the topicId to set
	 */
	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}

	public String getStrLetterContent() {
		return strLetterContent;
	}

	public void setStrLetterContent(String strLetterContent) {
		this.strLetterContent = strLetterContent;
	}

	public String getStrLetterReply() {
		return strLetterReply;
	}

	public void setStrLetterReply(String strLetterReply) {
		this.strLetterReply = strLetterReply;
	}

	public String getStrTopicId() {
		return strTopicId;
	}

	public void setStrTopicId(String strTopicId) {
		this.strTopicId = strTopicId;
	}
   

	}

