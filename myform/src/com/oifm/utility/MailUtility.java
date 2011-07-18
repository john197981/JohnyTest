package com.oifm.utility;


import java.util.Properties;
import java.util.StringTokenizer;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;

import com.oifm.common.OICommonMailBean;



/*
 * Class Name:MailUtility
 * Description:This class send mail to the recipient
 * 
 * 	Created By			Created/Modified on			Version					Remarks
 * -----------------------------------------------------------------------------------------------------
 * 	Rajkumar			15/06/2005					Draft					Inital Version
 * 
 * -----------------------------------------------------------------------------------------------------
 */

public class MailUtility 
{
	public void sendMessage(OICommonMailBean bean) throws Exception
	{
    	//System.out.println(bean.getBcc());
		//Set the host smtp address
        Properties properties = new Properties();
        properties.put("mail.smtp.host", bean.getSmtpHost());

        // create some properties and get the default Session
    	//System.out.println(bean.getBcc());

        boolean debug=false;
        Session session = Session.getDefaultInstance(properties, null);
		session.setDebug(debug);

        // create a message
        javax.mail.Message message = new javax.mail.internet.MimeMessage(session);

		// set the from and to address
        Address addressFrom = new InternetAddress(bean.getFrom(),"");
        
        if(OIFormUtilities.chkIsNull(bean.getBcc()).length()>0){
        	String [] sBcc = getArrayOfString(bean.getBcc());
        	InternetAddress[] addressBcc = new InternetAddress[sBcc.length];
        	for (int i=0;i<sBcc.length;i++){
        		addressBcc[i] = new InternetAddress(sBcc[i]);
        	}
        	message.setRecipients(Message.RecipientType.BCC,addressBcc);
        }	
        if(OIFormUtilities.chkIsNull(bean.getCc()).length()>0){
        	String [] sCc = getArrayOfString(bean.getCc());
        	InternetAddress[] addressCc = new InternetAddress[sCc.length];
        	for (int i=0;i<sCc.length;i++){
        		addressCc[i] = new InternetAddress(sCc[i]);
        	}
        	message.setRecipients(Message.RecipientType.CC,addressCc);
        }	
        //{new InternetAddress("")};
        //InternetAddress[] addressCC = {new InternetAddress("rajkumar.thiyaharajan@scandent.com","ntrk@yahoo.com")};
		message.setFrom(addressFrom);
        //message.setRecipient(Message.RecipientType.TO,new InternetAddress(bean.getTo()));
        if(OIFormUtilities.chkIsNull(bean.getTo()).length()>0){
        	String [] sTo = getArrayOfString(bean.getTo());
        	InternetAddress[] addressTo = new InternetAddress[sTo.length];
        	for (int i=0;i<sTo.length;i++){
        		addressTo[i] = new InternetAddress(sTo[i]);
        	}
        	message.setRecipients(Message.RecipientType.TO,addressTo);
        }	
        

		// Setting the Subject and Content Type
        message.setSubject(bean.getSubject());
        message.setContent(getFormattedMessageBody(bean),bean.getType());

        javax.mail.Transport.send(message);
	}

    private String getFormattedMessageBody(OICommonMailBean bean)
	{
		//Format the Message body
        StringBuffer formatterBuffer = new StringBuffer();
      // formatterBuffer.append("Greetings,\n\n");
      //  formatterBuffer.append("TEST MAIL\n\n\n");
		formatterBuffer.append(bean.getMessage());
        String formattedMessage = formatterBuffer.toString();
        formatterBuffer = null;
        return formattedMessage;
	}
    
    private String[] getArrayOfString(String str)
    { 
    	String[] s  = null;
    	int count = str.indexOf(',');
        StringTokenizer strt = new StringTokenizer(str,",");
        s = new String[strt.countTokens()];
        int i=0;
        while(strt.hasMoreTokens())
        {
            s[i] = strt.nextToken();
            i++;
        }
    	return s;
    }
     
    
    
    public static void main(String[] str)
    {
        try
        {
            OICommonMailBean aOICommonMailBean = new OICommonMailBean();
            aOICommonMailBean.setFrom("divya.bhargov@scandent.com");
            aOICommonMailBean.setTo("divya.bhargov@scandent.com,divya.bhargov@scandent.com");
            aOICommonMailBean.setBcc("divya.bhargov@scandent.com,divya.bhargov@scandent.com");
            aOICommonMailBean.setSmtpHost("192.168.30.11");
            aOICommonMailBean.setType("text/plain");
            aOICommonMailBean.setSubject("xcvcxvx");
            aOICommonMailBean.setMessage("dgsdfgdsfds");

            new MailUtility().sendMessage(aOICommonMailBean);
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    
}
