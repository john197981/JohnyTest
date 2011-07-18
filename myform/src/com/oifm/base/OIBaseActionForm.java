package com.oifm.base;

import org.apache.struts.action.ActionForm;
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

public class OIBaseActionForm extends ActionForm
{
    private String hiddenAction;

    public String getHiddenAction()
    {
        return hiddenAction;
    }
    public void setHiddenAction(String hiddenAction)
    {
        this.hiddenAction=hiddenAction;
    }
}
