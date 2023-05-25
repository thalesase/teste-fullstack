package com.unimed.avaliacao.servico;

import com.unimed.avaliacao.entidade.Plano;
import com.unimed.avaliacao.entidade.Usuario;
import com.unimed.avaliacao.excecao.RegistroNaoEncontradoException;

import javax.management.BadAttributeValueExpException;
import java.util.List;

public interface UsuarioServico {
    List<Usuario> listarUsuarios();
    Usuario buscarUsuarioPorLogin(String login) throws RegistroNaoEncontradoException;
    void criarUsuario(Usuario usuario) throws BadAttributeValueExpException;
    void atualizarUsuario(String login, Usuario usuario) throws RegistroNaoEncontradoException;
    void deletarUsuario(String login) throws RegistroNaoEncontradoException;
}
