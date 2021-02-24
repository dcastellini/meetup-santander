package com.santander.meetup.persistence.crud;

import com.santander.meetup.domain.Meetup;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface MeetupCrudRepository extends CrudRepository <Meetup, Integer> {

    @Query(value = "SELECT TEMPERATURA FROM MEETUPS WHERE ID_MEETUP = ?", nativeQuery = true)
    Double obtengoTemperaturaPorIdDeMeetup(Integer idMeetup);

    @Query(value = "SELECT 1 FROM MEETUPS WHERE ID_MEETUP = ?", nativeQuery = true)
    Integer existeMeetup(Integer IdMeetup);

    @Query(value = "SELECT * FROM MEETUPS WHERE ID_MEETUP = ?", nativeQuery = true)
    Meetup  obtengoMeetupPorId(Integer idMeetup);

    @Query(value = "SELECT CANTIDAD_CERVEZA FROM MEETUPS WHERE ID_MEETUP = ?", nativeQuery = true)
    Integer obtengoCantidadDeCervezaParaUnaMeetup(Integer idMeetup);

    @Transactional
    @Modifying
    @Query(value = "UPDATE MEETUPS SET CANTIDAD_CERVEZA = :cantidadCerveza WHERE ID_MEETUP = :idMeetup", nativeQuery = true)
    void actualizoCantidadDeCervezaParaLaMeetup(@Param("cantidadCerveza") Integer cantidadDeCerveza, @Param("idMeetup") Integer idMeetup);

}
