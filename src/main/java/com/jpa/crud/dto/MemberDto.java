package com.jpa.crud.dto;

import com.jpa.crud.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter@Setter
@NoArgsConstructor
public class MemberDto {
    private Long id;
    private String username;
    private LocalDateTime dateTime;

    public MemberDto(Member member) {
        this.id = member.getId();
        this.username=member.getUsername();
        this.dateTime = LocalDateTime.now();
    }

    public Member toEntity (){
        return Member.builder()
                .username(username)
                .dateTime(LocalDateTime.now())
                .build();
    }
}
