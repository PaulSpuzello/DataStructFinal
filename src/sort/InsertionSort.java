package sort;

public class InsertionSort {
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
