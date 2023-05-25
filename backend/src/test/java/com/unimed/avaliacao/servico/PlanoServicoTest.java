package com.unimed.avaliacao.servico;

import com.unimed.avaliacao.entidade.Plano;
import com.unimed.avaliacao.excecao.RegistroNaoEncontradoException;
import com.unimed.avaliacao.repositorio.PlanoRepositorio;
import com.unimed.avaliacao.servico.impl.PlanoServicoImpl;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class PlanoServicoTest {
    @InjectMocks
    PlanoServicoImpl planoServico;

    @Mock
    PlanoRepositorio planoRepositorio;

    @Test
    void deveriaRetornarExcecaoAoBuscarRegistroNaoExistente() {
        when(planoRepositorio.buscarPlanoPorId(1)).thenReturn(null);

        assertThatThrownBy(() -> planoServico.buscarPlanoPorId(1))
                .isInstanceOf(RegistroNaoEncontradoException.class);
    }

    @Test
    void deveriaRetornarPlano() throws RegistroNaoEncontradoException {
        when(planoRepositorio.buscarPlanoPorId(1)).thenReturn(new Plano(1, "teste", 100));

        assertThat(planoServico.buscarPlanoPorId(1).getId()).isEqualTo(1);

    }

    @Test
    void deveriaRetornarExcecaoAoAtualizarRegistroNaoExistente() {
        when(planoRepositorio.atualizarPlano(any(Plano.class))).thenReturn(0);

        assertThatThrownBy(() -> planoServico.atualizarPlano(1, new Plano()))
                .isInstanceOf(RegistroNaoEncontradoException.class);
    }

    @Test
    void deveriaRetornarExcecaoAoDeletarRegistroNaoExistente() {
        when(planoRepositorio.deletarPlano(1)).thenReturn(0);

        assertThatThrownBy(() -> planoServico.deletarPlano(1))
                .isInstanceOf(RegistroNaoEncontradoException.class);
    }

    @Test
    void deveriaRetornarExcecaoQuandoTiverErroAoInserir() {
        when(planoRepositorio.criarPlano(any(Plano.class))).thenReturn(0);

        assertThatThrownBy(() -> planoServico.criarPlano(new Plano()))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void deveriaRetornarMaisDeUmRegistro() {
        when(planoRepositorio.listarPlanos()).thenReturn(List.of(new Plano(), new Plano()));

        assertThat(planoServico.listarPlanos().size()).isEqualTo(2);
    }

}
