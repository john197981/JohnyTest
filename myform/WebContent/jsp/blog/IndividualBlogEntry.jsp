<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ page language="java" import="com.oifm.blog.OIBlogConstants"%>

<bean:define id="EntryDetails" name="EntryDetails" type="com.oifm.blog.OIBAIndividualBlog" />

<bean:size id="NoOfComments" name="Comments" scope="request" />

<logic:present name="BlogDetail" property="blogConfig" >	            	
	<bean:define id="bconfig" name="BlogDetail" property="blogConfig"/>	
</logic:present>								
	
<logic:present name="BlogDetail" property="latestFeaturedPost" >
	<bean:define id="latestFeatPost" name="BlogDetail" property="latestFeaturedPost"/>
</logic:present>

<script language="javascript">
			var maxlimit = 350;
			function fn_textCounter(field, countfield) 
			{
				if (field.value.length > maxlimit) 
				{
					field.value = field.value.substring(0, maxlimit);
				}
				else
				{
					countfield.value = maxlimit - field.value.length;
				}
			}
			
			function submitComment()
			{
				if (document.OIFormIndividualBlogEntry.comment.value.length > 0){
					document.OIFormIndividualBlogEntry.hiddenAction.value = 'DO_SUBMIT_COMMENT';
					document.OIFormIndividualBlogEntry.submit();
				}
			}
	</script>
	
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
<table width="857" border="0" cellspacing="0" cellpadding="0">
  <tr>
     <td class="Orange_fade">Entry Page</td>
  </tr>
</table>
<table width="857" border="0" cellspacing="0" cellpadding="0">
      
	<logic:present name="error" scope="request" >
          <tr>
            <td width="100%" class="body_detail_text" colspan="3">
			<b><bean:message name="error" scope="request"/></b>
			</td>
          </tr>
	</logic:present>
      <tr>
        <td>
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
													<tr>
														<td>&nbsp;</td> 													
													</tr>
													<tr>
															<td style="word-wrap:break-word">
																<blockquote><p>
																<span class="BlogEntry_Title"><bean:write name="EntryDetails" property="title" /></span>
																 <span class="body_extract_text">
																 <br />by <bean:write name="EntryDetails" property="authorUserName" />
																 <br /><bean:write name="EntryDetails" property="date" />
																 <br /><logic:present name="EntryDetails" property="tagList">
																 			<bean:size  id="EntryDetailsTagCount" name="EntryDetails" property="tagList" />
																 			<logic:greaterThan name="EntryDetailsTagCount" value="0">
																 				Tags : <logic:iterate id="EntryDetailsTag" name="EntryDetails" property="tagList" type="java.lang.String"><bean:write name="EntryDetailsTag" /> </logic:iterate>
																 			</logic:greaterThan>
																 	   </logic:present>
																</p></blockquote>
															</td>
														</tr>
														<tr>
															<td>&nbsp;</td> 													
														</tr>
														<tr>
															<td class="body_extract_text" valign="top" style="word-wrap:break-word;width:575px">
																<blockquote>
																<logic:notEmpty name="EntryDetails" property="picFileName"><div style="text-align:center"><img src="<bean:message key="OIFM.contextroot" />/BlogHome.do?hiddenAction=READ_PICTURE&picFileName=<bean:write name="EntryDetails" property="picFileName" />"></div></logic:notEmpty>
																 <p style="text-align:justify"><bean:write name="EntryDetails" property="entryBody" filter="false" /></p> 
																 </blockquote>
															</td> 
														</tr>
														<logic:equal name="EntryDetails" property="commentAllowed" value="true">
														<tr>
															<td class="body_extract_text" ><hr /><blockquote>
																 <p><a id="comments" name="comments"><%=NoOfComments%> Comment<%if (NoOfComments.intValue() > 1) {%>s<%}%></a> | <a href="#addComment">Add your comments</a></p> 
																 </blockquote></td>
															<td>&nbsp;</td> 		
														</tr>
														</logic:equal>
														<logic:iterate id="Comment" name="Comments" type="com.oifm.blog.OIBAIndividualBlog">
														<tr>
															<td class="body_extract_text" valign="top">
																<p><bean:write name="Comment" property="commentUser" /> wrote on <bean:write name="Comment" property="commentDate" /></p>
																<blockquote>
																<bean:write name="Comment" property="comment" />
																</blockquote>
															<td>
														</tr>
														<tr><td>&nbsp;</td></tr>
														</logic:iterate>
														<logic:equal name="EntryDetails" property="commentAllowed" value="true">
															<tr>
																<td class="body_extract_text"><a id="addComment" name="addComment">Add a new comment</a><br /><hr /></td>
															</tr>
															<tr>
																<td class="body_extract_text">Your comment:</td>
															</tr>
															<tr>
																<td class="body_extract_text">
																	<html:form action="/IndividualBlogEntry.do">
																		<html:textarea name="OIFormIndividualBlogEntry" property="comment" cols="60" rows="5"  onkeydown="fn_textCounter(this.form.comment,this.form.numleft);" onkeyup="fn_textCounter(this.form.comment,this.form.numleft);" onmouseover="fn_textCounter(this.form.comment,this.form.numleft);" onmouseout="fn_textCounter(this.form.comment,this.form.numleft);"></html:textarea><br />
																		No. of characters remaining: 
																		<input name="numleft" class="text_box" type="text" size="10" size="5" maxlength="3" value="350" disabled ><br /><br />
																		<a href="#" onclick="submitComment()"><img src="<bean:message key="OIFM.docroot"/>/images/but_submit.gif" border="0" alt = "Submit"></a>
																		<html:hidden name="OIFormIndividualBlogEntry" property="hiddenAction"/>
																		<html:hidden name="OIFormIndividualBlogEntry" property="entryId"/>
																		<html:hidden name="OIFormIndividualBlogEntry" property="blogId"/>
																	</html:form></td>
															</tr>
														</logic:equal>
														
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
					<td width="20%"  valign="top" class="Blue">
						<div align="center">
							<logic:present name="<%=OIBlogConstants.SELECTED_BLOG_ID%>" scope="session">
								<jsp:include page="/jsp/blog/IndividualBlogMenu.jsp" flush="true" />
							</logic:present>
							<table width="80%" border="0" cellpadding="0" cellspacing="0">
								<tr><td>&nbsp;</td> 		
								<tr><td align="Left" ><span class="Poll_body">Excerpt</span><br></td></tr>
								
								<tr><td>&nbsp;</td></tr> 		
								<tr><td align="Left" style="word-wrap:break-word;width:150px;font-size:10px;font-weight:bold;color:#FFFFFF;line-height:15px;">
									<bean:write name="EntryDetails" property="entryExcerpt" /><br></td></tr>
							</table>
						</div>
					</td>
				 </tr>
			</table> 						
        </td>
      </tr>
</table>
<logic:equal name="EntryDetails" property="commentAllowed" value="true">
<script language="javascript">
		fn_textCounter(this.OIFormIndividualBlogEntry.comment,this.OIFormIndividualBlogEntry.numleft);
</script>
</logic:equal>


