package com.santander.meetup.persistence.crud;

import com.santander.meetup.domain.Parametro;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ParametroCrudRepository extends CrudRepository <Parametro,Integer> {

    List<Parametro> findAll();

}
