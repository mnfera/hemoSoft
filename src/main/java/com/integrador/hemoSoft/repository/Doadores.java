package com.integrador.hemoSoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.integrador.hemoSoft.model.Doador;

public interface Doadores extends JpaRepository<Doador, Long>{

	public List<Doador> findByNomeContaining(String nome);
}
