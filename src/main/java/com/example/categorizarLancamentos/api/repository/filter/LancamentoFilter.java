package com.example.categorizarLancamentos.api.repository.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class LancamentoFilter {
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate data;

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

}
