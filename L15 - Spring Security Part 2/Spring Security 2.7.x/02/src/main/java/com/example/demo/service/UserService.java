package com.example.demo.service;

import com.example.demo.entities.UserInfo;
import com.example.demo.repository.UserInfoRepository;
import com.example.demo.request.CreateUserRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {


    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    PasswordEncoder bCryptPasswordEncoder;


    public UserInfo persist(CreateUserRequestDto requestDto) {
        UserInfo userInfo = requestDto.toUserInfo();

        Optional<UserInfo> byEmail = userInfoRepository.findByEmail(userInfo.getEmail());
        if (byEmail.isPresent()) {
            throw new RuntimeException();
        }

        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        return saveOrUpdate(userInfo);
    }


    private UserInfo saveOrUpdate(UserInfo userInfo) {
        return userInfoRepository.save(userInfo);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> byEmail = userInfoRepository.findByEmail(username);
        if(byEmail.isPresent()){
            return byEmail.get();
        }

        /**
         * Create an anonymous user
         */
        return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return Arrays.asList(new SimpleGrantedAuthority("ANONYMOUS"));
            }

            @Override
            public String getPassword() {
                return null;
            }

            @Override
            public String getUsername() {
                return "ANONYMOUS";
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
        };

    }



}
