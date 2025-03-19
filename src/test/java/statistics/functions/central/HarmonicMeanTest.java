package statistics.functions.central;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static statistics.Utils.toBigDecimalArray;

/**
 * Testa a classe {@link HarmonicMean}.
 *
 * @author Lucas da Paz
 * @author Rodrigo Miotto Slongo
 */
public class HarmonicMeanTest {

	/**
	 * Fornece os argumentos de entrada para {@link #harmonicMeanTest1}.
	 *
	 * @return Stream de argumentos onde cada elemento contém, respectivamente,
	 * o array de entrada e o resultado esperado para o cálculo da média harmônica.
	 */
	static Stream<Arguments> harmonicMeanTest1Values() {
		return Stream.of(
			Arguments.of(toBigDecimalArray(42.0), new BigDecimal("42")),
			Arguments.of(toBigDecimalArray(3.0, 2.0, 10.0), new BigDecimal("3.214285714285714")),
			Arguments.of(toBigDecimalArray(4.0, 2.0, 8.0), new BigDecimal("3.428571428571429")),
			Arguments.of(toBigDecimalArray(5.0, 3.9, 2.44, 1.11, 143.99, 2.56), new BigDecimal("2.771724703647531"))
		);
	}

	/**
	 * Fornece os argumentos de entrada para {@link #harmonicMeanTest2}.
	 *
	 * @return Stream de argumentos onde cada elemento contém o array de entrada.
	 */
	static Stream<Arguments> harmonicMeanTest2Values() {
		return Stream.of(
			Arguments.of((Object) toBigDecimalArray(42.0, 0)),
			Arguments.of((Object) toBigDecimalArray(3.0, -2.0, 10.0)),
			Arguments.of((Object) toBigDecimalArray(-4.0, 0.0, -8.0, 0))
		);
	}

	@ParameterizedTest
	@MethodSource("harmonicMeanTest1Values")
	@DisplayName("Deve calcular a média harmônica")
	void harmonicMeanTest1(BigDecimal[] in, BigDecimal expected) {
		BigDecimal actual = HarmonicMean.harmonicMean(in);

		assertEquals(expected, actual);
	}

	@ParameterizedTest
	@EmptySource
	@MethodSource("harmonicMeanTest2Values")
	@DisplayName("Deve lançar exceção para array vazio ou com elementos não positivos")
	void harmonicMeanTest2(BigDecimal... in) {
		assertThrows(ArithmeticException.class, () -> HarmonicMean.harmonicMean(in));
	}
}
