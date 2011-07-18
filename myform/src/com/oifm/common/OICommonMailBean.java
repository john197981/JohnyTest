package com.oifm.common;

/*
 * Class Name:
 * Description:
 * 
 * 	Created By			Created/Modified on			Revision				Remarks
 * -----------------------------------------------------------------------------------------------------
 * 	Rajkumar			15/06/2005					Draft					Inital Version
 * 
 * -----------------------------------------------------------------------------------------------------
 */
public class OICommonMailBean 
{
	private String to=  null;// "rajkumar.thiyaharajan@scandent.com";
	private String bcc= null;// "rajkumar.thiyaharajan@scandent.com";
	private String cc= null;// "rajkumar.thiyaharajan@scandent.com";
	private String subject= null; //"Test Subject";
	private String from= null;//;"rajkumar.thiyaharajan@scandent.com";
	private String message= null;//"Test Message";
	private String smtpHost=null;
	private String type=null; 
	private String principal=null;
	

	/**
	 * @return Returns the cc.
	 */
	public String getCc() {
		return cc;
	}
	/**
	 * @param cc The cc to set.
	 */
	public void setCc(String cc) {
		this.cc = cc;
	}
	public String getFrom() 
	{
		return from;
	}

	public String getMessage() 
	{
		return message;
	}

	public String getSmtpHost() 
	{
		return smtpHost;
	}

	public String getSubject() 
	{
		return subject;
	}

	public String getTo() 
	{
		return to;
	}

	public String getType() 
	{
		return type;
	}

	public void setMessage(String message) 
	{
		this.message = message;
	}

	public void setSmtpHost(String smtpHost) 
	{
		this.smtpHost = smtpHost;
	}

	public void setSubject(String subject) 
	{
		this.subject = subject;
	}

	public void setTo(String to) 
	{
		this.to = to;
	}

	public void setType(String type) 
	{
		this.type = type;
	}

	public void setFrom(String from) 
	{
		this.from = from;
	}
    /**
     * @return Returns the bcc.
     */
    public String getBcc() {
        return bcc;
    }
    /**
     * @param bcc The bcc to set.
     */
    public void setBcc(String bcc) {
        this.bcc = bcc;
    }
	/**
	 * @return the principal
	 */
	public String getPrincipal()
	{
		return principal;
	}
	/**
	 * @param principal the principal to set
	 */
	public void setPrincipal(String principal)
	{
		this.principal = principal;
	}
}
