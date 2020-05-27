package com.example.demo.TomaMiManoWeb.Repositorios;

import com.example.demo.TomaMiManoWeb.Entidades.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DomicilioRepositorio extends JpaRepository<Domicilio,String> {
        @Query("Select d FROM  Domicilio d where  d.calle = :calle")
        public Domicilio buscarporCalle(@Param("calle")String calle);
        @Query("Select d FROM  Domicilio d where d.calle = :calle AND d.nro = :nro")
        public Domicilio buscarporCalleyNro(@Param("calle")String calle, @Param("nro")String nro);
}
