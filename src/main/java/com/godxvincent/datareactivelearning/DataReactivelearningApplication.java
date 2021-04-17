package com.godxvincent.datareactivelearning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//Para que las funcionalidad de controla transaccional funcionen correctamente se debe habilitar en el core de la app
@EnableTransactionManagement
public class DataReactivelearningApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataReactivelearningApplication.class, args);
	}

}
