package br.com.ilegra.test.entities;

/**
 * Salesman entity
 * @author Tales Viegas - tales@terra.com.br
 *
 */
public class Salesman{

	private long cpf;
	private String name;
	private double salario;
	private double totalSales = 0d;

	public long getCpf() {
		return cpf;
	}

	public void setCpf(long cpf) {
		this.cpf = cpf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}
	
	public double getTotalSales(){
		return this.totalSales;
	}
	
	public void addTotalSales(double totalSale){
		this.totalSales += totalSale;
	}
	
	/**
	 * Get a Salesman based on a splitted line
	 * @param line Splitted line
	 * @return Salesman
	 */
	public static Salesman fromArray(String[] line){
		Salesman salesman = new Salesman();
		salesman.setCpf(Long.parseLong(line[1]));
		salesman.setName(line[2]);
		salesman.setSalario(Double.parseDouble(line[3]));
		return salesman;
	}
	

}
