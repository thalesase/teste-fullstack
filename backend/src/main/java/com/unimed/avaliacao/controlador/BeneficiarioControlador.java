package com.unimed.avaliacao.controlador;

import com.unimed.avaliacao.entidade.Beneficiario;
import com.unimed.avaliacao.excecao.RegistroNaoEncontradoException;
import com.unimed.avaliacao.servico.BeneficiarioServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/beneficiario", produces = { MediaType.APPLICATION_JSON_VALUE })
public class BeneficiarioControlador {


    @Autowired
    BeneficiarioServico beneficiarioServico;
    @GetMapping
    public ResponseEntity listarTodosBeneficiario() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(beneficiarioServico.listarBeneficiario());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity buscarBeneficiarioPorId(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(beneficiarioServico.buscarBeneficiarioPorId(id));
        } catch (RegistroNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity salvarBeneficiario(@RequestBody Beneficiario beneficiario) {
        try {
            beneficiarioServico.criarBeneficiario(beneficiario);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizarBeneficiario(@PathVariable int id, @RequestBody Beneficiario beneficiario) {
        try {
            beneficiarioServico.atualizarBeneficiario(id, beneficiario);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } catch (RegistroNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarBeneficiario(@PathVariable int id) {
        try {
            beneficiarioServico.deletarBeneficiario(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } catch (RegistroNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
