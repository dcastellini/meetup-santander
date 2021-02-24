package com.santander.meetup.web.controller;


import com.santander.meetup.domain.Usuario;
import com.santander.meetup.service.UsuarioService;
import com.santander.meetup.exception.UsuarioException;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private static final int USUARIO_ADMIN = 1;
    private static final int USUARIO_SIN_PRIVILEGIOS = 2;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/crear_usuario_admin")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 404, message = "No se pudo crear el usuario administrador.")
    })
    public ResponseEntity<Usuario> crearUsuarioAdmin(@RequestBody Usuario usuario) {
        try {
            usuarioService.validoQueExistaElUsuario(usuario.getUsuario());
            usuario.setIdPrivilegio(USUARIO_ADMIN);
            return new ResponseEntity<>(usuarioService.save(usuario), HttpStatus.CREATED);
        } catch (UsuarioException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }

    }

    @PostMapping("/crear_usuario")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 404, message = "No se pudo crear el usuario.")
    })
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        try {
            usuarioService.validoQueExistaElUsuario(usuario.getUsuario());
            usuario.setIdPrivilegio(USUARIO_SIN_PRIVILEGIOS);
            return new ResponseEntity<>(usuarioService.save(usuario), HttpStatus.CREATED);
        } catch (UsuarioException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);

        }

    }

}
