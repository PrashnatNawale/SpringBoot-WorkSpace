package com.example.demo.Controller;

import java.lang.invoke.MethodHandles.Lookup;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Address;
import com.example.demo.Service.AddressSVC;

@RestController
@RequestMapping("/address")
public class AddressController {
	@Autowired
	AddressSVC addressSVC;
	
	Logger logger=(Logger)LoggerFactory.getLogger(AddressController.class);
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getAllAddressById(@PathVariable Long id){
		logger.info("Request receive to get all address by user id={}",id);
		ResponseEntity responseEntity;
		List<Address>addresses=null;
		try {
			addresses=addressSVC.getAllAddressByUserId(id);
			responseEntity=ResponseEntity.ok(addresses);
			logger.info("Address fetch by User id={}",id);
		} catch (Exception e) {
			logger.info("Error to fetch address by id={}",id);
			responseEntity=ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(e.getMessage());
		}
		logger.info("Response return to client");
		return responseEntity;
	}

	@PostMapping("/{userId}")
	public ResponseEntity<?> saveAddressToUser(@PathVariable Long userId,@RequestBody Address address){
		logger.info("Request to save address");
		ResponseEntity responseEntity;
		try {
			Address savedAddress=addressSVC.saveAddress(userId,address);
			responseEntity=ResponseEntity.status(HttpStatus.OK)
					.body(savedAddress);
		}
		catch (Exception e) {
			responseEntity=ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(e.getMessage());
		}
		logger.info("Respond to save address");
		return responseEntity;
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateAddress(@PathVariable Long id,@RequestBody Address address){
		ResponseEntity<?> responseEntity;
		try {
			Address result=addressSVC.updateAddress(id,address);
			responseEntity=ResponseEntity.ok(result);
		}
		catch (Exception e) {
			responseEntity=ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(e.getMessage());
		}
		return responseEntity;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> removeAddressById(@PathVariable Long id){
		ResponseEntity<?> responseEntity;
		try {
			boolean result=addressSVC.removeAddressById(id);
			responseEntity=ResponseEntity.ok(result);
		}
		catch (Exception e) {
			responseEntity=ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(e.getMessage());
		}
		return responseEntity;
	}

}
