package com.oifm.asm;

//import java.io.Writer;
//import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import oracle.sql.CLOB;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseDao;
import com.oifm.siteRanking.OIBAWebSiteRanking;
import com.oifm.siteRanking.OIDAOWebSiteRanking;
import com.oifm.utility.DateUtility;
import com.oifm.utility.OIUtilities;




/*
 * Class Name:
 * Description:
 * 
 * 	Created By			Created/Modified on			Revision				Remarks
 * -----------------------------------------------------------------------------------------------------
 * 	Rajkumar			19/12/2005					Draft					Inital Version
 * 
 * -----------------------------------------------------------------------------------------------------
 */

public class ASMDAOView extends OIBaseDao
{
    Logger logger = Logger.getLogger(ASMDAOView.class.getName());
    
    public ArrayList readUserLetters(String pUserId,Connection connection) throws Exception
    {
        ArrayList arASMBALetters = new ArrayList();
        ASMBALetters aASMBALetters = null;
        PreparedStatement preparedStatement=null;
        ResultSet rs=null;
        try
        {
            preparedStatement=connection.prepareStatement(ASMSqls.ASM_VIEW_USER_LETTERS);
            preparedStatement.setString(1,pUserId);
            rs = preparedStatement.executeQuery();
            while(rs.next())
            {
            	aASMBALetters = new ASMBALetters();
            	aASMBALetters.setLetterId(rs.getString("LETTERID"));
            	aASMBALetters.setTopic(rs.getString("TOPIC"));
            	aASMBALetters.setStatus(rs.getString("STATUS"));
            	Date tempDate = rs.getDate("DRAFTEDON");
            	if (tempDate!=null)
            	{
            		aASMBALetters.setDraftedOn(DateUtility.getMMDDYYYYStringFromDate(tempDate));
            	}
            	tempDate = rs.getDate("SUBMITTEDON");
            	if (tempDate!=null)
            	{
            		aASMBALetters.setSubmittedOn(DateUtility.getMMDDYYYYStringFromDate(tempDate));
            	}
            	tempDate = rs.getDate("REDIRECTEDON");
            	if (tempDate!=null)
            	{
            		aASMBALetters.setReDirectedOn(DateUtility.getMMDDYYYYStringFromDate(tempDate));
            	}
            	tempDate = rs.getDate("REPLIEDON");
            	if (tempDate!=null)
            	{
            		aASMBALetters.setRepliedOn(DateUtility.getMMDDYYYYStringFromDate(tempDate));
            	}
            	tempDate = rs.getDate("PUBLISHEDON");
            	if (tempDate!=null)
            	{
            		aASMBALetters.setPublishOn(DateUtility.getMMDDYYYYStringFromDate(tempDate));
            	}
				arASMBALetters.add(aASMBALetters);
            }
        }catch(SQLException sqle)
        {
            logger.error("ASMDAOView --- readUserLetters -" + sqle.getMessage());
            throw sqle;
        }
        finally
        {
            if (preparedStatement!=null)
                preparedStatement.close();
            if (rs!=null)
                rs.close();
        }
        if (arASMBALetters.size()==0)
            arASMBALetters = null;

        return arASMBALetters;

    }
    
    public ASMBALetters readDetailLetters(String pLetterId,Connection connection,String pUserId) throws Exception
    {
        ASMBALetters aASMBALetters = null;
        PreparedStatement preparedStatement=null;
        ResultSet rs=null;
        try
        {
            preparedStatement=connection.prepareStatement(ASMSqls.ASM_DETAIL_LETTERS);
            preparedStatement.setString(1,pLetterId);
            
            rs = preparedStatement.executeQuery();
            while(rs.next())
            {
            	aASMBALetters = new ASMBALetters();
            	aASMBALetters.setLetterId(rs.getString("LETTERID"));
            	aASMBALetters.setTopic(rs.getString("TOPIC"));
            	aASMBALetters.setLetter(OIUtilities.clobToString(rs.getClob("LETTER")));
            	aASMBALetters.setStatus(rs.getString("STATUS"));
            	Date tempDate = rs.getDate("DRAFTEDON");
            	if (tempDate!=null)
            	{
            		aASMBALetters.setDraftedOn(DateUtility.getMMDDYYYYStringFromDate(tempDate));
            	}
            	tempDate = rs.getDate("SUBMITTEDON");
            	if (tempDate!=null)
            	{
            		aASMBALetters.setSubmittedOn(DateUtility.getMMDDYYYYStringFromDate(tempDate));
            	}
            	tempDate = rs.getDate("REDIRECTEDON");
            	if (tempDate!=null)
            	{
            		aASMBALetters.setReDirectedOn(DateUtility.getMMDDYYYYStringFromDate(tempDate));
            	}
            	aASMBALetters.setReDirectedBy(rs.getString("REDIRECTEDBY"));
            	aASMBALetters.setReDirectedTo(rs.getString("REDIRECTEDTO"));
            	aASMBALetters.setReDirectedCc(rs.getString("REDIRECTEDCC"));
            	aASMBALetters.setReDirectedBcc(rs.getString("REDIRECTEDBCC"));
            	aASMBALetters.setReDirectStatus(rs.getString("REDIRECTSTATUS"));
            	aASMBALetters.setReDirectMessage(rs.getString("REDIRECTMESSAGE"));
            	aASMBALetters.setSubject(rs.getString("SUBJECT"));
            	tempDate = rs.getDate("REPLIEDON");
            	if (tempDate!=null)
            	{
            		aASMBALetters.setRepliedOn(DateUtility.getMMDDYYYYStringFromDate(tempDate));
            	}
            	aASMBALetters.setRepliedBy(rs.getString("REPLIEDBY"));
            	aASMBALetters.setReply(OIUtilities.clobToString(rs.getClob("REPLY")));
            	//logger.info("Reply--" + aASMBALetters.getReply());
            	aASMBALetters.setPublishOnSite(rs.getString("PUBLISHONSITE"));
            	tempDate = rs.getDate("PUBLISHEDON");
            	if (tempDate!=null)
            	{
            		aASMBALetters.setPublishOn(DateUtility.getMMDDYYYYStringFromDate(tempDate));
            	}
            	aASMBALetters.setPublishedFrom(rs.getString("PUBLISHEDFROM"));
            	aASMBALetters.setPublishedTo(rs.getString("PUBLISHEDTO"));
            	aASMBALetters.setCreatedBy(rs.getString("CREATEDBY"));
            	aASMBALetters.setReminderMode(rs.getString("REMINDERMODE"));
            	tempDate = rs.getDate("REMINDERSENTON");
            	if (tempDate!=null)
            	{
            		aASMBALetters.setReminderSentOn(DateUtility.getMMDDYYYYStringFromDate(tempDate));
            	}
            	aASMBALetters.setDivisionCode(rs.getString("DIVISION_CODE"));
            	aASMBALetters.setContactNo(rs.getString("CONTACTNO"));
            	aASMBALetters.setAuthorName(rs.getString("AUTHOR_NAME"));
            	/*
            	// Website rank - View Letters
            	if (!OIUtilities.replaceNull(aASMBALetters.getStatus()).equalsIgnoreCase("D"))
            	{
	            	try{
	        			if(pUserId!= null){        				
	        				OIBAWebSiteRanking aOIBAWebSiteRanking = new OIBAWebSiteRanking();
	        		        aOIBAWebSiteRanking.setActionId("VL");
	        		        aOIBAWebSiteRanking.setUserId(pUserId);
	        		        aOIBAWebSiteRanking.setItemId(Integer.parseInt(pLetterId));
	        		        new OIDAOWebSiteRanking().save(aOIBAWebSiteRanking,connection);
	        			}
	        		} 
	        		catch(Exception e)
	        	    {
	        	        String error = e.getMessage();
	        	        logger.error(e);
	        	        try
	        	        {
	        	        	connection.rollback();
	        	        }catch (Exception ex){}
	        	    }
            	}
            	*/
            	// End website rank
            }
        }catch(SQLException sqle)
        {
            logger.error("ASMDAOView --- readDetailLetters -" + sqle.getMessage());
            throw sqle;
        }
        finally
        {
            if (preparedStatement!=null)
                preparedStatement.close();
            if (rs!=null)
                rs.close();
        }

        return aASMBALetters;
    }
    
    public boolean saveLetter(String pUserId,ASMBALetters aASMBALetters,Connection connection) throws Exception
    {
        boolean flag=false;
        int i=0;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        CLOB clob = null;
        try
        {
            if (aASMBALetters.getLetterId()==null || aASMBALetters.getLetterId().trim().equals(""))
            {
            	logger.info(aASMBALetters.getStatus());
            	if (aASMBALetters.getStatus().trim().equals("D"))
            	{
		            connection.setAutoCommit(false);
		            
            		preparedStatement = connection.prepareStatement(ASMSqls.LETTER_DRAFT_INSERT);
					preparedStatement.setString(1,aASMBALetters.getTopic());
					//preparedStatement.setString(2,"EMPTY_CLOB");
					preparedStatement.setString(2,aASMBALetters.getStatus());
					preparedStatement.setDate(3,new java.sql.Date(DateUtility.getDateFromMMDDYYYYString(aASMBALetters.getDraftedOn()).getTime()));
					preparedStatement.setString(4,pUserId);
					preparedStatement.setString(5,aASMBALetters.getContactNo());
					
					i = preparedStatement.executeUpdate();
					
					if (i==0)
		            {
		                logger.error("Save Failed");
		                throw new Exception("Save Failed");
		            }
					//logger.info("After inserting, before CLOB");
					int id = 0;
					String temp = null;
					preparedStatement=connection.prepareStatement("SELECT SEQ_OI_AM_LETTERID.CURRVAL FROM OI_AM_LETTERS");
					resultSet = preparedStatement.executeQuery();
					if (resultSet.next()) id = resultSet.getInt("CURRVAL");
					preparedStatement=connection.prepareStatement(ASMSqls.ASM_DETAIL_LETTERS + " FOR UPDATE");
    				preparedStatement.setInt(1,id);
    				
    				resultSet = preparedStatement.executeQuery();
        			
        			if (resultSet.next()) {
        				temp = resultSet.getString("TOPIC");
        				clob = (CLOB) resultSet.getClob("LETTER");
        			}
        			if (temp.equals(aASMBALetters.getTopic()))OIUtilities.writeClob(clob, aASMBALetters.getLetter());
        			else throw new Exception("Save Failed");
        			
        			connection.commit();
        			connection.setAutoCommit(true);
            	}
            	else if (aASMBALetters.getStatus().trim().equals("S"))
            	{
            		connection.setAutoCommit(false);
            		
		            preparedStatement = connection.prepareStatement(ASMSqls.LETTER_SUBMITTED_INSERT);
					preparedStatement.setString(1,aASMBALetters.getTopic());
					//preparedStatement.setString(2,aASMBALetters.getLetter());
					preparedStatement.setString(2,aASMBALetters.getStatus());
					preparedStatement.setDate(3,new java.sql.Date(DateUtility.getDateFromMMDDYYYYString(aASMBALetters.getSubmittedOn()).getTime()));
					preparedStatement.setString(4,pUserId);
					preparedStatement.setString(5,aASMBALetters.getContactNo());
					
					i = preparedStatement.executeUpdate();
					if (i==0)
		            {
		                logger.error("Save Failed");
		                throw new Exception("Save Failed");
		            }
					//logger.info("After inserting, before CLOB");
					int id = 0;
					String temp = null;
					preparedStatement=connection.prepareStatement("SELECT SEQ_OI_AM_LETTERID.CURRVAL FROM OI_AM_LETTERS");
					resultSet = preparedStatement.executeQuery();
					if (resultSet.next()) id = resultSet.getInt("CURRVAL");
					preparedStatement=connection.prepareStatement(ASMSqls.ASM_DETAIL_LETTERS + " FOR UPDATE");
    				preparedStatement.setInt(1,id);
    				resultSet = preparedStatement.executeQuery();
        			if (resultSet.next()) {
        				temp = resultSet.getString("TOPIC");
        				clob = (CLOB) resultSet.getClob("LETTER");
        			}
        			if (temp.equals(aASMBALetters.getTopic()))OIUtilities.writeClob(clob, aASMBALetters.getLetter());
        			else 
        			{
        				throw new Exception("Save Failed");
        			}
        			connection.commit();
        			connection.setAutoCommit(true);
        			// For website ranking -- Submit Letter
    				try{
            			if(pUserId!= null){        				
            				OIBAWebSiteRanking aOIBAWebSiteRanking = new OIBAWebSiteRanking();
            		        aOIBAWebSiteRanking.setActionId("SL");
            		        aOIBAWebSiteRanking.setUserId(pUserId);
            		        aOIBAWebSiteRanking.setItemId(id);
            		        new OIDAOWebSiteRanking().save(aOIBAWebSiteRanking,connection);
            			}
            		} 
            		catch(Exception e)
            	    {
            			String error = e.getMessage();
            	        logger.error(e);
            	        try
            	        {
            	        	connection.rollback();
            	        }catch (Exception ex){}
            	    }
            		// End website ranking
        			
            	}
            }
            else
            {
            	if (aASMBALetters.getStatus().trim().equals("D"))
            	{
            		
            		preparedStatement = connection.prepareStatement(ASMSqls.LETTER_DRAFT_UPDATE);
					preparedStatement.setString(1,aASMBALetters.getTopic());
					//preparedStatement.setClob(2,clob);
					preparedStatement.setString(2,aASMBALetters.getStatus());
					preparedStatement.setDate(3,new java.sql.Date(DateUtility.getDateFromMMDDYYYYString(aASMBALetters.getDraftedOn()).getTime()));
					preparedStatement.setString(4,aASMBALetters.getContactNo());
					preparedStatement.setString(5,aASMBALetters.getLetterId());
					
					i = preparedStatement.executeUpdate();
					
            		// WRITING CLOB
            			connection.setAutoCommit(false);
            			preparedStatement=connection.prepareStatement(ASMSqls.ASM_DETAIL_LETTERS + " FOR UPDATE");
            			preparedStatement.setString(1,aASMBALetters.getLetterId());
                    
            			resultSet = preparedStatement.executeQuery();
            			
            			if (resultSet.next()) clob = (CLOB) resultSet.getClob("LETTER");
            			OIUtilities.writeClob(clob, aASMBALetters.getLetter());
            			
            			connection.commit();
            			connection.setAutoCommit(true);
					
            	}
            	else if (aASMBALetters.getStatus().trim().equals("S"))
            	{
            		
            		preparedStatement = connection.prepareStatement(ASMSqls.LETTER_SUBMITTED_UPDATE);
					preparedStatement.setString(1,aASMBALetters.getTopic());
					//preparedStatement.setClob(2,clob);
					preparedStatement.setString(2,aASMBALetters.getStatus());
					preparedStatement.setDate(3,new java.sql.Date(DateUtility.getDateFromMMDDYYYYString(aASMBALetters.getSubmittedOn()).getTime()));
					preparedStatement.setString(4,aASMBALetters.getContactNo());
					preparedStatement.setString(5,aASMBALetters.getLetterId());
					
					i = preparedStatement.executeUpdate();
					
            		// WRITING CLOB
        				connection.setAutoCommit(false);
        				preparedStatement=connection.prepareStatement(ASMSqls.ASM_DETAIL_LETTERS + " FOR UPDATE");
        				preparedStatement.setString(1,aASMBALetters.getLetterId());
                
        				resultSet = preparedStatement.executeQuery();
        			
        				if (resultSet.next()) clob = (CLOB) resultSet.getClob("LETTER");
        				OIUtilities.writeClob(clob, aASMBALetters.getLetter());
        			
        				connection.commit();
        				connection.setAutoCommit(true);
        				
        				// For website ranking -- Submit Letter
        				try{
                			if(pUserId!= null){        				
                				OIBAWebSiteRanking aOIBAWebSiteRanking = new OIBAWebSiteRanking();
                		        aOIBAWebSiteRanking.setActionId("SL");
                		        aOIBAWebSiteRanking.setUserId(pUserId);
                		        aOIBAWebSiteRanking.setItemId(Integer.parseInt(aASMBALetters.getLetterId()));
                		        new OIDAOWebSiteRanking().save(aOIBAWebSiteRanking,connection);
                			}
                		} 
                		catch(Exception e)
                	    {
                	        String error = e.getMessage();
                	        logger.error(e);
                	        try
                	        {
                	        	connection.rollback();
                	        }catch (Exception ex){}
                	    }
                		// End website ranking
            	}
            }
            if (i==0)
            {
                logger.error("Save Failed");
                throw new Exception("Save Failed");
            }
            else
            {
                flag = true;
            }
        }catch(Exception sqle)
        {
        	connection.rollback();
            logger.error("saveLetter--" + sqle.getMessage());
            throw sqle;
        }
        finally
        {
        	connection.setAutoCommit(true);
            if (preparedStatement!=null)
                preparedStatement.close();
        }
        
        
        return flag;
    }

    public boolean deleteLetter(String pUserId,ASMBALetters aASMBALetters,Connection connection) throws Exception
    {
        boolean flag=false;
        PreparedStatement preparedStatement = null;
        try
        {
            preparedStatement = connection.prepareStatement(ASMSqls.LETTER_DRAFT_DELETE);
        	//preparedStatement = connection.prepareStatement("delete from OI_AM_LETTERS letter where letter.LETTERID=?");
			preparedStatement.setString(1,aASMBALetters.getLetterId());
			flag = preparedStatement.execute();
        }catch(Exception sqle)
        {
            logger.error("deleteLetter--" + sqle.getMessage());
            throw sqle;
        }
        finally
        {
            if (preparedStatement!=null)
                preparedStatement.close();
        }
        
        return flag;
    }
}
