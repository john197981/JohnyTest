package com.oifm.forum;

public interface OIForumConstants 
{
//	Added by RajKumar

	public static final String K_categoryId = "categoryId";
    public static final String Q_CATEGORYID = "CATEGORYID";
    public static final String Q_NAME = "NAME";
    public static final String K_aOIBAForumCategory = "aOIBAForumCategory";
    
    public static final String FORUM_CATEGORY_FORM = "ForumCategoryForm";
    

//	#############################################################################    
//	########### Added by Sukumar for Threads, Postings and Moderation ###########
//  #############################################################################    
    
//	file paths for request handlers
    public static final String	USER_PROFILE	= 	"/ThreadUserProfile.do";
	public static final String	FORUM_HOME_DO	=	"/WebForumListingAction.do";
	public static final String	THREAD_LIST_DO	=	"/ThreadList.do";
	public static final String	THREAD_MAINTAIN_DO	=	"/ThreadMaintain.do";
	public static final String	POSTINGS_MAINTAIN_DO=	"/PostingMaintain.do";
	public static final String	THREAD_POST_MOD_DO	=	"/ThreadPostMod.do";
	public static final String	ERROR_DO	=	"/error.do";	
	
//	labels to forward for JSP files 
	public static final String	ERROR_PAGE		=	"ErrorPage";
	public static final String	ADMIN_PAGE		=	"AdminPage";
	public static final String	USER_PAGE		=	"UserPage";
	public static final String	POSTING_PAGE	=	"PostingPage";
	public static final String	POPUP_PAGE		=	"PopupPage";

	public static final String	STICKY_THREAD	= 	"S";	
	public static final String	BOOKMARK_THREAD	= 	"B";	
	
//	labels to acton keys
	public static final String	POSTING_LIST	=	"PostingList";
	public static final String	GOTO_TOPIC		=	"GoToTopic";
	public static final String	MOVE_THREAD_TO_TOPIC =	"MoveThreadToTopic";
	public static final String	GOTO_TOPIC_HRCY =	"GoToTopicHrcy";
	public static final String	MOVETO_TOPIC_HRCY	=	"MoveToTopicHrcy";
	public static final String	THREAD_LIST		=	"ThreadList";
	public static final String	THREAD_CREATE	=	"NewThread";
	public static final String	THREAD_EDIT		=	"EditThread";
	public static final String	THREAD_MODIFY	=	"ModifyThread";
	public static final String	THREAD_EXPORT	=	"ExportThread";
	public static final String	THREAD_LOCK		=	"LockThread";
	public static final String	THREAD_UNLOCK	=	"UnLockThread";	
	public static final String	THREAD_PRINT	=	"PrintThread";
	public static final String	THREAD_TRACK	=	"TrackThread";
	public static final String	THREAD_STICKY	=	"StickyThread";
	public static final String	THREAD_DELETE	=	"DeleteThread";
	public static final String	THREAD_ADMIN_STICKY	=	"AdminStickyThread";
	public static final String	HITS			=	"hits";
	
	/* Added/Modified by Aik Mun @ Jan 16, 2009 */
	public static final String	MARK_AS_READ	=	"markRead";
	public static final String	MARK_AS_UNREAD	=	"markUnRead";
	
	public static final String	ADD_POSTING_TO_THREAD	=	"AddThreadPosting";
	public static final String	APPEND_POSTING_TO_REPLY	=	"AppendPostingReply";
	public static final String	POST_MESSAGE	=	"PostMessage";
	public static final String	POSTING_EDIT	=	"PostingEdit";
	public static final String	POSTING_MODIFY	=	"PostingModify";
	public static final String	POSTING_DELETE	=	"PostingDelete";
	public static final String	POSTING_PREVIEW	=	"PostingPreview";
	public static final String	POSTING_QUOTE	=	"PostingQuote";
	
	
//	Thread Listing for moderation by admin
	public static final String	THREAD_MOD_LISTING	=	"ThreadModList";
	public static final String	THREAD_MODERATION	=	"ThreadModeration";
	public static final String	THREAD_MODERATION_EDIT	=	"ThreadModerationEdit";
	public static final String	THREAD_MOD_MODIFY	=	"ThreadModModify";
	
//	Postings Listing for moderation by admin
	public static final String	POSTS_MOD_LISTING	=	"PostsModList";
	public static final String	POSTS_MODERATION	=	"PostsModeration";
	public static final String	POSTS_MODERATION_EDIT	=	"PostsModerationEdit";
	public static final String	POSTS_MOD_MODIFY	=	"PostsModModify";
	
//	Smilies icons symbols and respective images 
//	public static final String[] SMILIES_SYMBOLS	=	{ ":huh:", ":o", ";)", ":P", ":D", ":lol:", "B)", ":rolleyes:", "<_<", ":)", ":angry:", ":(", ":unsure:", ":blink:", ":ph34r:" };
	public static final String[] SMILIES_SYMBOLS	=	{ ":huh:", ":o:", ":WN:", ":P:", ":D:", ":lol:", ":B:", ":rolleyes:", ":DY:", ":SM:", ":angry:", ":MD:", ":unsure:", ":blink:", ":ph34r:" };
	public static final String[] SMILIES_ICONS	=	{"/images/smilies/huh.gif", "/images/smilies/ohmy.gif", "/images/smilies/wink.gif", "/images/smilies/tongue.gif", "/images/smilies/biggrin.gif", "/images/smilies/laugh.gif", "/images/smilies/cool.gif", "/images/smilies/rolleyes.gif", "/images/smilies/dry.gif", "/images/smilies/smile.gif", "/images/smilies/mad.gif", "/images/smilies/sad.gif", "/images/smilies/unsure.gif", "/images/smilies/blink.gif", "/images/smilies/ph34r.gif" };

//	Added by Suresh

  //  public static final String FORUM_CATEGORY_FORM = "ForumCategoryForm";
	
	public static final String FLAG_A ="A";
	public static final String FLAG_B ="B";
	public static final String FLAG_C ="C";
	public static final String FLAG_D ="D";
	public static final String FLAG_Y ="Y";
	public static final String THREADFWD ="?hiddenAction="+POSTING_LIST+"&strThreadId=";
	//Anbu added
	public static final String FLAG_ADMIN ="ADMIN";
	public static final String	ADMIN_STICKY_THREAD	= 	"A";
	public static final String	SPL_STICKY_THREAD	= 	"I";

}
