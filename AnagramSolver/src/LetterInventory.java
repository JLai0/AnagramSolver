/*
 * Name: Jonathan Lai
 * Due Date: September 25th 2019
 * Class: Data Structures
 * Period: 02
 * Teacher: Ms. Maloney
 * LetterInventory
 */

public class LetterInventory {
	/*
	 * The LetterInventory class works to create an object instance that takes in a
	 * String. The string is then parsed out into its individual characters.
	 * Ignoring non-alphabetical characters and converting upper-case characters to
	 * lower-case and converts these lower-case characters to ascii values. The
	 * array keeps count of each instance of each character's appearance using these
	 * ascii values with each indices corresponding to a specific alphabetical
	 * letter. In doing so a toString method is able to create a visual image of the
	 * array by printing out each character, depending on their number of
	 * appearance, in alphabetical order. Using this information other
	 * LetterInventories can be used to subtract or add their own character counts.
	 * Other methods like set allow for the modification of the count of a specific
	 * data while others like get allows for the access of character counts within
	 * the arrays. The following indices indicate the character values: 1 - a, 26 -
	 * z, 0 - nothing.
	 */
	private int[] letterCount;
	// array to track character appearances.
	private int totalCharacterCount;
	// int to track size of the LetterInventory.
	private boolean hasNotCheckedSize;
	// boolean to prevent redundancy when checking the size of the LetterInventory
	// object. It will only have to traverse the array once at the beginning to get
	// the initial size.

	LetterInventory(String data) {
		/*
		 * The LetterInventory constructor. It takes in a single String, which is
		 * subsequently converted to lower-case and has its spaces removed, elements of
		 * the string not important to LetterInventory or may cause errors
		 */
		/*
		 * pre: a string will be passed in.
		 */
		/*
		 * post: an array with the count of each alphabetical characters' appearance
		 * (case-insensitive). It will ignore numbers and non-alphabetical characters.
		 */
		letterCount = new int[27];
		/*
		 * letterCount is an int array that keeps track of every letter occurrence.
		 * non-case sensitive. Each index correlates with a different letter. a = 1. 26
		 * = z.
		 */
		hasNotCheckedSize = true;
		/*
		 * hasNotCheckedSize allows for the prevention of redundancy when it comes to
		 * checking the size of the LetterInventory object. By creating a boolean the
		 * size is only checked initially upon being constructed and the size is changed
		 * accordingly when add, subtract, set is called. the size is stored within the
		 * object's instance.
		 */

		data = data.toLowerCase();
		// turns entire inputed string into lower-case.
		data = data.replaceAll("\\s", "");
		// removes all spaces in inputed string because they are not needed.
		for (int x = 0; x < data.length(); x++) {
			/*
			 * The following for loop will loop through the entire inputed string, each time
			 * checking if the character is an alphabetical one by changing a specific ascii
			 * range and if it is then it will add to the index in the letterCount array
			 * corresponding to that character.
			 */
			if ((data.charAt(x) >= 97) && (data.charAt(x) <= 122)) {
				/*
				 * All ascii values between 97 and 122 will be accepted as these ascii values
				 * encompass all of the lower-case letters meaning that the character at these
				 * indices are valid, lower-case, alphabetical characters that will add to the
				 * count.
				 */
				int asciiValue = data.charAt(x) - 96;
				/*
				 * breaks down string into individual lower-case letters each individual letter
				 * is turned into an ascii value but subtracting 97 so that each ascii value
				 * correlates with the letterCount array values (1 - 26) (1 = a, 26 = z).
				 */
				letterCount[asciiValue]++;
				/*
				 * the resulting value resulted from the character adds one to the corresponding
				 * index value for that character in the alphabet array.
				 */

			}

		}
		totalCharacterCount = size();
		// Runs size method to store the size of the letterInventory object initially
	}

	/*
	 * pre: will take in a letter that may be lower-case or upper-case.
	 * non-alphabetical character may be passed in.
	 */
	/*
	 * post: an int value will be returned depending on the value that appears in
	 * the character's corresponding index value in letterCount array. Otherwise, an
	 * IllegalArgumentException may be caught.
	 * 
	 */
	public int get(char letter) throws IllegalArgumentException {
		// returns an int that is indicative of the total appearances of a certain
		// letter within the letterCount array.
		int letterModifiedAsciiValue = Character.toLowerCase(letter) - 96;
		// turns the alphabetical letter to lower-case and subtract 96 so its value
		// corresponds to the letterCount array indices
		if ((letterModifiedAsciiValue < 1) || (letterModifiedAsciiValue > 26)) {
			// the if statement checks that the value derived from the ascii value of the
			// character subtract 96 is not outside of the array's indices as that would
			// indicate a non-alphabetical character ascii value. If this is a case an error
			// will be thrown.
			throw new IllegalArgumentException("Input Invalid! Only alphabetical values are accepted!");
			// Will throw the following message if the IllegalArgumentException error is
			// caught.
		}
		try {
			return letterCount[letterModifiedAsciiValue];
			/*
			 * returns the count of that letter in the array as the value at that index
			 * indicates the total appearance of that letter/ascii value.
			 */

		} catch (IllegalArgumentException ex) {
			// IllegalArgumentException will be caught if the entered parameter is not an
			// alphabetical character
			ex.getMessage();
			// prints out error upon receiving an IllegalArgumentException.
		}
		return -1;
		// returns -1 if somehow methods of error catching is bypassed.

	}
	
	/*
	 * pre: will take in a char value that may be lower-case or upper-case. int
	 * values will also be passed in but may be positive or negative.
	 */
	/*
	 * post: IllegalArgumentException will be caught, the program will be stopped,
	 * and a message will be printed in the console if 1) the character passed in is
	 * non-alphabetical 2) the int passed in is negative.
	 */
	public void set(char letter, int value) throws IllegalArgumentException {
		// returns no values. Set modifies the count value of a certain character inside
		// of the letterCount array.
		int letterModifiedAsciiValue = Character.toLowerCase(letter) - 96;
		// turns the alphabetical letter to lower-case and subtract 96 so its value
		// corresponds to the letterCount array indices
		if ((letterModifiedAsciiValue < 1) || (letterModifiedAsciiValue > 26)) {
			// the if statement checks that the value derived from the ascii value of the
			// character subtract 96 is not outside of the array's indices as that would
			// indicate a non-alphabetical character ascii value. If this is a case an error
			// will be thrown.
			throw new IllegalArgumentException("Input Invalid! Only alphabetical values are accepted!");
			// prints out error upon receiving an IllegalArgumentException.
		}
		try {
			/*
			 * converts character to ascii value subtracts 96 so it corresponds with array
			 * index values
			 */
			int oldValue = letterCount[letterModifiedAsciiValue];
			// stores the old value of the character so size can be modified accordingly
			letterCount[letterModifiedAsciiValue] = value;
			/*
			 * sets the corresponding index value of that letter's ascii value to the
			 * inputed value
			 */
			totalCharacterCount += (-oldValue + value);
			// Calculates the difference between the old and new value and modifies the
			// existing characterCount of the letterCount array accordingly.
		} catch (IllegalArgumentException ex) {
			// IllegalArgumentException will be caught if the entered parameter is not an
			// alphabetical character
			ex.getMessage();
			// prints out error upon receiving an IllegalArgumentException.
		}
	}
	
	/*
	 * pre: LetterInventory will never have a size that is negative.
	 */
	/*
	 * post: a boolean will be returned. true will indicate that the LetterInventory
	 * object is indeed empty and false will indicate that it has at least one
	 * non-zero value stored
	 */
	public boolean isEmpty() {
		// returns a boolean depending on if the LetterInventory object has a total sum
		// value of 0 in regards to character appearances. Checks if it is empty.
		int totalCharacterCount = 0;
		// creates an int totalCharacterCount that will track the collective sum of all
		// the values stored in the letterCount array.
		for (int x = 0; x < letterCount.length; x++) {
			// loops through each letterCount index value, checking each indices' value
			// these values are added to a collective sum.
			totalCharacterCount += letterCount[x];
		}
		if (totalCharacterCount == 0) {
			// if the collective sum is equal to zero then the LetterInventory is indeed
			// empty as all values are 0.
			return true;
		} else {
			// otherwise, if the value of the array is not zero than the letterInventory is
			// not empty.
			return false;
		}
	}

	/*
	 * pre: LetterInventory will never have a size less than 0
	 */
	/*
	 * post: A int will be returned that is the collective sum of the count of all
	 * characters in the letterCount array.
	 * 
	 */
	public int size() {
		// returns an int of the total sum of character appearance in a LetterInventory
		// object.
		if (hasNotCheckedSize) {
			// hasNotCheckedSize is only true upon the construction of the LetterInventory
			// object initially. This is so that the initial size is collected and stored
			// but afterwards hasNotCheckedSize will be set false. This means that when size
			// is called the method will not have to check through the array again. Instead
			// the int totalLetterCount will change accordingly with the usage of add,
			// subtract, or set.
			totalCharacterCount = 0;
			// sets initial value to 0
			for (int x = 0; x < letterCount.length; x++) {
				// Traverses the array checking the value at each index.
				totalCharacterCount += letterCount[x];
				// the values are added to a collective sum under totalCharacterCount, which is
				// stored as a data value.
			}
			hasNotCheckedSize = false;
			// after the first check the method size will never traverse through the array
			// of LetterInventory again to prevent redundancy.
			return totalCharacterCount;
		} else {
			// if size method has already been called then return the existing stored
			// totalCharacterCount value, which already takes the changes created by add,
			// subtract, and set into account.
			return totalCharacterCount;
		}
	}

	/*
	 * pre: LetterInventory will not have a negative size value
	 */
	/*
	 * post: A string will be returned in the format: "[]" with each character
	 * occurrence being printed accordingly inside of the brackets in alphabetical
	 * order.
	 */
	public String toString() {
		// returns a string representation of the number of character appearances in the
		// LetterInventory object.
		String lettersStrungTogether = "";
		// creates the initial empty string where the characters will be concatenated to
		lettersStrungTogether += "[";
		for (int x = 0; x < letterCount.length; x++) {
			for (int y = 0; y < letterCount[x]; y++) {
				// traverses the array using the value x. y will repeat according to the value
				// in that index so that the character will be printed the correct amount of
				// times as the letterCount array indicates.
				lettersStrungTogether += ((char) (x + 96));
				// concatenates the chars together under a single string.
			}
		}
		lettersStrungTogether += "]";
		return lettersStrungTogether;
		// returns the concatenated string
	}

	/*
	 * pre: both LetterInventory's will have at least one value stored in the
	 * letterCount array
	 */
	/*
	 * post: a separate LetterInventory instance will be created as to prevent the
	 * modification and destruction of the existing LetterInventory objects. A new
	 * LetterInventory object will be returned.
	 */
	public LetterInventory add(LetterInventory other) {
		// adds the number of character appearances between two LetterInventories,
		// resulting in a new returned LetterInventory.
		LetterInventory addedAlphabetCount = new LetterInventory("");
		// creates new LetterInventory that will take in the values from both
		// LetterInventories being added together.
		int combinedCount;
		// Combined count will track the sum total amount of times each index value
		// indicates that a character has appeared in both LetterInventories.
		for (int x = 1; x < letterCount.length; x++) {
			// The for loop uses the x-value to traverse the arrays of the
			// LetterInventories.
			combinedCount = letterCount[x] + other.get((char) (x + 96));
			// Sum of character appearance in both letterInventories.
			addedAlphabetCount.set((char) (x + 96), combinedCount);
			// Using the combinedCount the character's appearances in the new
			// LetterInventory will be set accordingly.
		}
		return addedAlphabetCount;
		// returns the new LetterInventory object.
	}

	/*
	 * pre: both LetterInventory's will have at least one value stored in the
	 * letterCount array
	 */
	/*
	 * post: a separate LetterInventory instance will be created as to prevent the
	 * modification and destruction of the existing LetterInventory objects. A new
	 * LetterInventory object will be returned. If the value of any of the
	 * characters in the letterCount in the new LetterInventory results in a number
	 * less than 0 then null will be returned instead.
	 */
	public LetterInventory subtract(LetterInventory other) {
		// subtracts the number of character appearances between two LetterInventories,
		// resulting in a new returned LetterInventory.
		LetterInventory subtractedAlphabetCount = new LetterInventory("");
		// creates new LetterInventory that will take in the values from both
		// LetterInventories being added together.
		int combinedCount;
		// Combined count will track the sum total amount of times each index value
		// indicates that a character has appeared in both LetterInventories.
		for (int x = 1; x < letterCount.length; x++) {
			// The for loop uses the x-value to traverse the arrays of the
			// LetterInventories.
			combinedCount = letterCount[x] - other.get((char) (x + 96));
			// difference of character appearance in both letterInventories.
			if (combinedCount >= 0) {
				// if the combinedCount does not result in a number less than 0 then set the
				// new character value in the new LetterInvetory object.
				subtractedAlphabetCount.set((char) (x + 96), combinedCount);
				// Using the combinedCount the character's appearances in the new
				// LetterInventory will be set accordingly.
			} else {
				// if combinedCount results in a number less than 0 then null will be returned
				// instead.
				return null;
			}
		}
		return subtractedAlphabetCount;
		// returns the new LetterInventory object.
	}

}