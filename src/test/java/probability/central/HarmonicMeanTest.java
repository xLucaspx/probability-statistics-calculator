package probability.central;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static probability.Utils.toBigDecimalArray;

/**
 * Testa a classe {@link HarmonicMean}.
 *
 * @author Rodrigo Miotto Slongo
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
				Arguments.of(toBigDecimalArray(42.0), new BigDecimal("42")),
				Arguments.of(toBigDecimalArray(3.0, 2.0, 10.0), new BigDecimal("3.21428571428572")),
				Arguments.of(toBigDecimalArray(4.0, 2.0, 8.0), new BigDecimal("3.42857142857143")),
				Arguments.of(toBigDecimalArray(5.0, 3.9, 2.44, 1.11, 143.99, 2.56), new BigDecimal ("2.77172470364753"))
		);
	}

	/**
	 * Testa o cálculo da Média Geométrica realizado pelo método {@link HarmonicMean#harmonicMean(BigDecimal...)}.
	 *
	 * @param in       Dados de entrada.
	 * @param expected Resultado esperado.
	 */
	@ParameterizedTest
	@MethodSource("harmonicMeanTest1Values")
	@DisplayName("Deve calcular a média sem modificar os dados de entrada")
	void harmonicMeanTest1(BigDecimal[] in, BigDecimal expected) {
		BigDecimal actual = HarmonicMean.harmonicMean(in);

		assertEquals(expected, actual);
	}

	@Test
	@DisplayName("Deve lançar uma exceção para um array vazio")
	void harmonicMeanTest2() {
		BigDecimal[] in = new BigDecimal[]{};

		assertThrows(ArithmeticException.class, () -> GeometricMean.geometricMean(in));
	}

}
