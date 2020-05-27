package com.example.demo.TomaMiManoWeb.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.TomaMiManoWeb.Entidades.Usuario;

import java.util.List;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, String> {

	@Query("SELECT c FROM Usuario c WHERE c.mail = :mail")
	public Usuario buscarPorMail(@Param("mail") String mail);

	@Query("SELECT c FROM Usuario c WHERE c.nombre = :nombre")
	public List<Usuario> buscarTodos(@Param("nombre") String nombre);
	@Query("SELECT c FROM Usuario c WHERE c.dni = :dni")
	public Usuario buscarPorDNI(@Param("dni")String dni);

}
