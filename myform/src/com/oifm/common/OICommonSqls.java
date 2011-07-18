package com.oifm.common;

public interface OICommonSqls 
{
    public final static String CONFIGURATION_READ_ALL = "select CONFIGID,CAPTION,VALUE,UNIT,TAG from OI_AD_CONFIGURATION";
    public final static String CONFIGURATION_READ_DT = "select CONFIGID,CAPTION,VALUE,UNIT,TAG from OI_AD_CONFIGURATION where TRIM(UPPER(TAG))=TRIM(UPPER(?)) and trunc(SYSDATE)  between (select to_date(VALUE,'DD-MON-YYYY') from OI_AD_CONFIGURATION where TRIM(UPPER(TAG))=TRIM(UPPER(?))) and  (select to_date(VALUE,'DD-MON-YYYY') from OI_AD_CONFIGURATION where TRIM(UPPER(TAG))=TRIM(UPPER(?))) ";
    public final static String CONFIGURATION_READ = "select CONFIGID,CAPTION,VALUE,UNIT,TAG from OI_AD_CONFIGURATION where TRIM(UPPER(TAG))=TRIM(UPPER(?))";
    public final static String CONFIGURATION_SAVE = "update OI_AD_CONFIGURATION set VALUE=? where TRIM(UPPER(TAG))=TRIM(UPPER(?))";
}
