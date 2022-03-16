/**
 * 
 */
package com.learning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.entity.Staff;



@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
	public boolean existsById(long id);
}
