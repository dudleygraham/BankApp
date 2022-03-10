package com.learning.entity;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

import com.learning.enums.EnableType;

import lombok.Data;
@Data
public class Staff {
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
