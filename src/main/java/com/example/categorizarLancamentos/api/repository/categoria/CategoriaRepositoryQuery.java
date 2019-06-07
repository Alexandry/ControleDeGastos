package com.example.categorizarLancamentos.api.repository.categoria;

import java.util.List;

import com.example.categorizarLancamentos.api.model.Categoria;
import com.example.categorizarLancamentos.api.repository.filter.CategoriaFilter;

public interface CategoriaRepositoryQuery {

	
	public List<Categoria> filtrar(CategoriaFilter filter);
}
