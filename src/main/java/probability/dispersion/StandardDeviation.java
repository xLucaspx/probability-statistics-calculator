package probability.dispersion;

import probability.Utils;

import java.math.BigDecimal;
import java.math.MathContext;

import static probability.Utils.MATH_CONTEXT;

/**
 * Fornece os métodos {@link #populationStandardDeviation} e
 * {@link #sampleStandardDeviation} para realizar o cálculo do
 * desvio padrão para um conjunto de valores reais.
 *
 * @author Rodrigo Miotto Slongo
 */
public final class StandardDeviation {

	private StandardDeviation() {
	}

	/**
	 * Realiza o cálculo do desvio padrão da população para conjunto de valores
	 * passado como argumento.
	 *
	 * @param values Conjunto sobre o qual ocorrerá o cálculo; não pode ser vazio.
	 * @return Valor correspondente ao desvio padrão populacional do conjunto.
	 * @see Variance#populationVariance(BigDecimal...)
	 */
	public static BigDecimal populationStandardDeviation(BigDecimal... values) {
		return standardDeviation(Variance.populationVariance(values));
	}

	/**
	 * Realiza o cálculo do desvio padrão da amostra para o conjunto de valores
	 * passado como argumento.
	 *
	 * @param values Conjunto sobre o qual ocorrerá o cálculo; não pode ser vazio.
	 * @return Valor correspondente ao desvio padrão amostral do conjunto.
	 * @see Variance#sampleVariance(BigDecimal...)
	 */
	public static BigDecimal sampleStandardDeviation(BigDecimal... values) {
		return standardDeviation(Variance.sampleVariance(values));
	}

	/**
	 * Realiza o cálculo do desvio padrão para a variância passada como argumento.
	 * Este método retorna o valor de {@link BigDecimal#sqrt(MathContext)} utilizando
	 * o {@link Utils#MATH_CONTEXT MathContext} da aplicação e chamando
	 * {@link BigDecimal#stripTrailingZeros()}.
	 *
	 * @param variance Valor sobre o qual ocorrerá o cálculo.
	 * @return Valor correspondente ao desvio padrão da variância informada.
	 */
	private static BigDecimal standardDeviation(BigDecimal variance) {
		return variance.sqrt(MATH_CONTEXT).stripTrailingZeros();
	}
}
