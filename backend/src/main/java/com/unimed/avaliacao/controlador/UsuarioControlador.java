package com.unimed.avaliacao.controlador;

import com.unimed.avaliacao.entidade.Usuario;
import com.unimed.avaliacao.excecao.RegistroNaoEncontradoException;
import com.unimed.avaliacao.servico.UsuarioServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.BadAttributeValueExpException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/usuario", produces = { MediaType.APPLICATION_JSON_VALUE })
public class UsuarioControlador {


    @Autowired
    UsuarioServico usuarioServico;

    @GetMapping
    public ResponseEntity listarUsuarios() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(usuarioServico.listarUsuarios());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{login}")
    public ResponseEntity buscarUsuarioPorLogin(@PathVariable String login) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(usuarioServico.buscarUsuarioPorLogin(login));
        } catch (RegistroNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity criarUsuario(@RequestBody Usuario usuario) {
        try {
            usuarioServico.criarUsuario(usuario);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } catch (BadAttributeValueExpException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{login}")
    public ResponseEntity atualizarUsuario(@PathVariable String login, @RequestBody Usuario usuario) {
        try {
            usuarioServico.atualizarUsuario(login, usuario);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } catch (RegistroNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{login}")
    public ResponseEntity deletarUsuario(@PathVariable String login) {
        try {
            usuarioServico.deletarUsuario(login);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } catch (RegistroNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (BadAttributeValueExpException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("autenticado")
    public ResponseEntity verificaAutenticado() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
