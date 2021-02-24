package com.santander.meetup.persistence.repository;

import com.santander.meetup.domain.Meetup;
import com.santander.meetup.persistence.crud.MeetupCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MeetupRepository {

    @Autowired
    private MeetupCrudRepository meetupCrudRepository;

    public Meetup save(Meetup meetup) {
        return meetupCrudRepository.save(meetup);
    }

    public Double obtenerTemperaturaDeLaMeetup(Integer idMeetup){
        return meetupCrudRepository.obtengoTemperaturaPorIdDeMeetup(idMeetup);
    }

    public Integer existeMeetup(Integer idMeetup){
        return meetupCrudRepository.existeMeetup(idMeetup);
    }

    public Meetup obtengoMeetupPorId(Integer idMeetup){
        return meetupCrudRepository.obtengoMeetupPorId(idMeetup);
    }

    public void actualizoCantidadDeCervezaParaLaMeetup(Integer cantidadDeCerveza, Integer idMeetup){
        meetupCrudRepository.actualizoCantidadDeCervezaParaLaMeetup(cantidadDeCerveza, idMeetup);
    }

    public Integer obtengoCantidadDeCervezaParaUnaMeetup(Integer idMeetup){
        return meetupCrudRepository.obtengoCantidadDeCervezaParaUnaMeetup(idMeetup);
    }

}
