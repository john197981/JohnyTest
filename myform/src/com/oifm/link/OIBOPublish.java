package com.oifm.link;

import java.util.ArrayList;
import org.apache.log4j.Logger;

import com.oifm.base.OIBaseBo;
import com.oifm.common.OIResponseObject;
import com.oifm.consultation.OIBVPaper;
import com.oifm.forum.OIBVForumListing;
import com.oifm.forum.OIDAOForumWebListing;
//import com.oifm.forum.OIForumConstants;
import com.oifm.home.OIDAOHome;

import com.oifm.survey.OIBASurvey;
import com.oifm.asm.ASMBVCommon;
//import com.oifm.survey.OISurveyConstants;
import com.oifm.utility.OIDBRegistry;

public class OIBOPublish extends OIBaseBo
{
    Logger logger = Logger.getLogger(OIBOPublish.class.getName());
    
    public OIResponseObject generateXML(String pUserId)
    {
        try
        {
            getConnection();
          //  ArrayList arOIBVHottestThread = new OIDAOForumWebListing().readHottestThread(pUserId,connection);
           // ArrayList arOIBVListing = new OIDAOPublish().readPostListing(pUserId,arOIBVHottestThread,connection);
            //logger.info(arOIBVListing.size() + "");
            String XMLData = "";//prepareStartXML();
           // XMLData = XMLData + prepareThreadXMLData("Hottest Discussions",arOIBVListing);
            ArrayList arOIBVLatestThread = new OIDAOHome().readLatestThread(pUserId,connection);
            ArrayList arOIBVLatestListing = new OIDAOPublish().readPostListing(pUserId,arOIBVLatestThread,connection);
            XMLData = XMLData + prepareThreadXMLData("Latest Discussions",arOIBVLatestListing);
            ArrayList arOIBVPaper = new OIDAOHome().readLatestPapers(pUserId,connection);
            XMLData = XMLData + preparePaperXMLData("Latest Consultation Papers",arOIBVPaper);
            ArrayList arOIBASurvey = new OIDAOHome().readLatestSurvey(pUserId,connection);
            XMLData = XMLData + prepareSurveyXMLData("Latest Surveys",arOIBASurvey);
			ArrayList arASMLetter = new OIDAOHome().readASMLetters(pUserId,connection);
			XMLData = XMLData + prepareASMXMLData("ASM",arASMLetter);
			// Added by Oscar for Enhancement 3
			ArrayList alBlogEntry = null;
			XMLData = XMLData + prepareBlogXMLData("Latest Blog Entries", alBlogEntry);
			// End Enhancement 3

            XMLData = XMLData + prepareEndXML();
            //System.out.println("XML DATA >>>>>>>>>>>>>>>>>>>> " + XMLData  );
            responseObject.addResponseObject("XMLData",XMLData);
        }
        catch(Exception e)
        {
            error = e.getMessage();
            logger.error("generateXML--" + e);
        }
        finally
        {
            freeConnection();
        }
        addToResponseObject();
        return responseObject;
    }
    
    public String preparePaperXMLData(String type,ArrayList arOIBVListing)
    {
        String str = "";
        OIBVPaper aOIBVPaper = null;
        try
        {
            if (arOIBVListing!=null)
            {
	            //for (int i=0;i<arOIBVListing.size();i++)
                for (int i=0;i<1;i++)
	            {
	                aOIBVPaper = (OIBVPaper) arOIBVListing.get(i);
	                str = str + "<item>";
		            str = str + "<category>" + type + "</category>";
		            aOIBVPaper.setPaperName(aOIBVPaper.getPaperName().replaceAll("‘","'"));
	                aOIBVPaper.setPaperName(aOIBVPaper.getPaperName().replaceAll("’","'"));
	                aOIBVPaper.setPaperName(aOIBVPaper.getPaperName().replaceAll("&","&amp;"));
	                aOIBVPaper.setPaperName(aOIBVPaper.getPaperName().replaceAll("\"","&#34;"));
	                aOIBVPaper.setPaperName(aOIBVPaper.getPaperName().replaceAll("`","&#39;"));
	                aOIBVPaper.setPaperName(aOIBVPaper.getPaperName().replaceAll(">","&#62;"));
	                aOIBVPaper.setPaperName(aOIBVPaper.getPaperName().replaceAll("<","&#60;"));
		            str = str + "<title>" + aOIBVPaper.getPaperName()  + "</title>";
	                aOIBVPaper.setDescription(aOIBVPaper.getDescription().replaceAll("‘","'"));
	                aOIBVPaper.setDescription(aOIBVPaper.getDescription().replaceAll("’","'"));
	                aOIBVPaper.setDescription(aOIBVPaper.getDescription().replaceAll("&","&amp;"));
	                aOIBVPaper.setDescription(aOIBVPaper.getDescription().replaceAll("\"","&#34;"));
	                aOIBVPaper.setDescription(aOIBVPaper.getDescription().replaceAll("`","&#39;"));
	                aOIBVPaper.setDescription(aOIBVPaper.getDescription().replaceAll(">","&#62;"));
	                aOIBVPaper.setDescription(aOIBVPaper.getDescription().replaceAll("<","&#60;"));
		            str = str + "<description>" + aOIBVPaper.getDescription()  + "</description>";
		            str = str + "<link>" + OIDBRegistry.getValues("OI.PUBLISH.URL") + "?id=" + aOIBVPaper.getPaperId() + "&amp;module=C</link>";
		            str = str + "<guid>" + OIDBRegistry.getValues("OI.PUBLISH.URL") + "?id=" + aOIBVPaper.getPaperId() + "&amp;module=C</guid>";
		            str = str + "<pubDate>" + aOIBVPaper.getStartDate()  + "</pubDate>";
		            //str = str + "<expiryOn>" + aOIBVPaper.getExpireDate()  + "</expiryOn>";
		            str = str + "</item>";
	            }
            }
        }
        catch(Exception e)
        {
            logger.error("preparePaperXMLData-" + e.getMessage());
        }
        return str;
    }
    public String prepareSurveyXMLData(String type,ArrayList arOIBVListing)
    {
        String str = "";
        OIBASurvey aOIBASurvey = null;
        try
        {
            if (arOIBVListing!=null)
            {
	            //for (int i=0;i<arOIBVListing.size();i++)
                for (int i=0;i<1;i++)
	            {
	                aOIBASurvey = (OIBASurvey) arOIBVListing.get(i);
	                str = str + "<item>";
		            str = str + "<category>" + type + "</category>";
	                aOIBASurvey.setStrSurveyName(aOIBASurvey.getStrSurveyName().replaceAll("‘","'"));
	                aOIBASurvey.setStrSurveyName(aOIBASurvey.getStrSurveyName().replaceAll("’","'"));
	                aOIBASurvey.setStrSurveyName(aOIBASurvey.getStrSurveyName().replaceAll("&","&amp;"));
	                aOIBASurvey.setStrSurveyName(aOIBASurvey.getStrSurveyName().replaceAll("\"","&#34;"));
	                aOIBASurvey.setStrSurveyName(aOIBASurvey.getStrSurveyName().replaceAll("`","&#39;"));
	                aOIBASurvey.setStrSurveyName(aOIBASurvey.getStrSurveyName().replaceAll(">","&#62;"));
	                aOIBASurvey.setStrSurveyName(aOIBASurvey.getStrSurveyName().replaceAll("<","&#60;"));
		            str = str + "<title>" + aOIBASurvey.getStrSurveyName()  + "</title>";
	                aOIBASurvey.setStrDescription(aOIBASurvey.getStrDescription().replaceAll("‘","'"));
	                aOIBASurvey.setStrDescription(aOIBASurvey.getStrDescription().replaceAll("’","'"));
	                aOIBASurvey.setStrDescription(aOIBASurvey.getStrDescription().replaceAll("&","&amp;"));
	                aOIBASurvey.setStrDescription(aOIBASurvey.getStrDescription().replaceAll("\"","&#34;"));
	                aOIBASurvey.setStrDescription(aOIBASurvey.getStrDescription().replaceAll("`","&#39;"));
	                aOIBASurvey.setStrDescription(aOIBASurvey.getStrDescription().replaceAll(">","&#62;"));
	                aOIBASurvey.setStrDescription(aOIBASurvey.getStrDescription().replaceAll("<","&#60;"));
		            str = str + "<description>" + aOIBASurvey.getStrDescription()  + "</description>";
		            str = str + "<link>" + OIDBRegistry.getValues("OI.PUBLISH.URL") + "?id=" + aOIBASurvey.getStrSurveyId() + "&amp;module=S</link>";
		            str = str + "<guid>" + OIDBRegistry.getValues("OI.PUBLISH.URL") + "?id=" + aOIBASurvey.getStrSurveyId() + "&amp;module=S</guid>";
		            str = str + "<pubDate>" + aOIBASurvey.getStrFromDate()  + "</pubDate>";
		          //  str = str + "<expiryOn>" + aOIBASurvey.getStrToDate()  + "</expiryOn>";
		            str = str + "</item>";
	            }
            }
        }
        catch(Exception e)
        {
            logger.error("prepareSurveyXMLData-- " + e.getMessage());
        }
        return str;
    }

	public String prepareASMXMLData(String type,ArrayList arOIBVListing)
    {
        String str = "";
        ASMBVCommon aASMLetter = null;
        try
        {
            if (arOIBVListing!=null)
            {
	            //for (int i=0;i<arOIBVListing.size();i++)
                for (int i=0;i<1;i++)
	            {
	                aASMLetter = (ASMBVCommon) arOIBVListing.get(i);
	                str = str + "<item>";
	                str = str + "<category>" +  type  + "</category>";
		            str = str + "<title>" +  aASMLetter.getHidRecLtrTopic()  + "</title>";
	                /*aASMLetter.setHidName(aASMLetter.getHidName().replaceAll("‘","'"));
	                aASMLetter.setHidName(aASMLetter.getHidName().replaceAll("’","'"));
	                aASMLetter.setHidName(aASMLetter.getHidName().replaceAll("&","&amp;"));
	                aASMLetter.setHidName(aASMLetter.getHidName().replaceAll("\"","&#34;"));
	                aASMLetter.setHidName(aASMLetter.getHidName().replaceAll("`","&#39;"));
	                aASMLetter.setHidName(aASMLetter.getHidName().replaceAll(">","&#62;"));
	                aASMLetter.setHidName(aASMLetter.getHidName().replaceAll("<","&#60;"));
		            //str = str + "<name>" + aASMLetter.getHidName()  + "</name>";*/
	                str = str + "<link>" + OIDBRegistry.getValues("OI.PUBLISH.URL") + "?id=" + aASMLetter.getHidRecLtrID() + "&amp;module=ASM</link>";
	                str = str + "<guid>" + OIDBRegistry.getValues("OI.PUBLISH.URL") + "?id=" + aASMLetter.getHidRecLtrID() + "&amp;module=ASM</guid>";
	                aASMLetter.setHidRecLtrTopic(aASMLetter.getHidRecLtrTopic().replaceAll("‘","'"));
	                aASMLetter.setHidRecLtrTopic(aASMLetter.getHidRecLtrTopic().replaceAll("’","'"));
	                aASMLetter.setHidRecLtrTopic(aASMLetter.getHidRecLtrTopic().replaceAll("&","&amp;"));
	                aASMLetter.setHidRecLtrTopic(aASMLetter.getHidRecLtrTopic().replaceAll("\"","&#34;"));
	                aASMLetter.setHidRecLtrTopic(aASMLetter.getHidRecLtrTopic().replaceAll("`","&#39;"));
	                aASMLetter.setHidRecLtrTopic(aASMLetter.getHidRecLtrTopic().replaceAll(">","&#62;"));
	                aASMLetter.setHidRecLtrTopic(aASMLetter.getHidRecLtrTopic().replaceAll("<","&#60;"));
		            str = str + "<description>" + aASMLetter.getHidRecLtrTopic()  + "</description>";
		            str = str + "<pubDate>" + aASMLetter.getHidRecLtrPubOn()  + "</pubDate>";
		           // str = str + "<expiryOn></expiryOn>";
		            str = str + "</item>";
	            }
            }
        }
        catch(Exception e)
        {
            logger.error("prepareASMXMLData-- " + e.getMessage());
        }
        return str;
    }

    public String prepareThreadXMLData(String type,ArrayList arOIBVListing)
    {
        String str = "";
        OIBVForumListing aOIBVForumListing = null;
        try
        {
            if (arOIBVListing!=null)
            {
	            //for (int i=0;i<arOIBVListing.size();i++)
                for (int i=0;i<3;i++)
	            {
	                aOIBVForumListing = (OIBVForumListing) arOIBVListing.get(i);
	                str = str + "<item>";
		            str = str + "<category>" + type + "</category>";
	                str = str + "<title>" + aOIBVForumListing.getThreadName() + "</title>";
		            aOIBVForumListing.setThreadName(aOIBVForumListing.getThreadName().replaceAll("‘","'"));
		            aOIBVForumListing.setThreadName(aOIBVForumListing.getThreadName().replaceAll("’","'"));
	                aOIBVForumListing.setThreadName(aOIBVForumListing.getThreadName().replaceAll("&","&amp;"));
	                aOIBVForumListing.setThreadName(aOIBVForumListing.getThreadName().replaceAll("\"","&#34;"));
	                aOIBVForumListing.setThreadName(aOIBVForumListing.getThreadName().replaceAll("`","&#39;"));
	                aOIBVForumListing.setThreadName(aOIBVForumListing.getThreadName().replaceAll(">","&#62;"));
	                aOIBVForumListing.setThreadName(aOIBVForumListing.getThreadName().replaceAll("<","&#60;"));
		            //str = str + "<name>" + aOIBVForumListing.getThreadName()  + "</name>";
	                str = str + "<description>" +  aOIBVForumListing.getThreadName() + "</description>";
		            str = str + "<link>" + OIDBRegistry.getValues("OI.PUBLISH.URL") + "?id=" + aOIBVForumListing.getThreadId()  + "&amp;module=F</link>";
		            str = str + "<guid>" + OIDBRegistry.getValues("OI.PUBLISH.URL") + "?id=" + aOIBVForumListing.getThreadId()  + "&amp;module=F</guid>";
		            str = str + "<pubDate>" + aOIBVForumListing.getCreatedOn()  + "</pubDate>";
		            //str = str + "<startedBy>" + aOIBVForumListing.getCreatedBy()  + "</startedBy>";
		            //str = str + "<lastPostOn>" + aOIBVForumListing.getLastPostOn()  + "</lastPostOn>";
		            //str = str + "<lastPostBy>" + aOIBVForumListing.getPostedBy()  + "</lastPostBy>";
		            //str = str + "<posts>" + aOIBVForumListing.getNumberOfPosts()  + "</posts>";
		            str = str + "</item>";
	            }
            }
        }
        catch(Exception e)
        {
            logger.error("prepareThreadXMLData--" + e.getMessage());
        }
        return str;
    }
    
    // Added by Oscar for Enhancement 3
    public String prepareBlogXMLData(String type,ArrayList arOIBVListing)
    {
        String str = "";
//        OIBASurvey aOIBASurvey = null;
        try
        {
            if (arOIBVListing!=null)
            {
                for (int i=0;i<1;i++)
	            {
//	                aOIBASurvey = (OIBASurvey) arOIBVListing.get(i);
	                str = str + "<item>";
		            //str = str + "<category>" + type + "</category>";
//	                aOIBASurvey.setStrSurveyName(aOIBASurvey.getStrSurveyName().replaceAll("‘","'"));
//	                aOIBASurvey.setStrSurveyName(aOIBASurvey.getStrSurveyName().replaceAll("’","'"));
//	                aOIBASurvey.setStrSurveyName(aOIBASurvey.getStrSurveyName().replaceAll("&","&amp;"));
//	                aOIBASurvey.setStrSurveyName(aOIBASurvey.getStrSurveyName().replaceAll("\"","&#34;"));
//	                aOIBASurvey.setStrSurveyName(aOIBASurvey.getStrSurveyName().replaceAll("`","&#39;"));
//	                aOIBASurvey.setStrSurveyName(aOIBASurvey.getStrSurveyName().replaceAll(">","&#62;"));
//	                aOIBASurvey.setStrSurveyName(aOIBASurvey.getStrSurveyName().replaceAll("<","&#60;"));
//		            str = str + "<name>" + aOIBASurvey.getStrSurveyName()  + "</name>";
//	                str = str + "<link>" + OIDBRegistry.getValues("OI.PUBLISH.URL") + "?id=" + aOIBASurvey.getStrSurveyId() + "&amp;module=BE</link>";
//	                aOIBASurvey.setStrDescription(aOIBASurvey.getStrDescription().replaceAll("‘","'"));
//	                aOIBASurvey.setStrDescription(aOIBASurvey.getStrDescription().replaceAll("’","'"));
//	                aOIBASurvey.setStrDescription(aOIBASurvey.getStrDescription().replaceAll("&","&amp;"));
//	                aOIBASurvey.setStrDescription(aOIBASurvey.getStrDescription().replaceAll("\"","&#34;"));
//	                aOIBASurvey.setStrDescription(aOIBASurvey.getStrDescription().replaceAll("`","&#39;"));
//	                aOIBASurvey.setStrDescription(aOIBASurvey.getStrDescription().replaceAll(">","&#62;"));
//	                aOIBASurvey.setStrDescription(aOIBASurvey.getStrDescription().replaceAll("<","&#60;"));
//		            str = str + "<description>" + aOIBASurvey.getStrDescription()  + "</description>";
//		            str = str + "<pubDate>" + aOIBASurvey.getStrFromDate()  + "</pubDate>";
//		            str = str + "<expiryOn>" + aOIBASurvey.getStrToDate()  + "</expiryOn>";
		            str = str + "</item>";
	            }
            }
        }
        catch(Exception e)
        {
            logger.error("prepareBlogXMLData-- " + e.getMessage());
        }
        return str;
    }
    // End Enhancement 3
    
/*    public String prepareStartXML()
    {
        String str = null;
        try
        {
	        str = "<?xml version=\"1.0\" ?>";
	        str = str + "<rss version=\"2.0\">";
	        str = str + "<channel>";
	        str = str + "<title>OIFM Links</title>";
	        str = str + "<link>" + OIDBRegistry.getValues("OI.PUBLISH.URL") +"</link>";
	        str = str + "<description>Link publishing to external applications</description>";
	        str = str + "<language>en-us</language>";
	        str = str + "<pubDate>" + DateUtility.getMMDDYYYYStringFromDate(new Date()) + "</pubDate>";
        }
        catch(Exception e)
        {
            logger.error(e.getMessage());
        }
        return str;
    }
*/    public String prepareEndXML()
    {
        String str = null;
        try
        {
	        str = "</channel>";
	        str = str + "</rss>";
        }
        catch(Exception e)
        {
            logger.error(e.getMessage());
        }
        return str;
    }
}