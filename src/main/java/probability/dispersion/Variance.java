package probability.dispersion;

import probability.central.ArithmeticMean;
import java.math.BigDecimal;
import java.util.Arrays;

import static probability.Utils.MATH_CONTEXT;

/**
 * Fornece o método {@link #variance(BigDecimal...)} para realizar o
 * cálculo da média aritmética para um conjunto de valores reais.
 *
 * @author Rodrigo Miotto Slongo
 */
public class Variance {
	private static BigDecimal arithmeticMean;
	/**
	 * Realiza o cálculo da variância do conjunto de valores passado
	 * como argumento.
	 *
	 * @param values Conjunto sobre o qual ocorrerá o cálculo; não pode ser vazio.
	 * @return Valor correspondente à variância do conjunto.
	 * @throws ArithmeticException Se o conjunto de dados informado for vazio ou de 1 elemento.
	 */
	public static BigDecimal variance(BigDecimal... values) {
		arithmeticMean = ArithmeticMean.arithmeticMean(values);

		BigDecimal resultSomatory = arithmeticMeanSomatory(values);
		BigDecimal length = BigDecimal.valueOf(values.length -1);

		return resultSomatory.divide(length, MATH_CONTEXT).stripTrailingZeros();
	}

	/**
	 * Realiza a somatório dos elementos na do conjunto de dados
	 * - {@link ArithmeticMean#arithmeticMean(BigDecimal...)}; equivalente à
	 * &sum; {@code (Xi - média aritmética)ˆ2};
	 *
	 * @param values Conjunto sobre o qual ocorrerá o cálculo.
	 * @return O resultado da soma do somatório.
	 */
	private static BigDecimal arithmeticMeanSomatory(BigDecimal[] values) {
		return Arrays.stream(values)
				.map(e -> e.subtract(arithmeticMean).pow(2))
				.reduce(BigDecimal.ZERO,BigDecimal::add);
	}

}

