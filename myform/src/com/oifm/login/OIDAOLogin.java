package com.oifm.login;
/*
 * Class Name:
 * Description:
 * 
 * 	Created By			Created/Modified on			Revision				Remarks
 * -----------------------------------------------------------------------------------------------------
 * 	Rajkumar			28/06/2005					Draft					Inital Version
 * 
 * -----------------------------------------------------------------------------------------------------
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseDao;
import com.oifm.common.OILabelConstants;

public class OIDAOLogin extends OIBaseDao
{
    Logger logger = Logger.getLogger(OIDAOLogin.class.getName());
    
    public boolean checkOIFMUser(String userId, Connection connection) throws Exception
    {
        boolean flag=false;
        PreparedStatement preparedStatement = null;
        ResultSet rs=null;
        try
        {
            //getConnection();
            preparedStatement = connection.prepareStatement(OILoginSqls.CHECK_USERID);
            logger.info("checkOIFMUser query is"+OILoginSqls.CHECK_USERID);
            preparedStatement.setString(1,userId);
            logger.info("userId is"+userId);
            rs = preparedStatement.executeQuery();
            
            if (rs.next())
            {
                int count = rs.getInt(OILoginConstants.Q_aUserCount);
                //if (count==1)
                if(count>0)			// Modified by K.K.Kumaresan on June 24,2009(if the same nric is present more than once)
                    flag=true;
            }
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
        return flag;
    }
    
    public ArrayList readRoles(String userId, Connection connection) throws Exception
    {
        ArrayList arOIBVRole = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs=null;
        try
        {
            arOIBVRole = new ArrayList();
            OIBVRole oiBVRole=null;
            //getConnection();
            preparedStatement = connection.prepareStatement(OILoginSqls.READ_ROLES);
            preparedStatement.setString(1,userId);
            rs = preparedStatement.executeQuery();

            while (rs.next()) 
            {
                oiBVRole = new OIBVRole();
                oiBVRole.setFunctionName(rs.getString(OILoginConstants.Q_afunctionName));
                oiBVRole.setFunctionId(rs.getString(OILoginConstants.Q_afunctionId));
                oiBVRole.setRoleId(rs.getString(OILoginConstants.Q_aroleId));
                oiBVRole.setRoleName(rs.getString(OILoginConstants.Q_aroleName));
                oiBVRole.setDivCode(rs.getString(OILoginConstants.Q_divisionCode));
                oiBVRole.setUserName(rs.getString("NAME"));
                oiBVRole.setEmail(rs.getString("EMAILID"));
                oiBVRole.setNickname(rs.getString("nick"));
                oiBVRole.setUserId(rs.getString("USERID"));
                
                arOIBVRole.add(oiBVRole);
            }
            if (arOIBVRole.size()==0)
                arOIBVRole = null;
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
        return arOIBVRole;
    }
    
    public String readUserName(String userId, Connection connection) throws Exception
    {
        String userName=null;
        PreparedStatement preparedStatement = null;
        ResultSet rs=null;
        try
        {
            //getConnection();
            preparedStatement = connection.prepareStatement(OILoginSqls.READ_USER_NAME);
            preparedStatement.setString(1,userId);
            rs = preparedStatement.executeQuery();
            
            if (rs.next())
            {
                userName = rs.getString("Name");
            }
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
        return userName;
    }
 
    
    /**
     * This method returns the role of given userid
     * @param objCon
     * @param objBV
     * @return
     * @throws Exception
     */
    public String getRoleId (Connection objCon,String strUserId,String StrQuery ) throws Exception{
    	
    	logger.info(OILabelConstants.BEGIN_METHOD_DAO +"getRoleId"); 
    	ResultSet objRs =null;
        PreparedStatement objPs=null;
        boolean bFlag = false;
        String strRoleId = null;
        try{ 
        	  
        	objPs = objCon.prepareStatement(StrQuery);
        	logger.info(StrQuery);
           	objPs.setString(1,strUserId);
            objRs = objPs.executeQuery();
        
            if(objRs!= null && objRs.next()){
            	strRoleId = objRs.getString(1);
           	} 
                    
		}catch(Exception sqle){
			logger.error(sqle.getMessage());
			bFlag = false;
			throw sqle;
		}finally{
			if (objPs!= null){
				objPs.close();
			}
			if (objRs!= null){
				objRs.close();
			}
		}
        		
		logger.info(OILabelConstants.END_METHOD_DAO +"getRoleId");
        return strRoleId;
    }
    
    public String checkTempUser(String userId,String pwd, Connection connection) throws Exception
    {
        String surveyTag=null;
        PreparedStatement preparedStatement = null;
        ResultSet rs=null;
        try
        {
            //getConnection();
            preparedStatement = connection.prepareStatement(OILoginSqls.CHECK_TMP_USER);
            preparedStatement.setString(1,userId);
            preparedStatement.setString(2,pwd);
            rs = preparedStatement.executeQuery();
            
            if (rs.next())
            {
                surveyTag = rs.getString("SURVEYID");
            }
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
        return surveyTag;
    }
}
