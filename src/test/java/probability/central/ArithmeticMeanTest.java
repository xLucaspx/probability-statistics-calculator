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
 * Testa a classe {@link ArithmeticMean}.
 *
 * @author Rodrigo Miotto Slongo (Slongo11)
 */
public class ArithmeticMeanTest {
	/**
	 * Fornece os argumentos de entrada para {@link #arithmeticMeanTest1}.
	 *
	 * @return Stream de argumentos onde cada elemento contém, respectivamente,
	 * o array de entrada e o resultado esperado para o cálculo da mediana.
	 */
	static Stream<Arguments> arithmeticMeanTest1Values() {
		return Stream.of(
				Arguments.of(new Double[]{42.0}, 42.0),
				Arguments.of(new Double[]{5.0, -2.0, 5.0, 2.0}, 2.5),
				Arguments.of(new Double[]{5.0, 3.9, 2.44, 1.11, 143.99, 2.56}, 26.5),
				Arguments.of(new Double[]{12.4, 3.9, 12.125, 23.15, 1.119, 42.21, -4.404, 2.0 , 8.0, 1.5}, 10.2)
		);
	}

	/**
	 * Testa o cálculo da Média Aritimética realizado pelo método {@link ArithmeticMean#arithmeticMean(Double...)}.
	 *
	 * @param in       Dados de entrada.
	 * @param expected Resultado esperado.
	 */
	@ParameterizedTest
	@MethodSource("arithmeticMeanTest1Values")
	@DisplayName("Deve calcular a média sem modificar os dados de entrada")
	void arithmeticMeanTest1(Double[] in, Double expected) {
		Double[] original = Arrays.copyOf(in, in.length);

		Double actual = ArithmeticMean.arithmeticMean(in);

		assertArrayEquals(in, original);
		assertEquals(expected, actual);
	}

	@Test
	@DisplayName("Deve lançar uma exceção para um array vazio")
	void arithmeticMeanTest2() {
		Double[] in = new Double[]{};

		assertThrows(ArithmeticException.class, () -> ArithmeticMean.arithmeticMean(in));
	}
}
