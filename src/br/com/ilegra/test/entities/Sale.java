package br.com.ilegra.test.entities;

import br.com.ilegra.test.model.SalesmanList;

public class Sale implements Comparable<Sale> {

	private long id;
	private Salesman salesman;
	private double total;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Salesman getSalesman() {
		return salesman;
	}

	public void setSalesman(Salesman salesman) {
		this.salesman = salesman;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	@Override
	public int compareTo(Sale sale) {
		return Long.compare(this.id, sale.id);
	}

	public static Sale fromArray(String[] line) {
		Sale sale = new Sale();
		sale.setId(Long.parseLong(line[1]));

		// Parse and calculate Sale value
		double totalSale = 0;
		String[] strItems = line[2].replace("[", "").replace("]", "").split(",");
		if (strItems.length > 0){
			for(String strItem : strItems){
				String[] itemSplit = strItem.split("-");
				if (itemSplit.length == 3){
					double totalItem = Double.parseDouble(itemSplit[1]) * Double.parseDouble(itemSplit[2]);
					totalSale += totalItem;
				}
			}
		}
		sale.setTotal(totalSale);
		return sale;

	}

}
