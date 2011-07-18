<%--
/**
 * FileName			: iofmPollResult.jsp
 * Author      		: Suresh Kumar.R
 * Created Date 	: 22 jul 2005
 * Description 		: This page used to display the poll results
 * Version			: 1.0
 **/
--%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>


 <title>Poll Result </title>
  <logic:equal name="pageName" value="publish" scope="request">
	<jsp:include page="/jsp/common/iofm_Top.jsp" flush="true" >	
	   <jsp:param name="pageName" value="Home" />
	</jsp:include>
  </logic:equal> 
    <logic:equal name="pageName" value="export" scope="request">

		  <%response.setContentType("application/vnd.ms-excel") ;
		  //	response.addHeader ("content-disposition", "attachment; filename=Pollresult.xls");
		  %>
		
    </logic:equal> 
  
<html:html>

<script language="javascript" src='<bean:message key="OIFM.docroot" />/js/Common.js'></script>
<script language="javascript" src='<bean:message key="OIFM.docroot" />/js/Poll.js'></script>

<script>
var strDocRoot ='<bean:message key="OIFM.contextroot" />';
</script>	


<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
  <logic:notEqual name="pageName" value="export" scope="request">
	<link href='<bean:message key="OIFM.docroot" />/css/iofs.css' rel="stylesheet" type="text/css">		
  </logic:notEqual> 

<style type="text/css">
<!--
.style1 {
	font-size: 18px;
	font-weight: bold;
}
-->
</style> 
</head>
<table width="857" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td class="Orange_fade">Poll Result</td>
          </tr>
        </table>
        <table width="857" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td valign="top" class="orange"><div align="justify" class="Highlight_body"> 
              <blockquote>
                <p><br>&nbsp;
                  <br>
                  <br>
                  </p>
                </blockquote>
            </div></td>
            </tr>
          <tr>
            <td class="Grey_fade">&nbsp;</td>
          </tr>
	      <tr>
            <td align="left" valign="top" bgcolor="#f7f8fc"><table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0">

	<logic:notEqual name="PollForm" property="hiddenAction" value="publish">
    	<logic:present name="alreadyvoted" scope="request">
			<div align="center" class="text"> <font color="red">    <bean:message key="Poll.alreadyvoted"/> </font> </div>
		</logic:present>
	</logic:notEqual>

<table width="95%" height="225"  border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    
    <logic:notPresent name="alreadyvoted" scope="request">
		<logic:equal name="PollForm" property= "total" value="0">
		<td width="40%" align="left" valign="top" class="Boxoutline"><div align="center">
			<div align="center" class="text"> <font color="red"> No Results for the Poll </font> </div>
			<br> <br> <br> 
			 <a href="#" onClick="fnBack();"><img src='<bean:message key="OIFM.docroot" />/images/but_back.gif'  border="0" alt = "Back"></a><br>
		 </td> 	
		</logic:equal>
    </logic:notPresent>	

	

	<logic:notEqual name="PollForm" property= "total" value="0">
		<td width="77%" align="left" valign="top" class="Box" > 
		  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#BEDCF0">
	        <tr>
	          <td height="25" class="Table_head">Q. <bean:write name="PollForm" property="question"/> </td>
	        </tr>
	        <tr>
	          
	          <logic:notEqual name="pageName" value="export" scope="request">
					  <td align="center" class="sub_head">
			 </logic:notEqual> 
			 <logic:equal name="pageName" value="export" scope="request">
					  <td align="center" class="sub_head">
			 </logic:equal> 		 
	         
	 		          		 <bean:write name="PollForm" property="total"/> <bean:message key="OI.POLL.RESULT" />
							<!--since <bean:write name="PollForm" property="startDt"/> -->
							 </td>
	        </tr>
	        <tr>
	          <td><div align="center"></div></td>
	        </tr>
           


	   <% int i=1;%>
		
	    <logic:iterate id="Result" name="<%=com.oifm.common.OILabelConstants.OBJARBV%>" type="com.oifm.poll.OIBAPoll" scope="request" indexId="rowNum">  
			<logic:notEqual name="PollForm" property='<%="answer"+i%>' value="">
		
		    <tr>
          		<td>
	 			    <table width="100%" align="center" cellpadding="4" cellspacing="0">
        		    <tr>
		                <td width="40%" align="right" valign="top" class="Heading_thread">
						    <div align="left"><bean:write name="PollForm" property='<%="answer"+i%>'/></div>
						</td>
		                 <td align="left" valign="middle" class="body_extract_text">
		          		      <logic:notEqual name="Result" property="imgPer" value="0">
					          		<img height="20" src='<bean:message key="OIFM.docroot" />/images/bar2.gif' width='<bean:write name="Result" property="imgSize"/>'>
				    	      </logic:notEqual>	
				    <logic:equal name="pageName" value="export" scope="request">
				    	</td><td align="left" valign="middle" class="Text"> 
					 </logic:equal>		    	      
					 	      <bean:write name="Result" property="imgPer"/>% 
		                 </td>
         		         <td width="10%" align="left" valign="middle" class="body_extract_text">
         		           		<strong><bean:write name="Result" property="res"/> votes</strong>
         		         </td>
	                </tr>

	          </table>
			 </td>
        	</tr>
	        </logic:notEqual>
	        <%i++;%>
	    </logic:iterate>    
        </tr>
			<tr height="180"> <td colspan="1"></td></tr>

      </table>    
	  </tr>
	</table> 
	<br>
	<div align="center"> 
	      <logic:equal name="pageName" value="publish" scope="request">
		       <a href="#" onClick="fnBack();"><img src='<bean:message key="OIFM.docroot" />/images/but_back.gif'  border="0" alt = "Back"></a><br>
		   </logic:equal>    
		   <logic:notEqual name="pageName" value="publish" scope="request">
		     <logic:notEqual name="pageName" value="export" scope="request">
			       <a href="Javascript:window.print();"><img src='<bean:message key="OIFM.docroot" />/images/but_print.gif' border="0" alt = "Print"></a><br>
		    </logic:notEqual> 		       
		   </logic:notEqual>    	   
	    </div>
      </td>
   </logic:notEqual>
<html:form action="/Poll" method="post">
<html:hidden property="pollId"/>
<html:hidden property="question"/>
<html:hidden property="answer1"/>
<html:hidden property="answer2"/>
<html:hidden property="answer3"/>
<html:hidden property="answer4"/>
<html:hidden property="answer5"/>
<html:hidden property="total"/>
<html:hidden property="imgSize"/>
<html:hidden property="imgPer"/>
<html:hidden property="pubTitle"/>
<html:hidden property="startDt"/>
<html:hidden property="pubId"/>
<html:hidden property="hiddenAction"/>
</html:form>
</html:html>
