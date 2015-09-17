package com.mycompany.myapp.dto;

import java.util.Date;

public class Cart {

	private int cart_no;
	private int cart_amount;
	private Date cart_date;
	private String member_id;
	private int goods_no;

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
	public int getGoods_no() {
		return goods_no;
	}
	public void setGoods_no(int goods_no) {
		this.goods_no = goods_no;
	}


}
