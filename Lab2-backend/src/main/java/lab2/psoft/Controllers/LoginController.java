package lab2.psoft.Controllers;

import java.util.Optional;

import javax.servlet.ServletException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lab2.psoft.Entities.Usuario;
import lab2.psoft.Services.UsuarioService;

@RestController
@RequestMapping("/auth")
public class LoginController {
	
	private final String TOKEN_KEY = "login do batman monstrao";
	
	private UsuarioService usuarioService;

	public LoginController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@PostMapping("/login")
	public LoginResponse authenticate(@RequestBody Usuario usuario) throws ServletException {
		
		Optional<Usuario> authUsuario = usuarioService.getUsuario(usuario.getEmail());
 		
	}
}
