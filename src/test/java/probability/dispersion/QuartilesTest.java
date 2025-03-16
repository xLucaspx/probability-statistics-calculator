package probability.dispersion;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static probability.Utils.toBigDecimalArray;
import static probability.dispersion.Quartiles.Quartile.Q1;
import static probability.dispersion.Quartiles.Quartile.Q2;
import static probability.dispersion.Quartiles.Quartile.Q3;

/**
 * Testa a classe {@link Quartiles}.
 *
 * @author Bernardo Tarnowski Dallarosa
 * @author Lucas da Paz
 */
class QuartilesTest {

	/**
	 * Fornece os argumentos de entrada para {@link #quartilesTest}.
	 *
	 * @return Stream de argumentos onde cada elemento contém, respectivamente,
	 * o array de entrada e o resultado esperado para o cálculo dos quartis (Q1,
	 * Q2 e Q3, respectivamente).
	 */
	static Stream<Arguments> quartilesTestValues() {
		return Stream.of(
			Arguments.of(toBigDecimalArray(15.0, 5.0, 18.0, 7.0, 12.0, 20.0, 10.0),
									 new BigDecimal("7"),
									 new BigDecimal("12"),
									 new BigDecimal("18")
			),
			Arguments.of(toBigDecimalArray(36.0, 40.0, 7.0, 15.0, 39.0, 41.0),
									 new BigDecimal("13"),
									 new BigDecimal("37.5"),
									 new BigDecimal("40.25")
			),
			Arguments.of(toBigDecimalArray(21.0, 7.0, 3.0, 8.0, 5.0, 14.0, 12.0, 18.0, 13.0),
									 new BigDecimal("6"),
									 new BigDecimal("12"),
									 new BigDecimal("16")
			),
			Arguments.of(toBigDecimalArray(7.0, 1.0, 5.0, 3.0),
									 new BigDecimal("1.5"),
									 new BigDecimal("4"),
									 new BigDecimal("6.5")
			),
			Arguments.of(toBigDecimalArray(-1.5, -0.5, 2.5, 3.5, 4.5, 5.5, 6.5, 7.5, 8.5),
									 new BigDecimal("1"),
									 new BigDecimal("4.5"),
									 new BigDecimal("7")
			),
			Arguments.of(toBigDecimalArray(9.75, 2.25, 5.5, 8.0, 1.25, 7.5, 4.0, 6.25),
									 new BigDecimal("2.6875"),
									 new BigDecimal("5.875"),
									 new BigDecimal("7.875")
			),
			Arguments.of(toBigDecimalArray(20.0, 15.5, 10.2, 5.8, 30.3, 25.7, 40.4, 35.9),
									 new BigDecimal("11.525"),
									 new BigDecimal("22.85"),
									 new BigDecimal("34.5")
			)
		);
	}

	/**
	 * Fornece os argumentos de entrada para {@link #quartilesTestInvalid} e
	 * {@link #quartilesTestNullValues}.
	 *
	 * @return Stream de argumentos onde cada elemento contém o array de
	 * entrada.
	 */
	static Stream<Arguments> quartilesTestInvalidValues() {
		return Stream.of(
			Arguments.of((Object) null),
			Arguments.of((Object) new BigDecimal[]{null, BigDecimal.valueOf(2), null}),
			Arguments.of((Object) new BigDecimal[]{BigDecimal.valueOf(1), BigDecimal.valueOf(3), null, null, BigDecimal.valueOf(6)}),
			Arguments.of((Object) new BigDecimal[]{BigDecimal.valueOf(3.9), null, BigDecimal.valueOf(23.15), BigDecimal.valueOf(42.21)})
		);
	}

	@ParameterizedTest
	@MethodSource("quartilesTestValues")
	@DisplayName("Deve calcular corretamente os quartis do array")
	void quartilesTest(BigDecimal[] in, BigDecimal q1, BigDecimal q2, BigDecimal q3) {
		assertEquals(q1, Quartiles.quartile(Q1, in));
		assertEquals(q2, Quartiles.quartile(Q2, in));
		assertEquals(q3, Quartiles.quartile(Q3, in));
	}

	@ParameterizedTest
	@EmptySource
	@DisplayName("Deve lançar exceção para array vazio")
	void quartilesTestInvalid(BigDecimal[] in) {
		assertThrows(IllegalArgumentException.class, () -> Quartiles.quartile(Q1, in));
	}

	@ParameterizedTest
	@MethodSource("quartilesTestInvalidValues")
	@DisplayName("Deve lançar exceção para array com elementos nulos")
	void quartilesTestNullValues(BigDecimal... in) {
		assertThrows(NullPointerException.class, () -> Quartiles.quartile(Q2, in));
	}
}
