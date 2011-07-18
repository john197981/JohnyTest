<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="org.apache.struts.util.LabelValueBean" %>
<%@ page import="com.oifm.forum.OIForumConstants" %>
<%@ page import="com.oifm.utility.OIDBRegistry" %>

<link href='<bean:message key="OIFM.docroot" />/css/iofs.css' rel="stylesheet" type="text/css">
<script language="javascript" src='<bean:message key="OIFM.docroot" />/js/Common.js'></script>
<script language="javascript" src='<bean:message key="OIFM.docroot" />/js/validate.js'></script>
<script language="javascript">
	function doPopup1(value1,x)
	{
		cmdObj=document.getElementById(x);
		if (value1=="From Last")
		{
			cmdObj.style.visibility="visible";
		}
		else
		{
			cmdObj.style.visibility="hidden"
		}
	}
	function fnClear()
	{
		document.AdvancedSearchForm.reset();
	}
	function fnSubmit(actionName,hiddenAction)
	{
		if (Trim(document.AdvancedSearchForm.key.value)=="")
		{
			alert('<%= OIDBRegistry.getValues("OI.ADSEARCH.KEY") %>');
			document.AdvancedSearchForm.key.focus();
			return;
		}
		// commented by K.K.Kumaresan on June 29,2009 to hide the blog module										          
		//if (document.AdvancedSearchForm.inForum.checked==false && document.AdvancedSearchForm.inPaper.checked==false && document.AdvancedSearchForm.inSurvey.checked==false && document.AdvancedSearchForm.inBlog.checked==false)
		if (document.AdvancedSearchForm.inForum.checked==false && document.AdvancedSearchForm.inPaper.checked==false && document.AdvancedSearchForm.inSurvey.checked==false && document.AdvancedSearchForm.inASM.checked==false)
		{
			alert('<%= OIDBRegistry.getValues("OI.ADSEARCH.MODULE") %>');
			document.AdvancedSearchForm.inForum.focus();
			return;
		}
		if(document.AdvancedSearchForm.inForum.checked && Trim(document.AdvancedSearchForm.moduleFlag.value)=="")
			document.AdvancedSearchForm.moduleFlag.value="F";
		if(document.AdvancedSearchForm.inPaper.checked && Trim(document.AdvancedSearchForm.moduleFlag.value)=="")
			document.AdvancedSearchForm.moduleFlag.value="P";
		if(document.AdvancedSearchForm.inSurvey.checked && Trim(document.AdvancedSearchForm.moduleFlag.value)=="")
			document.AdvancedSearchForm.moduleFlag.value="S";
		if(document.AdvancedSearchForm.inASM.checked && Trim(document.AdvancedSearchForm.moduleFlag.value)=="")
			document.AdvancedSearchForm.moduleFlag.value="A";
		// commented by K.K.Kumaresan on June 29,2009 to hide the blog module										          
		/*if(document.AdvancedSearchForm.inBlog.checked && Trim(document.AdvancedSearchForm.moduleFlag.value)=="")
			document.AdvancedSearchForm.moduleFlag.value="B";*/
		document.AdvancedSearchForm.hiddenAction.value=hiddenAction;
		document.AdvancedSearchForm.action=actionName;
		document.AdvancedSearchForm.submit();
	}
	function fnSubmitUser(actionName,hiddenAction)
	{

		if (Trim(document.AdvancedSearchForm.searchByUser.value)=="")
		{
			alert('<%= OIDBRegistry.getValues("OI.ADSEARCH.USER") %>');
			document.AdvancedSearchForm.searchByUser.focus();
			return;
		}
		document.AdvancedSearchForm.inPaper.checked=false;
		document.AdvancedSearchForm.inSurvey.checked=false;
		document.AdvancedSearchForm.inASM.checked=false;
		document.AdvancedSearchForm.moduleFlag.value="F";
		document.AdvancedSearchForm.inForum.checked=true;
		document.AdvancedSearchForm.hiddenAction.value=hiddenAction;
		document.AdvancedSearchForm.action=actionName;
		document.AdvancedSearchForm.submit();
	}
</script>



        <table width="857" border="0" cellspacing="0" cellpadding="0">
<html:form action="/AdvancedSearch.do">
          <tr>
            <td class="Orange_fade">Advanced Search</td>
          </tr>
        </table>

         <table width="857" border="0" cellspacing="0" cellpadding="0">
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
								<td class="table_head" colspan="3" >Search Criteria </td>
							</tr>
							<tr>
								<td class="heading_thread" width="25%">Search Key Word(s) <font color="red">*</font></td>
								<td class="Body_detail_text" width="25%">
									<html:text property="key" styleClass="Text_box" size="30"></html:text>
								</td>
								<td class="Body_detail_text"></td>
							</tr>
							<tr>
								<td height="74" class="heading_thread">in <font color="red">*</font> </td>
								<td valign="top" class="box">
									<table cellpadding="0" cellspacing="0" >
										<tr>
											<td class="heading_attributes">
												<html:checkbox property="inForum" value="on"></html:checkbox>
												Discussion Forum
											</td>
										</tr>
										<tr>
											<td class="heading_attributes">
												<html:checkbox property="inPaper" value="on"></html:checkbox>
												Consultation Paper
											</td>
										</tr>
										<tr>
											<td class="heading_attributes">
												<html:checkbox property="inSurvey" value="on"></html:checkbox>
												Survey 
											</td>
										</tr>
										
										<!-- commented by K.K.Kumaresan on June 29,2009 to hide the blog module										          
										<tr>
											<td class="heading_attributes">
												<html:checkbox property="inBlog" value="on"></html:checkbox>
												Blog 
											</td>
										</tr>
										-->
										
										<tr>
											<td class="heading_attributes">
												<html:checkbox property="inASM" value="on"></html:checkbox>
												ASM 
											</td>
										</tr>
										
									</table>
								</td>
								<td class="body_detail_text" style="padding: 1"></td>
							</tr>
							<tr>
								<td class="heading_thread"> Search By </td>
								<td class="body_detail_text">
									<bean:define id="arSearch" name="AdvancedSearchForm" property="arSearchBy" />
									<html:select property="searchBy" styleClass="Text">
										<html:options collection="arSearch" property="value" labelProperty="label" />
									</html:select>
								</td>
								<td class="body_detail_text" ></td>
							</tr>
							<tr>
								<td class="body_detail_text" > </td>
								<td class="body_detail_text" colspan="2" ></td>
							</tr>
  			 
				<tr>
					<td align="left" valign="top" class="table_head" colspan="3">
							Search Option </td>
					</tr>
					<tr>
						<td class="Sub_head" colspan="3">Discussion Forum</td>
					</tr>
							<tr>
								<td class="heading_thread" width="25%">Find Threads with </td>
								<td class="heading_thread" width="15%">
									<bean:define id="arfindThreads" name="AdvancedSearchForm" property="arFindThreads" />
									<html:select property="findThreads" styleClass="Text">
										<html:options collection="arfindThreads" property="value" labelProperty="label"  />
									</html:select>
								</td>
								<td align="top" class="heading_thread">
									<html:text property="findThreadsPost" styleClass="Text_box" size="6"></html:text>
									 Posts
								</td>
							</tr>
							<tr>
								<td class="heading_thread"> Search By </td>
								<td class="heading_thread">
									<bean:define id="arforumSearchBy" name="AdvancedSearchForm" property="arForumSearchBy" />
									<html:select property="forumSearchBy" styleClass="Text" onchange="doPopup1(document.AdvancedSearchForm.forumSearchBy.options[document.AdvancedSearchForm.forumSearchBy.selectedIndex].text,'dropin');">
										<html:options collection="arforumSearchBy" property="value" labelProperty="label"  />
									</html:select>
								</td>
								<td align="top" class="body_detail_text">
									<DIV id=dropin style="VISIBILITY: hidden; height: 21px; width: 94px;">
										<html:text property="forumSearchByDays" styleClass="Text_box" size="6"></html:text>
										 days
									</DIV>
								</td>
							</tr>
							<tr>
								<td colspan="3" class="body_detail_text"> </td>
 							</tr>
							<tr>
								<td class="Sub_head" colspan="3">Consultation Paper</td>
							</tr>
							<tr>
								<td class="heading_thread"> Posted on </td>
								<td class="body_detail_text">
									<bean:define id="arpaperSearchBy" name="AdvancedSearchForm" property="arPaperSearchBy" />
									<html:select property="paperSearchBy" styleClass="Text" onchange="doPopup1(document.AdvancedSearchForm.paperSearchBy.options[document.AdvancedSearchForm.paperSearchBy.selectedIndex].text,'dropin1');">
										<html:options collection="arpaperSearchBy" property="value" labelProperty="label"  />
									</html:select>
								</td>
								<td align="top" class="body_detail_text">
									<DIV id=dropin1 style="VISIBILITY: hidden; height: 21px; width: 99px;">
										<html:text property="paperSearchByDays" styleClass="Text_box" size="6"></html:text>
										 days 
									</DIV>
								</td>
							</tr>
							<tr>
								<td class="body_detail_text" colspan="3"> </td>
 							</tr>
							<tr>
								<td class="Sub_head" colspan="3">Survey Paper</td>
							</tr>
							<tr>
								<td class="heading_thread"> Posted on </td>
								<td class="body_detail_text">
									<bean:define id="arsurveySearchBy" name="AdvancedSearchForm" property="arPaperSearchBy" />
									<html:select property="surveySearchBy" styleClass="Text" onchange="doPopup1(document.AdvancedSearchForm.surveySearchBy.options[document.AdvancedSearchForm.surveySearchBy.selectedIndex].text,'dropin2');">
										<html:options collection="arsurveySearchBy" property="value" labelProperty="label"  />
									</html:select>
								</td>
								<td align="top" class="body_detail_text">
									<DIV id=dropin2 style="VISIBILITY: hidden; height: 21px; width: 99px;">
										<html:text name="AdvancedSearchForm" property="surveySearchByDays" styleClass="Text_box" size="6"></html:text>
										 days 
									</DIV>
								</td>
							</tr>
							<tr>
								<td class="body_detail_text" colspan="3"> </td>
 							</tr>
 							
 							 							
 						<!-- commented by K.K.Kumaresan on June 29,2009 to hide the blog module										          
							<tr>
								<td class="Sub_head" colspan="3">Blog</td>
							</tr>
							<tr>
								<td class="heading_thread"> Posted on </td>
								<td class="body_detail_text">
									<bean:define id="arblogSearchBy" name="AdvancedSearchForm" property="arBlogSearchBy" />
									<html:select property="blogSearchBy" styleClass="Text" onchange="doPopup1(document.AdvancedSearchForm.blogSearchBy.options[document.AdvancedSearchForm.blogSearchBy.selectedIndex].text,'dropin3');">
										<html:options collection="arblogSearchBy" property="value" labelProperty="label"  />
									</html:select>
								</td>
								<td align="top" class="body_detail_text">
									<DIV id=dropin3 style="VISIBILITY: hidden; height: 21px; width: 99px;">
										<html:text name="AdvancedSearchForm" property="blogSearchByDays" styleClass="Text_box" size="6"></html:text>
										 days 
									</DIV>
								</td>
							</tr>
							<tr>
								<td colspan="3" class="body_detail_text"> </td>
 							</tr>
 						-->	
 						
 						<tr>
								<td class="Sub_head" colspan="3">ASM</td>
						</tr>
							<tr>
								<td class="heading_thread"> Posted on </td>
								<td class="body_detail_text">
									<bean:define id="arblogSearchBy" name="AdvancedSearchForm" property="arBlogSearchBy" />
									<html:select property="asmSearchBy" styleClass="Text" onchange="doPopup1(document.AdvancedSearchForm.asmSearchBy.options[document.AdvancedSearchForm.asmSearchBy.selectedIndex].text,'dropin3');">
										<html:options collection="arblogSearchBy" property="value" labelProperty="label"  />
									</html:select>
								</td>
								<td align="top" class="body_detail_text">
									<DIV id=dropin3 style="VISIBILITY: hidden; height: 21px; width: 99px;">
										<html:text name="AdvancedSearchForm" property="asmSearchByDays" styleClass="Text_box" size="6"></html:text>
										 days 
									</DIV>
								</td>
							</tr>
							<tr>
								<td colspan="3" class="body_detail_text"> </td>
 							</tr>
 
				<tr>
					<td align="left" colspan="3" class="body_detail_text">
						<p align="center">&nbsp;
							<a href="#" onclick="javascript:fnSubmit('<bean:message key="OIFM.contextroot" />/AdvancedSearch.do','search');">
								<img src='<bean:message key="OIFM.docroot" />/images/btn_Search.gif' border="0" 
								alt = "Search"></a> 
							<a href="#" onclick="javascript:fnClear()">
								<img src='<bean:message key="OIFM.docroot" />/images/btn_Clear.gif' border="0" alt = "Reset"></a>
							<a href='<bean:message key="OIFM.contextroot" />/home.do'>
								<img src='<bean:message key="OIFM.docroot" />/images/btn_Cancel.gif' border="0" alt = "Cancel"></a>
						</p>
					</td>
				</tr>
				<tr>
					<td colspan="3" ></td>
				</tr>
				<tr>
						<td colspan="3" class="table_head" >Search by User  </td>
					</tr>
					<tr>
						<td class="heading_thread" colspan="3">
							<p align="left">Search all messages posted by user (Nick name) <font color="red">*</font>
								<html:text property="searchByUser" styleClass="Text_box" size="21"></html:text>
								<a href="#" onclick="javascript:fnSubmitUser('<bean:message key="OIFM.contextroot" />/AdvancedSearch.do','searchByUser');">
									<img src='<bean:message key="OIFM.docroot" />/images/btn_Search.gif' border="0" alt = "Search"></a>
							</p>
						</td>
					</tr>
 			</table>
			</td>

		</tr>
<tr height="40"><td colspan="3"></td></tr>
</table>

<jsp:include page="/jsp/common/oifm_footer.jsp" flush="true" />

 <html:hidden property="hiddenAction"/>
<html:hidden property="moduleFlag"/>
<script>
	doPopup1(document.AdvancedSearchForm.forumSearchBy.options[document.AdvancedSearchForm.forumSearchBy.selectedIndex].text,'dropin');
	doPopup1(document.AdvancedSearchForm.paperSearchBy.options[document.AdvancedSearchForm.paperSearchBy.selectedIndex].text,'dropin1');
	doPopup1(document.AdvancedSearchForm.surveySearchBy.options[document.AdvancedSearchForm.surveySearchBy.selectedIndex].text,'dropin2');
	//doPopup1(document.AdvancedSearchForm.asmSearchBy.options[document.AdvancedSearchForm.asmSearchBy.selectedIndex].text,'dropin4');
</script>
</html:form>