/**
 * 
 */
package com.learning.repository;

import com.learning.entity.Customer;
import com.learning.enums.RoleType;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	public boolean existsById(long id);
	public Customer findById(long id);
	public Customer findByUsername(String user);
	
	@Transactional
	@Override
	<S extends Customer> S save(S entity);
	
	@Transactional
	@Override
	<S extends Customer> List<S> saveAll(Iterable<S> entities);
	
	
	boolean existsByUsername(String username);
	
	List<Customer> findCustomerByRoleName(RoleType roleName);
}
