/*********************************ASMActionReport.java******************* 
 * Title 		: ASMActionReport
 * Description 	: This class is the Action Class for ASM Report Page. 
 * Author 		: Anbalagan Varadharajan
 * Modified By	: Rakesh Chagallu
 * Version No 	: 1.0 
 * Date Modified: 28 - Jan -2008
 * Copyright 	: Scandent Group
 ******************************************************************************/
package com.oifm.asm;
 

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.oifm.base.OIBaseAction;
import com.oifm.common.OIFunctionConstants;
import com.oifm.common.OIPageInfoBean;
import com.oifm.common.OIResponseObject;
import com.oifm.consultation.OIConsultConstant;
import com.oifm.login.OILoginConstants;
import com.oifm.utility.OIUtilities;

public class ASMActionReport extends OIBaseAction 
{
	private static Logger objLogger = Logger.getLogger(ASMActionReport.class);
	
	public ActionForward doIt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String actionName)
	{	
		objLogger.debug("doIt method");	
		
		ASMBAReport objBA=null;
		ASMBAReport objBA2=null;
		ASMBOReport objReportBO=null;
		OIUtilities objUtility =null;
		ASMBACommon objCommonBA=null;
		HttpSession session = null;
        String strRedirect = "";
        String userID = "";
        ArrayList functions = null;
        boolean isASMRep = false;
//      Create the instance for BO and VO and rkUtility class
		objReportBO = new ASMBOReport();
		objBA = new ASMBAReport();
		objBA2 = new ASMBAReport();
		
		try {
			session = request.getSession();
        	functions = (ArrayList)getSessionAttribute(request, OILoginConstants.FUNCTION_LIST);
            userID = (String)getSessionAttribute(request, OILoginConstants.USER_ID);
            isASMRep = functions.contains(OIFunctionConstants.ASM_REPS);
            if(isASMRep) {
			ASMFormReport objForm = (ASMFormReport) form;
			
			objUtility =new OIUtilities();
			//Set the Form to VO using PropertyUtils class
			PropertyUtils.copyProperties(objBA, objForm);
			PropertyUtils.copyProperties(objBA2, objForm);
			//Get the division details
			ArrayList alDivList1 =objReportBO.getDivisionDetails(objBA);
			objBA.setAlDivision(alDivList1);
			request.setAttribute("DivisionList",objBA.getAlDivision());
			ArrayList alDivList2 =new ArrayList(alDivList1); 
			objReportBO.getSchoolDetails(objBA,alDivList2);
			request.setAttribute("DivisionSchoolList",alDivList2);
			// Added by Rakesh. 
			ArrayList alCategoryList =objReportBO.getCategoryList(objBA);
			objBA.setAlCategory(alCategoryList);
			request.setAttribute("CategoryList",objBA.getAlCategory());
			//System.out.println("ASMActionReport-doIt1");
			if(OIUtilities.replaceNull(objBA.getHiddenAction()).equalsIgnoreCase("SearchResult") || 
					OIUtilities.replaceNull(objBA.getHiddenAction()).equalsIgnoreCase("ExportExcel")){
				//Two BA instance is sent (one is for search query and the second is for excel sheet values
				OIResponseObject aOIResponseObject = objReportBO.process(objBA,objBA2);
				//############################# Pagination Start############################
				
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
		        
		        //############################# Pagination End############################
			}
			if(OIUtilities.replaceNull(objBA.getHiddenAction()).equalsIgnoreCase("ExportExcel")){
				//exportFile(objBA,objBA2,response);
			}
           }else {
             	strRedirect = ASMGlobals.ERR_REDIRECT + "?error=NoAccess";
            	}
          }catch(Exception e)
		{
			objLogger.info("doIt - ASMActionReport================================7");
		}
		finally{
			objReportBO=null;
			objUtility =null;
		}
		
		request.setAttribute("pageName","ASMReport");
		request.setAttribute("TotRec",""+objBA.getTotRecLtr());
		request.setAttribute("search_results",objBA.getObjBV());
		request.setAttribute("hiddenAction",objBA.getHiddenAction());
		
		//Added for Excel 
		request.setAttribute("TotRecExcel",""+objBA2.getTotRecLtr());
		request.setAttribute("search_results_excel",objBA2.getObjBV());
		
		if(!strRedirect.equals("")) 
            return new ActionForward(strRedirect);
        else		
		return mapping.findForward(actionName);
	}

	public void exportFile(ASMBAReport objBA,ASMBAReport objBA2,HttpServletResponse response)throws Exception{
		HSSFWorkbook wb= new HSSFWorkbook();		
		
		HSSFSheet sheet = wb.createSheet();
		HSSFRow row     = sheet.createRow((short)0);
		
		HSSFFont objFont = wb.createFont();

//		set font 1 to 10 point type
		objFont.setFontHeightInPoints((short) 10);
//		 make it bold
//		arial is the default font
		objFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		HSSFCellStyle cs = wb.createCellStyle();
		cs.setFont(objFont);
		
		int i=0;
		HSSFCell cell   = row.createCell((short)i);
		cell.setCellValue("Topic");
		cell.setCellStyle(cs);
		

		i=i+1;
		if(objBA.getChkSubmitOn()){
			cell = row.createCell((short)i);cell.setCellValue("Submit On");cell.setCellStyle(cs);i=i+1;			
		}
		if(objBA.getChkWrittenBy()){
			cell = row.createCell((short)i);cell.setCellValue("Written By");cell.setCellStyle(cs);i=i+1;
		}
		if(objBA.getChkDesigLW()){
			cell = row.createCell((short)i);cell.setCellValue("Designation");cell.setCellStyle(cs);i=i+1;
		}
		if(objBA.getChkDivisionLW()){
			cell = row.createCell((short)i);cell.setCellValue("Division");cell.setCellStyle(cs);i=i+1;
		}
		if(objBA.getChkLetterContent()){
			cell = row.createCell((short)i);cell.setCellValue("Letter Content");cell.setCellStyle(cs);i=i+1;
		}
		if(objBA.getChkYIS()){
			cell = row.createCell((short)i);cell.setCellValue("YIS");cell.setCellStyle(cs);i=i+1;
		}
		if(objBA.getChkAge()){
			cell = row.createCell((short)i);cell.setCellValue("Age");cell.setCellStyle(cs);i=i+1;
		}
		if(objBA.getChkDivInCharge()){
			cell = row.createCell((short)i);cell.setCellValue("Div-in charge");cell.setCellStyle(cs);i=i+1;
		}
		if(objBA.getChkCategory()){
			cell = row.createCell((short)i);cell.setCellValue("Category");cell.setCellStyle(cs);i=i+1;
		}
		if(objBA.getChkRedirectTo()){
			cell = row.createCell((short)i);cell.setCellValue("Redirect To");cell.setCellStyle(cs);i=i+1;
		}
		if(objBA.getChkRedirectOn()){
			cell = row.createCell((short)i);cell.setCellValue("Redirect On");cell.setCellStyle(cs);i=i+1;
		}
		if(objBA.getChkRepliedBy()){
			cell = row.createCell((short)i);cell.setCellValue("Replied By");cell.setCellStyle(cs);i=i+1;
		}
		if(objBA.getChkRepliedOn()){
			cell = row.createCell((short)i);cell.setCellValue("Replied On");cell.setCellStyle(cs);i=i+1;
		}
		
		if(objBA.getChkReplyContent()){
			cell = row.createCell((short)i);cell.setCellValue("Reply Content");cell.setCellStyle(cs);i=i+1;
		}
		ASMBVReport[] objBV = objBA2.getObjBV();
		for(int j=0;j<objBV.length;j++){
			row     = sheet.createRow((short)j+1);
			
			i=0;
			cell   = row.createCell((short)i);
			cell.setCellValue(objBV[j].getHidLetterTopic());
			i=i+1;
			if(objBA.getChkSubmitOn()){
				row.createCell((short)i).setCellValue(objBV[j].getTxtSubOnFromDate());i=i+1;			
			}
			if(objBA.getChkWrittenBy()){
				row.createCell((short)i).setCellValue(objBV[j].getTxtWrittenBy());i=i+1;
			}
			if(objBA.getChkDesigLW()){
				row.createCell((short)i).setCellValue(objBV[j].getTxtDesigLW());i=i+1;
			}
			if(objBA.getChkDivisionLW()){
				row.createCell((short)i).setCellValue(objBV[j].getTxtDivisionLW());i=i+1;
			}
			if(objBA.getChkLetterContent()){
				row.createCell((short)i).setCellValue(objBV[j].getTxtLetterContent());i=i+1;
			}
			if(objBA.getChkYIS()){
				row.createCell((short)i).setCellValue(objBV[j].getTxtYISFromDate());i=i+1;
			}
			if(objBA.getChkAge()){
				row.createCell((short)i).setCellValue(objBV[j].getTxtAgeFromDate());i=i+1;
			}
			if(objBA.getChkDivInCharge()){
				row.createCell((short)i).setCellValue(objBV[j].getCboDivInCharge());i=i+1;
			}
			if(objBA.getChkCategory()){
				row.createCell((short)i).setCellValue(objBV[j].getCboCategory());i=i+1;
			}
			
			if(objBA.getChkRedirectTo()){
				row.createCell((short)i).setCellValue(objBV[j].getTxtRedirectTo());i=i+1;
			}
			if(objBA.getChkRedirectOn()){
				row.createCell((short)i).setCellValue(objBV[j].getTxtRedirectFromDate());i=i+1;
			}
			if(objBA.getChkRepliedBy()){
				row.createCell((short)i).setCellValue(objBV[j].getTxtRepliedBy());i=i+1;
			}
			if(objBA.getChkRepliedOn()){
				row.createCell((short)i).setCellValue(objBV[j].getTxtReplyFromDate());i=i+1;
			}
			if(objBA.getChkReplyContent()){
				row.createCell((short)i).setCellValue(objBV[j].getTxtReplycontent());i=i+1;
			}
		}
		FileOutputStream fileOut = new FileOutputStream("Report.xls");
		wb.write(fileOut);
		fileOut.close();
			
		FileInputStream frOut = new FileInputStream("Report.xls");		
		//response.setContentType("application/vnd.ms-excel");
		//response.setHeader("Content-Disposition","attachment; filename=Report.xls");
		int value = 0;
		PrintWriter pw = response.getWriter();
		while((value = frOut.read()) != -1){					
			pw.write(value);		
		}
		pw.flush();
		pw.close();
		frOut.close();
		File fl = new File("Report.xls");
		fl.delete();
		
	}
}	

