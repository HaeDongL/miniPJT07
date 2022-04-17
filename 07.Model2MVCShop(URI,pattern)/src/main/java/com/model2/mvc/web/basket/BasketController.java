package com.model2.mvc.web.basket;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.basket.BasketService;
import com.model2.mvc.service.domain.Basket;
import com.model2.mvc.service.domain.User;

@Controller
@RequestMapping("/basket/*")
public class BasketController {
	///Field
	@Autowired
	@Qualifier("basketServiceImpl")
	BasketService basketServiceImpl;
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
	
	
	@RequestMapping("addBasket")
	public String addBasket(HttpSession session,
							@RequestParam int prodNo,
							Basket basket)throws Exception {
		
		User user = (User)session.getAttribute("user");
		basket.setPrice(prodNo);
		basket.setUserId(user.getUserId());
		
		basketServiceImpl.addBasket(basket);
		
		return "/product/getProduct";
	}
	
	
	@RequestMapping("listBasket")
	public String listBasketAction(ModelAndView modelAndView,
									HttpSession session,
									Search search,
									Map<String,Object> map,
									Model model)throws Exception{
			
			System.out.println("/listBasket.do");
			if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
			}
			search.setPageSize(10);
			System.out.println(search);
			
			// Business logic ผ๖วเ
			User user = (User)session.getAttribute("user");
			System.out.println("listBasket.do userId"+user.getUserId());
			map.put("user",user);
			map.put("search", search);
			map = basketServiceImpl.listBasket(map);
			Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
			System.out.println(resultPage);
			
			model.addAttribute("list",map.get("list"));
			model.addAttribute("resultPage", resultPage);


			return "/basket/listBasket.jsp";
		}
	
	@RequestMapping("deleteBasket")
	public String deleteBasket(@RequestParam String basketNo)throws Exception{
		basketServiceImpl.deleteBasket(basketNo);
		return "/basket/listBasket";
	}
}
