package br.com.ilegra.test.entities;

public class Customer implements Comparable<Customer>{

	private long cnpj;
	private String name;
	private String businessArea;


	public long getCnpj() {
		return cnpj;
	}

	public void setCnpj(long cnpj) {
		this.cnpj = cnpj;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBusinessArea() {
		return businessArea;
	}

	public void setBusinessArea(String businessArea) {
		this.businessArea = businessArea;
	}

	@Override
	public int compareTo(Customer customer){
		return Long.compare(this.cnpj, customer.cnpj);
	}
	
	public static Customer fromArray(String[] line){
		Customer customer = new Customer();
		customer.setCnpj(Long.parseLong(line[1]));
		customer.setName(line[2]);
		customer.setBusinessArea(line[3]);
		return customer;
		
	}
	
	
}
