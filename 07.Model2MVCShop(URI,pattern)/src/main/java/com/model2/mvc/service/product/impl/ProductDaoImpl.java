package com.model2.mvc.service.product.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductDao;

@Repository("productDaoImpl")
public class ProductDaoImpl implements ProductDao {
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	SqlSession sqlSession;
	
	///Constructor
	public ProductDaoImpl() {
		System.out.println("ProductDaoImpl 생성자 호출...");
	}
	
	@Override
	public void addProduct(Product product) throws Exception {
		product.setManuDate(product.getManuDate().replace("-", ""));
		sqlSession.insert("ProductMapper.insertProduct",product);
		int maxProdNo = sqlSession.selectOne("ProductMapper.getLastProdNo");
		product.setProdNo(maxProdNo);
		sqlSession.insert("ProductMapper.insertStock",product);
	}

	@Override
	public Map<String, Object> listProduct(Search search) {
		Map<String,Object> map = new HashMap<String, Object>();
		List<Product> list = new ArrayList<Product>();
		int totalCount = sqlSession.selectOne("ProductMapper.totalCount",search);
		list = sqlSession.selectList("ProductMapper.getProductList",search);
		System.out.println("ProductDao listProduct : "+list);
		
		
		map.put("list", list);
		map.put("totalCount", totalCount);
		
		return map;
	}

	@Override
	public Product getProduct(int prodNo) {
		Product product = sqlSession.selectOne("ProductMapper.getProduct",prodNo);
		System.out.println("ProductDao getProduct : "+product);
		return product;
	}

	@Override
	public void updateProduct(Product product) throws Exception {
		int i = sqlSession.update("ProductMapper.updateProduct",product);
		sqlSession.update("ProductMapper.updateStock",product);
		System.out.println("1이나오면 업데이트 완료 :"+i);
	}

	@Override
	public void deleteProduct(String prodNo) throws Exception {
		int i = sqlSession.delete("ProductMapper.deleteProduct",prodNo);
		System.out.println("1이나오면 삭제 완료 :"+i);
	}
	

	

}
