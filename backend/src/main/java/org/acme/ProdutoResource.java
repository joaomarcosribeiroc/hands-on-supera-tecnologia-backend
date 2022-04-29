package org.acme;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.acme.model.Produto;
import org.acme.resteasyjackson.Params;

@Path("/produtos")
public class ProdutoResource{

    @Inject EntityManager entityManager;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Produto> getProdutos(@BeanParam Params params) {
        
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Produto> criteriaQuery = criteriaBuilder.createQuery(Produto.class);
        Root<Produto> from = criteriaQuery.from(Produto.class);
        CriteriaQuery<Produto> select = criteriaQuery.select(from);
        criteriaQuery.orderBy(criteriaBuilder.asc(from.get(params.getOrderBy())));
        
        return 
            entityManager.createQuery(criteriaQuery)
            .setFirstResult(params.getOffset())
            .setMaxResults(params.getLimit())
            .getResultList();
    }   

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Produto getProdutos(@PathParam("id") Integer id) {
        return entityManager.find(Produto.class, id);
    }
}