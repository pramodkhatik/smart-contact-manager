package com.poc.SmartContactManager.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "CONTACT")
public class Contact {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "contactId")
	private int contactId;
	
	
	@Column(name = "name")
	private String name; 
	
	
	@Column(name = "secondName")
	private String secondName;
	
	
	@Column(name = "work")
	private String work;
	
	@Column(name = "designation")
	private String designation;
	
	
	@Column(name = "phone")
	private String phone;
	
	
	@Column(name="email")
	private String email;
	
	@Column(name = "image")
	private String image;

	@Column(name = "description", length = 500)
	private String description;
	
	@ManyToOne
	private User user;
	
	private String countryExtension;
	
	public Contact(){
		
	}
	
	public Contact(String name, String secondName, String work,String designation, String phone, String email,String image, String description,User user) {
		this.name = name;
		this.secondName = secondName;
		this.work = work;
		this.designation = designation;
		this.phone = phone;
		this.email=email;
		this.image = image;
		this.description = description;
		this.user = user;
		this.countryExtension= countryExtension;
	}
	
	public int getContactId() {
		return contactId;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getWork() {
		return work;
	}

	public void setWork(String work) {
		this.work = work;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getCountryExtension() {
	    return countryExtension;
	}

	public void setCountryExtension(String countryExtension) {
	    this.countryExtension = countryExtension;
	}



	@JsonProperty
	public User getUser() {
		return user;
	}

	@JsonProperty
	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Contacts [contactId=" + contactId + ", Name=" + name + ", secondName=" + secondName + ", work=" + work + ", designation=" + designation +", phone=" + phone
				+ ",email="+email+", image=" + image + ", description=" + description + ",countryExtension=" + countryExtension + "]";

	}

}
