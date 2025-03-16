package data;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public record CityWeatherData(String country, String city, boolean isCapital, List<MonthlyWeatherData> weatherData) {

	public static CityWeatherData of(String json) throws IOException {
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

		} catch (NullPointerException e) {
			throw new DataException("JSON sem informações históricas sobre o clima: %n%s%n".formatted(json), e);
		}
	}
}
