import java.io.File;
import java.util.*;

/*
 * By Jonathan Lai
 * Ms. Maloney
 * Data Structures
 * Anagram solver is a program that utilizes the idea of backtracking, 
 * a technique whose function is to determine all possible outcomes of a program.
 * This program in particular takes in a certain word and searches through a dictionary
 * using a scanner to find words that can be used as an anagram for the inputted word
 * while fitting the maximum word constraint the anagram may use.
 */
public class AnagramSolver {
	private String originalPhrase;
	// The word that is taken into the program
	private int maxWords;
	// Maximum possible words that can be included in an angram set
	private HashMap<String, LetterInventory> possibleCombinations = new HashMap<String, LetterInventory>();
	// Stores dictionary words as keys linking them to the set of letters that make
	// them up
	private Stack<String> solutions = new Stack<String>();
	// Stack used to store possible anagram solutions

	// pre: Anagramsolver will take in a non-empty list of words
	// post: The inputed list will be used to compile the possibleCombinations
	// HashMap
	public AnagramSolver(List<String> list) {
		/*
		 * The constructor AnagramSolver will be used to construct an AnagramSolver
		 * object using the inputed list as the dictionary that will be used to pull
		 * words from. These words will make up anagram solutions. Furthermore,
		 * the method will take in the word that will be used to create the anagramSolver,
		 * this will be stored as the originalPhrase. The maximum number of words the solutions
		 * may be will also be stored under variable maxWords.
		 */
		for (int x = 0; x < list.size(); x++) {
			// Loop through the inputed list to compile possibleCombinations
			// setting the word as the key and letters as the value
			LetterInventory currentWord = new LetterInventory(list.get(x));
			possibleCombinations.put(list.get(x), currentWord);
		}
		System.out.print("phrase to scramble? ");
		Scanner userInput = new Scanner(System.in);
		originalPhrase = userInput.nextLine().toLowerCase();
		if (originalPhrase.isEmpty() || originalPhrase.split(" ").length == 0) {
			// if word is empty then throw an illegal argumentException
			throw new IllegalArgumentException("Invalid Phrase");
		}
		System.out.print("Max words to include? ");
		maxWords = userInput.nextInt();
		if (maxWords <= 0) {
			// If inputed max number for anagram solution is 0 or less
			// throw an IllegalArgumentException
			throw new IllegalArgumentException("Invalid Anagram Length");
		}
		for (String word : possibleCombinations.keySet()) {
			// Loop through possibleCombinations removing any repeated
			// possible solutions in possibleCombinations
			if (possibleCombinations.get(word).size() > originalPhrase.length()) {
				possibleCombinations.remove(word);
			}
			print(originalPhrase, 0);
		}

	}

	
	//pre: takes in 
	private void print(String s, int max) {
		LetterInventory storedWord = new LetterInventory(s);
		if (!storedWord.isEmpty()) {
			if (max < maxWords) {
				for (String word : possibleCombinations.keySet()) {
					storedWord = new LetterInventory(s);
					if (word.length() <= s.length()) {
						if (storedWord.subtract(possibleCombinations.get(word)) != null) {
							solutions.push(word);
							String newWord = storedWord.subtract(possibleCombinations.get(word)).toString();
							print(newWord, max + 1);
						}
					}

				}
				if (!solutions.isEmpty()) {
					solutions.pop();
				}
			} else {
				solutions.pop();
			}

		} else {
			if (storedWord.isEmpty()) {
				System.out.println(solutions.toString());
				solutions.pop();
			}

		}

	}
}
