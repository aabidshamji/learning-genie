import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class GuessNode implements DecisionNode {
	public String root;
	public DecisionNode left; 
	public DecisionNode right; 
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
		System.out.println("Were you thinking of" + root + "?");
		String response = in.nextLine();
		response.toLowerCase();
		if (response.equals("yes")) {
			System.out.println("Excellent, thanks!");
		} else if (response.equals("no")) {
			System.out.println("Oh no, I was wrong!");
			System.out.println("What animal were you thinking of?");
			String newAnimal = in.nextLine();
			System.out.println("What is a yes or no question that distinguishes a "+ root +" from a " + newAnimal);
			String newQuestion = in.nextLine();
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
		// TODO Auto-generated method stub
		
	}
	
	public boolean isQuestion() {
		return false;
	}
	
}
