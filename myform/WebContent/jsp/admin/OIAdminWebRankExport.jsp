<%--
/**
 * FileName			: OIAdminWebRankExport.jsp
 * Author      		: Suresh Kumar.R
 * Created Date 	: 22 jul 2005
 * Description 		: This page used to display the poll results
 * Version			: 1.0
 **/
--%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="org.apache.struts.util.LabelValueBean" %>
<%@ page import="com.oifm.utility.OIDBRegistry" %>
<%@ page import="com.oifm.useradmin.OIBAWebRanking" %>
<%@ page import="com.oifm.useradmin.OIBARankingDetails" %>
<%@ page import="java.util.ArrayList" %>


 <title>Web Rank Details </title>

   
<%response.setContentType("application/vnd.ms-excel") ; %>

  
<html:html>

<script language="javascript" src='<bean:message key="OIFM.docroot" />/js/Common.js'></script>

<script>
var strDocRoot ='<bean:message key="OIFM.contextroot" />';
</script>	
<logic:present name="Result" scope="request">
    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
         <tr>
            <td width="20%" class="Mainheader">Name</td>
            <td width="18%" class="Mainheader">Designation</td>
            <td width="8%" class="Mainheader">Age</td>
            <td width="20%" class="Mainheader">School Level</td>
            <td width="16%" class="Mainheader">No. of Years in Service</td>
            <td width="15%" class="Mainheader">No. of Hits</td>
          </tr>
          <logic:iterate id="objRanking" name="Result" indexId="idx" type="com.oifm.useradmin.OIRankingBean">
          	<tr>
            	<td class="BodyText"><bean:write name="objRanking" property="strName" /></td>
            	<td class="BodyText"><bean:write name="objRanking" property="strDesignation" /></td>
            	<td class="BodyText"><bean:write name="objRanking" property="strAge" /></td>
            	<td class="BodyText"><bean:write name="objRanking" property="strSchoolLevel" /></td>
            	<td class="BodyText"><bean:write name="objRanking" property="strYIS" />
            	<td class="BodyText"><bean:write name="objRanking" property="strHits" /></td>
          	</tr>
          </logic:iterate>
        </table>
</logic:present>
</html:html>