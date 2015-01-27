package Helpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.SecureRandom;

public class StaticHelpers {

	public static BigInteger getRandomNumberOfBitLength(int numBits){
		SecureRandom sr = new SecureRandom();
		BigInteger result = BigInteger.probablePrime(numBits, sr);
		return result;
	}
	
    public static BigInteger getRandomNumberInRange(BigInteger min, BigInteger max) {
        if(max.compareTo(min) < 0) {
            BigInteger tmp = min;
            min = max;
            max = tmp;
        } else if (max.compareTo(min) == 0) {
            return min;
        }
        SecureRandom sr = new SecureRandom();
        max = max.add(BigInteger.ONE);
        BigInteger range = max.subtract(min);
        int length = range.bitLength();
        BigInteger result = new BigInteger(length, sr);
        while(result.compareTo(range) >= 0) {
            result = new BigInteger(length, sr);
        }
        result = result.add(min);
        return result;
    }
    
    public static void writeStringToFile(String string, String filename){
		File file = new File(filename);
		PrintWriter print;
		try {
			print = new PrintWriter(file);
			
			String[] arr = string.split("\n");
			
			for(int i = 0; i < arr.length; i++){
				print.println(arr[i]);	
			}
			
			print.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    	
    }
}
