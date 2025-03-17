package probability.dispersion;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static probability.Utils.toBigDecimalArray;
import probability.dispersion.Outliers.Bound;

/**
 * Testa a classe {@link Outliers}.
 *
 * @author Bernardo Tarnowski Dallarosa
 */
class OutliersTest {

    /**
     * Fornece os argumentos de entrada para
     * {@link #outliersTest(BigDecimal[], BigDecimal[], BigDecimal[])}.
     *
     * @return Stream de argumentos onde cada elemento contém, respectivamente,
     * o array de entrada, o resultado esperado para os outliers de limite
     * superior e inferior.
     */
    static Stream<Arguments> outliersTestValues() {
        return Stream.of(
                Arguments.of(toBigDecimalArray(1.0, 2.0, 3.0, 100.0, 200.0, 1500.0), toBigDecimalArray(1500), new BigDecimal[]{}),
                Arguments.of(toBigDecimalArray(-123123.0, -5.0, -1.0, 0.0, 1.0, 23.0), new BigDecimal[]{}, toBigDecimalArray(-123123)),
                Arguments.of(toBigDecimalArray(1.0, 10000.0, 10001.0, 10002.0, 10003.0, 10004.0, 10005.0, 10000000.0), toBigDecimalArray(10000000), toBigDecimalArray(1)),
                Arguments.of(toBigDecimalArray(1.0, 2.0, 3.0, 4.0, 5.0), new BigDecimal[]{}, new BigDecimal[]{})
        );
    }

    /**
     * Fornece os argumentos de entrada para testes de exceção com arrays nulos
     * ou vazios para {@link #outliersTestNullArray(BigDecimal[])}.
     *
     * @return Stream de argumentos onde cada elemento contém um array inválido.
     */
    static Stream<Arguments> outliersTestInvalidValues1() {
        return Stream.of(
                Arguments.of((Object) null),
                Arguments.of((Object) new BigDecimal[]{})
        );
    }

    /**
     * Fornece os argumentos de entrada para
     * {@link #outliersTestNullValues(BigDecimal[])}.
     *
     * @return Stream de argumentos onde cada elemento contém um array inválido.
     */
    static Stream<Arguments> outliersTestInvalidValues2() {
        return Stream.of(
                Arguments.of((Object) new BigDecimal[]{null, BigDecimal.valueOf(2), null}),
                Arguments.of((Object) new BigDecimal[]{BigDecimal.valueOf(1), BigDecimal.valueOf(3), null, null, BigDecimal.valueOf(6)})
        );
    }

    @ParameterizedTest
    @MethodSource("outliersTestValues")
    @DisplayName("Deve identificar corretamente os outliers do array")
    void outliersTest(BigDecimal[] in, BigDecimal[] upperExpected, BigDecimal[] lowerExpected) {
        List<BigDecimal> actualOutliersListUB = Outliers.detectOutliers(Bound.UPPERBOUND, in);
        List<BigDecimal> actualOutliersListLB = Outliers.detectOutliers(Bound.LOWERBOUND, in);

        BigDecimal[] actualOutliersUB = actualOutliersListUB.toArray(new BigDecimal[0]);
        BigDecimal[] actualOutliersLB = actualOutliersListLB.toArray(new BigDecimal[0]);

        assertArrayEquals(upperExpected, actualOutliersUB);
        assertArrayEquals(lowerExpected, actualOutliersLB);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Deve lançar exceção para array nulo ou vazio")
    void outliersTestNullArray(BigDecimal[] in) {
        assertThrows(NullPointerException.class, () -> Outliers.detectOutliers(Bound.UPPERBOUND, in));
        assertThrows(NullPointerException.class, () -> Outliers.detectOutliers(Bound.LOWERBOUND, in));
    }

    @ParameterizedTest
    @MethodSource("outliersTestInvalidValues2")
    @DisplayName("Deve lançar exceção para array com elementos nulos")
    void outliersTestNullValues(BigDecimal... in) {
        assertThrows(IllegalArgumentException.class, () -> Outliers.detectOutliers(Bound.UPPERBOUND, in));
        assertThrows(IllegalArgumentException.class, () -> Outliers.detectOutliers(Bound.LOWERBOUND, in));
    }
}
