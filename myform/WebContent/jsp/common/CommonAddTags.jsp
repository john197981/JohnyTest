<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="com.oifm.blog.OIBlogConstants" %>
<%@ page language="java" import="com.oifm.blog.OIBABlogAdminCategory"%>
<script type="text/javascript">
function noenter() {
	//if(document.OIFormBlogTag.tagName.value == "" && window.event.keyCode == 13)
		return !(window.event && window.event.keyCode == 13); 
  }
</script>

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
<script language="Javascript" src='<bean:message key="OIFM.docroot" />/js/RTFEditorASM.js'></script>
<script language="javascript" src='<bean:message key="OIFM.docroot" />/js/validate.js'></script>
<script language="javascript" src='<bean:message key="OIFM.docroot" />/js/Common.js'></script>

<script language="javascript">
	
	function fnSubmit(actionName)
	{
			document.OIFormBlogTag.action=actionName;
			document.OIFormBlogTag.submit();
	}

	var strDocRoot = '<bean:message key="OIFM.contextroot"/>'
	function submitForm(actionName)
	{
		var frm = document.OIFormBlogTag;
		document.OIFormBlogTag.action=actionName;
		frm.submit();

		//alert(frm.hHome.value);
		if(frm.hHome.value == "" && frm.hModule.value == "")
		{
			var openerForm = opener.document.forms.UserProfileForm;
			openerForm.action = "<bean:message key="OIFM.contextroot"/>/UserProfile.do?Mode=Edit";
			openerForm.hiddenAction.value = 'RefreshAction';
		}
		else if(frm.hHome.value != "")
		{
			var openerForm = opener.document.forms.HomeForm;
			openerForm.action = "<bean:message key="OIFM.contextroot"/>/home.do?Mode=Home";

		}
		else if(frm.hModule.value == "Survey")
		{
			var openerForm = opener.document.forms.SurveyForm;


			var nLen = document.forms.OIFormBlogTag.tagArrays.length;
			 var i = 0;
			 var tagList ='';
			 var tagListName ='';
			 for(i = 0 ;i < nLen ;i++){
			 	 if (document.forms.OIFormBlogTag.tagArrays[i].checked==true){
					    tagList+=document.forms.OIFormBlogTag.tagArrays[i].value+",";
						tagListName+=document.forms.OIFormBlogTag.nameOfTag[i].value+",";
						//break;
			 	 }
			 }	 

			
			opener.document.forms.SurveyForm.strTagIdList.value = tagList;
			//opener.document.forms.SurveyForm.strTagWords.value = tagListName;
			opener.document.getElementById('spanTagName').innerHTML = tagListName;
	
			self.close();
			return;
		}
		else if(frm.hModule.value == "Consult")
		{
			var openerForm = opener.document.forms.ConsultPageForm;
			
			var nLen = document.forms.OIFormBlogTag.tagArrays.length;
			 var i = 0;
			 var tagList ='';
			 var tagListName ='';
			 for(i = 0 ;i < nLen ;i++){
			 	 if (document.forms.OIFormBlogTag.tagArrays[i].checked==true){
					    tagList+=document.forms.OIFormBlogTag.tagArrays[i].value+",";
						tagListName+=document.forms.OIFormBlogTag.nameOfTag[i].value+",";
						//break;
			 	 }
			 }	 

			opener.document.forms.ConsultPageForm.strTagIdList.value = tagList;
			//opener.document.forms.ConsultPageForm.strTagWords.value = tagListName;

			opener.document.getElementById('spanTagName').innerHTML = tagListName;
	
			self.close();
			return;
		}
		else if(frm.hModule.value == "Blog")
		{
			var openerForm = opener.document.forms.OIBlogAdminCreateEntryForm;
			
			var nLen = document.forms.OIFormBlogTag.tagArrays.length;
			 var i = 0;
			 var tagList ='';
			 var tagListName ='';
			 for(i = 0 ;i < nLen ;i++){
			 	 if (document.forms.OIFormBlogTag.tagArrays[i].checked==true){
					    tagList+=document.forms.OIFormBlogTag.tagArrays[i].value+",";
						tagListName+=document.forms.OIFormBlogTag.nameOfTag[i].value+",";
						//break;
			 	 }
			 }	 

			opener.document.forms.OIBlogAdminCreateEntryForm.strTagIdList.value = tagList;
			//opener.document.forms.ConsultPageForm.strTagWords.value = tagListName;

			opener.document.getElementById('spanTagName').innerHTML = tagListName;
	
			self.close();
			return;
		}

		window.onunload = function(){
        window.opener.objActiveElem = "";
      }
		
		openerForm.method = "post";
		openerForm.submit();	
		
		//opener.document.forms.CreateBlogForm.
		//opener.location.reload(true); 
		self.close();
	}
</script>

<%
	String strHome = "";
	if(session.getAttribute("HomeAddTag")!= null){
		strHome = "home";
	}

	String strModule = "";
	if(session.getAttribute("SurveyAddTag")!= null){
		strModule = "Survey";
	}
	if(session.getAttribute("ConsultAddTag")!= null){
		strModule = "Consult";
	}
	if(session.getAttribute("BlogAddTag")!= null){
		strModule = "Blog";
	}
%>
<html>
<head>
	<title>My Forum, Ministry Of Education - Add Tag</title>
	<style type="text/css">
			<!--
			@import url("<bean:message key="OIFM.docroot" />/css/iofs.css");
			.style2 
			{
				color: #FFFFFF;
				font-weight: bold;
			}
			-->
		</style>
</head>
<body>
<html:form action="BlogTags" method="post">
<input type="hidden" name="hHome" value="<%=strHome%>"/>
<input type="hidden" name="hModule" value="<%=strModule%>"/>
<table>
	<tr>
		<td class="Boxoutline">
			<table width="500">
				<tr>
					<td class="Orange_fade" colspan="3">Create Tag</td>
				</tr>
				<logic:present name="error" scope="request">
				<tr>
					<td width="100%" class="body_detail_text" colspan="3">
						<b><bean:message name="error" scope="request"/></b>
					</td>
				</tr>
				</logic:present>
				<tr>
					<td colspan="3">&nbsp;&nbsp;</td>
				</tr>
				<tr>
					<td colspan="3">
						<table>
							<tr>
								<td colspan="3" class="SubHead"><FONT color="#000000"><STRONG>To create a new tag, please enter the new tag name and click on the "create tag" button</STRONG></font></td>
							</tr>
							<tr>
								<td colspan="3">&nbsp;</td>
							</tr>
							<tr>
								<td class="Sub_head">New tag name :</td>
								<td><html:text property="tagName" styleClass="text_box" size="50" maxlength="249" onkeypress="return noenter()"></html:text></td>
								<td>
								<a href='#' onclick="fnSubmit('<bean:message key="OIFM.contextroot" />/AddTags.do?hiddenAction=newTags')" ><img src='<bean:message key="OIFM.docroot" />/images/create-tag.gif' border="0" alt="Create tag" /></a>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="3">&nbsp;&nbsp;</td>
				</tr>
				<tr>
					<td colspan="3" class="Table_head">List of existing tags</td>
				</tr>
				<tr class="SubHead">
					<td colspan="1" width="100" align="center"><a href="#" onclick="submitForm('<bean:message key="OIFM.contextroot" />/AddTags.do?hiddenAction=selected')" ><img src='<bean:message key="OIFM.docroot"/>/images/add-tags.gif' border="0" alt = "Add" /></a></td>
					<td colspan="2" width="400"><FONT color="#000000"><STRONG>Tag Name</STRONG></font></td>
				</tr>
				<logic:present name="tagList" >
					<logic:iterate id="objTag" name="tagList" indexId="ifdx" type="com.oifm.common.OIBAAddTag">
				<tr class="Boxoutline">
					<td align="center" width="100">
						<html:multibox property="tagArrays" >
							<bean:write name="objTag" property="tagId" />
						</html:multibox>
					</td>
					<td colspan="2" class="body_extract_text">
						<bean:write name="objTag" property="tagName" />
						<input type="hidden" name="nameOfTag" value="<%=objTag.getTagName()%>"/>
					</td>
				</tr>
					</logic:iterate>
				</logic:present>
				<tr>
					<td colspan="3">&nbsp;&nbsp;</td>
				</tr>
				<tr>
					<td colspan="3">
						<a href="#" onclick="submitForm('<bean:message key="OIFM.contextroot" />/AddTags.do?hiddenAction=selected')" ><img src='<bean:message key="OIFM.docroot"/>/images/add-tags.gif' border="0" alt = "Add" /></a>
						<a href="#" onclick="window.close()"><img src="<bean:message key="OIFM.docroot"/>/images/btn_Cancel.gif" border="0" alt = "Cancel"></a>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</html:form>
</body>
</html>

