package net.javaguides.sms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "students")
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "email", unique = true)
	private String email;

	@Column(name = "currentOrg")
	private String currentOrg;

	@Column(name = "role")
	private String role;

	@Column(name = "password")
	private String password;

	@Column(name = "phone")
	private String phone;

	@Column(name = "address")
	private String address;

	@Column(name = "batch")
	private String batch;

	@Column(name = " schoolName")
	private String schoolName;

	@Column(name = "degree")
	private String degree;

	@Column(name = "orgRole")
	private String orgRole;

	public Student() {
		
	}

	public Student(Long id, String firstName, String lastName, String email, String currentOrg,
			String role, String password, String phone, String address, String batch, String schoolName,
			String degree, String orgRole) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.currentOrg = currentOrg;
		this.role = role;
		this.password = password;
		this.phone = phone;
		this.address = address;
		this.batch = batch;
		this.schoolName = schoolName;
		this.degree = degree;
		this.orgRole = orgRole;
	}

	public String getCurrentOrg() {		return currentOrg;	}

	public void setCurrentOrg(String currentOrg) {		this.currentOrg = currentOrg;	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public String getPassword() {return password;	}
	public void setPassword(String password) {		this.password = password;	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getOrgRole() {
		return orgRole;
	}

	public void setOrgRole(String orgRole) {
		this.orgRole = orgRole;
	}
}
