package com.example.demo.TomaMiManoWeb.Controladores;



import com.example.demo.TomaMiManoWeb.Enumeraciones.Departamento;
import com.example.demo.TomaMiManoWeb.Enumeraciones.Sexo;
import com.example.demo.TomaMiManoWeb.Errores.ErrorServicio;
import com.example.demo.TomaMiManoWeb.Repositorios.UsuarioRepositorio;
import com.example.demo.TomaMiManoWeb.Servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

@Controller
@RequestMapping("/")
public class PortalControlador {


    @GetMapping("/")
    public String index() {
        return "index.html";
    }
    @GetMapping("/registro")
    public String registrar() {
        return "registro.html";
    }
    @GetMapping("/inicio")
    public String  loggeado(){
        return "inicio.html";
    }



    }




