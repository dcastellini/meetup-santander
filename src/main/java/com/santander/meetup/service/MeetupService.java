package com.santander.meetup.service;


import com.santander.meetup.domain.Meetup;
import com.santander.meetup.exception.DateException;
import com.santander.meetup.exception.MeetupException;
import com.santander.meetup.persistence.repository.MeetupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MeetupService {

    private static final int RANGO_DE_DIAS_QUE_OBTENGO_CLIMA = 16;

    @Autowired
    private MeetupRepository meetupRepository;


    @Autowired
    private MeetupUsuarioService meetupUsuarioService;

    public Meetup save(Meetup meetup){
       Meetup meetupCreada = meetupRepository.save(meetup);
       meetupUsuarioService.crearMeetupConUsuarioCreador(meetup);
       return meetupCreada;
    }

    public void validoFechasDeLaMeetup(Meetup meetup) throws DateException {
        if(LocalDateTime.now().isAfter(meetup.getFecha())){
            throw new DateException("La fecha enviada es menor a hoy.");
        } else if (meetup.getFecha().isAfter(LocalDateTime.now().plusDays(RANGO_DE_DIAS_QUE_OBTENGO_CLIMA))){
            throw new DateException("La fecha enviada no puede ser mayor a " + RANGO_DE_DIAS_QUE_OBTENGO_CLIMA + " de diferencia con la fecha actual." );
        }
    }

    public void validoQueLaMeetupNoSeaPasada(Integer idMeetup) throws DateException, MeetupException {
        validoQueExistaLaMeetup(idMeetup);
        Meetup meetup = obtengoMeetup(idMeetup);
        if(LocalDateTime.now().isAfter(meetup.getFecha())){
            throw new DateException("La Meetup " + idMeetup + " ya ha finalizado el dia " + meetup.getFecha() + ".");
        }
    }

    public void validoQueLaMeetupYaHaFinalizado(Integer idMeetup) throws DateException, MeetupException{
        validoQueExistaLaMeetup(idMeetup);
        Meetup meetup = obtengoMeetup(idMeetup);
        if(LocalDateTime.now().isBefore(meetup.getFecha())){
            throw new DateException("La Meetup " + idMeetup + " comenzara el dia " + meetup.getFecha() + " .");
        }
    }

    public Double obtengoTemperaturaDeLaMeetup(Integer idMeetup){
        return meetupRepository.obtenerTemperaturaDeLaMeetup(idMeetup);
    }

    public void validoQueExistaLaMeetup(Integer idMeetup) throws MeetupException{
        if(meetupRepository.existeMeetup(idMeetup) == null){
            throw new MeetupException("La meetup " + idMeetup + " no existe.");
        }
    }

    public Meetup obtengoMeetup(Integer idMeetup){
        return meetupRepository.obtengoMeetupPorId(idMeetup);
    }

    public void actualizoCantidadDeCervezaParaLaMeetup(Integer cantidadDeCerveza, Integer idMeetup){
        meetupRepository.actualizoCantidadDeCervezaParaLaMeetup(cantidadDeCerveza, idMeetup);
    }

    public Integer obtengoCantidadDeCervezaParaUnaMeetup(Integer idMeetup){
        return meetupRepository.obtengoCantidadDeCervezaParaUnaMeetup(idMeetup);
    }





}
