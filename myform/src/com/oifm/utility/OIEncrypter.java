
package com.oifm.utility;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

public class OIEncrypter {

    protected static final byte	MASK		=	(byte)99; 
    protected static final int	APPVALUE	=	20; 
    protected static final String	UTF8Encoding =	"UTF-8";

	private static final Logger logger = Logger.getLogger(OIEncrypter.class);

    public static ArrayList encrypt(ArrayList alInputStr) throws RuntimeException {
        StringBuffer stbrEncrypt = null;
		byte bytes[] = null;
		ArrayList alEncryptStr = new ArrayList();

		try {
			if (alInputStr != null && alInputStr.size() > 0)	{
				for(int j=0; j<alInputStr.size(); j++)	{
					stbrEncrypt = new StringBuffer();
					bytes = getUTF8Bytes((String)alInputStr.get(j));
					for (int i = 0; i < bytes.length; i++) {
						bytes[i] ^= MASK;
						stbrEncrypt.append(Integer.toHexString((int)bytes[i] + APPVALUE));
					}
					alEncryptStr.add(stbrEncrypt.toString());
				}
			}
		} catch (RuntimeException re) {
logger.error("when try to encrypt the String :"+alInputStr+"\n"+re);
			throw re;
		}
        return alEncryptStr;
    }

    public static String encrypt(String inputStr) throws RuntimeException {
        String encStr = "";
		try {
			if (inputStr == null) encStr = null;
			else {
				byte bytes[] = getUTF8Bytes(inputStr);
				for (int i = 0; i < bytes.length; i++) {
					bytes[i] ^= MASK;
					encStr += Integer.toHexString((int)bytes[i] + APPVALUE);
				}
			}
		} catch (RuntimeException re) {
logger.error("when try to encrypt the String :"+inputStr+"\n"+re);
			throw re;
		}
        return encStr;
    }

    public static ArrayList decrypt(ArrayList alInputStr) throws RuntimeException {
        String strEncrypt = null;
		ArrayList alDecryptStr = new ArrayList();
		byte temp;
		byte[] inByte; 

		try {		
			if (alInputStr != null && alInputStr.size() > 0)	{
				for(int j=0; j<alInputStr.size(); j++)	{
					strEncrypt = (String)alInputStr.get(j);
					inByte = new byte[strEncrypt.length()/2];
					for (int i=0; i<strEncrypt.length(); i+=2)	{
						temp = (byte)(new Integer(Integer.parseInt((strEncrypt.substring(i, i+2)), 16) - APPVALUE)).byteValue();
						temp ^= MASK;
						inByte[i/2] = temp;
					}
					alDecryptStr.add(getUTF8String(inByte));
				}
			}
		} catch (RuntimeException re) {
logger.error("when try to decrypt the String :"+alInputStr+"\n"+re);
			throw re;
		}
        return alDecryptStr;
    }

	public static String decrypt(String encryptedStr) throws RuntimeException {
		String decrptStr = "";
		String inStr = "";
		byte temp;
		byte[] inByte; 
		try {		
			if (encryptedStr == null)	decrptStr = null;
			else {
				inByte = new byte[encryptedStr.length()/2];
				for (int i=0; i<encryptedStr.length(); i+=2)	{
					inStr = encryptedStr.substring(i, i+2);
					temp = (byte)(new Integer(Integer.parseInt(inStr, 16) - APPVALUE)).byteValue();
					temp ^= MASK;
					inByte[i/2] = temp;
				}
				decrptStr = getUTF8String(inByte);
			}
		} catch (RuntimeException re) {
logger.error("when try to decrypt the String :"+encryptedStr+"\n"+re);
			throw re;
		}
        return decrptStr;
    }

    public static byte[] getUTF8Bytes(String inStr) {
        byte result[] = null;
        if (inStr != null) {
            try {
                result = inStr.getBytes(UTF8Encoding);
            } catch (UnsupportedEncodingException ue) {
logger.error("unsupported encoding: "+ UTF8Encoding+" String: "+inStr+"\n"+ue);
                throw new RuntimeException(ue.getMessage());
            }
        }
        return result;
    }

    public static String getUTF8String(byte inBytes[]) {
        String result = null;
        if (inBytes != null) {
            try {
                result = new String(inBytes, UTF8Encoding);
            } catch (UnsupportedEncodingException ue) {
logger.error("unsupported encoding: "+ UTF8Encoding+" Bytes: "+inBytes+"\n"+ue);
                throw new RuntimeException(ue.getMessage());
            }
        }
        return result;
    }




	public static void main(String[] args)	{
		try {
			String tempStr = "42364a3d2a3d";	
			ArrayList alInput = new ArrayList();
			//alInput.addAll(Arrays.asList(tempStr));

			//ArrayList alEncrypt = OIEncrypter.encrypt(alInput);
			String alDecrypt = OIEncrypter.decrypt(tempStr);
System.out.println(alDecrypt);

		} catch(RuntimeException re) {
			System.out.println(" Error : "+ re);
		}
	}
}