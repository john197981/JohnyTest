<%--
/**
 * FileName		 	: iofmAdminProfileDetail.jsp
 * Author      		: Suresh Kumar.R
 * Created Date 	: 7 Aug 2005
 * Description 		: This page used to select the list of users
 * Version			: 1.0
 **/
--%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<html:html>
<head>
<title>Admin Profile Detail</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href='<bean:message key="OIFM.docroot" />/css/iofs.css' rel="stylesheet" type="text/css">
<script language="javascript" src='<bean:message key="OIFM.docroot"/>/js/AdminProfile.js'></script>
</head> 
<script>
 var strRole =  '<bean:write name="role" scope="session"/>'
</script>
<body onLoad="fnLoad();">
<table width="98%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td class="TableHead">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
			<td class="Box">
				<table width="100%" border="0" cellpadding="1"	cellspacing="1" bgcolor="white">
					<tr class="Table_head" >
						<td colspan="4">User Profile</td>
 					</tr>     
      <logic:present name="save" scope="request">
 							<tr align="center"><td class="body_detail_text" colspan="4">
							   		     <font color="red"><bean:message key="Userupdate"/></font> 
   <br>
		   	   <br>
		   	   
		   	   <a href="#" onClick="javascript:fnSearchview()";><img src='<bean:message key="OIFM.docroot" />/images/but_ok.gif'border="0" alt = "Close"></a> 
			</td></tr>	
	 </logic:present>	
	  <logic:present name="disable" scope="request">
 							<tr align="center"><td class="body_detail_text" colspan="4">
							   		     <font color="red">The user profile has been successfully updated.</font> 
   <br>
		   	   <br>
		   	   
		   	   <a href="#" onClick="javascript:fnSearchview()";><img src='<bean:message key="OIFM.docroot" />/images/but_ok.gif'border="0" alt = "Close"></a> 
			</td></tr>	
	 </logic:present>	
	 <logic:notPresent name="disable" scope="request">
	<logic:notPresent name="save" scope="request">
	<html:form action="/UserProfile">  
    <logic:iterate id="Profile" name="profile" type="com.oifm.useradmin.admin.OIBVAdminUserProfile" scope="request">  
               <tr align="left" valign="top"> 
                <td colspan="4" class="Sub_head">View / Edit Profile Detail 
                </td>
              </tr>
              <tr align="left" valign="top"> 
                <td width="14%" height="30" nowrap class="heading_attributes">NRIC</td>
                <td width="29%" height="30" nowrap class="heading_attributes"> <bean:write name="Profile" property="NRIC"/></td>
                <td width="14%" height="30" nowrap class="heading_attributes">Role</td>
                <td width="43%" height="30" nowrap class="heading_attributes"> 
                
                <%boolean bFlag =false;%>
                  <logic:match name="role" value="MODERATOR" scope="session">
						<logic:equal name="Profile" property="role" value="DIVADMIN">
							<bean:write name="Profile" property="role"/>
							<html:hidden property="role" value='<%=Profile.getRole()%>'/>
							<%bFlag=true;%>
						</logic:equal>
		           </logic:match>  									

					<logic:notEqual name="Profile" property="adminId" value='<%=Profile.getRole()%>'>

						    <logic:match name="role" value="DIVADMIN" scope="session">
							    <logic:equal name="Profile" property="role" value="DIVADMIN">
										<bean:write name="Profile" property="role"/>
										<html:hidden property="role" value='<%=Profile.getRole()%>'/>
										<%bFlag=true;%>
								</logic:equal>		
							</logic:match>							
							
						<%if(!bFlag){ %>
							<html:select property="role" styleClass="Text" tabindex="4" value='<%=Profile.getRole()%>'>
							    <html:options collection="role" property="value" labelProperty="label" />
						 	</html:select> 
						<%}%> 	
						
					</logic:notEqual>

				  <logic:equal name="Profile" property="adminId" value='<%=Profile.getRole()%>'>
 
	                   <logic:match name="role" value="ADMIN" scope="session">
	                   			<html:select property="role" styleClass="Text" tabindex="4" value='<%=Profile.getRole()%>'>
								    <html:options collection="role" property="value" labelProperty="label" />
							 	</html:select> 
					 	<%="Profile"%>
	                   </logic:match>  										
	                   <logic:notMatch name="role" value="ADMIN" scope="session">
	                   			<bean:write name="Profile" property="role"/>
								<html:hidden property="role" value='<%=Profile.getRole()%>'/>
	                  </logic:notMatch>  											
				 </logic:equal>
				
				 
				 
				 

			  </td>
              </tr>
              <tr align="left" valign="top">
                <td nowrap class="heading_attributes">Salutation</td>
                <td nowrap class="heading_attributes">
						<logic:notEqual name="Profile" property="adminId" value='<%=Profile.getRole()%>'>
		                	<bean:write name="Profile" property="salutation"/>
						</logic:notEqual>
                </td>
                <td nowrap class="heading_attributes">Nick Name </td>
                <td nowrap class="heading_attributes">
                	
                	<logic:equal name="role" value="ADMIN" scope="session">
					<logic:notEqual name="Profile" property="adminId" value='<%=Profile.getRole()%>'>
			                	<bean:write name="Profile" property="nickName"/>
					</logic:notEqual>
					</logic:equal>
                	</td>
              </tr>
              <tr align="left" valign="top">
                <td nowrap class="heading_attributes">Name</td>
                <td nowrap class="heading_attributes"> <bean:write name="Profile" property="name"/> </td> 
                <td nowrap class="heading_attributes">Email ID </td>
                <td nowrap class="body_detail_text">
				                <html:text property="mailName" 
		        		       			  styleClass="Text_box"
		        		       			  maxlength="40" 
		        		       			  size="25"
		        		       			  tabindex="1"/> @
		          
		          <html:select property="mailDomain">
		          		<html:options name="Domains" labelName="Domains"/>
		          </html:select>
                </td>
              </tr>
              <tr align="left" valign="top">
                <td nowrap class="heading_attributes">Age </td>
                <td nowrap class="heading_attributes"> <bean:write name="Profile" property="age"/></td> 
                <td nowrap class="heading_attributes">Joined Service </td>
                <td nowrap class="heading_attributes"><bean:write name="Profile" property="yrJoin"/></td>
              </tr>
              <tr align="left" valign="top">
                <td nowrap class="heading_attributes">Designation</td>
                <td nowrap class="heading_attributes"><bean:write name="Profile" property="design"/></td> 
                <td nowrap class="heading_attributes">Grade</td>
                <td nowrap class="heading_attributes"><bean:write name="Profile" property="grade"/></td>
              </tr>
              <tr align="left" valign="top"> 
                <td nowrap class="heading_attributes">Divisional Status</td>
                <td nowrap class="heading_attributes"><bean:write name="Profile" property="divStatus"/></td>
                <td nowrap class="heading_attributes">Division</td>
                <td nowrap class="heading_attributes"> <bean:write name="Profile" property="division"/></td>
              </tr>
              <tr align="left" valign="top">
                <td nowrap class="heading_attributes">CCA</td>
                <td nowrap class="body_detail_text"> 
                <%int i =1;%>
                <logic:present name="cca" scope="request">
		                  <table width="121" border="1" cellpadding="0" cellspacing="0" bordercolor="#BEDCF0">
    		                <tr> 
        			              <td height="19" bgcolor="#DBECF7" class="heading_attributes"> 
                			        <div align="center" class="heading_attributes"> 
                    	    			 <div align="left">No. </div>
		                	        </div>
        		            	  </td>
	                		      <td class="heading_attributes">CCA</td>
				           </tr>
				           <logic:iterate id="CCA" name="cca" type="String" scope="request" indexId="rowId">  
			      	        <tr> 
            	    		      <td width="18" height="19" bgcolor="#DBECF7" align="center" class="heading_attributes"> 
                	    	    		<div align="left" class="heading_attributes"><%=i++%></div>
		            	          </td>
	        		        	   <td width="97" class="heading_attributes"><bean:write name="CCA"/>
	        		        	   </td>
                		  </tr>
                   	   	  </logic:iterate>	  
                   	   	  </table>
                </logic:present>
                </td>
                <td nowrap class="heading_attributes">Branch/Zone</td>
                <td nowrap class="heading_attributes"> <bean:write name="Profile" property="zone"/> </td>
              </tr>
              <tr align="left" valign="top">
                <td nowrap class="heading_attributes"><b>School Type</b></td>
                <td nowrap class="heading_attributes"> <bean:write name="Profile" property="schoolType"/></td> 
                <td nowrap class="heading_attributes">School</td>
                <td nowrap class="heading_attributes"> <bean:write name="Profile" property="school"/></td>
              </tr>
              <tr align="left" valign="top"> 
                <td nowrap class="heading_attributes">Cluster</td>
                <td nowrap class="heading_attributes"><bean:write name="Profile" property="cluster"/></td>

              </tr>
              <tr align="left" valign="top"> 
                <td nowrap class="heading_attributes">Level Teaching</td>
                <td nowrap class="heading_attributes"> 
                <%i=1;%>
				 <logic:present name="teachlevel" scope="request">
		                  <table width="121" border="1" cellpadding="0" cellspacing="0" bordercolor="#BEDCF0">
    		                <tr> 
        			              <td height="19" bgcolor="#DBECF7"> 
                			        <div align="center" class="bold"> 
                    	    			 <div align="left">No. </div>
		                	        </div>
        		            	  </td>
	                		      <td class="bold">Level</td>
				           </tr>
				           
				           <logic:iterate id="Teach" name="teachlevel" type="String" scope="request" indexId="rowId">  
			      	        <tr> 
            	    		      <td width="18" height="19" bgcolor="#DBECF7" align="center"> 
                	    	    		<div align="left" class="bold"><%=i++%></div>
		            	          </td>
	        		        	   <td width="97" class="bold"><bean:write name="Teach"/>
	        		        	   </td>
                		  </tr>
                   	   	  </logic:iterate>	  
                   	   	   </table>
                </logic:present>
                </td>
                
                <td nowrap class="heading_attributes">Subject Teaching</td>
                <td nowrap class="heading_attributes"> 
                <%i=1;%>
                    <logic:present name="sublevel" scope="request">
	            	      <table width="121" border="1" cellpadding="0" cellspacing="0" bordercolor="#BEDCF0">
    	            	    <tr> 
        		        	      <td height="19" bgcolor="#DBECF7" class="heading_attributes"> 
                		        	<div align="center" class="heading_attributes"> 
                        				 <div align="left">No. </div>
		                        	</div>
	        		              </td>
		                		      <td class="heading_attributes">Level</td>
				           </tr>
			    	       <logic:iterate id="Subject" name="sublevel" type="String" scope="request" indexId="rowId">  
			                <tr> 
                		 	     <td width="18" height="19" bgcolor="#DBECF7" align="center"> 
                        			<div align="left" class="heading_attributes"><%=i++%></div>
			                      </td>
	        		              <td width="97" class="heading_attributes"><bean:write name="Subject"/></td>
        	        		</tr>
    	               	   </logic:iterate>	  
    	               	   </table>
                   </logic:present>
              </tr>
              <tr align="left" valign="top"> 
                <td nowrap class="heading_attributes">Signature</td>
                <td nowrap class="heading_attributes"> 
                
	                    <html:textarea  property="sign" 
	                    				styleClass="Text" 
	                    				 cols="35" rows="5"
	                    				onkeydown="fnTextCounter(this.form.sign,this.form.numleft1);" 
	                    				onkeyup="fnTextCounter(this.form.sign,this.form.numleft1);"
	                    				value='<%=Profile.getSign()%>'>
	                   </html:textarea>  
	               
	               <div align="left">
							<font color="#005BCC">
								No. of characters remaining: 
								<input name="numleft1" type="text" size="5" maxlength="3" value="200" disabled style="font-family: Arial, Helvetica, sans-serif;font-size:11px; background: #FFFFFF;border-bottom: 1px solid #7F9DB9;border-right: 1px solid #7F9DB9;border-left: 1px solid #7F9DB9;border-top:1px solid #7F9DB9; color:#018BAB;text-decoration:none">
							</font>
				 </div>
                </td>
                <td nowrap class="heading_attributes">Areas of Interest </td>
                <td nowrap class="heading_attributes">
						
	                    <html:textarea  property="area" 
	                    				styleClass="Text" 
	                    				 cols="35" rows="5"
	                    				onkeydown="fnTextCounter(this.form.area,this.form.numleft2);" 
	                    				onkeyup="fnTextCounter(this.form.area,this.form.numleft2);"
	                    				value='<%=Profile.getArea()%>'>
	                    				
	                   </html:textarea>  
  	               
	               <div align="left">
							<font color="#005BCC">
								No. of characters remaining: 
								<input name="numleft2" type="text" size="5" maxlength="3" value="200" disabled style="font-family: Arial, Helvetica, sans-serif;font-size:11px; background: #FFFFFF;border-bottom: 1px solid #7F9DB9;border-right: 1px solid #7F9DB9;border-left: 1px solid #7F9DB9;border-top:1px solid #7F9DB9; color:#018BAB;text-decoration:none">
							</font>
				 </div>              </tr>
              <tr align="left" valign="top">
                <td nowrap class="heading_attributes">Hobbies</td>
                <td nowrap class="heading_attributes"><p>

	                    <html:textarea  property="hobbies" 
	                    				styleClass="Text" 
		                    			 cols="35" rows="5"
	                    				onkeydown="fnTextCounter(this.form.hobbies,this.form.numleft3);" 
	                    				onkeyup="fnTextCounter(this.form.hobbies,this.form.numleft3);"
	                    				value='<%=Profile.getHobbies()%>'>

	                   </html:textarea>  
		               <div align="left">
							<font color="#005BCC">
								No. of characters remaining: 
								<input name="numleft3" type="text"  size="5" maxlength="3" value="200" disabled style="font-family: Arial, Helvetica, sans-serif;font-size:11px; background: #FFFFFF;border-bottom: 1px solid #7F9DB9;border-right: 1px solid #7F9DB9;border-left: 1px solid #7F9DB9;border-top:1px solid #7F9DB9; color:#018BAB;text-decoration:none">
							</font>
				 </div>
                </p></td>
                  
              </tr>
         <tr>
          <td height="35" align="left" colspan="4">
         	       <a href="#" onClick="javascript:fnUpdate();"><img src='<bean:message key="OIFM.docroot" />/images/btn_Save.gif' border="0" alt = "Save"></a> 
            	   <a href="#" onClick="javascript:fnSearchview()";><img src='<bean:message key="OIFM.docroot" />/images/btn_Cancel.gif' border="0" alt = "Cancel"></a> 
	               <a href="#" onClick="javascript:fnResetView();"><img src='<bean:message key="OIFM.docroot" />/images/btn_Clear.gif' border="0" alt = "Reset"></a>
				   <html:hidden property="obsolete" />
				 <logic:equal  name="Profile" property="obsolete" value="Y">
				 
				    <a href="#" onClick="javascript:fnEnable();"><img src='<bean:message key="OIFM.docroot" />/images/btn_Enable.gif' border="0" alt = "Enable"></a>
				</logic:equal>
				
				 <logic:notEqual name="Profile" property="obsolete" value="Y">
					 <a href="#" onClick="javascript:fnDisable();"><img src='<bean:message key="OIFM.docroot" />/images/btn_Disable.gif' border="0" alt = "Disable"></a>
				   
				</logic:notEqual>
           </td>
        </tr>
  
  </logic:iterate>    

<!-- Hidden Fields --> 



 
<html:hidden property="id"/>
<html:hidden property="hiddenAction"/>
</html:form>	 
<script>
		  fnTextCounter(document.UserProfileForm.sign,document.UserProfileForm.numleft1);
		  fnTextCounter(document.UserProfileForm.area,document.UserProfileForm.numleft2);
		  fnTextCounter(document.UserProfileForm.hobbies,document.UserProfileForm.numleft3);
</script>
</logic:notPresent>
</logic:notPresent>

				</table>
			</td>
		</tr>
	</table>
	</td>
	</tr>
</table>

</body>
</html:html>
