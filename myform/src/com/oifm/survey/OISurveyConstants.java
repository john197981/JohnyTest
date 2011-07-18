package com.oifm.survey;

public interface OISurveyConstants {


	public static final String	SURVEY_DOC		=	"S";
	public static final String	NEW				=	"N";
	public static final String	DRAFT			=	"D";
	public static final String	SUBMITTED		=	"S";
	public static final String	PUBLISH_CATEGORY=	"PUBLISH_CATEGORY";
	public static final String	PUBLISH_CATEGORY_P=	"PUBLISH_CATEGORY_P";

	public static final int	TEMP_USER_ID_SIZE	=	6;
	public static final int	PASSWORD_SIZE		=	6;
	public static final int	EXPORT_BUFFER_RECS	=	500;
	
	public static final String	SECURITY[]		=	{"N", "Public","Y", "Private"};
	public static final String	SINGLE_CHOICE	=	"R";
	public static final String	MULIPLE_CHOICE	=	"C";
	public static final String	FILL_IN_BLANKS	=	"T";
	public static final String	LIKERT_SCALE	=	"L";
	public static final String	QUESTION_TYPE_ID[]		=	{SINGLE_CHOICE, MULIPLE_CHOICE, FILL_IN_BLANKS, LIKERT_SCALE};
	public static final String	QUESTION_TYPE_TEXT[]	=	{"Single Choice", "Multiple Choice", "Fill in the blanks","Likert Scale"};

	public static final String	STATUS_ICONS[]	=	{"/images/latest_thread.gif", "/images/icon_draft.gif", "/images/icon_submited.gif"};
	
//	file paths for request handlers

	public static final String	SURVEY_ADMIN_DO	=	"/SurveyAdmin.do";
	public static final String	SURVEY_SECTION_DO	=	"/SurveySection.do";
	public static final String	SURVEY_QUESTION_DO	=	"/SurveyQuestion.do";
	public static final String	SURVEY_TEMP_USER_DO	=	"/SurveyTempUser.do";
	public static final String	SURVEY_MAIL_DO	=	"/SendMail.do";
	public static final String	SURVEY_USER_DO	=	"/UserSurvey.do";
	public static final String	SURVEY_USER_RSP_DO	=	"/UserSurveyResponse.do";
	//added by edmund
	public static final String	SURVEY_RESULT_DO	=	"/SurveyResult.do";
	//ended by edmund
	public static final String	ERROR_DO	=	"/error.do";	
	
//	labels to forward for JSP files 
	
	public static final String	LIST_PAGE		=	"AdminPage";
	public static final String	EDIT_PAGE		=	"AdminPage";
	public static final String	QST_EDIT_PAGE	=	"EditPage";
	public static final String	ERROR_PAGE		=	"ErrorPage";
	public static final String	USER_PAGE		=	"UserPage";
	public static final String	PREVIEW_PAGE	=	"PreviewPage";
	public static final String	PRINT_PREVIEW_PAGE	=	"PrintPreviewPage";
	public static final String	EXPORT_HTML_PAGE		=	"ExportHtmlPage";
	
	
//	labels to acton keys
	public static final String	SURVEY_ADMIN_LIST="SurveyAdminList";
	//added by priyanka
    public static final String	SURVEY_VIEW_ALL= "SurveyViewAll";
	//ended by priyanka
	//added by priyanka
    public static final String	DIVISION_SURVEY_VIEW_ALL= "DivisionSurveyViewAll";
	//ended by priyanka
	public static final String	SURVEY_SAVE		=	"SurveySave";
	public static final String	SURVEY_SAVE_NEXT=	"SurveySaveNext";
	public static final String	SURVEY_EDIT		=	"SurveyEdit";
	public static final String	SURVEY_DELETE	=	"SurveyDelete";
	public static final String	SURVEY_PUBLISH	=	"SurveyPublish";
	public static final String	SURVEY_PUBLISH_EDIT	=	"SurveyPublishEdit";
	public static final String	SURVEY_ATTACHMENT_REMOVE	=	"SurveyAttachmentRemove";
	public static final String	SURVEY_EXPORT	=	"SurveyExport";
	public static final String	SURVEY_EXPORT_HTML	=	"SurveyExportHtml";
	public static final String	SURVEY_ACTIVATE	=	"SurveyActivate";
	
	public static final String	SURVEY_CANCEL	=	"SurveyAdminList";
	public static final String	SURVEY_PREVIEW	=	"SurveyPreview";
	public static final String	SURVEY_MAIL		=	"SendMail";
	public static final String	SURVEY_IMPORT	=	"SurveyImport";
	public static final String	SURVEY_PRINT_PREVIEW=	"SurveyPrintPreview";
	
	public static final String	SECTION_SAVE	=	"SectionSave";
	public static final String	SECTION_EDIT	=	"SectionEdit";
	public static final String	SECTION_DELETE	=	"SectionDelete";
	
	public static final String	QUESTION_LIST	=	"QuestionList";
	public static final String	QUESTION_SAVE	=	"QuestionSave";
	public static final String	QUESTION_EDIT	=	"QuestionEdit";
	public static final String	QUESTION_DELETE	=	"QuestionDelete";
	public static final String	QUESTION_MOVE_UP	=	"QuestionMoveUp";
	public static final String	QUESTION_MOVE_DOWN	=	"QuestionMoveDown";
	
	//added by edmund
	public static final String  RESULT_TYPE		=	"ResultType";
	//ended by edmund

	public static final String	TEMP_USER_LIST	=	"TempUserList";
	public static final String	TEMP_USER_GENERATION=	"Generation";
	public static final String	TEMP_USER_REMOVE=	"TempUserRemove";
	public static final String	TEMP_USER_EXPORT=	"TempUserExport";
	
	public static final String	SURVEY_USER_LIST	=	"SurveyUserList";
	public static final String	SURVEY_DETAILS		=	"SurveyDetails";
	public static final String	SECTION_QST_RESP_DTLS	=	"SecQstRspDtls";
	public static final String	SECTION_QST_RESP_SAVE	=	"SecQstRspSave";
	public static final String	SURVEY_USER_SUBMIT	= "SurveyUserSubmit";
	public static final String	SURVEY_DOWNLOAD	= "SurveyDownlaod";
	
	public static final String	SURVEY_UPLOAD_DIR	= "SurveyUploadDirPath";
	
//	Result type
	//added by edmund
	public static final String  RESULT_TYPE_EXCEL	=	"ExcelFormat";
	public static final String  RESULT_TYPE_SUMMARY	=	"DemographicSummary";
	public static final String 	RESULT_TYPE_DETAIL	=	"DemographicDetail";
	public static final String 	RESULT_TYPE_RESPONDENTS	=	"RespondentsList";
	//ended by edmund
	
}