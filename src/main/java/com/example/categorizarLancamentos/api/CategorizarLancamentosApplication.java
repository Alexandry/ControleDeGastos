package com.example.categorizarLancamentos.api;

import java.util.concurrent.Executor;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
@EnableAsync
public class CategorizarLancamentosApplication {

	public static void main(String[] args) {
		SpringApplication.run(CategorizarLancamentosApplication.class, args);
	}
	
	@Bean(name="fileExecutor")
	public Executor asyncExecutor() {
		ThreadPoolTaskExecutor ex = new ThreadPoolTaskExecutor();
		ex.setCorePoolSize(2);
		ex.setMaxPoolSize(5);
		ex.setQueueCapacity(100000);
		ex.initialize();
		return ex;
	}

}
