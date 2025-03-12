package probability.dispersion;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import probability.central.Median;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
			Arguments.of(new Double[]{42.0}, 0.0),
			Arguments.of(new Double[]{1.0, 2.0, 4.0, 5.5}, 4.5),
			Arguments.of(new Double[]{5.0, 3.9, 2.44, 1.1}, 3.9),
			Arguments.of(new Double[]{12.4, 3.9, 12.15, 1.119, 42.21}, 41.091),
			Arguments.of(new Double[]{12.4, 3.9, -2.15, 3.9, 3.9, -4.7}, 17.1),
			Arguments.of(new Double[]{-2.15, -3.9, -27.9}, 25.75)
		);
	}

	/**
	 * Fornece os argumentos de entrada para {@link #amplitudeTest3}.
	 *
	 * @return Stream de argumentos onde cada elemento contém o array de entrada.
	 */
	static Stream<Arguments> amplitudeTest3Values() {
		return Stream.of(
			Arguments.of((Object) new Double[]{null, 2.0, null}),
			Arguments.of((Object) new Double[]{1.0, 3.0, null, null, 6.0}),
			Arguments.of((Object) new Double[]{12.4, 3.9, null, 23.15, 1.119, 42.21})
		);
	}

	@ParameterizedTest
	@MethodSource("amplitudeTest1Values")
	@DisplayName("Deve retornar a amplitude do array")
	void amplitudeTest1(Double[] in, Double expected) {
		Double actual = Amplitude.amplitude(in);

		assertEquals(expected, actual);
	}

	@ParameterizedTest
	@NullAndEmptySource
	@DisplayName("Deve lançar exceção para array nulo ou vazio")
	void amplitudeTest2(Double[] in) {
		assertThrows(IllegalArgumentException.class, () -> Amplitude.amplitude(in));
	}

	@ParameterizedTest
	@MethodSource("amplitudeTest3Values")
	@DisplayName("Deve lançar exceção para array com elementos nulos")
	void amplitudeTest3(Double... in) {
		assertThrows(NullPointerException.class, () -> Median.median(in));
	}
}
