package probability.central;

import java.util.List;

/**
 * Classe que tem a responsábilidade de calcular a {@code Média Geométrica}
 *
 * @author Rodrigo Miotto Slongo (Slongo11)
 */
public class geometricMean {
	/**
	 * Método que calcula a {@code Média Geométrica} com um conjunto de {@link Number}.
	 * @param conjuntoNumeros a ser calculado {@code Média Geométrica}
	 * @return a {@code Média Geométrica} referente ao conjunto de {@link Number}.
	 */
	public static double calculaMediaGeometrica(List<Number> conjuntoNumeros) {
		if (conjuntoNumeros.isEmpty()) {
			return 0.0;
		}
		double multiplicacao = 1;
		int quantidadeDeElementos = conjuntoNumeros.size();
		for(Number numero : conjuntoNumeros) {
			// Multiplica todos os elementos da lista/conjunto de números
			multiplicacao *= numero.doubleValue();
		}
		// Calcula a potência em qual vai ser elevado
		double potenciaElevada = 1.0/quantidadeDeElementos;
		// Eleva na potencia o resultado a multiplicação
		double mediaGeometrica = Math.pow(multiplicacao, potenciaElevada);

		return mediaGeometrica;
	}

}
