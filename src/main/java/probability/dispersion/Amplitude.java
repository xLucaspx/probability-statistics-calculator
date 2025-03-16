package probability.dispersion;

import probability.Utils;

import java.math.BigDecimal;

import static probability.Utils.max;
import static probability.Utils.min;

/**
 * Fornece o método {@link #amplitude(BigDecimal...)} para realizar
 * o cálculo da amplitude para um conjunto de valores reais.
 *
 * @author Lucas da Paz
 */
public final class Amplitude {

	private Amplitude() {
	}

	/**
	 * Realiza o cálculo da amplitude do conjunto de valores passado
	 * como argumento.
	 *
	 * @param values Conjunto sobre o qual ocorrerá o cálculo.
	 * @return Valor correspondente à amplitude do conjunto.
	 * @throws IllegalArgumentException Se o conjunto de dados informado for nulo
	 *                                  ou vazio.
	 * @see Utils#min(BigDecimal...)
	 * @see Utils#max(BigDecimal...)
	 */
	public static BigDecimal amplitude(BigDecimal... values) {
		BigDecimal max = max(values);
		BigDecimal min = min(values);

		return max.subtract(min);
	}
}
