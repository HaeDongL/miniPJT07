package com.model2.mvc.web.product;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

@Controller
@RequestMapping("/product/*")
public class ProductController {
	///Field
	@Autowired
	@Qualifier("productServiceImpl")
	ProductService productServiceimpl;
	
	public ProductController() {
		System.out.println(this.getClass());
	}
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
	@RequestMapping(value = "addProduct", method=RequestMethod.GET)
	public String addProductView()throws Exception{
		System.out.println("/addProductView");
		return "/product/addProductView.jsp";
	}
	
	
	
	@RequestMapping(value = "addProduct", method=RequestMethod.POST)
	public String addProductAction(@ModelAttribute("product") Product product,
									MultipartHttpServletRequest mtfRequest)throws Exception{
		////////////////FileUpload ����////////////////////////////////////////
		String path = "C:\\Users\\bitcamp\\git\\miniPJT07\\07.Model2MVCShop(URI,pattern)\\src\\main\\webapp\\images\\uploadFiles\\";
		// => �������̰� ���⼭ ���� ����η� ǥ��... �ص� ��ġ�� �ٳ��� �ᱹ ������̸����� ã�ƿͼ� �����ؾ��ϴ°� ����.
		MultipartFile mf = mtfRequest.getFile("file");//=> �о�帱 ������ �ִ� input�� name
		// �� �κ��� getParameter�����ɷ� �о ���ε��ϸ� StringŸ������ ���� �̸��� ����.
		
		String originFileName = mf.getOriginalFilename();
		
		String setFile = System.currentTimeMillis()+originFileName;
		
		String selfFile = path+System.currentTimeMillis()+originFileName;
		
		System.out.println("addProductAction selfFile  "+selfFile);
		
		product.setFileName(setFile);
		
		mf.transferTo(new File(selfFile));
		
		/*
		 * try{
		 * 		mf.transferTo(new File(selfFile));
		 * }catch(IllegalStateException e) {
		 * 		e.printStackTrace();
		 * }catch (IOException e) {
		 * 		e.printStackTrace();
		 * }
		 */
		//////////////////////////////////////////////////////////////////////
		
		productServiceimpl.addProduct(product);
		
		
		return "/product/addProductView.jsp";
	}
	
	
	
	
	
	
	@RequestMapping("listProduct")
	public String listProductAction( @ModelAttribute Search search , Model model , HttpServletRequest request,
							@RequestParam String menu) throws Exception{
		
		System.out.println("listProduct");
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}System.out.println(search);
		search.setPageSize(pageSize);
		
		// Business logic ����
		Map<String , Object> map=productServiceimpl.listProduct(search);
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		// Model �� View ����
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		model.addAttribute("menu",menu);
		
		return "/product/listProduct.jsp";
	}
	
	@RequestMapping("getProduct")
	public String getProductAction(@RequestParam int prodNo,@RequestParam(defaultValue = "search") String menu, Model model) throws Exception {
		
		Product product = productServiceimpl.getProduct(prodNo);
		model.addAttribute("product",product);
		model.addAttribute("menu",menu);
		return "/product/getProduct.jsp";
	}
	
	@RequestMapping(value = "updateProduct",method = RequestMethod.GET)
	public String updateProductViewAction(@RequestParam int prodNo,
											@RequestParam String menu,
											Model model)throws Exception {
		
		Product product = productServiceimpl.getProduct(prodNo);
		model.addAttribute("product",product);
		model.addAttribute("menu",menu);
		System.out.println("/updateProductviewAction");
		return "/product/updateProductView.jsp";
	}
	
	
	
	//////////////////////////���� ������Ʈ ����//////////////////////////////
	@RequestMapping(value = "updateProduct",method = RequestMethod.POST)
	public String updateProductAction(@RequestParam int prodNo,
									  @RequestParam String menu,
									  @ModelAttribute Product product,
									  MultipartHttpServletRequest mtfRequest)throws Exception {
										//���ε��� ���� �����ΰ�ü ����(setter)
		
		
		////////////////FileUpload ����////////////////////////////////////////
		String path = "C:\\Users\\bitcamp\\git\\miniPJT07\\07.Model2MVCShop(URI,pattern)\\src\\main\\webapp\\images\\uploadFiles\\";
		// => �������̰� ���⼭ ���� ����η� ǥ��... �ص� ��ġ�� �ٳ��� �ᱹ ������̸����� ã�ƿͼ� �����ؾ��ϴ°� ����.
		MultipartFile mf = mtfRequest.getFile("file");//=> �о�帱 ������ �ִ� input�� name
		// �� �κ��� getParameter�����ɷ� �о ���ε��ϸ� StringŸ������ ���� �̸��� ����.
		
		String originFileName = mf.getOriginalFilename();
		
		String setFile = System.currentTimeMillis()+originFileName;
		
		String selfFile = path+System.currentTimeMillis()+originFileName;
		
		System.out.println("addProductAction selfFile  "+selfFile);
		
		product.setFileName(setFile);
		
		mf.transferTo(new File(selfFile));
		
		/*
		 * try{
		 * 		mf.transferTo(new File(selfFile));
		 * }catch(IllegalStateException e) {
		 * 		e.printStackTrace();
		 * }catch (IOException e) {
		 * 		e.printStackTrace();
		 * }
		 */
		//////////////////////////////////////////////////////////////////////
		
		
		
		System.out.println("updateProductAction menu : "+menu);
		System.out.println("/updateProduct.do");
		System.out.println(product);
		productServiceimpl.updateProduct(product);
		
		return "/product/getProduct";
	}
	
	//////////////////////////���� ������Ʈ ����//////////////////////////////
	
}
