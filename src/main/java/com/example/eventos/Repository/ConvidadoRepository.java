package com.example.eventos.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.eventos.model.Convidado;
import com.example.eventos.model.Evento_model;

public interface ConvidadoRepository extends CrudRepository<Convidado, String>{
    Iterable<Convidado> findByEvento(Evento_model evento);
    Convidado findByRg(String rg);
     @Query("SELECT c FROM Convidado c WHERE c.evento = :evento")
    List<Convidado> findAllByEvento(@Param("evento") Evento_model evento);
}
