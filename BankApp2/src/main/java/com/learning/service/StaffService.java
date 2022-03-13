package com.learning.service;

import java.util.List;
import java.util.Optional;

import com.learning.entity.Staff;

public interface StaffService {
	public Staff addStaff(Staff Staff);
	public Optional<Staff> getStaffById (long id);
	public List<Staff> getAllStaffs();
	public String deleteStaffById(long id);
	public Staff updateStaff(Staff Staff);
	public List<Staff> getAllStaffsAscOrder();
	public List<Staff> getAllStaffsDescOrder();
	public boolean existsbyId(long id);
}
