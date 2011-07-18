/*********************************ASMActionHomePage.java******************* 
 * Title 		: ASMActionHomePage
 * Description 	: This class is the Action Class for ASM Home Page. 
 * Author 		: Anbalagan Varadharajan 
 * Version No 	: 1.0 
 * Date Created : 14 - Dec -2005
 * Copyright 	: Scandent Group
 ******************************************************************************/
package com.oifm.asm;
 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.oifm.base.OIBaseAction;
import com.oifm.login.OILoginConstants;
import com.oifm.utility.OIUtilities;

public class ASMActionHomePage extends OIBaseAction 
{
	private static Logger objLogger = Logger.getLogger(ASMActionHomePage.class);
	
	public ActionForward doIt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String actionName)
	{	
		
		ASMBVHomePage objBV=null;
		ASMBOHomePage objHomePageBO=null;
		OIUtilities objUtility =null;
		ASMBOCommon objCommonBO=null;
		ASMBACommon objCommonBA=null;
		String userId = (String)getSessionAttribute(request, OILoginConstants.USER_ID);
		
		try {
			ASMFormHomePage objForm = (ASMFormHomePage) form;
			//Create the instance for BO and VO and rkUtility class
			objHomePageBO = new ASMBOHomePage();
			objCommonBO = new ASMBOCommon();
			objCommonBA = new ASMBACommon();
			objBV = new ASMBVHomePage();
			objUtility =new OIUtilities();
			//Set the Form to VO using PropertyUtils class
			PropertyUtils.copyProperties(objBV, objForm);
			objBV.setUserId(userId);
			//Call the ASM Home page BO class to get the details of announcement, letter and reply
			objHomePageBO.process(objBV);
			
			objForm.setHidLetterID(objBV.getHidLetterID());
			
			if(OIUtilities.replaceNull(request.getParameter("hiddenAction")).equals("PrintPreview")){
				actionName = "printPreview";
			}else{
				objCommonBO.process(objCommonBA,null);
			}
		}
		catch(Exception e)
		{
			objLogger.info("doIt - ASMActionHomePage================================7");
		}
		finally{
			objHomePageBO=null;
			objUtility =null;
			objCommonBO=null;
		}
		request.setAttribute("announcement",OIUtilities.replaceNull(objBV.getHidAnnouncemnet()));
		request.setAttribute("letter_topic",OIUtilities.replaceNull(objBV.getHidLetterTopic()));
		request.setAttribute("published_on",OIUtilities.replaceNull(objBV.getHidPublishOn()));
		request.setAttribute("letter_content",objBV.getHidLetterContent());
		request.setAttribute("reply_content",objBV.getHidReplyContent());
		request.setAttribute("TotRecSizRecLtr",""+objCommonBA.getTotRecLtr());
		request.setAttribute("recent_letter",objCommonBA.getObjBV());
		request.setAttribute("editors_note_id",objCommonBA.getHidEditorsNoteID());
		request.setAttribute("editors_note",objCommonBA.getHidEditorsNote());
		request.setAttribute("editors_note_posted_on",objCommonBA.getHidEditorsNotePostedOn());
		request.setAttribute("created_by",objBV.getHidCreatedBy());
		request.setAttribute("replied_by",objBV.getHidRepliedBy());
		request.setAttribute("pageName","ASMHome");
		return mapping.findForward(actionName);
	}
}
