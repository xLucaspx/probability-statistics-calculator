package probability.central;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testa a classe {@link GeometricMean}.
 *
 * @author Rodrigo Miotto Slongo (Slongo11)
 */
public class GeometricMeanTest {

	/**
	 * Fornece os argumentos de entrada para {@link #geometricMeanTest1}.
	 *
	 * @return Stream de argumentos onde cada elemento contém, respectivamente,
	 * o array de entrada e o resultado esperado para o cálculo da mediana.
	 */
	static Stream<Arguments> geometricMeanTest1Values() {
		return Stream.of(
				Arguments.of(new Double[]{42.0}, 42.0),
				Arguments.of(new Double[]{3.0, 2.0, 10.0}, 3.9148676411688634),
				Arguments.of(new Double[]{4.0, 2.0, 8.0}, 3.9999999999999996),
				Arguments.of(new Double[]{5.0, 3.9, 2.44, 1.11, 143.99, 2.56}, 5.186646136945205)
		);
	}

	/**
	 * Testa o cálculo da Média Geométrica realizado pelo método {@link GeometricMean#geometricMean(Double...)}.
	 *
	 * @param in       Dados de entrada.
	 * @param expected Resultado esperado.
	 */
	@ParameterizedTest
	@MethodSource("geometricMeanTest1Values")
	@DisplayName("Deve calcular a média sem modificar os dados de entrada")
	void geometricMeanTest1(Double[] in, Double expected) {
		Double[] original = Arrays.copyOf(in, in.length);

		Double actual = GeometricMean.geometricMean(in);

		assertArrayEquals(in, original);
		assertEquals(expected, actual);
	}

	@Test
	@DisplayName("Deve lançar uma exceção para um array vazio")
	void geometricMeanTest2() {
		Double[] in = new Double[]{};

		assertThrows(ArithmeticException.class, () -> GeometricMean.geometricMean(in));
	}
}
