import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class GuessNode implements DecisionNode {
	public String root;

	public static int count; 
	
	public GuessNode(String root) {
		this.root= root;
		count++; 
	}

	@Override
	public int countObjects() {
		return count;
	}

	@Override
	public DecisionNode guess(Scanner in) {
		System.out.println("Were you thinking of " + root + "?");
		String response = in.nextLine();
		response.toLowerCase();
		if (response.equals("yes")) {
			System.out.println("Excellent, thanks!");
		} else if (response.equals("no")) {
			System.out.println("Oh no, I was wrong!");
			System.out.println("What were you thinking of?");
			String newAnimal = in.nextLine();
			System.out.println("What is a yes or no question that distinguishes a "+ root +" from a " + newAnimal + "?");
			System.out.println("(Yes corresponds to "+ root +"; No corresponds to " + newAnimal + ")");
			String newQuestion = in.nextLine();
			System.out.println("Thanks! I'll learn from this experince!");
			DecisionNode newRight = new GuessNode(newAnimal);
			DecisionNode newQ = new QuestionNode(newQuestion, this, newRight); 
		 
			return newQ; 
		} else {
			System.out.println("Invalid Answer");
		}
		return null;
	}

	@Override
	public void write(FileWriter out) throws IOException {
		out.write(this.root);
		out.append('\n');
		
	}
	
	public boolean isQuestion() {
		return false;
	}
	
}
