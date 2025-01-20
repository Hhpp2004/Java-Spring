package com.example.eventos.Repository;

import org.springframework.data.repository.CrudRepository;

import com.example.eventos.model.Convidado;
import com.example.eventos.model.Evento_model;

public interface ConvidadoRepository extends CrudRepository<Convidado, String>{
    Iterable<Convidado> findByEvento(Evento_model evento);
}
