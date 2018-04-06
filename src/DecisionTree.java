import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DecisionTree {
	public DecisionNode head; 
	
	
	public DecisionTree() {
		this.head = new GuessNode("Noyce");
	}
	
	public DecisionTree(File file) throws FileNotFoundException {
		Scanner text = new Scanner(file);
		String nextline = text.nextLine();
		this.head = parseNode(text, nextline);
	}
	
	public DecisionNode parseNode (Scanner text, String s) {
		DecisionNode newNode;
		if (s.charAt(0) == '#') {
			s = s.substring(1);
			String left = text.nextLine();
			DecisionNode leftNode = parseNode(text, left);
			String right = text.nextLine();
			DecisionNode rightNode = parseNode(text, right);
			newNode = new QuestionNode(s, leftNode, rightNode);
		}
		else {
			newNode = new GuessNode(s);
		}
		return newNode;
	}
	
	public int countObjects() {
		return head.countObjects();
	}

	public void guess(Scanner in) {
		DecisionNode newNode = head.guess(in);
		if (head.countObjects() == 2 && newNode != null) {
			head = newNode;
		}
	}
	
	public void write(FileWriter out) throws IOException {
		this.head.write(out);
	}
	
	
}
