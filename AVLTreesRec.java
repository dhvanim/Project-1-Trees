import java.util.ArrayList;
import java.util.List;

public class AVLTreesRec {
	
	class Node {
		public Node(int data) {
			this.data = data;
		}	
		int data;
		Node left;
		Node right;
	}
	Node root;
	
	/*
	 * cases:
	 * A. left subtree is unbalanced (+) 
	 * 		on left child: 
	 * 		 1. left child height is greater 
	 * 		    >>>>> right rotate on orig
	 * 		 2. right child height is greater
	 * 			>>>>> left rotate on orig's left child
	 * 			>>>>> right rotate on orig 
	 * B. right subtree is unbalanced (-)
	 * 		on right child:
	 * 		 3. right child height is greater
	 * 			>>>>> left rotate on orig
	 * 		 4. left child height is greater
	 * 			>>>>> right rotate on orig's right child
	 * 			>>>>> left rotate on orig
	 */				
	
	public int balanceFactor(Node node) {
		return height(node.left) - height(node.right); 
	}
	
	public int height(Node node) {
		if (node == null)
			return 0;	
		int left = height(node.left);
		int right = height(node.right);			
		
		return left > right ? left + 1 : right + 1;		
	}
	
	public void insertRec(int num) {
		insertRec(num, root);
	}
	
	private Node insertRec(int data, Node node) {		
		if (node == null) {
			return new Node(data);
		}	
		int nodeData = node.data;
		
		if (data > nodeData) 
			node.right = insertRec(data, node.right);	
		else if (data < nodeData) 
			node.left = insertRec(data, node.left);
		
		int BF = balanceFactor(node);
		Node left = node.left;
		Node right = node.right;
		
		if (BF > 1) {
			if (height(left.left) > height(left.right))
				rightRotate(node);
			else if (height(left.right) > height(left.right)) {
				leftRightRotate(node);
			}	
		}
		else if (BF < 1) {
			if (height(right.right) > height(right.left)) 		
				leftRotate(node);
			else if (height(right.left) > height(right.left)) 
				rightLeftRotate(node);
		}
		
		return node;
	}
	/*
	 * old:
	 * node
	 * node left child
	 * node left child's right child
	 * 
	 * new:
	 * node's left child = node'd left child's right child
	 * node's left child right child = node
	 * 
	 */
	
	public Node rightRotate(Node node) {
		Node y = node.left;
		Node w = y.right;
		
		node.left.right = node;
		node.left = w;
		
		return node;
	}
	
	public Node leftRotate(Node node) {
		Node y = node.right;
		Node w = y.left;
		
		node.right.left = node;
		node.right = w;
		
		return node;
	}
	
	public Node rightLeftRotate(Node node) {
		rightRotate(node.right);
		leftRotate(node);
		
		return node;
	}
	
	public Node leftRightRotate(Node node) {
		leftRotate(node.left);
		rightRotate(node);
		
		return node;
	}
	
	public void deleteRec(Node node) {
		
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
