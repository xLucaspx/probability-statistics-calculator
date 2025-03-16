package probability.dispersion;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static probability.Utils.toBigDecimalArray;

/**
 * Testa a classe {@link Amplitude}.
 *
 * @author Lucas da Paz
 */
class AmplitudeTest {

	/**
	 * Fornece os argumentos de entrada para {@link #amplitudeTest1}.
	 *
	 * @return Stream de argumentos onde cada elemento contém, respectivamente,
	 * o array de entrada e o resultado esperado para o cálculo da amplitude.
	 */
	static Stream<Arguments> amplitudeTest1Values() {
		return Stream.of(
			Arguments.of(toBigDecimalArray(42.0), new BigDecimal("0.0")),
			Arguments.of(toBigDecimalArray(1L, 2.0f, 4, 5.5), new BigDecimal("4.5")),
			Arguments.of(toBigDecimalArray(5.0, 3.9, 2.44, 1.1f), new BigDecimal("3.9")),
			Arguments.of(toBigDecimalArray(12.4, 3.9, 12.15, 1.119, 42.21), new BigDecimal("41.091")),
			Arguments.of(toBigDecimalArray(12.4, 3.9, -2.15, 3.9f, 3.9f, -4.7), new BigDecimal("17.1")),
			Arguments.of(toBigDecimalArray(-2.15, -3.9, -27.9), new BigDecimal("25.75"))
		);
	}

	/**
	 * Fornece os argumentos de entrada para {@link #amplitudeTest2}.
	 *
	 * @return Stream de argumentos onde cada elemento contém o array de entrada.
	 */
	static Stream<Arguments> amplitudeTest2Values() {
		return Stream.of(
			Arguments.of((Object) null),
			Arguments.of((Object) new BigDecimal[]{}),
			Arguments.of((Object) new BigDecimal[]{null, BigDecimal.valueOf(2), null}),
			Arguments.of((Object) new BigDecimal[]{BigDecimal.valueOf(1), BigDecimal.valueOf(3), null, null, BigDecimal.valueOf(6)}),
			Arguments.of((Object) new BigDecimal[]{BigDecimal.valueOf(3.9), null, BigDecimal.valueOf(23.15), BigDecimal.valueOf(42.21)})
		);
	}

	@ParameterizedTest
	@MethodSource("amplitudeTest1Values")
	@DisplayName("Deve retornar a amplitude do array")
	void amplitudeTest1(BigDecimal[] in, BigDecimal expected) {
		BigDecimal actual = Amplitude.amplitude(in);

		assertEquals(expected, actual);
	}

	@ParameterizedTest
	@MethodSource("amplitudeTest2Values")
	@DisplayName("Deve lançar exceção para array nulo, vazio ou com elementos nulos")
	void amplitudeTest2(BigDecimal... in) {
		assertThrows(NullPointerException.class, () -> Amplitude.amplitude(in));
	}
}
