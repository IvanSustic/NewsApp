package org.example.androidnewsbackend.service;


import org.example.androidnewsbackend.model.UserInfo;
import org.example.androidnewsbackend.model.UserRole;
import org.example.androidnewsbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository repository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo user = this.repository.findByUsername(username);

        if(user == null) {
            throw new UsernameNotFoundException("Unknown user " + username);
        }

        List<UserRole> userRoleList = user.getRoles();

        String[] roles = new String[userRoleList.size()];

        for(int i = 0; i < userRoleList.size(); i++) {
            roles[i] = userRoleList.get(i).getName();
        }

        return User.withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(roles)
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }


    public UserInfo saveUser(UserInfo userInfo)  {
        List<UserRole> roles = new ArrayList<>();
        roles.add(new UserRole(1l,"USER"));
        userInfo.setRoles(roles);
        repository.save(userInfo);
        return userInfo;
    }
}
