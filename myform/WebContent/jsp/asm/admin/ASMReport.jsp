<%--
/**
 * FileName			: ASMReport.jsp
 * Author      		: Anbalagan
 * Modified By		: Rakesh
 * Created Date 	: 23/12/2005
 * Modified Date	: 28 Jan 2008
 * Description 		: This page used to display the ASM Report.
 * Version			: 1.0
 **/  
--%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ page language="java" import="com.oifm.asm.ASMBVReport,com.oifm.utility.OIUtilities"%>
   
<link href='<bean:message key="OIFM.docroot" />/css/oicalendar.css' rel="stylesheet" type="text/css">
<script language="javascript" src='<bean:message key="OIFM.docroot" />/js/oicalendar.js'></script>
<script language="javascript" src='<bean:message key="OIFM.docroot" />/js/asmReport.js'></script>
<script language="javascript" src='<bean:message key="OIFM.docroot" />/js/asmHome.js'></script>
<script language="javascript" src='<bean:message key="OIFM.docroot" />/js/Common.js'></script>
<script language="javascript">
	function checkAll() {
		document.ASMFormReport.chkSubmitOn.checked = document.ASMFormReport.selectAll.checked;
		document.ASMFormReport.chkWrittenBy.checked = document.ASMFormReport.selectAll.checked;
		document.ASMFormReport.chkDesigLW.checked = document.ASMFormReport.selectAll.checked;
		document.ASMFormReport.chkLetterContent.checked = document.ASMFormReport.selectAll.checked;
		document.ASMFormReport.chkYIS.checked = document.ASMFormReport.selectAll.checked;
		document.ASMFormReport.chkAge.checked = document.ASMFormReport.selectAll.checked;
		document.ASMFormReport.chkDivisionLW.checked = document.ASMFormReport.selectAll.checked;
		document.ASMFormReport.chkDivInCharge.checked = document.ASMFormReport.selectAll.checked;
		document.ASMFormReport.chkCategory.checked = document.ASMFormReport.selectAll.checked;
		document.ASMFormReport.chkRedirectTo.checked = document.ASMFormReport.selectAll.checked;
		document.ASMFormReport.chkRedirectOn.checked = document.ASMFormReport.selectAll.checked;
		document.ASMFormReport.chkRepliedBy.checked = document.ASMFormReport.selectAll.checked;
		document.ASMFormReport.chkRepliedOn.checked = document.ASMFormReport.selectAll.checked;
		document.ASMFormReport.chkReplyContent.checked = document.ASMFormReport.selectAll.checked;
	}

	function checkSelectAll(chkbx) {
		if (!chkbx.checked) document.ASMFormReport.selectAll.checked = false;
	}
</script>

<%try{%>
<html:form action="ASMReport.do" method="post" >
 
 <DIV id=divCalendar style="VISIBILITY: hidden; POSITION: absolute; BACKGROUND-COLOR: white; layer-background-color: white"></DIV>
            
	 <jsp:include page="/jsp/common/oifm_admin_header.jsp" flush="true" />
	<table width="98%" border="0" cellpadding="0"
		cellspacing="0">
		<tr>
			<td class="TableHead">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
				<td class="Box">
					<table width="100%" border="0" cellpadding="0"
						cellspacing="0" bgcolor="white">
						<tr class="Table_head" >
							<td colspan="5">ASM Report</td>
 						</tr>
			
						<tr height="20" >
							<td bgcolor="#F0F8FF" class="Sub_head"><input type="checkbox" name="selectAll" onclick="checkAll()">
							</td>
							<td colspan="4" bgcolor="#F0F8FF" class="Sub_head">
							Search and Export
							</td>
						 </tr>


						    <tr class="body_detail_text" height="25" valign="top" align="left" >
								<td width="5%" ><html:checkbox property="chkSubmitOn" onclick="checkSelectAll(this)"/></td>
								<td width="25%" class="Heading_Thread">Submitted on</td>
								<td width="25%" class="Heading_Thread">From <html:text property="txtSubOnFromDate" size="12" styleClass="Text" readonly="true"/>
									<a href="#" onClick="cal.select(document.forms['ASMFormReport'].txtSubOnFromDate,'txtSubOnFromDate','dd-NNN-yyyy');return false;" name="toAnchor" id="toAnchor">
									<img src='<bean:message key="OIFM.docroot" />/images/img_calendar.gif' border="0" alt="Calendar"></a></td>
								<td width="35%" class="Heading_Thread">To <html:text property="txtSubOnToDate" size="12" styleClass="Text" readonly="true"/>
									<a href="#" onClick="cal.select(document.forms['ASMFormReport'].txtSubOnToDate,'txtSubOnToDate','dd-NNN-yyyy');return false;" name="toAnchor" id="toAnchor">
									<img src='<bean:message key="OIFM.docroot" />/images/img_calendar.gif' border="0" alt="Calendar"></a></td>
						    </tr>
						    <tr class="body_detail_text" height="25">
								<td ><html:checkbox property="chkWrittenBy" onclick="checkSelectAll(this)"/></td>
								<td  class="Heading_Thread">Submitted By</td>
								<td colspan="2"><html:text property="txtWrittenBy" size="60" styleClass="Text" />
								</td>
						    </tr>
						    <tr class="body_detail_text" height="25">
								<td ><html:checkbox property="chkDesigLW" onclick="checkSelectAll(this)"/></td>
								<td class="Heading_Thread" >Designation of Letter Writer</td>
								<td colspan="2"><html:text property="txtDesigLW" size="60" styleClass="Text" />
								</td>
						    </tr>
						    <tr class="body_detail_text" height="25">
								<td >
								<html:checkbox property="chkLetterTitle" /></td>
								<td  class="Heading_Thread">Letter Title </td>
								<td colspan="2"><html:text property="hidLetterTopic" size="60" styleClass="Text" /></td>
						    </tr>
						    <tr class="body_detail_text" height="25">
								<td ><html:checkbox property="chkLetterContent" onclick="checkSelectAll(this)"/></td>
								<td  class="Heading_Thread">Letter Content </td>
								<td colspan="2"><html:text property="txtLetterContent" size="60" styleClass="Text" /></td>
						    </tr>
						    <tr class="body_detail_text" height="25">
								<td ><html:checkbox property="chkYIS" onclick="checkSelectAll(this)"/></td>
								<td  class="Heading_Thread">Years in service</td>
								<td  class="Heading_Thread">From <html:text property="txtYISFromDate" size="4" maxlength="2" styleClass="Text" /></td>
								<td  class="Heading_Thread">To <html:text property="txtYISToDate" size="4" maxlength="2" styleClass="Text" /></td>
						    </tr>

						    <tr class="body_detail_text" height="25">
								<td><html:checkbox property="chkAge" onclick="checkSelectAll(this)"/></td>
								<td class="Heading_Thread">Age</td>
								<td class="Heading_Thread">From <html:text property="txtAgeFromDate" size="4" maxlength="2" styleClass="Text" /></td>
								<td class="Heading_Thread">To <html:text property="txtAgeToDate" size="4" maxlength="2" styleClass="Text" /></td>
						    </tr>
							<tr class="body_detail_text" height="20"><td colspan="5"></td></tr>
						    <tr class="body_detail_text" height="25">
								<td ><html:checkbox property="chkDivisionLW" onclick="checkSelectAll(this)"/></td>
								<td  class="Heading_Thread">School/Division of Letter Writer</td>
								<td colspan="2">
								<html:select property="txtDivisionLW" size="4" styleClass="Text" multiple="yes"> 	
									  <html:options collection="DivisionSchoolList" property="strDDLabelId" labelProperty="strDDLabelDesc"/> 
								</html:select>		 
								</td>
						    </tr>
							<tr class="body_detail_text" height="8"><td colspan="5"></td></tr>
						    <tr class="body_detail_text" height="25">
								<td ><html:checkbox property="chkDivInCharge" onclick="checkSelectAll(this)"/></td>
								<td  class="Heading_Thread">Division in-charge of reply </td>
								<td colspan="2">
								<html:select property="cboDivInCharge" size="4" styleClass="Text" multiple="yes"> 	
									  <html:options collection="DivisionList" property="strDDLabelId" labelProperty="strDDLabelDesc"/> 
								</html:select>		 

								</td>
						    </tr>
						    <!-- Added By Rakesh for ASM Category and Replied On  -->
						    <tr class="body_detail_text" height="8"><td colspan="5"></td></tr>
						    <tr class="body_detail_text" height="25">
								<td ><html:checkbox property="chkCategory" onclick="checkSelectAll(this)"/></td>
								<td  class="Heading_Thread">Category </td>
								<td colspan="2">
								<html:select property="cboCategory" size="4" styleClass="Text" multiple="yes"> 	
									  <html:options collection="CategoryList" property="strDDLabelId" labelProperty="strDDLabelDesc"/> 
								</html:select>		 

								</td>
						    </tr>
						    
							<tr class="body_detail_text" height="8"><td colspan="5"></td></tr>
						    <tr class="body_detail_text" height="25">
								<td ><html:checkbox property="chkRedirectTo" onclick="checkSelectAll(this)"/></td>
								<td  class="Heading_Thread">Redirected To</td>
								<td colspan="2"><html:text property="txtRedirectTo" size="60" styleClass="Text" />
								<a href="#" onclick="fnSelectUser('RedirectTo')"><img src='<bean:message key="OIFM.docroot" />/images/lookup.jpg' border="0"></a>
								</td>
						    </tr>
						    <tr class="body_detail_text" height="25">
								<td ><html:checkbox property="chkRedirectOn" onclick="checkSelectAll(this)"/></td>
								<td  class="Heading_Thread">Redirected on</td>
								<td  class="Heading_Thread">From <html:text property="txtRedirectFromDate" size="12" styleClass="Text" readonly="true"/>
									<a href="#" onClick="cal.select(document.forms['ASMFormReport'].txtRedirectFromDate,'txtRedirectFromDate','dd-NNN-yyyy');return false;" name="toAnchor" id="toAnchor">
									<img src='<bean:message key="OIFM.docroot" />/images/img_calendar.gif' border="0" alt="Calendar"></a></td>
								<td  class="Heading_Thread">To <html:text property="txtRedirectToDate" size="12" styleClass="Text" readonly="true"/>
									<a href="#" onClick="cal.select(document.forms['ASMFormReport'].txtRedirectToDate,'txtRedirectToDate','dd-NNN-yyyy');return false;" name="toAnchor" id="toAnchor">
									<img src='<bean:message key="OIFM.docroot" />/images/img_calendar.gif' border="0" alt="Calendar"></a></td>
						    </tr>
						    <tr class="body_detail_text" height="25">
								<td ><html:checkbox property="chkRepliedBy" onclick="checkSelectAll(this)"/></td>
								<td  class="Heading_Thread">Replied by </td>
								<td colspan="2">
								<html:text property="txtRepliedBy" size="60" styleClass="Text" />
								<a href="#" onclick="fnSelectUser('RepliedBy')"><img src='<bean:message key="OIFM.docroot" />/images/lookup.jpg' border="0"></a>

								</td>
						    </tr>
						     <tr class="body_detail_text" height="25">
								<td ><html:checkbox property="chkRepliedOn" onclick="checkSelectAll(this)"/></td>
								<td  class="Heading_Thread">Replied on</td>
								<td  class="Heading_Thread">From <html:text property="txtReplyFromDate" size="12" styleClass="Text" readonly="true"/>
									<a href="#" onClick="cal.select(document.forms['ASMFormReport'].txtReplyFromDate,'txtReplyFromDate','dd-NNN-yyyy');return false;" name="toAnchor" id="toAnchor">
									<img src='<bean:message key="OIFM.docroot" />/images/img_calendar.gif' border="0" alt="Calendar"></a></td>
								<td  class="Heading_Thread">To <html:text property="txtReplyToDate" size="12" styleClass="Text" readonly="true"/>
									<a href="#" onClick="cal.select(document.forms['ASMFormReport'].txtReplyToDate,'txtReplyToDate','dd-NNN-yyyy');return false;" name="toAnchor" id="toAnchor">
									<img src='<bean:message key="OIFM.docroot" />/images/img_calendar.gif' border="0" alt="Calendar"></a></td>
						    </tr>
						    
						    <tr class="body_detail_text" height="25">
								<td ><html:checkbox property="chkReplyContent" onclick="checkSelectAll(this)"/></td>
								<td  class="Heading_Thread">Reply Content </td>
								<td colspan="2"><html:text property="txtReplycontent" size="60" styleClass="Text" /></td>
						    </tr>
						    <tr class="body_detail_text" height="60">
								<td colspan="5" align="center">								
								<a href="#" onclick="fnSearch()"><img src='<bean:message key="OIFM.docroot" />/images/btn_Search.gif' border="0" alt="Search"></a>
								<a href="#" onclick="fnCancel()"><img src='<bean:message key="OIFM.docroot" />/images/but_reset.gif' border="0" alt="Reset"></a>
								</td>
						    </tr>
				      </table>
					<br><br>
				
<!-- start-->
<logic:notEqual name="TotRec" value="0">
								<!--div STYLE="overflow-x:auto;height=100%;width=100%;border-color:red;border:thin"-->
	<DIV align=left style='border: thin;  width: 98%; overflow: scroll;'> 
								<table border="0" class="boxtable" width="800%">
								<tr class="subhead1">
								<td>Title
								<%if(OIUtilities.replaceNull(request.getParameter("hidSortBy")).equalsIgnoreCase("TOPIC")){%>
									<a href="#" style="cursor:hand" onclick="fnSortBy('TOPIC')"><img src='<bean:message key="OIFM.docroot" />/images/DownArrow.gif' alt="Sort" width="15" height="15" border="0"/></a>
								<%}else{%>
									<a href="#" style="cursor:hand" onclick="fnSortBy('TOPIC')"><img src='<bean:message key="OIFM.docroot" />/images/btn_rightarrow.gif' alt="Sort" width="15" height="15" border="0" /></a>
								<%}%>
								</td>
								<%if(request.getParameter("chkSubmitOn")!=null){%>
								<td>Submitted On
								<%if(OIUtilities.replaceNull(request.getParameter("hidSortBy")).equalsIgnoreCase("SUBMITTEDON")){%>
									<a href="#" style="cursor:hand" onclick="fnSortBy('SUBMITTEDON')"><img src='<bean:message key="OIFM.docroot" />/images/DownArrow.gif' alt="Sort" width="15" height="15" border="0"/></a>
								<%}else{%>
									<a href="#" style="cursor:hand" onclick="fnSortBy('SUBMITTEDON')"><img src='<bean:message key="OIFM.docroot" />/images/btn_rightarrow.gif' alt="Sort" width="15" height="15" border="0" /></a>
								<%}%>
								</td>
								<%}if(request.getParameter("chkWrittenBy")!=null){%>
								<td>Submitted By
								<%if(OIUtilities.replaceNull(request.getParameter("hidSortBy")).equalsIgnoreCase("CREATEDBY")){%>
									<a href="#" style="cursor:hand" onclick="fnSortBy('CREATEDBY')"><img src='<bean:message key="OIFM.docroot" />/images/DownArrow.gif' alt="Sort" width="15" height="15" border="0"/></a>
								<%}else{%>
									<a href="#" style="cursor:hand" onclick="fnSortBy('CREATEDBY')"><img src='<bean:message key="OIFM.docroot" />/images/btn_rightarrow.gif' alt="Sort" width="15" height="15" border="0" /></a>
								<%}%>
								</td>
								<%}if(request.getParameter("chkDesigLW")!=null){%>
								<td>Designation
								<%if(OIUtilities.replaceNull(request.getParameter("hidSortBy")).equalsIgnoreCase("AUTHOR_DESIGNATION")){%>
									<a href="#" style="cursor:hand" onclick="fnSortBy('AUTHOR_DESIGNATION')"><img src='<bean:message key="OIFM.docroot" />/images/DownArrow.gif' alt="Sort" width="15" height="15" border="0"/></a>
								<%}else{%>
									<a href="#" style="cursor:hand" onclick="fnSortBy('AUTHOR_DESIGNATION')"><img src='<bean:message key="OIFM.docroot" />/images/btn_rightarrow.gif' alt="Sort" width="15" height="15" border="0" /></a>
								<%}%>
								</td>
								<%}if(request.getParameter("chkDivisionLW")!=null){%>
								<td>Division
								<%if(OIUtilities.replaceNull(request.getParameter("hidSortBy")).equalsIgnoreCase("AUTHOR_DIVISION_SCHOOL_DESC")){%>
									<a href="#" style="cursor:hand" onclick="fnSortBy('AUTHOR_DIVISION_SCHOOL_DESC')"><img src='<bean:message key="OIFM.docroot" />/images/DownArrow.gif' alt="Sort" width="15" height="15" border="0"/></a>
								<%}else{%>
									<a href="#" style="cursor:hand" onclick="fnSortBy('AUTHOR_DIVISION_SCHOOL_DESC')"><img src='<bean:message key="OIFM.docroot" />/images/btn_rightarrow.gif' alt="Sort" width="15" height="15" border="0" /></a>
								<%}%>
								</td>
								<%}if(request.getParameter("chkLetterContent")!=null){%>
								<td>Letter Content
								</td>
								<%}if(request.getParameter("chkYIS")!=null){%>
								<td>YIS
								<%if(OIUtilities.replaceNull(request.getParameter("hidSortBy")).equalsIgnoreCase("YIS")){%>
									<a href="#" style="cursor:hand" onclick="fnSortBy('YIS')"><img src='<bean:message key="OIFM.docroot" />/images/DownArrow.gif' alt="Sort" width="15" height="15" border="0"/></a>
								<%}else{%>
									<a href="#" style="cursor:hand" onclick="fnSortBy('YIS')"><img src='<bean:message key="OIFM.docroot" />/images/btn_rightarrow.gif' alt="Sort" width="15" height="15" border="0" /></a>
								<%}%>
								</td>
								<%}if(request.getParameter("chkAge")!=null){%>
								<td>Age
								<%if(OIUtilities.replaceNull(request.getParameter("hidSortBy")).equalsIgnoreCase("AGE")){%>
									<a href="#" style="cursor:hand" onclick="fnSortBy('AGE')"><img src='<bean:message key="OIFM.docroot" />/images/DownArrow.gif' alt="Sort" width="15" height="15" border="0"/></a>
								<%}else{%>
									<a href="#" style="cursor:hand" onclick="fnSortBy('AGE')"><img src='<bean:message key="OIFM.docroot" />/images/btn_rightarrow.gif' alt="Sort" width="15" height="15" border="0" /></a>
								<%}%>
								</td>
								<%}if(request.getParameter("chkDivInCharge")!=null){%>
								<td>Div-in-charge
								<%if(OIUtilities.replaceNull(request.getParameter("hidSortBy")).equalsIgnoreCase("DIVISION_INCHARGE_DESCRIPTION")){%>
									<a href="#" style="cursor:hand" onclick="fnSortBy('DIVISION_INCHARGE_DESCRIPTION')"><img src='<bean:message key="OIFM.docroot" />/images/DownArrow.gif' alt="Sort" width="15" height="15" border="0"/></a>
								<%}else{%>
									<a href="#" style="cursor:hand" onclick="fnSortBy('DIVISION_INCHARGE_DESCRIPTION')"><img src='<bean:message key="OIFM.docroot" />/images/btn_rightarrow.gif' alt="Sort" width="15" height="15" border="0" /></a>
								<%}%>
								</td>
								<%}if(request.getParameter("chkCategory")!=null){%>
								<td>Category
								<%if(OIUtilities.replaceNull(request.getParameter("hidSortBy")).equalsIgnoreCase("CATEGORY")){%>
									<a href="#" style="cursor:hand" onclick="fnSortBy('CATEGORY')"><img src='<bean:message key="OIFM.docroot" />/images/DownArrow.gif' alt="Sort" width="15" height="15" border="0"/></a>
								<%}else{%>
									<a href="#" style="cursor:hand" onclick="fnSortBy('CATEGORY')"><img src='<bean:message key="OIFM.docroot" />/images/btn_rightarrow.gif' alt="Sort" width="15" height="15" border="0" /></a>
								<%}%>
								</td>
								<%}if(request.getParameter("chkRedirectTo")!=null){%>
								<td >Redirect To
								<%if(OIUtilities.replaceNull(request.getParameter("hidSortBy")).equalsIgnoreCase("REDIRECTEDTO")){%>
									<a href="#" style="cursor:hand" onclick="fnSortBy('REDIRECTEDTO')"><img src='<bean:message key="OIFM.docroot" />/images/DownArrow.gif' alt="Sort" width="15" height="15" border="0"/></a>
								<%}else{%>
									<a href="#" style="cursor:hand" onclick="fnSortBy('REDIRECTEDTO')"><img src='<bean:message key="OIFM.docroot" />/images/btn_rightarrow.gif' alt="Sort" width="15" height="15" border="0" /></a>
								<%}%>
								</td>
								<%}if(request.getParameter("chkRedirectOn")!=null){%>
								<td>Redirect On
								<%if(OIUtilities.replaceNull(request.getParameter("hidSortBy")).equalsIgnoreCase("REDIRECTEDON")){%>
									<a href="#" style="cursor:hand" onclick="fnSortBy('REDIRECTEDON')"><img src='<bean:message key="OIFM.docroot" />/images/DownArrow.gif' alt="Sort" width="15" height="15" border="0"/></a>
								<%}else{%>
									<a href="#" style="cursor:hand" onclick="fnSortBy('REDIRECTEDON')"><img src='<bean:message key="OIFM.docroot" />/images/btn_rightarrow.gif' alt="Sort" width="15" height="15" border="0" /></a>
								<%}%>
								</td>
								<%}if(request.getParameter("chkRepliedBy")!=null){%>
								<td>Replied By
								<%if(OIUtilities.replaceNull(request.getParameter("hidSortBy")).equalsIgnoreCase("REPLIEDBY")){%>
									<a href="#" style="cursor:hand" onclick="fnSortBy('REPLIEDBY')"><img src='<bean:message key="OIFM.docroot" />/images/DownArrow.gif' alt="Sort" width="15" height="15" border="0"/></a>
								<%}else{%>
									<a href="#" style="cursor:hand" onclick="fnSortBy('REPLIEDBY')"><img src='<bean:message key="OIFM.docroot" />/images/btn_rightarrow.gif' alt="Sort" width="15" height="15" border="0" /></a>
								<%}%>
								</td>
								<%}if(request.getParameter("chkRepliedOn")!=null){%>
								<td>Replied On
								<%if(OIUtilities.replaceNull(request.getParameter("hidSortBy")).equalsIgnoreCase("REPLIEDON")){%>
									<a href="#" style="cursor:hand" onclick="fnSortBy('REPLIEDON')"><img src='<bean:message key="OIFM.docroot" />/images/DownArrow.gif' alt="Sort" width="15" height="15" border="0"/></a>
								<%}else{%>
									<a href="#" style="cursor:hand" onclick="fnSortBy('REPLIEDON')"><img src='<bean:message key="OIFM.docroot" />/images/btn_rightarrow.gif' alt="Sort" width="15" height="15" border="0" /></a>
								<%}%>
								</td>
								<%}if(request.getParameter("chkReplyContent")!=null){%>
								<td>Reply Content
								</td>
								<%}%>
								</tr>
								
								<logic:iterate id="objBV" name="search_results" type="com.oifm.asm.ASMBVReport" scope="request" indexId="rowNum" >
						      <tr class="BodyText">
									<td valign="top">
									<a href="#" style="cursor:hand" onclick='fnCallDetails(<bean:write name="objBV" property="hidLetterID"/>)'>
									<bean:write name="objBV" property="hidLetterTopic"/></a></td>
									<%if(request.getParameter("chkSubmitOn")!=null){%>
									<td valign="top"><bean:write name="objBV" property="txtSubOnFromDate"/></td>
									<%}if(request.getParameter("chkWrittenBy")!=null){%>
									<td valign="top"><bean:write name="objBV" property="txtWrittenBy"/></td>
									<%}if(request.getParameter("chkDesigLW")!=null){%>
									<td valign="top"><bean:write name="objBV" property="txtDesigLW"/></td>
									<%}if(request.getParameter("chkDivisionLW")!=null){%>
									<td valign="top"><bean:write name="objBV" property="txtDivisionLW"/></td>
									<%}if(request.getParameter("chkLetterContent")!=null){%>
									<td valign="top"><bean:write name="objBV" property="txtLetterContent" filter="false"/></td>
									<%}if(request.getParameter("chkYIS")!=null){%>
									<td valign="top"><bean:write name="objBV" property="txtYISFromDate"/></td>
									<%}if(request.getParameter("chkAge")!=null){%>
									<td valign="top"><bean:write name="objBV" property="txtAgeFromDate"/></td>
									<%}if(request.getParameter("chkDivInCharge")!=null){%>
									<td valign="top"><bean:write name="objBV" property="cboDivInCharge"/></td>
									<%}if(request.getParameter("chkCategory")!=null){%>
									<td valign="top"><bean:write name="objBV" property="cboCategory"/></td>
									<%}if(request.getParameter("chkRedirectTo")!=null){%>
									<td valign="top"><bean:write name="objBV" property="txtRedirectTo"/></td>
									<%}if(request.getParameter("chkRedirectOn")!=null){%>
									<td valign="top"><bean:write name="objBV" property="txtRedirectFromDate"/></td>
									<%}if(request.getParameter("chkRepliedBy")!=null){%>
									<td valign="top"><bean:write name="objBV" property="txtRepliedBy"/></td>
									<%}if(request.getParameter("chkRepliedOn")!=null){%>
									<td valign="top"><bean:write name="objBV" property="txtReplyFromDate"/></td>
									<%}if(request.getParameter("chkReplyContent")!=null){%>
									<td valign="top"><bean:write name="objBV" property="txtReplycontent" filter="false"/></td>
									<%}%>
						      </tr>
							</logic:iterate>
							
								</table>
								</div>
								
							</logic:notEqual>


<!---end -->				

			<!-- If No records found  Start-->
					<logic:equal name="TotRec" value="0">
						<br><br>
							<p align="center">
							<logic:equal name="hiddenAction" value="SearchResult">
								<strong><font size="3">No Records Found.</font></strong>
							</logic:equal>
							</p>
					</logic:equal>
			<!-- If No records found  End -->

		<!--pagination start  -->
		<logic:greaterThan name="totalPage" value="1">
		<br>
			<table border="0" cellpadding="2" cellspacing="0" class="BodyText" align="left">
				<tr>
					<td nowrap class="Boxinside_text"> 
						Page 
							<bean:write name="pageNo" scope="request" /> 
						of 
							<bean:write name="totalPage" scope="request" />
					</td>
					<logic:present name="previousSet" scope="request">
						<logic:equal name="previousSet" scope="request" value="true">
							<td nowrap class="BD_2">&lt;</td>
							<td nowrap class="BD_3"> 
								<a href='#' onclick="javascript:fnSubmitPageReport('<bean:write name="previousPage" scope="request"/>');">
									&laquo;Previous</a>
							</td>
						</logic:equal>
					</logic:present>


					<!--<td nowrap class="BD_1">1</td>-->
					<logic:present name="arPage" scope="request">
						<logic:iterate id="no" name="arPage" scope="request">
							<%
								String currentPage=(String) request.getAttribute("pageNo");
								String temp = (String) no;
								if (! currentPage.trim().equals(temp.trim()))
								{
							%>
							<td nowrap class="BD_2">
								<a href='#' onclick="javascript:fnSubmitPageReport('<bean:write name="no" />');">		
									<bean:write name="no" /></a>
							</td>
							<%
								}
								else
								{
							%>
							<td nowrap class="BD_1">
									<bean:write name="no" />
							</td>
							<%
								}
							%>
						</logic:iterate>
					</logic:present>
					<logic:present name="nextSet" scope="request">
						<logic:equal name="nextSet" scope="request" value="true">
							<td nowrap class="BD_2">&gt;</td>
							<td nowrap class="BD_3"> 
								<a href='#' onclick="javascript:fnSubmitPageReport('<bean:write name="nextPage" scope="request"/>');">
									Next&raquo;</a>
							</td>
						</logic:equal>
					</logic:present>

		</tr>
			</table>
			<br><br>
		</logic:greaterThan>
		<!-- Pagination end-->
<logic:notEqual name="TotRec" value="0">

						<table width="100%">
						<tr>
						<td align="center">
							<a href="#" onclick="fnExport()"><img src='<bean:message key="OIFM.docroot" />/images/btn_export.gif' border="0" alt="Export"></a>								
						</td>
						</tr>
							
						</table>
</logic:notEqual>


 			</td>
		</tr>
	</table>
 
	</td>
		</tr>
	</table>
 

<!-- This hidden variable holds the letter id-->
<html:hidden property="hidLetterID"/>	
<html:hidden property="hiddenAction"/>

<html:hidden property="hidLink" />
<html:hidden property="hidSortBy" />
<html:hidden property="hidDivIncharge" />
<html:hidden property="hidSchDiv" />
<html:hidden property="hidPageDesc"/>
<html:hidden property="hidCategory" />	

<!-- -->
 <!-- This is for calendar -->


</html:form> 

<%}catch(Exception e){out.println(e);}%>

<script>
	//To Retain HighLight the list box
	fnHighLightcombo();
	document.ASMFormReport.chkLetterTitle.checked=true;
	document.ASMFormReport.chkLetterTitle.disabled=true;

</script>