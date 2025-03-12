package probability.central;

/**
 * Classe que tem a responsábilidade de calcular a {@code Média Aritimédica}
 *
 * @author Rodrigo Miotto Slongo (Slongo11)
 */
public final class ArithmeticMean {
	private ArithmeticMean() {}
	/**
	 * Método que calcula a {@code Média Aritimética} com um conjunto de {@link Number}.
	 * @param setOfNumbers a ser calculado a {@code Média Aritimética}.
	 * @return a {@code Média Aritimética} referente ao conjunto de {@link Number}.
	 * @throws ArithmeticException se o conjunto de dados for vazio.
	 */
	public static Double arithmeticMean(Double... setOfNumbers) {
		Double sum = 0.0;
		Integer elementsQuantity = setOfNumbers.length;
		for (int i = 0; i < elementsQuantity; i++) {
			// Realiza a soma de todos os elementos da lista/conjunto de dados.
			sum += setOfNumbers[i];
		}

		Double arithmeticMean = sum / elementsQuantity;
		if(Double.isNaN(arithmeticMean)) {
			throw new ArithmeticException();
		}
		return arithmeticMean;
	}
}
