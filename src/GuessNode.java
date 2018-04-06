import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class GuessNode implements DecisionNode {
	public String root;
	public static int count;  //number of guessNodes
	
	/**
	** @param root, the string of the guess
	**/
	public GuessNode(String root) {
		this.root= root;
		count++; 
	}

	/**
	** @return int count, the number of guess nodes
	**/
	@Override
	public int countObjects() {
		return count;
	}

	/**
	** Prompts the user to see if the guess was correct, and adds new information if wrong
	** @param in, a scanner of the user command line input
	** @return newQ or null, a DecisionNode depending on the response to the guess
	**/
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

	/**
	** Writes new guess to the new file
	** @param out, the new file
	** @throws IOException
	**/
	@Override
	public void write(FileWriter out) throws IOException {
		out.write(this.root);
		out.append('\n');
		
	}
	
	/**
	**@return false, GuessNOde is not a question
	**/
	public boolean isQuestion() {
		return false;
	}
	
}
