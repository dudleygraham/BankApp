package com.learning.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.dto.Address;

public interface AddressRepo extends JpaRepository<Address, Long> {

}
