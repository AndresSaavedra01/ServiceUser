package co.uceva.serviceuser.service;

import co.uceva.serviceuser.model.entities.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUsuarioService {

    Usuario save(Usuario usuario);
    Usuario findById(Long id);
    void delete(Usuario usuario);
    Usuario update(Usuario usuario);
    List<Usuario> findAll();
    Page<Usuario> findAll(Pageable pageable);
}
