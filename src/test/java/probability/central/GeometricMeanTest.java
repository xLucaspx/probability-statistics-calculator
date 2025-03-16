package probability.central;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static probability.Utils.toBigDecimalArray;

/**
 * Testa a classe {@link GeometricMean}.
 *
 * @author Rodrigo Miotto Slongo
 */
public class GeometricMeanTest {

	/**
	 * Fornece os argumentos de entrada para {@link #geometricMeanTest1}.
	 *
	 * @return Stream de argumentos onde cada elemento contém, respectivamente,
	 * o array de entrada e o resultado esperado para o cálculo da média geométrica.
	 */
	static Stream<Arguments> geometricMeanTest1Values() {
		return Stream.of(
			Arguments.of(toBigDecimalArray(42.0), new BigDecimal("42")),
			Arguments.of(toBigDecimalArray(3.0, 2.0, 10.0), new BigDecimal("3.91486764116886")),
			Arguments.of(toBigDecimalArray(4.0, 2.0, 20.0), new BigDecimal("5.4288352331898")),
			Arguments.of(toBigDecimalArray(5.0, 3.9, 2.44, 1.11, 143.99, 2.56), new BigDecimal("5.18664613694522"))
		);
	}

	/**
	 * Fornece os argumentos de entrada para {@link #geometricMeanTest2}.
	 *
	 * @return Stream de argumentos onde cada elemento contém o array de entrada.
	 */
	static Stream<Arguments> geometricMeanTest2Values() {
		return Stream.of(
			Arguments.of((Object) toBigDecimalArray(42.0, 0)),
			Arguments.of((Object) toBigDecimalArray(3.0, -2.0, 10.0)),
			Arguments.of((Object) toBigDecimalArray(-4.0, 0.0, -8.0, 0))
		);
	}

	@ParameterizedTest
	@MethodSource("geometricMeanTest1Values")
	@DisplayName("Deve calcular a média geométrica")
	void geometricMeanTest1(BigDecimal[] in, BigDecimal expected) {
		BigDecimal actual = GeometricMean.geometricMean(in);

		assertEquals(expected, actual);
	}

	@ParameterizedTest
	@EmptySource
	@MethodSource("geometricMeanTest2Values")
	@DisplayName("Deve lançar exceção para array vazio ou com elementos não positivos")
	void geometricMeanTest2(BigDecimal... in) {
		assertThrows(ArithmeticException.class, () -> GeometricMean.geometricMean(in));
	}
}
