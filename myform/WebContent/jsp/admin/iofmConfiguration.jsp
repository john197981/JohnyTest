<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="com.oifm.utility.OIDBRegistry" %>
<%
try
{
 %>
<link href='<bean:message key="OIFM.docroot" />/css/oicalendar.css' rel="stylesheet" type="text/css">
<script language="javascript" src='<bean:message key="OIFM.docroot" />/js/validate.js'></script>
<script language="javascript" src='<bean:message key="OIFM.docroot" />/js/oicalendar.js'></script>
<script language="javascript" src='<bean:message key="OIFM.docroot" />/js/Common.js'></script>
 <script language="javascript">
	var maxlimitHA = 500;
	function fn_textCounter(field, countfield,maxlimit) 
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

 	function fnSubmit(actionName,hiddenAction)
	{
		if (document.ConfigurationForm.sessionTimeout.value=="" || document.ConfigurationForm.sessionTimeout.value=="0")
		{
			alert('<%= OIDBRegistry.getValues("OI.CONF.SESSSION") %>');
			document.ConfigurationForm.sessionTimeout.focus();
			return;
		}
		else if (document.ConfigurationForm.forumArchivePeriod.value=="" || document.ConfigurationForm.forumArchivePeriod.value=="0")
		{
			alert('<%= OIDBRegistry.getValues("OI.CONF.FORUM") %>');
			document.ConfigurationForm.forumArchivePeriod.focus();
			return;
		}
		else if (document.ConfigurationForm.surveyArchivePeriod.value=="" || document.ConfigurationForm.surveyArchivePeriod.value=="0")
		{
			alert('<%= OIDBRegistry.getValues("OI.CONF.SURVEY") %>');
			document.ConfigurationForm.surveyArchivePeriod.focus();
			return;
		}
		else if (document.ConfigurationForm.paperArchivePeriod.value=="" || document.ConfigurationForm.paperArchivePeriod.value=="0")
		{
			alert('<%= OIDBRegistry.getValues("OI.CONF.CONSULTATION") %>');
			document.ConfigurationForm.paperArchivePeriod.focus();
			return;
		}
		else if (document.ConfigurationForm.attachmentSize.value=="" || document.ConfigurationForm.attachmentSize.value=="0")
		{
			alert('<%= OIDBRegistry.getValues("OI.CONF.ATTACH") %>');
			document.ConfigurationForm.attachmentSize.focus();
			return;
		}
		else if (document.ConfigurationForm.noPostingForHotTopics.value=="" || document.ConfigurationForm.noPostingForHotTopics.value=="0")
		{
			alert('<%= OIDBRegistry.getValues("OI.CONF.HOTTOPICS") %>');
			document.ConfigurationForm.noPostingForHotTopics.focus();
			return;
		}
		else if (document.ConfigurationForm.categorizeThread.value=="" || document.ConfigurationForm.categorizeThread.value=="0")
		{
			alert('<%= OIDBRegistry.getValues("OI.CONF.THREAD") %>');
			document.ConfigurationForm.categorizeThread.focus();
			return;
		}
		else if (document.ConfigurationForm.noTopicToShowBookmark.value=="" || document.ConfigurationForm.noTopicToShowBookmark.value=="0")
		{
			alert('<%= OIDBRegistry.getValues("OI.CONF.BOOKMARK") %>');
			document.ConfigurationForm.noTopicToShowBookmark.focus();
			return;
		}
		else if (document.ConfigurationForm.noOfDaysToConsiderLatestTopics.value=="" || document.ConfigurationForm.noOfDaysToConsiderLatestTopics.value=="0")
		{
			alert('<%= OIDBRegistry.getValues("OI.CONF.LATESTTOPICS") %>');
			document.ConfigurationForm.noOfDaysToConsiderLatestTopics.focus();
			return;
		}
		else if (document.ConfigurationForm.sendRemiders.value=="" || document.ConfigurationForm.sendRemiders.value=="0")
		{
			alert('<%= OIDBRegistry.getValues("OI.CONF.SENDREMINDERS") %>');
			document.ConfigurationForm.sendRemiders.focus();
			return;
		}


		if (checkNumber(document.ConfigurationForm.sessionTimeout,'<%= OIDBRegistry.getValues("OI.CONF.SESSSION_1") %>')==false)
		{
			return;
		}
		else if (checkNumber(document.ConfigurationForm.forumArchivePeriod,'<%= OIDBRegistry.getValues("OI.CONF.FORUM_1") %>')==false)
		{
			return;
		}
		else if (checkNumber(document.ConfigurationForm.surveyArchivePeriod,'<%= OIDBRegistry.getValues("OI.CONF.SURVEY_1") %>')==false)
		{
			return;
		}
		else if (checkNumber(document.ConfigurationForm.attachmentSize,'<%= OIDBRegistry.getValues("OI.CONF.ATTACH_1") %>')==false)
		{
			return;
		}
		else if (checkNumber(document.ConfigurationForm.paperArchivePeriod,'<%= OIDBRegistry.getValues("OI.CONF.CONSULTATION_1") %>')==false)
		{
			return;
		}
		else if (checkNumber(document.ConfigurationForm.noPostingForHotTopics,'<%= OIDBRegistry.getValues("OI.CONF.HOTTOPICS_1") %>')==false)
		{
			return;
		}
		else if (checkNumber(document.ConfigurationForm.categorizeThread,'<%= OIDBRegistry.getValues("OI.CONF.THREAD_1") %>')==false)
		{
			return;
		}
		else if (checkNumber(document.ConfigurationForm.noTopicToShowBookmark,'<%= OIDBRegistry.getValues("OI.CONF.BOOKMARK_1") %>')==false)
		{
			return;
		}
		else if (checkNumber(document.ConfigurationForm.noOfDaysToConsiderLatestTopics,'<%= OIDBRegistry.getValues("OI.CONF.LATESTTOPICS_1") %>')==false)
		{
			return;
		}
		else if (checkNumber(document.ConfigurationForm.sendRemiders,'<%= OIDBRegistry.getValues("OI.CONF.SENDREMINDERS_1") %>')==false)
		{
			return;
		}

		if (document.ConfigurationForm.sessionTimeout.value.valueOf()>50)
		{
			alert('<%= OIDBRegistry.getValues("OI.CONF.SESSSION_2") %>');
			document.ConfigurationForm.sessionTimeout.focus();
			return;
		}
		else if (document.ConfigurationForm.forumArchivePeriod.value.valueOf()>24)
		{
			alert('<%= OIDBRegistry.getValues("OI.CONF.FORUM_2") %>');
			document.ConfigurationForm.forumArchivePeriod.focus();
			return;
		}
		else if (document.ConfigurationForm.surveyArchivePeriod.value.valueOf()>24)
		{
			alert('<%= OIDBRegistry.getValues("OI.CONF.SURVEY_2") %>');
			document.ConfigurationForm.surveyArchivePeriod.focus();
			return;
		}
		else if (document.ConfigurationForm.paperArchivePeriod.value.valueOf()>24)
		{
			alert('<%= OIDBRegistry.getValues("OI.CONF.CONSULTATION_2") %>');
			document.ConfigurationForm.paperArchivePeriod.focus();
			return;
		}
		else if (document.ConfigurationForm.attachmentSize.value.valueOf()>5)
		{
			alert('<%= OIDBRegistry.getValues("OI.CONF.ATTACH_2") %>');
			document.ConfigurationForm.attachmentSize.focus();
			return;
		}
		else if (document.ConfigurationForm.noPostingForHotTopics.value.valueOf()>99)
		{
			alert('<%= OIDBRegistry.getValues("OI.CONF.HOTTOPICS_2") %>');
			document.ConfigurationForm.noPostingForHotTopics.focus();
			return;
		}
		else if (document.ConfigurationForm.categorizeThread.value.valueOf()>90)
		{
			alert('<%= OIDBRegistry.getValues("OI.CONF.THREAD_2") %>');
			document.ConfigurationForm.categorizeThread.focus();
			return;
		}
		else if (document.ConfigurationForm.noTopicToShowBookmark.value.valueOf()>5)
		{
			alert('<%= OIDBRegistry.getValues("OI.CONF.BOOKMARK_2") %>');
			document.ConfigurationForm.noTopicToShowBookmark.focus();
			return;
		}
		else if (document.ConfigurationForm.noOfDaysToConsiderLatestTopics.value.valueOf()>7)
		{
			alert('<%= OIDBRegistry.getValues("OI.CONF.LATESTTOPICS_2") %>');
			document.ConfigurationForm.noOfDaysToConsiderLatestTopics.focus();
			return;
		}
		else if (document.ConfigurationForm.sendRemiders.value.valueOf()>10)
		{
			alert('<%= OIDBRegistry.getValues("OI.CONF.SENDREMINDERS_2") %>');
			document.ConfigurationForm.sendRemiders.focus();
			return;
		}

		document.ConfigurationForm.hiddenAction.value=hiddenAction;
		document.ConfigurationForm.action=actionName;
		document.ConfigurationForm.submit();
	}
	function fnClear()
	{
		document.ConfigurationForm.reset();
	}
	function fnCallConfMessage(actionName,hiddenAction)
	{
		document.ConfigurationForm.hiddenAction.value=hiddenAction;
		document.ConfigurationForm.action=actionName;
		document.ConfigurationForm.submit();
	}
 </script>
 <DIV id=divCalendar style="VISIBILITY: hidden; POSITION: absolute; BACKGROUND-COLOR: white; layer-background-color: white"></DIV>
<html:form action="/adminConfiguration.do">


<jsp:include page="/jsp/common/oifm_admin_header.jsp" flush="true" />


<table width="98%" border="0" cellpadding="0"
		cellspacing="0">
 
<tr>
	<td class="TableHead">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
		<td class="Box">
			<table width="100%" border="0" cellpadding="1"
				cellspacing="1" bgcolor="white">
				<tr class="Table_head">
					<td colspan="4">Configuration</td>
 				</tr>
 					<tr>
						<td colspan="4" align="left" valign="top" class="Sub_head">Parameters</td>
					</tr>
							<tr>
								<td align="left" valign="top" nowrap class="heading_attributes" height="15">Time Out Warning</td>
								<td  class="heading_attributes" >
									<html:text name="ConfigurationForm" property="sessionTimeout" styleClass="Text_box" size="5" maxlength="2" /> mins before Session expires
									<html:hidden name="ConfigurationForm" property="hiddenAction"/>
								</td>
								<td class="heading_attributes" >Survey Archive Period </td>
								<td class="heading_attributes" >
									<html:text name="ConfigurationForm" property="surveyArchivePeriod" styleClass="Text_box" size="5" maxlength="2" /> months 
								</td>
							</tr>
 							<tr>
								<td align="left" valign="top" nowrap class="heading_attributes"  height="15">Forum Archive Period </td>
								<td class="heading_attributes" >
									<html:text name="ConfigurationForm" property="forumArchivePeriod" styleClass="Text_box" size="5" maxlength="2" /> months 
								</td>
								<td class="heading_attributes" >Consultation Paper Archival period </td>
								<td class="heading_attributes" >
									<html:text name="ConfigurationForm" property="paperArchivePeriod" styleClass="Text_box" size="5" maxlength="2"/> months
								</td>
							</tr>
 							<tr>
								<td align="left" valign="top" nowrap class="heading_attributes"  height="15">Attachment size</td>
								<td class="heading_attributes" >
									<html:text name="ConfigurationForm" property="attachmentSize" styleClass="Text_box" size="5" maxlength="2"/> MB 
								</td>
								<td class="heading_attributes" >Categorise threads in last </td>
								<td class="heading_attributes" >
									<html:text name="ConfigurationForm" property="categorizeThread" styleClass="Text_box" size="5" maxlength="2"/> days
								</td>
							</tr>
 							<tr>
								<td align="left" valign="top" nowrap class="heading_attributes"  height="15">No of posting required<br> for hot topics </td>
								<td class="heading_attributes" >
									<html:text name="ConfigurationForm" property="noPostingForHotTopics" styleClass="Text_box" size="5" maxlength="2"/>
								</td>
								<td class="heading_attributes" valign="middle" align="left" > 
									Enable alert Admin 
								</td>
								<td class="heading_attributes" valign="middle" align="left" >
									<html:checkbox name="ConfigurationForm" property="alertAdmin"></html:checkbox>
								</td>
							</tr>
 							<tr>
								<td align="left" valign="top" nowrap class="heading_attributes"  height="15">No of  topics to <br> show bookmark list </td>
								<td class="heading_attributes" >
									<html:text name="ConfigurationForm" property="noTopicToShowBookmark" styleClass="Text_box" size="5" maxlength="2" />
								</td>
								<td class="heading_attributes" valign="middle" align="left" > 
									Enable Personal Message ( PM ) 
								</td>
								<td class="heading_attributes" valign="middle" align="left" >
									<html:checkbox name="ConfigurationForm" property="personalMessage"></html:checkbox>
								</td>
							</tr>
 							<tr>
								<td class="heading_attributes" height="15"> No of days to consider<br> latest topics </td>
								<td class="heading_attributes">
									<html:text name="ConfigurationForm" property="noOfDaysToConsiderLatestTopics" styleClass="Text_box" size="5" maxlength="2" />
								</td>
								<td class="heading_attributes" > ASM Reminder Frequency </td>
								<td class="heading_attributes" >
									
									 <html:text name="ConfigurationForm" property="sendRemiders" styleClass="Text_box" size="5" maxlength="2"/> day(s)
								</td>
							</tr> 
							<tr>
								<td class="heading_attributes">Show home page announcement </td>
								<td class="heading_attributes" align="right">
									From <html:text name="ConfigurationForm" property="homeFrmDt" styleClass="Text_box" size="14" readonly="true"/>
									<a href="#" onClick="cal.select(document.forms['ConfigurationForm'].homeFrmDt,'homeFrmDt','dd-NNN-yyyy');return false;" name="toAnchor" id="toAnchor">
										<img src='<bean:message key="OIFM.docroot" />/images/img_calendar.gif' border="0" alt = "Calendar"></a>
									<br>
									To <html:text name="ConfigurationForm" property="homeToDt" styleClass="Text_box" size="14" readonly="true" />
									<a href="#" onClick="cal.select(document.forms['ConfigurationForm'].homeToDt,'homeToDt','dd-NNN-yyyy');return false;" name="toAnchor" id="toAnchor">
										<img src='<bean:message key="OIFM.docroot" />/images/img_calendar.gif' border="0" alt = "Calendar"></a>
								</td>
								<td class="heading_attributes">Show Forum announcement </td>
								<td class="heading_attributes" align="right">
									From <html:text name="ConfigurationForm" property="forumFrmDt" styleClass="Text_box" size="14" readonly="true"/>
									<a href="#" onClick="cal.select(document.forms['ConfigurationForm'].forumFrmDt,'forumFrmDt','dd-NNN-yyyy');return false;" name="toAnchor" id="toAnchor">
										<img src='<bean:message key="OIFM.docroot" />/images/img_calendar.gif' border="0" alt = "Calendar"></a>
									<br>
									To
									<html:text name="ConfigurationForm" property="forumToDt" styleClass="Text_box" size="14" readonly="true"/>
									<a href="#" onClick="cal.select(document.forms['ConfigurationForm'].forumToDt,'forumToDt','dd-NNN-yyyy');return false;" name="toAnchor" id="toAnchor">
										<img src='<bean:message key="OIFM.docroot" />/images/img_calendar.gif' border="0" alt = "Calendar"></a>
								</td>
							</tr> 
							<tr>
								<td colspan="4" align="left" valign="top" nowrap class="heading_attributes1"><hr style="color:#CCCCCC;height:1px"></td>
							</tr>
							<tr>
								<td colspan="4" align="left" valign="top" nowrap class="sub_head">Click the following links to configure messages</td>
							</tr>
							<tr>
								<td nowrap class="heading_attributes" colspan="4">
									<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
										<tr>
										<td class="heading_thread" align="left"><a
											class="heading_thread" href="#"
											onclick="javascript:fnSubmit('<bean:message key="OIFM.contextroot" />/asmCategoryAction.do','populate')" />ASM Categories</a>
										</td>
										</tr>
										<logic:present name="ConfigurationForm" property="arMessages" scope="request">
											<logic:iterate id="cf" name="ConfigurationForm" property="arMessages" scope="request" type="com.oifm.common.OIBAMessageConfiguration">
												<tr>
													<td class="heading_thread" align="left">
														<a class="heading_thread" href="#" onclick="javascript:fnSubmit('<bean:message key="OIFM.contextroot" />/adminConfiguration.do?messageCaption=<bean:write name="cf" property="messageCaption" />&messageTag=<bean:write name="cf" property="messageTag" />','populateMessage')">
														<bean:write name="cf" property="messageCaption"/></a>
													</td>
												</tr>
 											</logic:iterate>
										</logic:present>
															
									</table>
								</td>
							</tr>
							<tr>
								<td height="10" colspan="4" align="left" valign="middle">&nbsp;</td>
							</tr>
				<tr>
					<td colspan="4" height="10" align="left" valign="left">
							<a href="#" onclick="javascript:fnSubmit('<bean:message key="OIFM.contextroot" />/adminConfiguration.do','save')" >
								<img src='<bean:message key="OIFM.docroot" />/images/but_save.gif' border="0" alt = "Save"></a> 
							<a href='<bean:message key="OIFM.contextroot" />/jsp/admin/iofmAdminTemplate.jsp'>
								<img src='<bean:message key="OIFM.docroot" />/images/but_Cancel.gif' border="0" alt = "Cancel"></a>  
							<a href="#" onclick="javascript:fnClear()">
								<img src='<bean:message key="OIFM.docroot" />/images/but_reset.gif' border="0" alt = "Reset"></a>
					</td>
				</tr>

 					</table>
				</td>
			</tr>
		</table>
	</td>
	</tr>
</table>

	<script language="javascript">
	</script>
</html:form>

<%
}catch(Exception e){e.getMessage();}
%>