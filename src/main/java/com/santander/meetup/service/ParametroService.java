package com.santander.meetup.service;

import com.santander.meetup.domain.Parametro;
import com.santander.meetup.persistence.repository.ParametroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParametroService {

    @Autowired
    private ParametroRepository parametroRepository;

    public List<Parametro> obtenerListaDeParametrosDeTemperatura(){
        return parametroRepository.obtenerListaDeParametrosDeTemperatura();
    }

}
