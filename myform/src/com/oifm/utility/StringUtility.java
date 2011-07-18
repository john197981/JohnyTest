package com.oifm.utility;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class StringUtility 
{
	public static ArrayList delimStringToArrayList(String delimString,String delimeter)
	{
		ArrayList arDelim = new ArrayList();
		StringTokenizer sttr = new StringTokenizer(delimString, delimeter);
		while (sttr.hasMoreTokens()) 
			arDelim.add(sttr.nextToken());
		return arDelim;
	}

	public static String getNNoWords(String str,int totalWords)
	{
		String wordsToBeReturned="";
		int count=0;
		StringTokenizer sttr = new StringTokenizer(str);
	    while (sttr.hasMoreTokens() && count<totalWords)
	    { 
	    	wordsToBeReturned = wordsToBeReturned + " " + sttr.nextToken(); 
	    	++count; 
	    }
		return wordsToBeReturned;
	}
}
