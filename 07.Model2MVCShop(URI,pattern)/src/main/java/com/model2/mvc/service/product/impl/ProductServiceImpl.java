package com.model2.mvc.service.product.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductDao;
import com.model2.mvc.service.product.ProductService;

@Service("productServiceImpl")
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	@Qualifier("productDaoImpl")
	ProductDao productDaoImpl;
	
	public ProductServiceImpl() {
		System.out.println("ProductServiceImpl 생성자 호출...");
	}
	
	
	@Override
	public void addProduct(Product product) throws Exception {
		productDaoImpl.addProduct(product);
	}


	@Override
	public Map<String, Object> listProduct(Search search) throws Exception {
		return productDaoImpl.listProduct(search);
	}


	@Override
	public Product getProduct(int prodNo)throws Exception{
		return productDaoImpl.getProduct(prodNo);
	}


	@Override
	public void updateProduct(Product product) throws Exception {
		productDaoImpl.updateProduct(product);
	}


	@Override
	public void deleteProduct(String prodNo) throws Exception {
		productDaoImpl.deleteProduct(prodNo);
	}


	

}
