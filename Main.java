/// import java.util.concurrent.*;

public class Main {

	public static void main(String[] args) {
		
		AVLTreesIter avlTreeI = new AVLTreesIter();
		AVLTreesRec avlTreeR = new AVLTreesRec();
		BinarySearchTreeIter bstTreeI = new BinarySearchTreeIter();
		BinarySearchTreeRec bstTreeR = new BinarySearchTreeRec();
		
		IntegerArrays arrays = new IntegerArrays();
		
		int[] array = arrays.getRandomArray(100000);
		int[] sorted = arrays.getSortedArray(100000);
		
		
		/// CREATING ITERATIVE TREES
		long aStart = System.nanoTime();
		for (int x : array) {
			avlTreeI.insertIter(x);
		}
		long aEnd = System.nanoTime();
		
		long bStart = System.nanoTime();
		for (int x : array) {
			bstTreeI.insertIter(x);
		}
		long bEnd = System.nanoTime();
		
		
		/// CREATING RECURSIVE TREES
		long cStart = System.nanoTime();
		for (int x : array) {
			avlTreeR.insertRec(x);
		}
		long cEnd = System.nanoTime();
		
		long dStart = System.nanoTime();
		for (int x : array) {
			bstTreeR.insertRec(x);
		}
		long dEnd = System.nanoTime();
		
		AVLTreesIter avlTreeI2 = new AVLTreesIter();
		BinarySearchTreeIter bstTreeI2 = new BinarySearchTreeIter();
		
		/// SORTED ARRAY
		for (int x : sorted) 
			avlTreeI2.insertIter(x);
		for (int x : sorted)
			bstTreeI2.insertIter(x);

		
		double aDiff = ((aEnd - aStart) / (double) 1000000.0);
		double bDiff = ((bEnd - bStart) / (double) 1000000.0);
		double cDiff = ((cEnd - cStart) / (double) 1000000.0);
		double dDiff = ((dEnd - dStart) / (double) 1000000.0);
		
		
		System.out.println("INSERTING ARRAY OF N INTEGERS");
		System.out.println("BST ITERATIVE: " + bDiff + " ms");
		System.out.println("BST RECURSIVE: " + dDiff + " ms");
		System.out.println("AVL ITERATIVE: " + aDiff + " ms");
		System.out.println("AVL RECURSIVE: " + cDiff + " ms");
		
		System.out.println("");
		
		System.out.println("NUMBER OF TRAVERSES DOWN ONE LEVEL: (iterative)");
		System.out.println("BST: " + bstTreeI.counter);
		System.out.println("AVL: " + avlTreeI.counter);
		
		System.out.println("S BST: " + bstTreeI2.counter);
		System.out.println("S AVL: " + avlTreeI2.counter);

		
	}
	

}
