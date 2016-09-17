package br.com.ilegra.test.model;

import java.util.*;
import br.com.ilegra.test.entities.*;

public class SalesmanList {

	private static SalesmanList instance = null;
	private HashSet<Salesman> salesmanList = new HashSet<>();
	
	private SalesmanList(){
	}
	
	public static SalesmanList getInstance(){
		if (SalesmanList.instance == null){
			SalesmanList.instance = new SalesmanList();
		}
		return SalesmanList.instance;
	}

	public void add(Salesman salesman){
		if (!salesmanList.contains(salesman)){
			this.salesmanList.add(salesman);
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
	
	public void reset(){
		this.salesmanList = new HashSet<>();
	}
	
}
