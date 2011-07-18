<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ page language="java" import="com.oifm.blog.OIBlogConstants"%>


 <head>
	 <style type="text/css">
			<!--
			@import url("iofs.css");
			.style2 
			{
				color: #FFFFFF;
				font-weight: bold;
			}
			-->
		</style>
		<script language="javascript" >	
			
			function getArchivesByCategory(hidAction,categoryId)
			{	
				var frm = document.BlogArchivesForm;
				frm.hiddenAction.value = hidAction;	
				//alert("categoryId : "+categoryId);
				frm.categoryId.value = categoryId;
				frm.submit();
			}
			
			function getArchivesByMonth(hidAction,month)
			{	
				var frm = document.BlogArchivesForm;
				frm.hiddenAction.value = hidAction;	
				//alert("month : "+month);
				frm.month.value = month;
				frm.submit();
			}
			
			function getArchivesByAuthor(hidAction,authorId)
			{	
				var frm = document.BlogArchivesForm;
				frm.hiddenAction.value = hidAction;	
				//alert("authorId : "+authorId);
				frm.authorId.value = authorId;
				frm.submit();
			}
		</script>
		<SCRIPT LANGUAGE="JavaScript" src='<bean:message key="OIFM.docroot" />/js/Common.js' ></SCRIPT>
 </head>
<body>  
<table width="857" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td valign="top" class="orange"><div align="justify" class="Highlight_body"> 
          <blockquote>
            <p><br><b> Archives</b><br><br>
            Blog Archives by Category,Authors and Date
            </p> 
            </blockquote>
        </div></td>
        </tr>

      <tr>
        <td>
        <html:form action="/BlogArchives.do" method="post">
<html:hidden property="hiddenAction" />
<html:hidden property="categoryId" />
<html:hidden property="month" />
<html:hidden property="authorId" />

        	<table width="857" border="0" valign="top" cellspacing="0" cellpadding="0" bgcolor="white">
				<tr valign="top">
					<td width="80%" bgcolor="white" >
						<table width="100%" border="0" cellspacing="0" cellpadding="0">									
							<tr>
								<td align="center">
									<table width="90%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td valign="top">
												<table width="100%" border="0" cellspacing="0" cellpadding="0">
													<tr>
														<td>&nbsp;</td> 													
													</tr>												
													
													<logic:present name="BlogDetail" property="blogs" >
														<logic:iterate id="latestPost" name="BlogDetail" property="blogs" type="com.oifm.blog.OIBABlogAdminCreateEntry">
															<tr>
																<td >
																	<blockquote><p>
																	<span class="Sub_Head"> 
																	 <bean:write name="latestPost" property="entryTitle" filter="false"/></span>
															 		<span class="smalltext"> 
																	 <BR><bean:write name="latestPost" property="createdOn" filter="false"/>
																	 <BR><bean:write name="latestPost" property="totalComments" filter="false"/>&nbsp;Comments</span>
																	</p></blockquote>
																</td> 													
															</tr>
															<tr>
																<td >
																	<span class="body_extract_text">
																	<blockquote><p>
																		<bean:write name="latestPost" property="entryBody" filter="false"/><a href="#">Read More</a>
																	</p></blockquote>
																	</span>
																</td>
															</tr>
															<tr>
																<td>&nbsp;</td> 													
															</tr>
														</logic:iterate>
														<logic:empty name="BlogDetail" property="blogs" >
															<tr>
																<td align="center" class="Sub_Head">&nbsp; No Entries matching this criteria</td> 													
															</tr>
														</logic:empty>
													</logic:present>
													<logic:notPresent name="BlogDetail" property="blogs" >
															<tr>
																<td class="Sub_Head">&nbsp;&nbsp; No Entries matching this criteria</td> 													
															</tr>
													</logic:notPresent>
													<tr>
														<td>&nbsp;</td> 													
													</tr>	
												</table>
											</td>											
																						
										</tr>									
									</table>
								</td>
							</tr>
							<tr>
								<td>&nbsp;</td> 													
							</tr>			
						</table>
					</td>
					<td width="20%"  valign="top" class="Blue">
						<div align="center"> 
							<table width="80%" border="0" cellpadding="0" cellspacing="0">
								<tr><td>&nbsp;</td> 		
								<tr><td align="Left" ><span class="Poll_body">Archives</span><br></td></tr>
								
								<tr><td>&nbsp;</td></tr> 		
								<tr><td align="Left" ><span class="Poll_body">&nbsp;&nbsp;&nbsp;&nbsp;By Category</span><br></td></tr>
								<logic:present name="BlogDetail" property="categories" >
									<logic:iterate id="objBlog" name="BlogDetail" property="categories" indexId="ifdx" type="com.oifm.blog.OIBABlogAdminCategory">
										<tr>
											<td align="Left"><span class="Poll_body">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<a href="#" onclick="getArchivesByCategory('<%=OIBlogConstants.ACTION_ARCHIVES_CATEGORY%>','<bean:write name="objBlog" property="categoryId" />')" class="Poll_body">
												<bean:write name="objBlog" property="category" /></a></span>
											</td>
										</tr>
									</logic:iterate>
								</logic:present>							
								<tr><td>&nbsp;</td></tr> 	
								<tr><td align="Left" ><span class="Poll_body">&nbsp;&nbsp;&nbsp;&nbsp;By Date</span><br></td></tr>
								<logic:present name="BlogDetail" property="months" >
									<logic:iterate id="months" name="BlogDetail" property="months" indexId="ifdx" type="java.lang.String">
										<tr>
											<td align="Left"><span class="Poll_body">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<a href="#" onclick="getArchivesByMonth('<%=OIBlogConstants.ACTION_ARCHIVES_MONTH%>','<bean:write name="months" />')" class="Poll_body">
													<bean:write name="months" />
												</a></span>
											</td>
										</tr>
									</logic:iterate>
								</logic:present>								
								<tr><td>&nbsp;</td></tr> 	
								<tr><td align="Left" ><span class="Poll_body">&nbsp;&nbsp;&nbsp;&nbsp;By Author</span><br></td></tr>
								<logic:present name="BlogDetail" property="authors" >
									<logic:iterate id="objBlog" name="BlogDetail" property="authors" indexId="ifdx" type="com.oifm.blog.OIBABlogAuthor">
										<tr>
											<td nowrap align="Left"><span class="Poll_body">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<a href="#" onclick="getArchivesByAuthor('<%=OIBlogConstants.ACTION_ARCHIVES_AUTHOR%>','<bean:write name="objBlog" property="userId" />')" class="Poll_body">			
													<bean:write name="objBlog" property="nickName" />
												</a></span>
											</td>
										</tr>
									</logic:iterate>
								</logic:present>													
								<tr><td>&nbsp;</td> 		
							</table>
						</div>
					</td>
				 </tr>
			</table> 
			</html:form>						
        </td>
      </tr>
</table>  

</body>
</HTML>

