package probability.central;

import java.util.List;

/**
 * Classe que tem a responsábilidade de calcular a {@code Média Harmônica}
 *
 * @author Rodrigo Miotto Slongo (Slongo11)
 */
public class HarmonicMean {
	/**
	 * Método que calcula a {@code Média Harmônica} com um conjunto de {@link Number}.
	 * @param setOfNumbers a ser calculado {@code Média Harmônica}
	 * @return a {@code Média Harmônica} referente ao conjunto de {@link Number}.
	 */
	public static double harmonicMean(List<Number> setOfNumbers) {
		if (setOfNumbers.isEmpty()) {
			return 0.0;
		}
		int elementsQuantity = setOfNumbers.size();

		double resultSomatory = somatoryOfNumbersRisedMinusOne(setOfNumbers);

		double harmonicMean = elementsQuantity / resultSomatory;

		return harmonicMean;
	}

	/**
	 * Realiza a {@code somatório} dos elementos {@code elevados na -1}.
	 * @param setOfNumbers que é utilizado para a soma.
	 * @return o resultado da soma do {@code somatório}.
	 */
	private static Double somatoryOfNumbersRisedMinusOne(List<Number> setOfNumbers) {
		Double sum = 0.0;
		for (Number number : setOfNumbers) {
			//  O somatório x^-1.
			sum += 1 / number.doubleValue();
		}
		return sum;
	}
}
