package com.example.eventos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Convidado {

    @Id
    private String rg;
    private String nome; 
    @ManyToOne
    private Evento_model evento;

    public Evento_model getEvento() {
        return evento;
    }
    public void setEvento(Evento_model evento) {
        this.evento = evento;
    }
    public String getRg() {
        return rg;
    }
    public void setRg(String rg) {
        this.rg = rg;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
       
}
