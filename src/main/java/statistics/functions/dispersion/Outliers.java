package statistics.functions.dispersion;

import java.math.BigDecimal;

/**
 * Fornece o método {@link #outlier(Bound, BigDecimal...)} para
 * identificar os outliers de um conjunto de valores.
 *
 * @author Bernardo Tarnowski Dallarosa
 * @author Lucas da Paz
 */
public final class Outliers {

	private Outliers() {
	}

	/**
	 * Identifica os outliers em um conjunto de valores usando o intervalo
	 * interquartil (IQR).
	 *
	 * @param bound  Determina qual limite (inferior ou superior) é analisado.
	 * @param values Conjunto de valores a serem analisados.
	 * @return Outlier calculado.
	 * @throws IllegalArgumentException Se o conjunto informado for nulo ou vazio.
	 * @see Bound
	 */
	public static BigDecimal outlier(Bound bound, BigDecimal... values) {
		if (values == null || values.length == 0) {
			throw new IllegalArgumentException("O conjunto de dados informado não pode ser nulo ou vazio!");
		}

		BigDecimal q1 = Quartiles.quartile(Quartiles.Quartile.Q1, values);
		BigDecimal q3 = Quartiles.quartile(Quartiles.Quartile.Q3, values);
		BigDecimal iqrMultiplied = q3.subtract(q1).multiply(new BigDecimal("1.5"));

		return switch (bound) {
			case LOWERBOUND -> q1.subtract(iqrMultiplied).stripTrailingZeros();
			case UPPERBOUND -> q3.add(iqrMultiplied).stripTrailingZeros();
		};
	}

	public enum Bound {
		LOWERBOUND, UPPERBOUND
	}
}
