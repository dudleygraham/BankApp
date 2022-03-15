package com.learning.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	Boolean existsByUsername(String username);
//	Boolean existsByEmail(String email);
	Optional<Customer> findByUsername(String username);
}
