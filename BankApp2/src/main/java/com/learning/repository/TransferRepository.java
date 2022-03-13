/**
 * 
 */
package com.learning.repository;

import com.learning.entity.Transfer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long> {
	public boolean existsById(long id);
}
