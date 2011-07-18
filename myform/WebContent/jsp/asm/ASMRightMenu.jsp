<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page language="java" import="com.oifm.asm.ASMBVCommon,com.oifm.utility.OIUtilities"%>


  
<%
try
{
%>
 	
<table width="98%"  border="0" cellspacing="0" cellpadding="0">
	  <tr>
		<td colspan="2" class="Poll_body style2">&nbsp;</td>
	  </tr>
					
					
					<tr class="Text">
 						<td width="13%" class="Poll_body style2">
							<img src='<bean:message key="OIFM.docroot" />/images/Bullet_round.gif' width="22" height="22">
						</td>
						<logic:present parameter="pageName">
							<logic:match parameter="pageName" value="Home">
								<td width="87%" class="Poll_body style2"> ASM Home Page</td>
							</logic:match>
							<logic:notMatch parameter="pageName" value="Home">
								<td width="87%" class="Poll_body style2"> <a class="Poll_body style2" href="javascript:fnRightPanelSubmit('<bean:message key="OIFM.contextroot" />/asmHome.do','populate');">ASM Home Page</a></td>
							</logic:notMatch>
						</logic:present>
						<logic:notPresent parameter="pageName">
							<td width="87%" class="Poll_body style2"> <a class="Poll_body style2" href="javascript:fnRightPanelSubmit('<bean:message key="OIFM.contextroot" />/asmHome.do','populate');">ASM Home Page</a></td>
						</logic:notPresent>
					</tr>


					<tr>
 						<td class="Poll_body style2">
							<img src='<bean:message key="OIFM.docroot" />/images/Bullet_round.gif' width="22" height="22">
						</td>
						<logic:present parameter="pageName">
							<logic:match parameter="pageName" value="SubmitView">
								<td class="Poll_body style2">Submit/View Your Letter(s)</td>
							</logic:match>
							<logic:notMatch parameter="pageName" value="SubmitView">
								<td class="Poll_body style2"><a class="Poll_body style2" href="javascript:fnRightPanelSubmit('<bean:message key="OIFM.contextroot" />/ASMView.do','populate');">Submit/View Your Letter(s)</a></td>
 
							</logic:notMatch>
						</logic:present>
						<logic:notPresent parameter="pageName">
							<td class="Poll_body style2"><a class="Poll_body style2" href="javascript:fnRightPanelSubmit('<bean:message key="OIFM.contextroot" />/ASMView.do','populate');">Submit/View Your Letter(s)</a></td>
						</logic:notPresent>
					</tr>
					<tr align="left" valign="top" class="Text">
						<td height="25" >
							<img src='<bean:message key="OIFM.docroot" />/images/Bullet_round.gif' width="22" height="22"> 
						</td>
						<logic:present parameter="pageName">
							<logic:match parameter="pageName" value="PrevRep">
								<td class="Poll_body style2">Previous Replies</td>
							</logic:match>
							<logic:notMatch parameter="pageName" value="PrevRep">
								<td class="Poll_body style2">
								<a class="Poll_body style2" href="javascript:fnRightPanelSubmit('<bean:message key="OIFM.contextroot" />/asmPrevRep.do','populateNew');">Previous Replies</a>
								</td>
							</logic:notMatch>
						</logic:present>
						<logic:notPresent parameter="pageName">
							<td class="Poll_body style2"><a class="Poll_body style2" href="javascript:fnRightPanelSubmit('<bean:message key="OIFM.contextroot" />/asmPrevRep.do','populateNew');">Previous Replies</a></td>
						</logic:notPresent>
					</tr>
					<tr align="left" valign="top" class="Text">
						<td height="25">
							<img src='<bean:message key="OIFM.docroot" />/images/Bullet_round.gif' width="22" height="22">
						</td>
						<logic:present parameter="pageName">
							<logic:match parameter="pageName" value="Senior">
								<td class="Poll_body style2">Our Senior Management</td>
							</logic:match>
							<logic:notMatch parameter="pageName" value="Senior">
								<td class="Poll_body style2"><a class="Poll_body style2" href="<bean:message key="OIFM.contextroot" />/ASMManagement.do">Our Senior Management</a></td>
							</logic:notMatch>
						</logic:present>
						<logic:notPresent parameter="pageName">
							<td class="Poll_body style2"><a class="Poll_body style2" href="<bean:message key="OIFM.contextroot" />/ASMManagement.do">Our Senior Management</a></td>
						</logic:notPresent>
					</tr>
					<tr align="left" valign="top" class="Text">
						<td height="28">
							<img src='<bean:message key="OIFM.docroot" />/images/Bullet_round.gif' width="22" height="22">
						</td>
						<logic:present parameter="pageName">
							<logic:match parameter="pageName" value="About">
								<td class="Poll_body">About ASM</td>
							</logic:match>
							<logic:notMatch parameter="pageName" value="About">
								<td class="Poll_body"><a class="Poll_body style2" href="<bean:message key="OIFM.contextroot" />/ASMAbout.do">About ASM</a></td>
							</logic:notMatch>
						</logic:present>
						<logic:notPresent parameter="pageName">
							<td class="Poll_body"><a class="Poll_body style2" href="<bean:message key="OIFM.contextroot" />/ASMAbout.do">About ASM</a></td>
						</logic:notPresent>
					</tr>
					
					<tr align="left" valign="top" class="Text">
						<td height="28">
							<img src='<bean:message key="OIFM.docroot" />/images/Bullet_round.gif' width="22" height="22">
						</td>
						<logic:present parameter="pageName">
							<logic:match parameter="pageName" value="Categories">
								<td class="Poll_body">Categories of ASM Letters</td>
							</logic:match>
							<logic:notMatch parameter="pageName" value="Categories">
								<td class="Poll_body"><a class="Poll_body style2" href="<bean:message key="OIFM.contextroot" />/ASMActionCategoriesOfLetter.do">Categories of ASM Letters</a></td>
							</logic:notMatch>
						</logic:present>
						<logic:notPresent parameter="pageName">
							<td class="Poll_body"><a class="Poll_body style2" href="<bean:message key="OIFM.contextroot" />/ASMActionCategoriesOfLetter.do">Categories of ASM Letters</a></td>
						</logic:notPresent>
					</tr>
					
					<tr align="left" valign="top" class="Text">
						<td height="28">
							<img src='<bean:message key="OIFM.docroot" />/images/Bullet_round.gif' width="22" height="22">
						</td>
						<logic:present parameter="pageName">
							<logic:match parameter="pageName" value="EditorNotes">
								<td class="Poll_body">Categories of Editor's Notes</td>
							</logic:match>
							<logic:notMatch parameter="pageName" value="EditorNotes">
								<td class="Poll_body"><a class="Poll_body style2" href="<bean:message key="OIFM.contextroot" />/ASMCategoriesOfEditorNotes.do">Categories of Editor's Notes</a></td>
							</logic:notMatch>
						</logic:present>
						<logic:notPresent parameter="pageName">
							<td class="Poll_body"><a class="Poll_body style2" href="<bean:message key="OIFM.contextroot" />/ASMCategoriesOfEditorNotes.do">Categories of Editor's Notes</a></td>
						</logic:notPresent>
					</tr>
					
					
					<!--<logic:present name="editors_note_posted_on" scope="request">

					 <tr >
						<td height="25" colspan="2" class="Yellow_text">Editor's Note </td>
					</tr>

<tr>
                        <td colspan="2" class="Poll_body style2"><table cellspacing="0" cellpadding="0">
                          <tr>
                            <td class="Address_body"><bean:write name="editors_note_posted_on" scope="request" />
                                <br> <bean:write name="editors_note" scope="request" filter="false" /><br>
                               	</td>
                          </tr>
						   <tr>
                            <td class="Address_body">
                                      <span>
									  <a class="Poll_body style2" href='<bean:message key="OIFM.contextroot" />/ASMEditor.do' class="Poll_body">Read More</a>
									  </span>
							</td>
                          </tr>
                          <tr>
                            <td colspan="2"></td>
                          </tr>
                           
                        </table></td>
                      </tr>

                      <tr>


						</logic:present>-->

					 <tr align="left" valign="top">
						<td height="28" colspan="2">&nbsp;</td>
					</tr>

					<%if(!OIUtilities.replaceNull(request.getParameter("hiddenAction")).equals("PrevRepSearch")){%>
				<logic:notEqual name="pageName" value="ASMPrevRep">
				<logic:notEqual name="TotRecSizRecLtr" value="0">

					<tr>
						<td height="25" colspan="2" class="Yellow_text"> Recent Letter(s)</td>
					</tr>
					<tr align="left" valign="top" class="Text">
						<td height="13" colspan="2">&nbsp;</td>
					</tr>
					
					<!-- Check the records size of recent letter-->

						<logic:present name="recent_letter" scope="request">
						<logic:iterate id="objBV" name="recent_letter" type="com.oifm.asm.ASMBVCommon" scope="request" indexId="rowNum" >
                       <tr>
                        <td colspan="2" > 
						<table cellspacing="0" cellpadding="0">
                          <tr>
                            <td colspan="2" class="Poll_body" ><p> 

								<%if(OIUtilities.replaceNull(objBV.getHidRecLtrID()).equals(request.getParameter("hidLetterID")) && 
								!OIUtilities.replaceNull(request.getParameter("pageName")).equals("SubmitView")){%>	
 									<bean:write name="objBV" property="hidRecLtrTopic"  /><br>
								<%}else{%>
 									<a href="#" class="Poll_body style2"  onclick="fnRecentLetter(<%=objBV.getHidRecLtrID()%>)">
									<bean:write name="objBV" property="hidRecLtrTopic"  /><br>

									</a>
								<%}%>
								<input type="hidden" name="hidRecLtrID" value="<%=objBV.getHidRecLtrID()%>"/>	
								 <span class="Highlight_body">-&nbsp;
								Published on <bean:write name="objBV" property="hidRecLtrPubOn" /></span>
									</p>
									
							</td>
                          </tr>
                         </table>
						 </td>
                        </tr>
					</logic:iterate>
 						</logic:present>
					</logic:notEqual>
					</logic:notEqual>
					<%}%>
 
	</table>
<%
}
catch(Exception e)
{
	out.println(e.getMessage());
}
%>
<input type="hidden" name="hiddenAction">

 
