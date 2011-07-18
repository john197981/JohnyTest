package com.oifm.search;

import java.util.ArrayList;

import com.oifm.base.OIBaseActionForm;

public class OIFormAdvancedSearch extends OIBaseActionForm
{
    private String key;
    private String inForum;
    private String inPaper;
    private String inSurvey;
    private String inBlog;
    private String searchBy;
    private String findThreads;
    private String findThreadsPost;
    private String forumSearchBy;
    private String forumSearchByDays;
    private String paperSearchBy;
    private String paperSearchByDays;
    private String surveySearchBy;
    private String surveySearchByDays;
    private String blogSearchBy;
    private String blogSearchByDays;
    private String searchByUser;
    private ArrayList arSearchBy;
    private ArrayList arFindThreads;
    private ArrayList arForumSearchBy;
    private ArrayList arPaperSearchBy;
    private ArrayList arSurveySearchBy;
    private ArrayList arBlogSearchBy;
    private String pageNo;
    private String moduleFlag;
    
    // Added by K.K.Kumaresan on Aug 17,2009
    private String inASM;
    private String asmSearchBy;
    private String asmSearchByDays;
    private ArrayList arASMSearchBy;
    /**
     * @return Returns the moduleFlag.
     */
    public String getModuleFlag() {
        return moduleFlag;
    }
    /**
     * @param moduleFlag The moduleFlag to set.
     */
    public void setModuleFlag(String moduleFlag) {
        this.moduleFlag = moduleFlag;
    }
    /**
     * @return Returns the pageNo.
     */
    public String getPageNo() {
        return pageNo;
    }
    /**
     * @param pageNo The pageNo to set.
     */
    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }
    public OIFormAdvancedSearch()
    {
        arSearchBy = new ArrayList();
        arFindThreads = new ArrayList();
        arForumSearchBy = new ArrayList();
        arPaperSearchBy = new ArrayList();
        arSurveySearchBy = new ArrayList();
        arBlogSearchBy = new ArrayList();
    }
    
    /**
     * @return Returns the arFindThreads.
     */
    public ArrayList getArFindThreads() {
        return arFindThreads;
    }
    /**
     * @param arFindThreads The arFindThreads to set.
     */
    public void setArFindThreads(ArrayList arFindThreads) {
        this.arFindThreads = arFindThreads;
    }
    public void addArFindThreads(Object obj) {
        this.arFindThreads.add(obj);
    }
    /**
     * @return Returns the arForumSearchBy.
     */
    public ArrayList getArForumSearchBy() {
        return arForumSearchBy;
    }
    /**
     * @param arForumSearchBy The arForumSearchBy to set.
     */
    public void setArForumSearchBy(ArrayList arForumSearchBy) {
        this.arForumSearchBy = arForumSearchBy;
    }
    public void addArForumSearchBy(Object obj) {
        this.arForumSearchBy.add(obj);
    }
    /**
     * @return Returns the arPaperSearchBy.
     */
    public ArrayList getArPaperSearchBy() {
        return arPaperSearchBy;
    }
    /**
     * @param arPaperSearchBy The arPaperSearchBy to set.
     */
    public void setArPaperSearchBy(ArrayList arPaperSearchBy) {
        this.arPaperSearchBy = arPaperSearchBy;
    }
    public void addArPaperSearchBy(Object obj) {
        this.arPaperSearchBy.add(obj);
    }
    /**
     * @return Returns the arSearchBy.
     */
    public ArrayList getArSearchBy() {
        return arSearchBy;
    }
    /**
     * @param arSearchBy The arSearchBy to set.
     */
    public void setArSearchBy(ArrayList arSearchBy) {
        this.arSearchBy = arSearchBy;
    }
    public void addArSearchBy(Object obj) {
        this.arSearchBy.add(obj);
    }
    /**
     * @return Returns the arSurveySearchBy.
     */
    public ArrayList getArSurveySearchBy() {
        return arSurveySearchBy;
    }
    /**
     * @param arSurveySearchBy The arSurveySearchBy to set.
     */
    public void setArSurveySearchBy(ArrayList arSurveySearchBy) {
        this.arSurveySearchBy = arSurveySearchBy;
    }
    public void addArSurveySearchBy(Object obj) {
        this.arSurveySearchBy.add(obj);
    }
    
    /**
     * @return Returns the arBlogSearchBy.
     */
    public ArrayList getArBlogSearchBy() {
        return arBlogSearchBy;
    }
    /**
     * @param arBlogSearchBy The arBlogSearchBy to set.
     */
    public void setArBlogSearchBy(ArrayList arBlogSearchBy) {
        this.arBlogSearchBy = arBlogSearchBy;
    }
    public void addArBlogSearchBy(Object obj) {
        this.arBlogSearchBy.add(obj);
    }
    /**
     * @return Returns the findThreads.
     */
    public String getFindThreads() {
        return findThreads;
    }
    /**
     * @param findThreads The findThreads to set.
     */
    public void setFindThreads(String findThreads) {
        this.findThreads = findThreads;
    }
    /**
     * @return Returns the findThreadsPost.
     */
    public String getFindThreadsPost() {
        return findThreadsPost;
    }
    /**
     * @param findThreadsPost The findThreadsPost to set.
     */
    public void setFindThreadsPost(String findThreadsPost) {
        this.findThreadsPost = findThreadsPost;
    }
    /**
     * @return Returns the forumSearchBy.
     */
    public String getForumSearchBy() {
        return forumSearchBy;
    }
    /**
     * @param forumSearchBy The forumSearchBy to set.
     */
    public void setForumSearchBy(String forumSearchBy) {
        this.forumSearchBy = forumSearchBy;
    }
    /**
     * @return Returns the forumSearchByDays.
     */
    public String getForumSearchByDays() {
        return forumSearchByDays;
    }
    /**
     * @param forumSearchByDays The forumSearchByDays to set.
     */
    public void setForumSearchByDays(String forumSearchByDays) {
        this.forumSearchByDays = forumSearchByDays;
    }
    /**
     * @return Returns the inForum.
     */
    public String getInForum() {
        return inForum;
    }
    /**
     * @param inForum The inForum to set.
     */
    public void setInForum(String inForum) {
        this.inForum = inForum;
    }
    /**
     * @return Returns the inPaper.
     */
    public String getInPaper() {
        return inPaper;
    }
    /**
     * @param inPaper The inPaper to set.
     */
    public void setInPaper(String inPaper) {
        this.inPaper = inPaper;
    }
    /**
     * @return Returns the inSurvey.
     */
    public String getInSurvey() {
        return inSurvey;
    }
    /**
     * @param inSurvey The inSurvey to set.
     */
    public void setInSurvey(String inSurvey) {
        this.inSurvey = inSurvey;
    }
    /**
     * @return Returns the key.
     */
    public String getKey() {
        return key;
    }
    /**
     * @param key The key to set.
     */
    public void setKey(String key) {
        this.key = key;
    }
    /**
     * @return Returns the paperSearchBy.
     */
    public String getPaperSearchBy() {
        return paperSearchBy;
    }
    /**
     * @param paperSearchBy The paperSearchBy to set.
     */
    public void setPaperSearchBy(String paperSearchBy) {
        this.paperSearchBy = paperSearchBy;
    }
    /**
     * @return Returns the paperSearchByDays.
     */
    public String getPaperSearchByDays() {
        return paperSearchByDays;
    }
    /**
     * @param paperSearchByDays The paperSearchByDays to set.
     */
    public void setPaperSearchByDays(String paperSearchByDays) {
        this.paperSearchByDays = paperSearchByDays;
    }
    /**
     * @return Returns the searchBy.
     */
    public String getSearchBy() {
        return searchBy;
    }
    /**
     * @param searchBy The searchBy to set.
     */
    public void setSearchBy(String searchBy) {
        this.searchBy = searchBy;
    }
    /**
     * @return Returns the searchByUser.
     */
    public String getSearchByUser() {
        return searchByUser;
    }
    /**
     * @param searchByUser The searchByUser to set.
     */
    public void setSearchByUser(String searchByUser) {
        this.searchByUser = searchByUser;
    }
    /**
     * @return Returns the surveySearchBy.
     */
    public String getSurveySearchBy() {
        return surveySearchBy;
    }
    /**
     * @param surveySearchBy The surveySearchBy to set.
     */
    public void setSurveySearchBy(String surveySearchBy) {
        this.surveySearchBy = surveySearchBy;
    }
    /**
     * @return Returns the surveySearchByDays.
     */
    public String getSurveySearchByDays() {
        return surveySearchByDays;
    }
    /**
     * @param surveySearchByDays The surveySearchByDays to set.
     */
    public void setSurveySearchByDays(String surveySearchByDays) {
        this.surveySearchByDays = surveySearchByDays;
    }
	/**
	 * @return the inBlog
	 */
	public String getInBlog()
	{
		return inBlog;
	}
	/**
	 * @param inBlog the inBlog to set
	 */
	public void setInBlog(String inBlog)
	{
		this.inBlog = inBlog;
	}
	/**
	 * @return the blogSearchBy
	 */
	public String getBlogSearchBy()
	{
		return blogSearchBy;
	}
	/**
	 * @param blogSearchBy the blogSearchBy to set
	 */
	public void setBlogSearchBy(String blogSearchBy)
	{
		this.blogSearchBy = blogSearchBy;
	}
	/**
	 * @return the blogSearchByDays
	 */
	public String getBlogSearchByDays()
	{
		return blogSearchByDays;
	}
	/**
	 * @param blogSearchByDays the blogSearchByDays to set
	 */
	public void setBlogSearchByDays(String blogSearchByDays)
	{
		this.blogSearchByDays = blogSearchByDays;
	}
	public ArrayList getArASMSearchBy() {
		return arASMSearchBy;
	}
	public void setArASMSearchBy(ArrayList arASMSearchBy) {
		this.arASMSearchBy = arASMSearchBy;
	}
	public String getAsmSearchBy() {
		return asmSearchBy;
	}
	public void setAsmSearchBy(String asmSearchBy) {
		this.asmSearchBy = asmSearchBy;
	}
	public String getAsmSearchByDays() {
		return asmSearchByDays;
	}
	public void setAsmSearchByDays(String asmSearchByDays) {
		this.asmSearchByDays = asmSearchByDays;
	}
	public String getInASM() {
		return inASM;
	}
	public void setInASM(String inASM) {
		this.inASM = inASM;
	}
	
	
}
