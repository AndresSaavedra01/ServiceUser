package co.uceva.serviceuser.repository;

import co.uceva.serviceuser.model.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
}
