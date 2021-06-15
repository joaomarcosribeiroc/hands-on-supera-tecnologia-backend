package org.acme;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
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

        System.out.println(params.getOrderBy());

        return entityManager
        .createNativeQuery("SELECT * FROM produto ORDER BY :orderBy ASC", Produto.class)
        .setFirstResult(params.getOffset())
        .setMaxResults(params.getLimit())
        .setParameter("orderBy", params.getOrderBy())
        .getResultList();
    }   

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Produto getProdutos(@PathParam("id") Integer id) {
        return entityManager.find(Produto.class, id);
    }
}