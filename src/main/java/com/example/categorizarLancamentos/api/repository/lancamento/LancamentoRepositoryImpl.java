package com.example.categorizarLancamentos.api.repository.lancamento;

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
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;

import com.example.categorizarLancamentos.api.model.Lancamento;
import com.example.categorizarLancamentos.api.repository.filter.LancamentoFilter;

public class LancamentoRepositoryImpl implements LancamentoRepositoryQuery{
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Lancamento> filtrar(LancamentoFilter filter) {
		
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Lancamento> criteria = builder.createQuery(Lancamento.class);
		
		Root<Lancamento> root = criteria.from(Lancamento.class);
		
		//Restriçoes da query
		Predicate[] predicates = criarPredicates(filter,builder, root);
		criteria.where(predicates);
		
		criteria.orderBy(builder.desc(root.get("data")));
		
		TypedQuery<Lancamento> query = em.createQuery(criteria);
		
		
		
		return query.getResultList();
	}

	private Predicate[] criarPredicates(LancamentoFilter filter, CriteriaBuilder builder, Root<Lancamento> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if (filter.getData() != null) {
			predicates.add(builder.equal(root.get("data"), filter.getData()));		
			
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	@Override
	public List<Lancamento> filtrarUltimaDescricao(Lancamento filter) {
			
		
			
			
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Lancamento> criteria = builder.createQuery(Lancamento.class);
		
			Root<Lancamento> root = criteria.from(Lancamento.class);
		
			Predicate[] predicates = criarPredicatesLancamento(filter,builder, root);
			
			criteria.where(predicates);
			
			TypedQuery<Lancamento> query = em.createQuery(criteria);
			
			
			
			return query.getResultList();
	}

	private Predicate[] criarPredicatesLancamento(Lancamento filter, CriteriaBuilder builder, Root<Lancamento> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if (!StringUtils.isEmpty(filter.getDescricao())) {
			predicates.add(builder.like
					(root.get("descricao"), "%" + filter.getDescricao() + "%"));
			predicates.add(builder.isNotNull(root.get("categoria")));
		
	}
		return predicates.toArray(new Predicate[predicates.size()]);

	}
}
