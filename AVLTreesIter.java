import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class AVLTreesIter {
	

	class Node {
		public Node(int data) {
			this.data = data;
		}	
		int data;
		Node left;
		Node right;
	}
	Node root;
	int counter;
	
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

		if (node == null)
			return 0;

		return height(node.left) - height(node.right); 
	}
	
	public int height(Node node) {

		if (node == null) 
			return 0;
		
		int height = 0;
		Queue<Node> queue = new LinkedList<>();
		queue.add(node);
		
		while (true) {	
			int nodes = queue.size();
			if (nodes == 0)
				break;
			height++;
			
			while (nodes > 0) {
				Node p = queue.remove();
				if (p.left != null) 
					queue.add(p.left);
				if (p.right != null) 
					queue.add(p.right);
				nodes--;
			}
		}
		return height;
	}
	
	public void insertIter(int num) {	
		Node node = new Node(num);
		Stack<Node> stack = new Stack<Node>();	
		Node treeNode = root;
		
		if (root == null) {
			root = node;
			treeNode = root;
		}
		else {
			
			while (treeNode != null) {	
				stack.push(treeNode);
				if (node.data < treeNode.data) 
					treeNode = treeNode.left;
				else if (node.data > treeNode.data) 
					treeNode = treeNode.right;
				counter++;
			}	
			treeNode = node;
		}
		
		
		stack.push(treeNode);
		balance(stack);
	}
	
	public void balance(Stack<Node> stack) {

		
		while (!stack.isEmpty()) {
			Node node = stack.pop();	
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
			else if (BF < -1) {
				if (height(right.right) > height(right.left)) 		
					leftRotate(node);
				else if (height(right.left) > height(right.left)) 
					rightLeftRotate(node);
			}			
		}
	}
	
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
	
	public void deleteIter(Node node) {
		Node treeNode = root;
		Stack<Node> stack = new Stack<Node>();
		
		while (treeNode.data != node.data) {
			if (treeNode == null)
				break;
			if (node.data < treeNode.data) 
				treeNode = treeNode.left;
			else if (node.data > treeNode.data) 
				treeNode = treeNode.right;
			
			stack.push(treeNode);
		}
		
		if (treeNode.left == null && treeNode.right == null) 
			treeNode = null;
		else if (treeNode.left != null && treeNode.right != null) {
			deleteParentofTwo(treeNode);
		}
		else {
			treeNode = treeNode.left == null ? treeNode.right : treeNode.left;
			treeNode.left = null;
			treeNode.right = null;
		}
		
		balance(stack);
	}
	
	public void deleteParentofTwo(Node node) {
		Node next = findNextIter(node);
		node.data = next.data;
		deleteIter(next);
	}
	
	public Node findNextIter(Node node) {
		/* if node has right child:
		 * 		find leftmost node in that subtree
		 * else:
		 * 		root where node is left child
		 */
		
		if (node.right != null) {
			node = node.right;
			while (node.left != null) 
				node = node.left;
		}
		else {
			Node parent = findParent(node);
			
			while (parent.right == node) {
				if (parent == null) 
					break;
				node = parent;
				parent = findParent(parent);
			}		
			node = parent;	
		}		
		return node;	
	}
	
	public Node findParent(Node node) {
		Node treeNode = root;
		
		while (true) {
			if (treeNode.right == node || treeNode.left == node) 
				break;
			
			if (treeNode == null) 
				break;
			
			if (node.data < treeNode.data) 
				treeNode = treeNode.left;
			
			else if (node.data > treeNode.data) 
				treeNode = treeNode.right;					
		}
		return treeNode;
	}
	
	
	public Node findMinIter(Node node) {
		while (node.left != null) 
			node = node.left;
		return node;	
	}
	
	public Node findMaxIter(Node node) {
		while (node.right != null) 
			node = node.right;
		return node;	
	}

}
