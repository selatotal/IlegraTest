package br.com.ilegra.test.main;

import java.io.*;
import java.util.*;

import br.com.ilegra.test.entities.*;

public class ProcessFile {

	private File currentFile;
	private HashSet<Customer> customerList = new HashSet<>();
	private HashSet<Salesman> salesmanList = new HashSet<>();
	private HashSet<Sale> saleList = new HashSet<>();

	// Get Most Expensive Sale and Worst Salesman
	private Sale mostExpensiveSale = null;
	private Salesman worstSalesman = null;

	
	public ProcessFile(File currentFile){
		this.currentFile = currentFile;
	}
	
	public void process(){
		
		// Open and scan file
		try {
			Scanner scanner = new Scanner(this.currentFile);
					
			while(scanner.hasNextLine()){
				
				String line = scanner.nextLine();
				
				// Parse line and process record type
				String[] parsedLine = line.split("ç");
				if (parsedLine.length > 0){
					if (parsedLine[0].equals("001")){
						// Salesman Data
						if (parsedLine.length == 4){
							Salesman salesman = Salesman.fromArray(parsedLine);
							salesmanList.add(salesman);
						}
					} else if (parsedLine[0].equals("002")){
						// Customer Data
						if (parsedLine.length == 4){
							Customer customer = Customer.fromArray(parsedLine);
							customerList.add(customer);
						}
					} else if (parsedLine[0].equals("003")){
						// Sales Data
						if (parsedLine.length == 4){
							Sale sale = Sale.fromArray(parsedLine);
							sale.setSalesman(this.getByName(parsedLine[3]));
							saleList.add(sale);
						}
					}
				}
			}
			scanner.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	private void summarize(){

		// Get Most Expensive Sale and Worst Salesman
		if (this.saleList.size() > 0){
			
			mostExpensiveSale = this.saleList.iterator().next();
			for (Sale sale : this.saleList){
				if (mostExpensiveSale.getTotal() > sale.getTotal()){
					mostExpensiveSale = sale;
				}
				sale.getSalesman().addTotalSales(sale.getTotal());
			}
			
			worstSalesman = this.salesmanList.iterator().next();
			for(Salesman salesman : this.salesmanList){
				if (worstSalesman.getTotalSales() < salesman.getTotalSales()){
					worstSalesman = salesman;
				}
			}
		}
	}
	
	public void moveProcessedFile(File pathTo){
		this.currentFile.renameTo(new File(pathTo.getAbsolutePath()+"//"+this.currentFile.getName()));
	}
	
	public void createOutputFile(File pathTo){
		
		// Create output file
		String currentFileName = this.currentFile.getName();
		int lastIndex = currentFileName.lastIndexOf(".dat");
		String outputFileName = pathTo.getAbsolutePath() + "//" + currentFileName.substring(0, lastIndex) + ".done.dat";
		File outputFile = new File(outputFileName);
		try {
			PrintWriter output = new PrintWriter(outputFile);
			this.summarize();
			output.println("Amount of clients: " + this.customerList.size());
			output.println("Amount of salesman: " + this.salesmanList.size());
			output.println("ID of Most Expensive Sale: " + this.mostExpensiveSale.getId());
			output.println("Worst Salesman Ever: " + this.worstSalesman.getName());
			output.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public Salesman getByName(String string) {
		for (Salesman salesman : this.salesmanList){
			if (salesman.getName().equals(string)){
				return salesman;
			}
		}
		return null;
	}
	

	
}
