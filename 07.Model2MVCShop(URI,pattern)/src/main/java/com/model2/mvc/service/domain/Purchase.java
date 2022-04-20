package com.model2.mvc.service.domain;

import java.sql.Date;

public class Purchase {
	User buyer;
	Product purchaseProd;
	String receiverAddr;
	String receiverDate;
	String receiverRequest;
	Date orderDate;
	String paymentOption;
	String receiverName;
	String receiverPhone;
	String tranCode;
	int tranNo;
	int buyQuantity;
	
	public int getBuyQuantity() {
		return buyQuantity;
	}
	public void setBuyQuantity(int buyQuantity) {
		this.buyQuantity = buyQuantity;
	}
	public User getBuyer() {
		return buyer;
	}
	public Product getPurchaseProd() {
		return purchaseProd;
	}
	
	public Date getOrderDate() {
		return orderDate;
	}
	public String getPaymentOption() {
		return paymentOption;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public String getReceiverPhone() {
		return receiverPhone;
	}
	public String getTranCode() {
		return tranCode;
	}
	public int getTranNo() {
		return tranNo;
	}
	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}
	public void setPurchaseProd(Product purchaseProd) {
		this.purchaseProd = purchaseProd;
	}
	
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public void setPaymentOption(String paymentOption) {
		this.paymentOption = paymentOption.trim();
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}
	public void setTranCode(String tranCode) {
		this.tranCode = tranCode.trim();
	}
	public void setTranNo(int tranNo) {
		this.tranNo = tranNo;
	}
	public String getReceiverAddr() {
		return receiverAddr;
	}
	public String getReceiverDate() {
		return receiverDate;
	}
	public String getReceiverRequest() {
		return receiverRequest;
	}
	public void setReceiverAddr(String receiverAddr) {
		this.receiverAddr = receiverAddr;
	}
	public void setReceiverDate(String receiverDate) {
		this.receiverDate = receiverDate;
	}
	public void setReceiverRequest(String receiverRequest) {
		this.receiverRequest = receiverRequest;
	}
	@Override
	public String toString() {
		return "Purchase [buyer=" + buyer + ", purchaseProd=" + purchaseProd + ", receiverAddr=" + receiverAddr
				+ ", receiverDate=" + receiverDate + ", receiverRequest=" + receiverRequest + ", orderDate=" + orderDate
				+ ", paymentOption=" + paymentOption + ", receiverName=" + receiverName + ", receiverPhone="
				+ receiverPhone + ", tranCode=" + tranCode + ", tranNo=" + tranNo + ", buyQuantity=" + buyQuantity
				+ "]";
	}
	
	
	
	
	
}
