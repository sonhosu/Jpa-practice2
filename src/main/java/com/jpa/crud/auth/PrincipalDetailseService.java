package com.jpa.crud.auth;


import com.jpa.crud.domain.User;
import com.jpa.crud.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailseService implements UserDetailsService {

    private  final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("PrincipalDetailsService 의 loadUserByUsername ");
        User user = userRepository.findByUsername(username);

        if (user == null){
            throw new IllegalArgumentException("일치하는 회원이 없습니다");
        }

        return new PrincipalDetails(user);
    }
}
