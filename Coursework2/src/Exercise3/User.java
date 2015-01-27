package Exercise3;

import java.math.BigInteger;

import Helpers.StaticHelpers;

public class User {
	
	private BigInteger generator;
	private BigInteger modulus;
	private BigInteger privateKey;
	private BigInteger firstRecievedKey;
	private BigInteger finalSecret;
	private BigInteger value;
	private User friend;
	
	public User(){
	}
	
	// First method called to set up key exchange
	public void initiateKeySetup(BigInteger modulus, BigInteger generator){
		if(friend == null){
			// if no friend to connect with return;
			return;
		}
		// Save values for future computation
		this.modulus = modulus;
		this.generator = generator;
		//Static key between 2 and modulus-2
		this.privateKey = StaticHelpers.getRandomNumberInRange(BigInteger.valueOf(2), modulus.subtract(BigInteger.valueOf(2)));
		this.value = generator.modPow(privateKey, modulus);
		//Compute Message A to B
		firstRecievedKey = friend.recieveFirstMessage(modulus, generator, value);
		
		// Computer Session Key
		finalSecret = firstRecievedKey.modPow(privateKey, modulus);
	}
	
	public BigInteger recieveFirstMessage(BigInteger modulus, BigInteger generator, BigInteger valueA){
		this.modulus = modulus;
		this.generator = generator;
		this.privateKey = StaticHelpers.getRandomNumberInRange(BigInteger.valueOf(2), modulus.subtract(BigInteger.valueOf(2)));
		this.firstRecievedKey = valueA;
				
		// Compute Session Key
		finalSecret = firstRecievedKey.modPow(privateKey, modulus);
		
		// Compute Message B to A
		this.value = generator.modPow(privateKey, modulus);
		return value;
	}
	
	public void initiateKeySetupAttack(Attacker attacker, BigInteger modulus, BigInteger generator ){
		this.modulus = modulus;
		this.generator = generator;
		//Static key between 2 and modulus-2
		this.privateKey = StaticHelpers.getRandomNumberInRange(BigInteger.valueOf(2), modulus.subtract(BigInteger.valueOf(2)));
		this.value = generator.modPow(privateKey, modulus);	
		
		//Compute Message A to B
		firstRecievedKey = attacker.recieveFirstMessage(this, friend, modulus, generator, value);
		
		// Computer Key B
		finalSecret = firstRecievedKey.modPow(privateKey, modulus);
	}
	
	public BigInteger getGenerator(){
		return generator;
	}
	
	public BigInteger getModulus(){
		return modulus;
	}
	
	public BigInteger getPrivateKey(){
		return privateKey;
	}
	
	public BigInteger getFinalSecret(){
		return finalSecret;
	}
	
	public BigInteger getValue(){
		return value;
	}
	
	public void setGenerator(BigInteger gen){
		this.generator = gen;
	}
	
	public void setModulus(BigInteger modulus){
		this.modulus = modulus;
	}
	
	public void setFriend(User friend){
		this.friend = friend;
	}
}
