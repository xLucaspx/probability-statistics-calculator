package statistics.data.web;

import statistics.data.DataException;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe utilizada para ler a lista de cidades fornecida pela
 * API utilizada.
 *
 * @author Lucas da Paz
 * @see <a href="https://worldweather.wmo.int/en/json/full_city_list.txt">World Weather Information Cities</a>
 */
public class CityReader {

	private static final String FILE_NAME = "src/main/resources/world-weather-full-city-list.csv";
	private static final String FIELD_DELIMITER = ";";

	/**
	 * Lê a lista de cidades encontrada no caminho de {@link #FILE_NAME}.
	 *
	 * @return Lista de DTOs contendo as informações das cidades lidas.
	 */
	public static List<City> getCitiesFromFile() {
		try (var reader = Files.newBufferedReader(new File(FILE_NAME).toPath(), StandardCharsets.UTF_8)) {
			List<City> cities = new ArrayList<>();

			String line = reader.readLine(); // descarta o header
			while ((line = reader.readLine()) != null && !line.isBlank()) {
				String[] fields = line.split(FIELD_DELIMITER);

				Long cityId = Long.parseLong(cleanField(fields[2]));
				String cityName = cleanField(fields[1]);
				String country = cleanField(fields[0]);

				cities.add(new City(cityId, cityName, country));
			}

			return cities;
		} catch (IOException e) {
			throw new DataException("Erro ao tentar obter cidades: %s%n".formatted(e.getMessage()), e);
		}
	}

	/**
	 * Remove caracteres indesejados de um campo.
	 *
	 * @param field Valor para ser limpo.
	 * @return Valor do campo sem os caracteres indesejados.
	 */
	private static String cleanField(String field) {
		return field.replace("\"", "");
	}
}
