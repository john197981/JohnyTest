<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<%@ page import="java.util.ArrayList" %>
<%@ page language="java" import="com.oifm.blog.OIBABlogAdminCategory"%>
<%@ page language="java" import="com.oifm.blog.OIBlogAdminCreateEntryForm"%>

<%
	OIBlogAdminCreateEntryForm entryForm = new OIBlogAdminCreateEntryForm();
	if(session.getAttribute("entryForm")!= null){
		entryForm = (OIBlogAdminCreateEntryForm)session.getAttribute("entryForm");
	}

%>

<script language="JavaScript" type="text/JavaScript">
	var docRoot = '<bean:message key="OIFM.docroot" />';
</script>

		<style type="text/css">
			<!--
			@import url("<bean:message key="OIFM.docroot" />/css/MySay.css");
 			.style2 
			{
				color: #FFFFFF;
				font-weight: bold;
			}
			-->

   		</style>
<script language="Javascript" src='<bean:message key="OIFM.docroot" />/js/RTFEditorBlog.js'></script>
<script language="javascript" src='<bean:message key="OIFM.docroot" />/js/validate.js'></script>
<script language="javascript" src='<bean:message key="OIFM.docroot" />/js/Common.js'></script>

<link href='<bean:message key="OIFM.docroot" />/css/oicalendar.css' rel="stylesheet" type="text/css">
<link href='<bean:message key="OIFM.docroot" />/css/simpleTxtFormating.css' rel="stylesheet" type="text/css">

<script language="javascript">
	
	function fnSubmit(actionName,hiddenAction)
	{
		

		if(document.OIBlogAdminCreateEntryForm.entryTitle.value == "")
		{
			alert("Please fill in the entry title");
			document.OIBlogAdminCreateEntryForm.entryTitle.focus();
			return;
		}

		if(document.OIBlogAdminCreateEntryForm.category.value == 0)
		{
			alert("Please select a category");
			document.OIBlogAdminCreateEntryForm.category.focus();
			return;
		}

		if(document.OIBlogAdminCreateEntryForm.entryBody.value == "")
		{
			alert("Please fill in the entry body");
			document.OIBlogAdminCreateEntryForm.entryBody.focus();
			return;
		}

		

		document.OIBlogAdminCreateEntryForm.hiddenAction.value=hiddenAction;
		document.OIBlogAdminCreateEntryForm.action=actionName;
		document.OIBlogAdminCreateEntryForm.submit();
	}

	function fnReset()
	{
		editor_setHTML('taContentId',hidEntryBody);
		document.OIBlogAdminCreateEntryForm.reset();
	}

	function fnLoad(){
		
		var frm = document.OIBlogAdminCreateEntryForm;

		if(frm.hAllow.value == "Y"){
		frm.acceptComments.checked = true;
		}

		if(frm.hCategory.value != '0'){
			for (iLoop = 0; iLoop< frm.category.options.length; iLoop++)
			{    
			  if (frm.category.options[iLoop].value == frm.hCategory.value)
			  {
				frm.category.options[iLoop].selected = true;
				break;
			  }
			}
		}
		

		for (iLoop = 0; iLoop< frm.status.options.length; iLoop++)
		{    
		  if (frm.status.options[iLoop].value == frm.hStatus.value)
		  {
			frm.status.options[iLoop].selected = true;
			break;
		  }
		}
		


	}

	function fnSelectTags(){
		objActiveElem = document.getElementById('spanTagName');
			var strUrl = '/AddTags.do?hiddenAction=addTags&module=B';
			help_window  = window.open('<bean:message key="OIFM.contextroot" />'+strUrl,'SelectTagss','width=500,height=620,left=0,top=0,resizable=yes,scrollbars=yes');
			help_window.focus();
		}
/*
	var htmlMode = true;
	function toggleMode(){
		if (htmlMode)
			editor_setmode('taContentId', 'textedit');
		else
			editor_setmode('taContentId', 'wysiwyg');

		htmlMode = !htmlMode;
	}
	*/
	var maxlimit = 500;
function fnTextCounter(field, countfield)
{
	if (field.value.length > maxlimit)
	{
		field.value = field.value.substring(0, maxlimit);
	}
	else
	{
		countfield.value = maxlimit - field.value.length;
	}
}
</script>

<SCRIPT LANGUAGE="JavaScript" src='<bean:message key="OIFM.docroot" />/js/oicalendar.js' ></SCRIPT>


<DIV id=divCalendar style="VISIBILITY: hidden; POSITION: absolute; BACKGROUND-COLOR: white; layer-background-color: white"></DIV>
 
<body onLoad="fnLoad();">

<table width="857" border="0" cellspacing="0" cellpadding="0" >
	<html:form action="BlogAdminNewEntry" method="post" enctype="multipart/form-data">
		<%  if(session.getAttribute("entryForm")!= null){
		%>
			<html:hidden property="strTagIdList" value="<%=entryForm.getStrTagIdList()%>"/>
		<%	}
			else
			{
		%>
			<html:hidden property="strTagIdList" />
		<%	}
		%>
	<input type="hidden" name="createEntryAction"/>
 	<tr>
	<td align="left" valign="top" bgcolor="#f7f8fc">
 		<table width="857" height="400" border="0" cellspacing="0" cellpadding="0">
		<%
			if(request.getAttribute("Mode")== "update")
			{
				String strMode = (String)request.getAttribute("Mode");
				String strEntryId = (String)session.getAttribute("entryid");
				//out.println(entryForm.getEntryTitle());
				if(strMode.equals("update"))
				{
		%>
		<tr>
			<td colspan="4" width="857" class="Orange_fade">Edit Entry</td>
		</tr>
		<logic:present name="error" scope="request">
		<tr>
			<td class="Mainheader" valign="top" colspan="4">
				<b><bean:message name="error" scope="request"/></b>
			<td>
		</tr>
		</logic:present>
   		<tr>
    		<td width="30" class="Grey_fade">&nbsp;</td>
        	<td width="648" class="Grey_fade" valign="top">
        		<table border="0" cellspacing="0" cellpadding="0">
        		<tr>
        			<td colspan="4" >&nbsp;</td>
        		</tr>
        		<tr>
        			<td width="100" class="Sub_head">Entry Title :</td>
        			<td align="left" width="200" >
        				<html:text property="entryTitle" styleClass="text_box" size="50" maxlength="70" value="<%=entryForm.getEntryTitle()%>"></html:text>
 					</td>
 					<td width="100">&nbsp;</td>
 					<td width="200" align="left">&nbsp;</td>
        		</tr>
        		<tr>
        			<td colspan="4" >&nbsp;</td>
        		</tr>
        		<tr>
        			<td class="Sub_head">Category :</td>
        			<td align="left">
						<!-- added by edmund -->
						<html:select property="category" styleClass="text_box">
								<html:option value='0'>Please Select a Category</html:option>
								<%
									ArrayList alList = new ArrayList();
									OIBABlogAdminCategory objCategory = null;

									if(request.getAttribute("categoriesList")!=null){
										alList = (ArrayList)request.getAttribute("categoriesList");

										for(int k=0; k<alList.size(); k++){
											objCategory = new OIBABlogAdminCategory ();
											objCategory = (OIBABlogAdminCategory)alList.get(k);

											int catId = objCategory.getCategoryId().intValue();
											String id = String.valueOf(catId);
											String name = objCategory.getCategory();
								%>
								<html:option value="<%=id%>"><%=name%></html:option>
								<%
										}
									}
								%>
						</html:select>
						<!-- end -->
					</td>
 					<td class="Sub_head">&nbsp;</td>
 					<td align="left">&nbsp;
					</td>

        		</tr>
        		<tr>
        			<td colspan="4" >&nbsp;</td>
        		</tr>

        		<tr>
        			<td class="Sub_head" colspan="4">Entry Body :</td>
        		</tr>
        		<tr>
        			<td colspan="4" >&nbsp;</td>
        		</tr>

        		<tr>
        			<td align="left" colspan="4" class="body_extract_text" >
						<html:textarea property="entryBody" styleId="taContentId" cols="70" rows="10" styleClass="text_area" value="<%=entryForm.getEntryBody()%>"/>

						<script language="javascript">
							var config = new Object();
							config.bodyStyle = 'background-color: white; font-family: "Verdana"; font-size: x-small;';
							config.debug = 0;
							editor_generate('taContentId',config);
					</script>
 					</td>
 					
 					
        		</tr>
        		<tr>
        			<td colspan="4" >&nbsp;</td>
        		</tr>
        		<tr>
        			<td class="Sub_head">Entry Excerpt :</td>
        			<td align="left" colspan="3">
        				<html:textarea property="entryExcept" 
							styleClass="text_area" 
							cols="50" rows="3" value="<%=entryForm.getEntryExcept()%>" 
							onkeydown="fnTextCounter(this.form.entryExcept,this.form.numleft1);" 
							onkeyup="fnTextCounter(this.form.entryExcept,this.form.numleft1);"/>
						<div align="left">
							<font color="#005BCC" class="Special_body_link">
								No. of characters remaining: 
								<input name="numleft1" type="text" size="5" maxlength="3" value="500" disabled  class="text_box">
							</font>
						</div>
 					</td>
        		</tr>
        		<tr>
        			<td colspan="4" >&nbsp;</td>
        		</tr>
				<tr>
					<td colspan=4>
						<table>
							<tr>
								<td valign="top" class="Sub_head">Tags :&nbsp;&nbsp;</td>
								<td>
								<span id="spanTagName" class="body_extract_text">
								<logic:notEqual name="entryForm" property="tags" value="">
									<bean:write name="entryForm" property="tags"/>
								</logic:notEqual>
								<logic:equal name="entryForm" property="tags" value="">
									Please click on "Select Tags" to select the key tag words for the entry
								</logic:equal>
								</span>
								</td>
								<td valign="bottom" class="body_extract_text">
								<a href="javascript:;" onClick="javascript:fnSelectTags();"><img src='<bean:message key="OIFM.docroot" />/images/select-tags.gif' border="0" alt="Add Tags" /></a>
								</td>
							</tr>
						</table>
					</td>
				</tr>        
        		<tr>
        			<td colspan="4" >&nbsp;</td>
        		</tr>
        		<tr>
        			<td class="Sub_head">Status :</td>
        			<td align="left"  >
						<html:select property="status" styleClass="text_box">
        					<html:option value="P">Publish</html:option>
							<html:option value="D">Draft</html:option>
						</html:select>
 					</td>
 					<td >&nbsp;</td>
 					<td  align="left" class="Sub_head"><html:checkbox property="acceptComments"></html:checkbox>&nbsp;&nbsp;Accept Comments</td>
        		</tr>
				<tr>
        			<td colspan="4" >&nbsp;</td>
        		</tr>
				<%
						if(session.getAttribute("pictureName")==null)
						{
				%>
				<tr>
        			<td class="Sub_head">Image :</td>
        			<td align="left" colspan="3" class="Special_body_link" >
        				<html:file property="fileUpload"></html:file> &nbsp;(Max 200 KB)
 					</td>
        		</tr>
				<tr>
        			<td colspan="4" >&nbsp;</td>
        		</tr>
				
				<%		}
				%>
				<%
					if(session.getAttribute("pictureName")!=null)
					{
						String picName = (String)session.getAttribute("pictureName");
				%>
				<tr>
        			<td class="Sub_head">Existing :</td>
					<td colspan="3"><img src="<bean:message key="OIFM.contextroot" />/BlogHome.do?hiddenAction=READ_PICTURE&picFileName=<%=picName%>"></td>
        		</tr>
				<tr>
					<td></td>
					<td colspan="3" class="Special_body_link"><a href="#" class="Menu_text"onclick="javascript:fnSubmit('<bean:message key="OIFM.contextroot" />/BlogAdminNewEntry.do?hiddenAction=removePhoto&entryId=<%=strEntryId%>&picFileName=<%=picName%>','removePhoto');">Remove Image</a></td>
				</tr>
				<%
					}
				%>
        		<tr>
        			<td colspan="4" >&nbsp;</td>
        		</tr>
				<tr>
        			<td class="Sub_head">&nbsp;</td>
        			<td align="left"  >
        		<!--		<html:submit property="submitEntry">Submit</html:submit>   -->
        			<a href="#" class="Menu_text" onclick="javascript:fnSubmit('<bean:message key="OIFM.contextroot" />/BlogAdminNewEntry.do?hiddenAction=update&entryId=<%=strEntryId%>','update');"><img src='<bean:message key="OIFM.docroot" />/images/but_submit.gif' border="0" alt="Submit" /></a>
					<a href="#" onClick='fnReset();'><img src='<bean:message key="OIFM.docroot"/>/images/btn_Clear.gif' border="0" tabindex="42" alt = "Reset"></a>        			
 					</td>
 					<td colspan="2">
 					</td>
 					
        		</tr>
		<%		}
			}
			else
			{
		%>
		<tr>
  		<td colspan="4" width="857" class="Orange_fade">Create New Entry</td>
  	</tr>
	<logic:present name="error" scope="request">
		<tr>
			<td class="Mainheader" valign="top" colspan="4">
				<b><bean:message name="error" scope="request"/></b>
			<td>
		</tr>
		</logic:present>
   		<tr>
    		<td width="30" class="Grey_fade">&nbsp;</td>
        	<td width="648" class="Grey_fade" valign="top">
        		<table border="0" cellspacing="0" cellpadding="0">
        		<tr>
        			<td colspan="4" >&nbsp;</td>
        		</tr>
        		<tr>
        			<td width="100" class="Sub_head">Entry Title :</td>
        			<td align="left" width="200" >
        				<html:text property="entryTitle" styleClass="text_box" size="50" maxlength="65"></html:text>
 					</td>
 					<td width="100">&nbsp;</td>
 					<td width="200" align="left">&nbsp;</td>
        		</tr>
				
        		<tr>
        			<td colspan="4" >&nbsp;</td>
        		</tr>
        		<tr>
        			<td class="Sub_head">Category :</td>
        			<td align="left">
						<!-- added by edmund -->
						<html:select property="category" styleClass="text_box">
								<html:option value='0'>Please Select a Category</html:option>
								<%
									ArrayList alList = new ArrayList();
									OIBABlogAdminCategory objCategory = null;

									if(request.getAttribute("categoriesList")!=null){
										alList = (ArrayList)request.getAttribute("categoriesList");

										for(int k=0; k<alList.size(); k++){
											objCategory = new OIBABlogAdminCategory ();
											objCategory = (OIBABlogAdminCategory)alList.get(k);

											int catId = objCategory.getCategoryId().intValue();
											String id = String.valueOf(catId);
											String name = objCategory.getCategory();
								%>
								<html:option value="<%=id%>"><%=name%></html:option>
								<%
										}
									}
								%>
						</html:select>
						<!-- end -->
					</td>
 					<td class="Sub_head">&nbsp;</td>
 					<td align="left">&nbsp;
					</td>

        		</tr>
        		<tr>
        			<td colspan="4" >&nbsp;</td>
        		</tr>

        		<tr>
        			<td class="Sub_head" colspan="4">Entry Body :</td>
        		</tr>
        		<tr>
        			<td colspan="4" >&nbsp;</td>
        		</tr>

        		<tr>
        			<td align="left" colspan="4" class="body_extract_text" >
						<html:textarea property="entryBody" styleId="taContentId" cols="70" rows="10" styleClass="text_area"/>

						<script language="javascript">
							var config = new Object();
							config.bodyStyle = 'background-color: white; font-family: "Verdana"; font-size: x-small;';
							config.debug = 0;
							editor_generate('taContentId',config);
					</script>
 					</td>
 					
 					
        		</tr>
        		<tr>
        			<td colspan="4" >&nbsp;</td>
        		</tr>
        		<tr>
        			<td class="Sub_head">Entry Excerpt :</td>
        			<td align="left" colspan="3">
        				<html:textarea property="entryExcept" styleClass="text_area" cols="50" rows="3" 
							onkeydown="fnTextCounter(this.form.entryExcept,this.form.numleft1);" 
							onkeyup="fnTextCounter(this.form.entryExcept,this.form.numleft1);"/>
						<div align="left" class="Special_body_link">
								No. of characters remaining: 
								<input name="numleft1" type="text" size="5" maxlength="3" value="500" disabled  class="text_box">
							</div>
 					</td>
        		</tr>
        		<tr>
        			<td colspan="4" >&nbsp;</td>
        		</tr>
				<tr>
					<td colspan=4>
						<table>
							<tr>
								<td valign="top" class="Sub_head">Tags :&nbsp;&nbsp;</td>
								<td>
								<span id="spanTagName" class="body_extract_text">
								<logic:notEqual name="OIBlogAdminCreateEntryForm" property="tags" value="">
									<bean:write name="OIBlogAdminCreateEntryForm" property="tags"/>
								</logic:notEqual>
								<logic:equal name="OIBlogAdminCreateEntryForm" property="tags" value="">
									Please click on "Select Tags" to select the key tag words for the entry
								</logic:equal>
								</span>
								</td>
								<td valign="bottom" class="body_extract_text">
								<a href="javascript:;" onClick="javascript:fnSelectTags();"><img src='<bean:message key="OIFM.docroot" />/images/select-tags.gif' border="0" alt="Add Tags" /></a>
								</td>
							</tr>
						</table>
					</td>
				</tr>
        		<tr>
        			<td colspan="4" >&nbsp;</td>
        		</tr>
        		<tr>
        			<td class="Sub_head">Status :</td>
        			<td align="left"  >
						<html:select property="status" styleClass="text_box">
							<html:option value="D">Draft</html:option>
        					<html:option value="P">Publish</html:option>
						</html:select>
 					</td>
 					<td>&nbsp;</td>
 					<td  align="left" class="Sub_head"><html:checkbox property="acceptComments"></html:checkbox>&nbsp;&nbsp;Accept Comments</td>
        		</tr>
        		<tr>
        			<td colspan="4" >&nbsp;</td>
        		</tr>
				<tr>
        			<td class="Sub_head">Image :</td>
        			<td align="left" colspan="3" class="Special_body_link">
        				<html:file property="fileUpload"></html:file>&nbsp; (Max 200 KB)
 					</td>
        		</tr>
        		<tr>
        			<td colspan="4" >&nbsp;</td>
        		</tr>
        		<tr>
        			<td class="Sub_head">&nbsp;</td>
        			<td align="left"  >
        		<!--		<html:submit property="submitEntry">Submit</html:submit>   -->
        			<a href="#" class="Menu_text" onclick="javascript:fnSubmit('<bean:message key="OIFM.contextroot" />/BlogAdminNewEntry.do?hiddenAction=create','create');"><img src='<bean:message key="OIFM.docroot" />/images/but_submit.gif' border="0" alt="Submit" /></a>
					<a href="#" onClick='fnReset();'><img src='<bean:message key="OIFM.docroot"/>/images/btn_Clear.gif' border="0" tabindex="42" alt = "Reset"></a>        			
 					</td>
 					<td colspan="2">
 					</td>
 					
        		</tr>
		<%		}//end else
		%>
				<!-- added by edmund -->
				
        		</table>
        	</td>
            <td width="16" class="Blue">&nbsp;</td>
            <td width="193" rowspan="2" align="left" valign="top" class="Blue">
				<jsp:include page="/jsp/blog/BlogAdminRightMenu.jsp" flush="true"> <jsp:param name="pageName" value="Home" /> </jsp:include>
			</td>
        </tr>
      

        </table>
	</td>
</tr>
<html:hidden name="OIBlogAdminCreateEntryForm" property="hiddenAction" />
<input type="hidden" name="hStatus" value="<%=entryForm.getStatus()%>"/>
<input type="hidden" name="hAllow" value="<%=entryForm.getAcceptComments()%>"/>
<input type="hidden" name="hCategory" value="<%=entryForm.getCategory()%>"/>

</html:form>
<script language="javascript">
	var hidEntryBody = document.OIBlogAdminCreateEntryForm.entryBody.value;
	fnTextCounter(document.OIBlogAdminCreateEntryForm.entryExcept,document.OIBlogAdminCreateEntryForm.numleft1);
</script>
</table>

</body>
 



