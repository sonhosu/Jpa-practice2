package com.jpa.crud.controller;

import com.jpa.crud.domain.User;
import com.jpa.crud.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final UserRepository userRepository;

//test
   /* @ResponseBody
    @PostMapping("/join")
    public UserDto createMember(@RequestBody UserDto userDto){
        log.info("memberDto={}", userDto);
        UserDto member = memberservice.createMember(userDto);
        return member;
    }*/

    @PostMapping("/join")
    public String join(@RequestBody User user){
        log.info("user.getUsername={}",user.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles("ROLE_USER");
        userRepository.save(user);
        log.info("저장완료");
        return"회원가입 완료";

    }


   // @GetMapping("/api/v1/user/**")
    public String user(HttpServletRequest request){

        String jwt =request.getHeader("Authorization");
        log.info("jwt={}",jwt);


        return "user권한 인증완료";
    }

    // admin,manager 권한만 접근가능
    @GetMapping("/api/v1/manager")
    public String manager(){
        return "manager";
    }

    // admin 만 접근가능
    @GetMapping("/api/v1/admin")
    public String admin(){
        return "admin";
    }
}
