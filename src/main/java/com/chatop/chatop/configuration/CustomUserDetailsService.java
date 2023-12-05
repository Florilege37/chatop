package com.chatop.chatop.configuration;

import java.util.ArrayList;
import java.util.List;

import com.chatop.chatop.entity.UserDB;
import com.chatop.chatop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository UserRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        UserDB user = UserRepository.findByName(name);
        return new User(user.getName(), user.getPassword(), getGrantedAuthorities("USER"));
    }

    private List<GrantedAuthority> getGrantedAuthorities(String role) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        return authorities;
    }
}
