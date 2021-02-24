package com.santander.meetup.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class MeetupUsuarioPk implements Serializable {

    @Column(name = "id_meetup")
    private Integer idMeetup;
    @Column(name = "id_usuario")
    private Integer idUsuario;

    public MeetupUsuarioPk(){}

    public MeetupUsuarioPk(Integer idMeetup, Integer idUsuario) {
        this.idMeetup = idMeetup;
        this.idUsuario = idUsuario;
    }

    public Integer getIdMeetup() {
        return idMeetup;
    }

    public void setIdMeetup(Integer idMeetup) {
        this.idMeetup = idMeetup;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
}
