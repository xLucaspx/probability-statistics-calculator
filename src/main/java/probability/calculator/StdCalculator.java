package probability.calculator;

import org.apache.commons.math3.stat.StatUtils;
import probability.dispersion.Quartiles;

import java.math.BigDecimal;

import static probability.Utils.toDoubleArray;

public class StdCalculator implements ProbabilityCalculator {

	@Override
	public BigDecimal arithmeticMean(BigDecimal... values) {
		double mean = StatUtils.mean(toDoubleArray(values));
		return BigDecimal.valueOf(mean);
	}

	@Override
	public BigDecimal geometricMean(BigDecimal... values) {
		try {
			double mean = StatUtils.geometricMean(toDoubleArray(values));
			return BigDecimal.valueOf(mean);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public BigDecimal harmonicMean(BigDecimal... values) {
		return null;
	}

	@Override
	public BigDecimal median(BigDecimal... values) {
		double median = StatUtils.percentile(toDoubleArray(values), 50.0);
		return BigDecimal.valueOf(median);
	}

	@Override
	public BigDecimal amplitude(BigDecimal... values) {
		double[] doubleValues = toDoubleArray(values);
		double amplitude = StatUtils.max(doubleValues) - StatUtils.min(doubleValues);
		return BigDecimal.valueOf(amplitude);
	}

	@Override
	public BigDecimal sampleVariance(BigDecimal... values) {
		double variance = StatUtils.variance(toDoubleArray(values));
		return BigDecimal.valueOf(variance);
	}

	@Override
	public BigDecimal populationVariance(BigDecimal... values) {
		double variance = StatUtils.populationVariance(toDoubleArray(values));
		return BigDecimal.valueOf(variance);
	}

	@Override
	public BigDecimal sampleStandardDeviation(BigDecimal... values) {
		double stdDeviation = Math.sqrt(sampleVariance(values).doubleValue());
		return BigDecimal.valueOf(stdDeviation);
	}

	@Override
	public BigDecimal populationStandardDeviation(BigDecimal... values) {
		double stdDeviation = Math.sqrt(populationVariance(values).doubleValue());
		return BigDecimal.valueOf(stdDeviation);
	}

	@Override
	public BigDecimal quartile(Quartiles.Quartile q, BigDecimal... values) {
		double quartile = StatUtils.percentile(toDoubleArray(values), q.percentValue().doubleValue() * 100);
		return BigDecimal.valueOf(quartile);
	}
}
