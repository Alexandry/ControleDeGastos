package com.example.categorizarLancamentos.api.Service;


import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.categorizarLancamentos.api.model.Categoria;
import com.example.categorizarLancamentos.api.model.Lancamento;
import com.example.categorizarLancamentos.api.repository.CategoriaRepository;
import com.example.categorizarLancamentos.api.repository.LancamentoRepository;
import com.example.categorizarLancamentos.api.repository.lancamento.LancamentoRepositoryImpl;

@Service
public class LancamentoService {
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Lancamento atualizar(Long codigo, Lancamento lancamento) {
		
	Lancamento lancamentoSalvo = buscarLancamentoPeloCodigo(codigo);
	BeanUtils.copyProperties(lancamento, lancamentoSalvo,"codigo");
	
	return lancamentoRepository.save(lancamentoSalvo);
	
	}
	
	
	public Lancamento salvarLancamento(Lancamento lancamento) {
		LocalDate data = LocalDate.now();
		if (lancamento.getData() == null) {
			lancamento.setData(data);
		}
		
		
		return lancamentoRepository.save(lancamento);
	}
	
	public Lancamento findById(Long codigo) {
		
		return lancamentoRepository.findOne(codigo);
	}
	
	@Async("fileExecutor")
	public void categoriaIsNotNull(Lancamento lancamentoSalvo) {
		
		
		if (lancamentoSalvo.getCategoria() == null) {
			validarCategoriazacaoAutomatica(lancamentoSalvo.getCodigo());
			
		}
		
	}
	

	private Lancamento buscarLancamentoPeloCodigo(Long codigo) {
		
		Lancamento lancamento = lancamentoRepository.findOne(codigo);
		
		if (lancamento == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		
		return lancamento;
	}

	
	private Lancamento validarCategoriazacaoAutomatica(Long codigo) {
		
			Lancamento lancamentoSalvo = buscarLancamentoPeloCodigo(codigo);
		
			Lancamento lancamento = atualizarCategoria(lancamentoSalvo.getCodigo(), lancamentoSalvo);		
			
			return lancamento;
			
		
		
	}

	private Lancamento atualizarCategoria(Long codigo, Lancamento lancamentoSalvo) {
		
		Lancamento lancamentoDesc;
		List<Lancamento> lancamento = lancamentoRepository.filtrarUltimaDescricao(lancamentoSalvo);
		
		if (!lancamento.isEmpty()) {
			lancamentoDesc = lancamento.get(0);
			
			lancamentoSalvo.setCategoria(lancamentoDesc.getCategoria());
			lancamentoRepository.save(lancamentoSalvo);
			return lancamentoSalvo;
		}
		
		
		//Atribuindo outros como categoria padrao caso nao haja uma descrição salva anteriormente.
		Categoria categoria = categoriaRepository.findOne(5L);
		lancamentoSalvo.setCategoria(categoria);
		lancamentoRepository.save(lancamentoSalvo);
		return lancamentoSalvo;
		
	}

	


	
	
	
	
	

}
