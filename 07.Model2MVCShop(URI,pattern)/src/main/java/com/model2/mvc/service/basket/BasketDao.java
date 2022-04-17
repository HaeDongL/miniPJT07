package com.model2.mvc.service.basket;

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Basket;

public interface BasketDao {
	public void insertBasket(Basket basket)throws Exception;
	
	public Map<String,Object> listBasket(Map<String, Object> map)throws Exception;
	
	public void deleteBasket(String basketNo)throws Exception;
	
}
