package com.santander.meetup.persistence.repository;

import com.santander.meetup.domain.MeetupUsuario;
import com.santander.meetup.domain.Usuario;
import com.santander.meetup.persistence.crud.MeetupUsuarioCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MeetupUsuarioRepository {

    @Autowired
    private MeetupUsuarioCrudRepository meetupUsuarioCrudRepository;

    public MeetupUsuario save(MeetupUsuario meetupUsuario) {
        return meetupUsuarioCrudRepository.save(meetupUsuario);
    }

    public Integer obtengoCantidadDeInvitados(Integer idMeetup){
        return meetupUsuarioCrudRepository.obtengoCantidadDeInvitados(idMeetup);
    }

    public Integer hacerCheckInEnUnaMeetup(Integer idMeetup, Integer idUsuario){
        return meetupUsuarioCrudRepository.hacerCheckInEnUnaMeetup(idMeetup, idUsuario);
    }

}
