package com.poc.SmartContactManager.service;

import java.util.List;

import com.poc.SmartContactManager.entity.Contact;

public interface ContactService {
	
	void save(Contact contact);
	
	List<Contact> getContacts();
	
	Contact getContact(String Name);
	
	Contact updateContact(Contact contact);
	
	void deleteContact(String Name);
	
	
	
	
	

}
