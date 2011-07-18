package com.oifm.utility;
/*
 * Class Name:
 * Description:
 * 
 * 	Created By			Created/Modified on			Revision				Remarks
 * -----------------------------------------------------------------------------------------------------
 * 	Rajkumar			06/07/2005					Draft					Inital Version
 * 
 * -----------------------------------------------------------------------------------------------------
 */

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public class DateUtility 
{
    static Logger logger = Logger.getLogger(DateUtility.class.getName());
    
    public static Date getDateFromMMDDYYYYString(String textDate) throws Exception
    {
        Date correctDate = null;
        try
        {
            SimpleDateFormat sdfInput = new SimpleDateFormat("dd-MMM-yyyy");
            correctDate = sdfInput.parse(textDate);
        }
        catch(Exception e)
        {
            logger.error("getDateFromMMDDYYYYString - " + e);
            throw e;
        }
        return correctDate; 
    }

    public static Date getDateFromMMDDYYYYHHString(String textDate) throws Exception
    {
        Date correctDate = null;
        try
        {
            SimpleDateFormat sdfInput = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
            correctDate = sdfInput.parse(textDate);
        }
        catch(Exception e)
        {
            logger.error("getDateFromMMDDYYYYString - " + e);
            throw e;
        }
        return correctDate; 
    }

    public static Date getDateFromDDMMYYYYString(String textDate) throws Exception
    {
        Date correctDate = null;
        try
        {
            SimpleDateFormat sdfInput = new SimpleDateFormat("dd/MM/yyyy");
            correctDate = sdfInput.parse(textDate);
        }
        catch(Exception e)
        {
            logger.error(e.getMessage());
            throw e;
        }
        return correctDate; 
    }

    public static Date getDateFromYYYYDDMMString(String textDate) throws Exception
    {
        Date correctDate = null;
        try
        {
            SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy/dd/MM");
            correctDate = sdfInput.parse(textDate);
        }
        catch(Exception e)
        {
            logger.error(e.getMessage());
            throw e;
        }
        return correctDate; 
    }

    public static Date getDateFromYYYYMMDDString(String textDate) throws Exception
    {
        Date correctDate = null;
        try
        {
            SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy/MM/dd");
            correctDate = sdfInput.parse(textDate);
        }
        catch(Exception e)
        {
            logger.error(e.getMessage());
            throw e;
        }
        return correctDate; 
    }
//////////////////////////////////////////////////////////////////////////////
    public static String getMMDDYYYYStringFromDate(Date date) throws Exception
    {
        String textDate = null;
        try
        {
            if(date!=null){
            	SimpleDateFormat sdfInput = new SimpleDateFormat("dd-MMM-yyyy");
            	textDate = sdfInput.format(date);
            } else textDate = "";
        }
        catch(Exception e)
        {
            logger.error("getMMDDYYYYStringFromDate-" + e.getMessage());
            throw e;
        }
        return textDate; 
    }

    public static String getMMDDYYYYHHStringFromDate(Date date) throws Exception
    {
        String textDate = null;
        try
        {
            SimpleDateFormat sdfInput = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
            textDate = sdfInput.format(date);
        }
        catch(Exception e)
        {
            logger.error("getMMDDYYYYStringFromDate-" + e.getMessage());
            throw e;
        }
        return textDate; 
    }

    public static String getDDMMYYYYStringFromDate(Date date) throws Exception
    {
        String textDate = null;
        try
        {
        	if(date!=null){
        		SimpleDateFormat sdfInput = new SimpleDateFormat("dd/MM/yyyy");
            	textDate = sdfInput.format(date);
        	} else textDate = "";
        }
        catch(Exception e)
        {
            logger.error(e.getMessage());
            throw e;
        }
        return textDate; 
    }

    public static String getYYYYDDMMStringFromDate(Date date) throws Exception
    {
        String textDate = null;
        try
        {
            SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy/dd/MM");
            textDate = sdfInput.format(date);
        }
        catch(Exception e)
        {
            logger.error(e.getMessage());
            throw e;
        }
        return textDate; 
    }

    public static String getYYYYMMDDStringFromDate(Date date) throws Exception
    {
        String textDate = null;
        try
        {
            SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy/MM/dd");
            textDate = sdfInput.format(date);
        }
        catch(Exception e)
        {
            logger.error(e.getMessage());
            throw e;
        }
        return textDate; 
    }
    
    
    public static Date getDateFromDDMMMYYYYString(String textDate) throws Exception
    {
        Date correctDate = null;
        try
        {
            SimpleDateFormat sdfInput = new SimpleDateFormat("dd-MMM-yyyy");
            correctDate = sdfInput.parse(textDate);
        }
        catch(Exception e)
        {
            logger.error(e.getMessage());
            throw e;
        }
        return correctDate; 
    }

    public static String getDDMMMYYYYStringFromDate(Date date) throws Exception
    {
        String textDate = null;
        try
        {
            SimpleDateFormat sdfInput = new SimpleDateFormat("dd-MMM-yyyy");
            textDate = sdfInput.format(date);
        }
        catch(Exception e)
        {
            logger.error(e.getMessage());
            throw e;
        }
        return textDate; 
    }

    public static void main(String str[])
    {
        try
        {
            System.out.println(getDDMMMYYYYStringFromDate(new Date()));
            getDateFromDDMMMYYYYString("21-Jul-2005");
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
