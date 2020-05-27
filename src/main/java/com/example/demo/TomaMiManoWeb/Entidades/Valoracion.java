package com.example.demo.TomaMiManoWeb.Entidades;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Valoracion {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id_valoracion;

    @ManyToOne
    private Usuario usuario;

    private Integer puntaje;
    private String comentario;


    public Valoracion() {
    }

    public String getId_valoracion() {
        return id_valoracion;
    }

    public void setId_valoracion(String id_valoracion) {
        this.id_valoracion = id_valoracion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Integer getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Integer puntaje) {
        this.puntaje = puntaje;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
