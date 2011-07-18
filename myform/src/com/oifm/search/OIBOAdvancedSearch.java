package com.oifm.search;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseBo;
import com.oifm.common.OIDAOSendMail;
import com.oifm.common.OIPageInfoBean;
import com.oifm.common.OIResponseObject;
import com.oifm.forum.OIDAOPosting;
import com.oifm.utility.OIUtilities;

public class OIBOAdvancedSearch extends OIBaseBo
{
    Logger logger = Logger.getLogger(OIBOAdvancedSearch.class.getName());
    
    public OIResponseObject search(OIBASearchCriteria pOIBASearchCriteria,String pUserId)
    {
        try
        {
            getConnection();
            ArrayList token=null;
            String query = null;
            int pageNo = 1;
            if (pOIBASearchCriteria.getPageNo()!=null)
                pageNo = Integer.parseInt(pOIBASearchCriteria.getPageNo());
            if (pOIBASearchCriteria.getSearchBy()!=null & pOIBASearchCriteria.getSearchBy().trim().equalsIgnoreCase("By Entire Phrase"))
            {
                token = new ArrayList();
                token.add(pOIBASearchCriteria.getKey());
            }
            else
            {
                token = new OIBASimpleSearch().constructTokens(pOIBASearchCriteria.getKey());                    
            }
            System.out.println("OIBOAdvancedSearch: search - pOIBASearchCriteria.getModuleFlag() : "+pOIBASearchCriteria.getModuleFlag());
            if (pOIBASearchCriteria.getModuleFlag()!=null && pOIBASearchCriteria.getModuleFlag().toUpperCase().equals("S"))
            {
                OIDAOAdvancedSearch aOIDAOAdvancedSearch = new OIDAOAdvancedSearch();
                query = new OIBOSearch().constructSurveySearchQuery(token);
                if (pOIBASearchCriteria.getSurveySearchBy()!=null && pOIBASearchCriteria.getSurveySearchBy().trim().equalsIgnoreCase("From Last"))
                    query = query + OISearchSqls.QRY_ADVANCED_SEARCH_SU_POST_OPEN + pOIBASearchCriteria.getSurveySearchByDays() + OISearchSqls.QRY_ADVANCED_SEARCH_SU_POST_CLOSE; 
                logger.info("Survey-" + query);
                String countQuery = OISearchSqls.QRY_COUNT  + query + ")";
                int totalRecord = aOIDAOAdvancedSearch.getTotalRecordCount(countQuery,pUserId,connection);
                OIPageInfoBean aOIPageInfoBean = new OIPageInfoBean(pageNo,OIDAOSendMail.recPerPage(connection));
                aOIPageInfoBean.setTotalRec(totalRecord);
                
                String suQueryLimit = OISearchSqls.QRY_ROW1 + query + OISearchSqls.QRY_ROW2;
                ArrayList arOISearchResultBean = aOIDAOAdvancedSearch.getSearchResultWithinLimit(suQueryLimit,pUserId,aOIPageInfoBean.getStartRec(),aOIPageInfoBean.getEndRec(),connection);
                responseObject.addResponseObject("arOISearchResultBean",arOISearchResultBean);
                responseObject.addResponseObject("aSUOIPageInfoBean",aOIPageInfoBean);

            }
            if (pOIBASearchCriteria.getModuleFlag()!=null && pOIBASearchCriteria.getModuleFlag().toUpperCase().equals("F"))
            {
/*                logger.info(pOIBASearchCriteria.getKey());
                logger.info(pOIBASearchCriteria.getSearchBy());
                logger.info(pOIBASearchCriteria.getFindThreads());
                logger.info(pOIBASearchCriteria.getFindThreadsPost());
                logger.info(pOIBASearchCriteria.getForumSearchBy());
                logger.info(pOIBASearchCriteria.getForumSearchByDays());
                logger.info("search-Forum");*/
                OIDAOAdvancedSearch aOIDAOAdvancedSearch = new OIDAOAdvancedSearch();
                query = new OIBOSearch().constructForumSearchQuery(token);
                logger.info(new Boolean(pOIBASearchCriteria.getForumSearchBy()!=null).toString());
                logger.info(new Boolean(pOIBASearchCriteria.getForumSearchBy().trim().equalsIgnoreCase("From Last")).toString());
                logger.info(new Boolean(pOIBASearchCriteria.getFindThreads()!=null).toString());
                logger.info(pOIBASearchCriteria.getFindThreads());
                logger.info(new Boolean(pOIBASearchCriteria.getFindThreads().trim().equalsIgnoreCase("")).toString());
                if (pOIBASearchCriteria.getForumSearchBy()!=null && pOIBASearchCriteria.getForumSearchBy().trim().equalsIgnoreCase("From Last") && (pOIBASearchCriteria.getFindThreads()==null || pOIBASearchCriteria.getFindThreads().trim().equalsIgnoreCase("")))
                    query = query + OISearchSqls.QRY_ADVANCED_SEARCH_FM_POST_OPEN + pOIBASearchCriteria.getForumSearchByDays() + OISearchSqls.QRY_ADVANCED_SEARCH_SU_POST_CLOSE; 
                if (pOIBASearchCriteria.getFindThreads()!=null && pOIBASearchCriteria.getForumSearchBy()!=null && pOIBASearchCriteria.getForumSearchBy().trim().equalsIgnoreCase("From Last")  && pOIBASearchCriteria.getFindThreads().trim().equalsIgnoreCase("Atleast"))
                    query = query + OISearchSqls.QRY_ADVANCED_SEARCH_FM_POST_OPEN1 + pOIBASearchCriteria.getForumSearchByDays() + OISearchSqls.QRY_ADVANCED_SEARCH_SU_POST_CLOSE + OISearchSqls.QRY_ADVANCED_SEARCH_FM_POST_OPEN2 + pOIBASearchCriteria.getFindThreadsPost(); 
                if (pOIBASearchCriteria.getFindThreads()!=null && pOIBASearchCriteria.getForumSearchBy()!=null && pOIBASearchCriteria.getForumSearchBy().trim().equalsIgnoreCase("From Last")  && pOIBASearchCriteria.getFindThreads().trim().equalsIgnoreCase("Exactly"))
                    query = query + OISearchSqls.QRY_ADVANCED_SEARCH_FM_POST_OPEN1 + pOIBASearchCriteria.getForumSearchByDays() + OISearchSqls.QRY_ADVANCED_SEARCH_SU_POST_CLOSE + OISearchSqls.QRY_ADVANCED_SEARCH_FM_POST_OPEN3 + pOIBASearchCriteria.getFindThreadsPost(); 

                if (pOIBASearchCriteria.getFindThreads()!=null && pOIBASearchCriteria.getForumSearchBy()!=null && pOIBASearchCriteria.getFindThreads().trim().equalsIgnoreCase("Atleast"))
                    query = query + OISearchSqls.QRY_ADVANCED_SEARCH_FM_POST_OPEN4 + pOIBASearchCriteria.getFindThreadsPost(); 
                if (pOIBASearchCriteria.getFindThreads()!=null && pOIBASearchCriteria.getForumSearchBy()!=null && pOIBASearchCriteria.getFindThreads().trim().equalsIgnoreCase("Exactly"))
                    query = query + OISearchSqls.QRY_ADVANCED_SEARCH_FM_POST_OPEN5 + pOIBASearchCriteria.getFindThreadsPost(); 

                logger.info(query);
                String countQuery = OISearchSqls.QRY_COUNT  + query + ")";
                int totalRecord = aOIDAOAdvancedSearch.getTotalRecordCount(countQuery,pUserId,connection);
                OIPageInfoBean aOIPageInfoBean = new OIPageInfoBean(pageNo,OIDAOSendMail.recPerPage(connection));
                aOIPageInfoBean.setTotalRec(totalRecord);
                
                String FMQueryLimit = OISearchSqls.QRY_ROW1 + query + OISearchSqls.QRY_ROW2;
                ArrayList arOISearchResultBean = aOIDAOAdvancedSearch.getSearchResultWithinLimit(FMQueryLimit,pUserId,aOIPageInfoBean.getStartRec(),aOIPageInfoBean.getEndRec(),connection);
                responseObject.addResponseObject("arOISearchResultBean",arOISearchResultBean);
                responseObject.addResponseObject("aSUOIPageInfoBean",aOIPageInfoBean);
            }
            if (pOIBASearchCriteria.getModuleFlag()!=null && pOIBASearchCriteria.getModuleFlag().toUpperCase().equals("P"))
            {
                OIDAOAdvancedSearch aOIDAOAdvancedSearch = new OIDAOAdvancedSearch();
                query = new OIBOSearch().constructPaperSearchQuery(token);
                if (pOIBASearchCriteria.getPaperSearchBy()!=null && pOIBASearchCriteria.getPaperSearchBy().trim().equalsIgnoreCase("From Last"))
                    query = query + OISearchSqls.QRY_ADVANCED_SEARCH_CP_POST_OPEN + pOIBASearchCriteria.getPaperSearchByDays() + OISearchSqls.QRY_ADVANCED_SEARCH_SU_POST_CLOSE; 
                logger.info(query);
                String countQuery = OISearchSqls.QRY_COUNT  + query + ")";
                int totalRecord = aOIDAOAdvancedSearch.getTotalRecordCount(countQuery,pUserId,connection);
                OIPageInfoBean aOIPageInfoBean = new OIPageInfoBean(pageNo,OIDAOSendMail.recPerPage(connection));
                aOIPageInfoBean.setTotalRec(totalRecord);
                
                String cpQueryLimit = OISearchSqls.QRY_ROW1 + query + OISearchSqls.QRY_ROW2;
                ArrayList arOISearchResultBean = aOIDAOAdvancedSearch.getSearchResultWithinLimit(cpQueryLimit,pUserId,aOIPageInfoBean.getStartRec(),aOIPageInfoBean.getEndRec(),connection);
                assignExpressions(arOISearchResultBean);
                responseObject.addResponseObject("arOISearchResultBean",arOISearchResultBean);
                responseObject.addResponseObject("aSUOIPageInfoBean",aOIPageInfoBean);
            }
            if (pOIBASearchCriteria.getModuleFlag()!=null && pOIBASearchCriteria.getModuleFlag().toUpperCase().equals("B"))
            {
                OIDAOAdvancedSearch aOIDAOAdvancedSearch = new OIDAOAdvancedSearch();
                query = new OIBOSearch().constructBlogSearchQuery(token);
                if (pOIBASearchCriteria.getBlogSearchBy()!=null && pOIBASearchCriteria.getBlogSearchBy().trim().equalsIgnoreCase("From Last"))
                    query = query + OISearchSqls.QRY_ADVANCED_SEARCH_BG_POST_OPEN + pOIBASearchCriteria.getBlogSearchByDays() + OISearchSqls.QRY_ADVANCED_SEARCH_SU_POST_CLOSE; 
                logger.info("Blog-" + query);
                String countQuery = OISearchSqls.QRY_COUNT  + query + ")";
                int totalRecord = aOIDAOAdvancedSearch.getTotalRecordCount(countQuery,connection);
                OIPageInfoBean aOIPageInfoBean = new OIPageInfoBean(pageNo,OIDAOSendMail.recPerPage(connection));
                aOIPageInfoBean.setTotalRec(totalRecord);
                
                String suQueryLimit = OISearchSqls.QRY_ROW1 + query + OISearchSqls.QRY_ROW2;
                ArrayList arOISearchResultBean = aOIDAOAdvancedSearch.getSearchResultWithinLimit(suQueryLimit,aOIPageInfoBean.getStartRec(),aOIPageInfoBean.getEndRec(),connection);
                responseObject.addResponseObject("arOISearchResultBean",arOISearchResultBean);
                responseObject.addResponseObject("aSUOIPageInfoBean",aOIPageInfoBean);

            }
            // Added by K.K.Kumaresan on Aug 17,2009
            if (pOIBASearchCriteria.getModuleFlag()!=null && pOIBASearchCriteria.getModuleFlag().toUpperCase().equals("A"))
            {
            	logger.info("Advanced search for ASM" );
            	 OIDAOAdvancedSearch aOIDAOAdvancedSearch = new OIDAOAdvancedSearch();
            	query = new OIBOSearch().constructASMSearchQuery(token);
                if (pOIBASearchCriteria.getAsmSearchBy()!=null && pOIBASearchCriteria.getAsmSearchBy().trim().equalsIgnoreCase("From Last"))
                    query = query + " and SUBMITTEDON>(SYSDATE - "+ pOIBASearchCriteria.getAsmSearchByDays() + " )"; 
                logger.info("ASM query is-" + query);
                String countQuery = OISearchSqls.QRY_COUNT  + query + ")";
                int totalRecord = aOIDAOAdvancedSearch.getTotalRecordCount(countQuery,connection);
                OIPageInfoBean aOIPageInfoBean = new OIPageInfoBean(pageNo,OIDAOSendMail.recPerPage(connection));
                aOIPageInfoBean.setTotalRec(totalRecord);
                
                String suQueryLimit = OISearchSqls.QRY_ROW1 + query + OISearchSqls.QRY_ROW2;
                ArrayList arOISearchResultBean = aOIDAOAdvancedSearch.getSearchResultWithinLimit(suQueryLimit,aOIPageInfoBean.getStartRec(),aOIPageInfoBean.getEndRec(),connection);
                responseObject.addResponseObject("arOISearchResultBean",arOISearchResultBean);
                responseObject.addResponseObject("aSUOIPageInfoBean",aOIPageInfoBean);
            }
        }
        catch(Exception e)
        {
            error = e.getMessage();
            logger.error(e);
        }
        finally
        {
            freeConnection();
        }
        addToResponseObject();
        return responseObject;
    }
    
    private void assignExpressions(ArrayList alResultList) {
		OISearchResultBean objResult = null;
		String strImagesRoot = OIUtilities.getUploadFilesDir("OIFM.docroot");
		String strImgPart1 = "<img src='"+strImagesRoot;
		String strImgPart2 = "' border='0'>";
		String strPosting = "";
		if(alResultList != null && alResultList.size() > 0)	{
			for (int j=0; j<alResultList.size(); j++)	{
				objResult = (OISearchResultBean) alResultList.get(j);
				strPosting = OIDAOPosting.allocateExpressions(strImgPart1, strImgPart2, objResult.getStrDescription()); 
				objResult.setStrDescription(strPosting);
			}
		}
	}

    public OIResponseObject searchByUser(OIBASearchCriteria pOIBASearchCriteria,String pUserId)
    {
        try
        {
            getConnection();
            ArrayList token=null;
            String query = null;
            int pageNo = 1;
            if (pOIBASearchCriteria.getPageNo()!=null)
                pageNo = Integer.parseInt(pOIBASearchCriteria.getPageNo());

            OIDAOAdvancedSearch aOIDAOAdvancedSearch = new OIDAOAdvancedSearch();
            query = OISearchSqls.QRY_ADVANCED_SEARCH_BY_USER + "?" + OISearchSqls.QRY_ADVANCED_SEARCH_BY_USER1 + pOIBASearchCriteria.getSearchByUser() + OISearchSqls.QRY_ADVANCED_SEARCH_BY_USER2; 

            logger.info(query);
            String countQuery = OISearchSqls.QRY_COUNT  + query + ")";
            int totalRecord = aOIDAOAdvancedSearch.getTotalRecordCount(countQuery,pUserId,connection);
            OIPageInfoBean aOIPageInfoBean = new OIPageInfoBean(pageNo,OIDAOSendMail.recPerPage(connection));
            aOIPageInfoBean.setTotalRec(totalRecord);
            
            String FMQueryLimit = OISearchSqls.QRY_ROW1 + query + OISearchSqls.QRY_ROW2;
            ArrayList arOISearchResultBean = aOIDAOAdvancedSearch.getSearchResultWithinLimit(FMQueryLimit,pUserId,aOIPageInfoBean.getStartRec(),aOIPageInfoBean.getEndRec(),connection);
            responseObject.addResponseObject("arOISearchResultBean",arOISearchResultBean);
            responseObject.addResponseObject("aSUOIPageInfoBean",aOIPageInfoBean);
        }
        catch(Exception e)
        {
            error = e.getMessage();
            logger.error(e);
        }
        finally
        {
            freeConnection();
        }
        addToResponseObject();
        return responseObject;
    }
}
