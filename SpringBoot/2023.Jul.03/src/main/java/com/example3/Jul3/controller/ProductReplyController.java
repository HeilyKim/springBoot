package com.example3.Jul3.controller;

import com.example3.Jul3.dao.ProductsReplyRepository;
import com.example3.Jul3.dao.ProductsRepository;
import com.example3.Jul3.dto.ProductDTO;
import com.example3.Jul3.dto.ProductReplyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/productsReply")
public class ProductReplyController {
    @Autowired
    ProductsReplyRepository productsReplyRepository;

    @GetMapping("insert")
    @ResponseBody //html을 찾는게 아니라 저 리턴된 문자열을 반환함
    public String insert(ProductReplyDTO productReply) {
        System.out.println(productReply);
        productsReplyRepository.doInsert(productReply);
        return "test";
    }

    @PostMapping("insert")
    public String pinsert(ProductReplyDTO productReply) {
        System.out.println(productReply);
        productReply.setRef_idx_reply(productReply.getIdx_reply());
        productReply.setRef_level(productReply.getRef_level()+1);
        productsReplyRepository.doInsert(productReply);
        return "redirect:/product/view?idx="+productReply.getIdx_products();
    }

    @GetMapping("view")
    public String view(Model model, ProductDTO product) {
//        ProductDTO dbProduct = productsReplyRepository.doSelectRow(product);
//        model.addAttribute("product", dbProduct);
        return "product/view";
    }
    @PostMapping("delete")
    @ResponseBody
    public String delete(ProductReplyDTO productReply){
        productsReplyRepository.doDelete(productReply);
        return "롸";
    }
}
