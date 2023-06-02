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

    private MemberRepository memberRepository;


    public MemberDto createMember(Member member){
        log.info("member={}",member);

        Member saveMember = memberRepository.save(member);
        MemberDto memberDto = new MemberDto(saveMember);

        return memberDto;



    }
}
