import java.util.Arrays;
import java.util.Random;

public class A6Driver {

	public static void main(String[] args) {
		System.out.println("kth Smallest:");
		System.out.println(A6Work.kthSmallest(10, 5));
		System.out.println(A6Work.kthSmallest(10, 3));
		System.out.println(A6Work.kthSmallest(100, 95));
		System.out.println();
		System.out.println("kth Largest:");
		System.out.println(A6Work.kthLargest(10, 5));
		System.out.println(A6Work.kthLargest(10, 3));
		System.out.println(A6Work.kthLargest(100, 95));
		System.out.println();
		
		Random randGen = new Random();
		randGen.nextInt(10);//better timing values
		long time = System.nanoTime();//better timing values
		A6BH<Integer> heap = new A6BH<>();
		heap.insert(5);//better timing values
		int testCases = 7;
		for(int i = 1; i <= testCases; i++)
		{
			System.out.println("Test "+i);
			int N = (int)Math.pow(10, i);
			Integer[] randoms = new Integer[N];
			time = System.nanoTime();
			for(int j = 0; j < randoms.length; j++)
			{
				randoms[j] = randGen.nextInt(N)+1;
			}
			time = System.nanoTime() - time;
			System.out.println("Generate "+N+" Randoms: "+(time/1000000000.0)+ " seconds");
			time = System.nanoTime();
			heap = new A6BH<>(randoms);
			time = System.nanoTime() - time;
			System.out.println("Bulk insert: " + (time/1000000000.0)+" seconds");
			System.out.println("Bulk insert: " + heap.getOpCount()+" operations");
			time = System.nanoTime();
			heap = new A6BH<>();
			for(int j = 0; j < randoms.length; j++)
			{
				heap.insert(randoms[j]);
			}
			time = System.nanoTime() - time;
			System.out.println("Individual insert: " + (time/1000000000.0)+" seconds");
			System.out.println("Individual insert: " + heap.getOpCount()+" operations");
			System.out.println();
		}
	}

}
