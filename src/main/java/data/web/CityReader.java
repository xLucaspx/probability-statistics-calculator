package data.web;

import data.DataException;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class CityReader {

	public static final String FILE_NAME = "src/main/resources/world-weather-full-city-list.csv";
	public static final String FIELD_DELIMITER = ";";

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

	private static String cleanField(String field) {
		return field.replace("\"", "");
	}
}
