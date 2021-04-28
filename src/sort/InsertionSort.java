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
		/*
		int n = arr.length;
		
		for (int i = 1; i < n; ++i) {
			int key = arr[i];
			int j = i - 1;
			
			while (j >= 0 && arr[j] > key) {
				arr[j + 1] = arr[j];
				j = j - 1;
			}
			arr[j + 1] = key;
		}*/
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
