package lesson2.example1;

import java.util.Arrays;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicInteger;

public class SortTask extends RecursiveTask<int[]> {

	private static final long serialVersionUID = 1L;
	public static final AtomicInteger TASK_COUNT = new AtomicInteger(0);

	private int[] ints;
	private int maxElementsToProcess, start, end;

	public SortTask(int maxElementsToProcess, int start, int end, int[] ints) {
		this.maxElementsToProcess = maxElementsToProcess;
		this.start = start;
		this.end = end;
		this.ints = ints;
		TASK_COUNT.incrementAndGet();
	}
	
	@Override
	protected int[] compute() {
		try {
			return recursiveInvocation(start, end);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public int[] recursiveInvocation(int start, int end) {
		if (end - start + 1 > maxElementsToProcess) {
			int half = (start + end + 1) / 2;
			SortTask task = (SortTask) new SortTask(maxElementsToProcess, 
													half + 1, end, ints).fork();
			int[] result1 = recursiveInvocation(start, half);			
			int[] result2 = task.join();
			return Sort.merge(result1, result2);
		} else {
			int[] result = Arrays.copyOfRange(ints, start, end + 1);
			Sort.insertionSort(result);
			return result;
		}
	}
}
