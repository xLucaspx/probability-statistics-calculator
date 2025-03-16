package probability;

import data.CityWeatherData;
import data.CityWeatherReader;

import java.util.List;

public class App {
	public static void main(String... args) {
		List<CityWeatherData> data = CityWeatherReader.getDataFromFile();
		System.out.printf("Total de cidades: %s%n", data.size());
	}
}
