package com.santander.meetup.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "privilegios")
public class Privilegio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_privilegio")
    private Integer idPrivilegio;
    private String descripcion;

    public Privilegio(){}

    public Privilegio(Integer idPrivilegio, String descripcion) {
        this.idPrivilegio = idPrivilegio;
        this.descripcion = descripcion;
    }

    @OneToMany(mappedBy = "privilegio")
    private List<Usuario> usuarios;

    public Integer getIdPrivilegio() {
        return idPrivilegio;
    }

    public void setIdPrivilegio(Integer idPrivilegio) {
        this.idPrivilegio = idPrivilegio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
