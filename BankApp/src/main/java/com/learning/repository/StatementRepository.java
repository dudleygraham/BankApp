/**
 * 
 */
package com.learning.repository;

import com.learning.entity.Statement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StatementRepository extends JpaRepository<Statement, Long> {
	public boolean existsById(long id);
}
