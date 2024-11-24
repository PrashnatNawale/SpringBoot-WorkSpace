package com.example.demo.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@GetMapping("/")
	public List<User> getUsers() {
		logger.info("Request receive to get all users.");
		List<User> users=userSVC.getAllUsers(); 
		logger.info("List of users deliver to Request.");
		return users;
	}
	
	@GetMapping("/{id}")
	public User getUser(@PathVariable Long id) {
		logger.info("Fetching information of user {}",id);
		User user=userSVC.getUserById(id);
		logger.info("Response return to request of user by id={}",id);
		return user;
	}
	
	@DeleteMapping("/")
	public boolean deleteUser(@RequestParam Long id) {
		logger.info("Request receive to delete user {}",id);
		boolean result=userSVC.removeUser(id);
		logger.info("Response return to request of user to delete id={}",id);
		return result;
	}
	
	@PostMapping("/")
	public Long addUser(@RequestBody User user) {
		logger.info("Request receive to add User {}");
		logger.info("User Information name={},email={},contact={},password:{}",user.getName(),user.getEmail(),user.getContact(),user.getPassword());
		return userSVC.registerUser(user);
	}
	
	@PostMapping("/getUserId")
	public Long getUserId(@RequestBody UserLogin userLogin) {
		logger.info("Request receive to get Id from Credentials.");
		return userSVC.getIdByCredentials(userLogin);
	}
	
	@PutMapping("/")
	public boolean patchfeild(@RequestParam Long id,@RequestBody User user) {
		boolean result=false;
		logger.info("Patch Request receive for id={}",id);
		user.setId(id);
		result=userSVC.updateUserInfo(user);
		logger.info("Path Request processed.");
		return result;
	}

}
