package com.oifm.consultation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseDao;
import com.oifm.survey.OISurveySqls;
import com.oifm.utility.OISQLUtilities;
public class OIDAOConsultMember extends OIBaseDao 
{
    Logger logger = Logger.getLogger(OIDAOConsultMember.class.getName());
    public void saveMember(OIBAMember pOIBAMember)
    {
    }
    
    public OIBAMember readMember(OIBAMember pOIBAMember,Connection connection) throws Exception
    {
        PreparedStatement preparedStatement=null;
        ResultSet rs = null;
        try
        {
            preparedStatement=connection.prepareStatement(OIConsultationSqls.CONSULT_PAPER_MEMBERS_READ);
            preparedStatement.setInt(1,pOIBAMember.getPaperId());
            preparedStatement.setString(2,pOIBAMember.getUserId());
            rs = preparedStatement.executeQuery();
            
            if (rs.next())
            {
                pOIBAMember.setInitiationDt(rs.getDate(OIConsultConstant.Q_INVITATION_DT));
                pOIBAMember.setMemberId(rs.getInt(OIConsultConstant.Q_MEMBERID));
                pOIBAMember.setPaperId(rs.getInt(OIConsultConstant.Q_PAPERID));
                pOIBAMember.setSubmitted(rs.getString(OIConsultConstant.Q_SUBMITTED));
                pOIBAMember.setSubmittedOn(rs.getDate(OIConsultConstant.Q_SUBMITTED_ON));
                pOIBAMember.setUserId(rs.getString(OIConsultConstant.Q_USERID));
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
//       	 Start : Added by deva on Sep 26, 2007
			OISQLUtilities.closeRsetPstatement(rs, preparedStatement);
        }

        return pOIBAMember;
    }

    public boolean updateMember(OIBAMember pOIBAMember,Connection connection) throws Exception
    {
        boolean flag=false;
        PreparedStatement preparedStatement=null;
        try
        {
            preparedStatement=connection.prepareStatement(OIConsultationSqls.CONSULT_PAPER_MEMBERS_UPDATE);
            preparedStatement.setInt(1,pOIBAMember.getMemberId());
            int i = preparedStatement.executeUpdate();

            if (i==0)
            {
                logger.error("Save Failed");
                throw new Exception("Save Failed");
            }
            else
            {
                flag = true;
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
        }

        return flag;
    }
    
    private LinkedHashMap getPaperMembers (Connection connection, int paperId) throws Exception
	{
		LinkedHashMap hmMap = new LinkedHashMap();
		
		ResultSet objRs = null;
		PreparedStatement objPs = null;
		ResultSet objRs2 = null;
		PreparedStatement objPs2 = null;
		
		try
		{
			// Survey private group members
			objPs = connection.prepareStatement(OIConsultationSqls.QRY_CP_MEMBERS);
			objPs.setInt(1, paperId);
			objRs = objPs.executeQuery();
			
			if (objRs != null)
			{
				while (objRs.next())
				{
					hmMap.put(objRs.getString(1), objRs.getString(1));
				}
			}
			
//			 Start : Added by deva on Sep 26, 2007
			OISQLUtilities.closeRsetPstatement(objRs, objPs);
			// Survey fixed group members
			objPs = connection.prepareStatement(OIConsultationSqls.QRY_CP_FIXED_GROUP_CONDITION);
			objPs.setInt(1, paperId);
			objRs = objPs.executeQuery();
			
			if (objRs != null)
			{
				while (objRs.next())
				{
					String condition = objRs.getString(1);
					
					objPs2 = connection.prepareStatement(OIConsultationSqls.QRY_USERIDS_ON_CONDITION + condition);
					objRs2 = objPs2.executeQuery();
					
					if (objRs2 != null)
					{
						while (objRs2.next())
						{
							hmMap.put(objRs2.getString(1), objRs2.getString(1));
						}
					}
//					Start : Added by deva on Sep 26, 2007
					OISQLUtilities.closeRsetPstatement(objRs2, objPs2);
				}
			}
			
			// Survey interest group members
//			objPs = connection.prepareStatement(OIConsultationSqls.QRY_CP_TAG_WORDS);
//			objPs.setInt(1, paperId);
//			objRs = objPs.executeQuery();
//			
//			if (objRs != null)
//			{
//				while (objRs.next())
//				{
//					String tagWord = objRs.getString(1);
//					objPs2 = connection.prepareStatement(OIConsultationSqls.QRY_USERIDS_ON_CONDITION);
//					objPs2.setString(1, tagWord);
//					objRs2 = objPs2.executeQuery();
//					
//					if (objRs2 != null)
//					{
//						while (objRs2.next())
//						{
//							hmMap.put(objRs2.getString(1), objRs2.getString(1));
//						}
//					}
//				}
//			}
		}
		catch (Exception sqle)
		{
			logger.error("getPaperMembers - " + sqle.getMessage());
			throw sqle;
		}
		finally
		{
//			 Start : Added by deva on Sep 26, 2007
			OISQLUtilities.closeRsetPstatement(objRs, objPs);
			OISQLUtilities.closeRsetPstatement(objRs2, objPs2);
		}
		
		return hmMap;
	}
}
