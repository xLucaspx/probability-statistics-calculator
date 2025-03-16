package probability.dispersion;

import java.math.BigDecimal;
import java.util.Arrays;

import static probability.Utils.MATH_CONTEXT;
import static probability.central.ArithmeticMean.arithmeticMean;

/**
 * Fornece os métodos {@link #populationVariance} e {@link #sampleVariance}
 * ára realizar o cálculo da variância para um conjunto de valores reais.
 *
 * @author Lucas da Paz
 * @author Rodrigo Miotto Slongo
 */
public class Variance {

	/**
	 * Realiza o cálculo de variáncia da população para o conjunto de dados
	 * passado como argumento.
	 * <p>
	 * <strong>Fórmula</strong>: &sigma;&sup2; = &sum;(xi - &mu;)&sup2; / N
	 * <ul>
	 *   <li><strong>&sigma;&sup2;</strong>: Variância da população;</li>
	 *   <li><strong>xi</strong>: Cada valor individual no conjunto de dados;</li>
	 *   <li><strong>&mu;</strong>: Média da população;</li>
	 *   <li><strong>N</strong>: Número total de observações na população.</li>
	 * </ul>
	 *
	 * @param values Conjunto sobre o qual ocorrerá o cálculo; não pode ser vazio.
	 * @return Valor correspondente à variância populacional do conjunto.
	 * @throws ArithmeticException  Se o conjunto de dados informado for vazio ou unitário.
	 * @throws NullPointerException Se o conjunto de dados informado for nulo.
	 */
	public static BigDecimal populationVariance(BigDecimal... values) {
		BigDecimal n = BigDecimal.valueOf(values.length);
		return variance(n, values);
	}

	/**
	 * Realiza o cálculo de variáncia da amostra para o conjunto de dados
	 * passado como argumento.
	 * <p>
	 * <strong>Fórmula</strong>: s&sup2; = &sum;(xi - x&#772;)&sup2; / (n - 1)
	 * <ul>
	 *   <li><strong>s&sup2;</strong>: Variância da amostra;</li>
	 *   <li><strong>xi</strong>: Cada valor individual no conjunto de dados;</li>
	 *   <li><strong>x&#772;</strong>: Média da amostra;</li>
	 *   <li><strong>n</strong>: Número de observações da amostra.</li>
	 * </ul>
	 *
	 * @param values Conjunto sobre o qual ocorrerá o cálculo; não pode ser vazio.
	 * @return Valor correspondente à variância amostral para o conjunto.
	 * @throws ArithmeticException  Se o conjunto de dados informado for vazio ou unitário.
	 * @throws NullPointerException Se o conjunto de dados informado for nulo.
	 */
	public static BigDecimal sampleVariance(BigDecimal... values) {
		BigDecimal nMinusOne = BigDecimal.valueOf(values.length - 1);
		return variance(nMinusOne, values);
	}

	/**
	 * Realiza o cálculo da variância do conjunto de valores passado como argumento
	 * utilizando o parâmetro {@code n} informado.
	 *
	 * @param n      Valor sobre o qual será dividido o somatório para obter a variância; difere
	 *               entre as variâncias {@link #sampleVariance amostral} e {@link #populationVariance populacional}.
	 * @param values Conjunto sobre o qual ocorrerá o cálculo; não pode ser vazio.
	 * @return Valor correspondente à variância calculada.
	 */
	private static BigDecimal variance(BigDecimal n, BigDecimal... values) {
		BigDecimal arithmeticMean = arithmeticMean(values);
		BigDecimal somatory = arithmeticMeanSomatory(arithmeticMean, values);

		return somatory.divide(n, MATH_CONTEXT).stripTrailingZeros();
	}

	/**
	 * Realiza o somatório utilizado para calcular a variância para o conjunto de dados
	 * informado: subtrai cada valor pela média aritmética e eleva ao quadrado; o somatório
	 * é o resultado da soma destes valores.
	 *
	 * @param arithmeticMean A média aritmética do conjunto.
	 * @param values         Conjunto sobre o qual ocorrerá o cálculo.
	 * @return O resultado do somatório.
	 */
	private static BigDecimal arithmeticMeanSomatory(BigDecimal arithmeticMean, BigDecimal... values) {
		return Arrays.stream(values).map(x -> x.subtract(arithmeticMean).pow(2)).reduce(BigDecimal.ZERO, BigDecimal::add);
	}
}
