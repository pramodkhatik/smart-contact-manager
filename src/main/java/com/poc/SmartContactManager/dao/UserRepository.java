package com.poc.SmartContactManager.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poc.SmartContactManager.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
