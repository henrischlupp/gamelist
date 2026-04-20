package com.gamelist.service;

import com.gamelist.model.User;
import com.gamelist.model.UserRepository;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;






 
// This class is used by spring security to authenticate and authorize user

@Service
public class UserDetailServiceImpl implements UserDetailsService  {

    private final UserRepository repository;

    // Constructor Injection
    public UserDetailServiceImpl(UserRepository userRepository) {
        this.repository = userRepository; 
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User curruser = repository.findByUsername(username);

        if (curruser == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        return new org.springframework.security.core.userdetails.User(
                curruser.getUsername(),
                curruser.getPasswordHash(),
                AuthorityUtils.createAuthorityList(curruser.getRole()));

    }
}
