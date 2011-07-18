
package com.oifm.common;

public interface OIFunctionConstants {
	
	public static final String	ADMINISTRATION_TAB	=	"ADMINTB";

	public static final String	CREATE_PRIVATE_THREAD	=	"PVTTHRD";
	
	public static final String	MOVE_THREADS	=	"MOVTHRD";
	
	public static final String	LOCK_UNLOCK_THREAD	=	"LCKTHRD";
	
	public static final String	DELETE_THREAD	=	"DELTHRD";
	
	public static final String	EXPORT_THREAD	=	"EXPTHRD";
	
	public static final String	DELETE_POSTING	=	"DELPOST";
	
	public static final String	EDIT_POSTING	=	"EDTPOST";
	
	public static final String	MODERATE_THREADS_POSTINGS	=	"MODPOST";
	
	public static final String	MAINTAIN_CATEGORY_BOARD	=	"MTNCTBD";
	
	public static final String 	MAINTAIN_BOARD = "MNTBORD";
	
	public static final String	MAINTAIN_TOPIC	=	"MTNTOPI";
	
	public static final String	MAINTAIN_POLL	=	"MNTPOLL";
	
	public static final String	MAINTAIN_CONSULTATION_PAPER	=	"MNTCLPP";
	
	public static final String	PUBLISH_CONSULTATION_PAPER	=	"PUBCLPP";
	
	public static final String	MAINTAIN_SURVEY	=	"MNTSURV";
	
	public static final String	PUBLISH_SURVEY	=	"PUBSURV";
	
	public static final String	MAINTAIN_ROLES	=	"MNTROLE";
	
	public static final String	MAINTAIN_USER_PROFILE	=	"MNTPROF";
	
	public static final String	MAINTAIN_GROUPS	=	"MNTGROP";
	
	public static final String	MAINTAIN_CODES	=	"MNTCODE";
	
	public static final String	CONFIGURATION	=	"MNTCONF";
	
	public static final String	USER_RANKING	=	"MNTURNK";
	
	public static final String	AUDIT_TRAIL	=	"MNTAUDT";
	
	public static final String	GDS_USER	=	"GDSUSER";
	
	public static final String	TEMP_USER	=	"TMPUSER";
	
	public static final String  EDIT_THREAD		=	"EDTTHRD";
	
	public static final String	STICKY_THREAD	=	"STCKTHRD";
	
	public static final String ASM_REPS = "ASMREP";
	
	public static final String MAINTAIN_BLOG = "MNTBLOG";

	/* 
	 * FUNCTION CATEGORISATION
	 * 
	 * Added by Oscar
	 * 
	 */
	
	// Functions to to activate Admin Tab
	public static final String[] ADMIN_TAB = {MAINTAIN_CATEGORY_BOARD, MAINTAIN_TOPIC, MODERATE_THREADS_POSTINGS,
											  MAINTAIN_SURVEY, PUBLISH_SURVEY, MAINTAIN_CONSULTATION_PAPER, PUBLISH_CONSULTATION_PAPER,
											  MAINTAIN_POLL, MAINTAIN_ROLES, MAINTAIN_USER_PROFILE, MAINTAIN_GROUPS,
											  MAINTAIN_CODES, CONFIGURATION, USER_RANKING, AUDIT_TRAIL, MAINTAIN_BOARD, ASM_REPS,MAINTAIN_BLOG};
	
	// Discussion Forum Administration Functions
	public static final String[] ADMIN_DF = {MAINTAIN_CATEGORY_BOARD, MAINTAIN_BOARD, MAINTAIN_TOPIC, MODERATE_THREADS_POSTINGS};
	
	
	// Survey Administration Functions
	public static final String[] ADMIN_SU = {MAINTAIN_SURVEY, PUBLISH_SURVEY};
	
	// Consultation Paper Administration Functions
	public static final String[] ADMIN_CP = {MAINTAIN_CONSULTATION_PAPER, PUBLISH_CONSULTATION_PAPER};
	
	// Other Administration Functions
	public static final String[] ADMIN_OTHERS = {MAINTAIN_POLL, MAINTAIN_ROLES, MAINTAIN_USER_PROFILE, MAINTAIN_GROUPS,
												 MAINTAIN_CODES, CONFIGURATION, USER_RANKING, AUDIT_TRAIL};
	
	// Discussion Forum Functions
	public static final String[] WEBSITE_DF = {CREATE_PRIVATE_THREAD, LOCK_UNLOCK_THREAD, DELETE_THREAD, EXPORT_THREAD, EDIT_THREAD,
											   DELETE_POSTING, EDIT_POSTING, MOVE_THREADS, STICKY_THREAD};
	
	// ASM Administration Functions
	public static final String[] ADMIN_ASM = {ASM_REPS};
	
	//	 bLOG Administration Functions
	public static final String[] ADMIN_BLOG = {MAINTAIN_BLOG};

}