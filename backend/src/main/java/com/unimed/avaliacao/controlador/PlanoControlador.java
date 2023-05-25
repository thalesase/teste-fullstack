package com.unimed.avaliacao.controlador;

import com.unimed.avaliacao.entidade.Plano;
import com.unimed.avaliacao.excecao.RegistroNaoEncontradoException;
import com.unimed.avaliacao.servico.PlanoServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/plano", produces = { MediaType.APPLICATION_JSON_VALUE })
public class PlanoControlador {


    @Autowired
    PlanoServico planoServico;
    @GetMapping
    public ResponseEntity listarTodosPlano() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(planoServico.listarPlanos());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity buscarPlanoPorId(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(planoServico.buscarPlanoPorId(id));
        } catch (RegistroNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity salvarPlano(@RequestBody Plano plano) {
        try {
            planoServico.criarPlano(plano);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizarPlano(@PathVariable int id, @RequestBody Plano plano) {
        try {
            planoServico.atualizarPlano(id, plano);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } catch (RegistroNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity atualizarPlano(@PathVariable int id) {
        try {
            planoServico.deletarPlano(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } catch (RegistroNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
