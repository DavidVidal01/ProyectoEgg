package com.example.demo.TomaMiManoWeb.Servicios;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.TomaMiManoWeb.Entidades.Foto;
import com.example.demo.TomaMiManoWeb.Errores.ErrorServicio;
import com.example.demo.TomaMiManoWeb.Repositorios.FotoRepositorio;

@Service
public class FotoServicio {

	@Autowired
	private FotoRepositorio fotoRepositorio;
	
	@Transactional //con esto estamos diciendo q si el metodo no larga ninguna execpcion, entonces hace un comit a la base de datos y se aplican todos los cambios. En el caso q exista una excepcion y no es atrapada se vuelve atras con la transaccion  y no se aplica nada en la base de datos
	public Foto guardar(MultipartFile archivo) throws ErrorServicio

	{
		System.out.println(archivo.getName());
		if(archivo != null)
		{
			try
			{
			Foto foto = new Foto();
			foto.setMime(archivo.getContentType());
			foto.setNombre(archivo.getName());
			foto.setContenido(archivo.getBytes());
				System.out.println("la guarde");
			return fotoRepositorio.save(foto);
			
			}catch(Exception e)
			{
				System.err.println(e.getMessage());
				System.out.println("no la guarde");
			}
		}
			return null;
		}
	
	@Transactional 
	public Foto actualizar(String idFoto, MultipartFile archivo) throws ErrorServicio
	{
	
		if(archivo != null)
		{
			try
			{
			Foto foto = new Foto();
			
			if(idFoto != null)
			{
				Optional<Foto> respuesta = fotoRepositorio.findById(idFoto);
				
				if(respuesta.isPresent())
				{
					foto = respuesta.get();
				}
			}
			
			foto.setMime(archivo.getContentType());
			foto.setNombre(archivo.getName());
			foto.setContenido(archivo.getBytes());
			
			return fotoRepositorio.save(foto);
			
			}catch(Exception e)
			{
				System.err.println(e.getMessage());
			}
		}
			return null;
		
		}
	
	}
	

