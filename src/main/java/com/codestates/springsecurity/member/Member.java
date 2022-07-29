package com.codestates.springsecurity.member;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String role; // USER, MANAGER, ADMIN

    public List<String> getRoleList() {

        if (this.role.length() > 0) {
            return Arrays.asList(this.role.split(","));
        }

        return new ArrayList<>();
    }

    private LocalDateTime createdAt = LocalDateTime.now();
    private String provider;
    private String providerId;

}
