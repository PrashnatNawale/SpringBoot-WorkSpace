package com.example.demo.Service;

import java.lang.System.LoggerFinder;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Address;
import com.example.demo.Entity.User;
import com.example.demo.Repository.AddressRepository;

import jakarta.persistence.EntityNotFoundException;
@Service
public class AddressSVC {
	
	@Autowired
	AddressRepository addressRepository;
	@Autowired
	UserSVC userSVC;
	
	
	Logger logger=(Logger)LoggerFactory.getLogger(AddressSVC.class);
	public List<Address> getAllAddressByUserId(Long id)throws Exception {
		List<Address> addresses=addressRepository.findAllByUserId(id);
		return addresses;
	}
	public Address saveAddress(Long userId, Address address) throws Exception {
		User user=userSVC.getUserById(userId);
		address.setUserId(user);
		Address savedAddress=addressRepository.save(address);
		return savedAddress;
	}
	public Address updateAddress(Long userId,Address address) throws Exception {
		// Verify that userId is must equals.
		return addressRepository.findById(address.getId())
				.map(existingAddress->{
					existingAddress.setHouseNumber(address.getHouseNumber());
					existingAddress.setBuildingName(address.getBuildingName());
					existingAddress.setStreet(address.getStreet());
					existingAddress.setLandMark(address.getLandMark());
					existingAddress.setCity(address.getCity());
					existingAddress.setDistrict(address.getDistrict());
					existingAddress.setState(address.getState());
					existingAddress.setPinCode(address.getPinCode());
					
					
					return addressRepository.save(existingAddress);
				})
				.orElseThrow(()->new EntityNotFoundException("User not found"));
				
	}
	public boolean removeAddressById(Long id)throws Exception {
		boolean result=false;
		try {
			if(addressRepository.findById(id).isEmpty())throw new Exception("User not found");
			addressRepository.deleteById(id);
			result=true;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return result;
	}
	


}
