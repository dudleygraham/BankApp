/**
 * 
 */
package com.learning.repository;

import com.learning.entity.Account;
import com.learning.entity.Staff;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	public boolean existsById(long id);
}
