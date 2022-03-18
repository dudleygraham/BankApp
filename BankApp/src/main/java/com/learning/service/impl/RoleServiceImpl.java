package com.learning.service.impl;

import java.util.Optional;

import com.learning.entity.Role;
import com.learning.enums.RoleType;
import com.learning.repository.RoleRepository;
import com.learning.service.RoleService;

public class RoleServiceImpl implements RoleService {

	RoleRepository roleRepository;
	
	@Override
	public Role addRole(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public Optional<Role> findByRoleName(RoleType roleName) {
		return roleRepository.findByRoleName(roleName);
	}

}
