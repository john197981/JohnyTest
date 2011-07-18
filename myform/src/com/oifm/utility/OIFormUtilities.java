package com.oifm.utility;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;


public class OIFormUtilities {

    /** logger instance */
	private static Logger logger = Logger.getLogger(OIFormUtilities.class);

    public static String setDateStr(String aDateStr) {
        SimpleDateFormat sdf = null;
        String dateStr = "";
        try {
            sdf = new SimpleDateFormat("MM/dd/yyyy");
            dateStr = sdf.format(sdf.parse(aDateStr));
        } catch (ParseException e) {
            logger.warn("invalid date : " + aDateStr + " : " + e.getMessage());
        }
        return dateStr;
    }

    public static boolean isNotEmpty(String aString) {
        return (aString != null) && (aString.length() > 0);
    }

    public static boolean isEmpty(String aString) {
        return !(isNotEmpty(aString));
    }

    public static boolean isValidEmail(String aEmail) {
        boolean valid = true;

		if (aEmail == null) {
            valid = false;
            logger.warn("Email : "+aEmail+" invalid");
        }

        if (valid) {
	        String[] invalid = { " ", "#", "&", ":", ";", "^", "%", "!", "*", "(", ")" };
            for (int i = 0; i < invalid.length; i++) {
                if (aEmail.indexOf(invalid[i]) >= 0) {
                    valid = false;
                    logger.warn("Email: "+aEmail+" invalid ");
                }
            }
        }

        if (valid) {
            int atFound = aEmail.indexOf("@");
            int dotFound = aEmail.indexOf(".", atFound);
            if ((atFound < 0) || (dotFound < 0) || ((atFound + 1) == dotFound)) {
                valid = false;
                logger.warn("Email: "+aEmail+" invalid");
            }
        }

        return valid;
    }

    public static boolean isValidDate(String aDate) {
        boolean valid = true;

        try {
            if (aDate.length() != 10) {
                return false;
            }
            String m = aDate.substring(0, 2);
            String d = aDate.substring(3, 5);
            String y = aDate.substring(6, 10);

            Integer.parseInt(m);
            Integer.parseInt(d);
            Integer.parseInt(y);
        } catch (Exception e) {
            logger.warn("Date: "+aDate+" invalid");
            return false;
        }
        return valid;
    }

    public static boolean isValidNumber(String aString) {
        boolean valid = true;

        try {
            long tmpLong = Long.parseLong(aString);
            logger.debug("tmpLong : "+tmpLong);
        } catch (NumberFormatException nfe) {
            logger.warn("Number: "+aString+" invalid");
            return false;
        }
        return valid;
    }

    public static boolean isValidStrNumber(String aStr, String aDelimitator) {
        String tmpStr = "";
        int location;
        boolean valid = true;

        location = aStr.indexOf(aDelimitator);
        if (location < 0) {
            tmpStr = aStr;
        } else {
            tmpStr = aStr.replaceFirst("\\" + aDelimitator, "");
        }
        valid = isValidNumber(tmpStr);

        return valid;
    }

//	Upload file new name based on id
	public static String getFileName(String prefix, int id)	{
		NumberFormat formatter = new DecimalFormat("00000000");
		String rtnStr = prefix + formatter.format(id) + ".pdf";
		return rtnStr;
	}

    /**
     * Generic string representation of object using reflection.
     *
     * @return String string representation fo this object
     */
    public static String toString(Object objInput) {
        StringBuffer results = new StringBuffer();
        Class clazz = objInput.getClass();

        results.append(objInput.getClass().getName() + "[\n");
        Field[] fields = clazz.getDeclaredFields();

        try {
            AccessibleObject.setAccessible(fields, true);
            for (int i = 0; i < fields.length; i++) {
                results.append("\t" + fields[i].getName() + "=" +
                    fields[i].get(objInput) + "\n");
            }
        } catch (Exception e) {
            // ignored!
        }
        results.append("]");
        return results.toString();
    }
    
    /**
	 * This method checks the given String object is null, if yes returns null String
	 * 
	 * @author Suresh kumar R
	 * @param String string
	 * @return String
	 * @see chkIsNullToY
	 * @see chkIsNullToN
	 * @see chkIsNullZero
	 */
	public static String chkIsNull(String string) {
		//logger.info(Globals.BEGNING_OF_METHOD_SPUTILS + "chkIsNull");
		String strTmp = string;
		try {
			if (strTmp == null || strTmp.equals("null")) {
				strTmp = "";
			}
			strTmp = strTmp.trim();
			//logger.info(Globals.END_OF_METHOD_SPUTILS + "chkIsNull");
			return strTmp;
		} finally {
			strTmp = null;
		}
	}
	

	/**
	 * This method checks the given String object is null , if yes returns '0' (Zero)
	 * 
	 * @author ArunKumar SG
	 * @param String string
	 * @return String
	 * @see replaceToZero
	 */
	public static String replaceToZero(String string) {
		String strTmp = string;
		try {
			if (strTmp == null || strTmp.equals("null")|| strTmp.trim().length()==0) {
				strTmp = "0";
			}
			 
			return strTmp.trim();
		} finally {
			strTmp = null;
		}
		//return chkIsNull(string).equals("") ? "0" : string.trim();
	}
	
	
	
} 

