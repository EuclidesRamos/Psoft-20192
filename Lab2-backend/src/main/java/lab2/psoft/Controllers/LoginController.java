package lab2.psoft.Controllers;

import java.util.Date;
import java.util.Optional;

import javax.servlet.ServletException;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lab2.psoft.Entities.Usuario;
import lab2.psoft.Services.UsuarioService;

@RestController
@RequestMapping("/Filters")
public class LoginController {
	
	private final String TOKEN_KEY = "login do batman monstrao";
	
	private UsuarioService usuarioService;

	public LoginController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@PostMapping("/login")
	public LoginResponse authenticate(@RequestBody Usuario usuario) throws ServletException {
		
		Optional<Usuario> authUsuario = usuarioService.getUsuario(usuario.getEmail());

		if (authUsuario.isEmpty()) {
			throw new ServletException("Usuario nao encontrado!");
		}

		if (!authUsuario.get().getSenha().equals(usuario.getSenha())) {
			throw new ServletException("Senha invalida!");
		}


		String token = Jwts.builder().setSubject(authUsuario.get().getEmail()).signWith(SignatureAlgorithm.HS512, TOKEN_KEY)
				.setExpiration(new Date(System.currentTimeMillis() + 1 * 60 * 1000)).compact();

		return new LoginResponse(token);
	}
}

private class LoginResponse {
	public String token;

	public LoginResponse(String token) {
		this.token = token;
	}
}
