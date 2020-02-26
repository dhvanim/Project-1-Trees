import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IntegerArrays {
	
	public int[] getRandomArray(int n) {
		int[] array = new int[n];
		List<Integer> used = new ArrayList<Integer>(n);
		Random random = new Random();
		
		for (int i = 0; i < n; i++) {
			int num;
			do {
				num = random.nextInt();
			} while(used.contains(num));			
			array[i] = num;
			used.add(num);	
		}
		return array;
	}
	
	public int[] getSortedArray(int n) {
		int[] array = new int[n];
		
		for (int i = 0; i < n; i++) 
			array[i] = n - i;	
		return array;
	}
}
