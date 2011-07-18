<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ page language="java" import="com.oifm.blog.OIBlogConstants"%>
<%@ page language="java" import="com.oifm.utility.OIUtilities"%>


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
			
			function getArchivesByCategory(hidAction,categoryId,categoryName)
			{	
				var frm = document.BlogArchivesForm;
				frm.hiddenAction.value = hidAction;	
				//alert("categoryId : "+categoryId);
				frm.categoryId.value = categoryId;
				frm.categoryName.value = categoryName;
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
			
			function getArchivesByAuthor(hidAction,authorId,authorName)
			{	
				var frm = document.BlogArchivesForm;
				frm.hiddenAction.value = hidAction;	
				//alert("authorId : "+authorId);
				frm.authorId.value = authorId;
				frm.authorName.value = authorName;
				frm.submit();
			}
			
			function fnPagination(pageNum)
			{
			     document.BlogArchivesForm.pageNo.value = pageNum;
				 document.BlogArchivesForm.hiddenAction.value="pagination";
				 document.BlogArchivesForm.submit();
			}
		</script>
		<SCRIPT LANGUAGE="JavaScript" src='<bean:message key="OIFM.docroot" />/js/Common.js' ></SCRIPT>
 </head>
<body>  
<table width="857" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td class="Orange_fade">Archives</td>
		</tr>
      <tr>
        <td valign="top" class="orange"><div align="justify" class="Highlight_body"> 
          <blockquote>
           <br><p>
            Blog Archives can be viewed by Category, Authors and Entry Dates
            </p>
            <logic:present name="TYPE" scope="request">
            	Viewing archive by <bean:write name="TYPE" scope="request" />
            	<logic:present name="TYPE_VALUE" scope="request">
            		: <bean:write name="TYPE_VALUE" scope="request"/>
            	</logic:present>
            </logic:present>
            </blockquote>
        </div></td>
        </tr>

      <tr>
        <td>
        <html:form action="/BlogArchives.do" method="post">
<html:hidden property="hiddenAction" />
<html:hidden property="categoryId" />
<html:hidden property="categoryName" />
<html:hidden property="month" />
<html:hidden property="authorId" />
<html:hidden property="authorName" />

        	<table width="857" border="0" valign="top" cellspacing="0" cellpadding="0" bgcolor="white">
				<tr valign="top">
					<td width="78%" bgcolor="white" >
						<table width="100%" border="0" cellspacing="0" cellpadding="0">									
							<tr>
								<td align="center">
									<table width="90%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td valign="top">
												<table width="100%" border="0" cellspacing="0" cellpadding="0">
													<tr><td>&nbsp;</td></tr>
													<tr><td>&nbsp;</td></tr>													
													<logic:present name="BlogDetail" property="blogs" >
														<logic:iterate id="latestPost" name="BlogDetail" property="blogs" type="com.oifm.blog.OIBABlogAdminCreateEntry">
															<logic:notPresent name="<%=OIBlogConstants.SELECTED_BLOG_ID%>" scope="session">	
																<bean:define id="currentBlogId" name="latestPost" property="blogId"/>
																<logic:present name="previousBlogId" >
																	<logic:notEqual name="previousBlogId" value='<%=currentBlogId.toString()%>'>
																		<tr><td>&nbsp;</td></tr>															
																		<tr>
																			<td align="Left">
																				<span class="Sub_Head"> 
																				&nbsp;&nbsp;Blog Name : <bean:write name="latestPost" property="blogTitle" filter="false"/></span>
																				<br>
																			<hr>
																			<br>
																			</td>
																		</tr>
																		<bean:define id="previousBlogId" name="latestPost" property="blogId"/>	
																	</logic:notEqual>	
																</logic:present>															
																<logic:notPresent name="previousBlogId" >
																	<bean:define id="previousBlogId" name="latestPost" property="blogId"/>
																	<tr><td>&nbsp;</td></tr>										
																	<tr>
																		<td align="Left">
																			<span class="Sub_Head"> 
																			&nbsp;&nbsp;Blog Name : <bean:write name="latestPost" property="blogTitle" filter="false"/>																			
																			</span>
																			<br>
																			<hr>
																			<br>
																		</td>
																	</tr>	
																</logic:notPresent>
															</logic:notPresent>											
															<tr>
																<td align="Left" style="word-wrap:break-word">
																	<span class="BlogEntry_Title">
																	 &nbsp;&nbsp;<bean:write name="latestPost" property="entryTitle" filter="false"/></span>
															 		<span class="smalltext">
																	<BR/>
																	&nbsp;&nbsp;by <bean:write name="latestPost" property="createdBy" />
																	 <BR>
																	 &nbsp;&nbsp;<bean:write name="latestPost" property="createdOn" filter="false" format="dd-MMM-yyyy"/>
																	 <BR>
																	 &nbsp;&nbsp;<bean:write name="latestPost" property="totalComments" filter="false"/>&nbsp;Comments
																	 <br /><logic:present name="latestPost" property="tagList">
																 			<bean:size  id="latestPostTagCount" name="latestPost" property="tagList" />
																 			<logic:greaterThan name="latestPostTagCount" value="0">
																 				&nbsp;&nbsp;Tags : <logic:iterate id="latestPostTag" name="latestPost" property="tagList" type="java.lang.String"><bean:write name="latestPostTag" /> </logic:iterate>
																 			</logic:greaterThan>
																 	   </logic:present></span>
																</td> 													
															</tr>
															<tr>
																<td style="word-wrap:break-word;width:500px;line-height: 20px;">
																	
																		<FONT size=1>
																		<bean:write name="latestPost" property="entryBody" filter="false"/>
																		</FONT>
																																		
																	<span class="body_detail_text"> 
																		<a href="<bean:message key="OIFM.contextroot" />/IndividualBlogEntry.do?blogId=<bean:write name="latestPost" property="blogId" />&entryId=<bean:write name="latestPost" property="entryId" />"class="Table_Sub_Topic">Read More</a>
																	</span>
																
															</tr>
															<tr><td>&nbsp;</td></tr>
															<tr><td>&nbsp;</td></tr>
														</logic:iterate>
														<logic:empty name="BlogDetail" property="blogs" >
															<tr><td>&nbsp;</td></tr>
															<tr>
																<td align="center" class="Sub_Head">&nbsp; No entry is present</td> 													
															</tr>
															<tr><td>&nbsp;</td></tr>
														</logic:empty>
													</logic:present>
													<logic:notPresent name="BlogDetail" property="blogs" >
														<tr><td>&nbsp;</td></tr>
														<tr>
															<td align="center" class="Sub_Head">&nbsp; No entry is present</td> 													
														</tr>
														<tr><td>&nbsp;</td></tr>
													</logic:notPresent>
													<tr><td>&nbsp;</td></tr>	
												</table>
											</td>											
																						
										</tr>									
									</table>
								</td>
							</tr>							
							<tr><td>&nbsp;</td></tr>
							<tr><td>&nbsp;</td></tr>
							<tr>
								<td>
									
								</td> 													
							</tr>				
						</table>
					</td>
					<td width="25%"  valign="top" class="Blue">
						<div align="center"> 
							<logic:present name="<%=OIBlogConstants.SELECTED_BLOG_ID%>" scope="session">
								<jsp:include page="/jsp/blog/IndividualBlogMenu.jsp" flush="true" />
							</logic:present>
							<table width="85%" border="0" cellpadding="0" cellspacing="0">
								<tr><td colspan="2" class="Poll_body">&nbsp;</td></tr>
								
									<tr class="Text">
										<td width="10%" class="Poll_body">											
										</td>
										<td  align="left" width="90%" class="yellow_text">
											<FONT size="2">Archives</FONT>
										</td>
									</tr>
									<tr><td colspan="2" class="Poll_body">&nbsp;</td></tr>
									<tr class="Text">
										<td width="20%" class="Poll_body">											
										</td>
										<td  align="left" width="80%" class="yellow_text">
											<I>By Category</I>
										</td>
									</tr>									
									<logic:present name="BlogDetail" property="categories" >
										<logic:iterate id="objBlog" name="BlogDetail" property="categories" indexId="ifdx" type="com.oifm.blog.OIBABlogAdminCategory">
											<tr>
												<td width="20%" class="Poll_body">											
												</td>
												</td>
												<td  align="left" width="80%" class="Poll_body"><li>
												<a href="#" onclick="getArchivesByCategory('<%=OIBlogConstants.ACTION_ARCHIVES_CATEGORY%>','<bean:write name="objBlog" property="categoryId" />','<%= OIUtilities.addSingleQuoteJS(objBlog.getCategory()) %>')" class="Poll_body">
													<bean:write name="objBlog" property="category" /></a>
												</td>
											</tr>
										</logic:iterate>
									</logic:present>
									<tr><td colspan="2" class="Poll_body">&nbsp;</td></tr>
									<tr class="Text">
										<td width="20%" class="Poll_body">											
										</td>
										<td  align="left" width="80%" class="yellow_text">
											<I>By Date</I>
										</td>
									</tr>									
									<logic:present name="BlogDetail" property="months" >
										<logic:iterate id="months" name="BlogDetail" property="months" indexId="ifdx" type="java.lang.String">
											<tr>
												<td width="20%" class="Poll_body">											
												</td>
												</td>
												<td  align="left" width="80%" class="Poll_body"><li>
													<a href="#" onclick="getArchivesByMonth('<%=OIBlogConstants.ACTION_ARCHIVES_MONTH%>','<bean:write name="months" />')" class="Poll_body">
														<bean:write name="months" />
													</a>
												</td>
											</tr>
										</logic:iterate>
									</logic:present>
									<logic:notPresent name="BlogDetail" property="months" >												
										<tr class="Text">
											<td width="20%" class="Poll_body">											
											</td>
											<td  align="left" width="80%" class="Poll_body">
												No Entries
											</td>
										</tr>	
									</logic:notPresent>
									<tr><td colspan="2" class="Poll_body">&nbsp;</td></tr>
									<tr class="Text">
										<td width="20%" class="Poll_body">											
										</td>
										<td  align="left" width="80%" class="yellow_text">
											<I>By Author</I>
										</td>
									</tr>									
									<logic:present name="BlogDetail" property="authors" >
										<logic:iterate id="objBlogAuthor" name="BlogDetail" property="authors" type="com.oifm.blog.OIBABlogAuthor">
											<tr>											
												<td colspan="2" align="Left" width="90%">
													<span class="Poll_body">
													<li><a href="#" onclick="getArchivesByAuthor('<%=OIBlogConstants.ACTION_ARCHIVES_AUTHOR%>','<bean:write name="objBlogAuthor" property="userId" />','<%= OIUtilities.addSingleQuoteJS(objBlogAuthor.getNickName()) %>')" class="Poll_body"  style="WORD-BREAK:BREAK-ALL;">			
														<bean:write name="objBlogAuthor" property="nickName" />
													</a></span>
												</td>
											</tr>
										</logic:iterate>
									</logic:present>
									<logic:notPresent name="BlogDetail" property="authors" >
										<tr class="Text">
											<td width="20%" class="Poll_body">											
											</td>
											<td  align="left" width="80%" class="Poll_body">
												No Authors
											</td>
										</tr>																	
									</logic:notPresent>	
									<tr class="Text">
										<td width="20%" class="Poll_body">											
										</td>
										<td  align="left" width="80%" class="Poll_body">
											
										</td>
									</tr>
																
							</table>
						</div>
					</td>
				 </tr>
				 <tr>
				 	<td width="80%" bgcolor="white" align="right">
				 		<logic:present name="BlogDetail" property="blogs" >
							<logic:notEmpty name="BlogDetail" property="blogs" >										
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
									<td>&nbsp;</td> 
									<td>&nbsp;</td> 		
									</tr>
								</table>		
								<input type="hidden" name="pageNo">	
							</logic:present>								
						
					</logic:notEmpty>
					</logic:present>
				 	</td>
				 	<td width="20%" class="Blue">&nbsp;</td>
				 </tr>
				 <tr><td width="80%" bgcolor="white" >&nbsp;</td><td width="20%" class="Blue">&nbsp;</td></tr>
				 <tr><td width="80%" bgcolor="white" >&nbsp;</td><td width="20%" class="Blue">&nbsp;</td></tr>
			</table> 		
			</html:form>						
        </td>
      </tr>
</table>
</body>
</HTML>

