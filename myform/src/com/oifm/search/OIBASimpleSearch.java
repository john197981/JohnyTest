/*
 * File name	= OIBASimpleSearch.java
 * Package		= com.oifm.search
 * Created on 	= Aug 14, 2005
 * Created by	= Oscar
 * Copyright	= Scandent Group
 *
 */

package com.oifm.search;

import java.util.ArrayList;
import java.util.StringTokenizer;

import com.oifm.base.OIBaseBa;
import com.oifm.utility.OIUtilities;


public class OIBASimpleSearch extends OIBaseBa {
	private String strSearchString;
	private ArrayList alSearchTokens;
	private String pageNo;
	/**
	 * @return Returns the alSearchTokens.
	 */
	public ArrayList getAlSearchTokens() {
		return alSearchTokens;
	}
	/**
	 * @param alSearchTokens The alSearchTokens to set.
	 */
	public void setAlSearchTokens(ArrayList alSearchTokens) {
		this.alSearchTokens = alSearchTokens;
	}
	/**
	 * @return Returns the strSearchString.
	 */
	public String getStrSearchString() {
		return strSearchString;
	}
	/**
	 * @param strSearchString The strSearchString to set.
	 */
	public void setStrSearchString(String strSearchString) {
		this.strSearchString = (strSearchString == null)?strSearchString:OIUtilities.addSingleQuoteDB(strSearchString);
		if (strSearchString != null)this.setAlSearchTokens(constructTokens(this.getStrSearchString()));
	}
	
	public ArrayList constructTokens(String str) {
		ArrayList alTokens = new ArrayList();
		String temp = null;
		
	
		while (str.length() > 0){
			int idxQuote = str.indexOf("\"");
			int idxSpace = str.indexOf(" ");
			if (idxSpace < 0) idxSpace = str.length();
			
			// Check if have quote
			if (idxQuote < 0) {
				// No quote mark -> straightly tokenize
				StringTokenizer tokenizer = new StringTokenizer(str);
				while (tokenizer.hasMoreTokens())
					alTokens.add(tokenizer.nextToken());
				break;
			/*	
			// Check if have space
			} else if (idxSpace < 0){
				// No space -> take the whole chunk
				alTokens.add(str);
				break;
				*/
			// Check if space before quote
			} else if (idxSpace < idxQuote) {
				// Take a word up to space, cut the string
				alTokens.add(str.substring(0, idxSpace));
				str = str.substring(idxSpace + 1);
				
			// Check if quote is the first character
			} else if (idxQuote != 0) {
				// Quote not the first char but before space -> take as a word up to space, cut the string
				alTokens.add(str.substring(0, idxSpace));
				if (idxSpace != str.length()) str = str.substring(idxSpace + 1);
				else str = str.substring(idxSpace);
				
			// Check if have closing quote
			} else {
				boolean flag = false;
				int idxAnotherQuote = idxQuote;
				while (!flag) {
					// Get index of next quote
					idxAnotherQuote = str.indexOf("\"", idxAnotherQuote + 1);
					
					 // If found closing quote
					if (idxAnotherQuote >= 0 && (str.startsWith(" ", idxAnotherQuote + 1) || (idxAnotherQuote + 1 >= str.length()))) {
						// Take everything between quotes, cut string
						alTokens.add(str.substring(idxQuote + 1, idxAnotherQuote));
						if (idxAnotherQuote + 1 != str.length())
							str = str.substring(idxAnotherQuote + 2);
						else str = str.substring(idxAnotherQuote + 1);
						flag = true;
						
					// If no next quote -> break
					} else if (idxAnotherQuote < 0) break;
				}
				
				// If no closing quote
				if (!flag) {
					// Take as a word up to space, cut string
					if (idxSpace != str.length()) {
						alTokens.add(str.substring(0, idxSpace));
						str = str.substring(idxSpace + 1);
					}
					else {
						alTokens.add(str);
						str = str.substring(idxSpace);
					}
				}
			}
			str = str.trim();
		}
		
		return alTokens;
	}
	/**
	 * @return Returns the pageNo.
	 */
	public String getPageNo() {
		return pageNo;
	}
	/**
	 * @param pageNo The pageNo to set.
	 */
	public void setPageNo(String pageNo) {
		if (pageNo==null || pageNo.equals("") || pageNo.length() == 0) this.pageNo = "1";
		else this.pageNo = pageNo;
	}
}
