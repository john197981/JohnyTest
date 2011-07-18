<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="com.oifm.survey.*,java.util.*,com.oifm.common.OILabelConstants,com.oifm.utility.OIUtilities" %>

<style type="text/css">

table, td
{
    border-color: #C0C0C0;
    border-style: solid;
}

table
{
    border-width: 0 0 1px 1px;
    border-spacing: 0;
    border-collapse: collapse;
}

td
{
    margin: 0;
    padding: 4px;
    border-width: 1px 1px 0 0;
   
}

</style>

<%
 String surveyInfo = (String)request.getAttribute("surveyInfo");
 if(surveyInfo==null)
 {
	 surveyInfo = (String)session.getAttribute("exporthtml_surveyInfo");
 }
 session.setAttribute("exporthtml_surveyInfo",surveyInfo);
%>

<%=
surveyInfo
%>
<table border=1><tr>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>

<%
					boolean nPageLnk = false;
					ArrayList alQuestAnsList =(ArrayList)request.getAttribute("alQuestAnsList");

					if(alQuestAnsList==null || alQuestAnsList.size()==0)
					{
						alQuestAnsList =(ArrayList)session.getAttribute("exporthtml_alQuestAnsList");
					}
					session.setAttribute("exporthtml_alQuestAnsList",alQuestAnsList);
					ArrayList alIdsList =(ArrayList)request.getAttribute("alIdsList");
					

					if(alIdsList==null || alIdsList.size()==0)
					{
						alIdsList =(ArrayList)session.getAttribute("exporthtml_alIdsList");
						
					}
					session.setAttribute("exporthtml_alIdsList",alIdsList);
					ArrayList alResponseList =(ArrayList)request.getAttribute("alResponse");
					
					if(alResponseList==null || alResponseList.size()==0)
					{
						alResponseList =(ArrayList)session.getAttribute("exporthtml_alResponse");
					}
					session.setAttribute("exporthtml_alResponse",alResponseList);

					OIBASurveyResponse objSurveyResponse = null;
					OIBAResponse objResponse = null;
					Hashtable htbUserResponses =  null;
					StringBuffer stbrOut = new StringBuffer();
					int startCount = 0;
					int endCount = (alQuestAnsList!=null?alQuestAnsList.size():0);
					if(alQuestAnsList!=null && alQuestAnsList.size()>20)
					{
						endCount = 20;
						nPageLnk = true;
					}
					if(request.getParameter("nextPage")!=null)
					{
						int nextPage = Integer.parseInt((String)request.getParameter("nextPage"));
						startCount = nextPage ;
						if((startCount+20)<alQuestAnsList.size())
						{
							endCount = startCount+20;
						}
						else
						{
							endCount = alQuestAnsList.size();
							nPageLnk = false;
						}
					}

					int jj = startCount+1;
					StringBuffer tempStbrOut = new StringBuffer();
			    		for(int k=startCount; k<endCount; k++) 
			    		{
			    		    OIBAQuestion objQuestion = (OIBAQuestion)alQuestAnsList.get(k);
			    		    //if (objQuestion.getStrAnswer1().trim().startsWith("Please use the"))
			    		    if (objQuestion.getStrQuestionType().trim().equals("T"))
			    		    {
			    		        stbrOut.append("<td>Q"+jj+"</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>");
			    		        tempStbrOut.append("<td>R1</td>");
			    		        tempStbrOut.append("<td>R2</td>");
			    		        tempStbrOut.append("<td>R3</td>");
			    		        tempStbrOut.append("<td>R4</td>");
			    		        tempStbrOut.append("<td>R5</td>");
			    		        tempStbrOut.append("<td>R6</td>");
			    		        tempStbrOut.append("<td>R7</td>");
			    		        tempStbrOut.append("<td>R8</td>");
			    		        tempStbrOut.append("<td>R9</td>");
			    		        tempStbrOut.append("<td>R10</td>");
			    		    }
			    		    else
			    		    {
			    		        stbrOut.append("<td>Q"+jj+"</td>");
			    		        if (objQuestion.getStrAnswer1() != null && ! objQuestion.getStrAnswer1().trim().equals(""))
			    		        {
			    		            tempStbrOut.append("<td>R1</td>");
								}
			    		        if (objQuestion.getStrAnswer2() != null && ! objQuestion.getStrAnswer2().trim().equals(""))
			    		        {
			    		            tempStbrOut.append("<td>R2</td>");
									stbrOut.append("<td>&nbsp;</td>");
			    		        }
			    		        if (objQuestion.getStrAnswer3() != null && ! objQuestion.getStrAnswer3().trim().equals(""))
			    		        {
			    		            tempStbrOut.append("<td>R3</td>");
									stbrOut.append("<td>&nbsp;</td>");
			    		        }
			    		        if (objQuestion.getStrAnswer4() != null && ! objQuestion.getStrAnswer4().trim().equals(""))
			    		        {
			    		            tempStbrOut.append("<td>R4</td>");
									stbrOut.append("<td>&nbsp;</td>");
			    		        }
			    		        if (objQuestion.getStrAnswer5() != null && ! objQuestion.getStrAnswer5().trim().equals(""))
			    		        {
			    		            tempStbrOut.append("<td>R5</td>");
									stbrOut.append("<td>&nbsp;</td>");
			    		        }
			    		        if (objQuestion.getStrAnswer6() != null && ! objQuestion.getStrAnswer6().trim().equals(""))
			    		        {
			    		            tempStbrOut.append("<td>R6</td>");
									stbrOut.append("<td>&nbsp;</td>");
			    		        }
			    		        if (objQuestion.getStrAnswer7() != null && ! objQuestion.getStrAnswer7().trim().equals(""))
			    		        {
			    		            tempStbrOut.append("<td>R7</td>");
									stbrOut.append("<td>&nbsp;</td>");
			    		        }
			    		        if (objQuestion.getStrAnswer8() != null && ! objQuestion.getStrAnswer8().trim().equals(""))
			    		        {
			    		            tempStbrOut.append("<td>R8</td>");
									stbrOut.append("<td>&nbsp;</td>");
			    		        }
			    		        if (objQuestion.getStrAnswer9() != null && ! objQuestion.getStrAnswer9().trim().equals(""))
			    		        {
			    		            tempStbrOut.append("<td>R9</td>");
									stbrOut.append("<td>&nbsp;</td>");
			    		        }
			    		        if (objQuestion.getStrAnswer10() != null && ! objQuestion.getStrAnswer10().trim().equals(""))
			    		        {
			    		            tempStbrOut.append("<td>R10</td>");
									stbrOut.append("<td>&nbsp;</td>");
			    		        }
			    		        //stbrOut.append(OILabelConstants.TABCOL);
			    		    }
			    		    tempStbrOut.append("<td>Remarks</td>");
							stbrOut.append("<td>&nbsp;</td>");
				    		jj++;
			    		}
						stbrOut.append("</tr><tr><td>");
				stbrOut.append("Name</td><td>Age</td><td>Years in Service</td><td>Email Address</td><td>Designation</td><td>Division Name</td><td>School Name</td><td>SchoolLevel</td>" );
				stbrOut.append(tempStbrOut.toString());
				for (int j=1; j<=alIdsList.size(); j++)
				{
					for (int k=1; k<=10; k++)
					{
						//stbrOut.append("R"+k+OILabelConstants.TABCOL);
					}
					//stbrOut.append("Remarks"+OILabelConstants.TABCOL);
				}
				stbrOut.append("</tr>");
				//logger.info("Total Size-" + alResponseList.size());
				for(int i=0; i<alResponseList.size(); i++) 
				{
    		        //logger.info("Raj-1");
				    objSurveyResponse = (OIBASurveyResponse) alResponseList.get(i);
				  stbrOut.append("<tr>");	
				  stbrOut.append(OIUtilities.returnEmptyIfNull("<td>"+objSurveyResponse.getStrNickName())+OILabelConstants.TABCOL+OIUtilities.returnEmptyIfNull(objSurveyResponse.getAge())+OILabelConstants.TABCOL+OIUtilities.returnEmptyIfNull(objSurveyResponse.getService())+OILabelConstants.TABCOL+OIUtilities.returnEmptyIfNull(objSurveyResponse.getEmail())+OILabelConstants.TABCOL+OIUtilities.returnEmptyIfNull(objSurveyResponse.getDesignation())+OILabelConstants.TABCOL+OIUtilities.returnEmptyIfNull(objSurveyResponse.getStrDivisioncode())+OILabelConstants.TABCOL+OIUtilities.returnEmptyIfNull(objSurveyResponse.getStrSchoolCode())+OILabelConstants.TABCOL+OIUtilities.returnEmptyIfNull(objSurveyResponse.getSchoolLevel())+"</td>");
					htbUserResponses = objSurveyResponse.getHtbUserResponse();
					
					
					for(int k=startCount; k<endCount; k++) 
					{
					 OIBAQuestion objQuestion = (OIBAQuestion)alQuestAnsList.get(k);
			    		        //logger.info("Raj-4");

								//for (int j=0; j<alIdsList.size(); j++) 
								//{
									objResponse = (OIBAResponse)htbUserResponses.get(objQuestion.getStrQuestionId());
									if(objResponse == null) 
									{
									    objResponse = new OIBAResponse();
									}
				
									//stbrOut.append(OIUtilities.returnEmptyIfNull(objResponse.getStrQuestionId() + "-" + objResponse.getStrResponse1())+OILabelConstants.TABCOL);
					    		    if (objQuestion.getStrQuestionType().trim().equals("T"))
					    		    {
										stbrOut.append("<td>");	stbrOut.append(OIUtilities.returnEmptyIfNull(objResponse.getStrResponse1())+OILabelConstants.TABCOL);
										stbrOut.append(OIUtilities.returnEmptyIfNull(objResponse.getStrResponse2())+OILabelConstants.TABCOL);
										stbrOut.append(OIUtilities.returnEmptyIfNull(objResponse.getStrResponse3())+OILabelConstants.TABCOL);
										stbrOut.append(OIUtilities.returnEmptyIfNull(objResponse.getStrResponse4())+OILabelConstants.TABCOL);
										stbrOut.append(OIUtilities.returnEmptyIfNull(objResponse.getStrResponse5())+OILabelConstants.TABCOL);
										stbrOut.append(OIUtilities.returnEmptyIfNull(objResponse.getStrResponse6())+OILabelConstants.TABCOL);
										stbrOut.append(OIUtilities.returnEmptyIfNull(objResponse.getStrResponse7())+OILabelConstants.TABCOL);
										stbrOut.append(OIUtilities.returnEmptyIfNull(objResponse.getStrResponse8())+OILabelConstants.TABCOL);
										stbrOut.append(OIUtilities.returnEmptyIfNull(objResponse.getStrResponse9())+OILabelConstants.TABCOL);
										stbrOut.append(OIUtilities.returnEmptyIfNull(objResponse.getStrResponse10()));
										stbrOut.append("</td>");
					    		    }
					    		    else
					    		    {
					    		        if (objQuestion.getStrAnswer1() != null && ! objQuestion.getStrAnswer1().trim().equals(""))
					    		        {
											stbrOut.append("<td>");
											stbrOut.append(OIUtilities.returnEmptyIfNull(objResponse.getStrResponse1()));
											stbrOut.append("</td>");
						    		        //logger.info("Answer-1" + OIUtilities.returnEmptyIfNull(objResponse.getStrResponse1()));
					    		        }
					    		        if (objQuestion.getStrAnswer2() != null && ! objQuestion.getStrAnswer2().trim().equals(""))
					    		        {
											stbrOut.append("<td>");
											stbrOut.append(OIUtilities.returnEmptyIfNull(objResponse.getStrResponse2()));
											stbrOut.append("</td>");
						    		        //logger.info("Answer-2" + OIUtilities.returnEmptyIfNull(objResponse.getStrResponse2()));
					    		        }
					    		        if (objQuestion.getStrAnswer3() != null && ! objQuestion.getStrAnswer3().trim().equals(""))
					    		        {
											stbrOut.append("<td>");
											stbrOut.append(OIUtilities.returnEmptyIfNull(objResponse.getStrResponse3()));
											stbrOut.append("</td>");
						    		        //logger.info("Answer-3" + OIUtilities.returnEmptyIfNull(objResponse.getStrResponse3()));
					    		        }
					    		        if (objQuestion.getStrAnswer4() != null && ! objQuestion.getStrAnswer4().trim().equals(""))
					    		        {
											stbrOut.append("<td>");
											stbrOut.append(OIUtilities.returnEmptyIfNull(objResponse.getStrResponse4()));
											stbrOut.append("</td>");
						    		        //logger.info("Answer-4" + OIUtilities.returnEmptyIfNull(objResponse.getStrResponse4()));
					    		        }
					    		        if (objQuestion.getStrAnswer5() != null && ! objQuestion.getStrAnswer5().trim().equals(""))
					    		        {
											stbrOut.append("<td>");
											stbrOut.append(OIUtilities.returnEmptyIfNull(objResponse.getStrResponse5()));
											stbrOut.append("</td>");
						    		        //logger.info("Answer-5" + OIUtilities.returnEmptyIfNull(objResponse.getStrResponse5()));
					    		        }
					    		        if (objQuestion.getStrAnswer6() != null && ! objQuestion.getStrAnswer6().trim().equals(""))
					    		        {
											stbrOut.append("<td>");
											stbrOut.append(OIUtilities.returnEmptyIfNull(objResponse.getStrResponse6()));
											stbrOut.append("</td>");
						    		        //logger.info("Answer-6" + OIUtilities.returnEmptyIfNull(objResponse.getStrResponse6()));
					    		        }
					    		        if (objQuestion.getStrAnswer7() != null && ! objQuestion.getStrAnswer7().trim().equals(""))
					    		        {
											stbrOut.append("<td>");
											stbrOut.append(OIUtilities.returnEmptyIfNull(objResponse.getStrResponse7()));
											stbrOut.append("</td>");
						    		        //logger.info("Answer-7" + OIUtilities.returnEmptyIfNull(objResponse.getStrResponse7()));
					    		        }
					    		        if (objQuestion.getStrAnswer8() != null && ! objQuestion.getStrAnswer8().trim().equals(""))
					    		        {
											stbrOut.append("<td>");
											stbrOut.append(OIUtilities.returnEmptyIfNull(objResponse.getStrResponse8()));
											stbrOut.append("</td>");
						    		        //logger.info("Answer-8" + OIUtilities.returnEmptyIfNull(objResponse.getStrResponse8()));
					    		        }
					    		        if (objQuestion.getStrAnswer9() != null && ! objQuestion.getStrAnswer9().trim().equals(""))
					    		        {
											stbrOut.append("<td>");
											stbrOut.append(OIUtilities.returnEmptyIfNull(objResponse.getStrResponse9()));
											stbrOut.append("</td>");
						    		        //logger.info("Answer-9" + OIUtilities.returnEmptyIfNull(objResponse.getStrResponse9()));
					    		        }
					    		        if (objQuestion.getStrAnswer10() != null && ! objQuestion.getStrAnswer10().trim().equals(""))
					    		        {
											stbrOut.append("<td>");
											stbrOut.append(OIUtilities.returnEmptyIfNull(objResponse.getStrResponse10()));
											stbrOut.append("</td>");
						    		        //logger.info("Answer-10" + OIUtilities.returnEmptyIfNull(objResponse.getStrResponse10()));
					    		        }
					    		    }
								
									stbrOut.append("<td>");
									stbrOut.append(OIUtilities.returnEmptyIfNull(objResponse.getStrOtherRemarks()));
									stbrOut.append("</td>");
								//}
					}
					stbrOut.append("</tr>");
				}
				stbrOut.append("</table>");
						out.println(stbrOut.toString());

%>
<form name='exportForm' action='<bean:message key="OIFM.contextroot" />/jsp/admin/iofmExportHtml.jsp' method="post">

<input type=hidden name=nextPage value='<%=endCount%>' >

<%

if(startCount>0)
{
%>
&nbsp;&nbsp;&nbsp;<input type=submit value = 'Previous' onClick='document.exportForm.nextPage.value="<%=startCount-20%>";' >
<%
}
if(nPageLnk)
{
%>
&nbsp;&nbsp;&nbsp;<input type=submit value = 'Next' onClick='document.exportForm.nextPage.value="<%=endCount%>";' >
<%
}
%>

</form>