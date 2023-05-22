package com.poc.SmartContactManager.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "image")
	private String image;

	@Column(name = "description", length = 500)
	private String description;
	
	public Contact(){
		
	}
	
	public Contact(String name, String secondName, String work, String phone, String image, String description) {
		this.name = name;
		this.secondName = secondName;
		this.work = work;
		this.phone = phone;
		this.image = image;
		this.description = description;
	}
	
	public int getContactId() {
		return contactId;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;
	}

	public String getname() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
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
	
	@Override
	public String toString() {
		return "Contacts [contactId=" + contactId + ", Name=" + name + ", secondName=" + secondName + ", work=" + work + ", phone=" + phone
				+ ", image=" + image + ", description=" + description + ", ]";

	}

}
