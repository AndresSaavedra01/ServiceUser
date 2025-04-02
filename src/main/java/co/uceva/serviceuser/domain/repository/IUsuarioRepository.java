package co.uceva.serviceuser.domain.repository;

import co.uceva.serviceuser.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
}
