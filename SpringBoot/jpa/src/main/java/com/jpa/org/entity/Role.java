package com.jpa.org.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Role {

    //PK_auto increasement
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idx;
    //User,Admin,Manager
    private String name;

    public Role(int idx, String name) {
        this.idx = idx;
        this.name = name;
    }

    //FetchType.EAGER : left join 바로 하기에 조회할때 데이터를 더 많이 조회 하고
    //FetchType.Lazy : 필요할때 select 구문을 한번더 실행하기에
    //EAGER - 자원을 한꺼번에 많이 먹음 LAZY - 자원을 천천히 먹음
    @ManyToMany(mappedBy = "roles",fetch = FetchType.EAGER)
    private List<Member> members;

}
