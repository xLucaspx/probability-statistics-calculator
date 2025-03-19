package statistics.calculator;

import statistics.functions.dispersion.Outliers;
import statistics.functions.dispersion.Quartiles;

import java.math.BigDecimal;

/**
 * Interface utilizada para generalizar implementações
 * de funções estatísticas.
 *
 * @author Lucas da Paz
 */
public interface StatisticsCalculator {

	/**
	 * Calcula a média aritmética.
	 *
	 * @param values Conjunto de valores.
	 * @return Média aritmética para o conjunto.
	 */
	BigDecimal arithmeticMean(BigDecimal... values);

	/**
	 * Calcula a média geométrica.
	 *
	 * @param values Conjunto de valores positivos.
	 * @return Média geométrica do conjunto.
	 */
	BigDecimal geometricMean(BigDecimal... values);

	/**
	 * Calcula a média harmônica.
	 *
	 * @param values Conjunto de valores positivos.
	 * @return Média harmônica do conjunto.
	 */
	BigDecimal harmonicMean(BigDecimal... values);

	/**
	 * Calcula a mediana.
	 *
	 * @param values Conjunto de valores.
	 * @return A mediana do conjunto.
	 */
	BigDecimal median(BigDecimal... values);

	/**
	 * Calcula a amplitude.
	 *
	 * @param values Conjunto de valores.
	 * @return Amplitude do conjunto.
	 */
	BigDecimal amplitude(BigDecimal... values);

	/**
	 * Calcula a variância para a amostra.
	 *
	 * @param values Conjunto de valores (amostra).
	 * @return Variância do conjunto.
	 */
	BigDecimal sampleVariance(BigDecimal... values);

	/**
	 * Calcula a variância para a população.
	 *
	 * @param values Conjunto de valores (população).
	 * @return Variância do conjunto.
	 */
	BigDecimal populationVariance(BigDecimal... values);

	/**
	 * Calcula o desvio padrão para a amostra.
	 *
	 * @param values Conjunto de valores (amostra).
	 * @return Desvio padrão do conjunto.
	 */
	BigDecimal sampleStandardDeviation(BigDecimal... values);

	/**
	 * Calcula o desvio padrão para a população.
	 *
	 * @param values Conjunto de valores (população).
	 * @return Desvio padrão do conjunto.
	 */
	BigDecimal populationStandardDeviation(BigDecimal... values);

	/**
	 * Calcula o quartil desejado.
	 *
	 * @param q      Quartil que será calculado.
	 * @param values Conjunto de valores.
	 * @return O valor do quartil calculado para o conjunto.
	 */
	BigDecimal quartile(Quartiles.Quartile q, BigDecimal... values);

	/**
	 * Calcula o outlier informado.
	 *
	 * @param bound  Outlier que será calculado.
	 * @param values Conjunto de valores.
	 * @return O valor do outlier calculado para o conjunto.
	 */
	BigDecimal outlier(Outliers.Bound bound, BigDecimal... values);
}
