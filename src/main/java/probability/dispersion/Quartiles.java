package probability.dispersion;

import java.math.BigDecimal;

import static probability.Utils.MATH_CONTEXT;
import static probability.Utils.sort;

/**
 * Fornece o métodos {@link #quartile(Quartile, BigDecimal...)} para realizar o
 * cálculo dos quartis de um conjunto de valores reais.
 *
 * @author Bernardo Tarnowski Dallarosa
 * @author Lucas da Paz
 */
public final class Quartiles {

	private Quartiles() {
	}

	/**
	 * Calcula o quartil desejado para o conjunto de valores informado.
	 *
	 * @param q      O {@link Quartile quartil} desejado.
	 * @param values Conjunto sobre o qual ocorrerá o cálculo; não pode ser vazio.
	 * @return O valor correspondente ao quartil calculado.
	 */
	public static BigDecimal quartile(Quartile q, BigDecimal... values) {
		if (values.length == 0) {
			throw new IllegalArgumentException("O conjunto não pode ser vazio.");
		}

		BigDecimal[] sorted = sort(values);
		BigDecimal position = BigDecimal.valueOf(sorted.length + 1).multiply(q.percentValue);

		return interpolate(position, sorted);
	}

	/**
	 * Cálculo da interpolação a partir dos valores ordenados e do índice informado.
	 *
	 * @param values   Conjunto de valores ordenados.
	 * @param position Índice calculado.
	 * @return Resultado da interpolação.
	 */
	private static BigDecimal interpolate(BigDecimal position, BigDecimal... values) {
		int index = position.intValue();
		BigDecimal fraction = position.remainder(BigDecimal.valueOf(1.0), MATH_CONTEXT);

		BigDecimal lower = values[index - 1];
		BigDecimal upper = values[index];

		return lower.add(fraction.multiply(upper.subtract(lower))).stripTrailingZeros();
	}

	public enum Quartile {
		Q1("0.25"), Q2("0.5"), Q3("0.75");

		private final BigDecimal percentValue;

		Quartile(String percentValue) {
			this.percentValue = new BigDecimal(percentValue);
		}
	}
}
