package com.santander.meetup.persistence.repository;


import com.santander.meetup.domain.Parametro;
import com.santander.meetup.persistence.crud.ParametroCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ParametroRepository {

    @Autowired
    private ParametroCrudRepository parametroCrudRepository;

    public List<Parametro> obtenerListaDeParametrosDeTemperatura(){
        return parametroCrudRepository.findAll();
    }


}
