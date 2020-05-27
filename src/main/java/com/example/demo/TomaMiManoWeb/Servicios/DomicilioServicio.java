package com.example.demo.TomaMiManoWeb.Servicios;

import com.example.demo.TomaMiManoWeb.Entidades.Domicilio;
import com.example.demo.TomaMiManoWeb.Enumeraciones.Departamento;
import com.example.demo.TomaMiManoWeb.Errores.ErrorServicio;
import com.example.demo.TomaMiManoWeb.Repositorios.DomicilioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class DomicilioServicio{
    @Autowired
    DomicilioRepositorio domicilioRepositorio;
@Transactional
public Domicilio registrarDomicilio(String calle, int nro, Departamento departamento) throws ErrorServicio {
    validar(departamento, calle, nro);
    Domicilio domicilio =new Domicilio();
    domicilio.setCalle(calle);
    domicilio.setNro(nro);
    domicilio.setDepartamento(departamento);
   return domicilioRepositorio.save(domicilio);
}
@Transactional
public void modificarDomicilio(String idDom,String calle,int nro,Departamento departamento)  throws ErrorServicio {
    validar(departamento, calle, nro);
    Optional<Domicilio> respuesta = domicilioRepositorio.findById(idDom);
    if (respuesta.isPresent()) {
        Domicilio domicilio = respuesta.get();
        domicilio.setDepartamento(departamento);
        domicilio.setNro(nro);
        domicilio.setCalle(calle);
        domicilioRepositorio.save(domicilio);
    }
    else
        throw new ErrorServicio("No se encontro el Domicilio Solicitado");
}
@Transactional
 public void eliminarDomicilio(String idDom)throws ErrorServicio{
     Optional<Domicilio> respuesta = domicilioRepositorio.findById(idDom);
     if(respuesta.isPresent()) {
         Domicilio domicilio = respuesta.get();
         domicilioRepositorio.delete(domicilio);
     }
     else
         throw new ErrorServicio("No se encontro el Domicilio Solicitado");
    }

public void validar(Departamento departamento,String calle,int nro) throws ErrorServicio {
    if(departamento == null ){
        throw new ErrorServicio("No ha ingresado ninguna zona");
    }
    if(calle == null || calle.isEmpty()){
        throw new ErrorServicio("No puede ingresar un nombre de calle nulo");
    }
    if(String.valueOf(nro)==null || String.valueOf(nro).isEmpty()){
        throw new ErrorServicio("No puede ingresar un numero de calle nulo");
    }
    }
}