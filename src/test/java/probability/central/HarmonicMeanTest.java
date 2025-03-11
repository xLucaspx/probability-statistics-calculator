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
 * Testa a classe {@link HarmonicMean}.
 *
 * @author Rodrigo Miotto Slongo (Slongo11)
 */
public class HarmonicMeanTest {
	/**
	 * Fornece os argumentos de entrada para {@link #harmonicMeanTest1}.
	 *
	 * @return Stream de argumentos onde cada elemento contém, respectivamente,
	 * o array de entrada e o resultado esperado para o cálculo da mediana.
	 */
	static Stream<Arguments> harmonicMeanTest1Values() {
		return Stream.of(
				Arguments.of(new Double[]{42.0}, 42.0),
				Arguments.of(new Double[]{3.0, 2.0, 10.0}, 3.2142857142857144),
				Arguments.of(new Double[]{4.0, 2.0, 8.0}, 3.4285714285714284),
				Arguments.of(new Double[]{5.0, 3.9, 2.44, 1.11, 143.99, 2.56}, 2.7717247036475308)
		);
	}

	/**
	 * Testa o cálculo da Média Geométrica realizado pelo método {@link HarmonicMean#harmonicMean(Double...)}.
	 *
	 * @param in       Dados de entrada.
	 * @param expected Resultado esperado.
	 */
	@ParameterizedTest
	@MethodSource("harmonicMeanTest1Values")
	@DisplayName("Deve calcular a média sem modificar os dados de entrada")
	void harmonicMeanTest1(Double[] in, Double expected) {
		Double[] original = Arrays.copyOf(in, in.length);

		Double actual = HarmonicMean.harmonicMean(in);

		assertArrayEquals(in, original);
		assertEquals(expected, actual);
	}

	@Test
	@DisplayName("Deve lançar uma exceção para um array vazio")
	void harmonicMeanTest2() {
		Double[] in = new Double[]{};

		assertThrows(ArithmeticException.class, () -> GeometricMean.geometricMean(in));
	}

}
