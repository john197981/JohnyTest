<%--
/**
 * FileName			: iofmPoll.jsp
 * Author      		: Suresh Kumar.R
 * Created Date 	: 22 jul 2005
 * Description 		: This page used to create the Poll data.
 * Version			: 1.0
 **/ 
--%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>    
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="org.apache.struts.util.LabelValueBean" %>

<html:html>
<link href='<bean:message key="OIFM.docroot" />/css/oicalendar.css' rel="stylesheet" type="text/css">
<script language="javascript" src='<bean:message key="OIFM.docroot" />/js/validate.js'></script>
<script language="javascript" src='<bean:message key="OIFM.docroot" />/js/oicalendar.js'></script>
<script language="javascript" src='<bean:message key="OIFM.docroot" />/js/Common.js'></script>
<script language="javascript" src='<bean:message key="OIFM.docroot" />/js/Poll.js'></script>

<script>
var strDocRoot ='<bean:message key="OIFM.contextroot" />';
var currDate = "<%= com.oifm.utility.OIUtilities.getCurrentDateTime("dd-MMM-yyyy")%>";
</script>	

<DIV id=divCalendar style="VISIBILITY: hidden; POSITION: absolute; BACKGROUND-COLOR: white; layer-background-color: white"></DIV>

<html:form action="/Poll" method="post">


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
						<td colspan="3">Poll</td>
 					</tr>
         	   <tr>
     	    	     <td colspan="3" class="sub_head">
					    <logic:equal name="PollForm" property="hiddenAction" value="view" scope="request">
					    View / Modify Poll 
    					</logic:equal>    	    	     
	     	    	     <logic:equal name="PollForm" property="hiddenAction" value="load" scope="request">
	     	    	     Create Poll 
    					</logic:equal>	 	    	     
     	    	     
     	    	     </td>
	            </tr>
			<logic:present name="Duplicate" scope="request">
					<tr align="center"><td class="body_detil_text" colspan="3">
							   		     <font color="red"><bean:message key="Poll.Exists"/></font> 
						</td></tr>											
				</logic:present>	

				<tr>
	         	    <td width="17%" class="heading_attributes">Title<font color="red">*</font></td>
	    	    	<td width="83%" class="body_detail_text" colspan="2">
			    	     <html:text property="title" 
				        			styleClass="Text_box"
				        		    maxlength="50" 
				        		    size="55"
				        		    tabindex="1"/>
		                </td>
	          </tr>
              <tr>
                <td class="heading_attributes">Poll date <font color="red">*</font></td>
                <td class="heading_attributes" colspan="2">Start Date
                   	      <input type="text" name="TempStartDt" 
                   	       			 class="Text_box"
		       		       			 maxlength="66" 
				        		     size="14"
				        		     tabindex="2"
									 readonly=true   
				        		     value ='<bean:write name="PollForm" property="startDt"/>'>
     					<a href="#" onClick="fnDisableStDt();" name="fromAnchor" id="fromAnchor">
						<img src='<bean:message key="OIFM.docroot" />/images/img_calendar.gif' width="25" height="20" border="0" tabindex="4" alt = "Calendar"></a>
						 End Date      		     
    	   	      	     <input type="text" name="TempExpDt" 
			   		       			 Class="Text_box"
		       		       			 maxlength="66" 
				        		     size="14"
   									 readonly=true
				        		     tabindex="4"
   				        		     value ='<bean:write name="PollForm" property="expDt"/>'>
					  <a href="#" onClick="cal.select(document.forms['PollForm'].TempExpDt,'TempExpDt','dd-NNN-yyyy');return false;" name="fromAnchor" id="fromAnchor">
					  <img src='<bean:message key="OIFM.docroot" />/images/img_calendar.gif' width="25" height="20" border="0" tabindex="5" alt = "Calendar"></a>
			 		  
              </tr>
              <tr>
                <td valign="top" class="heading_attributes">Question <font color="red">*</font> </td>
                <td class="body_detail_text" colspan="2">
                	
	                	<html:textarea  property="question" 
	                					styleClass="Text" 
	                					cols="53" 
	                					rows="6"
	                					tabindex="6"
	                					onkeydown="fn_PolltextCounter(this.form.question,this.form.numleft);" onkeyup="fn_PolltextCounter(this.form.question,this.form.numleft);" onmouseover="fn_PolltextCounter(this.form.question,this.form.numleft);" onmouseout="fn_PolltextCounter(this.form.question,this.form.numleft);">
		               	</html:textarea>
		            	<br>
							<div align="left">
								<font color="#005BCC">
									No. of characters remaining: 
									<input name="numleft" type="text" class="Text_box" size="10" size="5" maxlength="3" value="500" disabled style="font-family: Arial, Helvetica, sans-serif;font-size:11px; background: #FFFFFF;border-bottom: 1px solid #7F9DB9;border-right: 1px solid #7F9DB9;border-left: 1px solid #7F9DB9;border-top:1px solid #7F9DB9; color:#018BAB;text-decoration:none">
								</font>
							</div>
		       	</td>
              </tr>
              <tr>
            	    <td class="heading_attributes">Answer1 </td>
	                <td class="body_detail_text" colspan="2">
	               	      <html:text property="answer1" 
			   		       			 styleClass="Text_box"
			   		       			 maxlength="100" 
				        		     size="55"
				        		     tabindex="7"/>
			        </td>
	          </tr>
              <tr>
                <td class="heading_attributes">Answer2</td>
		        <td class="body_detail_text" colspan="2">
	               	      <html:text property="answer2" 
			   		       			 styleClass="Text_box"
			   		       			 maxlength="100" 
				        		     size="55"
				        		     tabindex="8"/>
			        </td>         
			  </tr>
              <tr>
				  <td class="heading_attributes">Answer3</td>
		          <td class="body_detail_text" colspan="2">
	               	      <html:text property="answer3" 
			   		       			 styleClass="Text_box"
			   		       			 maxlength="100" 
				        		     size="55"
				        		     tabindex="9"/>
			        </td>
			  </tr>
              <tr>
				  <td class="heading_attributes">Answer4</td>
		          <td class="body_detail_text" colspan="2">
	               	      <html:text property="answer4" 
			   		       			 styleClass="Text_box"
			   		       			 maxlength="100" 
				        		     size="55"
				        		     tabindex="10"/>
			        </td>
			  </tr>
              <tr>
				  <td class="heading_attributes">Answer5</td>
		          <td class="body_detail_text" colspan="2">
	               	      <html:text property="answer5" 
			   		       			 styleClass="Text_box"
			   		       			 maxlength="100" 
				        		     size="55"
				        		     tabindex="11"/>
			        </td>
			  </tr>
              <tr>
                	<td class="heading_attributes">Show Result After Poll </td>
	                <td class="body_detail_text" colspan="2">
				    	 <html:checkbox property="showRes" tabindex="12"value="Y"/>
                	</td>
             </tr>
              <tr>
	                <td class="heading_attributes">Multiple Answers </td>
	                <td class="body_detail_text" colspan="2">
	                	 <html:checkbox property="mutAns" tabindex="13" value="Y"/>
	                </td>
	          </tr>
          <logic:equal name="PollForm" property="hiddenAction" value="view" scope="request">             
              <tr>
	                <td class="heading_attributes">Publish Poll </td>
	                <td class="body_detail_text" colspan="2">
						<html:checkbox property="published" tabindex="14" value="Y" onclick="fnPublish()"/>
	                </td>
              </tr>
		</logic:equal>		   
         
        <tr>
        
			<logic:equal name="PollForm" property="hiddenAction" value="view" scope="request">
			      <td colspan="3" height="35" colspan="3" align="left" valign="middle"> &nbsp; 
		          		<a href="#" onClick="javascript:fnUpdate();"><img src='<bean:message key="OIFM.docroot" />/images/but_save.gif'  border="0" tabindex="15" alt = "Save"></a>
		          		<a href="#" onClick="javascript:fnDelete();"><img src='<bean:message key="OIFM.docroot" />/images/but_delete.gif' border="0" tabindex="16" alt = "Delete"></a>
		          		<a href="#"onClick ="javascript:fnClear();">
	          			<img src='<bean:message key="OIFM.docroot" />/images/but_reset.gif' border="0" tabindex="17" alt = "Reset"></a>
		          		<a href="#"onClick ="javascript:fnCancel();">
			          		<img src='<bean:message key="OIFM.docroot" />/images/but_Cancel.gif' border="0" tabindex="18" alt = "Cancel"></a>          		 
		          		
		          </td>
		    </logic:equal>
 	       <logic:equal name="PollForm" property="hiddenAction" value="load" scope="request">     
	          <td colspan="3" height="35" align="left" valign="middle">
	          		<a href="#"onClick="javascript:fnSave();">
		          		 <img src='<bean:message key="OIFM.docroot" />/images/but_save.gif' border="0" tabindex="15" alt = "Save"></a>
	          		<a href="#"onClick ="javascript:fnCancel();">
	          			<img src='<bean:message key="OIFM.docroot" />/images/but_Cancel.gif' border="0" tabindex="16" alt = "Cancel"></a>          		 
	          		<a href="#"onClick ="javascript:fnClear();">
	          			<img src='<bean:message key="OIFM.docroot" />/images/but_reset.gif' border="0" tabindex="17" alt = "Reset"></a>
	          </td>
	       </logic:equal>  
	    </tr>
      
   <logic:equal name="PollForm" property="hiddenAction" value="view" scope="request">
      
      <logic:notEqual name="PollForm" property="total" value="0" scope="request">
		 <% boolean bFlag =false ;%>

	      <tr class="subhead">
           <td colspan="3" align="left" valign="middle" class="Sub_head">Result</td>
	      </tr>
	      <tr><td align="center"  colspan="3">
	     	<div align="center" class="bold">
		     	<strong><bean:write name="PollForm" property="total"/> <bean:message key="OI.POLL.RESULT" />
				<!--since <bean:write name="PollForm" property="startDt"/>  -->
				</strong></div>
		     	<br>
	     	</td></tr>

	      <% int i=1;%>
	    
	  <logic:iterate id="Result" name="<%= com.oifm.common.OILabelConstants.OBJARBV %>" type="com.oifm.poll.OIBAPoll" scope="request" indexId="rowNum">  
		<logic:notEqual name="PollForm" property='<%="answer"+i%>' value="" scope="request">
			<% bFlag=true;%>
	    	  <tr height="21">
		          <td align="left" valign="middle" class="heading_attributes">
		          		&nbsp;&nbsp;
		          		<bean:write name="PollForm" property='<%="answer"+i%>'/>
		          </td>
		          <td align="left" valign="middle">
		          		<span class="heading_attributes" valign="middle">
		          		      <logic:notEqual name="Result" property="imgPer" value="0">
					          		<img height="20" src='<bean:message key="OIFM.docroot" />/images/bar2.gif' width='<bean:write name="Result" property="imgSize"/>'>
				    	      </logic:notEqual>		
					    	      <bean:write name="Result" property="imgPer"/>% 
		          		</span>
		          </td>
		          <td align="left" valign="middle" class="bold">
		          		<strong><bean:write name="Result" property="res"/>  votes</strong></td>
	        </tr>
	        </logic:notEqual>
	        <%i++;%>
	    </logic:iterate>    
	    <%if(bFlag){ %>
         <tr>
          <td align="left" valign="middle" class="body_detail_text">&nbsp;</td>
          <td align="left" valign="middle">&nbsp;</td>
          <td align="left" valign="middle">&nbsp;</td>
        </tr>
        <tr>
          <td align="left" valign="middle" class="Text" colspan="3">
	           <a href="#" onClick="javascript:fnExport();"><img src='<bean:message key="OIFM.docroot" />/images/btn_export.gif' border="0" tabindex="19" alt = "Export"></a>
    	       <a href="#" onClick="javascript:fnPrintPublish();"><img src='<bean:message key="OIFM.docroot" />/images/but_print.gif' border="0" tabindex="20" alt = "Print"></a> 
          </td>
         </tr>
        <%}%>        
     </logic:notEqual>
    </logic:equal>
  </tr>
			</table>
		</td>
	</tr>
</table>
</td>
</tr>
</table>

<!--Hidden Fields -->
<html:hidden property="pollId"/>
<html:hidden property="hiddenAction"/>
<html:hidden property="startDt"/>
<html:hidden property="expDt"/>
<html:hidden property="total"/>
<html:hidden property="imgSize"/>
<html:hidden property="imgPer"/>
<html:hidden property="pubTitle"/>
<html:hidden property="pubId"/>

<html:hidden property="res1"/>
<html:hidden property="res2"/>
<html:hidden property="res3"/>
<html:hidden property="res4"/>
<html:hidden property="res5"/>

<script>
	fn_PolltextCounter(document.PollForm.question,document.PollForm.numleft)
</script>
</html:form>
</html:html>
