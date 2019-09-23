package lab2.psoft.Controllers;

import lab2.psoft.Entities.Usuario;
import lab2.psoft.Services.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {

    private UsuarioService usuarioService;

    @PostMapping("/v1/auth/usuarios")
    public ResponseEntity<Usuario> setUsuario(@RequestBody Usuario usuario) {
        return new ResponseEntity<Usuario>(usuarioService.setUsuario(usuario), HttpStatus.OK);
    }

    
}
