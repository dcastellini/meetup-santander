package com.santander.meetup.service;


import com.santander.meetup.domain.Meetup;
import com.santander.meetup.domain.MeetupUsuario;
import com.santander.meetup.domain.MeetupUsuarioPk;
import com.santander.meetup.persistence.repository.MeetupUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeetupUsuarioService {

    public static final int ASISTENCIA_CONFIRMADA = 1;

    @Autowired
    private MeetupUsuarioRepository meetupUsuarioRepository;

    public MeetupUsuario crearMeetupConUsuarioCreador(Meetup meetup){
        MeetupUsuario meetupUsuario = new MeetupUsuario(new MeetupUsuarioPk(meetup.getIdMeetup(),meetup.getIdUsuarioCreador()),ASISTENCIA_CONFIRMADA);
        return meetupUsuarioRepository.save(meetupUsuario);
    }

    public MeetupUsuario asignarUsuarioMeetup(Integer idMeetup, Integer idUsuario){
        MeetupUsuario meetupUsuario = new MeetupUsuario(new MeetupUsuarioPk(idMeetup,idUsuario),ASISTENCIA_CONFIRMADA);
        return meetupUsuarioRepository.save(meetupUsuario);
    }

    public Integer obtengoCantidadDeInvitados(Integer idMeetup){
        return meetupUsuarioRepository.obtengoCantidadDeInvitados(idMeetup);
    }

    public Integer checkInMeetup(Integer idMeetup, Integer idUsuario){
        return meetupUsuarioRepository.hacerCheckInEnUnaMeetup(idMeetup, idUsuario);
    }


}
