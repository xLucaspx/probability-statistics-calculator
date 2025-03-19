package statistics.data.web;

import statistics.data.DataException;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.concurrent.Executors;

/**
 * Classe que busca os dados climáticos na API e escreve
 * em formato CSV.
 *
 * @author Lucas da Paz
 * @see <a href="https://worldweather.wmo.int/en/dataguide.html">World Weather Information Service</a>
 * @see CityReader
 * @see WorldWeatherClient
 */
public class WeatherDataWriter {

	private static final String FILE_PATH = "src/main/resources/weather-data.csv";
	private static final String CSV_HEADER =
		"País;Cidade;Capital;Mês;Temperatura mínima;Temperatura máxima;Dias de chuva;Precipitação";

	/**
	 * Lê a lista de cidades diponíveis e, para cada uma, realiza a requisição
	 * para buscar os dados climáticos e escrever em formato CSV. Utiliza múltiplas
	 * threads para aumentar a performance.
	 *
	 * @see WorldWeatherClient#getCityWeather(City)
	 * @see CityReader#getCitiesFromFile()
	 */
	public static void writeWeatherCsv() {
		WorldWeatherClient client = new WorldWeatherClient();
		var cities = CityReader.getCitiesFromFile();

		try (var writer = new PrintStream(FILE_PATH, StandardCharsets.UTF_8)) {
			writer.println(CSV_HEADER);

			try (var executor = Executors.newFixedThreadPool(10)) {
				executor.submit(() -> cities.parallelStream()
					.map(client::getCityWeather)
					.filter(Objects::nonNull)
					.forEach(c -> c.weatherData()
						.forEach(d -> writer.printf("%s;%s;%b;%d;%.1f;%.1f;%d;%.1f%n",
																				c.country(),
																				c.city(),
																				c.isCapital(),
																				d.month().numeric(),
																				d.minTemperature(),
																				d.maxTemperature(),
																				d.rainyDays(),
																				d.precipitation()
						))));
				executor.shutdown();
			}
		} catch (IOException e) {
			throw new DataException("Erro ao tentar escrever arquivo CSV: %s%n".formatted(e.getMessage()), e);
		}
	}
}
