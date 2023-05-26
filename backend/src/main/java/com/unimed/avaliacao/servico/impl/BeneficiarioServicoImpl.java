package com.unimed.avaliacao.servico.impl;

import com.unimed.avaliacao.entidade.Beneficiario;
import com.unimed.avaliacao.excecao.RegistroNaoEncontradoException;
import com.unimed.avaliacao.repositorio.BeneficarioRepositorio;
import com.unimed.avaliacao.servico.BeneficiarioServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.BadAttributeValueExpException;
import java.util.List;

@Service
public class BeneficiarioServicoImpl implements BeneficiarioServico {

    @Autowired
    private BeneficarioRepositorio beneficarioRepositorio;

    @Override
    public List<Beneficiario> listarBeneficiario(){
        return beneficarioRepositorio.listarBeneficiario();
    }
    @Override
    public Beneficiario buscarBeneficiarioPorId(int id) throws RegistroNaoEncontradoException {
        Beneficiario beneficiario = beneficarioRepositorio.buscarBeneficiarioPorId(id);
        if(beneficiario == null) throw new RegistroNaoEncontradoException();
        return beneficiario;
    }
    @Override
    public void criarBeneficiario(Beneficiario beneficiario) throws BadAttributeValueExpException {
        if(beneficiario.getIdade()<0) throw new BadAttributeValueExpException("Idade não pode ser menor que 0");
        if(beneficarioRepositorio.criarBeneficiario(beneficiario) == 0) throw new RuntimeException("Erro ao Inserir Registro");
    }
    @Override
    public void atualizarBeneficiario(int id, Beneficiario beneficiario) throws RegistroNaoEncontradoException, BadAttributeValueExpException {
        if(beneficiario.getIdade()<0) throw new BadAttributeValueExpException("Idade não pode ser menor que 0");
        beneficiario.setId(id);
        if(beneficarioRepositorio.atualizarBeneficiario(beneficiario) == 0) throw new RegistroNaoEncontradoException();
    }

    @Override
    public void deletarBeneficiario(int id) throws RegistroNaoEncontradoException {
        if(beneficarioRepositorio.deletarBeneficiario(id) == 0) throw new RegistroNaoEncontradoException();
    }
}
