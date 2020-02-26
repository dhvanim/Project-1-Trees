public class BSTsort {

	class Node {
		public Node(int data) {
			this.data = data;
		}	
		int data;
		Node left;
		Node right;
	}
	Node root;
	
	public int[] sort(int[] arr) {	
		root = new Node(arr[0]);
		for (int i = 1; i < arr.length; i++) 
			insert(arr[i]);
		
		int[] sortedArr = new int[arr.length];
		inOrder(sortedArr, root, 0);
		
		return sortedArr;
	}
	
	public void inOrder(int[] arr, Node node, int i) {
		
		if (node == null)
			return;	
		System.out.println(node.data);
		
		inOrder(arr, node.left, i);
		arr[i++] = node.data;
		inOrder(arr, node.right, i);	
	}
	
	public void insert(int data) {
		insert(data, root);
	}
	
	private void insert(int data, Node node) {
		if (node == null) {
			node = new Node(data);
			return;
		}	
		int nodeData = node.data;
		
		if (data > nodeData) {
			insert(data, node.right);	
		}
		else if (data < nodeData) 
			insert(data, node.left);
	}
}
