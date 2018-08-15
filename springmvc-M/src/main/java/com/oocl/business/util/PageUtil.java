package com.oocl.business.util;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * @Description
 * @Author Junier
 * @Date 8/2/2018 3:17 PM
 **/
public class PageUtil {

    /**
     *@Description 分页
     *@param  params 预编译需要传入的值
     *@param page 页码
     *@param size 每页条数
     *@return
     */
    public static List<?> getPageResult(EntityManager em, String jpql, Object[] params, int page, int size){
        Query query = em.createQuery(jpql);
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                query.setParameter(i + 1, params[i]);
            }
        }
        query.setFirstResult((page - 1) * size);
        query.setMaxResults(size);
        return query.getResultList();
    }
}
