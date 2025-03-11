package probability.central;

import java.util.List;
/**
 * Classe que tem a responsábilidade de calcular a {@code Média Aritimédica}
 *
 * @author Rodrigo Miotto Slongo (Slongo11)
 */
public class arithmeticMean {
	/**
	 * Método que calcula a {@code Média Aritimética} com um conjunto de {@link Number}.
	 * @param conjuntoNumeros a ser calculado a {@code Média Aritimética}.
	 * @return a {@code Média Aritimética} referente ao conjunto de {@link Number}.
	 */
	public static double calculaMediaAritimetica(List<Number> conjuntoNumeros) {
		if (conjuntoNumeros.isEmpty()) {
			return 0.0;
		}
		double soma = 0;
		int quantidadeDeElementos = conjuntoNumeros.size();
		for (int i = 0; i < quantidadeDeElementos; i++) {
			// Realiza a soma de todos os elementos da lista/conjunto de dados.
			soma += conjuntoNumeros.get(i).doubleValue();
		}

		double mediaAritimetica = soma / quantidadeDeElementos;

		return mediaAritimetica;
	}
}
