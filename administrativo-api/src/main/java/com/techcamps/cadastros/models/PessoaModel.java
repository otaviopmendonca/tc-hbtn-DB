package com.techcamps.cadastros.models;

import entities.Pessoa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaModel {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");

    public void create(Pessoa p) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Pessoa findById(Long id) {
        EntityManager em = emf.createEntityManager();
        return em.find(Pessoa.class, id);
    }

    public List<Pessoa> findAll() {
        EntityManager em = emf.createEntityManager();
        return em.createQuery("from Pessoa", Pessoa.class).getResultList();
    }

    public void update(Pessoa p) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(p);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void delete(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Pessoa p = em.find(Pessoa.class, id);
            if (p != null) em.remove(p);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
