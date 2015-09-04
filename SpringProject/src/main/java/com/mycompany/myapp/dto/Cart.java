package com.mycompany.myapp.dto;

import java.util.Date;

public class Cart {

	private int cart_no;
	private int cart_amount;
	private Date cart_date;
	private String member_id;
	private int product_no;

	public int getcartNo() {
		return cart_no;
	}
	public void setcartNo(int cart_no) {
		this.cart_no = cart_no;
	}
	public int getcartAmount() {
		return cart_amount;
	}
	public void setcartAmount(int cart_amount) {
		this.cart_amount = cart_amount;
	}
	public Date getcartDate() {
		return cart_date;
	}
	public void setcartDate(Date cart_date) {
		this.cart_date = cart_date;
	}
	public String getmemberId() {
		return member_id;
	}
	public void setmemberId(String member_id) {
		this.member_id = member_id;
	}
	public int getproductNo() {
		return product_no;
	}
	public void setproductNo(int product_no) {
		this.product_no = product_no;
	}


}
