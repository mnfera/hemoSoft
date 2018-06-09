package com.integrador.hemoSoft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.integrador.hemoSoft.model.Doador;
import com.integrador.hemoSoft.repository.DoadorRepository;

@Service
public class DoadorService {
	
	@Autowired
	private DoadorRepository repository;
	
	//Retorna uma lista com todos os doadores inseridos
	public List<Doador> findAll() {
		return repository.findAll(); 
	}
	
	//Retorna um doador a partir do ID
	public Doador findOne(Long id) {
		return repository.findById(id).get();
	}
	
	//Salva ou atualiza um doador
	public Doador save(Doador doador) {
		return repository.saveAndFlush(doador);		
	}
	
	//Exclui um doador
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	//Busca doadores pelo nome
	public List<Doador> findByNome(String nome){
		return repository.findByNomeContaining(nome);
	}
	

}