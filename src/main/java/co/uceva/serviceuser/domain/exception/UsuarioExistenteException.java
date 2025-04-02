package co.uceva.serviceuser.domain.exception;

public class UsuarioExistenteException extends RuntimeException {
    public UsuarioExistenteException(String name) {
        super("El producto con el nombre: " + name + " ya existe");
    }
}
