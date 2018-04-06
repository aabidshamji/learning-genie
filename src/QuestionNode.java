import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class QuestionNode implements DecisionNode{
	public String root;
	public DecisionNode left; 
	public DecisionNode right;  
	
	public QuestionNode(String root, DecisionNode left, DecisionNode right) {
		this.root = root; 
		this.left = left; 
		this.right = right;
	}

	@Override
	public int countObjects() {
		if (!this.left.isQuestion()) {
			return this.left.countObjects();
		} else {
			QuestionNode curr = this;
			while(curr.left.isQuestion()) {
				curr = (QuestionNode) curr.left;
			}
			return curr.left.countObjects();
		}
	}

	@Override
	public DecisionNode guess(Scanner in) {
		System.out.println(this.root);
		String response = in.nextLine();
		response.toLowerCase();
		if (response.equals("yes")) {
			left.guess(in);
		} else if (response.equals("no")) {
			right.guess(in);
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
		return true;
	}

}
