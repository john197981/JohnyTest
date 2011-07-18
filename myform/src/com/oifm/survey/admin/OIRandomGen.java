
package com.oifm.survey.admin;

import java.util.HashSet;
import java.util.Random;
import java.util.ArrayList;
import org.apache.log4j.Logger;

public class OIRandomGen {	

	int maxLimit;
	int size;

	private static final Logger logger = Logger.getLogger(OIDAOQuestionAdmin.class);

	final char[][] CHAR_SET = 
	{	{'V','u','A','c','J','M','E','U','5','m'}, 
		{'W','e','Q','2','k','n','f','B','R','7'}, 
		{'t','9','P','g','X','N','F','C','S','8'}, 
		{'3','K','d','h','Y','z','j','b','r','6'}, 
		{'4','L','D','T','a','q','G','y','H','Z'}	};	

	public OIRandomGen()	{	}
	
	public synchronized ArrayList getStringTokens(int noOfIds, int idSize) throws Exception {
		this.size = (idSize>12)? 12 : idSize;
		this.maxLimit = (int)Math.ceil(noOfIds*0.2);
		HashSet objRandomSet = generateRandomNumbers();
		ArrayList randomList = generateIds(objRandomSet);
		while(randomList.size() > noOfIds)
			randomList.remove(noOfIds);
		return randomList;
	}

	private ArrayList generateIds(HashSet objRandomSet)	throws Exception {
		ArrayList randomList = new ArrayList();
		ArrayList list = null;
		String newStr = ""; 
		byte[] byteArr = null;
		char[] charArr1 =  new char[size];
		char[] charArr2 =  new char[size];
		char[] charArr3 =  new char[size];
		char[] charArr4 =  new char[size];
		char[] charArr5 =  new char[size];
		
		try {
			list = new ArrayList();
			list.addAll(objRandomSet);

			for (int i=0; i< list.size(); i++ )	{
				newStr = (String)list.get(i);
				byteArr = newStr.getBytes();

				for (int j=0, k=0; j< size; j++ )	{
					k = byteArr[j] - 48;
					charArr1[j] = CHAR_SET[0][k];
					charArr2[j] = CHAR_SET[1][k];
					charArr3[j] = CHAR_SET[2][k];
					charArr4[j] = CHAR_SET[3][k];
					charArr5[j] = CHAR_SET[4][k];
				}

				randomList.add(new String(charArr1));
				randomList.add(new String(charArr2));
				randomList.add(new String(charArr3));
				randomList.add(new String(charArr4));
				randomList.add(new String(charArr5));
			}
logger.debug(" generatedIds() => "+randomList);
		} catch (Exception e) {
			logger.error(" generateIds() => "+e.getMessage());
			throw e;
		}
		return randomList;
	}

	private HashSet generateRandomNumbers()	{
		HashSet objRandomSet = new 	HashSet();
		Random random = new Random();
		String strRandom = null;
		double newNum = 0.0;
		boolean uniqueFlag = false;
		for (int i=0; i< maxLimit; )	{
			newNum = random.nextDouble();
			strRandom = new String(""+newNum).substring(2,size+2);
			uniqueFlag = objRandomSet.add(strRandom);
			if(uniqueFlag) i++;
		}
logger.debug(" generatedNumbers() => "+objRandomSet);
		return objRandomSet;
	}

/*	public static void main(String[] args)	{
		OIRandomGen objRdm = new OIRandomGen();
		objRdm.getTempUserIDs(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
		objRdm.display();
	}	*/

} 
