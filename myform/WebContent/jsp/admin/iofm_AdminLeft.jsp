<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
	
	<style type="text/css">
	<!--
		.clicked{background-color: blue;} 

		.style1 {font-size: 9pt}
		.style3 {font-size: 10}
		body 
		{
			
		}
	-->
	</style>
	<script language="JavaScript" type="text/JavaScript">
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
		}

		function fnLeftAdminSubmit(actionName,hiddenAction)
		{
			document.leftForm.hiddenAction.value=hiddenAction;
			document.leftForm.action=actionName;
			document.leftForm.submit();
		}

function changeStyle(t)
{
	for(i=1;i<18;i++)
	{
		e = document.getElementById(t);
		if(i==t)
			e.className = "clicked";
		else
			e.className = "";		
	}	
}
		//-->
	</script>
	<div align="center">
		<table width="100%"  border="0" cellspacing="0" cellpadding="0" align="right" >
			<tr height="20">
			<td  width="3%"></td>
			<td class="orange">  </td>
			</tr>
			<tr>
			<td width="3%"></td>
				<td width="135" align="left" valign="top" class="Blue">
					<table width="127%"  border="0" cellspacing="0" cellpadding="0" >
						<tr>
							<td height="190" colspan="3">
								<table  border="1" cellpadding="0" cellspacing="0">
									<!--tr>
										<td height="15" colspan="2" >&nbsp;</td>
									</tr-->
									<tr >
										<% boolean flag=false; %>
										<td height="20" colspan="2"  valign="top" class="Table_head" >
 											<logic:present name="functionList" scope="session">
											<table width="100%">
												<logic:iterate id="fnList" name="functionList" scope="session">
 													<logic:match name="fnList" value="MODPOST">
													<tr><td class="Table_head" >
													Moderation
													</td></tr><tr><td id="1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														<a class="Poll_body" href='<bean:message key="OIFM.contextroot" />/ThreadPostMod.do?hiddenAction=ThreadModList' >Threads </a>
														</td></tr><tr><td id="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  														<a class="Poll_body" href='<bean:message key="OIFM.contextroot" />/ThreadPostMod.do?hiddenAction=PostsModList'  >Postings</a>
														</td></tr>
											        </logic:match>														
													<% 
														if (flag == false && (fnList.equals("MTNCTBD") || fnList.equals("MTNTOPI"))) 
														{
															flag=true;
													%>
													 <tr><td>   
														<a class="Poll_body"  href="#" onclick="javascript:fnLeftAdminSubmit('<bean:message key="OIFM.contextroot" />/adminForumListing.do','populate');" >Category/Board</a></td></tr>
													<!-- /*Added/Modified by Aik Mun @ Jan 14, 2009*/ -->
													<tr><td id="3">   
														<a class="Poll_body"  href='<bean:message key="OIFM.contextroot" />/adminForumMoveThreadAction.do' >Move Thread</a></td></tr> 
													<%
														}
													%>
												
													<logic:match name="fnList" value="MNTPOLL">
													 <tr><td id="3">   
														<a class="Poll_body"  href='<bean:message key="OIFM.contextroot" />/Poll.do' >Poll</a></td></tr>   
											        </logic:match>
													<logic:match name="fnList" value="MNTCLPP">
													 <tr><td id="4">   
														<a class="Poll_body"  href="#" onclick="javascript:fnLeftAdminSubmit('<bean:message key="OIFM.contextroot" />/consultListingAction.do','divisionList');">Consultation Paper</a>
													</td></tr>    
											        </logic:match>
													<logic:match name="fnList" value="MNTSURV">
													 <tr><td id="5">   
														<a class="Poll_body"  href='<bean:message key="OIFM.contextroot" />/SurveyAdmin.do?id=<%= Math.random() %>' >Survey</a>
													</td></tr> 
											        </logic:match>
													<logic:match name="fnList" value="MTNCTBD">
													 <tr><td>   
														<a class="Poll_body"  href='<bean:message key="OIFM.contextroot" />/jsp/common/iofm_underConstructionAdmin.jsp' > </a>
														</td></tr><tr><td>   
														<a class="Poll_body"  href='<bean:message key="OIFM.contextroot" />/jsp/common/iofm_underConstructionAdmin.jsp' > </a>
														</td></tr>    
											        </logic:match>
													<logic:match name="fnList" value="MNTROLE">
													 <tr><td>   
														<a class="Poll_body"  href='<bean:message key="OIFM.contextroot" />/Roles.do'   >Roles</a></td></tr> 
											        </logic:match>
													<logic:match name="fnList" value="MNTPROF">
													<tr><td id="6">   
														<a class="Poll_body"  href='<bean:message key="OIFM.contextroot" />/UserProfile.do' >User Profile</a></td></tr>
											        </logic:match>
													<logic:match name="fnList" value="MNTGROP">
													<tr><td id="7">   
														<a class="Poll_body"  href='<bean:message key="OIFM.contextroot" />/Groups.do'  >User Groups</a>
													</td></tr>
											        </logic:match>
													<logic:match name="fnList" value="MNTCODE">
													 <tr><td id="8">   
														<a class="Poll_body"  href='<bean:message key="OIFM.contextroot" />/CodeMaster.do' >Code Master</a></td></tr>
											        </logic:match>
													<logic:match name="fnList" value="MNTCONF">
													<tr><td id="9">   
														<a class="Poll_body"  href="#" onclick="javascript:fnLeftAdminSubmit('<bean:message key="OIFM.contextroot" />/adminConfiguration.do','populate');" >Configuration</a>
													</td></tr>    
											        </logic:match>
													<logic:match name="fnList" value="MNTURNK">
													<tr><td id="10">   
														<a class="Poll_body"  href='<bean:message key="OIFM.contextroot" />/Ranking.do'  >User Ranking</a></td></tr>
											        </logic:match>
													<logic:match name="fnList" value="MNTAUDT">
													<tr><td id="11">   
														<a class="Poll_body" href='<bean:message key="OIFM.contextroot" />/AuditTrail.do' >Audit Trail</a></td></tr>
											        </logic:match>
											        <logic:match name="fnList" value="ASMREP">
											        	<tr><td id="12">   
									        			<a class="Poll_body"  href='<bean:message key="OIFM.contextroot" />/ASMEditor.do?hiddenAction=ADMIN'  > Editor's Note</a></td></tr>
														<tr><td id="13">   
									        			<a  class="Poll_body"  href='<bean:message key="OIFM.contextroot" />/ASMAbout.do?hiddenAction=ADMIN'  >About ASM</a></td></tr>
														<tr><td id="14">   
														<a  class="Poll_body"  href='<bean:message key="OIFM.contextroot" />/ASMActionCategoriesOfLetter.do?hiddenAction=ADMIN'  >Categories of ASM Letter's</a></td></tr>
														<tr><td id="15">   
									        			<a  class="Poll_body"  href='<bean:message key="OIFM.contextroot" />/ASMCategoriesOfEditorNotes.do?hiddenAction=ADMIN'  >Categories of Editor's Note</a></td></tr>
														<tr><td id="16">   
									        			<a class="Poll_body"  href="#" onclick="javascript:fnLeftAdminSubmit('<bean:message key="OIFM.contextroot" />/ASMReplyRedirect.do?','AdminPage')" >Reply/Redirect</a></td></tr>
														<tr><td id="17">   
									       				<a class="Poll_body"  href='<bean:message key="OIFM.contextroot" />/ASMManagement.do?hiddenAction=ADMIN'>Senior Management</a></td></tr>
														<tr><td id="18">   
									       			 	<a class="Poll_body"  href='<bean:message key="OIFM.contextroot" />/ASMAnnouncement.do' >Announcement</a></td></tr>
														<tr><td id="19">   
									        			<a class="Poll_body"  href="#" onclick="javascript:fnLeftAdminSubmit('<bean:message key="OIFM.contextroot" />/ASMReport.do?','AdminPage')" >ASM Reports</a></td></tr> 
											        </logic:match>	
											        				
											        <!-- commented by K.K.Kumaresan on June 29,2009 to hide the blog module										        
											        <logic:match name="fnList" value="MNTBLOG">
											        	<tr><td class="Table_head" >Blog</td></tr>
											        	<tr><td id="1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														<a class="Poll_body" href="#" onclick="javascript:fnLeftAdminSubmit('<bean:message key="OIFM.contextroot" />/BlogAdmin.do','CreateBlog')" >Create Blog </a></td></tr>
														<tr><td id="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  														<a class="Poll_body" href="#" onclick="javascript:fnLeftAdminSubmit('<bean:message key="OIFM.contextroot" />/BlogAdmin.do','ManageBlog')" >Manage Blog</a></td></tr>
  														<tr><td id="3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  														<a class="Poll_body" href="#" onclick="javascript:fnLeftAdminSubmit('<bean:message key="OIFM.contextroot" />/BlogAdmin.do','BlogSettings')" >Settings</a></td></tr>
											        </logic:match>
											        -->
											        
										        </logic:iterate>
												</table>
									        </logic:present>
									        
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		</div>
		<form name="leftForm" method="post">
			<input type="hidden" name="hiddenAction">
		</form>
		