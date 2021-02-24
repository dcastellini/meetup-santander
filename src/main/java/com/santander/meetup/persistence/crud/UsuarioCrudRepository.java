package com.santander.meetup.persistence.crud;

import com.santander.meetup.domain.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioCrudRepository extends CrudRepository<Usuario, Integer > {

    @Query(value = "SELECT * FROM USUARIOS WHERE ID_USUARIO = ?", nativeQuery = true)
    Optional<Usuario> obtengoUsuarioPorIdDeUsuario(Integer idUsuario);

    @Query(value = "SELECT 1 FROM USUARIOS WHERE USUARIO = ?", nativeQuery = true)
    Integer obtengoUsuarioPorNombreDeUsuario(String usuario);

    @Query(value = "SELECT PRIVILEGIO FROM USUARIOS WHERE ID_USUARIO = ?", nativeQuery = true)
    Integer obtengoPrivilegioDeUsuarioPorIdUsuario(Integer idUsuario);





}
