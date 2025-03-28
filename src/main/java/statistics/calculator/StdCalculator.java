package statistics.calculator;

import org.apache.commons.math3.stat.StatUtils;
import statistics.functions.dispersion.Outliers;
import statistics.functions.dispersion.Quartiles;

import java.math.BigDecimal;

import static statistics.Utils.toDoubleArray;

/**
 * Utiliza a implementação das funções estatísticas
 * da biblioteca {@link StatUtils}.
 *
 * @author Lucas da Paz
 */
public class StdCalculator implements StatisticsCalculator {

	/**
	 * @see StatUtils#mean(double[])
	 */
	@Override
	public BigDecimal arithmeticMean(BigDecimal... values) {
		double mean = StatUtils.mean(toDoubleArray(values));
		return BigDecimal.valueOf(mean);
	}

	/**
	 * @see StatUtils#geometricMean(double[])
	 */
	@Override
	public BigDecimal geometricMean(BigDecimal... values) {
		try {
			double mean = StatUtils.geometricMean(toDoubleArray(values));
			return BigDecimal.valueOf(mean);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Não possui implementação conhecida em bibliotecas.
	 *
	 * @return {@code null}.
	 */
	@Override
	public BigDecimal harmonicMean(BigDecimal... values) {
		return null;
	}

	/**
	 * Calcula o 50º percentil, equivalente ao 2º quartil
	 * e à mediana.
	 *
	 * @see StatUtils#percentile(double[], double)
	 */
	@Override
	public BigDecimal median(BigDecimal... values) {
		double median = StatUtils.percentile(toDoubleArray(values), 50.0);
		return BigDecimal.valueOf(median);
	}

	/**
	 * @see StatUtils#min(double[])
	 * @see StatUtils#max(double[])
	 */
	@Override
	public BigDecimal amplitude(BigDecimal... values) {
		double[] doubleValues = toDoubleArray(values);
		double amplitude = StatUtils.max(doubleValues) - StatUtils.min(doubleValues);
		return BigDecimal.valueOf(amplitude);
	}

	/**
	 * @see StatUtils#variance(double[])
	 */
	@Override
	public BigDecimal sampleVariance(BigDecimal... values) {
		double variance = StatUtils.variance(toDoubleArray(values));
		return BigDecimal.valueOf(variance);
	}

	/**
	 * @see StatUtils#populationVariance(double[])
	 */
	@Override
	public BigDecimal populationVariance(BigDecimal... values) {
		double variance = StatUtils.populationVariance(toDoubleArray(values));
		return BigDecimal.valueOf(variance);
	}

	/**
	 * {@inheritDoc} Retorna a raiz quadrada da variância.
	 *
	 * @see StatUtils#variance(double[])
	 */
	@Override
	public BigDecimal sampleStandardDeviation(BigDecimal... values) {
		double stdDeviation = Math.sqrt(sampleVariance(values).doubleValue());
		return BigDecimal.valueOf(stdDeviation);
	}

	/**
	 * {@inheritDoc} Retorna a raiz quadrada da variância da população.
	 *
	 * @see StatUtils#populationVariance(double[])
	 */
	@Override
	public BigDecimal populationStandardDeviation(BigDecimal... values) {
		double stdDeviation = Math.sqrt(populationVariance(values).doubleValue());
		return BigDecimal.valueOf(stdDeviation);
	}

	/**
	 * {@inheritDoc} Retorna a raiz quadrada da variância.
	 *
	 * @see StatUtils#variance(double[])
	 */
	@Override
	public BigDecimal quartile(Quartiles.Quartile q, BigDecimal... values) {
		double quartileAsPercentile = q.percentValue().doubleValue() * 100;
		double quartile = StatUtils.percentile(toDoubleArray(values), quartileAsPercentile);
		return BigDecimal.valueOf(quartile);
	}

	@Override
	public BigDecimal outlier(Outliers.Bound bound, BigDecimal... values) {
		BigDecimal q1 = quartile(Quartiles.Quartile.Q1, values);
		BigDecimal q3 = quartile(Quartiles.Quartile.Q3, values);
		BigDecimal iqrMultiplied = q3.subtract(q1).multiply(BigDecimal.valueOf(1.5));

		return switch (bound) {
			case LOWERBOUND -> q1.subtract(iqrMultiplied);
			case UPPERBOUND -> q3.add(iqrMultiplied);
		};
	}
}
