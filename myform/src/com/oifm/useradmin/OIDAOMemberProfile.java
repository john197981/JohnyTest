package com.oifm.useradmin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseDao;
import com.oifm.forum.OIForumSqls;
import com.oifm.utility.OIUtilities;

public class OIDAOMemberProfile extends OIBaseDao 
{
    Logger logger = Logger.getLogger(OIDAOMemberProfile.class.getName());
    
    public OIBAProfile readMemberProfile(String pUserId,Connection connection)throws Exception
    {
        OIBAProfile aOIBAProfile = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs=null;
        try
        {
            preparedStatement = connection.prepareStatement(OISearchSqls.QRY_MEMBER_PROFILE);
            preparedStatement.setString(1,pUserId);
            preparedStatement.setString(2,pUserId);
            rs = preparedStatement.executeQuery();
            
            if (rs.next()) 
            {
                aOIBAProfile = new OIBAProfile();
                aOIBAProfile.setBirthDt(rs.getDate("BIRTH_DT"));
                aOIBAProfile.setCreatedOn(rs.getDate("CREATED_ON"));
                //aOIBAProfile.setDesignation(rs.getString("DESIGNATION"));
                //aOIBAProfile.setDivision(rs.getString("DIVISION"));
                aOIBAProfile.setDivStatus(rs.getString("DIV_STATUS"));
                aOIBAProfile.setEmailId(rs.getString("EMAILID"));
                aOIBAProfile.setGrade(rs.getString("GRADE"));
                aOIBAProfile.setHobbies(rs.getString("HOBBIES"));
                aOIBAProfile.setInterest(rs.getString("INTEREST"));
                aOIBAProfile.setJoiningDt(rs.getDate("JOINING_DT"));
                aOIBAProfile.setName(rs.getString("NAME"));
                aOIBAProfile.setNickName(rs.getString("NICKNAME"));
                aOIBAProfile.setObsolete(rs.getString("OBSOLETE"));
                aOIBAProfile.setRoleId(rs.getString("ROLEID"));
                aOIBAProfile.setSalutation(rs.getString("SALUTATION"));
                //aOIBAProfile.setSchool(rs.getString("SCHOOL"));
                //aOIBAProfile.setSchoolCluster(rs.getString("SCHOOL_CLUSTER"));
                //aOIBAProfile.setSchoolType(rs.getString("SCHOOL_TYPE"));
                aOIBAProfile.setShowSignature(rs.getString("SHOW_SIGNATURE"));
                aOIBAProfile.setSignature(rs.getString("SIGNATURE"));
                aOIBAProfile.setTotalPapers(rs.getInt("TOTALPAPERS"));
                aOIBAProfile.setTotalPosting(rs.getInt("TOTALPOSTINGS"));
                aOIBAProfile.setTotalSurveys(rs.getInt("TOTALSURVEYS"));
                aOIBAProfile.setUserId(rs.getString("USERID"));
                aOIBAProfile.setUpdatedOn(rs.getDate("UPDATED_ON"));
                //aOIBAProfile.setZoneBranch(rs.getString("ZONEBRANCH"));
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
        return aOIBAProfile;
    }
    
    public boolean updatePapers(String pUserId,Connection connection) throws Exception
    {
        boolean flag=false;
        PreparedStatement preparedStatement = null;
        try
        {
            preparedStatement = connection.prepareStatement(OISearchSqls.QRY_PROFILE_UPDATE_PAPERS);
            preparedStatement.setString(1,pUserId);
            int i = preparedStatement.executeUpdate();
            logger.info(OISearchSqls.QRY_PROFILE_UPDATE_PAPERS + " ---" + pUserId);
            if (i==0)
            {
                logger.error(i + "updatePapers---Save Failed");
                throw new Exception("updatePapers - Save Failed");
            }
            else
            {
                flag = true;
            }
            preparedStatement.close();
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

        return flag;
    }
    
    public int totalPotsing(String pUserId,Connection connection)throws Exception
    {
        PreparedStatement preparedStatement1	 = null;
        ResultSet rs1=null;
        int value=0;
        try
        {
            preparedStatement1 = connection.prepareStatement(OISearchSqls.QRY_TOTAL_POSTING);
            preparedStatement1.setString(1,pUserId);
            rs1 = preparedStatement1.executeQuery();
            
            if (rs1.next()) 
            {
            	value= rs1.getInt(1);
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
            if (preparedStatement1!=null)
                preparedStatement1.close();
            if (rs1!=null)
                rs1.close();
        }
        return value;
    }
                	
// Check for sticky
    
    public String checkSticky(String pUserId,String strThreadID,Connection connection)throws Exception
    {
        PreparedStatement preparedStatement1	 = null;
        ResultSet rs1=null;
        String sticky="";
        int value=0;
        try
        {
            preparedStatement1 = connection.prepareStatement(OIForumSqls.QRY_WP_STICKYTHREAD);
            preparedStatement1.setString(1,pUserId);
            preparedStatement1.setString(2,strThreadID);
            rs1 = preparedStatement1.executeQuery();
            
            if (rs1.next()) 
            {
            	sticky="Sticky";
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
            if (preparedStatement1!=null)
                preparedStatement1.close();
            if (rs1!=null)
                rs1.close();
        }
        return sticky;
    }
    
    public String hotLatMsg(String strTopicId,String strThreadID,Connection connection)throws Exception
    {
        PreparedStatement objPs	 = null;
        ResultSet rs1=null;
        String strMsg="";
        int value=0;
        try
        {
        	objPs = connection.prepareStatement(OIForumSqls.QRY_WP_HL);
         	objPs.setInt (1,Integer.parseInt(strTopicId));
        	objPs.setInt (2,Integer.parseInt(strThreadID));
        	objPs.setInt (3,Integer.parseInt(strTopicId));
        	objPs.setInt (4,Integer.parseInt(strThreadID));
            rs1 = objPs.executeQuery();
            
            if (rs1.next()) 
            {
            	strMsg=rs1.getString(2);
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
            if (objPs!=null)
            	objPs.close();
            if (rs1!=null)
                rs1.close();
        }
        return strMsg;
    }

    public String alreadyPosted(String pUserId,String strTopicId,String strThreadID,Connection connection)throws Exception
    {
        PreparedStatement objPs	 = null;
        ResultSet rs1=null;
        String strAlrPosted="";
        int value=0;
        try
        {
        	objPs = connection.prepareStatement(OIForumSqls.QRY_WP_ALREADY_POSTED);
         	objPs.setInt (1,Integer.parseInt(strTopicId));
        	objPs.setString(2,pUserId);
         	objPs.setInt (3,Integer.parseInt(strThreadID));
            rs1 = objPs.executeQuery();
            
            if (rs1.next()) 
            {
            	strAlrPosted="AlreadyPosted";
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
            if (objPs!=null)
            	objPs.close();
            if (rs1!=null)
                rs1.close();
        }
        return strAlrPosted;
    }

    public String lockSec(String pUserId,String strTopicId,String strThreadID,Connection connection)throws Exception
    {
        PreparedStatement objPs	 = null;
        ResultSet rs1=null;
        String strLockSec="";
        int value=0;
        try
        {
        	objPs = connection.prepareStatement(OIForumSqls.QRY_WP_LP);
        	
        	objPs.setString(1,pUserId);
        	objPs.setString(2,pUserId);
        	objPs.setString(3,pUserId);
        	objPs.setInt (4,Integer.parseInt(strTopicId));
         	objPs.setInt (5,Integer.parseInt(strThreadID));
            rs1 = objPs.executeQuery();
            
            if (rs1.next()) 
            {
            	String strLock=OIUtilities.replaceNull(rs1.getString(1));
             	String strSec=OIUtilities.replaceNull(rs1.getString(2));
             	if(strLock.equalsIgnoreCase("Y")){
             		strLockSec ="Locked";
             	}
             	else if(strSec.equalsIgnoreCase("Y")){
             		strLockSec ="Security";
             	}
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
            if (objPs!=null)
            	objPs.close();
            if (rs1!=null)
                rs1.close();
        }
        return strLockSec;
    }

}
