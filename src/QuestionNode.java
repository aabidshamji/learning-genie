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
	
	/**
	* @return the number of objects that the decision tree has learn
	*/
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
	/**
	* @param in, an open valid scanner
	* @return a DecisionNode if the guess is not already in the tree; null if the guess is in the tree
	*/
	public DecisionNode guess(Scanner in) {
		System.out.println(this.root);
		String response = in.nextLine();
		response.toLowerCase();
		if (response.equals("yes")) {
			DecisionNode newNode = left.guess(in);
			if (newNode != null) { this.left = newNode;}
		} else if (response.equals("no")) {
			DecisionNode newNode = right.guess(in);
			if (newNode != null) { this.right = newNode;}
		} else {
			System.out.println("Invalid Answer");
		}
		return null;
	}
	
	/**
	* @param an open and valid filewriter that the pre-order representation of the tree will be written to
	*/
	@Override
	public void write(FileWriter out) throws IOException {
		out.write("#" + this.root);
		out.append('\n');
		this.left.write(out);
		this.right.write(out);
	}
	
	public boolean isQuestion() {
		return true;
	}

}
