package com.example.demo.TomaMiManoWeb.Repositorios;



import com.example.demo.TomaMiManoWeb.Entidades.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepositorio extends JpaRepository<Match,String> {
    @Query("SELECT c FROM Match c WHERE c.usuario_receptor.id= :id_usu ORDER BY c.inicio DESC")
    public List<Match> buscarMatchRecibidos(@Param("id_usu")String id_usu );

    @Query("SELECT c FROM Match c WHERE c.usuario_dador.id= :id_usu ORDER BY c.respuesta DESC")
    public List<Match> buscarMatchDados(@Param("id_usu")String id_usu );


}
