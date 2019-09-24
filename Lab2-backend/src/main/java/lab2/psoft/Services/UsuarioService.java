package lab2.psoft.Services;

import lab2.psoft.Dao.UsuarioRepository;
import lab2.psoft.Entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;

public class UsuarioService {

    @Autowired
    private UsuarioRepository<Usuario, String> usuarioDAO;

    public UsuarioService(UsuarioRepository<Usuario, String> usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public Usuario setUsuario(Usuario usuario) {
    	this.usuarioDAO.save(usuario);
    	return usuario;
    }

    public Usuario autenticaUsuario(Usuario usuario) {
        String email = usuario.getEmail();
        String senha = usuario.getSenha();
        if (this.usuarioDAO.getOne(email).getSenha().equals(senha)) {
            return usuario;
        } else {
            return null;
        }
    }



}
