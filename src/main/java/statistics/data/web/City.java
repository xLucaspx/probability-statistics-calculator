package statistics.data.web;

/**
 * Representa uma cidade da lista de cidades fornecida
 * pela API utilizada.
 *
 * @param id      O ID da cidade.
 * @param name    O nome da cidade.
 * @param country O país da cidade.
 * @author Lucas da Paz
 * @see <a href="https://worldweather.wmo.int/en/json/full_city_list.txt">World Weather Information Cities</a>
 */
public record City(Long id, String name, String country) {}
