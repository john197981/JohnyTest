<%--
/**
 * FileName			: iofmAdminProfile.jsp
 * Author      		: Suresh Kumar.R
 * Created Date 	: 4 Aug 2005
 * Description 		: This page used to select the list of users
 * Version			: 1.0
 **/ 
--%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>



<html:html> 
<head>
<title>Admin Profile</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<link href='<bean:message key="OIFM.docroot" />/css/iofs.css' rel="stylesheet" type="text/css">


<script>
	var strDocRoot ='<bean:message key="OIFM.contextroot" />';
</script>	  
<script language="javascript" src='<bean:message key="OIFM.docroot"/>/js/AdminProfile.js'></script>
</head>




<body>
<html:form action="/UserProfile" focus="NRIC">

<jsp:include page="/jsp/common/oifm_admin_header.jsp" flush="true" />

<table width="98%" border="0" cellpadding="0" cellspacing="0">

	<tr>
		<td class="TableHead">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
			<td class="Box">
				<table width="98%" border="0" cellpadding="1"
					cellspacing="1" bgcolor="white">
					<tr class="Table_head" >
						<td colspan="6">User Profile 
						 </td>
					</tr>
 
              
              <tr align="left" valign="top" class="heading_attributes"> 
	                <td width="3%" class="body_detail_text"> 
	            	      <html:checkbox property="colName" value="USERID"  tabindex="1" onclick="fnEnableCheck()"/>
	                </td>
	                <td width="18%" class="heading_attributes">
	                		NRIC
	                </td>
	                <td nowrap class="body_detail_text"> 
		                 <html:text property="NRIC" 
		        		       			  styleClass="Text_box"
		        		       			  maxlength="9" 
		        		       			  size="25"
		        		       			  tabindex="2"/>
        	        </td>
            	    <td width="3%" class="body_detail_text"> 
                	  <html:checkbox property="colName" value="ROLEID"  tabindex="3"/>
	                </td>
    	            <td width="13%" nowrap class="heading_attributes">
    	            	Role
    	            </td>
        		    <td class="body_detail_text"> 
			                 <html:select property="role" styleClass="Text_box" tabindex="4" >
									    <html:options collection="role" property="value" labelProperty="label" />
							  </html:select>
                	</td>
              </tr>
              
              <tr align="left" valign="top"> 
	                <td class="body_detail_text"> 
   	            	      <html:checkbox property="colName" value="NAME"  tabindex="5"/>
	                </td>
	                <td class="heading_attributes">
	                	Name
	                </td>
	                <td nowrap class="body_detail_text"> 
	                	  <html:text property="name" 
		        		       			  styleClass="Text_box"
		        		       			  maxlength="30" 
		        		       			  size="25"
		        		       			  tabindex="6"/>
	                </td>
	                <td class="body_detail_text"> 
		                   <html:checkbox property="colName" value="SCHOOL"  tabindex="7"  />
	                </td>
	                <td width="13%" nowrap class="heading_attributes">
	                	School
	                </td>
	                <td class="body_detail_text"> 
		                    <html:select property="school" styleClass="Text_box" tabindex="8" >
									    <html:options collection="school" property="value" labelProperty="label" />
							  </html:select>
	                </td>
              </tr>
              
              <tr align="left" valign="top"> 
	                <td class="body_detail_text"> 
		                  
		                  <logic:equal name="role" value="ADMIN" scope="session">
		                  	<html:checkbox property="colName" value="NICKNAME"  tabindex="9" />
		                  </logic:equal>
		                  
		                   <logic:notEqual name="role" value="ADMIN" scope="session">
		                   		<html:checkbox property="colName" value="NICKNAME"  tabindex="9" disabled="true" />
		                   </logic:notEqual>
		                  
	                </td>
	                <td class="heading_attributes">
	                		Nick Name
	                </td>
	                <td nowrap class="body_detail_text"> 
	                  <p> 
	                  		 <logic:equal name="role" value="ADMIN" scope="session">
	                    	 <html:text property="nickName" 
		        		       			  styleClass="Text_box"
		        		       			  maxlength="66" 
		        		       			  size="25"
		        		       			  tabindex="10"/>
		        		      </logic:equal>
		        		       			  
		        		      <logic:notEqual name="role" value="ADMIN" scope="session">
		        		      <html:text property="nickName" 
		        		       			  styleClass="Text_box"
		        		       			  maxlength="66" 
		        		       			  size="25"
		        		       			  tabindex="10"
		        		       			  disabled="true"/>
		        		      </logic:notEqual>
	                </td>
	                <td class="body_detail_text"> 
	                	   <html:checkbox property="colName" value="SCHOOL_TYPE"  tabindex="11"  />
	                </td>
	                <td nowrap class="heading_attributes">
	                	School Type
	                </td>
	                <td class="body_detail_text"> 
	                	  <html:select property="schoolType" styleClass="Text_box" tabindex="12" >
									    <html:options collection="schooltype" property="value" labelProperty="label" />
						  </html:select>
	                </td>
              </tr>
              
              <tr align="left" valign="top"> 
	               	<td class="body_detail_text"> 
	                	   <html:checkbox property="colName" value="EMAILID"  tabindex="13"  />
	                </td>
	                <td class="heading_attributes">
	                	Email ID
	                </td>
	                <td width="17%" nowrap class="body_detail_text"> 
	                	   <html:text property="mailId" 
		        		       			  styleClass="Text_box"
		        		       			  maxlength="40" 
		        		       			  size="25"
		        		       			  tabindex="14"/>
	                </td>
	                  <!-- Grade -->
					 <td class="body_detail_text"> 
		                   <html:checkbox property="colName" value="DESIGNATION"  tabindex="15"  />
	                </td>
	                <td nowrap class="heading_attributes">
	                		Designation
	                </td>
	                <td class="body_detail_text"> 
		                 <html:select property="design" styleClass="Text_box" tabindex="16" >
									    <html:options collection="designation" property="value" labelProperty="label" />
						  </html:select>
	                </td>


              </tr>
              
              <tr align="left" valign="top"> 
	                <td class="body_detail_text"> 
		                 <html:checkbox property="colName" value="AGE"  tabindex="17"  />
	                </td>
	                <td class="heading_attributes">
		                	Age 
	                </td>
	                 <td nowrap class="heading_attributes"> From &nbsp&nbsp
	                	 <html:text property="fromAge" 
				        		       	   styleClass="Text_box"
				        		       	   maxlength="2" 
				        		       	   size="2"
				        		       	   tabindex="18"
				        		       	   onkeypress="return fngetKeyCodeInteger(event);"
				        		       	   onblur="fnBlur(this.form.fromAge);"/>
	               
   						 &nbsp&nbsp&nbsp &nbsp&nbsp  To &nbsp&nbsp
	                	 <html:text property="toAge" 
				        		       	   styleClass="Text_box"
				        		       	   maxlength="2" 
				        		       	   size="2"
				        		       	   tabindex="19"
				        		       	   onkeypress="return fngetKeyCodeInteger(event);"
				        		       	    onblur="fnBlur(this.form.toAge);"/>
	                </td>
	                
	                <td class="body_detail_text"> 
		                  <html:checkbox property="colName" value="DIVISION"  tabindex="20"  />
	                </td>
	                <td nowrap class="heading_attributes">
	                		Division
	                </td>
	                <td class="body_detail_text"> 
	                	   <html:select property="division" styleClass="Text_box" tabindex="21" >
									    <html:options collection="division" property="value" labelProperty="label" />
						  </html:select>
	                </td>                
	               
              </tr>
              
              <tr align="left" valign="top"> 
	                <td class="body_detail_text"> 
	               		  <html:checkbox property="colName" value="INTEREST"  tabindex="22"  />
	                </td>
	                <td class="heading_attributes">
	                		Area of Interest 
	                </td>
	                <td nowrap class="body_detail_text"> 
	                	  <html:text property="area" 
		        		       			  styleClass="Text_box"
		        		       			  maxlength="66" 
		        		       			  size="25"
		        		       			  tabindex="23"/>
	                </td>
		            <td class="body_detail_text"> 
		                  	   <html:checkbox property="colName" value="BRANCH_ZONE"  tabindex="24"  />
		                </td>
		                <td nowrap class="heading_attributes">
		                		Zone/Branch
		                </td>
		                <td width="49%" class="body_detail_text"> 
		                	 <html:select property="zone" styleClass="Text_box" tabindex="25" >
										    <html:options collection="zone" property="value" labelProperty="label" />
							  </html:select>
		                </td>
        
              </tr>
              
              <tr align="left" valign="top"> 
	                <td class="body_detail_text"> 
		                <html:checkbox property="colName" value="CCA_1"  tabindex="26"  />
	                </td>
	                <td class="heading_attributes">
	                	CCA
	                </td>
	                <td nowrap class="body_detail_text"> 
	                	   <html:text property="cca" 
		        		       		  styleClass="Text_box"
		        		       		  maxlength="30" 
					        		  size="25"
		    			    		  tabindex="27"/>
	                </td>
	                <td class="body_detail_text"> 
		                    <html:checkbox property="colName" value="SCHOOL_CLUSTER"  tabindex="28"  />
	                </td>
	                <td class="heading_attributes">
	                	Cluster
	                </td>
	                <td nowrap class="body_detail_text"> 
	                	  <html:select property="cluster" styleClass="Text_box" tabindex="29" >
									    <html:options collection="cluster" property="value" labelProperty="label" />
						  </html:select>
	                </td>
	              
              </tr>
              
              <tr align="left" valign="top"> 
	                
	                <td class="body_detail_text"> 
						<input type="checkbox"  value="" disabled="true" tabindex="30"  />
				     </td>
	                <td nowrap class="heading_attributes"> Level Teaching </td>
	                <td class="body_detail_text"> 
		                  <html:text property="levelTech" 
		        		       		  styleClass="Text_box"
		        		       		  maxlength="25" 
					        		  size="25"
		    			    		  tabindex="31"/>
		            </td>
		             <td class="body_detail_text"> 
			                  <html:checkbox property="colName" value="DIV_STATUS"  tabindex="32"  />	    	             
			        </td>
	                <td nowrap class="heading_attributes">Divisional Status</td>
	                <td class="body_detail_text"> 
	        	          <html:select property="divStatus" styleClass="Text_box" tabindex="33" >
									  <!--html:options collection="div_status" property="value" labelProperty="label" /-->
									  <html:option value="">Please Select...</html:option>
									  <html:option value="Div I">Div I</html:option>
										<html:option value="Div I (Sup)">Div I (Sup)</html:option>
										<html:option value="Div II">Div II</html:option>
										<html:option value="Div III">Div III</html:option>
										<html:option value="Div IV">Div IV</html:option>
										
							 </html:select>
						    
	                </td>
		           
              </tr>
              
              <tr align="left" valign="top"> 
	                <td class="body_detail_text"> 
		                   <input type="checkbox"  value="" disabled="true" tabindex="34"  />
	                </td>
	                <td nowrap class="heading_attributes">Subject Teaching</td>
	                <td class="body_detail_text"> 
		                  <html:text property="subTech" 
		        		       		  styleClass="Text_box"
		        		       		  maxlength="25" 
					        		  size="25"
		    			    		  tabindex="35"/>
	                </td>
	                <td class="body_detail_text"> 
	    	               <html:checkbox property="colName" value="HOBBIES"  tabindex="36"  />
	                </td>
	                <td nowrap class="heading_attributes">Hobbies</td>
	        	    <td class="body_detail_text"> 
	            	       <html:text property="hobbies" 
		        		       		  styleClass="Text_box"
		        		       		  maxlength="25" 
					        		  size="25"
		    			    		  tabindex="37"/>
	                </td>
              </tr>
              
              <tr align="left" valign="top"> 
	                <td class="body_detail_text"> 
		                  <html:checkbox property="colName" value="JOINDT"  tabindex="38"  />
	                </td>
	                <td nowrap class="heading_attributes">Year of Joining Service</td>
	                <td class="heading_attributes"> From &nbsp&nbsp
		                  <html:text property="fromYrJoin" 
				        		       	   styleClass="Text_box"
				        		       	   maxlength="4" 
				        		       	   size="4"
				        		       	   tabindex="39"
				        		       	   onkeypress="return fngetKeyCodeInteger(event);"
										   onblur="fnBlur(this.form.fromYrJoin);"/>
						&nbsp&nbspTo&nbsp&nbsp
		                  <html:text property="toYrJoin" 
				        		       	   styleClass="Text_box"
				        		       	   maxlength="4" 
				        		       	   size="4"
				        		       	   tabindex="40"
				        		       	   onkeypress="return fngetKeyCodeInteger(event);"
										   onblur="fnBlur(this.form.toYrJoin);"/>
	                </td>
              </tr>              
     
         <tr>
          <td height="35" align="left" colspan="6">
  	          	<a href="#" onClick="javascript:fnSltUsrSearch();">
	          		<img src='<bean:message key="OIFM.docroot"/>/images/btn_Search.gif' border="0" tabindex="41" alt = "Search"></a> 
	          	<a href="#" onClick="javascript:fnClrSrh()" >
	          		<img src='<bean:message key="OIFM.docroot"/>/images/btn_Clear.gif' border="0" tabindex="42" alt = "Reset"></a> 
            </td>
        </tr>
        
    <%boolean bIsMail =false; %>  
</table>
</td></tr>

				</table>
			</td>
		</tr>
	</table>
	<br>
   <logic:present name="<%=com.oifm.useradmin.OISearchConstants.SEARCH%>" scope="request">
	<DIV align=left style='width: 725px; height: 207px; overflow: scroll;'  class="Box"> 
	<table width="100%">
              <tr class="Table_head"> 
	                <logic:iterate id="ColNames" name="colnames" scope="request" type="java.lang.String" >
 		                        	<td nowrap class="Table_head"> 
	                      	<script>document.write(fnTitle('<bean:write name="ColNames"/>'));</script> 
	        	            <a href="#"> 
									
							  		<logic:equal name="UserProfileForm" property="hidSortKey" value="<%=ColNames%>" scope="request">
							  	
								  		<logic:equal name="UserProfileForm" property="hidOrder" value="ASC">
									  		 <img src='<bean:message key="OIFM.docroot" />/images/DownArrow.gif' width="17" height="16" border="0" alt = "Down" onClick="javascript:fnSortUsers('<bean:write name="ColNames"/>','DESC');">
									  	</logic:equal>
									  	<logic:equal name="UserProfileForm" property="hidOrder" value="DESC" scope="request" >
									  		 <img src='<bean:message key="OIFM.docroot" />/images/btn_uparrow.gif' width="17" height="16" border="0" alt = "Up" onClick="javascript:fnSortUsers('<bean:write name="ColNames"/>','ASC');">
									  	</logic:equal>
									 </logic:equal>
									 
									 <logic:notEqual name="UserProfileForm" property="hidSortKey" value="<%=ColNames%>" scope="request">
									  		 <img src='<bean:message key="OIFM.docroot" />/images/btn_rightarrow.gif' width="17" height="16" border="0" alt = "Right" onClick="javascript:fnSortUsers('<bean:write name="ColNames"/>','ASC');">
									 </logic:notEqual>
									  			 
								</a>  	
	                    	</td> 
 	                 </logic:iterate> 	
              
              </tr>
              <logic:iterate id="ColDesc" name="search" scope="request">
              <tr class="heading_attributes"> 
                <bean:define id="ColDef" name="ColDesc" type="java.util.LinkedHashMap"/>
                <%

					String strKey = null;
					String strValue =null;
					
					java.util.Set sobj = ColDef.keySet();
    				java.util.Iterator it =   sobj.iterator();
					String strUserId = (String)ColDef.get("USERID");
					//String strPaperId = (String)ColDef.get("PAPERID");
					//String strEmail = (String)ColDef.get("EMAILID");
					
			
    				while(it.hasNext()){
 						 strKey = (String) it.next();
						 strValue =(String) ColDef.get(strKey);%>
 	             		  <td nowrap class="heading_attributes" > 
 	             		  	<% if(strKey.equals("USERID")){ %>		
		 	             		  	<a class="special_body_link" href="#" onClick="javascript:fnUserView('<%=strValue%>')"><%=strValue%></a>
 	             		  	<%}else{%>
 	             		  			<%=strValue%>
 	             		  	<%}%>		
						 </td>		
					<%}%>
     		                  
              </tr>
              </logic:iterate>
                   
            </table>
     <DIV> 
    
<logic:present name="currentPage" scope="request">
	<table  border="0" cellspacing="0" cellpadding="0" align="right"> 
	<tr>		
		<td align="right">

  					<table  border="0" cellpadding="3" cellspacing="0" class="BodyText" align="right">
						<tr>
							<td nowrap class="Boxinside_text"> 
								Page 
								<bean:write name="currentPage" scope="request" /> 
							of 
								<bean:write name="totalPage" scope="request" />
							</td>
						<logic:present name="previousSet" scope="request">
							<logic:equal name="previousSet" scope="request" value="true">
								<td nowrap class="BD_3">
								   <a href='javascript:fnPagination(<bean:write name="previousPage" scope="request"/>);'>&laquo;Previous</a>
								</td>
							</logic:equal>  
						</logic:present>
						<!--<td nowrap class="BD_1">1</td>-->
						<logic:present name="arPage" scope="request">
							<logic:iterate id="no" name="arPage" scope="request">
							
									<%	String currentPage=(String) request.getAttribute("pageNo");
										String temp = (String) no;
										if (! currentPage.trim().equals(temp.trim())){
									%>
											<td nowrap class="BD_2">
												<a href='javascript:fnPagination(<bean:write name="no" />);'><bean:write name="no" />

											</a>
											</td>
									<%}	else{%>
											<td nowrap class="BD_1">
													<bean:write name="no" />
													<script>nPagNum='<bean:write name="no"/>'</script>
											</td>
									<%}%>
							</logic:iterate>
						</logic:present>
						<logic:present name="nextSet" scope="request">
							<logic:equal name="nextSet" scope="request" value="true">
								<td nowrap class="BD_3"> 
									<a href='javascript:fnPagination(<bean:write name="nextPage" scope="request"/>);'>Next&raquo;</a>
								</td>
							</logic:equal>
						</logic:present>
						</td>
      				</tr>
				</table>
				</td>
			</tr>
		</table>		
		<input type="hidden" name="pageNo">	
		</logic:present> 
	</logic:present>
<logic:notPresent name="<%=com.oifm.useradmin.OISearchConstants.SEARCH%>" scope="request">
<table>
<tr><td >
	 <logic:equal name="UserProfileForm" property="hiddenAction" value="search" scope="request">
	  <DIV align=left style='width: 725px; height: 207px; overflow: scroll;' class="Box"> 
		  <table width="100%"  align="center" border="0" cellspacing="0" cellpadding="0">
              <tr> 
              	 <logic:iterate id="ColNames" name="colnames" scope="request">
	                  	<td nowrap width="70%" class="Table_head"> <script>document.write(fnTitle('<bean:write name="ColNames"/>'));</script> </td> 
	              </logic:iterate> 	
             </tr> 
             <tr>
				<td class="body_detail_text" align="center" nowrap> <b><br><br> No Records Found </b></td> 
			 </tr>
		 </table>		
		<DIV>	
	  </logic:equal>
</td></tr></table>
</logic:notPresent>

  <!-- hidden Fields -->
	<html:hidden property="id"/>
	<html:hidden property="hiddenAction"/>
	<html:hidden property="flag"/>
	<html:hidden property="rowId"/>
	<html:hidden property="chk"/>
	<html:hidden property="hidSortKey"/>
	<html:hidden property="hidOrder"/>
	<html:hidden property="hidPage"/>
	<html:hidden property="upCnt"/>	 
	<html:hidden property="cols"/>	 
	<br><br>
</body>
 <script>
          fnProfileEnableCheck();
          fnLoadEnableChk(); 
</script>
</html:form>
</html:html>
