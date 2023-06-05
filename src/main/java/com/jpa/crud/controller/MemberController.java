package com.jpa.crud.controller;

import com.jpa.crud.domain.Member;
import com.jpa.crud.dto.MemberDto;
import com.jpa.crud.service.Memberservice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final Memberservice  memberservice;
//test
    @ResponseBody
    @PostMapping("/member")
    public MemberDto createMember(@RequestBody MemberDto memberDto){

        log.info("memberDto={}",memberDto);
        MemberDto member = memberservice.createMember(memberDto);

        return member;


    }
}
