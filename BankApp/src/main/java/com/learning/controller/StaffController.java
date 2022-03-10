package com.learning.controller;
import com.learning.payload.response.CustomerResponse;
import com.learning.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/staff")
public class StaffController {
	@Autowired
	private CustomerService cs;
	@Autowired
	private CustomerResponse cr;
}