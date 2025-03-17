package statistics.data;

import java.util.Arrays;

/**
 * Enumeração que representa os meses do ano.
 *
 * @author Lucas da Paz
 */
public enum Month {
	JANUARY(1, "Janeiro"),
	FEBRUARY(2, "Fevereiro"),
	MARCH(3, "Março"),
	APRIL(4, "Abril"),
	MAY(5, "Maio"),
	JUNE(6, "Junho"),
	JULY(7, "Julho"),
	AUGUST(8, "Agosto"),
	SEPTEMBER(9, "Setembro"),
	OCTOBER(10, "Outubro"),
	NOVEMBER(11, "Novembro"),
	DECEMBER(12, "Dezembro");

	private final int numeric;
	private final String name;

	/**
	 * Constrói o enum com os valores informados.
	 *
	 * @param numeric O número correspondente ao mês.
	 * @param name    O nome do mês.
	 */
	Month(int numeric, String name) {
		this.numeric = numeric;
		this.name = name;
	}

	/**
	 * Busca o {@link Month} correspondente ao valor informado.
	 *
	 * @param numeric O número do mês.
	 * @return O mês com o número correspondente.
	 * @throws IllegalArgumentException Se nenhum mês for encontrado, i.e.,
	 *                                  se o valor de entrada não estiver entre
	 *                                  {@code 1} e {@code 12}.
	 */
	public static Month from(int numeric) {
		return Arrays.stream(values())
			.filter(month -> month.numeric == numeric)
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("Ordinal sem mês correspondente: %d".formatted(numeric)));
	}

	/**
	 * @return Número correspondente ao mês; para o valor
	 * ordinal do enum utilize {@link #ordinal()}.
	 */
	public int numeric() {
		return numeric;
	}

	/**
	 * @return Nome do mês; para o nome da constante enum
	 * utilize {@link #name()}.
	 */
	@Override
	public String toString() {
		return name;
	}
}
