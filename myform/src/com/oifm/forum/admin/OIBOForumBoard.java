package com.oifm.forum.admin;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseBo;
import com.oifm.codemaster.OIDAOCodeMaster;
import com.oifm.common.OIResponseObject;
import com.oifm.utility.OIDBRegistry;

public class OIBOForumBoard extends OIBaseBo
{
    Logger logger = Logger.getLogger(OIBOForumBoard.class.getName());
    
    public OIResponseObject readBoard(int pBoardId)
    {
        try
        {
            getConnection();
            ArrayList arOIBAForumCategory = new OIDAOForumCategory().readAll(connection);
            ArrayList arOIBACodeMaster = new OIDAOCodeMaster().readType("DIVISION_CODE",connection);
            if (pBoardId!=0)
            {
                OIBAForumBoard aOIBAForumBoard = new OIDAOForumBoard().read(pBoardId,connection);
                ArrayList arOIBAForumMembers = new OIDAOForumBoard().readUsers(pBoardId,connection);
                responseObject.addResponseObject("aOIBAForumBoard",aOIBAForumBoard);
                responseObject.addResponseObject("arOIBAForumMembers",arOIBAForumMembers);
            }
            responseObject.addResponseObject("arOIBACodeMaster",arOIBACodeMaster);
            responseObject.addResponseObject("arOIBAForumCategory",arOIBAForumCategory);
        }
        catch(Exception e)
        {
            error = e.getMessage();
        }
        finally
        {
            freeConnection();
        }
        addToResponseObject();
        return responseObject;
    }

    public OIResponseObject saveBoard(OIBAForumBoard pOIBAForumBoard)
    {
        try
        {
            getConnection();
            connection.setAutoCommit(false);
            OIDAOForumBoard aOIDAOForumBoard = new OIDAOForumBoard();
            if (aOIDAOForumBoard.checkDuplicateBoard(pOIBAForumBoard.getBoardName(),pOIBAForumBoard.getBoardId(),pOIBAForumBoard.getCategoryId(),connection))
            {
                boolean flag=aOIDAOForumBoard.save(pOIBAForumBoard,connection);
            }
            else
            {
                error = OIDBRegistry.getValues("OI.FORUM.DUPLICATE.BOARD");
            }
            connection.commit();
            connection.setAutoCommit(true);
        }
        catch(Exception e)
        {
            error = e.getMessage();
        }
        finally
        {
            freeConnection();
        }
        addToResponseObject();
        return responseObject;
    }

    public OIResponseObject deleteBoard(OIBAForumBoard pOIBAForumBoard)
    {
        try
        {
            getConnection();
            connection.setAutoCommit(false);
            OIDAOForumBoard aOIDAOForumBoard = new OIDAOForumBoard();
            if (aOIDAOForumBoard.checkBoard(pOIBAForumBoard.getBoardId(),connection)==0)
            {
                boolean flag=aOIDAOForumBoard.deleteBoardAdmin(pOIBAForumBoard,connection);
                flag=aOIDAOForumBoard.delete(pOIBAForumBoard,connection);
            }
            else
            {
                error = OIDBRegistry.getValues("OI.FORUM.BOARD.CHECK");
            }
            connection.commit();
            connection.setAutoCommit(true);
        }
        catch(Exception e)
        {
            error = e.getMessage();
        }
        finally
        {
            freeConnection();
        }
        addToResponseObject();
        return responseObject;
    }
    
    public OIResponseObject removeUserFromBoard(OIBAForumBoard pOIBAForumBoard,String[] pUser)
    {
        try
        {
            getConnection();
            connection.setAutoCommit(false);
            OIDAOForumBoard aOIDAOForumBoard = new OIDAOForumBoard();
            aOIDAOForumBoard.removeUser(pOIBAForumBoard,pUser,connection);
            connection.commit();
            connection.setAutoCommit(true);
        }
        catch(Exception e)
        {
            error = e.getMessage();
        }
        finally
        {
            freeConnection();
        }
        addToResponseObject();
        return responseObject;
    }
}
