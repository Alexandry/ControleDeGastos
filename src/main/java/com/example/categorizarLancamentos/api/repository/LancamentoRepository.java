package com.example.categorizarLancamentos.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.categorizarLancamentos.api.model.Lancamento;
import com.example.categorizarLancamentos.api.repository.lancamento.LancamentoRepositoryQuery;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery {

}
