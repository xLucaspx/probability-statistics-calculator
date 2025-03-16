package probability.central;

import java.math.BigDecimal;
import java.util.Arrays;

import static probability.Utils.MATH_CONTEXT;

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
		if (Arrays.stream(values).anyMatch(x -> x.compareTo(BigDecimal.valueOf(0.0)) <= 0)) {
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
		return Arrays.stream(values)
			.filter(v -> !(v.compareTo(BigDecimal.valueOf(0.0)) == 0))
			.reduce(BigDecimal.valueOf(0.0), HarmonicMean::addFraction);
	}

	private static BigDecimal addFraction(BigDecimal sum, BigDecimal n) {
		return sum.add(BigDecimal.valueOf(1).divide(n, MATH_CONTEXT));
	}
}
