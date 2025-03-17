package digitalhousemoney.servicio_tarjetas.exception;

public class CardAlreadyExistsException extends RuntimeException {
    public CardAlreadyExistsException(String message) {
        super(message);
    }
}