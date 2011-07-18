<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ page language="java" import="com.oifm.blog.OIBlogConstants"%>

<logic:present name="BlogDetail" property="blogConfig" >	            	
	<bean:define id="bconfig" name="BlogDetail" property="blogConfig"/>	
</logic:present>								
	
<logic:present name="BlogDetail" property="latestFeaturedPost" >
	<bean:define id="latestFeatPost" name="BlogDetail" property="latestFeaturedPost"/>
</logic:present>

<table width="857" border="0" cellspacing="0" cellpadding="0">
    <!--  <tr>
        <td valign="top" class="orange"><div align="justify" class="Highlight_body"> 
          <blockquote>
            <p><br> Welcome to the<b> Blog </b>Module. <br><br>	
	            <logic:present name="bconfig"> 
					<bean:write name="bconfig" property="blogMessage" filter="false"/>	
				</logic:present>				     
              </p> 
            </blockquote>
        </div></td>
        </tr>-->
	<tr>
							 <td class="Orange_fade">The Bloggers Profile</td>
						  </tr>
      <tr>
        <td>
        	<table width="857" border="0" valign="top" cellspacing="0" cellpadding="0" bgcolor="white">
			
				<tr valign="top">

					<td width="78%" bgcolor="white" >
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							
						  <logic:present name="error" scope="request" >
								<tr><td>&nbsp;</td></tr>
							  <tr>
								<td width="100%" class="body_detail_text" colspan="3">
								<b><bean:message name="error" scope="request"/></b>
								</td>
							  </tr>
							  <tr><td>&nbsp;</td></tr>
						</logic:present>
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
													<logic:iterate id="AuthorDetails" name="AuthorDetailsList" type="com.oifm.blog.OIBAIndividualBlog">
													<tr>
														<td class="body_extract_text bold" style="font-size:14px;"><a id="<bean:write name="AuthorDetails" property="authorUserName" />" name="<bean:write name="AuthorDetails" property="authorUserName" />"><bean:write name="AuthorDetails" property="authorUserName" /></a><br /><br /></td>
													</tr>
													<tr>
														<td class="body_extract_text">
															<logic:notEmpty name="AuthorDetails" property="authorImageFileName"><img src="<bean:message key="OIFM.contextroot" />/IndividualBlogAboutUs.do?hiddenAction=READ_PICTURE&authorImageFileName=<bean:write name="AuthorDetails" property="authorImageFileName" />" style="float:left; margin-right:15px; margin-bottom:15px;"></logic:notEmpty>
															<bean:write name="AuthorDetails" property="authorDescription" filter="false" /></td>
													</tr>
													<tr>
														<td><br /><hr /></td>
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
							<table width="80%" border="0" cellpadding="0" cellspacing="0">
								<tr><td>&nbsp;</td>
								<tr><td colspan="2"><jsp:include page="/jsp/blog/IndividualBlogMenu.jsp" flush="true" /></td> </tr>
								<tr><td>&nbsp;</td> </tr> 
								<tr><td>&nbsp;</td> </tr> 
								<tr><td align="Left" ><span class="Poll_body">The Bloggers</span><br></td></tr>
								<tr><td>&nbsp;</td></tr>
								<logic:iterate id="Author" name="AuthorList" type="com.oifm.blog.OIBAIndividualBlog">
								<tr>
									<td align="Left">
										<span class="Poll_body"><a href="#<bean:write name="Author" property="authorUserName" />"  class="Poll_body"><bean:write name="Author" property="authorUserName" /></a></span>
									</td>
								</tr>
								</logic:iterate>
								<tr><td>&nbsp;</td> </tr> 
								<tr><td>&nbsp;</td> </tr> 
							</table>
						</div>
					</td>
				 </tr>
			</table> 						
        </td>
      </tr>
</table>


