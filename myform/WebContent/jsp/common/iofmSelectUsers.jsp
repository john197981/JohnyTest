<%--
/**
 * FileName			: iofmSelectUsers.jsp
 * Author      		: Suresh Kumar.R
 * Created Date 	: 10 jul 2005
 * Description 		: This page used to select the list of users
 * Version			: 1.0
 **/
--%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="com.oifm.common.OIBASendMail" %>


<html:html>

<head>
<base target=_self>
	<title>Select Users</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<link href='<bean:message key="OIFM.docroot" />/css/iofs.css' rel="stylesheet" type="text/css">
	<script language="javascript" src='<bean:message key="OIFM.docroot" />/js/SendMail.js'></script>
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

<body onLoad="fnSltUsrLoad();">
<html:form action ="/SelectUsers" focus="NRIC">

<table width="98%" border="0" cellpadding="0"
		cellspacing="0">

	<tr>
		<td class="TableHead">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
			<td class="Box">
				<table width="100%" border="0" cellpadding="0"
					cellspacing="0" bgcolor="white">
					<tr class="Table_head" >
						<td colspan="6">User Profile  Search </td>
 					</tr>

               <tr align="left" valign="top"> 
	                <td width="3%" class="body_detail_text"> 
	            	      <html:checkbox property="colName" value="USERID"  tabindex="1" onclick="fnEnableCheck()"/>
	                </td>
	                <td width="18%" class="heading_thread">
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
    	            <td width="13%" nowrap class="heading_thread">
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
	                <td class="heading_thread">
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
	                <td width="13%" nowrap class="heading_thread">
	                	School
	                </td>
	                <td class="body_detail_text"> 
		                    <html:select property="school" styleClass="Text_box" tabindex="8" >
									    <html:options collection="school" property="value" labelProperty="label" />
							  </html:select>
	                </td>
              </tr>
              
              <tr align="left" valign="top"> 
	                
	                <!-- NICKNAME -->
	                 <td class="body_detail_text"> 
	                	   <html:checkbox property="colName" value="EMAILID"  tabindex="9"  />
	                </td>
	                <td class="heading_thread">
	                	Email ID
	                </td>
	                <td width="17%" nowrap class="body_detail_text"> 
	                	   <html:text property="mailId" 
		        		       			  styleClass="Text_box"
		        		       			  maxlength="66" 
		        		       			  size="25"
		        		       			  tabindex="10"/>
	                </td>
	                
	                
	                
	                
	                <td class="body_detail_text"> 
	                	   <html:checkbox property="colName" value="SCHOOL_TYPE"  tabindex="11"  />
	                </td>
	                <td nowrap class="heading_thread">
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
		                 <html:checkbox property="colName" value="AGE"  tabindex="13"  />
	                </td>
	                <td class="heading_thread">
		                	Age 
	                </td>
	                <td nowrap class="heading_thread"> From &nbsp&nbsp
	                	 <html:text property="fromAge" 
				        		       	   styleClass="Text_box"
				        		       	   maxlength="2" 
				        		       	   size="2"
				        		       	   tabindex="14"
				        		       	   onkeypress="return fngetKeyCodeInteger(event);"
				        		       	   onblur="fnBlur(this.form.fromAge);"/>
	               
   						 &nbsp&nbsp&nbsp &nbsp&nbsp  To &nbsp&nbsp
	                	 <html:text property="toAge" 
				        		       	   styleClass="Text_box"
				        		       	   maxlength="2" 
				        		       	   size="2"
				        		       	   tabindex="15"
				        		       	   onkeypress="return fngetKeyCodeInteger(event);"
				        		       	   onblur="fnBlur(this.form.toAge);"/>
	                </td>
	          
	                  <!-- Grade -->
					 <td class="body_detail_text"> 
		                   <html:checkbox property="colName" value="DESIGNATION"  tabindex="16"  />
	                </td>
	                <td nowrap class="heading_thread">
	                		Designation
	                </td>
	                <td class="body_detail_text"> 
		                 <html:select property="design" styleClass="Text_box" tabindex="17" >
									    <html:options collection="designation" property="value" labelProperty="label" />
						  </html:select>
	                </td>


              </tr>
              
              <tr align="left" valign="top"> 
	        		 <td class="body_detail_text"> 
	               		  <html:checkbox property="colName" value="INTEREST"  tabindex="18"  />
	                </td>
	                <td class="heading_thread">
	                		Area of Interest 
	                </td>
	                <td nowrap class="body_detail_text"> 
	                	  <html:text property="area" 
		        		       			  styleClass="Text_box"
		        		       			  maxlength="66" 
		        		       			  size="25"
		        		       			  tabindex="19"/>
	                </td>
	                
	                
	                <td class="body_detail_text"> 
		                  <html:checkbox property="colName" value="DIVISION"  tabindex="20"  />
	                </td>
	                <td nowrap class="heading_thread">
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
		                <html:checkbox property="colName" value="CCA_1"  tabindex="22"  />
	                </td>
	                <td class="heading_thread">
	                	CCA
	                </td>
	                <td nowrap class="body_detail_text"> 
	                	   <html:text property="cca" 
		        		       		  styleClass="Text_box"
		        		       		  maxlength="30" 
					        		  size="25"
		    			    		  tabindex="23"/>
	                </td>
	               	               
		            <td class="body_detail_text"> 
		                  	   <html:checkbox property="colName" value="BRANCH_ZONE"  tabindex="24"  />
		                </td>
		                <td nowrap class="heading_thread">
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
						<input type="checkbox"  value="" disabled="true" tabindex="26"  />
				     </td>
	                <td nowrap class="heading_thread"> Level Teaching </td>
	                <td class="body_detail_text"> 
		                  <html:text property="levelTech" 
		        		       		  styleClass="Text_box"
		        		       		  maxlength="25" 
					        		  size="25"
		    			    		  tabindex="27"/>
		            </td>
	               
	               
	                <td class="body_detail_text"> 
		                    <html:checkbox property="colName" value="SCHOOL_CLUSTER"  tabindex="28"  />
	                </td>
	                <td class="heading_thread">
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
	                <td nowrap class="heading_thread">Subject Teaching</td>
	                <td class="body_detail_text"> 
		                  <html:text property="subTech" 
		        		       		  styleClass="Text_box"
		        		       		  maxlength="25" 
					        		  size="25"
		    			    		  tabindex="31"/>
	                </td>
	                
	                
		             <td class="body_detail_text"> 
			                  <html:checkbox property="colName" value="DIV_STATUS"  tabindex="32"  />	    	             
			        </td>
	                <td nowrap class="heading_thread">Divisional Status</td>
	                <td class="body_detail_text"> 
	        	          <html:select property="divStatus" styleClass="Text_box" tabindex="33" >
									  <html:options collection="div_status" property="value" labelProperty="label" />
						  </html:select>
	                </td>
		           
              </tr>
              
              <tr align="left" valign="top"> 
	               <td class="body_detail_text"> 
		                  <html:checkbox property="colName" value="JOINDT"  tabindex="34"  />
	                </td>
	                <td nowrap class="heading_thread">Year of Joining Service</td>
	                <td class="heading_thread"> From &nbsp&nbsp
		                  <html:text property="fromYrJoin" 
				        		       	   styleClass="Text_box"
				        		       	   maxlength="4" 
				        		       	   size="4"
				        		       	   tabindex="35"
				        		       	   onkeypress="return fngetKeyCodeInteger(event);"
				        		       	   onblur="fnBlur(this.form.fromYrJoin);"/>

						&nbsp&nbspTo&nbsp&nbsp
		                  <html:text property="toYrJoin" 
				        		       	   styleClass="Text_box"
				        		       	   maxlength="4" 
				        		       	   size="4"
				        		       	   tabindex="36"
				        		       	   onkeypress="return fngetKeyCodeInteger(event);"
				        		       	   onblur="fnBlur(this.form.toYrJoin);"/>
	                </td>
	               
	               
	               
	                <td class="body_detail_text"> 
	    	               <html:checkbox property="colName" value="HOBBIES"  tabindex="37"  />
	                </td>
	                <td nowrap class="heading_thread">Hobbies</td>
	        	    <td class="body_detail_text"> 
	            	       <html:text property="hobbies" 
		        		       		  styleClass="Text_box"
		        		       		  maxlength="25" 
					        		  size="25"
		    			    		  tabindex="38"/>
	                </td>
              </tr>
            
		     <tr>     
     	 	     <td height="35" align="left" colspan = 3>
          			<p>
          			<a href="#" onClick="fnSltUsrSearch();">
          				<img src='<bean:message key="OIFM.docroot" />/images/btn_Search.gif' border="0" tabindex="21" alt="Search"></a>
          			<a href="#" onClick="fnClrSrh();">
	          			 <img src='<bean:message key="OIFM.docroot" />/images/btn_Clear.gif' border="0" tabindex="22" alt="Clear values"></a>		 
          			<a href="Javascript:window.close();">
          			 	<img src='<bean:message key="OIFM.docroot" />/images/btn_Cancel.gif' border="0" tabindex="23" alt="Cancel"/></a>
          			 </p>
	           </td>
           </tr>
       </table>
    <%boolean bIsMail =false; %>  
   <logic:present name="<%=com.oifm.useradmin.OISearchConstants.SEARCH%>" scope="request">
    	 <logic:equal name="SelectForm" property="hiddenAction" value="adduser" scope="request">
    	 		<table width="100%"  border="0" cellpadding="0" cellspacing="0" class="Boxoutline" align="center">		
						<tr align="center"><td class="sub_head" >
								<bean:write property="upCnt" name="SelectForm"/> <bean:message key="Users.add"/>
						</td></tr></table>
						<br>			 
        </logic:equal>	   
      
       <DIV align=left style='width: 900px; height: 200px; overflow: scroll;' > 
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="white">
        <tr>
          <td align="left" valign="top" class="Box">
            <table width="100%"  border="0" cellspacing="1" cellpadding="1" bgcolor="white">
              <tr> 
            	    <td class="table_head" width="5%"> 
                		  &nbsp;&nbsp;<html:checkbox property="userId" value="userId" onclick="fnEnableAllChk();"/>
	                </td>

	                <logic:iterate id="ColNames" name="colnames" scope="request" type="java.lang.String" >
 		               
		               
	                      	<td nowrap width="100%" class="table_head"> 
	                      	<script>document.write(fnTitle('<bean:write name="ColNames"/>'));</script> 
	        	            <a href="#"> 
									
							  		<logic:equal name="SelectForm" property="hidSortKey" value="<%=ColNames%>" scope="request">
							  	
								  		<logic:equal name="SelectForm" property="hidOrder" value="ASC">
									  		 <img src='<bean:message key="OIFM.docroot" />/images/DownArrow.gif' width="17" height="16" border="0" onClick="javascript:fnSortUsers('<bean:write name="ColNames"/>','DESC');">
									  	</logic:equal>
									  	<logic:equal name="SelectForm" property="hidOrder" value="DESC" scope="request" >
									  		 <img src='<bean:message key="OIFM.docroot" />/images/btn_uparrow.gif' width="17" height="16" border="0" onClick="javascript:fnSortUsers('<bean:write name="ColNames"/>','ASC');">
									  	</logic:equal>
									 </logic:equal>
									 
									 <logic:notEqual name="SelectForm" property="hidSortKey" value="<%=ColNames%>" scope="request">
									  		 <img src='<bean:message key="OIFM.docroot" />/images/btn_rightarrow.gif' width="17" height="16" border="0" onClick="javascript:fnSortUsers('<bean:write name="ColNames"/>','ASC');">
									 </logic:notEqual>
									  <%if(ColNames.equals("EMAILID")){
											bIsMail =true;
										}%>					 
								</a>  	
	                    	</td> 
 	                 </logic:iterate> 	
              
              </tr>
              <logic:iterate id="ColDesc" name="search" scope="request">
              <tr> 
                <bean:define id="ColDef" name="ColDesc" type="java.util.LinkedHashMap"/>
                <%

					String strKey = null;
					String strValue =null;
					
					java.util.Set sobj = ColDef.keySet();
    				java.util.Iterator it =   sobj.iterator();
					String strUserId = (String)ColDef.get("USERID");
					String strPaperId = (String)ColDef.get("PAPERID");
					String strEmail = (String)ColDef.get("EMAILID");
					

					%>
				  	<td nowrap class="body_detail_text" align="left">  
					  	<% if(strPaperId.trim().equals("E")){%>
						  	  <logic:equal name="SelectForm" property="hidPage" value="addemails" scope="request">
			  				  	    <html:checkbox property="userId" value="<%=strEmail%>" onclick="fnUsrIdChk();"/>
							  </logic:equal>
  							  <logic:notEqual name="SelectForm" property="hidPage" value="addemails" scope="request">					  	
			  				  	    <html:checkbox property="userId" value="<%=strUserId%>" onclick="fnUsrIdChk();"/>
			  				  </logic:notEqual>    
					  	 <%}else {%>
							  <logic:equal name="SelectForm" property="hidPage" value="addemails" scope="request">
			  				  	    <html:checkbox property="userId" value="<%=strEmail%>" onclick="fnUsrIdChk();"/>
							  </logic:equal>
  							  <logic:notEqual name="SelectForm" property="hidPage" value="addemails" scope="request">
									 <input type="checkbox" disabled=true/>							  
							  </logic:notEqual>		 
	  			 
						<%}%>
					  </td>	
					<%
    				while(it.hasNext()){
 						 strKey = (String) it.next();
						 strValue =(String) ColDef.get(strKey);
					%>
 	             		
						<%if(!strKey.equals("PAPERID")){ %>	
		  				  <td nowrap class="body_detail_text" align="left"> 
		  				    <%=strValue%>
							</td>		
						<% }}%>
     		  
                
              </tr>
              </logic:iterate>

            </table>
                 
          </td>
        </tr>
      </table>
     </div>       
      <table width="100%" height="35"  border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td align="left" colspan=2><p>&nbsp;</p>
		
          <a href="javascript:fnAdd()"><div id='TR'>
          		<img src='<bean:message key="OIFM.docroot" />/images/btn_AddUsers.gif' border="0" tabindex="24" alt="Add Users"/></div>
</a> 
          	</td>
          
          

<logic:present name="currentPage" scope="request">
	<td>
	<table  border="0" cellspacing="0" cellpadding="0" align="right" bgcolor="white"> 
		
		<td align="right">

		<table  border="0" cellspacing="0" cellpadding="0" bgcolor="white">
			<tr>
				<td align="right">
					<table  border="0" cellpadding="2" cellspacing="0" class="BodyText" align="right">
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
		 </tr>
    </table></td>	
    </tr>
 </table>
  </td>
  </tr>
  </table>
</logic:present>

<logic:notPresent name="<%=com.oifm.useradmin.OISearchConstants.SEARCH%>" scope="request">

	 <logic:equal name="SelectForm" property="hiddenAction" value="search" scope="request">
	 
		  <table width="100%"  align="center" border="0" cellspacing="0" cellpadding="0">
              <tr> 
              	 <logic:iterate id="ColNames" name="colnames" scope="request">
	                  	<td nowrap width="70%" class="Mainheader"> 	<script>document.write(fnTitle('<bean:write name="ColNames"/>'));</script> </td> 
	              </logic:iterate> 	
             </tr> 
             <tr>
  		 	 		 <table  align="center" border="0" cellspacing="0" cellpadding="0">
  		 	 		 	<tr><td >  </td> </tr>
  		 	 		 	<tr><td >  </td> </tr>
						<tr>
						<br><br>
						<td class="body_detail_text" align="center" nowrap> No Records Found </td> 
						</tr>
			 		</table>
			 </tr>
			</table>
	  </logic:equal >

</logic:notPresent>
	
</body>

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
	<html:hidden property="hidBoard"/>	
</html:form>
	
</html:html>

