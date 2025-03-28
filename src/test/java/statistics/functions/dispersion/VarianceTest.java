package statistics.functions.dispersion;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static statistics.Utils.toBigDecimalArray;

/**
 * Testa a classe {@link Variance}.
 *
 * @author Rodrigo Miotto Slongo
 */
public class VarianceTest {
	/**
	 * Fornece os argumentos de entrada para {@link #varianceTest1}.
	 *
	 * @return Stream de argumentos onde cada elemento contém, respectivamente,
	 * o array de entrada e o resultado esperado para o cálculo da variância amostral.
	 */
	static Stream<Arguments> varianceTest1Values() {
		return Stream.of(
			Arguments.of(toBigDecimalArray(42.0, 2), new BigDecimal("8E2")),
			Arguments.of(toBigDecimalArray(5.0, 7, 10.0, 12.0, 15), new BigDecimal("15.7")),
			Arguments.of(toBigDecimalArray(18.0, 21f, 21, 20, 25), new BigDecimal("6.5")),
			Arguments.of(toBigDecimalArray(12.4, 3.9, 12.125, 23.15, 1.119, 42.21, -4.404, 2.0, 8.0, 1.5), new BigDecimal("187.1210668888889"))
		);
	}

	/**
	 * Fornece os argumentos de entrada para {@link #varianceTest2}.
	 *
	 * @return Stream de argumentos onde cada elemento contém, respectivamente,
	 * o array de entrada e o resultado esperado para o cálculo da variância populacional.
	 */
	static Stream<Arguments> varianceTest2Values() {
		return Stream.of(
			Arguments.of(toBigDecimalArray(42.0, 2), new BigDecimal("4E2")),
			Arguments.of(toBigDecimalArray(5.0, 7, 10.0, 12.0, 15), new BigDecimal("12.56")),
			Arguments.of(toBigDecimalArray(18.0, 21f, 21, 20, 25), new BigDecimal("5.2")),
			Arguments.of(toBigDecimalArray(12.4, 3.9, 12.125, 23.15, 1.119, 42.21, -4.404, 2.0, 8.0, 1.5), new BigDecimal("168.4089602"))
		);
	}

	@ParameterizedTest
	@MethodSource("varianceTest1Values")
	@DisplayName("Deve calcular a variância amostral")
	void varianceTest1(BigDecimal[] in, BigDecimal expected) {
		BigDecimal actual = Variance.sampleVariance(in);

		assertEquals(expected, actual);
	}

	@ParameterizedTest
	@MethodSource("varianceTest2Values")
	@DisplayName("Deve calcular a variância populacional")
	void varianceTest2(BigDecimal[] in, BigDecimal expected) {
		BigDecimal actual = Variance.populationVariance(in);

		assertEquals(expected, actual);
	}

	@Test
	@DisplayName("Deve lançar exceção para array vazio")
	void varianceTest3() {
		BigDecimal[] in = new BigDecimal[]{};

		assertThrows(ArithmeticException.class, () -> Variance.sampleVariance(in));
		assertThrows(ArithmeticException.class, () -> Variance.populationVariance(in));
	}

	@Test
	@DisplayName("Deve lançar exceção para array unitário na variância amostral")
	void varianceTest4() {
		BigDecimal[] in = new BigDecimal[]{new BigDecimal("1")};

		assertThrows(ArithmeticException.class, () -> Variance.sampleVariance(in));
	}
}
