package Exercise1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigInteger;

public class ExtendedEuclidianAlgorithm {
	
	public static void main(String[] args) {
		algorithm(new BigInteger("1572855870797393"), new BigInteger("630065648824575"));
	}

	public static BigInteger[] algorithm(BigInteger x, BigInteger y){
		if(x.compareTo(y) == -1){
			// x must be smaller than y
			return algorithm(y, x);
		}
		
		// Set algorithm values
		BigInteger s0 = BigInteger.valueOf(1);
		BigInteger s1 = BigInteger.valueOf(0);
		
		BigInteger t0 = BigInteger.valueOf(0);
		BigInteger t1 = BigInteger.valueOf(1);
		
		BigInteger r0 = x;
		BigInteger r1 = y;
		
		// Temp values for computation
		BigInteger quotient;
		BigInteger tempR;
		BigInteger tempS;
		BigInteger tempT;
		
		// Resultant Value
		BigInteger d = BigInteger.valueOf(0);
		
		while( r1.compareTo(BigInteger.valueOf(0)) != 0 ){
			quotient = r0.divide(r1);
			tempR = r0.mod(r1); // Remainder
			
			r0 = r1;
			r1 = tempR;
			
			tempS = s0;
			s0 = s1;
			s1 = tempS.subtract(s1.multiply(quotient));
			
			tempT = t0;
			t0 = t1;
			t1 = tempT.subtract(t1.multiply(quotient));
			
			d = r0;
			//System.out.println("D = " + d + " s = " + s0 + " t = " + t0);
		}
		writeResultToFile(x, y, d, s0, t0);
		BigInteger[] arr = { d, s0, t0 };
		return arr;
	}
	
	public static void writeResultToFile(BigInteger x, BigInteger y, BigInteger d, BigInteger s, BigInteger t){
		File file = new File("Results/LatestQuestion1.txt");
		PrintWriter print;
		try {
			print = new PrintWriter(file);
			
			print.println("Inputs:");
			print.println("x = " + x);
			print.println("y = " + y);
			print.println("Outputs:");
			print.println("d = " + d);
			print.println("s = " + s);
			print.println("t = " + t);
			print.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
