package com.oifm.base;

// commons imports:
import org.apache.log4j.Logger;
/*
 * Class Name:
 * Description:
 * 
 * 	Created By			Created/Modified on			Revision				Remarks
 * -----------------------------------------------------------------------------------------------------
 * 	Sukumar				16/06/2005					Draft					Inital Version
 * 
 * -----------------------------------------------------------------------------------------------------
 */


/**
 * OIBaseRuntimeException is a subclass of RuntimeException and can be used as
 * the base exception to throw from EJB tier whenever a system exception
 * occurs. It keeps track of the exception is logged or not and also stores
 * the unique id, so that it can be carried all along to the client tier and
 * displayed to the end user. The end user can call up the customer support
 * using this number.
 *
 * @version $Revision: 1.0 $
 */
public abstract class OIBaseRuntimeException extends RuntimeException {

    /** logger instance */
	private static Logger logger = Logger.getLogger(OIBaseRuntimeException.class);

    private String strErrorCode = "";
    private String strErrorMsg = "";

    public OIBaseRuntimeException()	{
        super();
    }

	public OIBaseRuntimeException(String message, String code)	{
        super(message);
		this.strErrorMsg = message;
		this.strErrorCode = code;
        log(message, null);
    }

	/**
     * Creates a new OIBaseRuntimeException object.
     *
     * @param message the detail message.
     */
    public OIBaseRuntimeException(String message) {
        super(message);
		this.strErrorMsg = message;
        log(message, null);
    }

    /**
     * Creates a new OIBaseRuntimeException object.
     *
     * @param message the detail message.
     * @param exception The original exception that will be chained to the new
     *        exception
     */
    public OIBaseRuntimeException(String message, Exception exception) {
        super(message, exception);
		this.strErrorMsg = message;
		log(message, exception);
    }

    /**
     * Creates a new OIBaseRuntimeException object.
     *
     * @param exception The original exception that will be chained to the new
     *        exception
     */
    public OIBaseRuntimeException(Exception exception) {
        super(exception);
        log(null, exception);
    }

    //~ Methods ----------------------------------------------------------------

	/**
     * @return Returns the strErrorCode.
     */
    public String getStrErrorCode() {
        return strErrorCode;
    }

    public String getStrErrorMsg() {
        return strErrorMsg;
    }

    /**
     * @param strErrorCode The strErrorCode to set.
     */
    public  void setStrErrorCode(String strErrorCode) {
        this.strErrorCode = strErrorCode;
    }

	public  void setStrErrorMsg(String strErrorMsg) {
        this.strErrorMsg = strErrorMsg;
    }

    /**
     * Logs the exception in predefined format.
     *
     * <p>
     * Log Format:<br>
     * <pre>
     *
     * @param message The detail message.
     * @param exception The original exception that will be chained to the new
     *        exception
     */
    protected void log(String message, Exception exception) {
        if (message != null) {
            logger.error(message);
        } 
		if (exception != null) {
            logger.error("Exception: ", exception);
        }
    }

}
