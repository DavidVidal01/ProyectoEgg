package com.example.demo.TomaMiManoWeb.Controladores;

import com.example.demo.TomaMiManoWeb.Enumeraciones.Departamento;
import com.example.demo.TomaMiManoWeb.Enumeraciones.Sexo;
import com.example.demo.TomaMiManoWeb.Errores.ErrorServicio;
import com.example.demo.TomaMiManoWeb.Servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;


@Controller
@RequestMapping("/")
public class UsuarioControlador {
    @Autowired
    private UsuarioServicio usuarioServicio;

    @PostMapping("/registrar")

    public String agregarUsuario(ModelMap modelo, MultipartFile archivo,
                                 @RequestParam String dni, @RequestParam String nombre,
                                 @RequestParam String apellido, @RequestParam String mail,
                                 @RequestParam String clave, @RequestParam String clave2,
                                 @RequestParam String calle, @RequestParam int nro,
                                 @RequestParam Departamento departamentos,
                                 @RequestParam Sexo sexo, Date f_nac,@RequestParam Double tel) {
        try {

            usuarioServicio.registrar(archivo,dni,nombre,apellido,clave,calle,nro,departamentos,sexo,tel,mail,clave2);
            modelo.put("titulo", "Usuario");
            modelo.put("descripcion", "Creado de Forma Correcta!");


        } catch (ErrorServicio ex) {
            modelo.put("error", ex.getMessage());
            modelo.put("nombre",nombre);
            modelo.put("apellido",apellido);
            modelo.put("mail",dni);
            modelo.put("clave1",clave);
            return "registro.html";
        }
        modelo.put("titulo","Bienvenido ");
        return "login.html";
    }
    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, @RequestParam(required = false) String logout,
                        ModelMap model) {
        if (error != null) {
            model.put("error", "Nombre de Usuario o clave incorrectos.");

        }

        if (logout != null) {
            model.put("logout", "Ha salido correctamente de la plataforma");
        }
        return "login.html";
    }



}