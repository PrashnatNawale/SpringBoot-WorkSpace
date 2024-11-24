package com.example.demo.Service;

import java.lang.invoke.MethodHandles.Lookup;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.expression.spel.support.ReflectivePropertyAccessor.OptimalPropertyAccessor;
import org.springframework.stereotype.Service;

import com.example.Models.UserLogin;
import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;

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
	
	public boolean removeUser(Long id) {
		boolean result=false;
		try {
			userRepository.deleteById(id);
			result=true;
		    logger.info("User deleted sucessfully.");
		} catch (EmptyResultDataAccessException e) {
			logger.info("User does not exits.");
		}
		return result;
	}
	
	
	
	public List<User> getAllUsers(){
		List<User>users=userRepository.findAll();
		logger.info("Users data fetched successfully.");
		return users;
	}
	
	public User getUserById(Long id) {
		User user=null;
		try {
		    Optional<User>	userData=userRepository.findById(id);
		    user=userData.orElse(null);
		    if(user==null)throw new Exception("User does not exist");
		    logger.info("User retrive from Id");
		}
		catch (Exception e) {
			logger.info("User does not exits");;
		}
		return user;
	}

	public Long getIdByCredentials(UserLogin userLogin) {
		Long id=userRepository.findIdByEmailAndPassword(userLogin.getEmail(), userLogin.getPassword());
		return id;
	}

	public boolean updateUserInfo(User user) {
		// TODO Auto-generated method stub
		return false;
	}
}
