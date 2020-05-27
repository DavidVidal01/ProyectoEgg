package com.example.demo.TomaMiManoWeb.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.TomaMiManoWeb.Entidades.Foto;

public interface FotoRepositorio extends JpaRepository<Foto, String>{
	
}
