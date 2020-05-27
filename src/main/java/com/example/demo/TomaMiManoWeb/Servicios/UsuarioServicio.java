package com.example.demo.TomaMiManoWeb.Servicios;

import java.util.*;

import javax.transaction.Transactional;

import com.example.demo.TomaMiManoWeb.Entidades.Domicilio;
import com.example.demo.TomaMiManoWeb.Enumeraciones.Departamento;
import com.example.demo.TomaMiManoWeb.Enumeraciones.Sexo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.TomaMiManoWeb.Entidades.Foto;
import com.example.demo.TomaMiManoWeb.Entidades.Usuario;
import com.example.demo.TomaMiManoWeb.Errores.ErrorServicio;
import com.example.demo.TomaMiManoWeb.Repositorios.UsuarioRepositorio;


@Service
public class UsuarioServicio implements UserDetailsService{

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private FotoServicio fotoServicio;
    @Autowired
    private DomicilioServicio domicilioServicio;
    
    @Transactional //con esto estamos diciendo q si el metodo no larga ninguna execpcion, entonces hace un comit a la base de datos y se aplican todos los cambios. En el caso q exista una excepcion y no es atrapada se vuelve atras con la transaccion  y no se aplica nada en la base de datos
    public void registrar(MultipartFile archivo, String  dni, String nombre, String apellido, String clave, String calle, int nro, Departamento depto, Sexo sexo, Double nro_tel, String mail, String clave2) throws ErrorServicio
    {



        validar(dni,nombre,apellido,clave);
        
        Usuario usuario = new Usuario();
        Date fecha = new Date();
        usuario.setDni(dni);
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        String encriptada = new BCryptPasswordEncoder().encode(clave);
        usuario.setClave(encriptada);
        usuario.setCredito(1);
        usuario.setAlta(fecha);
        usuario.setSexo(sexo);
//        usuario.setFecha_nac(((Time) f_nac));
        usuario.setNro_telefono(nro_tel);
        usuario.setMail(mail);
        Foto foto = fotoServicio.guardar(archivo);
        Domicilio domicilio =  domicilioServicio.registrarDomicilio(calle,nro,depto);
        usuario.setDomicilio(domicilio);
        usuario.setFoto(foto);
    	
        usuarioRepositorio.save(usuario);
    	
        
    }
    
    @Transactional
    public void modificar(MultipartFile archivo,String id,String  dni, String nombre,String apellido, String clave, int credito) throws ErrorServicio
    {

        validar(dni,nombre,apellido,clave);
        
        Optional<Usuario> respuesta = (Optional<Usuario>) usuarioRepositorio.findById(id);
        
        if(respuesta.isPresent())
        {
        	Usuario usuario = respuesta.get();
        	usuario.setDni(dni);
            usuario.setNombre(nombre);
            usuario.setApellido(apellido);
            usuario.setClave(clave);
            usuario.setCredito(credito);
            
            String idFoto = null;
            
            if(usuario.getFoto() != null)
        {
            idFoto = usuario.getFoto().getId_foto();
        }
            
            Foto foto = fotoServicio.actualizar(idFoto, archivo);
            usuario.setFoto(foto);
            
            usuarioRepositorio.save(usuario);
    		
		}
		
		else
		{
		
			throw new ErrorServicio ("No se encontro el usuario solicitado.");
			
		}
    
    }
    
    private void validar(String dni,String nombre, String apellido,String clave) throws ErrorServicio
	{
	    if(clave == null || clave.isEmpty())
        {
        throw new ErrorServicio("Debe ingresar una clave para el cliente !!.");
        }
		if(nombre == null || nombre.isEmpty())
		{
			throw new ErrorServicio("El nombre del cliente NO puede ser nulo !!.");
		}
		if(apellido == null || apellido.isEmpty())
		{
			throw new ErrorServicio("El apellido del cliente NO puede ser nulo !!.");
		}
		if(dni == null || dni.isEmpty())
		{
			throw new ErrorServicio("El DNI del cliente NO puede ser nulo !!.");
		}
		
	}

    @Override
    public UserDetails loadUserByUsername(String dni) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepositorio.buscarPorDNI(dni);

        if (usuario != null) {

            List<GrantedAuthority> permisos = new ArrayList<>();

            GrantedAuthority p1 = new SimpleGrantedAuthority("ROLE_USUARIO_REGISTRADO");
            permisos.add(p1);


            User user = new User(usuario.getDni(), usuario.getClave(), permisos);
            System.out.println(usuario.getDni()+" "+usuario.getNombre());
            return user;

        } else {
            return null;
        }
    }
}
