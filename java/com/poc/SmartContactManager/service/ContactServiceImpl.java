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
		// TODO Auto-generated method stub
		return contactRepo.findAll();
	}

	@Override
	public Contact getContact(int contactId) {
		// TODO Auto-generated method stub
		return contactRepo.findById(contactId).orElse(null);
	}

	@Override
	public Contact updateContact(Contact contact) {
		// TODO Auto-generated method stub
		return contactRepo.save(contact);
	}

	@Override
	public void deleteContact(int contactId) {
		// TODO Auto-generated method stub
		contactRepo.deleteById(contactId);;
	}

}
