package statistics.functions.dispersion;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static statistics.Utils.toBigDecimalArray;

/**
 * Testa a classe {@link Outliers}.
 *
 * @author Bernardo Tarnowski Dallarosa
 * @author Lucas da Paz
 */
class OutliersTest {

	/**
	 * Fornece os argumentos de entrada para
	 * {@link #outliersTest(BigDecimal[], BigDecimal, BigDecimal)}.
	 *
	 * @return Stream de argumentos onde cada elemento contém, respectivamente,
	 * o array de entrada e o resultado esperado para os outliers de limite
	 * superior e inferior.
	 */
	static Stream<Arguments> outliersTestValues() {
		return Stream.of(
			Arguments.of(toBigDecimalArray(1.0, 2.0, 3.0, 100.0, 200.0, 1500.0),
									 new BigDecimal("-783.125"),
									 new BigDecimal("1309.875")
			),
			Arguments.of(toBigDecimalArray(-123123.0, -5.0, -1.0, 0.0, 1.0, 23.0),
									 new BigDecimal("-76971"),
									 new BigDecimal("46193")
			),
			Arguments.of(toBigDecimalArray(1.0, 10000.0, 10001.0, 10002.0, 10003.0, 10004.0, 10005.0, 10000000.0),
									 new BigDecimal("9993.5"),
									 new BigDecimal("10011.5")
			),
			Arguments.of(toBigDecimalArray(1.0, 2.0, 3.0, 4.0, 5.0), new BigDecimal("-3"), new BigDecimal("9")),
			Arguments.of(toBigDecimalArray(1, 2, 3), new BigDecimal("-2"), new BigDecimal("6"))
		);
	}

	/**
	 * Fornece os argumentos de entrada para {@link #outliersTestNullValues(BigDecimal[])}.
	 *
	 * @return Stream de argumentos onde cada elemento contém um array com mais de três posições,
	 * sendo pelo menos uma nula.
	 */
	static Stream<Arguments> outliersTestNullValues() {
		return Stream.of(
			Arguments.of((Object) new BigDecimal[]{null, BigDecimal.valueOf(2), null, BigDecimal.ZERO, null}),
			Arguments.of((Object) new BigDecimal[]{
				BigDecimal.valueOf(1), BigDecimal.valueOf(3), null, null, BigDecimal.valueOf(6)
			})
		);
	}

	@ParameterizedTest
	@MethodSource("outliersTestValues")
	@DisplayName("Deve identificar corretamente os outliers do array")
	void outliersTest(BigDecimal[] in, BigDecimal lowerExpected, BigDecimal upperExpected) {
		BigDecimal actualLowerBound = Outliers.outlier(Outliers.Bound.LOWERBOUND, in);
		BigDecimal actualUpperBound = Outliers.outlier(Outliers.Bound.UPPERBOUND, in);

		assertEquals(lowerExpected, actualLowerBound);
		assertEquals(upperExpected, actualUpperBound);
	}

	@ParameterizedTest
	@NullAndEmptySource
	@DisplayName("Deve lançar exceção para array nulo ou vazio")
	void outliersTestNullAndEmptyArray(BigDecimal[] in) {
		assertThrows(IllegalArgumentException.class, () -> Outliers.outlier(Outliers.Bound.LOWERBOUND, in));
		assertThrows(IllegalArgumentException.class, () -> Outliers.outlier(Outliers.Bound.UPPERBOUND, in));
	}

	@ParameterizedTest
	@MethodSource("outliersTestNullValues")
	@DisplayName("Deve lançar exceção para array com mais de três posições e com elementos nulos")
	void outliersTestNullValues(BigDecimal... in) {
		assertThrows(NullPointerException.class, () -> Outliers.outlier(Outliers.Bound.LOWERBOUND, in));
		assertThrows(NullPointerException.class, () -> Outliers.outlier(Outliers.Bound.UPPERBOUND, in));
	}
}
