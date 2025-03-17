package statistics;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Fornece métodos utilitários para a manipulação de dados.
 *
 * @author Lucas da Paz
 */
public final class Utils {

	/**
	 * {@link MathContext} utilizado para divisões referentes ao método
	 * {@link BigDecimal#divide(BigDecimal, MathContext)}; define precisão de 15
	 * valores significativos e {@link RoundingMode#HALF_UP}.
	 */
	public static final MathContext MATH_CONTEXT = new MathContext(15, RoundingMode.HALF_UP);

	private Utils() {
	}

	/**
	 * Retorna um array de {@link BigDecimal} contendo os mesmos elementos do array
	 * de {@link Number} passado como argumento, porém convertidos para o novo formato.
	 *
	 * @param values O array de referência.
	 * @return Novo array contendo os mesmos elementos, convertidos para {@link BigDecimal}.
	 * @throws IllegalArgumentException Se o conjunto de dados informado for nulo.
	 * @throws NullPointerException     Se o conjunto de dados informado contiver
	 *                                  valores nulos.
	 */
	public static <T extends Number> BigDecimal[] toBigDecimalArray(T... values) {
		return Arrays.stream(values)
			.map(n -> new BigDecimal(n.toString()))
			.toArray(BigDecimal[]::new);
	}

	/**
	 * Retorna um array de {@code double} contendo os mesmos elementos do array
	 * de {@link Number} passado como argumento, porém convertidos para o novo formato.
	 *
	 * @param values O array de referência.
	 * @return Novo array contendo os mesmos elementos, convertidos para {@code double}.
	 * @throws IllegalArgumentException Se o conjunto de dados informado for nulo.
	 * @throws NullPointerException     Se o conjunto de dados informado contiver
	 *                                  valores nulos.
	 */
	public static <T extends Number> double[] toDoubleArray(T... values) {
		return Arrays.stream(values).mapToDouble(Number::doubleValue).toArray();
	}

	/**
	 * Verifica se o conjunto de valores passados como argumento contém algum
	 * número não positivo, i.e., menor ou igual a zero.
	 *
	 * @param values O array de referência.
	 * @return {@code true} caso o array contenha pelo menos um valor menor ou
	 * igual a zero, {@code false} caso contrário.
	 */
	public static boolean containsNonPositiveValues(BigDecimal... values) {
		return Arrays.stream(values).anyMatch(x -> x.compareTo(BigDecimal.valueOf(0.0)) <= 0);
	}

	/**
	 * Encontra o maior valor em um conjunto de dados.
	 *
	 * @param values Array de referência.
	 * @return Maior valor encontrado no array, ou {@code null} caso
	 * o array seja nulo ou não contenha elementos.
	 * @throws IllegalArgumentException Se o conjunto de dados informado for nulo.
	 * @throws NullPointerException     Se o conjunto de dados informado contiver
	 *                                  valores nulos.
	 */
	public static BigDecimal max(BigDecimal... values) {
		return Arrays.stream(values).max(Comparator.naturalOrder()).orElse(null);
	}

	/**
	 * Encontra o menor valor em um conjunto de dados.
	 *
	 * @param values Array de referência.
	 * @return Menor valor encontrado no array, ou {@code null} caso
	 * o array seja nulo ou não contenha elementos.
	 * @throws IllegalArgumentException Se o conjunto de dados informado for nulo.
	 * @throws NullPointerException     Se o conjunto de dados informado contiver
	 *                                  valores nulos.
	 */
	public static BigDecimal min(BigDecimal... values) {
		return Arrays.stream(values).min(Comparator.naturalOrder()).orElse(null);
	}

	/**
	 * Ordena o conteúdo do array utilizando o algoritmo merge sort.
	 *
	 * @throws NullPointerException Se o array passado como argumento for nulo
	 *                              ou contiver valores nulos.
	 */
	public static BigDecimal[] sort(BigDecimal... values) {
		BigDecimal[] sorted = Arrays.copyOf(values, values.length);
		mergeSort(sorted, sorted.length);
		return sorted;
	}

	/**
	 * @param data  O array a ser ordenado (referência).
	 * @param count Quantidade de elementos deste array.
	 */
	private static void mergeSort(BigDecimal[] data, Integer count) {
		if (count <= 1) {
			return;
		}

		int half = count / 2;
		BigDecimal[] sub1 = new BigDecimal[half];
		int sub2Size = count - half;
		BigDecimal[] sub2 = new BigDecimal[sub2Size];

		System.arraycopy(data, 0, sub1, 0, half);
		System.arraycopy(data, half, sub2, 0, sub2Size);

		mergeSort(sub1, half);
		mergeSort(sub2, sub2Size);

		int i = 0; // int i -> sub1 index
		int j = 0; // int j -> sub2 index
		int k = 0; // int k -> data index

		while (i < half && j < sub2Size) {
			if (sub1[i].compareTo(sub2[j]) <= 0) {
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
