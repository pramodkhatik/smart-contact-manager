package com.poc.SmartContactManager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poc.SmartContactManager.entity.Contact;
import com.poc.SmartContactManager.service.ContactService;

@RestController
@RequestMapping("/api")
public class ContactController {
	@Autowired
	private ContactService contactService;
	
	@GetMapping("/contacts")
	public ResponseEntity<List<Contact>> getContacts(){
		List<Contact> contacts = contactService.getContacts();
		return ResponseEntity.ok(contacts);
	}
	
	@GetMapping("/contacts/{contactId}")
	public ResponseEntity<Contact> getContact(@PathVariable int contactId){
		Contact contact = contactService.getContact(contactId);
		return ResponseEntity.ok(contact);
	}
	
	@PostMapping("/contacts")
	public ResponseEntity<Contact> saveContact(@RequestBody Contact contact){
		contactService.save(contact);
		return ResponseEntity.ok(contact);
	}
	
	@PutMapping("/contacts")
	public ResponseEntity<Contact> updateContact(@RequestBody Contact contact){
		contactService.updateContact(contact);
		return ResponseEntity.ok(contact);
	}
	
	@DeleteMapping("/contacts/{contactId}")
	public ResponseEntity<String> deleteContact(@PathVariable int contactId){
		contactService.deleteContact(contactId);
		return ResponseEntity.ok("Contact Deleted with Contact Id : "+contactId);
	}
}
