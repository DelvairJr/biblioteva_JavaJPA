package br.com.biblioteca.dao;

import br.com.biblioteca.model.Editora;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EditoraDAO {

    public EntityManager getEM() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("BibliotecaPU");
        return factory.createEntityManager();
    }

    public Editora save(Editora editora) {
        EntityManager em = getEM();
        try {
            em.getTransaction().begin();
            em.persist(editora);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return editora;
    }

}
