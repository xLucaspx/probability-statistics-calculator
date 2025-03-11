package probability.central;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
			Arguments.of(new Double[]{42.0}, 42.0),
			Arguments.of(new Double[]{1.0, 2.0, 4.0}, 2.0),
			Arguments.of(new Double[]{5.0, 3.9, 2.44, 1.11, 143.99}, 3.9),
			Arguments.of(new Double[]{12.4, 3.9, 12.125, 23.15, 1.119, 42.21}, 12.2625),
			Arguments.of(new Double[]{42.0, 29.7, 123.44, 0.02, 1.23, 2.676, 5.777, 16.9}, 11.3385)
		);
	}

	/**
	 * Testa o cálculo da mediana realizado pelo método {@link Median#median(Double...)}.
	 *
	 * @param in       Dados de entrada.
	 * @param expected Resultado esperado.
	 */
	@ParameterizedTest
	@MethodSource("medianTest1Values")
	@DisplayName("Deve calcular a mediana sem modificar os dados de entrada")
	void medianTest1(Double[] in, Double expected) {
		Double[] original = Arrays.copyOf(in, in.length);

		Double actual = Median.median(in);

		assertArrayEquals(in, original);
		assertEquals(expected, actual);
	}

	@Test
	@DisplayName("Deve lançar uma exceção para um array vazio")
	void medianTest2() {
		Double[] in = new Double[]{};

		assertThrows(IndexOutOfBoundsException.class, () -> Median.median(in));
	}
}
