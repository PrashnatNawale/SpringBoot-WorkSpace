package com.example.demo.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.Models.UserLogin;
import com.example.demo.Entity.User;
import com.example.demo.Service.UserSVC;


@RestController
@RequestMapping("/User")
public class UserController {
	
	@Autowired
	UserSVC userSVC;
	
	private static final Logger logger =LoggerFactory.getLogger(UserController.class);
	
	// Fetch Complete List of User
	@GetMapping("/")
	public List<User> getUsers() {
		logger.info("Request receive to get all users.");
		List<User> users=userSVC.getAllUsers(); 
		logger.info("List of users deliver to Request.");
		return users;
	}
	// Get User information using Id.
	@GetMapping("/{id}")
	public ResponseEntity<?> getUser(@PathVariable Long id) {
		logger.info("Fetching information of user {}",id);
		User user=null;
		try {
			user=userSVC.getUserById(id);
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("User not found");
		}
		logger.info("Response return to request of user by id={}",id);
		return ResponseEntity.ok(user);
		
	}

	// Delete user from database.
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		logger.info("Request receive to delete user {}",id);
		ResponseEntity<?> responseEntity;
	    try {
	        userSVC.removeUser(id);
	    	responseEntity=ResponseEntity.ok("User deleted from database");
	    }
	    catch(IllegalArgumentException ex) {
	    	responseEntity=ResponseEntity.status(HttpStatus.BAD_REQUEST)
	    			.body(ex.getMessage());
	    }
	    catch(Exception ex) {
	    	responseEntity=ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	    			.body(ex.getMessage());
	    }
	    finally {
	    	logger.info("Response return to request of user to delete id={}",id);
		}
	    return responseEntity;
	}
	// Add User to database.
	@PostMapping("/")
	public Long addUser(@RequestBody User user) {
		logger.info("Request receive to add User {}");
		logger.info("User Information name={},email={},contact={},password:{}",user.getName(),user.getEmail(),user.getContact(),user.getPassword());
		return userSVC.registerUser(user);
	}
	// Retrive User Id using Credentials
	@PostMapping("/getUserId")
	public ResponseEntity<?> getUserId(@RequestBody UserLogin userLogin) throws Exception {
		logger.info("Request receive to get Id from Credentials.");
		try {
			Long id= userSVC.getIdByCredentials(userLogin);		
			return ResponseEntity.ok(id);
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Credential not found.");
		}
	}
	// Update all data of existing user.
	 @PutMapping("/{id}")
	public ResponseEntity<?> updateAllUserfeild(@PathVariable Long id,@RequestBody User user) {
		boolean result=false;
		logger.info("Put request receive to update user info id={}",id);
		user.setId(id);
		try {
			userSVC.updateUserInfo(user);
			logger.info("Path Request processed.");
			return  ResponseEntity.ok(user);
		}
		catch(IllegalArgumentException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("User does not exits for updations");
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Their might internal server error.Please try again later.");
		}
	}

}
