package com.example.audit;

import com.example.audit.pipe.AuditorAwareImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
/** Decorador requerido para habilitar las herramientas de auditoria de JPA en toda la app. */
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class AuditApplication {

	public static void main(String[] args) throws Throwable {
		SpringApplication.run(AuditApplication.class, args);
	}

	/**
	 * Declarar que la clase de auditoria es una clase de configuracion, e inicializarla para el proyecto.
	 */
	@Bean
    public AuditorAware<String> auditorProvider() {
		return new AuditorAwareImpl();
	}
}
