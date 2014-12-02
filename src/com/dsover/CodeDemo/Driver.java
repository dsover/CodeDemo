package com.dsover.CodeDemo;
import java.util.Vector;

/**
 * Application to test if a secret function is additive for all prime numbers up to some given value
 * ie. secret(x+y) = secret(x) + secret(y) 
 * @author alex
 *
 */
public class Driver {

	public static void main(String[] args) {
		int N = 100;//set this to the max bound integer for prime numbers
		boolean isAdditive = true;//flag to determine if the function was Additive
		
		
		
		//create a prime number generator object and generate prime numbers up to the given value
		//then create a vector to hold the prime numbers so they can be worked with in main
		PrimeNumberGen primeNumber= PrimeNumberGen.getInstance();
		primeNumber.generatePrimes(N);
		Vector<Integer> primes = primeNumber.getPrimeNumbers();
		//get the Secret object so we can later use the secret function
		Secret theSecret = new Secret();
		
		//Create a vector to hold prime numbers that have been used
		//this was done to prevent doing the same calculations multiple times
		Vector<Integer> usedPrimes = new Vector<Integer>();
		
		//work through the list of prime numbers
		//to test the Secret function
		for(Integer p: primes){
			System.out.println(p);
			
			//check each tuple of prime numbers testing if secret(x+y) == secret(x) + secret(y)  
			for(Integer p2: primes){
				if(p != p2 && !usedPrimes.contains(p2)){
					if(p2 != null){
						int test1 = theSecret.secretFunction(p + p2);
						int test2 = theSecret.secretFunction(p)+theSecret.secretFunction(p2);
						//if the tuple not equal set the is aditive = false and break out of the loop
						//there is no more reason to test the function is not Additive
						if(test1!=test2){
							isAdditive = false;
							break;
						}
					}
				}
			}
			//add the prime number to the used list because the program does not need to calculate that number any more
			usedPrimes.add(p);
		}
		//output to let the user know if the function was or was not Additive
		if(isAdditive == true){
			System.out.println("Secret is an additive function for prime integers up to "+primeNumber.getPrimeListUpperBound());
		}else{
			System.out.println("Secret is NOT an additive function for prime integers up to "+primeNumber.getPrimeListUpperBound());
		}
		
	}
	
}
