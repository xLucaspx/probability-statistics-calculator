package probability.dispersion;

import java.math.BigDecimal;

import static probability.Utils.MATH_CONTEXT;

/**
 * Fornece o método {@link #standardDeviation(BigDecimal...)} para realizar o
 * cálculo do desvio padrão para um conjunto de valores reais.
 *
 * @author Rodrigo Miotto Slongo
 */
public class StandardDeviation {

	/**
	 * Realiza o cálculo do desvio padrão do conjunto de valores passado
	 * como argumento.
	 *
	 * @param values Conjunto sobre o qual ocorrerá o cálculo; não pode ser vazio.
	 * @return Valor correspondente à variância do conjunto.
	 * @throws ArithmeticException Se o conjunto de dados informado for vazio ou de 1 elemento.
	 */
	public static BigDecimal standardDeviation(BigDecimal... values) {
		return Variance.variance(values).sqrt(MATH_CONTEXT).stripTrailingZeros();
	}
}
