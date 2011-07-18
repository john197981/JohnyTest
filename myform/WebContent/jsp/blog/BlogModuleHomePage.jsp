<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested"%>
<%@ page language="java" import="com.oifm.blog.OIBlogConstants"%>

<logic:present name="BlogDetail" property="blogConfig">
	<bean:define id="bconfig" name="BlogDetail" property="blogConfig" />
</logic:present>

<logic:present name="BlogDetail" property="latestFeaturedPost">
	<bean:define id="latestFeatPost" name="BlogDetail"
		property="latestFeaturedPost" />
</logic:present>

<body>
<table width="857" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td class="Orange_fade"></td>
	</tr>
</table>
<table width="857" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td valign="top" class="orange">
		<div align="justify" class="Highlight_body">
		<blockquote>
		<p>Welcome to the<b> Blog </b>Module. <br>
		<br>
		<logic:present name="bconfig">
			<bean:write name="bconfig" property="blogMessage" filter="false" />
		</logic:present></p>
		</blockquote>
		</div>
		</td>
	</tr>
	<tr>
		<td>
		<table width="857" border="0" valign="top" cellspacing="0"
			cellpadding="0" bgcolor="white">
			<tr valign="top">
				<td width="80%" bgcolor="white">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<logic:present name="latestFeatPost">
						<tr>
							<td align="center"><br>
							<table width="90%" border="0" cellspacing="0" cellpadding="0"
								bgcolor="#DFF2D2">
								<tr>
									<td width="60%">&nbsp;</td>
									<td width="30%" class="orange">
									<div align="justify" class="Highlight_body"><bean:write
										name="latestFeatPost" property="createdOn" filter="false"
										format="dd-MMM-yyyy" /></div>
									</td>
								</tr>
								<tr>
									<td colspan="2" width="90%" valign="top" class="heading_thread">
									<blockquote>
									<p><bean:write name="latestFeatPost" property="entryTitle"
										filter="false" /> <span class="smalltext"> <BR>
									<bean:write name="latestFeatPost" property="totalComments"
										filter="false" /> <logic:greaterThan name="latestFeatPost"
										property="totalComments" value="1">Comments</logic:greaterThan>
									<logic:lessThan name="latestFeatPost" property="totalComments"
										value="2">Comment</logic:lessThan> <br />
									<logic:present name="latestFeatPost" property="tagList">
										<bean:size id="latestFeatPostTagCount" name="latestFeatPost"
											property="tagList" />
										<logic:greaterThan name="latestFeatPostTagCount" value="0">
																 				Tags : <logic:iterate id="latestFeatPostTag"
												name="latestFeatPost" property="tagList"
												type="java.lang.String">
												<bean:write name="latestFeatPostTag" />
											</logic:iterate>
										</logic:greaterThan>
									</logic:present> </span></p>
									</blockquote>
									</td>
								</tr>
								<tr>
									<td colspan="2" width="90%" class="body_extract_text" valign="top">
										<blockquote>
											<logic:notEmpty name="latestFeatPost"
											property="picFileName">
	
											<img
												src="<bean:message key="OIFM.contextroot" />/BlogHome.do?hiddenAction=READ_PICTURE&picFileName=<bean:write name="latestFeatPost" property="picFileName" />"
												width="120" height="120"
												style="float:right; margin-left:15px; margin-bottom:15px;">
											<br>
	
											</logic:notEmpty>
										 <p style="text-align:justify">
										 <bean:write name="latestFeatPost" property="entryBody" filter="false" /> <a href="<bean:message key="OIFM.contextroot" />/IndividualBlogEntry.do?blogId=<bean:write name="latestFeatPost" property="blogId" />&entryId=<bean:write name="latestFeatPost" property="entryId" />">Read More</a>
										 </p> 
									   </blockquote>
									</td> 
								</tr>
								<tr>
									<td colspan="2">&nbsp;</td>
								</tr>
							</table>
							<br>
							</td>
						</tr>
					</logic:present>
					<tr>
						<td align="center">
						<table width="90%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="50%" valign="top">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td>
										<hr />
										</td>
									</tr>
									<tr>
										<td class="Sub_Head" align="right">Latest Posts</td>
									</tr>
									<logic:present name="BlogDetail" property="latestPosts">
										<logic:iterate id="latestPost" name="BlogDetail"
											property="latestPosts"
											type="com.oifm.blog.OIBABlogAdminCreateEntry"
											indexId="postIndex">
											<logic:notEqual name="postIndex" value="0">
												<tr>
													<td>
													<hr />
													</td>
												</tr>
											</logic:notEqual>
											<tr>
												<td>&nbsp;</td>
											</tr>
											<tr>
												<td align="left"  style="word-wrap:break-word;width:300px;line-height: 125%;font-weight: bold;color: #4A69B3;font-size: 12px;">
												&nbsp;&nbsp;<bean:write name="latestPost"
													property="entryTitle" filter="false" /> <span
													class="smalltext"> <BR />
												&nbsp;&nbsp;by <bean:write name="latestPost"
													property="createdBy" /> <BR>
												&nbsp;&nbsp;<bean:write name="latestPost"
													property="createdOn" filter="false" format="dd-MMM-yyyy" />
												<BR>
												&nbsp;&nbsp;<bean:write name="latestPost"
													property="totalComments" filter="false" /> <logic:greaterThan
													name="latestPost" property="totalComments" value="1">Comments</logic:greaterThan>
												<logic:lessThan name="latestPost" property="totalComments"
													value="2">Comment</logic:lessThan> <br />
												<logic:present name="latestPost" property="tagList">
													<bean:size id="latestPostTagCount" name="latestPost"
														property="tagList" />
													<logic:greaterThan name="latestPostTagCount" value="0">
																	 				&nbsp;&nbsp;Tags : <logic:iterate
															id="latestPostTag" name="latestPost" property="tagList"
															type="java.lang.String">
															<bean:write name="latestPostTag" />
														</logic:iterate>
													</logic:greaterThan>
												</logic:present> </span></td>
											</tr>
											<tr>
												<td>&nbsp;</td>
											</tr>
											<tr>
												<td align="left" style="word-wrap:break-word;width:300px;line-height: 125%">
													
													<FONT size=1><bean:write name="latestPost" property="entryBody" filter="false" /></FONT>
													<a href="<bean:message key="OIFM.contextroot" />/IndividualBlogEntry.do?blogId=<bean:write name="latestPost" property="blogId" />&entryId=<bean:write name="latestPost" property="entryId" />"
													class="Table_Sub_Topic">Read More</a>
													
												</td>
												
												
											</tr>
											<tr>
												<td>&nbsp;</td>
											</tr>

										</logic:iterate>
										<logic:empty name="BlogDetail" property="latestPosts">
											<tr>
												<td>&nbsp;</td>
											</tr>
											<tr align="center">
												<td class="heading_attributes">No Latest Posts</td>
											</tr>
										</logic:empty>
									</logic:present>
									<logic:notPresent name="BlogDetail" property="latestPosts">
										<tr>
											<td>&nbsp;</td>
										</tr>
										<tr align="center">
											<td class="heading_attributes">No Latest Posts</td>
										</tr>
									</logic:notPresent>
									<tr>
										<td>&nbsp;</td>
									</tr>
								</table>
								</td>
								<td width="1%">&nbsp;</td>
								<td width="49%" valign="top">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td>
										<hr />
										</td>
									</tr>
									<tr>
										<td class="Sub_Head" align="right">Previous Featured
										Posts</td>
									</tr>
									<logic:present name="BlogDetail"
										property="previousFeaturedPosts">
										<logic:iterate id="latestPost" name="BlogDetail"
											property="previousFeaturedPosts"
											type="com.oifm.blog.OIBABlogAdminCreateEntry"
											indexId="postIndex">
											<logic:notEqual name="postIndex" value="0">
												<tr>
													<td>
													<hr />
													</td>
												</tr>
											</logic:notEqual>
											<tr>
												<td>&nbsp;</td>
											</tr>
											<tr>
												<td align="left" style="word-wrap:break-word;width:300px;line-height: 125%;font-weight: bold;color: #4A69B3;font-size: 12px;">
												&nbsp;&nbsp;<bean:write name="latestPost" property="entryTitle"
													filter="false" /><span class="smalltext"> <BR />
												&nbsp;&nbsp;by <bean:write name="latestPost"
													property="createdBy" /> <BR>
												&nbsp;&nbsp;<bean:write name="latestPost"
													property="createdOn" filter="false" format="dd-MMM-yyyy" />
												<BR>
												&nbsp;&nbsp;<bean:write name="latestPost"
													property="totalComments" filter="false" />&nbsp; <logic:greaterThan
													name="latestPost" property="totalComments" value="1">Comments</logic:greaterThan>
												<logic:lessThan name="latestPost" property="totalComments"
													value="2">Comment</logic:lessThan> <br />
												<logic:present name="latestPost" property="tagList">
													<bean:size id="latestPostTagCount" name="latestPost"
														property="tagList" />
													<logic:greaterThan name="latestPostTagCount" value="0">
																	 				&nbsp;&nbsp;Tags : <logic:iterate
															id="latestPostTag" name="latestPost" property="tagList"
															type="java.lang.String">
															<bean:write name="latestPostTag" />
														</logic:iterate>
													</logic:greaterThan>
												</logic:present> </span></td>
											</tr>
											<tr>
												<td>&nbsp;</td>
											</tr>
											<tr>
												<td align="left" style="word-wrap:break-word;width:300px;line-height: 125%">
												<FONT size=1> <bean:write name="latestPost"
													property="entryBody" filter="false" /> </FONT> <a
													href="<bean:message key="OIFM.contextroot" />/IndividualBlogEntry.do?blogId=<bean:write name="latestPost" property="blogId" />&entryId=<bean:write name="latestPost" property="entryId" />"
													class="Table_Sub_Topic">Read More</a></td>
											</tr>
											<tr>
												<td>&nbsp;</td>
											</tr>
										</logic:iterate>
										<logic:empty name="BlogDetail"
											property="previousFeaturedPosts">
											<tr>
												<td>&nbsp;</td>
											</tr>
											<tr align="center">
												<td class="heading_attributes">No Previous Featured
												Posts</td>
											</tr>
										</logic:empty>
									</logic:present>
									<logic:notPresent name="BlogDetail"
										property="previousFeaturedPosts">
										<tr>
											<td>&nbsp;</td>
										</tr>
										<tr align="center">
											<td class="heading_attributes">No Previous Featured
											Posts</td>
										</tr>
									</logic:notPresent>
									<tr>
										<td>&nbsp;</td>
									</tr>
								</table>
								</td>
							</tr>
							<tr>
								<td colspan="3" width="90%">
								<hr />
								</td>
							</tr>
							<tr>
								<td colspan="3" width="90%">&nbsp;</td>
							</tr>
							<tr>
								<td colspan="3" width="90%" class="special_body_link"
									align="left"><FONT size="2">Tags</FONT></td>
							</tr>
							<tr>
								<td colspan="3" width="90%">&nbsp;</td>
							</tr>
							<tr>
								<td colspan="3" width="90%" class="Table_Sub_Topic"><FONT
									size="1"> <logic:present name="ALL_TAGS">
									<logic:iterate id="tags" name="ALL_TAGS"
										type="com.oifm.blog.OIBABlogTag">
															&nbsp;&nbsp;
															<logic:equal name="tags" property="hasPost" value="true">
											<a
												href="<bean:message key="OIFM.contextroot" />/BlogArchives.do?hiddenAction=ArchivesByTag&tagId=<bean:write name="tags" property="tagId" />&tagName=<bean:write name="tags" property="tagName" filter="false" />">
											<FONT size="2"><bean:write name="tags"
												property="tagName" filter="false" /></FONT></a>
										</logic:equal>
										<logic:notEqual name="tags" property="hasPost" value="true">
											<bean:write name="tags" property="tagName" filter="false" />
										</logic:notEqual>
									</logic:iterate>
								</logic:present> </FONT></td>
							</tr>
							<tr>
								<td colspan="3" width="90%">&nbsp;</td>
							</tr>
						</table>
						</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
					</tr>
				</table>
				</td>
				<td width="20%" valign="top" class="Blue">
				<div align="center">
				<table width="80%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td>&nbsp;</td>
					<tr>
						<td align="Left"><br>
						<span class="yellow_text"><FONT size="2"><I>Blogs</I></FONT></span><br>
						</td>
					</tr>
					<logic:present name="BlogDetail" property="blogs">
						<logic:iterate id="blogs" name="BlogDetail" property="blogs"
							type="com.oifm.blog.OIBABlog">
							<tr>
								<td align="Left"><span class="Poll_body">
								<li><a
									href='<bean:message key="OIFM.contextroot" />/IndividualBlogHome.do?blogId=<bean:write name="blogs" property="blogId" />'
									class="Poll_body" style="text-decoration:none"> <bean:write
									name="blogs" property="title" filter="false" /></a>
								</span></td>
							</tr>
						</logic:iterate>
					</logic:present>
					<tr>
						<td>&nbsp;</td>
					<tr>
						<td align="Left"><br>
						<br>
						<span class="yellow_text"><FONT size="2"><I>Recent
						Entries</I></FONT></span><br>
						</td>
					</tr>
					<logic:present name="BlogDetail" property="latestPosts">
						<logic:iterate id="latestPost" name="BlogDetail"
							property="latestPosts"
							type="com.oifm.blog.OIBABlogAdminCreateEntry">
							<tr>
								<td align="Left"><span class="Poll_body">
								<li><a
									href="<bean:message key="OIFM.contextroot" />/IndividualBlogEntry.do?blogId=<bean:write name="latestPost" property="blogId" />&entryId=<bean:write name="latestPost" property="entryId" />"
									class="Poll_body" style="text-decoration:none"> <bean:write
									name="latestPost" property="entryTitle" filter="false" /></a>
								</span></td>
							</tr>
						</logic:iterate>
					</logic:present>
					<tr>
						<td>&nbsp;</td>
				</table>
				</div>
				</td>
			</tr>
			<tr>
				<td width="80%" bgcolor="white">&nbsp;</td>
				<td width="20%" class="Blue">&nbsp;</td>
			</tr>
			<tr>
				<td width="80%" bgcolor="white">&nbsp;</td>
				<td width="20%" class="Blue">&nbsp;</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</body>
</HTML>

