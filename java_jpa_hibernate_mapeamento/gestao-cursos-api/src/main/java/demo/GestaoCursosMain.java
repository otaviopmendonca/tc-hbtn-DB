package demo;

import entities.*;
import models.AlunoModel;
import models.CursoModel;
import java.util.Date;

public class GestaoCursosMain {

    public static void main(String[] args) {
        AlunoModel alunoModel = new AlunoModel();
        CursoModel cursoModel = new CursoModel();

        // Criando e populando um Aluno
        Aluno aluno = new Aluno();
        aluno.setNomeCompleto("João Silva");
        aluno.setMatricula("20230001");
        aluno.setNascimento(new Date());
        aluno.setEmail("joao.silva@example.com");

        Endereco endereco = new Endereco();
        endereco.setLogradouro("Rua A");
        endereco.setEndereco("123");
        endereco.setBairro("Centro");
        endereco.setCidade("São Paulo");
        endereco.setEstado("SP");
        endereco.setCep(12345678);

        aluno.getEnderecos().add(endereco);

        Telefone telefone = new Telefone();
        telefone.setDDD("11");
        telefone.setNumero("987654321");

        aluno.getTelefones().add(telefone);

        alunoModel.create(aluno);

        // Criando e populando um Curso
        Professor professor = new Professor();
        professor.setNomeCompleto("Maria Oliveira");
        professor.setEmail("maria.oliveira@example.com");
        professor.setMatricula("P2023001");

        MaterialCurso material = new MaterialCurso();
        material.setUrl("https://example.com/material");

        Curso curso = new Curso();
        curso.setNome("Java Básico");
        curso.setSigla("JB");
        curso.setProfessor(professor);
        curso.setMaterialCurso(material);
        curso.getAlunos().add(aluno);

        cursoModel.create(curso);

        System.out.println("Dados populados com sucesso!");
    }
}