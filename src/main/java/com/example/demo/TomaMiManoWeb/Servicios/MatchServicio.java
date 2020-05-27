package com.example.demo.TomaMiManoWeb.Servicios;

import com.example.demo.TomaMiManoWeb.Entidades.Match;
import com.example.demo.TomaMiManoWeb.Entidades.Usuario;
import com.example.demo.TomaMiManoWeb.Errores.ErrorServicio;
import com.example.demo.TomaMiManoWeb.Repositorios.MatchRepositorio;

import com.example.demo.TomaMiManoWeb.Repositorios.OperacionRepositorio;
import com.example.demo.TomaMiManoWeb.Repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

@Service
public class MatchServicio {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private MatchRepositorio matchRepositorio;
    @Autowired
    private OperacionRepositorio operacionRepositorio;
    @Transactional
    public void match(String id_usu1, String id_usu2,String id_Operacion) throws ErrorServicio {
        Match match = new Match();
        match.setInicio(new Date());
        match.setOperacion(operacionRepositorio.getOne(id_Operacion));
        if (id_usu1.endsWith(id_usu2)) {

            throw new ErrorServicio("No puede votarse a si mismo");
        }
        Optional<Usuario> respuesta1 = usuarioRepositorio.findById(id_usu1);
        if (respuesta1.isPresent()) {
            Usuario usuario_receptor = respuesta1.get();
            match.setUsuario_receptor(usuario_receptor);

        } else {
            throw new ErrorServicio("No existe un usuario vinculad con ese identificador ");
        }
        Optional<Usuario> respuesta2 = usuarioRepositorio.findById(id_usu2);
        if (respuesta2.isPresent()) {
            Usuario usuario_dador = respuesta2.get();
            match.setUsuario_dador(usuario_dador);
            //notificacionServicio.enviar("Tus servicios han sido requeridos", "Toma mi Mano", match.getUsuario_dador().getMail());
        } else {
            throw new ErrorServicio("No existe un usuario vinculado con ese identificador ");
        }

        matchRepositorio.save(match);
    }

    @Transactional
    public void responder(String id_usu, String id_match) throws ErrorServicio {
        Optional<Match> respuesta = matchRepositorio.findById(id_match);
        if (respuesta.isPresent()) {
            Match match = respuesta.get();
            match.setRespuesta(new Date());
            if (match.getUsuario_dador().equals(id_usu)) {

              //  notificacionServicio.enviar("Tu solicitud ha sido aceptada", "Toma mi Mano", match.getUsuario_receptor().getMail());
                matchRepositorio.save(match);
            } else {
                throw new ErrorServicio("no tiene permiso para realizar la operacion");
            }

        } else {
            throw new ErrorServicio("No existe el match solicitado ");
        }

    }
}