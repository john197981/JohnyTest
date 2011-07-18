/*
 * File name	= ASMFormEditor.java
 * Package		= com.oifm.asm
 * Created on 	= Dec 19, 2005
 * Created by	= Oscar
 * Copyright	= Scandent Group
 *
 */

package com.oifm.asm;

import com.oifm.base.OIBaseActionForm;


public class ASMFormEditor extends OIBaseActionForm {

	private int id;
	private String editon;
	private String topic;
	private String content;
	private int pageNo;
	private String fromCategory;
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
	 * @return Returns the id.
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return Returns the pageNo.
	 */
	public int getPageNo() {
		return pageNo;
	}
	/**
	 * @param pageNo The pageNo to set.
	 */
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	/**
	 * @return Returns the topic.
	 */
	public String getTopic() {
		return topic;
	}
	/**
	 * @param topic The topic to set.
	 */
	public void setTopic(String topic) {
		this.topic = topic;
	}
	/**
	 * @return Returns the editon.
	 */
	public String getEditon() {
		return editon;
	}
	/**
	 * @param editon The editon to set.
	 */
	public void setEditon(String editon) {
		this.editon = editon;
	}
	public String getFromCategory() {
		return fromCategory;
	}
	public void setFromCategory(String fromCategory) {
		this.fromCategory = fromCategory;
	}
}
