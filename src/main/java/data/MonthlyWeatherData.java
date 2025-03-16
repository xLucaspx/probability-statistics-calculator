package data;

public record MonthlyWeatherData(
	Month month,
	Double minTemperature,
	Double maxTemperature,
	Integer rainyDays,
	Double precipitation
) {}
