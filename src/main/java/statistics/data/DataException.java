package statistics.data;

/**
 * Exeção lançada se ocorrer algum erro durante a extração
 * das informações climáticas das cidades.
 *
 * @author Lucas da Paz
 */
public class DataException extends RuntimeException {

	/**
	 * Constrói a exceção com a mensagem informada.
	 *
	 * @param message Mensagem de erro.
	 * @see RuntimeException#RuntimeException(String)
	 */
	public DataException(String message) {
		super(message);
	}

	/**
	 * Constrói a exceção com a mensagem e causa informadas.
	 *
	 * @param message Mensagem de erro.
	 * @param cause   Causa do erro.
	 * @see RuntimeException#RuntimeException(String, Throwable)
	 */
	public DataException(String message, Throwable cause) {
		super(message, cause);
	}
}
