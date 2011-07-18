package com.oifm.blog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseDao;
import com.oifm.utility.OISQLUtilities;
import com.oifm.utility.OIUtilities;

public class OIDAOIndividualBlogHome extends OIBaseDao
{
	private static final Logger logger = Logger.getLogger(OIBOIndividualBlogHome.class);
	
	public OIDAOIndividualBlogHome () {super();}
	
	public ArrayList getIndividualBlogEntries(Connection conn, Integer blogId) throws Exception
	{
		ArrayList alList = new ArrayList();
		PreparedStatement pst = null;
		ResultSet rs = null;
		OIBAIndividualBlog objBAIndividualBlogHome = null;
		
		try
		{	
			pst = conn.prepareStatement(OIIndividualBlogSqls.QRY_LIST_BLOG_ENTRIES);
			pst.setInt(1, blogId.intValue());
			pst.setInt(2, blogId.intValue());
			rs = pst.executeQuery();
			
			while (rs.next())
			{
				objBAIndividualBlogHome = new OIBAIndividualBlog();
				
				objBAIndividualBlogHome.setBlogId(Integer.valueOf(rs.getString("BLOG_ID")));
				objBAIndividualBlogHome.setEntryId(Integer.valueOf(rs.getString("ENTRY_ID")));
				objBAIndividualBlogHome.setTitle(rs.getString("TITLE"));
				objBAIndividualBlogHome.setAuthorUserName(rs.getString("NAME"));
				objBAIndividualBlogHome.setDate(rs.getString("CREATED_ON"));
				objBAIndividualBlogHome.setCommentAllowed(rs.getString("ALLOW_COMMENTS").trim().equalsIgnoreCase("Y"));
				
				objBAIndividualBlogHome.setEntryExcerpt(OIUtilities.clobToString(rs.getClob("ENTRY_BODY")));
				
//				if (rs.getString("ENTRY_EXCEPT") != null && rs.getString("ENTRY_EXCEPT").trim().length() > 0)
//					objBAIndividualBlogHome.setEntryExcerpt(rs.getString("ENTRY_EXCEPT"));
//				else
//				{
//					String temp = OIUtilities.clobToString(rs.getClob("ENTRY_BODY"));
//
//					int idx = 0;
//					for (int i = 0; i < 50 && idx > -1; i++)
//					{
//						idx = temp.indexOf(" ", idx);
//					}
//					
//					if (idx > 0)
//						temp = temp.substring(0, idx);
//					
//					objBAIndividualBlogHome.setEntryExcerpt(temp);
//				}
				
				objBAIndividualBlogHome.setPicFileName(rs.getString("IMAGE_NAME"));
				objBAIndividualBlogHome.setCommentCount(Integer.valueOf(rs.getString("CNT")));

				alList.add(objBAIndividualBlogHome);
			}
			
			for (int i = 0; i < alList.size(); i++)
			{
				objBAIndividualBlogHome = (OIBAIndividualBlog)alList.get(i);
				
				pst = conn.prepareStatement(OIBlogSqls.GET_ENTRY_TAG);
				pst.setInt(1,objBAIndividualBlogHome.getEntryId().intValue());
				rs = pst.executeQuery();
				
				ArrayList tagList = new ArrayList();
				
				while(rs.next()){
					tagList.add(rs.getString("NAME"));
				}
				objBAIndividualBlogHome.setTagList(tagList);
			}
		}
		catch (Exception e)
		{
			logger.error("getIndividualBlogEntries() : " + e);
			throw e;
		}
		finally
		{
			OISQLUtilities.closeRsetPstatement(rs, pst);
		}
		
		return alList;
	}
}
