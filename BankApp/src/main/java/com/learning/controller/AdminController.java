package com.learning.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.entity.Staff;
import com.learning.enums.EnableType;
import com.learning.exception.NoDataFoundException;
import com.learning.payload.request.SignupRequest;
import com.learning.payload.response.StaffResponse;
import com.learning.service.StaffService;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AdminController {
  @Autowired
  StaffService staffService;
  
  StaffResponse staffResponse;
//  @PostMapping("/staff")
//	public ResponseEntity<?> register(@Valid @RequestBody SignupRequest sr) {	
//		Staff s = new Staff();
//		s.setStaffUserName(sr.getUsername());
//		s.setStaffName(sr.getFullname());
//		s.setStaffPassword(sr.getPassword());
//		
//		Staff staff = ss.addStaff(s);
//		return ResponseEntity.status(201).body(staff);
//	}
  @GetMapping("/staff")
	public ResponseEntity<?> getAllStaffs(){
		List<Staff> Staffs = staffService.getAllStaffs();
		List<StaffResponse> srs = new ArrayList<>();
		Staffs.forEach(e->{StaffResponse sr = new StaffResponse();
		sr.setPassword(e.getStaffPassword());
		sr.setName(e.getStaffName());
		sr.setUsername(e.getStaffUserName());
		srs.add(sr);
		});
		if(srs.size()>0) {
		return ResponseEntity.ok(srs);
		}
		else {
			throw new NoDataFoundException("no Staff data");
		}
	}
  	@PutMapping(value="/{id}")
	public ResponseEntity<?> updateStaffById(@PathVariable("id")long id, EnableType status) {
		
	Staff Staff =	staffService.getStaffById(id).orElseThrow(()->new NoDataFoundException("data not available"));
	StaffResponse StaffResponse=  new StaffResponse();
	StaffResponse.setStatus(status);
	

	return ResponseEntity.status(200).body(StaffResponse);
	}
}