package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.Entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
	
	@Query("SELECT a FROM Address a WHERE a.userId.id = :id")
	List<Address> findAllByUserId(@Param("id") Long id);
	
}
