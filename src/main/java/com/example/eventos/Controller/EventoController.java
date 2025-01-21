package com.example.eventos.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.example.eventos.Repository.ConvidadoRepository;
import com.example.eventos.Repository.EventoRepository;
import com.example.eventos.model.Convidado;
import com.example.eventos.model.Evento_model;

@Controller
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoRepository er;
    @Autowired
    private ConvidadoRepository cv;

    // TODO aqui está ok
    @GetMapping("/cadastro")
    public String forms() {
        return "evento/formulario";
    }

    // TODO aqui está ok
    @PostMapping("/cadastro")
    public String forms1(Evento_model dados) {
        if (!er.existsByCodigo(dados.getCodigo())) {
            er.save(dados);
        }
        return "redirect:/eventos/lista";
    }

    // TODO aqui está ok
    @GetMapping("/lista")
    public ModelAndView lista_evento() {
        ModelAndView mv = new ModelAndView("evento/lista");
        Iterable<Evento_model> eventos = er.findAll();
        mv.addObject("evento", eventos);
        return mv;
    }

    // TODO aqui está ok
    @GetMapping("/{codigo}")
    public ModelAndView detalhes_eventos(@PathVariable("codigo") long codigo) {
        Evento_model evento = er.findByCodigo(codigo);
        if (evento == null) {
            //System.out.println("Evento com o código " + codigo + " não encontrado.");
            return new ModelAndView("redirect:/eventos/lista");
        } else {
            ModelAndView mv = new ModelAndView("evento/detalhes");
            mv.addObject("evento", evento);
            //System.out.println("Evento encontrado: " + evento);

            Iterable<Convidado> convidado = cv.findByEvento(evento);
            mv.addObject("convidado", convidado);
            return mv;
        }
    }

    // TODO aqui está ok
    @PostMapping("/{codigo}")
    public String detalhes_eventos_2(@PathVariable("codigo") long codigo, Convidado pessoa) {
        Evento_model evento = er.findByCodigo(codigo);
        if (evento == null) {
            return "redirect:/eventos/lista";
        } else {
            pessoa.setEvento(evento);
            cv.save(pessoa);
            System.out.println(pessoa);
            return "redirect:/eventos/" + codigo;
        }
    }

    @PostMapping("/deletar/{codigo}")
    public String deletarEvento(@PathVariable Long codigo) {
        Evento_model evento = er.findByCodigo(codigo);
        if (evento != null) {
            List<Convidado>convidados = cv.findAllByEvento(evento);
            for(Convidado c : convidados)
            {
                cv.delete(c);
            }            
            er.delete(evento);
        }
        return "redirect:/eventos/lista";
    }

    @PostMapping("/eventos/detalhes")
    public String adicionarConvidado(@PathVariable("codigo") long codigo,Convidado pessoa) {
        Evento_model evento = er.findByCodigo(codigo);
        if (evento != null) {
           pessoa.setEvento(evento);
           cv.save(pessoa);        
        }
        return "redirect:/eventos/detalhes?codigo=" + codigo;
    }

}