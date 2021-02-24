package com.santander.meetup.service;

import com.santander.meetup.domain.Parametro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CervezaService {

    public static final int CANTIDAD_DE_CERVEZAS_POR_PACK = 6;

    @Autowired
    private ParametroService parametroService;

    @Autowired
    private MeetupUsuarioService meetupUsuarioService;

    @Autowired
    private MeetupService meetupService;


    private int obtengoCantidadDePacksDeCerveza(Double value) {
        return (int) Math.ceil(value / CANTIDAD_DE_CERVEZAS_POR_PACK );
    }


    public Integer calcularCantidadDeCervezaParaLaMeetup(Integer idMeetup){

        List<Parametro> parametroList = parametroService.obtenerListaDeParametrosDeTemperatura();

        Integer cantidadDeInvitados = meetupUsuarioService.obtengoCantidadDeInvitados(idMeetup);
        Double temperaturaDelaMeetup = meetupService.obtengoTemperaturaDeLaMeetup(idMeetup);

        Double cantidadDeCervezaPorPersona = null;

        for(Parametro parametro : parametroList){
            if(temperaturaDelaMeetup >= parametro.getTemperaturaMinima() && temperaturaDelaMeetup <= parametro.getTemperaturaMaxima()){
                cantidadDeCervezaPorPersona = parametro.getCantidadDeCervezaPorPersona();
            }
        }
        Double cantidadTotal = cantidadDeCervezaPorPersona * cantidadDeInvitados;

        return obtengoCantidadDePacksDeCerveza(cantidadTotal);

    }



}
