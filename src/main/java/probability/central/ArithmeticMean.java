package probability.central;

import java.math.BigDecimal;

import static probability.Utils.MATH_CONTEXT;

/**
 * Fornece o método {@link #arithmeticMean(BigDecimal...)}para realizar o
 * cálculo da média aritmética para um conjunto de valores reais.
 *
 * @author Rodrigo Miotto Slongo
 */
public final class ArithmeticMean {
	private ArithmeticMean() {}
	/**
	 * Método que calcula a {@code Média Aritmética} com um conjunto de {@link Number}.
	 * @param setOfNumbers a ser calculado a {@code Média Aritmética}.
	 * @return a {@code Média Aritmética} referente ao conjunto de {@link Number}.
	 * @throws ArithmeticException se o conjunto de dados for vazio.
	 */
	public static BigDecimal arithmeticMean(BigDecimal... setOfNumbers) {
		BigDecimal sum = BigDecimal.valueOf(0);
		Integer elementsQuantity = setOfNumbers.length;
		for (BigDecimal number : setOfNumbers) {
			// Realiza a soma de todos os elementos da lista/conjunto de dados.
			sum = sum.add(number);
		}

		BigDecimal arithmeticMean = sum.divide(BigDecimal.valueOf(elementsQuantity),MATH_CONTEXT);

		return arithmeticMean.stripTrailingZeros();
	}
}
