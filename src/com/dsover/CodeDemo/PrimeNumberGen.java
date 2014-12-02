package com.dsover.CodeDemo;
import java.util.Vector;

/**
 * 
 * @author alex sover
 * this is a singleton Prime Number Generator used to create 
 * a single set of prime numbers to be used by application
 * 
 * input integer
 * output(s) Vector<Integer>
 */
public class PrimeNumberGen {
	private static PrimeNumberGen firstInstance = null;
	private Vector<Integer> primeList = new Vector<Integer>();
	private int primeListUpperBound = 2;	//I set the starting point at 2 because 2 is the smallest prime number
	
	
	/**
	 * the constructor is private to prevent 
	 * other classes from instantiating 
	 * multiple objects
	 */
	private PrimeNumberGen(){}
	
	/**
	 * returns the static instance for the PrimeNumberGen
	 * if no instance exists then it will create one
	 * @return PrimeNumberGen the active PrimeNumberGen
	 */
	public static PrimeNumberGen getInstance(){

		if(firstInstance == null){
			firstInstance = new PrimeNumberGen();
		}
		
		return firstInstance;
	}
	
	/**
	 * this method adds new prime numbers to primeList up to 'n' 
	 * by keeping track of the max 'n' value that has come through it prevents recalculation of prime numbers
	 * 
	 * @param n 
	 */
	public void generatePrimes(int n){
		
		if(n>primeListUpperBound){
			int previousMax = primeListUpperBound;
			primeListUpperBound = n;
			System.out.println("Generating Prime Numbers up to " + n);

			for(int i=previousMax; i<=n; i++){
				//remove even number other than 2 because they are not prime by definition 
				if((i == 2)||(!(i%2 ==0))){
					//assume all odd numbers are prime 
					boolean isPrime = true;
					//check if each odd number is divisible by some number smaller than it's square root rounded up
					//if it is then it is not prime and we exit the loop for that number 
					//and check the next number in our list of numbers to check 
					for (int j = 3; j <= Math.ceil(Math.sqrt(i)); j++){
						
			            if (i % j == 0){
			                isPrime = false;
			            	break;
			            }
					}
					// if the number is still marked as prime it is assumed to be prime and added to the list 
					if(isPrime){
						primeList.add(i);
					}
				}
			}
		}else{
			System.out.println("Using old Prime Set to Save Power");
		}
		
	}
	
	/**
	 * returns the list of prime numbers
	 * @return
	 */
	public Vector<Integer> getPrimeNumbers(){
		return firstInstance.primeList;
	}
	/**
	 * returns upper bound for prime list
	 * @return
	 */
	public int getPrimeListUpperBound(){
		return primeListUpperBound;
	}
	
}
