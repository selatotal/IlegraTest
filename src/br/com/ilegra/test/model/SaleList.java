package br.com.ilegra.test.model;

import java.util.*;
import br.com.ilegra.test.entities.*;

public class SaleList {

	private static SaleList instance = null;
	private HashSet<Sale> salesList = new HashSet<>();
	
	private SaleList(){
	}
	
	public static SaleList getInstance(){
		if (SaleList.instance == null){
			SaleList.instance = new SaleList();
		}
		return SaleList.instance;
	}

	public void add(Sale sales){
		if (!salesList.contains(sales)){
			this.salesList.add(sales);
		}
	}

	public void reset(){
		this.salesList = new HashSet<>();
	}
	
}
