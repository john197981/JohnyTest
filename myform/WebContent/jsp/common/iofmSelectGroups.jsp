<%--
/**
 * FileName			: iofmSelectGroups.jsp
 * Author      		: Suresh Kumar.R
 * Created Date 	: 10 jul 2005
 * Description 		: This page used to select the list of groups
 * Version			: 1.0
 **/
--%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="com.oifm.common.OIBASendMail" %>

<html:html>
<head>
	<title>Select Groups</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<link href='<bean:message key="OIFM.docroot" />/css/iofs.css' rel="stylesheet" type="text/css">
	<script language="javascript" src="<bean:message key="OIFM.docroot" />/js/SendMail.js"></script>
	<style type="text/css">
	<!--
	.style1 {font-size: 9pt}
	.style3 {font-size: 10}
	body {
		background-image: url('<bean:message key="OIFM.docroot" />/images/Admin_bk.gif');
	}
	-->
	</style>
</head>

<body onLoad="fnSltGrp();">
<html:form action="/SelectGroups">

	<table border="0" cellspacing="0" cellpadding="0" height="267">
	  <tr>
    	<td width="50%" align="center" valign="top" height="267"><table width="100%" height="45"  border="0" cellpadding="0" cellspacing="0">
      	<tr>
        	<td valign="bottom" class="Admin_header">
        		<div align="left">Group Search<br>
		        </div>
        	</td>
      </tr>
    </table>    
    <logic:notPresent name="<%=com.oifm.useradmin.OISearchConstants.SEARCH%>" scope="request">

	 <logic:equal name="SelectForm" property="hiddenAction" value="searchgroup" scope="request">
	 	<table  border="0" cellspacing="0" cellpadding="0">
        	<tr>
	          <td class="Boxoutline" width="400">
    	          <table width="500"  border="0" cellspacing="0" cellpadding="3" height="158">
      				<tr>
	      				   <td width="3%" class="Mainheader"></td>
				           <td nowrap width="30%" align="left" class="Mainheader">Group</td>
						   <td nowrap width="30%" class="Mainheader">Group Description</td>
				    </tr>
					<tr> 			
			          
						<td colspan=3 width="10%" class="BodyText" align="center" nowrap> <div align="center">No Records Found </div></td> 
					</tr>
			 	    <tr>

					     <td colspan=3 width="10%" height="50" align="center">
					     <div align="center">
          				       <a href="Javascript:window.close()"><img src='<bean:message key="OIFM.docroot" />/images/btn_Cancel.gif' border="0" alt="Cancel"></a>
          				    </div>   
	        	  		</td>      
	        	  	 </tr>
	        	  	</table>	   	
	        	  	</td>	   	
          			</tr>
          			</table>
          		</tr>		
        	  </table>	
        	 </td></tr></table>	 
					
	  </logic:equal>

</logic:notPresent>
    
   <%
	int fixedIndex=0, privIndex=0;
	%>  
	<logic:present name="<%=com.oifm.useradmin.OISearchConstants.SEARCH%>" scope="request">      
    	<table  border="0" cellspacing="0" cellpadding="0">
        	<tr>
	          <td class="Boxoutline" width="400">
    	          <table width="500"  border="0" cellspacing="0" cellpadding="3" height="158">
      				
		     		<logic:iterate id="SltGrp" name="<%= com.oifm.useradmin.OISearchConstants.SEARCH%>" type="com.oifm.useradmin.OIBVUserProfileSearch" scope="request" indexId="rowNum">
						 <%if(fixedIndex==0 && "F".equals(SltGrp.getGrpType())) {
						  fixedIndex++;
						  %>
						<tr>
	      				   <td width="3%" class="Mainheader"></td>
				           <td nowrap width="30%" class="Mainheader">Fixed Groups</td>
						   <td nowrap width="30%" class="Mainheader">Group Description</td>
				        </tr>
						<%}%>
						<%if(privIndex==0 && !"F".equals(SltGrp.getGrpType())) {
						  privIndex++;
						  %>
						<tr>
	      				   <td width="3%" class="Mainheader"></td>
				           <td nowrap width="30%" class="Mainheader">Private Groups</td>
						   <td nowrap width="30%" class="Mainheader">Group Description</td>
				        </tr>
						<%}%>
				          <tr align="center" valign="top">
								<td class="BodyText">
				                 	    <html:checkbox property="groupId" value="<%=SltGrp.getId()%>"/>
				              </td>
			    	          <td nowrap class="BodyText">
				    	     	   <bean:write name="SltGrp" property="grpName"/>
			       	          </td>
								<td nowrap class="BodyText">		
				              		 <bean:write name="SltGrp" property="grpName"/>
				              </td>
				              
			             </tr>
		            </logic:iterate>

  	        	  </table>
	           </td>
	         </tr>
	        
	        	        
	          <tr>
    		       <td height="35" align="left">
          				<a href="Javascript:fnSltGrpSubmit();"><img src='<bean:message key="OIFM.docroot" />/images/but_submit.gif'  border="0" alt="Submit"></a> 
		                <a href="Javascript:window.close()"><img src='<bean:message key="OIFM.docroot" />/images/btn_Cancel.gif' border="0" alt="Cancel"></a>
        	  	  </td>
	        </tr>
      </table>
     </logic:present>        
     
    </td>
  </tr>
</table>


</body>
<html:hidden property="id"/>
<html:hidden property="hiddenAction"/>
<html:hidden property="flag" />
<html:hidden property="groupId" value="0"/>
</html:form>
</html:html>
