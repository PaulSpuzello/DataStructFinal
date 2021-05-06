package sort;

public class InsertionSort {

	// Go through the array and sort by ascending
	public void sort(String[] arr) {

		int i, j;
		String key = "";

		for (j = 1; j < arr.length; j++) {
			key = arr[j];
			i = j - 1;

			while (i >= 0) {
				if (key.compareTo(arr[i]) > 0) {
					break;
				}
				arr[i + 1] = arr[i];
				i--;
			}
			arr[i + 1] = key;
		}
	}

	// Go through the array and sort by d
	public void sortDesc(String[] arr) {
		int i, j;
		String key = "";

		for (j = 1; j < arr.length; j++) {
			key = arr[j];
			i = j - 1;

			while (i >= 0) {
				if (key.compareTo(arr[i]) < 0) {
					break;
				}
				arr[i + 1] = arr[i];
				i--;
			}
			arr[i + 1] = key;
		}
	}
}
