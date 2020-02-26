import java.util.ArrayList;
import java.util.List;

public class BinarySearchTreeRec {
	
	class Node {
		public Node(int data) {
			this.data = data;
		}	
		int data;
		Node left;
		Node right;
	}

	Node root;
	
	public void insertRec(Node node) {
		insertRec(node.data, root);
	}
	
	private void insertRec(int data, Node node) {		
		if (node == null) {
			node = new Node(data);
			return;
		}	
		int nodeData = node.data;
		
		if (data > nodeData) 
			insertRec(data, node.right);	
		else if (data < nodeData) 
			insertRec(data, node.left);
	}
	
	public void deleteRec(Node node) {
		deleteRec(node.data, root);
	}
	
	private void deleteRec(int data, Node node) {
		int nodeData = node.data;
		
		if (node == null)
			return;
		
		if (data != nodeData) {
			if (data > nodeData)
				deleteRec(data, node.right);
			else if (data < nodeData)
				deleteRec(data, node.left);
		}
		else {
			if (node.left == null && node.right == null)
				node = null;
			else if (node.left != null && node.right != null) {
				int next = findNextRec(node).data;
				node.data = next;
				deleteRec(next, node.right);
			}
			else {
				node = node.right == null ? node.left : node.right;
				node.left = null;
				node.right = null;
			}
		}			
	}
	
	public void inOrder(List<Node> list, Node node) {
		if (node == null)
			return;	
		System.out.println(node.data);
		
		inOrder(list, node.left);
		list.add(node);
		inOrder(list, node.right);	
	}
	
	public Node findNextRec(Node node) {		
		List<Node> arr = new ArrayList<Node>();
		inOrder(arr, root);
		
		for (int i = 0; i < arr.size(); i++) {
			if (arr.get(i) != node) 
				continue;
			node = arr.get(i+1);
		}
		return node;
	}
	
	public Node findPrevRec(Node node) {	
		List<Node> arr = new ArrayList<Node>();
		inOrder(arr, root);
		
		for (int i = 0; i < arr.size(); i++) {
			if (arr.get(i) != node) 
				continue;
			node = arr.get(i-1);
		}
		return node;
	}
	
	public Node findMinRec(Node node) {
		if (node.left != null) 
			return findMinRec(node.left);
		return node;
	}
	
	public Node findMaxRec(Node node) {
		if (node.right != null) 
			return findMaxRec(node.right);
		return node;
	}
}
