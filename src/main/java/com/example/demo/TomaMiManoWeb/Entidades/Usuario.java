package com.example.demo.TomaMiManoWeb.Entidades;

//Código por daniel arrieta

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.example.demo.TomaMiManoWeb.Enumeraciones.Sexo;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	@NotNull
	private String dni;
	private String nombre;
	private String apellido;
	private String clave;
	private int credito;
	private double nro_telefono;
	private double valoracionpersonal; //promedio;
	private String mail;
	@Enumerated(EnumType.STRING)
	private Sexo sexo;
	@OneToOne
	private Foto foto;
	@OneToOne
	private Domicilio domicilio;
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fecha_nac;
	@Temporal(TemporalType.TIMESTAMP)
	private Date alta;
	@Temporal(TemporalType.TIMESTAMP)
	private Date baja;

	public Usuario() {
		credito=1;
	}


	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
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
	
	

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public int getCredito() {
		return credito;
	}

	public void setCredito(int credito) {
		this.credito = credito;
	}

	public Date getFecha_nac() {
		return fecha_nac;
	}

	public void setFecha_nac(Date fecha_nac) {
		this.fecha_nac = fecha_nac;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public Foto getFoto() {
		return foto;
	}

	public void setFoto(Foto foto) {
		this.foto = foto;
	}

	public double getValoracionpersonal() {
		return valoracionpersonal;
	}

	public void setValoracionpersonal(double valoracionpersonal) {
		this.valoracionpersonal = valoracionpersonal;
	}

	public Date getAlta() {
		return alta;
	}

	public void setAlta(Date alta) {
		this.alta = alta;
	}

	public Date getBaja() {
		return baja;
	}

	public void setBaja(Date baja) {
		this.baja = baja;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Domicilio getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}

	public double getNro_telefono() {
		return nro_telefono;
	}

	public void setNro_telefono(double nro_telefono) {
		this.nro_telefono = nro_telefono;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
