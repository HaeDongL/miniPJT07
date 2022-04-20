package com.model2.mvc.service.purchaseimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseDao;

@Repository("purchaseDaoImpl")
public class PurchaseDaoImpl implements PurchaseDao {
	@Autowired
	@Qualifier("sqlSessionTemplate")
	SqlSession sqlSession;
	
	@Override
	public void insertPurchase(Purchase purchase) throws Exception {
		int i = sqlSession.insert("PurchaseMapper.insertPurchase",purchase);
		sqlSession.update("ProductMapper.minusStock",purchase);
		System.out.println("PurchaseDaoImpl Insert 여부 : "+i);
	}

	@Override
	public Map<String,Object> listPurchase(Map<String,Object> map) throws Exception {
		List<Purchase> list = sqlSession.selectList("PurchaseMapper.listPurchase",map);
		int totalCount = sqlSession.selectOne("PurchaseMapper.totalCount",map);
		map.put("list", list);
		map.put("totalCount",totalCount);
		return map;
	}

	@Override
	public Purchase getPurchase(String tranNo) throws Exception {
		
		return sqlSession.selectOne("PurchaseMapper.getPurchase",tranNo);
	}

	@Override
	public void updatePurchase(Purchase purchase) throws Exception {
		int quantity = sqlSession.selectOne("PurchaseMapper.selectBuyQuantity",purchase);
		
		System.out.println(quantity);
		System.out.println("updatePurchase buyQuantity "+purchase.getBuyQuantity());
		
		int i = sqlSession.update("PurchaseMapper.updatePurchase",purchase);
		if(purchase.getBuyQuantity() > quantity) {
			purchase.setBuyQuantity(purchase.getBuyQuantity()-quantity);
			sqlSession.update("ProductMapper.minusStock",purchase);
		}else if(purchase.getBuyQuantity() < quantity) {
			purchase.setBuyQuantity(quantity-purchase.getBuyQuantity());
			sqlSession.update("ProductMapper.plusStock",purchase);
		}
		System.out.println("업데이트 여부 : "+i);
	}

	@Override
	public void deletePurchase(int tranNo) throws Exception {
		int i = sqlSession.delete("PurchaseMapper.deletePurchase",tranNo);
		System.out.println("삭제 여부 : "+i);
	}

	@Override
	public void updateTranCode(Map<String,Object> map) throws Exception {
		int i = sqlSession.update("PurchaseMapper.updateTranCode",map);
		System.out.println("updateTranCode 여부 : "+i);
	}

	@Override
	public Map<String, Object> requestPurchaseList(Map<String, Object> map) throws Exception {
		List<Purchase> list = sqlSession.selectList("PurchaseMapper.requestPurchaseList",map);
		int totalCount = sqlSession.selectOne("PurchaseMapper.purchaseTotalCount",map);
		map.put("list", list);
		map.put("totalCount",totalCount);
		return map;
	}

}
