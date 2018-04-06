import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DecisionTree {
	public DecisionNode head; 
	
	/**
	** The constructor when no previous file tree is being used.
	**/
	public DecisionTree() {
		this.head = new GuessNode("Noyce");
	}
	
	/**
	** @param file; the constuctor when a previous file tree is being used
	**/
	public DecisionTree(File file) throws FileNotFoundException {
		Scanner text = new Scanner(file);
		String nextline = text.nextLine();
		this.head = parseNode(text, nextline);
	}
	
	/**
	** Used to add tree from existing file to program
	** @return newNode, a DecisionNode that replaces the head of the tree
	** @param text, a scanner of the existing file containing a tree
	** @param s, the first line of the scanned file
	**/
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
	
	/**
	** @return countObjects; an int of the number of guess nodes in the tree
	**/
	public int countObjects() {
		return head.countObjects();
	}

	/**
	** Calls to guess in GuessNode, replaces the head if this is the first new guess
	** @param in, a scanner of the command line input
	**/
	public void guess(Scanner in) {
		DecisionNode newNode = head.guess(in);
		if (head.countObjects() == 2 && newNode != null) {
			head = newNode;
		}
	}
	
	/**
	** Writes to the new file
	** @param out, the new file to write to
	**/
	public void write(FileWriter out) throws IOException {
		this.head.write(out);
	}
	
	
}
