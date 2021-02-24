package com.santander.meetup.domain;

import com.santander.meetup.service.CervezaService;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "meetups")
public class Meetup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_meetup")
    private Integer idMeetup;
    @Column(name = "id_usuario_creador")
    private Integer idUsuarioCreador;
    private LocalDateTime fecha;
    private Double temperatura;
    @Column(name = "cantidad_cerveza")
    private Integer cantidadDeCerveza;
    private Double latitud;
    private Double longitud;

    @ManyToOne
    @JoinColumn(name = "id_usuario_creador", insertable = false, updatable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "meetup")
    private List<MeetupUsuario> meetupUsuarios;

    public Integer getIdMeetup() {
        return idMeetup;
    }

    public void setIdMeetup(Integer idMeetup) {
        this.idMeetup = idMeetup;
    }

    public Integer getIdUsuarioCreador() {
        return idUsuarioCreador;
    }

    public void setIdUsuarioCreador(Integer idUsuarioCreador) {
        this.idUsuarioCreador = idUsuarioCreador;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Double temperatura) {
        this.temperatura = temperatura;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<MeetupUsuario> getMeetupUsuarios() {
        return meetupUsuarios;
    }

    public void setMeetupUsuarios(List<MeetupUsuario> meetupUsuarios) {
        this.meetupUsuarios = meetupUsuarios;
    }

    public Integer getCantidadDeCerveza() {
        return cantidadDeCerveza;
    }

    public void setCantidadDeCerveza(Integer cantidadDeCerveza) {
        this.cantidadDeCerveza = cantidadDeCerveza;
    }

}
