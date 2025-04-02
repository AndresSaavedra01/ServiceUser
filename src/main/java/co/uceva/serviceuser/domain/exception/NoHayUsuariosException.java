package co.uceva.serviceuser.domain.exception;

public class NoHayUsuariosException extends RuntimeException {
    public NoHayUsuariosException() {
        super("No hay usuarios registrados en la base de datos");
    }
}
