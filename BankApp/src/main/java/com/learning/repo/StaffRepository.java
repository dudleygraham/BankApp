package com.learning.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.entity.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {

}
