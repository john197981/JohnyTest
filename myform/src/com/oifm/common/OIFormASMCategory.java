package com.oifm.common;

/**
 * @author Rakesh		mail@ Rakesh.Chagallu@cambridge-asia.com
 * Desc : This is a Form class for ASM Category.
 * Date : 15 Jan 2008
 */
import com.oifm.base.OIBaseActionForm;
import java.util.ArrayList;
public class OIFormASMCategory extends OIBaseActionForm
{
		private java.util.Date createdOn;
		private String createdBy;
		private String name;
		private int categoryID;
		private String description;
		private ArrayList arOIBAASMCategoryList;
		
		
		 public OIFormASMCategory()
		    {
			 arOIBAASMCategoryList = new ArrayList();
		    }
		    
		/**
		 * @return the arOIBAASMCategoryList
		 */
		public ArrayList getArOIBAASMCategoryList()
		{
			return arOIBAASMCategoryList;
		}
		/**
		 * @param arOIBAASMCategoryList the arOIBAASMCategoryList to set
		 */
		public void setArOIBAASMCategoryList(ArrayList arOIBAASMCategoryList)
		{
			this.arOIBAASMCategoryList = arOIBAASMCategoryList;
		}
		/**
		 * @return the categoryID
		 */
		public int getCategoryID()
		{
			return categoryID;
		}
		/**
		 * @param categoryID the categoryID to set
		 */
		public void setCategoryID(int categoryID)
		{
			this.categoryID = categoryID;
		}
		/**
		 * @return the createdBy
		 */
		public String getCreatedBy()
		{
			return createdBy;
		}
		/**
		 * @param createdBy the createdBy to set
		 */
		public void setCreatedBy(String createdBy)
		{
			this.createdBy = createdBy;
		}
		/**
		 * @return the createdOn
		 */
		public java.util.Date getCreatedOn()
		{
			return createdOn;
		}
		/**
		 * @param createdOn the createdOn to set
		 */
		public void setCreatedOn(java.util.Date createdOn)
		{
			this.createdOn = createdOn;
		}
		/**
		 * @return the description
		 */
		public String getDescription()
		{
			return description;
		}
		/**
		 * @param description the description to set
		 */
		public void setDescription(String description)
		{
			this.description = description;
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
		public void addArCategoryList(OIBAASMCategoryList aOIBAASMCategoryList) {
			this.arOIBAASMCategoryList.add(aOIBAASMCategoryList);
		}   	   
		
		
}
