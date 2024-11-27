package com.example.demo.Service;

import java.lang.invoke.MethodHandles.Lookup;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.interceptor.CacheOperationInvoker.ThrowableWrapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.expression.spel.support.ReflectivePropertyAccessor.OptimalPropertyAccessor;
import org.springframework.stereotype.Service;

import com.example.Models.UserLogin;
import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;
import com.jtspringproject.JtSpringProject.dao.userDao;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserSVC {
	
	@Autowired
	UserRepository userRepository;
	private static final Logger logger=(Logger) LoggerFactory.getLogger(UserSVC.class);

	public Long registerUser(User user) {
		Long id =(long) -1;
		try {
			User savedUser=userRepository.save(user);
			id=savedUser.getId();
			logger.info("User added successfully to database.");
		}
		catch (Exception e) {
			logger.info("Unable to save User Information");
		}
		return id;
	}
	
	public void removeUser(Long id) throws IllegalArgumentException, Exception {
		try {
			if(userRepository.findById(id).isEmpty()) {
				throw new EmptyResultDataAccessException(0);
			}
			userRepository.deleteById(id);
		    logger.info("User deleted sucessfully.");
		} catch (EmptyResultDataAccessException e) {
			logger.info("User does not exits.");
			throw new IllegalArgumentException("User not found");
		}
	}
	
	
	
	public List<User> getAllUsers(){
		List<User>users=userRepository.findAll();
		logger.info("Users data fetched successfully.");
		return users;
	}
	
	public User getUserById(Long id) throws Exception {
		User user=null;
		try {
		    Optional<User>	userData=userRepository.findById(id);
		    user=userData.orElse(null);
		    if(user==null)throw new Exception("User does not exist");
		    logger.info("User retrive from Id");
		}
		catch (Exception e) {
			logger.info("User does not exits");
			throw new Exception("User does not exist");
		}
		return user;
	}

	public Long getIdByCredentials(UserLogin userLogin) throws Exception {
		Long id=userRepository.findIdByEmailAndPassword(userLogin.getEmail(), userLogin.getPassword());
		if(id==null)throw new Exception("Invalid Credentials");
		return id;
	}

	public User updateUserInfo(User user) throws Exception {
		return userRepository.findById(user.getId())
		.map(existingUser->{
			// Set the attribute to existing user
			existingUser.setName(user.getName());
			existingUser.setContact(user.getContact());
			existingUser.setEmail(user.getEmail());
			existingUser.setPassword(user.getPassword());
			
			// Save new attribute to database.
			return userRepository.save(existingUser);
		})
		.orElseThrow(()->new EntityNotFoundException("Not found Entity"));
	}
}
