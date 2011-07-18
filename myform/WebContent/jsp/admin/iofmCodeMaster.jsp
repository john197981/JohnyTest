<%--
/**
 * FileName			: iofmCodeMaster.jsp
 * Author      		: Suresh Kumar.R
 * Created Date 	: 15 Aug 2005
 * Description 		: This page used to Search and Update the Code Master Details
 * Version			: 1.0
 **/ 
--%>

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<script>
var strDocRoot ='<bean:message key="OIFM.contextroot" />';
</script>	  
<script language="javascript" src='<bean:message key="OIFM.docroot"/>/js/CodeMaster.js'></script>

<html>
<head>
<title>Code Master</title>
</style>
</head>



<body>
<html:form action="CodeMaster">
	<jsp:include page="/jsp/common/oifm_admin_header.jsp" flush="true" />

<table width="98%" border="0" cellpadding="0"
		cellspacing="0">
	<tr>
	<td class="TableHead">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td class="Box">
				<table width="100%" border="0" cellpadding="1"
					cellspacing="1" bgcolor="white">
					<tr class="Table_head" >
						<td colspan="2">Code Master </td>
 					</tr>
					<tr>
					 <logic:notPresent name="<%=com.oifm.common.OILabelConstants.OBJARBV%>" scope="request">  
							<td colspan="2" class="Sub_head">Common Codes</td>
					</logic:notPresent>		
					<logic:present name="<%=com.oifm.common.OILabelConstants.OBJARBV%>" scope="request">  
							<td colspan="2" class="Sub_head">View / Modify Common Codes </td>
					</logic:present>		
 				  </tr>
               <tr>
                <td width="17%" class="heading_attributes">Code Type <font color="red">*</font></td>
                <td width="83%" class="heading_attributes">
		                <html:select property="type" styleClass="Text" tabindex="1" >
								    <html:options collection="codetype" property="value" labelProperty="label" />
						</html:select>
                
                </td>
              </tr>
              <tr>
                <td class="heading_attributes">Code
                  <logic:equal name="CodeMasterForm" property="hiddenAction" value="load">
	                <font color="red">*</font>
	              </logic:equal>  
                </td>
                <td class="heading_attributes">
	                 <html:text property="value" 
		        		       			  styleClass="Text_box"
		        		       			  maxlength="50" 
		        		       			  size="50"
		        		       			  tabindex="2"/>           
		        </td>
              </tr>
              <tr>
                <td valign="top" class="heading_attributes">Value
                 <logic:equal name="CodeMasterForm" property="hiddenAction" value="load">
	                <font color="red">*</font>
	              </logic:equal>  
                </td>
                <td class="heading_attributes">
                
                     <html:textarea  property="description" 
	                    				styleClass="Text" 
	                    				 cols="50" rows="5"
	                    				onkeydown="fnTextCounter(this.form.description,this.form.numleft1);" 
	                    				onkeyup="fnTextCounter(this.form.description,this.form.numleft1);"
	                    				tabindex="3"
	                    				>
	                   </html:textarea>  
	               
	               <div align="left">
							<font color="#005BCC">
								No. of characters remaining: 
								<input name="numleft1" type="text" size="5" maxlength="3" value="300" disabled  class="text_box">
							</font>
				 </div></td>
              </tr>
              <tr>
                <td class="heading_attributes">Short Name</td>
                <td class="heading_attributes">
	                 <html:text property="shortName" 
		        		       			  styleClass="Text_box"
		        		       			  maxlength="15" 
		        		       			  size="15"
		        		       			  tabindex="5"/>           
		        </td>
              </tr>
              <tr>
                <td class="heading_attributes">Obsolete</td>
                <td class="body_detail_text"> <html:checkbox property="obsolete" 
                									 value="Y"  tabindex="4"/></td>
              </tr>
				  <tr>
					<td colspan="2" class="body_detail_text">&nbsp;</td>
				   </tr>
        <tr>
          <td height="35" colspan="2" align="left" valign="middle"> &nbsp; 
	          <logic:equal name="CodeMasterForm" property="hiddenAction" value="load">
	          	<a href="#" onClick="javascript:fnUpdate();"> <img src='<bean:message key="OIFM.docroot"/>/images/but_save.gif'  border="0" alt = "Save Code Master"></a>   
	          </logic:equal>	
	           <logic:notEqual name="CodeMasterForm" property="hiddenAction" value="load">
		           <a  href="#" onClick="javascript:fnSearch();"> <img src='<bean:message key="OIFM.docroot"/>/images/btn_Search.gif'  border="0" alt = "Search Code Master"></a>   
	          </logic:notEqual>		           
	          	<a  href="#" onClick="javascript:fnCancel();"> <img src='<bean:message key="OIFM.docroot"/>/images/but_Cancel.gif'  border="0" alt = "Cancel Code Master"></a>
	    	</td>
        </tr>

          </table></td>
        </tr>
              <tr>
                <td colspan="2" class="body_detail_text">&nbsp;</td>
               </tr>
        
	     <logic:notPresent name="<%=com.oifm.common.OILabelConstants.OBJARBV%>" scope="request">   
	     	<logic:equal name="CodeMasterForm" property="hiddenAction" value="search">	     
         		<table width="100%"  border="0" align="left" cellpadding="1" cellspacing="1" class="Box">
	            <tr>
    	    	      <td colspan="4" class="Table_head"> <bean:write name="CodeMasterForm" property="type"/></td>
        	    </tr>
	            <tr class="Subhead">
		              <td width="20%" height="25" class="Sub_head">Code</td>
		              <td width="54%" class="Sub_head">Value</td>
		              <td width="13%" class="Sub_head">Short Name</td>
		              <td width="13%" class="Sub_head">Obsolete</td>
		         </tr>
		         <tr>
		         	<td class="text" nowrap align="center" colspan="4">		
		         		<br>
			         	<font color="red"><bean:message key="NoRecordLoad"/></font>
				   </td>			
				 </tr>
				 </table>
			</logic:equal>	
    	 </logic:notPresent>        
     <logic:present name="<%=com.oifm.common.OILabelConstants.OBJARBV%>" scope="request">   
        <tr>
          <td height="35" align="left" valign="middle">
                    
		  <table width="100%"  border="0" align="left" cellpadding="1" cellspacing="1" class="Box">
            <tr>
              <td colspan="4" class="Table_head"> <bean:write name="CodeMasterForm" property="type"/></td>
            </tr>
            <tr class="Subhead">
              <td width="20%" height="25" class="Sub_head">Code</td>
              <td width="54%" class="Sub_head">Value</td>
		      <td width="13%" class="Sub_head">Short Name</td>
              <td width="13%" class="Sub_head">Obsolete</td>
            </tr>
            <logic:iterate id="CodeMaster" name="<%= com.oifm.common.OILabelConstants.OBJARBV %>" type="com.oifm.codemaster.OIBACodeMaster" scope="request" indexId="rowNum">
            <tr>
              <td  valign="top">
              <a class="special_body_link" href="#" onClick="javascript:fnPopulate('<bean:write name="CodeMaster" property="strCodeId"/>')">
		              <bean:write name="CodeMaster" property="value"/></a></td>
              		
              <td class="heading_attributes" valign="top">
              		 <bean:write name="CodeMaster" property="description"/></td>
              <td class="heading_attributes" valign="top">
              		 <bean:write name="CodeMaster" property="shortName"/></td>
              <td class="heading_attributes" align="left" valign="top">
              <logic:equal name="CodeMaster" property="obsolete" value="Y">
              			Yes
              </logic:equal>
              <logic:equal name="CodeMaster" property="obsolete" value="N">
              			No
              </logic:equal>
              
              </td>
            </tr>
			<tr><td colspan="4">&nbsp;</td></tr>			
            </logic:iterate>            
          </table></td>
        </tr>
		<tr><td valign="top"> &nbsp; </td></tr>
 
    </logic:present>
  
</td></tr>
</table>
</td></tr>
</table>
<html:hidden property="hiddenAction"/>
<html:hidden property="strCodeId"/>
<html:hidden property="hidType"/>
<html:hidden property="hidCode"/>
<html:hidden property="hidDesc"/>
<html:hidden property="hidObs"/>
<html:hidden property="hidName"/>
</html:form>
<script>
		  fnLoad();
		  fnTextCounter(document.CodeMasterForm.description,document.CodeMasterForm.numleft1);

</script>
