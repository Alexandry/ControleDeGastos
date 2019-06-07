package com.example.categorizarLancamentos.api.repository.categoria;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import com.example.categorizarLancamentos.api.model.Categoria;
import com.example.categorizarLancamentos.api.repository.filter.CategoriaFilter;


public class CategoriaRepositoryImpl implements CategoriaRepositoryQuery {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Categoria> filtrar(CategoriaFilter filter) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Categoria> criteria = builder.createQuery(Categoria.class);
		
		Root<Categoria> root = criteria.from(Categoria.class);
		
		//Restriçoes da query
		Predicate[] predicates = criarPredicates(filter,builder, root);
		criteria.where(predicates);
		
		TypedQuery<Categoria> query = em.createQuery(criteria);
		
		
		return query.getResultList();
	}

	private Predicate[] criarPredicates(CategoriaFilter filter, CriteriaBuilder builder, Root<Categoria> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if (!StringUtils.isEmpty(filter.getNome())) {
			predicates.add(builder.like
					(root.get("nome"), "%" + filter.getNome() + "%"));		
			
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	
}
