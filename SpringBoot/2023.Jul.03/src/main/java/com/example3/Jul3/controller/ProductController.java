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

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductsRepository productsRepository;
    @Autowired
    ProductsReplyRepository productsReplyRepository;

    @PostMapping("insert")
    public String insert(ProductDTO product) {
        productsRepository.doInsert(product);
        return "redirect:/";
    }

    @GetMapping("view")
    public String view(Model model, ProductDTO product) {
        ProductDTO dbProduct = productsRepository.doSelectRow(product);
        ProductReplyDTO productReply = ProductReplyDTO
                .builder()
                .idx_products(product.getIdx())
                .build();
        List<ProductReplyDTO> productsReplyList = productsReplyRepository.doSelect(productReply);
        model.addAttribute("product", dbProduct);
        model.addAttribute("productsReplyList", productsReplyList);
        return "product/view";
    }
}
