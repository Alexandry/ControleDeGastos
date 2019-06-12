package com.example.categorizarLancamentos.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.categorizarLancamentos.api.Service.LancamentoService;
import com.example.categorizarLancamentos.api.events.RecursoCriadoEvent;
import com.example.categorizarLancamentos.api.model.Lancamento;
import com.example.categorizarLancamentos.api.repository.LancamentoRepository;
import com.example.categorizarLancamentos.api.repository.filter.LancamentoFilter;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	LancamentoService lancamento;
	
	@GetMapping
	public List<Lancamento> listarLancamentos(LancamentoFilter filter){
		
		return lancamentoRepository.filtrar(filter);
		
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Lancamento> buscarLancamentoPorCodigo(@PathVariable Long codigo){
		
		Lancamento lancamento = this.lancamento.findById(codigo);
		
		return lancamento != null ? ResponseEntity.ok(lancamento) : ResponseEntity.notFound().build();				
		
		
	}
	
	@PostMapping
	public ResponseEntity<Lancamento> salvar(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response){
		
		//Lancamento lancamentoSalvo = lancamentoRepository.save(lancamento);
		Lancamento lancamentoSalvo = this.lancamento.salvarLancamento(lancamento);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamento.getCodigo()));
		
		this.lancamento.categoriaIsNotNull(lancamentoSalvo);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalvo);
		
	}
	@PutMapping("/{codigo}")
	public ResponseEntity<Lancamento> atualizar(@PathVariable Long codigo,@Valid @RequestBody Lancamento lancamento){
		Lancamento lancamentoAlterado = this.lancamento.atualizar(codigo, lancamento);
		
		return ResponseEntity.ok(lancamentoAlterado);
		
	}
	
}
