package com.mycompany.myapp.dto;

public class OrderItems {
	int orderItemNo;
	int productItemNo;
	int orderNo;
	int orderAmount;
	
	public int getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(int orderAmount) {
		this.orderAmount = orderAmount;
	}
	public int getOrderItemNo() {
		return orderItemNo;
	}
	public void setOrderItemNo(int orderItemNo) {
		this.orderItemNo = orderItemNo;
	}
	public int getProductItemNo() {
		return productItemNo;
	}
	public void setProductItemNo(int productItemNo) {
		this.productItemNo = productItemNo;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

}
