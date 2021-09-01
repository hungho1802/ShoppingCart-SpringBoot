package com.poly.controller;

import com.poly.entity.Product;
import com.poly.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;


    @RequestMapping("/home/index/{pageNumber}")
    public String list(Model model,@RequestParam("cid")Optional<String> cid,
                       @PathVariable int pageNumber, HttpServletRequest request,
                       @RequestParam("sort")Optional<String> sort){

        PagedListHolder<Product> pages = (PagedListHolder<Product>) request.getSession().getAttribute("productList");
        int pageSize = 10;
        List<Product> list = productService.findAll();
        System.out.println(list.size());

            if(cid.isPresent()){
                list = productService.findByCategoryId(cid.get());
                pages = new PagedListHolder<>(list);
            }
            if(sort.isPresent()){
                if(sort.get().equals("newest")){
                    list = productService.getNewestProducts();
                    pages = new PagedListHolder<>(list);
                } else if (sort.get().equals("popular")) {
                    list = productService.getPopularProducts();
                    pages = new PagedListHolder<>(list);
                }else{
                    list = productService.getLastestProduct();
                    pages = new PagedListHolder<>(list);
                }
            }
            if(pages == null){
                pages = new PagedListHolder<>(list);
                pages.setPageSize(pageSize);
            }else{
                final int goToPage = pageNumber - 1;
                if(goToPage <= pages.getPageCount() && goToPage >= 0){
                    pages.setPage(goToPage);
                }
            }
            request.getSession().setAttribute("productList",pages);
            int current = pages.getPage() + 1;
            int begin = Math.max(1, current - list.size());
            int end = Math.min(begin + 5, pages.getPageCount());
            int totalPageCount = pages.getPageCount();
            String baseUrl = "/home/index/";

            model.addAttribute("beginIndex", begin);
            model.addAttribute("endIndex", end);
            model.addAttribute("currentIndex", current);
            model.addAttribute("totalPageCount", totalPageCount);
            model.addAttribute("baseUrl", baseUrl);
            model.addAttribute("items",pages);


            model.addAttribute("notifies",productService.getNotifications().subList(0,10));


//        model.addAttribute("items",list);
        return "product/list";
    }



    @RequestMapping("/product/details/{id}")
    public String detail(Model model, @PathVariable("id") Integer id){
        Product item = productService.findById(id);
        model.addAttribute("item",item);
        return "product/detail";
    }

    @RequestMapping(value = "/products" )
    public String rangeList(@RequestParam("min") Double min, Model model,
                         @RequestParam("max") Double max,HttpServletRequest request){
        List<Product> list = productService.findByRangeSlider(min,max);
        PagedListHolder<Product> pages  = new PagedListHolder<>(list);;

        pages.setPageSize(10);

        request.getSession().setAttribute("productList",pages);
        int current = pages.getPage() + 1;
        int begin = Math.max(1, current - list.size());
        int end = Math.min(begin + 5, pages.getPageCount());
        int totalPageCount = pages.getPageCount();
        String baseUrl = "/home/index/";

        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("baseUrl", baseUrl);
        model.addAttribute("items",pages);
        return "product/list";
    }



}
