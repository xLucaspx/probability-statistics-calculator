package probability;

import java.util.Arrays;

/**
 * Fornece métodos utilitários para a manipulação de dados.
 *
 * @author Lucas da Paz
 */
public class Utils {

	/**
	 * Ordena o conteúdo do array utilizando o algoritmo merge sort.
	 */
	public static Double[] sort(Double... values) {
		Double[] sorted = Arrays.copyOf(values, values.length);
		mergeSort(sorted, sorted.length);
		return sorted;
	}

	/**
	 * @param data  O array a ser ordenado (referência).
	 * @param count Quantidade de elementos deste array.
	 */
	private static void mergeSort(Double[] data, Integer count) {
		if (count <= 1) {
			return;
		}

		int half = count / 2;
		Double[] sub1 = new Double[half];
		int sub2Size = count - half;
		Double[] sub2 = new Double[sub2Size];

		System.arraycopy(data, 0, sub1, 0, half);
		System.arraycopy(data, half, sub2, 0, sub2Size);

		mergeSort(sub1, half);
		mergeSort(sub2, sub2Size);

		int i = 0; // int i -> sub1 index
		int j = 0; // int j -> sub2 index
		int k = 0; // int k -> data index

		while (i < half && j < sub2Size) {
			if (sub1[i] <= sub2[j]) {
				data[k++] = sub1[i++];
				continue;
			}
			data[k++] = sub2[j++];
		}

		while (i < half) {
			data[k++] = sub1[i++];
		}

		while (j < sub2Size) {
			data[k++] = sub2[j++];
		}
	}
}
