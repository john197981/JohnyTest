<%--
/**
 * FileName			: ASMHomePage.jsp
 * Author      		: Anbalagan
 * Created Date 	: 09/12/2005
 * Description 		: This page used to display the ASM home page.
 * Version			: 1.0
 **/  
--%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<html:html>
	 
<head>
<style type="text/css"> 
<!-- .style2 {font-size: 11px}
a:link {
	text-decoration: none;
}
.style3 {color: #FFFFCC}
	--> 
	</style>

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

 	<script>var contextRoot = '<bean:message key="OIFM.contextroot" />';</script>
<title>Ask Senior Management</title>

</head>
	<form name="ASMFormHome">
	<input type="hidden" name="hidPageDesc" />
	<input type="hidden" name="hidLetterID" />
	<input type="hidden" name="hiddenAction" />
	</form>
	<jsp:include page="/jsp/common/iofm_Top.jsp" flush="true" >	
		<jsp:param name="pageName" value="ASM" />
	</jsp:include>
 
	<logic:present name="pageName" scope="request">
		<logic:equal name="pageName" value="ASMHome" scope="request">
			<jsp:include page="/jsp/asm/ASMHomePage.jsp" flush="true" />
		</logic:equal>
		<logic:equal name="pageName" value="ASMAbout" scope="request">
			<jsp:include page="/jsp/asm/ASMAbout.jsp" flush="true" />
			<jsp:include page="/jsp/common/oifm_footer.jsp" flush="true" />
		</logic:equal>
		<logic:equal name="pageName" value="ASMView" scope="request">
			<jsp:include page="/jsp/asm/ASMViewLetters.jsp" flush="true" />			
		</logic:equal>
		<logic:equal name="pageName" value="ASMPrevRep" scope="request">
			<jsp:include page="/jsp/asm/ASMPrevRep.jsp" flush="true" />			
		</logic:equal>
		<logic:equal name="pageName" value="ASMEditor" scope="request">
			<jsp:include page="/jsp/asm/ASMEditor.jsp" flush="true" />
			<jsp:include page="/jsp/common/oifm_footer.jsp" flush="true" />
		</logic:equal>
		<logic:equal name="pageName" value="ASMManagement" scope="request">
			<jsp:include page="/jsp/asm/ASMManagement.jsp" flush="true" />
			<jsp:include page="/jsp/common/oifm_footer.jsp" flush="true" />
		</logic:equal>
		<logic:equal name="pageName" value="ASMDetailLetter" scope="request">
			<jsp:include page="/jsp/asm/ASMUserDetailLetter.jsp" flush="true" />			
			<jsp:include page="/jsp/common/oifm_footer.jsp" flush="true" />
		</logic:equal>	
		<logic:equal name="pageName" value="Categories" scope="request">
			<jsp:include page="/jsp/asm/ASMCategoriesLetters.jsp" flush="true" />		
		</logic:equal>	
		<logic:equal name="pageName" value="EditorNotes" scope="request">
			<jsp:include page="/jsp/asm/ASMCategoriesEditorNotes.jsp" flush="true" />		
		</logic:equal>	
	</logic:present>
	<logic:present name="pageName" scope="request">
	<logic:notEqual name="pageName" value="ASMHome">
	<logic:notEqual name="pageName" value="ASMPrevRep">

</logic:notEqual>
</logic:notEqual>
</logic:present>
</html:html>
