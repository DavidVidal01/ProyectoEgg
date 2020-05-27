
package com.example.demo.TomaMiManoWeb.Repositorios;


import com.example.demo.TomaMiManoWeb.Entidades.Operacion;
import java.util.List;

import com.example.demo.TomaMiManoWeb.Enumeraciones.TipoOperacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface OperacionRepositorio extends JpaRepository<Operacion, String> {
    
    @Query("SELECT c FROM Operacion c WHERE c.titulo LIKE %:titulo% ORDER BY c.titulo DESC")
    public List<Operacion> buscarOperacionPorNombre(@Param("titulo")String titulo);
    
    @Query("SELECT o FROM Operacion o WHERE o.tipoOperacion = :tipoOperacion")
    public List<Operacion> buscarOperacionPorTipo(@Param("tipoOperacion")TipoOperacion tipoOperacion);


}
