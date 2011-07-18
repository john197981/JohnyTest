/*
 * Roles module Data Access Object
 * 
 * File name	= OIDAORoles.java
 * Package		= com.oifm.useradmin
 * Created on 	= Aug 5, 2005
 * Created by	= Oscar
 * Copyright	= Scandent Group
 *
 */
package com.oifm.useradmin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseDao;
import com.oifm.utility.OISQLUtilities;

public class OIDAORoles extends OIBaseDao {
    private static final Logger logger = Logger.getLogger(OIDAORoles.class);
    
    public OIDAORoles() {}
    
    public ArrayList getRolesList(Connection conn) throws SQLException {
        ArrayList alRolesList = new ArrayList();
        PreparedStatement pst = null;
        ResultSet rs = null;
        OIBARoles objOIBARoles= null;
        
        try{
            pst = conn.prepareStatement(OIRolesSqls.QRY_LIST);
            rs = pst.executeQuery();
            while (rs.next()) {
                objOIBARoles= new OIBARoles();
                objOIBARoles.setStrRoleID(rs.getString("ROLEID"));
                objOIBARoles.setStrRoleName(rs.getString("NAME"));
                objOIBARoles.setIntNumOfUsers(rs.getInt("NUM_OF_USERS"));
                alRolesList.add(objOIBARoles);
            }
        } catch(SQLException e) {
            logger.error("getRolesList() : " + e);
            throw e;
        } finally {
            OISQLUtilities.closeRsetPstatement(rs, pst);
        }
        return alRolesList;
    }
    
    public OIBARoles getRoleDetails (Connection conn, String strRoleID) throws SQLException {
        OIBARoles objBARoles = new OIBARoles();
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        try {
            pst = conn.prepareStatement(OIRolesSqls.QRY_RETRIEVE_ROLE);
            pst.setString(1, strRoleID);
            rs = pst.executeQuery();
            while (rs.next()) {
                objBARoles.setStrRoleID(rs.getString("ROLEID"));
                objBARoles.setStrRoleName(rs.getString("NAME"));
                objBARoles.setBoolDefault((rs.getString("DEFAULT_ROLE") != null && !rs.getString("DEFAULT_ROLE").equals("") && rs.getString("DEFAULT_ROLE").equalsIgnoreCase("Y"))?true:false);
            }
            
        } catch (SQLException e) {
            logger.error("getRoleDetails() : " + e);
            throw e;
        } finally {
            OISQLUtilities.closeRsetPstatement(rs, pst);
        }
        
        return objBARoles;
    }
    
    public String[] getRoleFunctions (Connection conn, String strRoleID) throws SQLException {
        ArrayList alFunctionsList = new ArrayList();
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        try {
            pst = conn.prepareStatement(OIRolesSqls.QRY_GET_ROLE_FUNCTIONS);
            pst.setString(1, strRoleID);
            rs = pst.executeQuery();
            
            while (rs.next()) {
                alFunctionsList.add(rs.getString("FUNCTIONID"));
            }
            
        } catch (SQLException e) {
            logger.error("getRoleFunctions() : " + e);
            throw e;
        } finally {
            OISQLUtilities.closeRsetPstatement(rs, pst);
        }
        return (String[]) alFunctionsList.toArray(new String[alFunctionsList.size()]);
    }
    
    public boolean canDelete (Connection conn, String strRoleID) throws SQLException {
        return !isAssigned(conn, strRoleID) && !isDefault(conn, strRoleID);
    }
    
    public boolean isAssigned (Connection conn, String strRoleID) throws SQLException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        boolean ret = false;
        
        try {
            pst = conn.prepareStatement(OIRolesSqls.QRY_CHECK_NUM_OF_USERS);
            pst.setString(1, strRoleID);
            rs = pst.executeQuery();
            
            while (rs.next()) {
                if (rs.getInt("NUM_OF_USERS") > 0) ret = true;
            }
            
        } catch (SQLException e) {
            logger.error("isAssigned() : " + e);
            throw e;
        } finally {
            OISQLUtilities.closeRsetPstatement(rs, pst);
        }
        
        return ret;
    }
    
    public boolean isDefault (Connection conn, String strRoleID) throws SQLException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        boolean ret = false;
        
        try {
            pst = conn.prepareStatement(OIRolesSqls.QRY_RETRIEVE_ROLE);
            pst.setString(1, strRoleID);
            rs = pst.executeQuery();
            
            while (rs.next()) {
                if (rs.getString("DEFAULT_ROLE") != null && rs.getString("DEFAULT_ROLE").equalsIgnoreCase("Y")) ret = true;
            }
            
        } catch (SQLException e) {
            logger.error("isDefault() : " + e);
            throw e;
        } finally {
            OISQLUtilities.closeRsetPstatement(rs, pst);
        }
        
        return ret;
    }
    
    public boolean createRole (Connection conn, OIBARoles objBARoles, String strNickname) throws SQLException {
        PreparedStatement pst = null;
        boolean ret = false;
        
        try {
            pst = conn.prepareStatement(OIRolesSqls.QRY_ADD_ROLE);
            pst.setString(1, objBARoles.getStrRoleID());
            pst.setString(2, objBARoles.getStrRoleName());
            pst.setString(3, strNickname);
            if (pst.executeUpdate() <= 0) throw new SQLException();
            
            ret = true;
        } catch (SQLException e) {
            logger.error("createRole() : " + e);
            throw e;
        } finally {
            OISQLUtilities.closePstatement(pst);
        }
        
        return ret;
    }
    
    public boolean saveRole (Connection conn, OIBARoles objBARoles) throws SQLException {
        PreparedStatement pst = null;
        boolean ret = false;
        
        try {
            if (objBARoles.isBoolDefault()){
                pst = conn.prepareStatement(OIRolesSqls.QRY_REMOVE_DEFAULT);
                if (pst.executeUpdate() <= 0) throw new SQLException();
            }
            
            pst = conn.prepareStatement(OIRolesSqls.QRY_MODIFY_ROLE);
            pst.setString(1, objBARoles.getStrRoleName());
            pst.setString(2, (objBARoles.isBoolDefault())? "Y": "");
            pst.setString(3, objBARoles.getStrRoleID());
            if (pst.executeUpdate() <= 0) throw new SQLException();
            
            ret = true;
        } catch (SQLException e) {
            logger.error("saveRole() : " + e);
            throw e;
        } finally {
            OISQLUtilities.closePstatement(pst);
        }
        
        return ret;
    }
    
    public boolean deleteRole (Connection conn, String strRoleID) throws SQLException {
        PreparedStatement pst = null;
        boolean ret = false;
        
        try {
            
            pst = conn.prepareStatement(OIRolesSqls.QRY_DELETE_ROLE);
            pst.setString(1, strRoleID);
            if (pst.executeUpdate() <= 0) throw new SQLException();
            
            ret = true;
        } catch (SQLException e) {
            logger.error("deleteRole() : " + e);
            throw e;
        } finally {
            OISQLUtilities.closePstatement(pst);
        }
        
        return ret;
    }
    
    public HashMap getFunctionsList (Connection conn) throws SQLException {
        HashMap hashFunctions = new HashMap();
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        try{
            pst = conn.prepareStatement(OIRolesSqls.QRY_FUNCTIONS_LIST);
            rs = pst.executeQuery();
            while (rs.next()) {
                hashFunctions.put(rs.getString("FUNCTIONID"), rs.getString("NAME"));
            }
        } catch(SQLException e) {
            logger.error("getFunctionsList() : " + e);
            throw e;
        } finally {
            OISQLUtilities.closeRsetPstatement(rs, pst);
        }
        return hashFunctions;
    }
    
    public boolean hasDuplicate (Connection conn,String strRoleID) throws SQLException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        boolean ret = false;
        
        try {
            pst = conn.prepareStatement(OIRolesSqls.QRY_RETRIEVE_ROLE);
            pst.setString(1, strRoleID);
            rs = pst.executeQuery();
            
            ret = rs.next();
            
        } catch (SQLException e) {
            logger.error("hasDuplicate() : " + e);
            throw e;
        } finally {
            OISQLUtilities.closeRsetPstatement(rs, pst);
        }
        
        return ret;
    }
    
    public boolean setFunctions (Connection conn, String[] strFunctions, String strRoleID) throws SQLException {
        PreparedStatement pst = null;
        boolean ret = false;
        
        try {
            if (strFunctions != null && strFunctions.length != 0) {
                for (int i = 0; i < strFunctions.length; i++) {
                    pst = conn.prepareStatement(OIRolesSqls.QRY_SET_FUNCTION);
                    pst.setString(1, strFunctions[i]);
                    pst.setString(2, strRoleID);
                    if (pst.executeUpdate() <= 0) throw new SQLException();
                }
            }
            ret = true;
        } catch (SQLException e) {
            logger.error("setFunctions() : " + e);
            throw e;
        } finally {
            OISQLUtilities.closePstatement(pst);
        }
        
        return ret;
    }
    
    public boolean deleteFunctions (Connection conn, String strRoleID) throws SQLException {
        PreparedStatement pst = null;
        boolean ret = false;
        
        try {
            pst = conn.prepareStatement(OIRolesSqls.QRY_DELETE_FUNCTIONS);
            pst.setString(1, strRoleID);
            pst.executeUpdate();
            
            ret = true;
        } catch (SQLException e) {
            logger.error("deleteFunctions() : " + e);
            throw e;
        } finally {
            OISQLUtilities.closePstatement(pst);
        }
        
        return ret;
    }
}
