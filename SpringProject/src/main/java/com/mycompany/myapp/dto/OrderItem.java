package com.mycompany.myapp.dto;

public class OrderItem {
	int orderItemNo;
	int goodsItemNo;
	int orderNo;
	int orderAmount;
	
	public int getGoodsItemNo() {
		return goodsItemNo;
	}
	public void setGoodsItemNo(int goodsItemNo) {
		this.goodsItemNo = goodsItemNo;
	}
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
	
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

}
