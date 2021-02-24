package com.santander.meetup.domain;


import javax.persistence.*;

@Entity
@Table(name = "parametros")
public class Parametro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_parametro")
    private Integer idParametro;
    @Column(name = "temperatura_minima")
    private Double temperaturaMinima;
    @Column(name = "temperatura_maxima")
    private Double temperaturaMaxima;
    @Column(name = "cantidad_cerveza_persona")
    private Double cantidadDeCervezaPorPersona;

    public Integer getIdParametro() {
        return idParametro;
    }

    public void setIdParametro(Integer idParametro) {
        this.idParametro = idParametro;
    }

    public Double getTemperaturaMinima() {
        return temperaturaMinima;
    }

    public void setTemperaturaMinima(Double temperaturaMinima) {
        this.temperaturaMinima = temperaturaMinima;
    }

    public Double getTemperaturaMaxima() {
        return temperaturaMaxima;
    }

    public void setTemperaturaMaxima(Double temperaturaMaxima) {
        this.temperaturaMaxima = temperaturaMaxima;
    }

    public Double getCantidadDeCervezaPorPersona() {
        return cantidadDeCervezaPorPersona;
    }

    public void setCantidadDeCervezaPorPersona(Double cantidadDeCervezaPorPersona) {
        this.cantidadDeCervezaPorPersona = cantidadDeCervezaPorPersona;
    }
}
