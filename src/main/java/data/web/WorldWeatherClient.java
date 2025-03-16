package data.web;

import data.CityWeatherData;
import data.DataException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;


public class WorldWeatherClient {

	private static final String URL = "https://worldweather.wmo.int/en/json";
	private static final String BASE_NAME = "%d_en.json";

	private static final int TIMEOUT_IN_MILLIS = 2 * 60 * 1000;

	public CityWeatherData getCityWeather(City city) {
		try {
			System.out.printf("Executando para: %s%n", city);

			HttpURLConnection conn = getConnection(BASE_NAME.formatted(city.id()));

			int responseCode = conn.getResponseCode();
			if (responseCode != 200) {
				throw new DataException("Erro: Código HTTP %d ao tentar buscar dados da cidade: %s%n".formatted(responseCode, city));
			}

			String jsonResponse = getResponseAsJson(conn.getInputStream());
			return CityWeatherData.of(jsonResponse);

		} catch (IOException | URISyntaxException e) {
			throw new DataException("Erro ao tentar buscar dados da cidade: %s%n".formatted(city), e);
		}
	}

	private HttpURLConnection getConnection(String fileName) throws IOException, URISyntaxException {
		URL url = new URI("%s/%s".formatted(URL, fileName)).toURL();
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");
		conn.setConnectTimeout(TIMEOUT_IN_MILLIS);
		conn.setReadTimeout(TIMEOUT_IN_MILLIS);
		return conn;
	}

	private String getResponseAsJson(InputStream in) throws IOException {
		try (var reader = new BufferedReader(new InputStreamReader(in))) {
			StringBuilder response = new StringBuilder();

			String line;
			while ((line = reader.readLine()) != null) {
				response.append(line);
			}

			return response.toString();
		}
	}
}
