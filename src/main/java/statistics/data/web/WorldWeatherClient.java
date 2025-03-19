package statistics.data.web;

import statistics.data.CityWeatherData;
import statistics.data.DataException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Client HTTP para buscar os dados climáticos da API utilizada.
 *
 * @author Lucas da Paz
 * @see <a href="https://worldweather.wmo.int/en/dataguide.html">World Weather Information Service</a>
 */
public class WorldWeatherClient {

	private static final String URL = "https://worldweather.wmo.int/en/json";
	private static final String BASE_NAME = "%d_en.json";
	private static final int TIMEOUT_IN_MILLIS = 2 * 60 * 1000;

	/**
	 * Busca os dados climáticos para a cidade informada utilizando a
	 * API da World Meteorological Organization.
	 *
	 * @param city A cidade de referência.
	 * @return Informações climáticas da cidade, em caso de sucesso.
	 * @see <a href="https://worldweather.wmo.int/en/dataguide.html">World Weather Information Service</a>
	 */
	public CityWeatherData getCityWeather(City city) {
		try {
			System.out.printf("Executando para: %s%n", city);

			HttpURLConnection conn = getConnection(BASE_NAME.formatted(city.id()));

			int responseCode = conn.getResponseCode();
			if (responseCode != 200) {
				throw new DataException(
					"Erro: Código HTTP %d ao tentar buscar dados da cidade: %s%n".formatted(responseCode, city));
			}

			String jsonResponse = getResponseAsJson(conn.getInputStream());
			return CityWeatherData.of(jsonResponse);

		} catch (IOException | URISyntaxException e) {
			throw new DataException("Erro ao tentar buscar dados da cidade: %s%n".formatted(city), e);
		}
	}

	/**
	 * Retorna o objeto {@link HttpURLConnection} configurado, que será
	 * utilizado para fazer a requisição.
	 *
	 * @param fileName O nome do arquivo JSON que será buscado pela API.
	 * @return Objeto de conexão configurado para realizar a requisição para
	 * o arquivo informado.
	 * @throws IOException        Se ocorrer algum erro de entrada/saída.
	 * @throws URISyntaxException Se a URL da requisição violar as regras definidas
	 *                            em {@link URI#URI(String)}.
	 */
	private HttpURLConnection getConnection(String fileName) throws IOException, URISyntaxException {
		URL url = new URI("%s/%s".formatted(URL, fileName)).toURL();
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");
		conn.setConnectTimeout(TIMEOUT_IN_MILLIS);
		conn.setReadTimeout(TIMEOUT_IN_MILLIS);
		return conn;
	}

	/**
	 * Transforma o {@link InputStream} da resposta em uma string JSON.
	 *
	 * @param in Stream retornado pela requisição.
	 * @return Conteúdo da resposta como uma string JSON.
	 * @throws IOException Se ocorrer algum erro de entrada/saída.
	 */
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
