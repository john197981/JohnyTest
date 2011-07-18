/*********************************ASMActionPrevRep.java******************* 
 * Title 		: ASMActionPrevRep
 * Description 	: This class is the Action Class for ASM Home Page. 
 * Author 		: Anbalagan Varadharajan 
 * Version No 	: 1.0 
 * Date Created : 14 - Dec -2005
 * Copyright 	: Scandent Group
 ******************************************************************************/
package com.oifm.asm;
 

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.oifm.base.OIBaseAction;
import com.oifm.common.OIPageInfoBean;
import com.oifm.common.OIResponseObject;
import com.oifm.consultation.OIConsultConstant;
import com.oifm.utility.OIUtilities;

public class ASMActionPrevRep extends OIBaseAction 
{
	private static Logger objLogger = Logger.getLogger(ASMActionPrevRep.class);
	
	public ActionForward doIt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String actionName)
	{	
		
		ASMBVPrevRep objBV=null;
		ASMBAPrevRep objBA=null;
		ASMBOPrevRep objPrevRepBO=null;
		OIUtilities objUtility =null;
		ASMBOCommon objCommonBO=null;
		ASMBACommon objCommonBA=null;
		
		try {
			ASMFormPrevRep objForm = (ASMFormPrevRep) form;
			//Create the instance for BO and VO and rkUtility class
			objPrevRepBO = new ASMBOPrevRep();
			objCommonBO = new ASMBOCommon();
			objCommonBA = new ASMBACommon();
			objBV = new ASMBVPrevRep();
			objBA = new ASMBAPrevRep();
			objUtility =new OIUtilities();
			//Set the Form to VO using PropertyUtils class
			PropertyUtils.copyProperties(objBA, objForm);
			//Call the ASM Home page BO class to get the details of announcement, letter and reply
			OIResponseObject aOIResponseObject = objPrevRepBO.process(objBA);

	        OIPageInfoBean aOIPageInfoBean = (OIPageInfoBean) aOIResponseObject.getResponseObject(OIConsultConstant.K_aOIPageInfoBean);
	        ArrayList arPage = new ArrayList();
	        int start = aOIPageInfoBean.getCurrLinkStart();
	        for (int i=start;i<start + aOIPageInfoBean.getNoOfLinks();i++)
	        {
	            if (i<=aOIPageInfoBean.getNoOfPages())
	                arPage.add(i+"");
	        }
	        
	        if (aOIPageInfoBean.getNoOfPages()>=1){
	            request.setAttribute("totalPage",aOIPageInfoBean.getNoOfPages() + "");
	        }
	        else{
	            request.setAttribute("totalPage",aOIPageInfoBean.getNoOfPages() + "");
	        }
	        request.setAttribute(OIConsultConstant.K_currentPage,aOIPageInfoBean.getPageNo() + "");
	        request.setAttribute(OIConsultConstant.K_pageNo,aOIPageInfoBean.getPageNo() + "");
	        request.setAttribute(OIConsultConstant.K_nextSet,aOIPageInfoBean.isNSet() + "");
	        request.setAttribute(OIConsultConstant.K_previousSet,aOIPageInfoBean.isPSet() + "");
	        request.setAttribute(OIConsultConstant.K_nextPage,aOIPageInfoBean.getNextSet() + "");
	        request.setAttribute(OIConsultConstant.K_previousPage,aOIPageInfoBean.getPrevSet() + "");
	        request.setAttribute(OIConsultConstant.K_arPage,arPage);

			objForm.setHidLetterID(objBV.getHidLetterID());
			
			//This is for right Side portal
			objCommonBO.process(objCommonBA,null);
			objForm.setHidMonths(objBA.getHidMonths());
			request.setAttribute("months",""+objBA.getHidMonths());
			
		}
		catch(Exception e)
		{
			objLogger.info("Error occured"+e);
		}
		finally{
			//objBV=null;
			objPrevRepBO=null;
			objUtility =null;
			objCommonBO=null;
		}
		
		// ######################################################
		//This is for rightSide Portal -Start
		request.setAttribute("TotRecSizRecLtr",""+objCommonBA.getTotRecLtr());
		request.setAttribute("recent_letter",objCommonBA.getObjBV());
		request.setAttribute("editors_note_id",objCommonBA.getHidEditorsNoteID());
		request.setAttribute("editors_note",objCommonBA.getHidEditorsNote());
		request.setAttribute("editors_note_posted_on",objCommonBA.getHidEditorsNotePostedOn());
//		This is for rightSide Portal -End
		//For module short description
		request.setAttribute("moduleDesc",""+objBA.getModuleDesc());
		
		request.setAttribute("TotRec",""+objBA.getTotRecLtr());
		request.setAttribute("latest_letter",objBA.getObjBV());
		request.setAttribute("pageName","ASMPrevRep");
		return mapping.findForward(actionName);
	}
}
