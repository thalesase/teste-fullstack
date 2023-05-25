package com.unimed.avaliacao.repositorio;

import com.unimed.avaliacao.entidade.Beneficiario;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BeneficarioRepositorio {
    @Select("select b.id, b.nome as nome_beneficiario, cpf, email, idade, p.id as id_plano, p.nome as nome_plano, valor from beneficiario b inner join plano p on b.plano = p.id")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "nome", column = "nome_beneficiario"),
            @Result(property = "idade", column = "idade"),
            @Result(property = "email", column = "email"),
            @Result(property = "cpf", column = "cpf"),
            @Result(property = "plano.valor", column = "valor"),
            @Result(property = "plano.id", column = "id_plano"),
            @Result(property = "plano.nome", column = "nome_plano"),

    })
    public List<Beneficiario> listarBeneficiario();

    @Select("select b.id, b.nome as nome_beneficiario, cpf, email, idade, p.id as id_plano, p.nome as nome_plano, valor from beneficiario b inner join plano p on b.plano = p.id where b.id = #{id}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "nome", column = "nome_beneficiario"),
            @Result(property = "idade", column = "idade"),
            @Result(property = "email", column = "email"),
            @Result(property = "cpf", column = "cpf"),
            @Result(property = "plano.valor", column = "valor"),
            @Result(property = "plano.id", column = "id_plano"),
            @Result(property = "plano.nome", column = "nome_plano"),

    })
    public Beneficiario buscarBeneficiarioPorId(int id);

    @Delete("DELETE FROM beneficiario WHERE id = #{id}")
    public int deletarBeneficiario(int id);

    @Insert("INSERT INTO beneficiario(nome, email, cpf, idade, plano) " +
            " VALUES (#{nome}, #{email}, #{cpf},  #{idade}, #{plano.id})")
    public int criarBeneficiario(Beneficiario beneficiario);

    @Update("Update beneficiario set nome=#{nome}, " +
            " email=#{email}, cpf = #{cpf}, idade = #{idade}, plano = #{plano.id} where id=#{id}")
    public int atualizarBeneficiario(Beneficiario beneficiario);
}
