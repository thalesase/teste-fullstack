package com.unimed.avaliacao.servico;

import com.unimed.avaliacao.entidade.Usuario;
import com.unimed.avaliacao.excecao.RegistroNaoEncontradoException;
import com.unimed.avaliacao.repositorio.UsuarioRepositorio;
import com.unimed.avaliacao.servico.impl.UsuarioServicoImpl;
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
public class UsuarioServicoTest {
    @InjectMocks
    UsuarioServicoImpl usuarioServico;

    @Mock
    UsuarioRepositorio usuarioRepositorio;

    @Test
    void deveriaRetornarExcecaoAoBuscarUsuarioInexistente() {
        when(usuarioRepositorio.buscarUsuarioPorLogin("admin")).thenReturn(null);

        assertThatThrownBy(() -> usuarioServico.buscarUsuarioPorLogin("admin"))
                .isInstanceOf(RegistroNaoEncontradoException.class);
    }

    @Test
    void deveriaRetornarUsuario() throws RegistroNaoEncontradoException {
        when(usuarioRepositorio.buscarUsuarioPorLogin("admin")).thenReturn(new Usuario("admin", "teste", "100"));

        assertThat(usuarioServico.buscarUsuarioPorLogin("admin").getLogin()).isEqualTo("admin");

    }

    @Test
    void deveriaRetornarExcecaoAoAtualizarRegistroNaoExistente() {
        when(usuarioRepositorio.atualizarUsuario(any(Usuario.class))).thenReturn(0);

        assertThatThrownBy(() -> usuarioServico.atualizarUsuario("admin", new Usuario()))
                .isInstanceOf(RegistroNaoEncontradoException.class);
    }

    @Test
    void deveriaRetornarExcecaoAoTentarExcluirAdmin() {
        assertThatThrownBy(() -> usuarioServico.deletarUsuario("admin"))
                .isInstanceOf(BadAttributeValueExpException.class);
    }

    @Test
    void deveriaRetornarExcecaoQuandoTiverErroAoInserir() {
        when(usuarioRepositorio.criarUsuario(any(Usuario.class))).thenReturn(0);

        assertThatThrownBy(() -> usuarioServico.criarUsuario(new Usuario()))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void deveriaRetornarMaisDeUmRegistro() {
        when(usuarioRepositorio.listarUsuarios()).thenReturn(List.of(new Usuario(), new Usuario()));

        assertThat(usuarioServico.listarUsuarios().size()).isEqualTo(2);
    }

}
