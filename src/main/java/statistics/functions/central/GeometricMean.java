package statistics.functions.central;


import java.math.BigDecimal;
import java.util.Arrays;

import static statistics.Utils.MATH_CONTEXT;
import static statistics.Utils.containsNonPositiveValues;

/**
 * Fornece o método {@link #geometricMean(BigDecimal...)} para realizar o
 * cálculo da média geométrica para um conjunto de valores reais.
 *
 * @author Lucas da Paz
 * @author Rodrigo Miotto Slongo
 */
public final class GeometricMean {

	private GeometricMean() {
	}

	/**
	 * Realiza o cálculo da média geométrica do conjunto de valores passado
	 * como argumento. Utiliza o método da soma de logaritmos para evitar estouro
	 * de representação ao multiplicar vários valores.
	 *
	 * @param values Conjunto sobre o qual ocorrerá o cálculo; não pode ser vazio.
	 * @return Valor correspondente à média geométrica do conjunto.
	 * @throws ArithmeticException Se o conjunto de dados informado for vazio ou se
	 *                             o conjunto contiver valores não positivos.
	 */
	public static BigDecimal geometricMean(BigDecimal... values) {
		if (containsNonPositiveValues(values)) {
			throw new ArithmeticException("A média geométrica só é definida para números reais positivos");
		}

		BigDecimal logSum = Arrays.stream(values)
			.map(v -> BigDecimal.valueOf(Math.log(v.doubleValue())))
			.reduce(BigDecimal.valueOf(0.0), BigDecimal::add);

		BigDecimal meanLog = logSum.divide(BigDecimal.valueOf(values.length), MATH_CONTEXT);
		double result = Math.exp(meanLog.doubleValue());
		return BigDecimal.valueOf(result).stripTrailingZeros();
	}
}
