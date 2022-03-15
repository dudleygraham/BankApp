/**
 * 
 */
package com.learning.repository;

import com.learning.entity.Beneficiary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {
	public boolean existsById(long id);
}
