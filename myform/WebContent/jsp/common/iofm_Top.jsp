<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="com.oifm.search.OISearchConstants,com.oifm.blog.OIBlogConstants" %>
	<head>
		<title>My Forum, Ministry Of Education</title>
		<META http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
		<META HTTP-EQUIV="Expires" CONTENT="0">
		<style type="text/css">
			<!--
			@import url("<bean:message key="OIFM.docroot" />/css/iofs.css");
			.style2 
			{
				color: #FFFFFF;
				font-weight: bold;
			}
			-->
		</style>
		<script>
		//To set the window parent flag
		var basePageFlag= true
		</script>
		
		<script language="JavaScript" type="text/JavaScript">
			var objUsrSltWin;
			var objUsrGrpWin;

			<!--
			function MM_swapImgRestore() 
			{ //v3.0
				var i,x,a=document.MM_sr; 
				for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) 
					x.src=x.oSrc;
			}

			function MM_preloadImages() 
			{ //v3.0
				var d=document; 
				if(d.images)
				{ 
					if(!d.MM_p) 
						d.MM_p=new Array();
					var i,j=d.MM_p.length,a=MM_preloadImages.arguments; 
					for(i=0; i<a.length; i++)
						if (a[i].indexOf("#")!=0)
						{ 
							d.MM_p[j]=new Image; 
							d.MM_p[j++].src=a[i];
						}
				}
			}

			function MM_findObj(n, d) 
			{ //v4.01
				var p,i,x;  
				if(!d) 
					d=document; 
				if((p=n.indexOf("?"))>0&&parent.frames.length) 
				{
					d=parent.frames[n.substring(p+1)].document; 
					n=n.substring(0,p);
				}
				if(!(x=d[n])&&d.all) 
					x=d.all[n]; 
				for (i=0;!x&&i<d.forms.length;i++) 
					x=d.forms[i][n];
				for(i=0;!x&&d.layers&&i<d.layers.length;i++) 
					x=MM_findObj(n,d.layers[i].document);
				if(!x && d.getElementById) 
					x=d.getElementById(n); 
				return x;
			}

			function MM_swapImage() 
			{ //v3.0
				var i,j=0,x,a=MM_swapImage.arguments; 
				document.MM_sr=new Array; 
				for(i=0;i<(a.length-2);i+=3)
					if ((x=MM_findObj(a[i]))!=null)
					{
						document.MM_sr[j++]=x; 
						if(!x.oSrc) 
							x.oSrc=x.src; 
						x.src=a[i+2];
					}
			}//-->
			 
			var help_window;
			function fnModal()
			{ 
				try{
				if(!(!help_window ||  help_window.closed))
				{ 
					inside = 1;
					help_window.focus(); 
				}
				} catch (err) {}
			}
	
			function fnCallModal()
			{
				try{
				if(help_window)
				{
					window.setTimeout("fnModal()",1);
				}
				}
				catch(err){}
			}

			function fnClosePopup()
			{
				if(help_window!=null)
				{
					help_window.close();
				}
			}
			function fnTopAdminSubmit(actionName,hiddenAction)
			{
				document.topForm.hiddenAction.value=hiddenAction;
				document.topForm.action=actionName;
				document.topForm.submit();
			}

			function fnSearchSubmit() 
			{
				if (checkBlank(document.SimpleSearchForm.strSearchString, 'Search String')) 
				{
					if (document.SimpleSearchForm.hiddenAction.value == '' || document.SimpleSearchForm.hiddenAction.value.length == 0)
						document.SimpleSearchForm.hiddenAction.value = '<%= OISearchConstants.SEARCH_FORUM %>';
					document.SimpleSearchForm.pageNo.value = '1';
					document.SimpleSearchForm.submit();
				}
			}

			function fnSearchSubmitType(hidAction) 
			{
				if (checkBlank(document.SimpleSearchForm.strSearchString, 'Search String')) 
				{
					document.SimpleSearchForm.hiddenAction.value = hidAction;
					document.SimpleSearchForm.pageNo.value = '1';
					document.SimpleSearchForm.submit();
				}
			}

			function fnSearchSubmitPage(pageNo) 
			{
				if (checkBlank(document.SimpleSearchForm.strSearchString, 'Search String')) 
				{
					document.SimpleSearchForm.pageNo.value = pageNo;
					document.SimpleSearchForm.submit();
				}
			}
		</script>
		<SCRIPT LANGUAGE="JavaScript" src='<bean:message key="OIFM.docroot" />/js/Common.js' ></SCRIPT>
		<div style="display:none">
		<IFRAME width="0" height="0" name="raj" src='<bean:message key="OIFM.contextroot" />/jsp/common/refresh.jsp'  scrolling="no">
		</IFRAME>
		</div>
	</head>
	<%	
		response.setHeader("Cache-Control","no-cache"); //HTTP 1.1	
		response.setHeader("Pragma","no-cache"); //HTTP 1.0	
		response.setDateHeader ("Expires", 0); //prevents caching at the proxy server	
	%>	
<form name="topForm" method="post">
		<input type="hidden" name="hiddenAction">
</form>
<div align="center">
  <table width="902" border="0" cellpadding="0" cellspacing="0" background="<bean:message key="OIFM.docroot" />/images/Top_Fade.gif">
    <tr>
      <td><div align="center">
        <table width="0" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td class="Top_bk"><table width="857" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="211" rowspan="3"><a href='<bean:message key="OIFM.contextroot" />/home.do'><img src="<bean:message key="OIFM.docroot" />/images/my_forum_logo.gif" width="211" height="82" border="0" alt="My Forum home"></a></td>
                <td colspan="2" valign="top"><table width="97%"  border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td width="4%">&nbsp;</td>
 				
					<td width="67.8%" align="right" valign="top"><a href='<%=request.getContextPath()%>/jsp/common/PrinciplesOfInternalComm.jsp' class="Poll_body" alt="Click to read Principles Of Internal Communication">Principles Of Internal Communication</a></td>
                    <td width="2%"><img src="<bean:message key="OIFM.docroot" />/images/break1.gif" width="11" height="18"></td>
                    
					<logic:iterate id="fnList" name="functionList" scope="session"><logic:match name="fnList" value="ADMINTB">
					<td width="7%" align="right" valign="top">
					<a href='<bean:message key="OIFM.contextroot" />/jsp/admin/iofmAdminTemplate.jsp' class="Poll_body" alt="Click to browse My Forum Administration Modules">Admin</a></td>
                    <td width="2%"><img src="<bean:message key="OIFM.docroot" />/images/break1.gif" width="11" height="18"></td>
					</logic:match>
					</logic:iterate>
					<td width="8%" nowrap align="right" valign="top"><a href='<bean:message key="OIFM.contextroot" />/UserProfile.do?hiddenAction=user' class="Poll_body" alt="Update yor Profile with My Forum">My Profile</a></td>
                    <td width="2%"><img src="<bean:message key="OIFM.docroot" />/images/break1.gif" width="11" height="18"></td>
                    <td width="5%" align="right" valign="top"><a href='<bean:message key="OIFM.contextroot"/>/jsp/common/iofmCommonSearchTemplate.jsp?pageName=faq' class="Poll_body" alt="Read the FAQs for My Forum">FAQs</a></td>
                    <td width="2%"><img src="<bean:message key="OIFM.docroot" />/images/break1.gif" width="11" height="18"></td>
                    <td width="7%" align="right" valign="top"><a href="javascript:fnTopAdminSubmit('<bean:message key="OIFM.contextroot" />/loginAction.do','logout');" class="Poll_body" alt="Logout from My Forum">Logout</a></td>
                  </tr>
                </table></td>
                </tr>
              <tr>
                <td width="635" class="small_text"> <div align="right" >
                	<logic:present name="username" scope="session">
						<font  size="1"><i>
							Welcome,  
							<bean:write name="username" scope="session"/>
							<logic:present name="roleName" scope="session">
								<br>(<bean:write name="roleName" scope="session"/>)
							</logic:present>
							<br>
							<b><%= com.oifm.utility.OISessionCounter.getActiveSessions() %> Users online </b>
						</i></font>
					</logic:present>
                </td>
                <td width="11">&nbsp;</td>
              </tr>
              
              <tr>
                <td><table width="98%"  border="0" align="right" cellpadding="0" cellspacing="0">
 
                </table></td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td colspan="3"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td width="16">&nbsp;</td>
                    <td width="42" nowrap>
                    <logic:match parameter="pageName" value="Home">
	                    <a href='<bean:message key="OIFM.contextroot" />/home.do' class="Menu_highlight" alt="Click to visit My Forum Home">Home</a>
                    </logic:match>
                    <logic:notMatch parameter="pageName" value="Home">
    	                <a href='<bean:message key="OIFM.contextroot" />/home.do' class="Menu_text" alt="Click to visit My Forum Home ">Home</a>
    	             </logic:notMatch>
                    </td>                    
                    <td width="22"  align="center"><img src="<bean:message key="OIFM.docroot" />/images/break.gif"></td>
                    <td width="120" nowrap>
                    <logic:match parameter="pageName" value="Discussion">
	                    <a href="#" onclick="javascript:fnTopAdminSubmit('<bean:message key="OIFM.contextroot" />/WebForumListingAction.do','populate');" class="Menu_highlight"  alt="Click to visit My Forum Discussion Forum ">Discussion Forum</a>
                    </logic:match>
                    <logic:notMatch parameter="pageName" value="Discussion">
    	                <a href="#" onclick="javascript:fnTopAdminSubmit('<bean:message key="OIFM.contextroot" />/WebForumListingAction.do','populate');" class="Menu_text" alt="Click to visit My Forum Discussion Forum">Discussion Forum</a>
    	             </logic:notMatch>
                    </td>
                    <td width="22" align="center"><img src="<bean:message key="OIFM.docroot" />/images/break.gif"></td>
                    <td width="167" nowrap>
                    <logic:match parameter="pageName" value="ASM">
                     <a  href='<bean:message key="OIFM.contextroot" />/ASMEditor.do' class="Menu_highlight" alt="Click to visit My Forum Ask Senior Management">Ask Senior Management</a>
                    </logic:match>
                    <logic:notMatch parameter="pageName" value="ASM">
    	                 <a  href='<bean:message key="OIFM.contextroot" />/ASMEditor.do' class="Menu_text"  alt="Click to visit My Forum Ask Senior Management">Ask Senior Management</a>
    	             </logic:notMatch>
                    </td>
                    <td width="22" nowrap align="center"><img src="<bean:message key="OIFM.docroot" />/images/break.gif"></td>
                    <td width="130" nowrap>
                    <logic:match parameter="pageName" value="Consultation">
	                    <a href="#" onclick="javascript:fnTopAdminSubmit('<bean:message key="OIFM.contextroot" />/webConsultListingAction.do','populate');" class="Menu_highlight" alt="Click to visit My Forum Consultation Paper">Consultation Paper </a>
                    </logic:match>
                    <logic:notMatch parameter="pageName" value="Consultation">
    	                <a href="#" onclick="javascript:fnTopAdminSubmit('<bean:message key="OIFM.contextroot" />/webConsultListingAction.do','populate');" class="Menu_text"  alt="Click to visit My Forum Consultation Paper">Consultation Paper </a>
    	             </logic:notMatch>
    	             </td>
                    <td width="23" nowrap align="center"><img src="<bean:message key="OIFM.docroot" />/images/break.gif"></td>
                    <td width="50" nowrap>
                    <logic:match parameter="pageName" value="Survey">
	                    <a href='<bean:message key="OIFM.contextroot" />/UserSurvey.do?id=<%= Math.random() %>' class="Menu_highlight"  alt="Click to visit My Forum Survey">Survey</a>
                    </logic:match>
                    <logic:notMatch parameter="pageName" value="Survey">
    	                <a href='<bean:message key="OIFM.contextroot" />/UserSurvey.do?id=<%= Math.random() %>' class="Menu_text"  alt="Click to visit My Forum Survey">Survey</a>
    	             </logic:notMatch>
    	             </td>
    	             
    	           <!-- commented by K.K.Kumaresan on June 29,2009 to hide the blog module										          
					<td width="23" nowrap align="center"><img src="<bean:message key="OIFM.docroot" />/images/break.gif"></td>
                    <td width="50" nowrap>
                    <logic:match parameter="pageName" value="Blog">
	                    <a href='<bean:message key="OIFM.contextroot" />/BlogHome.do?id=<%= Math.random() %>&hiddenAction=<%=OIBlogConstants.BLOG_MODULE_HOME_PAGE%>' class="Menu_highlight"  alt="Click to visit My Forum Blog">Blog</a>
                    </logic:match>
                    <logic:notMatch parameter="pageName" value="Blog">
    	                <a href='<bean:message key="OIFM.contextroot" />/BlogHome.do?id=<%= Math.random() %>&hiddenAction=<%=OIBlogConstants.BLOG_MODULE_HOME_PAGE%>' class="Menu_text"  alt="Click to visit My Forum Blog">Blog</a>
    	             </logic:notMatch>
    	             </td>
					-->

                   <td width="27%"> 
                      <html:form action="/SimpleSearch.do" method="post" onsubmit="fnSearchSubmit();return false;">
					  <table width="100%" align="right" border="0"><tr><td align="right">
					<html:hidden property="hiddenAction" />
					<html:hidden property="pageNo" />
                       <html:text property="strSearchString" styleClass="Search_box" onkeypress="event.cancelBubble=true;" onkeydown="event.cancelBubble=true;"/>
					   </td><td width="3%">
                     <a href="#" onclick="fnSearchSubmit();"> <img src="<bean:message key="OIFM.docroot" />/images/search.gif" width="15" height="15" border="0" alt="Click to Search in My Forum"></a> 
					   </td><td width="3%">
                      <a href='#' onclick="javascript:fnTopAdminSubmit('<bean:message key="OIFM.contextroot" />/AdvancedSearch.do','populate');"><img src="<bean:message key="OIFM.docroot" />/images/ad_search.gif" width="15" height="15" border="0" alt="Click to make a Advanced Search in My Forum"></a>
					</td></tr></table>

					 </html:form>
					 </td>
 
                  </tr>
 
                </table></td>
                </tr>
            </table></td>
          </tr>
        </table>