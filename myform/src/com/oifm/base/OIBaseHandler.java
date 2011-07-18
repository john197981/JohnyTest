package com.oifm.base;
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

import java.util.ArrayList;

public class OIBaseHandler 
{
    private ArrayList messageList;
    public String error;
    private ArrayList categoryList;
    
    public ArrayList getCategoryList()
    {
    	return categoryList;
    }
    
    public void addCategoryList(Object obj)
    {
    	if (categoryList==null)
    		categoryList = new ArrayList();
    		categoryList.add(obj);
    }
    public ArrayList getMessageList()
    {
        return messageList;
    }

    public void addMessageList(Object obj)
    {
        if (messageList==null)
            messageList = new ArrayList();
        messageList.add(obj);
    }

    public String getError()
    {
        return error;
    }

    public void addError(String str)
    {
        error=str;
    }
}
