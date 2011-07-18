package com.oifm.consultation.admin;
/*
 * Class Name:
 * Description:
 * 
 * 	Created By			Created/Modified on			Revision				Remarks
 * -----------------------------------------------------------------------------------------------------
 * 	Rajkumar			05/07/2005					Draft					Inital Version
 * 
 * -----------------------------------------------------------------------------------------------------
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.CallableStatement;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseDao;
import com.oifm.consultation.OIConsultConstant;
import com.oifm.consultation.OIConsultationSqls;
import com.oifm.survey.OISurveySqls;
import com.oifm.utility.OISQLUtilities;
import com.oifm.utility.OIUtilities;

public class OIDAOConsultPaper extends OIBaseDao 
{
    Logger logger = Logger.getLogger(OIDAOConsultPaper.class.getName());
    /**
     * This method deletes only the Attachment of the respective paper
     * 
     * 1. gets connection from the base class
     * 2. create preparedStatement
     * 3. sets value of the primary key to the prepare statement, which will update the Attachment field to null in the database
     * 4. execute the query 
     */
    public boolean removeAttachment(int pPaperId,Connection connection) throws Exception
    {
        boolean flag=false;
        int i=0;
        PreparedStatement preparedStatement = null;
        try
        {
            preparedStatement=connection.prepareStatement(OIConsultationSqls.CONSULT_REMOVEATTACHMENT);
            preparedStatement.setInt(1,pPaperId);
            i = preparedStatement.executeUpdate();
            //preparedStatement.close();
        }catch(SQLException sqle)
        {
            logger.error(sqle.getMessage());
            //connection.rollback();
            throw sqle;
        }
        finally
        {
            //freeConnection();
            if (preparedStatement!=null)
                preparedStatement.close();
        }
        if (i==0)
        {
            logger.error("RemoveAttachment Failed");
            throw new Exception("RemoveAttachment Failed");
        }
        else
        {
            flag = true;
        }

        return flag;
    }
    
    /**
     * 1. gets connection from the base class
     * 2. create preparedStatement
     * 3. sets value to the prepare statement
     * 4. save it to the database 
     */
    public boolean save(OIBAConsultPaper pOIBAConsultPaper,Connection connection) throws Exception
    {
        boolean flag=false;
        int i=0;
        PreparedStatement preparedStatement = null;
		CallableStatement cstmt = null;
		 ResultSet rs=null;
        try
        {
            if (pOIBAConsultPaper.getPaperId()==0)
            {
				preparedStatement = connection.prepareStatement(OIConsultationSqls.GET_PAPER_ID);
				rs = preparedStatement.executeQuery(); 

				if (rs.next())
				{
					pOIBAConsultPaper.setPaperId(rs.getInt(1));
				}

	            preparedStatement = connection.prepareStatement(OIConsultationSqls.CONSULT_PAPER_INSERT);
				preparedStatement.setInt(1,pOIBAConsultPaper.getPaperId());
	            preparedStatement.setInt(2,pOIBAConsultPaper.getCategoryID());
	            preparedStatement.setString(3,pOIBAConsultPaper.getDescription());
	            preparedStatement.setDate(4,new java.sql.Date(pOIBAConsultPaper.getFromDt().getTime()));
	            preparedStatement.setDate(5,new java.sql.Date(pOIBAConsultPaper.getToDt().getTime()));
	            preparedStatement.setString(6,pOIBAConsultPaper.getTitle());
	            preparedStatement.setString(7,pOIBAConsultPaper.getSecurity());
	            preparedStatement.setString(8,"S");
	            preparedStatement.setString(9,pOIBAConsultPaper.getAttachedFile());
	            preparedStatement.setString(10,pOIBAConsultPaper.getSummary());
	            preparedStatement.setString(11,pOIBAConsultPaper.getTargetAudiance());
	            preparedStatement.setString(12,pOIBAConsultPaper.getReminderMode());
	            preparedStatement.setString(13,pOIBAConsultPaper.getActive());
	            preparedStatement.setString(14,pOIBAConsultPaper.getCreatedBy());
	            preparedStatement.setString(15,pOIBAConsultPaper.getDivCode());
				preparedStatement.setString(16,pOIBAConsultPaper.getContactPerson());
				preparedStatement.setString(17,pOIBAConsultPaper.getPhone());
				preparedStatement.setString(18,pOIBAConsultPaper.getEmailAddress());
				//new fields added
				preparedStatement.setString(19,pOIBAConsultPaper.getStrInstruction());
				preparedStatement.setString(20,pOIBAConsultPaper.getStrCompletionTime());
				preparedStatement.setString(21,pOIBAConsultPaper.getStrFindingsPlannedDt());
				preparedStatement.setString(22,pOIBAConsultPaper.getStrViewFindingType());
				preparedStatement.setString(23,pOIBAConsultPaper.getPublishStatus());
				preparedStatement.setString(24,pOIBAConsultPaper.getStrOpenTag());
				preparedStatement.setString(25,pOIBAConsultPaper.getStrTagWords());
				preparedStatement.setString(26,pOIBAConsultPaper.getStrEmailDate());
				preparedStatement.setString(27,pOIBAConsultPaper.getStrEmailMessage().replaceAll("<<<ID>>>",String.valueOf(pOIBAConsultPaper.getPaperId())));
				
            }
            else
            { 
                preparedStatement = connection.prepareStatement(OIConsultationSqls.CONSULT_PAPER_UPDATE);
	            preparedStatement.setInt(1,pOIBAConsultPaper.getCategoryID());
	            preparedStatement.setString(2,pOIBAConsultPaper.getDescription());
	            preparedStatement.setDate(3,new java.sql.Date(pOIBAConsultPaper.getFromDt().getTime()));
	            preparedStatement.setDate(4,new java.sql.Date(pOIBAConsultPaper.getToDt().getTime()));
	            preparedStatement.setString(5,pOIBAConsultPaper.getTitle());
	            preparedStatement.setString(6,pOIBAConsultPaper.getSecurity());
	            preparedStatement.setString(7,pOIBAConsultPaper.getAttachedFile());
	            preparedStatement.setString(8,pOIBAConsultPaper.getSummary());
	            preparedStatement.setString(9,pOIBAConsultPaper.getTargetAudiance());
	            preparedStatement.setString(10,pOIBAConsultPaper.getReminderMode());
	            preparedStatement.setString(11,pOIBAConsultPaper.getActive());
	            //preparedStatement.setString(12,pOIBAConsultPaper.getDivCode());
				preparedStatement.setString(12,pOIBAConsultPaper.getContactPerson());
				preparedStatement.setString(13,pOIBAConsultPaper.getPhone());
				preparedStatement.setString(14,pOIBAConsultPaper.getEmailAddress());

				//new fields added
				preparedStatement.setString(15,pOIBAConsultPaper.getStrInstruction());
				preparedStatement.setString(16,pOIBAConsultPaper.getStrCompletionTime());
				preparedStatement.setString(17,pOIBAConsultPaper.getStrFindingsPlannedDt());
				preparedStatement.setString(18,pOIBAConsultPaper.getStrViewFindingType());
				preparedStatement.setString(19,pOIBAConsultPaper.getPublishStatus());
				preparedStatement.setString(20,pOIBAConsultPaper.getStrOpenTag());
				preparedStatement.setString(21,pOIBAConsultPaper.getStrTagWords());
				preparedStatement.setString(22,pOIBAConsultPaper.getStrEmailDate());
				preparedStatement.setString(23,pOIBAConsultPaper.getStrEmailMessage());

	            preparedStatement.setInt(24,pOIBAConsultPaper.getPaperId());
				//logger.error("set Paper det qry =="+OIConsultationSqls.CONSULT_PAPER_UPDATE+"--"+pOIBAConsultPaper.getPaperId());
            }
            i = preparedStatement.executeUpdate();
			//logger.error("value of i is "+i);
			try{
					
					cstmt= connection.prepareCall(OIConsultationSqls.ADD_CP_MEMBERS);
					cstmt.setInt(1,pOIBAConsultPaper.getPaperId());
					cstmt.setString(2,pOIBAConsultPaper.getStrTargetGpIds());
					
					cstmt.execute();
				} catch(SQLException se) {
						logger.error(" add cp gp() "+se.getMessage());
						throw se;
				}
				finally {
					OISQLUtilities.closeStatement(cstmt);
				}
			
            //preparedStatement.close();
        }catch(Exception sqle)
        {
            logger.error(sqle.getMessage());
            //connection.rollback();
            throw sqle;
        }
        finally
        {
            //freeConnection();
            if (preparedStatement!=null)
                preparedStatement.close();
		   if(rs!=null)
			   rs.close();
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
        return flag;
    }

    /**
     * This method reads the paperID of the passed PaperName 
     */
    public OIBAConsultPaper getPaperID(String pPaperName,Connection connection) throws Exception
    {
        OIBAConsultPaper aOIBAConsultPaper=null;
        PreparedStatement preparedStatement = null;
        ResultSet rs=null;
        try
        {
            preparedStatement = connection.prepareStatement(OIConsultationSqls.CONSULT_PAPER_READ_WITH_PAPERNAME);
            preparedStatement.setString(1,pPaperName);
            rs = preparedStatement.executeQuery(); 

            if (rs.next())
            {
                aOIBAConsultPaper = new OIBAConsultPaper();
                aOIBAConsultPaper.setActive(rs.getString(OIConsultConstant.Q_ACTIVE));
                aOIBAConsultPaper.setAttachedFile(rs.getString(OIConsultConstant.Q_ATTACHED_FILE));
                aOIBAConsultPaper.setCategoryID(rs.getInt(OIConsultConstant.Q_CATEGORYID));
                aOIBAConsultPaper.setCreatedBy(rs.getString(OIConsultConstant.Q_CREATED_BY));
                aOIBAConsultPaper.setDescription(rs.getString(OIConsultConstant.Q_DESCRIPTION));
                aOIBAConsultPaper.setFromDt(rs.getDate(OIConsultConstant.Q_FROM_DT));
                aOIBAConsultPaper.setToDt(rs.getDate(OIConsultConstant.Q_TO_DT));
                aOIBAConsultPaper.setMailStatus(rs.getString(OIConsultConstant.Q_MAIL_STATUS));
                aOIBAConsultPaper.setPaperId(rs.getInt(OIConsultConstant.Q_PAPERID));
                aOIBAConsultPaper.setReminderMode(rs.getString(OIConsultConstant.Q_REMINDER_MODE));
                aOIBAConsultPaper.setSecurity(rs.getString(OIConsultConstant.Q_SECURITY));
                aOIBAConsultPaper.setSummary(rs.getString(OIConsultConstant.Q_SUMMARY));
                aOIBAConsultPaper.setTargetAudiance(rs.getString(OIConsultConstant.Q_TARGETAUDIENCE));
				aOIBAConsultPaper.setContactPerson(rs.getString(OIConsultConstant.Q_CONTACTPERSON));
				aOIBAConsultPaper.setPhone(rs.getString(OIConsultConstant.Q_PHONE));
				aOIBAConsultPaper.setEmailAddress(rs.getString(OIConsultConstant.Q_EMAILADDRESS));
                aOIBAConsultPaper.setTitle(rs.getString(OIConsultConstant.Q_TITLE));
                aOIBAConsultPaper.setPublishedStatus(rs.getString(OIConsultConstant.Q_PUBLISHED_ST));
                aOIBAConsultPaper.setPublishedOn(rs.getDate(OIConsultConstant.Q_PUBLISHED_ON));
                aOIBAConsultPaper.setAttachedSum(rs.getString(OIConsultConstant.Q_ATTACHED_SUM));

				//new fields added
				aOIBAConsultPaper.setStrInstruction(rs.getString("INSTRUCTIONS"));
				aOIBAConsultPaper.setStrCompletionTime(rs.getString("COMPLETIONTIME"));
				aOIBAConsultPaper.setStrFindingsPlannedDt(rs.getString("FINDING_PUBLISHED_PLANNED_DATE"));
				aOIBAConsultPaper.setStrViewFindingType(rs.getString("FINDINGS_VIEW_TYPE"));
				aOIBAConsultPaper.setPublishStatus(rs.getString("PUBLISHED_ST"));
				aOIBAConsultPaper.setStrOpenTag(rs.getString("OPENTAG"));
				aOIBAConsultPaper.setStrTagWords(rs.getString("TAG_WORDS"));
				aOIBAConsultPaper.setStrEmailDate(rs.getString("EMAILDATE"));
				aOIBAConsultPaper.setStrEmailMessage(OIUtilities.clobToString(rs.getClob("EMAILMESSAGE")));
				preparedStatement = connection.prepareStatement(OIConsultationSqls.CONSULT_GP_IDS);
				preparedStatement.setInt(1, aOIBAConsultPaper.getPaperId());
				rs = preparedStatement.executeQuery();
				String gpIds="";
				while(rs.next())
				{
					gpIds+=rs.getString("GROUPID")+",";
				}
				//logger.error("gpIds==="+gpIds);
				aOIBAConsultPaper.setStrTargetGpIds(gpIds);


            }
            //preparedStatement.close();
        }catch(Exception sqle)
        {
            logger.error(sqle.getMessage());
            //connection.rollback();
            throw sqle;
        }
        finally
        {
            //freeConnection();
            if (preparedStatement!=null)
                preparedStatement.close();
            if (rs!=null)
                rs.close();
        }

        return aOIBAConsultPaper;
    }
    /**
     * 1. gets connection from the base class
     * 2. create preparedStatement
     * 3. sets value of the primary keys to the prepare statement, which will delete that record from the database
     * 4. execute the query 
     */
    public boolean delete(int pPaperId,Connection connection) throws Exception
    {
        boolean flag=false;
        //int i=0;
        PreparedStatement preparedStatement = null;
        try
        {
            preparedStatement=connection.prepareStatement(OIConsultationSqls.CONSULT_PAPER_DELETE);
            preparedStatement.setInt(1,pPaperId);
            flag = preparedStatement.execute();
            //preparedStatement.close();
        }catch(SQLException sqle)
        {
            logger.error(sqle.getMessage());
            //connection.rollback();
            throw sqle;
        }
        finally
        {
            //freeConnection();
            if (preparedStatement!=null)
                preparedStatement.close();
        }

        return flag;
    }
   
    /**
     * This method reads the consultation paper of the passes paperID
     * 
     * It returns OIBAConsultPaper 
     */
    public OIBAConsultPaper read(int pPaperId,Connection connection) throws Exception
    {
        OIBAConsultPaper aOIBAConsultPaper=null;
        PreparedStatement preparedStatement = null;
        ResultSet rs=null;
        try
        {
            preparedStatement = connection.prepareStatement(OIConsultationSqls.CONSULT_PAPER_READ);
            preparedStatement.setInt(1,pPaperId);
            rs = preparedStatement.executeQuery(); 

            if (rs.next())
            {
                aOIBAConsultPaper = new OIBAConsultPaper();
                aOIBAConsultPaper.setActive(rs.getString(OIConsultConstant.Q_ACTIVE));
                aOIBAConsultPaper.setAttachedFile(rs.getString(OIConsultConstant.Q_ATTACHED_FILE));
                aOIBAConsultPaper.setCategoryID(rs.getInt(OIConsultConstant.Q_CATEGORYID));
                aOIBAConsultPaper.setCreatedBy(rs.getString(OIConsultConstant.Q_CREATED_BY));
                aOIBAConsultPaper.setDescription(rs.getString(OIConsultConstant.Q_DESCRIPTION));
                aOIBAConsultPaper.setFromDt(rs.getDate(OIConsultConstant.Q_FROM_DT));
                aOIBAConsultPaper.setToDt(rs.getDate(OIConsultConstant.Q_TO_DT));
                aOIBAConsultPaper.setMailStatus(rs.getString(OIConsultConstant.Q_MAIL_STATUS));
                aOIBAConsultPaper.setPaperId(rs.getInt(OIConsultConstant.Q_PAPERID));
                aOIBAConsultPaper.setReminderMode(rs.getString(OIConsultConstant.Q_REMINDER_MODE));
                aOIBAConsultPaper.setSecurity(rs.getString(OIConsultConstant.Q_SECURITY));
                aOIBAConsultPaper.setSummary(rs.getString(OIConsultConstant.Q_SUMMARY));
                aOIBAConsultPaper.setTargetAudiance(rs.getString(OIConsultConstant.Q_TARGETAUDIENCE));
				aOIBAConsultPaper.setContactPerson(rs.getString(OIConsultConstant.Q_CONTACTPERSON));
				aOIBAConsultPaper.setPhone(rs.getString(OIConsultConstant.Q_PHONE));
				aOIBAConsultPaper.setEmailAddress(rs.getString(OIConsultConstant.Q_EMAILADDRESS));
                aOIBAConsultPaper.setTitle(rs.getString(OIConsultConstant.Q_TITLE));
                aOIBAConsultPaper.setPublishedStatus(rs.getString(OIConsultConstant.Q_PUBLISHED_ST));
                aOIBAConsultPaper.setPublishedOn(rs.getDate(OIConsultConstant.Q_PUBLISHED_ON));
                aOIBAConsultPaper.setAttachedSum(rs.getString(OIConsultConstant.Q_ATTACHED_SUM));
                aOIBAConsultPaper.setDivCode(rs.getString("DIVISIONCODE"));
				//new fields added
				aOIBAConsultPaper.setStrInstruction(rs.getString("INSTRUCTIONS"));
				aOIBAConsultPaper.setStrCompletionTime(rs.getString("COMPLETIONTIME"));
				aOIBAConsultPaper.setStrFindingsPlannedDt(rs.getString("FINDING_PUBLISHED_PLANNED_DATE"));
				aOIBAConsultPaper.setStrViewFindingType(rs.getString("FINDINGS_VIEW_TYPE"));
				aOIBAConsultPaper.setPublishStatus(rs.getString("PUBLISHED_ST"));
				aOIBAConsultPaper.setStrOpenTag(rs.getString("OPENTAG"));
				aOIBAConsultPaper.setStrTagWords(rs.getString("TAG_WORDS"));
				aOIBAConsultPaper.setStrEmailDate(rs.getString("EMAILDATE"));
				aOIBAConsultPaper.setStrEmailMessage(OIUtilities.clobToString(rs.getClob("EMAILMESSAGE")));
				preparedStatement = connection.prepareStatement(OIConsultationSqls.CONSULT_GP_IDS);
				preparedStatement.setInt(1, pPaperId);
				rs = preparedStatement.executeQuery();
				String gpIds="";
				while(rs.next())
				{
					gpIds+=rs.getString("GROUPID")+",";
				}
				//logger.error("gpIds==="+gpIds);
				aOIBAConsultPaper.setStrTargetGpIds(gpIds);
					
				
            }
            //preparedStatement.close();
        }catch(Exception sqle)
        {
            logger.error(sqle.getMessage());
            //connection.rollback();
            throw sqle;
        }
        finally
        {
            //freeConnection();
        	OISQLUtilities.closeRsetPstatement(rs, preparedStatement);
//            if (preparedStatement!=null)
//                preparedStatement.close();
//            if (rs!=null)
//                rs.close();
        }

        return aOIBAConsultPaper;
    }
    //Added by edmund
    public OIBAConsultPaper getRespondentsData(int pPaperId,Connection connection) throws Exception
    {
        OIBAConsultPaper aOIBAConsultPaper=null;
        PreparedStatement preparedStatement = null;
        ResultSet rs=null;
        try
        {
            preparedStatement = connection.prepareStatement(OIConsultationSqls.CONSULT_PAPER_READ);
            preparedStatement.setInt(1,pPaperId);
            rs = preparedStatement.executeQuery(); 

            if (rs.next())
            {
                aOIBAConsultPaper = new OIBAConsultPaper();
                aOIBAConsultPaper.setActive(rs.getString(OIConsultConstant.Q_ACTIVE));
                aOIBAConsultPaper.setAttachedFile(rs.getString(OIConsultConstant.Q_ATTACHED_FILE));
                aOIBAConsultPaper.setCategoryID(rs.getInt(OIConsultConstant.Q_CATEGORYID));
                aOIBAConsultPaper.setCreatedBy(rs.getString(OIConsultConstant.Q_CREATED_BY));
                aOIBAConsultPaper.setDescription(rs.getString(OIConsultConstant.Q_DESCRIPTION));
                aOIBAConsultPaper.setFromDt(rs.getDate(OIConsultConstant.Q_FROM_DT));
                aOIBAConsultPaper.setToDt(rs.getDate(OIConsultConstant.Q_TO_DT));
                aOIBAConsultPaper.setMailStatus(rs.getString(OIConsultConstant.Q_MAIL_STATUS));
                aOIBAConsultPaper.setPaperId(rs.getInt(OIConsultConstant.Q_PAPERID));
                aOIBAConsultPaper.setReminderMode(rs.getString(OIConsultConstant.Q_REMINDER_MODE));
                aOIBAConsultPaper.setSecurity(rs.getString(OIConsultConstant.Q_SECURITY));
                aOIBAConsultPaper.setSummary(rs.getString(OIConsultConstant.Q_SUMMARY));
                aOIBAConsultPaper.setTargetAudiance(rs.getString(OIConsultConstant.Q_TARGETAUDIENCE));
				aOIBAConsultPaper.setContactPerson(rs.getString(OIConsultConstant.Q_CONTACTPERSON));
				aOIBAConsultPaper.setPhone(rs.getString(OIConsultConstant.Q_PHONE));
				aOIBAConsultPaper.setEmailAddress(rs.getString(OIConsultConstant.Q_EMAILADDRESS));
                aOIBAConsultPaper.setTitle(rs.getString(OIConsultConstant.Q_TITLE));
                aOIBAConsultPaper.setPublishedStatus(rs.getString(OIConsultConstant.Q_PUBLISHED_ST));
                aOIBAConsultPaper.setPublishedOn(rs.getDate(OIConsultConstant.Q_PUBLISHED_ON));
                aOIBAConsultPaper.setAttachedSum(rs.getString(OIConsultConstant.Q_ATTACHED_SUM));
                aOIBAConsultPaper.setDivCode(rs.getString("DIVISIONCODE"));

				//new fields added
				aOIBAConsultPaper.setStrInstruction(rs.getString("INSTRUCTIONS"));
				aOIBAConsultPaper.setStrCompletionTime(rs.getString("COMPLETIONTIME"));
				aOIBAConsultPaper.setStrFindingsPlannedDt(rs.getString("FINDING_PUBLISHED_PLANNED_DATE"));
				aOIBAConsultPaper.setStrViewFindingType(rs.getString("FINDINGS_VIEW_TYPE"));
				aOIBAConsultPaper.setPublishStatus(rs.getString("PUBLISHED_ST"));
				aOIBAConsultPaper.setStrOpenTag(rs.getString("OPENTAG"));
				aOIBAConsultPaper.setStrTagWords(rs.getString("TAG_WORDS"));
				aOIBAConsultPaper.setStrEmailDate(rs.getString("EMAILDATE"));
				aOIBAConsultPaper.setStrEmailMessage(OIUtilities.clobToString(rs.getClob("EMAILMESSAGE")));
				preparedStatement = connection.prepareStatement(OIConsultationSqls.CONSULT_GP_IDS);
				preparedStatement.setInt(1, pPaperId);
				rs = preparedStatement.executeQuery();
				String gpIds="";
				while(rs.next())
				{
					gpIds+=rs.getString("GROUPID")+",";
				}
				//logger.error("gpIds==="+gpIds);
				aOIBAConsultPaper.setStrTargetGpIds(gpIds);
            }
            //preparedStatement.close();
        }catch(Exception sqle)
        {
            logger.error(sqle.getMessage());
            //connection.rollback();
            throw sqle;
        }
        finally
        {
            //freeConnection();
            if (preparedStatement!=null)
                preparedStatement.close();
            if (rs!=null)
                rs.close();
        }

        return aOIBAConsultPaper;
    }

    public boolean publishPaper(OIBAConsultPaper pOIBAConsultPaper,Connection connection) throws Exception
    {
        boolean flag=false;
        int i=0;
        PreparedStatement preparedStatement = null;
        try
        {
            preparedStatement = connection.prepareStatement(OIConsultationSqls.CONSULT_PUBLISH_UPDATE);
            preparedStatement.setString(1,pOIBAConsultPaper.getSummary());
            preparedStatement.setString(2,pOIBAConsultPaper.getPublishedStatus());
            preparedStatement.setString(3,pOIBAConsultPaper.getAttachedSum());
            preparedStatement.setInt(4,pOIBAConsultPaper.getPaperId());

            i = preparedStatement.executeUpdate();
            //preparedStatement.close();
        }catch(Exception sqle)
        {
            logger.error(sqle.getMessage());
            //connection.rollback();
            throw sqle;
        }
        finally
        {
            //freeConnection();
            if (preparedStatement!=null)
                preparedStatement.close();
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
        return flag;
    }
    
    public boolean removePublishAttachment(int pPaperId,Connection connection) throws Exception
    {
        boolean flag=false;
        int i=0;
        PreparedStatement preparedStatement = null;
        try
        {
            preparedStatement=connection.prepareStatement(OIConsultationSqls.CONSULT_REMOVEPUBLISHATTACHMENT);
            preparedStatement.setInt(1,pPaperId);
            i = preparedStatement.executeUpdate();
            //preparedStatement.close();
        }catch(SQLException sqle)
        {
            logger.error(sqle.getMessage());
            //connection.rollback();
            throw sqle;
        }
        finally
        {
            //freeConnection();
            if (preparedStatement!=null)
                preparedStatement.close();
        }
        if (i==0)
        {
            logger.error("RemoveAttachment Failed");
            throw new Exception("RemoveAttachment Failed");
        }
        else
        {
            flag = true;
        }

        return flag;
    }

    public boolean checkActivePaper(int pPaperId, Connection connection) throws SQLException
    {
        boolean flag=false;
        PreparedStatement preparedStatement = null;
        ResultSet rs=null;
        try
        {
            //getConnection();
            preparedStatement = connection.prepareStatement(OIConsultationSqls.CONSULT_PAPER_ACTIVE_PAPER_CHECK);
            preparedStatement.setInt(1,pPaperId);
            rs = preparedStatement.executeQuery();
            
            if (rs.next())
            {
                int count = rs.getInt("count");
                if (count==0)
                    flag=true;
            }
            //preparedStatement.close();
        }catch(SQLException sqle)
        {
            logger.error(sqle.getMessage());
            //connection.rollback();
            throw sqle;
        }
        finally
        {
            //freeConnection();
            if (preparedStatement!=null)
                preparedStatement.close();
            if (rs!=null)
                rs.close();
        }        
        return flag;
    }

    public boolean checkNotActivePaper(int pPaperId, Connection connection) throws SQLException
    {
        boolean flag=true;
        PreparedStatement preparedStatement = null;
        ResultSet rs=null;
        try
        {
            //getConnection();
            preparedStatement = connection.prepareStatement(OIConsultationSqls.CONSULT_PAPER_NOT_ACTIVE_PAPER_CHECK);
            preparedStatement.setInt(1,pPaperId);
            rs = preparedStatement.executeQuery();
            
            if (rs.next())
            {
                int count = rs.getInt("count");
                if (count==0)
                    flag=false;
            }
            //preparedStatement.close();
        }catch(SQLException sqle)
        {
            logger.error(sqle.getMessage());
            //connection.rollback();
            throw sqle;
        }
        finally
        {
            //freeConnection();
        	OISQLUtilities.closeRsetPstatement(rs, preparedStatement);
//            if (preparedStatement!=null)
//                preparedStatement.close();
//            if (rs!=null)
//                rs.close();
        }        
        return flag;
    }

    public boolean checkDuplicatePaper(String pTitle,int pPaperId,int pCategoryId,Connection connection) throws Exception
    {
        boolean flag=false;
        PreparedStatement preparedStatement = null;
        ResultSet rs=null;
        try
        {
            //getConnection();
            if (pPaperId==0)
            {
                preparedStatement = connection.prepareStatement(OIConsultationSqls.CONSULT_CHECK_DUPLICATE_PAPER);
                preparedStatement.setInt(1,pCategoryId);
                preparedStatement.setString(2,pTitle);
            }
            else
            {
                preparedStatement = connection.prepareStatement(OIConsultationSqls.CONSULT_CHECK_DUPLICATE_PAPER1);
                preparedStatement.setInt(1,pCategoryId);
                preparedStatement.setString(2,pTitle);                
                preparedStatement.setInt(3,pPaperId);
            }
            rs = preparedStatement.executeQuery();
            
            if (rs.next())
            {
                int count = rs.getInt("count");
                if (count==0)
                    flag=true;
            }
            //preparedStatement.close();
        }catch(SQLException sqle)
        {
            logger.error(sqle.getMessage());
            //connection.rollback();
            throw sqle;
        }
        finally
        {
            //freeConnection();
            if (preparedStatement!=null)
                preparedStatement.close();
            if (rs!=null)
                rs.close();
        }        
        return flag;
    }
    
    public boolean deleteMembers(int pPaperId,Connection connection) throws Exception
    {
        boolean flag=false;
        //int i=0;
        PreparedStatement preparedStatement = null;
        try
        {
            preparedStatement=connection.prepareStatement(OIConsultationSqls.CONSULT_MEMBER_DELETE);
            preparedStatement.setInt(1,pPaperId);
            flag = preparedStatement.execute();
            //preparedStatement.close();
        }catch(SQLException sqle)
        {
            logger.error(sqle.getMessage());
            //connection.rollback();
            throw sqle;
        }
        finally
        {
            //freeConnection();
            if (preparedStatement!=null)
                preparedStatement.close();
        }

        return flag;
    }

	public String importPaper(Integer importPaperId,Connection connection, String userId) throws Exception
    {
        String paperId=null;
		int pId = 0;
		CallableStatement cstmt = null;
		
		try{
			if(importPaperId != null) {
				
				
					//logger.error("copy query "+OIConsultationSqls.COPY_QUERY);
					cstmt= connection.prepareCall(OIConsultationSqls.COPY_QUERY);
					cstmt.setInt(1,importPaperId.intValue());
					cstmt.registerOutParameter(2,java.sql.Types.INTEGER);
					cstmt.setString(3, userId);
					
					cstmt.execute();
					pId = cstmt.getInt (2);

			}
		
		} catch(SQLException se) {
			logger.error(" copyFrom() "+se.getMessage());
			throw se;
		}
		finally {
			OISQLUtilities.closeStatement(cstmt);
		}
		return String.valueOf(pId);
       
    }
	
	public boolean doesPaperExists(Connection con,Integer paperId) throws SQLException
	{
		boolean doesPaperExists = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try
		{
			pstmt = con.prepareStatement(OIConsultationSqls.DOES_PAPER_EXISTS);
			pstmt.setInt(1, paperId.intValue());
			
			rs = pstmt.executeQuery();
			
			while (rs.next())
			{		
				doesPaperExists = true;
			}			
		}
		catch (SQLException se)
		{
			logger.error(" doesPaperExists() " + se.getMessage());
			throw se;			
		}
		finally
		{
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}		
		return doesPaperExists;
	}
	
	public String getPaperDefaultMessage(Connection con) throws SQLException
	{
		PreparedStatement pst = null;
		ResultSet rs = null;
		String defaultMessage = "";
		
		try
		{
			pst = con.prepareStatement(OIConsultationSqls.QRY_CP_DEFAULT_MAIL_MESSAGE);
			
			rs = pst.executeQuery();
			
			if (rs.next())
				defaultMessage = rs.getString("DESCRIPTION");
		}
		catch (SQLException se)
		{
			logger.error("getPaperDefaultMessage() " + se.getMessage());
			throw se;			
		}
		finally
		{
			OISQLUtilities.closeRsetPstatement(rs, pst);
		}		
		return defaultMessage;
	}
}
