package com.unimed.avaliacao.repositorio;

import com.unimed.avaliacao.entidade.Plano;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PlanoRepositorio {
    @Select("select * from plano")
    public List<Plano> listarPlanos();

    @Select("SELECT * FROM plano WHERE id = #{id}")
    public Plano buscarPlanoPorId(long id);

    @Delete("DELETE FROM plano WHERE id = #{id}")
    public int deletarPlano(long id);

    @Insert("INSERT INTO plano(nome, valor) " +
            " VALUES (#{nome}, #{valor})")
    public int criarPlano(Plano Plano);

    @Update("Update plano set nome=#{nome}, " +
            " valor=#{valor} where id=#{id}")
    public int atualizarPlano(Plano Plano);
}
