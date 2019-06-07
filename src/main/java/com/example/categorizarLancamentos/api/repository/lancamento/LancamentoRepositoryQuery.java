package com.example.categorizarLancamentos.api.repository.lancamento;

import java.util.List;

import com.example.categorizarLancamentos.api.model.Lancamento;
import com.example.categorizarLancamentos.api.repository.filter.LancamentoFilter;

public interface LancamentoRepositoryQuery {
	
	
	public List<Lancamento>filtrar(LancamentoFilter filter);
	
	public List<Lancamento>filtrarUltimaDescricao(Lancamento filter);

}
