package com.oifm.blog;

import java.io.Serializable;

public class OIBABlogAuthor implements Serializable 
{
	private String userId = null;
	private String notifyEmail = null;
	private String name = null;
	private String emailId = null;
	private String nickName = null;
	private boolean addedToEntry = false;
	
	private String authorDescription;
	private String authorImageFileName;
	
	public boolean equals(Object obj)
	{
		if (obj instanceof OIBABlogAuthor)
		{
			OIBABlogAuthor oiBABlogAuthor = (OIBABlogAuthor) obj;
			if ((oiBABlogAuthor.getUserId()).equals(getUserId()))
			{
				return true;
			}
			else
			{	
				return false;
			}			
		}
		else
		{
			return false;
		}
	}
	/**
	 * @return the notifyEmail
	 */
	public String getNotifyEmail()
	{
		return notifyEmail;
	}
	/**
	 * @param notifyEmail the notifyEmail to set
	 */
	public void setNotifyEmail(String notifyEmail)
	{
		this.notifyEmail = notifyEmail;
	}
	/**
	 * @return the userId
	 */
	public String getUserId()
	{
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId)
	{
		this.userId = userId;
	}
	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	/**
	 * @return the nickName
	 */
	public String getNickName()
	{
		return nickName;
	}
	/**
	 * @param nickName the nickName to set
	 */
	public void setNickName(String nickName)
	{
		this.nickName = nickName;
	}
	/**
	 * @return the emailId
	 */
	public String getEmailId()
	{
		return emailId;
	}
	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(String emailId)
	{
		this.emailId = emailId;
	}
	/**
	 * @return the addedToEntry
	 */
	public boolean isAddedToEntry()
	{
		return addedToEntry;
	}
	/**
	 * @param addedToEntry the addedToEntry to set
	 */
	public void setAddedToEntry(boolean addedToEntry)
	{
		this.addedToEntry = addedToEntry;
	}
	/**
	 * @return the authorDescription
	 */
	public String getAuthorDescription()
	{
		return authorDescription;
	}
	/**
	 * @param authorDescription
	 *            the authorDescription to set
	 */
	public void setAuthorDescription(String authorDescription)
	{
		this.authorDescription = authorDescription;
	}
	/**
	 * @return the authorImageFileName
	 */
	public String getAuthorImageFileName()
	{
		return authorImageFileName;
	}
	/**
	 * @param authorImageFileName
	 *            the authorImageFileName to set
	 */
	public void setAuthorImageFileName(String authorImageFileName)
	{
		this.authorImageFileName = authorImageFileName;
	}
}
