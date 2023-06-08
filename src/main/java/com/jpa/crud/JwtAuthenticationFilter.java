package com.jpa.crud;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpa.crud.auth.PrincipalDetails;
import com.jpa.crud.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


/*
스프링 시큐리티에서 UsernamePasswordAuthenticationFilter 가 있다.
UsernamePasswordAuthenticationFilter 필터는 /login 요청해서 username,password 전송하면 (POST) 동작함
하지만 securityConfig 파일에서 .formLogin().disable() 를 선언했기떄문에 지금 동작하지 않는상태인데
이 필터가 동작하게하기위해서는 SecurityConfig 파일에서 add필터로 추가해주면 된다.
*/

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private  final AuthenticationManager authenticationManager;

    // /login 요청을 하면 로그인 시도를 위해서 실행되는 메서드
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        System.out.println("JwtAuthenticationFilter:로그인 시도중");


        try {
            //1. username ,password 받는다.
            ObjectMapper om = new ObjectMapper();
            User user = om.readValue(request.getInputStream() , User.class);

            System.out.println("User:"+user);
            System.out.println(request.getInputStream().toString());

            //2.username,password로 인증되지않은 Authentication 객체 생성
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());

            logger.info("1");
            //3.authenticationManager 에게 토큰을 전달하고 인증역할 위임한다
            // 이렇게되면 authenticationManager가 AuthenticationProvider 를 호출한다
            // AuthenticationProvider 는 UserDetailsService 를 호출하고 UserDetailsService의 loadUserByUsername 메서드가 호출되는데
            // 여기서 DB와 사용자를 비교하는 로직이 수행하고 인증결과가 포함된 Authentication  객체를 리턴한다.
            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            logger.info("2");

            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
            //로그인이 되었다는 뜻.
            System.out.println("로그인 완료"+principalDetails.getUser().getUsername());
            //authentication 객체가 session 영역에 저장을 해야하고 그방법이 return해주면 됨 >
            //리턴의 이유는 권한 관리를 security가 대신 해주기 때문에 편하기위해 하는거임
            //굳이 JWT 를 토큰을 사용하면서 세션을 만들이유가없다. 단지 권한처리때문에 session을 만들어준다


            return authentication;

        } catch (IOException e) {
           e.printStackTrace();
        }
        System.out.println("============================");
        return null;
    }

    // attemptAuthentication 실행후 인증이 정상적으로 되었으면 successfulAuthentication 메서드가 실행된다
    // 여기서 JWT 토큰을 만들어서 request요청한 사용자에게 JWT 토큰을 response 해주면 된다.
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult ) throws IOException, ServletException {


        ObjectMapper objectMapper = new ObjectMapper();

        System.out.println("successfulAuthentication 실행됨 : 인증완료");

        PrincipalDetails principalDetailis = (PrincipalDetails) authResult.getPrincipal();

        //Hash 암호방식

        String jwtToken = JWT.create()
                .withSubject("cos토큰")
                .withExpiresAt(new Date(System.currentTimeMillis()+(60000*10)))//만료시간
                .withClaim("id", principalDetailis.getUser().getId())
                .withClaim("username", principalDetailis.getUser().getUsername())
                .sign(Algorithm.HMAC512("cos")); // 서버의 고유값
        // 응답 헤더부에 key,value 로 JWT 를 넣어서 내보내줌

        response.addHeader("Authorization",jwtToken);
        response.setContentType("application/json");
        User user = principalDetailis.getUser();
        user.setJwt(jwtToken);
        String result = objectMapper.writeValueAsString(user);  //Java객체를 json 으로  json 을 자바객체로 변환해줌
        response.getWriter().print(result);


    }


}
