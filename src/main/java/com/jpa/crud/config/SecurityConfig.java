package com.jpa.crud.config;


import com.jpa.crud.JwtAuthenticationFilter;
import com.jpa.crud.JwtAuthorizationFilter;
import com.jpa.crud.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.CorsFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserRepository userRepository;
    private final CorsFilter corsFilter;

    @Bean
    public BCryptPasswordEncoder  passwordEncoder() {

        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //스프링 시큐리티 필터가 실행되기전에 MyFilter1 가 실행되도록 등록. .addFilter() 이렇게 해서 바로 걸수없다.
        // http.addFilterBefore(new MyFilter3(), SecurityContextPersistenceFilter.class);
        http.csrf().disable();
         //세션을 사용하지 않겠다는 선언
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
              .and()
              .addFilter(corsFilter) //cross origin  정책을 벗어날수있다.
            //  .formLogin().disable()
              .httpBasic().disable() //기본인증방식 제거.
                .addFilter(new JwtAuthenticationFilter(authenticationManager())) //JwtAuthenticationFilter 필터가 동작하도록 추가.
                .addFilter(new JwtAuthorizationFilter(authenticationManager() , userRepository)) //JwtAuthorizationFilter 필터가 동작하도록 추가.
              .authorizeRequests()
              .antMatchers("/api/v1/user/**")
              .access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
              .antMatchers("/api/v1/manager/**")
              .access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
              .antMatchers("/api/v1/admin/**")
              .access(" hasRole('ROLE_ADMIN')")
              .anyRequest().permitAll();


    }
}
