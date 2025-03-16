package probability.calculator;

import probability.central.ArithmeticMean;
import probability.central.GeometricMean;
import probability.central.HarmonicMean;
import probability.central.Median;
import probability.dispersion.Amplitude;
import probability.dispersion.Quartiles;
import probability.dispersion.StandardDeviation;
import probability.dispersion.Variance;

import java.math.BigDecimal;

public class PucrsCalculator implements ProbabilityCalculator {

	@Override
	public BigDecimal arithmeticMean(BigDecimal... values) {
		return ArithmeticMean.arithmeticMean(values);
	}

	@Override
	public BigDecimal geometricMean(BigDecimal... values) {
		try {
			return GeometricMean.geometricMean(values);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public BigDecimal harmonicMean(BigDecimal... values) {
		try {
			return HarmonicMean.harmonicMean(values);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public BigDecimal median(BigDecimal... values) {
		return Median.median(values);
	}

	@Override
	public BigDecimal amplitude(BigDecimal... values) {
		return Amplitude.amplitude(values);
	}

	@Override
	public BigDecimal sampleVariance(BigDecimal... values) {
		return Variance.sampleVariance(values);
	}

	@Override
	public BigDecimal populationVariance(BigDecimal... values) {
		return Variance.populationVariance(values);
	}

	@Override
	public BigDecimal sampleStandardDeviation(BigDecimal... values) {
		return StandardDeviation.sampleStandardDeviation(values);
	}

	@Override
	public BigDecimal populationStandardDeviation(BigDecimal... values) {
		return StandardDeviation.populationStandardDeviation(values);
	}

	@Override
	public BigDecimal quartile(Quartiles.Quartile q, BigDecimal... values) {
		return Quartiles.quartile(q, values);
	}
}
