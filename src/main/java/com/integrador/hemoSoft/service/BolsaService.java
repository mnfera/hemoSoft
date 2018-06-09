package com.integrador.hemoSoft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.integrador.hemoSoft.model.Bolsa;
import com.integrador.hemoSoft.repository.BolsaRepository;

@Service
public class BolsaService {
	
	@Autowired
	private BolsaRepository repository; //Injeta o repositório
	
	//Retorna uma lista com todas as bolsas inseridas
	public List<Bolsa> findAll() {
		return repository.findAll(); 
	}
	
	//Retorna uma bolsa a partir do ID
	public Bolsa findOne(Long id) {
		return repository.findById(id).get();
	}
	
	//Salva ou atualiza uma bolsa
	public Bolsa save(Bolsa bolsa) {
		return repository.saveAndFlush(bolsa);		
	}
	
	//Exclui uma bolsa
	public void delete(Long id) {
		repository.deleteById(id);
	}

}
