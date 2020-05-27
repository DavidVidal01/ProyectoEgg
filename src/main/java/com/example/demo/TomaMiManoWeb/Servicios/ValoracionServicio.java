package com.example.demo.TomaMiManoWeb.Servicios;

import com.example.demo.TomaMiManoWeb.Entidades.Usuario;
import com.example.demo.TomaMiManoWeb.Entidades.Valoracion;
import com.example.demo.TomaMiManoWeb.Repositorios.UsuarioRepositorio;
import com.example.demo.TomaMiManoWeb.Repositorios.ValoracionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ValoracionServicio {
    @Autowired
    private ValoracionRepositorio valoracionrepositorio;
    @Autowired
    private UsuarioRepositorio usuariorepositorio;

    @Transactional
    public void valorarUsuario(String id_usu,String id_valoracion, String puntaje, String comentario) {

        Usuario usuario = usuariorepositorio.getOne(id_usu);
        //debo convertirlo a un numero ya que en html se como String (parsear)
        int puntajeparseado = Integer.parseInt(puntaje);

        Valoracion valoracion = new Valoracion();
        valoracion.setUsuario(usuario);
        valoracion.setPuntaje(puntajeparseado);
        //  comentario = comentario.toUpperCase();
        valoracion.setComentario(comentario.toUpperCase());

        valoracionrepositorio.save(valoracion);
        promedio( id_usu,id_valoracion, puntaje);

    }


    public void promedio(String id_usu,String id_valoracion, String puntaje) {
        Usuario usuario = usuariorepositorio.getOne(id_usu);
       // List<Valoracion> valoracions = valoracionrepositorio.buscartodaslasValoraciones(dni);
        //Integer total=0;
        //int cant_valo=0;
        //for (Valoracion valoracion: valoracions
        //     ) {
        //    total =valoracion.getPuntaje();
        //    cant_valo++;
        //}
        //usuario.setValoracionpersonal(total/cant_valo);
        double auxpromedio = valoracionrepositorio.promedioValoracion(id_valoracion);
        usuario.setValoracionpersonal(auxpromedio);
        usuario.setDni(usuario.getDni());
        usuario.setNombre(usuario.getNombre());
        usuario.setApellido(usuario.getApellido());
        usuario.setFecha_nac(usuario.getFecha_nac());
        usuario.setCredito(usuario.getCredito());
        //valoracionrepositorio.save(auxpromedio);
        usuariorepositorio.save(usuario);
    }
}
