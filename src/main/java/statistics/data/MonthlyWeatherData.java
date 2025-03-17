package statistics.data;

/**
 * Represenda dados climáticos referentes a um {@link Month mês}.
 *
 * @param month          O mês de referência para os dados.
 * @param minTemperature A temperatura mínima neste mês.
 * @param maxTemperature A temperatura máxima neste mês.
 * @param rainyDays      A quantidade de dias de chuva neste mês.
 * @param precipitation  O volume da precipitação neste mês.
 * @author Lucas da Paz
 */
public record MonthlyWeatherData(
	Month month,
	Double minTemperature,
	Double maxTemperature,
	Integer rainyDays,
	Double precipitation
) {}
