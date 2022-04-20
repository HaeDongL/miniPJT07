package com.model2.mvc.service.purchaseimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseDao;
import com.model2.mvc.service.purchase.PurchaseService;

@Service("purchaseServiceImpl")
public class PurchaseServiceImpl implements PurchaseService {
	
	///Field
	@Autowired
	@Qualifier("purchaseDaoImpl")
	PurchaseDao purchaseDaoImpl;
	
	@Override
	public void addPurchase(Purchase purchase) throws Exception {
		purchaseDaoImpl.insertPurchase(purchase);
	}

	@Override
	public Map<String,Object> listPurchase(Map<String, Object> map) throws Exception {
		return purchaseDaoImpl.listPurchase(map);
	}

	@Override
	public Purchase getPurchase(String tranNo) throws Exception {
		return purchaseDaoImpl.getPurchase(tranNo);
	}

	@Override
	public void updatePurchase(Purchase purchase) throws Exception {
		purchaseDaoImpl.updatePurchase(purchase);
	}

	@Override
	public void deletePurchase(int tranNo) throws Exception {
		purchaseDaoImpl.deletePurchase(tranNo);
	}

	@Override
	public void updateTranCode(Map<String,Object> map) throws Exception {
		purchaseDaoImpl.updateTranCode(map);
		
	}

	@Override
	public Map<String, Object> requsetPuerchaseList(Map<String, Object> map) throws Exception {
		
		return purchaseDaoImpl.requestPurchaseList(map);
	}


	

}
