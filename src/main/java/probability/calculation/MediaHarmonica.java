package probability.calculation;

import java.util.List;

public class MediaHarmonica {
	/**
	 * Método que calcula a {@code Média Harmônica} com um conjunto de {@link Number}.
	 * @param conjuntoNumeros a ser calculado {@code Média Harmônica}
	 * @return a {@code Média Harmônica} referente ao conjunto de {@link Number}.
	 */
	public static double calculaMediaHarmonica(List<Number> conjuntoNumeros) {
		if (conjuntoNumeros.isEmpty()) {
			return 0.0;
		}
		int quantidadeDeElementos = conjuntoNumeros.size();

		double somatorioResultado = somatorioDosNumerosElevadosNaMenosUm(conjuntoNumeros);

		double mediaArmonica = quantidadeDeElementos / somatorioResultado;

		return mediaArmonica;
	}

	/**
	 * Realiza a {@code somatório} dos elementos {@code elevados na -1}.
	 * @param conjuntoNumeros que é utilizado para a soma.
	 * @return o resultado da soma do {@code somatório}.
	 */
	private static double somatorioDosNumerosElevadosNaMenosUm(List<Number> conjuntoNumeros) {
		double soma = 0;
		for (Number conjuntoNumero : conjuntoNumeros) {
			//  O somatório x^-1.
			soma += 1 / conjuntoNumero.doubleValue();
		}
		return soma;
	}
}
