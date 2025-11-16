package view;

import model.Sale;
import model.SalePresenter;

public class Main {
	public static void main(String[] args) {
		Sale sale = new Sale();
		sale.addItem("pn", 5);
		sale.addItem("fm", 2);
		sale.addItem("ms", 10);

		sale.cretePayment("dc");
		
		SalePresenter presenter = new ConsoleSalePresenter();
		presenter.show(sale);
	}
}
