
package com.oifm.forum;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.oifm.base.OIBaseAction;
import com.oifm.common.OIResponseObject;
import com.oifm.login.OILoginConstants;
import com.oifm.utility.OIFormUtilities;

public class OIActionPosting extends OIBaseAction 
{
	private static Logger logger = Logger.getLogger(OIActionPosting.class);

    public ActionForward doIt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String strAction)
    {
    	String strForward = "";
    	String strRedirect = "";
    	String strError = "";
    	String strUserId = "";
    	ArrayList alFunctions = null;
    	HttpSession session = null;
    	OIBOPosting objBOPosting = new OIBOPosting();
    	OIFormPosting objPosting = (OIFormPosting)form;
    	OIBAPosting objPostingVo = new OIBAPosting();
    	OIResponseObject objResponseObject = null;
    	OIForumAccessInfo objAccessInfo = null;
    	strAction = (strAction != null)? strAction: "";
    	boolean isThreadUserBoard = false;
    	
    	try 
    	{
    	 	session = request.getSession();
			strUserId = (String)getSessionAttribute(request, OILoginConstants.USER_ID);
			alFunctions = (ArrayList)getSessionAttribute(request, OILoginConstants.FUNCTION_LIST);
	    	objPosting.setStrUserId(strUserId);
			if (session.getAttribute(OILoginConstants.NICKNAME)!=null)
			{
			    //objOIBVSendMail.setFrom(OIFormUtilities.chkIsNull(session.getAttribute(OILoginConstants.EMAIL).toString()));
			    objPosting.setStrNickName(OIFormUtilities.chkIsNull(session.getAttribute(OILoginConstants.NICKNAME).toString()));
			}
			else
			{
			    objPosting.setStrNickName("");
			}

	    	objPosting.setStrUserId(strUserId);
			objAccessInfo = new OIForumAccessInfo(alFunctions);
			logger.debug("######### user id : "+strUserId+" post Id : "+objPosting.getStrPostId() +" Thread Id : "+objPosting.getStrThreadId());
			if(strAction.equals(OIForumConstants.POST_MESSAGE))
			{
				objPosting.setStrPosting(objPosting.getStrPostingNew()+"<br>"+objPosting.getStrPosting());
				PropertyUtils.copyProperties(objPostingVo, objPosting);
				objResponseObject = objBOPosting.createPosting(objPostingVo);
				request.setAttribute("ExpressionsList", objResponseObject.getResponseObject("ExpressionsList"));
				request.setAttribute("message", objResponseObject.getResponseObject(OILoginConstants.K_MESSAGE));
				if(objResponseObject.getResponseObject("error") != null)
				{
					strRedirect = OIForumConstants.ERROR_DO+"?error="+objResponseObject.getResponseObject("error");
					request.setAttribute("pageName", "Posting");
				}
				else 
				{
					request.setAttribute("pageName", "StatusPage");
					request.setAttribute("refreshUrl", OIForumConstants.THREAD_MAINTAIN_DO+"?strThreadId="+objPostingVo.getStrThreadId());
				}
				strForward = OIForumConstants.POPUP_PAGE;
			} 
			else if(strAction.equals(OIForumConstants.POSTING_EDIT) || strAction.equals(OIForumConstants.POSTS_MODERATION_EDIT) || strAction.equals(OIForumConstants.POSTING_QUOTE))
			{
				PropertyUtils.copyProperties(objPostingVo, objPosting);
				objResponseObject = objBOPosting.getPostingDetails(objPostingVo);
				request.setAttribute("ExpressionsList", objResponseObject.getResponseObject("ExpressionsList"));
				objPostingVo = (OIBAPosting)objResponseObject.getResponseObject("objPostingVo");
				
				request.setAttribute("objPostingVo", objPostingVo);				
				OIBAThread objThreadVo = (OIBAThread)objResponseObject.getResponseObject("objThreadVo");
				request.setAttribute("objThreadVo", objThreadVo);
				
				if(strAction.equals(OIForumConstants.POSTING_QUOTE))
				{
					prepareContentForQuote(objPosting, objPostingVo);
				}
				else
				{
					objPostingVo.setStrPostingNew(objPostingVo.getStrPosting());
					objPostingVo.setStrPosting("");
		 		    PropertyUtils.copyProperties(objPosting, objPostingVo);
				}
				if(objResponseObject.getResponseObject("error") != null)
				{
					strRedirect = OIForumConstants.ERROR_DO+"?error="+objResponseObject.getResponseObject("error");
					request.setAttribute("pageName", "StatusPage");
				}
				else
				{
				    request.setAttribute("pageName", "Posting");
				}
				strForward = OIForumConstants.POPUP_PAGE;
			}
			else if(strAction.equals(OIForumConstants.POSTING_MODIFY) || strAction.equals(OIForumConstants.POSTS_MOD_MODIFY))
			{
				//objPosting.setStrPosting(objPosting.getStrPostingNew()+"<br>"+objPosting.getStrPosting());
				objPosting.setStrPosting(objPosting.getStrPostingNew());
				
				PropertyUtils.copyProperties(objPostingVo, objPosting);
				objResponseObject = objBOPosting.modifyPosting(objPostingVo, (strAction.equals(OIForumConstants.POSTS_MOD_MODIFY)));
				request.setAttribute("ExpressionsList", objResponseObject.getResponseObject("ExpressionsList"));
				request.setAttribute("message", objResponseObject.getResponseObject(OILoginConstants.K_MESSAGE));
				if(objResponseObject.getResponseObject("error") != null)
				{
					strRedirect = OIForumConstants.ERROR_DO+"?error="+objResponseObject.getResponseObject("error");
					request.setAttribute("pageName", "Posting");
				}
				else 
				{
					request.setAttribute("pageName", "StatusPage");
					if(strAction.equals(OIForumConstants.POSTING_MODIFY))
					{
						request.setAttribute("refreshUrl", OIForumConstants.THREAD_MAINTAIN_DO+"?strThreadId="+objPostingVo.getStrThreadId());
					}
					else if(strAction.equals(OIForumConstants.POSTS_MOD_MODIFY))
					{
						request.setAttribute("refreshUrl", OIForumConstants.THREAD_POST_MOD_DO+"?hiddenAction="+OIForumConstants.POSTS_MOD_LISTING);
					}
				}
				strForward = OIForumConstants.POPUP_PAGE;
			} 
			else if(strAction.equals(OIForumConstants.POSTING_DELETE))
			{
				PropertyUtils.copyProperties(objPostingVo, objPosting);
				objResponseObject = objBOPosting.deletePosting(objPostingVo);
				request.setAttribute("message", objResponseObject.getResponseObject(OILoginConstants.K_MESSAGE));
				if(objResponseObject.getResponseObject("error") != null)
				{
					strRedirect = OIForumConstants.ERROR_DO+"?error="+objResponseObject.getResponseObject("error");
				}
				else
				{
				    strRedirect = OIForumConstants.THREAD_MAINTAIN_DO+"?hiddenAction="+OIForumConstants.POSTING_LIST+"&strThreadId="+objPostingVo.getStrThreadId();
				}
			} 
			else
			{
			    strRedirect = OIForumConstants.ERROR_DO+"?error=NoAccess";
			}
    	} 
    	catch(Exception e) 
    	{
			e.printStackTrace();
	    	strRedirect = OIForumConstants.ERROR_DO+"?error=OIDEFAULT";
		} 
    	finally 
    	{
			if(!strForward.equals("") && objResponseObject.getResponseObject("error") != null && !objResponseObject.getResponseObject("error").equals("null") )
			{
				request.setAttribute("error", objResponseObject.getResponseObject("error"));
			}
			request.setAttribute("accessInfo", objAccessInfo);
		}
		
		if(!strRedirect.equals(""))
		{
			return new ActionForward(strRedirect);
		}
		else
		{
			return (mapping.findForward(strForward));
		}
	}	

    private void prepareContentForQuote(OIFormPosting objPosting, OIBAPosting objPostingVo) 
    {
      	//anbu added
    	//objPosting.setStrPostingNew("<br><br>QUOTE:<br><P><B>Posted By : </B>"+objPostingVo.getStrNickName()+"  <B> Posted On : </B>"+objPostingVo.getStrPostedOn()+"</P>"+OIUtilities.replaceNull(objPosting.getStrPostingNew()));
    	//objPosting.setStrPosting(objPostingVo.getStrPosting());
    	
    	/* Added/Modified by Aik Mun @ Jan 15, 2009 */
    	objPosting.setStrQuotePost("QUOTE:<br><P><B>Posted By : </B>"+objPostingVo.getStrNickName()+"  <B> Posted On : </B>"+objPostingVo.getStrPostedOn()+"</P>"+objPostingVo.getStrPosting());
    	
    	if(objPosting.getStrQuotePost().length()>4000){
    		objPosting.setStrQuotePost(objPosting.getStrQuotePost().substring(0,3990)+"...");
    	}
    	objPostingVo.setStrQuotePost(objPosting.getStrQuotePost());
    	objPosting.setStrPostId("");
    	objPosting.setStrThreadId(objPostingVo.getStrThreadId());
    }
}