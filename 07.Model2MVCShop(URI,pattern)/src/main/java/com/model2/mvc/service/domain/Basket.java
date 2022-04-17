package com.model2.mvc.service.domain;

import java.sql.Date;

public class Basket {
	private int basketNo;
	private String userId; // basket���� ����ã���� ���
	private int prodNo; // product�� join�� ���
	private String prodName; // join�ؼ� ������ ���� binding
	private int price; //	join�ؼ� ������ ���� binding
	private String tranCode; // join�ؼ� ������ ���� binding
	private Date regDate; // �ٱ��Ͽ� �߰��� ��¥
	
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
