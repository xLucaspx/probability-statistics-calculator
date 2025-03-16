package probability.calculator;

import probability.dispersion.Quartiles;

import java.math.BigDecimal;

public interface ProbabilityCalculator {

	BigDecimal arithmeticMean(BigDecimal... values);

	BigDecimal geometricMean(BigDecimal... values);

	BigDecimal harmonicMean(BigDecimal... values);

	BigDecimal median(BigDecimal... values);

	BigDecimal amplitude(BigDecimal... values);

	BigDecimal sampleVariance(BigDecimal... values);

	BigDecimal populationVariance(BigDecimal... values);

	BigDecimal sampleStandardDeviation(BigDecimal... values);

	BigDecimal populationStandardDeviation(BigDecimal... values);

	BigDecimal quartile(Quartiles.Quartile q, BigDecimal... values);
}
