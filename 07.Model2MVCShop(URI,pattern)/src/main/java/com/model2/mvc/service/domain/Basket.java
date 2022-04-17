package com.model2.mvc.service.domain;

import java.sql.Date;

public class Basket {
	private int basketNo;
	private String userId; // basket에서 정보찾을때 사용
	private int prodNo; // product와 join시 사용
	private String prodName; // join해서 가져온 정보 binding
	private int price; //	join해서 가져온 정보 binding
	private String tranCode; // join해서 가져온 정보 binding
	private Date regDate; // 바구니에 추가한 날짜
	
	public int getPrice() {
		return price;
	}
	
	public String getTranCode() {
		return tranCode;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public void setTranCode(String tranCode) {
		this.tranCode = tranCode;
	}
	public int getBasketNo() {
		return basketNo;
	}
	public String getUserId() {
		return userId;
	}
	public int getProdNo() {
		return prodNo;
	}
	public String getProdName() {
		return prodName;
	}
	public void setBasketNo(int basketNo) {
		this.basketNo = basketNo;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setProdNo(int prodNo) {
		this.prodNo = prodNo;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "Basket [basketNo=" + basketNo + ", userId=" + userId + ", prodNo=" + prodNo + ", prodName=" + prodName
				+ ", price=" + price + ", tranCode=" + tranCode + ", regDate=" + regDate + "]";
	}
	
	
}
