package com.poc.SmartContactManager.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poc.SmartContactManager.entity.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, String> {

}
