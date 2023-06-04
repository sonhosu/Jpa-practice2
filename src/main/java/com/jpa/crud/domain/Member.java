package com.jpa.crud.domain;

import com.jpa.crud.dto.MemberDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter@Setter
@Entity
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;
    private String username;
    private LocalDateTime dateTime;


    public Member(MemberDto memberDto) {
        this.id= memberDto.getId();
        this.username = memberDto.getUsername();
        this.dateTime = memberDto.getDateTime();
    }

    @Builder
    public Member(String username, LocalDateTime dateTime) {
        this.username = username;
        this.dateTime = dateTime;
    }

    public Member() {

    }
}
