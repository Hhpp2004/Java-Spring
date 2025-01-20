package com.example.eventos.model;


import java.io.Serializable;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Evento_model implements Serializable {
    
    private static final long serialVersionUID = 1l;
    @Id    
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "evento_seq")
    @SequenceGenerator(name = "evento_seq", sequenceName = "evento_model_seq", allocationSize = 1)
    private long codigo;
    private String nome;
    private String local;
    private String data;
    private String horario;
    @OneToMany
    private List<Convidado> pessoas;

    public List<Convidado> getPessoas() {
        return pessoas;
    }
    public void setPessoas(List<Convidado> pessoas) {
        this.pessoas = pessoas;
    }
    public long getCodigo() {
        return codigo;
    }
    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getLocal() {
        return local;
    }
    public void setLocal(String local) {
        this.local = local;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
    public String getHorario() {
        return horario;
    }
    public void setHorario(String horario) {
        this.horario = horario;
    }
}
