package co.uceva.serviceuser.delivery.controller;

import co.uceva.serviceuser.domain.exception.NoHayUsuariosException;
import co.uceva.serviceuser.domain.exception.PaginaSinUsuariosException;
import co.uceva.serviceuser.domain.exception.UsuarioNoEncontradoException;
import co.uceva.serviceuser.domain.exception.ValidationException;
import co.uceva.serviceuser.domain.model.Usuario;
import co.uceva.serviceuser.domain.service.IUsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/usuario-service")
public class UsuarioRestController {
    private final IUsuarioService usuarioService;


    private static final String MENSAJE = "mesaje";
    private static final String USUARIO = "usuario";
    private static final String USUARIOS = "usuarios";

    @Autowired
    public UsuarioRestController(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;

    }

    @GetMapping("/usuarios")
    public ResponseEntity<Map<String, Object>> getUsuarios() {
        List<Usuario> Usuarios = usuarioService.findAll();
        if (Usuarios.isEmpty()) {
            throw new NoHayUsuariosException();
        }
        Map<String, Object> response = new HashMap<>();
        response.put(USUARIOS, Usuarios);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/usuario/page/{page}")
    public ResponseEntity<Object> index(@PathVariable Integer page) {
        Pageable pageable = PageRequest.of(page, 4);
        Page<Usuario> usuarios = usuarioService.findAll(pageable);
        if (usuarios.isEmpty()) {
            throw new PaginaSinUsuariosException(page);
        }
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping("/usuarios")
    public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody Usuario usuario, BindingResult result) {
        if (result.hasErrors()){
            throw new ValidationException(result);
        }
        Map<String, Object> response = new HashMap<>();
        Usuario usuarioCreado = usuarioService.save(usuario);
        response.put(MENSAJE, "Usuario creado con exito");
        response.put(USUARIO, usuarioCreado);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/usuarios")
    public void delete(@RequestBody Usuario usuario) {
        usuarioService.delete(usuario);
    }
    @PutMapping("/usuarios")
    public Usuario update(@RequestBody Usuario usuario) {
        return usuarioService.update(usuario);
    }
    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable Long id) {
        Usuario usuario = usuarioService.findById(id)
                .orElseThrow(() -> new UsuarioNoEncontradoException(id));
        Map<String, Object> response = new HashMap<>();
        response.put(MENSAJE, "El usuario con el id: " + id + " existe");
        response.put(USUARIO, usuario);
        return ResponseEntity.ok(response);
    }
}
