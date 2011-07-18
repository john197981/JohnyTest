/*
 * Created on Aug 4, 2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.oifm.codemaster;

import java.util.ArrayList;
import org.apache.log4j.Logger;
import com.oifm.base.OIBaseBo;
import com.oifm.common.OILabelConstants;
import com.oifm.common.OIResponseObject;
import com.oifm.codemaster.OICodeMasterSqls;
import com.oifm.home.OIActionHome;
import com.oifm.useradmin.admin.OIAdminConstants;
import com.oifm.utility.OIFormUtilities;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class OIBOCodeMaster  extends OIBaseBo {
	
	
	/**	Create instance for logger and objects  used**/
	transient private final  Logger logger = Logger.getLogger(OIActionHome.class.getName());
	
	public OIBOCodeMaster(){
		logger.debug(OILabelConstants.BEGIN_METHOD_BO + this.getClass().getName());
	}
 
	
	   /**
	    * This is the main method to process the profile, it will delagate to populate,search methods.
	    * @param objBVUsrPrfSrh
	    * @return 
	    */
	    public OIResponseObject processCdeMaster(OIBACodeMaster objBV)throws Exception{
	    	
	    	logger.debug(OILabelConstants.BEGIN_METHOD_BO + "processProfile");
	     	
	    	/** Get theb Hidden Action form the BV Object **/
	    	String strHidAct = OIFormUtilities.chkIsNull(objBV.getHiddenAction());
	    	boolean bFlag = false;
	    		    			    		
	    		if(strHidAct.equals(OIAdminConstants.SEARCH)){
	    			responseObject = searchCdeMaster(objBV);
	    		}else if(strHidAct.equals(OIAdminConstants.UPDATE)){
	    			updateCdeMaster(objBV);
	    			//hidData(objBV);
	    			responseObject = searchCdeMaster(objBV);
	    		}else if(strHidAct.equals(OIAdminConstants.LOAD)){
	    			sltCdeMaster(objBV);
	    			responseObject = searchCdeMaster(objBV);
	    		}
	    		responseObject = getLists(objBV);
	    		
	    	logger.debug(OILabelConstants.END_METHOD_BO + "processProfile");
	    	   	
			return responseObject;
	    	
	    }
	    

	    
	    /**
	     * This method populates the dropdowns for code types
	     * @param objBV
	     * @return
	     */
	    		
	    		
	    private OIResponseObject getLists(OIBACodeMaster objBV) throws Exception {
	    	
	    	    logger.debug(OILabelConstants.BEGIN_METHOD_BO + "getLists");
	        	ArrayList alCombo = new ArrayList();
	        	OIDAOCodeMaster objDAO = new OIDAOCodeMaster();
	        	try{
	    	    	  /**Create the Connection Object **/
	    	    	  getConnection();
	    	    	  
	    	    	  /** Get the List of Codes Types**/ 
	    	    	  alCombo = objDAO.readCodeTypes(connection);
	    	    	  addList(alCombo ,OICodeMasterSqls.CODETYPE);
	    	    	  
	    	    	  
	    	}catch (Exception ex){
	    		
	    			logger.error(OILabelConstants.EXCEPTION_IN_BO,ex);
	    			responseObject.addResponseObject(OILabelConstants.ERROR,ex.getMessage());
	    		
	    	}finally{
	    		freeConnection();
	    		alCombo = null;		
	    	}
	    	logger.debug(OILabelConstants.END_METHOD_BO + "getLists");		 
	    	return responseObject;
	     }
	   
	    
	    /** This is the helper to add arraylist to responseobject
	     * @param alList
	     * @param strKey
	     * @return
	     */
	    
	    private void addList(ArrayList alList ,String strKey ){
	      //	if(alList!=null){
	    	  	responseObject.addResponseObject(strKey,alList);
	    	  	logger.debug("strKey "+ strKey);	
	    	//  }
	    }
	    
	    /**
	     * This bo method fetchs the code master details 
	     * @param objBV  
	     * @return
	     */
	    	    
	    private OIResponseObject searchCdeMaster(OIBACodeMaster objBV) throws Exception{
	    
	    	logger.debug(OILabelConstants.BEGIN_METHOD_BO + "searchCdeMaster");
	    	ArrayList alCde = new ArrayList();
	    	OIDAOCodeMaster objDAO = new OIDAOCodeMaster();
	    	boolean bFlag = false;
	    	try{
	    	
	    		 /**Create the Connection Object **/ 
		    	  getConnection();	
	    		 /**DAO call to search the Code Master **/
		    	  alCde = objDAO.searchCdeMaster(connection,objBV);
		    	  if(alCde!=null){
		    	  	logger.debug("alCde"+alCde.size());
		    	  		responseObject.addResponseObject(OILabelConstants.OBJARBV,alCde);
		    	  }
	    	
		    }catch (Exception ex){
		    		logger.error(OILabelConstants.EXCEPTION_IN_BO,ex);
					responseObject.addResponseObject(OILabelConstants.ERROR,ex.getMessage());
					throw ex;
			}finally{
				freeConnection();
				alCde = null;		
			}
			logger.debug(OILabelConstants.END_METHOD_BO + "searchCdeMaster");		 
			return responseObject;
	   
	    }
	    
	    
	    /**
	     * This method update the code master value
	     * @param objBV
	     * @return
	     */
	    private  boolean updateCdeMaster(OIBACodeMaster objBV)throws Exception{
		 	
		 	logger.debug(OILabelConstants.BEGIN_METHOD_BO + "updateCdeMaster");
		 	OIDAOCodeMaster objDAO = new OIDAOCodeMaster();
		 	boolean bFlag =false;   	
		   	try{
				    /** Create the Connection Object **/
			    	getConnection();
			    	connection.setAutoCommit(false);
			    	bFlag = objDAO.uptCdeMaster(connection, objBV);
				    connection.commit();
			  
				     	
		   	}catch (Exception ex){
		   			logger.error(OILabelConstants.EXCEPTION_IN_BO,ex);
		   			responseObject.addResponseObject(OILabelConstants.ERROR,ex.getMessage());
		   			throw ex;
		   	}finally{
		   			freeConnection();
		   	}
			logger.debug(OILabelConstants.END_METHOD_BO + "updateCdeMaster");	
			return bFlag;
		
		  } 

	
	    /**
	     * This method selects the Code Master information based on the given code id
	     * @param objBV
	     * @return
	     * @throws Exception
	     */	
	    private boolean sltCdeMaster(OIBACodeMaster objBV) throws Exception{
		    
		    	logger.debug(OILabelConstants.BEGIN_METHOD_BO + "searchCdeMaster");
		    	ArrayList alCde = new ArrayList();
		    	OIDAOCodeMaster objDAO = new OIDAOCodeMaster();
		    	boolean bFlag = false;
		    	try{
		    	
		    		 /**Create the Connection Object **/ 
			    	  getConnection();	
		    		 /**DAO call to search the Code Master **/
			    	  bFlag = objDAO.sltCdeMaster(connection,objBV);
			    	 		    	
			    }catch (Exception ex){
			    		logger.error(OILabelConstants.EXCEPTION_IN_BO,ex);
						responseObject.addResponseObject(OILabelConstants.ERROR,ex.getMessage());
						throw ex;
				}finally{
					freeConnection();
					alCde = null;		
				}
				logger.debug(OILabelConstants.END_METHOD_BO + "searchCdeMaster");		 
				return bFlag;
		   
		    }
    		
	    
	    /**
	     * This method sets hidden search critiera for search fields.
	     * @param objBV
	     */
	    private void hidData(OIBACodeMaster objBV){
	       	objBV.setType(objBV.getHidType());
			objBV.setValue(objBV.getHidCode());
			objBV.setDescription(objBV.getHidDesc());
			objBV.setShortName(objBV.getHidName());
			objBV.setObsolete(objBV.getHidObs());
	    }
}

    