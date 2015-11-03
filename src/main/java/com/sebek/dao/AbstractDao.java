package com.sebek.dao;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

import com.sebek.model.AbstractModel;

@Transactional
public abstract class AbstractDao<M, T>
{
    @PersistenceContext
    protected EntityManager entityManager;

    public M create(final M m)
    {
        entityManager.persist(m);
        return m;
    }
    
    public M update(final M m)
    {
        entityManager.merge(m);
        return m;
    }
    
    public void delete(final M m)
    {
        entityManager.remove(entityManager.find(m.getClass(), ((AbstractModel)m).getId()));
    }
}
