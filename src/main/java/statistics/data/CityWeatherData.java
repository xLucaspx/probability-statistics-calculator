package statistics.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import statistics.data.web.City;
import statistics.data.web.WorldWeatherClient;

import java.util.ArrayList;
import java.util.List;

/**
 * Encapsula as infomações climáticas de uma cidade.
 *
 * @param country     O país da cidade.
 * @param city        O nome da cidade.
 * @param isCapital   Se a cidade é capital do país.
 * @param weatherData Lista contendo informações climáticas da cidade em determinado
 *                    mês; geralmente contém 12 itens, um para cada mês do ano.
 * @author Lucas da Paz
 */
public record CityWeatherData(String country, String city, boolean isCapital, List<MonthlyWeatherData> weatherData) {

	/**
	 * Constrói o objeto a partir de uma string JSON, geralmente obtida
	 * pelo método {@link WorldWeatherClient#getCityWeather(City)}.
	 *
	 * @param json String JSON para extrair os dados climáticos de uma cidade.
	 * @return Objeto contendo as informações climáticas extraídas do JSON.
	 */
	public static CityWeatherData of(String json) {
		try {
			JsonNode root = new ObjectMapper().readTree(json).get("city");
			JsonNode climateData = root.get("climate").get("climateMonth");

			if (climateData.isEmpty()) {
				throw new DataException("JSON sem informações históricas sobre o clima: %n%s%n".formatted(json));
			}

			String city = root.get("cityName").asText();
			String country = root.get("member").get("memName").asText();
			boolean isCapital = root.get("isCapital").asBoolean();
			List<MonthlyWeatherData> weatherData = new ArrayList<>(12);

			for (JsonNode monthObject : climateData) {
				Month month = Month.from(monthObject.get("month").asInt());
				Double minTemp = monthObject.get("minTemp").asDouble();
				Double maxTemp = monthObject.get("maxTemp").asDouble();
				Integer raindays = monthObject.get("raindays").asInt();
				Double rainfall = monthObject.get("rainfall").asDouble();
				weatherData.add(new MonthlyWeatherData(month, minTemp, maxTemp, raindays, rainfall));
			}

			return new CityWeatherData(country, city, isCapital, weatherData);

		} catch (NullPointerException | JsonProcessingException e) {
			throw new DataException("JSON com erro ou sem informações históricas sobre o clima: %n%s%n".formatted(json), e);
		}
	}
}
