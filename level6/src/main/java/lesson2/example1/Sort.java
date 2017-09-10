package lesson2.example1;

public class Sort {

	public static void insertionSort(int[] ints) {
		for (int i=0; i<ints.length; i++) {
			int x = ints[i];
			int j = i - 1;
			while (j >= 0 && ints[j] > x) {
				ints[j + 1] = ints[j];
				j--;
			}
			ints[j + 1] = x;
		}
	}

	public static int[] merge(int[] result1, int[] result2) {
		int[] result = new int[result1.length + result2.length];		
		int j = 0, k = 0;

		for (int i=0; i<result.length; i++) {
			if (j >= result1.length) {
				result[i] = result2[k++];
			}
			else if (k >= result2.length) {
				result[i] = result1[j++];
			}
			else if (result1[j] < result2[k]) {
				result[i] = result1[j++];
			}
			else {
				result[i] = result2[k++];
			}
		}
		return result;
	}
}
