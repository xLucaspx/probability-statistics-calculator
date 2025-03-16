package probability.dispersion;

import java.math.BigDecimal;

import static probability.Utils.MATH_CONTEXT;

/**
 * Fornece o método {@link #populationStandardDeviation(BigDecimal...)} e
 * {@link #sampleStandardDeviation(BigDecimal...)} para realizar o
 * cálculo do desvio padrão para um conjunto de valores reais.
 *
 * @author Rodrigo Miotto Slongo
 */
public class StandardDeviation {

	/**
	 * Realiza o cálculo do desvio padrão amostral do conjunto de valores passado
	 * como argumento.
	 *
	 * @param values Conjunto sobre o qual ocorrerá o cálculo; não pode ser vazio.
	 * @return Valor correspondente à variância do conjunto.
	 * @throws ArithmeticException Se o conjunto de dados informado for vazio ou de 1 elemento.
	 */
	public static BigDecimal sampleStandardDeviation(BigDecimal... values) {
		return standardDeviation(Variance.sampleVariance(values));
	}

	/**
	 * Realiza o cálculo do desvio padrão amostral do conjunto de valores passado
	 * como argumento.
	 *
	 * @param values Conjunto sobre o qual ocorrerá o cálculo; não pode ser vazio.
	 * @return Valor correspondente à variância do conjunto.
	 * @throws ArithmeticException Se o conjunto de dados informado for vazio.
	 */
	public static BigDecimal populationStandardDeviation(BigDecimal... values) {
		return standardDeviation(Variance.populationVariance(values));
	}

	/**
	 * Realiza o cálculo do desvio padrão do valor passado como argumento
	 * utilizando o parâmetro {@code value} informado.
	 *
	 * @param value Valor sobre o qual ocorrerá o cálculo; não pode ser vazio.
	 * @return Valor correspondente ao desvio padrão.
	 */
	public static BigDecimal standardDeviation(BigDecimal value) {
		return value.sqrt(MATH_CONTEXT).stripTrailingZeros();
	}
}