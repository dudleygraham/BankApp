package com.learning.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.entity.Staff;
import com.learning.repository.StaffRepository;
import com.learning.service.StaffService;

@Service
public class StaffServiceImpl implements StaffService {

	@Autowired
	StaffRepository staffRepository;
	
	@Override
	public Staff addStaff(Staff staff) {
		return staffRepository.save(staff);
	}

	@Override
	public Optional<Staff> getStaffById(long id) {
		return staffRepository.findById(id);
	}

	@Override
	public List<Staff> getAllStaffs() {
		return staffRepository.findAll();
	}

	@Override
	public String deleteStaffById(long id) {	
		staffRepository.deleteById(id);
		return "success";
	}

	@Override
	public Staff updateStaff(Staff staff) {
		return staff;
	}

	@Override
	public List<Staff> getAllStaffAscOrder() {
		return staffRepository.findAll();
	}

	@Override
	public List<Staff> getAllStaffDescOrder() {
		return staffRepository.findAll();
	}

	@Override
	public boolean existsById(long id) {
		return staffRepository.existsById(id);
	}

}
