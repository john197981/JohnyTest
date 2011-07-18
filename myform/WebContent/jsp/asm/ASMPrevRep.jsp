<%--
/**
 * FileName			: ASMPrevRep.jsp
 * Author      		: Anbalagan
 * Created Date 	: 09/12/2005
 * Description 		: This page used to display the ASM home page.
 * Version			: 1.0
 **/  
--%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page language="java" import="com.oifm.asm.ASMBVPrevRep,com.oifm.utility.OIUtilities"%>
   
<script language="javascript" src='<bean:message key="OIFM.docroot" />/js/asmPrevRep.js'></script>

<%String strAction = OIUtilities.replaceNull(""+request.getParameter("hiddenAction"));%>

<%try{%>
<table border="0" cellspacing="0" cellpadding="0" width="100%" align="center">
<tr><td valign="top" align="center">

<html:form action="asmPrevRep.do" method="post" >
 		<table width="857" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td class="Orange_fade">Previous Published Replies</td>
          </tr>
        </table>
 		<table width="857" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td valign="top" class="orange"><div align="justify" class="Highlight_body"> 
              <blockquote>
                <p><br>

					<logic:present name="ModuleDesc" scope="request">
						<bean:write name="moduleDesc" scope="request" filter="false"/> 
					</logic:present>
 
                </p>
                 
              </blockquote>
            </div></td>
            </tr>
          <tr>
            <td align="left" valign="top" bgcolor="#f7f8fc">
 			<table width="857" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="648" class="Grey_fade">&nbsp;</td>
                <td width="16" class="Blue">&nbsp;</td>
                <td width="193" rowspan="2" align="left" valign="top" class="Blue">
				  

<jsp:include page="/jsp/asm/ASMRightMenu.jsp" flush="true"> <jsp:param name="pageName" value="PrevRep" /> </jsp:include>
					   

					   <p class="Address_body">&nbsp;</p></td>
              </tr>
              <tr>
                <td align="left" valign="top" bgcolor="#f7f8fc">
			
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
				
					<tr>
                      <td width="100%" colspan="2" class="Box">

					  <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
                        <tr>
                          <td colspan="3" align="right" ></td>
                          </tr>
                        <tr>
                          <td width="4%" class="Table_head">&nbsp;</td>
                          <td width="62%" class="Table_head">
						  
						<%if(strAction.equals("populateNew")){%>
							Letter(s) Published in last <bean:write name="months" scope="request"/> months
						<%}else{%>
							Letter(s) Published before <bean:write name="months" scope="request"/> months
						<%}%>
						  </td>
                          <td width="34%" align="right" class="Table_head">Status</td>
                        </tr>
                        
						<logic:notEqual name="TotRec" value="0">
							<logic:iterate id="objBV" name="latest_letter" type="com.oifm.asm.ASMBVPrevRep" scope="request" indexId="rowNum" >

						<tr>
                          <td>&nbsp;</td>
                          <td>
						  
								<a class="Heading_Thread" href="#" onclick="fnCallDetails(<bean:write name="objBV" property="hidLetterID"/>)">
									<bean:write name="objBV" property="hidLetterTopic"  />
								</a>
						  
						  </td>
                          <td align="right" class="Heading_Attributes">
						  Submitted By <bean:write name="objBV" property="hidSubmittedBy" /> <br>
						  Published On <bean:write name="objBV" property="hidPublishOn" /> 

						  </td>
                        </tr>

				</logic:iterate>
			</logic:notEqual>
    
					  </table></td>
                    </tr>
					<tr><td colspan="2">&nbsp;</td></tr>
					
					<tr>
					  <td colspan="2">&nbsp;</td>
					  </tr>
                  </table>

                  <br></td>
                <td class="Blue">&nbsp;</td>
              </tr>

<!--pagination start  -->
		<tr>
		<td height="28"  align="right">
		
			<logic:greaterThan name="totalPage" value="1">
			<table border="0" cellpadding="2" cellspacing="0" class="BodyText">
				<tr>
					<td nowrap class="Boxinside_text"> 
						Page 
							<bean:write name="pageNo" scope="request" /> 
						of 
							<bean:write name="totalPage" scope="request" />
					</td>
					<logic:present name="previousSet" scope="request">
						<logic:equal name="previousSet" scope="request" value="true">
							<td nowrap class="BD_2">&lt;</td>
							<td nowrap class="BD_3"> 
								<a href='#' onclick="javascript:fnSubmitPage('<bean:write name="previousPage" scope="request"/>');">
									&laquo;Previous</a>
							</td>
						</logic:equal>
					</logic:present>


					<!--<td nowrap class="BD_1">1</td>-->
					<logic:present name="arPage" scope="request">
						<logic:iterate id="no" name="arPage" scope="request">
							<%
								String currentPage=(String) request.getAttribute("pageNo");
								String temp = (String) no;
								if (! currentPage.trim().equals(temp.trim()))
								{
							%>
							<td nowrap class="BD_2">
								<a href='#' onclick="javascript:fnSubmitPage('<bean:write name="no" />');">		
									<bean:write name="no" /></a>
							</td>
							<%
								}
								else
								{
							%>
							<td nowrap class="BD_1">
									<bean:write name="no" />
							</td>
							<%
								}
							%>
						</logic:iterate>
					</logic:present>
					<logic:present name="nextSet" scope="request">
						<logic:equal name="nextSet" scope="request" value="true">
							<td nowrap class="BD_2">&gt;</td>
							<td nowrap class="BD_3"> 
								<a href='#' onclick="javascript:fnSubmitPage('<bean:write name="nextPage" scope="request"/>');">
									Next&raquo;</a>
							</td>
						</logic:equal>
					</logic:present>
		</tr>
			</table>
			</logic:greaterThan >
		</td><td colspan = 2 class="Blue">&nbsp;</td>
		</tr>
	<!-- Pagination end-->

            </table>
			

			</td>
 		  </tr>
        </table>
 
			</td>
 		  </tr>
        </table>
<jsp:include page="/jsp/common/oifm_footer.jsp" flush="true" />
<!-- This hidden variable holds the letter id-->
<html:hidden property="hidLetterID" />	
<html:hidden property="hiddenAction" />

<!-- -->
<html:hidden property="hidLink" />
<html:hidden property="hidPageDesc" />

</html:form> 

<%}catch(Exception e){out.println(e);}%>

<script language="javascript">
	function fnRightPanelSubmit(actionName,hiddenAction)
	{
 		document.ASMFormPrevRep.hiddenAction.value=hiddenAction;
		document.ASMFormPrevRep.action=actionName+"?hiddenAction="+hiddenAction;
		document.ASMFormPrevRep.submit();
	}
</script>
