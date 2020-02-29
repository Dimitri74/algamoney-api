/**
 * 
 */
package com.algamoney.api.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.algamoney.api.model.Pessoa;
import com.algamoney.api.repository.pessoa.PessoaRepositoryQuery;


/**
 * @author Morpheus
 *
 */
public interface PessoaRepository extends JpaRepository<Pessoa, Long>  {

	public Page<Pessoa> findByNomeContaining(String nome, Pageable pageable);
	
	//public List<Pessoa> listar();
	

	
	

}
