
package com.oifm.survey.admin;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.oifm.utility.OIEncrypter;

public class OITempUserEncrypter extends OIEncrypter {

	private static final Logger logger = Logger.getLogger(OITempUserEncrypter.class);

    public static void decryptTempUser(ArrayList alTempUser) throws RuntimeException {
        String strEncrypt = null;
		byte temp;
		byte[] inByte; 
		OIBATempUser objTempUser = null;

		try {		
			if (alTempUser != null && alTempUser.size() > 0)	{
				for(int j=0; j<alTempUser.size(); j++)	{
					objTempUser = (OIBATempUser)alTempUser.get(j);
					strEncrypt = objTempUser.getStrPassword();
					inByte = new byte[strEncrypt.length()/2];
					for (int i=0; i<strEncrypt.length(); i+=2)	{
						temp = (byte)(new Integer(Integer.parseInt((strEncrypt.substring(i, i+2)), 16) - APPVALUE)).byteValue();
						temp ^= MASK;
						inByte[i/2] = temp;
					}
					objTempUser.setStrPassword(getUTF8String(inByte));
				}
			}
		} catch (RuntimeException re) {
logger.error("when try to decrypt the String :"+alTempUser+"\n"+re);
			throw re;
		}
    }

}