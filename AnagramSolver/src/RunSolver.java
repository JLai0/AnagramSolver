import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RunSolver {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner readDictionary = new Scanner(new File("dictionary.txt"));
		List<String> dictionaryStored = new ArrayList<String>();
		while (readDictionary.hasNext()) {
			dictionaryStored.add(readDictionary.next());
		}
		AnagramSolver solver = new AnagramSolver(dictionaryStored);
	}

}
