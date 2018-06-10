package com.integrador.hemoSoft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.integrador.hemoSoft.model.Enfermeiro;
import com.integrador.hemoSoft.repository.EnfermeiroRepository;

@Service
public class EnfermeiroService {
	
	@Autowired
	private EnfermeiroRepository repository;
	
	//Retorna uma lista com todos os enfermeiros inseridos
	public List<Enfermeiro> findAll() {
		return repository.findAll(); 
	}
	
	//Retorna um enfermeiro a partir do ID
	public Enfermeiro findOne(Long id) {
		return repository.findById(id).get();
	}
	
	//Salva ou atualiza um enfermeiro
	public Enfermeiro save(Enfermeiro enfermeiro) {
		return repository.saveAndFlush(enfermeiro);		
	}
	
	//Exclui um enfermeiro
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	//Busca enfermeiros pelo nome
	public List<Enfermeiro> findByNome(String nome){
		return repository.findByNomeContaining(nome);
	}

}
