package probability.dispersion;

import java.math.BigDecimal;
import java.util.Arrays;

import static probability.Utils.MATH_CONTEXT;
import static probability.Utils.sort;

/**
 * Fornece os métodos {@link #quartile1}, {@link #quartile2} e
 * {@link #quartile3} para realizar o cálculo dos quartis de um conjunto de
 * valores reais.
 *
 * @author Bernardo Tarnowski Dallarosa
 */
public final class Quartiles {

    private Quartiles() {
    }

    /**
     * Calcula o primeiro quartil (Q1).
     *
     * @param values Conjunto sobre o qual ocorrerá o cálculo.
     * @return Valor correspondente ao primeiro quartil.
     * @throws IllegalArgumentException Se o conjunto de dados informado for
     * nulo ou vazio.
     * @throws NullPointerException Se o conjunto de dados informado contiver
     * valores nulos.
     */
    public static BigDecimal quartile1(BigDecimal... values) {
        return computePercentile(values, new BigDecimal(0.25));
    }

    /**
     * Calcula o segundo quartil (Q2), que é a mediana.
     *
     * @param values Conjunto sobre o qual ocorrerá o cálculo.
     * @return Valor correspondente ao segundo quartil.
     * @throws IllegalArgumentException Se o conjunto de dados informado for
     * nulo ou vazio.
     * @throws NullPointerException Se o conjunto de dados informado contiver
     * valores nulos.
     */
    public static BigDecimal quartile2(BigDecimal... values) {
        return computePercentile(values, new BigDecimal(0.5));
    }

    /**
     * Calcula o terceiro quartil (Q3).
     *
     * @param values Conjunto sobre o qual ocorrerá o cálculo.
     * @return Valor correspondente ao terceiro quartil.
     * @throws IllegalArgumentException Se o conjunto de dados informado for
     * nulo ou vazio.
     * @throws NullPointerException Se o conjunto de dados informado contiver
     * valores nulos.
     */
    public static BigDecimal quartile3(BigDecimal... values) {
        return computePercentile(values, new BigDecimal(0.75));
    }

    /**
     * Método auxiliar para calcular um percentil específico de um conjunto de
     * valores.
     *
     * @param values Conjunto de valores.
     * @param percentile Percentual desejado (0.25 para Q1, 0.50 para Q2, 0.75
     * para Q3).
     * @return Cálculo feito pela interpolação.
     */
    private static BigDecimal computePercentile(BigDecimal[] values, BigDecimal percentile) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException("O conjunto de dados informado não pode ser nulo ou vazio!");
        }

        if (Arrays.asList(values).contains(null)) {
            throw new NullPointerException("O conjunto de dados informado não pode conter valores nulos!");
        }

        BigDecimal[] sorted = sort(values);
        Integer n = sorted.length;

        BigDecimal position = BigDecimal.valueOf(n + 1).multiply(percentile);

        return interpolate(sorted, position);

    }

    /**
     * Cálculo da interpolação a partir dos valores ordenados e índice
     * calculado.
     *
     * @param arr Conjunto de valores ordenados.
     * @param position Posição calculada em {@link #computePercentile}.
     * @return Resultado da interpolação.
     */
    private static BigDecimal interpolate(BigDecimal[] arr, BigDecimal position) {

        Integer index = position.intValue();
        BigDecimal fraction = position.remainder(BigDecimal.ONE, MATH_CONTEXT);

        BigDecimal lower = arr[index - 1];
        BigDecimal upper = arr[index];

        return lower.add(fraction.multiply(upper.subtract(lower))).stripTrailingZeros();
    }

}
