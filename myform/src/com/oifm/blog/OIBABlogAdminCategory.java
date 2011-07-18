package com.oifm.blog;

import java.io.Serializable;

public class OIBABlogAdminCategory implements Serializable
{
	private Integer categoryId;
	private String category;

	/**
	 * @return the all_categories
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the categoryId
	 */
	public Integer getCategoryId()
	{
		return categoryId;
	}

	/**
	 * @param categoryId the categoryId to set
	 */
	public void setCategoryId(Integer categoryId)
	{
		this.categoryId = categoryId;
	}

	




}
