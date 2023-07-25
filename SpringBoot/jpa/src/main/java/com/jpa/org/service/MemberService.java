package com.jpa.org.service;

import com.jpa.org.entity.Member;
import com.jpa.org.entity.Role;
import com.jpa.org.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.List;

@Service
public class MemberService implements UserDetailsService {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    MemberRepository memberRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("username: " + username);
        Member member = memberRepository.findByUsername(username);
        System.out.println(member);
        if (member == null) {
            throw new UsernameNotFoundException("누구시죠?");
        }
        // spring security system

        //jsp-> session id menu etc
        return User.builder()
                .username(username)
                .password(member.getPassword())
                .roles(new String[]{"USER"})
                .build();
    }

    public void save(Member member) {
        boolean result = validate(member);
        if (result) {
            member.setPassword(passwordEncoder.encode(member.getPassword()));
            List<Role> list = Arrays.asList(new Role(1,"User"));
            member.setRoles(list);
            memberRepository.save(member);
        }
    }

    public boolean validate(Member member) {
        Member dmember = memberRepository.findByUsername(member.getUsername());
        if (dmember == null) {
            return true;
        } else {
            throw new IllegalStateException("이미 가입된 회원입니다");
        }
    }
}
