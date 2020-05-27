package com.example.demo.TomaMiManoWeb.Controladores;

import com.example.demo.TomaMiManoWeb.Entidades.Operacion;
import com.example.demo.TomaMiManoWeb.Enumeraciones.Categorias;
import com.example.demo.TomaMiManoWeb.Enumeraciones.TipoOperacion;
import com.example.demo.TomaMiManoWeb.Errores.ErrorServicio;
import com.example.demo.TomaMiManoWeb.Repositorios.OperacionRepositorio;
import com.example.demo.TomaMiManoWeb.Servicios.OperacionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/")
public class OperacionControlador {

    @Autowired
    OperacionRepositorio operacionRepositorio;
    @Autowired
    OperacionServicio operacionServicio;
    @GetMapping("/cargarOpe")
    public String Cargar(){
        return "cargar.html";
    }
    @GetMapping("/eleccion")
    public String Seleccionar(){
        return "eleccion.html";
    }
    @PostMapping("/carga")
    public String agregarOperacion(ModelMap modelo,MultipartFile archivo,
                                   @RequestParam String titulo,
                                   @RequestParam String comentario,
                                   @RequestParam TipoOperacion tipoOperacion,
                                   @RequestParam Categorias categoria) {
        try {
            operacionServicio.registrarOperacion(titulo, comentario, tipoOperacion, archivo, categoria);
            modelo.put("titulo", "Operacion");
            modelo.put("descripcion", "creado de la forma correcta");
        } catch (ErrorServicio ex) {
            modelo.put("error", ex.getMessage());

            System.out.println("NO ME TRAE NADA");


        return "cargarOpe.html";
    }
        modelo.put("titulo","PANTALLA DE CARGA ");
        return "eleccion.html";
    }
    @GetMapping("/buscarOpe")
        public String BuscarOpe(ModelMap modelo){
        //List<Operacion> operaciones= operacionRepositorio.findAll();
        //  modelo.put("Operaciones",operaciones);
        return "busqueda.html";
    }
}
