package probability.dispersion;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import probability.dispersion.Quartiles.Quartile;

/**
 * Fornece o método {@link #detectOutliers(BigDecimal...)} para identificar os
 * outliers de um conjunto de valores.
 *
 * @author Bernardo Tarnowski Dallarosa
 */
public final class Outliers {

    private Outliers() {
    }

    /**
     * Identifica os outliers em um conjunto de valores usando o intervalo
     * interquartil (IQR).
     *
     * @param bound Determina qual limite (inferior ou superior) é analisado.
     * @param values Conjunto de valores a serem analisados.
     * @return Lista de outliers identificados.
     */
    public static List<BigDecimal> detectOutliers(Bound bound, BigDecimal... values) {
        if (values == null || values.length == 0) {
            throw new NullPointerException("O conjunto de dados informado não pode ser nulo ou vazio!");
        }
        if (Arrays.stream(values).anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException("O conjunto de dados não pode conter valores nulos!");
        }
        if (values.length < 3) {
            return Collections.emptyList();
        }

        BigDecimal q1 = Quartiles.quartile(Quartile.Q1, values);
        BigDecimal q3 = Quartiles.quartile(Quartile.Q3, values);
        BigDecimal multiply = new BigDecimal(1.5);
        BigDecimal iqr = q3.subtract(q1);

        BigDecimal lowerBound = q1.subtract(iqr.multiply(multiply)).stripTrailingZeros();
        BigDecimal upperBound = q3.add(iqr.multiply(multiply)).stripTrailingZeros();

        List<BigDecimal> outliers = Arrays.stream(values)
                .filter(value -> detectBound(bound, value, lowerBound, upperBound))
                .map(value -> value.stripTrailingZeros())
                .map(value -> new BigDecimal(value.toPlainString()))
                .collect(Collectors.toList());
        return outliers;
    }

    /**
     * Determina qual limite é analisado para filtrar os outliers respectivos.
     *
     * @param bound Determinado em {@link #detectOutliers(Bound, BigDecimal...)}
     * para analisar o limite.
     * @param value Valor que é analisado.
     * @param lower Limite inferior.
     * @param upper Limite superior.
     * @return
     */
    private static boolean detectBound(Bound bound, BigDecimal value, BigDecimal lower, BigDecimal upper) {
        return bound == Bound.LOWERBOUND ? value.compareTo(lower) < 0 : value.compareTo(upper) > 0;
    }

    public enum Bound {
        LOWERBOUND, UPPERBOUND;
    }
}
