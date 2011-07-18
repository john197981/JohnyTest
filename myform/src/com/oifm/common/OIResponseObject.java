package com.oifm.common;

import java.util.HashMap;
/*
 * Class Name:
 * Description:
 * 
 * 	Created By			Created/Modified on			Revision				Remarks
 * -----------------------------------------------------------------------------------------------------
 * 	Rajkumar			16/06/2005					Draft					Inital Version
 * 
 * -----------------------------------------------------------------------------------------------------
 */

public class OIResponseObject 
{
    public HashMap responseObject= new HashMap();
    
    
    public OIResponseObject()
    {
        //responseObject = new Hashtable();
    }
    
    public Object getResponseObject(String key)
    {
        return responseObject.get(key);
    }

    public void addResponseObject(String key,Object obj)
    {
        responseObject.put(key,obj);
    }

    public void removeResponseObject(String key)
    {
        responseObject.remove(key);
    }
    /**
     * This method Check for the Existence of the Key
     * @author Suresh Kumar.R
     */
    public boolean containsKey(String key){
    	return responseObject.containsKey(key);	
    }
    public HashMap getResponseObject()
    {
        return responseObject;
    }
}
