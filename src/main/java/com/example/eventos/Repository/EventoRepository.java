package com.example.eventos.Repository;

import org.springframework.data.repository.CrudRepository;

import com.example.eventos.model.Evento_model;

public interface EventoRepository extends CrudRepository<Evento_model,String> {
    Evento_model findByCodigo(long codigo);
    boolean existsByCodigo(long codigo);
    default void deleteAllByEvento(Evento_model evento) {
        delete(evento);
    }
    
}
