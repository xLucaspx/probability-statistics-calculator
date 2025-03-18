package statistics.functions.central;

import java.math.BigDecimal;
import java.util.Arrays;

import static statistics.Utils.MATH_CONTEXT;
import static statistics.Utils.containsNonPositiveValues;

/**
 * Fornece o método {@link #harmonicMean(BigDecimal...)} para realizar o
 * cálculo da média harmônica para um conjunto de valores reais.
 *
 * @author Rodrigo Miotto Slongo
 */
public final class HarmonicMean {

	private HarmonicMean() {
	}

	/**
	 * Realiza o cálculo da média harmônica do conjunto de valores passado
	 * como argumento.
	 *
	 * @param values Conjunto sobre o qual ocorrerá o cálculo; não pode ser vazio.
	 * @return Valor correspondente à média harmônica do conjunto.
	 * @throws ArithmeticException Se o conjunto de dados informado for vazio.
	 */
	public static BigDecimal harmonicMean(BigDecimal... values) {
		if (containsNonPositiveValues(values)) {
			throw new ArithmeticException("A média harmônica só é definida para números reais positivos");
		}

		BigDecimal length = BigDecimal.valueOf(values.length);

		BigDecimal summationResult = inverseNumbersSummation(values);

		return length.divide(summationResult, MATH_CONTEXT).stripTrailingZeros();
	}

	/**
	 * Realiza a somatório dos elementos na potência {@code -1}; equivalente à
	 * &sum; {@code 1 / values[i]};
	 *
	 * @param values Conjunto sobre o qual ocorrerá o cálculo.
	 * @return O resultado da soma do somatório.
	 */
	private static BigDecimal inverseNumbersSummation(BigDecimal[] values) {
		return Arrays.stream(values).reduce(BigDecimal.valueOf(0.0), HarmonicMean::addFraction);
	}

	/**
	 * Adiciona o valor de {@code 1/x} ao valor de {@code sum}.
	 *
	 * @param sum O valor do somatório.
	 * @param x   Elemento sendo adicionado.
	 * @return Resultado de {@code sum + 1/x}.
	 */
	private static BigDecimal addFraction(BigDecimal sum, BigDecimal x) {
		return sum.add(BigDecimal.valueOf(1).divide(x, MATH_CONTEXT));
	}
}

