package statistics.functions.dispersion;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static statistics.Utils.toBigDecimalArray;

/**
 * Testa a classe {@link StandardDeviation}.
 *
 * @author Rodrigo Miotto Slongo
 */
public class StandardDeviationTest {
	/**
	 * Fornece os argumentos de entrada para {@link #standardDeviationTest1}.
	 *
	 * @return Stream de argumentos onde cada elemento contém, respectivamente,
	 * o array de entrada e o resultado esperado para o cálculo do desvio padrão.
	 */
	static Stream<Arguments> standardDeviationTest1Values() {
		return Stream.of(
			Arguments.of(toBigDecimalArray(42.0, 2), new BigDecimal("28.2842712474619")),
			Arguments.of(toBigDecimalArray(5.0, 7, 10.0, 12.0, 15), new BigDecimal("3.96232255123179")),
			Arguments.of(toBigDecimalArray(18.0, 21f, 21, 20, 25), new BigDecimal("2.549509756796392")),
			Arguments.of(toBigDecimalArray(12.4, 3.9, 12.125, 23.15, 1.119, 42.21, -4.404, 2.0, 8.0, 1.5), new BigDecimal("13.67922025880455"))
		);
	}

	/**
	 * Fornece os argumentos de entrada para {@link #standardDeviationTest2}.
	 *
	 * @return Stream de argumentos onde cada elemento contém, respectivamente,
	 * o array de entrada e o resultado esperado para o cálculo do desvio padrão.
	 */
	static Stream<Arguments> standardDeviationTest2Values() {
		return Stream.of(
			Arguments.of(toBigDecimalArray(42.0), new BigDecimal("0")),
			Arguments.of(toBigDecimalArray(42.0, 2), new BigDecimal("2E+1")),
			Arguments.of(toBigDecimalArray(5.0, 7, 10.0, 12.0, 15), new BigDecimal("3.54400902933387")),
			Arguments.of(toBigDecimalArray(18.0, 21f, 21, 20, 25), new BigDecimal("2.280350850198276")),
			Arguments.of(toBigDecimalArray(12.4, 3.9, 12.125, 23.15, 1.119, 42.21, -4.404, 2.0, 8.0, 1.5),
									 new BigDecimal("12.97724778988211")
			)
		);
	}

	@ParameterizedTest
	@MethodSource("standardDeviationTest1Values")
	@DisplayName("Deve calcular o desvio padrão da amostra")
	void standardDeviationTest1(BigDecimal[] in, BigDecimal expected) {
		BigDecimal actual = StandardDeviation.sampleStandardDeviation(in);

		assertEquals(expected, actual);
	}

	@ParameterizedTest
	@MethodSource("standardDeviationTest2Values")
	@DisplayName("Deve calcular o desvio padrão da população")
	void standardDeviationTest2(BigDecimal[] in, BigDecimal expected) {
		BigDecimal actual = StandardDeviation.populationStandardDeviation(in);

		assertEquals(expected, actual);
	}
}
