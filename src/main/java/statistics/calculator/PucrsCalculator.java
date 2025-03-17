package statistics.calculator;

import statistics.functions.central.ArithmeticMean;
import statistics.functions.central.GeometricMean;
import statistics.functions.central.HarmonicMean;
import statistics.functions.central.Median;
import statistics.functions.dispersion.Amplitude;
import statistics.functions.dispersion.Quartiles;
import statistics.functions.dispersion.StandardDeviation;
import statistics.functions.dispersion.Variance;

import java.math.BigDecimal;

/**
 * Utiliza a implementação das funções estatísticas
 * desenvolvidas pelos alunos para o projeto.
 *
 * @author Lucas da Paz
 */
public class PucrsCalculator implements StatisticsCalculator {

	/**
	 * @see ArithmeticMean#arithmeticMean(BigDecimal...)
	 */
	@Override
	public BigDecimal arithmeticMean(BigDecimal... values) {
		return ArithmeticMean.arithmeticMean(values);
	}

	/**
	 * @see GeometricMean#geometricMean(BigDecimal...)
	 */
	@Override
	public BigDecimal geometricMean(BigDecimal... values) {
		try {
			return GeometricMean.geometricMean(values);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * @see HarmonicMean#harmonicMean(BigDecimal...)
	 */
	@Override
	public BigDecimal harmonicMean(BigDecimal... values) {
		try {
			return HarmonicMean.harmonicMean(values);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * @see Median#median(BigDecimal...)
	 */
	@Override
	public BigDecimal median(BigDecimal... values) {
		return Median.median(values);
	}

	/**
	 * @see Amplitude#amplitude(BigDecimal...)
	 */
	@Override
	public BigDecimal amplitude(BigDecimal... values) {
		return Amplitude.amplitude(values);
	}

	/**
	 * @see Variance#sampleVariance(BigDecimal...)
	 */
	@Override
	public BigDecimal sampleVariance(BigDecimal... values) {
		return Variance.sampleVariance(values);
	}

	/**
	 * @see Variance#populationVariance(BigDecimal...)
	 */
	@Override
	public BigDecimal populationVariance(BigDecimal... values) {
		return Variance.populationVariance(values);
	}

	/**
	 * @see StandardDeviation#sampleStandardDeviation(BigDecimal...)
	 */
	@Override
	public BigDecimal sampleStandardDeviation(BigDecimal... values) {
		return StandardDeviation.sampleStandardDeviation(values);
	}

	/**
	 * @see StandardDeviation#populationStandardDeviation(BigDecimal...)
	 */
	@Override
	public BigDecimal populationStandardDeviation(BigDecimal... values) {
		return StandardDeviation.populationStandardDeviation(values);
	}

	/**
	 * @see Quartiles#quartile(Quartiles.Quartile, BigDecimal...)
	 */
	@Override
	public BigDecimal quartile(Quartiles.Quartile q, BigDecimal... values) {
		return Quartiles.quartile(q, values);
	}
}
