import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class LearningGenie {

	public static void main(String[] args) throws IOException {
		// Open the files that are to be used for reading and writing to
		FileWriter outfile = new FileWriter("src/WriteToMe.txt");
		File file = new File("src/GrinnellBuildingsTree.txt");
		
		// Creates a new tree from the given file
		DecisionTree tree = new DecisionTree(file);
		boolean playing = true;

		System.out.println("I am the learning genie!");
		System.out.println("I can figure out whatever you are thinking of by asking questions.");
		System.out.println("I know " + tree.countObjects() + " thing!");
		
		// Opens a scanner to get input from the user
		Scanner in = new Scanner(System.in);
	
		// Loops untill promted to stop
		while (playing) {
			
			System.out.println("Think of an object!");
			tree.guess(in);
			
			System.out.println("Do you want to continue?");
			String resp = in.nextLine();
			resp.toLowerCase();
			if (resp.equals("no")) { // user want to quit program
				playing = false;
				// Write current tree to file
				tree.head.write(outfile);
			} else if (!resp.equals("yes")) {
				System.out.println("invalid response");
			}
		}
		
		outfile.close();
		
	}

}

