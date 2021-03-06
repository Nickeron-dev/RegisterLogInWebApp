package com.application.RegisterLogInWebApp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id = 0;
	
	@NotEmpty(message = "User's email cannot be empty.")
    @Size(min = 13, max = 255)
	@Email
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	
	@NotEmpty(message = "User's password cannot be empty.")
    @Size(min = 5, max = 64)
	@Column(name = "password", nullable = false, length = 64) // when it already exists handle error
	private String password;
	
	@NotEmpty(message = "User's first name cannot be empty.")
    @Size(min = 2, max = 20)
	@Column(name = "firstName", nullable = false, length = 20)
	private String firstName;
	
	@NotEmpty(message = "User's last name cannot be empty.")
    @Size(min = 2, max = 20)
	@Column(name = "lastName", nullable = false, length = 20)
	private String lastName;
	
	// getters and setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
	
}
