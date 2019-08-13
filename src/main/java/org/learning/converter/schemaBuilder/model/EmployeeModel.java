package org.learning.converter.schemaBuilder.model;

import org.learning.converter.employee.Gender;

public class EmployeeModel {

  private int id;
  private String firstName;
  private String lastName;
  private String email;
  private Gender gender;

  public EmployeeModel() {
  }

  public EmployeeModel(int id, String firstName, String lastName, String email,
      Gender gender) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.gender = gender;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

}

