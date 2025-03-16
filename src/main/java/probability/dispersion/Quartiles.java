package probability.dispersion;

import java.math.BigDecimal;
import java.util.Arrays;

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
        return computePercentile(values, 0.25);
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
        return computePercentile(values, 0.50);
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
        return computePercentile(values, 0.75);
    }

    /**
     * Método auxiliar para calcular um percentil específico de um conjunto de
     * valores.
     *
     * @param values Conjunto de valores.
     * @param percentile Percentual desejado (0.25 para Q1, 0.50 para Q2, 0.75
     * para Q3).
     * @return Valor do percentil especificado.
     */
    private static BigDecimal computePercentile(BigDecimal[] values, double percentile) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException("O conjunto de dados informado não pode ser nulo ou vazio!");
        }

        if (Arrays.asList(values).contains(null)) {
            throw new NullPointerException("O conjunto de dados informado não pode conter valores nulos!");
        }

        BigDecimal[] sorted = sort(values);
        int n = sorted.length;

        BigDecimal position = BigDecimal.valueOf(n + 1).multiply(BigDecimal.valueOf(percentile));

        int index = position.intValue(); // Parte inteira
        BigDecimal fraction = position.remainder(BigDecimal.ONE); // Parte decimal

        if (index < 1) {
            return sorted[0]; // Retorna o menor valor
        } else if (index >= n) {
            return sorted[n - 1]; // Retorna o maior valor
        } else {
            // Cálculo de interpolação

            BigDecimal lower = sorted[index - 1];
            BigDecimal upper = sorted[index];

            return lower.add(fraction.multiply(upper.subtract(lower))).stripTrailingZeros();
        }
    }

}
