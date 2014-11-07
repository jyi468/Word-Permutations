import java.util.*;

public class Ranker {
	//Ex. BOOKKEEPER
	char[] charLetters; // [B,E,K,O,P,R]
	int[] charFreq;     // [1,3,2,2,1,1]
	int[] charSeq;      // [0,1,1,1,2,2,3,3,4,5]-> ordered  ->[B,E,E,E,K,K,O,O,P,R]
	int[] wordSeq;      // [0,3,3,2,2,1,1,4,1,5]->nonordered->[B,O,O,K,K,E,E,P,E,R]

	// charFreq and charLetters go hand-in-hand. The first alphabetical character in charLetters (B)
	// corresponds to the first number in charFreq (1). charSeq and wordSeq basically translate the
	// characters into alphabetical number rankings. These number rankings also correspond to the
	// indices of charFreq and char Letters.

	char[] ordered;
	char[] nonordered;

	public Ranker() {
	}

	public void rank(String word) {
		// This method returns the "rank" of the word entered (out of all permutations of the letters
		// in the word.


		// Sort chars into alphabetical order
		ordered = word.toCharArray();
		nonordered = word.toCharArray();
		Arrays.sort(ordered); // alphabetical order
		alphabetize(ordered); // get frequencies/pattern for ordered array


		// get number pattern for nonordered (word) array
		for (int i = 0; i < charSeq.length; i++) { 
			for (int j = 0; j < charLetters.length; j++) {
				if (nonordered[i]==charLetters[j])
					wordSeq[i] = j;
			}
		}
		/*for(int x:wordSeq)
			System.out.print(x + " ");
		System.out.println();*/

		long Rank = 1;
		int n = charSeq.length;


		for(int i = 0; i < n; i++) { 
			// This is where the multiple factorials ( n!/(n_1!*n_2!*...*n_n!) )
			// are calculated and summed up
			// We use the wordSeq and charFreq array to do the calculations	
			for(int j = 0; j < wordSeq[i]; j++) {
				if(charFreq[j] > 0) {
					charFreq[j]--;
					Rank += multiFact(n-i-1,charFreq);
					//System.out.println(Rank);
					charFreq[j]++;
					/*for(int x:charFreq)
							System.out.print(x + " ");
						System.out.println();*/
				}
			}
			charFreq[wordSeq[i]]--;
		}
		System.out.println("The Rank of: " + word + " is " + Rank);
	}




	public void alphabetize(char[] chars){
		// Ex. [A,B,B,B,C,D,D]
		// charSeq -> [0,1,1,1,2,3,3]
		// charFreq -> [1,3,1,2,0,0,0]
		// charLetters -> [A,B,C,D,0,0,0]
		int len = chars.length;
		charSeq = new int[len];
		charFreq = new int[len]; //initialize as same size as charSeq for simplicity
		charLetters = new char[len];
		wordSeq = new int[len];

		char curChar = chars[0]; // A
		charLetters[0] = curChar;
		int curCharCount = 1;
		int curCharIndex = 0;

		// curCharIndex = 0;
		charSeq[0] = curCharIndex;

		for(int i = 1; i < chars.length; i++) {

			if (chars[i] > curChar) { 

				charFreq[curCharIndex] = curCharCount; 
				curChar = chars[i]; 

				curCharCount = 1;
				curCharIndex++; 
				charLetters[curCharIndex] = curChar; 
				charSeq[i] = curCharIndex;
				charFreq[curCharIndex] = curCharCount;
			}
			else { 
				curCharCount++; 
				charSeq[i] = curCharIndex; 
			}
		}

		// Debugging --------------------------------------

		// Resize charFreq and charLetters
		/*int size = 0;
		while(charFreq[size] != 0)
		size++;*/

		//System.out.println(charFreq.length);
		//System.out.println(charLetters.length);

		/*System.out.println(charLetters);
		for(int x:charFreq)
			System.out.print(x + " ");*/


		// -------------------------------------------------

	}

	public long factorial(int n) {
		// For basic factorials such as 7! = 5040
		if (n <= 0) {
			return 1;
		}
		else {
			return n*factorial(n-1);
		}
	}

	public long multiFact(int n, int[] cFreq){ 
		// For doing calculations with multiple factorials given an array sequence of numbers
		// Ex. 8!/(3!*2!*2!)

		long f = factorial(n);

		for(int i = 0; i < cFreq.length; i++) {
			f /= factorial(cFreq[i]);
		}
		return f;
	}
}
