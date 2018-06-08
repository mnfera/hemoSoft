package com.integrador.hemoSoft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.integrador.hemoSoft.model.Bolsa;
import com.integrador.hemoSoft.repository.Bolsas;

@Service
public class BolsaService {
	
	@Autowired
	private Bolsas repository; //Injeta o repositório
	
	//Retorna uma lista com todas as bolsas inseridas
	public List<Bolsa> findAll() {
		return repository.findAll(); 
	}

}
