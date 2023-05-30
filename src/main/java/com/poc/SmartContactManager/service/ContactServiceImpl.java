package com.poc.SmartContactManager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc.SmartContactManager.dao.ContactRepository;
import com.poc.SmartContactManager.entity.Contact;

@Service
public class ContactServiceImpl implements ContactService {
	
	@Autowired
	private ContactRepository contactRepo;
	
	
	public void save(Contact contact) {
		contactRepo.save(contact);
	}

	@Override
	public List<Contact> getContacts() {
		
		return contactRepo.findAll();
	}

	@Override
	public Contact getContact(int contactId) {
		
		return contactRepo.findById(contactId).orElse(null);
	}

	@Override
	public Contact updateContact(Contact contact) {
		
		return contactRepo.save(contact);
	}

	@Override
	public void deleteContact(int contactId) {
		
		contactRepo.deleteById(contactId);;
	}

	@Override
	public List<Contact> getContactByUserId(int userId) {

		return contactRepo.getByUserId(userId);
	}

}
