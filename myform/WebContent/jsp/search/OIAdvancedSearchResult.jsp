<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<%@ page import="org.apache.struts.util.LabelValueBean" %>

<%@ page import="com.oifm.forum.OIForumConstants" %>

<%@ page import="com.oifm.search.OISearchConstants" %>

<%@ page import="com.oifm.survey.OISurveyConstants" %>



<link href='<bean:message key="OIFM.docroot" />/css/iofs.css' rel="stylesheet" type="text/css">

<style type=text/css>

	.activetab 
		{
		 font-family: Verdana;
		 font-weight: bold;
		 BORDER-RIGHT: red 1px solid;
		 BORDER-TOP: red 1px solid; 
		 border-left: red 1px solid;
		 border-bottom: silver 0px solid; 
		 MARGIN-TOP: 1px; 
		 MARGIN-LEFT: 1px; 
		 BORDER-LEFT: red 1px solid; 
		 WIDTH: 120px; 
		 MARGIN-RIGHT: 1px;
		 font-size: 11px;
		 text-align: center;		 
		}
	.inactivetab
		{
		font-family: Verdana;
		font-weight: normal;
		BORDER-RIGHT: red 1px solid;
		BORDER-TOP: red 1px solid; 
		border-left: red 1px solid;
		border-bottom: red 0px solid; 
		MARGIN-TOP: 1px; 
		MARGIN-LEFT: 1px; 		
		WIDTH: 120px; 
		MARGIN-RIGHT: 1px;
		font-size: 11px;
		text-align: center;
		}
	

	.bodytopborder

	{

		BORDER-TOP: red 1px solid; 		

	}

	.bodyleftborder

	{

		BORDER-LEFT: red 1px solid; 

	}				

	.bodyrightborder

	{

		BORDER-RIGHT: red 1px solid; 		

	}

	.bodybottomborder

	{

		BORDER-BOTTOM: red 1px solid; 		

	}

	.bodytoprightborder

	{

		BORDER-RIGHT: red 1px solid; 

		BORDER-TOP: red 1px solid;

	}

	.bodytopleftborder

	{

		BORDER-LEFT: red 1px solid; 

		BORDER-TOP: red 1px solid;

	}

	.bodyleftbtmrightborder

	{

		BORDER-LEFT: red 1px solid; 

		BORDER-BOTTOM: red 1px solid;

		BORDER-RIGHT: red 1px solid;

	}

	

</style>



<script language="javascript">

	function fnSubmit(actionName,moduleFlag,hiddenAction)

	{

		document.AdvancedSearchForm.pageNo.value='1';

		document.AdvancedSearchForm.moduleFlag.value=moduleFlag;

		document.AdvancedSearchForm.hiddenAction.value=hiddenAction;

		document.AdvancedSearchForm.action=actionName;

		document.AdvancedSearchForm.submit();

	}



	function fnSubmit1(actionName,moduleFlag,hiddenAction,pageNo)

	{

		document.AdvancedSearchForm.pageNo.value=pageNo;

		document.AdvancedSearchForm.moduleFlag.value=moduleFlag;

		document.AdvancedSearchForm.hiddenAction.value=hiddenAction;

		document.AdvancedSearchForm.action=actionName;

		document.AdvancedSearchForm.submit();

	}

</script>



<jsp:include page="/jsp/common/iofm_Top.jsp" flush="true">

	<jsp:param name="pageName" value="Search" />

</jsp:include>




        <table width="95%" border="0" cellspacing="0" cellpadding="0">
<html:form action="/AdvancedSearch.do">
          <tr>
            <td class="Orange_fade">Advanced Search</td>
          </tr>
        </table>

         <table width="95%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td valign="top" class="orange">
            <div align="justify" class="Highlight_body">
              <p>&nbsp;</p>
              <p> 
              </p>
            </div></td>
            <td width="664" valign="top" class="orange"><p>&nbsp;</p>
              <p><span class="Highlight_body">  
 
                  <br>
                </p></td>
            <td width="148" valign="top" class="orange">&nbsp;</td>
          </tr>
          
          <tr>
            <td colspan="3" class="Grey_fade">&nbsp;</td>
          </tr>
          <tr>
</table> 
 
			<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td align="left" valign="top"  width="100%">
						<table width="98%" class="box" border="0" cellspacing="1" cellpadding="1" align="center" >
							<tr>
								<td colspan="2" >
						<table><tr height="10">
					<logic:present name="AdvancedSearchForm" property="moduleFlag">
 							<logic:present name="AdvancedSearchForm" property="inForum">
 								<logic:equal name="AdvancedSearchForm" property="inForum" value="on">
  									<logic:equal name="AdvancedSearchForm" property="moduleFlag" value="F">
  										<td class="inactivetab" bgColor="#cccccc">
										Discussion Forum
										<td style="WIDTH: 1px"></TD>
 									</logic:equal>
 									<logic:notEqual name="AdvancedSearchForm" property="moduleFlag" value="F">
										<td class="activetab" bgColor="#ffcc66">
										<a class="Heading_attributes" href="#" onclick="javascript:fnSubmit('<bean:message key="OIFM.contextroot" />/AdvancedSearch.do','F','search');">Discussion Forum</a> 
 									</logic:notEqual>
 								</logic:equal>
 							</logic:present>

							<logic:present name="AdvancedSearchForm" property="inSurvey">
 								<logic:equal name="AdvancedSearchForm" property="inSurvey" value="on">
  									<logic:equal name="AdvancedSearchForm" property="moduleFlag" value="S">
									<td class="inactivetab" bgColor="#cccccc">
 										Survey
										<td style="WIDTH: 1px"></TD>
 									</logic:equal>
 									<logic:notEqual name="AdvancedSearchForm" property="moduleFlag" value="S">
									<td class="activetab" bgColor="#ffcc66">
 										<a class="Heading_attributes" href="#"\ onclick="javascript:fnSubmit('<bean:message key="OIFM.contextroot" />/AdvancedSearch.do','S','search');">Survey</a> 
										<td style="WIDTH: 1px"></TD>
 									</logic:notEqual>
 								</logic:equal>
 							</logic:present>
 							<logic:present name="AdvancedSearchForm" property="inPaper">
 								<logic:equal name="AdvancedSearchForm" property="inPaper" value="on">
   									<logic:equal name="AdvancedSearchForm" property="moduleFlag" value="P">
									<td class="inactivetab" bgColor="#cccccc">
										Consultation Paper
										<td style="WIDTH: 1px"></TD>
									</logic:equal>
 									<logic:notEqual name="AdvancedSearchForm" property="moduleFlag" value="P">
									<td class="activetab" bgColor="#ffcc66">
 										<a class="Heading_attributes" href="#"  onclick="javascript:fnSubmit('<bean:message key="OIFM.contextroot" />/AdvancedSearch.do','P','search');">Consultation Paper</a>
										<td style="WIDTH: 1px"></TD>
 									</logic:notEqual> 
 								</logic:equal>
 							</logic:present>
 							<logic:present name="AdvancedSearchForm" property="inBlog">
 								<logic:equal name="AdvancedSearchForm" property="inBlog" value="on">
   									<logic:equal name="AdvancedSearchForm" property="moduleFlag" value="B">
									<td class="inactivetab" bgColor="#cccccc">
										Blog
										<td style="WIDTH: 1px"></TD>
									</logic:equal>
 									<logic:notEqual name="AdvancedSearchForm" property="moduleFlag" value="B">
									<td class="activetab" bgColor="#ffcc66">
 										<a class="Heading_attributes" href="#"  onclick="javascript:fnSubmit('<bean:message key="OIFM.contextroot" />/AdvancedSearch.do','B','search');">Blog</a>
										<td style="WIDTH: 1px"></TD>
 									</logic:notEqual> 
 								</logic:equal>
 							</logic:present>
  					</logic:present>
  					
  					<logic:present name="AdvancedSearchForm" property="inASM">
 								<logic:equal name="AdvancedSearchForm" property="inASM" value="on">
  									<logic:equal name="AdvancedSearchForm" property="moduleFlag" value="A">
									<td class="inactivetab" bgColor="#cccccc">
 										ASM
										<td style="WIDTH: 1px"></TD>
 									</logic:equal>
 									<logic:notEqual name="AdvancedSearchForm" property="moduleFlag" value="A">
									<td class="activetab" bgColor="#ffcc66">
 										<a class="Heading_attributes" href="#"\ onclick="javascript:fnSubmit('<bean:message key="OIFM.contextroot" />/AdvancedSearch.do','A','search');">ASM</a> 
										<td style="WIDTH: 1px"></TD>
 									</logic:notEqual>
 								</logic:equal>
 					</logic:present>
 
					</tr></table>
					</td>

				</tr>

   				<tr>
  					<td colspan="2" class="sub_head"">Searching 

						<logic:present name="AdvancedSearchForm" property="moduleFlag">
   							<logic:equal name="AdvancedSearchForm" property="moduleFlag" value="S">
								Survey
 							</logic:equal>
 							<logic:equal name="AdvancedSearchForm" property="moduleFlag" value="P">
 								Consultation Paper
 							</logic:equal>
 							<logic:equal name="AdvancedSearchForm" property="moduleFlag" value="F">
 								Discussion Forum
 							</logic:equal>
 							<logic:equal name="AdvancedSearchForm" property="moduleFlag" value="B">
 								Blog
 							</logic:equal>
 							<logic:equal name="AdvancedSearchForm" property="moduleFlag" value="A">
								ASM
 							</logic:equal>
  						</logic:present>
 						for 
 						'<bean:write name="AdvancedSearchForm" property="key"/>'</td>
 				</tr>

   				<tr>
  					<td colspan="2" class="table_head"">Search Result
					</td>
  
				<logic:present name="arOISearchResultBean" scope="request">
 					<logic:iterate id="survey" name="arOISearchResultBean" scope="request" type="com.oifm.search.OISearchResultBean">

						<tr>
  							<td width="71%" align="left" valign="top" class="body_detail_text">

								<logic:equal name="AdvancedSearchForm" property="moduleFlag" value="S">

									<a class="special_body_link" href='<bean:message key="OIFM.contextroot" /><%= OISurveyConstants.SURVEY_USER_DO %>?hiddenAction=SurveyDetails&strSurveyId=<bean:write name="survey" property="strID"/>'>

								</logic:equal>

								<logic:equal name="AdvancedSearchForm" property="moduleFlag" value="F">

									<a class="special_body_link" href='<bean:message key="OIFM.contextroot" />/ThreadMaintain.do?strThreadId=<bean:write name="survey" property="strID"/>'>

								</logic:equal>

								<logic:equal name="AdvancedSearchForm" property="moduleFlag" value="P">

									<a class="special_body_link" href='<bean:message key="OIFM.contextroot" />/webConsultOpenPaperAction.do?hiddenAction=populate&paperId=<bean:write name="survey" property="strID"/>'>

								</logic:equal>
								
								<logic:equal name="AdvancedSearchForm" property="moduleFlag" value="B">

									<a class="special_body_link" href='<bean:message key="OIFM.contextroot" />/IndividualBlogEntry.do?entryId=<bean:write name="survey" property="strID"/>'>

								</logic:equal>
								
								<logic:equal name="AdvancedSearchForm" property="moduleFlag" value="A">

									<a class="special_body_link" href='<bean:message key="OIFM.contextroot" />/asmHome.do?hidPageDesc=RecentLetters&hiddenAction=populate&hidLetterID=<bean:write name="survey" property="strID"/>'>

								</logic:equal>

									<bean:write name="survey" property="strTitle"/>

								</a>

								<br>

									<bean:write name="survey" property="strDescription" filter="false" />
 								<br>&nbsp;

							</td>

							<td width="29%" align="left" valign="top" class="body_detail_text">

								<logic:equal name="AdvancedSearchForm" property="moduleFlag" value="F">Posted by 

								<a class="special_body_link" href="#" onclick="javascript:window.showModalDialog('<bean:message key="OIFM.contextroot" />/MemberProfileAction.do?nric=<bean:write name="survey" property="strCreatedBy"/>&hiddenAction=populate','mywindow','dialogWidth:900px;dialogHeight:260px;dialogLeft:50px;dialogRight:0px;resizable:yes,scrollbars:yes;help:no;status:off;' );">

									<bean:write name="survey" property="strNickname"/>

								</a></logic:equal>

								<logic:notEqual name="AdvancedSearchForm" property="moduleFlag" value="F">Created by <bean:write name="survey" property="strNickname"/></logic:notEqual>

								<br>

								on <bean:write name="survey" property="strCreatedOn"/> <br>

							</td>

						</tr>

					</logic:iterate>

				</logic:present>

				<logic:notPresent name="arOISearchResultBean" scope="request">

					<tr>

						<td colspan="1" class="body_detail_text">No Result Found</td>

					</tr>

				</logic:notPresent>

			</table>

  
<br>

<br>

<logic:present name="arOISearchResultBean" scope="request">

	<table border="0" cellspacing="0" cellpadding="0" align="right">

		<tr>

			<td>

				<table border="0" cellpadding="2" cellspacing="0" class="BodyText">

					<tr>

						<logic:present name="pageNo" scope="request">

						<logic:present name="totalPage" scope="request">

							<td nowrap class="Boxinside_text"> 

								Page 

									<bean:write name="pageNo" scope="request" /> 

								of 

									<bean:write name="totalPage" scope="request" />

							</td>

						</logic:present>

						</logic:present>

						<logic:present name="previousSet" scope="request">

							<logic:equal name="previousSet" scope="request" value="true">

								<td nowrap class="BD_2">&lt;</td>

								<td nowrap class="BD_3"> 

									<a href='#' onclick="javascript:fnSubmit1('<bean:message key="OIFM.contextroot" />/AdvancedSearch.do','<bean:write name="AdvancedSearchForm" property="moduleFlag"/>','<bean:write name="AdvancedSearchForm" property="hiddenAction" />',<bean:write name="previousPage" scope="request"/>);">

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

									<a href='#' onclick="javascript:fnSubmit1('<bean:message key="OIFM.contextroot" />/AdvancedSearch.do','<bean:write name="AdvancedSearchForm" property="moduleFlag"/>','<bean:write name="AdvancedSearchForm" property="hiddenAction" />',<bean:write name="no" />);">		

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

									<a href='#' onclick="javascript:fnSubmit1('<bean:message key="OIFM.contextroot" />/AdvancedSearch.do','<bean:write name="AdvancedSearchForm" property="moduleFlag"/>','<bean:write name="AdvancedSearchForm" property="hiddenAction" />',<bean:write name="nextPage" scope="request"/>);">

										Next&raquo;</a>

								</td>

							</logic:equal>

						</logic:present>

	  				</tr>

				</table>

			</td>

			<td width="10">&nbsp;</td>

		</tr>

	</table>

</logic:present>

<br>

<center>

	<a href="#" onclick="javascript:fnSubmit('<bean:message key="OIFM.contextroot" />/AdvancedSearch.do','','populate');">

		<img src='<bean:message key="OIFM.docroot" />/images/but_back.gif' border="0" alt = "Back to Advanced Search"></a>

</center>

						</td>
					</tr>
 			</table>
						</td>
					</tr>
 			</table>

<jsp:include page="/jsp/common/oifm_footer.jsp" flush="true" />


<html:hidden property="hiddenAction"/>

<html:hidden property="key"/>

<html:hidden property="inForum"/>

<html:hidden property="inPaper"/>

<html:hidden property="inSurvey"/>
<html:hidden property="inASM"/>

<html:hidden property="inBlog"/>

<html:hidden property="searchBy"/>

<html:hidden property="findThreads"/>



<html:hidden property="findThreadsPost"/>

<html:hidden property="forumSearchBy"/>

<html:hidden property="forumSearchByDays"/>

<html:hidden property="paperSearchBy"/>

<html:hidden property="paperSearchByDays"/>

<html:hidden property="surveySearchBy"/>
<html:hidden property="asmSearchBy"/>


<html:hidden property="surveySearchByDays"/>
<html:hidden property="asmSearchByDays"/>

<html:hidden property="searchByUser"/>

<html:hidden property="pageNo"/>

<html:hidden property="moduleFlag"/>

</html:form>