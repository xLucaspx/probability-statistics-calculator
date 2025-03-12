package probability.central;

import java.util.List;

/**
 * Classe que tem a responsábilidade de calcular a {@code Média Geométrica}
 *
 * @author Rodrigo Miotto Slongo (Slongo11)
 */
public final class GeometricMean {
	private GeometricMean() {}
	/**
	 * Método que calcula a {@code Média Geométrica} com um conjunto de {@link Number}.
	 * @param setOfNumbers a ser calculado {@code Média Geométrica}
	 * @return a {@code Média Geométrica} referente ao conjunto de {@link Number}.
	 * @throws ArithmeticException se o conjunto de dados for vazio.
	 */
	public static Double geometricMean(Double... setOfNumbers) {
		Double multply = 1.0;
		Integer elementsQuantity = setOfNumbers.length;
		for(Double number : setOfNumbers) {
			// Multiplica todos os elementos da lista/conjunto de números
			multply *= number;
		}
		// Calcula a potência em qual vai ser elevado
		Double power = 1.0/elementsQuantity;
		// Eleva na potencia o resultado a multiplicação
		Double mediaGeometrica = Math.pow(multply, power);
		if(Double.isNaN(mediaGeometrica)) {
			throw new ArithmeticException();
		}
		return mediaGeometrica;
	}

}
