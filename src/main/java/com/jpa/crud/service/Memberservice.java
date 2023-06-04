package com.jpa.crud.service;

import com.jpa.crud.domain.Member;
import com.jpa.crud.dto.MemberDto;
import com.jpa.crud.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class Memberservice {

    private final MemberRepository memberRepository;


    public MemberDto createMember(MemberDto memberDto){
        log.info("memberDto={}",memberDto);

        Member member = memberRepository.save(memberDto.toEntity());

        return new MemberDto(member);



    }
}
