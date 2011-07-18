<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="com.oifm.poll.OIBVPoll" %>
<%@ page import="com.oifm.forum.OIForumConstants" %>
<%@ page import="com.oifm.survey.OISurveyConstants,java.util.Random" %>
<%@ page import="com.oifm.blog.OIBVBlogCommon" %>
<%@ page import="com.oifm.home.OIFormHome" %>

<style type=text/css>
	<!-- body { margin-left: 0px; margin-top: 0px; margin-right: 0px; margin-bottom: 0px; background-color: #DBECF7; }
	.style4 {font-size: 10px}
	.style5 {font-weight: bold; background-image: url(<bean:message key="OIFM.docroot" />/<bean:message key="OIFM.docroot" />/images/title_bk.gif); background-repeat: repeat-x; text-align: left; border-right-style: solid; border-bottom-style: solid; border-top-width: 1px; border-right-width: 1px; border-bottom-width: 1px; border-left-width: 1px; padding-right: 8px; padding-left: 8px; border-right-color: #FFFFFF; border-bottom-color: #FFFFFF; height: 20px; font-size: 12px; padding-top: 4px; border-top-style: solid; border-left-style: solid; border-top-color: #FFFFFF; border-left-color: #FFFFFF; font-family: Verdana;}
	--> 
</style>
<script language="javascript" src='<bean:message key="OIFM.docroot" />/js/Home.js'></script>
<script language="javascript">
	function fnSubmit(actionName,paperId,hiddenAction)
	{
		document.tempForm.paperId.value=paperId;
		document.tempForm.hiddenAction.value=hiddenAction;
		document.tempForm.action=actionName;
		document.tempForm.submit();
	}

	function fnSelectTags(){
			var strUrl = '/AddTags.do?hiddenAction=addTags&module=H';
			help_window  = window.open('<bean:message key="OIFM.contextroot" />'+strUrl,'SelectTagss','width=500,height=620,left=0,top=0,resizable=yes,scrollbars=yes');
			help_window.focus();
		}

</script>
<SCRIPT language=JavaScript>
	var timer=0;
	window.onerror = null;
	var bName = navigator.appName;
	var bVer = parseInt(navigator.appVersion);
	var NS4 = (bName == "Netscape" && bVer >= 4);
	var IE4 = (bName == "Microsoft Internet Explorer" && bVer >= 4);
	var NS3 = (bName == "Netscape" && bVer < 4);
	var IE3 = (bName == "Microsoft Internet Explorer" && bVer < 4);
	var blink_speed=1500;
	var i=0;
 
	if (NS4 || IE4) 
	{
		if (navigator.appName == "Netscape") 
		{
			layerStyleRef="layer.";
			layerRef="document.layers";
			styleSwitch="";
		}
		else
		{
			layerStyleRef="layer.style.";
			layerRef="document.all";
			styleSwitch=".style";
		}
	}

	//BLINKING
	function Blink(layerName)
	{
		if (NS4 || IE4) 
		{ 
			if(i%2==0)
			{
				eval(layerRef+'["'+layerName+'"]'+ styleSwitch+'.visibility="visible"');
			}
			else
			{
				eval(layerRef+'["'+layerName+'"]'+ styleSwitch+'.visibility="hidden"');
			}
		} 
		if(i<1)
		{
			i++;
		}
		else
		{
			i--;
		}
		if (timer < 20)
		{
			setTimeout("Blink('"+layerName+"')",blink_speed);
			timer = timer + 1;
		}
		else
		{
			if(document.getElementById("Table1").style.display == "")
				document.getElementById("Table1").style.display = "none";
			else 
				document.getElementById("Table1").style.display = "";
		}		
	}
	//  End -->
</SCRIPT>

<jsp:include page="/jsp/common/iofm_Top.jsp" flush="true">	
   <jsp:param name="pageName" value="Home" />
</jsp:include>
<%
	Cookie cookies[] = request.getCookies();
	for(int i = 0; i < cookies.length; i++ )
	{
		if (cookies[i].getName().equals("MyModule"))
		{
			//out.println(cookies[i].getValue());
		}
		if (cookies[i].getName().equals("MyID"))
		{
			//out.println(cookies[i].getValue());
		}
	}
%>
 <%
int nBannerCnt = 0;
int nBannerDisp = 0;

if(request.getAttribute("bannerCnt")!=null){
	nBannerCnt = Integer.parseInt(""+request.getAttribute("bannerCnt"));
	nBannerDisp = 1 + (int) (Math.random() * nBannerCnt);
 	//nBannerDisp=(new Random()).nextInt(nBannerCnt); 
	/*
 	if(nBannerDisp==0){
		do{
			nBannerDisp=(new Random()).nextInt(nBannerCnt);
		}while(nBannerDisp==0);
	}
	*/
 }
%>

 
<body>	
			<bean:define id="OR_CONDITION1" value="false"/>
			<bean:define id="OR_CONDITION2" value="false"/>
			<bean:define id="ASM_WRITTEN" value="false"/>
			<bean:define id="ASM_POSITIONED" value="false"/>
			<bean:define id="CONS_WRITTEN" value="false"/>
			<bean:define id="CONS_POSITIONED" value="false"/>
			<bean:define id="SUR_WRITTEN" value="false"/>
			<logic:present name="arOIBVLatestThread"  scope="request">
				 <bean:define id="OR_CONDITION1" value="true"/>
				 
			</logic:present>
			<logic:present name="arASMLetter">
					 <bean:define id="OR_CONDITION1" value="true"/>
			</logic:present>
			<logic:present name="arOIBlog"  scope="request">
				 <bean:define id="OR_CONDITION2" value="true"/>
				 
			</logic:present>
			<logic:present name="arOIBVPaper">
					 <bean:define id="OR_CONDITION2" value="true"/>
			</logic:present>


        <table width="857" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td bordercolor="0">
              <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,19,0" width="857" height="169">
                <param name="movie" value="<bean:message key="OIFM.docroot"/>/images/banners/myforumbanner<%=nBannerDisp%>.swf">
                <param name="quality" value="high">
                <embed src="<bean:message key="OIFM.docroot"/>/images/myforumbanner1.swf" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" width="857" height="169"></embed>
              </object>
            </td>
          </tr>
        </table>
        <table width="857" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="648" valign="top" class="orange"><div align="left" class="Highlight">Highlights</div></td>
            <td width="16" rowspan="2" valign="bottom" class="BLUE_FADE"><span class="Blue"><img src="<bean:message key="OIFM.docroot" />/images/Blue_fade.gif" width="16" height="100"></span></td>
            <td width="193" class="Blue"><div align="left"><span class="poll">Poll</span></div></td>
          </tr>
          <tr>
            <td valign="top" > 
			<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0" ><tr>
			<td class="orange" valign="top">
				<font class = "Highlight_body">
                <p><br>
                 <logic:present name="aOIBAConfiguration" scope="request">
					<bean:define id="homeAnnouncement" name="aOIBAConfiguration" scope="request" type="com.oifm.common.OIBAConfiguration"></bean:define>
        
		
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td colspan="2" valign="top" class="orange"><div align="justify" class="Highlight_body"> 
              <blockquote>
                <p><br>
						<logic:present name="aOIBAConfiguration" scope="request">
							<bean:define id="forumAnnouncement" name="aOIBAConfiguration" scope="request" type="com.oifm.common.OIBAConfiguration"></bean:define>
							<%= homeAnnouncement.getValue() %>
						</logic:present>
                 </p>
               </blockquote>
            </div></td>
            </tr>
			</table>			
					
					
				</logic:present> <br>
                 </p></font>
 			  </tr>
				</tr></table>
              <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0" valign="top">
             <tr>
            <td class="Grey_fade" valign="top">&nbsp;</td>
          </tr>
            <tr>
            <td align="left" valign="top" bgcolor="white">
            <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0" >
			   <logic:equal name="OR_CONDITION1" value="true">
              <tr>
                <td width="49%" align="left" valign="top">
                <table width="98%"  border="0" align="left" cellpadding="0" cellspacing="0">
				 <logic:present name="arOIBVLatestThread" scope="request">
                  <tr>
                    <td colspan="2" class="Heading_Topic">Latest Discussion(s)
					<!--
                <logic:present name="aLatTopic" scope="request">
					<bean:define id="latestDisc" name="aLatTopic" scope="request" type="com.oifm.common.OIBAConfiguration"></bean:define>
 						<logic:present name="aLatTopic" scope="request">
							<bean:define id="forumAnnouncement" name="aLatTopic" scope="request" type="com.oifm.common.OIBAConfiguration"></bean:define>
							in last <%= latestDisc.getValue() %> day(s)
						</logic:present>
						</logic:present>
					-->
					</td>
                  </tr>
                  
                  	<logic:iterate id="forumLatest" name="arOIBVLatestThread" scope="request" type="com.oifm.forum.OIBVHottestThread">
                  <tr>
                    <td width="6%"><div align="left"></div></td>
                    <td width="94%">
                    	<a href='<bean:message key="OIFM.contextroot" /><%= OIForumConstants.THREAD_MAINTAIN_DO %>?strThreadId=<bean:write name="forumLatest" property="threadId" />' class="Heading_Thread">
							<bean:write name="forumLatest" property="title" /> 
							
						</a>
                    </td>
                  </tr>
					<tr>
					  <td colspan="2" align="right" valign="top" class="sub_script_text">
						Last post <bean:write name="forumLatest" property="postedOn" />
						</td>
					</tr>

                  	</logic:iterate>
				</logic:present>

					<logic:notPresent name="arOIBVLatestThread" scope="request">
					<logic:present name="arASMLetter" scope="request">
                    <tr>
                      <td colspan="2" class="Heading_Topic">Ask Senior Management Letter(s)</td>
                    </tr>
                    
                    <logic:iterate id="asmLetter" name="arASMLetter" scope="request" type="com.oifm.asm.ASMBVCommon">
                    <tr>
                      <td width="6%"><div align="left"></div></td>
                      <td width="94%">
                      	<a href='<bean:message key="OIFM.contextroot" />/asmHome.do?hidLetterID=<bean:write name="asmLetter" property="hidRecLtrID" />&hiddenAction=populate&hidPageDesc=RecentLetters'); class="Heading_Thread">
							<bean:write name="asmLetter" property="hidRecLtrTopic" />
 						</a> 
                      </td>
                    </tr>
					<tr>
					  <td colspan="2" align="right" valign="top" class="sub_script_text">
						Published <bean:write name="asmLetter" property="hidRecLtrPubOn" />
						</td>
					</tr>

                    </logic:iterate>
					<bean:define id="ASM_WRITTEN" value="true"/>
				</logic:present>

					</logic:notPresent>

                </table></td>
                <td width="2%" align="center" valign="middle" >&nbsp;</td>
                <td width="49%" align="left" valign="top"><table width="98%"  border="0" cellspacing="0" cellpadding="0">
				<logic:notEqual name="ASM_WRITTEN" value="true">
				<logic:present name="arASMLetter" scope="request">
                    <tr>
                      <td colspan="2" class="Heading_Topic">Ask Senior Management Letter(s)</td>
                    </tr>
                    
                    <logic:iterate id="asmLetter" name="arASMLetter" scope="request" type="com.oifm.asm.ASMBVCommon">
                    <tr>
                      <td width="6%"><div align="left"></div></td>
                      <td width="94%">
                      	<a href='<bean:message key="OIFM.contextroot" />/asmHome.do?hidLetterID=<bean:write name="asmLetter" property="hidRecLtrID" />&hiddenAction=populate&hidPageDesc=RecentLetters'); class="Heading_Thread">
							<bean:write name="asmLetter" property="hidRecLtrTopic" />
 						</a> 
                      </td>
                    </tr>
					<tr>
					  <td colspan="2" align="right" valign="top" class="sub_script_text">
						Published <bean:write name="asmLetter" property="hidRecLtrPubOn" />
						</td>
					</tr>

                    </logic:iterate>
					<bean:define id="ASM_POSITIONED" value="true"/>
				</logic:present>
				</logic:notEqual>
				<logic:notEqual name="ASM_POSITIONED" value="true">
				
				<logic:present name="arOIBVPaper" scope="request">
                  <tr>
                    <td colspan="2" class="Heading_Topic">Latest Consultation Paper(s)</td>
                  </tr>
                  
                  	<logic:iterate id="paperLatest" name="arOIBVPaper" scope="request" type="com.oifm.consultation.OIBVPaper">
	                  <tr>
	                    <td width="6%"><div align="left"></div></td>
	                    <td width="94%">
	                    <a href='<bean:message key="OIFM.contextroot" />/webConsultOpenPaperAction.do?paperId=<bean:write name="paperLatest" property="paperId" />&hiddenAction=populate'); class="Heading_Thread">
							<bean:write name="paperLatest" property="paperName" />
 						</a> </td>
	                  </tr>
					<tr>
					  <td colspan="2" align="right" valign="top" class="sub_script_text">
						Posted <bean:write name="paperLatest" property="startStrDate" />
						</td>
					</tr>

					  
	               </logic:iterate>
				   <bean:define id="CONS_WRITTEN" value="true"/>
				</logic:present>  
				<logic:notEqual name="CONS_WRITTEN" value="true">
				<!-- 	<logic:present name="arOIBASurvey" scope="request">
                  <tr>
                    <td colspan="2" class="Heading_Topic">Latest Survey(s) </td>
                  </tr>
                  
                  <logic:iterate id="surveyLatest" name="arOIBASurvey" scope="request" type="com.oifm.survey.OIBASurvey">								
                  <tr>
                    <td width="6%"><div align="left"></div></td>
                    <td width="94%">
                    <a href='<bean:message key="OIFM.contextroot" /><%= OISurveyConstants.SURVEY_USER_DO %>?strSurveyId=<bean:write name="surveyLatest" property="strSurveyId" />&hiddenAction=<%= OISurveyConstants.SURVEY_DETAILS %>' class="Heading_Thread">
						<bean:write name="surveyLatest" property="strSurveyName" />
						
					</a>
                    </td>
                  </tr>
					<tr>
					  <td colspan="2" align="right" valign="top" class="sub_script_text">
						Posted <bean:write name="surveyLatest" property="strFromDate" />
						</td>
					</tr>
                  </logic:iterate>
				   <bean:define id="SUR_WRITTEN" value="true"/>
				</logic:present>
				 -->
				</logic:notEqual>

				
				</logic:notEqual>
                </table></td>
              </tr>
              <tr>
                <td colspan=3><hr style="color:#CCCCCC;height=1px"></td>
              </tr>
			  </logic:equal>
			   <logic:equal name="OR_CONDITION2" value="true">
              <tr>
                <td align="left" valign="top"><table width="98%"  border="0" align="left" cellpadding="0" cellspacing="0">


	<!-- commented by K.K.Kumaresan on June 29,2009 to hide the blog module
				<logic:present name="arOIBlog" scope="request">
				     <tr>
                    <td colspan="2" class="Heading_Topic">Latest Blog Entries</td>
                  </tr>
				
                <logic:iterate id="forumLatestBlog" name="arOIBlog" scope="request" type="com.oifm.blog.OIBVBlogCommon">
	                    <tr>
	                      <td width="6%"><div align="left"></div></td>
	                      <td width="94%">
	                      <a href='<bean:message key="OIFM.contextroot" />/IndividualBlogEntry.do?blogId=<bean:write name="forumLatestBlog" property="blogId" />&entryId=<bean:write name="forumLatestBlog" property="entryId" />' class="Heading_Thread">
							<bean:write name="forumLatestBlog" property="entryTitle" /></a> 
 							</td>
	                    </tr>
	                    <tr>
 	                      <td colspan="2" align="right" valign="top" class="sub_script_text">
 							Created On <bean:write name="forumLatestBlog" property="createdOn" />
							</td>
	                    </tr>

                    </logic:iterate>
				</logic:present>
	-->
				<logic:present name="arOIBASurvey" scope="request">
                  <tr>
                    <td colspan="2" class="Heading_Topic">Latest Survey(S)</td>
                  </tr>
                  
                  <logic:iterate id="surveyLatest" name="arOIBASurvey" scope="request" type="com.oifm.survey.OIBASurvey">								
                  <tr>
                    <td width="6%"><div align="left"></div></td>
                    <td width="94%">
                    <a href='<bean:message key="OIFM.contextroot" /><%= OISurveyConstants.SURVEY_USER_DO %>?strSurveyId=<bean:write name="surveyLatest" property="strSurveyId" />&hiddenAction=<%= OISurveyConstants.SURVEY_DETAILS %>' class="Heading_Thread">
						<bean:write name="surveyLatest" property="strSurveyName" />
						
					</a>
                    </td>
                  </tr>
					<tr>
					  <td colspan="2" align="right" valign="top" class="sub_script_text">
						Posted <bean:write name="surveyLatest" property="strFromDate" />
						</td>
					</tr>
                  </logic:iterate>
				</logic:present>
				<logic:notPresent name="arOIBlog" scope="request">
					<logic:notEqual name="CONS_WRITTEN" value="true">
					<logic:present name="arOIBVPaper" scope="request">
                  <tr>
                    <td colspan="2" class="Heading_Topic">Latest Consultation Paper(s)</td>
                  </tr>
                  
                  	<logic:iterate id="paperLatest" name="arOIBVPaper" scope="request" type="com.oifm.consultation.OIBVPaper">
	                  <tr>
	                    <td width="6%"><div align="left"></div></td>
	                    <td width="94%">
	                    <a href='<bean:message key="OIFM.contextroot" />/webConsultOpenPaperAction.do?paperId=<bean:write name="paperLatest" property="paperId" />&hiddenAction=populate'); class="Heading_Thread">
							<bean:write name="paperLatest" property="paperName" />
 						</a> </td>
	                  </tr>
					<tr>
					  <td colspan="2" align="right" valign="top" class="sub_script_text">
						Posted <bean:write name="paperLatest" property="startStrDate" />
						</td>
					</tr>

					  
	               </logic:iterate>
				   <bean:define id="CONS_WRITTEN" value="true"/>
				</logic:present>  

					</logic:notEqual>
					<logic:equal name="CONS_WRITTEN" value="true">
					<logic:notEqual name="SUR_WRITTEN" value="true">
						<logic:present name="arOIBASurvey" scope="request">
                  <tr>
                    <td colspan="2" class="Heading_Topic">Latest Survey(s) </td>
                  </tr>
                  
                  <logic:iterate id="surveyLatest" name="arOIBASurvey" scope="request" type="com.oifm.survey.OIBASurvey">								
                  <tr>
                    <td width="6%"><div align="left"></div></td>
                    <td width="94%">
                    <a href='<bean:message key="OIFM.contextroot" /><%= OISurveyConstants.SURVEY_USER_DO %>?strSurveyId=<bean:write name="surveyLatest" property="strSurveyId" />&hiddenAction=<%= OISurveyConstants.SURVEY_DETAILS %>' class="Heading_Thread">
						<bean:write name="surveyLatest" property="strSurveyName" />
						
					</a>
                    </td>
                  </tr>
					<tr>
					  <td colspan="2" align="right" valign="top" class="sub_script_text">
						Posted <bean:write name="surveyLatest" property="strFromDate" />
						</td>
					</tr>
                  </logic:iterate>
				  <bean:define id="SUR_WRITTEN" value="true"/>
				</logic:present>
					</logic:notEqual>
					</logic:equal>

				</logic:notPresent>
                </table></td>
                <td align="left" >&nbsp;</td>
                <td width="49%" align="left" valign="top"><table width="98%"  border="0" align="left" cellpadding="0" cellspacing="0">
				<logic:notEqual name="CONS_WRITTEN" value="true">
				<logic:present name="arOIBVPaper" scope="request">
                  <tr>
                    <td colspan="2" class="Heading_Topic">Latest Consultation Paper(s)</td>
                  </tr>
                  
                  	<logic:iterate id="paperLatest" name="arOIBVPaper" scope="request" type="com.oifm.consultation.OIBVPaper">
	                  <tr>
	                    <td width="6%"><div align="left"></div></td>
	                    <td width="94%">
	                    <a href='<bean:message key="OIFM.contextroot" />/webConsultOpenPaperAction.do?paperId=<bean:write name="paperLatest" property="paperId" />&hiddenAction=populate'); class="Heading_Thread">
							<bean:write name="paperLatest" property="paperName" />
 						</a> </td>
	                  </tr>
					<tr>
					  <td colspan="2" align="right" valign="top" class="sub_script_text">
						Posted <bean:write name="paperLatest" property="startStrDate" />
						</td>
					</tr>

					  
	               </logic:iterate>
				    <bean:define id="CONS_POSITIONED" value="true"/>
				</logic:present>    
				</logic:notEqual>
				<logic:notEqual name="CONS_POSITIONED" value="true">
				<logic:notEqual name="SUR_WRITTEN" value="true">
				<!-- 		<logic:present name="arOIBASurvey" scope="request">
                  <tr>
                    <td colspan="2" class="Heading_Topic">Latest Survey(s) </td>
                  </tr>
                  
                  <logic:iterate id="surveyLatest" name="arOIBASurvey" scope="request" type="com.oifm.survey.OIBASurvey">								
                  <tr>
                    <td width="6%"><div align="left"></div></td>
                    <td width="94%">
                    <a href='<bean:message key="OIFM.contextroot" /><%= OISurveyConstants.SURVEY_USER_DO %>?strSurveyId=<bean:write name="surveyLatest" property="strSurveyId" />&hiddenAction=<%= OISurveyConstants.SURVEY_DETAILS %>' class="Heading_Thread">
						<bean:write name="surveyLatest" property="strSurveyName" />
						
					</a>
                    </td>
                  </tr>
					<tr>
					  <td colspan="2" align="right" valign="top" class="sub_script_text">
						Posted <bean:write name="surveyLatest" property="strFromDate" />
						</td>
					</tr>
                  </logic:iterate>
				  <bean:define id="SUR_WRITTEN" value="true"/>
				</logic:present>
				 -->
					</logic:notEqual>
				</logic:notEqual>
                </table></td>
              </tr>
              <tr>
                <td align="left" valign="top" colspan=3><hr style="color:#CCCCCC;height=1px"></td>
              </tr>
		    </logic:equal>
              <tr>
			    <td colspan=3>
				<table width="100%" border="0">
				<tr>
                 <td align="left" valign="top" width="1%">&nbsp;</td> 
                
                <td align="left" valign="top" width="50%"><table width="50%"  border="0" cellspacing="0" cellpadding="0">
				<logic:notEqual name="SUR_WRITTEN" value="true">
				<!--
				<logic:present name="arOIBASurvey" scope="request">
                  <tr>
                    <td colspan="2" class="Heading_Topic">Latest Survey(s) </td>
                  </tr>
                  
                  <logic:iterate id="surveyLatest" name="arOIBASurvey" scope="request" type="com.oifm.survey.OIBASurvey">								
                  <tr>
                    <td width="6%"><div align="left"></div></td>
                    <td width="94%">
                    <a href='<bean:message key="OIFM.contextroot" /><%= OISurveyConstants.SURVEY_USER_DO %>?strSurveyId=<bean:write name="surveyLatest" property="strSurveyId" />&hiddenAction=<%= OISurveyConstants.SURVEY_DETAILS %>' class="Heading_Thread">
						<bean:write name="surveyLatest" property="strSurveyName" />
						
					</a>
                    </td>
                  </tr>
					<tr>
					  <td colspan="2" align="right" valign="top" class="sub_script_text">
						Posted <bean:write name="surveyLatest" property="strFromDate" />
						</td>
					</tr>
                  </logic:iterate>
				</logic:present>
				-->
				</logic:notEqual>
                </table></td>
              </tr>
			  </table></td></tr>
            </table></td>
            
          </tr>
            </table>
            </td>
        <td width="193" rowspan="3" align="left" valign="top" class="Blue">
        <table width="85%"  border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td class="Poll_body style2">&nbsp;</td>
          </tr>
          <html:form action ="/home.do">
         <logic:present name="pollview" scope="request">
         <logic:iterate id="objPoll" name="pollview" type="com.oifm.poll.OIBVPoll" scope="request" >   
          <tr>
            <td class="Poll_body style2"><bean:write name="objPoll" property="title"/></td>
          </tr>
          <tr>
            <td class="Poll_body style2"><table width="100%"  border="0" cellpadding="0" cellspacing="0">
                <tr>
                  <td height="5 px" colspan="2" class="Poll_body">&nbsp;</td>
                </tr>
                <tr class="Text">
				<td height="35" colspan="2" class="Poll_body"><strong>Q. </strong><bean:write name="objPoll" property="question"/><br><br></td>
				<%for(int i =1;i<=5 ;i++){%>   	        
					<logic:notEqual name="objPoll" property='<%="answer"+i%>' value="" > 
						<tr align="left" valign="top" class="Poll_body">
							<td width="14%">
								<logic:equal name="objPoll" property="mutAns" value="Y" >
									<html:checkbox property='<%="res"+i%>' value="Y"></html:checkbox>
								</logic:equal>
								<logic:notEqual name="objPoll" property="mutAns" value="Y">
								      <html:radio property="response" value='<%=""+i%>'></html:radio>
								</logic:notEqual>
              				</td>
							<td height="25"><bean:write name ="objPoll" property='<%="answer"+i%>'/></td>
						</tr>
					</logic:notEqual>
				<%}%>
			</tr>
            <tr>
              <td>&nbsp;</td>
              <td align="right">
              <a href="#" onClick="fnShowResult();">
				<img src='<bean:message key="OIFM.docroot" />/images/btn_poll.gif' border="0" alt="Submit your vote">
			</a>
            </tr>
          	</logic:iterate> 
			</logic:present> 
			<logic:present name="pollview" scope="request">
                <tr>
                  <td>&nbsp;</td>
                  <td >
                  <a href="#" onClick="fnPublishCurrent();" class="Address"  alt="Click to view current Poll Result">View Current Poll results</a>
				</td>
                </tr>
			</logic:present>
			
                <tr>
                  <td>&nbsp;</td>
                  <td nowrap>
                  	<a href="#" onClick="fnPublish();" class="Address" alt="Click to view earlier Poll Result">View Previous Poll results</a>
                  </td>
                </tr>
                <tr>
                  <td colspan="2" valign="bottom" class="Poll_body">&nbsp;</td>
                </tr>
                 <tr>
                  <td colspan="2">
				  &nbsp; 
				  </td>
                </tr>
                
                <% /* Commented by Oscar @ 07 Feb 2007 as requested by Lisa for not displaying tag in home page
                <tr>
                  <td colspan="2" valign="bottom" class="body_detail_text">
				 
                  <html:text property="tags" styleClass="text_box" size="25" maxlength="250" readonly="true" />
				  <logic:present name="alTagNames" scope="session">							
						<logic:lessThan name="alTagSize" scope="session" value="1">
							Please Click on the button to add tag to your profile.
						</logic:lessThan>
					</logic:present>
					<logic:notPresent name="alTagNames" scope="session">							  
								Please Click on the button to add tag to your profile.	    
					</logic:notPresent>
					</td>
                </tr>
                <tr>
                  <td colspan="2" valign="bottom" class="Poll_body">&nbsp;</td>
                </tr>
       				<tr>
					<td colspan="2" align="right"><a href="javascript:;" onClick="javascript:fnSelectTags();"><img src='<bean:message key="OIFM.docroot" />/images/select-tags.gif' border="0" alt="Add Tags" /></a>&nbsp;&nbsp;&nbsp;<a href="#" onclick="saveForm('saveTag')"><img src="<bean:message key="OIFM.docroot"/>/images/btn_Save.gif" border="0" alt ="Save Tags"></a></td>
				</tr>
                 -- */ %>
                 
                <tr>
                  <td colspan="2" valign="bottom" class="Poll_body">
					</td>
                </tr>
            </table></td>
          </tr>
        </table>
        </td>
          </tr>
        </table>
        <tr align = "center"><td><jsp:include page="/jsp/common/oifm_footer.jsp" flush="true" /></td></tr>
       </td>
    </tr>    
  </table>  
<html:hidden property="hiddenAction"/>
<html:hidden property="showRes"/>  
<html:hidden property="pollId"/>
<html:hidden property="multiple"/>
<html:hidden property="pubId"/>
<html:hidden property="published"/>
</html:form>
<form name="tempForm" method="post">
	<input type="hidden" name="paperId">
	<input type="hidden" name="hiddenAction">
</form>
</body>
</HTML>

<script language="javascript" >
var strDocRoot = '<bean:message key="OIFM.contextroot"/>'
function saveForm(hidAction)
{
	var frm = document.HomeForm;
	frm.hiddenAction.value = hidAction;
	frm.submit();
}

</script>

<!-- Start of StatCounter Code --> 
<script type="text/javascript" language="javascript"> 
<!-- 
var sc_project=1856639; 
var sc_invisible=1; 
var sc_partition=17; 
var sc_security="71c335a2"; 
//--> 
</script> 

<script type="text/javascript" language="javascript" src="http://www.statcounter.com/counter/counter.js"></script><noscript><a href="http://www.statcounter.com/" target="_blank"><img  src="http://c18.statcounter.com/counter.php?sc_project=1856639&java=0&security=71c335a2&invisible=1" alt="web counter" border="0" style="display:none;"></a> </noscript> 
<!-- End of StatCounter Code -->
