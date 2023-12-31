package com.example3.Jul3.dao;

import com.example3.Jul3.dto.PostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PostsRepository {
    @Autowired
    DataSource dataSource;

    public void doInsert(String content) {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(
                    "insert into posts (content) values (?)");
            pstmt.setString(1, content);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) try {
                conn.close();
            } catch (Exception e) {
            }
        }
    }

    public List<PostDTO> doSelect() {
        List<PostDTO> list = new ArrayList<>();
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(
                    "select * from posts");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                PostDTO post = PostDTO.builder()
                        .idx(rs.getInt("idx"))
                        .content(rs.getString("content"))
                        .build();
//위 builder랑 같음 : PostDTO post = new PostDTO(rs.getInt("idx"),rs.getString("content"));
                list.add(post);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) try {
                conn.close();
            } catch (Exception e) {
            }
        }
        return list;
    }
}

