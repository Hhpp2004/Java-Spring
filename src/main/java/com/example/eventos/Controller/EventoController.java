package com.example.eventos.Controller;

import com.example.eventos.model.*;

import lombok.extern.log4j.Log4j2;

import com.example.eventos.Repository.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Log4j2
@Controller
public class EventoController {

    

    @Autowired
    private EventoRepository er;

    @Autowired
    private ConvidadoRepository cr;

    

    @GetMapping("/favicon.ico")
    @ResponseBody
    void returnNoFavicon() {
        // Não faz nada, apenas evita o erro 404
    }

    @PostMapping("/deletar/{codigo}")
    public String deletarEvento(@PathVariable Long codigo) {
        Evento_model evento = er.findByCodigo(codigo);
        if (evento != null) {
            List<Convidado> convidados = cr.findAllByEvento(evento);
            for (Convidado c : convidados) {
                cr.delete(c);
            }
            er.delete(evento);
        }
        return "redirect:/lista";
    }

    @GetMapping("/cadastro")
    public String forms() {
        return "evento/formulario";
    }

    @PostMapping("/cadastro")
    public String forms1(Evento_model dados) {
        if (!er.existsByCodigo(dados.getCodigo())) {
            er.save(dados);
        }
        return "redirect:/lista";
    }

    @GetMapping("/lista")
    public ModelAndView lista_evento() {
        ModelAndView mv = new ModelAndView("evento/lista");
        Iterable<Evento_model> eventos = er.findAll();
        mv.addObject("evento", eventos);
        return mv;
    }

    @GetMapping("/{codigo}")
    public ModelAndView detalhes_eventos(@PathVariable("codigo") long codigo) {
        Evento_model evento = er.findByCodigo(codigo);
        if (evento == null) {
            return new ModelAndView("redirect:/lista");
        } else {
            ModelAndView mv = new ModelAndView("evento/detalhes");
            mv.addObject("evento", evento);

            Iterable<Convidado> convidados = cr.findByEvento(evento);
            mv.addObject("pessoas", convidados);
            return mv;
        }

    }

    @DeleteMapping("/convidados/deletar/{rg}")
    public String deletar_convidado(@PathVariable String rg) {
        log.info("O delete foi acessado {}", rg);
        Convidado dcv = cr.findByRg(rg);
        if (dcv == null) {
            log.info("Convidado não existente");
            return "evento/404";
        } else {
            Evento_model eve = dcv.getEvento();
            log.info("Evento_model = {}",eve);
            cr.delete(dcv);
            long codigo = eve.getCodigo();
            log.info("Codigo: {}",codigo);
            String cod_str = "" + codigo;
            return "redirect:/" + cod_str;
        }
    }

    @PostMapping("/detalhes/{codigo}")
    public String adicionarConvidado(@PathVariable("codigo") long codigo,
     @RequestParam("nome") String nome,
            @RequestParam("rg") String rg) {
        Evento_model evento = er.findByCodigo(codigo);
        if (evento != null) {
            Convidado convidado = new Convidado();
            convidado.setNome(nome);
            convidado.setRg(rg);
            convidado.setEvento(evento);
            cr.save(convidado);
        }
        return "redirect:/" + codigo;
    }
}