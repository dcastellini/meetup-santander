package com.santander.meetup.persistence.repository;

import com.santander.meetup.domain.Usuario;
import com.santander.meetup.persistence.crud.UsuarioCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioRepository {

    @Autowired
    private UsuarioCrudRepository usuarioCrudRepository;

    public List<Usuario> getAll(){
        return (List<Usuario>) usuarioCrudRepository.findAll();
    }

    public Optional<Usuario> obtengoUsuarioPorIdDeUsuario(Integer idUsuario){
        return usuarioCrudRepository.obtengoUsuarioPorIdDeUsuario(idUsuario);
    }

    public Integer obtengoUsuarioPorNombreDeUsuario(String usuario){
        return usuarioCrudRepository.obtengoUsuarioPorNombreDeUsuario(usuario);
    }

    public Usuario save(Usuario usuario) {
        return usuarioCrudRepository.save(usuario);
    }

    public Integer obtengoPrivilegioPorIdDeUsuario(Integer idUsuario){
        return usuarioCrudRepository.obtengoPrivilegioDeUsuarioPorIdUsuario(idUsuario);
    }

}
