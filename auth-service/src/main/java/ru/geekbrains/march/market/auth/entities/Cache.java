package ru.geekbrains.march.market.auth.entities;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.stream.Collectors;

public class Cache {
    private User user;
    private UserDetails userDetails;


    public Cache(User user) {
        this.user = user;
        userDetails = new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList()));
    }

    public User getUser() {
        return user;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

}
