package com.example.demo.TomaMiManoWeb.Entidades;

import com.example.demo.TomaMiManoWeb.Enumeraciones.Departamento;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Domicilio {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id_domicilio;
    private String calle;
    private int nro;
    @Enumerated(EnumType.STRING)
    private Departamento departamento;

    public Domicilio() {
    }

    public String getId_domicilio() {
        return id_domicilio;
    }

    public void setId_domicilio(String id_domicilio) {
        this.id_domicilio = id_domicilio;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNro() {
        return nro;
    }

    public void setNro(int nro) {
        this.nro = nro;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
}
