package com.santander.meetup.service;

import com.santander.meetup.domain.Usuario;
import com.santander.meetup.exception.UsuarioException;
import com.santander.meetup.persistence.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario save(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public void validoQueExistaElUsuario(String usuario) throws UsuarioException {
        if(usuarioRepository.obtengoUsuarioPorNombreDeUsuario(usuario) != null) {
            throw new UsuarioException("El usuario " + usuario + " ya existe.");
        }
    }

    public void validoQueNoExistaElUsuario(String usuario) throws UsuarioException {
        if(usuarioRepository.obtengoUsuarioPorNombreDeUsuario(usuario) == null) {
            throw new UsuarioException("El usuario " + usuario + " no existe.");
        }
    }

    public void validoQueElUsuarioSeaAdministrador(Integer idUsuario) throws UsuarioException {
        if(usuarioRepository.obtengoPrivilegioPorIdDeUsuario(idUsuario) == 2){
            throw new UsuarioException("El usuario de ID: " + idUsuario + " no es administrador.");
        }
    }

    public void validoQueElUsuarioNoSeaAdministrador(Integer idUsuario) throws UsuarioException {
        if(usuarioRepository.obtengoPrivilegioPorIdDeUsuario(idUsuario) == 1){
            throw new UsuarioException("El usuario de ID: " + idUsuario + " es administrador.");
        }
    }

}
