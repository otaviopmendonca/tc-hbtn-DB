package com.techcamps.cadastros.models;

import com.techcamps.cadastros.entities.Produto;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class ProdutoModel {

    public void create(Produto p) {
        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            emf = Persistence.createEntityManagerFactory("admin-jpa");
            em = emf.createEntityManager();

            System.out.println("Iniciando a transação para create (Produto)");
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            System.out.println("Produto criado com sucesso !!!");
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Erro ao criar o produto !!!" + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
            if (emf != null) {
                emf.close();
            }
            System.out.println("Finalizando a transação de create (Produto)");
        }
    }

    public void update(Produto p) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        try {
            emf = Persistence.createEntityManagerFactory("admin-jpa");
            em = emf.createEntityManager();

            System.out.println("Iniciando a transação para update (Produto)");
            em.getTransaction().begin();
            em.merge(p);
            em.getTransaction().commit();
            System.out.println("Produto atualizado com sucesso !!!");
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Erro ao atualizar o produto !!!" + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
            if (emf != null) {
                emf.close();
            }
            System.out.println("Finalizando a transação de update (Produto)");
        }
    }

    public void delete(Produto p) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        try {
            emf = Persistence.createEntityManagerFactory("admin-jpa");
            em = emf.createEntityManager();

            System.out.println("Iniciando a transação para delete (Produto)");
            em.getTransaction().begin();
            Produto produtoToDelete = em.find(Produto.class, p.getId());
            if (produtoToDelete != null) {
                em.remove(produtoToDelete);
            } else {
                System.out.println("Produto com ID " + p.getId() + " não encontrado para deleção.");
            }
            em.getTransaction().commit();
            System.out.println("Produto deletado com sucesso !!!");
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Erro ao deletar o produto !!!" + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
            if (emf != null) {
                emf.close();
            }
            System.out.println("Finalizando a transação de delete (Produto)");
        }
    }

    public Produto findById(Produto p) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        Produto produto = null;
        try {
            emf = Persistence.createEntityManagerFactory("admin-jpa");
            em = emf.createEntityManager();

            produto = em.find(Produto.class, p.getId());
            if (produto != null) {
                System.out.println("Produto encontrado por ID: " + produto.getId());
            } else {
                System.out.println("Produto com ID " + p.getId() + " não encontrado.");
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar produto por ID !!!" + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
            if (emf != null) {
                emf.close();
            }
        }
        return produto;
    }

    public List<Produto> findAll() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        List<Produto> produtos = new ArrayList<>();

        try {
            emf = Persistence.createEntityManagerFactory("admin-jpa");
            em = emf.createEntityManager();

            Query query = em.createQuery("SELECT p FROM Produto p", Produto.class);
            produtos = query.getResultList();
            System.out.println("Todos os produtos encontrados (Qtde: " + produtos.size() + ")");
        } catch (Exception e) {
            System.err.println("Erro ao buscar todos os produtos !!!" + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
            if (emf != null) {
                emf.close();
            }
        }
        return produtos;
    }
}