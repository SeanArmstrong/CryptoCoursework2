package Exercise2;

import java.math.BigInteger;

public class TimeCompare {
	public static void time(){
		/*
		 * Compare how much time it takes to compute the step related to integer a with the BigInteger
			library method as well as the method we introduced in Number Theory II with Fermat�s Theorem.
			Write a concise report on your findings.
		 */
		
		// x^-1 = x^N-2 (mod N)
		BigInteger N = new BigInteger("64380800680355443923012985496149269915138610753401343291807343952413826484237063006136971539473" +
				"9134090922937332590384720397133335969549256322620979036686633213903952966175107096769180017646161851573147596390153");
		BigInteger a = new BigInteger("34325464564574564564768795534569998743457687643234566579654234676796634378768434237897634345765879087764242354365767869780876543424");
		
		BigInteger fermat_aneg1;
		BigInteger bigint_aneg1;

		long startTime;
		long endTime;
		long fermat_total = 0;
		long bigint_total = 0;
		int loops = 1000;
		
		for(int i = 0; i < loops; i++){
			startTime = System.nanoTime();
			fermat_aneg1 = a.modPow(N.subtract(BigInteger.valueOf(2)), N);
			endTime = System.nanoTime();
	
			fermat_total = fermat_total + (endTime - startTime);
			//System.out.println("Fermat Time: " + (endTime - startTime)); 
			//System.out.println("Fermat a^-1 = " + fermat_aneg1);
			
			startTime = System.nanoTime();
			bigint_aneg1 = a.modInverse(N);
			endTime = System.nanoTime();
	
			bigint_total = bigint_total + (endTime - startTime);
			//System.out.println("BigInteger Library Time: " + (endTime - startTime)); 
			//System.out.println("BigInteger a^-1 = " + bigint_aneg1);
			
			//System.out.println("Are they equal? " + fermat_aneg1.compareTo(bigint_aneg1));
		}
		
		System.out.println("Fermat Average Time: " + fermat_total/loops + " nanoseconds");
		System.out.println("BigInteger Average Time: " + bigint_total/loops + " nanosecond");
	}
}
