<%--
/**    
 * FileName			: iofmAdminUserProfile.jsp
 * Author      		: Suresh Kumar.R
 * Created Date 	: 7 Aug 2005
 * Description 		: This page used to select the list of users
 * Version			: 1.0
 **/  
--%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<jsp:include page="/jsp/common/iofm_Top.jsp" flush="true" >	
   <jsp:param name="pageName" value="Home" />
</jsp:include>

<SCRIPT LANGUAGE="JavaScript">

	function fnSelectTags(){
			var strUrl = '/AddTags.do?hiddenAction=addTags&module=P';
			help_window  = window.open('<bean:message key="OIFM.contextroot" />'+strUrl,'SelectTagss','width=500,height=620,left=0,top=0,resizable=yes,scrollbars=yes');
			help_window.focus();
		}

	function fnLoad(){
		alert('load');
		document.UserProfileForm.tags.value = document.UserProfileForm.htag.value;
		return;
	}

</script>

<html:html>
<head>
<title>Admin Profile Detail</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<link href='<bean:message key="OIFM.docroot" />/css/iofs.css' rel="stylesheet" type="text/css">
</head>
<script language="javascript" src='<bean:message key="OIFM.docroot"/>/js/AdminProfile.js'></script>

<body>
 		<table width="857" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td class="Orange_fade">User Profile</td>
          </tr>
        </table>
 		<table width="857" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td valign="top" colspan="4" class="orange" align="center"> 
              <blockquote>
 				<br><font class="Highlight_body">
				<logic:present name="save" scope="request">
						<bean:message key="SuccessfullyUpdated" /> 	  			
				 </logic:present>	
				<logic:present name="delbookmark" scope="request">
					<bean:message key="BookMarkDel" />  
			   </logic:present>
			   <logic:present name="delsticky" scope="request">
						<bean:message key="StickyDel" /> 			  								
			  </logic:present>
			  <logic:present name="dupnickname" scope="request">
					<bean:message key="DupNickName" /> 				  								
			 </logic:present> 
			 </font>
                   
                </blockquote>
             </td>
            </tr>

<html:form action="/UserProfile">  
<tr>
<td colspan="4" align="center" bgcolor="#FFFFFF">
<table width=100%>

<tr>
<td colspan="4" align="center" >&nbsp;</td>
</tr>
<tr>
             <td colspan="4" align="center" >

			 <table width="98%" border="0" align="center" cellspacing="1" cellpadding="1" class="box" > 

<tr><td colspan="4" class="table_head">
			 Profile Detail
			 
			 </td>
          </tr>
 
               <tr align="left" valign="top"> 
                <td width="13%" nowrap class="heading_thread">Name</td>
                <td width="32%" class="body_detail_text"><bean:write name="UserProfileForm" property="name"/> </td>
                <td width="7%" nowrap class="body_detail_text">&nbsp;</td>
                <td width="48%" class="body_detail_text">&nbsp;</td>
              </tr>
              <tr align="left" valign="top"> 
                <td nowrap class="heading_thread">Nick Name </td>
                <td class="body_detail_text"> 
	                 <html:text property="nickName" 
			        		       	 styleClass="Text_box"
			        		       	  maxlength="30" 
			        		       	  size="35"
			        		       	  tabindex="6"/>
	                </td>
                <td nowrap class="heading_thread">Email ID </td>
                <td class="body_detail_text"> 
                  <html:text property="mailName" 
		        		       			  styleClass="Text_box"
		        		       			  maxlength="40" 
		        		       			  size="20"
		        		       			  tabindex="1"/> @
		          
		          <html:select property="mailDomain">
		          		<html:options name="Domains" labelName="Domains"/>
		          </html:select>
                </td>
              </tr>
              <tr align="left" valign="top"> 
                <td nowrap class="heading_thread">Areas of Interest </td>
                <td class="body_detail_text"> 
                   <html:textarea  property="area" 
	                    				styleClass="Text" 
	                    				 cols="35" rows="5"
	                    				onkeydown="fnTextCounter(this.form.area,this.form.numleft2);" 
	                    				onkeyup="fnTextCounter(this.form.area,this.form.numleft2);"
	                    				>
	                    				
	                   </html:textarea>  
  	               
	               <div align="left">
							<font color="#005BCC">
								No. of characters remaining: 
								<input name="numleft2" type="text" size="5" maxlength="3" value="200" disabled class="text_box">
							</font>
				 </div>             
                </td>
                <td nowrap class="heading_thread">Hobbies</td>
                <td class="body_detail_text"> 
                   <html:textarea  property="hobbies" 
	                    				styleClass="Text" 
		                    			 cols="35" rows="5"
	                    				onkeydown="fnTextCounter(this.form.hobbies,this.form.numleft3);" 
	                    				onkeyup="fnTextCounter(this.form.hobbies,this.form.numleft3);">
	                   </html:textarea>  
		               <div align="left">
							<font color="#005BCC">
								No. of characters remaining: 
								<input name="numleft3" type="text"  size="5" maxlength="3" value="200" disabled class="text_box">
							</font>
					 </div>
                </td>
              </tr>
              <tr align="left" valign="top"> 
                <td nowrap class="heading_thread">Signature</td>
                <td class="body_detail_text"> 
                 <html:textarea  property="sign" 
	                    				styleClass="Text" 
	                    				 cols="35" rows="5"
	                    				onkeydown="fnTextCounter(this.form.sign,this.form.numleft1);" 
	                    				onkeyup="fnTextCounter(this.form.sign,this.form.numleft1);">
	                   </html:textarea>  
	               
	               <div align="left">
							<font color="#005BCC">
								No. of characters remaining: 
								<input name="numleft1" type="text" size="5" maxlength="3" value="200" disabled class="text_box">
							</font>
                </td>
                <td nowrap class="body_detail_text">&nbsp;</td>
                <td class="body_detail_text">&nbsp;</td>
              </tr>
              <tr align="left" valign="top">
                <td nowrap class="heading_thread">Show signature in posting </td>
                <td class="body_detail_text"> 
                   <html:checkbox property="showSign" value='Y'  tabindex="17"  />
                </td>
                <td nowrap class="body_detail_text">&nbsp;</td>
                <td class="body_detail_text">&nbsp;</td>
              </tr>
              <tr align="left" valign="top"> 
                <td nowrap class="heading_thread">Number of posts</td>
                <td class="body_detail_text"> <bean:write name="UserProfileForm" property="totPostings"/></td>
                <td nowrap class="body_detail_text">&nbsp;</td>
                <td class="body_detail_text">&nbsp;</td>
              </tr>
              <tr align="left" valign="top"> 
                <td colspan="4" nowrap class="Table_head">Preference</td>
              </tr>
              <tr align="left" valign="top"> 
                <td nowrap class="heading_thread">Bookmarks for alert </td>
                <td class="body_detail_text"> 
                <logic:present name="bookmarks" scope="request"> 
	                 <html:select property="bookIds" styleClass="Text" tabindex="20" multiple="true" style="width:220px">
									    <html:options collection="bookmarks" property="value" labelProperty="label" />
					  </html:select>
                   <br>
                   <a href="#" onClick="javascript:fnWebBookMarkdelete()"><img src='<bean:message key="OIFM.docroot"/>/images/remove.gif' border="0" alt = "Remove"></a></td>
                 </logic:present>
                 <logic:notPresent name="bookmarks" scope="request"> 
		                 <bean:message key="NoBookMarks" />
                 </logic:notPresent>
                <td nowrap class="heading_thread">Sticky Threads</td>
                <td class="body_detail_text">
                	 <logic:present name="sticky" scope="request"> 
	                	 <html:select property="stickyIds" styleClass="Text" tabindex="20" multiple="true" style="width:220px">
								    <html:options collection="sticky" property="value" labelProperty="label" />
						  </html:select>
    	               <br><br>
        	           <a href="#" onClick="javascript:fnWebStickydelete()"><img src='<bean:message key="OIFM.docroot"/>/images/remove.gif' border="0" alt = "Remove"></a></td>
            	    </logic:present>
                	 <logic:notPresent name="sticky" scope="request"> 
		            	     <bean:message key="NoSticky" />
	                 </logic:notPresent>
                 </td>
              </tr>
              <tr align="left" valign="top"> 
                <td nowrap class="heading_thread" colspan="4">&nbsp;&nbsp;&nbsp;Inform me when activities are conducted in these topics (including Survey, Consultation Papers)&nbsp;&nbsp;
              </tr>
			   <tr align="left" valign="top">
                <td nowrap class="body_extract_text" colspan="4">
				<span title="example format : Book,Movie,Music">&nbsp;&nbsp;&nbsp;Tags :</span>
				<logic:present name="alTagNames" scope="session">
						<logic:iterate id="objTagName" name="alTagNames" scope="session" indexId="ifdx" type="com.oifm.common.OIBAAddTag">
        					<span class="Boxoutline"><bean:write name="objTagName" property="tagName" /></span>,
						</logic:iterate>
				</logic:present>
				<logic:notPresent name="alTagNames" scope="session">							  
							Please Click on the button to add tag to your profile.	    
				</logic:notPresent>
				&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:;" onClick="javascript:fnSelectTags();"><img src='<bean:message key="OIFM.docroot" />/images/select-tags.gif' border="0" alt="Add Tags" /></a></td>
				</td>
              </tr>
              <tr align="left" valign="top"> 
                <td nowrap class="body_detail_text">&nbsp;</td>
                <td class="body_detail_text">&nbsp;</td>
                <td nowrap class="body_detail_text">&nbsp;</td>
                <td class="body_detail_text">&nbsp;</td>
              </tr>
         <tr>
          <td height="35" align="left" colspan="4"> 
	          <a href="#"onClick="javascript:fnWebSave()"><img src='<bean:message key="OIFM.docroot"/>/images/btn_Save.gif'  border="0" alt = "Save"></a> 
	          <a href="#" onClick="javascript:fnWebReset()"><img src='<bean:message key="OIFM.docroot"/>/images/btn_Clear.gif'  border="0" alt = "Reset"></a>
		</td>
	</tr>
	</table></td></tr>
	</table></td>


        </tr>
      </table>      </td>
  </tr>
</table>

  <jsp:include page="/jsp/common/oifm_footer.jsp" flush="true" />
 
 

<html:hidden property="id"/>
<html:hidden property="hiddenAction"/>
<html:hidden property="name"/>
<html:hidden property="totPostings"/>
</html:form>
<script>
		  fnTextCounter(document.UserProfileForm.sign,document.UserProfileForm.numleft1);
		  fnTextCounter(document.UserProfileForm.area,document.UserProfileForm.numleft2);
		  fnTextCounter(document.UserProfileForm.hobbies,document.UserProfileForm.numleft3);
</script>

</body>

</html:html>
