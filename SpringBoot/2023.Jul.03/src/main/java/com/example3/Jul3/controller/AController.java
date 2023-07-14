package com.example3.Jul3.controller;

import com.example3.Jul3.dao.PeopleRepository;
import com.example3.Jul3.dao.PostsRepository;
import com.example3.Jul3.dao.ProductsRepository;
import com.example3.Jul3.dto.PeopleDTO;
import com.example3.Jul3.dto.PeopleDetailDTO;
import com.example3.Jul3.dto.ProductDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

@Controller
public class AController {
    @Autowired
    DataSource ds;
    @Autowired
    PostsRepository postsRepository;
    @Autowired
    PeopleRepository peopleRepository;
    @Autowired
    ProductsRepository productsRepository;
    @Autowired
    SqlSession sqlSession;


    @GetMapping("/")
    public String index(Model model) throws IOException { //model에다가 데이터 넣어서 가져감(프론트로)
//
//        ArrayList<PeopleDTO> pDto = new ArrayList<>();
//        ArrayList<PeopleDetailDTO> pdDto = new ArrayList<>();
//        ArrayList<ProductDTO> proDto = new ArrayList<>();
//        Connection conn = null;
//        try {
//            conn = ds.getConnection();
//            PreparedStatement pstmt = conn.prepareStatement("select * from people");
//            ResultSet rs = pstmt.executeQuery();
//            while (rs.next()) {
//                String name = rs.getString("name");
//                String age = rs.getString("age");
//                PeopleDTO p = new PeopleDTO(name, age);
//                pDto.add(p);
//            }
//            pstmt = conn.prepareStatement("select * from peopleDetail");
//            rs = pstmt.executeQuery();
//            while (rs.next()) {
//                int height = rs.getInt("height");
//                int weight = rs.getInt("weight");
//                PeopleDetailDTO pd = new PeopleDetailDTO(height, weight);
//                pdDto.add(pd);
//            }
//            pstmt = conn.prepareStatement("select * from products");
//            rs = pstmt.executeQuery();
//            while (rs.next()) {
//                String name = rs.getString("name");
//                int price = rs.getInt("price");
//                int quantity = rs.getInt("quantity");
//                int idx = rs.getInt("idx");
//                ProductDTO prod = new ProductDTO(idx, name, price, quantity);
//                proDto.add(prod);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (conn != null) try {
//                conn.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
        model.addAttribute("people", peopleRepository.doSelect());
//        model.addAttribute("peopleD", pdDto);
        model.addAttribute("products", productsRepository.doSelect());
        model.addAttribute("posts", postsRepository.doSelect());
        return "index";
    }

    @PostMapping("/post")
    public String post(String content, HttpServletRequest request) {
        System.out.println("예야");
        System.out.println(content);
        postsRepository.doInsert(content);
        return "redirect:/";
    }
}