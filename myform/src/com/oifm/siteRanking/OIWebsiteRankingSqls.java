package com.oifm.siteRanking;

public interface OIWebsiteRankingSqls 
{
	public final String SAVE = "insert into OI_AD_SITERANK (RANKID, USERID, ACTIONID,  ACTIONTIME, ITEMID) " +
								"values (SEQ_OI_AD_RANKID.NEXTVAL,?,?,SYSDATE,?)";
}
