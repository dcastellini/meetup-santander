package com.santander.meetup.domain;

import javax.persistence.*;

@Entity
@Table(name = "MEETUPS_USUARIOS")
public class MeetupUsuario {

    @EmbeddedId
    private MeetupUsuarioPk id;
    @Column(name = "id_asistencia")
    private Integer idAsistencia;

    @ManyToOne
    @JoinColumn(name = "ID_ASISTENCIA", insertable = false, updatable = false)
    private Asistencia asistencia;

    public MeetupUsuario(){}

    public MeetupUsuario(MeetupUsuarioPk id, Integer idAsistencia) {
        this.id = id;
        this.idAsistencia = idAsistencia;
    }

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO", insertable = false, updatable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "ID_MEETUP", insertable = false, updatable = false)
    private Meetup meetup;

    public MeetupUsuarioPk getId() {
        return id;
    }

    public void setId(MeetupUsuarioPk id) {
        this.id = id;
    }

    public Asistencia getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(Asistencia asistencia) {
        this.asistencia = asistencia;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Meetup getMeetup() {
        return meetup;
    }

    public void setMeetup(Meetup meetup) {
        this.meetup = meetup;
    }
}
