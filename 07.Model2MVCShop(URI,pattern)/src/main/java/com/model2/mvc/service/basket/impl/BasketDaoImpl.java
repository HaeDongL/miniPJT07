package com.model2.mvc.service.basket.impl;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.basket.BasketDao;
import com.model2.mvc.service.domain.Basket;

@Repository("basketDaoImpl")
public class BasketDaoImpl implements BasketDao {
	
	//Field
	@Autowired
	@Qualifier("sqlSessionTemplate")
	SqlSession sqlSession;
	
	public BasketDaoImpl() {
		System.out.println(this.getClass().getName()+"start...");
	}
	
	@Override
	public void insertBasket(Basket basket) throws Exception {
		sqlSession.insert("BasketMapper.insertBasket",basket);
		
	}

	@Override
	public Map<String, Object> listBasket(Map<String, Object> map) throws Exception {
		System.out.println("SERSERSERSERSERSER "+map.get("search"));
		List<Basket> list = sqlSession.selectList("BasketMapper.listBasket",map);
		int totalCount = sqlSession.selectOne("BasketMapper.totalCount",map);
		map.put("list",list);
		map.put("totalCount", totalCount);
		
		return map;
	}

	@Override
	public void deleteBasket(String basketNo) throws Exception {
		sqlSession.delete("BasketMapper.deleteBasket",basketNo);
		
	}

}
