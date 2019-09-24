package lab2.psoft.Dao;

import lab2.psoft.Entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface UsuarioRepository<T, ID extends Serializable> extends JpaRepository<Usuario, String> {
}
