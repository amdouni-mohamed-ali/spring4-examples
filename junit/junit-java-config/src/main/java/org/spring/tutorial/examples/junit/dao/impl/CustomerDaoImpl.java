package org.spring.tutorial.examples.junit.dao.impl;

import org.spring.tutorial.examples.junit.dao.CustomerDao;
import org.spring.tutorial.examples.junit.entities.Customer;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerDaoImpl implements CustomerDao {

	private List<Customer> listCustomers;
	
	public CustomerDaoImpl() {
		listCustomers = new ArrayList<Customer>();
	}
	
	public Customer findById(Long id) {

		Optional<Customer> customer = listCustomers
				.stream()
				.filter(cust -> cust.getIdentifier()==id)
				.findFirst();
		if(customer.isPresent()){
			return customer.get();
		}else{
			return null;
		}
	}
	
	@PostConstruct
	public void init() {
		
		listCustomers.add(new Customer(1L, "customer1", "customer1"));
		listCustomers.add(new Customer(2L, "customer2", "customer2"));
		listCustomers.add(new Customer(3L, "customer3", "customer3"));
	}

}
