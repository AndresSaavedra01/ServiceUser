package co.uceva.serviceuser.domain.service;

import co.uceva.serviceuser.domain.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {

    Usuario save(Usuario usuario);
    void delete(Usuario usuario);
    Usuario update(Usuario usuario);
    List<Usuario> findAll();
    Page<Usuario> findAll(Pageable pageable);
    Optional<Usuario> findById(Long id);
}
