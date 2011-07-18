<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ page language="java" import="com.oifm.blog.OIBlogConstants"%>
<%@ page language="java" import="com.oifm.utility.OIUtilities"%>

<logic:present name="BlogDetail" property="blogConfig" >	            	
	<bean:define id="bconfig" name="BlogDetail" property="blogConfig"/>	
</logic:present>								
	
<logic:present name="BlogDetail" property="latestFeaturedPost" >
	<bean:define id="latestFeatPost" name="BlogDetail" property="latestFeaturedPost"/>
</logic:present>

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
		</script>
 
<table width="857" border="0" cellspacing="0" cellpadding="0">
  <tr>
     <td class="Orange_fade"></td>
  </tr>
  <tr>
     <td class="Orange"><div align="justify" class="Highlight_body">  <blockquote>
            <p><b> Welcome to <bean:write name="SelectedBlogTitle" /></b>     
            </p> 
            </blockquote> </div></td>
  </tr>
</table>
<table width="857" border="0" cellspacing="0" cellpadding="0">
       <tr>
        <td>
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
													<tr>
														<td colspan="2">&nbsp;</td> 													
													</tr>	
													<tr>
														<td colspan="2">&nbsp;</td> 													
													</tr>
													<logic:iterate id="blogEntry" name="BlogEntries" type="com.oifm.blog.OIBAIndividualBlog">
														<tr>
															<td colspan="2">
																&nbsp;
															</td>
														</tr>
														<tr>
															<td colspan="2">
																<span class="BlogEntry_Title">&nbsp;&nbsp;<bean:write name="blogEntry" property="title" /></span>
																<span class="smalltext"><br />&nbsp;&nbsp;by <bean:write name="blogEntry" property="authorUserName" /></span>
																<span class="smalltext"><br />&nbsp;&nbsp;<bean:write name="blogEntry" property="date" /></span>
																<span class="smalltext">
																<br /><logic:present name="blogEntry" property="tagList">
																 			<bean:size  id="blogEntryTagCount" name="blogEntry" property="tagList" />
																 			<logic:greaterThan name="blogEntryTagCount" value="0">
																 				&nbsp;&nbsp;Tags : <logic:iterate id="blogEntryTag" name="blogEntry" property="tagList" type="java.lang.String"><bean:write name="blogEntryTag" /> </logic:iterate>
																 			</logic:greaterThan>
																 	   </logic:present>
																</span>
															</td>
														</tr>
														<tr>
															<td>&nbsp;</td> 													
														</tr>
														<tr>
															<td align="left">
																 <span class="body_detail_text">
																 	<font size=1>
																 		<bean:write name="blogEntry" property="entryExcerpt" filter="false" />
																 	</font>
																 	<a href="<bean:message key="OIFM.contextroot" />/IndividualBlogEntry.do?blogId=<bean:write name="blogEntry" property="blogId" />&entryId=<bean:write name="blogEntry" property="entryId" />" class="Table_Sub_Topic">Read More</a>
																 </span> 
															</td>
															<td valign="top"><p><logic:notEmpty name="blogEntry" property="picFileName"><img src="<bean:message key="OIFM.contextroot" />/BlogHome.do?hiddenAction=READ_PICTURE&picFileName=<bean:write name="blogEntry" property="picFileName" />" width="120" height="120"></logic:notEmpty><logic:empty name="blogEntry" property="picFileName">&nbsp;</logic:empty></p></td> 
														</tr>
														<logic:equal name="blogEntry" property="commentAllowed" value="true">
														<tr>
															<td class="body_extract_text" >
																 <a href="<bean:message key="OIFM.contextroot" />/IndividualBlogEntry.do?blogId=<bean:write name="blogEntry" property="blogId" />&entryId=<bean:write name="blogEntry" property="entryId" />#comments"><bean:write name="blogEntry" property="commentCount" /> Comment<logic:greaterThan name="blogEntry" property="commentCount" value="1">s</logic:greaterThan></a> | <a href="<bean:message key="OIFM.contextroot" />/IndividualBlogEntry.do?blogId=<bean:write name="blogEntry" property="blogId" />&entryId=<bean:write name="blogEntry" property="entryId" />#addComment">Add your comment</a>
															</td>
															<td>&nbsp;</td> 		
														</tr>
														</logic:equal>
														<tr>
															<td colspan="2">
																&nbsp;
															</td>
														</tr>
														
														<tr>
															<td colspan="2">
																<hr />
															</td>
														</tr>
														
													</logic:iterate>
													<tr>
														<td colspan="2">&nbsp;</td> 													
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
					<td width="22%"  valign="top" class="Blue">
						<div align="center">
							<html:form action="/BlogArchives.do" method="post">
							<html:hidden property="hiddenAction" />
							<html:hidden property="categoryId" />
							<html:hidden property="categoryName" />
							<html:hidden property="month" />
							<html:hidden property="authorId" />
							<html:hidden property="authorName" />
							<table width="80%" border="0" cellpadding="0" cellspacing="0">
								<tr><td colspan="2"><jsp:include page="/jsp/blog/IndividualBlogMenu.jsp" flush="true" /></td> </tr>
								<tr><td>&nbsp;</td> </tr> 
								<tr colspan="2"><td>&nbsp;</td>
								<tr colspan="2"><td align="Left" ><span class="Yellow_text"><FONT size="2"><I>&nbsp;&nbsp;&nbsp;&nbsp;Archives</I></FONT></span><br><br></td></tr>
								<tr><td align="Left" ><span class="Poll_body">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<I>By Category</I></span><br></td></tr>
								<logic:present name="BlogDetail" property="categories" >
									<logic:iterate id="objBlog" name="BlogDetail" property="categories" indexId="ifdx" type="com.oifm.blog.OIBABlogAdminCategory">
										<tr>
											<td align="Left"><span class="Poll_body">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<a href="#" onclick="getArchivesByCategory('<%=OIBlogConstants.ACTION_ARCHIVES_CATEGORY%>','<bean:write name="objBlog" property="categoryId" />','<%= OIUtilities.addSingleQuoteJS(objBlog.getCategory()) %>')" class="Poll_body">
												<bean:write name="objBlog" property="category" /></a></span>
											</td>
										</tr>
									</logic:iterate>
								</logic:present>							
								<tr><td>&nbsp;</td></tr> 	
								<tr><td align="Left" ><span class="Poll_body">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<I>By Date</I></span><br></td></tr>
								<logic:present name="BlogDetail" property="months" >
									<logic:iterate id="months" name="BlogDetail" property="months" indexId="ifdx" type="java.lang.String">
										<tr>
											<td align="Left"><span class="Poll_body">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<a href="#" onclick="getArchivesByMonth('<%=OIBlogConstants.ACTION_ARCHIVES_MONTH%>','<bean:write name="months" />')" class="Poll_body">
													<bean:write name="months" />
												</a></span>
											</td>
										</tr>
									</logic:iterate>
								</logic:present>								
								<tr><td>&nbsp;</td></tr> 	
								<!--
								<tr><td align="Left" ><span class="Poll_body">&nbsp;&nbsp;&nbsp;&nbsp;By Author</span><br></td></tr>
								<logic:present name="BlogDetail" property="authors" >
									<logic:iterate id="objBlog" name="BlogDetail" property="authors" indexId="ifdx" type="com.oifm.blog.OIBABlogAuthor">
										<tr>
											<td nowrap align="Left"><span class="Poll_body">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<a href="#" onclick="getArchivesByAuthor('<%=OIBlogConstants.ACTION_ARCHIVES_AUTHOR%>','<bean:write name="objBlog" property="userId" />','<%= OIUtilities.addSingleQuoteJS(objBlog.getNickName()) %>')" class="Poll_body">			
													<bean:write name="objBlog" property="nickName" />
												</a></span>
											</td>
										</tr>
									</logic:iterate>
								</logic:present>													
								<tr><td>&nbsp;</td> -->		
							</table>
							</html:form>
						</div>
					</td>
				 </tr>
			</table> 						
        </td>
      </tr>
</table>  


