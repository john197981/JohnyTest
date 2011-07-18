package com.oifm.utility;
/*
 * Class Name:
 * Description:
 * 
 * 	Created By			Created/Modified on			Revision				Remarks
 * -----------------------------------------------------------------------------------------------------
 * 	Sukumar			16/06/2005					Draft					Inital Version
 * 
 * -----------------------------------------------------------------------------------------------------
 */

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

public class OISessionCounter implements HttpSessionListener 
{
	
	final static Logger logger = Logger.getLogger(OISessionCounter.class);

	private static int activeSessions = 0;
	
	public void	sessionCreated(HttpSessionEvent	se)	
	{
		
		activeSessions = activeSessions +1;
		//logger.info("Session Created => activeSessions : "+activeSessions);
	}

	
	public void	sessionDestroyed(HttpSessionEvent se) 
	{
		if(activeSessions >	0)
		{
			activeSessions = activeSessions -1;
			
		}
		logger.info("Session Destroyed => activeSessions : "+activeSessions);
	}
	public static int getActiveSessions() 
	{
		if(activeSessions ==0){
			activeSessions =1;
		}
		return activeSessions;
	}


}
