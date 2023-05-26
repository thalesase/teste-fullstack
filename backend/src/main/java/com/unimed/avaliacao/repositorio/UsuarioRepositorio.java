package com.unimed.avaliacao.repositorio;

import com.unimed.avaliacao.entidade.Usuario;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UsuarioRepositorio {
    @Select("select * from usuario")
    public List<Usuario> listarUsuarios();

    @Select("SELECT * FROM usuario WHERE login = #{login}")
    public Usuario buscarUsuarioPorLogin(String id);

    @Delete("DELETE FROM usuario WHERE login = #{login}")
    public int deletarUsuario(String id);

    @Insert("INSERT INTO usuario(login, senha, nome) " +
            " VALUES (#{login}, #{senha}, #{nome})")
    public int criarUsuario(Usuario usuario);

    @Update("Update usuario set nome=#{nome}, " +
            " senha=#{senha} where login=#{login}")
    public int atualizarUsuario(Usuario usuario);
}
