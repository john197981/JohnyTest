package com.oifm.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
//import java.io.Writer;
import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import oracle.sql.CLOB;

import org.apache.log4j.Logger;

import com.oifm.common.OIComboVO;
import com.oifm.common.OILabelConstants;

public class  OIUtilities	{
	private static Logger logger = Logger.getLogger(OIUtilities.class);

	private static DecimalFormat formatter = new DecimalFormat(OILabelConstants.DECIMAL_FMT);

	public static String getUploadFilesDir(String uploadDirKeyName)	{
		String filesDirPath = "";
		Locale currentLocale = Locale.getDefault();			
		ResourceBundle myResources = ResourceBundle.getBundle("OIProperties", currentLocale);
		filesDirPath = myResources.getString(uploadDirKeyName); 
		return filesDirPath;
	}
	
	//Seperates all delimeter strings into vector and retuns vector
	public static Vector delimStringToVector(String delimString)	{
		return delimStringToVector(delimString, OILabelConstants.COMMA);
	}

	public static Vector delimStringToVector(String delimString,String delimeter)	{
		Vector vctDelim = new Vector();
		StringTokenizer sttr = new StringTokenizer(delimString, delimeter);
		while (sttr.hasMoreTokens()) 
			vctDelim.addElement(sttr.nextToken());
		return vctDelim;
	}

	//Properties Object to read information from file
	public String getProperties(String key, String path) throws FileNotFoundException,IOException {
		String rtnValue = "";
		Properties prop = null;
		InputStream is = null;
		try{
			prop = new Properties();
			is = new FileInputStream(path);
			prop.load(is);
			rtnValue = prop.getProperty(key);
		}catch(FileNotFoundException fe){
			throw new FileNotFoundException("Exception! in getProperties() of Utilities : \n key :"+key+"\t path :"+path+"\n"+fe);
		}catch(IOException ie){
			throw new IOException("Exception! in getProperties() of Utilities : \n key :"+key+"\t path :"+path+"\n"+ie);
		}
		return rtnValue;
	}

	//Making SQL IN String
	public String sqlINString(Vector vctINStr)	{
		String inString = "";
		if(vctINStr !=null && vctINStr.size()>0)	{
			for(int i=0;i<vctINStr.size();i++)	{
				inString += ",'"+(String)vctINStr.elementAt(i)+"'";
			}
		}
		if(inString.length() > 0) 
			inString = inString.substring(1);
		return inString;
	}

	public static int getInt(String strIn) {
		int rtnVal;
		try {
			if(strIn == null || strIn.equals("")) rtnVal = 0;
			else rtnVal = Integer.parseInt(strIn.trim());
		}
		catch (NumberFormatException ne) { rtnVal = 0; }
		return rtnVal;
	}

	public static int getIntValue(Integer integ){
		if(integ == null)return 0;
		else return integ.intValue();
	}

	public static double getDouble(String strIn) {
		double rtnVal;
		try {
			if(strIn == null || strIn.equals("")) rtnVal = 0;
			else rtnVal = Double.parseDouble(strIn.trim());
		}
		catch (NumberFormatException ne) { rtnVal = 0; }
		return rtnVal;
	}

	public static String checkNull(String strTemp)	{
		if(strTemp == null)	strTemp = "";
			if(strTemp != null && strTemp.equalsIgnoreCase("null"))
				strTemp="";
		return strTemp.trim();
	}

	public static String getCurrentDate(){
		return getCurrentDateTime("dd/MMM/yyyy");
	}

	public static String getCurrentDateTime(String format){
		java.util.Date dt = new java.util.Date();
		SimpleDateFormat sf = new SimpleDateFormat(format);
		String toDate = sf.format(dt);
		return toDate;
	}

	public static String getNextMonthDate(){
		Calendar calDate = Calendar.getInstance();
		calDate.add(Calendar.MONTH, 1);
		return formatDate(calDate.getTime());
	}

	public static int getDaysInMonth(boolean isCurrentMonth) {
		Calendar calDate = Calendar.getInstance();
		calDate.add(Calendar.MONTH, ((isCurrentMonth)?0:1));
		return calDate.getActualMaximum(Calendar.DATE);		
	}

	public static String formatDate(java.util.Date dt){
		SimpleDateFormat sf = new SimpleDateFormat("dd/MMM/yyyy");
		String toDate = sf.format(dt);
		return toDate;
	}

	public static String arrayToString(String[] strArr) {
		StringBuffer stbr = new StringBuffer();
		String rtnStr = "";
		if(strArr != null && strArr.length > 0) {
			for(int i=0; i < strArr.length; i++) stbr.append(","+strArr[i]);
			rtnStr = stbr.substring(1);
		}
		return rtnStr;
	}
	
	public static String arrayToSqlString(String[] strArr) {
		StringBuffer stbr = new StringBuffer();
		String rtnStr = "";
		if(strArr != null && strArr.length > 0) {
			for(int i=0; i < strArr.length; i++) stbr.append(",'"+strArr[i]+"'");
			rtnStr = stbr.substring(1);
		}
		return rtnStr;
	}
	
	public static String returnEmptyIfNull(String strTemp)	{
		return (strTemp == null)?"":((strTemp.trim().equalsIgnoreCase("null"))?"":strTemp.trim());
	}
	
	//added by edmund
	public static String returnWithSeparate(String strTemp, String strTemp2) {
		if(strTemp == null || strTemp.trim().equalsIgnoreCase("null")){
			return "";
		}
		else if(strTemp2 != null || !strTemp2.trim().equalsIgnoreCase("null")){
			return "," + strTemp;
		}
		else {
			return strTemp;
		}
	}

	public static boolean isSameValue(String str1, String str2)	{
		return (str1 == null)? false:((str1.trim().equalsIgnoreCase(str2))?true:false);
	}
	
	public static boolean checkBlank(String strIn)	{
		if(strIn != null && !(strIn.trim().equals(""))) return true;
		else return false;
	}

	public static String checkHtbValue(String key, HashMap htbCont) {
		String str = "";
		if(htbCont != null && htbCont.get(key) != null)
			str = (String)htbCont.get(key);
		return str;
	}

	public static String getNewFormat(String strDateIn, String inputFormat, String outputFormat)	{
		SimpleDateFormat inSDF = new SimpleDateFormat(inputFormat);
        Date inputDate = inSDF.parse(strDateIn, new ParsePosition(0));
		inSDF.applyPattern(outputFormat);
        return inSDF.format(inputDate);
	}

	//	Input dates in format of DD/MMM/YYYY.
	public static int getDateDifference(String strDate1, String strDate2) {
		SimpleDateFormat inSDF = new SimpleDateFormat("dd/MMM/yyyy");
		Date date1 = inSDF.parse(strDate1, new ParsePosition(0));
		Date date2 = inSDF.parse(strDate2,  new ParsePosition(0));
		double diff = Math.ceil((date2.getTime() - date1.getTime())/(24*60*60*1000));
		return (int)diff;
	}

	//	Input date in format of DD/MMM/YYYY.
	public static int getDateMonthYear(String strDate, int fieldName) {
		int rtnVal = 0;
		if(strDate != null && !strDate.equals("")) {
			SimpleDateFormat inSDF = new SimpleDateFormat("dd/MMM/yyyy");
			Date inDate = inSDF.parse(strDate, new ParsePosition(0));
			Calendar calDate = Calendar.getInstance();
			calDate.setTime(inDate);
			rtnVal = calDate.get(fieldName);
		}
		return rtnVal;
	}

	public static int getCurrentDateMonthYear(int fieldName) {
		Calendar calDate = Calendar.getInstance();
		int rtnVal = calDate.get(fieldName);
		return rtnVal;
	}

	public static String incDate(String strDate, int noDays) {
		Date outDate = null;
		SimpleDateFormat inSDF = new SimpleDateFormat("dd/MMM/yyyy");
		if(strDate != null && !strDate.equals("")) {
			Date inDate = inSDF.parse(strDate, new ParsePosition(0));
			Calendar calDate = Calendar.getInstance();
			calDate.setTime(inDate);
			calDate.add(Calendar.DATE, noDays);
			outDate = calDate.getTime();
		}
		return inSDF.format(outDate);
	}

	public static String incDate(String strDate, int noDays, String currentDF, String newDF) {
		Date outDate = null;
		SimpleDateFormat inSDF = new SimpleDateFormat(currentDF);
		SimpleDateFormat outSDF = new SimpleDateFormat(newDF);
		if(strDate != null && !strDate.equals("")) {
			Date inDate = inSDF.parse(strDate, new ParsePosition(0));
			Calendar calDate = Calendar.getInstance();
			calDate.setTime(inDate);
			calDate.add(Calendar.DATE, noDays);
			outDate = calDate.getTime();
		}
		return outSDF.format(outDate);
	}
	
	public static String setDateFormat(Date strDate) {
		Date outDate = null;
		SimpleDateFormat outSDF = new SimpleDateFormat("dd-MMM-yy HH:MI:SS");

		if(strDate != null ) {

			Calendar calDate = Calendar.getInstance();
			calDate.setTime(strDate);
			outDate = calDate.getTime();
		}
		return outSDF.format(outDate);
	}

	public static String format(double val){
		return formatter.format(val);
	}

	public static String format(Double val){
		return (val!= null)?formatter.format(val.doubleValue()) : "";
	}
	
	public /*static*/ String addCommaDB(String strInputString) {
		
		if( (strInputString == null) || (strInputString .equals("")) )
		{
			 return "";
		}
		
		String strMinusSymbol="";
		String strFinals="";
		String strFinals1="";
		String strReversed="";
		String strReversed1="";
		String strDecimal="";
		String strBeforeDecimal="";		
		String strWithoutMinus="";
		String strMinus="";
		
		if(Double.parseDouble(strInputString) == 0){
			return "0.00";
		}
		if(Double.parseDouble(strInputString) < 0){
			strInputString=""+(-1)*Double.parseDouble(strInputString);
			strMinusSymbol = "-";
		}
		if(strInputString.indexOf(".") != -1){
			
			strInputString = roundOffDigits(strInputString);

			if(strInputString.indexOf(".") != -1){
				strBeforeDecimal = strInputString.substring(0,strInputString.indexOf("."));
				strDecimal = strInputString.substring(strInputString.indexOf("."),strInputString.length());
				
			}else{
				
				strBeforeDecimal = strInputString;
			}
		}
		else
		{
			strBeforeDecimal = strInputString;
		}
		
		if(strBeforeDecimal.indexOf("-") != -1)
		{		
			strWithoutMinus=strBeforeDecimal.substring(strBeforeDecimal.indexOf("-")+1,strBeforeDecimal.length());
			strMinus="-";
			strBeforeDecimal=strWithoutMinus;
				
		}
				
		char cCurrentChar;
		int j=1;
		int nLength = strBeforeDecimal.length();
		nLength=nLength-1;
		
		for(;nLength>=0; --nLength)
		{
			
			strFinals= String.valueOf(strBeforeDecimal.charAt(nLength));
					
			if ((j==3) && (nLength!=0))
			{
				strFinals = strFinals + ",";
				j=0;
			}
			
			
			strFinals1 = strFinals1 + strFinals;
			++j;
		}
		
		
		int nLength1=strFinals1.length();
		nLength1=nLength1-1;

		for (;nLength1>=0;--nLength1)
		{
			strReversed=String.valueOf(strFinals1.charAt(nLength1));
			strReversed1= strReversed1 + strReversed;
			
		}
		strReversed1 = strMinus+strReversed1 +strDecimal;
		
		strReversed1 =strMinusSymbol+strReversed1;
		
		return strReversed1;
	}
	
	/**
	 * The method is used to round off the string passed as parameter 
	 * to two decimal points 
	 * 
	 * @return String 
	 * @param  String 
	 * @throws Exception
	 * 
	 * Coded By           : Anbalagan
	 * Date Coded         : June 09 2005   
	 */

	public static String roundOffDigits(String strValue)
	{
		if( (!strValue.equals("")) || (strValue !=null) )
		{
			if( strValue.indexOf(".") != -1 )
		    {
				BigDecimal bgVal1 = new BigDecimal(strValue).setScale(2, BigDecimal.ROUND_HALF_UP);
				strValue 		  = String.valueOf(bgVal1);
		    }
		}
		else
		{
			strValue = "";			
		}	
		
		if(strValue.equals("0.00"))
		{
			strValue = "0";
		}
		
		return strValue;
		
	} // End of roundOffDigits

	
// this function converts the value into Currency format value
public static String CurrencyFormatted(String amount)
{
	double i = Double.parseDouble(amount);
	//if(isNaN(i)) { i = 0.00; }
	String minus = "";
	/*if(i < 0) { 
		minus = "-"; 
	}*/
	//i = Math.abs(i);
	//double iValue =(double)(i + .005) * 100;
	double iValue =i;
	//iValue = iValue / 100;
	String s = String.valueOf(iValue);
	if(s.indexOf('.') < 0) { s += ".00"; }
	if(s.indexOf('.') == (s.length() - 2)) { s += "0"; }
	s = minus + s;
	return s;
}

	
//	 This method is used to put the Arraylist value to the ComboBox
//	 and put the arraylist in the request object
		public void populateCombo(HttpServletRequest request,ArrayList alValues,String reqAttName) throws Exception{
			logger.debug(" Population of Combo Values Start");
			
			ArrayList alDetailsList = new ArrayList();
			ArrayList alreturnList = new ArrayList();
			
			for (int i = 0; i < alValues.size(); i++) {
				alDetailsList = (ArrayList) alValues.get(i);
				alreturnList.add(new OIComboVO((String) alDetailsList.get(0),(String) alDetailsList.get(1)));
			}
			
			request.setAttribute(reqAttName,alreturnList);
			logger.debug(" Population of Combo Values End");
		}
	
	//This method will replace the null value with emptyspaces ie ""
		public static String replaceNull(String strInp){
			try{
				if(strInp==null || strInp.equals("null")){
					strInp="";
				}
			}catch(Exception e){strInp="";}
		return strInp;
	}
	//This method will convert the string into integer
	public static int convertInteger(String strInpValue){
		int nRtnVal=0;
		if(strInpValue==null || strInpValue.equals("")){
			strInpValue ="0";
		}
		nRtnVal=Integer.parseInt(strInpValue);
		return nRtnVal;
	}
	
	/**
	 * This method is used to append an other single quote wherever single quote  is 
	 * present in the given input string.  
	 * This method can be used while passing a string to a DB query
	 * 
	 * @param	String			The string for which the conversion has to be done
	 * 
	 * @return	String			The output string after conversion.
	 * 
	 */
	public static String addSingleQuoteDB(String strInputString){
		if (strInputString == null) return "";

		String strFinals="";
		char cSingleQuotes;
		int nLength = strInputString.length();
		for(int i=0;i<nLength; i++){
			cSingleQuotes=strInputString.charAt(i);
			if (cSingleQuotes=='\''){
				strFinals = strFinals + cSingleQuotes;
			}
			strFinals = strFinals + cSingleQuotes;
		}
		strFinals=strFinals.trim();
		return strFinals;
	}
	/**
	 * This method is used to append an other single quote wherever singlequote is
	 * present in the given input string. 
	 * This method can be used while passing a string to a JS.
	 * 
	 * @param	String			The string for which the conversion has to be done
	 * 
	 * @return	String			The output string after conversion.
	 * 
	 */
	public static String addSingleQuoteJS(String strInputString){
		if (strInputString == null) return "";

		String strFinals="";
		char cSingleQuotes;
		int nLength = strInputString.length();
		for(int i=0;i<nLength; i++){
			cSingleQuotes=strInputString.charAt(i);
			
			if (cSingleQuotes=='\''){
				strFinals = strFinals + "\\"  ;
			}
			strFinals = strFinals + cSingleQuotes;
		}
		strFinals=strFinals.trim();
		return strFinals;
	}
	
	//Method to convert from  CLOB to String
	//by Oscar
	public static String clobToString (Clob clob) throws SQLException, IOException{
		if (clob == null) return "";
		try {
			char buffer[] = new char[(int)clob.length()];
			Reader chReader = clob.getCharacterStream();
			chReader.read(buffer);
			String ret = new String(buffer);
			chReader.close();
			return ret;
		} catch (SQLException e) {
			logger.error("SQLException - clobToString() : " + e.getMessage());
			throw e;
		} catch (IOException e) {
			logger.error("IOException - clobToString() : " + e.getMessage());
			throw e;
		}
	}
	
	//Method to write string to a CLOB
	//by Oscar
	public static void writeClob (CLOB clob, String str) throws SQLException, IOException {
		if (str == null) return;
		
		//Writer writer = null;
		StringReader reader = null;
		int read = 0;
		long position = 1;
		char[] buffer = null;
		int bufflen = 0;
		
		try {
			reader = new StringReader(str);
			//writer = clob.getCharacterOutputStream();
		    
		    // Get optimal buffer size to read/write data  
		    buffer = new char[ clob.getChunkSize() ];
		    read = 0;
		    position = 1;
		    bufflen = buffer.length;

		    // Read from String and write to CLOB
		    while((read = reader.read(buffer,0,bufflen)) > 0 ) {
		      clob.putChars(position, buffer, read);
		      //writer.write(buffer, (int)position, read);
		      position += read;
		    }
		    //writer.write(str);
		} catch (IOException ioe) {
			logger.error("writeClob() - IOException - " + ioe);
			throw ioe;
		} catch (SQLException e) {
			logger.error("writeClob() - SQLException - " + e);
			throw e;
		} finally {
			reader.close();
			//writer.close();
		}
	}
	
//	Method to write string to a CLOB
	//by Oscar
	public static void writeBlogClob (CLOB clob, String str) throws SQLException, IOException {
		if (str == null) return;
		
		//Writer writer = null;
		StringReader reader = null;
		int read = 0;
		long position = 1;
		char[] buffer = null;
		int bufflen = 0;
		
		try {
			reader = new StringReader(str);
			//writer = clob.getCharacterOutputStream();
		    	    
		    // Get optimal buffer size to read/write data  
		    //buffer = new char[ clob.getChunkSize() ];
			buffer= new char[ clob.getChunkSize() ];
			read = 0;
		    position = 1;
		    bufflen = buffer.length;

		    // Read from String and write to CLOB
		    
		    while((read = reader.read(buffer,0,bufflen)) > 0 ) {
		      clob.putChars(position, buffer, read);
		      //writer.write(buffer, (int)position, read);
		      position += read;
		    }
		    //writer.write(str);
		} catch (IOException ioe) {
			logger.error("writeClob() - IOException - " + ioe);
			throw ioe;
		} catch (SQLException e) {
			logger.error("writeClob() - SQLException - " + e);
			throw e;
		} finally {
			reader.close();
			//writer.close();
		}
	}
	
	public static String truncateString(String strInput) throws Exception{
		String strData =replaceNull(strInput);
		String strOutputData ="";
		int incr=0;
		if(!strData.equals("")){
			StringTokenizer strTok = new StringTokenizer(strData," ");
			while(strTok.hasMoreTokens()){
				if(incr<25){
					strOutputData =strOutputData+strTok.nextToken()+" ";
				}else{
					break;
				}
				incr = incr+1;
			}
		}
		if(!strData.equals("") && incr>24){
			strOutputData = strOutputData.trim()+ "...";			
		}
		 
		return strOutputData;
	}
	//added by deva on 04/09/2007
	public static String removeHtmlTags(String totalWord){

		String finalWord="";
		boolean htmlTagStart = false;
		for(int i=0;i<totalWord.length();i++){
		String word= totalWord.substring(i,i+1);
		if(word.equals("<")){
		htmlTagStart = true;
		}
		if(!htmlTagStart){
			finalWord =finalWord +word;
		}
		if(word.equals(">")){
		htmlTagStart = false;
		}
		}
		
		return finalWord.replaceAll("&nbsp;"," ");
		}
		//end

		
   public static int replaceNulltoZero(String strInput)throws Exception{
	int i=0;
	if(OIUtilities.replaceNull(strInput).equals("")){
		return 0;	
	}else{
		return Integer.parseInt(strInput);
	}
	
}
   
}
