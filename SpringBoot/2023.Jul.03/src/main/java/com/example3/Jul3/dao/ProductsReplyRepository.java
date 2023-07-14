package com.example3.Jul3.dao;

import com.example3.Jul3.dto.ProductDTO;
import com.example3.Jul3.dto.ProductReplyDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductsReplyRepository {
    @Autowired
    SqlSession sqlSession;

    public List<ProductReplyDTO> doSelect(ProductReplyDTO productReply) {
        return sqlSession.selectList("productsReply.select", productReply);
    }

    public void doInsert(ProductReplyDTO productReply) {
        sqlSession.insert("productsReply.insert", productReply);
    }

    public ProductReplyDTO doSelectRow(ProductReplyDTO productReply) {
        return sqlSession.selectOne("productsReply.selectRow", productReply);
    }

    public void doDelete(ProductReplyDTO productReply) {
        sqlSession.delete("productsReply.delete", productReply);
    }
}
