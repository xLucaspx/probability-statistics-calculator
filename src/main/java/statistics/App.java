package statistics;

import statistics.calculator.PucrsCalculator;
import statistics.calculator.StatisticsCalculator;
import statistics.calculator.StdCalculator;
import statistics.data.CityWeatherData;
import statistics.data.CityWeatherReader;
import statistics.data.MonthlyWeatherData;
import statistics.functions.dispersion.Outliers;
import statistics.functions.dispersion.Quartiles;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static statistics.Utils.MATH_CONTEXT;

public class App {

	private static final StatisticsCalculator PUCRS_CALC = new PucrsCalculator();
	private static final StatisticsCalculator STD_CALC = new StdCalculator();
	private static final List<CityWeatherData> DATA = CityWeatherReader.getDataFromFile();

	public static void main(String... args) {
		header();
		dataPerCountry();
	}

	private static void header() {
		System.out.println("\n--- Probabilidade e Estatística---\n");
		System.out.printf("Base de dados: meteorológicos | Total de cidades: %d%n%n", DATA.size());
	}

	private static void dataPerCountry() {
		Map<String, List<MonthlyWeatherData>> countryWeatherData = new HashMap<>();

		DATA.forEach(c -> {
			var entry = countryWeatherData.getOrDefault(c.country(), new ArrayList<>());
			entry.addAll(c.weatherData());
			countryWeatherData.put(c.country(), entry);
		});

		System.out.printf("\tTotal de países: %d%n%n", countryWeatherData.size());

		for (var entry : countryWeatherData.entrySet()) {
			String country = entry.getKey();
			List<MonthlyWeatherData> weatherData = entry.getValue();

			BigDecimal[] minTemperatures =
				weatherData.stream().map(d -> BigDecimal.valueOf(d.minTemperature())).toArray(BigDecimal[]::new);
			BigDecimal[] maxTemperatures =
				weatherData.stream().map(d -> BigDecimal.valueOf(d.maxTemperature())).toArray(BigDecimal[]::new);

			OperationsResult minTempPucrs = OperationsResult.of(PUCRS_CALC, minTemperatures);
			OperationsResult minTempLib = OperationsResult.of(STD_CALC, minTemperatures);
			OperationsResult maxTempPucrs = OperationsResult.of(PUCRS_CALC, maxTemperatures);
			OperationsResult maxTempLib = OperationsResult.of(STD_CALC, maxTemperatures);

			System.out.println(generateTable("%s - Temperatura mínima".formatted(country), minTempPucrs, minTempLib));
			System.out.println(generateTable("%s - Temperatura máxima".formatted(country), maxTempPucrs, maxTempLib));
		}
	}

	private static String generateTable(String title, OperationsResult pucrs, OperationsResult lib) {
		StringBuilder table = new StringBuilder();
		table.append(title).append("\n");
		table.append("| Função                     | Resultado PUCRS            | Resultado lib              | Diferença                  |\n");
		table.append("| -------------------------- | -------------------------- | -------------------------- | -------------------------- |\n");

		table.append(formatTableRow("Média aritmética", pucrs.arithmeticMean(), lib.arithmeticMean()));
		table.append(formatTableRow("Média geométrica", pucrs.geometricMean(), lib.geometricMean()));
		table.append(formatTableRow("Média harmônica", pucrs.harmonicMean(), lib.harmonicMean()));
		table.append(formatTableRow("Mediana", pucrs.median(), lib.median()));
		table.append(formatTableRow("Amplitude", pucrs.amplitude(), lib.amplitude()));
		table.append(formatTableRow("Variância amostral", pucrs.sampleVariance(), lib.sampleVariance()));
		table.append(formatTableRow("Variância populacional", pucrs.populationVariance(), lib.populationVariance()));
		table.append(formatTableRow("Desvio padrão amostral",
																pucrs.sampleStandardDeviation(),
																lib.sampleStandardDeviation()
		));
		table.append(formatTableRow("Desvio padrão populacional",
																pucrs.populationStandardDeviation(),
																lib.populationStandardDeviation()
		));
		table.append(formatTableRow("Q1 (1º Quartil)", pucrs.quartile1(), lib.quartile1()));
		table.append(formatTableRow("Q2 (2º Quartil/Mediana)", pucrs.quartile2(), lib.quartile2()));
		table.append(formatTableRow("Q3 (3º Quartil)", pucrs.quartile3(), lib.quartile3()));
		table.append(formatTableRow("Outlier inferior", pucrs.outlierLower(), lib.outlierLower()));
		table.append(formatTableRow("Outlier supperior", pucrs.outlierUpper(), lib.outlierUpper()));

		return table.append("\n").toString();
	}

	private static String formatTableRow(String functionName, BigDecimal pucrsResult, BigDecimal libResult) {
		return String.format("| %-26s | %-26s | %-26s | %-26s |\n",
												 functionName,
												 formatDecimal(pucrsResult),
												 formatDecimal(libResult),
												 formatDecimal(difference(pucrsResult, libResult))
		);
	}

	private static String formatDecimal(BigDecimal value) {
		return (value != null) ? value.stripTrailingZeros().toPlainString() : "N/A";
	}

	private static BigDecimal difference(BigDecimal pucrsResult, BigDecimal libResult) {
		return pucrsResult != null && libResult != null ? pucrsResult.subtract(libResult).abs(MATH_CONTEXT) : null;
	}

	private record OperationsResult(
		BigDecimal arithmeticMean,
		BigDecimal geometricMean,
		BigDecimal harmonicMean,
		BigDecimal median,
		BigDecimal amplitude,
		BigDecimal sampleVariance,
		BigDecimal populationVariance,
		BigDecimal sampleStandardDeviation,
		BigDecimal populationStandardDeviation,
		BigDecimal quartile1,
		BigDecimal quartile2,
		BigDecimal quartile3,
		BigDecimal outlierLower,
		BigDecimal outlierUpper
	) {

		public static OperationsResult of(StatisticsCalculator calc, BigDecimal... values) {
			return new OperationsResult(calc.arithmeticMean(values),
																	calc.geometricMean(values),
																	calc.harmonicMean(values),
																	calc.median(values),
																	calc.amplitude(values),
																	calc.sampleVariance(values),
																	calc.populationVariance(values),
																	calc.sampleStandardDeviation(values),
																	calc.populationStandardDeviation(values),
																	calc.quartile(Quartiles.Quartile.Q1, values),
																	calc.quartile(Quartiles.Quartile.Q2, values),
																	calc.quartile(Quartiles.Quartile.Q3, values),
																	calc.outlier(Outliers.Bound.LOWERBOUND, values),
																	calc.outlier(Outliers.Bound.UPPERBOUND, values)
			);
		}
	}
}
