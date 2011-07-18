package com.oifm.useradmin;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.oifm.base.OIBaseAction;
import com.oifm.common.OIResponseObject;
import com.oifm.consultation.OIConsultConstant;
import com.oifm.login.OILoginConstants;
import com.oifm.utility.OIEncrypter;

/*
 * Class Name:
 * Description:
 * 
 * 	Created By			Created/Modified on			Revision				Remarks
 * -----------------------------------------------------------------------------------------------------
 * 	Rajkumar			05/13/2005					Draft					Inital Version
 * 
 * -----------------------------------------------------------------------------------------------------
 */
public class OIActionMemberProfile  extends OIBaseAction
{
    private static final String POPULATE_ACTION = "populate";
    public ActionForward doIt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String actionName)
    {
        if (actionName!=null)
        {
	        if (actionName.equals(POPULATE_ACTION))
	        {
	            return populate(mapping, form, request, response);
	        }
        }
        String error = "Action not Available";
        request.setAttribute(OILoginConstants.K_ERROR,error);
        return new ActionForward(OILoginConstants.ERRORPAGE);
    }

    public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        String userId = request.getParameter("nric");
        //Amended by Zhao Yu @ 2008-Aug-21
        String isEncrpyt = request.getParameter("isEncrpyt");
        if ("yes".equals(isEncrpyt))
        {
        	userId = OIEncrypter.decrypt(userId);
        }        
        OIResponseObject aOIResponseObject = new OIBOMemberProfile().readMemberProfile(userId);
        OIFormMemberProfile aOIFormMemberProfile = new OIFormMemberProfile();
        
        ArrayList messageList = (ArrayList) aOIResponseObject.getResponseObject(OILoginConstants.K_MESSAGE);
        String error = (String) aOIResponseObject.getResponseObject(OILoginConstants.K_ERROR);
        if (error != null)
        {
            request.setAttribute(OILoginConstants.K_ERROR,error);
            return new ActionForward(OILoginConstants.ERRORPAGE);
        }
        OIBAProfile aOIBAProfile = (OIBAProfile) aOIResponseObject.getResponseObject("aOIBAProfile");
        aOIFormMemberProfile.setUserId(aOIBAProfile.getUserId());
        aOIFormMemberProfile.setHobbies(aOIBAProfile.getHobbies());
        aOIFormMemberProfile.setInterest(aOIBAProfile.getInterest());
        aOIFormMemberProfile.setNickName(aOIBAProfile.getNickName());
        aOIFormMemberProfile.setTotalPosting(aOIBAProfile.getTotalPosting() + ""); 
        aOIFormMemberProfile.setSignature(aOIBAProfile.getSignature());
        
        request.setAttribute("MemberProfileForm",aOIFormMemberProfile);
        return mapping.findForward(OIConsultConstant.POPULATE_CONSULTLISTING);
    }
}
