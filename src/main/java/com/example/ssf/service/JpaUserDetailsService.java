package com.example.ssf.service;

import com.example.ssf.reposiotrty.UserRepository;
import com.example.ssf.security.SecurityUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service // this will be much easier, no need to make securityConfig more complex thant it is
@AllArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    // we have two choise is to @ this class with @service or use it in the bean of WebSecurity 'UserDetailsService'
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       var u = userRepository.findUserByUserName(username);
       // we need to define a class that has a contract of userDetails


        return u.map(SecurityUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("user name not found :"+ username));
    }
}
