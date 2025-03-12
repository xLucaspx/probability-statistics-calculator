package probability.central;

import java.math.BigDecimal;

import static probability.Utils.MATH_CONTEXT;

/**
 * Fornece o método {@link #harmonicMean(BigDecimal...)} para realizar o
 * cálculo da média harmônica para um conjunto de valores reais.
 *
 * @author Rodrigo Miotto Slongo
 */
public final class HarmonicMean {
	private HarmonicMean() {}
	/**
	 * Método que calcula a Média Harmônica com um conjunto de {@link BigDecimal}.
	 * @param setOfNumbers a ser calculado Média Harmônica
	 * @return a Média Harmônica referente ao conjunto de {@link BigDecimal}.
	 * @throws ArithmeticException se o conjunto de dados for vazio.
	 */
	public static BigDecimal harmonicMean(BigDecimal... setOfNumbers) {
		Integer elementsQuantity = setOfNumbers.length;

		BigDecimal resultSomatory = inverseNumbersSomatory(setOfNumbers);

		BigDecimal harmonicMean = BigDecimal.valueOf(elementsQuantity).divide(resultSomatory,MATH_CONTEXT);
		// Utiliza este método para remover os zeros restantes resultantes da precisão
		return harmonicMean.stripTrailingZeros();
	}

	/**
	 * Realiza a somatório dos elementos {@code elevados na -1}.
	 * @param setOfNumbers que é utilizado para a soma.
	 * @return o resultado da soma do somatório.
	 */
	private static BigDecimal inverseNumbersSomatory(BigDecimal[] setOfNumbers) {
		BigDecimal sum = BigDecimal.valueOf(0.0);
		BigDecimal fraction = BigDecimal.valueOf(1);
		for (BigDecimal number : setOfNumbers) {
			//  O somatório x^-1.
			sum = sum.add(fraction.divide(number,MATH_CONTEXT));
		}
		return sum;
	}
}
