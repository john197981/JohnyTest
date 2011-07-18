<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page import="com.oifm.useradmin.OIRankingConstants" %>

<jsp:include page="/jsp/common/oifm_admin_header.jsp" flush="true" />


 	<table width="98%" border="0" cellpadding="0"
		cellspacing="0" bgcolor="white">
		<tr>
 				<td class="Box">
					<table width="100%" border="0" cellpadding="0"
						cellspacing="0" bgcolor="white">
					<tr class="Table_head" >
						<td colspan="3">User Ranking </td>
 					</tr>

         <tr>
          <td colspan="3">
		  <br> 
		  <span class="Text">
		  The following are most active users in each module </span><br>         
		  <br>
		  </td>
        </tr>
      <tr>
		  <td width="47%" >
					<table width="98%"  border="0" cellspacing="0" cellpadding="0" align="center" class="Box" bgcolor="white">
						  <tr>
							<td colspan="2" class="Table_head">Forum posting</td>
							</tr>
						  <tr>
							<td width="20%" class="Sub_head">Name</td>
							<td width="18%" class="Sub_head">No of Posts</td>
						  </tr>
						  <logic:iterate id="objRanking" name="PostResult" indexId="idx" type="com.oifm.useradmin.OIRankingBean">
							<tr>
								<td class="heading_attributes"><bean:write name="objRanking" property="strNickname" /></td>
								<td class="heading_attributes"><bean:write name="objRanking" property="strPostCount" /></td>
							</tr>
						 </logic:iterate>
					  </table>
	 		</td>
		<td width="6%">&nbsp;</td>
        <td width="47%" align="left" valign="top" > 
						<table width="98%"  border="0" cellspacing="0" cellpadding="0" align="center" class="Box" bgcolor="white">
						<tr>
						  <td colspan="2" class="Table_head">Forum Threads </td>
						  </tr>
						<tr>
						  <td width="20%" class="Sub_head">Name</td>
						  <td width="18%" class="Sub_head">No of Threads</td>
						</tr>
						<logic:iterate id="objRanking" name="ThreadResult" indexId="idx" type="com.oifm.useradmin.OIRankingBean">
							<tr>
								<td class="heading_attributes"><bean:write name="objRanking" property="strNickname" /></td>
								<td class="heading_attributes"><bean:write name="objRanking" property="strThreadCount" /></td>
							</tr>
						</logic:iterate>
					</table>
			</td>
          </tr>
       <tr>
        <td colspan="3" align="left" valign="top">&nbsp;</td>
       </tr>

      <tr>
        <td align="left" valign="top" > 
			<table width="98%"  border="0" cellspacing="0" cellpadding="0" class="Box" align="center" bgcolor="white">
                <tr class="Table_head">
                  <td colspan="2" class="Table_head">Consultation Paper </td>
                  </tr>
                <tr>
                  <td width="20%" class="Sub_head">Name</td>
                  <td width="18%" class="Sub_head">No of Consultations</td>
                </tr>
                <logic:iterate id="objRanking" name="PaperResult" indexId="idx" type="com.oifm.useradmin.OIRankingBean">
          			<tr>
            			<td class="heading_attributes"><bean:write name="objRanking" property="strNickname" /></td>
            			<td class="heading_attributes"><bean:write name="objRanking" property="strPaperCount" /></td>
          			</tr>
          		</logic:iterate>
            </table>
		</td>
         
		 <td >&nbsp;</td>

        <td align="left" valign="top" >
			<table width="98%"  border="0" cellspacing="0" cellpadding="0" class="Box" bgcolor="white">
                <tr>
                  <td colspan="2" class="Table_head">Survey</td>
                  </tr>
                <tr>
                  <td width="20%" class="Sub_head">Name</td>
                  <td width="18%" class="Sub_head">No of Survey</td>
                </tr>
                <logic:iterate id="objRanking" name="SurveyResult" indexId="idx" type="com.oifm.useradmin.OIRankingBean">
          			<tr>
            			<td class="heading_attributes"><bean:write name="objRanking" property="strNickname" /></td>
            			<td class="heading_attributes"><bean:write name="objRanking" property="strSurveyCount" /></td>
          			</tr>
        		  </logic:iterate>
            </table>
			</td>
          </tr>
 
	   <tr>
        <td colspan="3" align="left">  <p>&nbsp;</p>
          <p><a href="<bean:message key="OIFM.contextroot"/>/Ranking.do"><img src="<bean:message key="OIFM.docroot"/>/images/but_ok.gif" border="0" alt="OK"></a> <a href="#" onclick="window.print()"><img src="<bean:message key="OIFM.docroot"/>/images/but_print.gif" border="0" alt="Print"></a></p></td>
		</tr>

	</table>

	</td>
	</tr>
</table>
