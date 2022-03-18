package com.learning.service;

import java.util.Optional;

import com.learning.entity.Role;
import com.learning.enums.RoleType;

public interface RoleService {
 public Role addRole(Role role);
 public Optional<Role> findByRoleName(RoleType roleName);
}
