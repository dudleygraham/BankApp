package com.learning.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.learning.entity.Staff;

public interface StaffService {

	public Staff addStaff(Staff staff);
	public Optional<Staff> getStaffById(long id); 
	public List<Staff> getAllStaffs();
	public String deleteStaffById(long id);
	public Staff updateStaff(Staff staff);
	public List<Staff> getAllStaffAscOrder();
	public List<Staff> getAllStaffDescOrder();
	public boolean existsById(long id);

}
