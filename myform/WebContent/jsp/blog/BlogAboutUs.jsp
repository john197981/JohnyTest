<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%> 
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page language="java" import="com.oifm.utility.OIUtilities"%>

<%@ page language="java" import="com.oifm.blog.OIBlogConstants"%>

<script language="javascript" >
var strDocRoot = '<bean:message key="OIFM.contextroot"/>';
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
<SCRIPT LANGUAGE="JavaScript" src='<bean:message key="OIFM.docroot" />/js/Common.js' ></SCRIPT>

<table width="857" border="0" cellspacing="0" cellpadding="0">
<tr>
		<td class="TableHead">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
			<td class="Box">
				 <html:form action="/BlogArchives.do" method="post">
					<html:hidden property="hiddenAction" />
					<html:hidden property="authorId" />
				<table width="857" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td class="Orange_fade">List Of Bloggers</td>
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
					<td width="100%" bgcolor="white" >
						<table width="100%" border="0" cellspacing="0" cellpadding="0">									
							<tr>
								<td align="center">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td valign="top" align="center">
												<table width="90%" border="0" cellpadding="0" cellspacing="0" bgcolor="white">
													<tr>
														<td>&nbsp;</td> 													
													</tr>	
													<tr>
														<td>&nbsp;</td> 													
													</tr>
													<logic:iterate id="objBlog" name="ALL_BLOGS_AUTHORS" indexId="ifdx" type="com.oifm.blog.OIBABlogAuthor">
													<tr>
														<td class="body_extract_text bold" style="font-size:12px;">
														<a id="<bean:write name="objBlog" property="name" />" name="<bean:write name="objBlog" property="name" />"> </a>
														<a href="#" onclick="getArchivesByAuthor('<%=OIBlogConstants.ACTION_ARCHIVES_AUTHOR%>','<bean:write name="objBlog" property="userId" />','<%= OIUtilities.addSingleQuoteJS(objBlog.getNickName()) %>')" >
															<bean:write name="objBlog" property="name" /></a>
															<br /> <br />
															<span class="body_extract_text"><bean:write name="objBlog" property="emailId" /></span>
															<br /><br />
														</td>
													</tr>
													<!--
													<tr>
														<td class="body_extract_text">
															<logic:notEmpty name="objBlog" property="authorImageFileName"><img src="<bean:message key="OIFM.contextroot" />/IndividualBlogAboutUs.do?hiddenAction=READ_PICTURE&authorImageFileName=<bean:write name="objBlog" property="authorImageFileName" />" style="float:left; margin-right:15px; margin-bottom:15px;"></logic:notEmpty>
															<bean:write name="objBlog" property="authorDescription" filter="false" /></td>
													</tr>-->
													<tr>
														<td><br /><hr /></td>
													</tr>
													</logic:iterate>
													<tr>
														<td colspan="2">&nbsp;</td> 													
													</tr>	
												</table>
											</td>
											<td width="20%"  valign="top" class="Blue">
												<div align="center"> 
													<table width="80%" border="0" cellpadding="0" cellspacing="0">
														<tr><td>&nbsp;</td> 			
														<tr><td align="Left" ><span class="yellow_text" style="font-size:12px;">The Bloggers</span><br></td></tr>
														<tr><td>&nbsp;</td></tr>
														<logic:iterate id="objBlog" name="ALL_BLOGS_AUTHORS" indexId="ifdx" type="com.oifm.blog.OIBABlogAuthor">
														<tr>
															<td align="Left">
																<span class="Poll_body"><a href="#<bean:write name="objBlog" property="name" />" class="Poll_body" style="text-decoration:none"><bean:write name="objBlog" property="name" /></a></span>
															</td>
														</tr>
														</logic:iterate>
													</table>
												</div>
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
			       </tr>
			</table> 						
        </td>
      </tr>
</table>
	</html:form>
