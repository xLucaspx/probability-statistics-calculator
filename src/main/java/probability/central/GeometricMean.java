package probability.central;


import com.google.common.math.BigDecimalMath;

import java.math.BigDecimal;

import static probability.Utils.MATH_CONTEXT;

/**
 * Fornece o método {@link #geometricMean(BigDecimal...)} para realizar o
 * cálculo da média geométrica para um conjunto de valores reais.
 *
 * @author Rodrigo Miotto Slongo
 */
public final class GeometricMean {
	private GeometricMean() {}
	/**
	 * Método que calcula a code Média Geométrica com um conjunto de {@link BigDecimal}.
	 * @param setOfNumbers a ser calculado code Média Geométrica
	 * @return a Média Geométrica referente ao conjunto de {@link BigDecimal}.
	 * @throws ArithmeticException se o conjunto de dados for vazio.
	 */
	public static BigDecimal geometricMean(BigDecimal... setOfNumbers) {
		BigDecimal multply = BigDecimal.valueOf(1);
		Integer elementsQuantity = setOfNumbers.length;
		for(BigDecimal number : setOfNumbers) {
			// Multiplica todos os elementos da lista/conjunto de números
			multply = multply.multiply(number);
		}
		// Calcula a potência em qual vai ser elevado
		BigDecimal one = BigDecimal.valueOf(1);
		BigDecimal power =one.divide(BigDecimal.valueOf(elementsQuantity),MATH_CONTEXT);
		// Eleva na potência o resultado a multiplicação
		BigDecimal mediaGeometrica = new BigDecimal(Math.pow(multply.doubleValue(), power.doubleValue()), MATH_CONTEXT);

		return mediaGeometrica.stripTrailingZeros();
	}

}
