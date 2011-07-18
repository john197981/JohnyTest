package com.oifm.common;

/*
 * Class Name:
 * Description:
 * 
 * 	Created By			Created/Modified on			Revision				Remarks
 * -----------------------------------------------------------------------------------------------------
 * 	Sukumar				16/06/2005					Draft					Inital Version
 * 
 * -----------------------------------------------------------------------------------------------------
 */

 
public interface OILabelConstants {


//	Special constants as delimeters
	public static final String  COMMA			=	",";
	public static final String  AT				=	"@";
	public static final String	TAB				=	"\t"; 
	public static final String	LINE			=	"\n";
	public static final String	TABCOL				=	"</td><td>&nbsp;"; 
	public static final String	TABROW			=	"</td></tr><tr><td>";

//	Labels for Display
	public static final String	NO				=	"N";
	public static final String	YES				=	"Y";
	public static final String 	ZERO_AMT		=   "0.00";

	public static final String	TIME_FORMAT		=	"##:00;(##:00)";
	public static final String	HOURS_DEC		=	"9999999.99";
	public static final String	DECIMAL_FMT		=	"##########0.00";

// Period Names / Months
	public static final String[]	PERIOD_MONTH=	{"1", "JAN", "2", "FEB", "3", "MAR", "4", "APR", "5", "MAY", "6", "JUN","7","JUL","8","AUG","9","SEP","10","OCT","11","NOV","12","DEC"};
	public static final String[]	MONTHS		=	{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
	public static final String[]	TIME_HRS	=	{"0", "0:00", "1", "1:00", "2", "2:00", "3", "3:00", "4", "4:00", "5", "5:00", "6", "6:00", "7", "7:00", "8", "8:00", "9", "9:00", "10", "10:00", "11", "11:00", "12", "12:00", "13", "13:00", "14", "14:00", "15", "15:00", "16", "16:00", "17", "17:00", "18", "18:00", "19", "19:00", "20", "20:00", "21", "21:00", "22", "22:00", "23", "23:00"};

//	Date formats 
	public static final String INPUT_DATE_FORMAT	=	"DD-MON-YYYY";
	public static final String HEADER_DATE_FORMAT	=	"yy/MM/dd";
	public static final String DISPLAY_DATE_FORMAT	=	"DD-Mon-YYYY";
	public static final String DISPLAY_DATE_TIME_FORMAT	=	"DD-Mon-YYYY HH:MI:SS PM";

//	Labels by oifm
	public static final String BASE_ACTION		=	"actionName";
	public static final String ERROR_CODE		=	"errorCode";
	public static final String MESSAGE_CODE		=	"messageCode";

	
   /*************** Common Constants ****************************************/	
   public static final String BEGIN_METHOD		=  "Begining of doIt Method -";
   public static final String END_METHOD		=  "End of doIt Method - ";
   public static final String BEGIN_METHOD_BO	=  "Begining of BO Method - ";
   public static final String END_METHOD_BO		=  "End of BO Method - ";
   public static final String BEGIN_METHOD_DAO	=  "Begining of DAO Method - ";
   public static final String END_METHOD_DAO	=  "End of DAO Method - ";
   
   public static final String EXCEPTION_IN_ACTION ="Exception in Action Class";
   public static final String EXCEPTION_IN_BO =		"Exception in BO Class";
	   

   
   
   /*************** Start of Send Mail Constants ****************/	   	
   
   public static final String CONSULT_PAPER 	=	"C";
   public static final String SURVEY 			=	"S";
   public static final String SND_MAIL 			=	"M";
   public static final String SND_REM 			=	"R";
   public static final String SORT 				=	"Sort";
   public static final String REMOVE	 		=	"Remove";
   public static final String POPULATE	 		=	"populate";
   public static final String OBJARBV		 	=	"OBJARBV";
   public static final String ERROR		 		=	"error"; 
   public static final String ADDUSERS		 	=	"addusers";
   public static final String PAPERSURVEY		=   "papersurvery"; 
   
   
   
   //Forwards   
   public static final String SENDMAIL	 		=	"SendMail";
   public static final String CONSULTLIST		=	"/consultListingAction.do?hiddenAction=populate";
   public static final String SURVEYADMIN		=	"/SurveyAdmin.do?hiddenAction=SurveyAdminList";
   public static final String ERROR_FORWARD		=	"/error.do";
   public static final String SURVEYLIST		=   "SurveyList";  
   public static final String PAPERLIST			=   "PaperList"; 
   
   
   // Consultation Paper Constants for Subject & Body
   public static final String CP_MAIL_SUBJECT 	 = "CP_MAIL_SUBJECT";   
   public static final String CP_MAIL_BODY		 = "CP_MAIL_BODY";
   public static final String CP_REMINDER_SUBJECT ="CP_REMINDER_SUBJECT"; 
   public static final String CP_REMINDER_BODY    ="CP_REMINDER_BODY";
	 
   // Survery Constants for Subject & Body
   public static final String SU_MAIL_SUBJECT     = "SU_MAIL_SUBJECT";
   public static final String SU_MAIL_BODY        = "SU_MAIL_BODY";
   public static final String SU_REMINDER_SUBJECT = "SU_REMINDER_SUBJECT";
   public static final String SU_REMINDER_BODY    = "SU_REMINDER_BODY";
   public static final String ORDER_BY 			  = " ORDER BY ";
   public static final String SPACE				  = " ";
   public static final String ASC				  = " ASC ";
   public static final String DESC				  = " DESC ";
   public static final String ONE				  = " 1 ";
   public static final String TWO				  = " 2 ";
   public static final String PMMAIL			  = "pmmail";
   public static final String ADMINMAIL			  = "adminmail";
   public static final String REFERER 			  = "Referer";
   public static final String MAIL 				  = "mail";
   public static final String PLS_SELECT		  = "PleaseSlt";
   public static final String FLAG_N			  = "N";
   public static final String FLAG_Y			  = "Y";
   public static final String QUERY				  = "query";
   public static final String OBJFORM			  = "form";
   public static final String DUPNICKNAME		  = "dupnickname";
   public static final String VALIDIDS			  = "valid";
   public static final String INVALIDIDS		  = "invalid";
   public static final String DBQUOTES		 	  = "";
   public static final String MAILERROR			  = "mailerror";
   public static final String MODULE			  = "?module=";
   public static final String AMPID				  = "&id=";
   public static final String NEXTLN			  = "/n/n";
   public static final String INVALIDALONE		  = "invalidalone";
   public static final String NODOMAINS			  = "noDomains";
   public static final String K_aOIBASendMail	  = "aOIBASendMail";
   /*************** End of Send Mail Constants ****************/
   
}