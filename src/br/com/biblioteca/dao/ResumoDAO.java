/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.biblioteca.dao;

import br.com.biblioteca.dao.exceptions.NonexistentEntityException;
import br.com.biblioteca.model.Resumo;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Junior
 */
public class ResumoDAO implements Serializable {

    private EntityManagerFactory emf = null;

    public ResumoDAO() {
        this.emf = Persistence.createEntityManagerFactory("BibliotecaPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Resumo resumo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(resumo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Resumo resumo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            resumo = em.merge(resumo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = resumo.getId();
                if (findResumo(id) == null) {
                    throw new NonexistentEntityException("The resumo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Resumo resumo;
            try {
                resumo = em.getReference(Resumo.class, id);
                resumo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The resumo with id " + id + " no longer exists.", enfe);
            }
            em.remove(resumo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Resumo> findResumoEntities() {
        return findResumoEntities(true, -1, -1);
    }

    public List<Resumo> findResumoEntities(int maxResults, int firstResult) {
        return findResumoEntities(false, maxResults, firstResult);
    }

    private List<Resumo> findResumoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Resumo.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Resumo findResumo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Resumo.class, id);
        } finally {
            em.close();
        }
    }

    public int getResumoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Resumo> rt = cq.from(Resumo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
