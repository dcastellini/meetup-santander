package com.santander.meetup.domain;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    private String nombre;
    private String apellido;
    private String usuario;
    private String password;
    private String email;
    @Column(name = "privilegio")
    private Integer idPrivilegio;

    @ManyToOne
    @JoinColumn(name = "privilegio", insertable = false, updatable = false)
    private Privilegio privilegio;

    @OneToMany(mappedBy = "usuario")
    private List<Meetup> meetups;

    @OneToMany(mappedBy = "usuario")
    private List<MeetupUsuario> meetupUsuarios;

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Privilegio getPrivilegio() {
        return privilegio;
    }

    public void setPrivilegio(Privilegio privilegio) {
        this.privilegio = privilegio;
    }

    public List<Meetup> getMeetups() {
        return meetups;
    }

    public void setMeetups(List<Meetup> meetups) {
        this.meetups = meetups;
    }

    public List<MeetupUsuario> getMeetupUsuarios() {
        return meetupUsuarios;
    }

    public void setMeetupUsuarios(List<MeetupUsuario> meetupUsuarios) {
        this.meetupUsuarios = meetupUsuarios;
    }

    public Integer getIdPrivilegio() {
        return idPrivilegio;
    }

    public void setIdPrivilegio(Integer idPrivilegio) {
        this.idPrivilegio = idPrivilegio;
    }
}
