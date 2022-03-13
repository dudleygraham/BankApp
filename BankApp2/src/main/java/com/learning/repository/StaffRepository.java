/**
 * 
 */
package com.learning.repository;

import com.learning.entity.Staff;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
	public boolean existsById(long id);
}
