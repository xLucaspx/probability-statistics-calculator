package probability;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static probability.Utils.toBigDecimalArray;

/**
 * Testa a classe de utiidades {@link Utils}.
 *
 * @author Lucas da Paz
 */
class UtilsTest {

	/**
	 * Fornece os argumentos de entrada para {@link #sortTest1}.
	 *
	 * @return Stream de argumentos onde cada elemento contém, respectivamente,
	 * o array de entrada e o array ordenado representando o resultado esperado.
	 */
	static Stream<Arguments> sortTest1Values() {
		return Stream.of(
			Arguments.of(new BigDecimal[]{}, new BigDecimal[]{}),
			Arguments.of(toBigDecimalArray(42.0), toBigDecimalArray(42.0)),
			Arguments.of(toBigDecimalArray(1.0, 2.0, 4.0, 5.5), toBigDecimalArray(1.0, 2.0, 4.0, 5.5)),
			Arguments.of(toBigDecimalArray(5.0, 3.9, 2.44, 1.11), toBigDecimalArray(1.11, 2.44, 3.9, 5.0)),
			Arguments.of(toBigDecimalArray(12.4, 3.9, 12.15, 1.119, 42.21), toBigDecimalArray(1.119, 3.9, 12.15, 12.4, 42.21)),
			Arguments.of(toBigDecimalArray(12.4, 3.9, -2.15, 3.9, 3.9, -4.7), toBigDecimalArray(-4.7, -2.15, 3.9, 3.9, 3.9, 12.4))
		);
	}

	/**
	 * Fornece os argumentos de entrada para {@link #minMaxTest1}.
	 *
	 * @return Stream de argumentos onde cada elemento contém, respectivamente,
	 * o array de entrada, o valor mínimo e o valor máximo esperados.
	 */
	static Stream<Arguments> minMaxTest1Values() {
		return Stream.of(
			Arguments.of(toBigDecimalArray(42L), new BigDecimal("42"), new BigDecimal("42")),
			Arguments.of(toBigDecimalArray(1.0, 2.0f, 4.0, 5.5f), new BigDecimal("1.0"), new BigDecimal("5.5")),
			Arguments.of(toBigDecimalArray(5, 3.9, 2.44f, 1.11), new BigDecimal("1.11"), new BigDecimal("5")),
			Arguments.of(toBigDecimalArray(12.4, 3.9, 12.15, 1.119, 42.21), new BigDecimal("1.119"), new BigDecimal("42.21")),
			Arguments.of(toBigDecimalArray(12.4, 3.9, -2.15, 3.9, 3.9, -4.7), new BigDecimal("-4.7"), new BigDecimal("12.4")),
			Arguments.of(toBigDecimalArray(-2.15, -3.9, -27.92), new BigDecimal("-27.92"), new BigDecimal("-2.15"))
		);
	}

	@ParameterizedTest
	@MethodSource("sortTest1Values")
	@DisplayName("Deve ordenar arrays corretamente sem modificar o original")
	void sortTest1(BigDecimal[] in, BigDecimal[] expected) {
		BigDecimal[] actual = Utils.sort(in);

		assertNotSame(in, actual);
		assertArrayEquals(expected, actual);
	}

	@ParameterizedTest
	@MethodSource("minMaxTest1Values")
	@DisplayName("Deve retornar o menor e o maior valor do array")
	void minMaxTest1(BigDecimal[] in, BigDecimal expectedMin, BigDecimal expectedMax) {
		BigDecimal actualMin = Utils.min(in);
		BigDecimal actualMax = Utils.max(in);

		assertEquals(expectedMin, actualMin);
		assertEquals(expectedMax, actualMax);
	}
}
