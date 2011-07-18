/*************************************************************************************
* Title 		: OIDAOPoll.java
* Description 	: This is the Data Acess Object of poll module to interact with db for 
* 				  creation,deletion,updation,publish the poll details  				    
* Author		: Suresh Kumar.R 
* Version No 	: 1.0
* Date Created 	: 26 - Jul -2005
* Copyright 	: Scandent Group
***************************************************************************************/

package com.oifm.poll;


import com.oifm.common.OILabelConstants;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;

import com.oifm.utility.DateUtility;
import com.oifm.utility.OIFormUtilities;



public class OIDAOPoll {
	
	
	transient private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	public OIDAOPoll(){
		logger.debug(OILabelConstants.BEGIN_METHOD_BO + this.getClass().getName());
	}
	
	/**
	 * This method is uded to create a new poll details
	 * @param objCon
	 * @param objBVPoll
	 * @return
	 * @throws Exception
	 */
	
	public boolean createPoll (Connection objCon ,OIBVPoll objBVPoll ) throws Exception{
	
			logger.debug(OILabelConstants.BEGIN_METHOD_DAO +"createPoll"); 
	         PreparedStatement objPs=null;
	        boolean bFlag = false;
	        int nUpate =0;
	        	        
	        try{ 
	       	        	  
	        	objPs = objCon.prepareStatement(OIPollSqls.QRY_INSERT);
	        	logger.debug(OIPollSqls.QRY_INSERT);
	        	objPs.setString(1,objBVPoll.getTitle());
	        	objPs.setString(2,objBVPoll.getQuestion());
	        	objPs.setString(3,objBVPoll.getStartDt());
	        	objPs.setString(4,objBVPoll.getExpDt()); 
	        	objPs.setString(5,objBVPoll.getAnswer1());
	        	objPs.setString(6,objBVPoll.getAnswer2());
	        	objPs.setString(7,objBVPoll.getAnswer3());
	        	objPs.setString(8,objBVPoll.getAnswer4());
	        	objPs.setString(9,objBVPoll.getAnswer5());
	        	objPs.setString(10,objBVPoll.getMutAns());
	        	objPs.setString(11,objBVPoll.getShowRes());
	        	objPs.setString(12,objBVPoll.getPublished());
	        	
	        	nUpate = objPs.executeUpdate();
	            if(nUpate > 0){
	            	logger.debug("Records Update "+nUpate);
	            	bFlag = true;
	            }
	           	       	        
			}catch(Exception sqle){
				logger.error(sqle.getMessage());
				bFlag = false;
				throw sqle;
			}finally{
				if (objPs!= null){
					objPs.close();
				}
				
			}
	       
	        logger.debug(OILabelConstants.END_METHOD_DAO +"createPoll");
	        return bFlag;
	 }
	
	
	/**
	 * This method is used to update the poll details
	 * @param objCon
	 * @param objBVPoll
	 * @return
	 * @throws Exception
	 */
	
	
	public boolean updatePoll (Connection objCon ,OIBVPoll objBVPoll ) throws Exception{
		
				logger.debug(OILabelConstants.BEGIN_METHOD_DAO +"updatePoll"); 
		        PreparedStatement objPs=null;
		        boolean bFlag = false;
		        int nUpate =0;
		        try
		        { 
		        	  
		        	objPs = objCon.prepareStatement(OIPollSqls.QRY_UPDATE);
		        	logger.debug(OIPollSqls.QRY_UPDATE);
		        	objPs.setString(1,objBVPoll.getTitle());
		        	objPs.setString(2,objBVPoll.getQuestion());
		        	objPs.setString(3,objBVPoll.getStartDt());
		        	objPs.setString(4,objBVPoll.getExpDt()); 
		        	objPs.setString(5,objBVPoll.getAnswer1());
		        	objPs.setString(6,objBVPoll.getAnswer2());
		        	objPs.setString(7,objBVPoll.getAnswer3());
		        	objPs.setString(8,objBVPoll.getAnswer4());
		        	objPs.setString(9,objBVPoll.getAnswer5());
		        	objPs.setString(10,objBVPoll.getMutAns());
		        	objPs.setString(11,objBVPoll.getShowRes());
		        	objPs.setString(12,objBVPoll.getPublished());
		        	objPs.setInt(13,Integer.parseInt(objBVPoll.getPollId()));
		        	nUpate = objPs.executeUpdate();
		        	if(nUpate > 0){
		            	bFlag = true;
		            	logger.debug("Records Update "+nUpate);
		            }
		           	       	        
				}catch(Exception sqle){
					logger.error(sqle.getMessage());
					bFlag = false;
					throw sqle;
				}finally{
					if (objPs!= null){
						objPs.close();
					}
			    }
		       
		        logger.debug(OILabelConstants.END_METHOD_DAO +"updatePoll");
		        return bFlag;
		 }
	
	
	
	public boolean updateResCnt (Connection objCon ,OIBVPoll objBVPoll ) throws Exception{
		
				logger.debug(OILabelConstants.BEGIN_METHOD_DAO +"updatePoll"); 
		        PreparedStatement objPs=null;
		        String strWhere =" WHERE POLLID =  " +objBVPoll.getPollId();
		        String strTemp =null;
		        boolean bFlag = false;
		        int nUpate = 0;
		        int nChk =0;
		        StringBuffer sbQuery = new StringBuffer();
		        try
		        { 
		        	  
		        	//objPs = objCon.prepareStatement(OIPollSqls.QRY_RES_UPDATE);
		        	sbQuery.append(OIPollSqls.QRY_RES_UPDATE);
		        	logger.debug(OIPollSqls.QRY_RES_UPDATE);
		        	if(objBVPoll.getAnswer1().trim().length()==0){
		        		sbQuery.append("Response1 = 0");
		        		sbQuery.append(" , ");
		        		nChk++;
		            }if(objBVPoll.getAnswer2().trim().length()==0){
			        	sbQuery.append("Response2 = 0");
			        	sbQuery.append(" , ");
			        	nChk++;
		            }if(objBVPoll.getAnswer3().trim().length()==0){
			        	sbQuery.append("Response3 = 0");
			        	sbQuery.append(" , ");
		            }if(objBVPoll.getAnswer4().trim().length()==0){
			        	sbQuery.append("Response4 = 0");
			        	sbQuery.append(" , ");
			        	nChk++;
		            }logger.debug(objBVPoll.getAnswer5().trim().length() +objBVPoll.getAnswer5());
		            if(objBVPoll.getAnswer5().trim().length()==0){
			        	sbQuery.append("Response5 = 0 ");
			        	sbQuery.append(" , ");
		            }
		            int nIndex = sbQuery.toString().lastIndexOf(",");
		            strTemp = sbQuery.toString();
		            if(nIndex >0){
		            	strTemp = sbQuery.substring(0,nIndex-1);
		            }
		            if(nChk>0){	
			            objPs = objCon.prepareStatement(strTemp+strWhere);
				    	logger.debug("Query "+strTemp+strWhere);
				        nUpate = objPs.executeUpdate();
				      	if(nUpate>0){
				            	bFlag = true;
				            	logger.debug("Records Update "+nUpate);
				        }
		            }else{
		            	bFlag = true;
		            }
			        	
				}catch(Exception sqle){
					logger.error(sqle.getMessage());
					bFlag = false;
					throw sqle;
				}finally{
					if (objPs!= null){
						objPs.close();
					}
					sbQuery.setLength(0);
					
			    }
		       
		        logger.debug(OILabelConstants.END_METHOD_DAO +"updatePoll");
		        return bFlag;
		 }
	
	
	
	/**
	 * This method get the all the questions for the poll module.
	 * @param objCon
	 * @param objBVPoll
	 * @return
	 * @throws Exception
	 */
	
	public boolean deletePoll(Connection objCon ,OIBVPoll objBVPoll,String strQuery ) throws Exception{
		
			logger.debug(OILabelConstants.BEGIN_METHOD_DAO +"deletePoll"); 
				  
	        PreparedStatement objPs=null;
	        boolean bFlag = false;
	        int nDeleted = 0;
	        try
	        { 
	        	  
	        	objPs = objCon.prepareStatement(strQuery);
	        	objPs.setInt(1,Integer.parseInt(objBVPoll.getPollId()));
	        	logger.debug(strQuery);
	        	nDeleted = objPs.executeUpdate();
	            if(nDeleted > 0){
	            	bFlag = true;
	            	logger.debug("Records Deleted "+nDeleted);
	            }
	       	        
			}catch(Exception sqle){
				logger.error(sqle.getMessage());
				bFlag = false;
				throw sqle;
			}finally{
				if (objPs!= null){
					objPs.close();
				}
			}
	       
	        logger.debug(OILabelConstants.END_METHOD_DAO +"deletePoll");
	        return bFlag;
	 }
	    
	
		
	
	
	/**
	 * This method get the all the questions for the poll module.
	 * @param objCon
	 * @param objBVPoll
	 * @return
	 * @throws Exception
	 */
	
	public ArrayList listPoll(Connection objCon ,OIBVPoll objBVPoll ) throws Exception{
		
		logger.debug(OILabelConstants.BEGIN_METHOD_DAO +"listPoll"); 
				
	        ArrayList arPoll=null;
	        ResultSet objRs =null;
	        PreparedStatement objPs=null;
	        boolean bFlag = false;
	        OIBVPoll objBVPolls = null;
	        try
	        { 
	        	  
	        	objPs = objCon.prepareStatement(OIPollSqls.QRY_LIST);
	        	logger.debug(OIPollSqls.QRY_LIST);
	            objRs = objPs.executeQuery();
	            if(objRs!= null){
	           		bFlag = true;
	           		arPoll = new ArrayList(); 
	           		while(objRs.next()){
	           			objBVPolls = new OIBVPoll();
	           			objBVPolls.setPollId(OIFormUtilities.chkIsNull(objRs.getString(1)));
	           			objBVPolls.setTitle(OIFormUtilities.chkIsNull(objRs.getString(2)));
	           			objBVPolls.setQuestion(OIFormUtilities.chkIsNull(objRs.getString(3)));
	           			objBVPolls.setStartDt(convertDate(objRs.getDate(4)));
	           			objBVPolls.setExpDt(convertDate(objRs.getDate(5)));
		                arPoll.add(objBVPolls);
	           		}
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
	        if (arPoll.size()==0){
	        	arPoll=null;
	        }
	        logger.debug(OILabelConstants.END_METHOD_DAO +"listPoll");
	        return arPoll;
	 }
	
	
	
	/**
	 * This method fetches the poll details  
	 * @param objCon
	 * @param objBVPoll
	 * @return
	 * @throws Exception
	 */	    	
	public boolean viewPoll(Connection objCon ,OIBVPoll objBVPoll ) throws Exception{
		
			logger.debug(OILabelConstants.BEGIN_METHOD_DAO +"viewPoll"); 
				  
	        ResultSet objRs =null;
	        PreparedStatement objPs=null;
	        boolean bFlag = false;
	        try
	        { 
	        	  
	        	objPs = objCon.prepareStatement(OIPollSqls.QRY_VIEW);
	        	objPs.setInt(1,Integer.parseInt(objBVPoll.getPollId()));
	        	logger.debug(OIPollSqls.QRY_VIEW);
	            objRs = objPs.executeQuery();
	            if(objRs!= null){
	           		bFlag = true;
	           		while(objRs.next()){
	           			objBVPoll.setPollId(OIFormUtilities.chkIsNull(objRs.getString(1)));
	           			objBVPoll.setTitle(OIFormUtilities.chkIsNull(objRs.getString(2)));
	           			objBVPoll.setQuestion(OIFormUtilities.chkIsNull(objRs.getString(3)));
	           			objBVPoll.setStartDt(convertDate(objRs.getDate(4)));
	           			objBVPoll.setExpDt(convertDate(objRs.getDate(5)));
	           			objBVPoll.setAnswer1(OIFormUtilities.chkIsNull(objRs.getString(6)));
	           			objBVPoll.setAnswer2(OIFormUtilities.chkIsNull(objRs.getString(7)));
	           			objBVPoll.setAnswer3(OIFormUtilities.chkIsNull(objRs.getString(8)));
	           			objBVPoll.setAnswer4(OIFormUtilities.chkIsNull(objRs.getString(9)));
	           			objBVPoll.setAnswer5(OIFormUtilities.chkIsNull(objRs.getString(10)));
	           			objBVPoll.setMutAns(OIFormUtilities.chkIsNull(objRs.getString(11)));
						objBVPoll.setShowRes(OIFormUtilities.chkIsNull(objRs.getString(12)));
						objBVPoll.setPublished(OIFormUtilities.chkIsNull(objRs.getString(13)));
						objBVPoll.setFmtDt(OIFormUtilities.chkIsNull(objRs.getString(14)));
						
	    	       }
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
	       
	        logger.debug(OILabelConstants.END_METHOD_DAO +"viewPoll");
	        return bFlag;
	 }
	    
	
	/**
	 * This method retrives the Poll responses. 
	 * @param objCon
	 * @param objBVPoll
	 * @return
	 * @throws Exception
	 */
	public ArrayList responsePoll(Connection objCon ,OIBVPoll objBVPoll ) throws Exception{
		
			logger.debug(OILabelConstants.BEGIN_METHOD_DAO +"responsePoll"); 
				  
	        ResultSet objRs =null;
	        PreparedStatement objPs=null;
	        OIBAPoll objPoll = null; 
	        boolean bFlag = false;
	        ArrayList alPoll = null;
	        try
	        { 
	        	  
	        	objPs = objCon.prepareStatement(OIPollSqls.QRY_RESULT);
	        	objPs.setInt(1,Integer.parseInt(objBVPoll.getPollId()));
	        	logger.debug(OIPollSqls.QRY_RESULT);
	            objRs = objPs.executeQuery();
	            if(objRs!= null && objRs.next()){
	            	alPoll = new ArrayList();
	           			bFlag = true;
	           			for(int i=3;i<=7;i++){
	           				objPoll = new OIBAPoll();
	           				objPoll.setRes(OIFormUtilities.replaceToZero(objRs.getString(i)));
	           				alPoll.add(objPoll);
	           			}	
	           			objBVPoll.setRes1(OIFormUtilities.replaceToZero(objRs.getString(3)));
	           			objBVPoll.setRes2(OIFormUtilities.replaceToZero(objRs.getString(4)));
	           			objBVPoll.setRes3(OIFormUtilities.replaceToZero(objRs.getString(5)));
	           			objBVPoll.setRes4(OIFormUtilities.replaceToZero(objRs.getString(6)));
	           			objBVPoll.setRes5(OIFormUtilities.replaceToZero(objRs.getString(7)));
						
	           			objBVPoll.setTotal(OIFormUtilities.replaceToZero(objRs.getString(8)));
							           			
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
				       
	        logger.debug(OILabelConstants.END_METHOD_DAO +"responsePoll");
	        return alPoll;
	 }
	
	/**
	 * This method checks for the publish flag is Y/N and get the title of checked published Poll.
	 * @param objCon
	 * @param objBVPoll
	 * @return
	 * @throws Exception
	 */
		
	public boolean getPublishCnt(Connection objCon ,OIBVPoll objBVPoll ) throws Exception{
		
			logger.debug(OILabelConstants.BEGIN_METHOD_DAO +"getPublishCnt"); 
				  
	        ResultSet objRs =null;
	        PreparedStatement objPs=null;
	        boolean bFlag = false;
	        try{ 
	        	  
	        	objPs = objCon.prepareStatement(OIPollSqls.QRY_PUBLISH);
	        	logger.debug(OIPollSqls.QRY_PUBLISH);
	        	objRs = objPs.executeQuery();
	            if(objRs!= null && objRs.next()){
	            	bFlag = true;
	            	objBVPoll.setPubId(objRs.getString(1));
	            	objBVPoll.setPubTitle(objRs.getString(2));
	            }
	            objBVPoll.setPubTitle(OIFormUtilities.chkIsNull(objBVPoll.getPubTitle()));     
	            objBVPoll.setPubId(OIFormUtilities.chkIsNull(objBVPoll.getPubId()));
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
	       
	        logger.debug(OILabelConstants.END_METHOD_DAO +"getPublishCnt");
	        return bFlag;
	 }
	    
	
	
	/**
	 * This method checks the poll dates which is already exist ot not.
	 * @param objCon
	 * @param objBVPoll
	 * @return
	 * @throws Exception
	 */
		
	public boolean ChkPollDts(Connection objCon ,OIBVPoll objBVPoll,String strQuery ) throws Exception{
		
			logger.debug(OILabelConstants.BEGIN_METHOD_DAO +"ChkPollDts"); 
				  
	        ResultSet objRs =null;
	        PreparedStatement objPs=null;
	        boolean bFlag = false;
	        try{ 
	        		        	
	        	objPs = objCon.prepareStatement(OIPollSqls.QRY_SYSDATE_CHK);
	        	logger.debug(OIPollSqls.QRY_SYSDATE_CHK);
	        	objPs.setString(1,objBVPoll.getStartDt());
	        	objPs.setString(2,objBVPoll.getExpDt()); 
	        	objRs = objPs.executeQuery();
	            if(objRs!= null && objRs.next()){
	            	  	
	        	      	objPs = objCon.prepareStatement(strQuery);
			        	logger.debug(strQuery);
			        	
			        	if(!objBVPoll.getAction().equals(OIPollConstants.CREATEPOLL)){
				        	objPs.setString(1,objBVPoll.getPollId());
				        	objPs.setString(2,objBVPoll.getStartDt());
				        	objPs.setString(3,objBVPoll.getExpDt()); 
				        	objPs.setString(4,objBVPoll.getStartDt());
				        	objPs.setString(5,objBVPoll.getExpDt());
				        	objPs.setString(6,objBVPoll.getStartDt());
				        	objPs.setString(7,objBVPoll.getExpDt());
				        	
				        	
			        	}else{
			        		objPs.setString(1,objBVPoll.getStartDt());
				        	objPs.setString(2,objBVPoll.getExpDt());
				        	objPs.setString(3,objBVPoll.getStartDt());
				        	objPs.setString(4,objBVPoll.getExpDt());
				        	objPs.setString(5,objBVPoll.getStartDt());
				        	objPs.setString(6,objBVPoll.getExpDt());
				        	
				        }
			        	logger.debug("Poll" + objBVPoll.getPollId());
			        	logger.debug("getStartDt" + objBVPoll.getStartDt());
			        	logger.debug("getExpDt" + objBVPoll.getExpDt());
			        	
			        	
			        	objRs = objPs.executeQuery();
			            if(objRs!= null && objRs.next()){
			            	objBVPoll.setDtCnt(objRs.getString(1));
			            	 logger.debug("objBVPoll.setDtCnt()"+objBVPoll.getDtCnt());
			            }
			            objBVPoll.setDtCnt(OIFormUtilities.replaceToZero(objBVPoll.getDtCnt()));   
			            if(Integer.parseInt(objBVPoll.getDtCnt()) > 0){
			            	bFlag = true;
			            }
	             }
			}catch(Exception sqle){
				logger.error(sqle.getMessage());
				bFlag = true;
				throw sqle;
			}finally{
				if (objPs!= null){
					objPs.close();
				}
				if (objRs!= null){
					objRs.close();
				}
			}
	       
	        logger.debug(OILabelConstants.END_METHOD_DAO +"ChkPollDts");
	        return bFlag;
	 }
		
	/**
     * This is the helper method to convert date to String dd-mmm-yyyy format
     * @param objDt
     * @return
     */
    private String convertDate(Date objDt){
    	
    	String strDate = null;
    	try{
	     	if (objDt!=null){
	     		strDate = DateUtility.getMMDDYYYYStringFromDate(objDt);
	     	}
    	}catch(Exception e){
	    	logger.error(e.getMessage());
	    }	
	   	return  OIFormUtilities.chkIsNull(strDate);
    }	
		
	
}