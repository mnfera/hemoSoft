package com.integrador.hemoSoft;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

@SpringBootApplication
public class HemoSoftApplication {

	public static void main(String[] args) {
		SpringApplication.run(HemoSoftApplication.class, args);
	}
	
	@Bean
	public org.springframework.web.servlet.LocaleResolver localeResolver() {
		return new FixedLocaleResolver(new Locale("pt", "BR"));
	}
	
}
