package com.example.categorizarLancamentos.api.repository.categoria;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.categorizarLancamentos.api.model.Categoria;
import com.example.categorizarLancamentos.api.model.Lancamento;
import com.example.categorizarLancamentos.api.repository.filter.CategoriaFilter;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class CategoriaRepositoryTest {
	
	@Mock
	@Autowired
	CategoriaRepositoryImpl categoriaImpl;
	
	
	@Mock
	private EntityManager em;
	
	@Mock
	private CriteriaBuilder builder;
	
	@Mock
	private CriteriaQuery criteria;
	
	@Mock	
	private Root root;
	
	@Mock
	private TypedQuery typedQyery;
	
	@Mock
	@Autowired
	private CategoriaFilter categoriaFilter;
	
	@Autowired
	private List<Categoria> categoriaList;
	
	@Before
	public void setUp() {
		
		MockitoAnnotations.initMocks(this);
		
	
	}
	
	@Test
	public void testeFilterCategorias() {
		
		Mockito.when(em.getCriteriaBuilder()).thenReturn(builder);
		Mockito.when(builder.createQuery(any())).thenReturn(criteria);
		Mockito.when(criteria.from(Lancamento.class)).thenReturn(root);
		Mockito.when(criteria.select(root)).thenReturn(criteria);
		Mockito.when(em.createQuery(criteria)).thenReturn(typedQyery);
		Mockito.when(typedQyery.getResultList()).thenReturn(categoriaList);
		
		when(categoriaImpl.filtrar(categoriaFilter)).thenReturn(categoriaList);
		assertNotNull(typedQyery);
	}

}
