package com.example3.Jul3.dao;

import com.example3.Jul3.dto.ProductDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductsRepository {
    @Autowired
    SqlSession sqlSession;

    public List<ProductDTO> doSelect() {
        return sqlSession.selectList("product.select");
    }

    public void doInsert(ProductDTO product) {
        sqlSession.insert("product.insert", product);
    }

    public ProductDTO doSelectRow(ProductDTO product) {
        return sqlSession.selectOne("product.selectRow", product);
    }
}
