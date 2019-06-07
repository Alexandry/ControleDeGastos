package com.example.categorizarLancamentos.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.categorizarLancamentos.api.model.Categoria;
import com.example.categorizarLancamentos.api.repository.categoria.CategoriaRepositoryQuery;;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>, CategoriaRepositoryQuery {

}
