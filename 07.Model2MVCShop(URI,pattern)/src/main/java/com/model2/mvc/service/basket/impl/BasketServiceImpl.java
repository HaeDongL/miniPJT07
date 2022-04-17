package com.model2.mvc.service.basket.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.basket.BasketDao;
import com.model2.mvc.service.basket.BasketService;
import com.model2.mvc.service.domain.Basket;

@Service("basketServiceImpl")
public class BasketServiceImpl implements BasketService {
	
	@Autowired
	@Qualifier("basketDaoImpl")
	BasketDao basketDao;
	
	public BasketServiceImpl() {
		System.out.println(this.getClass().getName()+"start");
	}
	
	@Override
	public void addBasket(Basket basket) throws Exception {
		basketDao.insertBasket(basket);
	}

	@Override
	public Map<String, Object> listBasket(Map<String, Object> map) throws Exception {
		
		return basketDao.listBasket(map);
	}

	@Override
	public void deleteBasket(String basketNo) throws Exception {
		basketDao.deleteBasket(basketNo);
	}

}
