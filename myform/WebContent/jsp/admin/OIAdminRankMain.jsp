<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page import="com.oifm.useradmin.OIRankingConstants" %>

<script language="javascript">
function submitWebsiteRank() {
	if(checkBlank(document.RankingForm.strNumToShow, "number of users to show")){
		if (checkNumber(document.RankingForm.strNumToShow, "number of users to show")) {
			document.RankingForm.hiddenAction.value = '<%= OIRankingConstants.WEB_RANK %>';
			document.RankingForm.submit();
		}
	}
}

function submitModuleRank() {
	if (checkBlank(document.RankingForm.strNumToShow, "number of users to show")) {
		if (checkNumber(document.RankingForm.strNumToShow, "number of users to show")) {
			document.RankingForm.hiddenAction.value = '<%= OIRankingConstants.MOD_RANK %>';
			document.RankingForm.submit();
		}
	}
}

function submitSummaryDetails() {
	document.RankingForm.action="SummaryDetails.do"
	document.RankingForm.hiddenAction.value = "SummaryDetails";
	document.RankingForm.submit();
 }

</script>
<SCRIPT LANGUAGE="JavaScript" src='<bean:message key="OIFM.docroot" />/js/Common.js' ></SCRIPT>

<html:form action="/Ranking.do" method="post">
<html:hidden property="hiddenAction" />
<jsp:include page="/jsp/common/oifm_admin_header.jsp" flush="true" />


 	<table width="98%" border="0" cellpadding="0"
		cellspacing="0" bgcolor="white">
		<tr>
 				<td class="Box">
					<table width="100%" border="0" cellpadding="1"
						cellspacing="1" bgcolor="white">
					<tr class="Table_head" >
						<td colspan="3">User Ranking</td>
 					</tr>
           <tr>
            <td width="11%" class="Heading_attributes">Show Top</td>
            <td width="11%" class="Heading_Thread"><html:text property="strNumToShow" styleClass="Text_box" size="5" maxlength="4" value="10" /></td>
            <td width="78%" class="Heading_attributes">Users</td>
          </tr>
          <tr>
            <td class="Heading_attributes">For last </td>
            <td class="Heading_Thread"><html:select property="strPeriod" styleClass="Text">
              <html:option value="1">1</html:option>
              <html:option value="2">2</html:option>
              <html:option value="3">3</html:option>
              <html:option value="4">4</html:option>
              <html:option value="5">5</html:option>
              <html:option value="6">6</html:option>
                        </html:select></td>
            <td class="Heading_attributes">Months</td>
          </tr>
       <tr>
        <td height="35" colspan="3" align="left"><p><a href="#" onclick="submitWebsiteRank()"><img src="<bean:message key="OIFM.docroot"/>/images/btn_website_rank.gif" border="0" alt="Website Rank"></a> <a href="#" onclick="submitModuleRank()"><img src="<bean:message key="OIFM.docroot"/>/images/btn_module_wise_rank.gif" border="0" alt="Module Wise Rank"></a>
		<a class="special_body_link" href="#" onclick="submitSummaryDetails()">Summary Details</a>
		</p></td>
		</tr>

			</table>
 
 			</td>
		</tr>
	</table>
</html:form>