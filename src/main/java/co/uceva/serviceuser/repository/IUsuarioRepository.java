package co.uceva.serviceuser.repository;

import co.uceva.serviceuser.model.entities.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface IUsuarioRepository extends CrudRepository<Usuario, Long> {
}
