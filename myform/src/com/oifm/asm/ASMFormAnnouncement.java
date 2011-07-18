/*
 * File name	= ASMFormAnnouncement.java
 * Package		= com.oifm.asm
 * Created on 	= Dec 15, 2005
 * Created by	= Oscar
 * Copyright	= Scandent Group
 *
 */

package com.oifm.asm;

import com.oifm.base.OIBaseActionForm;


public class ASMFormAnnouncement extends OIBaseActionForm {
	private String message;
	
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
}
