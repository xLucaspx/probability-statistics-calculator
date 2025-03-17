package statistics.functions.central;

import java.math.BigDecimal;

import static statistics.Utils.sort;
import static statistics.functions.central.ArithmeticMean.arithmeticMean;

/**
 * Fornece o método {@link #median(BigDecimal...)} para realizar
 * o cálculo da mediana para um conjunto de valores reais.
 *
 * @author Lucas da Paz
 */
public final class Median {

	private Median() {
	}

	/**
	 * Realiza o cálculo da mediana do conjunto de valores passado
	 * como argumento.
	 *
	 * @param values Conjunto sobre o qual ocorrerá o cálculo; não pode ser vazio.
	 * @return Valor correspondente à mediana do conjunto.
	 * @throws IndexOutOfBoundsException Se o conjunto de dados informado for vazio.
	 * @throws NullPointerException      Se o conjunto de dados informado for nulo ou
	 *                                   contiver valores nulos.
	 */
	public static BigDecimal median(BigDecimal... values) {
		int length = values.length;

		BigDecimal[] sorted = sort(values);

		if (length % 2 == 0) {
			return medianEven(sorted).stripTrailingZeros();
		}
		return medianOdd(sorted).stripTrailingZeros();
	}

	/**
	 * Calcula a mediana de um conjunto de valores reais ordenados com número par
	 * de elementos.
	 * <p>
	 * Dado que arrays, em Java, começam na posição 0, e que o valor de
	 * {@code int i = values.length / 2} é o quociente inteiro da divisão, retorna a média
	 * dos valores nas posições {@code i} e {@code i - 1} do array.
	 * <p>
	 * E.g.: Se o tamanho do array for {@code 8}, {@code i} será o quociente inteiro
	 * de {@code 8 / 2}, i.e., {@code 4}, que representa o valor na quinta posição do
	 * array, equivalente ao cálculo matemático {@code n / 2 + 1}; logo, para completar
	 * o cálculo da média utiliza-se o valor na posição {@code i - 1}, que representa o
	 * valor na quarta posição ({@code n / 2}).
	 *
	 * @param values Conjunto de valores reais ordenados com número par de elementos.
	 * @return A mediana para o conjunto passado como argumento.
	 */
	private static BigDecimal medianEven(BigDecimal... values) {
		int i = values.length / 2;
		BigDecimal n1 = values[i - 1];
		BigDecimal n2 = values[i];
		return arithmeticMean(n1, n2);
	}

	/**
	 * Calcula a mediana de um conjunto de valores reais ordenados com número ímpar
	 * de elementos.
	 * <p>
	 * Dado que arrays, em Java, começam na posição 0, e que o valor de
	 * {@code int i = values.length / 2} é o quociente inteiro da divisão, retorna
	 * o valor na posição {@code i} do array.
	 * <p>
	 * E.g.: Se o tamanho do array for {@code 15}, {@code i} será o quociente inteiro
	 * de {@code 15 / 2}, i.e., {@code 7}, que representa o valor na oitava posição do
	 * array, equivalente ao cálculo matemático {@code (n + 1) / 2}.
	 *
	 * @param values Conjunto de valores reais ordenados com número ímpar de elementos.
	 * @return A mediana para o conjunto passado como argumento.
	 */
	private static BigDecimal medianOdd(BigDecimal... values) {
		int i = values.length / 2;
		return values[i];
	}
}
