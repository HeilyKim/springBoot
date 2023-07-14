package com.example3.Jul3.dao;

import com.example3.Jul3.dto.PeopleDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PeopleRepository {
    @Autowired
    SqlSession sqlSession;

    public List<PeopleDTO> doSelect() {
        return sqlSession.selectList("people.select");
    }
}
