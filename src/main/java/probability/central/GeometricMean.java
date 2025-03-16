package probability.central;


import java.math.BigDecimal;
import java.util.Arrays;

import static probability.Utils.MATH_CONTEXT;

/**
 * Fornece o método {@link #geometricMean(BigDecimal...)} para realizar o
 * cálculo da média geométrica para um conjunto de valores reais.
 *
 * @author Rodrigo Miotto Slongo
 */
public final class GeometricMean {

	private GeometricMean() {
	}

	/**
	 * Realiza o cálculo da média geométrica do conjunto de valores passado
	 * como argumento.
	 *
	 * @param values Conjunto sobre o qual ocorrerá o cálculo; não pode ser vazio.
	 * @return Valor correspondente à média geométrica do conjunto.
	 * @throws ArithmeticException Se o conjunto de dados informado for vazio ou se
	 *                             o conjunto contiver valores não positivos.
	 */
	public static BigDecimal geometricMean(BigDecimal... values) {
		if (Arrays.stream(values).anyMatch(x -> x.compareTo(BigDecimal.valueOf(0.0)) <= 0)) {
			throw new ArithmeticException("A média geométrica só é definida para números reais positivos");
		}

		BigDecimal multiply = BigDecimal.valueOf(1.0);
		BigDecimal length = BigDecimal.valueOf(values.length);

		for (BigDecimal number : values) {
			multiply = multiply.multiply(number);
		}

		double oneOverN = BigDecimal.valueOf(1.0).divide(length, MATH_CONTEXT).doubleValue();
		double geometricMean = Math.pow(multiply.doubleValue(), oneOverN);

		return new BigDecimal(String.valueOf(geometricMean), MATH_CONTEXT).stripTrailingZeros();
	}
}
