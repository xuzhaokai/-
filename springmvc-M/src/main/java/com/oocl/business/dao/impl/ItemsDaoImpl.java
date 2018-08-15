package com.oocl.business.dao.impl;

import com.oocl.business.dao.ItemsDao;
import com.oocl.business.model.Items;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Repository("itemsDao")
public class ItemsDaoImpl implements ItemsDao {

    @PersistenceContext(name = "em")
    private EntityManager em;

    public Items add(Items items) {
        em.persist(items);
        return items;
    }
}
