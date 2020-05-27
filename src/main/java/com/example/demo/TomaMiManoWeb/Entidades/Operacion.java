package com.example.demo.TomaMiManoWeb.Entidades;

import com.example.demo.TomaMiManoWeb.Enumeraciones.Categorias;
import com.example.demo.TomaMiManoWeb.Enumeraciones.TipoOperacion;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Operacion {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid",strategy = "uuid2")
	private String id_Operacion;
	private String titulo;
	private String detalle;

	@Enumerated(EnumType.STRING)
	private TipoOperacion tipoOperacion;
	@ManyToOne

	private  Usuario usuario;
	@OneToOne
	private Foto foto;
	@Enumerated(EnumType.STRING)
	private Categorias categorias;
	public Operacion() {

	}

	public String getId_Operacion() {
	return id_Operacion;
}

	public void setId_Operacion(String id_Operacion) {
	this.id_Operacion = id_Operacion;
}

	public String getTitulo() {
	return titulo;
}

	public void setTitulo(String titulo) {
	this.titulo = titulo;
}

	public String getDetalle() {
	return detalle;
}

	public void setDetalle(String detalle) {
	this.detalle = detalle;
}

	public Foto getFoto() {
		return foto;
	}

	public void setFoto(Foto foto) {
		this.foto = foto;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public TipoOperacion getTipoOperacion() {
		return tipoOperacion;
	}

	public void setTipoOperacion(TipoOperacion tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}

	public Categorias getCategorias() {
		return categorias;
	}

	public void setCategorias(Categorias categorias) {
		this.categorias = categorias;
	}
}
