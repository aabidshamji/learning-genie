import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class LearningGenie {

	public static void main(String[] args) throws IOException {
		FileWriter outfile = new FileWriter("src/hello2.txt");
		File file = new File("src/hello.txt");
		
		DecisionTree tree = new DecisionTree(file);
		boolean playing = true;

		System.out.println("I am the learning genie!");
		System.out.println("I can figure out whatever you are thinking of by asking questions.");
		System.out.println("I know " + tree.countObjects() + " thing!");
		
		
		
		Scanner in = new Scanner(System.in);

		while (playing) {
			
			System.out.println("Think of an object!");
			tree.guess(in);
			
			System.out.println("Do you want to continue?");
			String resp = in.nextLine();
			resp.toLowerCase();
			if (resp.equals("no")) {
				playing = false;
				tree.head.write(outfile);
			} else if (!resp.equals("yes")) {
				System.out.println("invalid response");
			}
		}
		
		outfile.close();
		
	}

}

