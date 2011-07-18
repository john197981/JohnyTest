/*****************************************************************************
* Title 		: OIPollBO.java
* Description 	: This is the Business Object for the Poll Module to create,
* 				   modify,view, publish the poll details. 
* Author		: Suresh Kumar.R 
* Version No 	: 1.0
* Date Created 	: 26 - Jul -2005
* Copyright 	: Scandent Group
********************************************************************************/
package com.oifm.poll;


import java.util.ArrayList;
import org.apache.log4j.Logger;
import com.oifm.base.OIBaseBo;
import com.oifm.poll.OIDAOPoll;
import com.oifm.utility.OIDBRegistry;
import com.oifm.utility.OIFormUtilities;
import com.oifm.common.OILabelConstants;
import com.oifm.common.OIResponseObject;



/**
 * 
 * @author Suresh kumar R 
 *  This Business Object is used to process poll module.
 */

public class OIBOPoll extends OIBaseBo{


//	Create instance for logger and objects  used
	transient private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	public OIBOPoll(){
		logger.debug(OILabelConstants.BEGIN_METHOD_BO + this.getClass().getName());
	}
	
	
	/**
	 * This is public method to delegate to various operations Create,modify,view,publish the poll information.
	 * @param objBVPoll
	 * @return
	 */
	
	 public OIResponseObject processPoll(OIBVPoll objBVPoll )throws Exception{
	 	
		logger.debug(OILabelConstants.BEGIN_METHOD_BO + "processPoll");
		
	 		String strAction = objBVPoll.getAction();
	 		if(strAction.trim().length()==0){
	 			responseObject = listPoll(objBVPoll );
	 		}else if (strAction.trim().equals(OIPollConstants.CREATEPOLL)){
	 			if(createPoll(objBVPoll)){
	 				responseObject = listPoll(objBVPoll );
	 			}
	 		}else if(strAction.equals(OIPollConstants.VIEWPOLL)||strAction.equals(OIPollConstants.EXPORT)||
	 				strAction.equals(OIPollConstants.PRINT)){
	 				viewPoll(objBVPoll);
	 				responseObject =responsePoll(objBVPoll);
	 		}else if (strAction.trim().equals(OIPollConstants.MODIFYPOLL)){
	 			if(modifyPoll(objBVPoll)){
	 				responseObject = listPoll(objBVPoll );
	 			}else{
	 			//	viewPoll(objBVPoll);
	 				responseObject =responsePoll(objBVPoll);
	 			}	
	 		}else if (strAction.trim().equals(OIPollConstants.DELETEPOLL)){
	 			if(deletePoll(objBVPoll)){
	 				responseObject = listPoll(objBVPoll);
	 			}
	 		}else if (strAction.equals(OIPollConstants.LOADPOLL)){
	 			publishCnt(objBVPoll );
	 		}
	 			
	 		logger.debug(OILabelConstants.END_METHOD_BO + "processPoll");	
	 	
	 	return responseObject;
	 }
	
	 
	 
	 /**
	  * 
	  * @param objBVPoll
	  * @return
	  */
	 private  boolean createPoll(OIBVPoll objBVPoll ) throws Exception{
	 	logger.debug(OILabelConstants.BEGIN_METHOD_BO + "createPoll");
	 	OIDAOPoll objDAOPoll= new OIDAOPoll();
	 	boolean bFlag =false;   	
	   	try{
			    /** Create the Connection Object **/
		    	getConnection();
		    	
		    	
		    	if(!objDAOPoll.ChkPollDts(connection,objBVPoll,OIPollSqls.QRY_PUB_DATE_ADD)){
		    		connection.setAutoCommit(false);
		    		bFlag = objDAOPoll.createPoll(connection,objBVPoll);
			    	connection.commit();
		    	}	
			     	
	   	}catch (Exception ex){
	   			logger.error(OILabelConstants.EXCEPTION_IN_BO,ex);
	   			responseObject.addResponseObject(OILabelConstants.ERROR,ex.getMessage());
	   		
	   	}finally{
	   			freeConnection();
	   	}
		logger.debug(OILabelConstants.END_METHOD_BO + "createPoll");	
		return bFlag;
	
	  }

	 /**
	  * 
	  * @param objBVPoll
	  * @return
	  */	
	 
	 private boolean modifyPoll(OIBVPoll objBVPoll )throws Exception{
	 	
	 	logger.debug(OILabelConstants.BEGIN_METHOD_BO + "modifyPoll");
	 	OIDAOPoll objDAOPoll= new OIDAOPoll();
		boolean bFlag =false;   	
		ArrayList arBVPoll = null;
	   	
	   	try{
			    /** Create the Connection Object **/
		    	getConnection();
		    	if(!objDAOPoll.ChkPollDts(connection,objBVPoll,OIPollSqls.QRY_PUB_DATE_MODIFY)){
			    	connection.setAutoCommit(false);
			    	bFlag =  objDAOPoll.updatePoll(connection,objBVPoll);
			    	bFlag =  objDAOPoll.updateResCnt(connection,objBVPoll);
			    	connection.commit();
			    	arBVPoll =  objDAOPoll.listPoll(connection,objBVPoll);
			    	if(arBVPoll!=null){
			     	 	responseObject.addResponseObject(OILabelConstants.OBJARBV,arBVPoll);
			     	 }
			    //	viewPoll(objBVPoll);
			    	//responseObject =responsePoll(objBVPoll);
		    	}	
			     	
	   	}catch (Exception ex){
	   			logger.error(OILabelConstants.EXCEPTION_IN_BO,ex);
	   			responseObject.addResponseObject(OILabelConstants.ERROR,ex.getMessage());
	   		
	   	}finally{
	   			freeConnection();
	   	}
		logger.debug(OILabelConstants.END_METHOD_BO + "modifyPoll");	
		return bFlag;
	 }
	 
	  
	 
	 
	 /**
	  * This Method is used to get the list of Poll information.
	  * @param objBVPoll
	  * @return
	  */
	 
	 private OIResponseObject listPoll(OIBVPoll objBVPoll )throws Exception{
		
	 	logger.debug(OILabelConstants.BEGIN_METHOD_BO + "listPoll");
	   	OIDAOPoll objDAOPoll= new OIDAOPoll();
	   	ArrayList arBVPoll = null;
	   	
	   	try{
			      /** Create the Connection Object **/
		    	  getConnection();
		    	  arBVPoll =  objDAOPoll.listPoll(connection,objBVPoll);
		    	  if(arBVPoll!=null){
		     	 	responseObject.addResponseObject(OILabelConstants.OBJARBV,arBVPoll);
		     	 }
			     	
	   	}catch (Exception ex){
	   			logger.error(OILabelConstants.EXCEPTION_IN_BO,ex);
	   			responseObject.addResponseObject(OILabelConstants.ERROR,ex.getMessage());
	   		
	   	}finally{
	   			freeConnection();
	   	}
		logger.debug(OILabelConstants.END_METHOD_BO + "listPoll");	
		return responseObject;
	
	  }
	
	 /**
	  * This Method is used to get the list of Poll information.
	  * @param objBVPoll
	  * @return
	  */
	 
	 private OIResponseObject viewPoll(OIBVPoll objBVPoll )throws Exception{
	 	logger.debug(OILabelConstants.BEGIN_METHOD_BO + "viewPoll");
	   	OIDAOPoll objDAOPoll= new OIDAOPoll();
	   	ArrayList arBVPoll = null;
	   	boolean bFlag = false;
	   	try{
			      /** Create the Connection Object **/
		    	  getConnection();
		    	  bFlag =  objDAOPoll.viewPoll(connection,objBVPoll);
		    	  /** This method get the Publish poll status **/
		    	  objDAOPoll.getPublishCnt(connection,objBVPoll);
		    	  
	   	}catch (Exception ex){
	   			logger.error(OILabelConstants.EXCEPTION_IN_BO,ex);
	   			responseObject.addResponseObject(OILabelConstants.ERROR,ex.getMessage());
	   		
	   	}finally{
	   			freeConnection();
	   	}
		logger.debug(OILabelConstants.END_METHOD_BO + "viewPoll");	
		return responseObject;
	
	  }

	 

	 /**
	  * This Method is used to get the list of Poll information.
	  * @param objBVPoll
	  * @return
	  */
	 
	 private OIResponseObject publishCnt(OIBVPoll objBVPoll )throws Exception{
	 	logger.debug(OILabelConstants.BEGIN_METHOD_BO + "publishCnt");
	   	OIDAOPoll objDAOPoll= new OIDAOPoll();
	   	ArrayList arBVPoll = null;
	   	boolean bFlag = false;
	   	try{
			      /** Create the Connection Object **/
	   			  getConnection();
				   /** This method get the Publish poll status **/
		    	   objDAOPoll.getPublishCnt(connection,objBVPoll);
		    	  
	   	}catch (Exception ex){
	   			logger.error(OILabelConstants.EXCEPTION_IN_BO,ex);
	   			responseObject.addResponseObject(OILabelConstants.ERROR,ex.getMessage());
	   		
	   	}finally{
	   			freeConnection();
	   	}
		logger.debug(OILabelConstants.END_METHOD_BO + "publishCnt");	
		return responseObject;
	
	  }
	 
	 /**
	  * This method is used to delete the poll details 
	  * @param objBVPoll
	  * @return
	  */
	 
	 private boolean deletePoll(OIBVPoll objBVPoll )throws Exception{
	 	logger.debug(OILabelConstants.BEGIN_METHOD_BO + "deletePoll");
	   	OIDAOPoll objDAOPoll= new OIDAOPoll();
	   	ArrayList arBVPoll = null;
	   	boolean bFlag = false;
	   	try{
			      /** Create the Connection Object **/
		    	  getConnection();
		    	  connection.setAutoCommit(false);
		    	  bFlag =  objDAOPoll.deletePoll(connection,objBVPoll,OIPollSqls.QRY_DELETE_RESPONSE);
		    	  //if(bFlag){
		    	  bFlag= objDAOPoll.deletePoll(connection,objBVPoll,OIPollSqls.QRY_DELETE_POLL);
		    	  //}	
		    	  connection.commit();
		     	
	   	}catch (Exception ex){
	   			logger.error(OILabelConstants.EXCEPTION_IN_BO,ex);
	   			responseObject.addResponseObject(OILabelConstants.ERROR,ex.getMessage());
	   		
	   	}finally{
	   			freeConnection();
	   	}
		logger.debug(OILabelConstants.END_METHOD_BO + "deletePoll");	
		return bFlag;
	
	  }

	 /**
	  * This method is fetches the results of polls
	  * @param objBVPoll
	  * @return
	  */
	 
	 private OIResponseObject responsePoll(OIBVPoll objBVPoll )throws Exception{
	 	logger.debug(OILabelConstants.BEGIN_METHOD_BO + "responsePoll");
	   	OIDAOPoll objDAOPoll= new OIDAOPoll();
	   	ArrayList alBAPoll = null;
	   	boolean bFlag = false;
	   	try{
			      /** Create the Connection Object **/
		    	  getConnection();
		    	  alBAPoll =  objDAOPoll.responsePoll(connection,objBVPoll);
		    	  
		    	  if(alBAPoll!=null){
		    	  	responseObject.addResponseObject(OILabelConstants.OBJARBV,alBAPoll);
		    	  	calImgPer(objBVPoll,alBAPoll);
		    	  }
		    	 
	   	}catch (Exception ex){
	   			logger.error(OILabelConstants.EXCEPTION_IN_BO,ex);
	   			responseObject.addResponseObject(OILabelConstants.ERROR,ex.getMessage());
	   		
	   	}finally{
	   			freeConnection();
	   	}
		logger.debug(OILabelConstants.END_METHOD_BO + "responsePoll");	
		return responseObject;
	
	  }
	 
	 
	 /** 
	  * This is the helper method calculates the Width of the Image
	  * @param objBVPoll
	  * @return
	  */
	 private void calImgPer(OIBVPoll objBVPoll ,ArrayList alTemp )throws Exception{
	 	
	 	logger.debug("objBVPoll.getTotal()"+objBVPoll.getTotal());
	 	double lTotal = Double.parseDouble(OIFormUtilities.replaceToZero(objBVPoll.getTotal()));
	 	double lWidth = 0;
	 	double lsize = 0;
	 	int nSize = alTemp.size();
		try{
				lsize =  Double.parseDouble(OIDBRegistry.getValues(OIPollConstants.IMGSIZE));
			 	if(lTotal >0){
			 		lWidth = lTotal/100; 
			 		objBVPoll.setWidth(Double.toString(lWidth));
			 	}
			 	if(lWidth!=0){
			 		for(int i =0 ;i<nSize;i++){
			 			OIBAPoll objPoll =(OIBAPoll) alTemp.get(i);
			 			objPoll.setImgPer((chkZero(objPoll.getRes(),lTotal)));
			 			objPoll.setImgSize(Long.toString(java.lang.Math.round(Double.parseDouble(objPoll.getImgPer())* lsize)));
			 			objPoll.setImgPer(Long.toString(java.lang.Math.round(Double.parseDouble(objPoll.getImgPer()))));
			 						 			
			 		}
			
			 	}	
			 				  
		}catch(Exception e){
            logger.error(e);
			throw e;	
		}	
	 }
	 
	 /**
	  * This is the private method to check for zero and divide the value with width
	  * @param strVal
	  * @param lWidth
	  * @return
	  */
	 private String chkZero(String strVal,double lTotal){
	 	String strTemp="0";
		
		if(Double.parseDouble(strVal)>0){
			strTemp=Double.toString((Double.parseDouble(strVal)/lTotal) * 100 );
		}
	  return strTemp;
	 }
	 
	
	 
	 
}