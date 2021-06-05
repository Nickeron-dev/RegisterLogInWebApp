package com.application.RegisterLogInWebApp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	private Integer id = 0;
	
	@Column(name = "email", nullable = false, unique = true)
	@Email
	@Size(min =  13, message = "Email's must be between 13 and 64 characters")
	private String email;
	//message = "Password's length must be between 5 and 64 characters"

	@Column(name = "password", nullable = false, length = 64) // check if it will add to database before check of after
	@Size(min = 5, max = 64)
	private String password;
	
	@Column(name = "firstName", nullable = false, length = 20)
	@Size(min = 3, max = 20, message = "First name's length must be between 3 and 20 characters")
	private String firstName;
	
	@Column(name = "lastName", nullable = false, length = 20)
	@Size(min = 2, max = 20, message = "Last name's length must be between 2 and 20 characters")
	private String lastName;

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
