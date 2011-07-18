package com.oifm.codemaster;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import com.oifm.base.OIBaseDao;
import com.oifm.common.OILabelConstants;
import com.oifm.useradmin.admin.OIAdminSqls;
import com.oifm.utility.OIDBRegistry;
import com.oifm.utility.OIFormUtilities;


/**
 * 
 * @author Suresh
 * This DAO class to maintain the Code Master table to update
 * and fetch the records for the display
 * 
 */

public class OIDAOCodeMaster extends OIBaseDao
{
	Logger logger = Logger.getLogger(OIDAOCodeMaster.class.getName());
	
	/**
	 * This method updates code master records
	 * @param objCon
	 * @param objBV
	 * @return
	 * @throws Exception
	 */
    public boolean uptCdeMaster(Connection objCon, OIBACodeMaster objBV) throws Exception
    {
    	logger.debug(OILabelConstants.BEGIN_METHOD_DAO +"uptCdeMaster"); 
        PreparedStatement objPs=null;
        boolean bFlag = false;
        int nUpate =0;
        String strObslete = null;
        try
        { 
        	objPs = objCon.prepareStatement(OICodeMasterSqls.QRY_UPDATE);
        	logger.debug(OIAdminSqls.QRY_PROFILE_UPDATE);
        	strObslete = objBV.getObsolete(); 
        	if(strObslete==null)
        	{
        		strObslete = OILabelConstants.FLAG_N;
        	}
        	objPs.setString(1,objBV.getDescription());
        	objPs.setString(2,objBV.getShortName());
			objPs.setString(3,strObslete);
			objPs.setInt(4,Integer.parseInt(objBV.getStrCodeId()));
			        	
			nUpate = objPs.executeUpdate();
        	if(nUpate > 0)
        	{
            	bFlag = true;
            	logger.debug("Records Update "+nUpate);
            }
		}
        catch(Exception sqle)
        {
			logger.error(sqle.getMessage());
			bFlag = false;
			throw sqle;
		}
        finally
        {
			if (objPs!= null)
			{
				objPs.close();
			}
	    }
       
		logger.debug(OILabelConstants.END_METHOD_DAO +"uptCdeMaster");
        return bFlag;
    }

	/**
	 * This method populates List of code Types
	 * @param connection
	 * @return ArrayList
	 * @throws Exception
	 */
    public ArrayList readCodeTypes(Connection objCon) throws Exception
    {
    	logger.debug(OILabelConstants.BEGIN_METHOD_DAO +"readCodeTypes"); 
    	ArrayList alList=null;
        ResultSet objRs =null;
        PreparedStatement objPs=null;
        boolean bFlag = false;
        try
        { 
        	objPs = objCon.prepareStatement(OICodeMasterSqls.QRY_CODE_TYPE);
           	logger.debug(OICodeMasterSqls.QRY_CODE_TYPE);
            objRs = objPs.executeQuery();
            String strVal = null;
            if(objRs!= null)
            {
           		bFlag = true;
           		alList = new ArrayList(); 
           		alList.add(new org.apache.struts.util.LabelValueBean(OIDBRegistry.getValues(OILabelConstants.PLS_SELECT),""));
           		while(objRs.next())
           		{
           			alList.add(new org.apache.struts.util.LabelValueBean(OIFormUtilities.chkIsNull(objRs.getString(1)),OIFormUtilities.chkIsNull(objRs.getString(1))));
           		}
        	} 
		}
        catch(Exception sqle)
        {
			logger.error(sqle.getMessage());
			bFlag = false;
			throw sqle;
		}
        finally
        {
			if (objPs!= null)
			{
				objPs.close();
			}
			if (objRs!= null)
			{
				objRs.close();
			}
		}
        if (alList.size()==0)
        {
        	alList=null;
        }		
             
    	logger.debug(OILabelConstants.END_METHOD_DAO +"readCodeTypes");
    	return alList;
    }

    
   /**
    * This method fetches the code master details based on the seach criteria
    * @param objCon
    * @param objBV
    * @return
    * @throws Exception
    */
    
    public ArrayList searchCdeMaster(Connection objCon, OIBACodeMaster objBV) throws Exception
    {
    	logger.debug(OILabelConstants.BEGIN_METHOD_DAO +"searchCdeMaster"); 
				
		ArrayList arCdeMaster=null;
		ResultSet objRs =null;
		PreparedStatement objPs=null;
		OIBACodeMaster objArr = null;
		boolean bFlag = false;
		String strQuery = null;
		try
		{ 
		    strQuery = objBV.getQuery();
		    logger.debug("Query "+ strQuery);
			if(strQuery==null)
			{ 	  
			    strQuery = constructQuery(objBV);
			}	
					
			logger.debug("Query "+ strQuery);
			objPs = objCon.prepareStatement(strQuery);
			objRs = objPs.executeQuery();
			if(objRs!=null)
			{
			    bFlag = true;
				arCdeMaster = new ArrayList();
				while(objRs.next())
				{
				    objArr = new OIBACodeMaster();
					objArr.setStrCodeId(OIFormUtilities.chkIsNull(objRs.getString(1)));
					objArr.setType(OIFormUtilities.chkIsNull(objRs.getString(2)));
					objArr.setValue(OIFormUtilities.chkIsNull(objRs.getString(3)));
					objArr.setDescription(OIFormUtilities.chkIsNull(objRs.getString(4)));
					objArr.setShortName(OIFormUtilities.chkIsNull(objRs.getString(5)));
					objArr.setObsolete(OIFormUtilities.chkIsNull(objRs.getString(6)));
					arCdeMaster.add(objArr);
				}
			}	
		}
		catch(Exception sqle)
		{
		    logger.error(sqle.getMessage());
			bFlag = false;
			throw sqle;
		}
		finally
		{
		    if (objPs!= null)
		    {
		        objPs.close();
		    }
			if (objRs!= null)
			{
			    objRs.close();
			}
		}
		if (arCdeMaster.size()==0)
		{
			arCdeMaster=null;
		}	
		logger.debug(OILabelConstants.END_METHOD_DAO +"searchCdeMaster");
		return arCdeMaster;
    }    
    
    /**
     * This is helper method to construct the search query
     * @param objBV
     * @param sbQuery
     */
    private String  constructQuery(OIBACodeMaster objBV)
    {
    	StringBuffer sbQuery = new StringBuffer(OICodeMasterSqls.QRY_SERACH);
    	StringBuffer sbWhere = new StringBuffer(OICodeMasterSqls.WHERE);
    	if(objBV.getType().length()>0)
    	{
    		sbWhere.append(OICodeMasterSqls.CODE_TYPE)
				   .append("'"+objBV.getType().trim()+"'")
    				.append(OICodeMasterSqls.AND);
    	}
    	if(objBV.getValue().length()>0)
    	{
    		sbWhere.append(OICodeMasterSqls.VALUE)
				   .append(objBV.getValue().trim())
    			   .append(OICodeMasterSqls.PERCENT)
    			   .append(OICodeMasterSqls.AND);
    	}		
    	if(objBV.getDescription().length()>0)
    	{
    		sbWhere.append(OICodeMasterSqls.DESCRIPTION)
				   .append(objBV.getDescription().trim())
    			   .append(OICodeMasterSqls.PERCENT)		
    			   .append(OICodeMasterSqls.AND);
    	}
    	if(objBV.getShortName().length()>0)
    	{
    		sbWhere.append(OICodeMasterSqls.SHORTNAME)
				   .append(objBV.getShortName().trim())
    			   .append(OICodeMasterSqls.PERCENT)		
    			   .append(OICodeMasterSqls.AND);
    	}
    	if(objBV.getObsolete()==null)
    	{
    		sbWhere.append("(OBSOLETE = '' OR OBSOLETE IS NULL OR ")
    			   .append(OICodeMasterSqls.OBSOLETE)
				   .append(OILabelConstants.FLAG_N)
    			   .append("'))")		
    			   .append(OICodeMasterSqls.AND);
    	}
    	else if (objBV.getObsolete().length()>0)
    	{
    		sbWhere.append(OICodeMasterSqls.OBSOLETE)
			   .append(objBV.getObsolete().trim())
			   .append("')")		
			   .append(OICodeMasterSqls.AND);
    	}
    	
    	int nIndex = sbWhere.lastIndexOf(OICodeMasterSqls.AND);
     	if(nIndex > 0)
     	{
     	    String strTemp = sbWhere.substring(0,nIndex);
     	 	sbQuery.append(strTemp); 
     	}
     	sbQuery.append(OICodeMasterSqls.ORDER_BY);
     	objBV.setQuery(sbQuery.toString());
    	return sbQuery.toString();
    }
    
    public ArrayList readType(String type,Connection connection) throws Exception
    {
        ArrayList arOIBACodeMaster = new ArrayList();
        OIBACodeMaster aOIBACodeMaster = null;
        PreparedStatement preparedStatement=null;
        ResultSet rs=null;
        try
        {
            preparedStatement=connection.prepareStatement(OICodeMasterSqls.READ_CODEMASTERTYPE_ONE);
            preparedStatement.setString(1,type);
            rs = preparedStatement.executeQuery();
            while(rs.next())
            {
                aOIBACodeMaster = new OIBACodeMaster();
                aOIBACodeMaster.setCodeId(rs.getInt("CODEID"));
                aOIBACodeMaster.setDescription(rs.getString("DESCRIPTION"));
                aOIBACodeMaster.setObsolete(rs.getString("OBSOLETE"));
                aOIBACodeMaster.setType(rs.getString("TYPE"));
                aOIBACodeMaster.setValue(rs.getString("VALUE"));
                arOIBACodeMaster.add(aOIBACodeMaster);
            }
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
        if (arOIBACodeMaster.size()==0)
            arOIBACodeMaster = null;

        return arOIBACodeMaster;
    }


    public ArrayList readType(String type,Connection connection,String type1) throws Exception
    {
        ArrayList arOIBACodeMaster = new ArrayList();
        OIBACodeMaster aOIBACodeMaster = null;
        PreparedStatement preparedStatement=null;
        ResultSet rs=null;
        try
        {
            preparedStatement=connection.prepareStatement(OICodeMasterSqls.READ_CODEMASTERTYPE);
            preparedStatement.setString(1,type);
            preparedStatement.setString(2,type1);
            rs = preparedStatement.executeQuery();
            while(rs.next())
            {
                aOIBACodeMaster = new OIBACodeMaster();
                aOIBACodeMaster.setCodeId(rs.getInt("CODEID"));
                aOIBACodeMaster.setDescription(rs.getString("DESCRIPTION"));
                aOIBACodeMaster.setObsolete(rs.getString("OBSOLETE"));
                aOIBACodeMaster.setType(rs.getString("TYPE"));
                aOIBACodeMaster.setValue(rs.getString("VALUE"));
                arOIBACodeMaster.add(aOIBACodeMaster);
            }
            preparedStatement.close();
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
        if (arOIBACodeMaster.size()==0)
            arOIBACodeMaster = null;

        return arOIBACodeMaster;
    }

    public OIBACodeMaster readType(String pType,String pValue,Connection connection) throws Exception
    {
        OIBACodeMaster aOIBACodeMaster = null;
        PreparedStatement preparedStatement=null;
        ResultSet rs=null;
        try
        {
            preparedStatement=connection.prepareStatement(OICodeMasterSqls.READ_CODEMASTERTYPEVALUE);
            preparedStatement.setString(1,pType);
            preparedStatement.setString(2,pValue);
            rs = preparedStatement.executeQuery();
            while(rs.next())
            {
                aOIBACodeMaster = new OIBACodeMaster();
                aOIBACodeMaster.setCodeId(rs.getInt("CODEID"));
                aOIBACodeMaster.setDescription(rs.getString("DESCRIPTION"));
                aOIBACodeMaster.setObsolete(rs.getString("OBSOLETE"));
                aOIBACodeMaster.setType(rs.getString("TYPE"));
                aOIBACodeMaster.setValue(rs.getString("VALUE"));
            }
            preparedStatement.close();
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

        return aOIBACodeMaster;
    }
    

    
    /**
     * This method selects Code Master Details for the given Codeid
     * @param objCon
     * @param objBV
     * @return
     * @throws Exception
     */
	public boolean sltCdeMaster(Connection objCon, OIBACodeMaster objBV) throws Exception
	{
    	logger.debug(OILabelConstants.BEGIN_METHOD_DAO +"sltCdeMaster"); 
				
		ResultSet objRs =null;
		PreparedStatement objPs=null;
		boolean bFlag = false;
		
		try
		{ 
		    objPs = objCon.prepareStatement(OICodeMasterSqls.QRY_SLT_CDEMASTER);
			logger.debug("Query "+ OICodeMasterSqls.QRY_SLT_CDEMASTER);
			objPs.setInt(1,Integer.parseInt(objBV.getStrCodeId()));
			objRs = objPs.executeQuery();
			if(objRs!=null)
			{
			    bFlag = true;
				while(objRs.next())
				{
				    objBV.setStrCodeId(OIFormUtilities.chkIsNull(objRs.getString(1)));
					objBV.setType(OIFormUtilities.chkIsNull(objRs.getString(2)));
					objBV.setValue(OIFormUtilities.chkIsNull(objRs.getString(3)));
					objBV.setDescription(OIFormUtilities.chkIsNull(objRs.getString(4)));
					objBV.setShortName(OIFormUtilities.chkIsNull(objRs.getString(5)));
					objBV.setObsolete(OIFormUtilities.chkIsNull(objRs.getString(6)));
				}
			}	
		}
		catch(Exception sqle)
		{
		    logger.error(sqle.getMessage());
			bFlag = false;
			throw sqle;
		}
		finally
		{
		    if (objPs!= null)
		    {
		        objPs.close();
		    }
			if (objRs!= null)
			{
			    objRs.close();
			}
		}
		
		logger.debug(OILabelConstants.END_METHOD_DAO +"sltCdeMaster");
		return bFlag;
    }    
}