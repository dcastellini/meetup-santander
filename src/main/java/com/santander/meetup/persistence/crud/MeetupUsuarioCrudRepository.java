package com.santander.meetup.persistence.crud;

import com.santander.meetup.domain.Meetup;
import com.santander.meetup.domain.MeetupUsuario;
import com.santander.meetup.domain.MeetupUsuarioPk;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface MeetupUsuarioCrudRepository extends CrudRepository <MeetupUsuario, MeetupUsuarioPk> {

    int ASISTIO_A_LA_MEETUP = 2;

    @Query(value = "SELECT COUNT(*) FROM MEETUPS_USUARIOS WHERE ID_MEETUP = ?", nativeQuery = true)
    Integer obtengoCantidadDeInvitados(Integer idMeetup);

    @Transactional
    @Modifying
    @Query(value = "UPDATE MEETUPS_USUARIOS SET ID_ASISTENCIA = " + ASISTIO_A_LA_MEETUP + " WHERE ID_MEETUP = :idMeetup AND ID_USUARIO = :idUsuario" , nativeQuery = true)
    Integer hacerCheckInEnUnaMeetup(@Param("idMeetup") Integer idMeetup, @Param("idUsuario") Integer idUsuario);


}
