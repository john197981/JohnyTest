/*
 * File name	= ASMActionAbout.java
 * Package		= com.oifm.asm
 * Created on 	= Dec 16, 2005
 * Created by	= Oscar
 * Copyright	= Scandent Group
 *
 */

package com.oifm.asm;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

import com.oifm.base.OIBaseAction;
import com.oifm.common.OIFunctionConstants;
import com.oifm.common.OIResponseObject;
import com.oifm.forum.admin.OIBAForumCategory;
import com.oifm.forum.admin.OIBOForumTopic;
import com.oifm.forum.admin.OIFormForumMoveThread;
import com.oifm.login.OILoginConstants;
import com.oifm.utility.OIDBRegistry;


public class ASMActionCategoriesOfLetter extends OIBaseAction {

	// Constants
	//ASMActionCategoriesOfLetter

	// End of constants

	private static final Logger logger = Logger.getLogger(ASMActionCategoriesOfLetter.class);
	private static final String POPULATE_ACTION = "populate";
	private static final String TITLE_ACTION = "ADMIN";
	private static final String SEARCH_ACTION = "SearchResult";
	private static final String Admin_SEARCH_ACTION = "Adminsearch";
	//private static final String TOPIC_ACTION = "topic";
	//private static final String THREAD_ACTION = "thread";

	public ActionForward doIt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String actionName)
	{

		//String forward="UserPage";
		logger.info(" actionName is "+actionName);

		if (actionName!=null)
		{
			if (actionName.equals(POPULATE_ACTION))
			{
				return populate(mapping, form, request, response);
			}
			else if(actionName.equals(TITLE_ACTION))
			{
				//System.out.println("inside else if" + TITLE_ACTION);
				return populateForAdmin(mapping, form, request, response);
			}else if(actionName.equals(Admin_SEARCH_ACTION))
			{
				//System.out.println("inside else if Admin_SEARCH_ACTION " + Admin_SEARCH_ACTION);
				return searchAdminCategoriesLetters(mapping, form, request, response);
			}
			else if (actionName.equals(SEARCH_ACTION))
			{
				//System.out.println(" else if (actionName.equals(SEARCH_ACTION))" +  SEARCH_ACTION);
				return searchLetter(mapping, form, request, response);
			}
			else
				return populate(mapping, form, request, response);

		}
		return  populate(mapping, form, request, response);

	}


	//Method to call the BO class to fire the search query and load the Category.
	public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		ArrayList temp = new ArrayList();
		request.setAttribute("pageName", "Categories");
		String forward="UserPage";
		ASMFormCategoriesOFLetters objForm = (ASMFormCategoriesOFLetters) form;

		// For the category drop down
		OIResponseObject aOIResponseObject = new ASMBOCategoriesOfLetter().readCategory();
		int categoryId =0;
		ArrayList arOIBAForumCategory = (ArrayList) aOIResponseObject.getResponseObject("arOIBAForumCategory");
		ASMFormCategoriesOFLetters aSMFormCategoriesOFLetters = new ASMFormCategoriesOFLetters();
		if (categoryId!=0)
			aSMFormCategoriesOFLetters.setCategoryId(categoryId + "");
		String str=null;
		try
		{
			str = OIDBRegistry.getValues("OI.FORUM.CATEGORY.SELECT");
		}catch(Exception e){}
		aSMFormCategoriesOFLetters.addArCategoryID(new LabelValueBean(str,""));
		if (arOIBAForumCategory!=null)
		{
			for (int i=0;i<arOIBAForumCategory.size();i++)
			{
				ASMBVCategoryDetails aOIBAForumCategory = (ASMBVCategoryDetails) arOIBAForumCategory.get(i);
				aSMFormCategoriesOFLetters.addArCategoryID(new LabelValueBean(aOIBAForumCategory.getStrCategoryName(),aOIBAForumCategory.getStrCategoryID()+""));
				//logger.info("Inside loop");
			}
		}

		if(request.getAttribute("saveFlag")!=null)
			request.setAttribute("saveFlag", request.getAttribute("saveFlag"));
		String selectedCategoryId=objForm.getCategoryId();
		aSMFormCategoriesOFLetters.setCategoryId(selectedCategoryId);
		
		//System.out.println(" aSMFormCategoriesOFLetters ::::: " + aSMFormCategoriesOFLetters.getLetterId());
		request.setAttribute("ASMFormCategoriesOFLetters",aSMFormCategoriesOFLetters);

		// For the second drop down

		logger.info("Category id"+objForm.getCategoryId());
		OIResponseObject letterResponseObject = new ASMBOCategoriesOfLetter().readLetters(objForm.getCategoryId()); // Warning. Need to change
		int letterId =0;
		ArrayList arOIBAForumLetter = (ArrayList) letterResponseObject.getResponseObject("arOIBAForumLetter");
		//ASMFormCategoriesOFLetters aSMFormCategoriesOFLetters = new ASMFormCategoriesOFLetters();
		if (categoryId!=0)
			aSMFormCategoriesOFLetters.setLetterId(letterId + "");
		if (arOIBAForumLetter!=null) logger.info("size is"+arOIBAForumLetter.size());
		String str1=null;
		try
		{
			str1 = OIDBRegistry.getValues("OI.FORUM.CATEGORY.SELECT");
		}catch(Exception e){}
		aSMFormCategoriesOFLetters.addArCategoryID(new LabelValueBean(str1,""));
		if (arOIBAForumLetter!=null)
		{
			for (int i=0;i<arOIBAForumLetter.size();i++) 
			{
				ASMBVCategoryDetails aOIBAForumCategory = (ASMBVCategoryDetails) arOIBAForumLetter.get(i);
				//aSMFormCategoriesOFLetters.addArLetterID(new LabelValueBean(aOIBAForumCategory.getStrLetterName(),aOIBAForumCategory.getStrLetterID()+""));
				aSMFormCategoriesOFLetters.addArLetterID(new LabelValueBean(aOIBAForumCategory.getStrLetterName(),aOIBAForumCategory.getStrLetterID()+""));
				//logger.info("Inside for loop");
			}
		}

		if(request.getAttribute("saveFlag")!=null)
			request.setAttribute("saveFlag", request.getAttribute("saveFlag"));
		aSMFormCategoriesOFLetters.setLetterId(null);
		String selectedLetterId=objForm.getLetterId();
		aSMFormCategoriesOFLetters.setLetterId(selectedLetterId);
		request.setAttribute("ASMFormCategoriesOFLetters",aSMFormCategoriesOFLetters);

		return (mapping.findForward(forward));
	}

//	Method to call the BO class to fire the search query and load the Category.
	public ActionForward populateForAdmin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		ArrayList temp = new ArrayList();
		request.setAttribute("pageName", "AdminCategories");
		String forward="ADMIN";
		ASMFormCategoriesOFLetters objForm = (ASMFormCategoriesOFLetters) form;
		OIResponseObject aOIResponseObject = new ASMBOCategoriesOfLetter().readCategory();
		int categoryId =0;
		ArrayList arOIBAForumCategory = (ArrayList) aOIResponseObject.getResponseObject("arOIBAForumCategory");
		ASMFormCategoriesOFLetters aSMFormCategoriesOFLetters = new ASMFormCategoriesOFLetters();
		if (categoryId!=0)
			aSMFormCategoriesOFLetters.setCategoryId(categoryId + "");
		String str=null;
		try
		{
			str = OIDBRegistry.getValues("OI.FORUM.CATEGORY.SELECT");
		}catch(Exception e){}
		aSMFormCategoriesOFLetters.addArCategoryID(new LabelValueBean(str,""));
		if (arOIBAForumCategory!=null)
		{
			for (int i=0;i<arOIBAForumCategory.size();i++)
			{
				ASMBVCategoryDetails aOIBAForumCategory = (ASMBVCategoryDetails) arOIBAForumCategory.get(i);
				aSMFormCategoriesOFLetters.addArCategoryID(new LabelValueBean(aOIBAForumCategory.getStrCategoryName(),aOIBAForumCategory.getStrCategoryID()+""));
				//logger.info("Inside loop");
			}
		}

		if(request.getAttribute("saveFlag")!=null)
			request.setAttribute("saveFlag", request.getAttribute("saveFlag"));
		aSMFormCategoriesOFLetters.setCategoryId(null);
		String selectedCategoryId=objForm.getCategoryId();
		aSMFormCategoriesOFLetters.setCategoryId(selectedCategoryId);
		request.setAttribute("ASMFormCategoriesOFLetters",aSMFormCategoriesOFLetters);
		// For the second drop down

		logger.info("Category id"+objForm.getCategoryId());
		OIResponseObject letterResponseObject = new ASMBOCategoriesOfLetter().readLetters(objForm.getCategoryId()); // Warning. Need to change
		int letterId =0;
		ArrayList arOIBAForumLetter = (ArrayList) letterResponseObject.getResponseObject("arOIBAForumLetter");
		//ASMFormCategoriesOFLetters aSMFormCategoriesOFLetters = new ASMFormCategoriesOFLetters();
		if (categoryId!=0)
			aSMFormCategoriesOFLetters.setLetterId(letterId + "");
		if (arOIBAForumLetter!=null) logger.info("size is"+arOIBAForumLetter.size());
		String str1=null;
		try
		{
			str1 = OIDBRegistry.getValues("OI.FORUM.CATEGORY.SELECT");
		}catch(Exception e){}
		aSMFormCategoriesOFLetters.addArCategoryID(new LabelValueBean(str1,""));
		if (arOIBAForumLetter!=null)
		{
			for (int i=0;i<arOIBAForumLetter.size();i++) 
			{
				ASMBVCategoryDetails aOIBAForumCategory = (ASMBVCategoryDetails) arOIBAForumLetter.get(i);
				//aSMFormCategoriesOFLetters.addArLetterID(new LabelValueBean(aOIBAForumCategory.getStrLetterName(),aOIBAForumCategory.getStrLetterID()+""));
				aSMFormCategoriesOFLetters.addArLetterID(new LabelValueBean(aOIBAForumCategory.getStrLetterName(),aOIBAForumCategory.getStrLetterID()+""));
				//logger.info("Inside for loop");
			}
		}

		if(request.getAttribute("saveFlag")!=null)
			request.setAttribute("saveFlag", request.getAttribute("saveFlag"));
		aSMFormCategoriesOFLetters.setLetterId(null);
		
		String selectedLetterId=objForm.getLetterId();
		aSMFormCategoriesOFLetters.setLetterId(selectedLetterId);
		//System.out.println("cat id ::::" + aSMFormCategoriesOFLetters.getCategoryId());
		request.setAttribute("ASMFormCategoriesOFLetters",aSMFormCategoriesOFLetters);

		return (mapping.findForward(forward));
	}



//	Method to call the BO class to fire the search query and get list of Letter Titles for a Category ID.
	/* public ActionForward getTitle(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	    {

		  String forward="UserPage";
		  ArrayList temp = new ArrayList();
		  ASMBOCategoriesOfLetter objCategoryDetails = new ASMBOCategoriesOfLetter();
		  temp = objCategoryDetails.getLetterForCategory((Integer)(request.getAttribute("ID")));
		  request.setAttribute("arrCategoryList", temp);


		return (mapping.findForward(forward));
	}*/

//	Method to call the BO class to fire the search query and get the result.
	public ActionForward searchLetter(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{

		ASMFormCategoriesOFLetters objForm = (ASMFormCategoriesOFLetters) form;
		//System.out.println("CategoryId ************** " + objForm.getLetterId());
		ArrayList temp = new ArrayList();
		request.setAttribute("pageName", "Categories");
		String forward="SearchResult";
		//Hardcoded for timebeing
		// int ij=15;
		OIResponseObject aOIResponseObject = 
			new ASMBOCategoriesOfLetter().readNotesDetail(Integer.parseInt(objForm.getCategoryId()),Integer.parseInt(objForm.getLetterId()));
		// int noteId =0;
		ASMBVLetterDetails objDetails = (ASMBVLetterDetails) aOIResponseObject.getResponseObject("objDetails");
		//System.out.println("objDetails.getIntLetterid()"+ objDetails.getStrLetterContent());
		// System.out.println("objDetails.getStrNoteContent()" + objDetails.getStrNoteContent());

		ASMFormCategoriesOFLetters asmFormCategoriesOFLetters = new ASMFormCategoriesOFLetters();
		//if (noteId!=0){
		//  System.out.println("noteid " + noteId);
		asmFormCategoriesOFLetters.setCategoryId(objDetails.getIntCategoryID().toString());
		asmFormCategoriesOFLetters.setTopicId(objDetails.getIntLetterid().toString());
		asmFormCategoriesOFLetters.setStrLetterContent(objDetails.getStrLetterContent());
		asmFormCategoriesOFLetters.setStrLetterReply(objDetails.getStrLetterReply());
		//System.out.println("asmFormCategoriesOFLetters :::::: "+  asmFormCategoriesOFLetters.getTopicId());
		OIResponseObject objOIResponseObject = new ASMBOCategoriesOfLetter().readCategory();
		// int noteId =0;
		ArrayList arOIBAForumCategory = (ArrayList) objOIResponseObject.getResponseObject("arOIBAForumCategory");
		//int categoryId =0;
		//  ArrayList arOIBAForumCategory = (ArrayList) aOIResponseObject.getResponseObject("arOIBAForumCategory");
		// ASMFormCategoriesOFLetters aSMFormCategoriesOFLetters = new ASMFormCategoriesOFLetters();
		// if (categoryId!=0)
		//	asmFormCategoriesOFLetters.setCategoryId(categoryId + "");
		String str=null;
		try
		{
			str = OIDBRegistry.getValues("OI.FORUM.CATEGORY.SELECT");
		}catch(Exception e){}
		asmFormCategoriesOFLetters.addArCategoryID(new LabelValueBean(str,""));
		if (arOIBAForumCategory!=null)
		{
			for (int i=0;i<arOIBAForumCategory.size();i++)
			{
				ASMBVCategoryDetails aOIBAForumCategory = (ASMBVCategoryDetails) arOIBAForumCategory.get(i);
				asmFormCategoriesOFLetters.addArCategoryID(new LabelValueBean(aOIBAForumCategory.getStrCategoryName(),aOIBAForumCategory.getStrCategoryID()+""));
				//logger.info("Inside loop");
			}
		}
		String selectedCategoryId=objForm.getCategoryId();
		asmFormCategoriesOFLetters.setCategoryId(selectedCategoryId);
		
		request.setAttribute("ASMFormCategoriesOFLetters",asmFormCategoriesOFLetters);
		// For the second drop down

		logger.info("Category id"+objForm.getCategoryId());
		OIResponseObject letterResponseObject = new ASMBOCategoriesOfLetter().readLetters(objForm.getCategoryId()); // Warning. Need to change
		int letterId =0;
		ArrayList arOIBAForumLetter = (ArrayList) letterResponseObject.getResponseObject("arOIBAForumLetter");
		//ASMFormCategoriesOFLetters aSMFormCategoriesOFLetters = new ASMFormCategoriesOFLetters();
		if (letterId!=0)
			asmFormCategoriesOFLetters.setLetterId(letterId + "");
		if (arOIBAForumLetter!=null) logger.info("size is"+arOIBAForumLetter.size());
		String str1=null;
		try
		{
			str1 = OIDBRegistry.getValues("OI.FORUM.CATEGORY.SELECT");
		}catch(Exception e){}
		asmFormCategoriesOFLetters.addArLetterID(new LabelValueBean(str1,""));
		if (arOIBAForumLetter!=null)
		{
			for (int i=0;i<arOIBAForumLetter.size();i++) 
			{
				ASMBVCategoryDetails aOIBAForumCategory = (ASMBVCategoryDetails) arOIBAForumLetter.get(i);
				//aSMFormCategoriesOFLetters.addArLetterID(new LabelValueBean(aOIBAForumCategory.getStrLetterName(),aOIBAForumCategory.getStrLetterID()+""));
				asmFormCategoriesOFLetters.addArLetterID(new LabelValueBean(aOIBAForumCategory.getStrLetterName(),aOIBAForumCategory.getStrLetterID()+""));
				//logger.info("Inside for loop");
			}
		}
		String selectedLetterId=objForm.getLetterId();
		logger.info("selectedLetterId id********"+selectedLetterId);
		asmFormCategoriesOFLetters.setLetterId(selectedLetterId);
		if(request.getAttribute("saveFlag")!=null)
			request.setAttribute("saveFlag", request.getAttribute("saveFlag"));
		//asmFormCategoriesOFLetters.setLetterId(null);
		request.setAttribute("ASMFormCategoriesOFLetters",asmFormCategoriesOFLetters);



		return (mapping.findForward(forward));

	}

//	Method to call the BO class to fire the search query and get the result.
	public ActionForward searchAdminCategoriesLetters(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		ASMBVLetterDetails objDetails = null;
		ASMFormCategoriesOFLetters objForm = (ASMFormCategoriesOFLetters) form;
		int ctID = 0;
		ArrayList temp = new ArrayList();
		String str1;
		request.setAttribute("pageName", "AdminCategories");
		String forward="Adminsearch";
		if(objForm.getCategoryId()== null && objForm.getLetterId()!=null){
			
			 	
				ctID = new ASMBOCategoriesOfLetter().getCategorybyLetterID(Integer.parseInt(objForm.getLetterId()));
				objForm.setCategoryId(Integer.toString(ctID));
			System.out.println("CategoryId ************** inside if" + ctID);
			OIResponseObject aOIResponseObject = 
				new ASMBOCategoriesOfLetter().readNotesDetail(ctID,Integer.parseInt(objForm.getLetterId()));
			 objDetails = (ASMBVLetterDetails) aOIResponseObject.getResponseObject("objDetails");
		}else{
			OIResponseObject aOIResponseObject1 = 
			new ASMBOCategoriesOfLetter().readNotesDetail(Integer.parseInt(objForm.getCategoryId()),Integer.parseInt(objForm.getLetterId()));
			objDetails = (ASMBVLetterDetails) aOIResponseObject1.getResponseObject("objDetails");
		}
		// int noteId =0;
		

		ASMFormCategoriesOFLetters asmFormCategoriesOFLetters = new ASMFormCategoriesOFLetters();
		//if (noteId!=0){
		//  System.out.println("noteid " + noteId);
		asmFormCategoriesOFLetters.setCategoryId(objDetails.getIntCategoryID().toString());
		asmFormCategoriesOFLetters.setStrTopicId(objDetails.getStrTopic());
		asmFormCategoriesOFLetters.setTopicId(objDetails.getIntLetterid().toString());
		asmFormCategoriesOFLetters.setStrLetterContent(objDetails.getStrLetterContent());
		asmFormCategoriesOFLetters.setStrLetterReply(objDetails.getStrLetterReply());
		//System.out.println("aSMFormCategoriesOFEditorNotes ::::::>>>>>>>> "+  asmFormCategoriesOFLetters.getCategoryId());
		OIResponseObject objOIResponseObject = new ASMBOCategoriesOfLetter().readCategory();
		ArrayList arOIBAForumCategory = (ArrayList) objOIResponseObject.getResponseObject("arOIBAForumCategory");
		String str=null;
		try
		{
			str = OIDBRegistry.getValues("OI.FORUM.CATEGORY.SELECT");
		}catch(Exception e){}
		asmFormCategoriesOFLetters.addArCategoryID(new LabelValueBean(str,""));
		if (arOIBAForumCategory!=null)
		{
			for (int i=0;i<arOIBAForumCategory.size();i++)
			{
				ASMBVCategoryDetails aOIBAForumCategory = (ASMBVCategoryDetails) arOIBAForumCategory.get(i);
				asmFormCategoriesOFLetters.addArCategoryID(new LabelValueBean(aOIBAForumCategory.getStrCategoryName(),aOIBAForumCategory.getStrCategoryID()+""));
				//logger.info("Inside loop");
			}
		}
		String selectedCategoryId=null;
		/*if(objForm.getCategoryId()==null){

			asmFormCategoriesOFLetters.setCategoryId(Integer.toString(ctID));
			request.setAttribute("ASMFormCategoriesOFLetters",asmFormCategoriesOFLetters);
		}else{*/
			asmFormCategoriesOFLetters.setCategoryId(objForm.getCategoryId());
			request.setAttribute("ASMFormCategoriesOFLetters",asmFormCategoriesOFLetters);
		//}
		
		//For the second drop down

		logger.info("Category id"+objForm.getCategoryId());
		OIResponseObject letterResponseObject = new ASMBOCategoriesOfLetter().readLetters(objForm.getCategoryId()); // Warning. Need to change
		int letterId =0;
		ArrayList arOIBAForumLetter = (ArrayList) letterResponseObject.getResponseObject("arOIBAForumLetter");
		//ASMFormCategoriesOFLetters aSMFormCategoriesOFLetters = new ASMFormCategoriesOFLetters();
		if (letterId!=0)
			asmFormCategoriesOFLetters.setLetterId(letterId + "");
		if (arOIBAForumLetter!=null) logger.info("size is"+arOIBAForumLetter.size());
		str1=null;
		try
		{
			str1 = OIDBRegistry.getValues("OI.FORUM.CATEGORY.SELECT");
		}catch(Exception e){}
		asmFormCategoriesOFLetters.addArLetterID(new LabelValueBean(str1,""));
		if (arOIBAForumLetter!=null)
		{
			for (int i=0;i<arOIBAForumLetter.size();i++) 
			{
				ASMBVCategoryDetails aOIBAForumCategory = (ASMBVCategoryDetails) arOIBAForumLetter.get(i);
				//aSMFormCategoriesOFLetters.addArLetterID(new LabelValueBean(aOIBAForumCategory.getStrLetterName(),aOIBAForumCategory.getStrLetterID()+""));
				asmFormCategoriesOFLetters.addArLetterID(new LabelValueBean(aOIBAForumCategory.getStrLetterName(),aOIBAForumCategory.getStrLetterID()+""));
				//logger.info("Inside for loop");
			}
		}

		if(request.getAttribute("saveFlag")!=null)
			request.setAttribute("saveFlag", request.getAttribute("saveFlag"));
		//asmFormCategoriesOFLetters.setLetterId(null);
		System.out.println("letterId " + objForm.getLetterId() );
		String selectedLetterId=objForm.getLetterId();
		asmFormCategoriesOFLetters.setLetterId(selectedLetterId);
		request.setAttribute("ASMFormCategoriesOFLetters",asmFormCategoriesOFLetters);


		return (mapping.findForward(forward));

	}



}
