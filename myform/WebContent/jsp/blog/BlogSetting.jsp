<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page language="java" import="com.oifm.blog.OIBlogConstants"%>
<script language="Javascript" src='<bean:message key="OIFM.docroot" />/js/RTFEditorASM.js'></script>
<script language="javascript" >
var docRoot = '<bean:message key="OIFM.docroot" />';
function submitBLOGFormHome(submitUrl,hiddenAction)
{
	//alert(document.BlogAdminForm.blogMessage.value.length);
	if(document.BlogAdminForm.blogMessage.value.length > 250)
	{
		document.BlogAdminForm.blogMessage.value = document.BlogAdminForm.blogMessage.value.substring(0, 250);
	}
	//alert(document.BlogAdminForm.blogMessage.value.length);
		//alert("submitBLOGFormHome::" + submitUrl + "," + hiddenAction);
		document.BlogAdminForm.action= '<bean:message key="OIFM.contextroot" />'+submitUrl+'?id=<%= Math.random() %>';
		document.BlogAdminForm.hiddenAction.value=hiddenAction;
		document.BlogAdminForm.submit();
}

var maxlimit = 250;
function fnTextCounter(field)
{
	if (field.value.length > maxlimit)
	{
		field.value = field.value.substring(0, maxlimit);
	}	
}

var htmlMode = true;
function toggleEditMode(){
	if (htmlMode)
		editor_setmode('taContentId', 'textedit')
	else
		editor_setmode('taContentId', 'wysiwyg')
	
	htmlMode = !htmlMode;
}

</script>
<%
try
{
%>
<html>
<head>
<title>Settings</title>
</style>
</head>
 
 <body>
 <html:form action="/BlogAdmin.do">	
 <jsp:include page="/jsp/common/oifm_admin_header.jsp" flush="true" />
<table width="98%"  border="0" cellspacing="0" cellpadding="0">
			<tr>
			<td class="Box">
			<table width="100%" border="0" cellpadding="1"
			cellspacing="1" bgcolor="white">
			<tr class="Table_head" >
			<td colspan="2">Settings </td>
			</tr>
			 <tr>
		       <td colspan="2" class="Poll_body style2">&nbsp;</td>
	           </tr>
			
			<tr>
			<td class="heading_attributes" >&nbsp;&nbsp;No of post to show  on Blog Home Page</td>
				<td class="heading_attributes" >
				<html:text property="noOfPosts" maxlength="2" size="2"/> 
			   </td>
			</tr>
              
			   <tr>
		       <td colspan="2" class="Poll_body style2">&nbsp;</td>
	           </tr>
			
			<tr>
			<td class="heading_attributes" >&nbsp;&nbsp;No of Featured post to show  on Blog Home Page</td>
				<td class="heading_attributes" >
				<html:text property="noOfFeatPosts" maxlength="1" size="2"/> 
			  
			   </td>
	  		</tr>
	  
             <tr>
		     <td colspan="2" class="Poll_body style2">&nbsp;</td>
	        </tr>


			<tr>
			<td class="heading_attributes" >&nbsp;&nbsp;Message to End User to Diplayed Below Blog Name</td>
				<td class="heading_attributes" >
				<html:textarea property="blogMessage" styleClass="Text" 
	                    				 cols="50" rows="5"
	                    				onkeydown="fnTextCounter(this.form.blogMessage);" 
	                    				onkeyup="fnTextCounter(this.form.blogMessage);"
	                    				tabindex="3" styleId="taContentId"
	                    				>
	                   </html:textarea>
	            <script language="javascript">
					var config = new Object();
					config.bodyStyle = 'background-color: white; font-family: "Verdana"; font-size: x-small;';
					config.debug = 0;
					editor_generate('taContentId',config);
				</script>
				<!-- br /><a href="#" onclick="toggleEditMode();">Toggle mode</a -->
			   </td>
			</tr>
		             
				 <tr>
		       <td colspan="2" class="Poll_body style2">&nbsp;</td>
	           </tr>
	
		<tr>
		  <td height="55" colspan="2" align="centre" valign="middle"> &nbsp; 
				<logic:notEqual name="BlogAdminForm" property="hiddenAction" value="load">
				<a href="#" onClick="javascript:submitBLOGFormHome('/BlogAdmin.do','SaveBlogSettings');"><img src='<bean:message key="OIFM.docroot" />/images/but_save.gif'  border="0" alt = "Save Blog Master"></a>
               </logic:notEqual>	
				<logic:notEqual name="BlogAdminForm" property="hiddenAction" value="load">
				<a href="#" onClick="javascript:submitBLOGFormHome('/BlogAdmin.do','<%=OIBlogConstants.ACTION_CANCEL%>')" ><img src='<bean:message key="OIFM.docroot" />/images/but_Cancel.gif'  border="0" alt = "Cancel Blog Master"></a>
				</logic:notEqual>	
		  </td>
		   </tr>
	
	

</table>
<html:hidden property="hiddenAction"/>
</html:form>
<%
}
catch(Exception e)
{
	out.println(e.getMessage());
}
%>
<input type="hidden" name="hiddenAction">

 
