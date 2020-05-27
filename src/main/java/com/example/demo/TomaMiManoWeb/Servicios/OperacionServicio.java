
package com.example.demo.TomaMiManoWeb.Servicios;

import com.example.demo.TomaMiManoWeb.Entidades.Foto;
import com.example.demo.TomaMiManoWeb.Entidades.Operacion;
import com.example.demo.TomaMiManoWeb.Entidades.Usuario;
import com.example.demo.TomaMiManoWeb.Enumeraciones.Categorias;
import com.example.demo.TomaMiManoWeb.Errores.ErrorServicio;
import com.example.demo.TomaMiManoWeb.Repositorios.*;
import com.example.demo.TomaMiManoWeb.Enumeraciones.TipoOperacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class OperacionServicio {
    
    @Autowired
    private OperacionRepositorio operacionRepositorio;


    @Autowired
    private MatchRepositorio matchRepositorio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private FotoServicio fotoServicio;

    @Transactional
    public  void registrarOperacion(String titulo, String detalle, TipoOperacion tipoOperacion, MultipartFile archivo, Categorias categorias) throws ErrorServicio {
        validar(titulo,detalle,tipoOperacion);
        Object principal  = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = null;
        if(principal instanceof UserDetails){
            userDetails = (UserDetails) principal;
        }
        String username = userDetails.getUsername();
        Usuario usuario =usuarioRepositorio.buscarPorDNI(username);
        Operacion operacion =  new Operacion();
        operacion.setUsuario(usuarioRepositorio.getOne(usuario.getId()));
        operacion.setTitulo(titulo);
        operacion.setDetalle(detalle);
        operacion.setCategorias(categorias);
        operacion.setTipoOperacion(tipoOperacion);
        Foto foto = fotoServicio.guardar(archivo);
        operacion.setFoto(foto);
        operacionRepositorio.save(operacion);
    }
    @Transactional
     public void modificarOperacion(String id_ope,String titulo, String detalle, TipoOperacion tipoOperacion, MultipartFile archivo,String dni) throws ErrorServicio{
        validar(titulo,detalle,tipoOperacion);
         Optional<Operacion> respuesta = operacionRepositorio.findById(id_ope);
         if(respuesta!=null){
            Operacion operacion = respuesta.get();
                if(operacion.getUsuario().getDni().equals(dni)){
                        operacion.setTitulo(titulo);
                        operacion.setDetalle(detalle);
                        operacion.setTipoOperacion(tipoOperacion);
                        String idFoto= null;
                        if(operacion.getFoto()!=null){
                            idFoto=operacion.getFoto().getId_foto();
                        }
                        Foto foto=  fotoServicio.actualizar(idFoto,archivo);
                        operacion.setFoto(foto);
                        operacionRepositorio.save(operacion);
                }else{
                    throw new ErrorServicio("No tenes permisos para realizar esta operacion");
                }
         }else{
                    throw new ErrorServicio("No se encuentra la operacion con ese Id");
         }
     }

     @Transactional
     public void eliminarOperacion(String dni,String id_ope)throws  ErrorServicio{
        Optional<Operacion> respuesta = operacionRepositorio.findById(id_ope);
        if(respuesta.isPresent()){
                Operacion operacion = respuesta.get();
                if(operacion.getTipoOperacion().equals("Producto")) {
                    if (operacion.getUsuario().getDni().equals(dni)) {
                            operacionRepositorio.delete(operacion);
                    }else{
                        throw new ErrorServicio("No tenes permisos para realizar esta operacion");
                    }
                }else{
                    throw new ErrorServicio("No se puede dar de baja a un Servicio");
                }
        }
     }


    public void validar(String titulo,String detalle,TipoOperacion tipoOperacion) throws ErrorServicio {
        if(titulo == null || titulo.isEmpty()){
            throw new ErrorServicio("El titulo de la operacion no puede ser nulo");
        }
        if(detalle == null|| detalle.isEmpty()){
            throw new ErrorServicio("El detalle de la operacion no puede ser nulo");
        }
        if(tipoOperacion==null){
            throw new ErrorServicio("El tipo de operacion no puede ser nulo");
        }
    }
/*
    public void comprobanteOperacion(String id_Operacion, String titulo, String id_usu2, String detalle) throws ErrorServicio {
        
        Operacion operacion = new Operacion();
      
        operacion.setTitulo(titulo);

        operacion.setDetalle(detalle);

        operacion.setUsuario(usuarioRepositorio.getOne(id_usu2));

        operacionRepositorio.save(operacion);
        
    }
    
  */
}
