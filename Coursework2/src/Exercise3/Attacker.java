package Exercise3;

import java.math.BigInteger;

import Helpers.StaticHelpers;

public class Attacker {

	private User messengerA;
	private User messengerB;
	
	private BigInteger generator;
	private BigInteger modulus;
	
	private BigInteger privateKey;
	
	private BigInteger firstRecievedKeyA;
	private BigInteger firstRecievedKeyB;
	
	private BigInteger newValue;
	
	private BigInteger finalSecretA;
	private BigInteger finalSecretB;
	
	public Attacker(){
	}
	
	public BigInteger recieveFirstMessage(User from, User to, 
										  BigInteger modulus, BigInteger generator, 
										  BigInteger valueA){
		// Save data for future use
		this.messengerA = from;
		this.messengerB = to;
		this.modulus = modulus;
		this.generator = generator;
		this.privateKey = StaticHelpers.getRandomNumberInRange(BigInteger.valueOf(2), modulus.subtract(BigInteger.valueOf(2)));
		this.firstRecievedKeyA = valueA;
		
		// Fake forward message to B
		this.newValue = generator.modPow(privateKey, modulus);
		this.firstRecievedKeyB = messengerB.recieveFirstMessage(modulus, generator, newValue);
		finalSecretB = firstRecievedKeyB.modPow(privateKey, modulus);
		
		// Compute Key from Messenger A
		finalSecretA = firstRecievedKeyA.modPow(privateKey, modulus);
		
		// Fake return from B to A
		return newValue;	
	}
	
	public BigInteger getFinalSecretA(){
		return finalSecretA;
	}
	
	public BigInteger getFinalSecretB(){
		return finalSecretB;
	}
	
	public BigInteger getPrivateKey(){
		return privateKey;
	}
	
	public BigInteger getNewValue(){
		return newValue;
	}

}
