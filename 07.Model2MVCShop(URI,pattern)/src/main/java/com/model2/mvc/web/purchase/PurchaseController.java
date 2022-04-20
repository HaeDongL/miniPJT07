package com.model2.mvc.web.purchase;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;

@Controller
@RequestMapping("/purchase/*")
public class PurchaseController {
	///Field
	@Autowired
	@Qualifier("purchaseServiceImpl")
	PurchaseService purchaseServiceImpl;
	@Autowired
	@Qualifier("productServiceImpl")
	ProductService productServiceImpl;
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
	
	@RequestMapping(value = "addPurchase", method = RequestMethod.GET)
	public ModelAndView addPurchaseViewAction(HttpSession session, @RequestParam int prodNo,
										ModelAndView modelAndView) 
	throws Exception{
		
		User user = (User)session.getAttribute("user");
		System.out.println("/addpurchaseView.do userId"+user.getUserId());
		Product product = productServiceImpl.getProduct(prodNo);
		
//		model.addAttribute("user",user);
//		model.addAttribute("product",product);
		
		modelAndView.addObject("user", user);
		modelAndView.addObject("product", product);
		modelAndView.setViewName("/purchase/addPurchaseView.jsp");
		
		return modelAndView;
		
	}
	
	@RequestMapping(value = "addPurchase", method = RequestMethod.POST)
	public ModelAndView addPurchaseAction(@ModelAttribute("purchase") Purchase purchase,
									@ModelAttribute Product product,
									@RequestParam String buyerId,
									User user,ModelAndView modelAndView)throws Exception {
		user.setUserId(buyerId);
		System.out.println("/addPurchase.do userId"+buyerId);
		purchase.setPurchaseProd(product);
		purchase.setBuyer(user);
		purchaseServiceImpl.addPurchase(purchase);
		modelAndView.setViewName("/purchase/addPurchaseResultView.jsp");
		return modelAndView;
	}
	
	@RequestMapping("listPurchase")
	public ModelAndView listPurchaseAction(ModelAndView modelAndView,
											HttpSession session,
											Search search,
											Map<String,Object> map)throws Exception{

		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}System.out.println(search);
		search.setPageSize(pageSize);
		
		// Business logic 수행
		User user = (User)session.getAttribute("user");
		System.out.println("listPurchase userId"+user.getUserId());
		map.put("user",user);
		map.put("search", search);
		purchaseServiceImpl.listPurchase(map);
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		modelAndView.addObject("list",map.get("list"));
		modelAndView.addObject("resultPage", resultPage);
		modelAndView.setViewName("/purchase/listPurchase.jsp");
		
		return modelAndView;
	}
	
	@RequestMapping("getPurchase")
	public ModelAndView getPurchaseAction(ModelAndView modelAndView,
										 Purchase purchase,
										 @RequestParam String tranNo)throws Exception{
		purchase = purchaseServiceImpl.getPurchase(tranNo);
		
		modelAndView.addObject("purchase",purchase);
		modelAndView.setViewName("/purchase/getPurchase.jsp");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "updatePurchase",method = RequestMethod.GET)
	public ModelAndView updatePurchaseViewAction(ModelAndView modelAndView,
												Purchase purchase,
												Product product,
												HttpSession session,
												@RequestParam String tranNo,
												@RequestParam int prodNo)throws Exception{
		
		purchase = purchaseServiceImpl.getPurchase(tranNo);
		product = productServiceImpl.getProduct(prodNo);
		modelAndView.addObject("purchase",purchase);
		modelAndView.addObject("product",product);
		modelAndView.setViewName("/purchase/updatePurchaseView.jsp");
		
		
		return modelAndView;
	}
	
	@RequestMapping(value = "updatePurchase",method = RequestMethod.POST)
	public ModelAndView updatePurchaseAction(ModelAndView modelAndView,
											 HttpSession session,
											 User user,
											 @ModelAttribute Purchase purchase,
											 @ModelAttribute Product product,
											 @RequestParam String tranNo)throws Exception{
		user = (User)session.getAttribute("user");
		purchase.setBuyer(user);
		purchase.setPurchaseProd(product);
		System.out.println("user "+purchase.getBuyer().getUserId());
		System.out.println("prodNo"+product.getProdNo());
		
		System.out.println("updatePurchase.do purchase"+ purchase);
		
		purchaseServiceImpl.updatePurchase(purchase);
		
		modelAndView.setViewName("/purchase/getPurchase");
		
		return modelAndView;
	}
	
	@RequestMapping("updateTranCode")
	public ModelAndView updateTranCodeAction(ModelAndView modelAndView,
											 @RequestParam String tranCode,
											 @RequestParam int prodNo,
											 Map<String,Object> map,
											 @RequestParam(defaultValue = "") String menu)throws Exception{
		System.out.println("updateTranCode");
		map.put("prodNo", prodNo);
		map.put("tranCode", tranCode);
		purchaseServiceImpl.updateTranCode(map);
		if(menu.equals("manage")) {
			modelAndView.setViewName("/purchase/requestPurchaseList");
		}else {
			modelAndView.setViewName("/purchase/listPurchase");
		}
		return modelAndView;
	}
	
	@RequestMapping("requestPurchaseList")
	public ModelAndView requestPurchaseList(ModelAndView modelAndView,
											HttpSession session,   	
			                                Search search,
			                                @RequestParam String menu,
											Map<String,Object> map,
											Map<String,Object> resultMap)throws Exception{
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}System.out.println(search);
		search.setPageSize(pageSize);
		
		// Business logic 수행
		User user = (User)session.getAttribute("user");
		System.out.println("listPurchase userId"+user.getUserId());
		map.put("user",user);
		map.put("search", search);
		resultMap = purchaseServiceImpl.requsetPuerchaseList(map);
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		modelAndView.addObject("list",resultMap.get("list"));
		modelAndView.addObject("resultPage", resultPage);
		modelAndView.addObject("menu",menu);
		modelAndView.setViewName("/purchase/requestPurchaseList.jsp");
		
		
		
		return modelAndView;
	}
	
}
