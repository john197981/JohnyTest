package com.oifm.utility;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.log4j.Logger;

public class OIFileUtility 
{
    private static Logger logger = Logger.getLogger(OIFileUtility.class.getName());
    
    public static void writeFile(InputStream stream,String filePath,String fileName)throws Exception
    {
        try
        {
            logger.info(filePath);
            OutputStream bos = new FileOutputStream(new File(filePath,fileName));
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) 
            {
                bos.write(buffer, 0, bytesRead);
            }
            bos.close();
        }catch(Exception e)
        {
            logger.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }
    
    public static String generateFileName(String moduleName,String Id,String extension)
    {
        return moduleName + Id + extension;
    }
}
