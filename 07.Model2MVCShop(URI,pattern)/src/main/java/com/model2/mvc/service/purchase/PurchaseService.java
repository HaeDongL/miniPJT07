package com.model2.mvc.service.purchase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.model2.mvc.service.domain.Purchase;

public interface PurchaseService {
	public void addPurchase(Purchase purchase)throws Exception;
	
	public Map<String,Object> listPurchase(Map<String, Object> map)throws Exception;
	
	public Purchase getPurchase(String tranNo)throws Exception;
	
	public void updatePurchase(Purchase purchase)throws Exception;
	
	public void deletePurchase(int tranNo)throws Exception;
	
	public void updateTranCode(Map<String,Object> map)throws Exception;
	
	public Map<String,Object> requsetPuerchaseList(Map<String, Object> map) throws Exception;
}
