package com.poc.SmartContactManager.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
	public ResponseEntity<?> saveContact(@Valid @RequestBody Contact contact, BindingResult bindingResult){
		if(bindingResult.hasFieldErrors()) {
			List<FieldError> errors=bindingResult.getFieldErrors();
			List<String> errorMessage = new ArrayList<>();
			
			for(FieldError error:errors) {
				errorMessage.add(error.getDefaultMessage());
			}
			
			Map<String,Object> errorResponse = new HashMap<>();
			errorResponse.put("errors", errorMessage);
			return ResponseEntity.badRequest().body(errorResponse);
		}
		
		contactService.save(contact);
		return ResponseEntity.ok(contact);
	}
	
	@PutMapping("/contacts")
	public ResponseEntity<Contact> updateContact(@Valid @RequestBody Contact contact){
		contactService.updateContact(contact);
		return ResponseEntity.ok(contact);
	}
	
	@DeleteMapping("/contacts/{contactId}")
	public ResponseEntity<String> deleteContact(@PathVariable int contactId){
		contactService.deleteContact(contactId);
		return ResponseEntity.ok("Contact Deleted with Contact Id : "+contactId);
	}
	
	@GetMapping("/contacts/users/{userId}")
	public ResponseEntity<List<Contact>> getContactsByUserId(@PathVariable int userId){
		
		List<Contact> contactList = contactService.getContactByUserId(userId);
		
		return ResponseEntity.ok(contactList);
	}
}
