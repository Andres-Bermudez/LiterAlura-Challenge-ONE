package com.challengeone.literalura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import com.challengeone.literalura.presentacion.MenuPrincipal;

@SpringBootApplication  // Anotación de arranque de la aplicación Spring Boot
public class LiterAluraApplication {

	public static void main(String[] args) {
		// Arrancar la aplicación Spring Boot
		ApplicationContext context = SpringApplication.run(LiterAluraApplication.class, args);

		// Obtener el bean de MenuPrincipal desde el contexto de Spring
		MenuPrincipal menuPrincipal = context.getBean(MenuPrincipal.class);

		// Llamar al método mostrarMenu para iniciar la aplicación.
		menuPrincipal.mostrarMenu();
	}
}
