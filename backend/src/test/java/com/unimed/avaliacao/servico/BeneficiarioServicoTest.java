package com.unimed.avaliacao.servico;

import com.unimed.avaliacao.entidade.Beneficiario;
import com.unimed.avaliacao.excecao.RegistroNaoEncontradoException;
import com.unimed.avaliacao.repositorio.BeneficarioRepositorio;
import com.unimed.avaliacao.servico.impl.BeneficiarioServicoImpl;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.management.BadAttributeValueExpException;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class BeneficiarioServicoTest {
    @InjectMocks
    BeneficiarioServicoImpl beneficiarioServico;

    @Mock
    BeneficarioRepositorio beneficarioRepositorio;

    @Test
    void deveriaRetornarExcecaoAoBuscarRegistroNaoExistente() {
        when(beneficarioRepositorio.buscarBeneficiarioPorId(1)).thenReturn(null);

        assertThatThrownBy(() -> beneficiarioServico.buscarBeneficiarioPorId(1))
                .isInstanceOf(RegistroNaoEncontradoException.class);
    }

    @Test
    void deveriaRetornarRegistro() throws RegistroNaoEncontradoException {
        when(beneficarioRepositorio.buscarBeneficiarioPorId(1)).thenReturn(new Beneficiario(1, "teste", "cpf", "email",12, null));

        assertThat(beneficiarioServico.buscarBeneficiarioPorId(1).getId()).isEqualTo(1);

    }

    @Test
    void deveriaRetornarExcecaoAoAtualizarRegistroNaoExistente() {
        when(beneficarioRepositorio.atualizarBeneficiario(any(Beneficiario.class))).thenReturn(0);

        assertThatThrownBy(() -> beneficiarioServico.atualizarBeneficiario(1, new Beneficiario()))
                .isInstanceOf(RegistroNaoEncontradoException.class);
    }

    @Test
    void deveriaRetornarExcecaoAoDeletarRegistroNaoExistente() {
        when(beneficarioRepositorio.deletarBeneficiario(1)).thenReturn(0);

        assertThatThrownBy(() -> beneficiarioServico.deletarBeneficiario(1))
                .isInstanceOf(RegistroNaoEncontradoException.class);
    }

    @Test
    void deveriaRetornarExcecaoQuandoTiverErroAoInserir() {
        when(beneficarioRepositorio.criarBeneficiario(any(Beneficiario.class))).thenReturn(0);

        assertThatThrownBy(() -> beneficiarioServico.criarBeneficiario(new Beneficiario()))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void deveriaRetornarMaisDeUmRegistro() {
        when(beneficarioRepositorio.listarBeneficiario()).thenReturn(List.of(new Beneficiario(), new Beneficiario()));

        assertThat(beneficiarioServico.listarBeneficiario().size()).isEqualTo(2);
    }

    @Test
    void deveriaRetornarExcecaoQuandoIdadeMenorQueZero() {
        Beneficiario b = new Beneficiario(1, "teste", "cpf", "email",-1, null);
        assertThatThrownBy(() -> beneficiarioServico.criarBeneficiario(b))
                .isInstanceOf(BadAttributeValueExpException.class);
    }

}
