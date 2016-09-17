package br.com.ilegra.test.model;

import java.util.*;
import br.com.ilegra.test.entities.*;

public class CustomerList {

	private static CustomerList instance = null;
	private HashSet<Customer> customerList = new HashSet<>();
	
	private CustomerList(){
	}
	
	public static CustomerList getInstance(){
		if (CustomerList.instance == null){
			CustomerList.instance = new CustomerList();
		}
		return CustomerList.instance;
	}

	public void add(Customer customer){
		if (!customerList.contains(customer)){
			this.customerList.add(customer);
		}
	}
	
	public void reset(){
		this.customerList = new HashSet<>();
	}
	
}
