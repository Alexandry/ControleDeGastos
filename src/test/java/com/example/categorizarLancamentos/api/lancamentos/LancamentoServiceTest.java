package com.example.categorizarLancamentos.api.lancamentos;

import static org.mockito.Mockito.validateMockitoUsage;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.MockitoAnnotations;
import static org.junit.Assert.*;
import com.example.categorizarLancamentos.api.Service.LancamentoService;
import com.example.categorizarLancamentos.api.model.Lancamento;
import com.example.categorizarLancamentos.api.repository.LancamentoRepository;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class LancamentoServiceTest {
	
	@Mock
	@Autowired
	LancamentoService lancamentoService;
	
	@Mock
	@Autowired
	Lancamento lancamento;

	private LocalDate date;
	
	
	
	@Before
	public void setUp() {
		
	MockitoAnnotations.initMocks(this);
		
		
	}
	
	@Test
	public void testFindOneById() {
		
		lancamentoService.findById(1L);
		Mockito.verify(lancamentoService).findById(1L);
	}
	
	@Test	
	public void testSaveNewLancamento() {
			
		lancamentoService.salvarLancamento(lancamento);
		Mockito.when(lancamentoService.salvarLancamento(lancamento)).thenReturn(lancamento);
		Mockito.verify(lancamentoService).salvarLancamento(lancamento);
		
	}
	
	@Test
	public void testUpdateLancamento() {
		
		Lancamento lancamentoSalvo = lancamentoService.findById(1L);
		Mockito.when(lancamentoService.atualizar(1L, lancamento)).thenReturn(lancamento);
		assertNotEquals(lancamentoSalvo, lancamento);
		Mockito.when(lancamentoService.salvarLancamento(lancamento)).thenReturn(lancamento);
	}
	
	
}
