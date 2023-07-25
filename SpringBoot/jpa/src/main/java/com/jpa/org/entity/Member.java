package com.jpa.org.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Member extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto-increasement
    private int idx;
    private String username;
    private String password;
    private String email;
    private String age;
    private String gender;

    @ManyToMany
    @JoinTable(name = "member_role"
            ,joinColumns = @JoinColumn(name = "member_idx")
            ,inverseJoinColumns = @JoinColumn(name = "role_idx"))
    private List<Role> roles;

    @OneToMany(mappedBy = "member",fetch = FetchType.LAZY)
    private List<FreeBoard> boards = new ArrayList<>();
}
