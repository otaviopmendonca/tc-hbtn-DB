package models;

import entities.Aluno;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class AlunoModel {

    public void create(Aluno aluno) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            em.persist(aluno);
            em.getTransaction().commit();
            System.out.println("Aluno criado com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao criar um aluno !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
    }

    public Aluno findById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Aluno.class, id);
        } finally {
            em.close();
        }
    }

    public List<Aluno> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT a FROM Aluno a", Aluno.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void update(Aluno aluno) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(aluno);
            em.getTransaction().commit();
            System.out.println("Aluno atualizado com sucesso!");
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Erro ao atualizar o aluno: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void delete(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Aluno aluno = em.find(Aluno.class, id);
            if (aluno != null) {
                em.remove(aluno);
                System.out.println("Aluno removido com sucesso!");
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Erro ao remover o aluno: " + e.getMessage());
        } finally {
            em.close();
        }
    }
}
