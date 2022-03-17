package com.learning.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.learning.enums.RoleType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter 
@Getter
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Role {
	@Id
	private long roleId;
	@NotNull
	@Enumerated(EnumType.STRING)
	private RoleType roleName;
}
