package com.oifm.consultation;

public interface OIConsultConstant 
{
    //Query variables
    public static final String	Q_aName = "aName";
	public static final String	Q_aDivisionCode = "DivisionCode";
	public static final String	Q_aDivisionName = "aDivisionName";
    public static final String	Q_aCategoryId = "aCategoryId";
    public static final String	Q_aPaperId = "aPaperId";
    public static final String	Q_aTitle = "aTitle";
    public static final String	Q_aDescription = "aDescription";
    public static final String	Q_aFromDt = "aFromDt";
    public static final String	Q_aToDt = "aToDt";
    public static final String	Q_aMailStatus = "aMailStatus";
	public static final String	Q_aPublishedOn = "aPublishedOn";
    public static final String	Q_aNoFeedBacks = "aNoFeedBacks";
    
    //FEEDBACK QUERY
    public static final String	Q_feedback = "feedback";
    public static final String	Q_feedbackon = "feedbackon";
    public static final String	Q_userid = "userid";
    public static final String	Q_name = "name";
    public static final String	Q_totalCount = "totalCount";
    public static final String	Q_FEEDBACK1 = "FEEDBACK1";
    public static final String	Q_FEEDBACK2 = "FEEDBACK2";
    public static final String	Q_FEEDBACK_ON = "FEEDBACK_ON";
    public static final String	Q_PAPERID = "PAPERID";
    public static final String	Q_FEEDBACKID = "FEEDBACKID";
    public static final String	Q_USERID = "USERID";

    //CP_MEMBER Query
    public static final String	Q_INVITATION_DT = "INVITATION_DT";
    public static final String	Q_MEMBERID = "MEMBERID";
    public static final String	Q_SUBMITTED = "SUBMITTED";
    public static final String	Q_SUBMITTED_ON = "SUBMITTED_ON";
    
    //CP_RESPONSE Query
    public static final String	Q_QUESTIONID = "QUESTIONID";
    public static final String	Q_RESPONSEID = "RESPONSEID";
    public static final String	Q_REMARKS = "REMARKS";
    public static final String	Q_RESPONSE1 = "RESPONSE1";
    public static final String	Q_RESPONSE2 = "RESPONSE2";
    public static final String	Q_RESPONSE3 = "RESPONSE3";
    public static final String	Q_RESPONSE4 = "RESPONSE4";
    public static final String	Q_RESPONSE5 = "RESPONSE5";
    public static final String	Q_RESPONSE_ON = "RESPONSE_ON";
    public static final String	Q_CPSUID = "CPSUID";
    public static final String	Q_DRAFTID = "DRAFTID";
    public static final String	Q_DRAFTED_ON = "DRAFTED_ON";
    public static final String	Q_DRAFT_TYPE = "DRAFT_TYPE";
    public static final String	Q_STATUS = "STATUS";
    
    //CP_PAPER Query
    public static final String	Q_ACTIVE = "ACTIVE";
    public static final String	Q_CATEGORYID = "CATEGORYID";
    public static final String	Q_NAME = "NAME";
    public static final String	Q_DESCRIPTION = "DESCRIPTION";
    public static final String	Q_TO_DT = "TO_DT";
    public static final String	Q_FROM_DT = "FROM_DT";
    public static final String	Q_TITLE = "TITLE";
    public static final String	Q_SECURITY = "SECURITY";
    public static final String	Q_ATTACHED_FILE = "ATTACHED_FILE";
    public static final String	Q_CREATED_BY = "CREATED_BY";
    public static final String	Q_MAIL_STATUS = "MAIL_STATUS";
    public static final String	Q_REMINDER_MODE = "REMINDER_MODE";
    public static final String	Q_SUMMARY = "SUMMARY";
    public static final String	Q_TARGETAUDIENCE = "TARGETAUDIENCE";
	public static final String	Q_CONTACTPERSON = "CONTACTPERSON";
	public static final String	Q_PHONE = "PHONE";
	public static final String	Q_EMAILADDRESS = "EMAILADDRESS";
    public static final String	Q_PUBLISHED_ST = "PUBLISHED_ST";
    public static final String	Q_PUBLISHED_ON = "PUBLISHED_ON";
    public static final String	Q_ATTACHED_SUM = "ATTACHED_SUM";
    public static final String	Q_QUESTION = "QUESTION";
    public static final String	Q_ANSWER_TYPE = "ANSWER_TYPE";
    public static final String	Q_ANSWER1 = "ANSWER1";
    public static final String	Q_ANSWER2 = "ANSWER2";
    public static final String	Q_ANSWER3 = "ANSWER3";
    public static final String	Q_ANSWER4 = "ANSWER4";
    public static final String	Q_ANSWER5 = "ANSWER5";

    
    //Keys
    public static final String	K_arOIBVConsultListingFeedBack = "arOIBVConsultListingFeedBack";
    public static final String	K_arOIBVConsultListing = "arOIBVConsultListing";
	public static final String	K_arOIBVConsultDivision = "arOIBVConsultDivision";
	public static final String	K_arOIBVConsultPaper = "arOIBVConsultPaper";
    public static final String	K_aOIBAConsultCategory = "aOIBAConsultCategory";
    public static final String	K_arOIBAConsultCategory = "arOIBAConsultCategory";
    public static final String	K_refresh = "refresh";

    
    //Consult WEB
    public static final String	K_aOIBAConsultPaper = "aOIBAConsultPaper";
    public static final String	K_arOIBAConsultQuestion = "arOIBAConsultQuestion";
    public static final String	K_arOIBAResponse = "arOIBAResponse";
    public static final String	K_aOIBAFeedBack = "aOIBAFeedBack";
    public static final String	K_aOIBADraft = "aOIBADraft";
    public static final String	K_aOIBAMember = "aOIBAMember";
    public static final String	K_arOIBVPaperPresent = "arOIBVPaperPresent";
    public static final String	K_arOIBVPaperPast = "arOIBVPaperPast";
    public static final String	K_arOIBAConsultNameEmail = "arOIBAConsultNameEmail";
    //Consult Admin
    public static final String	K_arOIBACodeMaster = "arOIBACodeMaster";
    public static final String	K_arOIBVFeedBack = "arOIBVFeedBack";
    public static final String	K_aOIPageInfoBean = "aOIPageInfoBean";
    public static final String	K_Open = "Open";
    public static final String	K_Closed = "Closed";
    public static final String	K_currentPage = "currentPage";
    public static final String	K_pageNo = "pageNo";
    public static final String	K_paperId = "paperId";
    public static final String	K_nextSet = "nextSet";
    public static final String	K_previousSet = "previousSet";
    public static final String	K_nextPage = "nextPage";
    public static final String	K_previousPage = "previousPage";
    public static final String	K_arPage = "arPage";
    public static final String	K_summaryFile = "summaryFile";
    public static final String	K_questionId = "questionId";
    public static final String	K_attachedFileName = "attachedFile";
    public static final String	K_aOIBAConsultQuestion = "aOIBAConsultQuestion";
    public static final String	K_categoryId = "categoryId";

    //Struts Mappings

	
    public static final String POPULATE_CONSULTLISTING = "populate";
	public static final String POPULATE_PAPER_CONSULTLISTING = "populatePaper";


    //Form bean variables
    public static final String	CONSULT_PRESENT_PAPER_FORM_WEB = "ConsultPresentPaperForm";
    public static final String	CONSULT_LISTING_PAPER_FORM_WEB = "ConsultListingFormWeb";
    public static final String	CONSULT_PAST_PAPER_FORM_WEB = "ConsultPastPaperForm";
    public static final String	CONSULT_CATEGORY_FORM = "ConsultCategoryForm";
    public static final String	CONSULT_CREATE_PAPER_FORM = "ConsultPageForm";
    public static final String	CONSULT_QUESTION_FORM = "ConsultQuestionForm";
    public static final String	CONSULT_LISTING_FORM = "ConsultListingForm";
    public static final String	CONSULT_PUBLISH_FORM = "ConsultPublishForm";
	public static final String	CONSULT_LISTING_DO	=	"/consultListingAction.do";
	public static final String	CATEGORY_LISTING_FORM = "ASMCategoryForm";	 
}
