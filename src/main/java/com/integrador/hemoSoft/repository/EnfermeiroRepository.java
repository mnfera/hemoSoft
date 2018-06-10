package com.integrador.hemoSoft.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.integrador.hemoSoft.model.Enfermeiro;

public interface EnfermeiroRepository extends JpaRepository<Enfermeiro, Long>{

	List<Enfermeiro> findByNomeContaining(String nome); 
}
