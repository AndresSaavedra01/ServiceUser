package co.uceva.serviceuser.domain.exception;

public class PaginaSinUsuariosException extends RuntimeException {
  public PaginaSinUsuariosException(int page) {
    super("No hay usuarios en la pagina:" + page);
  }
}
