import java.util.Random;

public class A6Work {
	private static Random randGen = new Random();
	/*
	 * Add operation counts to all methods - 3pts
	 */
	
	/*
	 * Grading: 
	 * Create array of N random values: 0.5pt
	 * Create a heap from the values in the array in the most efficient method: 1pt
	 * Remove the k smallest values from the heap: 0.5pt
	 * Return the kth smallest value from the heap: 0.5pt
	 * f(N): 0.75pt
	 * O(N): 0.75pt
	 */
	@SuppressWarnings("Duplicates")
	public static int kthSmallest(int N, int k)
	{
		/*
		 * Create an Array of size N
		 * Fill the array with N random values between 0 and N (duplicates is fine)
		 * Create a heap from the array, ensure this is done in the most efficient way possible
		 * Perform k deleteMin() operations
		 * Return the final value returned from deleteMin() as the kth smallest
		 * When calculating f(N)/O(N), ensure you mention the BigO notation for each heap method used
		 * - You can use the BigO reduction from the heap methods in your f(N)
		 */
		//randGen.nextInt(N);//count as 1 unit of time
		int opCount = 0;

		opCount++; //1 initialize
		Integer[] array = new Integer[N];//1 - Declaration

		//2 loop start
		opCount+=2;
		for (int i = 0; i < N; i++) { //4N
			opCount++; //1 Operation
			array[i] = randGen.nextInt(N);

			opCount+=3;
		}

		opCount++; //1 initialize
		A6BH foo = new A6BH(array);

		opCount+=2; //2 loop start
		for (int i = 0; i < k-1; i++) {//4N(Log(N))
			opCount++;
			foo.deleteMin();//O(NLogN)

			opCount+=3;
		}

		opCount++;
		//4N^2 + 4N + 9
		System.out.println("f(N) = 4NLogN + 4N + 9");
		opCount++;
		System.out.println("O(N) = N(Log(N))");
		opCount++;
		return (int) foo.deleteMin();
	}
	/*
	 * Grading: 
	 * Create array of N random values: 0.5pt
	 * Create a heap of first k values: 1pt
	 * Correctly maintain heap size while adding/removing values: 1pt
	 * Return the kth largest value from the heap: 0.5pt
	 * f(N): 0.5pt
	 * O(N): 0.5pt
	 */
	@SuppressWarnings("Duplicates")
	public static int kthLargest(int N, int k)
	{
		/*
		 * Create an Array of size N
		 * Fill the array with N random values between 0 and N (duplicates is fine)
		 * Create a heap from the first k values in the array
		 * For all other values in the array
		 * - if the value is larger than the smallest in the heap, remove the smallest from the heap and add the larger value
		 * - if the value is smaller than the smallest in the heap, continue to the next value
		 * Return the smallest value in the heap as the kth largest
		 * When calculating f(N)/O(N), ensure you mention the BigO notation for each heap method used
		 * - You can use the BigO reduction from the heap methods in your f(N)
		 */
		//randGen.nextInt(N);//count as 1 unit of time
		int opCount = 0;


		opCount++; // 1 initialize
		Integer[] array = new Integer[N];//1 - Declaration

		//2 loop start
		opCount+=2;
		for (int i = 0; i < N; i++) {//4N
			opCount++;
			array[i] = randGen.nextInt(N);

			opCount+=3;
		}

		opCount++;
		A6BH foo = new A6BH(N);

		opCount+=2;
		for (int i = 0; i < k; i++) { //4NLogN
			opCount++;
			foo.insert(array[i]);//O(NLogN)

			opCount+=3;
		}

		opCount+=2;
		for (int i = k; i < N; i++) {//8N(2(Log(N))) === 16N(Log(N))
			opCount+=3;
			if(foo.findMin().compareTo(array[i]) < 0){
				opCount++;
				foo.deleteMin();//O(NLogN)
				opCount++;
				foo.insert(array[i]);//O(NLogN)
			}
			opCount+=3;
		}

		opCount++;
		System.out.println("f(N) = 20N(Log(N)) + 4N + 11");
		opCount++;
		System.out.println("O(N) = N(Log(N))");
		opCount++;
		return (int) foo.deleteMin();
	}
	
}
