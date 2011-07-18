<%--
/**
 * FileName			: iofmPollList.jsp
 * Author      		: Suresh Kumar.R
 * Created Date 	: 22 jul 2005
 * Description 		: This page used to List the Poll questions.
 * Version			: 1.0
 **/  
--%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="org.apache.struts.util.LabelValueBean" %>



<script language="javascript" src='<bean:message key="OIFM.docroot" />/js/Poll.js'></script>
<html:html>
<head>
<title>Poll Listing</title>

</head>


<html:form action="/Poll">

<jsp:include page="/jsp/common/oifm_admin_header.jsp" flush="true" />

<table width="98%" border="0" cellpadding="0"
		cellspacing="0">

	<tr>
		<td class="TableHead">
		<table width="100%" border="0" cellspacing="1" cellpadding="1">
			<tr>
			<td class="Box">
				<table width="100%" border="0" cellpadding="0"
					cellspacing="0" bgcolor="white">
					<tr class="Table_head" >
						<td colspan="4">Poll</td>
 					</tr>
     
  <logic:present name="<%=com.oifm.common.OILabelConstants.OBJARBV%>" scope="request">
           <tr>
                 <td width="20%" bgcolor="#F0F8FF" class="Sub_head">Title</td>
                <td width="53%" bgcolor="#F0F8FF" class="Sub_head">Question</td>
                <td width="12%" bgcolor="#F0F8FF" class="Sub_head">From date </td>
                <td width="11%" bgcolor="#F0F8FF" class="Sub_head">To date </td>
              </tr>

              
			<logic:iterate id="poll" name="<%= com.oifm.common.OILabelConstants.OBJARBV %>" type="com.oifm.poll.OIBVPoll" scope="request">
	              <tr>
	                <td class="special_body_link"><a class="special_body_link" href="#"onClick="javascript:fnView('<bean:write name="poll" property="pollId"/>');"><bean:write name="poll" property="title"/> </a></td>
	                <td valign="top" class="heading_attributes"><bean:write name="poll" property="question"/></td>
	                <td valign="top" class="heading_attributes"><bean:write name="poll" property="startDt"/></td>
	                <td valign="top" class="heading_attributes"><bean:write name="poll" property="expDt"/></td>
	              </tr>
         

              <tr height="20">
                 <td colspan="4" class="body_detail_text">&nbsp;</td>
              </tr>

          </logic:iterate>      
              
   </logic:present>  

   <logic:notPresent name="<%=com.oifm.common.OILabelConstants.OBJARBV%>" scope="request">
           <tr>
                <td width="20%" bgcolor="#F0F8FF" class="Sub_head">Title</td>
                <td width="53%" bgcolor="#F0F8FF" class="Sub_head">Question</td>
                <td width="12%" bgcolor="#F0F8FF" class="Sub_head">From date </td>
                <td width="11%" bgcolor="#F0F8FF" class="Sub_head">To date </td>
              </tr>
           </tr>
   </logic:notPresent>  

              <tr height="20">
                 <td colspan="4" class="body_detail_text">&nbsp;</td>
              </tr>
 		
           <tr>
            <td colspan="4" class="body_detail_text" align="left">&nbsp; 
            	<a href="#" onClick="javascript:fnLoad();">
            		<img src='<bean:message key="OIFM.docroot" />/images/btn_create_poll.gif' border="0" alt = "Create Poll"></a> 
            </td>
          </tr>

        </table>
			</td>
		</tr>
	</table>
	</td>
	</tr>
</table>

<!--hidden Fields -->

<html:hidden property="pollId"/>
<html:hidden property="hiddenAction"/>
</html:form>
</html:html>
