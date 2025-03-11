package probability.central;

import java.util.List;

/**
 * Classe que tem a responsábilidade de calcular a {@code Média Geométrica}
 *
 * @author Rodrigo Miotto Slongo (Slongo11)
 */
public class GeometricMean {
	/**
	 * Método que calcula a {@code Média Geométrica} com um conjunto de {@link Number}.
	 * @param setOfNumbers a ser calculado {@code Média Geométrica}
	 * @return a {@code Média Geométrica} referente ao conjunto de {@link Number}.
	 */
	public static double geometricMean(List<Number> setOfNumbers) {
		if (setOfNumbers.isEmpty()) {
			return 0.0;
		}
		double multply = 1;
		int elementsQuantity = setOfNumbers.size();
		for(Number number : setOfNumbers) {
			// Multiplica todos os elementos da lista/conjunto de números
			multply *= number.doubleValue();
		}
		// Calcula a potência em qual vai ser elevado
		double power = 1.0/elementsQuantity;
		// Eleva na potencia o resultado a multiplicação
		double mediaGeometrica = Math.pow(multply, power);

		return mediaGeometrica;
	}

}
