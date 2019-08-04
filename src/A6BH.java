/*
 * Add operation counts to all methods - 3pts
 * No other methods/variables should be added/modified
 */
public class A6BH <E extends Comparable<? super E>> {
	private int defaultSize = 4;
	private int count = 0;
	private E[] heap;
	private int opCount = 0;
	
	public int getOpCount()
	{
		return opCount;
	}
	public void resetOpCount()
	{
		opCount = 0;
	}

	public A6BH()
	{
		heap = (E[])new Comparable[defaultSize];
		opCount++;
	}
	public A6BH(int size)
	{

		heap = (E[])new Comparable[this.nextSize(size)];
		opCount++;
	}
	public A6BH(E[] items)
	{
		heap = (E[])new Comparable[this.nextSize(items.length)];
		opCount++;
		this.addAll(items);
		opCount++;
	}

	public void addAll(E[] items)
	{
		//make sure there is room for all new items
		opCount+=2;
		if(count+items.length >= heap.length)
		{
			growArray(this.nextSize(count+items.length));
		}

		opCount+=2;
		for(E item : items)//copy new items in order
		{
			opCount+=2;
			count++;
			opCount++;
			heap[count] = item;

			opCount++;
		}

		this.buildHeap();//fix heap order
	}
	private void buildHeap()
	{
		opCount+=3;
		for(int i = count >> 1; i > 0; i--)
		{
			percolateDown(i);
			opCount+=3;
		}
	}

	public void insert(E val)
	{
		//make sure we have room for new item
		opCount+=2;
		if(count+1 >= heap.length)
		{
			growArray();
		}
		opCount+=2;
		count++;
		opCount++;
		heap[0] = val;//temporary storage
		percolateUp(count);
	}
	private void percolateUp(int pos)
	{
		//pos>>1 = pos/2 - getting to parent of empty space
		opCount+=3;
		for(;heap[pos>>1].compareTo(heap[0]) > 0;pos = pos>>1)
		{
			opCount+=2;
			heap[pos] = heap[pos>>1];//move parent down a level

			opCount+=3;
		}
		heap[pos] = heap[0];//take value from initial insert and put in correct pos
	}

	public E findMin()
	{
		opCount+=2;
		return (count > 0)?heap[1]:null;
	}
	public E deleteMin()//O(1)
	{
		opCount++;
		if(count > 0)
		{
			opCount++;
			E temp = heap[1];

			opCount++;
			heap[1] = heap[count];//moved last value to top
			opCount+=2;
			count--;//decrease size
			percolateDown(1);//move top value down to final position

			opCount++;
			return temp;
		}
		else
		{
			opCount++;
			return null;//no items in heap
		}
	}
	private void percolateDown(int pos) //O(N) = LogN?
	{
		opCount+=2;
		int firstChild = pos << 1;//pos * 2
		opCount++;
		E temp = heap[pos];
		opCount+=3;
		for(;pos<<1 <= count; pos = firstChild, firstChild = pos<<1)
		{
			opCount+=2;
			if(firstChild+1 <= count)//there is a second child
			{
				opCount+=4;
				if(heap[firstChild].compareTo(heap[firstChild+1]) > 0)
				{
					firstChild++;
				}
			}
			//firstChild is now the index of the smaller child
			opCount+=2;
			if(temp.compareTo(heap[firstChild]) > 0)
			{
				opCount++;
				heap[pos] = heap[firstChild];//move child up to parent and continue
			}
			else
			{
				break;//stop loop because we found correct position for temp
			}
		}
		opCount++;
		heap[pos] = temp;
	}

	public String toString()
	{
		opCount++;
		String output = "Heap Size:"+heap.length+"\n";
		opCount+=2;
		for(int i = 1; i <= count; i++)
		{
			opCount+=3;
			output += heap[i]+",";

			opCount+=3;
		}
		return output;
	}

	public boolean isEmpty()
	{
		opCount++;
		return count == 0;
	}
	public void makeEmpty()
	{
		opCount++;
		count = 0;
	}

	private void growArray()//O(N)
	{
		opCount++;
		growArray(heap.length << 1);//heap.length * 2
	}
	private void growArray(int size)
	{
		//new array that is twice as big
		opCount++;
		E[] newArr = (E[])new Comparable[size];
		//Move values to new array
		opCount+=2;
		for(int i = 1; i <= count; i++)//O(N)
		{
			opCount++;
			newArr[i] = heap[i];

			opCount+=3;
		}
		//System.out.println(Arrays.toString(newArr));
		opCount++;
		heap = newArr;//replace small array with new one
	}
	private int nextSize(int size)
	{
		opCount+=3;
		return 1 << (Integer.toBinaryString(size).length() + 1);//2^(number of bits to represent number)
	}
}
