package models;

import entities.Curso;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class CursoModel {

    public void create(Curso curso) {
        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            System.out.println("Iniciando a criação do curso");
            emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
            em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(curso);
            em.getTransaction().commit();
            System.out.println("Curso criado com sucesso!");
        } catch (Exception e) {
            if (em != null && em.isOpen()) {
                em.close();
            }
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Erro ao criar o curso: " + e.getMessage());
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
            System.out.println("Finalizando a criação do curso");
        }
    }

    public Curso findById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Curso.class, id);
        } finally {
            em.close();
        }
    }

    public List<Curso> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT c FROM Curso c", Curso.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void update(Curso curso) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(curso);
            em.getTransaction().commit();
            System.out.println("Curso atualizado com sucesso!");
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Erro ao atualizar o curso: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void delete(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Curso curso = em.find(Curso.class, id);
            if (curso != null) {
                em.remove(curso);
                System.out.println("Curso removido com sucesso!");
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Erro ao remover o curso: " + e.getMessage());
        } finally {
            em.close();
        }
    }
}
