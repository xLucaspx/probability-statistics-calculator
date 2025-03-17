package statistics.functions.central;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static statistics.Utils.toBigDecimalArray;

/**
 * Testa a classe {@link Median}.
 *
 * @author Lucas da Paz
 */
class MedianTest {

	/**
	 * Fornece os argumentos de entrada para {@link #medianTest1}.
	 *
	 * @return Stream de argumentos onde cada elemento contém, respectivamente,
	 * o array de entrada e o resultado esperado para o cálculo da mediana.
	 */
	static Stream<Arguments> medianTest1Values() {
		return Stream.of(
			Arguments.of(toBigDecimalArray(42), new BigDecimal("42")),
			Arguments.of(toBigDecimalArray(1.0f, 2.0f, 4.0f), new BigDecimal("2.0")),
			Arguments.of(toBigDecimalArray(5.0f, 3.9, 2L, 1.11, 143.99f), new BigDecimal("3.9")),
			Arguments.of(toBigDecimalArray(12.4, 3.9, 12.125, 23.15, 1.119, 42.21), new BigDecimal("12.2625")),
			Arguments.of(toBigDecimalArray(42.0, 29.7, 123.44, 0.02, 1.23, 2.676, 5.777, 16.9), new BigDecimal("11.3385"))
		);
	}

	/**
	 * Fornece os argumentos de entrada para {@link #medianTest3}.
	 *
	 * @return Stream de argumentos onde cada elemento contém o array de entrada.
	 */
	static Stream<Arguments> medianTest3Values() {
		return Stream.of(
			Arguments.of((Object) new BigDecimal[]{null, BigDecimal.valueOf(2), null}),
			Arguments.of((Object) new BigDecimal[]{
				BigDecimal.valueOf(1), BigDecimal.valueOf(3), null, null, BigDecimal.valueOf(6)
			}),
			Arguments.of((Object) new BigDecimal[]{
				BigDecimal.valueOf(3.9), null, BigDecimal.valueOf(23.15), BigDecimal.valueOf(42.21)
			})
		);
	}

	@ParameterizedTest
	@MethodSource("medianTest1Values")
	@DisplayName("Deve calcular a mediana sem modificar os dados de entrada")
	void medianTest1(BigDecimal[] in, BigDecimal expected) {
		BigDecimal[] original = Arrays.copyOf(in, in.length);

		BigDecimal actual = Median.median(in);

		assertArrayEquals(in, original);
		assertEquals(expected, actual);
	}

	@Test
	@DisplayName("Deve lançar exceção para array vazio")
	void medianTest2() {
		BigDecimal[] in = new BigDecimal[]{};
		assertThrows(IndexOutOfBoundsException.class, () -> Median.median(in));
	}

	@ParameterizedTest
	@NullSource
	@MethodSource("medianTest3Values")
	@DisplayName("Deve lançar exceção para array nulo ou com elementos nulos")
	void medianTest3(BigDecimal... in) {
		assertThrows(NullPointerException.class, () -> Median.median(in));
	}
}
