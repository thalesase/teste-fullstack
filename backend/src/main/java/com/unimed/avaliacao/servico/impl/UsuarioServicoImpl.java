package com.unimed.avaliacao.servico.impl;

import com.unimed.avaliacao.entidade.Beneficiario;
import com.unimed.avaliacao.entidade.Usuario;
import com.unimed.avaliacao.excecao.RegistroNaoEncontradoException;
import com.unimed.avaliacao.repositorio.BeneficarioRepositorio;
import com.unimed.avaliacao.repositorio.UsuarioRepositorio;
import com.unimed.avaliacao.servico.UsuarioServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.BadAttributeValueExpException;
import java.util.List;

@Service
public class UsuarioServicoImpl implements UsuarioServico {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepositorio.listarUsuarios();
    }
    @Override
    public Usuario buscarUsuarioPorLogin(String login) throws RegistroNaoEncontradoException {
        Usuario usuario = usuarioRepositorio.buscarUsuarioPorLogin(login);
        if(usuario == null) throw new RegistroNaoEncontradoException();
        return usuario;
    }
    @Override
    public void criarUsuario(Usuario usuario) throws BadAttributeValueExpException {
        if(usuarioRepositorio.buscarUsuarioPorLogin(usuario.getLogin()) != null) throw new BadAttributeValueExpException("Usuario já existe");
        if(usuarioRepositorio.criarUsuario(usuario) == 0) throw new RuntimeException("Erro ao Inserir Registro");
    }
    @Override
    public void atualizarUsuario(String login, Usuario usuario) throws RegistroNaoEncontradoException {
        usuario.setLogin(login);
        if(usuarioRepositorio.atualizarUsuario(usuario) == 0) throw new RegistroNaoEncontradoException();
    }

    @Override
    public void deletarUsuario(String login) throws RegistroNaoEncontradoException {
        if(usuarioRepositorio.deletarUsuario(login) == 0) throw new RegistroNaoEncontradoException();
    }

}
