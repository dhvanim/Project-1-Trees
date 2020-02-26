
public class BinarySearchTreeIter {

	class Node {
		public Node(int data) {
			this.data = data;
		}	
		int data;
		Node left;
		Node right;
	}
	Node root;
	
	public void insertIter(Node node) {
		Node treeNode = root;
		
		while (treeNode != null) {						
			if (node.data < treeNode.data) 
				treeNode = treeNode.left;
			else if (node.data > treeNode.data) 
				treeNode = treeNode.right;
		}	
		treeNode = node;		
	}
	
	public void deleteIter(Node node) {
		Node treeNode = root;
		
		while (treeNode.data != node.data) {
			if (treeNode == null)
				break;
			if (node.data < treeNode.data) 
				treeNode = treeNode.left;
			else if (node.data > treeNode.data) 
				treeNode = treeNode.right;
		}
		
		if (treeNode.left == null && treeNode.right == null) 
			treeNode = null;
		else if (treeNode.left != null && treeNode.right != null) {
			Node next = findNextIter(node);
			node.data = next.data;
			deleteIter(next);
		}
		else {
			treeNode = treeNode.left == null ? treeNode.right : treeNode.left;
			treeNode.left = null;
			treeNode.right = null;
		}
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
		
	public Node findPrevIter(Node node) {
		/* if node has left child:
		 * 		find rightmost node in that subtree
		 * else:
		 * 		root where node is right child
		 */
		
		if (node.left != null) {
			node = node.left;
			while (node.right != null) 
				node = node.right;
		}
		else {
			Node parent = findParent(node);
			
			while (parent.left == node) {
				if (parent == null) 
					break;
				node = parent;
				parent = findParent(parent);
			}			
			node = parent;	
		}
		return node;		
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
}
