package co.uceva.serviceuser.controller;

import co.uceva.serviceuser.model.entities.Usuario;
import co.uceva.serviceuser.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuario-service")
public class UsuarioRestController {
    private IUsuarioService usuarioService;

    @Autowired
    public UsuarioRestController(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    @GetMapping("/usuarios")
    public List<Usuario> getUsuarios() {
        return usuarioService.findAll();
    }
    @PostMapping("/usuarios")
    public Usuario save(@RequestBody Usuario usuario) {
        return usuarioService.save(usuario);
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
    public Usuario findById(@PathVariable("id") Long id) {
        return usuarioService.findById(id);
    }
}
