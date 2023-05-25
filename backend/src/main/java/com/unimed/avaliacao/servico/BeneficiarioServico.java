package com.unimed.avaliacao.servico;

import com.unimed.avaliacao.entidade.Beneficiario;
import com.unimed.avaliacao.excecao.RegistroNaoEncontradoException;

import java.util.List;

public interface BeneficiarioServico {
    List<Beneficiario> listarBeneficiario();
    Beneficiario buscarBeneficiarioPorId(int id) throws RegistroNaoEncontradoException;
    void criarBeneficiario(Beneficiario beneficiario);
    void atualizarBeneficiario(int id, Beneficiario beneficiario) throws RegistroNaoEncontradoException;
    void deletarBeneficiario(int id) throws RegistroNaoEncontradoException;
}
