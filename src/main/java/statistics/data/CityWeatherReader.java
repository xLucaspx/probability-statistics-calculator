package statistics.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe utilizada para ler o CSV de informações climáticas de
 * cidades, utilizado como base de dados para o projeto.
 *
 * @author Lucas da Paz
 */
public class CityWeatherReader {

	private static final String FILE_NAME = "src/main/resources/weather-data.csv";
	private static final String FIELD_DELIMITER = ";";

	/**
	 * Lê o CSV encontrado no caminho de {@link #FILE_NAME}.
	 *
	 * @return Lista de DTOs contendo as informações climáticas para
	 * cada cidade do CSV; espera-se que cada objeto {@link CityWeatherData}
	 * contenha 12 objetos {@link MonthlyWeatherData}, um para cada mês do ano.
	 */
	public static List<CityWeatherData> getDataFromFile() {
		try (var reader = Files.newBufferedReader(new File(FILE_NAME).toPath(), StandardCharsets.UTF_8)) {
			Map<String, CityWeatherData> cityDataMap = new HashMap<>();

			String line = reader.readLine(); // descarta o header
			while ((line = reader.readLine()) != null && !line.isBlank()) {
				String[] fields = line.split(FIELD_DELIMITER);

				String country = fields[0];
				String city = fields[1];
				boolean isCapital = Boolean.parseBoolean(fields[2]);
				Month month = Month.from(Integer.parseInt(fields[3]));
				Double tempMin = Double.parseDouble(fields[4]);
				Double tempMax = Double.parseDouble(fields[5]);
				Integer rainyDays = Integer.parseInt(fields[6]);
				Double precipitation = Double.parseDouble(fields[7]);

				CityWeatherData entry =
					cityDataMap.getOrDefault(city, new CityWeatherData(country, city, isCapital, new ArrayList<>(12)));
				entry.weatherData().add(new MonthlyWeatherData(month, tempMin, tempMax, rainyDays, precipitation));
				cityDataMap.put(city, entry);
			}
			return cityDataMap.values().stream().toList();

		} catch (IOException e) {
			throw new DataException("Erro ao tentar obter cidades: %s%n".formatted(e.getMessage()), e);
		}
	}
}
