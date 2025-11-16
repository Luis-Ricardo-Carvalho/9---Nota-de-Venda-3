package model;

import java.util.ArrayList;
import java.util.List;

public class Sale {
	private List<SaleItem> items;
	private Payment payment;
	
	public Sale () {
		items = new ArrayList<>();
	}
	
	public boolean addItem (String code, int qtde) {  
		SaleItem saleItem = new SaleItem(code, qtde);
		
		if(!saleItem.getProduct().hasStock(qtde)) {
			System.out.println("Estoque insuficiente para o produto: "+saleItem.getProduct().getDescription());
			return false;
		}
		
		saleItem.getProduct().decreaseStock(qtde);
		return items.add(saleItem);	
	}
	
	public double getTotalAmount() {
		double totalAmount = 0;
		
		for (SaleItem saleItem : items)
			totalAmount += saleItem.getTotalAmount();
		
		return totalAmount;
	}
	
	public List<SaleItem> getItems() {
		return items;
	}
	
	public void cretePayment(String paymentMethod) {
		payment = DBMock.selectPayment(paymentMethod);
	}
	
	public double valueToBePaid() {
		if (payment == null)
			throw new IllegalStateException("Método de pagamento não definido");
		
		return payment.valueToBePaid(getTotalAmount());
	}
	
	public String paymentMethod() {
		return payment == null ? "" : payment.toString();
	}
}
