package probability;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

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
			Arguments.of(new Double[]{}, new Double[]{}),
			Arguments.of(new Double[]{42.0}, new Double[]{42.0}),
			Arguments.of(new Double[]{1.0, 2.0, 4.0, 5.5}, new Double[]{1.0, 2.0, 4.0, 5.5}),
			Arguments.of(new Double[]{5.0, 3.9, 2.44, 1.11}, new Double[]{1.11, 2.44, 3.9, 5.0}),
			Arguments.of(new Double[]{12.4, 3.9, 12.15, 1.119, 42.21}, new Double[]{1.119, 3.9, 12.15, 12.4, 42.21}),
			Arguments.of(new Double[]{12.4, 3.9, -2.15, 3.9, 3.9, -4.7}, new Double[]{-4.7, -2.15, 3.9, 3.9, 3.9, 12.4})
		);
	}

	/**
	 * Testa a ordenação de método {@link Utils#sort(Double...)}. Verifica se o
	 * array retornado está ordenado corretamente e se o array original não foi
	 * modificado pelo método.
	 *
	 * @param in       Dados de entrada.
	 * @param expected Resultado esperado.
	 */
	@ParameterizedTest
	@MethodSource("sortTest1Values")
	@DisplayName("Deve ordenar arrays corretamente sem modificar o original")
	void sortTest1(Double[] in, Double[] expected) {
		Double[] original = Arrays.copyOf(in, in.length);

		Double[] actual = Utils.sort(in);

		assertArrayEquals(in, original);
		assertArrayEquals(expected, actual);
	}
}
