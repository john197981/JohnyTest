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
import com.oifm.login.OILoginConstants;
import com.oifm.utility.OIDBRegistry;


public class ASMActionCategoriesOfEditorNotes extends OIBaseAction {
	
	// Constants
	//ASMActionCategoriesOfLetter
	
	// End of constants

	private static final Logger logger = Logger.getLogger(ASMActionCategoriesOfEditorNotes.class);
	private static final String POPULATE_ACTION = "populate";
	private static final String TITLE_ACTION = "ADMIN";
	private static final String SEARCH_ACTION = "search";
	private static final String Admin_SEARCH_ACTION = "Adminsearch";
	//private static final String THREAD_ACTION = "thread";
	
	  public ActionForward doIt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String actionName)
	    {
	        
		  System.out.println("actionName :::: " + actionName);
		
		  if (actionName!=null)
	        {
		        if (actionName.equals(POPULATE_ACTION))
		        {
		            return populate(mapping, form, request, response);
		        }else if(actionName.equals(SEARCH_ACTION))
		        {
		        	System.out.println("inside else if");
		            return searchEditorNotes(mapping, form, request, response);
		        }else if(actionName.equals(TITLE_ACTION))
		        {
		        	System.out.println("inside else if" + TITLE_ACTION);
		            return populateForAdmin(mapping, form, request, response);
		        }else if(actionName.equals(Admin_SEARCH_ACTION))
		        {
		        	System.out.println("inside else if Admin_SEARCH_ACTION " + Admin_SEARCH_ACTION);
		            return searchAdminEditorNotes(mapping, form, request, response);
		        }
		        
	        }
	        	return populate(mapping, form, request, response);
	      
	    }

	//Method to call the BO class to fire the search query and load the Category.
	  public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	  {
		  ArrayList temp = new ArrayList();
		  request.setAttribute("pageName", "EditorNotes");
		  String forward="UserPage";
		  OIResponseObject aOIResponseObject = new ASMBOCategoriesOfEditorNotes().readNotes();
		  int noteId =0;
		  ArrayList arOIBAForumCategory = (ArrayList) aOIResponseObject.getResponseObject("arOIBAForumCategory");
		  ASMFormCategoriesOfEditorNotes aSMFormCategoriesOFEditorNotes = new ASMFormCategoriesOfEditorNotes();
		  if (noteId!=0)
			  aSMFormCategoriesOFEditorNotes.setNoteId(noteId + "");
		  String str=null;
		  try
		  {
			  str = OIDBRegistry.getValues("OI.FORUM.CATEGORY.SELECT");
		  }catch(Exception e){}
		  aSMFormCategoriesOFEditorNotes.addArNoteID(new LabelValueBean(str,""));
		  if (arOIBAForumCategory!=null)
		  {
			  for (int i=0;i<arOIBAForumCategory.size();i++)
			  {
				  ASMBVEditorNotesDetails aOIBAForumCategory = (ASMBVEditorNotesDetails) arOIBAForumCategory.get(i);
				  aSMFormCategoriesOFEditorNotes.addArNoteID(new LabelValueBean(aOIBAForumCategory.getStrTitle(),aOIBAForumCategory.getStrContentID()+""));
				  //logger.info("Inside loop");
			  }
		  }

		  if(request.getAttribute("saveFlag")!=null)
			  request.setAttribute("saveFlag", request.getAttribute("saveFlag"));
		  aSMFormCategoriesOFEditorNotes.setNoteId(null);
		  request.setAttribute("ASMFormCategoriesOfEditorNotes",aSMFormCategoriesOFEditorNotes);

		  return (mapping.findForward(forward));
	  }
	  
//	Method to call the BO class to fire the search query and load the Category.
	  public ActionForward populateForAdmin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	  {
		  
		  System.out.println("inside populateForAdmin");
		  ArrayList temp = new ArrayList();
		  request.setAttribute("pageName", "AdminEditorNotes");
		  String forward="ADMIN";
		  OIResponseObject aOIResponseObject = new ASMBOCategoriesOfEditorNotes().readNotes();
		  int noteId =0;
		  ArrayList arOIBAForumCategory = (ArrayList) aOIResponseObject.getResponseObject("arOIBAForumCategory");
		  ASMFormCategoriesOfEditorNotes aSMFormCategoriesOFEditorNotes = new ASMFormCategoriesOfEditorNotes();
		  if (noteId!=0)
			  aSMFormCategoriesOFEditorNotes.setNoteId(noteId + "");
		  String str=null;
		  try
		  {
			  str = OIDBRegistry.getValues("OI.FORUM.CATEGORY.SELECT");
		  }catch(Exception e){}
		  aSMFormCategoriesOFEditorNotes.addArNoteID(new LabelValueBean(str,""));
		  if (arOIBAForumCategory!=null)
		  {
			  for (int i=0;i<arOIBAForumCategory.size();i++)
			  {
				  ASMBVEditorNotesDetails aOIBAForumCategory = (ASMBVEditorNotesDetails) arOIBAForumCategory.get(i);
				  aSMFormCategoriesOFEditorNotes.addArNoteID(new LabelValueBean(aOIBAForumCategory.getStrTitle(),aOIBAForumCategory.getStrContentID()+""));
				  //logger.info("Inside loop");
			  }
		  }

		  if(request.getAttribute("saveFlag")!=null)
			  request.setAttribute("saveFlag", request.getAttribute("saveFlag"));
		  aSMFormCategoriesOFEditorNotes.setNoteId(null);
		  request.setAttribute("ASMFormCategoriesOfEditorNotes",aSMFormCategoriesOFEditorNotes);
		  System.out.println("exiting populateForAdmin");
		  return (mapping.findForward(forward));
	  }

		  
//	Method to call the BO class to fire the search query and get the result.
	  public ActionForward searchEditorNotes(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	    {
		  
		  ASMFormCategoriesOfEditorNotes objForm = (ASMFormCategoriesOfEditorNotes) form;
		  System.out.println("getNoteId ************** " + objForm.getNoteId());
		  ArrayList temp = new ArrayList();
		  request.setAttribute("pageName", "EditorNotes");
		  String forward="UserPage";
		  OIResponseObject aOIResponseObject = new ASMBOCategoriesOfEditorNotes().readNotesDetail(Integer.parseInt(objForm.getNoteId()));
		 // int noteId =0;
		  ASMBVEditorNotesDetails objDetails = (ASMBVEditorNotesDetails) aOIResponseObject.getResponseObject("objDetails");
		  System.out.println("objDetails.getStrTitle()"+ objDetails.getStrTitle());
         // System.out.println("objDetails.getStrNoteContent()" + objDetails.getStrNoteContent());
          
		  ASMFormCategoriesOfEditorNotes aSMFormCategoriesOFEditorNotes = new ASMFormCategoriesOfEditorNotes();
		  //if (noteId!=0){
			//  System.out.println("noteid " + noteId);
			  aSMFormCategoriesOFEditorNotes.setNoteId(objDetails.getStrContentID().toString());
		  	  aSMFormCategoriesOFEditorNotes.setNoteTitle(objDetails.getStrTitle());
		  	  aSMFormCategoriesOFEditorNotes.setNoteDetail(objDetails.getStrNoteContent());
		  	System.out.println("aSMFormCategoriesOFEditorNotes :::::: "+ aSMFormCategoriesOFEditorNotes.getNoteId());
		  //}
	/*	  String str=null;
		  try
		  {
			  str = OIDBRegistry.getValues("OI.FORUM.CATEGORY.SELECT");
		  }catch(Exception e){}
		  aSMFormCategoriesOFEditorNotes.addArNoteID(new LabelValueBean(str,""));
		  

		  if(request.getAttribute("saveFlag")!=null)
			  request.setAttribute("saveFlag", request.getAttribute("saveFlag"));
		  aSMFormCategoriesOFEditorNotes.setNoteId(null);*/
		  	
		  	OIResponseObject objOIResponseObject = new ASMBOCategoriesOfEditorNotes().readNotes();
			  int noteId =0;
			  ArrayList arOIBAForumCategory = (ArrayList) objOIResponseObject.getResponseObject("arOIBAForumCategory");
			  //ASMFormCategoriesOfEditorNotes aSMFormCategoriesOFEditorNotes = new ASMFormCategoriesOfEditorNotes();
			  //if (noteId!=0)
				//  aSMFormCategoriesOFEditorNotes.setNoteId(noteId + "");
			  String str=null;
			  try
			  {
				  str = OIDBRegistry.getValues("OI.FORUM.CATEGORY.SELECT");
			  }catch(Exception e){}
			  aSMFormCategoriesOFEditorNotes.addArNoteID(new LabelValueBean(str,""));
			  if (arOIBAForumCategory!=null)
			  {
				  for (int i=0;i<arOIBAForumCategory.size();i++)
				  {
					  ASMBVEditorNotesDetails aOIBAForumCategory = (ASMBVEditorNotesDetails) arOIBAForumCategory.get(i);
					  aSMFormCategoriesOFEditorNotes.addArNoteID(new LabelValueBean(aOIBAForumCategory.getStrTitle(),aOIBAForumCategory.getStrContentID()+""));
					  //logger.info("Inside loop");
				  }
			  }

			  /*if(request.getAttribute("saveFlag")!=null)
				  request.setAttribute("saveFlag", request.getAttribute("saveFlag"));
			  aSMFormCategoriesOFEditorNotes.setNoteId(null);*/
		  	
		  	
		  request.setAttribute("ASMFormCategoriesOfEditorNotes",aSMFormCategoriesOFEditorNotes);

		  return (mapping.findForward(forward));
		  
		  
		  
	}

//		Method to call the BO class to fire the search query and get the result.
	  public ActionForward searchAdminEditorNotes(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	    {
		  
		  ASMFormCategoriesOfEditorNotes objForm = (ASMFormCategoriesOfEditorNotes) form;
		  System.out.println("getNoteId ************** " + objForm.getNoteId());
		  ArrayList temp = new ArrayList();
		  request.setAttribute("pageName", "AdminEditorNotes");
		  String forward="Adminsearch";
		  OIResponseObject aOIResponseObject = new ASMBOCategoriesOfEditorNotes().readNotesDetail(Integer.parseInt(objForm.getNoteId()));
		 // int noteId =0;
		  ASMBVEditorNotesDetails objDetails = (ASMBVEditorNotesDetails) aOIResponseObject.getResponseObject("objDetails");
		  System.out.println("objDetails.getStrTitle()"+ objDetails.getStrTitle());
         // System.out.println("objDetails.getStrNoteContent()" + objDetails.getStrNoteContent());
          
		  ASMFormCategoriesOfEditorNotes aSMFormCategoriesOFEditorNotes = new ASMFormCategoriesOfEditorNotes();
		  //if (noteId!=0){
			//  System.out.println("noteid " + noteId);
			  aSMFormCategoriesOFEditorNotes.setNoteId(objDetails.getStrContentID().toString());
		  	  aSMFormCategoriesOFEditorNotes.setNoteTitle(objDetails.getStrTitle());
		  	  aSMFormCategoriesOFEditorNotes.setNoteDetail(objDetails.getStrNoteContent());
		  	System.out.println("aSMFormCategoriesOFEditorNotes :::::: "+ aSMFormCategoriesOFEditorNotes.getNoteId());
		  //}
	/*	  String str=null;
		  try
		  {
			  str = OIDBRegistry.getValues("OI.FORUM.CATEGORY.SELECT");
		  }catch(Exception e){}
		  aSMFormCategoriesOFEditorNotes.addArNoteID(new LabelValueBean(str,""));
		  

		  if(request.getAttribute("saveFlag")!=null)
			  request.setAttribute("saveFlag", request.getAttribute("saveFlag"));
		  aSMFormCategoriesOFEditorNotes.setNoteId(null);*/
		  	
		  	OIResponseObject objOIResponseObject = new ASMBOCategoriesOfEditorNotes().readNotes();
			  int noteId =0;
			  ArrayList arOIBAForumCategory = (ArrayList) objOIResponseObject.getResponseObject("arOIBAForumCategory");
			  //ASMFormCategoriesOfEditorNotes aSMFormCategoriesOFEditorNotes = new ASMFormCategoriesOfEditorNotes();
			  //if (noteId!=0)
				//  aSMFormCategoriesOFEditorNotes.setNoteId(noteId + "");
			  String str=null;
			  try
			  {
				  str = OIDBRegistry.getValues("OI.FORUM.CATEGORY.SELECT");
			  }catch(Exception e){}
			  aSMFormCategoriesOFEditorNotes.addArNoteID(new LabelValueBean(str,""));
			  if (arOIBAForumCategory!=null)
			  {
				  for (int i=0;i<arOIBAForumCategory.size();i++)
				  {
					  ASMBVEditorNotesDetails aOIBAForumCategory = (ASMBVEditorNotesDetails) arOIBAForumCategory.get(i);
					  aSMFormCategoriesOFEditorNotes.addArNoteID(new LabelValueBean(aOIBAForumCategory.getStrTitle(),aOIBAForumCategory.getStrContentID()+""));
					  //logger.info("Inside loop");
				  }
			  }

			  /*if(request.getAttribute("saveFlag")!=null)
				  request.setAttribute("saveFlag", request.getAttribute("saveFlag"));
			  aSMFormCategoriesOFEditorNotes.setNoteId(null);*/
		  	
		  	
		  request.setAttribute("ASMFormCategoriesOfEditorNotes",aSMFormCategoriesOFEditorNotes);

		  return (mapping.findForward(forward));
		  
		  
		  
	}

	  
}
