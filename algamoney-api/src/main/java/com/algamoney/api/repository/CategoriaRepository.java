/**
 * 
 */
package com.algamoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algamoney.api.model.Categoria;

/**
 * @author Morpheus
 *
 */
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	
	

}
