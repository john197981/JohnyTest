/*
 * File name	= ASMBAAnnouncement.java
 * Package		= com.oifm.asm
 * Created on 	= Dec 15, 2005
 * Created by	= Oscar
 * Copyright	= Scandent Group
 *
 */

package com.oifm.asm;

import com.oifm.base.OIBaseBa;


public class ASMBAAnnouncement extends OIBaseBa {
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
