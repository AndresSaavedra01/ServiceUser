package co.uceva.serviceuser.delivery.exception;


import co.uceva.serviceuser.domain.exception.*;
import io.micrometer.core.ipc.http.HttpSender;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    private static final String ERROR = "error";
    private static final String MENSAJE = "mesaje";
    private static final String USUARIO = "usuario";
    private static final String USUARIOS = "usuarios";
    private static final String STATUS = "status";
    
    @ExceptionHandler(PaginaSinUsuariosException.class)
    public ResponseEntity<Map<String, Object>> handlePaginasSinUsuarios(PaginaSinUsuariosException ex){
        Map<String, Object> response = new HashMap<>();
        response.put(MENSAJE, ex.getMessage());
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(IllegalArgumentException ex){
        Map<String, Object> response = new HashMap<>();
        response.put(MENSAJE, "Numero de pagina no valido");
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(NoHayUsuariosException.class)
    public ResponseEntity<Map<String, Object>> handleNoHayUsuariosException(NoHayUsuariosException ex){
        Map<String, Object> response = new HashMap<>();
        response.put(MENSAJE, "No hay usuarios en la base de datos");
        response.put(USUARIOS, null);
        return  ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ExceptionHandler(UsuarioNoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> handleUsuarioNoEncontradoException(UsuarioNoEncontradoException ex){
        Map<String, Object> response = new HashMap<>();
        response.put(ERROR, ex.getMessage());
        response.put(STATUS, HttpStatus.NOT_FOUND.value());
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(UsuarioExistenteException.class)
    public ResponseEntity<Map<String, Object>> handleUsuarioExistenteException(UsuarioExistenteException ex){
        Map<String, Object> response = new HashMap<>();
        response.put(ERROR, ex.getMessage());
        response.put(STATUS, HttpStatus.BAD_REQUEST.value());
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleUsuarioExistenteException(Exception ex){
        Map<String, Object> response = new HashMap<>();
        response.put(ERROR, "Error inesparado: "+ex.getMessage());
        response.put(STATUS, HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new  ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Map<String, Object>> handleUsuarioExistenteException(DataAccessException ex){
        Map<String, Object> response = new HashMap<>();
        response.put(MENSAJE, "Error al consultar la base de datos.");
        response.put(ERROR, ex.getMessage().concat(": ").concat(ex.getMostSpecificCause().getMessage()));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(ValidationException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put(MENSAJE, ex.getMessage());
        response.put(ERROR, ex.getMessage());

        List<String> errors = ex.result.getFieldErrors()
                .stream()
                .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                .toList();

        response.put(ERROR, errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

    }
}
