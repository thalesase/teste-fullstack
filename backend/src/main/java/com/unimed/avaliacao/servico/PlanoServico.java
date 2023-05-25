package com.unimed.avaliacao.servico;

import com.unimed.avaliacao.entidade.Plano;
import com.unimed.avaliacao.excecao.RegistroNaoEncontradoException;

import java.util.List;

public interface PlanoServico {
    List<Plano> listarPlanos();
    Plano buscarPlanoPorId(int id) throws RegistroNaoEncontradoException;
    void criarPlano(Plano plano);
    void atualizarPlano(int id, Plano plano) throws RegistroNaoEncontradoException;
    void deletarPlano(int id) throws RegistroNaoEncontradoException;
}
