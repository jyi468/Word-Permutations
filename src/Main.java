//import java.util.*;
import java.io.*;

public class Main {


	public static void main(String[] args) {
		System.out.println("Enter your text: ");
		String word;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			word = br.readLine();
			word = word.toUpperCase();
			Ranker r = new Ranker();
			r.rank(word);
			//System.out.println("The ranking of " + word + " is: " + rank);
		}
		
		catch (IOException x) {
			System.out.println("Error with Input");
			System.exit(1);
		}
	}

}
