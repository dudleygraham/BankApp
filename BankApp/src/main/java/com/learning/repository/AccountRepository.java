/**
 * 
 */
package com.learning.repository;

import com.learning.entity.Account;
import com.learning.entity.Staff;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	
//	@Transactional
//	@Override
//	<S extends Account> S save(S entity);
//	
//	@Transactional
//	@Override
//	<S extends Account> List<S> saveAll(Iterable<S> entities);
	
	public boolean existsById(long accountId);
	
//	boolean existsByAccountNo(long accountNo);
	
	//List<Account> findCustomerAccountById(long accountId);
}
