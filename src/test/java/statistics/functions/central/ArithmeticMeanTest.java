package statistics.functions.central;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static statistics.Utils.toBigDecimalArray;

/**
 * Testa a classe {@link ArithmeticMean}.
 *
 * @author Rodrigo Miotto Slongo
 */
public class ArithmeticMeanTest {
	/**
	 * Fornece os argumentos de entrada para {@link #arithmeticMeanTest1}.
	 *
	 * @return Stream de argumentos onde cada elemento contém, respectivamente,
	 * o array de entrada e o resultado esperado para o cálculo da média aritmética.
	 */
	static Stream<Arguments> arithmeticMeanTest1Values() {
		return Stream.of(
			Arguments.of(toBigDecimalArray(42.0), new BigDecimal("42")),
			Arguments.of(toBigDecimalArray(5.0, -2.0, 5.0, 2.0), new BigDecimal("2.5")),
			Arguments.of(toBigDecimalArray(5.0, 3.9, 2.44, 1.11, 143.99, 2.56), new BigDecimal("26.5")),
			Arguments.of(toBigDecimalArray(12.4, 3.9, 12.125, 23.15, 1.119, 42.21, -4.404, 2.0, 8.0, 1.5), new BigDecimal("10.2"))
		);
	}

	@ParameterizedTest
	@MethodSource("arithmeticMeanTest1Values")
	@DisplayName("Deve calcular a média aritmética")
	void arithmeticMeanTest1(BigDecimal[] in, BigDecimal expected) {
		BigDecimal actual = ArithmeticMean.arithmeticMean(in);

		assertEquals(expected, actual);
	}

	@Test
	@DisplayName("Deve lançar exceção para array vazio")
	void arithmeticMeanTest2() {
		BigDecimal[] in = new BigDecimal[]{};

		assertThrows(ArithmeticException.class, () -> ArithmeticMean.arithmeticMean(in));
	}
}
