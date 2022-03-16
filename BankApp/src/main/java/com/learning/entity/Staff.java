package com.learning.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

import com.learning.enums.EnableType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(uniqueConstraints = {@UniqueConstraint(columnNames="staffId") })
@AllArgsConstructor
@NoArgsConstructor
public class Staff {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private int staffId;
  @NotBlank
  private String staffName;
  @NotBlank
  private String staffUserName;
  @NotBlank
  private String staffPassword;
  @Enumerated(EnumType.STRING)
  private EnableType status; 
}
