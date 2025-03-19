package statistics.functions.central;

import java.math.BigDecimal;

import static statistics.Utils.MATH_CONTEXT;

/**
 * Fornece o método {@link #arithmeticMean(BigDecimal...)} para realizar o
 * cálculo da média aritmética para um conjunto de valores reais.
 *
 * @author Rodrigo Miotto Slongo
 */
public final class ArithmeticMean {

	private ArithmeticMean() {
	}

	/**
	 * Realiza o cálculo da média aritmética do conjunto de valores passado
	 * como argumento.
	 *
	 * @param values Conjunto sobre o qual ocorrerá o cálculo; não pode ser vazio.
	 * @return Valor correspondente à média aritmética do conjunto.
	 * @throws ArithmeticException Se o conjunto de dados informado for vazio.
	 */
	public static BigDecimal arithmeticMean(BigDecimal... values) {
		BigDecimal sum = BigDecimal.valueOf(0.0);
		BigDecimal length = BigDecimal.valueOf(values.length);

		for (BigDecimal number : values) {
			sum = sum.add(number);
		}

		return sum.divide(length, MATH_CONTEXT).stripTrailingZeros();
	}
}
