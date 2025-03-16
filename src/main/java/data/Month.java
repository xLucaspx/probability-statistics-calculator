package data;

import java.util.Arrays;

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

	Month(int numeric, String name) {
		this.numeric = numeric;
		this.name = name;
	}

	public static Month from(int numeric) {
		return Arrays.stream(values())
			.filter(month -> month.numeric == numeric)
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("Ordinal sem mês correspondente: %d".formatted(numeric)));
	}

	public int numeric() {
		return numeric;
	}

	@Override
	public String toString() {
		return name;
	}
}
