package com.unimed.avaliacao.servico.impl;

import com.unimed.avaliacao.entidade.Plano;
import com.unimed.avaliacao.excecao.RegistroNaoEncontradoException;
import com.unimed.avaliacao.repositorio.PlanoRepositorio;
import com.unimed.avaliacao.servico.PlanoServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlanoServicoImpl implements PlanoServico {

    @Autowired
    private PlanoRepositorio planoRepositorio;
    @Override
    public List<Plano> listarPlanos(){
        return planoRepositorio.listarPlanos();
    }
    @Override
    public Plano buscarPlanoPorId(int id) throws RegistroNaoEncontradoException {
        Plano plano = planoRepositorio.buscarPlanoPorId(id);
        if(plano == null) throw new RegistroNaoEncontradoException();
        return plano;
    }
    @Override
    public void criarPlano(Plano plano) {
        if(planoRepositorio.criarPlano(plano) == 0) throw new RuntimeException("Erro ao Inserir Registro");
    }
    @Override
    public void atualizarPlano(int id, Plano plano) throws RegistroNaoEncontradoException {
        plano.setId(id);
        if(planoRepositorio.atualizarPlano(plano) == 0) throw new RegistroNaoEncontradoException();
    }

    @Override
    public void deletarPlano(int id) throws RegistroNaoEncontradoException {
        if(planoRepositorio.deletarPlano(id) == 0) throw new RegistroNaoEncontradoException();
    }

}
